package net.minecraft.entity.monster;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWitch extends EntityMob implements IRangedAttackMob {

   private static final UUID field_110184_bp = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
   private static final AttributeModifier field_110185_bq = (new AttributeModifier(field_110184_bp, "Drinking speed penalty", -0.25D, 0)).setSaved(false);
   private static final Item[] witchDrops = new Item[]{Items.glowstone_dust, Items.sugar, Items.redstone, Items.spider_eye, Items.glass_bottle, Items.gunpowder, Items.stick, Items.stick};
   private int witchAttackTimer;


   public EntityWitch(World var1) {
      super(var1);
      super.tasks.addTask(1, new EntityAISwimming(this));
      super.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
      super.tasks.addTask(2, new EntityAIWander(this, 1.0D));
      super.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      super.tasks.addTask(3, new EntityAILookIdle(this));
      super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
   }

   protected void entityInit() {
      super.entityInit();
      this.getDataWatcher().addObject(21, Byte.valueOf((byte)0));
   }

   protected String getLivingSound() {
      return "mob.witch.idle";
   }

   protected String getHurtSound() {
      return "mob.witch.hurt";
   }

   protected String getDeathSound() {
      return "mob.witch.death";
   }

   public void setAggressive(boolean var1) {
      this.getDataWatcher().updateObject(21, Byte.valueOf((byte)(var1?1:0)));
   }

   public boolean getAggressive() {
      return this.getDataWatcher().getWatchableObjectByte(21) == 1;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(26.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
   }

   public boolean isAIEnabled() {
      return true;
   }

   public void onLivingUpdate() {
      if(!super.worldObj.isRemote) {
         if(this.getAggressive()) {
            if(this.witchAttackTimer-- <= 0) {
               this.setAggressive(false);
               ItemStack var1 = this.getHeldItem();
               this.setCurrentItemOrArmor(0, (ItemStack)null);
               if(var1 != null && var1.getItem() == Items.potionitem) {
                  List var2 = Items.potionitem.getEffects(var1);
                  if(var2 != null) {
                     Iterator var3 = var2.iterator();

                     while(var3.hasNext()) {
                        PotionEffect var4 = (PotionEffect)var3.next();
                        this.addPotionEffect(new PotionEffect(var4));
                     }
                  }
               }

               this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(field_110185_bq);
            }
         } else {
            short var5 = -1;
            if(super.rand.nextFloat() < 0.15F && this.isInsideOfMaterial(Material.water) && !this.isPotionActive(Potion.waterBreathing)) {
               var5 = 8237;
            } else if(super.rand.nextFloat() < 0.15F && this.isBurning() && !this.isPotionActive(Potion.fireResistance)) {
               var5 = 16307;
            } else if(super.rand.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
               var5 = 16341;
            } else if(super.rand.nextFloat() < 0.25F && this.getAttackTarget() != null && !this.isPotionActive(Potion.moveSpeed) && this.getAttackTarget().getDistanceSqToEntity(this) > 121.0D) {
               var5 = 16274;
            } else if(super.rand.nextFloat() < 0.25F && this.getAttackTarget() != null && !this.isPotionActive(Potion.moveSpeed) && this.getAttackTarget().getDistanceSqToEntity(this) > 121.0D) {
               var5 = 16274;
            }

            if(var5 > -1) {
               this.setCurrentItemOrArmor(0, new ItemStack(Items.potionitem, 1, var5));
               this.witchAttackTimer = this.getHeldItem().getMaxItemUseDuration();
               this.setAggressive(true);
               IAttributeInstance var6 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
               var6.removeModifier(field_110185_bq);
               var6.applyModifier(field_110185_bq);
            }
         }

         if(super.rand.nextFloat() < 7.5E-4F) {
            super.worldObj.setEntityState(this, (byte)15);
         }
      }

      super.onLivingUpdate();
   }

   public void handleHealthUpdate(byte var1) {
      if(var1 == 15) {
         for(int var2 = 0; var2 < super.rand.nextInt(35) + 10; ++var2) {
            super.worldObj.spawnParticle("witchMagic", super.posX + super.rand.nextGaussian() * 0.12999999523162842D, super.boundingBox.maxY + 0.5D + super.rand.nextGaussian() * 0.12999999523162842D, super.posZ + super.rand.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D);
         }
      } else {
         super.handleHealthUpdate(var1);
      }

   }

   protected float applyPotionDamageCalculations(DamageSource var1, float var2) {
      var2 = super.applyPotionDamageCalculations(var1, var2);
      if(var1.getEntity() == this) {
         var2 = 0.0F;
      }

      if(var1.isMagicDamage()) {
         var2 = (float)((double)var2 * 0.15D);
      }

      return var2;
   }

   protected void dropFewItems(boolean var1, int var2) {
      int var3 = super.rand.nextInt(3) + 1;

      for(int var4 = 0; var4 < var3; ++var4) {
         int var5 = super.rand.nextInt(3);
         Item var6 = witchDrops[super.rand.nextInt(witchDrops.length)];
         if(var2 > 0) {
            var5 += super.rand.nextInt(var2 + 1);
         }

         for(int var7 = 0; var7 < var5; ++var7) {
            this.dropItem(var6, 1);
         }
      }

   }

   public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
      if(!this.getAggressive()) {
         EntityPotion var3 = new EntityPotion(super.worldObj, this, 32732);
         var3.rotationPitch -= -20.0F;
         double var4 = var1.posX + var1.motionX - super.posX;
         double var6 = var1.posY + (double)var1.getEyeHeight() - 1.100000023841858D - super.posY;
         double var8 = var1.posZ + var1.motionZ - super.posZ;
         float var10 = MathHelper.sqrt_double(var4 * var4 + var8 * var8);
         if(var10 >= 8.0F && !var1.isPotionActive(Potion.moveSlowdown)) {
            var3.setPotionDamage(32698);
         } else if(var1.getHealth() >= 8.0F && !var1.isPotionActive(Potion.poison)) {
            var3.setPotionDamage(32660);
         } else if(var10 <= 3.0F && !var1.isPotionActive(Potion.weakness) && super.rand.nextFloat() < 0.25F) {
            var3.setPotionDamage(32696);
         }

         var3.setThrowableHeading(var4, var6 + (double)(var10 * 0.2F), var8, 0.75F, 8.0F);
         super.worldObj.spawnEntityInWorld(var3);
      }
   }

}
