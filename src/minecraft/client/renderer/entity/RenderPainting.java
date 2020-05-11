package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityPainting$EnumArt;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderPainting extends Render {

   private static final ResourceLocation field_110807_a = new ResourceLocation("textures/painting/paintings_kristoffer_zetterstrand.png");


   public void doRender(EntityPainting var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      GL11.glTranslated(var2, var4, var6);
      GL11.glRotatef(var8, 0.0F, 1.0F, 0.0F);
      GL11.glEnable('\u803a');
      this.bindEntityTexture(var1);
      EntityPainting$EnumArt var10 = var1.art;
      float var11 = 0.0625F;
      GL11.glScalef(var11, var11, var11);
      this.func_77010_a(var1, var10.sizeX, var10.sizeY, var10.offsetX, var10.offsetY);
      GL11.glDisable('\u803a');
      GL11.glPopMatrix();
   }

   protected ResourceLocation getEntityTexture(EntityPainting var1) {
      return field_110807_a;
   }

   private void func_77010_a(EntityPainting var1, int var2, int var3, int var4, int var5) {
      float var6 = (float)(-var2) / 2.0F;
      float var7 = (float)(-var3) / 2.0F;
      float var8 = 0.5F;
      float var9 = 0.75F;
      float var10 = 0.8125F;
      float var11 = 0.0F;
      float var12 = 0.0625F;
      float var13 = 0.75F;
      float var14 = 0.8125F;
      float var15 = 0.001953125F;
      float var16 = 0.001953125F;
      float var17 = 0.7519531F;
      float var18 = 0.7519531F;
      float var19 = 0.0F;
      float var20 = 0.0625F;

      for(int var21 = 0; var21 < var2 / 16; ++var21) {
         for(int var22 = 0; var22 < var3 / 16; ++var22) {
            float var23 = var6 + (float)((var21 + 1) * 16);
            float var24 = var6 + (float)(var21 * 16);
            float var25 = var7 + (float)((var22 + 1) * 16);
            float var26 = var7 + (float)(var22 * 16);
            this.func_77008_a(var1, (var23 + var24) / 2.0F, (var25 + var26) / 2.0F);
            float var27 = (float)(var4 + var2 - var21 * 16) / 256.0F;
            float var28 = (float)(var4 + var2 - (var21 + 1) * 16) / 256.0F;
            float var29 = (float)(var5 + var3 - var22 * 16) / 256.0F;
            float var30 = (float)(var5 + var3 - (var22 + 1) * 16) / 256.0F;
            Tessellator var31 = Tessellator.instance;
            var31.startDrawingQuads();
            var31.setNormal(0.0F, 0.0F, -1.0F);
            var31.addVertexWithUV((double)var23, (double)var26, (double)(-var8), (double)var28, (double)var29);
            var31.addVertexWithUV((double)var24, (double)var26, (double)(-var8), (double)var27, (double)var29);
            var31.addVertexWithUV((double)var24, (double)var25, (double)(-var8), (double)var27, (double)var30);
            var31.addVertexWithUV((double)var23, (double)var25, (double)(-var8), (double)var28, (double)var30);
            var31.setNormal(0.0F, 0.0F, 1.0F);
            var31.addVertexWithUV((double)var23, (double)var25, (double)var8, (double)var9, (double)var11);
            var31.addVertexWithUV((double)var24, (double)var25, (double)var8, (double)var10, (double)var11);
            var31.addVertexWithUV((double)var24, (double)var26, (double)var8, (double)var10, (double)var12);
            var31.addVertexWithUV((double)var23, (double)var26, (double)var8, (double)var9, (double)var12);
            var31.setNormal(0.0F, 1.0F, 0.0F);
            var31.addVertexWithUV((double)var23, (double)var25, (double)(-var8), (double)var13, (double)var15);
            var31.addVertexWithUV((double)var24, (double)var25, (double)(-var8), (double)var14, (double)var15);
            var31.addVertexWithUV((double)var24, (double)var25, (double)var8, (double)var14, (double)var16);
            var31.addVertexWithUV((double)var23, (double)var25, (double)var8, (double)var13, (double)var16);
            var31.setNormal(0.0F, -1.0F, 0.0F);
            var31.addVertexWithUV((double)var23, (double)var26, (double)var8, (double)var13, (double)var15);
            var31.addVertexWithUV((double)var24, (double)var26, (double)var8, (double)var14, (double)var15);
            var31.addVertexWithUV((double)var24, (double)var26, (double)(-var8), (double)var14, (double)var16);
            var31.addVertexWithUV((double)var23, (double)var26, (double)(-var8), (double)var13, (double)var16);
            var31.setNormal(-1.0F, 0.0F, 0.0F);
            var31.addVertexWithUV((double)var23, (double)var25, (double)var8, (double)var18, (double)var19);
            var31.addVertexWithUV((double)var23, (double)var26, (double)var8, (double)var18, (double)var20);
            var31.addVertexWithUV((double)var23, (double)var26, (double)(-var8), (double)var17, (double)var20);
            var31.addVertexWithUV((double)var23, (double)var25, (double)(-var8), (double)var17, (double)var19);
            var31.setNormal(1.0F, 0.0F, 0.0F);
            var31.addVertexWithUV((double)var24, (double)var25, (double)(-var8), (double)var18, (double)var19);
            var31.addVertexWithUV((double)var24, (double)var26, (double)(-var8), (double)var18, (double)var20);
            var31.addVertexWithUV((double)var24, (double)var26, (double)var8, (double)var17, (double)var20);
            var31.addVertexWithUV((double)var24, (double)var25, (double)var8, (double)var17, (double)var19);
            var31.draw();
         }
      }

   }

   private void func_77008_a(EntityPainting var1, float var2, float var3) {
      int var4 = MathHelper.floor_double(var1.posX);
      int var5 = MathHelper.floor_double(var1.posY + (double)(var3 / 16.0F));
      int var6 = MathHelper.floor_double(var1.posZ);
      if(var1.hangingDirection == 2) {
         var4 = MathHelper.floor_double(var1.posX + (double)(var2 / 16.0F));
      }

      if(var1.hangingDirection == 1) {
         var6 = MathHelper.floor_double(var1.posZ - (double)(var2 / 16.0F));
      }

      if(var1.hangingDirection == 0) {
         var4 = MathHelper.floor_double(var1.posX - (double)(var2 / 16.0F));
      }

      if(var1.hangingDirection == 3) {
         var6 = MathHelper.floor_double(var1.posZ + (double)(var2 / 16.0F));
      }

      int var7 = super.renderManager.worldObj.getLightBrightnessForSkyBlocks(var4, var5, var6, 0);
      int var8 = var7 % 65536;
      int var9 = var7 / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var8, (float)var9);
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
   }

}
