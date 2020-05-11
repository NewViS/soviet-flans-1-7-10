package net.minecraft.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityGhast extends EntityFlying implements IMob {

   public int courseChangeCooldown;
   public double waypointX;
   public double waypointY;
   public double waypointZ;
   private Entity targetedEntity;
   private int aggroCooldown;
   public int prevAttackCounter;
   public int attackCounter;
   private int explosionStrength = 1;


   public EntityGhast(World var1) {
      super(var1);
      this.setSize(4.0F, 4.0F);
      super.isImmuneToFire = true;
      super.experienceValue = 5;
   }

   public boolean func_110182_bF() {
      return super.dataWatcher.getWatchableObjectByte(16) != 0;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else if("fireball".equals(var1.getDamageType()) && var1.getEntity() instanceof EntityPlayer) {
         super.attackEntityFrom(var1, 1000.0F);
         ((EntityPlayer)var1.getEntity()).triggerAchievement(AchievementList.ghast);
         return true;
      } else {
         return super.attackEntityFrom(var1, var2);
      }
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, Byte.valueOf((byte)0));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
   }

   protected void updateEntityActionState() {
      if(!super.worldObj.isRemote && super.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
         this.setDead();
      }

      this.despawnEntity();
      this.prevAttackCounter = this.attackCounter;
      double var1 = this.waypointX - super.posX;
      double var3 = this.waypointY - super.posY;
      double var5 = this.waypointZ - super.posZ;
      double var7 = var1 * var1 + var3 * var3 + var5 * var5;
      if(var7 < 1.0D || var7 > 3600.0D) {
         this.waypointX = super.posX + (double)((super.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
         this.waypointY = super.posY + (double)((super.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
         this.waypointZ = super.posZ + (double)((super.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
      }

      if(this.courseChangeCooldown-- <= 0) {
         this.courseChangeCooldown += super.rand.nextInt(5) + 2;
         var7 = (double)MathHelper.sqrt_double(var7);
         if(this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var7)) {
            super.motionX += var1 / var7 * 0.1D;
            super.motionY += var3 / var7 * 0.1D;
            super.motionZ += var5 / var7 * 0.1D;
         } else {
            this.waypointX = super.posX;
            this.waypointY = super.posY;
            this.waypointZ = super.posZ;
         }
      }

      if(this.targetedEntity != null && this.targetedEntity.isDead) {
         this.targetedEntity = null;
      }

      if(this.targetedEntity == null || this.aggroCooldown-- <= 0) {
         this.targetedEntity = super.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);
         if(this.targetedEntity != null) {
            this.aggroCooldown = 20;
         }
      }

      double var9 = 64.0D;
      if(this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < var9 * var9) {
         double var11 = this.targetedEntity.posX - super.posX;
         double var13 = this.targetedEntity.boundingBox.minY + (double)(this.targetedEntity.height / 2.0F) - (super.posY + (double)(super.height / 2.0F));
         double var15 = this.targetedEntity.posZ - super.posZ;
         super.renderYawOffset = super.rotationYaw = -((float)Math.atan2(var11, var15)) * 180.0F / 3.1415927F;
         if(this.canEntityBeSeen(this.targetedEntity)) {
            if(this.attackCounter == 10) {
               super.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1007, (int)super.posX, (int)super.posY, (int)super.posZ, 0);
            }

            ++this.attackCounter;
            if(this.attackCounter == 20) {
               super.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)super.posX, (int)super.posY, (int)super.posZ, 0);
               EntityLargeFireball var17 = new EntityLargeFireball(super.worldObj, this, var11, var13, var15);
               var17.field_92057_e = this.explosionStrength;
               double var18 = 4.0D;
               Vec3 var20 = this.getLook(1.0F);
               var17.posX = super.posX + var20.xCoord * var18;
               var17.posY = super.posY + (double)(super.height / 2.0F) + 0.5D;
               var17.posZ = super.posZ + var20.zCoord * var18;
               super.worldObj.spawnEntityInWorld(var17);
               this.attackCounter = -40;
            }
         } else if(this.attackCounter > 0) {
            --this.attackCounter;
         }
      } else {
         super.renderYawOffset = super.rotationYaw = -((float)Math.atan2(super.motionX, super.motionZ)) * 180.0F / 3.1415927F;
         if(this.attackCounter > 0) {
            --this.attackCounter;
         }
      }

      if(!super.worldObj.isRemote) {
         byte var21 = super.dataWatcher.getWatchableObjectByte(16);
         byte var12 = (byte)(this.attackCounter > 10?1:0);
         if(var21 != var12) {
            super.dataWatcher.updateObject(16, Byte.valueOf(var12));
         }
      }

   }

   private boolean isCourseTraversable(double var1, double var3, double var5, double var7) {
      double var9 = (this.waypointX - super.posX) / var7;
      double var11 = (this.waypointY - super.posY) / var7;
      double var13 = (this.waypointZ - super.posZ) / var7;
      AxisAlignedBB var15 = super.boundingBox.copy();

      for(int var16 = 1; (double)var16 < var7; ++var16) {
         var15.offset(var9, var11, var13);
         if(!super.worldObj.getCollidingBoundingBoxes(this, var15).isEmpty()) {
            return false;
         }
      }

      return true;
   }

   protected String getLivingSound() {
      return "mob.ghast.moan";
   }

   protected String getHurtSound() {
      return "mob.ghast.scream";
   }

   protected String getDeathSound() {
      return "mob.ghast.death";
   }

   protected Item getDropItem() {
      return Items.gunpowder;
   }

   protected void dropFewItems(boolean var1, int var2) {
      int var3 = super.rand.nextInt(2) + super.rand.nextInt(1 + var2);

      int var4;
      for(var4 = 0; var4 < var3; ++var4) {
         this.dropItem(Items.ghast_tear, 1);
      }

      var3 = super.rand.nextInt(3) + super.rand.nextInt(1 + var2);

      for(var4 = 0; var4 < var3; ++var4) {
         this.dropItem(Items.gunpowder, 1);
      }

   }

   protected float getSoundVolume() {
      return 10.0F;
   }

   public boolean getCanSpawnHere() {
      return super.rand.nextInt(20) == 0 && super.getCanSpawnHere() && super.worldObj.difficultySetting != EnumDifficulty.PEACEFUL;
   }

   public int getMaxSpawnedInChunk() {
      return 1;
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("ExplosionPower", this.explosionStrength);
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if(var1.hasKey("ExplosionPower", 99)) {
         this.explosionStrength = var1.getInteger("ExplosionPower");
      }

   }
}
