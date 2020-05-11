package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;

public class EntityPig extends EntityAnimal {

   private final EntityAIControlledByPlayer aiControlledByPlayer;


   public EntityPig(World var1) {
      super(var1);
      this.setSize(0.9F, 0.9F);
      this.getNavigator().setAvoidsWater(true);
      super.tasks.addTask(0, new EntityAISwimming(this));
      super.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
      super.tasks.addTask(2, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.3F));
      super.tasks.addTask(3, new EntityAIMate(this, 1.0D));
      super.tasks.addTask(4, new EntityAITempt(this, 1.2D, Items.carrot_on_a_stick, false));
      super.tasks.addTask(4, new EntityAITempt(this, 1.2D, Items.carrot, false));
      super.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
      super.tasks.addTask(6, new EntityAIWander(this, 1.0D));
      super.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      super.tasks.addTask(8, new EntityAILookIdle(this));
   }

   public boolean isAIEnabled() {
      return true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
   }

   protected void updateAITasks() {
      super.updateAITasks();
   }

   public boolean canBeSteered() {
      ItemStack var1 = ((EntityPlayer)super.riddenByEntity).getHeldItem();
      return var1 != null && var1.getItem() == Items.carrot_on_a_stick;
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, Byte.valueOf((byte)0));
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("Saddle", this.getSaddled());
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setSaddled(var1.getBoolean("Saddle"));
   }

   protected String getLivingSound() {
      return "mob.pig.say";
   }

   protected String getHurtSound() {
      return "mob.pig.say";
   }

   protected String getDeathSound() {
      return "mob.pig.death";
   }

   protected void func_145780_a(int var1, int var2, int var3, Block var4) {
      this.playSound("mob.pig.step", 0.15F, 1.0F);
   }

   public boolean interact(EntityPlayer var1) {
      if(super.interact(var1)) {
         return true;
      } else if(this.getSaddled() && !super.worldObj.isRemote && (super.riddenByEntity == null || super.riddenByEntity == var1)) {
         var1.mountEntity(this);
         return true;
      } else {
         return false;
      }
   }

   protected Item getDropItem() {
      return this.isBurning()?Items.cooked_porkchop:Items.porkchop;
   }

   protected void dropFewItems(boolean var1, int var2) {
      int var3 = super.rand.nextInt(3) + 1 + super.rand.nextInt(1 + var2);

      for(int var4 = 0; var4 < var3; ++var4) {
         if(this.isBurning()) {
            this.dropItem(Items.cooked_porkchop, 1);
         } else {
            this.dropItem(Items.porkchop, 1);
         }
      }

      if(this.getSaddled()) {
         this.dropItem(Items.saddle, 1);
      }

   }

   public boolean getSaddled() {
      return (super.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   public void setSaddled(boolean var1) {
      if(var1) {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
      } else {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
      }

   }

   public void onStruckByLightning(EntityLightningBolt var1) {
      if(!super.worldObj.isRemote) {
         EntityPigZombie var2 = new EntityPigZombie(super.worldObj);
         var2.setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
         var2.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
         super.worldObj.spawnEntityInWorld(var2);
         this.setDead();
      }
   }

   protected void fall(float var1) {
      super.fall(var1);
      if(var1 > 5.0F && super.riddenByEntity instanceof EntityPlayer) {
         ((EntityPlayer)super.riddenByEntity).triggerAchievement(AchievementList.flyPig);
      }

   }

   public EntityPig createChild(EntityAgeable var1) {
      return new EntityPig(super.worldObj);
   }

   public boolean isBreedingItem(ItemStack var1) {
      return var1 != null && var1.getItem() == Items.carrot;
   }

   public EntityAIControlledByPlayer getAIControlledByPlayer() {
      return this.aiControlledByPlayer;
   }

   // $FF: synthetic method
   public EntityAgeable createChild(EntityAgeable var1) {
      return this.createChild(var1);
   }
}
