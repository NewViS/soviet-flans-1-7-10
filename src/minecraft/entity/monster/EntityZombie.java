package net.minecraft.entity.monster;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie$1;
import net.minecraft.entity.monster.EntityZombie$GroupData;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityZombie extends EntityMob {

   protected static final IAttribute field_110186_bp = (new RangedAttribute("zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
   private static final UUID babySpeedBoostUUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
   private static final AttributeModifier babySpeedBoostModifier = new AttributeModifier(babySpeedBoostUUID, "Baby speed boost", 0.5D, 1);
   private final EntityAIBreakDoor field_146075_bs = new EntityAIBreakDoor(this);
   private int conversionTime;
   private boolean field_146076_bu = false;
   private float field_146074_bv = -1.0F;
   private float field_146073_bw;


   public EntityZombie(World var1) {
      super(var1);
      this.getNavigator().setBreakDoors(true);
      super.tasks.addTask(0, new EntityAISwimming(this));
      super.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
      super.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
      super.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
      super.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
      super.tasks.addTask(7, new EntityAIWander(this, 1.0D));
      super.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      super.tasks.addTask(8, new EntityAILookIdle(this));
      super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
      super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
      this.setSize(0.6F, 1.8F);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
      this.getAttributeMap().registerAttribute(field_110186_bp).setBaseValue(super.rand.nextDouble() * 0.10000000149011612D);
   }

   protected void entityInit() {
      super.entityInit();
      this.getDataWatcher().addObject(12, Byte.valueOf((byte)0));
      this.getDataWatcher().addObject(13, Byte.valueOf((byte)0));
      this.getDataWatcher().addObject(14, Byte.valueOf((byte)0));
   }

   public int getTotalArmorValue() {
      int var1 = super.getTotalArmorValue() + 2;
      if(var1 > 20) {
         var1 = 20;
      }

      return var1;
   }

   protected boolean isAIEnabled() {
      return true;
   }

   public boolean func_146072_bX() {
      return this.field_146076_bu;
   }

   public void func_146070_a(boolean var1) {
      if(this.field_146076_bu != var1) {
         this.field_146076_bu = var1;
         if(var1) {
            super.tasks.addTask(1, this.field_146075_bs);
         } else {
            super.tasks.removeTask(this.field_146075_bs);
         }
      }

   }

   public boolean isChild() {
      return this.getDataWatcher().getWatchableObjectByte(12) == 1;
   }

   protected int getExperiencePoints(EntityPlayer var1) {
      if(this.isChild()) {
         super.experienceValue = (int)((float)super.experienceValue * 2.5F);
      }

      return super.getExperiencePoints(var1);
   }

   public void setChild(boolean var1) {
      this.getDataWatcher().updateObject(12, Byte.valueOf((byte)(var1?1:0)));
      if(super.worldObj != null && !super.worldObj.isRemote) {
         IAttributeInstance var2 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
         var2.removeModifier(babySpeedBoostModifier);
         if(var1) {
            var2.applyModifier(babySpeedBoostModifier);
         }
      }

      this.func_146071_k(var1);
   }

   public boolean isVillager() {
      return this.getDataWatcher().getWatchableObjectByte(13) == 1;
   }

   public void setVillager(boolean var1) {
      this.getDataWatcher().updateObject(13, Byte.valueOf((byte)(var1?1:0)));
   }

   public void onLivingUpdate() {
      if(super.worldObj.isDaytime() && !super.worldObj.isRemote && !this.isChild()) {
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

      if(this.isRiding() && this.getAttackTarget() != null && super.ridingEntity instanceof EntityChicken) {
         ((EntityLiving)super.ridingEntity).getNavigator().setPath(this.getNavigator().getPath(), 1.5D);
      }

      super.onLivingUpdate();
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(!super.attackEntityFrom(var1, var2)) {
         return false;
      } else {
         EntityLivingBase var3 = this.getAttackTarget();
         if(var3 == null && this.getEntityToAttack() instanceof EntityLivingBase) {
            var3 = (EntityLivingBase)this.getEntityToAttack();
         }

         if(var3 == null && var1.getEntity() instanceof EntityLivingBase) {
            var3 = (EntityLivingBase)var1.getEntity();
         }

         if(var3 != null && super.worldObj.difficultySetting == EnumDifficulty.HARD && (double)super.rand.nextFloat() < this.getEntityAttribute(field_110186_bp).getAttributeValue()) {
            int var4 = MathHelper.floor_double(super.posX);
            int var5 = MathHelper.floor_double(super.posY);
            int var6 = MathHelper.floor_double(super.posZ);
            EntityZombie var7 = new EntityZombie(super.worldObj);

            for(int var8 = 0; var8 < 50; ++var8) {
               int var9 = var4 + MathHelper.getRandomIntegerInRange(super.rand, 7, 40) * MathHelper.getRandomIntegerInRange(super.rand, -1, 1);
               int var10 = var5 + MathHelper.getRandomIntegerInRange(super.rand, 7, 40) * MathHelper.getRandomIntegerInRange(super.rand, -1, 1);
               int var11 = var6 + MathHelper.getRandomIntegerInRange(super.rand, 7, 40) * MathHelper.getRandomIntegerInRange(super.rand, -1, 1);
               if(World.doesBlockHaveSolidTopSurface(super.worldObj, var9, var10 - 1, var11) && super.worldObj.getBlockLightValue(var9, var10, var11) < 10) {
                  var7.setPosition((double)var9, (double)var10, (double)var11);
                  if(super.worldObj.checkNoEntityCollision(var7.boundingBox) && super.worldObj.getCollidingBoundingBoxes(var7, var7.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(var7.boundingBox)) {
                     super.worldObj.spawnEntityInWorld(var7);
                     var7.setAttackTarget(var3);
                     var7.onSpawnWithEgg((IEntityLivingData)null);
                     this.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
                     var7.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
                     break;
                  }
               }
            }
         }

         return true;
      }
   }

   public void onUpdate() {
      if(!super.worldObj.isRemote && this.isConverting()) {
         int var1 = this.getConversionTimeBoost();
         this.conversionTime -= var1;
         if(this.conversionTime <= 0) {
            this.convertToVillager();
         }
      }

      super.onUpdate();
   }

   public boolean attackEntityAsMob(Entity var1) {
      boolean var2 = super.attackEntityAsMob(var1);
      if(var2) {
         int var3 = super.worldObj.difficultySetting.getDifficultyId();
         if(this.getHeldItem() == null && this.isBurning() && super.rand.nextFloat() < (float)var3 * 0.3F) {
            var1.setFire(2 * var3);
         }
      }

      return var2;
   }

   protected String getLivingSound() {
      return "mob.zombie.say";
   }

   protected String getHurtSound() {
      return "mob.zombie.hurt";
   }

   protected String getDeathSound() {
      return "mob.zombie.death";
   }

   protected void func_145780_a(int var1, int var2, int var3, Block var4) {
      this.playSound("mob.zombie.step", 0.15F, 1.0F);
   }

   protected Item getDropItem() {
      return Items.rotten_flesh;
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   protected void dropRareDrop(int var1) {
      switch(super.rand.nextInt(3)) {
      case 0:
         this.dropItem(Items.iron_ingot, 1);
         break;
      case 1:
         this.dropItem(Items.carrot, 1);
         break;
      case 2:
         this.dropItem(Items.potato, 1);
      }

   }

   protected void addRandomArmor() {
      super.addRandomArmor();
      if(super.rand.nextFloat() < (super.worldObj.difficultySetting == EnumDifficulty.HARD?0.05F:0.01F)) {
         int var1 = super.rand.nextInt(3);
         if(var1 == 0) {
            this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
         } else {
            this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_shovel));
         }
      }

   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      if(this.isChild()) {
         var1.setBoolean("IsBaby", true);
      }

      if(this.isVillager()) {
         var1.setBoolean("IsVillager", true);
      }

      var1.setInteger("ConversionTime", this.isConverting()?this.conversionTime:-1);
      var1.setBoolean("CanBreakDoors", this.func_146072_bX());
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if(var1.getBoolean("IsBaby")) {
         this.setChild(true);
      }

      if(var1.getBoolean("IsVillager")) {
         this.setVillager(true);
      }

      if(var1.hasKey("ConversionTime", 99) && var1.getInteger("ConversionTime") > -1) {
         this.startConversion(var1.getInteger("ConversionTime"));
      }

      this.func_146070_a(var1.getBoolean("CanBreakDoors"));
   }

   public void onKillEntity(EntityLivingBase var1) {
      super.onKillEntity(var1);
      if((super.worldObj.difficultySetting == EnumDifficulty.NORMAL || super.worldObj.difficultySetting == EnumDifficulty.HARD) && var1 instanceof EntityVillager) {
         if(super.worldObj.difficultySetting != EnumDifficulty.HARD && super.rand.nextBoolean()) {
            return;
         }

         EntityZombie var2 = new EntityZombie(super.worldObj);
         var2.copyLocationAndAnglesFrom(var1);
         super.worldObj.removeEntity(var1);
         var2.onSpawnWithEgg((IEntityLivingData)null);
         var2.setVillager(true);
         if(var1.isChild()) {
            var2.setChild(true);
         }

         super.worldObj.spawnEntityInWorld(var2);
         super.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1016, (int)super.posX, (int)super.posY, (int)super.posZ, 0);
      }

   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData var1) {
      Object var6 = super.onSpawnWithEgg(var1);
      float var2 = super.worldObj.func_147462_b(super.posX, super.posY, super.posZ);
      this.setCanPickUpLoot(super.rand.nextFloat() < 0.55F * var2);
      if(var6 == null) {
         var6 = new EntityZombie$GroupData(this, super.worldObj.rand.nextFloat() < 0.05F, super.worldObj.rand.nextFloat() < 0.05F, (EntityZombie$1)null);
      }

      if(var6 instanceof EntityZombie$GroupData) {
         EntityZombie$GroupData var3 = (EntityZombie$GroupData)var6;
         if(var3.field_142046_b) {
            this.setVillager(true);
         }

         if(var3.field_142048_a) {
            this.setChild(true);
            if((double)super.worldObj.rand.nextFloat() < 0.05D) {
               List var4 = super.worldObj.selectEntitiesWithinAABB(EntityChicken.class, super.boundingBox.expand(5.0D, 3.0D, 5.0D), IEntitySelector.field_152785_b);
               if(!var4.isEmpty()) {
                  EntityChicken var5 = (EntityChicken)var4.get(0);
                  var5.func_152117_i(true);
                  this.mountEntity(var5);
               }
            } else if((double)super.worldObj.rand.nextFloat() < 0.05D) {
               EntityChicken var9 = new EntityChicken(super.worldObj);
               var9.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, 0.0F);
               var9.onSpawnWithEgg((IEntityLivingData)null);
               var9.func_152117_i(true);
               super.worldObj.spawnEntityInWorld(var9);
               this.mountEntity(var9);
            }
         }
      }

      this.func_146070_a(super.rand.nextFloat() < var2 * 0.1F);
      this.addRandomArmor();
      this.enchantEquipment();
      if(this.getEquipmentInSlot(4) == null) {
         Calendar var7 = super.worldObj.getCurrentDate();
         if(var7.get(2) + 1 == 10 && var7.get(5) == 31 && super.rand.nextFloat() < 0.25F) {
            this.setCurrentItemOrArmor(4, new ItemStack(super.rand.nextFloat() < 0.1F?Blocks.lit_pumpkin:Blocks.pumpkin));
            super.equipmentDropChances[4] = 0.0F;
         }
      }

      this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier("Random spawn bonus", super.rand.nextDouble() * 0.05000000074505806D, 0));
      double var8 = super.rand.nextDouble() * 1.5D * (double)super.worldObj.func_147462_b(super.posX, super.posY, super.posZ);
      if(var8 > 1.0D) {
         this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random zombie-spawn bonus", var8, 2));
      }

      if(super.rand.nextFloat() < var2 * 0.05F) {
         this.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Leader zombie bonus", super.rand.nextDouble() * 0.25D + 0.5D, 0));
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("Leader zombie bonus", super.rand.nextDouble() * 3.0D + 1.0D, 2));
         this.func_146070_a(true);
      }

      return (IEntityLivingData)var6;
   }

   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.getCurrentEquippedItem();
      if(var2 != null && var2.getItem() == Items.golden_apple && var2.getItemDamage() == 0 && this.isVillager() && this.isPotionActive(Potion.weakness)) {
         if(!var1.capabilities.isCreativeMode) {
            --var2.stackSize;
         }

         if(var2.stackSize <= 0) {
            var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
         }

         if(!super.worldObj.isRemote) {
            this.startConversion(super.rand.nextInt(2401) + 3600);
         }

         return true;
      } else {
         return false;
      }
   }

   protected void startConversion(int var1) {
      this.conversionTime = var1;
      this.getDataWatcher().updateObject(14, Byte.valueOf((byte)1));
      this.removePotionEffect(Potion.weakness.id);
      this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, var1, Math.min(super.worldObj.difficultySetting.getDifficultyId() - 1, 0)));
      super.worldObj.setEntityState(this, (byte)16);
   }

   public void handleHealthUpdate(byte var1) {
      if(var1 == 16) {
         super.worldObj.playSound(super.posX + 0.5D, super.posY + 0.5D, super.posZ + 0.5D, "mob.zombie.remedy", 1.0F + super.rand.nextFloat(), super.rand.nextFloat() * 0.7F + 0.3F, false);
      } else {
         super.handleHealthUpdate(var1);
      }

   }

   protected boolean canDespawn() {
      return !this.isConverting();
   }

   public boolean isConverting() {
      return this.getDataWatcher().getWatchableObjectByte(14) == 1;
   }

   protected void convertToVillager() {
      EntityVillager var1 = new EntityVillager(super.worldObj);
      var1.copyLocationAndAnglesFrom(this);
      var1.onSpawnWithEgg((IEntityLivingData)null);
      var1.setLookingForHome();
      if(this.isChild()) {
         var1.setGrowingAge(-24000);
      }

      super.worldObj.removeEntity(this);
      super.worldObj.spawnEntityInWorld(var1);
      var1.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
      super.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1017, (int)super.posX, (int)super.posY, (int)super.posZ, 0);
   }

   protected int getConversionTimeBoost() {
      int var1 = 1;
      if(super.rand.nextFloat() < 0.01F) {
         int var2 = 0;

         for(int var3 = (int)super.posX - 4; var3 < (int)super.posX + 4 && var2 < 14; ++var3) {
            for(int var4 = (int)super.posY - 4; var4 < (int)super.posY + 4 && var2 < 14; ++var4) {
               for(int var5 = (int)super.posZ - 4; var5 < (int)super.posZ + 4 && var2 < 14; ++var5) {
                  Block var6 = super.worldObj.getBlock(var3, var4, var5);
                  if(var6 == Blocks.iron_bars || var6 == Blocks.bed) {
                     if(super.rand.nextFloat() < 0.3F) {
                        ++var1;
                     }

                     ++var2;
                  }
               }
            }
         }
      }

      return var1;
   }

   public void func_146071_k(boolean var1) {
      this.func_146069_a(var1?0.5F:1.0F);
   }

   protected final void setSize(float var1, float var2) {
      boolean var3 = this.field_146074_bv > 0.0F && this.field_146073_bw > 0.0F;
      this.field_146074_bv = var1;
      this.field_146073_bw = var2;
      if(!var3) {
         this.func_146069_a(1.0F);
      }

   }

   protected final void func_146069_a(float var1) {
      super.setSize(this.field_146074_bv * var1, this.field_146073_bw * var1);
   }

}
