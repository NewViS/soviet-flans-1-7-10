package net.minecraft.entity.monster;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySnowman extends EntityGolem implements IRangedAttackMob {

   public EntitySnowman(World var1) {
      super(var1);
      this.setSize(0.4F, 1.8F);
      this.getNavigator().setAvoidsWater(true);
      super.tasks.addTask(1, new EntityAIArrowAttack(this, 1.25D, 20, 10.0F));
      super.tasks.addTask(2, new EntityAIWander(this, 1.0D));
      super.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      super.tasks.addTask(4, new EntityAILookIdle(this));
      super.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.mobSelector));
   }

   public boolean isAIEnabled() {
      return true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      int var1 = MathHelper.floor_double(super.posX);
      int var2 = MathHelper.floor_double(super.posY);
      int var3 = MathHelper.floor_double(super.posZ);
      if(this.isWet()) {
         this.attackEntityFrom(DamageSource.drown, 1.0F);
      }

      if(super.worldObj.getBiomeGenForCoords(var1, var3).getFloatTemperature(var1, var2, var3) > 1.0F) {
         this.attackEntityFrom(DamageSource.onFire, 1.0F);
      }

      for(int var4 = 0; var4 < 4; ++var4) {
         var1 = MathHelper.floor_double(super.posX + (double)((float)(var4 % 2 * 2 - 1) * 0.25F));
         var2 = MathHelper.floor_double(super.posY);
         var3 = MathHelper.floor_double(super.posZ + (double)((float)(var4 / 2 % 2 * 2 - 1) * 0.25F));
         if(super.worldObj.getBlock(var1, var2, var3).getMaterial() == Material.air && super.worldObj.getBiomeGenForCoords(var1, var3).getFloatTemperature(var1, var2, var3) < 0.8F && Blocks.snow_layer.canPlaceBlockAt(super.worldObj, var1, var2, var3)) {
            super.worldObj.setBlock(var1, var2, var3, Blocks.snow_layer);
         }
      }

   }

   protected Item getDropItem() {
      return Items.snowball;
   }

   protected void dropFewItems(boolean var1, int var2) {
      int var3 = super.rand.nextInt(16);

      for(int var4 = 0; var4 < var3; ++var4) {
         this.dropItem(Items.snowball, 1);
      }

   }

   public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
      EntitySnowball var3 = new EntitySnowball(super.worldObj, this);
      double var4 = var1.posX - super.posX;
      double var6 = var1.posY + (double)var1.getEyeHeight() - 1.100000023841858D - var3.posY;
      double var8 = var1.posZ - super.posZ;
      float var10 = MathHelper.sqrt_double(var4 * var4 + var8 * var8) * 0.2F;
      var3.setThrowableHeading(var4, var6 + (double)var10, var8, 1.6F, 12.0F);
      this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
      super.worldObj.spawnEntityInWorld(var3);
   }
}
