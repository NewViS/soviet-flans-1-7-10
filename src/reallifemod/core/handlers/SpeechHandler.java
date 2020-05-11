package de.ItsAMysterious.mods.reallifemod.core.handlers;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class SpeechHandler implements Runnable {

   public static double vVoice;
   public static float vPitch;
   public static float vPitchRange;
   public static float vPitchShift;
   public static String vSentence;


   public static void speechSynth(double vVoice2, float vPitch2, float vPitchRange2, float vPitchShift2, String vSentence2) {
      vVoice = vVoice2;
      vPitch = vPitch2;
      vPitchRange = vPitchRange2;
      vPitchShift = vPitchShift2;
      vSentence = vSentence2;
      System.out.println(" set the values to " + vVoice + " " + vPitch + " " + vPitchRange + " " + vPitchShift + " " + vSentence);
      Thread t1 = new Thread(new SpeechHandler());
      t1.start();
   }

   public void run() {
      System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
      System.out.println(" about to try " + vVoice + " " + vPitch + " " + vPitchRange + " " + vPitchShift + " " + vSentence);

      try {
         String e = "kevin16";
         if(vVoice == 1.0D) {
            e = "kevin";
         }

         if(vVoice == 2.0D) {
            e = "kevin16";
         }

         if(vVoice == 3.0D) {
            e = "alan";
         }

         if(vVoice == 4.0D) {
            e = "alan";
         }

         VoiceManager vm = VoiceManager.getInstance();
         Voice voice = vm.getVoice(e);
         voice.allocate();
         voice.setPitch(vPitch);
         voice.setPitchRange(vPitchRange);
         voice.setPitchShift(vPitchShift);
         voice.setDetailedMetrics(true);
         voice.setRate(1000.0F);
         voice.speak(vSentence);
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }
}
