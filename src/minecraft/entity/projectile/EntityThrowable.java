package net.minecraft.entity.projectile;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class EntityThrowable extends Entity implements IProjectile {

   private int field_145788_c = -1;
   private int field_145786_d = -1;
   private int field_145787_e = -1;
   private Block field_145785_f;
   protected boolean inGround;
   public int throwableShake;
   private EntityLivingBase thrower;
   private String throwerName;
   private int ticksInGround;
   private int ticksInAir;


   public EntityThrowable(World var1) {
      super(var1);
      this.setSize(0.25F, 0.25F);
   }

   protected void entityInit() {}

   public boolean isInRangeToRenderDist(double var1) {
      double var3 = super.boundingBox.getAverageEdgeLength() * 4.0D;
      var3 *= 64.0D;
      return var1 < var3 * var3;
   }

   public EntityThrowable(World var1, EntityLivingBase var2) {
      super(var1);
      this.thrower = var2;
      this.setSize(0.25F, 0.25F);
      this.setLocationAndAngles(var2.posX, var2.posY + (double)var2.getEyeHeight(), var2.posZ, var2.rotationYaw, var2.rotationPitch);
      super.posX -= (double)(MathHelper.cos(super.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
      super.posY -= 0.10000000149011612D;
      super.posZ -= (double)(MathHelper.sin(super.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
      this.setPosition(super.posX, super.posY, super.posZ);
      super.yOffset = 0.0F;
      float var3 = 0.4F;
      super.motionX = (double)(-MathHelper.sin(super.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(super.rotationPitch / 180.0F * 3.1415927F) * var3);
      super.motionZ = (double)(MathHelper.cos(super.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(super.rotationPitch / 180.0F * 3.1415927F) * var3);
      super.motionY = (double)(-MathHelper.sin((super.rotationPitch + this.func_70183_g()) / 180.0F * 3.1415927F) * var3);
      this.setThrowableHeading(super.motionX, super.motionY, super.motionZ, this.func_70182_d(), 1.0F);
   }

   public EntityThrowable(World var1, double var2, double var4, double var6) {
      super(var1);
      this.ticksInGround = 0;
      this.setSize(0.25F, 0.25F);
      this.setPosition(var2, var4, var6);
      super.yOffset = 0.0F;
   }

   protected float func_70182_d() {
      return 1.5F;
   }

   protected float func_70183_g() {
      return 0.0F;
   }

   public void setThrowableHeading(double var1, double var3, double var5, float var7, float var8) {
      float var9 = MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5);
      var1 /= (double)var9;
      var3 /= (double)var9;
      var5 /= (double)var9;
      var1 += super.rand.nextGaussian() * 0.007499999832361937D * (double)var8;
      var3 += super.rand.nextGaussian() * 0.007499999832361937D * (double)var8;
      var5 += super.rand.nextGaussian() * 0.007499999832361937D * (double)var8;
      var1 *= (double)var7;
      var3 *= (double)var7;
      var5 *= (double)var7;
      super.motionX = var1;
      super.motionY = var3;
      super.motionZ = var5;
      float var10 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
      super.prevRotationYaw = super.rotationYaw = (float)(Math.atan2(var1, var5) * 180.0D / 3.1415927410125732D);
      super.prevRotationPitch = super.rotationPitch = (float)(Math.atan2(var3, (double)var10) * 180.0D / 3.1415927410125732D);
      this.ticksInGround = 0;
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
      if(this.throwableShake > 0) {
         --this.throwableShake;
      }

      if(this.inGround) {
         if(super.worldObj.getBlock(this.field_145788_c, this.field_145786_d, this.field_145787_e) == this.field_145785_f) {
            ++this.ticksInGround;
            if(this.ticksInGround == 1200) {
               this.setDead();
            }

            return;
         }

         this.inGround = false;
         super.motionX *= (double)(super.rand.nextFloat() * 0.2F);
         super.motionY *= (double)(super.rand.nextFloat() * 0.2F);
         super.motionZ *= (double)(super.rand.nextFloat() * 0.2F);
         this.ticksInGround = 0;
         this.ticksInAir = 0;
      } else {
         ++this.ticksInAir;
      }

      Vec3 var1 = Vec3.createVectorHelper(super.posX, super.posY, super.posZ);
      Vec3 var2 = Vec3.createVectorHelper(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ);
      MovingObjectPosition var3 = super.worldObj.rayTraceBlocks(var1, var2);
      var1 = Vec3.createVectorHelper(super.posX, super.posY, super.posZ);
      var2 = Vec3.createVectorHelper(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ);
      if(var3 != null) {
         var2 = Vec3.createVectorHelper(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
      }

      if(!super.worldObj.isRemote) {
         Entity var4 = null;
         List var5 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.addCoord(super.motionX, super.motionY, super.motionZ).expand(1.0D, 1.0D, 1.0D));
         double var6 = 0.0D;
         EntityLivingBase var8 = this.getThrower();

         for(int var9 = 0; var9 < var5.size(); ++var9) {
            Entity var10 = (Entity)var5.get(var9);
            if(var10.canBeCollidedWith() && (var10 != var8 || this.ticksInAir >= 5)) {
               float var11 = 0.3F;
               AxisAlignedBB var12 = var10.boundingBox.expand((double)var11, (double)var11, (double)var11);
               MovingObjectPosition var13 = var12.calculateIntercept(var1, var2);
               if(var13 != null) {
                  double var14 = var1.distanceTo(var13.hitVec);
                  if(var14 < var6 || var6 == 0.0D) {
                     var4 = var10;
                     var6 = var14;
                  }
               }
            }
         }

         if(var4 != null) {
            var3 = new MovingObjectPosition(var4);
         }
      }

      if(var3 != null) {
         if(var3.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK && super.worldObj.getBlock(var3.blockX, var3.blockY, var3.blockZ) == Blocks.portal) {
            this.setInPortal();
         } else {
            this.onImpact(var3);
         }
      }

      super.posX += super.motionX;
      super.posY += super.motionY;
      super.posZ += super.motionZ;
      float var16 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
      super.rotationYaw = (float)(Math.atan2(super.motionX, super.motionZ) * 180.0D / 3.1415927410125732D);

      for(super.rotationPitch = (float)(Math.atan2(super.motionY, (double)var16) * 180.0D / 3.1415927410125732D); super.rotationPitch - super.prevRotationPitch < -180.0F; super.prevRotationPitch -= 360.0F) {
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
      float var17 = 0.99F;
      float var18 = this.getGravityVelocity();
      if(this.isInWater()) {
         for(int var7 = 0; var7 < 4; ++var7) {
            float var19 = 0.25F;
            super.worldObj.spawnParticle("bubble", super.posX - super.motionX * (double)var19, super.posY - super.motionY * (double)var19, super.posZ - super.motionZ * (double)var19, super.motionX, super.motionY, super.motionZ);
         }

         var17 = 0.8F;
      }

      super.motionX *= (double)var17;
      super.motionY *= (double)var17;
      super.motionZ *= (double)var17;
      super.motionY -= (double)var18;
      this.setPosition(super.posX, super.posY, super.posZ);
   }

   protected float getGravityVelocity() {
      return 0.03F;
   }

   protected abstract void onImpact(MovingObjectPosition var1);

   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setShort("xTile", (short)this.field_145788_c);
      var1.setShort("yTile", (short)this.field_145786_d);
      var1.setShort("zTile", (short)this.field_145787_e);
      var1.setByte("inTile", (byte)Block.getIdFromBlock(this.field_145785_f));
      var1.setByte("shake", (byte)this.throwableShake);
      var1.setByte("inGround", (byte)(this.inGround?1:0));
      if((this.throwerName == null || this.throwerName.length() == 0) && this.thrower != null && this.thrower instanceof EntityPlayer) {
         this.throwerName = this.thrower.getCommandSenderName();
      }

      var1.setString("ownerName", this.throwerName == null?"":this.throwerName);
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      this.field_145788_c = var1.getShort("xTile");
      this.field_145786_d = var1.getShort("yTile");
      this.field_145787_e = var1.getShort("zTile");
      this.field_145785_f = Block.getBlockById(var1.getByte("inTile") & 255);
      this.throwableShake = var1.getByte("shake") & 255;
      this.inGround = var1.getByte("inGround") == 1;
      this.throwerName = var1.getString("ownerName");
      if(this.throwerName != null && this.throwerName.length() == 0) {
         this.throwerName = null;
      }

   }

   public float getShadowSize() {
      return 0.0F;
   }

   public EntityLivingBase getThrower() {
      if(this.thrower == null && this.throwerName != null && this.throwerName.length() > 0) {
         this.thrower = super.worldObj.getPlayerEntityByName(this.throwerName);
      }

      return this.thrower;
   }
}
