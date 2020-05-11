package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class EntityAuraFX extends EntityFX {

   public EntityAuraFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      float var14 = super.rand.nextFloat() * 0.1F + 0.2F;
      super.particleRed = var14;
      super.particleGreen = var14;
      super.particleBlue = var14;
      this.setParticleTextureIndex(0);
      this.setSize(0.02F, 0.02F);
      super.particleScale *= super.rand.nextFloat() * 0.6F + 0.5F;
      super.motionX *= 0.019999999552965164D;
      super.motionY *= 0.019999999552965164D;
      super.motionZ *= 0.019999999552965164D;
      super.particleMaxAge = (int)(20.0D / (Math.random() * 0.8D + 0.2D));
      super.noClip = true;
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      super.motionX *= 0.99D;
      super.motionY *= 0.99D;
      super.motionZ *= 0.99D;
      if(super.particleMaxAge-- <= 0) {
         this.setDead();
      }

   }
}
