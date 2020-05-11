package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockSandStone extends Block {

   public static final String[] field_150157_a = new String[]{"default", "chiseled", "smooth"};
   private static final String[] field_150156_b = new String[]{"normal", "carved", "smooth"};
   private IIcon[] field_150158_M;
   private IIcon field_150159_N;
   private IIcon field_150160_O;


   public BlockSandStone() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      if(var1 != 1 && (var1 != 0 || var2 != 1 && var2 != 2)) {
         if(var1 == 0) {
            return this.field_150160_O;
         } else {
            if(var2 < 0 || var2 >= this.field_150158_M.length) {
               var2 = 0;
            }

            return this.field_150158_M[var2];
         }
      } else {
         return this.field_150159_N;
      }
   }

   public int damageDropped(int var1) {
      return var1;
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
      var3.add(new ItemStack(var1, 1, 2));
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_150158_M = new IIcon[field_150156_b.length];

      for(int var2 = 0; var2 < this.field_150158_M.length; ++var2) {
         this.field_150158_M[var2] = var1.registerIcon(this.getTextureName() + "_" + field_150156_b[var2]);
      }

      this.field_150159_N = var1.registerIcon(this.getTextureName() + "_top");
      this.field_150160_O = var1.registerIcon(this.getTextureName() + "_bottom");
   }

}
