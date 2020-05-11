package net.minecraft.client.renderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData$1;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadDownloadImageData extends SimpleTexture {

   private static final Logger logger = LogManager.getLogger();
   private static final AtomicInteger threadDownloadCounter = new AtomicInteger(0);
   private final File field_152434_e;
   private final String imageUrl;
   private final IImageBuffer imageBuffer;
   private BufferedImage bufferedImage;
   private Thread imageThread;
   private boolean textureUploaded;


   public ThreadDownloadImageData(File var1, String var2, ResourceLocation var3, IImageBuffer var4) {
      super(var3);
      this.field_152434_e = var1;
      this.imageUrl = var2;
      this.imageBuffer = var4;
   }

   private void checkTextureUploaded() {
      if(!this.textureUploaded) {
         if(this.bufferedImage != null) {
            if(super.textureLocation != null) {
               this.deleteGlTexture();
            }

            TextureUtil.uploadTextureImage(super.getGlTextureId(), this.bufferedImage);
            this.textureUploaded = true;
         }

      }
   }

   public int getGlTextureId() {
      this.checkTextureUploaded();
      return super.getGlTextureId();
   }

   public void setBufferedImage(BufferedImage var1) {
      this.bufferedImage = var1;
      if(this.imageBuffer != null) {
         this.imageBuffer.func_152634_a();
      }

   }

   public void loadTexture(IResourceManager var1) {
      if(this.bufferedImage == null && super.textureLocation != null) {
         super.loadTexture(var1);
      }

      if(this.imageThread == null) {
         if(this.field_152434_e != null && this.field_152434_e.isFile()) {
            logger.debug("Loading http texture from local cache ({})", new Object[]{this.field_152434_e});

            try {
               this.bufferedImage = ImageIO.read(this.field_152434_e);
               if(this.imageBuffer != null) {
                  this.setBufferedImage(this.imageBuffer.parseUserSkin(this.bufferedImage));
               }
            } catch (IOException var3) {
               logger.error("Couldn\'t load skin " + this.field_152434_e, var3);
               this.func_152433_a();
            }
         } else {
            this.func_152433_a();
         }
      }

   }

   protected void func_152433_a() {
      this.imageThread = new ThreadDownloadImageData$1(this, "Texture Downloader #" + threadDownloadCounter.incrementAndGet());
      this.imageThread.setDaemon(true);
      this.imageThread.start();
   }

   // $FF: synthetic method
   static String access$000(ThreadDownloadImageData var0) {
      return var0.imageUrl;
   }

   // $FF: synthetic method
   static File access$100(ThreadDownloadImageData var0) {
      return var0.field_152434_e;
   }

   // $FF: synthetic method
   static Logger access$200() {
      return logger;
   }

   // $FF: synthetic method
   static IImageBuffer access$300(ThreadDownloadImageData var0) {
      return var0.imageBuffer;
   }

}
