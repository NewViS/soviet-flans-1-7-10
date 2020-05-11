package de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.sanitary;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class FloorTile extends Block {

   public FloorTile(Material material) {
      super(material);
      this.func_149663_c("tile");
      this.field_149768_d = "reallifemod:tile";
      this.func_149672_a(field_149769_e);
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.1F, 1.0F);
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149721_r() {
      return false;
   }
}
