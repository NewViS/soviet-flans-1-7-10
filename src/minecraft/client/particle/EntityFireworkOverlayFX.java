package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFireworkOverlayFX extends EntityFX {

   protected EntityFireworkOverlayFX(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
      super.particleMaxAge = 4;
   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = 0.25F;
      float var9 = var8 + 0.25F;
      float var10 = 0.125F;
      float var11 = var10 + 0.25F;
      float var12 = 7.1F * MathHelper.sin(((float)super.particleAge + var2 - 1.0F) * 0.25F * 3.1415927F);
      super.particleAlpha = 0.6F - ((float)super.particleAge + var2 - 1.0F) * 0.25F * 0.5F;
      float var13 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)var2 - EntityFX.interpPosX);
      float var14 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)var2 - EntityFX.interpPosY);
      float var15 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)var2 - EntityFX.interpPosZ);
      var1.setColorRGBA_F(super.particleRed, super.particleGreen, super.particleBlue, super.particleAlpha);
      var1.addVertexWithUV((double)(var13 - var3 * var12 - var6 * var12), (double)(var14 - var4 * var12), (double)(var15 - var5 * var12 - var7 * var12), (double)var9, (double)var11);
      var1.addVertexWithUV((double)(var13 - var3 * var12 + var6 * var12), (double)(var14 + var4 * var12), (double)(var15 - var5 * var12 + var7 * var12), (double)var9, (double)var10);
      var1.addVertexWithUV((double)(var13 + var3 * var12 + var6 * var12), (double)(var14 + var4 * var12), (double)(var15 + var5 * var12 + var7 * var12), (double)var8, (double)var10);
      var1.addVertexWithUV((double)(var13 + var3 * var12 - var6 * var12), (double)(var14 - var4 * var12), (double)(var15 + var5 * var12 - var7 * var12), (double)var8, (double)var11);
   }
}
