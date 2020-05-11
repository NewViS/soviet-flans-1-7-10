package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityRainFX;
import net.minecraft.world.World;

public class EntitySplashFX extends EntityRainFX {

   public EntitySplashFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6);
      super.particleGravity = 0.04F;
      this.nextTextureIndexX();
      if(var10 == 0.0D && (var8 != 0.0D || var12 != 0.0D)) {
         super.motionX = var8;
         super.motionY = var10 + 0.1D;
         super.motionZ = var12;
      }

   }
}
