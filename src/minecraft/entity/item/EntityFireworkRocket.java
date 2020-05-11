package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFireworkRocket extends Entity {

   private int fireworkAge;
   private int lifetime;


   public EntityFireworkRocket(World var1) {
      super(var1);
      this.setSize(0.25F, 0.25F);
   }

   protected void entityInit() {
      super.dataWatcher.addObjectByDataType(8, 5);
   }

   public boolean isInRangeToRenderDist(double var1) {
      return var1 < 4096.0D;
   }

   public EntityFireworkRocket(World var1, double var2, double var4, double var6, ItemStack var8) {
      super(var1);
      this.fireworkAge = 0;
      this.setSize(0.25F, 0.25F);
      this.setPosition(var2, var4, var6);
      super.yOffset = 0.0F;
      int var9 = 1;
      if(var8 != null && var8.hasTagCompound()) {
         super.dataWatcher.updateObject(8, var8);
         NBTTagCompound var10 = var8.getTagCompound();
         NBTTagCompound var11 = var10.getCompoundTag("Fireworks");
         if(var11 != null) {
            var9 += var11.getByte("Flight");
         }
      }

      super.motionX = super.rand.nextGaussian() * 0.001D;
      super.motionZ = super.rand.nextGaussian() * 0.001D;
      super.motionY = 0.05D;
      this.lifetime = 10 * var9 + super.rand.nextInt(6) + super.rand.nextInt(7);
   }

   public void setVelocity(double var1, double var3, double var5) {
      super.motionX = var1;
      super.motionY = var3;
      super.motionZ = var5;
      if(super.prevRotationPitch == 0.0F && super.prevRotationYaw == 0.0F) {
         float var7 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
         super.prevRotationYaw = super.rotationYaw = (float)(Math.atan2(var1, var5) * 180.0D / 3.1415927410125732D);
         super.prevRotationPitch = super.rotationPitch = (float)(Math.atan2(var3, (double)var7) * 180.0D / 3.1415927410125732D);
      }

   }

   public void onUpdate() {
      super.lastTickPosX = super.posX;
      super.lastTickPosY = super.posY;
      super.lastTickPosZ = super.posZ;
      super.onUpdate();
      super.motionX *= 1.15D;
      super.motionZ *= 1.15D;
      super.motionY += 0.04D;
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      float var1 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
      super.rotationYaw = (float)(Math.atan2(super.motionX, super.motionZ) * 180.0D / 3.1415927410125732D);

      for(super.rotationPitch = (float)(Math.atan2(super.motionY, (double)var1) * 180.0D / 3.1415927410125732D); super.rotationPitch - super.prevRotationPitch < -180.0F; super.prevRotationPitch -= 360.0F) {
         ;
      }

      while(super.rotationPitch - super.prevRotationPitch >= 180.0F) {
         super.prevRotationPitch += 360.0F;
      }

      while(super.rotationYaw - super.prevRotationYaw < -180.0F) {
         super.prevRotationYaw -= 360.0F;
      }

      while(super.rotationYaw - super.prevRotationYaw >= 180.0F) {
         super.prevRotationYaw += 360.0F;
      }

      super.rotationPitch = super.prevRotationPitch + (super.rotationPitch - super.prevRotationPitch) * 0.2F;
      super.rotationYaw = super.prevRotationYaw + (super.rotationYaw - super.prevRotationYaw) * 0.2F;
      if(this.fireworkAge == 0) {
         super.worldObj.playSoundAtEntity(this, "fireworks.launch", 3.0F, 1.0F);
      }

      ++this.fireworkAge;
      if(super.worldObj.isRemote && this.fireworkAge % 2 < 2) {
         super.worldObj.spawnParticle("fireworksSpark", super.posX, super.posY - 0.3D, super.posZ, super.rand.nextGaussian() * 0.05D, -super.motionY * 0.5D, super.rand.nextGaussian() * 0.05D);
      }

      if(!super.worldObj.isRemote && this.fireworkAge > this.lifetime) {
         super.worldObj.setEntityState(this, (byte)17);
         this.setDead();
      }

   }

   public void handleHealthUpdate(byte var1) {
      if(var1 == 17 && super.worldObj.isRemote) {
         ItemStack var2 = super.dataWatcher.getWatchableObjectItemStack(8);
         NBTTagCompound var3 = null;
         if(var2 != null && var2.hasTagCompound()) {
            var3 = var2.getTagCompound().getCompoundTag("Fireworks");
         }

         super.worldObj.makeFireworks(super.posX, super.posY, super.posZ, super.motionX, super.motionY, super.motionZ, var3);
      }

      super.handleHealthUpdate(var1);
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setInteger("Life", this.fireworkAge);
      var1.setInteger("LifeTime", this.lifetime);
      ItemStack var2 = super.dataWatcher.getWatchableObjectItemStack(8);
      if(var2 != null) {
         NBTTagCompound var3 = new NBTTagCompound();
         var2.writeToNBT(var3);
         var1.setTag("FireworksItem", var3);
      }

   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      this.fireworkAge = var1.getInteger("Life");
      this.lifetime = var1.getInteger("LifeTime");
      NBTTagCompound var2 = var1.getCompoundTag("FireworksItem");
      if(var2 != null) {
         ItemStack var3 = ItemStack.loadItemStackFromNBT(var2);
         if(var3 != null) {
            super.dataWatcher.updateObject(8, var3);
         }
      }

   }

   public float getShadowSize() {
      return 0.0F;
   }

   public float getBrightness(float var1) {
      return super.getBrightness(var1);
   }

   public int getBrightnessForRender(float var1) {
      return super.getBrightnessForRender(var1);
   }

   public boolean canAttackWithItem() {
      return false;
   }
}
