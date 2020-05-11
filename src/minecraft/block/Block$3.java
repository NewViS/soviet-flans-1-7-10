package net.minecraft.block;

import net.minecraft.block.Block$SoundType;

final class Block$3 extends Block$SoundType {

   Block$3(String var1, float var2, float var3) {
      super(var1, var2, var3);
   }

   public String getBreakSound() {
      return "dig.stone";
   }

   public String func_150496_b() {
      return "random.anvil_land";
   }
}
