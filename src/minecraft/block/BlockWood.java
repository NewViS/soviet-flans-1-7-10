package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockWood extends Block {

   public static final String[] field_150096_a = new String[]{"oak", "spruce", "birch", "jungle", "acacia", "big_oak"};
   private IIcon[] field_150095_b;


   public BlockWood() {
      super(Material.wood);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      if(var2 < 0 || var2 >= this.field_150095_b.length) {
         var2 = 0;
      }

      return this.field_150095_b[var2];
   }

   public int damageDropped(int var1) {
      return var1;
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
      var3.add(new ItemStack(var1, 1, 2));
      var3.add(new ItemStack(var1, 1, 3));
      var3.add(new ItemStack(var1, 1, 4));
      var3.add(new ItemStack(var1, 1, 5));
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_150095_b = new IIcon[field_150096_a.length];

      for(int var2 = 0; var2 < this.field_150095_b.length; ++var2) {
         this.field_150095_b[var2] = var1.registerIcon(this.getTextureName() + "_" + field_150096_a[var2]);
      }

   }

}
