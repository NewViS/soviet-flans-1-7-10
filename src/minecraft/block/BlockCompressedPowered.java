package net.minecraft.block;

import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

public class BlockCompressedPowered extends BlockCompressed {

   public BlockCompressedPowered(MapColor var1) {
      super(var1);
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   public boolean canProvidePower() {
      return true;
   }

   public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return 15;
   }
}
