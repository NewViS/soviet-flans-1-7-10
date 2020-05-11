package net.minecraft.block;

import java.util.List;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockNewLog extends BlockLog {

   public static final String[] field_150169_M = new String[]{"acacia", "big_oak"};


   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.field_150167_a = new IIcon[field_150169_M.length];
      super.field_150166_b = new IIcon[field_150169_M.length];

      for(int var2 = 0; var2 < super.field_150167_a.length; ++var2) {
         super.field_150167_a[var2] = var1.registerIcon(this.getTextureName() + "_" + field_150169_M[var2]);
         super.field_150166_b[var2] = var1.registerIcon(this.getTextureName() + "_" + field_150169_M[var2] + "_top");
      }

   }

}
