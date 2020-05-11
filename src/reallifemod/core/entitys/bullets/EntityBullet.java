package de.ItsAMysterious.mods.reallifemod.core.entitys.bullets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBullet extends Entity implements IProjectile {

   private int field_145791_d;
   private int field_145792_e;
   private int field_145789_f;
   private Block field_145790_g;
   private int inData;
   private boolean inGround;
   public int canBePickedUp;
   public int arrowShake;
   public Entity shootingEntity;
   private int ticksInGround;
   private int ticksInAir;
   private double damage;
   private int knockbackStrength;
   private static final String __OBFID = "CL_00001715";


   public EntityBullet(World p_i1753_1_) {
      super(p_i1753_1_);
      this.field_145791_d = -1;
      this.field_145792_e = -1;
      this.field_145789_f = -1;
      this.damage = 2.0D;
      this.field_70155_l = 10.0D;
      this.func_70105_a(0.1F, 0.1F);
   }

   public EntityBullet(World p_i1754_1_, double p_i1754_2_, double p_i1754_4_, double p_i1754_6_) {
      this(p_i1754_1_);
      this.field_70155_l = 10.0D;
      this.func_70105_a(0.5F, 0.5F);
      this.func_70107_b(p_i1754_2_, p_i1754_4_, p_i1754_6_);
      this.field_70129_M = 0.0F;
   }

   public EntityBullet(World p_i1755_1_, EntityLivingBase p_i1755_2_, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_) {
      this(p_i1755_1_);
      this.field_70155_l = 10.0D;
      this.shootingEntity = p_i1755_2_;
      if(p_i1755_2_ instanceof EntityPlayer) {
         this.canBePickedUp = 1;
      }

      this.field_70163_u = p_i1755_2_.field_70163_u + (double)p_i1755_2_.getEyeHeight() - 0.10000000149011612D;
      double d0 = p_i1755_3_.field_70165_t - p_i1755_2_.field_70165_t;
      double d1 = p_i1755_3_.field_70121_D.minY + (double)(p_i1755_3_.field_70131_O / 3.0F) - this.field_70163_u;
      double d2 = p_i1755_3_.field_70161_v - p_i1755_2_.field_70161_v;
      double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);
      if(d3 >= 1.0E-7D) {
         float f2 = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
         float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D));
         double d4 = d0 / d3;
         double d5 = d2 / d3;
         this.func_70012_b(p_i1755_2_.field_70165_t + d4, this.field_70163_u, p_i1755_2_.field_70161_v + d5, f2, f3);
         this.field_70129_M = 0.0F;
         float f4 = (float)d3 * 0.2F;
         this.func_70186_c(d0, d1 + (double)f4, d2, p_i1755_4_, p_i1755_5_);
      }

   }

   public EntityBullet(World p_i1756_1_, EntityLivingBase p_i1756_2_, float p_i1756_3_) {
      this(p_i1756_1_);
      this.field_70155_l = 10.0D;
      this.shootingEntity = p_i1756_2_;
      if(p_i1756_2_ instanceof EntityPlayer) {
         this.canBePickedUp = 1;
      }

      this.func_70105_a(0.5F, 0.5F);
      this.func_70012_b(p_i1756_2_.field_70165_t, p_i1756_2_.field_70163_u + (double)p_i1756_2_.getEyeHeight(), p_i1756_2_.field_70161_v, p_i1756_2_.rotationYawHead, p_i1756_2_.field_70125_A);
      this.field_70165_t -= (double)(MathHelper.cos(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
      this.field_70161_v -= (double)(MathHelper.sin(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      this.field_70129_M = 0.0F;
      this.field_70159_w = (double)(-MathHelper.sin(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.cos(this.field_70125_A / 180.0F * 3.1415927F));
      this.field_70181_x = -Math.atan((double)(p_i1756_2_.field_70125_A / 180.0F * 3.1415927F));
      this.field_70179_y = (double)(MathHelper.cos(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.cos(this.field_70125_A / 180.0F * 3.1415927F));
      this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, p_i1756_3_ * 1.5F, 1.0F);
   }

   protected void func_70088_a() {
      this.field_70180_af.addObject(16, Byte.valueOf((byte)0));
   }

   public void func_70186_c(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
      float f2 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
      p_70186_1_ /= (double)f2;
      p_70186_3_ /= (double)f2;
      p_70186_5_ /= (double)f2;
      p_70186_1_ *= (double)p_70186_7_;
      p_70186_3_ *= (double)p_70186_7_;
      p_70186_5_ *= (double)p_70186_7_;
      this.field_70159_w = p_70186_1_;
      this.field_70181_x = p_70186_3_;
      this.field_70179_y = p_70186_5_;
      float f3 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
      this.field_70126_B = this.field_70177_z = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / 3.141592653589793D);
      this.field_70127_C = this.field_70125_A = (float)(Math.atan2(p_70186_3_, (double)f3) * 180.0D / 3.141592653589793D);
      this.ticksInGround = 0;
   }

   @SideOnly(Side.CLIENT)
   public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
      this.func_70107_b(p_70056_1_, p_70056_3_, p_70056_5_);
      this.func_70101_b(p_70056_7_, p_70056_8_);
   }

   @SideOnly(Side.CLIENT)
   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.field_70159_w = p_70016_1_;
      this.field_70181_x = p_70016_3_;
      this.field_70179_y = p_70016_5_;
      if(this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
         float f = MathHelper.sqrt_double(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
         this.field_70126_B = this.field_70177_z = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0D / 3.141592653589793D);
         this.field_70127_C = this.field_70125_A = (float)(Math.atan2(p_70016_3_, (double)f) * 180.0D / 3.141592653589793D);
         this.field_70127_C = this.field_70125_A;
         this.field_70126_B = this.field_70177_z;
         this.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
         this.ticksInGround = 0;
      }

   }

   public void func_70071_h_() {
      super.onUpdate();
      if(this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
         float block = MathHelper.sqrt_double(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         this.field_70126_B = this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D);
         this.field_70127_C = this.field_70125_A = (float)(Math.atan2(this.field_70181_x, (double)block) * 180.0D / 3.141592653589793D);
      }

      Block var16 = this.field_70170_p.getBlock(this.field_145791_d, this.field_145792_e, this.field_145789_f);
      if(var16.getMaterial() != Material.air) {
         var16.setBlockBoundsBasedOnState(this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f);
         AxisAlignedBB vec31 = var16.getCollisionBoundingBoxFromPool(this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f);
         if(vec31 != null && vec31.isVecInside(Vec3.createVectorHelper(this.field_70165_t, this.field_70163_u, this.field_70161_v))) {
            this.inGround = true;
         }
      }

      if(this.arrowShake > 0) {
         --this.arrowShake;
      }

      if(this.inGround) {
         int var17 = this.field_70170_p.getBlockMetadata(this.field_145791_d, this.field_145792_e, this.field_145789_f);
         if(var16 == this.field_145790_g && var17 == this.inData) {
            ++this.ticksInGround;
            if(this.ticksInGround == 100) {
               this.func_70106_y();
            }
         } else {
            this.inGround = false;
            this.ticksInGround = 0;
            this.ticksInAir = 0;
         }
      } else {
         ++this.ticksInAir;
         Vec3 var18 = Vec3.createVectorHelper(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         Vec3 vec3 = Vec3.createVectorHelper(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
         MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(var18, vec3, false, true, false);
         var18 = Vec3.createVectorHelper(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         vec3 = Vec3.createVectorHelper(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
         if(movingobjectposition != null) {
            vec3 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
         }

         Entity entity = null;
         List list = this.field_70170_p.getEntitiesWithinAABBExcludingEntity(this, this.field_70121_D.addCoord(this.field_70159_w, this.field_70181_x, this.field_70179_y).expand(1.0D, 1.0D, 1.0D));
         double d0 = 0.0D;

         int i;
         float f1;
         for(i = 0; i < list.size(); ++i) {
            Entity f2 = (Entity)list.get(i);
            if(f2.canBeCollidedWith() && (f2 != this.shootingEntity || this.ticksInAir >= 5)) {
               f1 = 0.3F;
               AxisAlignedBB f4 = f2.boundingBox.expand((double)f1, (double)f1, (double)f1);
               MovingObjectPosition f3 = f4.calculateIntercept(var18, vec3);
               if(f3 != null) {
                  double l = var18.distanceTo(f3.hitVec);
                  if(l < d0 || d0 == 0.0D) {
                     entity = f2;
                     d0 = l;
                  }
               }
            }
         }

         if(entity != null) {
            movingobjectposition = new MovingObjectPosition(entity);
         }

         if(movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer) {
            EntityPlayer var19 = (EntityPlayer)movingobjectposition.entityHit;
            if(var19.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(var19)) {
               movingobjectposition = null;
            }
         }

         float var20;
         float var21;
         if(movingobjectposition != null) {
            if(movingobjectposition.entityHit != null) {
               var20 = MathHelper.sqrt_double(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
               int var22 = MathHelper.ceiling_double_int((double)var20 * this.damage);
               if(this.getIsCritical()) {
                  var22 += this.field_70146_Z.nextInt(var22 / 2 + 2);
               }

               DamageSource var23 = null;
               if(this.shootingEntity == null) {
                  var23 = DamageSource.causeThrownDamage(this, this);
               } else {
                  var23 = DamageSource.causeThrownDamage(this, this.shootingEntity);
               }

               if(this.func_70027_ad() && !(movingobjectposition.entityHit instanceof EntityEnderman)) {
                  movingobjectposition.entityHit.setFire(5);
               }

               if(movingobjectposition.entityHit.attackEntityFrom(var23, (float)var22)) {
                  if(movingobjectposition.entityHit instanceof EntityLivingBase) {
                     EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.entityHit;
                     if(!this.field_70170_p.isRemote) {
                        entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
                     }

                     if(this.knockbackStrength > 0) {
                        var21 = MathHelper.sqrt_double(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
                        if(var21 > 0.0F) {
                           movingobjectposition.entityHit.addVelocity(this.field_70159_w * (double)this.knockbackStrength * 0.6000000238418579D / (double)var21, 0.1D, this.field_70179_y * (double)this.knockbackStrength * 0.6000000238418579D / (double)var21);
                        }
                     }

                     if(this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
                        EnchantmentHelper.func_151384_a(entitylivingbase, this.shootingEntity);
                        EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, entitylivingbase);
                     }

                     if(this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
                     }
                  }

                  if(!(movingobjectposition.entityHit instanceof EntityEnderman)) {
                     this.func_70106_y();
                  }
               } else {
                  this.field_70159_w *= -0.10000000149011612D;
                  this.field_70181_x *= -0.10000000149011612D;
                  this.field_70179_y *= -0.10000000149011612D;
                  this.field_70177_z += 180.0F;
                  this.field_70126_B += 180.0F;
                  this.ticksInAir = 0;
               }
            } else {
               this.field_145791_d = movingobjectposition.blockX;
               this.field_145792_e = movingobjectposition.blockY;
               this.field_145789_f = movingobjectposition.blockZ;
               this.field_145790_g = this.field_70170_p.getBlock(this.field_145791_d, this.field_145792_e, this.field_145789_f);
               this.inData = this.field_70170_p.getBlockMetadata(this.field_145791_d, this.field_145792_e, this.field_145789_f);
               this.field_70159_w = (double)((float)(movingobjectposition.hitVec.xCoord - this.field_70165_t));
               this.field_70181_x = (double)((float)(movingobjectposition.hitVec.yCoord - this.field_70163_u));
               this.field_70179_y = (double)((float)(movingobjectposition.hitVec.zCoord - this.field_70161_v));
               var20 = MathHelper.sqrt_double(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
               this.field_70165_t -= this.field_70159_w / (double)var20 * 0.05000000074505806D;
               this.field_70163_u -= this.field_70181_x / (double)var20 * 0.05000000074505806D;
               this.field_70161_v -= this.field_70179_y / (double)var20 * 0.05000000074505806D;
               this.inGround = true;
               this.arrowShake = 7;
               this.setIsCritical(false);
               if(this.field_145790_g.getMaterial() != Material.air) {
                  this.field_145790_g.onEntityCollidedWithBlock(this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f, this);
               }
            }
         }

         if(this.getIsCritical()) {
            for(i = 0; i < 4; ++i) {
               this.field_70170_p.spawnParticle("crit", this.field_70165_t + this.field_70159_w * (double)i / 4.0D, this.field_70163_u + this.field_70181_x * (double)i / 4.0D, this.field_70161_v + this.field_70179_y * (double)i / 4.0D, -this.field_70159_w, -this.field_70181_x + 0.2D, -this.field_70179_y);
            }
         }

         this.field_70165_t += this.field_70159_w;
         this.field_70163_u += this.field_70181_x;
         this.field_70161_v += this.field_70179_y;
         var20 = MathHelper.sqrt_double(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D);

         for(this.field_70125_A = (float)(Math.atan2(this.field_70181_x, (double)var20) * 180.0D / 3.141592653589793D); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {
            ;
         }

         while(this.field_70125_A - this.field_70127_C >= 180.0F) {
            this.field_70127_C += 360.0F;
         }

         while(this.field_70177_z - this.field_70126_B < -180.0F) {
            this.field_70126_B -= 360.0F;
         }

         while(this.field_70177_z - this.field_70126_B >= 180.0F) {
            this.field_70126_B += 360.0F;
         }

         this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
         this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
         float var24 = 0.99F;
         f1 = 0.05F;
         if(this.func_70090_H()) {
            for(int var25 = 0; var25 < 4; ++var25) {
               var21 = 0.25F;
               this.field_70170_p.spawnParticle("bubble", this.field_70165_t - this.field_70159_w * (double)var21, this.field_70163_u - this.field_70181_x * (double)var21, this.field_70161_v - this.field_70179_y * (double)var21, this.field_70159_w, this.field_70181_x, this.field_70179_y);
            }

            var24 = 0.8F;
         }

         if(this.func_70026_G()) {
            this.func_70066_B();
         }

         this.field_70159_w *= (double)var24;
         this.field_70181_x *= (double)var24;
         this.field_70179_y *= (double)var24;
         this.field_70181_x -= (double)f1;
         this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         this.func_145775_I();
         if(this.field_70170_p.isRemote) {
            this.field_70170_p.spawnParticle("flame", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, 0.0D, 0.0D);
         }
      }

   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      p_70014_1_.setShort("xTile", (short)this.field_145791_d);
      p_70014_1_.setShort("yTile", (short)this.field_145792_e);
      p_70014_1_.setShort("zTile", (short)this.field_145789_f);
      p_70014_1_.setShort("life", (short)this.ticksInGround);
      p_70014_1_.setByte("inTile", (byte)Block.getIdFromBlock(this.field_145790_g));
      p_70014_1_.setByte("inData", (byte)this.inData);
      p_70014_1_.setByte("shake", (byte)this.arrowShake);
      p_70014_1_.setByte("inGround", (byte)(this.inGround?1:0));
      p_70014_1_.setByte("pickup", (byte)this.canBePickedUp);
      p_70014_1_.setDouble("damage", this.damage);
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      this.field_145791_d = p_70037_1_.getShort("xTile");
      this.field_145792_e = p_70037_1_.getShort("yTile");
      this.field_145789_f = p_70037_1_.getShort("zTile");
      this.ticksInGround = p_70037_1_.getShort("life");
      this.field_145790_g = Block.getBlockById(p_70037_1_.getByte("inTile") & 255);
      this.inData = p_70037_1_.getByte("inData") & 255;
      this.arrowShake = p_70037_1_.getByte("shake") & 255;
      this.inGround = p_70037_1_.getByte("inGround") == 1;
      if(p_70037_1_.hasKey("damage", 99)) {
         this.damage = p_70037_1_.getDouble("damage");
      }

      if(p_70037_1_.hasKey("pickup", 99)) {
         this.canBePickedUp = p_70037_1_.getByte("pickup");
      } else if(p_70037_1_.hasKey("player", 99)) {
         this.canBePickedUp = p_70037_1_.getBoolean("player")?1:0;
      }

   }

   protected boolean func_70041_e_() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public float func_70053_R() {
      return 0.0F;
   }

   public void setDamage(double p_70239_1_) {
      this.damage = p_70239_1_;
   }

   public double getDamage() {
      return this.damage;
   }

   public void setKnockbackStrength(int p_70240_1_) {
      this.knockbackStrength = p_70240_1_;
   }

   public boolean func_70075_an() {
      return true;
   }

   public void setIsCritical(boolean p_70243_1_) {
      byte b0 = this.field_70180_af.getWatchableObjectByte(16);
      if(p_70243_1_) {
         this.field_70180_af.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
      } else {
         this.field_70180_af.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
      }

   }

   public boolean getIsCritical() {
      byte b0 = this.field_70180_af.getWatchableObjectByte(16);
      return (b0 & 1) != 0;
   }
}
