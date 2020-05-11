package net.minecraft.client.renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;
import net.minecraft.client.renderer.IImageBuffer;

public class ImageBufferDownload implements IImageBuffer {

   private int[] imageData;
   private int imageWidth;
   private int imageHeight;


   public BufferedImage parseUserSkin(BufferedImage var1) {
      if(var1 == null) {
         return null;
      } else {
         this.imageWidth = 64;
         this.imageHeight = 32;
         BufferedImage var2 = new BufferedImage(this.imageWidth, this.imageHeight, 2);
         Graphics var3 = var2.getGraphics();
         var3.drawImage(var1, 0, 0, (ImageObserver)null);
         var3.dispose();
         this.imageData = ((DataBufferInt)var2.getRaster().getDataBuffer()).getData();
         this.setAreaOpaque(0, 0, 32, 16);
         this.setAreaTransparent(32, 0, 64, 32);
         this.setAreaOpaque(0, 16, 64, 32);
         return var2;
      }
   }

   public void func_152634_a() {}

   private void setAreaTransparent(int var1, int var2, int var3, int var4) {
      if(!this.hasTransparency(var1, var2, var3, var4)) {
         for(int var5 = var1; var5 < var3; ++var5) {
            for(int var6 = var2; var6 < var4; ++var6) {
               this.imageData[var5 + var6 * this.imageWidth] &= 16777215;
            }
         }

      }
   }

   private void setAreaOpaque(int var1, int var2, int var3, int var4) {
      for(int var5 = var1; var5 < var3; ++var5) {
         for(int var6 = var2; var6 < var4; ++var6) {
            this.imageData[var5 + var6 * this.imageWidth] |= -16777216;
         }
      }

   }

   private boolean hasTransparency(int var1, int var2, int var3, int var4) {
      for(int var5 = var1; var5 < var3; ++var5) {
         for(int var6 = var2; var6 < var4; ++var6) {
            int var7 = this.imageData[var5 + var6 * this.imageWidth];
            if((var7 >> 24 & 255) < 128) {
               return true;
            }
         }
      }

      return false;
   }
}
