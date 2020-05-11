package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemCloth extends ItemBlock {

   public ItemCloth(Block var1) {
      super(var1);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public IIcon getIconFromDamage(int var1) {
      return super.field_150939_a.func_149735_b(2, BlockColored.func_150032_b(var1));
   }

   public int getMetadata(int var1) {
      return var1;
   }

   public String getUnlocalizedName(ItemStack var1) {
      return super.getUnlocalizedName() + "." + ItemDye.field_150923_a[BlockColored.func_150032_b(var1.getItemDamage())];
   }
}
