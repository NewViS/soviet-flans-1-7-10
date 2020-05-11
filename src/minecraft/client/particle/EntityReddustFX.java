package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityReddustFX extends EntityFX {

   float reddustParticleScale;


   public EntityReddustFX(World var1, double var2, double var4, double var6, float var8, float var9, float var10) {
      this(var1, var2, var4, var6, 1.0F, var8, var9, var10);
   }

   public EntityReddustFX(World var1, double var2, double var4, double var6, float var8, float var9, float var10, float var11) {
      super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
      super.motionX *= 0.10000000149011612D;
      super.motionY *= 0.10000000149011612D;
      super.motionZ *= 0.10000000149011612D;
      if(var9 == 0.0F) {
         var9 = 1.0F;
      }

      float var12 = (float)Math.random() * 0.4F + 0.6F;
      super.particleRed = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * var9 * var12;
      super.particleGreen = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * var10 * var12;
      super.particleBlue = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * var11 * var12;
      super.particleScale *= 0.75F;
      super.particleScale *= var8;
      this.reddustParticleScale = super.particleScale;
      super.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
      super.particleMaxAge = (int)((float)super.particleMaxAge * var8);
      super.noClip = false;
   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = ((float)super.particleAge + var2) / (float)super.particleMaxAge * 32.0F;
      if(var8 < 0.0F) {
         var8 = 0.0F;
      }

      if(var8 > 1.0F) {
         var8 = 1.0F;
      }

      super.particleScale = this.reddustParticleScale * var8;
      super.renderParticle(var1, var2, var3, var4, var5, var6, var7);
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

      this.setParticleTextureIndex(7 - super.particleAge * 8 / super.particleMaxAge);
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      if(super.posY == super.prevPosY) {
         super.motionX *= 1.1D;
         super.motionZ *= 1.1D;
      }

      super.motionX *= 0.9599999785423279D;
      super.motionY *= 0.9599999785423279D;
      super.motionZ *= 0.9599999785423279D;
      if(super.onGround) {
         super.motionX *= 0.699999988079071D;
         super.motionZ *= 0.699999988079071D;
      }

   }
}
