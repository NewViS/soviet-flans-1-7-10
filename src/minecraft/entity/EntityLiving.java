package net.minecraft.entity;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityBodyHelper;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityLiving extends EntityLivingBase {

   public int livingSoundTime;
   protected int experienceValue;
   private EntityLookHelper lookHelper;
   private EntityMoveHelper moveHelper;
   private EntityJumpHelper jumpHelper;
   private EntityBodyHelper bodyHelper;
   private PathNavigate navigator;
   protected final EntityAITasks tasks;
   protected final EntityAITasks targetTasks;
   private EntityLivingBase attackTarget;
   private EntitySenses senses;
   private ItemStack[] equipment = new ItemStack[5];
   protected float[] equipmentDropChances = new float[5];
   private boolean canPickUpLoot;
   private boolean persistenceRequired;
   protected float defaultPitch;
   private Entity currentTarget;
   protected int numTicksToChaseTarget;
   private boolean isLeashed;
   private Entity leashedToEntity;
   private NBTTagCompound field_110170_bx;


   public EntityLiving(World var1) {
      super(var1);
      this.tasks = new EntityAITasks(var1 != null && var1.theProfiler != null?var1.theProfiler:null);
      this.targetTasks = new EntityAITasks(var1 != null && var1.theProfiler != null?var1.theProfiler:null);
      this.lookHelper = new EntityLookHelper(this);
      this.moveHelper = new EntityMoveHelper(this);
      this.jumpHelper = new EntityJumpHelper(this);
      this.bodyHelper = new EntityBodyHelper(this);
      this.navigator = new PathNavigate(this, var1);
      this.senses = new EntitySenses(this);

      for(int var2 = 0; var2 < this.equipmentDropChances.length; ++var2) {
         this.equipmentDropChances[var2] = 0.085F;
      }

   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
   }

   public EntityLookHelper getLookHelper() {
      return this.lookHelper;
   }

   public EntityMoveHelper getMoveHelper() {
      return this.moveHelper;
   }

   public EntityJumpHelper getJumpHelper() {
      return this.jumpHelper;
   }

   public PathNavigate getNavigator() {
      return this.navigator;
   }

   public EntitySenses getEntitySenses() {
      return this.senses;
   }

   public EntityLivingBase getAttackTarget() {
      return this.attackTarget;
   }

   public void setAttackTarget(EntityLivingBase var1) {
      this.attackTarget = var1;
   }

   public boolean canAttackClass(Class var1) {
      return EntityCreeper.class != var1 && EntityGhast.class != var1;
   }

   public void eatGrassBonus() {}

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(11, Byte.valueOf((byte)0));
      super.dataWatcher.addObject(10, "");
   }

   public int getTalkInterval() {
      return 80;
   }

   public void playLivingSound() {
      String var1 = this.getLivingSound();
      if(var1 != null) {
         this.playSound(var1, this.getSoundVolume(), this.getSoundPitch());
      }

   }

   public void onEntityUpdate() {
      super.onEntityUpdate();
      super.worldObj.theProfiler.startSection("mobBaseTick");
      if(this.isEntityAlive() && super.rand.nextInt(1000) < this.livingSoundTime++) {
         this.livingSoundTime = -this.getTalkInterval();
         this.playLivingSound();
      }

      super.worldObj.theProfiler.endSection();
   }

   protected int getExperiencePoints(EntityPlayer var1) {
      if(this.experienceValue > 0) {
         int var2 = this.experienceValue;
         ItemStack[] var3 = this.getLastActiveItems();

         for(int var4 = 0; var4 < var3.length; ++var4) {
            if(var3[var4] != null && this.equipmentDropChances[var4] <= 1.0F) {
               var2 += 1 + super.rand.nextInt(3);
            }
         }

         return var2;
      } else {
         return this.experienceValue;
      }
   }

   public void spawnExplosionParticle() {
      for(int var1 = 0; var1 < 20; ++var1) {
         double var2 = super.rand.nextGaussian() * 0.02D;
         double var4 = super.rand.nextGaussian() * 0.02D;
         double var6 = super.rand.nextGaussian() * 0.02D;
         double var8 = 10.0D;
         super.worldObj.spawnParticle("explode", super.posX + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width - var2 * var8, super.posY + (double)(super.rand.nextFloat() * super.height) - var4 * var8, super.posZ + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width - var6 * var8, var2, var4, var6);
      }

   }

   public void onUpdate() {
      super.onUpdate();
      if(!super.worldObj.isRemote) {
         this.updateLeashedState();
      }

   }

   protected float func_110146_f(float var1, float var2) {
      if(this.isAIEnabled()) {
         this.bodyHelper.func_75664_a();
         return var2;
      } else {
         return super.func_110146_f(var1, var2);
      }
   }

   protected String getLivingSound() {
      return null;
   }

   protected Item getDropItem() {
      return Item.getItemById(0);
   }

   protected void dropFewItems(boolean var1, int var2) {
      Item var3 = this.getDropItem();
      if(var3 != null) {
         int var4 = super.rand.nextInt(3);
         if(var2 > 0) {
            var4 += super.rand.nextInt(var2 + 1);
         }

         for(int var5 = 0; var5 < var4; ++var5) {
            this.dropItem(var3, 1);
         }
      }

   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("CanPickUpLoot", this.canPickUpLoot());
      var1.setBoolean("PersistenceRequired", this.persistenceRequired);
      NBTTagList var2 = new NBTTagList();

      NBTTagCompound var4;
      for(int var3 = 0; var3 < this.equipment.length; ++var3) {
         var4 = new NBTTagCompound();
         if(this.equipment[var3] != null) {
            this.equipment[var3].writeToNBT(var4);
         }

         var2.appendTag(var4);
      }

      var1.setTag("Equipment", var2);
      NBTTagList var6 = new NBTTagList();

      for(int var7 = 0; var7 < this.equipmentDropChances.length; ++var7) {
         var6.appendTag(new NBTTagFloat(this.equipmentDropChances[var7]));
      }

      var1.setTag("DropChances", var6);
      var1.setString("CustomName", this.getCustomNameTag());
      var1.setBoolean("CustomNameVisible", this.getAlwaysRenderNameTag());
      var1.setBoolean("Leashed", this.isLeashed);
      if(this.leashedToEntity != null) {
         var4 = new NBTTagCompound();
         if(this.leashedToEntity instanceof EntityLivingBase) {
            var4.setLong("UUIDMost", this.leashedToEntity.getUniqueID().getMostSignificantBits());
            var4.setLong("UUIDLeast", this.leashedToEntity.getUniqueID().getLeastSignificantBits());
         } else if(this.leashedToEntity instanceof EntityHanging) {
            EntityHanging var5 = (EntityHanging)this.leashedToEntity;
            var4.setInteger("X", var5.field_146063_b);
            var4.setInteger("Y", var5.field_146064_c);
            var4.setInteger("Z", var5.field_146062_d);
         }

         var1.setTag("Leash", var4);
      }

   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setCanPickUpLoot(var1.getBoolean("CanPickUpLoot"));
      this.persistenceRequired = var1.getBoolean("PersistenceRequired");
      if(var1.hasKey("CustomName", 8) && var1.getString("CustomName").length() > 0) {
         this.setCustomNameTag(var1.getString("CustomName"));
      }

      this.setAlwaysRenderNameTag(var1.getBoolean("CustomNameVisible"));
      NBTTagList var2;
      int var3;
      if(var1.hasKey("Equipment", 9)) {
         var2 = var1.getTagList("Equipment", 10);

         for(var3 = 0; var3 < this.equipment.length; ++var3) {
            this.equipment[var3] = ItemStack.loadItemStackFromNBT(var2.getCompoundTagAt(var3));
         }
      }

      if(var1.hasKey("DropChances", 9)) {
         var2 = var1.getTagList("DropChances", 5);

         for(var3 = 0; var3 < var2.tagCount(); ++var3) {
            this.equipmentDropChances[var3] = var2.func_150308_e(var3);
         }
      }

      this.isLeashed = var1.getBoolean("Leashed");
      if(this.isLeashed && var1.hasKey("Leash", 10)) {
         this.field_110170_bx = var1.getCompoundTag("Leash");
      }

   }

   public void setMoveForward(float var1) {
      super.moveForward = var1;
   }

   public void setAIMoveSpeed(float var1) {
      super.setAIMoveSpeed(var1);
      this.setMoveForward(var1);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      super.worldObj.theProfiler.startSection("looting");
      if(!super.worldObj.isRemote && this.canPickUpLoot() && !super.dead && super.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
         List var1 = super.worldObj.getEntitiesWithinAABB(EntityItem.class, super.boundingBox.expand(1.0D, 0.0D, 1.0D));
         Iterator var2 = var1.iterator();

         while(var2.hasNext()) {
            EntityItem var3 = (EntityItem)var2.next();
            if(!var3.isDead && var3.getEntityItem() != null) {
               ItemStack var4 = var3.getEntityItem();
               int var5 = getArmorPosition(var4);
               if(var5 > -1) {
                  boolean var6 = true;
                  ItemStack var7 = this.getEquipmentInSlot(var5);
                  if(var7 != null) {
                     if(var5 == 0) {
                        if(var4.getItem() instanceof ItemSword && !(var7.getItem() instanceof ItemSword)) {
                           var6 = true;
                        } else if(var4.getItem() instanceof ItemSword && var7.getItem() instanceof ItemSword) {
                           ItemSword var8 = (ItemSword)var4.getItem();
                           ItemSword var9 = (ItemSword)var7.getItem();
                           if(var8.func_150931_i() == var9.func_150931_i()) {
                              var6 = var4.getItemDamage() > var7.getItemDamage() || var4.hasTagCompound() && !var7.hasTagCompound();
                           } else {
                              var6 = var8.func_150931_i() > var9.func_150931_i();
                           }
                        } else {
                           var6 = false;
                        }
                     } else if(var4.getItem() instanceof ItemArmor && !(var7.getItem() instanceof ItemArmor)) {
                        var6 = true;
                     } else if(var4.getItem() instanceof ItemArmor && var7.getItem() instanceof ItemArmor) {
                        ItemArmor var10 = (ItemArmor)var4.getItem();
                        ItemArmor var12 = (ItemArmor)var7.getItem();
                        if(var10.damageReduceAmount == var12.damageReduceAmount) {
                           var6 = var4.getItemDamage() > var7.getItemDamage() || var4.hasTagCompound() && !var7.hasTagCompound();
                        } else {
                           var6 = var10.damageReduceAmount > var12.damageReduceAmount;
                        }
                     } else {
                        var6 = false;
                     }
                  }

                  if(var6) {
                     if(var7 != null && super.rand.nextFloat() - 0.1F < this.equipmentDropChances[var5]) {
                        this.entityDropItem(var7, 0.0F);
                     }

                     if(var4.getItem() == Items.diamond && var3.func_145800_j() != null) {
                        EntityPlayer var11 = super.worldObj.getPlayerEntityByName(var3.func_145800_j());
                        if(var11 != null) {
                           var11.triggerAchievement(AchievementList.field_150966_x);
                        }
                     }

                     this.setCurrentItemOrArmor(var5, var4);
                     this.equipmentDropChances[var5] = 2.0F;
                     this.persistenceRequired = true;
                     this.onItemPickup(var3, 1);
                     var3.setDead();
                  }
               }
            }
         }
      }

      super.worldObj.theProfiler.endSection();
   }

   protected boolean isAIEnabled() {
      return false;
   }

   protected boolean canDespawn() {
      return true;
   }

   protected void despawnEntity() {
      if(this.persistenceRequired) {
         super.entityAge = 0;
      } else {
         EntityPlayer var1 = super.worldObj.getClosestPlayerToEntity(this, -1.0D);
         if(var1 != null) {
            double var2 = var1.posX - super.posX;
            double var4 = var1.posY - super.posY;
            double var6 = var1.posZ - super.posZ;
            double var8 = var2 * var2 + var4 * var4 + var6 * var6;
            if(this.canDespawn() && var8 > 16384.0D) {
               this.setDead();
            }

            if(super.entityAge > 600 && super.rand.nextInt(800) == 0 && var8 > 1024.0D && this.canDespawn()) {
               this.setDead();
            } else if(var8 < 1024.0D) {
               super.entityAge = 0;
            }
         }

      }
   }

   protected void updateAITasks() {
      ++super.entityAge;
      super.worldObj.theProfiler.startSection("checkDespawn");
      this.despawnEntity();
      super.worldObj.theProfiler.endSection();
      super.worldObj.theProfiler.startSection("sensing");
      this.senses.clearSensingCache();
      super.worldObj.theProfiler.endSection();
      super.worldObj.theProfiler.startSection("targetSelector");
      this.targetTasks.onUpdateTasks();
      super.worldObj.theProfiler.endSection();
      super.worldObj.theProfiler.startSection("goalSelector");
      this.tasks.onUpdateTasks();
      super.worldObj.theProfiler.endSection();
      super.worldObj.theProfiler.startSection("navigation");
      this.navigator.onUpdateNavigation();
      super.worldObj.theProfiler.endSection();
      super.worldObj.theProfiler.startSection("mob tick");
      this.updateAITick();
      super.worldObj.theProfiler.endSection();
      super.worldObj.theProfiler.startSection("controls");
      super.worldObj.theProfiler.startSection("move");
      this.moveHelper.onUpdateMoveHelper();
      super.worldObj.theProfiler.endStartSection("look");
      this.lookHelper.onUpdateLook();
      super.worldObj.theProfiler.endStartSection("jump");
      this.jumpHelper.doJump();
      super.worldObj.theProfiler.endSection();
      super.worldObj.theProfiler.endSection();
   }

   protected void updateEntityActionState() {
      super.updateEntityActionState();
      super.moveStrafing = 0.0F;
      super.moveForward = 0.0F;
      this.despawnEntity();
      float var1 = 8.0F;
      if(super.rand.nextFloat() < 0.02F) {
         EntityPlayer var2 = super.worldObj.getClosestPlayerToEntity(this, (double)var1);
         if(var2 != null) {
            this.currentTarget = var2;
            this.numTicksToChaseTarget = 10 + super.rand.nextInt(20);
         } else {
            super.randomYawVelocity = (super.rand.nextFloat() - 0.5F) * 20.0F;
         }
      }

      if(this.currentTarget != null) {
         this.faceEntity(this.currentTarget, 10.0F, (float)this.getVerticalFaceSpeed());
         if(this.numTicksToChaseTarget-- <= 0 || this.currentTarget.isDead || this.currentTarget.getDistanceSqToEntity(this) > (double)(var1 * var1)) {
            this.currentTarget = null;
         }
      } else {
         if(super.rand.nextFloat() < 0.05F) {
            super.randomYawVelocity = (super.rand.nextFloat() - 0.5F) * 20.0F;
         }

         super.rotationYaw += super.randomYawVelocity;
         super.rotationPitch = this.defaultPitch;
      }

      boolean var4 = this.isInWater();
      boolean var3 = this.handleLavaMovement();
      if(var4 || var3) {
         super.isJumping = super.rand.nextFloat() < 0.8F;
      }

   }

   public int getVerticalFaceSpeed() {
      return 40;
   }

   public void faceEntity(Entity var1, float var2, float var3) {
      double var4 = var1.posX - super.posX;
      double var8 = var1.posZ - super.posZ;
      double var6;
      if(var1 instanceof EntityLivingBase) {
         EntityLivingBase var10 = (EntityLivingBase)var1;
         var6 = var10.posY + (double)var10.getEyeHeight() - (super.posY + (double)this.getEyeHeight());
      } else {
         var6 = (var1.boundingBox.minY + var1.boundingBox.maxY) / 2.0D - (super.posY + (double)this.getEyeHeight());
      }

      double var14 = (double)MathHelper.sqrt_double(var4 * var4 + var8 * var8);
      float var12 = (float)(Math.atan2(var8, var4) * 180.0D / 3.1415927410125732D) - 90.0F;
      float var13 = (float)(-(Math.atan2(var6, var14) * 180.0D / 3.1415927410125732D));
      super.rotationPitch = this.updateRotation(super.rotationPitch, var13, var3);
      super.rotationYaw = this.updateRotation(super.rotationYaw, var12, var2);
   }

   private float updateRotation(float var1, float var2, float var3) {
      float var4 = MathHelper.wrapAngleTo180_float(var2 - var1);
      if(var4 > var3) {
         var4 = var3;
      }

      if(var4 < -var3) {
         var4 = -var3;
      }

      return var1 + var4;
   }

   public boolean getCanSpawnHere() {
      return super.worldObj.checkNoEntityCollision(super.boundingBox) && super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox);
   }

   public float getRenderSizeModifier() {
      return 1.0F;
   }

   public int getMaxSpawnedInChunk() {
      return 4;
   }

   public int getMaxSafePointTries() {
      if(this.getAttackTarget() == null) {
         return 3;
      } else {
         int var1 = (int)(this.getHealth() - this.getMaxHealth() * 0.33F);
         var1 -= (3 - super.worldObj.difficultySetting.getDifficultyId()) * 4;
         if(var1 < 0) {
            var1 = 0;
         }

         return var1 + 3;
      }
   }

   public ItemStack getHeldItem() {
      return this.equipment[0];
   }

   public ItemStack getEquipmentInSlot(int var1) {
      return this.equipment[var1];
   }

   public ItemStack func_130225_q(int var1) {
      return this.equipment[var1 + 1];
   }

   public void setCurrentItemOrArmor(int var1, ItemStack var2) {
      this.equipment[var1] = var2;
   }

   public ItemStack[] getLastActiveItems() {
      return this.equipment;
   }

   protected void dropEquipment(boolean var1, int var2) {
      for(int var3 = 0; var3 < this.getLastActiveItems().length; ++var3) {
         ItemStack var4 = this.getEquipmentInSlot(var3);
         boolean var5 = this.equipmentDropChances[var3] > 1.0F;
         if(var4 != null && (var1 || var5) && super.rand.nextFloat() - (float)var2 * 0.01F < this.equipmentDropChances[var3]) {
            if(!var5 && var4.isItemStackDamageable()) {
               int var6 = Math.max(var4.getMaxDamage() - 25, 1);
               int var7 = var4.getMaxDamage() - super.rand.nextInt(super.rand.nextInt(var6) + 1);
               if(var7 > var6) {
                  var7 = var6;
               }

               if(var7 < 1) {
                  var7 = 1;
               }

               var4.setItemDamage(var7);
            }

            this.entityDropItem(var4, 0.0F);
         }
      }

   }

   protected void addRandomArmor() {
      if(super.rand.nextFloat() < 0.15F * super.worldObj.func_147462_b(super.posX, super.posY, super.posZ)) {
         int var1 = super.rand.nextInt(2);
         float var2 = super.worldObj.difficultySetting == EnumDifficulty.HARD?0.1F:0.25F;
         if(super.rand.nextFloat() < 0.095F) {
            ++var1;
         }

         if(super.rand.nextFloat() < 0.095F) {
            ++var1;
         }

         if(super.rand.nextFloat() < 0.095F) {
            ++var1;
         }

         for(int var3 = 3; var3 >= 0; --var3) {
            ItemStack var4 = this.func_130225_q(var3);
            if(var3 < 3 && super.rand.nextFloat() < var2) {
               break;
            }

            if(var4 == null) {
               Item var5 = getArmorItemForSlot(var3 + 1, var1);
               if(var5 != null) {
                  this.setCurrentItemOrArmor(var3 + 1, new ItemStack(var5));
               }
            }
         }
      }

   }

   public static int getArmorPosition(ItemStack var0) {
      if(var0.getItem() != Item.getItemFromBlock(Blocks.pumpkin) && var0.getItem() != Items.skull) {
         if(var0.getItem() instanceof ItemArmor) {
            switch(((ItemArmor)var0.getItem()).armorType) {
            case 0:
               return 4;
            case 1:
               return 3;
            case 2:
               return 2;
            case 3:
               return 1;
            }
         }

         return 0;
      } else {
         return 4;
      }
   }

   public static Item getArmorItemForSlot(int var0, int var1) {
      switch(var0) {
      case 4:
         if(var1 == 0) {
            return Items.leather_helmet;
         } else if(var1 == 1) {
            return Items.golden_helmet;
         } else if(var1 == 2) {
            return Items.chainmail_helmet;
         } else if(var1 == 3) {
            return Items.iron_helmet;
         } else if(var1 == 4) {
            return Items.diamond_helmet;
         }
      case 3:
         if(var1 == 0) {
            return Items.leather_chestplate;
         } else if(var1 == 1) {
            return Items.golden_chestplate;
         } else if(var1 == 2) {
            return Items.chainmail_chestplate;
         } else if(var1 == 3) {
            return Items.iron_chestplate;
         } else if(var1 == 4) {
            return Items.diamond_chestplate;
         }
      case 2:
         if(var1 == 0) {
            return Items.leather_leggings;
         } else if(var1 == 1) {
            return Items.golden_leggings;
         } else if(var1 == 2) {
            return Items.chainmail_leggings;
         } else if(var1 == 3) {
            return Items.iron_leggings;
         } else if(var1 == 4) {
            return Items.diamond_leggings;
         }
      case 1:
         if(var1 == 0) {
            return Items.leather_boots;
         } else if(var1 == 1) {
            return Items.golden_boots;
         } else if(var1 == 2) {
            return Items.chainmail_boots;
         } else if(var1 == 3) {
            return Items.iron_boots;
         } else if(var1 == 4) {
            return Items.diamond_boots;
         }
      default:
         return null;
      }
   }

   protected void enchantEquipment() {
      float var1 = super.worldObj.func_147462_b(super.posX, super.posY, super.posZ);
      if(this.getHeldItem() != null && super.rand.nextFloat() < 0.25F * var1) {
         EnchantmentHelper.addRandomEnchantment(super.rand, this.getHeldItem(), (int)(5.0F + var1 * (float)super.rand.nextInt(18)));
      }

      for(int var2 = 0; var2 < 4; ++var2) {
         ItemStack var3 = this.func_130225_q(var2);
         if(var3 != null && super.rand.nextFloat() < 0.5F * var1) {
            EnchantmentHelper.addRandomEnchantment(super.rand, var3, (int)(5.0F + var1 * (float)super.rand.nextInt(18)));
         }
      }

   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData var1) {
      this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random spawn bonus", super.rand.nextGaussian() * 0.05D, 1));
      return var1;
   }

   public boolean canBeSteered() {
      return false;
   }

   public String getCommandSenderName() {
      return this.hasCustomNameTag()?this.getCustomNameTag():super.getCommandSenderName();
   }

   public void func_110163_bv() {
      this.persistenceRequired = true;
   }

   public void setCustomNameTag(String var1) {
      super.dataWatcher.updateObject(10, var1);
   }

   public String getCustomNameTag() {
      return super.dataWatcher.getWatchableObjectString(10);
   }

   public boolean hasCustomNameTag() {
      return super.dataWatcher.getWatchableObjectString(10).length() > 0;
   }

   public void setAlwaysRenderNameTag(boolean var1) {
      super.dataWatcher.updateObject(11, Byte.valueOf((byte)(var1?1:0)));
   }

   public boolean getAlwaysRenderNameTag() {
      return super.dataWatcher.getWatchableObjectByte(11) == 1;
   }

   public boolean getAlwaysRenderNameTagForRender() {
      return this.getAlwaysRenderNameTag();
   }

   public void setEquipmentDropChance(int var1, float var2) {
      this.equipmentDropChances[var1] = var2;
   }

   public boolean canPickUpLoot() {
      return this.canPickUpLoot;
   }

   public void setCanPickUpLoot(boolean var1) {
      this.canPickUpLoot = var1;
   }

   public boolean isNoDespawnRequired() {
      return this.persistenceRequired;
   }

   public final boolean interactFirst(EntityPlayer var1) {
      if(this.getLeashed() && this.getLeashedToEntity() == var1) {
         this.clearLeashed(true, !var1.capabilities.isCreativeMode);
         return true;
      } else {
         ItemStack var2 = var1.inventory.getCurrentItem();
         if(var2 != null && var2.getItem() == Items.lead && this.allowLeashing()) {
            if(!(this instanceof EntityTameable) || !((EntityTameable)this).isTamed()) {
               this.setLeashedToEntity(var1, true);
               --var2.stackSize;
               return true;
            }

            if(((EntityTameable)this).func_152114_e(var1)) {
               this.setLeashedToEntity(var1, true);
               --var2.stackSize;
               return true;
            }
         }

         return this.interact(var1)?true:super.interactFirst(var1);
      }
   }

   protected boolean interact(EntityPlayer var1) {
      return false;
   }

   protected void updateLeashedState() {
      if(this.field_110170_bx != null) {
         this.recreateLeash();
      }

      if(this.isLeashed) {
         if(this.leashedToEntity == null || this.leashedToEntity.isDead) {
            this.clearLeashed(true, true);
         }
      }
   }

   public void clearLeashed(boolean var1, boolean var2) {
      if(this.isLeashed) {
         this.isLeashed = false;
         this.leashedToEntity = null;
         if(!super.worldObj.isRemote && var2) {
            this.dropItem(Items.lead, 1);
         }

         if(!super.worldObj.isRemote && var1 && super.worldObj instanceof WorldServer) {
            ((WorldServer)super.worldObj).getEntityTracker().func_151247_a(this, new S1BPacketEntityAttach(1, this, (Entity)null));
         }
      }

   }

   public boolean allowLeashing() {
      return !this.getLeashed() && !(this instanceof IMob);
   }

   public boolean getLeashed() {
      return this.isLeashed;
   }

   public Entity getLeashedToEntity() {
      return this.leashedToEntity;
   }

   public void setLeashedToEntity(Entity var1, boolean var2) {
      this.isLeashed = true;
      this.leashedToEntity = var1;
      if(!super.worldObj.isRemote && var2 && super.worldObj instanceof WorldServer) {
         ((WorldServer)super.worldObj).getEntityTracker().func_151247_a(this, new S1BPacketEntityAttach(1, this, this.leashedToEntity));
      }

   }

   private void recreateLeash() {
      if(this.isLeashed && this.field_110170_bx != null) {
         if(this.field_110170_bx.hasKey("UUIDMost", 4) && this.field_110170_bx.hasKey("UUIDLeast", 4)) {
            UUID var5 = new UUID(this.field_110170_bx.getLong("UUIDMost"), this.field_110170_bx.getLong("UUIDLeast"));
            List var6 = super.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, super.boundingBox.expand(10.0D, 10.0D, 10.0D));
            Iterator var7 = var6.iterator();

            while(var7.hasNext()) {
               EntityLivingBase var8 = (EntityLivingBase)var7.next();
               if(var8.getUniqueID().equals(var5)) {
                  this.leashedToEntity = var8;
                  break;
               }
            }
         } else if(this.field_110170_bx.hasKey("X", 99) && this.field_110170_bx.hasKey("Y", 99) && this.field_110170_bx.hasKey("Z", 99)) {
            int var1 = this.field_110170_bx.getInteger("X");
            int var2 = this.field_110170_bx.getInteger("Y");
            int var3 = this.field_110170_bx.getInteger("Z");
            EntityLeashKnot var4 = EntityLeashKnot.getKnotForBlock(super.worldObj, var1, var2, var3);
            if(var4 == null) {
               var4 = EntityLeashKnot.func_110129_a(super.worldObj, var1, var2, var3);
            }

            this.leashedToEntity = var4;
         } else {
            this.clearLeashed(false, true);
         }
      }

      this.field_110170_bx = null;
   }
}
