package net.minecraft.entity.passive;

import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySquid extends EntityWaterMob {

   public float squidPitch;
   public float prevSquidPitch;
   public float squidYaw;
   public float prevSquidYaw;
   public float squidRotation;
   public float prevSquidRotation;
   public float tentacleAngle;
   public float lastTentacleAngle;
   private float randomMotionSpeed;
   private float rotationVelocity;
   private float field_70871_bB;
   private float randomMotionVecX;
   private float randomMotionVecY;
   private float randomMotionVecZ;


   public EntitySquid(World var1) {
      super(var1);
      this.setSize(0.95F, 0.95F);
      this.rotationVelocity = 1.0F / (super.rand.nextFloat() + 1.0F) * 0.2F;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
   }

   protected String getLivingSound() {
      return null;
   }

   protected String getHurtSound() {
      return null;
   }

   protected String getDeathSound() {
      return null;
   }

   protected float getSoundVolume() {
      return 0.4F;
   }

   protected Item getDropItem() {
      return Item.getItemById(0);
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void dropFewItems(boolean var1, int var2) {
      int var3 = super.rand.nextInt(3 + var2) + 1;

      for(int var4 = 0; var4 < var3; ++var4) {
         this.entityDropItem(new ItemStack(Items.dye, 1, 0), 0.0F);
      }

   }

   public boolean isInWater() {
      return super.worldObj.handleMaterialAcceleration(super.boundingBox.expand(0.0D, -0.6000000238418579D, 0.0D), Material.water, this);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.prevSquidPitch = this.squidPitch;
      this.prevSquidYaw = this.squidYaw;
      this.prevSquidRotation = this.squidRotation;
      this.lastTentacleAngle = this.tentacleAngle;
      this.squidRotation += this.rotationVelocity;
      if(this.squidRotation > 6.2831855F) {
         this.squidRotation -= 6.2831855F;
         if(super.rand.nextInt(10) == 0) {
            this.rotationVelocity = 1.0F / (super.rand.nextFloat() + 1.0F) * 0.2F;
         }
      }

      if(this.isInWater()) {
         float var1;
         if(this.squidRotation < 3.1415927F) {
            var1 = this.squidRotation / 3.1415927F;
            this.tentacleAngle = MathHelper.sin(var1 * var1 * 3.1415927F) * 3.1415927F * 0.25F;
            if((double)var1 > 0.75D) {
               this.randomMotionSpeed = 1.0F;
               this.field_70871_bB = 1.0F;
            } else {
               this.field_70871_bB *= 0.8F;
            }
         } else {
            this.tentacleAngle = 0.0F;
            this.randomMotionSpeed *= 0.9F;
            this.field_70871_bB *= 0.99F;
         }

         if(!super.worldObj.isRemote) {
            super.motionX = (double)(this.randomMotionVecX * this.randomMotionSpeed);
            super.motionY = (double)(this.randomMotionVecY * this.randomMotionSpeed);
            super.motionZ = (double)(this.randomMotionVecZ * this.randomMotionSpeed);
         }

         var1 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
         super.renderYawOffset += (-((float)Math.atan2(super.motionX, super.motionZ)) * 180.0F / 3.1415927F - super.renderYawOffset) * 0.1F;
         super.rotationYaw = super.renderYawOffset;
         this.squidYaw += 3.1415927F * this.field_70871_bB * 1.5F;
         this.squidPitch += (-((float)Math.atan2((double)var1, super.motionY)) * 180.0F / 3.1415927F - this.squidPitch) * 0.1F;
      } else {
         this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * 3.1415927F * 0.25F;
         if(!super.worldObj.isRemote) {
            super.motionX = 0.0D;
            super.motionY -= 0.08D;
            super.motionY *= 0.9800000190734863D;
            super.motionZ = 0.0D;
         }

         this.squidPitch = (float)((double)this.squidPitch + (double)(-90.0F - this.squidPitch) * 0.02D);
      }

   }

   public void moveEntityWithHeading(float var1, float var2) {
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
   }

   protected void updateEntityActionState() {
      ++super.entityAge;
      if(super.entityAge > 100) {
         this.randomMotionVecX = this.randomMotionVecY = this.randomMotionVecZ = 0.0F;
      } else if(super.rand.nextInt(50) == 0 || !super.inWater || this.randomMotionVecX == 0.0F && this.randomMotionVecY == 0.0F && this.randomMotionVecZ == 0.0F) {
         float var1 = super.rand.nextFloat() * 3.1415927F * 2.0F;
         this.randomMotionVecX = MathHelper.cos(var1) * 0.2F;
         this.randomMotionVecY = -0.1F + super.rand.nextFloat() * 0.2F;
         this.randomMotionVecZ = MathHelper.sin(var1) * 0.2F;
      }

      this.despawnEntity();
   }

   public boolean getCanSpawnHere() {
      return super.posY > 45.0D && super.posY < 63.0D && super.getCanSpawnHere();
   }
}
