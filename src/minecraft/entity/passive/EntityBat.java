package net.minecraft.entity.passive;

import java.util.Calendar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBat extends EntityAmbientCreature {

   private ChunkCoordinates spawnPosition;


   public EntityBat(World var1) {
      super(var1);
      this.setSize(0.5F, 0.9F);
      this.setIsBatHanging(true);
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, new Byte((byte)0));
   }

   protected float getSoundVolume() {
      return 0.1F;
   }

   protected float getSoundPitch() {
      return super.getSoundPitch() * 0.95F;
   }

   protected String getLivingSound() {
      return this.getIsBatHanging() && super.rand.nextInt(4) != 0?null:"mob.bat.idle";
   }

   protected String getHurtSound() {
      return "mob.bat.hurt";
   }

   protected String getDeathSound() {
      return "mob.bat.death";
   }

   public boolean canBePushed() {
      return false;
   }

   protected void collideWithEntity(Entity var1) {}

   protected void collideWithNearbyEntities() {}

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0D);
   }

   public boolean getIsBatHanging() {
      return (super.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   public void setIsBatHanging(boolean var1) {
      byte var2 = super.dataWatcher.getWatchableObjectByte(16);
      if(var1) {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 1)));
      } else {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -2)));
      }

   }

   protected boolean isAIEnabled() {
      return true;
   }

   public void onUpdate() {
      super.onUpdate();
      if(this.getIsBatHanging()) {
         super.motionX = super.motionY = super.motionZ = 0.0D;
         super.posY = (double)MathHelper.floor_double(super.posY) + 1.0D - (double)super.height;
      } else {
         super.motionY *= 0.6000000238418579D;
      }

   }

   protected void updateAITasks() {
      super.updateAITasks();
      if(this.getIsBatHanging()) {
         if(!super.worldObj.getBlock(MathHelper.floor_double(super.posX), (int)super.posY + 1, MathHelper.floor_double(super.posZ)).isNormalCube()) {
            this.setIsBatHanging(false);
            super.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)super.posX, (int)super.posY, (int)super.posZ, 0);
         } else {
            if(super.rand.nextInt(200) == 0) {
               super.rotationYawHead = (float)super.rand.nextInt(360);
            }

            if(super.worldObj.getClosestPlayerToEntity(this, 4.0D) != null) {
               this.setIsBatHanging(false);
               super.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)super.posX, (int)super.posY, (int)super.posZ, 0);
            }
         }
      } else {
         if(this.spawnPosition != null && (!super.worldObj.isAirBlock(this.spawnPosition.posX, this.spawnPosition.posY, this.spawnPosition.posZ) || this.spawnPosition.posY < 1)) {
            this.spawnPosition = null;
         }

         if(this.spawnPosition == null || super.rand.nextInt(30) == 0 || this.spawnPosition.getDistanceSquared((int)super.posX, (int)super.posY, (int)super.posZ) < 4.0F) {
            this.spawnPosition = new ChunkCoordinates((int)super.posX + super.rand.nextInt(7) - super.rand.nextInt(7), (int)super.posY + super.rand.nextInt(6) - 2, (int)super.posZ + super.rand.nextInt(7) - super.rand.nextInt(7));
         }

         double var1 = (double)this.spawnPosition.posX + 0.5D - super.posX;
         double var3 = (double)this.spawnPosition.posY + 0.1D - super.posY;
         double var5 = (double)this.spawnPosition.posZ + 0.5D - super.posZ;
         super.motionX += (Math.signum(var1) * 0.5D - super.motionX) * 0.10000000149011612D;
         super.motionY += (Math.signum(var3) * 0.699999988079071D - super.motionY) * 0.10000000149011612D;
         super.motionZ += (Math.signum(var5) * 0.5D - super.motionZ) * 0.10000000149011612D;
         float var7 = (float)(Math.atan2(super.motionZ, super.motionX) * 180.0D / 3.1415927410125732D) - 90.0F;
         float var8 = MathHelper.wrapAngleTo180_float(var7 - super.rotationYaw);
         super.moveForward = 0.5F;
         super.rotationYaw += var8;
         if(super.rand.nextInt(100) == 0 && super.worldObj.getBlock(MathHelper.floor_double(super.posX), (int)super.posY + 1, MathHelper.floor_double(super.posZ)).isNormalCube()) {
            this.setIsBatHanging(true);
         }
      }

   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void fall(float var1) {}

   protected void updateFallState(double var1, boolean var3) {}

   public boolean doesEntityNotTriggerPressurePlate() {
      return true;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         if(!super.worldObj.isRemote && this.getIsBatHanging()) {
            this.setIsBatHanging(false);
         }

         return super.attackEntityFrom(var1, var2);
      }
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      super.dataWatcher.updateObject(16, Byte.valueOf(var1.getByte("BatFlags")));
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setByte("BatFlags", super.dataWatcher.getWatchableObjectByte(16));
   }

   public boolean getCanSpawnHere() {
      int var1 = MathHelper.floor_double(super.boundingBox.minY);
      if(var1 >= 63) {
         return false;
      } else {
         int var2 = MathHelper.floor_double(super.posX);
         int var3 = MathHelper.floor_double(super.posZ);
         int var4 = super.worldObj.getBlockLightValue(var2, var1, var3);
         byte var5 = 4;
         Calendar var6 = super.worldObj.getCurrentDate();
         if((var6.get(2) + 1 != 10 || var6.get(5) < 20) && (var6.get(2) + 1 != 11 || var6.get(5) > 3)) {
            if(super.rand.nextBoolean()) {
               return false;
            }
         } else {
            var5 = 7;
         }

         return var4 > super.rand.nextInt(var5)?false:super.getCanSpawnHere();
      }
   }
}
