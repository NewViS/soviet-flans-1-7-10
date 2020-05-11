package net.minecraft.client.renderer;

import java.util.Comparator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.EntityLivingBase;

public class RenderSorter implements Comparator {

   private EntityLivingBase baseEntity;


   public RenderSorter(EntityLivingBase var1) {
      this.baseEntity = var1;
   }

   public int compare(WorldRenderer var1, WorldRenderer var2) {
      if(var1.isInFrustum && !var2.isInFrustum) {
         return 1;
      } else if(var2.isInFrustum && !var1.isInFrustum) {
         return -1;
      } else {
         double var3 = (double)var1.distanceToEntitySquared(this.baseEntity);
         double var5 = (double)var2.distanceToEntitySquared(this.baseEntity);
         return var3 < var5?1:(var3 > var5?-1:(var1.chunkIndex < var2.chunkIndex?1:-1));
      }
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.compare((WorldRenderer)var1, (WorldRenderer)var2);
   }
}
