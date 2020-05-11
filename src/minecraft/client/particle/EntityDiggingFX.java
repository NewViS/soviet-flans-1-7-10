package net.minecraft.client.particle;

import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityDiggingFX extends EntityFX {

   private Block field_145784_a;


   public EntityDiggingFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, Block var14, int var15) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.field_145784_a = var14;
      this.setParticleIcon(var14.getIcon(0, var15));
      super.particleGravity = var14.blockParticleGravity;
      super.particleRed = super.particleGreen = super.particleBlue = 0.6F;
      super.particleScale /= 2.0F;
   }

   public EntityDiggingFX applyColourMultiplier(int var1, int var2, int var3) {
      if(this.field_145784_a == Blocks.grass) {
         return this;
      } else {
         int var4 = this.field_145784_a.colorMultiplier(super.worldObj, var1, var2, var3);
         super.particleRed *= (float)(var4 >> 16 & 255) / 255.0F;
         super.particleGreen *= (float)(var4 >> 8 & 255) / 255.0F;
         super.particleBlue *= (float)(var4 & 255) / 255.0F;
         return this;
      }
   }

   public EntityDiggingFX applyRenderColor(int var1) {
      if(this.field_145784_a == Blocks.grass) {
         return this;
      } else {
         int var2 = this.field_145784_a.getRenderColor(var1);
         super.particleRed *= (float)(var2 >> 16 & 255) / 255.0F;
         super.particleGreen *= (float)(var2 >> 8 & 255) / 255.0F;
         super.particleBlue *= (float)(var2 & 255) / 255.0F;
         return this;
      }
   }

   public int getFXLayer() {
      return 1;
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
