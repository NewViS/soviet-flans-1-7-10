package net.minecraft.entity.boss;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityDragon extends EntityLiving implements IBossDisplayData, IEntityMultiPart, IMob {

   public double targetX;
   public double targetY;
   public double targetZ;
   public double[][] ringBuffer = new double[64][3];
   public int ringBufferIndex = -1;
   public EntityDragonPart[] dragonPartArray;
   public EntityDragonPart dragonPartHead;
   public EntityDragonPart dragonPartBody;
   public EntityDragonPart dragonPartTail1;
   public EntityDragonPart dragonPartTail2;
   public EntityDragonPart dragonPartTail3;
   public EntityDragonPart dragonPartWing1;
   public EntityDragonPart dragonPartWing2;
   public float prevAnimTime;
   public float animTime;
   public boolean forceNewTarget;
   public boolean slowed;
   private Entity target;
   public int deathTicks;
   public EntityEnderCrystal healingEnderCrystal;


   public EntityDragon(World var1) {
      super(var1);
      this.dragonPartArray = new EntityDragonPart[]{this.dragonPartHead = new EntityDragonPart(this, "head", 6.0F, 6.0F), this.dragonPartBody = new EntityDragonPart(this, "body", 8.0F, 8.0F), this.dragonPartTail1 = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail2 = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail3 = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartWing1 = new EntityDragonPart(this, "wing", 4.0F, 4.0F), this.dragonPartWing2 = new EntityDragonPart(this, "wing", 4.0F, 4.0F)};
      this.setHealth(this.getMaxHealth());
      this.setSize(16.0F, 8.0F);
      super.noClip = true;
      super.isImmuneToFire = true;
      this.targetY = 100.0D;
      super.ignoreFrustumCheck = true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
   }

   protected void entityInit() {
      super.entityInit();
   }

   public double[] getMovementOffsets(int var1, float var2) {
      if(this.getHealth() <= 0.0F) {
         var2 = 0.0F;
      }

      var2 = 1.0F - var2;
      int var3 = this.ringBufferIndex - var1 * 1 & 63;
      int var4 = this.ringBufferIndex - var1 * 1 - 1 & 63;
      double[] var5 = new double[3];
      double var6 = this.ringBuffer[var3][0];
      double var8 = MathHelper.wrapAngleTo180_double(this.ringBuffer[var4][0] - var6);
      var5[0] = var6 + var8 * (double)var2;
      var6 = this.ringBuffer[var3][1];
      var8 = this.ringBuffer[var4][1] - var6;
      var5[1] = var6 + var8 * (double)var2;
      var5[2] = this.ringBuffer[var3][2] + (this.ringBuffer[var4][2] - this.ringBuffer[var3][2]) * (double)var2;
      return var5;
   }

   public void onLivingUpdate() {
      float var1;
      float var2;
      if(super.worldObj.isRemote) {
         var1 = MathHelper.cos(this.animTime * 3.1415927F * 2.0F);
         var2 = MathHelper.cos(this.prevAnimTime * 3.1415927F * 2.0F);
         if(var2 <= -0.3F && var1 >= -0.3F) {
            super.worldObj.playSound(super.posX, super.posY, super.posZ, "mob.enderdragon.wings", 5.0F, 0.8F + super.rand.nextFloat() * 0.3F, false);
         }
      }

      this.prevAnimTime = this.animTime;
      float var3;
      if(this.getHealth() <= 0.0F) {
         var1 = (super.rand.nextFloat() - 0.5F) * 8.0F;
         var2 = (super.rand.nextFloat() - 0.5F) * 4.0F;
         var3 = (super.rand.nextFloat() - 0.5F) * 8.0F;
         super.worldObj.spawnParticle("largeexplode", super.posX + (double)var1, super.posY + 2.0D + (double)var2, super.posZ + (double)var3, 0.0D, 0.0D, 0.0D);
      } else {
         this.updateDragonEnderCrystal();
         var1 = 0.2F / (MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ) * 10.0F + 1.0F);
         var1 *= (float)Math.pow(2.0D, super.motionY);
         if(this.slowed) {
            this.animTime += var1 * 0.5F;
         } else {
            this.animTime += var1;
         }

         super.rotationYaw = MathHelper.wrapAngleTo180_float(super.rotationYaw);
         if(this.ringBufferIndex < 0) {
            for(int var25 = 0; var25 < this.ringBuffer.length; ++var25) {
               this.ringBuffer[var25][0] = (double)super.rotationYaw;
               this.ringBuffer[var25][1] = super.posY;
            }
         }

         if(++this.ringBufferIndex == this.ringBuffer.length) {
            this.ringBufferIndex = 0;
         }

         this.ringBuffer[this.ringBufferIndex][0] = (double)super.rotationYaw;
         this.ringBuffer[this.ringBufferIndex][1] = super.posY;
         double var4;
         double var6;
         double var8;
         double var26;
         float var31;
         if(super.worldObj.isRemote) {
            if(super.newPosRotationIncrements > 0) {
               var26 = super.posX + (super.newPosX - super.posX) / (double)super.newPosRotationIncrements;
               var4 = super.posY + (super.newPosY - super.posY) / (double)super.newPosRotationIncrements;
               var6 = super.posZ + (super.newPosZ - super.posZ) / (double)super.newPosRotationIncrements;
               var8 = MathHelper.wrapAngleTo180_double(super.newRotationYaw - (double)super.rotationYaw);
               super.rotationYaw = (float)((double)super.rotationYaw + var8 / (double)super.newPosRotationIncrements);
               super.rotationPitch = (float)((double)super.rotationPitch + (super.newRotationPitch - (double)super.rotationPitch) / (double)super.newPosRotationIncrements);
               --super.newPosRotationIncrements;
               this.setPosition(var26, var4, var6);
               this.setRotation(super.rotationYaw, super.rotationPitch);
            }
         } else {
            var26 = this.targetX - super.posX;
            var4 = this.targetY - super.posY;
            var6 = this.targetZ - super.posZ;
            var8 = var26 * var26 + var4 * var4 + var6 * var6;
            if(this.target != null) {
               this.targetX = this.target.posX;
               this.targetZ = this.target.posZ;
               double var10 = this.targetX - super.posX;
               double var12 = this.targetZ - super.posZ;
               double var14 = Math.sqrt(var10 * var10 + var12 * var12);
               double var16 = 0.4000000059604645D + var14 / 80.0D - 1.0D;
               if(var16 > 10.0D) {
                  var16 = 10.0D;
               }

               this.targetY = this.target.boundingBox.minY + var16;
            } else {
               this.targetX += super.rand.nextGaussian() * 2.0D;
               this.targetZ += super.rand.nextGaussian() * 2.0D;
            }

            if(this.forceNewTarget || var8 < 100.0D || var8 > 22500.0D || super.isCollidedHorizontally || super.isCollidedVertically) {
               this.setNewTarget();
            }

            var4 /= (double)MathHelper.sqrt_double(var26 * var26 + var6 * var6);
            var31 = 0.6F;
            if(var4 < (double)(-var31)) {
               var4 = (double)(-var31);
            }

            if(var4 > (double)var31) {
               var4 = (double)var31;
            }

            super.motionY += var4 * 0.10000000149011612D;
            super.rotationYaw = MathHelper.wrapAngleTo180_float(super.rotationYaw);
            double var11 = 180.0D - Math.atan2(var26, var6) * 180.0D / 3.1415927410125732D;
            double var13 = MathHelper.wrapAngleTo180_double(var11 - (double)super.rotationYaw);
            if(var13 > 50.0D) {
               var13 = 50.0D;
            }

            if(var13 < -50.0D) {
               var13 = -50.0D;
            }

            Vec3 var15 = Vec3.createVectorHelper(this.targetX - super.posX, this.targetY - super.posY, this.targetZ - super.posZ).normalize();
            Vec3 var39 = Vec3.createVectorHelper((double)MathHelper.sin(super.rotationYaw * 3.1415927F / 180.0F), super.motionY, (double)(-MathHelper.cos(super.rotationYaw * 3.1415927F / 180.0F))).normalize();
            float var17 = (float)(var39.dotProduct(var15) + 0.5D) / 1.5F;
            if(var17 < 0.0F) {
               var17 = 0.0F;
            }

            super.randomYawVelocity *= 0.8F;
            float var18 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ) * 1.0F + 1.0F;
            double var19 = Math.sqrt(super.motionX * super.motionX + super.motionZ * super.motionZ) * 1.0D + 1.0D;
            if(var19 > 40.0D) {
               var19 = 40.0D;
            }

            super.randomYawVelocity = (float)((double)super.randomYawVelocity + var13 * (0.699999988079071D / var19 / (double)var18));
            super.rotationYaw += super.randomYawVelocity * 0.1F;
            float var21 = (float)(2.0D / (var19 + 1.0D));
            float var22 = 0.06F;
            this.moveFlying(0.0F, -1.0F, var22 * (var17 * var21 + (1.0F - var21)));
            if(this.slowed) {
               this.moveEntity(super.motionX * 0.800000011920929D, super.motionY * 0.800000011920929D, super.motionZ * 0.800000011920929D);
            } else {
               this.moveEntity(super.motionX, super.motionY, super.motionZ);
            }

            Vec3 var23 = Vec3.createVectorHelper(super.motionX, super.motionY, super.motionZ).normalize();
            float var24 = (float)(var23.dotProduct(var39) + 1.0D) / 2.0F;
            var24 = 0.8F + 0.15F * var24;
            super.motionX *= (double)var24;
            super.motionZ *= (double)var24;
            super.motionY *= 0.9100000262260437D;
         }

         super.renderYawOffset = super.rotationYaw;
         this.dragonPartHead.width = this.dragonPartHead.height = 3.0F;
         this.dragonPartTail1.width = this.dragonPartTail1.height = 2.0F;
         this.dragonPartTail2.width = this.dragonPartTail2.height = 2.0F;
         this.dragonPartTail3.width = this.dragonPartTail3.height = 2.0F;
         this.dragonPartBody.height = 3.0F;
         this.dragonPartBody.width = 5.0F;
         this.dragonPartWing1.height = 2.0F;
         this.dragonPartWing1.width = 4.0F;
         this.dragonPartWing2.height = 3.0F;
         this.dragonPartWing2.width = 4.0F;
         var2 = (float)(this.getMovementOffsets(5, 1.0F)[1] - this.getMovementOffsets(10, 1.0F)[1]) * 10.0F / 180.0F * 3.1415927F;
         var3 = MathHelper.cos(var2);
         float var27 = -MathHelper.sin(var2);
         float var5 = super.rotationYaw * 3.1415927F / 180.0F;
         float var28 = MathHelper.sin(var5);
         float var7 = MathHelper.cos(var5);
         this.dragonPartBody.onUpdate();
         this.dragonPartBody.setLocationAndAngles(super.posX + (double)(var28 * 0.5F), super.posY, super.posZ - (double)(var7 * 0.5F), 0.0F, 0.0F);
         this.dragonPartWing1.onUpdate();
         this.dragonPartWing1.setLocationAndAngles(super.posX + (double)(var7 * 4.5F), super.posY + 2.0D, super.posZ + (double)(var28 * 4.5F), 0.0F, 0.0F);
         this.dragonPartWing2.onUpdate();
         this.dragonPartWing2.setLocationAndAngles(super.posX - (double)(var7 * 4.5F), super.posY + 2.0D, super.posZ - (double)(var28 * 4.5F), 0.0F, 0.0F);
         if(!super.worldObj.isRemote && super.hurtTime == 0) {
            this.collideWithEntities(super.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing1.boundingBox.expand(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
            this.collideWithEntities(super.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing2.boundingBox.expand(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
            this.attackEntitiesInList(super.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartHead.boundingBox.expand(1.0D, 1.0D, 1.0D)));
         }

         double[] var29 = this.getMovementOffsets(5, 1.0F);
         double[] var9 = this.getMovementOffsets(0, 1.0F);
         var31 = MathHelper.sin(super.rotationYaw * 3.1415927F / 180.0F - super.randomYawVelocity * 0.01F);
         float var33 = MathHelper.cos(super.rotationYaw * 3.1415927F / 180.0F - super.randomYawVelocity * 0.01F);
         this.dragonPartHead.onUpdate();
         this.dragonPartHead.setLocationAndAngles(super.posX + (double)(var31 * 5.5F * var3), super.posY + (var9[1] - var29[1]) * 1.0D + (double)(var27 * 5.5F), super.posZ - (double)(var33 * 5.5F * var3), 0.0F, 0.0F);

         for(int var30 = 0; var30 < 3; ++var30) {
            EntityDragonPart var32 = null;
            if(var30 == 0) {
               var32 = this.dragonPartTail1;
            }

            if(var30 == 1) {
               var32 = this.dragonPartTail2;
            }

            if(var30 == 2) {
               var32 = this.dragonPartTail3;
            }

            double[] var34 = this.getMovementOffsets(12 + var30 * 2, 1.0F);
            float var35 = super.rotationYaw * 3.1415927F / 180.0F + this.simplifyAngle(var34[0] - var29[0]) * 3.1415927F / 180.0F * 1.0F;
            float var37 = MathHelper.sin(var35);
            float var36 = MathHelper.cos(var35);
            float var38 = 1.5F;
            float var40 = (float)(var30 + 1) * 2.0F;
            var32.onUpdate();
            var32.setLocationAndAngles(super.posX - (double)((var28 * var38 + var37 * var40) * var3), super.posY + (var34[1] - var29[1]) * 1.0D - (double)((var40 + var38) * var27) + 1.5D, super.posZ + (double)((var7 * var38 + var36 * var40) * var3), 0.0F, 0.0F);
         }

         if(!super.worldObj.isRemote) {
            this.slowed = this.destroyBlocksInAABB(this.dragonPartHead.boundingBox) | this.destroyBlocksInAABB(this.dragonPartBody.boundingBox);
         }

      }
   }

   private void updateDragonEnderCrystal() {
      if(this.healingEnderCrystal != null) {
         if(this.healingEnderCrystal.isDead) {
            if(!super.worldObj.isRemote) {
               this.attackEntityFromPart(this.dragonPartHead, DamageSource.setExplosionSource((Explosion)null), 10.0F);
            }

            this.healingEnderCrystal = null;
         } else if(super.ticksExisted % 10 == 0 && this.getHealth() < this.getMaxHealth()) {
            this.setHealth(this.getHealth() + 1.0F);
         }
      }

      if(super.rand.nextInt(10) == 0) {
         float var1 = 32.0F;
         List var2 = super.worldObj.getEntitiesWithinAABB(EntityEnderCrystal.class, super.boundingBox.expand((double)var1, (double)var1, (double)var1));
         EntityEnderCrystal var3 = null;
         double var4 = Double.MAX_VALUE;
         Iterator var6 = var2.iterator();

         while(var6.hasNext()) {
            EntityEnderCrystal var7 = (EntityEnderCrystal)var6.next();
            double var8 = var7.getDistanceSqToEntity(this);
            if(var8 < var4) {
               var4 = var8;
               var3 = var7;
            }
         }

         this.healingEnderCrystal = var3;
      }

   }

   private void collideWithEntities(List var1) {
      double var2 = (this.dragonPartBody.boundingBox.minX + this.dragonPartBody.boundingBox.maxX) / 2.0D;
      double var4 = (this.dragonPartBody.boundingBox.minZ + this.dragonPartBody.boundingBox.maxZ) / 2.0D;
      Iterator var6 = var1.iterator();

      while(var6.hasNext()) {
         Entity var7 = (Entity)var6.next();
         if(var7 instanceof EntityLivingBase) {
            double var8 = var7.posX - var2;
            double var10 = var7.posZ - var4;
            double var12 = var8 * var8 + var10 * var10;
            var7.addVelocity(var8 / var12 * 4.0D, 0.20000000298023224D, var10 / var12 * 4.0D);
         }
      }

   }

   private void attackEntitiesInList(List var1) {
      for(int var2 = 0; var2 < var1.size(); ++var2) {
         Entity var3 = (Entity)var1.get(var2);
         if(var3 instanceof EntityLivingBase) {
            var3.attackEntityFrom(DamageSource.causeMobDamage(this), 10.0F);
         }
      }

   }

   private void setNewTarget() {
      this.forceNewTarget = false;
      if(super.rand.nextInt(2) == 0 && !super.worldObj.playerEntities.isEmpty()) {
         this.target = (Entity)super.worldObj.playerEntities.get(super.rand.nextInt(super.worldObj.playerEntities.size()));
      } else {
         boolean var1 = false;

         do {
            this.targetX = 0.0D;
            this.targetY = (double)(70.0F + super.rand.nextFloat() * 50.0F);
            this.targetZ = 0.0D;
            this.targetX += (double)(super.rand.nextFloat() * 120.0F - 60.0F);
            this.targetZ += (double)(super.rand.nextFloat() * 120.0F - 60.0F);
            double var2 = super.posX - this.targetX;
            double var4 = super.posY - this.targetY;
            double var6 = super.posZ - this.targetZ;
            var1 = var2 * var2 + var4 * var4 + var6 * var6 > 100.0D;
         } while(!var1);

         this.target = null;
      }

   }

   private float simplifyAngle(double var1) {
      return (float)MathHelper.wrapAngleTo180_double(var1);
   }

   private boolean destroyBlocksInAABB(AxisAlignedBB var1) {
      int var2 = MathHelper.floor_double(var1.minX);
      int var3 = MathHelper.floor_double(var1.minY);
      int var4 = MathHelper.floor_double(var1.minZ);
      int var5 = MathHelper.floor_double(var1.maxX);
      int var6 = MathHelper.floor_double(var1.maxY);
      int var7 = MathHelper.floor_double(var1.maxZ);
      boolean var8 = false;
      boolean var9 = false;

      for(int var10 = var2; var10 <= var5; ++var10) {
         for(int var11 = var3; var11 <= var6; ++var11) {
            for(int var12 = var4; var12 <= var7; ++var12) {
               Block var13 = super.worldObj.getBlock(var10, var11, var12);
               if(var13.getMaterial() != Material.air) {
                  if(var13 != Blocks.obsidian && var13 != Blocks.end_stone && var13 != Blocks.bedrock && super.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
                     var9 = super.worldObj.setBlockToAir(var10, var11, var12) || var9;
                  } else {
                     var8 = true;
                  }
               }
            }
         }
      }

      if(var9) {
         double var16 = var1.minX + (var1.maxX - var1.minX) * (double)super.rand.nextFloat();
         double var17 = var1.minY + (var1.maxY - var1.minY) * (double)super.rand.nextFloat();
         double var14 = var1.minZ + (var1.maxZ - var1.minZ) * (double)super.rand.nextFloat();
         super.worldObj.spawnParticle("largeexplode", var16, var17, var14, 0.0D, 0.0D, 0.0D);
      }

      return var8;
   }

   public boolean attackEntityFromPart(EntityDragonPart var1, DamageSource var2, float var3) {
      if(var1 != this.dragonPartHead) {
         var3 = var3 / 4.0F + 1.0F;
      }

      float var4 = super.rotationYaw * 3.1415927F / 180.0F;
      float var5 = MathHelper.sin(var4);
      float var6 = MathHelper.cos(var4);
      this.targetX = super.posX + (double)(var5 * 5.0F) + (double)((super.rand.nextFloat() - 0.5F) * 2.0F);
      this.targetY = super.posY + (double)(super.rand.nextFloat() * 3.0F) + 1.0D;
      this.targetZ = super.posZ - (double)(var6 * 5.0F) + (double)((super.rand.nextFloat() - 0.5F) * 2.0F);
      this.target = null;
      if(var2.getEntity() instanceof EntityPlayer || var2.isExplosion()) {
         this.func_82195_e(var2, var3);
      }

      return true;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      return false;
   }

   protected boolean func_82195_e(DamageSource var1, float var2) {
      return super.attackEntityFrom(var1, var2);
   }

   protected void onDeathUpdate() {
      ++this.deathTicks;
      if(this.deathTicks >= 180 && this.deathTicks <= 200) {
         float var1 = (super.rand.nextFloat() - 0.5F) * 8.0F;
         float var2 = (super.rand.nextFloat() - 0.5F) * 4.0F;
         float var3 = (super.rand.nextFloat() - 0.5F) * 8.0F;
         super.worldObj.spawnParticle("hugeexplosion", super.posX + (double)var1, super.posY + 2.0D + (double)var2, super.posZ + (double)var3, 0.0D, 0.0D, 0.0D);
      }

      int var4;
      int var5;
      if(!super.worldObj.isRemote) {
         if(this.deathTicks > 150 && this.deathTicks % 5 == 0) {
            var4 = 1000;

            while(var4 > 0) {
               var5 = EntityXPOrb.getXPSplit(var4);
               var4 -= var5;
               super.worldObj.spawnEntityInWorld(new EntityXPOrb(super.worldObj, super.posX, super.posY, super.posZ, var5));
            }
         }

         if(this.deathTicks == 1) {
            super.worldObj.playBroadcastSound(1018, (int)super.posX, (int)super.posY, (int)super.posZ, 0);
         }
      }

      this.moveEntity(0.0D, 0.10000000149011612D, 0.0D);
      super.renderYawOffset = super.rotationYaw += 20.0F;
      if(this.deathTicks == 200 && !super.worldObj.isRemote) {
         var4 = 2000;

         while(var4 > 0) {
            var5 = EntityXPOrb.getXPSplit(var4);
            var4 -= var5;
            super.worldObj.spawnEntityInWorld(new EntityXPOrb(super.worldObj, super.posX, super.posY, super.posZ, var5));
         }

         this.createEnderPortal(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posZ));
         this.setDead();
      }

   }

   private void createEnderPortal(int var1, int var2) {
      byte var3 = 64;
      BlockEndPortal.field_149948_a = true;
      byte var4 = 4;

      for(int var5 = var3 - 1; var5 <= var3 + 32; ++var5) {
         for(int var6 = var1 - var4; var6 <= var1 + var4; ++var6) {
            for(int var7 = var2 - var4; var7 <= var2 + var4; ++var7) {
               double var8 = (double)(var6 - var1);
               double var10 = (double)(var7 - var2);
               double var12 = var8 * var8 + var10 * var10;
               if(var12 <= ((double)var4 - 0.5D) * ((double)var4 - 0.5D)) {
                  if(var5 < var3) {
                     if(var12 <= ((double)(var4 - 1) - 0.5D) * ((double)(var4 - 1) - 0.5D)) {
                        super.worldObj.setBlock(var6, var5, var7, Blocks.bedrock);
                     }
                  } else if(var5 > var3) {
                     super.worldObj.setBlock(var6, var5, var7, Blocks.air);
                  } else if(var12 > ((double)(var4 - 1) - 0.5D) * ((double)(var4 - 1) - 0.5D)) {
                     super.worldObj.setBlock(var6, var5, var7, Blocks.bedrock);
                  } else {
                     super.worldObj.setBlock(var6, var5, var7, Blocks.end_portal);
                  }
               }
            }
         }
      }

      super.worldObj.setBlock(var1, var3 + 0, var2, Blocks.bedrock);
      super.worldObj.setBlock(var1, var3 + 1, var2, Blocks.bedrock);
      super.worldObj.setBlock(var1, var3 + 2, var2, Blocks.bedrock);
      super.worldObj.setBlock(var1 - 1, var3 + 2, var2, Blocks.torch);
      super.worldObj.setBlock(var1 + 1, var3 + 2, var2, Blocks.torch);
      super.worldObj.setBlock(var1, var3 + 2, var2 - 1, Blocks.torch);
      super.worldObj.setBlock(var1, var3 + 2, var2 + 1, Blocks.torch);
      super.worldObj.setBlock(var1, var3 + 3, var2, Blocks.bedrock);
      super.worldObj.setBlock(var1, var3 + 4, var2, Blocks.dragon_egg);
      BlockEndPortal.field_149948_a = false;
   }

   protected void despawnEntity() {}

   public Entity[] getParts() {
      return this.dragonPartArray;
   }

   public boolean canBeCollidedWith() {
      return false;
   }

   public World func_82194_d() {
      return super.worldObj;
   }

   protected String getLivingSound() {
      return "mob.enderdragon.growl";
   }

   protected String getHurtSound() {
      return "mob.enderdragon.hit";
   }

   protected float getSoundVolume() {
      return 5.0F;
   }
}
