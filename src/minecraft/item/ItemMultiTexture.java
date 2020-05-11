package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemMultiTexture extends ItemBlock {

   protected final Block field_150941_b;
   protected final String[] field_150942_c;


   public ItemMultiTexture(Block var1, Block var2, String[] var3) {
      super(var1);
      this.field_150941_b = var2;
      this.field_150942_c = var3;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public IIcon getIconFromDamage(int var1) {
      return this.field_150941_b.getIcon(2, var1);
   }

   public int getMetadata(int var1) {
      return var1;
   }

   public String getUnlocalizedName(ItemStack var1) {
      int var2 = var1.getItemDamage();
      if(var2 < 0 || var2 >= this.field_150942_c.length) {
         var2 = 0;
      }

      return super.getUnlocalizedName() + "." + this.field_150942_c[var2];
   }
}
