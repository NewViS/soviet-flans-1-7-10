package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.MapColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class BlockObsidian extends BlockStone {

   public int quantityDropped(Random var1) {
      return 1;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.obsidian);
   }

   public MapColor getMapColor(int var1) {
      return MapColor.obsidianColor;
   }
}
