package net.minecraft.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBlaze extends EntityMob {

   private float heightOffset = 0.5F;
   private int heightOffsetUpdateTime;
   private int field_70846_g;


   public EntityBlaze(World var1) {
      super(var1);
      super.isImmuneToFire = true;
      super.experienceValue = 10;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, new Byte((byte)0));
   }

   protected String getLivingSound() {
      return "mob.blaze.breathe";
   }

   protected String getHurtSound() {
      return "mob.blaze.hit";
   }

   protected String getDeathSound() {
      return "mob.blaze.death";
   }

   public int getBrightnessForRender(float var1) {
      return 15728880;
   }

   public float getBrightness(float var1) {
      return 1.0F;
   }

   public void onLivingUpdate() {
      if(!super.worldObj.isRemote) {
         if(this.isWet()) {
            this.attackEntityFrom(DamageSource.drown, 1.0F);
         }

         --this.heightOffsetUpdateTime;
         if(this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5F + (float)super.rand.nextGaussian() * 3.0F;
         }

         if(this.getEntityToAttack() != null && this.getEntityToAttack().posY + (double)this.getEntityToAttack().getEyeHeight() > super.posY + (double)this.getEyeHeight() + (double)this.heightOffset) {
            super.motionY += (0.30000001192092896D - super.motionY) * 0.30000001192092896D;
         }
      }

      if(super.rand.nextInt(24) == 0) {
         super.worldObj.playSoundEffect(super.posX + 0.5D, super.posY + 0.5D, super.posZ + 0.5D, "fire.fire", 1.0F + super.rand.nextFloat(), super.rand.nextFloat() * 0.7F + 0.3F);
      }

      if(!super.onGround && super.motionY < 0.0D) {
         super.motionY *= 0.6D;
      }

      for(int var1 = 0; var1 < 2; ++var1) {
         super.worldObj.spawnParticle("largesmoke", super.posX + (super.rand.nextDouble() - 0.5D) * (double)super.width, super.posY + super.rand.nextDouble() * (double)super.height, super.posZ + (super.rand.nextDouble() - 0.5D) * (double)super.width, 0.0D, 0.0D, 0.0D);
      }

      super.onLivingUpdate();
   }

   protected void attackEntity(Entity var1, float var2) {
      if(super.attackTime <= 0 && var2 < 2.0F && var1.boundingBox.maxY > super.boundingBox.minY && var1.boundingBox.minY < super.boundingBox.maxY) {
         super.attackTime = 20;
         this.attackEntityAsMob(var1);
      } else if(var2 < 30.0F) {
         double var3 = var1.posX - super.posX;
         double var5 = var1.boundingBox.minY + (double)(var1.height / 2.0F) - (super.posY + (double)(super.height / 2.0F));
         double var7 = var1.posZ - super.posZ;
         if(super.attackTime == 0) {
            ++this.field_70846_g;
            if(this.field_70846_g == 1) {
               super.attackTime = 60;
               this.func_70844_e(true);
            } else if(this.field_70846_g <= 4) {
               super.attackTime = 6;
            } else {
               super.attackTime = 100;
               this.field_70846_g = 0;
               this.func_70844_e(false);
            }

            if(this.field_70846_g > 1) {
               float var9 = MathHelper.sqrt_float(var2) * 0.5F;
               super.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)super.posX, (int)super.posY, (int)super.posZ, 0);

               for(int var10 = 0; var10 < 1; ++var10) {
                  EntitySmallFireball var11 = new EntitySmallFireball(super.worldObj, this, var3 + super.rand.nextGaussian() * (double)var9, var5, var7 + super.rand.nextGaussian() * (double)var9);
                  var11.posY = super.posY + (double)(super.height / 2.0F) + 0.5D;
                  super.worldObj.spawnEntityInWorld(var11);
               }
            }
         }

         super.rotationYaw = (float)(Math.atan2(var7, var3) * 180.0D / 3.1415927410125732D) - 90.0F;
         super.hasAttacked = true;
      }

   }

   protected void fall(float var1) {}

   protected Item getDropItem() {
      return Items.blaze_rod;
   }

   public boolean isBurning() {
      return this.func_70845_n();
   }

   protected void dropFewItems(boolean var1, int var2) {
      if(var1) {
         int var3 = super.rand.nextInt(2 + var2);

         for(int var4 = 0; var4 < var3; ++var4) {
            this.dropItem(Items.blaze_rod, 1);
         }
      }

   }

   public boolean func_70845_n() {
      return (super.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   public void func_70844_e(boolean var1) {
      byte var2 = super.dataWatcher.getWatchableObjectByte(16);
      if(var1) {
         var2 = (byte)(var2 | 1);
      } else {
         var2 &= -2;
      }

      super.dataWatcher.updateObject(16, Byte.valueOf(var2));
   }

   protected boolean isValidLightLevel() {
      return true;
   }
}
