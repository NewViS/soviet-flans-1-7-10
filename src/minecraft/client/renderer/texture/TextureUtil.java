package net.minecraft.client.renderer.texture;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class TextureUtil {

   private static final Logger logger = LogManager.getLogger();
   private static final IntBuffer dataBuffer = GLAllocation.createDirectIntBuffer(4194304);
   public static final DynamicTexture missingTexture = new DynamicTexture(16, 16);
   public static final int[] missingTextureData = missingTexture.getTextureData();
   private static int field_147958_e = -1;
   private static int field_147956_f = -1;
   private static float field_152779_g = -1.0F;
   private static final int[] field_147957_g;


   public static int glGenTextures() {
      return GL11.glGenTextures();
   }

   public static void deleteTexture(int var0) {
      GL11.glDeleteTextures(var0);
   }

   public static int uploadTextureImage(int var0, BufferedImage var1) {
      return uploadTextureImageAllocate(var0, var1, false, false);
   }

   public static void uploadTexture(int var0, int[] var1, int var2, int var3) {
      bindTexture(var0);
      uploadTextureSub(0, var1, var2, var3, 0, 0, false, false, false);
   }

   public static int[][] generateMipmapData(int var0, int var1, int[][] var2) {
      int[][] var3 = new int[var0 + 1][];
      var3[0] = var2[0];
      if(var0 > 0) {
         boolean var4 = false;

         int var5;
         for(var5 = 0; var5 < var2.length; ++var5) {
            if(var2[0][var5] >> 24 == 0) {
               var4 = true;
               break;
            }
         }

         for(var5 = 1; var5 <= var0; ++var5) {
            if(var2[var5] != null) {
               var3[var5] = var2[var5];
            } else {
               int[] var6 = var3[var5 - 1];
               int[] var7 = new int[var6.length >> 2];
               int var8 = var1 >> var5;
               int var9 = var7.length / var8;
               int var10 = var8 << 1;

               for(int var11 = 0; var11 < var8; ++var11) {
                  for(int var12 = 0; var12 < var9; ++var12) {
                     int var13 = 2 * (var11 + var12 * var10);
                     var7[var11 + var12 * var8] = func_147943_a(var6[var13 + 0], var6[var13 + 1], var6[var13 + 0 + var10], var6[var13 + 1 + var10], var4);
                  }
               }

               var3[var5] = var7;
            }
         }
      }

      return var3;
   }

   private static int func_147943_a(int var0, int var1, int var2, int var3, boolean var4) {
      if(!var4) {
         int var13 = func_147944_a(var0, var1, var2, var3, 24);
         int var14 = func_147944_a(var0, var1, var2, var3, 16);
         int var15 = func_147944_a(var0, var1, var2, var3, 8);
         int var16 = func_147944_a(var0, var1, var2, var3, 0);
         return var13 << 24 | var14 << 16 | var15 << 8 | var16;
      } else {
         field_147957_g[0] = var0;
         field_147957_g[1] = var1;
         field_147957_g[2] = var2;
         field_147957_g[3] = var3;
         float var5 = 0.0F;
         float var6 = 0.0F;
         float var7 = 0.0F;
         float var8 = 0.0F;

         int var9;
         for(var9 = 0; var9 < 4; ++var9) {
            if(field_147957_g[var9] >> 24 != 0) {
               var5 += (float)Math.pow((double)((float)(field_147957_g[var9] >> 24 & 255) / 255.0F), 2.2D);
               var6 += (float)Math.pow((double)((float)(field_147957_g[var9] >> 16 & 255) / 255.0F), 2.2D);
               var7 += (float)Math.pow((double)((float)(field_147957_g[var9] >> 8 & 255) / 255.0F), 2.2D);
               var8 += (float)Math.pow((double)((float)(field_147957_g[var9] >> 0 & 255) / 255.0F), 2.2D);
            }
         }

         var5 /= 4.0F;
         var6 /= 4.0F;
         var7 /= 4.0F;
         var8 /= 4.0F;
         var9 = (int)(Math.pow((double)var5, 0.45454545454545453D) * 255.0D);
         int var10 = (int)(Math.pow((double)var6, 0.45454545454545453D) * 255.0D);
         int var11 = (int)(Math.pow((double)var7, 0.45454545454545453D) * 255.0D);
         int var12 = (int)(Math.pow((double)var8, 0.45454545454545453D) * 255.0D);
         if(var9 < 96) {
            var9 = 0;
         }

         return var9 << 24 | var10 << 16 | var11 << 8 | var12;
      }
   }

   private static int func_147944_a(int var0, int var1, int var2, int var3, int var4) {
      float var5 = (float)Math.pow((double)((float)(var0 >> var4 & 255) / 255.0F), 2.2D);
      float var6 = (float)Math.pow((double)((float)(var1 >> var4 & 255) / 255.0F), 2.2D);
      float var7 = (float)Math.pow((double)((float)(var2 >> var4 & 255) / 255.0F), 2.2D);
      float var8 = (float)Math.pow((double)((float)(var3 >> var4 & 255) / 255.0F), 2.2D);
      float var9 = (float)Math.pow((double)(var5 + var6 + var7 + var8) * 0.25D, 0.45454545454545453D);
      return (int)((double)var9 * 255.0D);
   }

   public static void uploadTextureMipmap(int[][] var0, int var1, int var2, int var3, int var4, boolean var5, boolean var6) {
      for(int var7 = 0; var7 < var0.length; ++var7) {
         int[] var8 = var0[var7];
         uploadTextureSub(var7, var8, var1 >> var7, var2 >> var7, var3 >> var7, var4 >> var7, var5, var6, var0.length > 1);
      }

   }

   private static void uploadTextureSub(int var0, int[] var1, int var2, int var3, int var4, int var5, boolean var6, boolean var7, boolean var8) {
      int var9 = 4194304 / var2;
      func_147954_b(var6, var8);
      setTextureClamped(var7);

      int var12;
      for(int var10 = 0; var10 < var2 * var3; var10 += var2 * var12) {
         int var11 = var10 / var2;
         var12 = Math.min(var9, var3 - var11);
         int var13 = var2 * var12;
         copyToBufferPos(var1, var10, var13);
         GL11.glTexSubImage2D(3553, var0, var4, var5 + var11, var2, var12, '\u80e1', '\u8367', dataBuffer);
      }

   }

   public static int uploadTextureImageAllocate(int var0, BufferedImage var1, boolean var2, boolean var3) {
      allocateTexture(var0, var1.getWidth(), var1.getHeight());
      return uploadTextureImageSub(var0, var1, 0, 0, var2, var3);
   }

   public static void allocateTexture(int var0, int var1, int var2) {
      allocateTextureImpl(var0, 0, var1, var2, 1.0F);
   }

   public static void allocateTextureImpl(int var0, int var1, int var2, int var3, float var4) {
      deleteTexture(var0);
      bindTexture(var0);
      if(OpenGlHelper.anisotropicFilteringSupported) {
         GL11.glTexParameterf(3553, '\u84fe', var4);
      }

      if(var1 > 0) {
         GL11.glTexParameteri(3553, '\u813d', var1);
         GL11.glTexParameterf(3553, '\u813a', 0.0F);
         GL11.glTexParameterf(3553, '\u813b', (float)var1);
         GL11.glTexParameterf(3553, '\u8501', 0.0F);
      }

      for(int var5 = 0; var5 <= var1; ++var5) {
         GL11.glTexImage2D(3553, var5, 6408, var2 >> var5, var3 >> var5, 0, '\u80e1', '\u8367', (IntBuffer)null);
      }

   }

   public static int uploadTextureImageSub(int var0, BufferedImage var1, int var2, int var3, boolean var4, boolean var5) {
      bindTexture(var0);
      uploadTextureImageSubImpl(var1, var2, var3, var4, var5);
      return var0;
   }

   private static void uploadTextureImageSubImpl(BufferedImage var0, int var1, int var2, boolean var3, boolean var4) {
      int var5 = var0.getWidth();
      int var6 = var0.getHeight();
      int var7 = 4194304 / var5;
      int[] var8 = new int[var7 * var5];
      setTextureBlurred(var3);
      setTextureClamped(var4);

      for(int var9 = 0; var9 < var5 * var6; var9 += var5 * var7) {
         int var10 = var9 / var5;
         int var11 = Math.min(var7, var6 - var10);
         int var12 = var5 * var11;
         var0.getRGB(0, var10, var5, var11, var8, 0, var5);
         copyToBuffer(var8, var12);
         GL11.glTexSubImage2D(3553, 0, var1, var2 + var10, var5, var11, '\u80e1', '\u8367', dataBuffer);
      }

   }

   private static void setTextureClamped(boolean var0) {
      if(var0) {
         GL11.glTexParameteri(3553, 10242, 10496);
         GL11.glTexParameteri(3553, 10243, 10496);
      } else {
         GL11.glTexParameteri(3553, 10242, 10497);
         GL11.glTexParameteri(3553, 10243, 10497);
      }

   }

   private static void setTextureBlurred(boolean var0) {
      func_147954_b(var0, false);
   }

   public static void func_152777_a(boolean var0, boolean var1, float var2) {
      field_147958_e = GL11.glGetTexParameteri(3553, 10241);
      field_147956_f = GL11.glGetTexParameteri(3553, 10240);
      field_152779_g = GL11.glGetTexParameterf(3553, '\u84fe');
      func_147954_b(var0, var1);
      func_152778_a(var2);
   }

   public static void func_147945_b() {
      if(field_147958_e >= 0 && field_147956_f >= 0 && field_152779_g >= 0.0F) {
         func_147952_b(field_147958_e, field_147956_f);
         func_152778_a(field_152779_g);
         field_152779_g = -1.0F;
         field_147958_e = -1;
         field_147956_f = -1;
      }
   }

   private static void func_147952_b(int var0, int var1) {
      GL11.glTexParameteri(3553, 10241, var0);
      GL11.glTexParameteri(3553, 10240, var1);
   }

   private static void func_152778_a(float var0) {
      GL11.glTexParameterf(3553, '\u84fe', var0);
   }

   private static void func_147954_b(boolean var0, boolean var1) {
      if(var0) {
         GL11.glTexParameteri(3553, 10241, var1?9987:9729);
         GL11.glTexParameteri(3553, 10240, 9729);
      } else {
         GL11.glTexParameteri(3553, 10241, var1?9986:9728);
         GL11.glTexParameteri(3553, 10240, 9728);
      }

   }

   private static void copyToBuffer(int[] var0, int var1) {
      copyToBufferPos(var0, 0, var1);
   }

   private static void copyToBufferPos(int[] var0, int var1, int var2) {
      int[] var3 = var0;
      if(Minecraft.getMinecraft().gameSettings.anaglyph) {
         var3 = updateAnaglyph(var0);
      }

      dataBuffer.clear();
      dataBuffer.put(var3, var1, var2);
      dataBuffer.position(0).limit(var2);
   }

   static void bindTexture(int var0) {
      GL11.glBindTexture(3553, var0);
   }

   public static int[] readImageData(IResourceManager var0, ResourceLocation var1) {
      BufferedImage var2 = ImageIO.read(var0.getResource(var1).getInputStream());
      int var3 = var2.getWidth();
      int var4 = var2.getHeight();
      int[] var5 = new int[var3 * var4];
      var2.getRGB(0, 0, var3, var4, var5, 0, var3);
      return var5;
   }

   public static int[] updateAnaglyph(int[] var0) {
      int[] var1 = new int[var0.length];

      for(int var2 = 0; var2 < var0.length; ++var2) {
         int var3 = var0[var2] >> 24 & 255;
         int var4 = var0[var2] >> 16 & 255;
         int var5 = var0[var2] >> 8 & 255;
         int var6 = var0[var2] & 255;
         int var7 = (var4 * 30 + var5 * 59 + var6 * 11) / 100;
         int var8 = (var4 * 30 + var5 * 70) / 100;
         int var9 = (var4 * 30 + var6 * 70) / 100;
         var1[var2] = var3 << 24 | var7 << 16 | var8 << 8 | var9;
      }

      return var1;
   }

   public static int[] prepareAnisotropicData(int[] var0, int var1, int var2, int var3) {
      int var4 = var1 + 2 * var3;

      int var5;
      int var6;
      for(var5 = var2 - 1; var5 >= 0; --var5) {
         var6 = var5 * var1;
         int var7 = var3 + (var5 + var3) * var4;

         int var8;
         for(var8 = 0; var8 < var3; var8 += var1) {
            int var9 = Math.min(var1, var3 - var8);
            System.arraycopy(var0, var6 + var1 - var9, var0, var7 - var8 - var9, var9);
         }

         System.arraycopy(var0, var6, var0, var7, var1);

         for(var8 = 0; var8 < var3; var8 += var1) {
            System.arraycopy(var0, var6, var0, var7 + var1 + var8, Math.min(var1, var3 - var8));
         }
      }

      for(var5 = 0; var5 < var3; var5 += var2) {
         var6 = Math.min(var2, var3 - var5);
         System.arraycopy(var0, (var3 + var2 - var6) * var4, var0, (var3 - var5 - var6) * var4, var4 * var6);
      }

      for(var5 = 0; var5 < var3; var5 += var2) {
         var6 = Math.min(var2, var3 - var5);
         System.arraycopy(var0, var3 * var4, var0, (var2 + var3 + var5) * var4, var4 * var6);
      }

      return var0;
   }

   public static void func_147953_a(int[] var0, int var1, int var2) {
      int[] var3 = new int[var1];
      int var4 = var2 / 2;

      for(int var5 = 0; var5 < var4; ++var5) {
         System.arraycopy(var0, var5 * var1, var3, 0, var1);
         System.arraycopy(var0, (var2 - 1 - var5) * var1, var0, var5 * var1, var1);
         System.arraycopy(var3, 0, var0, (var2 - 1 - var5) * var1, var1);
      }

   }

   static {
      int var0 = -16777216;
      int var1 = -524040;
      int[] var2 = new int[]{-524040, -524040, -524040, -524040, -524040, -524040, -524040, -524040};
      int[] var3 = new int[]{-16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216};
      int var4 = var2.length;

      for(int var5 = 0; var5 < 16; ++var5) {
         System.arraycopy(var5 < var4?var2:var3, 0, missingTextureData, 16 * var5, var4);
         System.arraycopy(var5 < var4?var3:var2, 0, missingTextureData, 16 * var5 + var4, var4);
      }

      missingTexture.updateDynamicTexture();
      field_147957_g = new int[4];
   }
}
