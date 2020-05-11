package net.minecraft.block;

import net.minecraft.block.Block$SoundType;

final class Block$1 extends Block$SoundType {

   Block$1(String var1, float var2, float var3) {
      super(var1, var2, var3);
   }

   public String getBreakSound() {
      return "dig.glass";
   }

   public String func_150496_b() {
      return "step.stone";
   }
}
