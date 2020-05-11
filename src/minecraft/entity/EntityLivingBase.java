package net.minecraft.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.Block$SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityLivingBase extends Entity {

   private static final UUID sprintingSpeedBoostModifierUUID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
   private static final AttributeModifier sprintingSpeedBoostModifier = (new AttributeModifier(sprintingSpeedBoostModifierUUID, "Sprinting speed boost", 0.30000001192092896D, 2)).setSaved(false);
   private BaseAttributeMap attributeMap;
   private final CombatTracker _combatTracker = new CombatTracker(this);
   private final HashMap activePotionsMap = new HashMap();
   private final ItemStack[] previousEquipment = new ItemStack[5];
   public boolean isSwingInProgress;
   public int swingProgressInt;
   public int arrowHitTimer;
   public float prevHealth;
   public int hurtTime;
   public int maxHurtTime;
   public float attackedAtYaw;
   public int deathTime;
   public int attackTime;
   public float prevSwingProgress;
   public float swingProgress;
   public float prevLimbSwingAmount;
   public float limbSwingAmount;
   public float limbSwing;
   public int maxHurtResistantTime = 20;
   public float prevCameraPitch;
   public float cameraPitch;
   public float field_70769_ao;
   public float field_70770_ap;
   public float renderYawOffset;
   public float prevRenderYawOffset;
   public float rotationYawHead;
   public float prevRotationYawHead;
   public float jumpMovementFactor = 0.02F;
   protected EntityPlayer attackingPlayer;
   protected int recentlyHit;
   protected boolean dead;
   protected int entityAge;
   protected float field_70768_au;
   protected float field_110154_aX;
   protected float field_70764_aw;
   protected float field_70763_ax;
   protected float field_70741_aB;
   protected int scoreValue;
   protected float lastDamage;
   protected boolean isJumping;
   public float moveStrafing;
   public float moveForward;
   protected float randomYawVelocity;
   protected int newPosRotationIncrements;
   protected double newPosX;
   protected double newPosY;
   protected double newPosZ;
   protected double newRotationYaw;
   protected double newRotationPitch;
   private boolean potionsNeedUpdate = true;
   private EntityLivingBase entityLivingToAttack;
   private int revengeTimer;
   private EntityLivingBase lastAttacker;
   private int lastAttackerTime;
   private float landMovementFactor;
   private int jumpTicks;
   private float field_110151_bq;


   public EntityLivingBase(World var1) {
      super(var1);
      this.applyEntityAttributes();
      this.setHealth(this.getMaxHealth());
      super.preventEntitySpawning = true;
      this.field_70770_ap = (float)(Math.random() + 1.0D) * 0.01F;
      this.setPosition(super.posX, super.posY, super.posZ);
      this.field_70769_ao = (float)Math.random() * 12398.0F;
      super.rotationYaw = (float)(Math.random() * 3.1415927410125732D * 2.0D);
      this.rotationYawHead = super.rotationYaw;
      super.stepHeight = 0.5F;
   }

   protected void entityInit() {
      super.dataWatcher.addObject(7, Integer.valueOf(0));
      super.dataWatcher.addObject(8, Byte.valueOf((byte)0));
      super.dataWatcher.addObject(9, Byte.valueOf((byte)0));
      super.dataWatcher.addObject(6, Float.valueOf(1.0F));
   }

   protected void applyEntityAttributes() {
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.maxHealth);
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.knockbackResistance);
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.movementSpeed);
      if(!this.isAIEnabled()) {
         this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.10000000149011612D);
      }

   }

   protected void updateFallState(double var1, boolean var3) {
      if(!this.isInWater()) {
         this.handleWaterMovement();
      }

      if(var3 && super.fallDistance > 0.0F) {
         int var4 = MathHelper.floor_double(super.posX);
         int var5 = MathHelper.floor_double(super.posY - 0.20000000298023224D - (double)super.yOffset);
         int var6 = MathHelper.floor_double(super.posZ);
         Block var7 = super.worldObj.getBlock(var4, var5, var6);
         if(var7.getMaterial() == Material.air) {
            int var8 = super.worldObj.getBlock(var4, var5 - 1, var6).getRenderType();
            if(var8 == 11 || var8 == 32 || var8 == 21) {
               var7 = super.worldObj.getBlock(var4, var5 - 1, var6);
            }
         } else if(!super.worldObj.isRemote && super.fallDistance > 3.0F) {
            super.worldObj.playAuxSFX(2006, var4, var5, var6, MathHelper.ceiling_float_int(super.fallDistance - 3.0F));
         }

         var7.onFallenUpon(super.worldObj, var4, var5, var6, this, super.fallDistance);
      }

      super.updateFallState(var1, var3);
   }

   public boolean canBreatheUnderwater() {
      return false;
   }

   public void onEntityUpdate() {
      this.prevSwingProgress = this.swingProgress;
      super.onEntityUpdate();
      super.worldObj.theProfiler.startSection("livingEntityBaseTick");
      if(this.isEntityAlive() && this.isEntityInsideOpaqueBlock()) {
         this.attackEntityFrom(DamageSource.inWall, 1.0F);
      }

      if(this.isImmuneToFire() || super.worldObj.isRemote) {
         this.extinguish();
      }

      boolean var1 = this instanceof EntityPlayer && ((EntityPlayer)this).capabilities.disableDamage;
      if(this.isEntityAlive() && this.isInsideOfMaterial(Material.water)) {
         if(!this.canBreatheUnderwater() && !this.isPotionActive(Potion.waterBreathing.id) && !var1) {
            this.setAir(this.decreaseAirSupply(this.getAir()));
            if(this.getAir() == -20) {
               this.setAir(0);

               for(int var2 = 0; var2 < 8; ++var2) {
                  float var3 = super.rand.nextFloat() - super.rand.nextFloat();
                  float var4 = super.rand.nextFloat() - super.rand.nextFloat();
                  float var5 = super.rand.nextFloat() - super.rand.nextFloat();
                  super.worldObj.spawnParticle("bubble", super.posX + (double)var3, super.posY + (double)var4, super.posZ + (double)var5, super.motionX, super.motionY, super.motionZ);
               }

               this.attackEntityFrom(DamageSource.drown, 2.0F);
            }
         }

         if(!super.worldObj.isRemote && this.isRiding() && super.ridingEntity instanceof EntityLivingBase) {
            this.mountEntity((Entity)null);
         }
      } else {
         this.setAir(300);
      }

      if(this.isEntityAlive() && this.isWet()) {
         this.extinguish();
      }

      this.prevCameraPitch = this.cameraPitch;
      if(this.attackTime > 0) {
         --this.attackTime;
      }

      if(this.hurtTime > 0) {
         --this.hurtTime;
      }

      if(super.hurtResistantTime > 0 && !(this instanceof EntityPlayerMP)) {
         --super.hurtResistantTime;
      }

      if(this.getHealth() <= 0.0F) {
         this.onDeathUpdate();
      }

      if(this.recentlyHit > 0) {
         --this.recentlyHit;
      } else {
         this.attackingPlayer = null;
      }

      if(this.lastAttacker != null && !this.lastAttacker.isEntityAlive()) {
         this.lastAttacker = null;
      }

      if(this.entityLivingToAttack != null) {
         if(!this.entityLivingToAttack.isEntityAlive()) {
            this.setRevengeTarget((EntityLivingBase)null);
         } else if(super.ticksExisted - this.revengeTimer > 100) {
            this.setRevengeTarget((EntityLivingBase)null);
         }
      }

      this.updatePotionEffects();
      this.field_70763_ax = this.field_70764_aw;
      this.prevRenderYawOffset = this.renderYawOffset;
      this.prevRotationYawHead = this.rotationYawHead;
      super.prevRotationYaw = super.rotationYaw;
      super.prevRotationPitch = super.rotationPitch;
      super.worldObj.theProfiler.endSection();
   }

   public boolean isChild() {
      return false;
   }

   protected void onDeathUpdate() {
      ++this.deathTime;
      if(this.deathTime == 20) {
         int var1;
         if(!super.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && this.func_146066_aG() && super.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) {
            var1 = this.getExperiencePoints(this.attackingPlayer);

            while(var1 > 0) {
               int var2 = EntityXPOrb.getXPSplit(var1);
               var1 -= var2;
               super.worldObj.spawnEntityInWorld(new EntityXPOrb(super.worldObj, super.posX, super.posY, super.posZ, var2));
            }
         }

         this.setDead();

         for(var1 = 0; var1 < 20; ++var1) {
            double var8 = super.rand.nextGaussian() * 0.02D;
            double var4 = super.rand.nextGaussian() * 0.02D;
            double var6 = super.rand.nextGaussian() * 0.02D;
            super.worldObj.spawnParticle("explode", super.posX + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, super.posY + (double)(super.rand.nextFloat() * super.height), super.posZ + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, var8, var4, var6);
         }
      }

   }

   protected boolean func_146066_aG() {
      return !this.isChild();
   }

   protected int decreaseAirSupply(int var1) {
      int var2 = EnchantmentHelper.getRespiration(this);
      return var2 > 0 && super.rand.nextInt(var2 + 1) > 0?var1:var1 - 1;
   }

   protected int getExperiencePoints(EntityPlayer var1) {
      return 0;
   }

   protected boolean isPlayer() {
      return false;
   }

   public Random getRNG() {
      return super.rand;
   }

   public EntityLivingBase getAITarget() {
      return this.entityLivingToAttack;
   }

   public int func_142015_aE() {
      return this.revengeTimer;
   }

   public void setRevengeTarget(EntityLivingBase var1) {
      this.entityLivingToAttack = var1;
      this.revengeTimer = super.ticksExisted;
   }

   public EntityLivingBase getLastAttacker() {
      return this.lastAttacker;
   }

   public int getLastAttackerTime() {
      return this.lastAttackerTime;
   }

   public void setLastAttacker(Entity var1) {
      if(var1 instanceof EntityLivingBase) {
         this.lastAttacker = (EntityLivingBase)var1;
      } else {
         this.lastAttacker = null;
      }

      this.lastAttackerTime = super.ticksExisted;
   }

   public int getAge() {
      return this.entityAge;
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setFloat("HealF", this.getHealth());
      var1.setShort("Health", (short)((int)Math.ceil((double)this.getHealth())));
      var1.setShort("HurtTime", (short)this.hurtTime);
      var1.setShort("DeathTime", (short)this.deathTime);
      var1.setShort("AttackTime", (short)this.attackTime);
      var1.setFloat("AbsorptionAmount", this.getAbsorptionAmount());
      ItemStack[] var2 = this.getLastActiveItems();
      int var3 = var2.length;

      int var4;
      ItemStack var5;
      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         if(var5 != null) {
            this.attributeMap.removeAttributeModifiers(var5.getAttributeModifiers());
         }
      }

      var1.setTag("Attributes", SharedMonsterAttributes.writeBaseAttributeMapToNBT(this.getAttributeMap()));
      var2 = this.getLastActiveItems();
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         if(var5 != null) {
            this.attributeMap.applyAttributeModifiers(var5.getAttributeModifiers());
         }
      }

      if(!this.activePotionsMap.isEmpty()) {
         NBTTagList var6 = new NBTTagList();
         Iterator var7 = this.activePotionsMap.values().iterator();

         while(var7.hasNext()) {
            PotionEffect var8 = (PotionEffect)var7.next();
            var6.appendTag(var8.writeCustomPotionEffectToNBT(new NBTTagCompound()));
         }

         var1.setTag("ActiveEffects", var6);
      }

   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      this.setAbsorptionAmount(var1.getFloat("AbsorptionAmount"));
      if(var1.hasKey("Attributes", 9) && super.worldObj != null && !super.worldObj.isRemote) {
         SharedMonsterAttributes.func_151475_a(this.getAttributeMap(), var1.getTagList("Attributes", 10));
      }

      if(var1.hasKey("ActiveEffects", 9)) {
         NBTTagList var2 = var1.getTagList("ActiveEffects", 10);

         for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
            NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            PotionEffect var5 = PotionEffect.readCustomPotionEffectFromNBT(var4);
            if(var5 != null) {
               this.activePotionsMap.put(Integer.valueOf(var5.getPotionID()), var5);
            }
         }
      }

      if(var1.hasKey("HealF", 99)) {
         this.setHealth(var1.getFloat("HealF"));
      } else {
         NBTBase var6 = var1.getTag("Health");
         if(var6 == null) {
            this.setHealth(this.getMaxHealth());
         } else if(var6.getId() == 5) {
            this.setHealth(((NBTTagFloat)var6).func_150288_h());
         } else if(var6.getId() == 2) {
            this.setHealth((float)((NBTTagShort)var6).func_150289_e());
         }
      }

      this.hurtTime = var1.getShort("HurtTime");
      this.deathTime = var1.getShort("DeathTime");
      this.attackTime = var1.getShort("AttackTime");
   }

   protected void updatePotionEffects() {
      Iterator var1 = this.activePotionsMap.keySet().iterator();

      while(var1.hasNext()) {
         Integer var2 = (Integer)var1.next();
         PotionEffect var3 = (PotionEffect)this.activePotionsMap.get(var2);
         if(!var3.onUpdate(this)) {
            if(!super.worldObj.isRemote) {
               var1.remove();
               this.onFinishedPotionEffect(var3);
            }
         } else if(var3.getDuration() % 600 == 0) {
            this.onChangedPotionEffect(var3, false);
         }
      }

      int var11;
      if(this.potionsNeedUpdate) {
         if(!super.worldObj.isRemote) {
            if(this.activePotionsMap.isEmpty()) {
               super.dataWatcher.updateObject(8, Byte.valueOf((byte)0));
               super.dataWatcher.updateObject(7, Integer.valueOf(0));
               this.setInvisible(false);
            } else {
               var11 = PotionHelper.calcPotionLiquidColor(this.activePotionsMap.values());
               super.dataWatcher.updateObject(8, Byte.valueOf((byte)(PotionHelper.func_82817_b(this.activePotionsMap.values())?1:0)));
               super.dataWatcher.updateObject(7, Integer.valueOf(var11));
               this.setInvisible(this.isPotionActive(Potion.invisibility.id));
            }
         }

         this.potionsNeedUpdate = false;
      }

      var11 = super.dataWatcher.getWatchableObjectInt(7);
      boolean var12 = super.dataWatcher.getWatchableObjectByte(8) > 0;
      if(var11 > 0) {
         boolean var4 = false;
         if(!this.isInvisible()) {
            var4 = super.rand.nextBoolean();
         } else {
            var4 = super.rand.nextInt(15) == 0;
         }

         if(var12) {
            var4 &= super.rand.nextInt(5) == 0;
         }

         if(var4 && var11 > 0) {
            double var5 = (double)(var11 >> 16 & 255) / 255.0D;
            double var7 = (double)(var11 >> 8 & 255) / 255.0D;
            double var9 = (double)(var11 >> 0 & 255) / 255.0D;
            super.worldObj.spawnParticle(var12?"mobSpellAmbient":"mobSpell", super.posX + (super.rand.nextDouble() - 0.5D) * (double)super.width, super.posY + super.rand.nextDouble() * (double)super.height - (double)super.yOffset, super.posZ + (super.rand.nextDouble() - 0.5D) * (double)super.width, var5, var7, var9);
         }
      }

   }

   public void clearActivePotions() {
      Iterator var1 = this.activePotionsMap.keySet().iterator();

      while(var1.hasNext()) {
         Integer var2 = (Integer)var1.next();
         PotionEffect var3 = (PotionEffect)this.activePotionsMap.get(var2);
         if(!super.worldObj.isRemote) {
            var1.remove();
            this.onFinishedPotionEffect(var3);
         }
      }

   }

   public Collection getActivePotionEffects() {
      return this.activePotionsMap.values();
   }

   public boolean isPotionActive(int var1) {
      return this.activePotionsMap.containsKey(Integer.valueOf(var1));
   }

   public boolean isPotionActive(Potion var1) {
      return this.activePotionsMap.containsKey(Integer.valueOf(var1.id));
   }

   public PotionEffect getActivePotionEffect(Potion var1) {
      return (PotionEffect)this.activePotionsMap.get(Integer.valueOf(var1.id));
   }

   public void addPotionEffect(PotionEffect var1) {
      if(this.isPotionApplicable(var1)) {
         if(this.activePotionsMap.containsKey(Integer.valueOf(var1.getPotionID()))) {
            ((PotionEffect)this.activePotionsMap.get(Integer.valueOf(var1.getPotionID()))).combine(var1);
            this.onChangedPotionEffect((PotionEffect)this.activePotionsMap.get(Integer.valueOf(var1.getPotionID())), true);
         } else {
            this.activePotionsMap.put(Integer.valueOf(var1.getPotionID()), var1);
            this.onNewPotionEffect(var1);
         }

      }
   }

   public boolean isPotionApplicable(PotionEffect var1) {
      if(this.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
         int var2 = var1.getPotionID();
         if(var2 == Potion.regeneration.id || var2 == Potion.poison.id) {
            return false;
         }
      }

      return true;
   }

   public boolean isEntityUndead() {
      return this.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD;
   }

   public void removePotionEffectClient(int var1) {
      this.activePotionsMap.remove(Integer.valueOf(var1));
   }

   public void removePotionEffect(int var1) {
      PotionEffect var2 = (PotionEffect)this.activePotionsMap.remove(Integer.valueOf(var1));
      if(var2 != null) {
         this.onFinishedPotionEffect(var2);
      }

   }

   protected void onNewPotionEffect(PotionEffect var1) {
      this.potionsNeedUpdate = true;
      if(!super.worldObj.isRemote) {
         Potion.potionTypes[var1.getPotionID()].applyAttributesModifiersToEntity(this, this.getAttributeMap(), var1.getAmplifier());
      }

   }

   protected void onChangedPotionEffect(PotionEffect var1, boolean var2) {
      this.potionsNeedUpdate = true;
      if(var2 && !super.worldObj.isRemote) {
         Potion.potionTypes[var1.getPotionID()].removeAttributesModifiersFromEntity(this, this.getAttributeMap(), var1.getAmplifier());
         Potion.potionTypes[var1.getPotionID()].applyAttributesModifiersToEntity(this, this.getAttributeMap(), var1.getAmplifier());
      }

   }

   protected void onFinishedPotionEffect(PotionEffect var1) {
      this.potionsNeedUpdate = true;
      if(!super.worldObj.isRemote) {
         Potion.potionTypes[var1.getPotionID()].removeAttributesModifiersFromEntity(this, this.getAttributeMap(), var1.getAmplifier());
      }

   }

   public void heal(float var1) {
      float var2 = this.getHealth();
      if(var2 > 0.0F) {
         this.setHealth(var2 + var1);
      }

   }

   public final float getHealth() {
      return super.dataWatcher.getWatchableObjectFloat(6);
   }

   public void setHealth(float var1) {
      super.dataWatcher.updateObject(6, Float.valueOf(MathHelper.clamp_float(var1, 0.0F, this.getMaxHealth())));
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else if(super.worldObj.isRemote) {
         return false;
      } else {
         this.entityAge = 0;
         if(this.getHealth() <= 0.0F) {
            return false;
         } else if(var1.isFireDamage() && this.isPotionActive(Potion.fireResistance)) {
            return false;
         } else {
            if((var1 == DamageSource.anvil || var1 == DamageSource.fallingBlock) && this.getEquipmentInSlot(4) != null) {
               this.getEquipmentInSlot(4).damageItem((int)(var2 * 4.0F + super.rand.nextFloat() * var2 * 2.0F), this);
               var2 *= 0.75F;
            }

            this.limbSwingAmount = 1.5F;
            boolean var3 = true;
            if((float)super.hurtResistantTime > (float)this.maxHurtResistantTime / 2.0F) {
               if(var2 <= this.lastDamage) {
                  return false;
               }

               this.damageEntity(var1, var2 - this.lastDamage);
               this.lastDamage = var2;
               var3 = false;
            } else {
               this.lastDamage = var2;
               this.prevHealth = this.getHealth();
               super.hurtResistantTime = this.maxHurtResistantTime;
               this.damageEntity(var1, var2);
               this.hurtTime = this.maxHurtTime = 10;
            }

            this.attackedAtYaw = 0.0F;
            Entity var4 = var1.getEntity();
            if(var4 != null) {
               if(var4 instanceof EntityLivingBase) {
                  this.setRevengeTarget((EntityLivingBase)var4);
               }

               if(var4 instanceof EntityPlayer) {
                  this.recentlyHit = 100;
                  this.attackingPlayer = (EntityPlayer)var4;
               } else if(var4 instanceof EntityWolf) {
                  EntityWolf var5 = (EntityWolf)var4;
                  if(var5.isTamed()) {
                     this.recentlyHit = 100;
                     this.attackingPlayer = null;
                  }
               }
            }

            if(var3) {
               super.worldObj.setEntityState(this, (byte)2);
               if(var1 != DamageSource.drown) {
                  this.setBeenAttacked();
               }

               if(var4 != null) {
                  double var9 = var4.posX - super.posX;

                  double var7;
                  for(var7 = var4.posZ - super.posZ; var9 * var9 + var7 * var7 < 1.0E-4D; var7 = (Math.random() - Math.random()) * 0.01D) {
                     var9 = (Math.random() - Math.random()) * 0.01D;
                  }

                  this.attackedAtYaw = (float)(Math.atan2(var7, var9) * 180.0D / 3.1415927410125732D) - super.rotationYaw;
                  this.knockBack(var4, var2, var9, var7);
               } else {
                  this.attackedAtYaw = (float)((int)(Math.random() * 2.0D) * 180);
               }
            }

            String var10;
            if(this.getHealth() <= 0.0F) {
               var10 = this.getDeathSound();
               if(var3 && var10 != null) {
                  this.playSound(var10, this.getSoundVolume(), this.getSoundPitch());
               }

               this.onDeath(var1);
            } else {
               var10 = this.getHurtSound();
               if(var3 && var10 != null) {
                  this.playSound(var10, this.getSoundVolume(), this.getSoundPitch());
               }
            }

            return true;
         }
      }
   }

   public void renderBrokenItemStack(ItemStack var1) {
      this.playSound("random.break", 0.8F, 0.8F + super.worldObj.rand.nextFloat() * 0.4F);

      for(int var2 = 0; var2 < 5; ++var2) {
         Vec3 var3 = Vec3.createVectorHelper(((double)super.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
         var3.rotateAroundX(-super.rotationPitch * 3.1415927F / 180.0F);
         var3.rotateAroundY(-super.rotationYaw * 3.1415927F / 180.0F);
         Vec3 var4 = Vec3.createVectorHelper(((double)super.rand.nextFloat() - 0.5D) * 0.3D, (double)(-super.rand.nextFloat()) * 0.6D - 0.3D, 0.6D);
         var4.rotateAroundX(-super.rotationPitch * 3.1415927F / 180.0F);
         var4.rotateAroundY(-super.rotationYaw * 3.1415927F / 180.0F);
         var4 = var4.addVector(super.posX, super.posY + (double)this.getEyeHeight(), super.posZ);
         super.worldObj.spawnParticle("iconcrack_" + Item.getIdFromItem(var1.getItem()), var4.xCoord, var4.yCoord, var4.zCoord, var3.xCoord, var3.yCoord + 0.05D, var3.zCoord);
      }

   }

   public void onDeath(DamageSource var1) {
      Entity var2 = var1.getEntity();
      EntityLivingBase var3 = this.func_94060_bK();
      if(this.scoreValue >= 0 && var3 != null) {
         var3.addToPlayerScore(this, this.scoreValue);
      }

      if(var2 != null) {
         var2.onKillEntity(this);
      }

      this.dead = true;
      this.func_110142_aN().func_94549_h();
      if(!super.worldObj.isRemote) {
         int var4 = 0;
         if(var2 instanceof EntityPlayer) {
            var4 = EnchantmentHelper.getLootingModifier((EntityLivingBase)var2);
         }

         if(this.func_146066_aG() && super.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) {
            this.dropFewItems(this.recentlyHit > 0, var4);
            this.dropEquipment(this.recentlyHit > 0, var4);
            if(this.recentlyHit > 0) {
               int var5 = super.rand.nextInt(200) - var4;
               if(var5 < 5) {
                  this.dropRareDrop(var5 <= 0?1:0);
               }
            }
         }
      }

      super.worldObj.setEntityState(this, (byte)3);
   }

   protected void dropEquipment(boolean var1, int var2) {}

   public void knockBack(Entity var1, float var2, double var3, double var5) {
      if(super.rand.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()) {
         super.isAirBorne = true;
         float var7 = MathHelper.sqrt_double(var3 * var3 + var5 * var5);
         float var8 = 0.4F;
         super.motionX /= 2.0D;
         super.motionY /= 2.0D;
         super.motionZ /= 2.0D;
         super.motionX -= var3 / (double)var7 * (double)var8;
         super.motionY += (double)var8;
         super.motionZ -= var5 / (double)var7 * (double)var8;
         if(super.motionY > 0.4000000059604645D) {
            super.motionY = 0.4000000059604645D;
         }

      }
   }

   protected String getHurtSound() {
      return "game.neutral.hurt";
   }

   protected String getDeathSound() {
      return "game.neutral.die";
   }

   protected void dropRareDrop(int var1) {}

   protected void dropFewItems(boolean var1, int var2) {}

   public boolean isOnLadder() {
      int var1 = MathHelper.floor_double(super.posX);
      int var2 = MathHelper.floor_double(super.boundingBox.minY);
      int var3 = MathHelper.floor_double(super.posZ);
      Block var4 = super.worldObj.getBlock(var1, var2, var3);
      return var4 == Blocks.ladder || var4 == Blocks.vine;
   }

   public boolean isEntityAlive() {
      return !super.isDead && this.getHealth() > 0.0F;
   }

   protected void fall(float var1) {
      super.fall(var1);
      PotionEffect var2 = this.getActivePotionEffect(Potion.jump);
      float var3 = var2 != null?(float)(var2.getAmplifier() + 1):0.0F;
      int var4 = MathHelper.ceiling_float_int(var1 - 3.0F - var3);
      if(var4 > 0) {
         this.playSound(this.func_146067_o(var4), 1.0F, 1.0F);
         this.attackEntityFrom(DamageSource.fall, (float)var4);
         int var5 = MathHelper.floor_double(super.posX);
         int var6 = MathHelper.floor_double(super.posY - 0.20000000298023224D - (double)super.yOffset);
         int var7 = MathHelper.floor_double(super.posZ);
         Block var8 = super.worldObj.getBlock(var5, var6, var7);
         if(var8.getMaterial() != Material.air) {
            Block$SoundType var9 = var8.stepSound;
            this.playSound(var9.getStepResourcePath(), var9.getVolume() * 0.5F, var9.getPitch() * 0.75F);
         }
      }

   }

   protected String func_146067_o(int var1) {
      return var1 > 4?"game.neutral.hurt.fall.big":"game.neutral.hurt.fall.small";
   }

   public void performHurtAnimation() {
      this.hurtTime = this.maxHurtTime = 10;
      this.attackedAtYaw = 0.0F;
   }

   public int getTotalArmorValue() {
      int var1 = 0;
      ItemStack[] var2 = this.getLastActiveItems();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ItemStack var5 = var2[var4];
         if(var5 != null && var5.getItem() instanceof ItemArmor) {
            int var6 = ((ItemArmor)var5.getItem()).damageReduceAmount;
            var1 += var6;
         }
      }

      return var1;
   }

   protected void damageArmor(float var1) {}

   protected float applyArmorCalculations(DamageSource var1, float var2) {
      if(!var1.isUnblockable()) {
         int var3 = 25 - this.getTotalArmorValue();
         float var4 = var2 * (float)var3;
         this.damageArmor(var2);
         var2 = var4 / 25.0F;
      }

      return var2;
   }

   protected float applyPotionDamageCalculations(DamageSource var1, float var2) {
      if(var1.isDamageAbsolute()) {
         return var2;
      } else {
         if(this instanceof EntityZombie) {
            var2 = var2;
         }

         int var3;
         int var4;
         float var5;
         if(this.isPotionActive(Potion.resistance) && var1 != DamageSource.outOfWorld) {
            var3 = (this.getActivePotionEffect(Potion.resistance).getAmplifier() + 1) * 5;
            var4 = 25 - var3;
            var5 = var2 * (float)var4;
            var2 = var5 / 25.0F;
         }

         if(var2 <= 0.0F) {
            return 0.0F;
         } else {
            var3 = EnchantmentHelper.getEnchantmentModifierDamage(this.getLastActiveItems(), var1);
            if(var3 > 20) {
               var3 = 20;
            }

            if(var3 > 0 && var3 <= 20) {
               var4 = 25 - var3;
               var5 = var2 * (float)var4;
               var2 = var5 / 25.0F;
            }

            return var2;
         }
      }
   }

   protected void damageEntity(DamageSource var1, float var2) {
      if(!this.isEntityInvulnerable()) {
         var2 = this.applyArmorCalculations(var1, var2);
         var2 = this.applyPotionDamageCalculations(var1, var2);
         float var3 = var2;
         var2 = Math.max(var2 - this.getAbsorptionAmount(), 0.0F);
         this.setAbsorptionAmount(this.getAbsorptionAmount() - (var3 - var2));
         if(var2 != 0.0F) {
            float var4 = this.getHealth();
            this.setHealth(var4 - var2);
            this.func_110142_aN().func_94547_a(var1, var4, var2);
            this.setAbsorptionAmount(this.getAbsorptionAmount() - var2);
         }
      }
   }

   public CombatTracker func_110142_aN() {
      return this._combatTracker;
   }

   public EntityLivingBase func_94060_bK() {
      return (EntityLivingBase)(this._combatTracker.func_94550_c() != null?this._combatTracker.func_94550_c():(this.attackingPlayer != null?this.attackingPlayer:(this.entityLivingToAttack != null?this.entityLivingToAttack:null)));
   }

   public final float getMaxHealth() {
      return (float)this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
   }

   public final int getArrowCountInEntity() {
      return super.dataWatcher.getWatchableObjectByte(9);
   }

   public final void setArrowCountInEntity(int var1) {
      super.dataWatcher.updateObject(9, Byte.valueOf((byte)var1));
   }

   private int getArmSwingAnimationEnd() {
      return this.isPotionActive(Potion.digSpeed)?6 - (1 + this.getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1:(this.isPotionActive(Potion.digSlowdown)?6 + (1 + this.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2:6);
   }

   public void swingItem() {
      if(!this.isSwingInProgress || this.swingProgressInt >= this.getArmSwingAnimationEnd() / 2 || this.swingProgressInt < 0) {
         this.swingProgressInt = -1;
         this.isSwingInProgress = true;
         if(super.worldObj instanceof WorldServer) {
            ((WorldServer)super.worldObj).getEntityTracker().func_151247_a(this, new S0BPacketAnimation(this, 0));
         }
      }

   }

   public void handleHealthUpdate(byte var1) {
      if(var1 == 2) {
         this.limbSwingAmount = 1.5F;
         super.hurtResistantTime = this.maxHurtResistantTime;
         this.hurtTime = this.maxHurtTime = 10;
         this.attackedAtYaw = 0.0F;
         this.playSound(this.getHurtSound(), this.getSoundVolume(), (super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F);
         this.attackEntityFrom(DamageSource.generic, 0.0F);
      } else if(var1 == 3) {
         this.playSound(this.getDeathSound(), this.getSoundVolume(), (super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F);
         this.setHealth(0.0F);
         this.onDeath(DamageSource.generic);
      } else {
         super.handleHealthUpdate(var1);
      }

   }

   protected void kill() {
      this.attackEntityFrom(DamageSource.outOfWorld, 4.0F);
   }

   protected void updateArmSwingProgress() {
      int var1 = this.getArmSwingAnimationEnd();
      if(this.isSwingInProgress) {
         ++this.swingProgressInt;
         if(this.swingProgressInt >= var1) {
            this.swingProgressInt = 0;
            this.isSwingInProgress = false;
         }
      } else {
         this.swingProgressInt = 0;
      }

      this.swingProgress = (float)this.swingProgressInt / (float)var1;
   }

   public IAttributeInstance getEntityAttribute(IAttribute var1) {
      return this.getAttributeMap().getAttributeInstance(var1);
   }

   public BaseAttributeMap getAttributeMap() {
      if(this.attributeMap == null) {
         this.attributeMap = new ServersideAttributeMap();
      }

      return this.attributeMap;
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEFINED;
   }

   public abstract ItemStack getHeldItem();

   public abstract ItemStack getEquipmentInSlot(int var1);

   public abstract void setCurrentItemOrArmor(int var1, ItemStack var2);

   public void setSprinting(boolean var1) {
      super.setSprinting(var1);
      IAttributeInstance var2 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
      if(var2.getModifier(sprintingSpeedBoostModifierUUID) != null) {
         var2.removeModifier(sprintingSpeedBoostModifier);
      }

      if(var1) {
         var2.applyModifier(sprintingSpeedBoostModifier);
      }

   }

   public abstract ItemStack[] getLastActiveItems();

   protected float getSoundVolume() {
      return 1.0F;
   }

   protected float getSoundPitch() {
      return this.isChild()?(super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.5F:(super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F;
   }

   protected boolean isMovementBlocked() {
      return this.getHealth() <= 0.0F;
   }

   public void setPositionAndUpdate(double var1, double var3, double var5) {
      this.setLocationAndAngles(var1, var3, var5, super.rotationYaw, super.rotationPitch);
   }

   public void dismountEntity(Entity var1) {
      double var3 = var1.posX;
      double var5 = var1.boundingBox.minY + (double)var1.height;
      double var7 = var1.posZ;
      byte var9 = 1;

      for(int var10 = -var9; var10 <= var9; ++var10) {
         for(int var11 = -var9; var11 < var9; ++var11) {
            if(var10 != 0 || var11 != 0) {
               int var12 = (int)(super.posX + (double)var10);
               int var13 = (int)(super.posZ + (double)var11);
               AxisAlignedBB var2 = super.boundingBox.getOffsetBoundingBox((double)var10, 1.0D, (double)var11);
               if(super.worldObj.func_147461_a(var2).isEmpty()) {
                  if(World.doesBlockHaveSolidTopSurface(super.worldObj, var12, (int)super.posY, var13)) {
                     this.setPositionAndUpdate(super.posX + (double)var10, super.posY + 1.0D, super.posZ + (double)var11);
                     return;
                  }

                  if(World.doesBlockHaveSolidTopSurface(super.worldObj, var12, (int)super.posY - 1, var13) || super.worldObj.getBlock(var12, (int)super.posY - 1, var13).getMaterial() == Material.water) {
                     var3 = super.posX + (double)var10;
                     var5 = super.posY + 1.0D;
                     var7 = super.posZ + (double)var11;
                  }
               }
            }
         }
      }

      this.setPositionAndUpdate(var3, var5, var7);
   }

   public boolean getAlwaysRenderNameTagForRender() {
      return false;
   }

   public IIcon getItemIcon(ItemStack var1, int var2) {
      return var1.getItem().requiresMultipleRenderPasses()?var1.getItem().getIconFromDamageForRenderPass(var1.getItemDamage(), var2):var1.getIconIndex();
   }

   protected void jump() {
      super.motionY = 0.41999998688697815D;
      if(this.isPotionActive(Potion.jump)) {
         super.motionY += (double)((float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
      }

      if(this.isSprinting()) {
         float var1 = super.rotationYaw * 0.017453292F;
         super.motionX -= (double)(MathHelper.sin(var1) * 0.2F);
         super.motionZ += (double)(MathHelper.cos(var1) * 0.2F);
      }

      super.isAirBorne = true;
   }

   public void moveEntityWithHeading(float var1, float var2) {
      double var8;
      if(this.isInWater() && (!(this instanceof EntityPlayer) || !((EntityPlayer)this).capabilities.isFlying)) {
         var8 = super.posY;
         this.moveFlying(var1, var2, this.isAIEnabled()?0.04F:0.02F);
         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         super.motionX *= 0.800000011920929D;
         super.motionY *= 0.800000011920929D;
         super.motionZ *= 0.800000011920929D;
         super.motionY -= 0.02D;
         if(super.isCollidedHorizontally && this.isOffsetPositionInLiquid(super.motionX, super.motionY + 0.6000000238418579D - super.posY + var8, super.motionZ)) {
            super.motionY = 0.30000001192092896D;
         }
      } else if(this.handleLavaMovement() && (!(this instanceof EntityPlayer) || !((EntityPlayer)this).capabilities.isFlying)) {
         var8 = super.posY;
         this.moveFlying(var1, var2, 0.02F);
         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         super.motionX *= 0.5D;
         super.motionY *= 0.5D;
         super.motionZ *= 0.5D;
         super.motionY -= 0.02D;
         if(super.isCollidedHorizontally && this.isOffsetPositionInLiquid(super.motionX, super.motionY + 0.6000000238418579D - super.posY + var8, super.motionZ)) {
            super.motionY = 0.30000001192092896D;
         }
      } else {
         float var3 = 0.91F;
         if(super.onGround) {
            var3 = super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.boundingBox.minY) - 1, MathHelper.floor_double(super.posZ)).slipperiness * 0.91F;
         }

         float var4 = 0.16277136F / (var3 * var3 * var3);
         float var5;
         if(super.onGround) {
            var5 = this.getAIMoveSpeed() * var4;
         } else {
            var5 = this.jumpMovementFactor;
         }

         this.moveFlying(var1, var2, var5);
         var3 = 0.91F;
         if(super.onGround) {
            var3 = super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.boundingBox.minY) - 1, MathHelper.floor_double(super.posZ)).slipperiness * 0.91F;
         }

         if(this.isOnLadder()) {
            float var6 = 0.15F;
            if(super.motionX < (double)(-var6)) {
               super.motionX = (double)(-var6);
            }

            if(super.motionX > (double)var6) {
               super.motionX = (double)var6;
            }

            if(super.motionZ < (double)(-var6)) {
               super.motionZ = (double)(-var6);
            }

            if(super.motionZ > (double)var6) {
               super.motionZ = (double)var6;
            }

            super.fallDistance = 0.0F;
            if(super.motionY < -0.15D) {
               super.motionY = -0.15D;
            }

            boolean var7 = this.isSneaking() && this instanceof EntityPlayer;
            if(var7 && super.motionY < 0.0D) {
               super.motionY = 0.0D;
            }
         }

         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         if(super.isCollidedHorizontally && this.isOnLadder()) {
            super.motionY = 0.2D;
         }

         if(super.worldObj.isRemote && (!super.worldObj.blockExists((int)super.posX, 0, (int)super.posZ) || !super.worldObj.getChunkFromBlockCoords((int)super.posX, (int)super.posZ).isChunkLoaded)) {
            if(super.posY > 0.0D) {
               super.motionY = -0.1D;
            } else {
               super.motionY = 0.0D;
            }
         } else {
            super.motionY -= 0.08D;
         }

         super.motionY *= 0.9800000190734863D;
         super.motionX *= (double)var3;
         super.motionZ *= (double)var3;
      }

      this.prevLimbSwingAmount = this.limbSwingAmount;
      var8 = super.posX - super.prevPosX;
      double var9 = super.posZ - super.prevPosZ;
      float var10 = MathHelper.sqrt_double(var8 * var8 + var9 * var9) * 4.0F;
      if(var10 > 1.0F) {
         var10 = 1.0F;
      }

      this.limbSwingAmount += (var10 - this.limbSwingAmount) * 0.4F;
      this.limbSwing += this.limbSwingAmount;
   }

   protected boolean isAIEnabled() {
      return false;
   }

   public float getAIMoveSpeed() {
      return this.isAIEnabled()?this.landMovementFactor:0.1F;
   }

   public void setAIMoveSpeed(float var1) {
      this.landMovementFactor = var1;
   }

   public boolean attackEntityAsMob(Entity var1) {
      this.setLastAttacker(var1);
      return false;
   }

   public boolean isPlayerSleeping() {
      return false;
   }

   public void onUpdate() {
      super.onUpdate();
      if(!super.worldObj.isRemote) {
         int var1 = this.getArrowCountInEntity();
         if(var1 > 0) {
            if(this.arrowHitTimer <= 0) {
               this.arrowHitTimer = 20 * (30 - var1);
            }

            --this.arrowHitTimer;
            if(this.arrowHitTimer <= 0) {
               this.setArrowCountInEntity(var1 - 1);
            }
         }

         for(int var2 = 0; var2 < 5; ++var2) {
            ItemStack var3 = this.previousEquipment[var2];
            ItemStack var4 = this.getEquipmentInSlot(var2);
            if(!ItemStack.areItemStacksEqual(var4, var3)) {
               ((WorldServer)super.worldObj).getEntityTracker().func_151247_a(this, new S04PacketEntityEquipment(this.getEntityId(), var2, var4));
               if(var3 != null) {
                  this.attributeMap.removeAttributeModifiers(var3.getAttributeModifiers());
               }

               if(var4 != null) {
                  this.attributeMap.applyAttributeModifiers(var4.getAttributeModifiers());
               }

               this.previousEquipment[var2] = var4 == null?null:var4.copy();
            }
         }

         if(super.ticksExisted % 20 == 0) {
            this.func_110142_aN().func_94549_h();
         }
      }

      this.onLivingUpdate();
      double var9 = super.posX - super.prevPosX;
      double var10 = super.posZ - super.prevPosZ;
      float var5 = (float)(var9 * var9 + var10 * var10);
      float var6 = this.renderYawOffset;
      float var7 = 0.0F;
      this.field_70768_au = this.field_110154_aX;
      float var8 = 0.0F;
      if(var5 > 0.0025000002F) {
         var8 = 1.0F;
         var7 = (float)Math.sqrt((double)var5) * 3.0F;
         var6 = (float)Math.atan2(var10, var9) * 180.0F / 3.1415927F - 90.0F;
      }

      if(this.swingProgress > 0.0F) {
         var6 = super.rotationYaw;
      }

      if(!super.onGround) {
         var8 = 0.0F;
      }

      this.field_110154_aX += (var8 - this.field_110154_aX) * 0.3F;
      super.worldObj.theProfiler.startSection("headTurn");
      var7 = this.func_110146_f(var6, var7);
      super.worldObj.theProfiler.endSection();
      super.worldObj.theProfiler.startSection("rangeChecks");

      while(super.rotationYaw - super.prevRotationYaw < -180.0F) {
         super.prevRotationYaw -= 360.0F;
      }

      while(super.rotationYaw - super.prevRotationYaw >= 180.0F) {
         super.prevRotationYaw += 360.0F;
      }

      while(this.renderYawOffset - this.prevRenderYawOffset < -180.0F) {
         this.prevRenderYawOffset -= 360.0F;
      }

      while(this.renderYawOffset - this.prevRenderYawOffset >= 180.0F) {
         this.prevRenderYawOffset += 360.0F;
      }

      while(super.rotationPitch - super.prevRotationPitch < -180.0F) {
         super.prevRotationPitch -= 360.0F;
      }

      while(super.rotationPitch - super.prevRotationPitch >= 180.0F) {
         super.prevRotationPitch += 360.0F;
      }

      while(this.rotationYawHead - this.prevRotationYawHead < -180.0F) {
         this.prevRotationYawHead -= 360.0F;
      }

      while(this.rotationYawHead - this.prevRotationYawHead >= 180.0F) {
         this.prevRotationYawHead += 360.0F;
      }

      super.worldObj.theProfiler.endSection();
      this.field_70764_aw += var7;
   }

   protected float func_110146_f(float var1, float var2) {
      float var3 = MathHelper.wrapAngleTo180_float(var1 - this.renderYawOffset);
      this.renderYawOffset += var3 * 0.3F;
      float var4 = MathHelper.wrapAngleTo180_float(super.rotationYaw - this.renderYawOffset);
      boolean var5 = var4 < -90.0F || var4 >= 90.0F;
      if(var4 < -75.0F) {
         var4 = -75.0F;
      }

      if(var4 >= 75.0F) {
         var4 = 75.0F;
      }

      this.renderYawOffset = super.rotationYaw - var4;
      if(var4 * var4 > 2500.0F) {
         this.renderYawOffset += var4 * 0.2F;
      }

      if(var5) {
         var2 *= -1.0F;
      }

      return var2;
   }

   public void onLivingUpdate() {
      if(this.jumpTicks > 0) {
         --this.jumpTicks;
      }

      if(this.newPosRotationIncrements > 0) {
         double var1 = super.posX + (this.newPosX - super.posX) / (double)this.newPosRotationIncrements;
         double var3 = super.posY + (this.newPosY - super.posY) / (double)this.newPosRotationIncrements;
         double var5 = super.posZ + (this.newPosZ - super.posZ) / (double)this.newPosRotationIncrements;
         double var7 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - (double)super.rotationYaw);
         super.rotationYaw = (float)((double)super.rotationYaw + var7 / (double)this.newPosRotationIncrements);
         super.rotationPitch = (float)((double)super.rotationPitch + (this.newRotationPitch - (double)super.rotationPitch) / (double)this.newPosRotationIncrements);
         --this.newPosRotationIncrements;
         this.setPosition(var1, var3, var5);
         this.setRotation(super.rotationYaw, super.rotationPitch);
      } else if(!this.isClientWorld()) {
         super.motionX *= 0.98D;
         super.motionY *= 0.98D;
         super.motionZ *= 0.98D;
      }

      if(Math.abs(super.motionX) < 0.005D) {
         super.motionX = 0.0D;
      }

      if(Math.abs(super.motionY) < 0.005D) {
         super.motionY = 0.0D;
      }

      if(Math.abs(super.motionZ) < 0.005D) {
         super.motionZ = 0.0D;
      }

      super.worldObj.theProfiler.startSection("ai");
      if(this.isMovementBlocked()) {
         this.isJumping = false;
         this.moveStrafing = 0.0F;
         this.moveForward = 0.0F;
         this.randomYawVelocity = 0.0F;
      } else if(this.isClientWorld()) {
         if(this.isAIEnabled()) {
            super.worldObj.theProfiler.startSection("newAi");
            this.updateAITasks();
            super.worldObj.theProfiler.endSection();
         } else {
            super.worldObj.theProfiler.startSection("oldAi");
            this.updateEntityActionState();
            super.worldObj.theProfiler.endSection();
            this.rotationYawHead = super.rotationYaw;
         }
      }

      super.worldObj.theProfiler.endSection();
      super.worldObj.theProfiler.startSection("jump");
      if(this.isJumping) {
         if(!this.isInWater() && !this.handleLavaMovement()) {
            if(super.onGround && this.jumpTicks == 0) {
               this.jump();
               this.jumpTicks = 10;
            }
         } else {
            super.motionY += 0.03999999910593033D;
         }
      } else {
         this.jumpTicks = 0;
      }

      super.worldObj.theProfiler.endSection();
      super.worldObj.theProfiler.startSection("travel");
      this.moveStrafing *= 0.98F;
      this.moveForward *= 0.98F;
      this.randomYawVelocity *= 0.9F;
      this.moveEntityWithHeading(this.moveStrafing, this.moveForward);
      super.worldObj.theProfiler.endSection();
      super.worldObj.theProfiler.startSection("push");
      if(!super.worldObj.isRemote) {
         this.collideWithNearbyEntities();
      }

      super.worldObj.theProfiler.endSection();
   }

   protected void updateAITasks() {}

   protected void collideWithNearbyEntities() {
      List var1 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
      if(var1 != null && !var1.isEmpty()) {
         for(int var2 = 0; var2 < var1.size(); ++var2) {
            Entity var3 = (Entity)var1.get(var2);
            if(var3.canBePushed()) {
               this.collideWithEntity(var3);
            }
         }
      }

   }

   protected void collideWithEntity(Entity var1) {
      var1.applyEntityCollision(this);
   }

   public void updateRidden() {
      super.updateRidden();
      this.field_70768_au = this.field_110154_aX;
      this.field_110154_aX = 0.0F;
      super.fallDistance = 0.0F;
   }

   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9) {
      super.yOffset = 0.0F;
      this.newPosX = var1;
      this.newPosY = var3;
      this.newPosZ = var5;
      this.newRotationYaw = (double)var7;
      this.newRotationPitch = (double)var8;
      this.newPosRotationIncrements = var9;
   }

   protected void updateAITick() {}

   protected void updateEntityActionState() {
      ++this.entityAge;
   }

   public void setJumping(boolean var1) {
      this.isJumping = var1;
   }

   public void onItemPickup(Entity var1, int var2) {
      if(!var1.isDead && !super.worldObj.isRemote) {
         EntityTracker var3 = ((WorldServer)super.worldObj).getEntityTracker();
         if(var1 instanceof EntityItem) {
            var3.func_151247_a(var1, new S0DPacketCollectItem(var1.getEntityId(), this.getEntityId()));
         }

         if(var1 instanceof EntityArrow) {
            var3.func_151247_a(var1, new S0DPacketCollectItem(var1.getEntityId(), this.getEntityId()));
         }

         if(var1 instanceof EntityXPOrb) {
            var3.func_151247_a(var1, new S0DPacketCollectItem(var1.getEntityId(), this.getEntityId()));
         }
      }

   }

   public boolean canEntityBeSeen(Entity var1) {
      return super.worldObj.rayTraceBlocks(Vec3.createVectorHelper(super.posX, super.posY + (double)this.getEyeHeight(), super.posZ), Vec3.createVectorHelper(var1.posX, var1.posY + (double)var1.getEyeHeight(), var1.posZ)) == null;
   }

   public Vec3 getLookVec() {
      return this.getLook(1.0F);
   }

   public Vec3 getLook(float var1) {
      float var2;
      float var3;
      float var4;
      float var5;
      if(var1 == 1.0F) {
         var2 = MathHelper.cos(-super.rotationYaw * 0.017453292F - 3.1415927F);
         var3 = MathHelper.sin(-super.rotationYaw * 0.017453292F - 3.1415927F);
         var4 = -MathHelper.cos(-super.rotationPitch * 0.017453292F);
         var5 = MathHelper.sin(-super.rotationPitch * 0.017453292F);
         return Vec3.createVectorHelper((double)(var3 * var4), (double)var5, (double)(var2 * var4));
      } else {
         var2 = super.prevRotationPitch + (super.rotationPitch - super.prevRotationPitch) * var1;
         var3 = super.prevRotationYaw + (super.rotationYaw - super.prevRotationYaw) * var1;
         var4 = MathHelper.cos(-var3 * 0.017453292F - 3.1415927F);
         var5 = MathHelper.sin(-var3 * 0.017453292F - 3.1415927F);
         float var6 = -MathHelper.cos(-var2 * 0.017453292F);
         float var7 = MathHelper.sin(-var2 * 0.017453292F);
         return Vec3.createVectorHelper((double)(var5 * var6), (double)var7, (double)(var4 * var6));
      }
   }

   public float getSwingProgress(float var1) {
      float var2 = this.swingProgress - this.prevSwingProgress;
      if(var2 < 0.0F) {
         ++var2;
      }

      return this.prevSwingProgress + var2 * var1;
   }

   public Vec3 getPosition(float var1) {
      if(var1 == 1.0F) {
         return Vec3.createVectorHelper(super.posX, super.posY, super.posZ);
      } else {
         double var2 = super.prevPosX + (super.posX - super.prevPosX) * (double)var1;
         double var4 = super.prevPosY + (super.posY - super.prevPosY) * (double)var1;
         double var6 = super.prevPosZ + (super.posZ - super.prevPosZ) * (double)var1;
         return Vec3.createVectorHelper(var2, var4, var6);
      }
   }

   public MovingObjectPosition rayTrace(double var1, float var3) {
      Vec3 var4 = this.getPosition(var3);
      Vec3 var5 = this.getLook(var3);
      Vec3 var6 = var4.addVector(var5.xCoord * var1, var5.yCoord * var1, var5.zCoord * var1);
      return super.worldObj.func_147447_a(var4, var6, false, false, true);
   }

   public boolean isClientWorld() {
      return !super.worldObj.isRemote;
   }

   public boolean canBeCollidedWith() {
      return !super.isDead;
   }

   public boolean canBePushed() {
      return !super.isDead;
   }

   public float getEyeHeight() {
      return super.height * 0.85F;
   }

   protected void setBeenAttacked() {
      super.velocityChanged = super.rand.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue();
   }

   public float getRotationYawHead() {
      return this.rotationYawHead;
   }

   public void setRotationYawHead(float var1) {
      this.rotationYawHead = var1;
   }

   public float getAbsorptionAmount() {
      return this.field_110151_bq;
   }

   public void setAbsorptionAmount(float var1) {
      if(var1 < 0.0F) {
         var1 = 0.0F;
      }

      this.field_110151_bq = var1;
   }

   public Team getTeam() {
      return null;
   }

   public boolean isOnSameTeam(EntityLivingBase var1) {
      return this.isOnTeam(var1.getTeam());
   }

   public boolean isOnTeam(Team var1) {
      return this.getTeam() != null?this.getTeam().isSameTeam(var1):false;
   }

   public void func_152111_bt() {}

   public void func_152112_bu() {}

}
