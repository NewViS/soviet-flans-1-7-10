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

public class BlockWoodSlab extends BlockSlab {

   public static final String[] field_150005_b = new String[]{"oak", "spruce", "birch", "jungle", "acacia", "big_oak"};


   public BlockWoodSlab(boolean var1) {
      super(var1, Material.wood);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      return Blocks.planks.getIcon(var1, var2 & 7);
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.wooden_slab);
   }

   protected ItemStack createStackedBlock(int var1) {
      return new ItemStack(Item.getItemFromBlock(Blocks.wooden_slab), 2, var1 & 7);
   }

   public String func_150002_b(int var1) {
      if(var1 < 0 || var1 >= field_150005_b.length) {
         var1 = 0;
      }

      return super.getUnlocalizedName() + "." + field_150005_b[var1];
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      if(var1 != Item.getItemFromBlock(Blocks.double_wooden_slab)) {
         for(int var4 = 0; var4 < field_150005_b.length; ++var4) {
            var3.add(new ItemStack(var1, 1, var4));
         }

      }
   }

   public void registerBlockIcons(IIconRegister var1) {}

}
