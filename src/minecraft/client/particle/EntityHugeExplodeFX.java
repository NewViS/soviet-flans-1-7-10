package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityHugeExplodeFX extends EntityFX {

   private int timeSinceStart;
   private int maximumTime = 8;


   public EntityHugeExplodeFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {}

   public void onUpdate() {
      for(int var1 = 0; var1 < 6; ++var1) {
         double var2 = super.posX + (super.rand.nextDouble() - super.rand.nextDouble()) * 4.0D;
         double var4 = super.posY + (super.rand.nextDouble() - super.rand.nextDouble()) * 4.0D;
         double var6 = super.posZ + (super.rand.nextDouble() - super.rand.nextDouble()) * 4.0D;
         super.worldObj.spawnParticle("largeexplode", var2, var4, var6, (double)((float)this.timeSinceStart / (float)this.maximumTime), 0.0D, 0.0D);
      }

      ++this.timeSinceStart;
      if(this.timeSinceStart == this.maximumTime) {
         this.setDead();
      }

   }

   public int getFXLayer() {
      return 1;
   }
}
