package net.minecraft.client.particle;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFX extends Entity {

   protected int particleTextureIndexX;
   protected int particleTextureIndexY;
   protected float particleTextureJitterX;
   protected float particleTextureJitterY;
   protected int particleAge;
   protected int particleMaxAge;
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


   protected EntityFX(World var1, double var2, double var4, double var6) {
      super(var1);
      this.particleAlpha = 1.0F;
      this.setSize(0.2F, 0.2F);
      super.yOffset = super.height / 2.0F;
      this.setPosition(var2, var4, var6);
      super.lastTickPosX = var2;
      super.lastTickPosY = var4;
      super.lastTickPosZ = var6;
      this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
      this.particleTextureJitterX = super.rand.nextFloat() * 3.0F;
      this.particleTextureJitterY = super.rand.nextFloat() * 3.0F;
      this.particleScale = (super.rand.nextFloat() * 0.5F + 0.5F) * 2.0F;
      this.particleMaxAge = (int)(4.0F / (super.rand.nextFloat() * 0.9F + 0.1F));
      this.particleAge = 0;
   }

   public EntityFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      this(var1, var2, var4, var6);
      super.motionX = var8 + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.4F);
      super.motionY = var10 + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.4F);
      super.motionZ = var12 + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.4F);
      float var14 = (float)(Math.random() + Math.random() + 1.0D) * 0.15F;
      float var15 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionY * super.motionY + super.motionZ * super.motionZ);
      super.motionX = super.motionX / (double)var15 * (double)var14 * 0.4000000059604645D;
      super.motionY = super.motionY / (double)var15 * (double)var14 * 0.4000000059604645D + 0.10000000149011612D;
      super.motionZ = super.motionZ / (double)var15 * (double)var14 * 0.4000000059604645D;
   }

   public EntityFX multiplyVelocity(float var1) {
      super.motionX *= (double)var1;
      super.motionY = (super.motionY - 0.10000000149011612D) * (double)var1 + 0.10000000149011612D;
      super.motionZ *= (double)var1;
      return this;
   }

   public EntityFX multipleParticleScaleBy(float var1) {
      this.setSize(0.2F * var1, 0.2F * var1);
      this.particleScale *= var1;
      return this;
   }

   public void setRBGColorF(float var1, float var2, float var3) {
      this.particleRed = var1;
      this.particleGreen = var2;
      this.particleBlue = var3;
   }

   public void setAlphaF(float var1) {
      this.particleAlpha = var1;
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

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void entityInit() {}

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(this.particleAge++ >= this.particleMaxAge) {
         this.setDead();
      }

      super.motionY -= 0.04D * (double)this.particleGravity;
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      super.motionX *= 0.9800000190734863D;
      super.motionY *= 0.9800000190734863D;
      super.motionZ *= 0.9800000190734863D;
      if(super.onGround) {
         super.motionX *= 0.699999988079071D;
         super.motionZ *= 0.699999988079071D;
      }

   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = (float)this.particleTextureIndexX / 16.0F;
      float var9 = var8 + 0.0624375F;
      float var10 = (float)this.particleTextureIndexY / 16.0F;
      float var11 = var10 + 0.0624375F;
      float var12 = 0.1F * this.particleScale;
      if(this.particleIcon != null) {
         var8 = this.particleIcon.getMinU();
         var9 = this.particleIcon.getMaxU();
         var10 = this.particleIcon.getMinV();
         var11 = this.particleIcon.getMaxV();
      }

      float var13 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)var2 - interpPosX);
      float var14 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)var2 - interpPosY);
      float var15 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)var2 - interpPosZ);
      var1.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
      var1.addVertexWithUV((double)(var13 - var3 * var12 - var6 * var12), (double)(var14 - var4 * var12), (double)(var15 - var5 * var12 - var7 * var12), (double)var9, (double)var11);
      var1.addVertexWithUV((double)(var13 - var3 * var12 + var6 * var12), (double)(var14 + var4 * var12), (double)(var15 - var5 * var12 + var7 * var12), (double)var9, (double)var10);
      var1.addVertexWithUV((double)(var13 + var3 * var12 + var6 * var12), (double)(var14 + var4 * var12), (double)(var15 + var5 * var12 + var7 * var12), (double)var8, (double)var10);
      var1.addVertexWithUV((double)(var13 + var3 * var12 - var6 * var12), (double)(var14 - var4 * var12), (double)(var15 + var5 * var12 - var7 * var12), (double)var8, (double)var11);
   }

   public int getFXLayer() {
      return 0;
   }

   public void writeEntityToNBT(NBTTagCompound var1) {}

   public void readEntityFromNBT(NBTTagCompound var1) {}

   public void setParticleIcon(IIcon var1) {
      if(this.getFXLayer() == 1) {
         this.particleIcon = var1;
      } else {
         if(this.getFXLayer() != 2) {
            throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
         }

         this.particleIcon = var1;
      }

   }

   public void setParticleTextureIndex(int var1) {
      if(this.getFXLayer() != 0) {
         throw new RuntimeException("Invalid call to Particle.setMiscTex");
      } else {
         this.particleTextureIndexX = var1 % 16;
         this.particleTextureIndexY = var1 / 16;
      }
   }

   public void nextTextureIndexX() {
      ++this.particleTextureIndexX;
   }

   public boolean canAttackWithItem() {
      return false;
   }

   public String toString() {
      return this.getClass().getSimpleName() + ", Pos (" + super.posX + "," + super.posY + "," + super.posZ + "), RGBA (" + this.particleRed + "," + this.particleGreen + "," + this.particleBlue + "," + this.particleAlpha + "), Age " + this.particleAge;
   }
}
