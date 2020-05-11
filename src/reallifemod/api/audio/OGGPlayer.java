package de.ItsAMysterious.mods.reallifemod.api.audio;

import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.StreamState;
import com.jcraft.jogg.SyncState;
import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.Comment;
import com.jcraft.jorbis.DspState;
import com.jcraft.jorbis.Info;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownServiceException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class OGGPlayer implements Runnable {

   private final boolean debugMode = true;
   private URLConnection urlConnection = null;
   private InputStream inputStream = null;
   byte[] buffer = null;
   int bufferSize = 2048;
   int count = 0;
   int index = 0;
   byte[] convertedBuffer;
   int convertedBufferSize;
   private SourceDataLine outputLine = null;
   private float[][][] pcmInfo;
   private int[] pcmIndex;
   private Packet joggPacket = new Packet();
   private Page joggPage = new Page();
   private StreamState joggStreamState = new StreamState();
   private SyncState joggSyncState = new SyncState();
   private DspState jorbisDspState = new DspState();
   private Block jorbisBlock;
   private Comment jorbisComment;
   private Info jorbisInfo;


   public static void main(String[] args) {
      String url = args.length > 0?(url = args[0]):null;
      if(url != null) {
         OGGPlayer examplePlayer = new OGGPlayer(url);
         examplePlayer.run();
      } else {
         System.err.println("Please provide an argument with the file to play.");
      }

   }

   public OGGPlayer(String pUrl) {
      this.jorbisBlock = new Block(this.jorbisDspState);
      this.jorbisComment = new Comment();
      this.jorbisInfo = new Info();
      this.configureInputStream(this.getUrl(pUrl));
   }

   public URL getUrl(String pUrl) {
      URL url = null;

      try {
         url = new URL(pUrl);
      } catch (MalformedURLException var4) {
         System.err.println("Malformed \"url\" parameter: \"" + pUrl + "\"");
      }

      return url;
   }

   private void configureInputStream(URL pUrl) {
      try {
         this.urlConnection = pUrl.openConnection();
      } catch (UnknownServiceException var4) {
         System.err.println("The protocol does not support input.");
      } catch (IOException var5) {
         System.err.println("An I/O error occoured while trying create the URL connection.");
      }

      if(this.urlConnection != null) {
         try {
            this.inputStream = this.urlConnection.getInputStream();
         } catch (IOException var3) {
            System.err.println("An I/O error occoured while trying to get an input stream from the URL.");
            System.err.println(var3);
         }
      }

   }

   public void run() {
      if(this.inputStream == null) {
         System.err.println("We don\'t have an input stream and therefor cannot continue.");
      } else {
         this.initializeJOrbis();
         if(this.readHeader() && this.initializeSound()) {
            this.readBody();
         }

         this.cleanUp();
      }
   }

   private void initializeJOrbis() {
      this.debugOutput("Initializing JOrbis.");
      this.joggSyncState.init();
      this.joggSyncState.buffer(this.bufferSize);
      this.buffer = this.joggSyncState.data;
      this.debugOutput("Done initializing JOrbis.");
   }

   private boolean readHeader() {
      this.debugOutput("Starting to read the header.");
      boolean needMoreData = true;
      int packet = 1;

      while(needMoreData) {
         try {
            this.count = this.inputStream.read(this.buffer, this.index, this.bufferSize);
         } catch (IOException var4) {
            System.err.println("Could not read from the input stream.");
            System.err.println(var4);
         }

         this.joggSyncState.wrote(this.count);
         switch(packet) {
         case 1:
            switch(this.joggSyncState.pageout(this.joggPage)) {
            case -1:
               System.err.println("There is a hole in the first packet data.");
               return false;
            case 0:
            default:
               break;
            case 1:
               this.joggStreamState.init(this.joggPage.serialno());
               this.joggStreamState.reset();
               this.jorbisInfo.init();
               this.jorbisComment.init();
               if(this.joggStreamState.pagein(this.joggPage) == -1) {
                  System.err.println("We got an error while reading the first header page.");
                  return false;
               }

               if(this.joggStreamState.packetout(this.joggPacket) != 1) {
                  System.err.println("We got an error while reading the first header packet.");
                  return false;
               }

               if(this.jorbisInfo.synthesis_headerin(this.jorbisComment, this.joggPacket) < 0) {
                  System.err.println("We got an error while interpreting the first packet. Apparantly, it\'s not Vorbis data.");
                  return false;
               }

               ++packet;
            }

            if(packet == 1) {
               break;
            }
         case 2:
         case 3:
            switch(this.joggSyncState.pageout(this.joggPage)) {
            case -1:
               System.err.println("There is a hole in the second or third packet data.");
               return false;
            case 0:
            default:
               break;
            case 1:
               this.joggStreamState.pagein(this.joggPage);
               switch(this.joggStreamState.packetout(this.joggPacket)) {
               case -1:
                  System.err.println("There is a hole in the firstpacket data.");
                  return false;
               case 0:
               default:
                  break;
               case 1:
                  this.jorbisInfo.synthesis_headerin(this.jorbisComment, this.joggPacket);
                  ++packet;
                  if(packet == 4) {
                     needMoreData = false;
                  }
               }
            }
         }

         this.index = this.joggSyncState.buffer(this.bufferSize);
         this.buffer = this.joggSyncState.data;
         if(this.count == 0 && needMoreData) {
            System.err.println("Not enough header data was supplied.");
            return false;
         }
      }

      this.debugOutput("Finished reading the header.");
      return true;
   }

   private boolean initializeSound() {
      this.debugOutput("Initializing the sound system.");
      this.convertedBufferSize = this.bufferSize * 2;
      this.convertedBuffer = new byte[this.convertedBufferSize];
      this.jorbisDspState.synthesis_init(this.jorbisInfo);
      this.jorbisBlock.init(this.jorbisDspState);
      int channels = this.jorbisInfo.channels;
      int rate = this.jorbisInfo.rate;
      AudioFormat audioFormat = new AudioFormat((float)rate, 16, channels, true, false);
      javax.sound.sampled.DataLine.Info datalineInfo = new javax.sound.sampled.DataLine.Info(SourceDataLine.class, audioFormat, -1);
      if(!AudioSystem.isLineSupported(datalineInfo)) {
         System.err.println("Audio output line is not supported.");
         return false;
      } else {
         try {
            this.outputLine = (SourceDataLine)AudioSystem.getLine(datalineInfo);
            this.outputLine.open(audioFormat);
         } catch (LineUnavailableException var6) {
            System.out.println("The audio output line could not be opened due to resource restrictions.");
            System.err.println(var6);
            return false;
         } catch (IllegalStateException var7) {
            System.out.println("The audio output line is already open.");
            System.err.println(var7);
            return false;
         } catch (SecurityException var8) {
            System.out.println("The audio output line could not be opened due to security restrictions.");
            System.err.println(var8);
            return false;
         }

         this.outputLine.start();
         this.pcmInfo = new float[1][][];
         this.pcmIndex = new int[this.jorbisInfo.channels];
         this.debugOutput("Done initializing the sound system.");
         return true;
      }
   }

   private void readBody() {
      this.debugOutput("Reading the body.");
      boolean needMoreData = true;

      while(needMoreData) {
         switch(this.joggSyncState.pageout(this.joggPage)) {
         case -1:
            this.debugOutput("There is a hole in the data. We proceed.");
         case 0:
         default:
            break;
         case 1:
            this.joggStreamState.pagein(this.joggPage);
            if(this.joggPage.granulepos() == 0L) {
               needMoreData = false;
            } else {
               label43:
               while(true) {
                  switch(this.joggStreamState.packetout(this.joggPacket)) {
                  case -1:
                     this.debugOutput("There is a hole in the data, we continue though.");
                  case 0:
                     break label43;
                  case 1:
                     this.decodeCurrentPacket();
                  }
               }

               if(this.joggPage.eos() != 0) {
                  needMoreData = false;
               }
            }
         }

         if(needMoreData) {
            this.index = this.joggSyncState.buffer(this.bufferSize);
            this.buffer = this.joggSyncState.data;

            try {
               this.count = this.inputStream.read(this.buffer, this.index, this.bufferSize);
            } catch (Exception var3) {
               System.err.println(var3);
               return;
            }

            this.joggSyncState.wrote(this.count);
            if(this.count == 0) {
               needMoreData = false;
            }
         }
      }

      this.debugOutput("Done reading the body.");
   }

   private void cleanUp() {
      this.debugOutput("Cleaning up.");
      this.joggStreamState.clear();
      this.jorbisBlock.clear();
      this.jorbisDspState.clear();
      this.jorbisInfo.clear();
      this.joggSyncState.clear();

      try {
         if(this.inputStream != null) {
            this.inputStream.close();
         }
      } catch (Exception var2) {
         ;
      }

      this.debugOutput("Done cleaning up.");
   }

   private void decodeCurrentPacket() {
      if(this.jorbisBlock.synthesis(this.joggPacket) == 0) {
         this.jorbisDspState.synthesis_blockin(this.jorbisBlock);
      }

      int samples;
      while((samples = this.jorbisDspState.synthesis_pcmout(this.pcmInfo, this.pcmIndex)) > 0) {
         int range;
         if(samples < this.convertedBufferSize) {
            range = samples;
         } else {
            range = this.convertedBufferSize;
         }

         for(int i = 0; i < this.jorbisInfo.channels; ++i) {
            int sampleIndex = i * 2;

            for(int j = 0; j < range; ++j) {
               int value = (int)(this.pcmInfo[0][i][this.pcmIndex[i] + j] * 32767.0F);
               if(value > 32767) {
                  value = 32767;
               }

               if(value < -32768) {
                  value = -32768;
               }

               if(value < 0) {
                  value |= '\u8000';
               }

               this.convertedBuffer[sampleIndex] = (byte)value;
               this.convertedBuffer[sampleIndex + 1] = (byte)(value >>> 8);
               sampleIndex += 2 * this.jorbisInfo.channels;
            }
         }

         this.outputLine.write(this.convertedBuffer, 0, 2 * this.jorbisInfo.channels * range);
         this.jorbisDspState.synthesis_read(range);
      }

   }

   private void debugOutput(String output) {
      System.out.println("Debug: " + output);
   }
}
