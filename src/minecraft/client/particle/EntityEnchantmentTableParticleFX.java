package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class EntityEnchantmentTableParticleFX extends EntityFX {

   private float field_70565_a;
   private double field_70568_aq;
   private double field_70567_ar;
   private double field_70566_as;


   public EntityEnchantmentTableParticleFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      super.motionX = var8;
      super.motionY = var10;
      super.motionZ = var12;
      this.field_70568_aq = super.posX = var2;
      this.field_70567_ar = super.posY = var4;
      this.field_70566_as = super.posZ = var6;
      float var14 = super.rand.nextFloat() * 0.6F + 0.4F;
      this.field_70565_a = super.particleScale = super.rand.nextFloat() * 0.5F + 0.2F;
      super.particleRed = super.particleGreen = super.particleBlue = 1.0F * var14;
      super.particleGreen *= 0.9F;
      super.particleRed *= 0.9F;
      super.particleMaxAge = (int)(Math.random() * 10.0D) + 30;
      super.noClip = true;
      this.setParticleTextureIndex((int)(Math.random() * 26.0D + 1.0D + 224.0D));
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
      var3 *= var3;
      var3 *= var3;
      return var2 * (1.0F - var3) + var3;
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      float var1 = (float)super.particleAge / (float)super.particleMaxAge;
      var1 = 1.0F - var1;
      float var2 = 1.0F - var1;
      var2 *= var2;
      var2 *= var2;
      super.posX = this.field_70568_aq + super.motionX * (double)var1;
      super.posY = this.field_70567_ar + super.motionY * (double)var1 - (double)(var2 * 1.2F);
      super.posZ = this.field_70566_as + super.motionZ * (double)var1;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

   }
}
