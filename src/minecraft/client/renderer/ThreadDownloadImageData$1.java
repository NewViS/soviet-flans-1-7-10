package net.minecraft.client.renderer;

import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import org.apache.commons.io.FileUtils;

class ThreadDownloadImageData$1 extends Thread {

   // $FF: synthetic field
   final ThreadDownloadImageData theThreadDownloadImageData;


   ThreadDownloadImageData$1(ThreadDownloadImageData var1, String var2) {
      super(var2);
      this.theThreadDownloadImageData = var1;
   }

   public void run() {
      HttpURLConnection var1 = null;
      ThreadDownloadImageData.access$200().debug("Downloading http texture from {} to {}", new Object[]{ThreadDownloadImageData.access$000(this.theThreadDownloadImageData), ThreadDownloadImageData.access$100(this.theThreadDownloadImageData)});

      try {
         var1 = (HttpURLConnection)(new URL(ThreadDownloadImageData.access$000(this.theThreadDownloadImageData))).openConnection(Minecraft.getMinecraft().getProxy());
         var1.setDoInput(true);
         var1.setDoOutput(false);
         var1.connect();
         if(var1.getResponseCode() / 100 != 2) {
            return;
         }

         BufferedImage var2;
         if(ThreadDownloadImageData.access$100(this.theThreadDownloadImageData) != null) {
            FileUtils.copyInputStreamToFile(var1.getInputStream(), ThreadDownloadImageData.access$100(this.theThreadDownloadImageData));
            var2 = ImageIO.read(ThreadDownloadImageData.access$100(this.theThreadDownloadImageData));
         } else {
            var2 = ImageIO.read(var1.getInputStream());
         }

         if(ThreadDownloadImageData.access$300(this.theThreadDownloadImageData) != null) {
            var2 = ThreadDownloadImageData.access$300(this.theThreadDownloadImageData).parseUserSkin(var2);
         }

         this.theThreadDownloadImageData.setBufferedImage(var2);
      } catch (Exception var6) {
         ThreadDownloadImageData.access$200().error("Couldn\'t download http texture", var6);
      } finally {
         if(var1 != null) {
            var1.disconnect();
         }

      }

   }
}
