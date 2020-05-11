package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBuchs extends Block {

   public BlockBuchs(Material material) {
      super(material);
      this.func_149658_d("reallifemod:buchs");
      this.func_149663_c("boxwood");
      this.func_149645_b();
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149637_q() {
      return false;
   }

   public int func_149645_b() {
      return 1;
   }
}
