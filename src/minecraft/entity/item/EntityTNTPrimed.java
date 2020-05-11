package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityTNTPrimed extends Entity {

   public int fuse;
   private EntityLivingBase tntPlacedBy;


   public EntityTNTPrimed(World var1) {
      super(var1);
      super.preventEntitySpawning = true;
      this.setSize(0.98F, 0.98F);
      super.yOffset = super.height / 2.0F;
   }

   public EntityTNTPrimed(World var1, double var2, double var4, double var6, EntityLivingBase var8) {
      this(var1);
      this.setPosition(var2, var4, var6);
      float var9 = (float)(Math.random() * 3.1415927410125732D * 2.0D);
      super.motionX = (double)(-((float)Math.sin((double)var9)) * 0.02F);
      super.motionY = 0.20000000298023224D;
      super.motionZ = (double)(-((float)Math.cos((double)var9)) * 0.02F);
      this.fuse = 80;
      super.prevPosX = var2;
      super.prevPosY = var4;
      super.prevPosZ = var6;
      this.tntPlacedBy = var8;
   }

   protected void entityInit() {}

   protected boolean canTriggerWalking() {
      return false;
   }

   public boolean canBeCollidedWith() {
      return !super.isDead;
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      super.motionY -= 0.03999999910593033D;
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      super.motionX *= 0.9800000190734863D;
      super.motionY *= 0.9800000190734863D;
      super.motionZ *= 0.9800000190734863D;
      if(super.onGround) {
         super.motionX *= 0.699999988079071D;
         super.motionZ *= 0.699999988079071D;
         super.motionY *= -0.5D;
      }

      if(this.fuse-- <= 0) {
         this.setDead();
         if(!super.worldObj.isRemote) {
            this.explode();
         }
      } else {
         super.worldObj.spawnParticle("smoke", super.posX, super.posY + 0.5D, super.posZ, 0.0D, 0.0D, 0.0D);
      }

   }

   private void explode() {
      float var1 = 4.0F;
      super.worldObj.createExplosion(this, super.posX, super.posY, super.posZ, var1, true);
   }

   protected void writeEntityToNBT(NBTTagCompound var1) {
      var1.setByte("Fuse", (byte)this.fuse);
   }

   protected void readEntityFromNBT(NBTTagCompound var1) {
      this.fuse = var1.getByte("Fuse");
   }

   public float getShadowSize() {
      return 0.0F;
   }

   public EntityLivingBase getTntPlacedBy() {
      return this.tntPlacedBy;
   }
}
