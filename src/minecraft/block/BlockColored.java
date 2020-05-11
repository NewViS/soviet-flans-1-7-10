package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockColored extends Block {

   private IIcon[] field_150033_a;


   public BlockColored(Material var1) {
      super(var1);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      return this.field_150033_a[var2 % this.field_150033_a.length];
   }

   public int damageDropped(int var1) {
      return var1;
   }

   public static int func_150032_b(int var0) {
      return func_150031_c(var0);
   }

   public static int func_150031_c(int var0) {
      return ~var0 & 15;
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      for(int var4 = 0; var4 < 16; ++var4) {
         var3.add(new ItemStack(var1, 1, var4));
      }

   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_150033_a = new IIcon[16];

      for(int var2 = 0; var2 < this.field_150033_a.length; ++var2) {
         this.field_150033_a[var2] = var1.registerIcon(this.getTextureName() + "_" + ItemDye.field_150921_b[func_150031_c(var2)]);
      }

   }

   public MapColor getMapColor(int var1) {
      return MapColor.getMapColorForBlockColored(var1);
   }
}
