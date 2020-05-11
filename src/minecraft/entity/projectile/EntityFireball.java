package net.minecraft.entity.projectile;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class EntityFireball extends Entity {

   private int field_145795_e = -1;
   private int field_145793_f = -1;
   private int field_145794_g = -1;
   private Block field_145796_h;
   private boolean inGround;
   public EntityLivingBase shootingEntity;
   private int ticksAlive;
   private int ticksInAir;
   public double accelerationX;
   public double accelerationY;
   public double accelerationZ;


   public EntityFireball(World var1) {
      super(var1);
      this.setSize(1.0F, 1.0F);
   }

   protected void entityInit() {}

   public boolean isInRangeToRenderDist(double var1) {
      double var3 = super.boundingBox.getAverageEdgeLength() * 4.0D;
      var3 *= 64.0D;
      return var1 < var3 * var3;
   }

   public EntityFireball(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1);
      this.setSize(1.0F, 1.0F);
      this.setLocationAndAngles(var2, var4, var6, super.rotationYaw, super.rotationPitch);
      this.setPosition(var2, var4, var6);
      double var14 = (double)MathHelper.sqrt_double(var8 * var8 + var10 * var10 + var12 * var12);
      this.accelerationX = var8 / var14 * 0.1D;
      this.accelerationY = var10 / var14 * 0.1D;
      this.accelerationZ = var12 / var14 * 0.1D;
   }

   public EntityFireball(World var1, EntityLivingBase var2, double var3, double var5, double var7) {
      super(var1);
      this.shootingEntity = var2;
      this.setSize(1.0F, 1.0F);
      this.setLocationAndAngles(var2.posX, var2.posY, var2.posZ, var2.rotationYaw, var2.rotationPitch);
      this.setPosition(super.posX, super.posY, super.posZ);
      super.yOffset = 0.0F;
      super.motionX = super.motionY = super.motionZ = 0.0D;
      var3 += super.rand.nextGaussian() * 0.4D;
      var5 += super.rand.nextGaussian() * 0.4D;
      var7 += super.rand.nextGaussian() * 0.4D;
      double var9 = (double)MathHelper.sqrt_double(var3 * var3 + var5 * var5 + var7 * var7);
      this.accelerationX = var3 / var9 * 0.1D;
      this.accelerationY = var5 / var9 * 0.1D;
      this.accelerationZ = var7 / var9 * 0.1D;
   }

   public void onUpdate() {
      if(!super.worldObj.isRemote && (this.shootingEntity != null && this.shootingEntity.isDead || !super.worldObj.blockExists((int)super.posX, (int)super.posY, (int)super.posZ))) {
         this.setDead();
      } else {
         super.onUpdate();
         this.setFire(1);
         if(this.inGround) {
            if(super.worldObj.getBlock(this.field_145795_e, this.field_145793_f, this.field_145794_g) == this.field_145796_h) {
               ++this.ticksAlive;
               if(this.ticksAlive == 600) {
                  this.setDead();
               }

               return;
            }

            this.inGround = false;
            super.motionX *= (double)(super.rand.nextFloat() * 0.2F);
            super.motionY *= (double)(super.rand.nextFloat() * 0.2F);
            super.motionZ *= (double)(super.rand.nextFloat() * 0.2F);
            this.ticksAlive = 0;
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

         Entity var4 = null;
         List var5 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.addCoord(super.motionX, super.motionY, super.motionZ).expand(1.0D, 1.0D, 1.0D));
         double var6 = 0.0D;

         for(int var8 = 0; var8 < var5.size(); ++var8) {
            Entity var9 = (Entity)var5.get(var8);
            if(var9.canBeCollidedWith() && (!var9.isEntityEqual(this.shootingEntity) || this.ticksInAir >= 25)) {
               float var10 = 0.3F;
               AxisAlignedBB var11 = var9.boundingBox.expand((double)var10, (double)var10, (double)var10);
               MovingObjectPosition var12 = var11.calculateIntercept(var1, var2);
               if(var12 != null) {
                  double var13 = var1.distanceTo(var12.hitVec);
                  if(var13 < var6 || var6 == 0.0D) {
                     var4 = var9;
                     var6 = var13;
                  }
               }
            }
         }

         if(var4 != null) {
            var3 = new MovingObjectPosition(var4);
         }

         if(var3 != null) {
            this.onImpact(var3);
         }

         super.posX += super.motionX;
         super.posY += super.motionY;
         super.posZ += super.motionZ;
         float var15 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
         super.rotationYaw = (float)(Math.atan2(super.motionZ, super.motionX) * 180.0D / 3.1415927410125732D) + 90.0F;

         for(super.rotationPitch = (float)(Math.atan2((double)var15, super.motionY) * 180.0D / 3.1415927410125732D) - 90.0F; super.rotationPitch - super.prevRotationPitch < -180.0F; super.prevRotationPitch -= 360.0F) {
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
         float var16 = this.getMotionFactor();
         if(this.isInWater()) {
            for(int var17 = 0; var17 < 4; ++var17) {
               float var18 = 0.25F;
               super.worldObj.spawnParticle("bubble", super.posX - super.motionX * (double)var18, super.posY - super.motionY * (double)var18, super.posZ - super.motionZ * (double)var18, super.motionX, super.motionY, super.motionZ);
            }

            var16 = 0.8F;
         }

         super.motionX += this.accelerationX;
         super.motionY += this.accelerationY;
         super.motionZ += this.accelerationZ;
         super.motionX *= (double)var16;
         super.motionY *= (double)var16;
         super.motionZ *= (double)var16;
         super.worldObj.spawnParticle("smoke", super.posX, super.posY + 0.5D, super.posZ, 0.0D, 0.0D, 0.0D);
         this.setPosition(super.posX, super.posY, super.posZ);
      }
   }

   protected float getMotionFactor() {
      return 0.95F;
   }

   protected abstract void onImpact(MovingObjectPosition var1);

   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setShort("xTile", (short)this.field_145795_e);
      var1.setShort("yTile", (short)this.field_145793_f);
      var1.setShort("zTile", (short)this.field_145794_g);
      var1.setByte("inTile", (byte)Block.getIdFromBlock(this.field_145796_h));
      var1.setByte("inGround", (byte)(this.inGround?1:0));
      var1.setTag("direction", this.newDoubleNBTList(new double[]{super.motionX, super.motionY, super.motionZ}));
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      this.field_145795_e = var1.getShort("xTile");
      this.field_145793_f = var1.getShort("yTile");
      this.field_145794_g = var1.getShort("zTile");
      this.field_145796_h = Block.getBlockById(var1.getByte("inTile") & 255);
      this.inGround = var1.getByte("inGround") == 1;
      if(var1.hasKey("direction", 9)) {
         NBTTagList var2 = var1.getTagList("direction", 6);
         super.motionX = var2.func_150309_d(0);
         super.motionY = var2.func_150309_d(1);
         super.motionZ = var2.func_150309_d(2);
      } else {
         this.setDead();
      }

   }

   public boolean canBeCollidedWith() {
      return true;
   }

   public float getCollisionBorderSize() {
      return 1.0F;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         this.setBeenAttacked();
         if(var1.getEntity() != null) {
            Vec3 var3 = var1.getEntity().getLookVec();
            if(var3 != null) {
               super.motionX = var3.xCoord;
               super.motionY = var3.yCoord;
               super.motionZ = var3.zCoord;
               this.accelerationX = super.motionX * 0.1D;
               this.accelerationY = super.motionY * 0.1D;
               this.accelerationZ = super.motionZ * 0.1D;
            }

            if(var1.getEntity() instanceof EntityLivingBase) {
               this.shootingEntity = (EntityLivingBase)var1.getEntity();
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public float getShadowSize() {
      return 0.0F;
   }

   public float getBrightness(float var1) {
      return 1.0F;
   }

   public int getBrightnessForRender(float var1) {
      return 15728880;
   }
}
