package net.minecraft.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIDefendVillage;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookAtVillager;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityIronGolem extends EntityGolem {

   private int homeCheckTimer;
   Village villageObj;
   private int attackTimer;
   private int holdRoseTick;


   public EntityIronGolem(World var1) {
      super(var1);
      this.setSize(1.4F, 2.9F);
      this.getNavigator().setAvoidsWater(true);
      super.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
      super.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
      super.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
      super.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
      super.tasks.addTask(5, new EntityAILookAtVillager(this));
      super.tasks.addTask(6, new EntityAIWander(this, 0.6D));
      super.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      super.tasks.addTask(8, new EntityAILookIdle(this));
      super.targetTasks.addTask(1, new EntityAIDefendVillage(this));
      super.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
      super.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.mobSelector));
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, Byte.valueOf((byte)0));
   }

   public boolean isAIEnabled() {
      return true;
   }

   protected void updateAITick() {
      if(--this.homeCheckTimer <= 0) {
         this.homeCheckTimer = 70 + super.rand.nextInt(50);
         this.villageObj = super.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ), 32);
         if(this.villageObj == null) {
            this.detachHome();
         } else {
            ChunkCoordinates var1 = this.villageObj.getCenter();
            this.setHomeArea(var1.posX, var1.posY, var1.posZ, (int)((float)this.villageObj.getVillageRadius() * 0.6F));
         }
      }

      super.updateAITick();
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
   }

   protected int decreaseAirSupply(int var1) {
      return var1;
   }

   protected void collideWithEntity(Entity var1) {
      if(var1 instanceof IMob && this.getRNG().nextInt(20) == 0) {
         this.setAttackTarget((EntityLivingBase)var1);
      }

      super.collideWithEntity(var1);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if(this.attackTimer > 0) {
         --this.attackTimer;
      }

      if(this.holdRoseTick > 0) {
         --this.holdRoseTick;
      }

      if(super.motionX * super.motionX + super.motionZ * super.motionZ > 2.500000277905201E-7D && super.rand.nextInt(5) == 0) {
         int var1 = MathHelper.floor_double(super.posX);
         int var2 = MathHelper.floor_double(super.posY - 0.20000000298023224D - (double)super.yOffset);
         int var3 = MathHelper.floor_double(super.posZ);
         Block var4 = super.worldObj.getBlock(var1, var2, var3);
         if(var4.getMaterial() != Material.air) {
            super.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(var4) + "_" + super.worldObj.getBlockMetadata(var1, var2, var3), super.posX + ((double)super.rand.nextFloat() - 0.5D) * (double)super.width, super.boundingBox.minY + 0.1D, super.posZ + ((double)super.rand.nextFloat() - 0.5D) * (double)super.width, 4.0D * ((double)super.rand.nextFloat() - 0.5D), 0.5D, ((double)super.rand.nextFloat() - 0.5D) * 4.0D);
         }
      }

   }

   public boolean canAttackClass(Class var1) {
      return this.isPlayerCreated() && EntityPlayer.class.isAssignableFrom(var1)?false:super.canAttackClass(var1);
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("PlayerCreated", this.isPlayerCreated());
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setPlayerCreated(var1.getBoolean("PlayerCreated"));
   }

   public boolean attackEntityAsMob(Entity var1) {
      this.attackTimer = 10;
      super.worldObj.setEntityState(this, (byte)4);
      boolean var2 = var1.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(7 + super.rand.nextInt(15)));
      if(var2) {
         var1.motionY += 0.4000000059604645D;
      }

      this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
      return var2;
   }

   public void handleHealthUpdate(byte var1) {
      if(var1 == 4) {
         this.attackTimer = 10;
         this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
      } else if(var1 == 11) {
         this.holdRoseTick = 400;
      } else {
         super.handleHealthUpdate(var1);
      }

   }

   public Village getVillage() {
      return this.villageObj;
   }

   public int getAttackTimer() {
      return this.attackTimer;
   }

   public void setHoldingRose(boolean var1) {
      this.holdRoseTick = var1?400:0;
      super.worldObj.setEntityState(this, (byte)11);
   }

   protected String getHurtSound() {
      return "mob.irongolem.hit";
   }

   protected String getDeathSound() {
      return "mob.irongolem.death";
   }

   protected void func_145780_a(int var1, int var2, int var3, Block var4) {
      this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
   }

   protected void dropFewItems(boolean var1, int var2) {
      int var3 = super.rand.nextInt(3);

      int var4;
      for(var4 = 0; var4 < var3; ++var4) {
         this.func_145778_a(Item.getItemFromBlock(Blocks.red_flower), 1, 0.0F);
      }

      var4 = 3 + super.rand.nextInt(3);

      for(int var5 = 0; var5 < var4; ++var5) {
         this.dropItem(Items.iron_ingot, 1);
      }

   }

   public int getHoldRoseTick() {
      return this.holdRoseTick;
   }

   public boolean isPlayerCreated() {
      return (super.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   public void setPlayerCreated(boolean var1) {
      byte var2 = super.dataWatcher.getWatchableObjectByte(16);
      if(var1) {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 1)));
      } else {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -2)));
      }

   }

   public void onDeath(DamageSource var1) {
      if(!this.isPlayerCreated() && super.attackingPlayer != null && this.villageObj != null) {
         this.villageObj.setReputationForPlayer(super.attackingPlayer.getCommandSenderName(), -5);
      }

      super.onDeath(var1);
   }
}
