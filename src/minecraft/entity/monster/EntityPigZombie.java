package net.minecraft.entity.monster;

import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityPigZombie extends EntityZombie {

   private static final UUID field_110189_bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
   private static final AttributeModifier field_110190_br = (new AttributeModifier(field_110189_bq, "Attacking speed boost", 0.45D, 0)).setSaved(false);
   private int angerLevel;
   private int randomSoundDelay;
   private Entity field_110191_bu;


   public EntityPigZombie(World var1) {
      super(var1);
      super.isImmuneToFire = true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(EntityZombie.field_110186_bp).setBaseValue(0.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
   }

   protected boolean isAIEnabled() {
      return false;
   }

   public void onUpdate() {
      if(this.field_110191_bu != super.entityToAttack && !super.worldObj.isRemote) {
         IAttributeInstance var1 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
         var1.removeModifier(field_110190_br);
         if(super.entityToAttack != null) {
            var1.applyModifier(field_110190_br);
         }
      }

      this.field_110191_bu = super.entityToAttack;
      if(this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
         this.playSound("mob.zombiepig.zpigangry", this.getSoundVolume() * 2.0F, ((super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
      }

      super.onUpdate();
   }

   public boolean getCanSpawnHere() {
      return super.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && super.worldObj.checkNoEntityCollision(super.boundingBox) && super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox);
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setShort("Anger", (short)this.angerLevel);
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.angerLevel = var1.getShort("Anger");
   }

   protected Entity findPlayerToAttack() {
      return this.angerLevel == 0?null:super.findPlayerToAttack();
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         Entity var3 = var1.getEntity();
         if(var3 instanceof EntityPlayer) {
            List var4 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.expand(32.0D, 32.0D, 32.0D));

            for(int var5 = 0; var5 < var4.size(); ++var5) {
               Entity var6 = (Entity)var4.get(var5);
               if(var6 instanceof EntityPigZombie) {
                  EntityPigZombie var7 = (EntityPigZombie)var6;
                  var7.becomeAngryAt(var3);
               }
            }

            this.becomeAngryAt(var3);
         }

         return super.attackEntityFrom(var1, var2);
      }
   }

   private void becomeAngryAt(Entity var1) {
      super.entityToAttack = var1;
      this.angerLevel = 400 + super.rand.nextInt(400);
      this.randomSoundDelay = super.rand.nextInt(40);
   }

   protected String getLivingSound() {
      return "mob.zombiepig.zpig";
   }

   protected String getHurtSound() {
      return "mob.zombiepig.zpighurt";
   }

   protected String getDeathSound() {
      return "mob.zombiepig.zpigdeath";
   }

   protected void dropFewItems(boolean var1, int var2) {
      int var3 = super.rand.nextInt(2 + var2);

      int var4;
      for(var4 = 0; var4 < var3; ++var4) {
         this.dropItem(Items.rotten_flesh, 1);
      }

      var3 = super.rand.nextInt(2 + var2);

      for(var4 = 0; var4 < var3; ++var4) {
         this.dropItem(Items.gold_nugget, 1);
      }

   }

   public boolean interact(EntityPlayer var1) {
      return false;
   }

   protected void dropRareDrop(int var1) {
      this.dropItem(Items.gold_ingot, 1);
   }

   protected void addRandomArmor() {
      this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
   }

   public IEntityLivingData onSpawnWithEgg(IEntityLivingData var1) {
      super.onSpawnWithEgg(var1);
      this.setVillager(false);
      return var1;
   }

}
