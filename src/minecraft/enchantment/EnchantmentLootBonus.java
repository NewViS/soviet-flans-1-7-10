package net.minecraft.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentLootBonus extends Enchantment {

   protected EnchantmentLootBonus(int var1, int var2, EnumEnchantmentType var3) {
      super(var1, var2, var3);
      if(var3 == EnumEnchantmentType.digger) {
         this.setName("lootBonusDigger");
      } else if(var3 == EnumEnchantmentType.fishing_rod) {
         this.setName("lootBonusFishing");
      } else {
         this.setName("lootBonus");
      }

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

   public boolean canApplyTogether(Enchantment var1) {
      return super.canApplyTogether(var1) && var1.effectId != Enchantment.silkTouch.effectId;
   }
}
