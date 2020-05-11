package net.minecraft.entity.passive;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityAnimal extends EntityAgeable implements IAnimals {

   private int inLove;
   private int breeding;
   private EntityPlayer field_146084_br;


   public EntityAnimal(World var1) {
      super(var1);
   }

   protected void updateAITick() {
      if(this.getGrowingAge() != 0) {
         this.inLove = 0;
      }

      super.updateAITick();
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if(this.getGrowingAge() != 0) {
         this.inLove = 0;
      }

      if(this.inLove > 0) {
         --this.inLove;
         String var1 = "heart";
         if(this.inLove % 10 == 0) {
            double var2 = super.rand.nextGaussian() * 0.02D;
            double var4 = super.rand.nextGaussian() * 0.02D;
            double var6 = super.rand.nextGaussian() * 0.02D;
            super.worldObj.spawnParticle(var1, super.posX + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, super.posY + 0.5D + (double)(super.rand.nextFloat() * super.height), super.posZ + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, var2, var4, var6);
         }
      } else {
         this.breeding = 0;
      }

   }

   protected void attackEntity(Entity var1, float var2) {
      if(var1 instanceof EntityPlayer) {
         if(var2 < 3.0F) {
            double var3 = var1.posX - super.posX;
            double var5 = var1.posZ - super.posZ;
            super.rotationYaw = (float)(Math.atan2(var5, var3) * 180.0D / 3.1415927410125732D) - 90.0F;
            super.hasAttacked = true;
         }

         EntityPlayer var7 = (EntityPlayer)var1;
         if(var7.getCurrentEquippedItem() == null || !this.isBreedingItem(var7.getCurrentEquippedItem())) {
            super.entityToAttack = null;
         }
      } else if(var1 instanceof EntityAnimal) {
         EntityAnimal var8 = (EntityAnimal)var1;
         if(this.getGrowingAge() > 0 && var8.getGrowingAge() < 0) {
            if((double)var2 < 2.5D) {
               super.hasAttacked = true;
            }
         } else if(this.inLove > 0 && var8.inLove > 0) {
            if(var8.entityToAttack == null) {
               var8.entityToAttack = this;
            }

            if(var8.entityToAttack == this && (double)var2 < 3.5D) {
               ++var8.inLove;
               ++this.inLove;
               ++this.breeding;
               if(this.breeding % 4 == 0) {
                  super.worldObj.spawnParticle("heart", super.posX + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, super.posY + 0.5D + (double)(super.rand.nextFloat() * super.height), super.posZ + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, 0.0D, 0.0D, 0.0D);
               }

               if(this.breeding == 60) {
                  this.procreate((EntityAnimal)var1);
               }
            } else {
               this.breeding = 0;
            }
         } else {
            this.breeding = 0;
            super.entityToAttack = null;
         }
      }

   }

   private void procreate(EntityAnimal var1) {
      EntityAgeable var2 = this.createChild(var1);
      if(var2 != null) {
         if(this.field_146084_br == null && var1.func_146083_cb() != null) {
            this.field_146084_br = var1.func_146083_cb();
         }

         if(this.field_146084_br != null) {
            this.field_146084_br.triggerAchievement(StatList.field_151186_x);
            if(this instanceof EntityCow) {
               this.field_146084_br.triggerAchievement(AchievementList.field_150962_H);
            }
         }

         this.setGrowingAge(6000);
         var1.setGrowingAge(6000);
         this.inLove = 0;
         this.breeding = 0;
         super.entityToAttack = null;
         var1.entityToAttack = null;
         var1.breeding = 0;
         var1.inLove = 0;
         var2.setGrowingAge(-24000);
         var2.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);

         for(int var3 = 0; var3 < 7; ++var3) {
            double var4 = super.rand.nextGaussian() * 0.02D;
            double var6 = super.rand.nextGaussian() * 0.02D;
            double var8 = super.rand.nextGaussian() * 0.02D;
            super.worldObj.spawnParticle("heart", super.posX + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, super.posY + 0.5D + (double)(super.rand.nextFloat() * super.height), super.posZ + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, var4, var6, var8);
         }

         super.worldObj.spawnEntityInWorld(var2);
      }

   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         super.fleeingTick = 60;
         if(!this.isAIEnabled()) {
            IAttributeInstance var3 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            if(var3.getModifier(EntityCreature.field_110179_h) == null) {
               var3.applyModifier(EntityCreature.field_110181_i);
            }
         }

         super.entityToAttack = null;
         this.inLove = 0;
         return super.attackEntityFrom(var1, var2);
      }
   }

   public float getBlockPathWeight(int var1, int var2, int var3) {
      return super.worldObj.getBlock(var1, var2 - 1, var3) == Blocks.grass?10.0F:super.worldObj.getLightBrightness(var1, var2, var3) - 0.5F;
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("InLove", this.inLove);
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.inLove = var1.getInteger("InLove");
   }

   protected Entity findPlayerToAttack() {
      if(super.fleeingTick > 0) {
         return null;
      } else {
         float var1 = 8.0F;
         List var2;
         int var3;
         EntityAnimal var4;
         if(this.inLove > 0) {
            var2 = super.worldObj.getEntitiesWithinAABB(this.getClass(), super.boundingBox.expand((double)var1, (double)var1, (double)var1));

            for(var3 = 0; var3 < var2.size(); ++var3) {
               var4 = (EntityAnimal)var2.get(var3);
               if(var4 != this && var4.inLove > 0) {
                  return var4;
               }
            }
         } else if(this.getGrowingAge() == 0) {
            var2 = super.worldObj.getEntitiesWithinAABB(EntityPlayer.class, super.boundingBox.expand((double)var1, (double)var1, (double)var1));

            for(var3 = 0; var3 < var2.size(); ++var3) {
               EntityPlayer var5 = (EntityPlayer)var2.get(var3);
               if(var5.getCurrentEquippedItem() != null && this.isBreedingItem(var5.getCurrentEquippedItem())) {
                  return var5;
               }
            }
         } else if(this.getGrowingAge() > 0) {
            var2 = super.worldObj.getEntitiesWithinAABB(this.getClass(), super.boundingBox.expand((double)var1, (double)var1, (double)var1));

            for(var3 = 0; var3 < var2.size(); ++var3) {
               var4 = (EntityAnimal)var2.get(var3);
               if(var4 != this && var4.getGrowingAge() < 0) {
                  return var4;
               }
            }
         }

         return null;
      }
   }

   public boolean getCanSpawnHere() {
      int var1 = MathHelper.floor_double(super.posX);
      int var2 = MathHelper.floor_double(super.boundingBox.minY);
      int var3 = MathHelper.floor_double(super.posZ);
      return super.worldObj.getBlock(var1, var2 - 1, var3) == Blocks.grass && super.worldObj.getFullBlockLightValue(var1, var2, var3) > 8 && super.getCanSpawnHere();
   }

   public int getTalkInterval() {
      return 120;
   }

   protected boolean canDespawn() {
      return false;
   }

   protected int getExperiencePoints(EntityPlayer var1) {
      return 1 + super.worldObj.rand.nextInt(3);
   }

   public boolean isBreedingItem(ItemStack var1) {
      return var1.getItem() == Items.wheat;
   }

   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.inventory.getCurrentItem();
      if(var2 != null && this.isBreedingItem(var2) && this.getGrowingAge() == 0 && this.inLove <= 0) {
         if(!var1.capabilities.isCreativeMode) {
            --var2.stackSize;
            if(var2.stackSize <= 0) {
               var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
            }
         }

         this.func_146082_f(var1);
         return true;
      } else {
         return super.interact(var1);
      }
   }

   public void func_146082_f(EntityPlayer var1) {
      this.inLove = 600;
      this.field_146084_br = var1;
      super.entityToAttack = null;
      super.worldObj.setEntityState(this, (byte)18);
   }

   public EntityPlayer func_146083_cb() {
      return this.field_146084_br;
   }

   public boolean isInLove() {
      return this.inLove > 0;
   }

   public void resetInLove() {
      this.inLove = 0;
   }

   public boolean canMateWith(EntityAnimal var1) {
      return var1 == this?false:(var1.getClass() != this.getClass()?false:this.isInLove() && var1.isInLove());
   }

   public void handleHealthUpdate(byte var1) {
      if(var1 == 18) {
         for(int var2 = 0; var2 < 7; ++var2) {
            double var3 = super.rand.nextGaussian() * 0.02D;
            double var5 = super.rand.nextGaussian() * 0.02D;
            double var7 = super.rand.nextGaussian() * 0.02D;
            super.worldObj.spawnParticle("heart", super.posX + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, super.posY + 0.5D + (double)(super.rand.nextFloat() * super.height), super.posZ + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, var3, var5, var7);
         }
      } else {
         super.handleHealthUpdate(var1);
      }

   }
}
