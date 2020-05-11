package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityChicken extends EntityAnimal {

   public float field_70886_e;
   public float destPos;
   public float field_70884_g;
   public float field_70888_h;
   public float field_70889_i = 1.0F;
   public int timeUntilNextEgg;
   public boolean field_152118_bv;


   public EntityChicken(World var1) {
      super(var1);
      this.setSize(0.3F, 0.7F);
      this.timeUntilNextEgg = super.rand.nextInt(6000) + 6000;
      super.tasks.addTask(0, new EntityAISwimming(this));
      super.tasks.addTask(1, new EntityAIPanic(this, 1.4D));
      super.tasks.addTask(2, new EntityAIMate(this, 1.0D));
      super.tasks.addTask(3, new EntityAITempt(this, 1.0D, Items.wheat_seeds, false));
      super.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
      super.tasks.addTask(5, new EntityAIWander(this, 1.0D));
      super.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      super.tasks.addTask(7, new EntityAILookIdle(this));
   }

   public boolean isAIEnabled() {
      return true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.field_70888_h = this.field_70886_e;
      this.field_70884_g = this.destPos;
      this.destPos = (float)((double)this.destPos + (double)(super.onGround?-1:4) * 0.3D);
      if(this.destPos < 0.0F) {
         this.destPos = 0.0F;
      }

      if(this.destPos > 1.0F) {
         this.destPos = 1.0F;
      }

      if(!super.onGround && this.field_70889_i < 1.0F) {
         this.field_70889_i = 1.0F;
      }

      this.field_70889_i = (float)((double)this.field_70889_i * 0.9D);
      if(!super.onGround && super.motionY < 0.0D) {
         super.motionY *= 0.6D;
      }

      this.field_70886_e += this.field_70889_i * 2.0F;
      if(!super.worldObj.isRemote && !this.isChild() && !this.func_152116_bZ() && --this.timeUntilNextEgg <= 0) {
         this.playSound("mob.chicken.plop", 1.0F, (super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F);
         this.dropItem(Items.egg, 1);
         this.timeUntilNextEgg = super.rand.nextInt(6000) + 6000;
      }

   }

   protected void fall(float var1) {}

   protected String getLivingSound() {
      return "mob.chicken.say";
   }

   protected String getHurtSound() {
      return "mob.chicken.hurt";
   }

   protected String getDeathSound() {
      return "mob.chicken.hurt";
   }

   protected void func_145780_a(int var1, int var2, int var3, Block var4) {
      this.playSound("mob.chicken.step", 0.15F, 1.0F);
   }

   protected Item getDropItem() {
      return Items.feather;
   }

   protected void dropFewItems(boolean var1, int var2) {
      int var3 = super.rand.nextInt(3) + super.rand.nextInt(1 + var2);

      for(int var4 = 0; var4 < var3; ++var4) {
         this.dropItem(Items.feather, 1);
      }

      if(this.isBurning()) {
         this.dropItem(Items.cooked_chicken, 1);
      } else {
         this.dropItem(Items.chicken, 1);
      }

   }

   public EntityChicken createChild(EntityAgeable var1) {
      return new EntityChicken(super.worldObj);
   }

   public boolean isBreedingItem(ItemStack var1) {
      return var1 != null && var1.getItem() instanceof ItemSeeds;
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.field_152118_bv = var1.getBoolean("IsChickenJockey");
   }

   protected int getExperiencePoints(EntityPlayer var1) {
      return this.func_152116_bZ()?10:super.getExperiencePoints(var1);
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("IsChickenJockey", this.field_152118_bv);
   }

   protected boolean canDespawn() {
      return this.func_152116_bZ() && super.riddenByEntity == null;
   }

   public void updateRiderPosition() {
      super.updateRiderPosition();
      float var1 = MathHelper.sin(super.renderYawOffset * 3.1415927F / 180.0F);
      float var2 = MathHelper.cos(super.renderYawOffset * 3.1415927F / 180.0F);
      float var3 = 0.1F;
      float var4 = 0.0F;
      super.riddenByEntity.setPosition(super.posX + (double)(var3 * var1), super.posY + (double)(super.height * 0.5F) + super.riddenByEntity.getYOffset() + (double)var4, super.posZ - (double)(var3 * var2));
      if(super.riddenByEntity instanceof EntityLivingBase) {
         ((EntityLivingBase)super.riddenByEntity).renderYawOffset = super.renderYawOffset;
      }

   }

   public boolean func_152116_bZ() {
      return this.field_152118_bv;
   }

   public void func_152117_i(boolean var1) {
      this.field_152118_bv = var1;
   }

   // $FF: synthetic method
   public EntityAgeable createChild(EntityAgeable var1) {
      return this.createChild(var1);
   }
}
