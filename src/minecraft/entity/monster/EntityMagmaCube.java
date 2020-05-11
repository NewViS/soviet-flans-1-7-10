package net.minecraft.entity.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityMagmaCube extends EntitySlime {

   public EntityMagmaCube(World var1) {
      super(var1);
      super.isImmuneToFire = true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
   }

   public boolean getCanSpawnHere() {
      return super.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && super.worldObj.checkNoEntityCollision(super.boundingBox) && super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox);
   }

   public int getTotalArmorValue() {
      return this.getSlimeSize() * 3;
   }

   public int getBrightnessForRender(float var1) {
      return 15728880;
   }

   public float getBrightness(float var1) {
      return 1.0F;
   }

   protected String getSlimeParticle() {
      return "flame";
   }

   protected EntitySlime createInstance() {
      return new EntityMagmaCube(super.worldObj);
   }

   protected Item getDropItem() {
      return Items.magma_cream;
   }

   protected void dropFewItems(boolean var1, int var2) {
      Item var3 = this.getDropItem();
      if(var3 != null && this.getSlimeSize() > 1) {
         int var4 = super.rand.nextInt(4) - 2;
         if(var2 > 0) {
            var4 += super.rand.nextInt(var2 + 1);
         }

         for(int var5 = 0; var5 < var4; ++var5) {
            this.dropItem(var3, 1);
         }
      }

   }

   public boolean isBurning() {
      return false;
   }

   protected int getJumpDelay() {
      return super.getJumpDelay() * 4;
   }

   protected void alterSquishAmount() {
      super.squishAmount *= 0.9F;
   }

   protected void jump() {
      super.motionY = (double)(0.42F + (float)this.getSlimeSize() * 0.1F);
      super.isAirBorne = true;
   }

   protected void fall(float var1) {}

   protected boolean canDamagePlayer() {
      return true;
   }

   protected int getAttackStrength() {
      return super.getAttackStrength() + 2;
   }

   protected String getJumpSound() {
      return this.getSlimeSize() > 1?"mob.magmacube.big":"mob.magmacube.small";
   }

   public boolean handleLavaMovement() {
      return false;
   }

   protected boolean makesSoundOnLand() {
      return true;
   }
}
