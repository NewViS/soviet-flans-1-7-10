package net.minecraft.block;

import net.minecraft.block.BlockButton;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlockButtonStone extends BlockButton {

   protected BlockButtonStone() {
      super(false);
   }

   public IIcon getIcon(int var1, int var2) {
      return Blocks.stone.getBlockTextureFromSide(1);
   }
}
