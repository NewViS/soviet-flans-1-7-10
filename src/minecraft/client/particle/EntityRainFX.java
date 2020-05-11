package net.minecraft.client.particle;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityRainFX extends EntityFX {

   public EntityRainFX(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
      super.motionX *= 0.30000001192092896D;
      super.motionY = (double)((float)Math.random() * 0.2F + 0.1F);
      super.motionZ *= 0.30000001192092896D;
      super.particleRed = 1.0F;
      super.particleGreen = 1.0F;
      super.particleBlue = 1.0F;
      this.setParticleTextureIndex(19 + super.rand.nextInt(4));
      this.setSize(0.01F, 0.01F);
      super.particleGravity = 0.06F;
      super.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
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
      if(super.particleMaxAge-- <= 0) {
         this.setDead();
      }

      if(super.onGround) {
         if(Math.random() < 0.5D) {
            this.setDead();
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
