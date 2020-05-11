package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityDirectional;

public class RadioTE extends TileEntityDirectional {

   protected boolean active;


   public void setActive(boolean b) {
      this.active = b;
   }

   public boolean isActive() {
      return this.active;
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
