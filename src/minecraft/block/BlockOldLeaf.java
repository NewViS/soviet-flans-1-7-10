package net.minecraft.block;

import java.util.List;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOldLeaf extends BlockLeaves {

   public static final String[][] field_150130_N = new String[][]{{"leaves_oak", "leaves_spruce", "leaves_birch", "leaves_jungle"}, {"leaves_oak_opaque", "leaves_spruce_opaque", "leaves_birch_opaque", "leaves_jungle_opaque"}};
   public static final String[] field_150131_O = new String[]{"oak", "spruce", "birch", "jungle"};


   public int getRenderColor(int var1) {
      return (var1 & 3) == 1?ColorizerFoliage.getFoliageColorPine():((var1 & 3) == 2?ColorizerFoliage.getFoliageColorBirch():super.getRenderColor(var1));
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      return (var5 & 3) == 1?ColorizerFoliage.getFoliageColorPine():((var5 & 3) == 2?ColorizerFoliage.getFoliageColorBirch():super.colorMultiplier(var1, var2, var3, var4));
   }

   protected void func_150124_c(World var1, int var2, int var3, int var4, int var5, int var6) {
      if((var5 & 3) == 0 && var1.rand.nextInt(var6) == 0) {
         this.dropBlockAsItem(var1, var2, var3, var4, new ItemStack(Items.apple, 1, 0));
      }

   }

   protected int func_150123_b(int var1) {
      int var2 = super.func_150123_b(var1);
      if((var1 & 3) == 3) {
         var2 = 40;
      }

      return var2;
   }

   public IIcon getIcon(int var1, int var2) {
      return (var2 & 3) == 1?super.field_150129_M[super.field_150127_b][1]:((var2 & 3) == 3?super.field_150129_M[super.field_150127_b][3]:((var2 & 3) == 2?super.field_150129_M[super.field_150127_b][2]:super.field_150129_M[super.field_150127_b][0]));
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
      var3.add(new ItemStack(var1, 1, 2));
      var3.add(new ItemStack(var1, 1, 3));
   }

   public void registerBlockIcons(IIconRegister var1) {
      for(int var2 = 0; var2 < field_150130_N.length; ++var2) {
         super.field_150129_M[var2] = new IIcon[field_150130_N[var2].length];

         for(int var3 = 0; var3 < field_150130_N[var2].length; ++var3) {
            super.field_150129_M[var2][var3] = var1.registerIcon(field_150130_N[var2][var3]);
         }
      }

   }

   public String[] func_150125_e() {
      return field_150131_O;
   }

}
