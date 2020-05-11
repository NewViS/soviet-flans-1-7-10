package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityPortalFX extends EntityFX {

   private float portalParticleScale;
   private double portalPosX;
   private double portalPosY;
   private double portalPosZ;


   public EntityPortalFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      super.motionX = var8;
      super.motionY = var10;
      super.motionZ = var12;
      this.portalPosX = super.posX = var2;
      this.portalPosY = super.posY = var4;
      this.portalPosZ = super.posZ = var6;
      float var14 = super.rand.nextFloat() * 0.6F + 0.4F;
      this.portalParticleScale = super.particleScale = super.rand.nextFloat() * 0.2F + 0.5F;
      super.particleRed = super.particleGreen = super.particleBlue = 1.0F * var14;
      super.particleGreen *= 0.3F;
      super.particleRed *= 0.9F;
      super.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
      super.noClip = true;
      this.setParticleTextureIndex((int)(Math.random() * 8.0D));
   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = ((float)super.particleAge + var2) / (float)super.particleMaxAge;
      var8 = 1.0F - var8;
      var8 *= var8;
      var8 = 1.0F - var8;
      super.particleScale = this.portalParticleScale * var8;
      super.renderParticle(var1, var2, var3, var4, var5, var6, var7);
   }

   public int getBrightnessForRender(float var1) {
      int var2 = super.getBrightnessForRender(var1);
      float var3 = (float)super.particleAge / (float)super.particleMaxAge;
      var3 *= var3;
      var3 *= var3;
      int var4 = var2 & 255;
      int var5 = var2 >> 16 & 255;
      var5 += (int)(var3 * 15.0F * 16.0F);
      if(var5 > 240) {
         var5 = 240;
      }

      return var4 | var5 << 16;
   }

   public float getBrightness(float var1) {
      float var2 = super.getBrightness(var1);
      float var3 = (float)super.particleAge / (float)super.particleMaxAge;
      var3 = var3 * var3 * var3 * var3;
      return var2 * (1.0F - var3) + var3;
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      float var1 = (float)super.particleAge / (float)super.particleMaxAge;
      float var2 = var1;
      var1 = -var1 + var1 * var1 * 2.0F;
      var1 = 1.0F - var1;
      super.posX = this.portalPosX + super.motionX * (double)var1;
      super.posY = this.portalPosY + super.motionY * (double)var1 + (double)(1.0F - var2);
      super.posZ = this.portalPosZ + super.motionZ * (double)var1;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

   }
}
