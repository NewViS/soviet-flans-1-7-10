package net.minecraft.block;

import java.util.List;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockNewLeaf extends BlockLeaves {

   public static final String[][] field_150132_N = new String[][]{{"leaves_acacia", "leaves_big_oak"}, {"leaves_acacia_opaque", "leaves_big_oak_opaque"}};
   public static final String[] field_150133_O = new String[]{"acacia", "big_oak"};


   protected void func_150124_c(World var1, int var2, int var3, int var4, int var5, int var6) {
      if((var5 & 3) == 1 && var1.rand.nextInt(var6) == 0) {
         this.dropBlockAsItem(var1, var2, var3, var4, new ItemStack(Items.apple, 1, 0));
      }

   }

   public int damageDropped(int var1) {
      return super.damageDropped(var1) + 4;
   }

   public int getDamageValue(World var1, int var2, int var3, int var4) {
      return var1.getBlockMetadata(var2, var3, var4) & 3;
   }

   public IIcon getIcon(int var1, int var2) {
      return (var2 & 3) == 1?super.field_150129_M[super.field_150127_b][1]:super.field_150129_M[super.field_150127_b][0];
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
   }

   public void registerBlockIcons(IIconRegister var1) {
      for(int var2 = 0; var2 < field_150132_N.length; ++var2) {
         super.field_150129_M[var2] = new IIcon[field_150132_N[var2].length];

         for(int var3 = 0; var3 < field_150132_N[var2].length; ++var3) {
            super.field_150129_M[var2][var3] = var1.registerIcon(field_150132_N[var2][var3]);
         }
      }

   }

   public String[] func_150125_e() {
      return field_150133_O;
   }

}
