package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class EntityExplodeFX extends EntityFX {

   public EntityExplodeFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      super.motionX = var8 + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.05F);
      super.motionY = var10 + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.05F);
      super.motionZ = var12 + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.05F);
      super.particleRed = super.particleGreen = super.particleBlue = super.rand.nextFloat() * 0.3F + 0.7F;
      super.particleScale = super.rand.nextFloat() * super.rand.nextFloat() * 6.0F + 1.0F;
      super.particleMaxAge = (int)(16.0D / ((double)super.rand.nextFloat() * 0.8D + 0.2D)) + 2;
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

      this.setParticleTextureIndex(7 - super.particleAge * 8 / super.particleMaxAge);
      super.motionY += 0.004D;
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      super.motionX *= 0.8999999761581421D;
      super.motionY *= 0.8999999761581421D;
      super.motionZ *= 0.8999999761581421D;
      if(super.onGround) {
         super.motionX *= 0.699999988079071D;
         super.motionZ *= 0.699999988079071D;
      }

   }
}
