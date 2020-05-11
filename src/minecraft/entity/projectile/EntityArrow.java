package net.minecraft.entity.projectile;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityArrow extends Entity implements IProjectile {

   private int field_145791_d = -1;
   private int field_145792_e = -1;
   private int field_145789_f = -1;
   private Block field_145790_g;
   private int inData;
   private boolean inGround;
   public int canBePickedUp;
   public int arrowShake;
   public Entity shootingEntity;
   private int ticksInGround;
   private int ticksInAir;
   private double damage = 2.0D;
   private int knockbackStrength;


   public EntityArrow(World var1) {
      super(var1);
      super.renderDistanceWeight = 10.0D;
      this.setSize(0.5F, 0.5F);
   }

   public EntityArrow(World var1, double var2, double var4, double var6) {
      super(var1);
      super.renderDistanceWeight = 10.0D;
      this.setSize(0.5F, 0.5F);
      this.setPosition(var2, var4, var6);
      super.yOffset = 0.0F;
   }

   public EntityArrow(World var1, EntityLivingBase var2, EntityLivingBase var3, float var4, float var5) {
      super(var1);
      super.renderDistanceWeight = 10.0D;
      this.shootingEntity = var2;
      if(var2 instanceof EntityPlayer) {
         this.canBePickedUp = 1;
      }

      super.posY = var2.posY + (double)var2.getEyeHeight() - 0.10000000149011612D;
      double var6 = var3.posX - var2.posX;
      double var8 = var3.boundingBox.minY + (double)(var3.height / 3.0F) - super.posY;
      double var10 = var3.posZ - var2.posZ;
      double var12 = (double)MathHelper.sqrt_double(var6 * var6 + var10 * var10);
      if(var12 >= 1.0E-7D) {
         float var14 = (float)(Math.atan2(var10, var6) * 180.0D / 3.1415927410125732D) - 90.0F;
         float var15 = (float)(-(Math.atan2(var8, var12) * 180.0D / 3.1415927410125732D));
         double var16 = var6 / var12;
         double var18 = var10 / var12;
         this.setLocationAndAngles(var2.posX + var16, super.posY, var2.posZ + var18, var14, var15);
         super.yOffset = 0.0F;
         float var20 = (float)var12 * 0.2F;
         this.setThrowableHeading(var6, var8 + (double)var20, var10, var4, var5);
      }
   }

   public EntityArrow(World var1, EntityLivingBase var2, float var3) {
      super(var1);
      super.renderDistanceWeight = 10.0D;
      this.shootingEntity = var2;
      if(var2 instanceof EntityPlayer) {
         this.canBePickedUp = 1;
      }

      this.setSize(0.5F, 0.5F);
      this.setLocationAndAngles(var2.posX, var2.posY + (double)var2.getEyeHeight(), var2.posZ, var2.rotationYaw, var2.rotationPitch);
      super.posX -= (double)(MathHelper.cos(super.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
      super.posY -= 0.10000000149011612D;
      super.posZ -= (double)(MathHelper.sin(super.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
      this.setPosition(super.posX, super.posY, super.posZ);
      super.yOffset = 0.0F;
      super.motionX = (double)(-MathHelper.sin(super.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(super.rotationPitch / 180.0F * 3.1415927F));
      super.motionZ = (double)(MathHelper.cos(super.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(super.rotationPitch / 180.0F * 3.1415927F));
      super.motionY = (double)(-MathHelper.sin(super.rotationPitch / 180.0F * 3.1415927F));
      this.setThrowableHeading(super.motionX, super.motionY, super.motionZ, var3 * 1.5F, 1.0F);
   }

   protected void entityInit() {
      super.dataWatcher.addObject(16, Byte.valueOf((byte)0));
   }

   public void setThrowableHeading(double var1, double var3, double var5, float var7, float var8) {
      float var9 = MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5);
      var1 /= (double)var9;
      var3 /= (double)var9;
      var5 /= (double)var9;
      var1 += super.rand.nextGaussian() * (double)(super.rand.nextBoolean()?-1:1) * 0.007499999832361937D * (double)var8;
      var3 += super.rand.nextGaussian() * (double)(super.rand.nextBoolean()?-1:1) * 0.007499999832361937D * (double)var8;
      var5 += super.rand.nextGaussian() * (double)(super.rand.nextBoolean()?-1:1) * 0.007499999832361937D * (double)var8;
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

   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9) {
      this.setPosition(var1, var3, var5);
      this.setRotation(var7, var8);
   }

   public void setVelocity(double var1, double var3, double var5) {
      super.motionX = var1;
      super.motionY = var3;
      super.motionZ = var5;
      if(super.prevRotationPitch == 0.0F && super.prevRotationYaw == 0.0F) {
         float var7 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
         super.prevRotationYaw = super.rotationYaw = (float)(Math.atan2(var1, var5) * 180.0D / 3.1415927410125732D);
         super.prevRotationPitch = super.rotationPitch = (float)(Math.atan2(var3, (double)var7) * 180.0D / 3.1415927410125732D);
         super.prevRotationPitch = super.rotationPitch;
         super.prevRotationYaw = super.rotationYaw;
         this.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
         this.ticksInGround = 0;
      }

   }

   public void onUpdate() {
      super.onUpdate();
      if(super.prevRotationPitch == 0.0F && super.prevRotationYaw == 0.0F) {
         float var1 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
         super.prevRotationYaw = super.rotationYaw = (float)(Math.atan2(super.motionX, super.motionZ) * 180.0D / 3.1415927410125732D);
         super.prevRotationPitch = super.rotationPitch = (float)(Math.atan2(super.motionY, (double)var1) * 180.0D / 3.1415927410125732D);
      }

      Block var16 = super.worldObj.getBlock(this.field_145791_d, this.field_145792_e, this.field_145789_f);
      if(var16.getMaterial() != Material.air) {
         var16.setBlockBoundsBasedOnState(super.worldObj, this.field_145791_d, this.field_145792_e, this.field_145789_f);
         AxisAlignedBB var2 = var16.getCollisionBoundingBoxFromPool(super.worldObj, this.field_145791_d, this.field_145792_e, this.field_145789_f);
         if(var2 != null && var2.isVecInside(Vec3.createVectorHelper(super.posX, super.posY, super.posZ))) {
            this.inGround = true;
         }
      }

      if(this.arrowShake > 0) {
         --this.arrowShake;
      }

      if(this.inGround) {
         int var18 = super.worldObj.getBlockMetadata(this.field_145791_d, this.field_145792_e, this.field_145789_f);
         if(var16 == this.field_145790_g && var18 == this.inData) {
            ++this.ticksInGround;
            if(this.ticksInGround == 1200) {
               this.setDead();
            }

         } else {
            this.inGround = false;
            super.motionX *= (double)(super.rand.nextFloat() * 0.2F);
            super.motionY *= (double)(super.rand.nextFloat() * 0.2F);
            super.motionZ *= (double)(super.rand.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
         }
      } else {
         ++this.ticksInAir;
         Vec3 var17 = Vec3.createVectorHelper(super.posX, super.posY, super.posZ);
         Vec3 var3 = Vec3.createVectorHelper(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ);
         MovingObjectPosition var4 = super.worldObj.func_147447_a(var17, var3, false, true, false);
         var17 = Vec3.createVectorHelper(super.posX, super.posY, super.posZ);
         var3 = Vec3.createVectorHelper(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ);
         if(var4 != null) {
            var3 = Vec3.createVectorHelper(var4.hitVec.xCoord, var4.hitVec.yCoord, var4.hitVec.zCoord);
         }

         Entity var5 = null;
         List var6 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.addCoord(super.motionX, super.motionY, super.motionZ).expand(1.0D, 1.0D, 1.0D));
         double var7 = 0.0D;

         int var9;
         float var11;
         for(var9 = 0; var9 < var6.size(); ++var9) {
            Entity var10 = (Entity)var6.get(var9);
            if(var10.canBeCollidedWith() && (var10 != this.shootingEntity || this.ticksInAir >= 5)) {
               var11 = 0.3F;
               AxisAlignedBB var12 = var10.boundingBox.expand((double)var11, (double)var11, (double)var11);
               MovingObjectPosition var13 = var12.calculateIntercept(var17, var3);
               if(var13 != null) {
                  double var14 = var17.distanceTo(var13.hitVec);
                  if(var14 < var7 || var7 == 0.0D) {
                     var5 = var10;
                     var7 = var14;
                  }
               }
            }
         }

         if(var5 != null) {
            var4 = new MovingObjectPosition(var5);
         }

         if(var4 != null && var4.entityHit != null && var4.entityHit instanceof EntityPlayer) {
            EntityPlayer var19 = (EntityPlayer)var4.entityHit;
            if(var19.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(var19)) {
               var4 = null;
            }
         }

         float var20;
         float var26;
         if(var4 != null) {
            if(var4.entityHit != null) {
               var20 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionY * super.motionY + super.motionZ * super.motionZ);
               int var21 = MathHelper.ceiling_double_int((double)var20 * this.damage);
               if(this.getIsCritical()) {
                  var21 += super.rand.nextInt(var21 / 2 + 2);
               }

               DamageSource var22 = null;
               if(this.shootingEntity == null) {
                  var22 = DamageSource.causeArrowDamage(this, this);
               } else {
                  var22 = DamageSource.causeArrowDamage(this, this.shootingEntity);
               }

               if(this.isBurning() && !(var4.entityHit instanceof EntityEnderman)) {
                  var4.entityHit.setFire(5);
               }

               if(var4.entityHit.attackEntityFrom(var22, (float)var21)) {
                  if(var4.entityHit instanceof EntityLivingBase) {
                     EntityLivingBase var24 = (EntityLivingBase)var4.entityHit;
                     if(!super.worldObj.isRemote) {
                        var24.setArrowCountInEntity(var24.getArrowCountInEntity() + 1);
                     }

                     if(this.knockbackStrength > 0) {
                        var26 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
                        if(var26 > 0.0F) {
                           var4.entityHit.addVelocity(super.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)var26, 0.1D, super.motionZ * (double)this.knockbackStrength * 0.6000000238418579D / (double)var26);
                        }
                     }

                     if(this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
                        EnchantmentHelper.func_151384_a(var24, this.shootingEntity);
                        EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, var24);
                     }

                     if(this.shootingEntity != null && var4.entityHit != this.shootingEntity && var4.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
                     }
                  }

                  this.playSound("random.bowhit", 1.0F, 1.2F / (super.rand.nextFloat() * 0.2F + 0.9F));
                  if(!(var4.entityHit instanceof EntityEnderman)) {
                     this.setDead();
                  }
               } else {
                  super.motionX *= -0.10000000149011612D;
                  super.motionY *= -0.10000000149011612D;
                  super.motionZ *= -0.10000000149011612D;
                  super.rotationYaw += 180.0F;
                  super.prevRotationYaw += 180.0F;
                  this.ticksInAir = 0;
               }
            } else {
               this.field_145791_d = var4.blockX;
               this.field_145792_e = var4.blockY;
               this.field_145789_f = var4.blockZ;
               this.field_145790_g = super.worldObj.getBlock(this.field_145791_d, this.field_145792_e, this.field_145789_f);
               this.inData = super.worldObj.getBlockMetadata(this.field_145791_d, this.field_145792_e, this.field_145789_f);
               super.motionX = (double)((float)(var4.hitVec.xCoord - super.posX));
               super.motionY = (double)((float)(var4.hitVec.yCoord - super.posY));
               super.motionZ = (double)((float)(var4.hitVec.zCoord - super.posZ));
               var20 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionY * super.motionY + super.motionZ * super.motionZ);
               super.posX -= super.motionX / (double)var20 * 0.05000000074505806D;
               super.posY -= super.motionY / (double)var20 * 0.05000000074505806D;
               super.posZ -= super.motionZ / (double)var20 * 0.05000000074505806D;
               this.playSound("random.bowhit", 1.0F, 1.2F / (super.rand.nextFloat() * 0.2F + 0.9F));
               this.inGround = true;
               this.arrowShake = 7;
               this.setIsCritical(false);
               if(this.field_145790_g.getMaterial() != Material.air) {
                  this.field_145790_g.onEntityCollidedWithBlock(super.worldObj, this.field_145791_d, this.field_145792_e, this.field_145789_f, this);
               }
            }
         }

         if(this.getIsCritical()) {
            for(var9 = 0; var9 < 4; ++var9) {
               super.worldObj.spawnParticle("crit", super.posX + super.motionX * (double)var9 / 4.0D, super.posY + super.motionY * (double)var9 / 4.0D, super.posZ + super.motionZ * (double)var9 / 4.0D, -super.motionX, -super.motionY + 0.2D, -super.motionZ);
            }
         }

         super.posX += super.motionX;
         super.posY += super.motionY;
         super.posZ += super.motionZ;
         var20 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
         super.rotationYaw = (float)(Math.atan2(super.motionX, super.motionZ) * 180.0D / 3.1415927410125732D);

         for(super.rotationPitch = (float)(Math.atan2(super.motionY, (double)var20) * 180.0D / 3.1415927410125732D); super.rotationPitch - super.prevRotationPitch < -180.0F; super.prevRotationPitch -= 360.0F) {
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
         float var23 = 0.99F;
         var11 = 0.05F;
         if(this.isInWater()) {
            for(int var25 = 0; var25 < 4; ++var25) {
               var26 = 0.25F;
               super.worldObj.spawnParticle("bubble", super.posX - super.motionX * (double)var26, super.posY - super.motionY * (double)var26, super.posZ - super.motionZ * (double)var26, super.motionX, super.motionY, super.motionZ);
            }

            var23 = 0.8F;
         }

         if(this.isWet()) {
            this.extinguish();
         }

         super.motionX *= (double)var23;
         super.motionY *= (double)var23;
         super.motionZ *= (double)var23;
         super.motionY -= (double)var11;
         this.setPosition(super.posX, super.posY, super.posZ);
         this.func_145775_I();
      }
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setShort("xTile", (short)this.field_145791_d);
      var1.setShort("yTile", (short)this.field_145792_e);
      var1.setShort("zTile", (short)this.field_145789_f);
      var1.setShort("life", (short)this.ticksInGround);
      var1.setByte("inTile", (byte)Block.getIdFromBlock(this.field_145790_g));
      var1.setByte("inData", (byte)this.inData);
      var1.setByte("shake", (byte)this.arrowShake);
      var1.setByte("inGround", (byte)(this.inGround?1:0));
      var1.setByte("pickup", (byte)this.canBePickedUp);
      var1.setDouble("damage", this.damage);
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      this.field_145791_d = var1.getShort("xTile");
      this.field_145792_e = var1.getShort("yTile");
      this.field_145789_f = var1.getShort("zTile");
      this.ticksInGround = var1.getShort("life");
      this.field_145790_g = Block.getBlockById(var1.getByte("inTile") & 255);
      this.inData = var1.getByte("inData") & 255;
      this.arrowShake = var1.getByte("shake") & 255;
      this.inGround = var1.getByte("inGround") == 1;
      if(var1.hasKey("damage", 99)) {
         this.damage = var1.getDouble("damage");
      }

      if(var1.hasKey("pickup", 99)) {
         this.canBePickedUp = var1.getByte("pickup");
      } else if(var1.hasKey("player", 99)) {
         this.canBePickedUp = var1.getBoolean("player")?1:0;
      }

   }

   public void onCollideWithPlayer(EntityPlayer var1) {
      if(!super.worldObj.isRemote && this.inGround && this.arrowShake <= 0) {
         boolean var2 = this.canBePickedUp == 1 || this.canBePickedUp == 2 && var1.capabilities.isCreativeMode;
         if(this.canBePickedUp == 1 && !var1.inventory.addItemStackToInventory(new ItemStack(Items.arrow, 1))) {
            var2 = false;
         }

         if(var2) {
            this.playSound("random.pop", 0.2F, ((super.rand.nextFloat() - super.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            var1.onItemPickup(this, 1);
            this.setDead();
         }

      }
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   public float getShadowSize() {
      return 0.0F;
   }

   public void setDamage(double var1) {
      this.damage = var1;
   }

   public double getDamage() {
      return this.damage;
   }

   public void setKnockbackStrength(int var1) {
      this.knockbackStrength = var1;
   }

   public boolean canAttackWithItem() {
      return false;
   }

   public void setIsCritical(boolean var1) {
      byte var2 = super.dataWatcher.getWatchableObjectByte(16);
      if(var1) {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 1)));
      } else {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -2)));
      }

   }

   public boolean getIsCritical() {
      byte var1 = super.dataWatcher.getWatchableObjectByte(16);
      return (var1 & 1) != 0;
   }
}
