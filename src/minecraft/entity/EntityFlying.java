package net.minecraft.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityFlying extends EntityLiving {

   public EntityFlying(World var1) {
      super(var1);
   }

   protected void fall(float var1) {}

   protected void updateFallState(double var1, boolean var3) {}

   public void moveEntityWithHeading(float var1, float var2) {
      if(this.isInWater()) {
         this.moveFlying(var1, var2, 0.02F);
         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         super.motionX *= 0.800000011920929D;
         super.motionY *= 0.800000011920929D;
         super.motionZ *= 0.800000011920929D;
      } else if(this.handleLavaMovement()) {
         this.moveFlying(var1, var2, 0.02F);
         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         super.motionX *= 0.5D;
         super.motionY *= 0.5D;
         super.motionZ *= 0.5D;
      } else {
         float var3 = 0.91F;
         if(super.onGround) {
            var3 = super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.boundingBox.minY) - 1, MathHelper.floor_double(super.posZ)).slipperiness * 0.91F;
         }

         float var4 = 0.16277136F / (var3 * var3 * var3);
         this.moveFlying(var1, var2, super.onGround?0.1F * var4:0.02F);
         var3 = 0.91F;
         if(super.onGround) {
            var3 = super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.boundingBox.minY) - 1, MathHelper.floor_double(super.posZ)).slipperiness * 0.91F;
         }

         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         super.motionX *= (double)var3;
         super.motionY *= (double)var3;
         super.motionZ *= (double)var3;
      }

      super.prevLimbSwingAmount = super.limbSwingAmount;
      double var8 = super.posX - super.prevPosX;
      double var5 = super.posZ - super.prevPosZ;
      float var7 = MathHelper.sqrt_double(var8 * var8 + var5 * var5) * 4.0F;
      if(var7 > 1.0F) {
         var7 = 1.0F;
      }

      super.limbSwingAmount += (var7 - super.limbSwingAmount) * 0.4F;
      super.limbSwing += super.limbSwingAmount;
   }

   public boolean isOnLadder() {
      return false;
   }
}
