package net.minecraft.client.particle;

import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBubbleFX extends EntityFX {

   public EntityBubbleFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      super.particleRed = 1.0F;
      super.particleGreen = 1.0F;
      super.particleBlue = 1.0F;
      this.setParticleTextureIndex(32);
      this.setSize(0.02F, 0.02F);
      super.particleScale *= super.rand.nextFloat() * 0.6F + 0.2F;
      super.motionX = var8 * 0.20000000298023224D + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
      super.motionY = var10 * 0.20000000298023224D + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
      super.motionZ = var12 * 0.20000000298023224D + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
      super.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      super.motionY += 0.002D;
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      super.motionX *= 0.8500000238418579D;
      super.motionY *= 0.8500000238418579D;
      super.motionZ *= 0.8500000238418579D;
      if(super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ)).getMaterial() != Material.water) {
         this.setDead();
      }

      if(super.particleMaxAge-- <= 0) {
         this.setDead();
      }

   }
}
