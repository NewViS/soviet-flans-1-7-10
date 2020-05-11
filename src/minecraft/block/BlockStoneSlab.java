package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockStoneSlab extends BlockSlab {

   public static final String[] field_150006_b = new String[]{"stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick", "netherBrick", "quartz"};
   private IIcon field_150007_M;


   public BlockStoneSlab(boolean var1) {
      super(var1, Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      int var3 = var2 & 7;
      if(super.field_150004_a && (var2 & 8) != 0) {
         var1 = 1;
      }

      return var3 == 0?(var1 != 1 && var1 != 0?this.field_150007_M:super.blockIcon):(var3 == 1?Blocks.sandstone.getBlockTextureFromSide(var1):(var3 == 2?Blocks.planks.getBlockTextureFromSide(var1):(var3 == 3?Blocks.cobblestone.getBlockTextureFromSide(var1):(var3 == 4?Blocks.brick_block.getBlockTextureFromSide(var1):(var3 == 5?Blocks.stonebrick.getIcon(var1, 0):(var3 == 6?Blocks.nether_brick.getBlockTextureFromSide(1):(var3 == 7?Blocks.quartz_block.getBlockTextureFromSide(var1):super.blockIcon)))))));
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon("stone_slab_top");
      this.field_150007_M = var1.registerIcon("stone_slab_side");
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.stone_slab);
   }

   protected ItemStack createStackedBlock(int var1) {
      return new ItemStack(Item.getItemFromBlock(Blocks.stone_slab), 2, var1 & 7);
   }

   public String func_150002_b(int var1) {
      if(var1 < 0 || var1 >= field_150006_b.length) {
         var1 = 0;
      }

      return super.getUnlocalizedName() + "." + field_150006_b[var1];
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      if(var1 != Item.getItemFromBlock(Blocks.double_stone_slab)) {
         for(int var4 = 0; var4 <= 7; ++var4) {
            if(var4 != 2) {
               var3.add(new ItemStack(var1, 1, var4));
            }
         }

      }
   }

}
