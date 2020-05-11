package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.entity.ai.EntityAIOcelotSit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityOcelot extends EntityTameable {

   private EntityAITempt aiTempt;


   public EntityOcelot(World var1) {
      super(var1);
      this.setSize(0.6F, 0.8F);
      this.getNavigator().setAvoidsWater(true);
      super.tasks.addTask(1, new EntityAISwimming(this));
      super.tasks.addTask(2, super.aiSit);
      super.tasks.addTask(3, this.aiTempt = new EntityAITempt(this, 0.6D, Items.fish, true));
      super.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D));
      super.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
      super.tasks.addTask(6, new EntityAIOcelotSit(this, 1.33D));
      super.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
      super.tasks.addTask(8, new EntityAIOcelotAttack(this));
      super.tasks.addTask(9, new EntityAIMate(this, 0.8D));
      super.tasks.addTask(10, new EntityAIWander(this, 0.8D));
      super.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
      super.targetTasks.addTask(1, new EntityAITargetNonTamed(this, EntityChicken.class, 750, false));
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(18, Byte.valueOf((byte)0));
   }

   public void updateAITick() {
      if(this.getMoveHelper().isUpdating()) {
         double var1 = this.getMoveHelper().getSpeed();
         if(var1 == 0.6D) {
            this.setSneaking(true);
            this.setSprinting(false);
         } else if(var1 == 1.33D) {
            this.setSneaking(false);
            this.setSprinting(true);
         } else {
            this.setSneaking(false);
            this.setSprinting(false);
         }
      } else {
         this.setSneaking(false);
         this.setSprinting(false);
      }

   }

   protected boolean canDespawn() {
      return !this.isTamed() && super.ticksExisted > 2400;
   }

   public boolean isAIEnabled() {
      return true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
   }

   protected void fall(float var1) {}

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("CatType", this.getTameSkin());
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setTameSkin(var1.getInteger("CatType"));
   }

   protected String getLivingSound() {
      return this.isTamed()?(this.isInLove()?"mob.cat.purr":(super.rand.nextInt(4) == 0?"mob.cat.purreow":"mob.cat.meow")):"";
   }

   protected String getHurtSound() {
      return "mob.cat.hitt";
   }

   protected String getDeathSound() {
      return "mob.cat.hitt";
   }

   protected float getSoundVolume() {
      return 0.4F;
   }

   protected Item getDropItem() {
      return Items.leather;
   }

   public boolean attackEntityAsMob(Entity var1) {
      return var1.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         super.aiSit.setSitting(false);
         return super.attackEntityFrom(var1, var2);
      }
   }

   protected void dropFewItems(boolean var1, int var2) {}

   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.inventory.getCurrentItem();
      if(this.isTamed()) {
         if(this.func_152114_e(var1) && !super.worldObj.isRemote && !this.isBreedingItem(var2)) {
            super.aiSit.setSitting(!this.isSitting());
         }
      } else if(this.aiTempt.isRunning() && var2 != null && var2.getItem() == Items.fish && var1.getDistanceSqToEntity(this) < 9.0D) {
         if(!var1.capabilities.isCreativeMode) {
            --var2.stackSize;
         }

         if(var2.stackSize <= 0) {
            var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
         }

         if(!super.worldObj.isRemote) {
            if(super.rand.nextInt(3) == 0) {
               this.setTamed(true);
               this.setTameSkin(1 + super.worldObj.rand.nextInt(3));
               this.func_152115_b(var1.getUniqueID().toString());
               this.playTameEffect(true);
               super.aiSit.setSitting(true);
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

   public EntityOcelot createChild(EntityAgeable var1) {
      EntityOcelot var2 = new EntityOcelot(super.worldObj);
      if(this.isTamed()) {
         var2.func_152115_b(this.func_152113_b());
         var2.setTamed(true);
         var2.setTameSkin(this.getTameSkin());
      }

      return var2;
   }

   public boolean isBreedingItem(ItemStack var1) {
      return var1 != null && var1.getItem() == Items.fish;
   }

   public boolean canMateWith(EntityAnimal var1) {
      if(var1 == this) {
         return false;
      } else if(!this.isTamed()) {
         return false;
      } else if(!(var1 instanceof EntityOcelot)) {
         return false;
      } else {
         EntityOcelot var2 = (EntityOcelot)var1;
         return !var2.isTamed()?false:this.isInLove() && var2.isInLove();
      }
   }

   public int getTameSkin() {
      return super.dataWatcher.getWatchableObjectByte(18);
   }

   public void setTameSkin(int var1) {
      super.dataWatcher.updateObject(18, Byte.valueOf((byte)var1));
   }

   public boolean getCanSpawnHere() {
      if(super.worldObj.rand.nextInt(3) == 0) {
         return false;
      } else {
         if(super.worldObj.checkNoEntityCollision(super.boundingBox) && super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox)) {
            int var1 = MathHelper.floor_double(super.posX);
            int var2 = MathHelper.floor_double(super.boundingBox.minY);
            int var3 = MathHelper.floor_double(super.posZ);
            if(var2 < 63) {
               return false;
            }

            Block var4 = super.worldObj.getBlock(var1, var2 - 1, var3);
            if(var4 == Blocks.grass || var4.getMaterial() == Material.leaves) {
               return true;
            }
         }

         return false;
      }
   }

   public String getCommandSenderName() {
      return this.hasCustomNameTag()?this.getCustomNameTag():(this.isTamed()?StatCollector.translateToLocal("entity.Cat.name"):super.getCommandSenderName());
   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData var1) {
      var1 = super.onSpawnWithEgg(var1);
      if(super.worldObj.rand.nextInt(7) == 0) {
         for(int var2 = 0; var2 < 2; ++var2) {
            EntityOcelot var3 = new EntityOcelot(super.worldObj);
            var3.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, 0.0F);
            var3.setGrowingAge(-24000);
            super.worldObj.spawnEntityInWorld(var3);
         }
      }

      return var1;
   }

   // $FF: synthetic method
   public EntityAgeable createChild(EntityAgeable var1) {
      return this.createChild(var1);
   }
}
