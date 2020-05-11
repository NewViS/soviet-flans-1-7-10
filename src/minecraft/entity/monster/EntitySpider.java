package net.minecraft.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider$GroupData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySpider extends EntityMob {

   public EntitySpider(World var1) {
      super(var1);
      this.setSize(1.4F, 0.9F);
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, new Byte((byte)0));
   }

   public void onUpdate() {
      super.onUpdate();
      if(!super.worldObj.isRemote) {
         this.setBesideClimbableBlock(super.isCollidedHorizontally);
      }

   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.800000011920929D);
   }

   protected Entity findPlayerToAttack() {
      float var1 = this.getBrightness(1.0F);
      if(var1 < 0.5F) {
         double var2 = 16.0D;
         return super.worldObj.getClosestVulnerablePlayerToEntity(this, var2);
      } else {
         return null;
      }
   }

   protected String getLivingSound() {
      return "mob.spider.say";
   }

   protected String getHurtSound() {
      return "mob.spider.say";
   }

   protected String getDeathSound() {
      return "mob.spider.death";
   }

   protected void func_145780_a(int var1, int var2, int var3, Block var4) {
      this.playSound("mob.spider.step", 0.15F, 1.0F);
   }

   protected void attackEntity(Entity var1, float var2) {
      float var3 = this.getBrightness(1.0F);
      if(var3 > 0.5F && super.rand.nextInt(100) == 0) {
         super.entityToAttack = null;
      } else {
         if(var2 > 2.0F && var2 < 6.0F && super.rand.nextInt(10) == 0) {
            if(super.onGround) {
               double var4 = var1.posX - super.posX;
               double var6 = var1.posZ - super.posZ;
               float var8 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
               super.motionX = var4 / (double)var8 * 0.5D * 0.800000011920929D + super.motionX * 0.20000000298023224D;
               super.motionZ = var6 / (double)var8 * 0.5D * 0.800000011920929D + super.motionZ * 0.20000000298023224D;
               super.motionY = 0.4000000059604645D;
            }
         } else {
            super.attackEntity(var1, var2);
         }

      }
   }

   protected Item getDropItem() {
      return Items.string;
   }

   protected void dropFewItems(boolean var1, int var2) {
      super.dropFewItems(var1, var2);
      if(var1 && (super.rand.nextInt(3) == 0 || super.rand.nextInt(1 + var2) > 0)) {
         this.dropItem(Items.spider_eye, 1);
      }

   }

   public boolean isOnLadder() {
      return this.isBesideClimbableBlock();
   }

   public void setInWeb() {}

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.ARTHROPOD;
   }

   public boolean isPotionApplicable(PotionEffect var1) {
      return var1.getPotionID() == Potion.poison.id?false:super.isPotionApplicable(var1);
   }

   public boolean isBesideClimbableBlock() {
      return (super.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   public void setBesideClimbableBlock(boolean var1) {
      byte var2 = super.dataWatcher.getWatchableObjectByte(16);
      if(var1) {
         var2 = (byte)(var2 | 1);
      } else {
         var2 &= -2;
      }

      super.dataWatcher.updateObject(16, Byte.valueOf(var2));
   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData var1) {
      Object var3 = super.onSpawnWithEgg(var1);
      if(super.worldObj.rand.nextInt(100) == 0) {
         EntitySkeleton var2 = new EntitySkeleton(super.worldObj);
         var2.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, 0.0F);
         var2.onSpawnWithEgg((IEntityLivingData)null);
         super.worldObj.spawnEntityInWorld(var2);
         var2.mountEntity(this);
      }

      if(var3 == null) {
         var3 = new EntitySpider$GroupData();
         if(super.worldObj.difficultySetting == EnumDifficulty.HARD && super.worldObj.rand.nextFloat() < 0.1F * super.worldObj.func_147462_b(super.posX, super.posY, super.posZ)) {
            ((EntitySpider$GroupData)var3).func_111104_a(super.worldObj.rand);
         }
      }

      if(var3 instanceof EntitySpider$GroupData) {
         int var4 = ((EntitySpider$GroupData)var3).field_111105_a;
         if(var4 > 0 && Potion.potionTypes[var4] != null) {
            this.addPotionEffect(new PotionEffect(var4, Integer.MAX_VALUE));
         }
      }

      return (IEntityLivingData)var3;
   }
}
