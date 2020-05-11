package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class BlockStone extends Block {

   public BlockStone() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.cobblestone);
   }
}
