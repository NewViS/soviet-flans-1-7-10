package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityDirectional;
import java.util.Random;

public class FireplaceTE extends TileEntityDirectional {

   public boolean burning = false;
   private Random rand;


   public void extinguish() {
      this.burning = false;
   }

   public void lit() {
      this.burning = true;
   }

   public void func_145845_h() {
      super.func_145845_h();
      if(!this.field_145850_b.isRemote && this.burning) {
         this.func_145831_w().spawnParticle("flame", (double)this.field_145851_c + 0.5D, (double)this.field_145848_d, (double)this.field_145849_e + 0.5D, 0.0D, 0.0025D, 0.0D);
      }

      if(this.field_145850_b.isRaining() && this.field_145850_b.canBlockSeeTheSky(this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
         this.extinguish();
         this.field_145850_b.spawnParticle("smoke", (double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.5D, (double)this.field_145849_e + 0.5D, 0.025D, 0.025D, 0.025D);
      }

      this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
   }

   public int func_145832_p() {
      if(this.field_145850_b != null) {
         if(this.field_145847_g == -1) {
            this.field_145847_g = this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, this.field_145849_e);
         }

         return this.field_145847_g;
      } else {
         return 1;
      }
   }
}
