package net.minecraft.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class RenderFish extends Render {

   private static final ResourceLocation field_110792_a = new ResourceLocation("textures/particle/particles.png");


   public void doRender(EntityFishHook var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      GL11.glEnable('\u803a');
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      this.bindEntityTexture(var1);
      Tessellator var10 = Tessellator.instance;
      byte var11 = 1;
      byte var12 = 2;
      float var13 = (float)(var11 * 8 + 0) / 128.0F;
      float var14 = (float)(var11 * 8 + 8) / 128.0F;
      float var15 = (float)(var12 * 8 + 0) / 128.0F;
      float var16 = (float)(var12 * 8 + 8) / 128.0F;
      float var17 = 1.0F;
      float var18 = 0.5F;
      float var19 = 0.5F;
      GL11.glRotatef(180.0F - super.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-super.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      var10.startDrawingQuads();
      var10.setNormal(0.0F, 1.0F, 0.0F);
      var10.addVertexWithUV((double)(0.0F - var18), (double)(0.0F - var19), 0.0D, (double)var13, (double)var16);
      var10.addVertexWithUV((double)(var17 - var18), (double)(0.0F - var19), 0.0D, (double)var14, (double)var16);
      var10.addVertexWithUV((double)(var17 - var18), (double)(1.0F - var19), 0.0D, (double)var14, (double)var15);
      var10.addVertexWithUV((double)(0.0F - var18), (double)(1.0F - var19), 0.0D, (double)var13, (double)var15);
      var10.draw();
      GL11.glDisable('\u803a');
      GL11.glPopMatrix();
      if(var1.field_146042_b != null) {
         float var20 = var1.field_146042_b.getSwingProgress(var9);
         float var21 = MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F);
         Vec3 var22 = Vec3.createVectorHelper(-0.5D, 0.03D, 0.8D);
         var22.rotateAroundX(-(var1.field_146042_b.prevRotationPitch + (var1.field_146042_b.rotationPitch - var1.field_146042_b.prevRotationPitch) * var9) * 3.1415927F / 180.0F);
         var22.rotateAroundY(-(var1.field_146042_b.prevRotationYaw + (var1.field_146042_b.rotationYaw - var1.field_146042_b.prevRotationYaw) * var9) * 3.1415927F / 180.0F);
         var22.rotateAroundY(var21 * 0.5F);
         var22.rotateAroundX(-var21 * 0.7F);
         double var23 = var1.field_146042_b.prevPosX + (var1.field_146042_b.posX - var1.field_146042_b.prevPosX) * (double)var9 + var22.xCoord;
         double var25 = var1.field_146042_b.prevPosY + (var1.field_146042_b.posY - var1.field_146042_b.prevPosY) * (double)var9 + var22.yCoord;
         double var27 = var1.field_146042_b.prevPosZ + (var1.field_146042_b.posZ - var1.field_146042_b.prevPosZ) * (double)var9 + var22.zCoord;
         double var29 = var1.field_146042_b == Minecraft.getMinecraft().thePlayer?0.0D:(double)var1.field_146042_b.getEyeHeight();
         if(super.renderManager.options.thirdPersonView > 0 || var1.field_146042_b != Minecraft.getMinecraft().thePlayer) {
            float var31 = (var1.field_146042_b.prevRenderYawOffset + (var1.field_146042_b.renderYawOffset - var1.field_146042_b.prevRenderYawOffset) * var9) * 3.1415927F / 180.0F;
            double var32 = (double)MathHelper.sin(var31);
            double var34 = (double)MathHelper.cos(var31);
            var23 = var1.field_146042_b.prevPosX + (var1.field_146042_b.posX - var1.field_146042_b.prevPosX) * (double)var9 - var34 * 0.35D - var32 * 0.85D;
            var25 = var1.field_146042_b.prevPosY + var29 + (var1.field_146042_b.posY - var1.field_146042_b.prevPosY) * (double)var9 - 0.45D;
            var27 = var1.field_146042_b.prevPosZ + (var1.field_146042_b.posZ - var1.field_146042_b.prevPosZ) * (double)var9 - var32 * 0.35D + var34 * 0.85D;
         }

         double var46 = var1.prevPosX + (var1.posX - var1.prevPosX) * (double)var9;
         double var33 = var1.prevPosY + (var1.posY - var1.prevPosY) * (double)var9 + 0.25D;
         double var35 = var1.prevPosZ + (var1.posZ - var1.prevPosZ) * (double)var9;
         double var37 = (double)((float)(var23 - var46));
         double var39 = (double)((float)(var25 - var33));
         double var41 = (double)((float)(var27 - var35));
         GL11.glDisable(3553);
         GL11.glDisable(2896);
         var10.startDrawing(3);
         var10.setColorOpaque_I(0);
         byte var43 = 16;

         for(int var44 = 0; var44 <= var43; ++var44) {
            float var45 = (float)var44 / (float)var43;
            var10.addVertex(var2 + var37 * (double)var45, var4 + var39 * (double)(var45 * var45 + var45) * 0.5D + 0.25D, var6 + var41 * (double)var45);
         }

         var10.draw();
         GL11.glEnable(2896);
         GL11.glEnable(3553);
      }

   }

   protected ResourceLocation getEntityTexture(EntityFishHook var1) {
      return field_110792_a;
   }

}
