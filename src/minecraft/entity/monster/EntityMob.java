package net.minecraft.entity.monster;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public abstract class EntityMob extends EntityCreature implements IMob {

   public EntityMob(World var1) {
      super(var1);
      super.experienceValue = 5;
   }

   public void onLivingUpdate() {
      this.updateArmSwingProgress();
      float var1 = this.getBrightness(1.0F);
      if(var1 > 0.5F) {
         super.entityAge += 2;
      }

      super.onLivingUpdate();
   }

   public void onUpdate() {
      super.onUpdate();
      if(!super.worldObj.isRemote && super.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
         this.setDead();
      }

   }

   protected String getSwimSound() {
      return "game.hostile.swim";
   }

   protected String getSplashSound() {
      return "game.hostile.swim.splash";
   }

   protected Entity findPlayerToAttack() {
      EntityPlayer var1 = super.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
      return var1 != null && this.canEntityBeSeen(var1)?var1:null;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else if(super.attackEntityFrom(var1, var2)) {
         Entity var3 = var1.getEntity();
         if(super.riddenByEntity != var3 && super.ridingEntity != var3) {
            if(var3 != this) {
               super.entityToAttack = var3;
            }

            return true;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   protected String getHurtSound() {
      return "game.hostile.hurt";
   }

   protected String getDeathSound() {
      return "game.hostile.die";
   }

   protected String func_146067_o(int var1) {
      return var1 > 4?"game.hostile.hurt.fall.big":"game.hostile.hurt.fall.small";
   }

   public boolean attackEntityAsMob(Entity var1) {
      float var2 = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
      int var3 = 0;
      if(var1 instanceof EntityLivingBase) {
         var2 += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)var1);
         var3 += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)var1);
      }

      boolean var4 = var1.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
      if(var4) {
         if(var3 > 0) {
            var1.addVelocity((double)(-MathHelper.sin(super.rotationYaw * 3.1415927F / 180.0F) * (float)var3 * 0.5F), 0.1D, (double)(MathHelper.cos(super.rotationYaw * 3.1415927F / 180.0F) * (float)var3 * 0.5F));
            super.motionX *= 0.6D;
            super.motionZ *= 0.6D;
         }

         int var5 = EnchantmentHelper.getFireAspectModifier(this);
         if(var5 > 0) {
            var1.setFire(var5 * 4);
         }

         if(var1 instanceof EntityLivingBase) {
            EnchantmentHelper.func_151384_a((EntityLivingBase)var1, this);
         }

         EnchantmentHelper.func_151385_b(this, var1);
      }

      return var4;
   }

   protected void attackEntity(Entity var1, float var2) {
      if(super.attackTime <= 0 && var2 < 2.0F && var1.boundingBox.maxY > super.boundingBox.minY && var1.boundingBox.minY < super.boundingBox.maxY) {
         super.attackTime = 20;
         this.attackEntityAsMob(var1);
      }

   }

   public float getBlockPathWeight(int var1, int var2, int var3) {
      return 0.5F - super.worldObj.getLightBrightness(var1, var2, var3);
   }

   protected boolean isValidLightLevel() {
      int var1 = MathHelper.floor_double(super.posX);
      int var2 = MathHelper.floor_double(super.boundingBox.minY);
      int var3 = MathHelper.floor_double(super.posZ);
      if(super.worldObj.getSavedLightValue(EnumSkyBlock.Sky, var1, var2, var3) > super.rand.nextInt(32)) {
         return false;
      } else {
         int var4 = super.worldObj.getBlockLightValue(var1, var2, var3);
         if(super.worldObj.isThundering()) {
            int var5 = super.worldObj.skylightSubtracted;
            super.worldObj.skylightSubtracted = 10;
            var4 = super.worldObj.getBlockLightValue(var1, var2, var3);
            super.worldObj.skylightSubtracted = var5;
         }

         return var4 <= super.rand.nextInt(8);
      }
   }

   public boolean getCanSpawnHere() {
      return super.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere();
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
   }

   protected boolean func_146066_aG() {
      return true;
   }
}
