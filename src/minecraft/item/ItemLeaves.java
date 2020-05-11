package net.minecraft.item;

import net.minecraft.block.BlockLeaves;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemLeaves extends ItemBlock {

   private final BlockLeaves field_150940_b;


   public ItemLeaves(BlockLeaves var1) {
      super(var1);
      this.field_150940_b = var1;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public int getMetadata(int var1) {
      return var1 | 4;
   }

   public IIcon getIconFromDamage(int var1) {
      return this.field_150940_b.getIcon(0, var1);
   }

   public int getColorFromItemStack(ItemStack var1, int var2) {
      return this.field_150940_b.getRenderColor(var1.getItemDamage());
   }

   public String getUnlocalizedName(ItemStack var1) {
      int var2 = var1.getItemDamage();
      if(var2 < 0 || var2 >= this.field_150940_b.func_150125_e().length) {
         var2 = 0;
      }

      return super.getUnlocalizedName() + "." + this.field_150940_b.func_150125_e()[var2];
   }
}
