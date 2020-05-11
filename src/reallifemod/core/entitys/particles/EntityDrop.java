package de.ItsAMysterious.mods.reallifemod.core.entitys.particles;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class EntityDrop extends Entity {

   protected int particleTextureIndexX;
   protected int particleTextureIndexY;
   protected float particleTextureJitterX;
   protected float particleTextureJitterY;
   protected int particleAge;
   private int particleMaxAge;
   protected float particleScale;
   protected float particleGravity;
   protected float particleRed;
   protected float particleGreen;
   protected float particleBlue;
   protected float particleAlpha;
   protected IIcon particleIcon;
   public static double interpPosX;
   public static double interpPosY;
   public static double interpPosZ;


   protected EntityDrop(World p_i1218_1_, double p_i1218_2_, double p_i1218_4_, double p_i1218_6_) {
      super(p_i1218_1_);
      this.particleAlpha = 1.0F;
      this.func_70105_a(0.2F, 0.2F);
      this.field_70129_M = this.field_70131_O / 2.0F;
      this.func_70107_b(p_i1218_2_, p_i1218_4_, p_i1218_6_);
      this.field_70142_S = p_i1218_2_;
      this.field_70137_T = p_i1218_4_;
      this.field_70136_U = p_i1218_6_;
      this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
      this.particleTextureJitterX = this.field_70146_Z.nextFloat() * 3.0F;
      this.particleTextureJitterY = this.field_70146_Z.nextFloat() * 3.0F;
      this.particleScale = (this.field_70146_Z.nextFloat() * 0.5F + 0.5F) * 2.0F;
   }

   public EntityDrop(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
      this(world, x, y, z);
      this.field_70159_w = motionX;
      this.field_70181_x = motionY;
      this.field_70179_y = motionZ;
      this.setParticleMaxAge((int)(4.0F / (this.field_70146_Z.nextFloat() * 0.9F + 0.1F)));
      this.particleAge = 0;
   }

   public EntityDrop(World world, double x, double y, double z, double motionX, double motionY, double motionZ, float rotationYaw) {
      this(world, x, y, z);
      this.field_70159_w = motionX;
      this.field_70181_x = motionY;
      this.field_70179_y = motionZ;
      this.field_70177_z = rotationYaw;
      this.setParticleMaxAge((int)(4.0F / (this.field_70146_Z.nextFloat() * 0.9F + 0.1F)));
      this.particleAge = 0;
   }

   public EntityDrop(World world, double x, double y, double z, double motionX, double motionY, double motionZ, double maxAge) {
      this(world, x, y, z);
      this.field_70159_w = motionX;
      this.field_70181_x = motionY;
      this.field_70179_y = motionZ;
      this.field_70177_z = 0.0F;
      this.setParticleMaxAge((int)(maxAge / (double)(this.field_70146_Z.nextFloat() * 0.9F + 0.1F)));
      this.particleAge = 0;
   }

   public EntityDrop multiplyVelocity(float p_70543_1_) {
      return this;
   }

   public EntityDrop multipleParticleScaleBy(float p_70541_1_) {
      this.func_70105_a(0.2F * p_70541_1_, 0.2F * p_70541_1_);
      this.particleScale *= p_70541_1_;
      return this;
   }

   public void setRBGColorF(float p_70538_1_, float p_70538_2_, float p_70538_3_) {
      this.particleRed = p_70538_1_;
      this.particleGreen = p_70538_2_;
      this.particleBlue = p_70538_3_;
   }

   public void setAlphaF(float p_82338_1_) {
      this.particleAlpha = p_82338_1_;
   }

   public float getRedColorF() {
      return this.particleRed;
   }

   public float getGreenColorF() {
      return this.particleGreen;
   }

   public float getBlueColorF() {
      return this.particleBlue;
   }

   protected boolean func_70041_e_() {
      return false;
   }

   protected void func_70088_a() {}

   public void func_70071_h_() {
      super.onUpdate();
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      if(this.particleAge++ >= this.getParticleMaxAge() * 2) {
         this.func_70106_y();
      }

      this.field_70181_x -= 0.04D * (double)this.particleGravity;
      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      this.field_70159_w *= 0.9800000190734863D;
      this.field_70181_x *= 0.9800000190734863D;
      this.field_70179_y *= 0.9800000190734863D;
      Random d = new Random();
      this.field_70181_x -= 0.00988881D * d.nextDouble() * 7.5D;
      if(this.field_70123_F) {
         this.field_70159_w = -this.field_70159_w / 2.0D;
      }

   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {}

   protected void func_70014_b(NBTTagCompound p_70014_1_) {}

   public int getParticleMaxAge() {
      return this.particleMaxAge;
   }

   public void setParticleMaxAge(int particleMaxAge) {
      this.particleMaxAge = particleMaxAge;
   }
}
