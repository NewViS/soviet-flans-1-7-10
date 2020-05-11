package net.minecraft.client.particle;

import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySuspendFX extends EntityFX {

   public EntitySuspendFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4 - 0.125D, var6, var8, var10, var12);
      super.particleRed = 0.4F;
      super.particleGreen = 0.4F;
      super.particleBlue = 0.7F;
      this.setParticleTextureIndex(0);
      this.setSize(0.01F, 0.01F);
      super.particleScale *= super.rand.nextFloat() * 0.6F + 0.2F;
      super.motionX = var8 * 0.0D;
      super.motionY = var10 * 0.0D;
      super.motionZ = var12 * 0.0D;
      super.particleMaxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      if(super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ)).getMaterial() != Material.water) {
         this.setDead();
      }

      if(super.particleMaxAge-- <= 0) {
         this.setDead();
      }

   }
}
