package net.minecraft.entity.passive;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Block$SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse$1;
import net.minecraft.entity.passive.EntityHorse$GroupData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityHorse extends EntityAnimal implements IInvBasic {

   private static final IEntitySelector horseBreedingSelector = new EntityHorse$1();
   private static final IAttribute horseJumpStrength = (new RangedAttribute("horse.jumpStrength", 0.7D, 0.0D, 2.0D)).setDescription("Jump Strength").setShouldWatch(true);
   private static final String[] horseArmorTextures = new String[]{null, "textures/entity/horse/armor/horse_armor_iron.png", "textures/entity/horse/armor/horse_armor_gold.png", "textures/entity/horse/armor/horse_armor_diamond.png"};
   private static final String[] field_110273_bx = new String[]{"", "meo", "goo", "dio"};
   private static final int[] armorValues = new int[]{0, 5, 7, 11};
   private static final String[] horseTextures = new String[]{"textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png"};
   private static final String[] field_110269_bA = new String[]{"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
   private static final String[] horseMarkingTextures = new String[]{null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png"};
   private static final String[] field_110292_bC = new String[]{"", "wo_", "wmo", "wdo", "bdo"};
   private int eatingHaystackCounter;
   private int openMouthCounter;
   private int jumpRearingCounter;
   public int field_110278_bp;
   public int field_110279_bq;
   protected boolean horseJumping;
   private AnimalChest horseChest;
   private boolean hasReproduced;
   protected int temper;
   protected float jumpPower;
   private boolean field_110294_bI;
   private float headLean;
   private float prevHeadLean;
   private float rearingAmount;
   private float prevRearingAmount;
   private float mouthOpenness;
   private float prevMouthOpenness;
   private int field_110285_bP;
   private String field_110286_bQ;
   private String[] field_110280_bR = new String[3];


   public EntityHorse(World var1) {
      super(var1);
      this.setSize(1.4F, 1.6F);
      super.isImmuneToFire = false;
      this.setChested(false);
      this.getNavigator().setAvoidsWater(true);
      super.tasks.addTask(0, new EntityAISwimming(this));
      super.tasks.addTask(1, new EntityAIPanic(this, 1.2D));
      super.tasks.addTask(1, new EntityAIRunAroundLikeCrazy(this, 1.2D));
      super.tasks.addTask(2, new EntityAIMate(this, 1.0D));
      super.tasks.addTask(4, new EntityAIFollowParent(this, 1.0D));
      super.tasks.addTask(6, new EntityAIWander(this, 0.7D));
      super.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      super.tasks.addTask(8, new EntityAILookIdle(this));
      this.func_110226_cD();
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, Integer.valueOf(0));
      super.dataWatcher.addObject(19, Byte.valueOf((byte)0));
      super.dataWatcher.addObject(20, Integer.valueOf(0));
      super.dataWatcher.addObject(21, String.valueOf(""));
      super.dataWatcher.addObject(22, Integer.valueOf(0));
   }

   public void setHorseType(int var1) {
      super.dataWatcher.updateObject(19, Byte.valueOf((byte)var1));
      this.func_110230_cF();
   }

   public int getHorseType() {
      return super.dataWatcher.getWatchableObjectByte(19);
   }

   public void setHorseVariant(int var1) {
      super.dataWatcher.updateObject(20, Integer.valueOf(var1));
      this.func_110230_cF();
   }

   public int getHorseVariant() {
      return super.dataWatcher.getWatchableObjectInt(20);
   }

   public String getCommandSenderName() {
      if(this.hasCustomNameTag()) {
         return this.getCustomNameTag();
      } else {
         int var1 = this.getHorseType();
         switch(var1) {
         case 0:
         default:
            return StatCollector.translateToLocal("entity.horse.name");
         case 1:
            return StatCollector.translateToLocal("entity.donkey.name");
         case 2:
            return StatCollector.translateToLocal("entity.mule.name");
         case 3:
            return StatCollector.translateToLocal("entity.zombiehorse.name");
         case 4:
            return StatCollector.translateToLocal("entity.skeletonhorse.name");
         }
      }
   }

   private boolean getHorseWatchableBoolean(int var1) {
      return (super.dataWatcher.getWatchableObjectInt(16) & var1) != 0;
   }

   private void setHorseWatchableBoolean(int var1, boolean var2) {
      int var3 = super.dataWatcher.getWatchableObjectInt(16);
      if(var2) {
         super.dataWatcher.updateObject(16, Integer.valueOf(var3 | var1));
      } else {
         super.dataWatcher.updateObject(16, Integer.valueOf(var3 & ~var1));
      }

   }

   public boolean isAdultHorse() {
      return !this.isChild();
   }

   public boolean isTame() {
      return this.getHorseWatchableBoolean(2);
   }

   public boolean func_110253_bW() {
      return this.isAdultHorse();
   }

   public String func_152119_ch() {
      return super.dataWatcher.getWatchableObjectString(21);
   }

   public void func_152120_b(String var1) {
      super.dataWatcher.updateObject(21, var1);
   }

   public float getHorseSize() {
      int var1 = this.getGrowingAge();
      return var1 >= 0?1.0F:0.5F + (float)(-24000 - var1) / -24000.0F * 0.5F;
   }

   public void setScaleForAge(boolean var1) {
      if(var1) {
         this.setScale(this.getHorseSize());
      } else {
         this.setScale(1.0F);
      }

   }

   public boolean isHorseJumping() {
      return this.horseJumping;
   }

   public void setHorseTamed(boolean var1) {
      this.setHorseWatchableBoolean(2, var1);
   }

   public void setHorseJumping(boolean var1) {
      this.horseJumping = var1;
   }

   public boolean allowLeashing() {
      return !this.func_110256_cu() && super.allowLeashing();
   }

   protected void func_142017_o(float var1) {
      if(var1 > 6.0F && this.isEatingHaystack()) {
         this.setEatingHaystack(false);
      }

   }

   public boolean isChested() {
      return this.getHorseWatchableBoolean(8);
   }

   public int func_110241_cb() {
      return super.dataWatcher.getWatchableObjectInt(22);
   }

   private int getHorseArmorIndex(ItemStack var1) {
      if(var1 == null) {
         return 0;
      } else {
         Item var2 = var1.getItem();
         return var2 == Items.iron_horse_armor?1:(var2 == Items.golden_horse_armor?2:(var2 == Items.diamond_horse_armor?3:0));
      }
   }

   public boolean isEatingHaystack() {
      return this.getHorseWatchableBoolean(32);
   }

   public boolean isRearing() {
      return this.getHorseWatchableBoolean(64);
   }

   public boolean func_110205_ce() {
      return this.getHorseWatchableBoolean(16);
   }

   public boolean getHasReproduced() {
      return this.hasReproduced;
   }

   public void func_146086_d(ItemStack var1) {
      super.dataWatcher.updateObject(22, Integer.valueOf(this.getHorseArmorIndex(var1)));
      this.func_110230_cF();
   }

   public void func_110242_l(boolean var1) {
      this.setHorseWatchableBoolean(16, var1);
   }

   public void setChested(boolean var1) {
      this.setHorseWatchableBoolean(8, var1);
   }

   public void setHasReproduced(boolean var1) {
      this.hasReproduced = var1;
   }

   public void setHorseSaddled(boolean var1) {
      this.setHorseWatchableBoolean(4, var1);
   }

   public int getTemper() {
      return this.temper;
   }

   public void setTemper(int var1) {
      this.temper = var1;
   }

   public int increaseTemper(int var1) {
      int var2 = MathHelper.clamp_int(this.getTemper() + var1, 0, this.getMaxTemper());
      this.setTemper(var2);
      return var2;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      Entity var3 = var1.getEntity();
      return super.riddenByEntity != null && super.riddenByEntity.equals(var3)?false:super.attackEntityFrom(var1, var2);
   }

   public int getTotalArmorValue() {
      return armorValues[this.func_110241_cb()];
   }

   public boolean canBePushed() {
      return super.riddenByEntity == null;
   }

   public boolean prepareChunkForSpawn() {
      int var1 = MathHelper.floor_double(super.posX);
      int var2 = MathHelper.floor_double(super.posZ);
      super.worldObj.getBiomeGenForCoords(var1, var2);
      return true;
   }

   public void dropChests() {
      if(!super.worldObj.isRemote && this.isChested()) {
         this.dropItem(Item.getItemFromBlock(Blocks.chest), 1);
         this.setChested(false);
      }
   }

   private void func_110266_cB() {
      this.openHorseMouth();
      super.worldObj.playSoundAtEntity(this, "eating", 1.0F, 1.0F + (super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F);
   }

   protected void fall(float var1) {
      if(var1 > 1.0F) {
         this.playSound("mob.horse.land", 0.4F, 1.0F);
      }

      int var2 = MathHelper.ceiling_float_int(var1 * 0.5F - 3.0F);
      if(var2 > 0) {
         this.attackEntityFrom(DamageSource.fall, (float)var2);
         if(super.riddenByEntity != null) {
            super.riddenByEntity.attackEntityFrom(DamageSource.fall, (float)var2);
         }

         Block var3 = super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY - 0.2D - (double)super.prevRotationYaw), MathHelper.floor_double(super.posZ));
         if(var3.getMaterial() != Material.air) {
            Block$SoundType var4 = var3.stepSound;
            super.worldObj.playSoundAtEntity(this, var4.getStepResourcePath(), var4.getVolume() * 0.5F, var4.getPitch() * 0.75F);
         }

      }
   }

   private int func_110225_cC() {
      int var1 = this.getHorseType();
      return this.isChested() && (var1 == 1 || var1 == 2)?17:2;
   }

   private void func_110226_cD() {
      AnimalChest var1 = this.horseChest;
      this.horseChest = new AnimalChest("HorseChest", this.func_110225_cC());
      this.horseChest.func_110133_a(this.getCommandSenderName());
      if(var1 != null) {
         var1.func_110132_b(this);
         int var2 = Math.min(var1.getSizeInventory(), this.horseChest.getSizeInventory());

         for(int var3 = 0; var3 < var2; ++var3) {
            ItemStack var4 = var1.getStackInSlot(var3);
            if(var4 != null) {
               this.horseChest.setInventorySlotContents(var3, var4.copy());
            }
         }

         var1 = null;
      }

      this.horseChest.func_110134_a(this);
      this.func_110232_cE();
   }

   private void func_110232_cE() {
      if(!super.worldObj.isRemote) {
         this.setHorseSaddled(this.horseChest.getStackInSlot(0) != null);
         if(this.func_110259_cr()) {
            this.func_146086_d(this.horseChest.getStackInSlot(1));
         }
      }

   }

   public void onInventoryChanged(InventoryBasic var1) {
      int var2 = this.func_110241_cb();
      boolean var3 = this.isHorseSaddled();
      this.func_110232_cE();
      if(super.ticksExisted > 20) {
         if(var2 == 0 && var2 != this.func_110241_cb()) {
            this.playSound("mob.horse.armor", 0.5F, 1.0F);
         } else if(var2 != this.func_110241_cb()) {
            this.playSound("mob.horse.armor", 0.5F, 1.0F);
         }

         if(!var3 && this.isHorseSaddled()) {
            this.playSound("mob.horse.leather", 0.5F, 1.0F);
         }
      }

   }

   public boolean getCanSpawnHere() {
      this.prepareChunkForSpawn();
      return super.getCanSpawnHere();
   }

   protected EntityHorse getClosestHorse(Entity var1, double var2) {
      double var4 = Double.MAX_VALUE;
      Entity var6 = null;
      List var7 = super.worldObj.getEntitiesWithinAABBExcludingEntity(var1, var1.boundingBox.addCoord(var2, var2, var2), horseBreedingSelector);
      Iterator var8 = var7.iterator();

      while(var8.hasNext()) {
         Entity var9 = (Entity)var8.next();
         double var10 = var9.getDistanceSq(var1.posX, var1.posY, var1.posZ);
         if(var10 < var4) {
            var6 = var9;
            var4 = var10;
         }
      }

      return (EntityHorse)var6;
   }

   public double getHorseJumpStrength() {
      return this.getEntityAttribute(horseJumpStrength).getAttributeValue();
   }

   protected String getDeathSound() {
      this.openHorseMouth();
      int var1 = this.getHorseType();
      return var1 == 3?"mob.horse.zombie.death":(var1 == 4?"mob.horse.skeleton.death":(var1 != 1 && var1 != 2?"mob.horse.death":"mob.horse.donkey.death"));
   }

   protected Item getDropItem() {
      boolean var1 = super.rand.nextInt(4) == 0;
      int var2 = this.getHorseType();
      return var2 == 4?Items.bone:(var2 == 3?(var1?Item.getItemById(0):Items.rotten_flesh):Items.leather);
   }

   protected String getHurtSound() {
      this.openHorseMouth();
      if(super.rand.nextInt(3) == 0) {
         this.makeHorseRear();
      }

      int var1 = this.getHorseType();
      return var1 == 3?"mob.horse.zombie.hit":(var1 == 4?"mob.horse.skeleton.hit":(var1 != 1 && var1 != 2?"mob.horse.hit":"mob.horse.donkey.hit"));
   }

   public boolean isHorseSaddled() {
      return this.getHorseWatchableBoolean(4);
   }

   protected String getLivingSound() {
      this.openHorseMouth();
      if(super.rand.nextInt(10) == 0 && !this.isMovementBlocked()) {
         this.makeHorseRear();
      }

      int var1 = this.getHorseType();
      return var1 == 3?"mob.horse.zombie.idle":(var1 == 4?"mob.horse.skeleton.idle":(var1 != 1 && var1 != 2?"mob.horse.idle":"mob.horse.donkey.idle"));
   }

   protected String getAngrySoundName() {
      this.openHorseMouth();
      this.makeHorseRear();
      int var1 = this.getHorseType();
      return var1 != 3 && var1 != 4?(var1 != 1 && var1 != 2?"mob.horse.angry":"mob.horse.donkey.angry"):null;
   }

   protected void func_145780_a(int var1, int var2, int var3, Block var4) {
      Block$SoundType var5 = var4.stepSound;
      if(super.worldObj.getBlock(var1, var2 + 1, var3) == Blocks.snow_layer) {
         var5 = Blocks.snow_layer.stepSound;
      }

      if(!var4.getMaterial().isLiquid()) {
         int var6 = this.getHorseType();
         if(super.riddenByEntity != null && var6 != 1 && var6 != 2) {
            ++this.field_110285_bP;
            if(this.field_110285_bP > 5 && this.field_110285_bP % 3 == 0) {
               this.playSound("mob.horse.gallop", var5.getVolume() * 0.15F, var5.getPitch());
               if(var6 == 0 && super.rand.nextInt(10) == 0) {
                  this.playSound("mob.horse.breathe", var5.getVolume() * 0.6F, var5.getPitch());
               }
            } else if(this.field_110285_bP <= 5) {
               this.playSound("mob.horse.wood", var5.getVolume() * 0.15F, var5.getPitch());
            }
         } else if(var5 == Block.soundTypeWood) {
            this.playSound("mob.horse.wood", var5.getVolume() * 0.15F, var5.getPitch());
         } else {
            this.playSound("mob.horse.soft", var5.getVolume() * 0.15F, var5.getPitch());
         }
      }

   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(horseJumpStrength);
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(53.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22499999403953552D);
   }

   public int getMaxSpawnedInChunk() {
      return 6;
   }

   public int getMaxTemper() {
      return 100;
   }

   protected float getSoundVolume() {
      return 0.8F;
   }

   public int getTalkInterval() {
      return 400;
   }

   public boolean func_110239_cn() {
      return this.getHorseType() == 0 || this.func_110241_cb() > 0;
   }

   private void func_110230_cF() {
      this.field_110286_bQ = null;
   }

   private void setHorseTexturePaths() {
      this.field_110286_bQ = "horse/";
      this.field_110280_bR[0] = null;
      this.field_110280_bR[1] = null;
      this.field_110280_bR[2] = null;
      int var1 = this.getHorseType();
      int var2 = this.getHorseVariant();
      int var3;
      if(var1 == 0) {
         var3 = var2 & 255;
         int var4 = (var2 & '\uff00') >> 8;
         this.field_110280_bR[0] = horseTextures[var3];
         this.field_110286_bQ = this.field_110286_bQ + field_110269_bA[var3];
         this.field_110280_bR[1] = horseMarkingTextures[var4];
         this.field_110286_bQ = this.field_110286_bQ + field_110292_bC[var4];
      } else {
         this.field_110280_bR[0] = "";
         this.field_110286_bQ = this.field_110286_bQ + "_" + var1 + "_";
      }

      var3 = this.func_110241_cb();
      this.field_110280_bR[2] = horseArmorTextures[var3];
      this.field_110286_bQ = this.field_110286_bQ + field_110273_bx[var3];
   }

   public String getHorseTexture() {
      if(this.field_110286_bQ == null) {
         this.setHorseTexturePaths();
      }

      return this.field_110286_bQ;
   }

   public String[] getVariantTexturePaths() {
      if(this.field_110286_bQ == null) {
         this.setHorseTexturePaths();
      }

      return this.field_110280_bR;
   }

   public void openGUI(EntityPlayer var1) {
      if(!super.worldObj.isRemote && (super.riddenByEntity == null || super.riddenByEntity == var1) && this.isTame()) {
         this.horseChest.func_110133_a(this.getCommandSenderName());
         var1.displayGUIHorse(this, this.horseChest);
      }

   }

   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.inventory.getCurrentItem();
      if(var2 != null && var2.getItem() == Items.spawn_egg) {
         return super.interact(var1);
      } else if(!this.isTame() && this.func_110256_cu()) {
         return false;
      } else if(this.isTame() && this.isAdultHorse() && var1.isSneaking()) {
         this.openGUI(var1);
         return true;
      } else if(this.func_110253_bW() && super.riddenByEntity != null) {
         return super.interact(var1);
      } else {
         if(var2 != null) {
            boolean var3 = false;
            if(this.func_110259_cr()) {
               byte var4 = -1;
               if(var2.getItem() == Items.iron_horse_armor) {
                  var4 = 1;
               } else if(var2.getItem() == Items.golden_horse_armor) {
                  var4 = 2;
               } else if(var2.getItem() == Items.diamond_horse_armor) {
                  var4 = 3;
               }

               if(var4 >= 0) {
                  if(!this.isTame()) {
                     this.makeHorseRearWithSound();
                     return true;
                  }

                  this.openGUI(var1);
                  return true;
               }
            }

            if(!var3 && !this.func_110256_cu()) {
               float var7 = 0.0F;
               short var5 = 0;
               byte var6 = 0;
               if(var2.getItem() == Items.wheat) {
                  var7 = 2.0F;
                  var5 = 60;
                  var6 = 3;
               } else if(var2.getItem() == Items.sugar) {
                  var7 = 1.0F;
                  var5 = 30;
                  var6 = 3;
               } else if(var2.getItem() == Items.bread) {
                  var7 = 7.0F;
                  var5 = 180;
                  var6 = 3;
               } else if(Block.getBlockFromItem(var2.getItem()) == Blocks.hay_block) {
                  var7 = 20.0F;
                  var5 = 180;
               } else if(var2.getItem() == Items.apple) {
                  var7 = 3.0F;
                  var5 = 60;
                  var6 = 3;
               } else if(var2.getItem() == Items.golden_carrot) {
                  var7 = 4.0F;
                  var5 = 60;
                  var6 = 5;
                  if(this.isTame() && this.getGrowingAge() == 0) {
                     var3 = true;
                     this.func_146082_f(var1);
                  }
               } else if(var2.getItem() == Items.golden_apple) {
                  var7 = 10.0F;
                  var5 = 240;
                  var6 = 10;
                  if(this.isTame() && this.getGrowingAge() == 0) {
                     var3 = true;
                     this.func_146082_f(var1);
                  }
               }

               if(this.getHealth() < this.getMaxHealth() && var7 > 0.0F) {
                  this.heal(var7);
                  var3 = true;
               }

               if(!this.isAdultHorse() && var5 > 0) {
                  this.addGrowth(var5);
                  var3 = true;
               }

               if(var6 > 0 && (var3 || !this.isTame()) && var6 < this.getMaxTemper()) {
                  var3 = true;
                  this.increaseTemper(var6);
               }

               if(var3) {
                  this.func_110266_cB();
               }
            }

            if(!this.isTame() && !var3) {
               if(var2 != null && var2.interactWithEntity(var1, this)) {
                  return true;
               }

               this.makeHorseRearWithSound();
               return true;
            }

            if(!var3 && this.func_110229_cs() && !this.isChested() && var2.getItem() == Item.getItemFromBlock(Blocks.chest)) {
               this.setChested(true);
               this.playSound("mob.chickenplop", 1.0F, (super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F);
               var3 = true;
               this.func_110226_cD();
            }

            if(!var3 && this.func_110253_bW() && !this.isHorseSaddled() && var2.getItem() == Items.saddle) {
               this.openGUI(var1);
               return true;
            }

            if(var3) {
               if(!var1.capabilities.isCreativeMode && --var2.stackSize == 0) {
                  var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
               }

               return true;
            }
         }

         if(this.func_110253_bW() && super.riddenByEntity == null) {
            if(var2 != null && var2.interactWithEntity(var1, this)) {
               return true;
            } else {
               this.func_110237_h(var1);
               return true;
            }
         } else {
            return super.interact(var1);
         }
      }
   }

   private void func_110237_h(EntityPlayer var1) {
      var1.rotationYaw = super.rotationYaw;
      var1.rotationPitch = super.rotationPitch;
      this.setEatingHaystack(false);
      this.setRearing(false);
      if(!super.worldObj.isRemote) {
         var1.mountEntity(this);
      }

   }

   public boolean func_110259_cr() {
      return this.getHorseType() == 0;
   }

   public boolean func_110229_cs() {
      int var1 = this.getHorseType();
      return var1 == 2 || var1 == 1;
   }

   protected boolean isMovementBlocked() {
      return super.riddenByEntity != null && this.isHorseSaddled()?true:this.isEatingHaystack() || this.isRearing();
   }

   public boolean func_110256_cu() {
      int var1 = this.getHorseType();
      return var1 == 3 || var1 == 4;
   }

   public boolean func_110222_cv() {
      return this.func_110256_cu() || this.getHorseType() == 2;
   }

   public boolean isBreedingItem(ItemStack var1) {
      return false;
   }

   private void func_110210_cH() {
      this.field_110278_bp = 1;
   }

   public void onDeath(DamageSource var1) {
      super.onDeath(var1);
      if(!super.worldObj.isRemote) {
         this.dropChestItems();
      }

   }

   public void onLivingUpdate() {
      if(super.rand.nextInt(200) == 0) {
         this.func_110210_cH();
      }

      super.onLivingUpdate();
      if(!super.worldObj.isRemote) {
         if(super.rand.nextInt(900) == 0 && super.deathTime == 0) {
            this.heal(1.0F);
         }

         if(!this.isEatingHaystack() && super.riddenByEntity == null && super.rand.nextInt(300) == 0 && super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY) - 1, MathHelper.floor_double(super.posZ)) == Blocks.grass) {
            this.setEatingHaystack(true);
         }

         if(this.isEatingHaystack() && ++this.eatingHaystackCounter > 50) {
            this.eatingHaystackCounter = 0;
            this.setEatingHaystack(false);
         }

         if(this.func_110205_ce() && !this.isAdultHorse() && !this.isEatingHaystack()) {
            EntityHorse var1 = this.getClosestHorse(this, 16.0D);
            if(var1 != null && this.getDistanceSqToEntity(var1) > 4.0D) {
               PathEntity var2 = super.worldObj.getPathEntityToEntity(this, var1, 16.0F, true, false, false, true);
               this.setPathToEntity(var2);
            }
         }
      }

   }

   public void onUpdate() {
      super.onUpdate();
      if(super.worldObj.isRemote && super.dataWatcher.hasChanges()) {
         super.dataWatcher.func_111144_e();
         this.func_110230_cF();
      }

      if(this.openMouthCounter > 0 && ++this.openMouthCounter > 30) {
         this.openMouthCounter = 0;
         this.setHorseWatchableBoolean(128, false);
      }

      if(!super.worldObj.isRemote && this.jumpRearingCounter > 0 && ++this.jumpRearingCounter > 20) {
         this.jumpRearingCounter = 0;
         this.setRearing(false);
      }

      if(this.field_110278_bp > 0 && ++this.field_110278_bp > 8) {
         this.field_110278_bp = 0;
      }

      if(this.field_110279_bq > 0) {
         ++this.field_110279_bq;
         if(this.field_110279_bq > 300) {
            this.field_110279_bq = 0;
         }
      }

      this.prevHeadLean = this.headLean;
      if(this.isEatingHaystack()) {
         this.headLean += (1.0F - this.headLean) * 0.4F + 0.05F;
         if(this.headLean > 1.0F) {
            this.headLean = 1.0F;
         }
      } else {
         this.headLean += (0.0F - this.headLean) * 0.4F - 0.05F;
         if(this.headLean < 0.0F) {
            this.headLean = 0.0F;
         }
      }

      this.prevRearingAmount = this.rearingAmount;
      if(this.isRearing()) {
         this.prevHeadLean = this.headLean = 0.0F;
         this.rearingAmount += (1.0F - this.rearingAmount) * 0.4F + 0.05F;
         if(this.rearingAmount > 1.0F) {
            this.rearingAmount = 1.0F;
         }
      } else {
         this.field_110294_bI = false;
         this.rearingAmount += (0.8F * this.rearingAmount * this.rearingAmount * this.rearingAmount - this.rearingAmount) * 0.6F - 0.05F;
         if(this.rearingAmount < 0.0F) {
            this.rearingAmount = 0.0F;
         }
      }

      this.prevMouthOpenness = this.mouthOpenness;
      if(this.getHorseWatchableBoolean(128)) {
         this.mouthOpenness += (1.0F - this.mouthOpenness) * 0.7F + 0.05F;
         if(this.mouthOpenness > 1.0F) {
            this.mouthOpenness = 1.0F;
         }
      } else {
         this.mouthOpenness += (0.0F - this.mouthOpenness) * 0.7F - 0.05F;
         if(this.mouthOpenness < 0.0F) {
            this.mouthOpenness = 0.0F;
         }
      }

   }

   private void openHorseMouth() {
      if(!super.worldObj.isRemote) {
         this.openMouthCounter = 1;
         this.setHorseWatchableBoolean(128, true);
      }

   }

   private boolean func_110200_cJ() {
      return super.riddenByEntity == null && super.ridingEntity == null && this.isTame() && this.isAdultHorse() && !this.func_110222_cv() && this.getHealth() >= this.getMaxHealth();
   }

   public void setEating(boolean var1) {
      this.setHorseWatchableBoolean(32, var1);
   }

   public void setEatingHaystack(boolean var1) {
      this.setEating(var1);
   }

   public void setRearing(boolean var1) {
      if(var1) {
         this.setEatingHaystack(false);
      }

      this.setHorseWatchableBoolean(64, var1);
   }

   private void makeHorseRear() {
      if(!super.worldObj.isRemote) {
         this.jumpRearingCounter = 1;
         this.setRearing(true);
      }

   }

   public void makeHorseRearWithSound() {
      this.makeHorseRear();
      String var1 = this.getAngrySoundName();
      if(var1 != null) {
         this.playSound(var1, this.getSoundVolume(), this.getSoundPitch());
      }

   }

   public void dropChestItems() {
      this.dropItemsInChest(this, this.horseChest);
      this.dropChests();
   }

   private void dropItemsInChest(Entity var1, AnimalChest var2) {
      if(var2 != null && !super.worldObj.isRemote) {
         for(int var3 = 0; var3 < var2.getSizeInventory(); ++var3) {
            ItemStack var4 = var2.getStackInSlot(var3);
            if(var4 != null) {
               this.entityDropItem(var4, 0.0F);
            }
         }

      }
   }

   public boolean setTamedBy(EntityPlayer var1) {
      this.func_152120_b(var1.getUniqueID().toString());
      this.setHorseTamed(true);
      return true;
   }

   public void moveEntityWithHeading(float var1, float var2) {
      if(super.riddenByEntity != null && super.riddenByEntity instanceof EntityLivingBase && this.isHorseSaddled()) {
         super.prevRotationYaw = super.rotationYaw = super.riddenByEntity.rotationYaw;
         super.rotationPitch = super.riddenByEntity.rotationPitch * 0.5F;
         this.setRotation(super.rotationYaw, super.rotationPitch);
         super.rotationYawHead = super.renderYawOffset = super.rotationYaw;
         var1 = ((EntityLivingBase)super.riddenByEntity).moveStrafing * 0.5F;
         var2 = ((EntityLivingBase)super.riddenByEntity).moveForward;
         if(var2 <= 0.0F) {
            var2 *= 0.25F;
            this.field_110285_bP = 0;
         }

         if(super.onGround && this.jumpPower == 0.0F && this.isRearing() && !this.field_110294_bI) {
            var1 = 0.0F;
            var2 = 0.0F;
         }

         if(this.jumpPower > 0.0F && !this.isHorseJumping() && super.onGround) {
            super.motionY = this.getHorseJumpStrength() * (double)this.jumpPower;
            if(this.isPotionActive(Potion.jump)) {
               super.motionY += (double)((float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
            }

            this.setHorseJumping(true);
            super.isAirBorne = true;
            if(var2 > 0.0F) {
               float var3 = MathHelper.sin(super.rotationYaw * 3.1415927F / 180.0F);
               float var4 = MathHelper.cos(super.rotationYaw * 3.1415927F / 180.0F);
               super.motionX += (double)(-0.4F * var3 * this.jumpPower);
               super.motionZ += (double)(0.4F * var4 * this.jumpPower);
               this.playSound("mob.horse.jump", 0.4F, 1.0F);
            }

            this.jumpPower = 0.0F;
         }

         super.stepHeight = 1.0F;
         super.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
         if(!super.worldObj.isRemote) {
            this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
            super.moveEntityWithHeading(var1, var2);
         }

         if(super.onGround) {
            this.jumpPower = 0.0F;
            this.setHorseJumping(false);
         }

         super.prevLimbSwingAmount = super.limbSwingAmount;
         double var8 = super.posX - super.prevPosX;
         double var5 = super.posZ - super.prevPosZ;
         float var7 = MathHelper.sqrt_double(var8 * var8 + var5 * var5) * 4.0F;
         if(var7 > 1.0F) {
            var7 = 1.0F;
         }

         super.limbSwingAmount += (var7 - super.limbSwingAmount) * 0.4F;
         super.limbSwing += super.limbSwingAmount;
      } else {
         super.stepHeight = 0.5F;
         super.jumpMovementFactor = 0.02F;
         super.moveEntityWithHeading(var1, var2);
      }
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("EatingHaystack", this.isEatingHaystack());
      var1.setBoolean("ChestedHorse", this.isChested());
      var1.setBoolean("HasReproduced", this.getHasReproduced());
      var1.setBoolean("Bred", this.func_110205_ce());
      var1.setInteger("Type", this.getHorseType());
      var1.setInteger("Variant", this.getHorseVariant());
      var1.setInteger("Temper", this.getTemper());
      var1.setBoolean("Tame", this.isTame());
      var1.setString("OwnerUUID", this.func_152119_ch());
      if(this.isChested()) {
         NBTTagList var2 = new NBTTagList();

         for(int var3 = 2; var3 < this.horseChest.getSizeInventory(); ++var3) {
            ItemStack var4 = this.horseChest.getStackInSlot(var3);
            if(var4 != null) {
               NBTTagCompound var5 = new NBTTagCompound();
               var5.setByte("Slot", (byte)var3);
               var4.writeToNBT(var5);
               var2.appendTag(var5);
            }
         }

         var1.setTag("Items", var2);
      }

      if(this.horseChest.getStackInSlot(1) != null) {
         var1.setTag("ArmorItem", this.horseChest.getStackInSlot(1).writeToNBT(new NBTTagCompound()));
      }

      if(this.horseChest.getStackInSlot(0) != null) {
         var1.setTag("SaddleItem", this.horseChest.getStackInSlot(0).writeToNBT(new NBTTagCompound()));
      }

   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setEatingHaystack(var1.getBoolean("EatingHaystack"));
      this.func_110242_l(var1.getBoolean("Bred"));
      this.setChested(var1.getBoolean("ChestedHorse"));
      this.setHasReproduced(var1.getBoolean("HasReproduced"));
      this.setHorseType(var1.getInteger("Type"));
      this.setHorseVariant(var1.getInteger("Variant"));
      this.setTemper(var1.getInteger("Temper"));
      this.setHorseTamed(var1.getBoolean("Tame"));
      if(var1.hasKey("OwnerUUID", 8)) {
         this.func_152120_b(var1.getString("OwnerUUID"));
      }

      IAttributeInstance var2 = this.getAttributeMap().getAttributeInstanceByName("Speed");
      if(var2 != null) {
         this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(var2.getBaseValue() * 0.25D);
      }

      if(this.isChested()) {
         NBTTagList var3 = var1.getTagList("Items", 10);
         this.func_110226_cD();

         for(int var4 = 0; var4 < var3.tagCount(); ++var4) {
            NBTTagCompound var5 = var3.getCompoundTagAt(var4);
            int var6 = var5.getByte("Slot") & 255;
            if(var6 >= 2 && var6 < this.horseChest.getSizeInventory()) {
               this.horseChest.setInventorySlotContents(var6, ItemStack.loadItemStackFromNBT(var5));
            }
         }
      }

      ItemStack var7;
      if(var1.hasKey("ArmorItem", 10)) {
         var7 = ItemStack.loadItemStackFromNBT(var1.getCompoundTag("ArmorItem"));
         if(var7 != null && func_146085_a(var7.getItem())) {
            this.horseChest.setInventorySlotContents(1, var7);
         }
      }

      if(var1.hasKey("SaddleItem", 10)) {
         var7 = ItemStack.loadItemStackFromNBT(var1.getCompoundTag("SaddleItem"));
         if(var7 != null && var7.getItem() == Items.saddle) {
            this.horseChest.setInventorySlotContents(0, var7);
         }
      } else if(var1.getBoolean("Saddle")) {
         this.horseChest.setInventorySlotContents(0, new ItemStack(Items.saddle));
      }

      this.func_110232_cE();
   }

   public boolean canMateWith(EntityAnimal var1) {
      if(var1 == this) {
         return false;
      } else if(var1.getClass() != this.getClass()) {
         return false;
      } else {
         EntityHorse var2 = (EntityHorse)var1;
         if(this.func_110200_cJ() && var2.func_110200_cJ()) {
            int var3 = this.getHorseType();
            int var4 = var2.getHorseType();
            return var3 == var4 || var3 == 0 && var4 == 1 || var3 == 1 && var4 == 0;
         } else {
            return false;
         }
      }
   }

   public EntityAgeable createChild(EntityAgeable var1) {
      EntityHorse var2 = (EntityHorse)var1;
      EntityHorse var3 = new EntityHorse(super.worldObj);
      int var4 = this.getHorseType();
      int var5 = var2.getHorseType();
      int var6 = 0;
      if(var4 == var5) {
         var6 = var4;
      } else if(var4 == 0 && var5 == 1 || var4 == 1 && var5 == 0) {
         var6 = 2;
      }

      if(var6 == 0) {
         int var8 = super.rand.nextInt(9);
         int var7;
         if(var8 < 4) {
            var7 = this.getHorseVariant() & 255;
         } else if(var8 < 8) {
            var7 = var2.getHorseVariant() & 255;
         } else {
            var7 = super.rand.nextInt(7);
         }

         int var9 = super.rand.nextInt(5);
         if(var9 < 2) {
            var7 |= this.getHorseVariant() & '\uff00';
         } else if(var9 < 4) {
            var7 |= var2.getHorseVariant() & '\uff00';
         } else {
            var7 |= super.rand.nextInt(5) << 8 & '\uff00';
         }

         var3.setHorseVariant(var7);
      }

      var3.setHorseType(var6);
      double var14 = this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() + var1.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() + (double)this.func_110267_cL();
      var3.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(var14 / 3.0D);
      double var13 = this.getEntityAttribute(horseJumpStrength).getBaseValue() + var1.getEntityAttribute(horseJumpStrength).getBaseValue() + this.func_110245_cM();
      var3.getEntityAttribute(horseJumpStrength).setBaseValue(var13 / 3.0D);
      double var11 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() + var1.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() + this.func_110203_cN();
      var3.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(var11 / 3.0D);
      return var3;
   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData var1) {
      Object var6 = super.onSpawnWithEgg(var1);
      boolean var2 = false;
      int var3 = 0;
      int var7;
      if(var6 instanceof EntityHorse$GroupData) {
         var7 = ((EntityHorse$GroupData)var6).field_111107_a;
         var3 = ((EntityHorse$GroupData)var6).field_111106_b & 255 | super.rand.nextInt(5) << 8;
      } else {
         if(super.rand.nextInt(10) == 0) {
            var7 = 1;
         } else {
            int var4 = super.rand.nextInt(7);
            int var5 = super.rand.nextInt(5);
            var7 = 0;
            var3 = var4 | var5 << 8;
         }

         var6 = new EntityHorse$GroupData(var7, var3);
      }

      this.setHorseType(var7);
      this.setHorseVariant(var3);
      if(super.rand.nextInt(5) == 0) {
         this.setGrowingAge(-24000);
      }

      if(var7 != 4 && var7 != 3) {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.func_110267_cL());
         if(var7 == 0) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.func_110203_cN());
         } else {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.17499999701976776D);
         }
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
         this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
      }

      if(var7 != 2 && var7 != 1) {
         this.getEntityAttribute(horseJumpStrength).setBaseValue(this.func_110245_cM());
      } else {
         this.getEntityAttribute(horseJumpStrength).setBaseValue(0.5D);
      }

      this.setHealth(this.getMaxHealth());
      return (IEntityLivingData)var6;
   }

   public float getGrassEatingAmount(float var1) {
      return this.prevHeadLean + (this.headLean - this.prevHeadLean) * var1;
   }

   public float getRearingAmount(float var1) {
      return this.prevRearingAmount + (this.rearingAmount - this.prevRearingAmount) * var1;
   }

   public float func_110201_q(float var1) {
      return this.prevMouthOpenness + (this.mouthOpenness - this.prevMouthOpenness) * var1;
   }

   protected boolean isAIEnabled() {
      return true;
   }

   public void setJumpPower(int var1) {
      if(this.isHorseSaddled()) {
         if(var1 < 0) {
            var1 = 0;
         } else {
            this.field_110294_bI = true;
            this.makeHorseRear();
         }

         if(var1 >= 90) {
            this.jumpPower = 1.0F;
         } else {
            this.jumpPower = 0.4F + 0.4F * (float)var1 / 90.0F;
         }
      }

   }

   protected void spawnHorseParticles(boolean var1) {
      String var2 = var1?"heart":"smoke";

      for(int var3 = 0; var3 < 7; ++var3) {
         double var4 = super.rand.nextGaussian() * 0.02D;
         double var6 = super.rand.nextGaussian() * 0.02D;
         double var8 = super.rand.nextGaussian() * 0.02D;
         super.worldObj.spawnParticle(var2, super.posX + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, super.posY + 0.5D + (double)(super.rand.nextFloat() * super.height), super.posZ + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, var4, var6, var8);
      }

   }

   public void handleHealthUpdate(byte var1) {
      if(var1 == 7) {
         this.spawnHorseParticles(true);
      } else if(var1 == 6) {
         this.spawnHorseParticles(false);
      } else {
         super.handleHealthUpdate(var1);
      }

   }

   public void updateRiderPosition() {
      super.updateRiderPosition();
      if(this.prevRearingAmount > 0.0F) {
         float var1 = MathHelper.sin(super.renderYawOffset * 3.1415927F / 180.0F);
         float var2 = MathHelper.cos(super.renderYawOffset * 3.1415927F / 180.0F);
         float var3 = 0.7F * this.prevRearingAmount;
         float var4 = 0.15F * this.prevRearingAmount;
         super.riddenByEntity.setPosition(super.posX + (double)(var3 * var1), super.posY + this.getMountedYOffset() + super.riddenByEntity.getYOffset() + (double)var4, super.posZ - (double)(var3 * var2));
         if(super.riddenByEntity instanceof EntityLivingBase) {
            ((EntityLivingBase)super.riddenByEntity).renderYawOffset = super.renderYawOffset;
         }
      }

   }

   private float func_110267_cL() {
      return 15.0F + (float)super.rand.nextInt(8) + (float)super.rand.nextInt(9);
   }

   private double func_110245_cM() {
      return 0.4000000059604645D + super.rand.nextDouble() * 0.2D + super.rand.nextDouble() * 0.2D + super.rand.nextDouble() * 0.2D;
   }

   private double func_110203_cN() {
      return (0.44999998807907104D + super.rand.nextDouble() * 0.3D + super.rand.nextDouble() * 0.3D + super.rand.nextDouble() * 0.3D) * 0.25D;
   }

   public static boolean func_146085_a(Item var0) {
      return var0 == Items.iron_horse_armor || var0 == Items.golden_horse_armor || var0 == Items.diamond_horse_armor;
   }

   public boolean isOnLadder() {
      return false;
   }

}
