package net.minecraft.entity.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBoat extends Entity {

   private boolean isBoatEmpty;
   private double speedMultiplier;
   private int boatPosRotationIncrements;
   private double boatX;
   private double boatY;
   private double boatZ;
   private double boatYaw;
   private double boatPitch;
   private double velocityX;
   private double velocityY;
   private double velocityZ;


   public EntityBoat(World var1) {
      super(var1);
      this.isBoatEmpty = true;
      this.speedMultiplier = 0.07D;
      super.preventEntitySpawning = true;
      this.setSize(1.5F, 0.6F);
      super.yOffset = super.height / 2.0F;
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void entityInit() {
      super.dataWatcher.addObject(17, new Integer(0));
      super.dataWatcher.addObject(18, new Integer(1));
      super.dataWatcher.addObject(19, new Float(0.0F));
   }

   public AxisAlignedBB getCollisionBox(Entity var1) {
      return var1.boundingBox;
   }

   public AxisAlignedBB getBoundingBox() {
      return super.boundingBox;
   }

   public boolean canBePushed() {
      return true;
   }

   public EntityBoat(World var1, double var2, double var4, double var6) {
      this(var1);
      this.setPosition(var2, var4 + (double)super.yOffset, var6);
      super.motionX = 0.0D;
      super.motionY = 0.0D;
      super.motionZ = 0.0D;
      super.prevPosX = var2;
      super.prevPosY = var4;
      super.prevPosZ = var6;
   }

   public double getMountedYOffset() {
      return (double)super.height * 0.0D - 0.30000001192092896D;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else if(!super.worldObj.isRemote && !super.isDead) {
         this.setForwardDirection(-this.getForwardDirection());
         this.setTimeSinceHit(10);
         this.setDamageTaken(this.getDamageTaken() + var2 * 10.0F);
         this.setBeenAttacked();
         boolean var3 = var1.getEntity() instanceof EntityPlayer && ((EntityPlayer)var1.getEntity()).capabilities.isCreativeMode;
         if(var3 || this.getDamageTaken() > 40.0F) {
            if(super.riddenByEntity != null) {
               super.riddenByEntity.mountEntity(this);
            }

            if(!var3) {
               this.func_145778_a(Items.boat, 1, 0.0F);
            }

            this.setDead();
         }

         return true;
      } else {
         return true;
      }
   }

   public void performHurtAnimation() {
      this.setForwardDirection(-this.getForwardDirection());
      this.setTimeSinceHit(10);
      this.setDamageTaken(this.getDamageTaken() * 11.0F);
   }

   public boolean canBeCollidedWith() {
      return !super.isDead;
   }

   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9) {
      if(this.isBoatEmpty) {
         this.boatPosRotationIncrements = var9 + 5;
      } else {
         double var10 = var1 - super.posX;
         double var12 = var3 - super.posY;
         double var14 = var5 - super.posZ;
         double var16 = var10 * var10 + var12 * var12 + var14 * var14;
         if(var16 <= 1.0D) {
            return;
         }

         this.boatPosRotationIncrements = 3;
      }

      this.boatX = var1;
      this.boatY = var3;
      this.boatZ = var5;
      this.boatYaw = (double)var7;
      this.boatPitch = (double)var8;
      super.motionX = this.velocityX;
      super.motionY = this.velocityY;
      super.motionZ = this.velocityZ;
   }

   public void setVelocity(double var1, double var3, double var5) {
      this.velocityX = super.motionX = var1;
      this.velocityY = super.motionY = var3;
      this.velocityZ = super.motionZ = var5;
   }

   public void onUpdate() {
      super.onUpdate();
      if(this.getTimeSinceHit() > 0) {
         this.setTimeSinceHit(this.getTimeSinceHit() - 1);
      }

      if(this.getDamageTaken() > 0.0F) {
         this.setDamageTaken(this.getDamageTaken() - 1.0F);
      }

      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      byte var1 = 5;
      double var2 = 0.0D;

      for(int var4 = 0; var4 < var1; ++var4) {
         double var5 = super.boundingBox.minY + (super.boundingBox.maxY - super.boundingBox.minY) * (double)(var4 + 0) / (double)var1 - 0.125D;
         double var7 = super.boundingBox.minY + (super.boundingBox.maxY - super.boundingBox.minY) * (double)(var4 + 1) / (double)var1 - 0.125D;
         AxisAlignedBB var9 = AxisAlignedBB.getBoundingBox(super.boundingBox.minX, var5, super.boundingBox.minZ, super.boundingBox.maxX, var7, super.boundingBox.maxZ);
         if(super.worldObj.isAABBInMaterial(var9, Material.water)) {
            var2 += 1.0D / (double)var1;
         }
      }

      double var19 = Math.sqrt(super.motionX * super.motionX + super.motionZ * super.motionZ);
      double var6;
      double var8;
      int var10;
      if(var19 > 0.26249999999999996D) {
         var6 = Math.cos((double)super.rotationYaw * 3.141592653589793D / 180.0D);
         var8 = Math.sin((double)super.rotationYaw * 3.141592653589793D / 180.0D);

         for(var10 = 0; (double)var10 < 1.0D + var19 * 60.0D; ++var10) {
            double var11 = (double)(super.rand.nextFloat() * 2.0F - 1.0F);
            double var13 = (double)(super.rand.nextInt(2) * 2 - 1) * 0.7D;
            double var15;
            double var17;
            if(super.rand.nextBoolean()) {
               var15 = super.posX - var6 * var11 * 0.8D + var8 * var13;
               var17 = super.posZ - var8 * var11 * 0.8D - var6 * var13;
               super.worldObj.spawnParticle("splash", var15, super.posY - 0.125D, var17, super.motionX, super.motionY, super.motionZ);
            } else {
               var15 = super.posX + var6 + var8 * var11 * 0.7D;
               var17 = super.posZ + var8 - var6 * var11 * 0.7D;
               super.worldObj.spawnParticle("splash", var15, super.posY - 0.125D, var17, super.motionX, super.motionY, super.motionZ);
            }
         }
      }

      double var24;
      double var26;
      if(super.worldObj.isRemote && this.isBoatEmpty) {
         if(this.boatPosRotationIncrements > 0) {
            var6 = super.posX + (this.boatX - super.posX) / (double)this.boatPosRotationIncrements;
            var8 = super.posY + (this.boatY - super.posY) / (double)this.boatPosRotationIncrements;
            var24 = super.posZ + (this.boatZ - super.posZ) / (double)this.boatPosRotationIncrements;
            var26 = MathHelper.wrapAngleTo180_double(this.boatYaw - (double)super.rotationYaw);
            super.rotationYaw = (float)((double)super.rotationYaw + var26 / (double)this.boatPosRotationIncrements);
            super.rotationPitch = (float)((double)super.rotationPitch + (this.boatPitch - (double)super.rotationPitch) / (double)this.boatPosRotationIncrements);
            --this.boatPosRotationIncrements;
            this.setPosition(var6, var8, var24);
            this.setRotation(super.rotationYaw, super.rotationPitch);
         } else {
            var6 = super.posX + super.motionX;
            var8 = super.posY + super.motionY;
            var24 = super.posZ + super.motionZ;
            this.setPosition(var6, var8, var24);
            if(super.onGround) {
               super.motionX *= 0.5D;
               super.motionY *= 0.5D;
               super.motionZ *= 0.5D;
            }

            super.motionX *= 0.9900000095367432D;
            super.motionY *= 0.949999988079071D;
            super.motionZ *= 0.9900000095367432D;
         }

      } else {
         if(var2 < 1.0D) {
            var6 = var2 * 2.0D - 1.0D;
            super.motionY += 0.03999999910593033D * var6;
         } else {
            if(super.motionY < 0.0D) {
               super.motionY /= 2.0D;
            }

            super.motionY += 0.007000000216066837D;
         }

         if(super.riddenByEntity != null && super.riddenByEntity instanceof EntityLivingBase) {
            EntityLivingBase var20 = (EntityLivingBase)super.riddenByEntity;
            float var21 = super.riddenByEntity.rotationYaw + -var20.moveStrafing * 90.0F;
            super.motionX += -Math.sin((double)(var21 * 3.1415927F / 180.0F)) * this.speedMultiplier * (double)var20.moveForward * 0.05000000074505806D;
            super.motionZ += Math.cos((double)(var21 * 3.1415927F / 180.0F)) * this.speedMultiplier * (double)var20.moveForward * 0.05000000074505806D;
         }

         var6 = Math.sqrt(super.motionX * super.motionX + super.motionZ * super.motionZ);
         if(var6 > 0.35D) {
            var8 = 0.35D / var6;
            super.motionX *= var8;
            super.motionZ *= var8;
            var6 = 0.35D;
         }

         if(var6 > var19 && this.speedMultiplier < 0.35D) {
            this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;
            if(this.speedMultiplier > 0.35D) {
               this.speedMultiplier = 0.35D;
            }
         } else {
            this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;
            if(this.speedMultiplier < 0.07D) {
               this.speedMultiplier = 0.07D;
            }
         }

         int var22;
         for(var22 = 0; var22 < 4; ++var22) {
            int var23 = MathHelper.floor_double(super.posX + ((double)(var22 % 2) - 0.5D) * 0.8D);
            var10 = MathHelper.floor_double(super.posZ + ((double)(var22 / 2) - 0.5D) * 0.8D);

            for(int var25 = 0; var25 < 2; ++var25) {
               int var12 = MathHelper.floor_double(super.posY) + var25;
               Block var27 = super.worldObj.getBlock(var23, var12, var10);
               if(var27 == Blocks.snow_layer) {
                  super.worldObj.setBlockToAir(var23, var12, var10);
                  super.isCollidedHorizontally = false;
               } else if(var27 == Blocks.waterlily) {
                  super.worldObj.func_147480_a(var23, var12, var10, true);
                  super.isCollidedHorizontally = false;
               }
            }
         }

         if(super.onGround) {
            super.motionX *= 0.5D;
            super.motionY *= 0.5D;
            super.motionZ *= 0.5D;
         }

         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         if(super.isCollidedHorizontally && var19 > 0.2D) {
            if(!super.worldObj.isRemote && !super.isDead) {
               this.setDead();

               for(var22 = 0; var22 < 3; ++var22) {
                  this.func_145778_a(Item.getItemFromBlock(Blocks.planks), 1, 0.0F);
               }

               for(var22 = 0; var22 < 2; ++var22) {
                  this.func_145778_a(Items.stick, 1, 0.0F);
               }
            }
         } else {
            super.motionX *= 0.9900000095367432D;
            super.motionY *= 0.949999988079071D;
            super.motionZ *= 0.9900000095367432D;
         }

         super.rotationPitch = 0.0F;
         var8 = (double)super.rotationYaw;
         var24 = super.prevPosX - super.posX;
         var26 = super.prevPosZ - super.posZ;
         if(var24 * var24 + var26 * var26 > 0.001D) {
            var8 = (double)((float)(Math.atan2(var26, var24) * 180.0D / 3.141592653589793D));
         }

         double var14 = MathHelper.wrapAngleTo180_double(var8 - (double)super.rotationYaw);
         if(var14 > 20.0D) {
            var14 = 20.0D;
         }

         if(var14 < -20.0D) {
            var14 = -20.0D;
         }

         super.rotationYaw = (float)((double)super.rotationYaw + var14);
         this.setRotation(super.rotationYaw, super.rotationPitch);
         if(!super.worldObj.isRemote) {
            List var16 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
            if(var16 != null && !var16.isEmpty()) {
               for(int var28 = 0; var28 < var16.size(); ++var28) {
                  Entity var18 = (Entity)var16.get(var28);
                  if(var18 != super.riddenByEntity && var18.canBePushed() && var18 instanceof EntityBoat) {
                     var18.applyEntityCollision(this);
                  }
               }
            }

            if(super.riddenByEntity != null && super.riddenByEntity.isDead) {
               super.riddenByEntity = null;
            }

         }
      }
   }

   public void updateRiderPosition() {
      if(super.riddenByEntity != null) {
         double var1 = Math.cos((double)super.rotationYaw * 3.141592653589793D / 180.0D) * 0.4D;
         double var3 = Math.sin((double)super.rotationYaw * 3.141592653589793D / 180.0D) * 0.4D;
         super.riddenByEntity.setPosition(super.posX + var1, super.posY + this.getMountedYOffset() + super.riddenByEntity.getYOffset(), super.posZ + var3);
      }
   }

   protected void writeEntityToNBT(NBTTagCompound var1) {}

   protected void readEntityFromNBT(NBTTagCompound var1) {}

   public float getShadowSize() {
      return 0.0F;
   }

   public boolean interactFirst(EntityPlayer var1) {
      if(super.riddenByEntity != null && super.riddenByEntity instanceof EntityPlayer && super.riddenByEntity != var1) {
         return true;
      } else {
         if(!super.worldObj.isRemote) {
            var1.mountEntity(this);
         }

         return true;
      }
   }

   protected void updateFallState(double var1, boolean var3) {
      int var4 = MathHelper.floor_double(super.posX);
      int var5 = MathHelper.floor_double(super.posY);
      int var6 = MathHelper.floor_double(super.posZ);
      if(var3) {
         if(super.fallDistance > 3.0F) {
            this.fall(super.fallDistance);
            if(!super.worldObj.isRemote && !super.isDead) {
               this.setDead();

               int var7;
               for(var7 = 0; var7 < 3; ++var7) {
                  this.func_145778_a(Item.getItemFromBlock(Blocks.planks), 1, 0.0F);
               }

               for(var7 = 0; var7 < 2; ++var7) {
                  this.func_145778_a(Items.stick, 1, 0.0F);
               }
            }

            super.fallDistance = 0.0F;
         }
      } else if(super.worldObj.getBlock(var4, var5 - 1, var6).getMaterial() != Material.water && var1 < 0.0D) {
         super.fallDistance = (float)((double)super.fallDistance - var1);
      }

   }

   public void setDamageTaken(float var1) {
      super.dataWatcher.updateObject(19, Float.valueOf(var1));
   }

   public float getDamageTaken() {
      return super.dataWatcher.getWatchableObjectFloat(19);
   }

   public void setTimeSinceHit(int var1) {
      super.dataWatcher.updateObject(17, Integer.valueOf(var1));
   }

   public int getTimeSinceHit() {
      return super.dataWatcher.getWatchableObjectInt(17);
   }

   public void setForwardDirection(int var1) {
      super.dataWatcher.updateObject(18, Integer.valueOf(var1));
   }

   public int getForwardDirection() {
      return super.dataWatcher.getWatchableObjectInt(18);
   }

   public void setIsBoatEmpty(boolean var1) {
      this.isBoatEmpty = var1;
   }
}
