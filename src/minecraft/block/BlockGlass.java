package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockGlass extends BlockBreakable {

   public BlockGlass(Material var1, boolean var2) {
      super("glass", var1, var2);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public int getRenderBlockPass() {
      return 0;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   protected boolean canSilkHarvest() {
      return true;
   }
}
