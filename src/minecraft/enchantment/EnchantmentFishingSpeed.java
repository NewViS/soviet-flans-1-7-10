package net.minecraft.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentFishingSpeed extends Enchantment {

   protected EnchantmentFishingSpeed(int var1, int var2, EnumEnchantmentType var3) {
      super(var1, var2, var3);
      this.setName("fishingSpeed");
   }

   public int getMinEnchantability(int var1) {
      return 15 + (var1 - 1) * 9;
   }

   public int getMaxEnchantability(int var1) {
      return super.getMinEnchantability(var1) + 50;
   }

   public int getMaxLevel() {
      return 3;
   }
}
