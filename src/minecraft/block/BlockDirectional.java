package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockDirectional extends Block {

   protected BlockDirectional(Material var1) {
      super(var1);
   }

   public static int getDirection(int var0) {
      return var0 & 3;
   }
}
