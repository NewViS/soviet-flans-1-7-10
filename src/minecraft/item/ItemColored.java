package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemColored extends ItemBlock {

   private final Block field_150944_b;
   private String[] field_150945_c;


   public ItemColored(Block var1, boolean var2) {
      super(var1);
      this.field_150944_b = var1;
      if(var2) {
         this.setMaxDamage(0);
         this.setHasSubtypes(true);
      }

   }

   public int getColorFromItemStack(ItemStack var1, int var2) {
      return this.field_150944_b.getRenderColor(var1.getItemDamage());
   }

   public IIcon getIconFromDamage(int var1) {
      return this.field_150944_b.getIcon(0, var1);
   }

   public int getMetadata(int var1) {
      return var1;
   }

   public ItemColored func_150943_a(String[] var1) {
      this.field_150945_c = var1;
      return this;
   }

   public String getUnlocalizedName(ItemStack var1) {
      if(this.field_150945_c == null) {
         return super.getUnlocalizedName(var1);
      } else {
         int var2 = var1.getItemDamage();
         return var2 >= 0 && var2 < this.field_150945_c.length?super.getUnlocalizedName(var1) + "." + this.field_150945_c[var2]:super.getUnlocalizedName(var1);
      }
   }
}
