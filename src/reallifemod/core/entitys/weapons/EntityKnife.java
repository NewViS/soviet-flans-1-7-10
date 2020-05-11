package de.ItsAMysterious.mods.reallifemod.core.entitys.weapons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public abstract class EntityKnife extends Entity implements IProjectile {

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
   private static final String __OBFID = "CL_00001723";


   public EntityKnife(World p_i1776_1_) {
      super(p_i1776_1_);
      this.func_70105_a(0.25F, 0.25F);
   }

   protected void func_70088_a() {}

   @SideOnly(Side.CLIENT)
   public boolean func_70112_a(double p_70112_1_) {
      double d1 = this.field_70121_D.getAverageEdgeLength() * 4.0D;
      d1 *= 64.0D;
      return p_70112_1_ < d1 * d1;
   }

   public EntityKnife(World p_i1777_1_, EntityLivingBase p_i1777_2_) {
      super(p_i1777_1_);
      this.thrower = p_i1777_2_;
      this.func_70105_a(0.25F, 0.25F);
      this.func_70012_b(p_i1777_2_.field_70165_t, p_i1777_2_.field_70163_u + (double)p_i1777_2_.getEyeHeight(), p_i1777_2_.field_70161_v, p_i1777_2_.field_70177_z, p_i1777_2_.field_70125_A);
      this.field_70165_t -= (double)(MathHelper.cos(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
      this.field_70163_u -= 0.10000000149011612D;
      this.field_70161_v -= (double)(MathHelper.sin(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      this.field_70129_M = 0.0F;
      float f = 0.4F;
      this.field_70159_w = (double)(-MathHelper.sin(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.cos(this.field_70125_A / 180.0F * 3.1415927F) * f);
      this.field_70179_y = (double)(MathHelper.cos(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.cos(this.field_70125_A / 180.0F * 3.1415927F) * f);
      this.field_70181_x = (double)(-MathHelper.sin((this.field_70125_A + this.func_70183_g()) / 180.0F * 3.1415927F) * f);
      this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.func_70182_d(), 1.0F);
   }

   public EntityKnife(World p_i1778_1_, double p_i1778_2_, double p_i1778_4_, double p_i1778_6_) {
      super(p_i1778_1_);
      this.ticksInGround = 0;
      this.func_70105_a(0.25F, 0.25F);
      this.func_70107_b(p_i1778_2_, p_i1778_4_, p_i1778_6_);
      this.field_70129_M = 0.0F;
   }

   protected float func_70182_d() {
      return 1.5F;
   }

   protected float func_70183_g() {
      return 0.0F;
   }

   public void func_70186_c(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
      float f2 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
      p_70186_1_ /= (double)f2;
      p_70186_3_ /= (double)f2;
      p_70186_5_ /= (double)f2;
      p_70186_1_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_70186_8_;
      p_70186_3_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_70186_8_;
      p_70186_5_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_70186_8_;
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
   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.field_70159_w = p_70016_1_;
      this.field_70181_x = p_70016_3_;
      this.field_70179_y = p_70016_5_;
      if(this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
         float f = MathHelper.sqrt_double(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
         this.field_70126_B = this.field_70177_z = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0D / 3.141592653589793D);
         this.field_70127_C = this.field_70125_A = (float)(Math.atan2(p_70016_3_, (double)f) * 180.0D / 3.141592653589793D);
      }

   }

   public void func_70071_h_() {
      this.field_70142_S = this.field_70165_t;
      this.field_70137_T = this.field_70163_u;
      this.field_70136_U = this.field_70161_v;
      super.onUpdate();
      if(this.throwableShake > 0) {
         --this.throwableShake;
      }

      if(this.inGround) {
         if(this.field_70170_p.getBlock(this.field_145788_c, this.field_145786_d, this.field_145787_e) == this.field_145785_f) {
            ++this.ticksInGround;
            if(this.ticksInGround == 1200) {
               this.func_70106_y();
            }

            return;
         }

         this.inGround = false;
         this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
         this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
         this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
         this.ticksInGround = 0;
         this.ticksInAir = 0;
      } else {
         ++this.ticksInAir;
      }

      Vec3 vec3 = Vec3.createVectorHelper(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      Vec3 vec31 = Vec3.createVectorHelper(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
      MovingObjectPosition movingobjectposition = this.field_70170_p.rayTraceBlocks(vec3, vec31);
      vec3 = Vec3.createVectorHelper(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      vec31 = Vec3.createVectorHelper(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
      if(movingobjectposition != null) {
         vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
      }

      if(!this.field_70170_p.isRemote) {
         Entity f1 = null;
         List f2 = this.field_70170_p.getEntitiesWithinAABBExcludingEntity(this, this.field_70121_D.addCoord(this.field_70159_w, this.field_70181_x, this.field_70179_y).expand(1.0D, 1.0D, 1.0D));
         double f3 = 0.0D;
         EntityLivingBase f4 = this.getThrower();

         for(int j = 0; j < f2.size(); ++j) {
            Entity entity1 = (Entity)f2.get(j);
            if(entity1.canBeCollidedWith() && (entity1 != f4 || this.ticksInAir >= 5)) {
               float f = 0.3F;
               AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double)f, (double)f, (double)f);
               MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);
               if(movingobjectposition1 != null) {
                  double d1 = vec3.distanceTo(movingobjectposition1.hitVec);
                  if(d1 < f3 || f3 == 0.0D) {
                     f1 = entity1;
                     f3 = d1;
                  }
               }
            }
         }

         if(f1 != null) {
            movingobjectposition = new MovingObjectPosition(f1);
         }
      }

      if(movingobjectposition != null) {
         if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK && this.field_70170_p.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) == Blocks.portal) {
            this.func_70063_aa();
         } else {
            this.onImpact(movingobjectposition);
         }
      }

      this.field_70165_t += this.field_70159_w;
      this.field_70163_u += this.field_70181_x;
      this.field_70161_v += this.field_70179_y;
      float var16 = MathHelper.sqrt_double(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / 3.141592653589793D);

      for(this.field_70125_A = (float)(Math.atan2(this.field_70181_x, (double)var16) * 180.0D / 3.141592653589793D); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {
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
      float var17 = 0.99F;
      float var18 = this.getGravityVelocity();
      if(this.func_70090_H()) {
         for(int i = 0; i < 4; ++i) {
            float var19 = 0.25F;
            this.field_70170_p.spawnParticle("bubble", this.field_70165_t - this.field_70159_w * (double)var19, this.field_70163_u - this.field_70181_x * (double)var19, this.field_70161_v - this.field_70179_y * (double)var19, this.field_70159_w, this.field_70181_x, this.field_70179_y);
         }

         var17 = 0.8F;
      }

      this.field_70159_w *= (double)var17;
      this.field_70181_x *= (double)var17;
      this.field_70179_y *= (double)var17;
      this.field_70181_x -= (double)var18;
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }

   protected float getGravityVelocity() {
      return 0.03F;
   }

   protected abstract void onImpact(MovingObjectPosition var1);

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      p_70014_1_.setShort("xTile", (short)this.field_145788_c);
      p_70014_1_.setShort("yTile", (short)this.field_145786_d);
      p_70014_1_.setShort("zTile", (short)this.field_145787_e);
      p_70014_1_.setByte("inTile", (byte)Block.getIdFromBlock(this.field_145785_f));
      p_70014_1_.setByte("shake", (byte)this.throwableShake);
      p_70014_1_.setByte("inGround", (byte)(this.inGround?1:0));
      if((this.throwerName == null || this.throwerName.length() == 0) && this.thrower != null && this.thrower instanceof EntityPlayer) {
         this.throwerName = this.thrower.func_70005_c_();
      }

      p_70014_1_.setString("ownerName", this.throwerName == null?"":this.throwerName);
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      this.field_145788_c = p_70037_1_.getShort("xTile");
      this.field_145786_d = p_70037_1_.getShort("yTile");
      this.field_145787_e = p_70037_1_.getShort("zTile");
      this.field_145785_f = Block.getBlockById(p_70037_1_.getByte("inTile") & 255);
      this.throwableShake = p_70037_1_.getByte("shake") & 255;
      this.inGround = p_70037_1_.getByte("inGround") == 1;
      this.throwerName = p_70037_1_.getString("ownerName");
      if(this.throwerName != null && this.throwerName.length() == 0) {
         this.throwerName = null;
      }

   }

   @SideOnly(Side.CLIENT)
   public float func_70053_R() {
      return 0.0F;
   }

   public EntityLivingBase getThrower() {
      if(this.thrower == null && this.throwerName != null && this.throwerName.length() > 0) {
         this.thrower = this.field_70170_p.getPlayerEntityByName(this.throwerName);
      }

      return this.thrower;
   }
}
