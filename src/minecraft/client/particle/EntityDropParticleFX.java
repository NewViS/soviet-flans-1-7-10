package net.minecraft.client.particle;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDropParticleFX extends EntityFX {

   private Material materialType;
   private int bobTimer;


   public EntityDropParticleFX(World var1, double var2, double var4, double var6, Material var8) {
      super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
      super.motionX = super.motionY = super.motionZ = 0.0D;
      if(var8 == Material.water) {
         super.particleRed = 0.0F;
         super.particleGreen = 0.0F;
         super.particleBlue = 1.0F;
      } else {
         super.particleRed = 1.0F;
         super.particleGreen = 0.0F;
         super.particleBlue = 0.0F;
      }

      this.setParticleTextureIndex(113);
      this.setSize(0.01F, 0.01F);
      super.particleGravity = 0.06F;
      this.materialType = var8;
      this.bobTimer = 40;
      super.particleMaxAge = (int)(64.0D / (Math.random() * 0.8D + 0.2D));
      super.motionX = super.motionY = super.motionZ = 0.0D;
   }

   public int getBrightnessForRender(float var1) {
      return this.materialType == Material.water?super.getBrightnessForRender(var1):257;
   }

   public float getBrightness(float var1) {
      return this.materialType == Material.water?super.getBrightness(var1):1.0F;
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(this.materialType == Material.water) {
         super.particleRed = 0.2F;
         super.particleGreen = 0.3F;
         super.particleBlue = 1.0F;
      } else {
         super.particleRed = 1.0F;
         super.particleGreen = 16.0F / (float)(40 - this.bobTimer + 16);
         super.particleBlue = 4.0F / (float)(40 - this.bobTimer + 8);
      }

      super.motionY -= (double)super.particleGravity;
      if(this.bobTimer-- > 0) {
         super.motionX *= 0.02D;
         super.motionY *= 0.02D;
         super.motionZ *= 0.02D;
         this.setParticleTextureIndex(113);
      } else {
         this.setParticleTextureIndex(112);
      }

      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      super.motionX *= 0.9800000190734863D;
      super.motionY *= 0.9800000190734863D;
      super.motionZ *= 0.9800000190734863D;
      if(super.particleMaxAge-- <= 0) {
         this.setDead();
      }

      if(super.onGround) {
         if(this.materialType == Material.water) {
            this.setDead();
            super.worldObj.spawnParticle("splash", super.posX, super.posY, super.posZ, 0.0D, 0.0D, 0.0D);
         } else {
            this.setParticleTextureIndex(114);
         }

         super.motionX *= 0.699999988079071D;
         super.motionZ *= 0.699999988079071D;
      }

      Material var1 = super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ)).getMaterial();
      if(var1.isLiquid() || var1.isSolid()) {
         double var2 = (double)((float)(MathHelper.floor_double(super.posY) + 1) - BlockLiquid.getLiquidHeightPercent(super.worldObj.getBlockMetadata(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ))));
         if(super.posY < var2) {
            this.setDead();
         }
      }

   }
}
