package net.minecraft.block;

import net.minecraft.block.BlockButton;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlockButtonWood extends BlockButton {

   protected BlockButtonWood() {
      super(true);
   }

   public IIcon getIcon(int var1, int var2) {
      return Blocks.planks.getBlockTextureFromSide(1);
   }
}
