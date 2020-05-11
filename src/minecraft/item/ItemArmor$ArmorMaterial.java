package net.minecraft.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public enum ItemArmor$ArmorMaterial {

   CLOTH("CLOTH", 0, 5, new int[]{1, 3, 2, 1}, 15),
   CHAIN("CHAIN", 1, 15, new int[]{2, 5, 4, 1}, 12),
   IRON("IRON", 2, 15, new int[]{2, 6, 5, 2}, 9),
   GOLD("GOLD", 3, 7, new int[]{2, 5, 3, 1}, 25),
   DIAMOND("DIAMOND", 4, 33, new int[]{3, 8, 6, 3}, 10);
   private int maxDamageFactor;
   private int[] damageReductionAmountArray;
   private int enchantability;
   // $FF: synthetic field
   private static final ItemArmor$ArmorMaterial[] $VALUES = new ItemArmor$ArmorMaterial[]{CLOTH, CHAIN, IRON, GOLD, DIAMOND};


   private ItemArmor$ArmorMaterial(String var1, int var2, int var3, int[] var4, int var5) {
      this.maxDamageFactor = var3;
      this.damageReductionAmountArray = var4;
      this.enchantability = var5;
   }

   public int getDurability(int var1) {
      return ItemArmor.access$000()[var1] * this.maxDamageFactor;
   }

   public int getDamageReductionAmount(int var1) {
      return this.damageReductionAmountArray[var1];
   }

   public int getEnchantability() {
      return this.enchantability;
   }

   public Item func_151685_b() {
      return this == CLOTH?Items.leather:(this == CHAIN?Items.iron_ingot:(this == GOLD?Items.gold_ingot:(this == IRON?Items.iron_ingot:(this == DIAMOND?Items.diamond:null))));
   }

}
