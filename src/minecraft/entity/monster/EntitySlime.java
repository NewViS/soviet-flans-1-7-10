package net.minecraft.entity.monster;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class EntitySlime extends EntityLiving implements IMob {

   public float squishAmount;
   public float squishFactor;
   public float prevSquishFactor;
   private int slimeJumpDelay;


   public EntitySlime(World var1) {
      super(var1);
      int var2 = 1 << super.rand.nextInt(3);
      super.yOffset = 0.0F;
      this.slimeJumpDelay = super.rand.nextInt(20) + 10;
      this.setSlimeSize(var2);
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, new Byte((byte)1));
   }

   protected void setSlimeSize(int var1) {
      super.dataWatcher.updateObject(16, new Byte((byte)var1));
      this.setSize(0.6F * (float)var1, 0.6F * (float)var1);
      this.setPosition(super.posX, super.posY, super.posZ);
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)(var1 * var1));
      this.setHealth(this.getMaxHealth());
      super.experienceValue = var1;
   }

   public int getSlimeSize() {
      return super.dataWatcher.getWatchableObjectByte(16);
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("Size", this.getSlimeSize() - 1);
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      int var2 = var1.getInteger("Size");
      if(var2 < 0) {
         var2 = 0;
      }

      this.setSlimeSize(var2 + 1);
   }

   protected String getSlimeParticle() {
      return "slime";
   }

   protected String getJumpSound() {
      return "mob.slime." + (this.getSlimeSize() > 1?"big":"small");
   }

   public void onUpdate() {
      if(!super.worldObj.isRemote && super.worldObj.difficultySetting == EnumDifficulty.PEACEFUL && this.getSlimeSize() > 0) {
         super.isDead = true;
      }

      this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
      this.prevSquishFactor = this.squishFactor;
      boolean var1 = super.onGround;
      super.onUpdate();
      int var2;
      if(super.onGround && !var1) {
         var2 = this.getSlimeSize();

         for(int var3 = 0; var3 < var2 * 8; ++var3) {
            float var4 = super.rand.nextFloat() * 3.1415927F * 2.0F;
            float var5 = super.rand.nextFloat() * 0.5F + 0.5F;
            float var6 = MathHelper.sin(var4) * (float)var2 * 0.5F * var5;
            float var7 = MathHelper.cos(var4) * (float)var2 * 0.5F * var5;
            super.worldObj.spawnParticle(this.getSlimeParticle(), super.posX + (double)var6, super.boundingBox.minY, super.posZ + (double)var7, 0.0D, 0.0D, 0.0D);
         }

         if(this.makesSoundOnLand()) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
         }

         this.squishAmount = -0.5F;
      } else if(!super.onGround && var1) {
         this.squishAmount = 1.0F;
      }

      this.alterSquishAmount();
      if(super.worldObj.isRemote) {
         var2 = this.getSlimeSize();
         this.setSize(0.6F * (float)var2, 0.6F * (float)var2);
      }

   }

   protected void updateEntityActionState() {
      this.despawnEntity();
      EntityPlayer var1 = super.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
      if(var1 != null) {
         this.faceEntity(var1, 10.0F, 20.0F);
      }

      if(super.onGround && this.slimeJumpDelay-- <= 0) {
         this.slimeJumpDelay = this.getJumpDelay();
         if(var1 != null) {
            this.slimeJumpDelay /= 3;
         }

         super.isJumping = true;
         if(this.makesSoundOnJump()) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
         }

         super.moveStrafing = 1.0F - super.rand.nextFloat() * 2.0F;
         super.moveForward = (float)(1 * this.getSlimeSize());
      } else {
         super.isJumping = false;
         if(super.onGround) {
            super.moveStrafing = super.moveForward = 0.0F;
         }
      }

   }

   protected void alterSquishAmount() {
      this.squishAmount *= 0.6F;
   }

   protected int getJumpDelay() {
      return super.rand.nextInt(20) + 10;
   }

   protected EntitySlime createInstance() {
      return new EntitySlime(super.worldObj);
   }

   public void setDead() {
      int var1 = this.getSlimeSize();
      if(!super.worldObj.isRemote && var1 > 1 && this.getHealth() <= 0.0F) {
         int var2 = 2 + super.rand.nextInt(3);

         for(int var3 = 0; var3 < var2; ++var3) {
            float var4 = ((float)(var3 % 2) - 0.5F) * (float)var1 / 4.0F;
            float var5 = ((float)(var3 / 2) - 0.5F) * (float)var1 / 4.0F;
            EntitySlime var6 = this.createInstance();
            var6.setSlimeSize(var1 / 2);
            var6.setLocationAndAngles(super.posX + (double)var4, super.posY + 0.5D, super.posZ + (double)var5, super.rand.nextFloat() * 360.0F, 0.0F);
            super.worldObj.spawnEntityInWorld(var6);
         }
      }

      super.setDead();
   }

   public void onCollideWithPlayer(EntityPlayer var1) {
      if(this.canDamagePlayer()) {
         int var2 = this.getSlimeSize();
         if(this.canEntityBeSeen(var1) && this.getDistanceSqToEntity(var1) < 0.6D * (double)var2 * 0.6D * (double)var2 && var1.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength())) {
            this.playSound("mob.attack", 1.0F, (super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F);
         }
      }

   }

   protected boolean canDamagePlayer() {
      return this.getSlimeSize() > 1;
   }

   protected int getAttackStrength() {
      return this.getSlimeSize();
   }

   protected String getHurtSound() {
      return "mob.slime." + (this.getSlimeSize() > 1?"big":"small");
   }

   protected String getDeathSound() {
      return "mob.slime." + (this.getSlimeSize() > 1?"big":"small");
   }

   protected Item getDropItem() {
      return this.getSlimeSize() == 1?Items.slime_ball:Item.getItemById(0);
   }

   public boolean getCanSpawnHere() {
      Chunk var1 = super.worldObj.getChunkFromBlockCoords(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posZ));
      if(super.worldObj.getWorldInfo().getTerrainType() == WorldType.FLAT && super.rand.nextInt(4) != 1) {
         return false;
      } else {
         if(this.getSlimeSize() == 1 || super.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) {
            BiomeGenBase var2 = super.worldObj.getBiomeGenForCoords(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posZ));
            if(var2 == BiomeGenBase.swampland && super.posY > 50.0D && super.posY < 70.0D && super.rand.nextFloat() < 0.5F && super.rand.nextFloat() < super.worldObj.getCurrentMoonPhaseFactor() && super.worldObj.getBlockLightValue(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ)) <= super.rand.nextInt(8)) {
               return super.getCanSpawnHere();
            }

            if(super.rand.nextInt(10) == 0 && var1.getRandomWithSeed(987234911L).nextInt(10) == 0 && super.posY < 40.0D) {
               return super.getCanSpawnHere();
            }
         }

         return false;
      }
   }

   protected float getSoundVolume() {
      return 0.4F * (float)this.getSlimeSize();
   }

   public int getVerticalFaceSpeed() {
      return 0;
   }

   protected boolean makesSoundOnJump() {
      return this.getSlimeSize() > 0;
   }

   protected boolean makesSoundOnLand() {
      return this.getSlimeSize() > 2;
   }
}
