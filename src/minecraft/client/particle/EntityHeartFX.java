package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityHeartFX extends EntityFX {

   float particleScaleOverTime;


   public EntityHeartFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      this(var1, var2, var4, var6, var8, var10, var12, 2.0F);
   }

   public EntityHeartFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, float var14) {
      super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
      super.motionX *= 0.009999999776482582D;
      super.motionY *= 0.009999999776482582D;
      super.motionZ *= 0.009999999776482582D;
      super.motionY += 0.1D;
      super.particleScale *= 0.75F;
      super.particleScale *= var14;
      this.particleScaleOverTime = super.particleScale;
      super.particleMaxAge = 16;
      super.noClip = false;
      this.setParticleTextureIndex(80);
   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = ((float)super.particleAge + var2) / (float)super.particleMaxAge * 32.0F;
      if(var8 < 0.0F) {
         var8 = 0.0F;
      }

      if(var8 > 1.0F) {
         var8 = 1.0F;
      }

      super.particleScale = this.particleScaleOverTime * var8;
      super.renderParticle(var1, var2, var3, var4, var5, var6, var7);
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      if(super.posY == super.prevPosY) {
         super.motionX *= 1.1D;
         super.motionZ *= 1.1D;
      }

      super.motionX *= 0.8600000143051147D;
      super.motionY *= 0.8600000143051147D;
      super.motionZ *= 0.8600000143051147D;
      if(super.onGround) {
         super.motionX *= 0.699999988079071D;
         super.motionZ *= 0.699999988079071D;
      }

   }
}
