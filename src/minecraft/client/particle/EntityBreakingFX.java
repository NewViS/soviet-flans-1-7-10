package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityBreakingFX extends EntityFX {

   public EntityBreakingFX(World var1, double var2, double var4, double var6, Item var8) {
      this(var1, var2, var4, var6, var8, 0);
   }

   public EntityBreakingFX(World var1, double var2, double var4, double var6, Item var8, int var9) {
      super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
      this.setParticleIcon(var8.getIconFromDamage(var9));
      super.particleRed = super.particleGreen = super.particleBlue = 1.0F;
      super.particleGravity = Blocks.snow.blockParticleGravity;
      super.particleScale /= 2.0F;
   }

   public EntityBreakingFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, Item var14, int var15) {
      this(var1, var2, var4, var6, var14, var15);
      super.motionX *= 0.10000000149011612D;
      super.motionY *= 0.10000000149011612D;
      super.motionZ *= 0.10000000149011612D;
      super.motionX += var8;
      super.motionY += var10;
      super.motionZ += var12;
   }

   public int getFXLayer() {
      return 2;
   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = ((float)super.particleTextureIndexX + super.particleTextureJitterX / 4.0F) / 16.0F;
      float var9 = var8 + 0.015609375F;
      float var10 = ((float)super.particleTextureIndexY + super.particleTextureJitterY / 4.0F) / 16.0F;
      float var11 = var10 + 0.015609375F;
      float var12 = 0.1F * super.particleScale;
      if(super.particleIcon != null) {
         var8 = super.particleIcon.getInterpolatedU((double)(super.particleTextureJitterX / 4.0F * 16.0F));
         var9 = super.particleIcon.getInterpolatedU((double)((super.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
         var10 = super.particleIcon.getInterpolatedV((double)(super.particleTextureJitterY / 4.0F * 16.0F));
         var11 = super.particleIcon.getInterpolatedV((double)((super.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
      }

      float var13 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)var2 - EntityFX.interpPosX);
      float var14 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)var2 - EntityFX.interpPosY);
      float var15 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)var2 - EntityFX.interpPosZ);
      var1.setColorOpaque_F(super.particleRed, super.particleGreen, super.particleBlue);
      var1.addVertexWithUV((double)(var13 - var3 * var12 - var6 * var12), (double)(var14 - var4 * var12), (double)(var15 - var5 * var12 - var7 * var12), (double)var8, (double)var11);
      var1.addVertexWithUV((double)(var13 - var3 * var12 + var6 * var12), (double)(var14 + var4 * var12), (double)(var15 - var5 * var12 + var7 * var12), (double)var8, (double)var10);
      var1.addVertexWithUV((double)(var13 + var3 * var12 + var6 * var12), (double)(var14 + var4 * var12), (double)(var15 + var5 * var12 + var7 * var12), (double)var9, (double)var10);
      var1.addVertexWithUV((double)(var13 + var3 * var12 - var6 * var12), (double)(var14 - var4 * var12), (double)(var15 + var5 * var12 - var7 * var12), (double)var9, (double)var11);
   }
}
