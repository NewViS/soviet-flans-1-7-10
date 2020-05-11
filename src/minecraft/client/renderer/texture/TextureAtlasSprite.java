package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.renderer.texture.TextureAtlasSprite$1;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.IIcon;
import net.minecraft.util.ReportedException;

public class TextureAtlasSprite implements IIcon {

   private final String iconName;
   protected List framesTextureData = Lists.newArrayList();
   private AnimationMetadataSection animationMetadata;
   protected boolean rotated;
   private boolean useAnisotropicFiltering;
   protected int originX;
   protected int originY;
   protected int width;
   protected int height;
   private float minU;
   private float maxU;
   private float minV;
   private float maxV;
   protected int frameCounter;
   protected int tickCounter;


   protected TextureAtlasSprite(String var1) {
      this.iconName = var1;
   }

   public void initSprite(int var1, int var2, int var3, int var4, boolean var5) {
      this.originX = var3;
      this.originY = var4;
      this.rotated = var5;
      float var6 = (float)(0.009999999776482582D / (double)var1);
      float var7 = (float)(0.009999999776482582D / (double)var2);
      this.minU = (float)var3 / (float)((double)var1) + var6;
      this.maxU = (float)(var3 + this.width) / (float)((double)var1) - var6;
      this.minV = (float)var4 / (float)var2 + var7;
      this.maxV = (float)(var4 + this.height) / (float)var2 - var7;
      if(this.useAnisotropicFiltering) {
         float var8 = 8.0F / (float)var1;
         float var9 = 8.0F / (float)var2;
         this.minU += var8;
         this.maxU -= var8;
         this.minV += var9;
         this.maxV -= var9;
      }

   }

   public void copyFrom(TextureAtlasSprite var1) {
      this.originX = var1.originX;
      this.originY = var1.originY;
      this.width = var1.width;
      this.height = var1.height;
      this.rotated = var1.rotated;
      this.minU = var1.minU;
      this.maxU = var1.maxU;
      this.minV = var1.minV;
      this.maxV = var1.maxV;
   }

   public int getOriginX() {
      return this.originX;
   }

   public int getOriginY() {
      return this.originY;
   }

   public int getIconWidth() {
      return this.width;
   }

   public int getIconHeight() {
      return this.height;
   }

   public float getMinU() {
      return this.minU;
   }

   public float getMaxU() {
      return this.maxU;
   }

   public float getInterpolatedU(double var1) {
      float var3 = this.maxU - this.minU;
      return this.minU + var3 * (float)var1 / 16.0F;
   }

   public float getMinV() {
      return this.minV;
   }

   public float getMaxV() {
      return this.maxV;
   }

   public float getInterpolatedV(double var1) {
      float var3 = this.maxV - this.minV;
      return this.minV + var3 * ((float)var1 / 16.0F);
   }

   public String getIconName() {
      return this.iconName;
   }

   public void updateAnimation() {
      ++this.tickCounter;
      if(this.tickCounter >= this.animationMetadata.getFrameTimeSingle(this.frameCounter)) {
         int var1 = this.animationMetadata.getFrameIndex(this.frameCounter);
         int var2 = this.animationMetadata.getFrameCount() == 0?this.framesTextureData.size():this.animationMetadata.getFrameCount();
         this.frameCounter = (this.frameCounter + 1) % var2;
         this.tickCounter = 0;
         int var3 = this.animationMetadata.getFrameIndex(this.frameCounter);
         if(var1 != var3 && var3 >= 0 && var3 < this.framesTextureData.size()) {
            TextureUtil.uploadTextureMipmap((int[][])this.framesTextureData.get(var3), this.width, this.height, this.originX, this.originY, false, false);
         }
      }

   }

   public int[][] getFrameTextureData(int var1) {
      return (int[][])this.framesTextureData.get(var1);
   }

   public int getFrameCount() {
      return this.framesTextureData.size();
   }

   public void setIconWidth(int var1) {
      this.width = var1;
   }

   public void setIconHeight(int var1) {
      this.height = var1;
   }

   public void loadSprite(BufferedImage[] var1, AnimationMetadataSection var2, boolean var3) {
      this.resetSprite();
      this.useAnisotropicFiltering = var3;
      int var4 = var1[0].getWidth();
      int var5 = var1[0].getHeight();
      this.width = var4;
      this.height = var5;
      if(var3) {
         this.width += 16;
         this.height += 16;
      }

      int[][] var6 = new int[var1.length][];

      int var7;
      for(var7 = 0; var7 < var1.length; ++var7) {
         BufferedImage var8 = var1[var7];
         if(var8 != null) {
            if(var7 > 0 && (var8.getWidth() != var4 >> var7 || var8.getHeight() != var5 >> var7)) {
               throw new RuntimeException(String.format("Unable to load miplevel: %d, image is size: %dx%d, expected %dx%d", new Object[]{Integer.valueOf(var7), Integer.valueOf(var8.getWidth()), Integer.valueOf(var8.getHeight()), Integer.valueOf(var4 >> var7), Integer.valueOf(var5 >> var7)}));
            }

            var6[var7] = new int[var8.getWidth() * var8.getHeight()];
            var8.getRGB(0, 0, var8.getWidth(), var8.getHeight(), var6[var7], 0, var8.getWidth());
         }
      }

      if(var2 == null) {
         if(var5 != var4) {
            throw new RuntimeException("broken aspect ratio and not an animation");
         }

         this.fixTransparentPixels(var6);
         this.framesTextureData.add(this.prepareAnisotropicFiltering(var6, var4, var5));
      } else {
         var7 = var5 / var4;
         int var12 = var4;
         int var9 = var4;
         this.height = this.width;
         int var11;
         if(var2.getFrameCount() > 0) {
            Iterator var10 = var2.getFrameIndexSet().iterator();

            while(var10.hasNext()) {
               var11 = ((Integer)var10.next()).intValue();
               if(var11 >= var7) {
                  throw new RuntimeException("invalid frameindex " + var11);
               }

               this.allocateFrameTextureData(var11);
               this.framesTextureData.set(var11, this.prepareAnisotropicFiltering(getFrameTextureData(var6, var12, var9, var11), var12, var9));
            }

            this.animationMetadata = var2;
         } else {
            ArrayList var13 = Lists.newArrayList();

            for(var11 = 0; var11 < var7; ++var11) {
               this.framesTextureData.add(this.prepareAnisotropicFiltering(getFrameTextureData(var6, var12, var9, var11), var12, var9));
               var13.add(new AnimationFrame(var11, -1));
            }

            this.animationMetadata = new AnimationMetadataSection(var13, this.width, this.height, var2.getFrameTime());
         }
      }

   }

   public void generateMipmaps(int var1) {
      ArrayList var2 = Lists.newArrayList();

      for(int var3 = 0; var3 < this.framesTextureData.size(); ++var3) {
         int[][] var4 = (int[][])this.framesTextureData.get(var3);
         if(var4 != null) {
            try {
               var2.add(TextureUtil.generateMipmapData(var1, this.width, var4));
            } catch (Throwable var8) {
               CrashReport var6 = CrashReport.makeCrashReport(var8, "Generating mipmaps for frame");
               CrashReportCategory var7 = var6.makeCategory("Frame being iterated");
               var7.addCrashSection("Frame index", Integer.valueOf(var3));
               var7.addCrashSectionCallable("Frame sizes", new TextureAtlasSprite$1(this, var4));
               throw new ReportedException(var6);
            }
         }
      }

      this.setFramesTextureData(var2);
   }

   private void fixTransparentPixels(int[][] var1) {
      int[] var2 = var1[0];
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;

      int var7;
      for(var7 = 0; var7 < var2.length; ++var7) {
         if((var2[var7] & -16777216) != 0) {
            var4 += var2[var7] >> 16 & 255;
            var5 += var2[var7] >> 8 & 255;
            var6 += var2[var7] >> 0 & 255;
            ++var3;
         }
      }

      if(var3 != 0) {
         var4 /= var3;
         var5 /= var3;
         var6 /= var3;

         for(var7 = 0; var7 < var2.length; ++var7) {
            if((var2[var7] & -16777216) == 0) {
               var2[var7] = var4 << 16 | var5 << 8 | var6;
            }
         }

      }
   }

   private int[][] prepareAnisotropicFiltering(int[][] var1, int var2, int var3) {
      if(!this.useAnisotropicFiltering) {
         return var1;
      } else {
         int[][] var4 = new int[var1.length][];

         for(int var5 = 0; var5 < var1.length; ++var5) {
            int[] var6 = var1[var5];
            if(var6 != null) {
               int[] var7 = new int[(var2 + 16 >> var5) * (var3 + 16 >> var5)];
               System.arraycopy(var6, 0, var7, 0, var6.length);
               var4[var5] = TextureUtil.prepareAnisotropicData(var7, var2 >> var5, var3 >> var5, 8 >> var5);
            }
         }

         return var4;
      }
   }

   private void allocateFrameTextureData(int var1) {
      if(this.framesTextureData.size() <= var1) {
         for(int var2 = this.framesTextureData.size(); var2 <= var1; ++var2) {
            this.framesTextureData.add((Object)null);
         }

      }
   }

   private static int[][] getFrameTextureData(int[][] var0, int var1, int var2, int var3) {
      int[][] var4 = new int[var0.length][];

      for(int var5 = 0; var5 < var0.length; ++var5) {
         int[] var6 = var0[var5];
         if(var6 != null) {
            var4[var5] = new int[(var1 >> var5) * (var2 >> var5)];
            System.arraycopy(var6, var3 * var4[var5].length, var4[var5], 0, var4[var5].length);
         }
      }

      return var4;
   }

   public void clearFramesTextureData() {
      this.framesTextureData.clear();
   }

   public boolean hasAnimationMetadata() {
      return this.animationMetadata != null;
   }

   public void setFramesTextureData(List var1) {
      this.framesTextureData = var1;
   }

   private void resetSprite() {
      this.animationMetadata = null;
      this.setFramesTextureData(Lists.newArrayList());
      this.frameCounter = 0;
      this.tickCounter = 0;
   }

   public String toString() {
      return "TextureAtlasSprite{name=\'" + this.iconName + '\'' + ", frameCount=" + this.framesTextureData.size() + ", rotated=" + this.rotated + ", x=" + this.originX + ", y=" + this.originY + ", height=" + this.height + ", width=" + this.width + ", u0=" + this.minU + ", u1=" + this.maxU + ", v0=" + this.minV + ", v1=" + this.maxV + '}';
   }
}
