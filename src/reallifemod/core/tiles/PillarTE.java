package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.pillarBlock;
import net.minecraft.tileentity.TileEntity;

public class PillarTE extends TileEntity {

   public String Texture;
   public float scale = 1.0F;
   public pillarBlock.pillarType type = null;


   public PillarTE(String texture, pillarBlock.pillarType type) {
      this.Texture = texture;
      this.type = type;
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
