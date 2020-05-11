package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.tileentity.TileEntity;

public class OakTreeTE extends TileEntity {

   public double scale;
   public int stability;
   public double rotationPitch;
   public double rotationYaw;


   public void func_145845_h() {
      if(this.stability <= 0) {
         ++this.rotationPitch;
      }

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
