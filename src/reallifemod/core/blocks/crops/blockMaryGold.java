package de.ItsAMysterious.mods.reallifemod.core.blocks.crops;

import java.util.Random;
import net.minecraft.block.BlockBush;
import net.minecraft.util.IIcon;

public class blockMaryGold extends BlockBush {

   private IIcon[] blockIcon;


   public blockMaryGold() {
      this.func_149663_c("marygold");
      this.func_149658_d("reallifemod:marygold");
      this.func_149672_a(field_149779_h);
   }

   public int func_149745_a(Random random) {
      return 1;
   }
}
