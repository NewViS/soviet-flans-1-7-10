package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityLavaFX extends EntityFX {

   private float lavaParticleScale;


   public EntityLavaFX(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
      super.motionX *= 0.800000011920929D;
      super.motionY *= 0.800000011920929D;
      super.motionZ *= 0.800000011920929D;
      super.motionY = (double)(super.rand.nextFloat() * 0.4F + 0.05F);
      super.particleRed = super.particleGreen = super.particleBlue = 1.0F;
      super.particleScale *= super.rand.nextFloat() * 2.0F + 0.2F;
      this.lavaParticleScale = super.particleScale;
      super.particleMaxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
      super.noClip = false;
      this.setParticleTextureIndex(49);
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
      short var4 = 240;
      int var5 = var3 >> 16 & 255;
      return var4 | var5 << 16;
   }

   public float getBrightness(float var1) {
      return 1.0F;
   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = ((float)super.particleAge + var2) / (float)super.particleMaxAge;
      super.particleScale = this.lavaParticleScale * (1.0F - var8 * var8);
      super.renderParticle(var1, var2, var3, var4, var5, var6, var7);
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

      float var1 = (float)super.particleAge / (float)super.particleMaxAge;
      if(super.rand.nextFloat() > var1) {
         super.worldObj.spawnParticle("smoke", super.posX, super.posY, super.posZ, super.motionX, super.motionY, super.motionZ);
      }

      super.motionY -= 0.03D;
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      super.motionX *= 0.9990000128746033D;
      super.motionY *= 0.9990000128746033D;
      super.motionZ *= 0.9990000128746033D;
      if(super.onGround) {
         super.motionX *= 0.699999988079071D;
         super.motionZ *= 0.699999988079071D;
      }

   }
}
