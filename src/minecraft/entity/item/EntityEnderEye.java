package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityEnderEye extends Entity {

   private double targetX;
   private double targetY;
   private double targetZ;
   private int despawnTimer;
   private boolean shatterOrDrop;


   public EntityEnderEye(World var1) {
      super(var1);
      this.setSize(0.25F, 0.25F);
   }

   protected void entityInit() {}

   public boolean isInRangeToRenderDist(double var1) {
      double var3 = super.boundingBox.getAverageEdgeLength() * 4.0D;
      var3 *= 64.0D;
      return var1 < var3 * var3;
   }

   public EntityEnderEye(World var1, double var2, double var4, double var6) {
      super(var1);
      this.despawnTimer = 0;
      this.setSize(0.25F, 0.25F);
      this.setPosition(var2, var4, var6);
      super.yOffset = 0.0F;
   }

   public void moveTowards(double var1, int var3, double var4) {
      double var6 = var1 - super.posX;
      double var8 = var4 - super.posZ;
      float var10 = MathHelper.sqrt_double(var6 * var6 + var8 * var8);
      if(var10 > 12.0F) {
         this.targetX = super.posX + var6 / (double)var10 * 12.0D;
         this.targetZ = super.posZ + var8 / (double)var10 * 12.0D;
         this.targetY = super.posY + 8.0D;
      } else {
         this.targetX = var1;
         this.targetY = (double)var3;
         this.targetZ = var4;
      }

      this.despawnTimer = 0;
      this.shatterOrDrop = super.rand.nextInt(5) > 0;
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
      super.posX += super.motionX;
      super.posY += super.motionY;
      super.posZ += super.motionZ;
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
      if(!super.worldObj.isRemote) {
         double var2 = this.targetX - super.posX;
         double var4 = this.targetZ - super.posZ;
         float var6 = (float)Math.sqrt(var2 * var2 + var4 * var4);
         float var7 = (float)Math.atan2(var4, var2);
         double var8 = (double)var1 + (double)(var6 - var1) * 0.0025D;
         if(var6 < 1.0F) {
            var8 *= 0.8D;
            super.motionY *= 0.8D;
         }

         super.motionX = Math.cos((double)var7) * var8;
         super.motionZ = Math.sin((double)var7) * var8;
         if(super.posY < this.targetY) {
            super.motionY += (1.0D - super.motionY) * 0.014999999664723873D;
         } else {
            super.motionY += (-1.0D - super.motionY) * 0.014999999664723873D;
         }
      }

      float var10 = 0.25F;
      if(this.isInWater()) {
         for(int var3 = 0; var3 < 4; ++var3) {
            super.worldObj.spawnParticle("bubble", super.posX - super.motionX * (double)var10, super.posY - super.motionY * (double)var10, super.posZ - super.motionZ * (double)var10, super.motionX, super.motionY, super.motionZ);
         }
      } else {
         super.worldObj.spawnParticle("portal", super.posX - super.motionX * (double)var10 + super.rand.nextDouble() * 0.6D - 0.3D, super.posY - super.motionY * (double)var10 - 0.5D, super.posZ - super.motionZ * (double)var10 + super.rand.nextDouble() * 0.6D - 0.3D, super.motionX, super.motionY, super.motionZ);
      }

      if(!super.worldObj.isRemote) {
         this.setPosition(super.posX, super.posY, super.posZ);
         ++this.despawnTimer;
         if(this.despawnTimer > 80 && !super.worldObj.isRemote) {
            this.setDead();
            if(this.shatterOrDrop) {
               super.worldObj.spawnEntityInWorld(new EntityItem(super.worldObj, super.posX, super.posY, super.posZ, new ItemStack(Items.ender_eye)));
            } else {
               super.worldObj.playAuxSFX(2003, (int)Math.round(super.posX), (int)Math.round(super.posY), (int)Math.round(super.posZ), 0);
            }
         }
      }

   }

   public void writeEntityToNBT(NBTTagCompound var1) {}

   public void readEntityFromNBT(NBTTagCompound var1) {}

   public float getShadowSize() {
      return 0.0F;
   }

   public float getBrightness(float var1) {
      return 1.0F;
   }

   public int getBrightnessForRender(float var1) {
      return 15728880;
   }

   public boolean canAttackWithItem() {
      return false;
   }
}
