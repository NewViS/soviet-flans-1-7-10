package net.minecraft.entity.monster;

import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

public class EntitySkeleton extends EntityMob implements IRangedAttackMob {

   private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
   private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);


   public EntitySkeleton(World var1) {
      super(var1);
      super.tasks.addTask(1, new EntityAISwimming(this));
      super.tasks.addTask(2, new EntityAIRestrictSun(this));
      super.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
      super.tasks.addTask(5, new EntityAIWander(this, 1.0D));
      super.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      super.tasks.addTask(6, new EntityAILookIdle(this));
      super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
      if(var1 != null && !var1.isRemote) {
         this.setCombatTask();
      }

   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(13, new Byte((byte)0));
   }

   public boolean isAIEnabled() {
      return true;
   }

   protected String getLivingSound() {
      return "mob.skeleton.say";
   }

   protected String getHurtSound() {
      return "mob.skeleton.hurt";
   }

   protected String getDeathSound() {
      return "mob.skeleton.death";
   }

   protected void func_145780_a(int var1, int var2, int var3, Block var4) {
      this.playSound("mob.skeleton.step", 0.15F, 1.0F);
   }

   public boolean attackEntityAsMob(Entity var1) {
      if(super.attackEntityAsMob(var1)) {
         if(this.getSkeletonType() == 1 && var1 instanceof EntityLivingBase) {
            ((EntityLivingBase)var1).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
         }

         return true;
      } else {
         return false;
      }
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   public void onLivingUpdate() {
      if(super.worldObj.isDaytime() && !super.worldObj.isRemote) {
         float var1 = this.getBrightness(1.0F);
         if(var1 > 0.5F && super.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && super.worldObj.canBlockSeeTheSky(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ))) {
            boolean var2 = true;
            ItemStack var3 = this.getEquipmentInSlot(4);
            if(var3 != null) {
               if(var3.isItemStackDamageable()) {
                  var3.setItemDamage(var3.getItemDamageForDisplay() + super.rand.nextInt(2));
                  if(var3.getItemDamageForDisplay() >= var3.getMaxDamage()) {
                     this.renderBrokenItemStack(var3);
                     this.setCurrentItemOrArmor(4, (ItemStack)null);
                  }
               }

               var2 = false;
            }

            if(var2) {
               this.setFire(8);
            }
         }
      }

      if(super.worldObj.isRemote && this.getSkeletonType() == 1) {
         this.setSize(0.72F, 2.34F);
      }

      super.onLivingUpdate();
   }

   public void updateRidden() {
      super.updateRidden();
      if(super.ridingEntity instanceof EntityCreature) {
         EntityCreature var1 = (EntityCreature)super.ridingEntity;
         super.renderYawOffset = var1.renderYawOffset;
      }

   }

   public void onDeath(DamageSource var1) {
      super.onDeath(var1);
      if(var1.getSourceOfDamage() instanceof EntityArrow && var1.getEntity() instanceof EntityPlayer) {
         EntityPlayer var2 = (EntityPlayer)var1.getEntity();
         double var3 = var2.posX - super.posX;
         double var5 = var2.posZ - super.posZ;
         if(var3 * var3 + var5 * var5 >= 2500.0D) {
            var2.triggerAchievement(AchievementList.snipeSkeleton);
         }
      }

   }

   protected Item getDropItem() {
      return Items.arrow;
   }

   protected void dropFewItems(boolean var1, int var2) {
      int var3;
      int var4;
      if(this.getSkeletonType() == 1) {
         var3 = super.rand.nextInt(3 + var2) - 1;

         for(var4 = 0; var4 < var3; ++var4) {
            this.dropItem(Items.coal, 1);
         }
      } else {
         var3 = super.rand.nextInt(3 + var2);

         for(var4 = 0; var4 < var3; ++var4) {
            this.dropItem(Items.arrow, 1);
         }
      }

      var3 = super.rand.nextInt(3 + var2);

      for(var4 = 0; var4 < var3; ++var4) {
         this.dropItem(Items.bone, 1);
      }

   }

   protected void dropRareDrop(int var1) {
      if(this.getSkeletonType() == 1) {
         this.entityDropItem(new ItemStack(Items.skull, 1, 1), 0.0F);
      }

   }

   protected void addRandomArmor() {
      super.addRandomArmor();
      this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData var1) {
      var1 = super.onSpawnWithEgg(var1);
      if(super.worldObj.provider instanceof WorldProviderHell && this.getRNG().nextInt(5) > 0) {
         super.tasks.addTask(4, this.aiAttackOnCollide);
         this.setSkeletonType(1);
         this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
         this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
      } else {
         super.tasks.addTask(4, this.aiArrowAttack);
         this.addRandomArmor();
         this.enchantEquipment();
      }

      this.setCanPickUpLoot(super.rand.nextFloat() < 0.55F * super.worldObj.func_147462_b(super.posX, super.posY, super.posZ));
      if(this.getEquipmentInSlot(4) == null) {
         Calendar var2 = super.worldObj.getCurrentDate();
         if(var2.get(2) + 1 == 10 && var2.get(5) == 31 && super.rand.nextFloat() < 0.25F) {
            this.setCurrentItemOrArmor(4, new ItemStack(super.rand.nextFloat() < 0.1F?Blocks.lit_pumpkin:Blocks.pumpkin));
            super.equipmentDropChances[4] = 0.0F;
         }
      }

      return var1;
   }

   public void setCombatTask() {
      super.tasks.removeTask(this.aiAttackOnCollide);
      super.tasks.removeTask(this.aiArrowAttack);
      ItemStack var1 = this.getHeldItem();
      if(var1 != null && var1.getItem() == Items.bow) {
         super.tasks.addTask(4, this.aiArrowAttack);
      } else {
         super.tasks.addTask(4, this.aiAttackOnCollide);
      }

   }

   public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
      EntityArrow var3 = new EntityArrow(super.worldObj, this, var1, 1.6F, (float)(14 - super.worldObj.difficultySetting.getDifficultyId() * 4));
      int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
      int var5 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
      var3.setDamage((double)(var2 * 2.0F) + super.rand.nextGaussian() * 0.25D + (double)((float)super.worldObj.difficultySetting.getDifficultyId() * 0.11F));
      if(var4 > 0) {
         var3.setDamage(var3.getDamage() + (double)var4 * 0.5D + 0.5D);
      }

      if(var5 > 0) {
         var3.setKnockbackStrength(var5);
      }

      if(EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0 || this.getSkeletonType() == 1) {
         var3.setFire(100);
      }

      this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
      super.worldObj.spawnEntityInWorld(var3);
   }

   public int getSkeletonType() {
      return super.dataWatcher.getWatchableObjectByte(13);
   }

   public void setSkeletonType(int var1) {
      super.dataWatcher.updateObject(13, Byte.valueOf((byte)var1));
      super.isImmuneToFire = var1 == 1;
      if(var1 == 1) {
         this.setSize(0.72F, 2.34F);
      } else {
         this.setSize(0.6F, 1.8F);
      }

   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if(var1.hasKey("SkeletonType", 99)) {
         byte var2 = var1.getByte("SkeletonType");
         this.setSkeletonType(var2);
      }

      this.setCombatTask();
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setByte("SkeletonType", (byte)this.getSkeletonType());
   }

   public void setCurrentItemOrArmor(int var1, ItemStack var2) {
      super.setCurrentItemOrArmor(var1, var2);
      if(!super.worldObj.isRemote && var1 == 0) {
         this.setCombatTask();
      }

   }

   public double getYOffset() {
      return super.getYOffset() - 0.5D;
   }
}
