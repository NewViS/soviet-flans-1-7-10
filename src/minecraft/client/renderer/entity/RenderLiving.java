package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public abstract class RenderLiving extends RendererLivingEntity {

   public RenderLiving(ModelBase var1, float var2) {
      super(var1, var2);
   }

   protected boolean func_110813_b(EntityLiving var1) {
      return super.func_110813_b(var1) && (var1.getAlwaysRenderNameTagForRender() || var1.hasCustomNameTag() && var1 == super.renderManager.field_147941_i);
   }

   public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      super.doRender((EntityLivingBase)var1, var2, var4, var6, var8, var9);
      this.func_110827_b(var1, var2, var4, var6, var8, var9);
   }

   private double func_110828_a(double var1, double var3, double var5) {
      return var1 + (var3 - var1) * var5;
   }

   protected void func_110827_b(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      Entity var10 = var1.getLeashedToEntity();
      if(var10 != null) {
         var4 -= (1.6D - (double)var1.height) * 0.5D;
         Tessellator var11 = Tessellator.instance;
         double var12 = this.func_110828_a((double)var10.prevRotationYaw, (double)var10.rotationYaw, (double)(var9 * 0.5F)) * 0.01745329238474369D;
         double var14 = this.func_110828_a((double)var10.prevRotationPitch, (double)var10.rotationPitch, (double)(var9 * 0.5F)) * 0.01745329238474369D;
         double var16 = Math.cos(var12);
         double var18 = Math.sin(var12);
         double var20 = Math.sin(var14);
         if(var10 instanceof EntityHanging) {
            var16 = 0.0D;
            var18 = 0.0D;
            var20 = -1.0D;
         }

         double var22 = Math.cos(var14);
         double var24 = this.func_110828_a(var10.prevPosX, var10.posX, (double)var9) - var16 * 0.7D - var18 * 0.5D * var22;
         double var26 = this.func_110828_a(var10.prevPosY + (double)var10.getEyeHeight() * 0.7D, var10.posY + (double)var10.getEyeHeight() * 0.7D, (double)var9) - var20 * 0.5D - 0.25D;
         double var28 = this.func_110828_a(var10.prevPosZ, var10.posZ, (double)var9) - var18 * 0.7D + var16 * 0.5D * var22;
         double var30 = this.func_110828_a((double)var1.prevRenderYawOffset, (double)var1.renderYawOffset, (double)var9) * 0.01745329238474369D + 1.5707963267948966D;
         var16 = Math.cos(var30) * (double)var1.width * 0.4D;
         var18 = Math.sin(var30) * (double)var1.width * 0.4D;
         double var32 = this.func_110828_a(var1.prevPosX, var1.posX, (double)var9) + var16;
         double var34 = this.func_110828_a(var1.prevPosY, var1.posY, (double)var9);
         double var36 = this.func_110828_a(var1.prevPosZ, var1.posZ, (double)var9) + var18;
         var2 += var16;
         var6 += var18;
         double var38 = (double)((float)(var24 - var32));
         double var40 = (double)((float)(var26 - var34));
         double var42 = (double)((float)(var28 - var36));
         GL11.glDisable(3553);
         GL11.glDisable(2896);
         GL11.glDisable(2884);
         boolean var44 = true;
         double var45 = 0.025D;
         var11.startDrawing(5);

         int var47;
         float var48;
         for(var47 = 0; var47 <= 24; ++var47) {
            if(var47 % 2 == 0) {
               var11.setColorRGBA_F(0.5F, 0.4F, 0.3F, 1.0F);
            } else {
               var11.setColorRGBA_F(0.35F, 0.28F, 0.21000001F, 1.0F);
            }

            var48 = (float)var47 / 24.0F;
            var11.addVertex(var2 + var38 * (double)var48 + 0.0D, var4 + var40 * (double)(var48 * var48 + var48) * 0.5D + (double)((24.0F - (float)var47) / 18.0F + 0.125F), var6 + var42 * (double)var48);
            var11.addVertex(var2 + var38 * (double)var48 + 0.025D, var4 + var40 * (double)(var48 * var48 + var48) * 0.5D + (double)((24.0F - (float)var47) / 18.0F + 0.125F) + 0.025D, var6 + var42 * (double)var48);
         }

         var11.draw();
         var11.startDrawing(5);

         for(var47 = 0; var47 <= 24; ++var47) {
            if(var47 % 2 == 0) {
               var11.setColorRGBA_F(0.5F, 0.4F, 0.3F, 1.0F);
            } else {
               var11.setColorRGBA_F(0.35F, 0.28F, 0.21000001F, 1.0F);
            }

            var48 = (float)var47 / 24.0F;
            var11.addVertex(var2 + var38 * (double)var48 + 0.0D, var4 + var40 * (double)(var48 * var48 + var48) * 0.5D + (double)((24.0F - (float)var47) / 18.0F + 0.125F) + 0.025D, var6 + var42 * (double)var48);
            var11.addVertex(var2 + var38 * (double)var48 + 0.025D, var4 + var40 * (double)(var48 * var48 + var48) * 0.5D + (double)((24.0F - (float)var47) / 18.0F + 0.125F), var6 + var42 * (double)var48 + 0.025D);
         }

         var11.draw();
         GL11.glEnable(2896);
         GL11.glEnable(3553);
         GL11.glEnable(2884);
      }

   }
}
