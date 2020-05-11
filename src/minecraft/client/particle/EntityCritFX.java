package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityCritFX extends EntityFX {

   float initialParticleScale;


   public EntityCritFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      this(var1, var2, var4, var6, var8, var10, var12, 1.0F);
   }

   public EntityCritFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, float var14) {
      super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
      super.motionX *= 0.10000000149011612D;
      super.motionY *= 0.10000000149011612D;
      super.motionZ *= 0.10000000149011612D;
      super.motionX += var8 * 0.4D;
      super.motionY += var10 * 0.4D;
      super.motionZ += var12 * 0.4D;
      super.particleRed = super.particleGreen = super.particleBlue = (float)(Math.random() * 0.30000001192092896D + 0.6000000238418579D);
      super.particleScale *= 0.75F;
      super.particleScale *= var14;
      this.initialParticleScale = super.particleScale;
      super.particleMaxAge = (int)(6.0D / (Math.random() * 0.8D + 0.6D));
      super.particleMaxAge = (int)((float)super.particleMaxAge * var14);
      super.noClip = false;
      this.setParticleTextureIndex(65);
      this.onUpdate();
   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = ((float)super.particleAge + var2) / (float)super.particleMaxAge * 32.0F;
      if(var8 < 0.0F) {
         var8 = 0.0F;
      }

      if(var8 > 1.0F) {
         var8 = 1.0F;
      }

      super.particleScale = this.initialParticleScale * var8;
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
      super.particleGreen = (float)((double)super.particleGreen * 0.96D);
      super.particleBlue = (float)((double)super.particleBlue * 0.9D);
      super.motionX *= 0.699999988079071D;
      super.motionY *= 0.699999988079071D;
      super.motionZ *= 0.699999988079071D;
      super.motionY -= 0.019999999552965164D;
      if(super.onGround) {
         super.motionX *= 0.699999988079071D;
         super.motionZ *= 0.699999988079071D;
      }

   }
}
