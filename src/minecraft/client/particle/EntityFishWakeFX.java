package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class EntityFishWakeFX extends EntityFX {

   public EntityFishWakeFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
      super.motionX *= 0.30000001192092896D;
      super.motionY = (double)((float)Math.random() * 0.2F + 0.1F);
      super.motionZ *= 0.30000001192092896D;
      super.particleRed = 1.0F;
      super.particleGreen = 1.0F;
      super.particleBlue = 1.0F;
      this.setParticleTextureIndex(19);
      this.setSize(0.01F, 0.01F);
      super.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
      super.particleGravity = 0.0F;
      super.motionX = var8;
      super.motionY = var10;
      super.motionZ = var12;
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      super.motionY -= (double)super.particleGravity;
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      super.motionX *= 0.9800000190734863D;
      super.motionY *= 0.9800000190734863D;
      super.motionZ *= 0.9800000190734863D;
      int var1 = 60 - super.particleMaxAge;
      float var2 = (float)var1 * 0.001F;
      this.setSize(var2, var2);
      this.setParticleTextureIndex(19 + var1 % 4);
      if(super.particleMaxAge-- <= 0) {
         this.setDead();
      }

   }
}
