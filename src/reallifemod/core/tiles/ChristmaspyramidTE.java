package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityDirectional;

public class ChristmaspyramidTE extends TileEntityDirectional {

   private float rotation = 0.0F;
   private boolean active;


   public void clicked() {
      if(this.isActive()) {
         this.setActive(false);
      } else if(!this.isActive()) {
         this.setActive(true);
      }

   }

   public void func_145845_h() {
      if(this.active) {
         this.setRotation(0.05F);
      }

   }

   public boolean isActive() {
      return this.active;
   }

   public boolean setActive(boolean active) {
      this.active = active;
      return active;
   }

   public float getRotation() {
      return this.rotation;
   }

   public void setRotation(float f) {
      this.rotation += f;
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
