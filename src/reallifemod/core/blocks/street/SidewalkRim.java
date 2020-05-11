package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SidewalkRim extends Block {

   public SidewalkRim() {
      super(Material.rock);
      this.func_149658_d("reallifemod:tar");
      this.func_149663_c("sidewalk");
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.3F, 1.0F);
   }

   public boolean func_149662_c() {
      return false;
   }
}
