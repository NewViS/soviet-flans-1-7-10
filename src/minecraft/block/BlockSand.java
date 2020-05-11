package net.minecraft.block;

import java.util.List;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockSand extends BlockFalling {

   public static final String[] field_149838_a = new String[]{"default", "red"};
   private static IIcon field_149837_b;
   private static IIcon field_149839_N;


   public IIcon getIcon(int var1, int var2) {
      return var2 == 1?field_149839_N:field_149837_b;
   }

   public void registerBlockIcons(IIconRegister var1) {
      field_149837_b = var1.registerIcon("sand");
      field_149839_N = var1.registerIcon("red_sand");
   }

   public int damageDropped(int var1) {
      return var1;
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
   }

   public MapColor getMapColor(int var1) {
      return var1 == 1?MapColor.dirtColor:MapColor.sandColor;
   }

}
