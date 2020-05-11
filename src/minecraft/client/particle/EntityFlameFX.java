package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityFlameFX extends EntityFX {

   private float flameScale;


   public EntityFlameFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      super.motionX = super.motionX * 0.009999999776482582D + var8;
      super.motionY = super.motionY * 0.009999999776482582D + var10;
      super.motionZ = super.motionZ * 0.009999999776482582D + var12;
      double var10000 = var2 + (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.05F);
      var10000 = var4 + (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.05F);
      var10000 = var6 + (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.05F);
      this.flameScale = super.particleScale;
      super.particleRed = super.particleGreen = super.particleBlue = 1.0F;
      super.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
      super.noClip = true;
      this.setParticleTextureIndex(48);
   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = ((float)super.particleAge + var2) / (float)super.particleMaxAge;
      super.particleScale = this.flameScale * (1.0F - var8 * var8 * 0.5F);
      super.renderParticle(var1, var2, var3, var4, var5, var6, var7);
   }

   public int getBrightnessForRender(float var1) {
      float var2 = ((float)super.particleAge + var1) / (float)super.particleMaxAge;
      if(var2 < 0.0F) {
         var2 = 0.0F;
      }

      if(var2 > 1.0F) {
         var2 = 1.0F;
      }

      int var3 = super.getBrightnessForRender(var1);
      int var4 = var3 & 255;
      int var5 = var3 >> 16 & 255;
      var4 += (int)(var2 * 15.0F * 16.0F);
      if(var4 > 240) {
         var4 = 240;
      }

      return var4 | var5 << 16;
   }

   public float getBrightness(float var1) {
      float var2 = ((float)super.particleAge + var1) / (float)super.particleMaxAge;
      if(var2 < 0.0F) {
         var2 = 0.0F;
      }

      if(var2 > 1.0F) {
         var2 = 1.0F;
      }

      float var3 = super.getBrightness(var1);
      return var3 * var2 + (1.0F - var2);
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      super.motionX *= 0.9599999785423279D;
      super.motionY *= 0.9599999785423279D;
      super.motionZ *= 0.9599999785423279D;
      if(super.onGround) {
         super.motionX *= 0.699999988079071D;
         super.motionZ *= 0.699999988079071D;
      }

   }
}
