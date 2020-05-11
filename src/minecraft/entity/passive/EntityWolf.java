package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWolf extends EntityTameable {

   private float field_70926_e;
   private float field_70924_f;
   private boolean isShaking;
   private boolean field_70928_h;
   private float timeWolfIsShaking;
   private float prevTimeWolfIsShaking;


   public EntityWolf(World var1) {
      super(var1);
      this.setSize(0.6F, 0.8F);
      this.getNavigator().setAvoidsWater(true);
      super.tasks.addTask(1, new EntityAISwimming(this));
      super.tasks.addTask(2, super.aiSit);
      super.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
      super.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
      super.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
      super.tasks.addTask(6, new EntityAIMate(this, 1.0D));
      super.tasks.addTask(7, new EntityAIWander(this, 1.0D));
      super.tasks.addTask(8, new EntityAIBeg(this, 8.0F));
      super.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      super.tasks.addTask(9, new EntityAILookIdle(this));
      super.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
      super.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
      super.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
      super.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntitySheep.class, 200, false));
      this.setTamed(false);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
      if(this.isTamed()) {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
      }

   }

   public boolean isAIEnabled() {
      return true;
   }

   public void setAttackTarget(EntityLivingBase var1) {
      super.setAttackTarget(var1);
      if(var1 == null) {
         this.setAngry(false);
      } else if(!this.isTamed()) {
         this.setAngry(true);
      }

   }

   protected void updateAITick() {
      super.dataWatcher.updateObject(18, Float.valueOf(this.getHealth()));
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(18, new Float(this.getHealth()));
      super.dataWatcher.addObject(19, new Byte((byte)0));
      super.dataWatcher.addObject(20, new Byte((byte)BlockColored.func_150032_b(1)));
   }

   protected void func_145780_a(int var1, int var2, int var3, Block var4) {
      this.playSound("mob.wolf.step", 0.15F, 1.0F);
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("Angry", this.isAngry());
      var1.setByte("CollarColor", (byte)this.getCollarColor());
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setAngry(var1.getBoolean("Angry"));
      if(var1.hasKey("CollarColor", 99)) {
         this.setCollarColor(var1.getByte("CollarColor"));
      }

   }

   protected String getLivingSound() {
      return this.isAngry()?"mob.wolf.growl":(super.rand.nextInt(3) == 0?(this.isTamed() && super.dataWatcher.getWatchableObjectFloat(18) < 10.0F?"mob.wolf.whine":"mob.wolf.panting"):"mob.wolf.bark");
   }

   protected String getHurtSound() {
      return "mob.wolf.hurt";
   }

   protected String getDeathSound() {
      return "mob.wolf.death";
   }

   protected float getSoundVolume() {
      return 0.4F;
   }

   protected Item getDropItem() {
      return Item.getItemById(-1);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if(!super.worldObj.isRemote && this.isShaking && !this.field_70928_h && !this.hasPath() && super.onGround) {
         this.field_70928_h = true;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
         super.worldObj.setEntityState(this, (byte)8);
      }

   }

   public void onUpdate() {
      super.onUpdate();
      this.field_70924_f = this.field_70926_e;
      if(this.func_70922_bv()) {
         this.field_70926_e += (1.0F - this.field_70926_e) * 0.4F;
      } else {
         this.field_70926_e += (0.0F - this.field_70926_e) * 0.4F;
      }

      if(this.func_70922_bv()) {
         super.numTicksToChaseTarget = 10;
      }

      if(this.isWet()) {
         this.isShaking = true;
         this.field_70928_h = false;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
      } else if((this.isShaking || this.field_70928_h) && this.field_70928_h) {
         if(this.timeWolfIsShaking == 0.0F) {
            this.playSound("mob.wolf.shake", this.getSoundVolume(), (super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F);
         }

         this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
         this.timeWolfIsShaking += 0.05F;
         if(this.prevTimeWolfIsShaking >= 2.0F) {
            this.isShaking = false;
            this.field_70928_h = false;
            this.prevTimeWolfIsShaking = 0.0F;
            this.timeWolfIsShaking = 0.0F;
         }

         if(this.timeWolfIsShaking > 0.4F) {
            float var1 = (float)super.boundingBox.minY;
            int var2 = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * 3.1415927F) * 7.0F);

            for(int var3 = 0; var3 < var2; ++var3) {
               float var4 = (super.rand.nextFloat() * 2.0F - 1.0F) * super.width * 0.5F;
               float var5 = (super.rand.nextFloat() * 2.0F - 1.0F) * super.width * 0.5F;
               super.worldObj.spawnParticle("splash", super.posX + (double)var4, (double)(var1 + 0.8F), super.posZ + (double)var5, super.motionX, super.motionY, super.motionZ);
            }
         }
      }

   }

   public boolean getWolfShaking() {
      return this.isShaking;
   }

   public float getShadingWhileShaking(float var1) {
      return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * var1) / 2.0F * 0.25F;
   }

   public float getShakeAngle(float var1, float var2) {
      float var3 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * var1 + var2) / 1.8F;
      if(var3 < 0.0F) {
         var3 = 0.0F;
      } else if(var3 > 1.0F) {
         var3 = 1.0F;
      }

      return MathHelper.sin(var3 * 3.1415927F) * MathHelper.sin(var3 * 3.1415927F * 11.0F) * 0.15F * 3.1415927F;
   }

   public float getInterestedAngle(float var1) {
      return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * var1) * 0.15F * 3.1415927F;
   }

   public float getEyeHeight() {
      return super.height * 0.8F;
   }

   public int getVerticalFaceSpeed() {
      return this.isSitting()?20:super.getVerticalFaceSpeed();
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         Entity var3 = var1.getEntity();
         super.aiSit.setSitting(false);
         if(var3 != null && !(var3 instanceof EntityPlayer) && !(var3 instanceof EntityArrow)) {
            var2 = (var2 + 1.0F) / 2.0F;
         }

         return super.attackEntityFrom(var1, var2);
      }
   }

   public boolean attackEntityAsMob(Entity var1) {
      int var2 = this.isTamed()?4:2;
      return var1.attackEntityFrom(DamageSource.causeMobDamage(this), (float)var2);
   }

   public void setTamed(boolean var1) {
      super.setTamed(var1);
      if(var1) {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
      }

   }

   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.inventory.getCurrentItem();
      if(this.isTamed()) {
         if(var2 != null) {
            if(var2.getItem() instanceof ItemFood) {
               ItemFood var3 = (ItemFood)var2.getItem();
               if(var3.isWolfsFavoriteMeat() && super.dataWatcher.getWatchableObjectFloat(18) < 20.0F) {
                  if(!var1.capabilities.isCreativeMode) {
                     --var2.stackSize;
                  }

                  this.heal((float)var3.func_150905_g(var2));
                  if(var2.stackSize <= 0) {
                     var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
                  }

                  return true;
               }
            } else if(var2.getItem() == Items.dye) {
               int var4 = BlockColored.func_150032_b(var2.getItemDamage());
               if(var4 != this.getCollarColor()) {
                  this.setCollarColor(var4);
                  if(!var1.capabilities.isCreativeMode && --var2.stackSize <= 0) {
                     var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
                  }

                  return true;
               }
            }
         }

         if(this.func_152114_e(var1) && !super.worldObj.isRemote && !this.isBreedingItem(var2)) {
            super.aiSit.setSitting(!this.isSitting());
            super.isJumping = false;
            this.setPathToEntity((PathEntity)null);
            this.setTarget((Entity)null);
            this.setAttackTarget((EntityLivingBase)null);
         }
      } else if(var2 != null && var2.getItem() == Items.bone && !this.isAngry()) {
         if(!var1.capabilities.isCreativeMode) {
            --var2.stackSize;
         }

         if(var2.stackSize <= 0) {
            var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
         }

         if(!super.worldObj.isRemote) {
            if(super.rand.nextInt(3) == 0) {
               this.setTamed(true);
               this.setPathToEntity((PathEntity)null);
               this.setAttackTarget((EntityLivingBase)null);
               super.aiSit.setSitting(true);
               this.setHealth(20.0F);
               this.func_152115_b(var1.getUniqueID().toString());
               this.playTameEffect(true);
               super.worldObj.setEntityState(this, (byte)7);
            } else {
               this.playTameEffect(false);
               super.worldObj.setEntityState(this, (byte)6);
            }
         }

         return true;
      }

      return super.interact(var1);
   }

   public void handleHealthUpdate(byte var1) {
      if(var1 == 8) {
         this.field_70928_h = true;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
      } else {
         super.handleHealthUpdate(var1);
      }

   }

   public float getTailRotation() {
      return this.isAngry()?1.5393804F:(this.isTamed()?(0.55F - (20.0F - super.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * 3.1415927F:0.62831855F);
   }

   public boolean isBreedingItem(ItemStack var1) {
      return var1 == null?false:(!(var1.getItem() instanceof ItemFood)?false:((ItemFood)var1.getItem()).isWolfsFavoriteMeat());
   }

   public int getMaxSpawnedInChunk() {
      return 8;
   }

   public boolean isAngry() {
      return (super.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
   }

   public void setAngry(boolean var1) {
      byte var2 = super.dataWatcher.getWatchableObjectByte(16);
      if(var1) {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 2)));
      } else {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -3)));
      }

   }

   public int getCollarColor() {
      return super.dataWatcher.getWatchableObjectByte(20) & 15;
   }

   public void setCollarColor(int var1) {
      super.dataWatcher.updateObject(20, Byte.valueOf((byte)(var1 & 15)));
   }

   public EntityWolf createChild(EntityAgeable var1) {
      EntityWolf var2 = new EntityWolf(super.worldObj);
      String var3 = this.func_152113_b();
      if(var3 != null && var3.trim().length() > 0) {
         var2.func_152115_b(var3);
         var2.setTamed(true);
      }

      return var2;
   }

   public void func_70918_i(boolean var1) {
      if(var1) {
         super.dataWatcher.updateObject(19, Byte.valueOf((byte)1));
      } else {
         super.dataWatcher.updateObject(19, Byte.valueOf((byte)0));
      }

   }

   public boolean canMateWith(EntityAnimal var1) {
      if(var1 == this) {
         return false;
      } else if(!this.isTamed()) {
         return false;
      } else if(!(var1 instanceof EntityWolf)) {
         return false;
      } else {
         EntityWolf var2 = (EntityWolf)var1;
         return !var2.isTamed()?false:(var2.isSitting()?false:this.isInLove() && var2.isInLove());
      }
   }

   public boolean func_70922_bv() {
      return super.dataWatcher.getWatchableObjectByte(19) == 1;
   }

   protected boolean canDespawn() {
      return !this.isTamed() && super.ticksExisted > 2400;
   }

   public boolean func_142018_a(EntityLivingBase var1, EntityLivingBase var2) {
      if(!(var1 instanceof EntityCreeper) && !(var1 instanceof EntityGhast)) {
         if(var1 instanceof EntityWolf) {
            EntityWolf var3 = (EntityWolf)var1;
            if(var3.isTamed() && var3.getOwner() == var2) {
               return false;
            }
         }

         return var1 instanceof EntityPlayer && var2 instanceof EntityPlayer && !((EntityPlayer)var2).canAttackPlayer((EntityPlayer)var1)?false:!(var1 instanceof EntityHorse) || !((EntityHorse)var1).isTame();
      } else {
         return false;
      }
   }

   // $FF: synthetic method
   public EntityAgeable createChild(EntityAgeable var1) {
      return this.createChild(var1);
   }
}
