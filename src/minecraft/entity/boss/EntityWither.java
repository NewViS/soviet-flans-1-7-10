package net.minecraft.entity.boss;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityWither$1;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityWither extends EntityMob implements IBossDisplayData, IRangedAttackMob {

   private float[] field_82220_d = new float[2];
   private float[] field_82221_e = new float[2];
   private float[] field_82217_f = new float[2];
   private float[] field_82218_g = new float[2];
   private int[] field_82223_h = new int[2];
   private int[] field_82224_i = new int[2];
   private int field_82222_j;
   private static final IEntitySelector attackEntitySelector = new EntityWither$1();


   public EntityWither(World var1) {
      super(var1);
      this.setHealth(this.getMaxHealth());
      this.setSize(0.9F, 4.0F);
      super.isImmuneToFire = true;
      this.getNavigator().setCanSwim(true);
      super.tasks.addTask(0, new EntityAISwimming(this));
      super.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
      super.tasks.addTask(5, new EntityAIWander(this, 1.0D));
      super.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      super.tasks.addTask(7, new EntityAILookIdle(this));
      super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, attackEntitySelector));
      super.experienceValue = 50;
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(17, new Integer(0));
      super.dataWatcher.addObject(18, new Integer(0));
      super.dataWatcher.addObject(19, new Integer(0));
      super.dataWatcher.addObject(20, new Integer(0));
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("Invul", this.func_82212_n());
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.func_82215_s(var1.getInteger("Invul"));
   }

   public float getShadowSize() {
      return super.height / 8.0F;
   }

   protected String getLivingSound() {
      return "mob.wither.idle";
   }

   protected String getHurtSound() {
      return "mob.wither.hurt";
   }

   protected String getDeathSound() {
      return "mob.wither.death";
   }

   public void onLivingUpdate() {
      super.motionY *= 0.6000000238418579D;
      double var4;
      double var6;
      double var8;
      if(!super.worldObj.isRemote && this.getWatchedTargetId(0) > 0) {
         Entity var1 = super.worldObj.getEntityByID(this.getWatchedTargetId(0));
         if(var1 != null) {
            if(super.posY < var1.posY || !this.isArmored() && super.posY < var1.posY + 5.0D) {
               if(super.motionY < 0.0D) {
                  super.motionY = 0.0D;
               }

               super.motionY += (0.5D - super.motionY) * 0.6000000238418579D;
            }

            double var2 = var1.posX - super.posX;
            var4 = var1.posZ - super.posZ;
            var6 = var2 * var2 + var4 * var4;
            if(var6 > 9.0D) {
               var8 = (double)MathHelper.sqrt_double(var6);
               super.motionX += (var2 / var8 * 0.5D - super.motionX) * 0.6000000238418579D;
               super.motionZ += (var4 / var8 * 0.5D - super.motionZ) * 0.6000000238418579D;
            }
         }
      }

      if(super.motionX * super.motionX + super.motionZ * super.motionZ > 0.05000000074505806D) {
         super.rotationYaw = (float)Math.atan2(super.motionZ, super.motionX) * 57.295776F - 90.0F;
      }

      super.onLivingUpdate();

      int var20;
      for(var20 = 0; var20 < 2; ++var20) {
         this.field_82218_g[var20] = this.field_82221_e[var20];
         this.field_82217_f[var20] = this.field_82220_d[var20];
      }

      int var21;
      for(var20 = 0; var20 < 2; ++var20) {
         var21 = this.getWatchedTargetId(var20 + 1);
         Entity var3 = null;
         if(var21 > 0) {
            var3 = super.worldObj.getEntityByID(var21);
         }

         if(var3 != null) {
            var4 = this.func_82214_u(var20 + 1);
            var6 = this.func_82208_v(var20 + 1);
            var8 = this.func_82213_w(var20 + 1);
            double var10 = var3.posX - var4;
            double var12 = var3.posY + (double)var3.getEyeHeight() - var6;
            double var14 = var3.posZ - var8;
            double var16 = (double)MathHelper.sqrt_double(var10 * var10 + var14 * var14);
            float var18 = (float)(Math.atan2(var14, var10) * 180.0D / 3.1415927410125732D) - 90.0F;
            float var19 = (float)(-(Math.atan2(var12, var16) * 180.0D / 3.1415927410125732D));
            this.field_82220_d[var20] = this.func_82204_b(this.field_82220_d[var20], var19, 40.0F);
            this.field_82221_e[var20] = this.func_82204_b(this.field_82221_e[var20], var18, 10.0F);
         } else {
            this.field_82221_e[var20] = this.func_82204_b(this.field_82221_e[var20], super.renderYawOffset, 10.0F);
         }
      }

      boolean var22 = this.isArmored();

      for(var21 = 0; var21 < 3; ++var21) {
         double var23 = this.func_82214_u(var21);
         double var5 = this.func_82208_v(var21);
         double var7 = this.func_82213_w(var21);
         super.worldObj.spawnParticle("smoke", var23 + super.rand.nextGaussian() * 0.30000001192092896D, var5 + super.rand.nextGaussian() * 0.30000001192092896D, var7 + super.rand.nextGaussian() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D);
         if(var22 && super.worldObj.rand.nextInt(4) == 0) {
            super.worldObj.spawnParticle("mobSpell", var23 + super.rand.nextGaussian() * 0.30000001192092896D, var5 + super.rand.nextGaussian() * 0.30000001192092896D, var7 + super.rand.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D);
         }
      }

      if(this.func_82212_n() > 0) {
         for(var21 = 0; var21 < 3; ++var21) {
            super.worldObj.spawnParticle("mobSpell", super.posX + super.rand.nextGaussian() * 1.0D, super.posY + (double)(super.rand.nextFloat() * 3.3F), super.posZ + super.rand.nextGaussian() * 1.0D, 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
         }
      }

   }

   protected void updateAITasks() {
      int var1;
      if(this.func_82212_n() > 0) {
         var1 = this.func_82212_n() - 1;
         if(var1 <= 0) {
            super.worldObj.newExplosion(this, super.posX, super.posY + (double)this.getEyeHeight(), super.posZ, 7.0F, false, super.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
            super.worldObj.playBroadcastSound(1013, (int)super.posX, (int)super.posY, (int)super.posZ, 0);
         }

         this.func_82215_s(var1);
         if(super.ticksExisted % 10 == 0) {
            this.heal(10.0F);
         }

      } else {
         super.updateAITasks();

         int var12;
         for(var1 = 1; var1 < 3; ++var1) {
            if(super.ticksExisted >= this.field_82223_h[var1 - 1]) {
               this.field_82223_h[var1 - 1] = super.ticksExisted + 10 + super.rand.nextInt(10);
               if(super.worldObj.difficultySetting == EnumDifficulty.NORMAL || super.worldObj.difficultySetting == EnumDifficulty.HARD) {
                  int var10001 = var1 - 1;
                  int var10003 = this.field_82224_i[var1 - 1];
                  this.field_82224_i[var10001] = this.field_82224_i[var1 - 1] + 1;
                  if(var10003 > 15) {
                     float var2 = 10.0F;
                     float var3 = 5.0F;
                     double var4 = MathHelper.getRandomDoubleInRange(super.rand, super.posX - (double)var2, super.posX + (double)var2);
                     double var6 = MathHelper.getRandomDoubleInRange(super.rand, super.posY - (double)var3, super.posY + (double)var3);
                     double var8 = MathHelper.getRandomDoubleInRange(super.rand, super.posZ - (double)var2, super.posZ + (double)var2);
                     this.func_82209_a(var1 + 1, var4, var6, var8, true);
                     this.field_82224_i[var1 - 1] = 0;
                  }
               }

               var12 = this.getWatchedTargetId(var1);
               if(var12 > 0) {
                  Entity var14 = super.worldObj.getEntityByID(var12);
                  if(var14 != null && var14.isEntityAlive() && this.getDistanceSqToEntity(var14) <= 900.0D && this.canEntityBeSeen(var14)) {
                     this.func_82216_a(var1 + 1, (EntityLivingBase)var14);
                     this.field_82223_h[var1 - 1] = super.ticksExisted + 40 + super.rand.nextInt(20);
                     this.field_82224_i[var1 - 1] = 0;
                  } else {
                     this.func_82211_c(var1, 0);
                  }
               } else {
                  List var13 = super.worldObj.selectEntitiesWithinAABB(EntityLivingBase.class, super.boundingBox.expand(20.0D, 8.0D, 20.0D), attackEntitySelector);

                  for(int var16 = 0; var16 < 10 && !var13.isEmpty(); ++var16) {
                     EntityLivingBase var5 = (EntityLivingBase)var13.get(super.rand.nextInt(var13.size()));
                     if(var5 != this && var5.isEntityAlive() && this.canEntityBeSeen(var5)) {
                        if(var5 instanceof EntityPlayer) {
                           if(!((EntityPlayer)var5).capabilities.disableDamage) {
                              this.func_82211_c(var1, var5.getEntityId());
                           }
                        } else {
                           this.func_82211_c(var1, var5.getEntityId());
                        }
                        break;
                     }

                     var13.remove(var5);
                  }
               }
            }
         }

         if(this.getAttackTarget() != null) {
            this.func_82211_c(0, this.getAttackTarget().getEntityId());
         } else {
            this.func_82211_c(0, 0);
         }

         if(this.field_82222_j > 0) {
            --this.field_82222_j;
            if(this.field_82222_j == 0 && super.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
               var1 = MathHelper.floor_double(super.posY);
               var12 = MathHelper.floor_double(super.posX);
               int var15 = MathHelper.floor_double(super.posZ);
               boolean var17 = false;

               for(int var18 = -1; var18 <= 1; ++var18) {
                  for(int var19 = -1; var19 <= 1; ++var19) {
                     for(int var7 = 0; var7 <= 3; ++var7) {
                        int var20 = var12 + var18;
                        int var9 = var1 + var7;
                        int var10 = var15 + var19;
                        Block var11 = super.worldObj.getBlock(var20, var9, var10);
                        if(var11.getMaterial() != Material.air && var11 != Blocks.bedrock && var11 != Blocks.end_portal && var11 != Blocks.end_portal_frame && var11 != Blocks.command_block) {
                           var17 = super.worldObj.func_147480_a(var20, var9, var10, true) || var17;
                        }
                     }
                  }
               }

               if(var17) {
                  super.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1012, (int)super.posX, (int)super.posY, (int)super.posZ, 0);
               }
            }
         }

         if(super.ticksExisted % 20 == 0) {
            this.heal(1.0F);
         }

      }
   }

   public void func_82206_m() {
      this.func_82215_s(220);
      this.setHealth(this.getMaxHealth() / 3.0F);
   }

   public void setInWeb() {}

   public int getTotalArmorValue() {
      return 4;
   }

   private double func_82214_u(int var1) {
      if(var1 <= 0) {
         return super.posX;
      } else {
         float var2 = (super.renderYawOffset + (float)(180 * (var1 - 1))) / 180.0F * 3.1415927F;
         float var3 = MathHelper.cos(var2);
         return super.posX + (double)var3 * 1.3D;
      }
   }

   private double func_82208_v(int var1) {
      return var1 <= 0?super.posY + 3.0D:super.posY + 2.2D;
   }

   private double func_82213_w(int var1) {
      if(var1 <= 0) {
         return super.posZ;
      } else {
         float var2 = (super.renderYawOffset + (float)(180 * (var1 - 1))) / 180.0F * 3.1415927F;
         float var3 = MathHelper.sin(var2);
         return super.posZ + (double)var3 * 1.3D;
      }
   }

   private float func_82204_b(float var1, float var2, float var3) {
      float var4 = MathHelper.wrapAngleTo180_float(var2 - var1);
      if(var4 > var3) {
         var4 = var3;
      }

      if(var4 < -var3) {
         var4 = -var3;
      }

      return var1 + var4;
   }

   private void func_82216_a(int var1, EntityLivingBase var2) {
      this.func_82209_a(var1, var2.posX, var2.posY + (double)var2.getEyeHeight() * 0.5D, var2.posZ, var1 == 0 && super.rand.nextFloat() < 0.001F);
   }

   private void func_82209_a(int var1, double var2, double var4, double var6, boolean var8) {
      super.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1014, (int)super.posX, (int)super.posY, (int)super.posZ, 0);
      double var9 = this.func_82214_u(var1);
      double var11 = this.func_82208_v(var1);
      double var13 = this.func_82213_w(var1);
      double var15 = var2 - var9;
      double var17 = var4 - var11;
      double var19 = var6 - var13;
      EntityWitherSkull var21 = new EntityWitherSkull(super.worldObj, this, var15, var17, var19);
      if(var8) {
         var21.setInvulnerable(true);
      }

      var21.posY = var11;
      var21.posX = var9;
      var21.posZ = var13;
      super.worldObj.spawnEntityInWorld(var21);
   }

   public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
      this.func_82216_a(0, var1);
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else if(var1 == DamageSource.drown) {
         return false;
      } else if(this.func_82212_n() > 0) {
         return false;
      } else {
         Entity var3;
         if(this.isArmored()) {
            var3 = var1.getSourceOfDamage();
            if(var3 instanceof EntityArrow) {
               return false;
            }
         }

         var3 = var1.getEntity();
         if(var3 != null && !(var3 instanceof EntityPlayer) && var3 instanceof EntityLivingBase && ((EntityLivingBase)var3).getCreatureAttribute() == this.getCreatureAttribute()) {
            return false;
         } else {
            if(this.field_82222_j <= 0) {
               this.field_82222_j = 20;
            }

            for(int var4 = 0; var4 < this.field_82224_i.length; ++var4) {
               this.field_82224_i[var4] += 3;
            }

            return super.attackEntityFrom(var1, var2);
         }
      }
   }

   protected void dropFewItems(boolean var1, int var2) {
      this.dropItem(Items.nether_star, 1);
      if(!super.worldObj.isRemote) {
         Iterator var3 = super.worldObj.getEntitiesWithinAABB(EntityPlayer.class, super.boundingBox.expand(50.0D, 100.0D, 50.0D)).iterator();

         while(var3.hasNext()) {
            EntityPlayer var4 = (EntityPlayer)var3.next();
            var4.triggerAchievement(AchievementList.field_150964_J);
         }
      }

   }

   protected void despawnEntity() {
      super.entityAge = 0;
   }

   public int getBrightnessForRender(float var1) {
      return 15728880;
   }

   protected void fall(float var1) {}

   public void addPotionEffect(PotionEffect var1) {}

   protected boolean isAIEnabled() {
      return true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6000000238418579D);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
   }

   public float func_82207_a(int var1) {
      return this.field_82221_e[var1];
   }

   public float func_82210_r(int var1) {
      return this.field_82220_d[var1];
   }

   public int func_82212_n() {
      return super.dataWatcher.getWatchableObjectInt(20);
   }

   public void func_82215_s(int var1) {
      super.dataWatcher.updateObject(20, Integer.valueOf(var1));
   }

   public int getWatchedTargetId(int var1) {
      return super.dataWatcher.getWatchableObjectInt(17 + var1);
   }

   public void func_82211_c(int var1, int var2) {
      super.dataWatcher.updateObject(17 + var1, Integer.valueOf(var2));
   }

   public boolean isArmored() {
      return this.getHealth() <= this.getMaxHealth() / 2.0F;
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   public void mountEntity(Entity var1) {
      super.ridingEntity = null;
   }

}
