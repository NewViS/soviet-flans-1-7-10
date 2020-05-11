package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockBookshelf extends Block {

   public BlockBookshelf() {
      super(Material.wood);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 != 1 && var1 != 0?super.getIcon(var1, var2):Blocks.planks.getBlockTextureFromSide(var1);
   }

   public int quantityDropped(Random var1) {
      return 3;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.book;
   }
}
