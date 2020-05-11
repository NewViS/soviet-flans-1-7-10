package net.minecraft.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class EnchantmentUntouching extends Enchantment {

   protected EnchantmentUntouching(int var1, int var2) {
      super(var1, var2, EnumEnchantmentType.digger);
      this.setName("untouching");
   }

   public int getMinEnchantability(int var1) {
      return 15;
   }

   public int getMaxEnchantability(int var1) {
      return super.getMinEnchantability(var1) + 50;
   }

   public int getMaxLevel() {
      return 1;
   }

   public boolean canApplyTogether(Enchantment var1) {
      return super.canApplyTogether(var1) && var1.effectId != Enchantment.fortune.effectId;
   }

   public boolean canApply(ItemStack var1) {
      return var1.getItem() == Items.shears?true:super.canApply(var1);
   }
}
