package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockCompressed extends Block {

   private final MapColor field_150202_a;


   public BlockCompressed(MapColor var1) {
      super(Material.iron);
      this.field_150202_a = var1;
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public MapColor getMapColor(int var1) {
      return this.field_150202_a;
   }
}
