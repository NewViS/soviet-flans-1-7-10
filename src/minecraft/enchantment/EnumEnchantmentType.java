package net.minecraft.enchantment;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public enum EnumEnchantmentType {

   all("all", 0),
   armor("armor", 1),
   armor_feet("armor_feet", 2),
   armor_legs("armor_legs", 3),
   armor_torso("armor_torso", 4),
   armor_head("armor_head", 5),
   weapon("weapon", 6),
   digger("digger", 7),
   fishing_rod("fishing_rod", 8),
   breakable("breakable", 9),
   bow("bow", 10);
   // $FF: synthetic field
   private static final EnumEnchantmentType[] $VALUES = new EnumEnchantmentType[]{all, armor, armor_feet, armor_legs, armor_torso, armor_head, weapon, digger, fishing_rod, breakable, bow};


   private EnumEnchantmentType(String var1, int var2) {}

   public boolean canEnchantItem(Item var1) {
      if(this == all) {
         return true;
      } else if(this == breakable && var1.isDamageable()) {
         return true;
      } else if(var1 instanceof ItemArmor) {
         if(this == armor) {
            return true;
         } else {
            ItemArmor var2 = (ItemArmor)var1;
            return var2.armorType == 0?this == armor_head:(var2.armorType == 2?this == armor_legs:(var2.armorType == 1?this == armor_torso:(var2.armorType == 3?this == armor_feet:false)));
         }
      } else {
         return var1 instanceof ItemSword?this == weapon:(var1 instanceof ItemTool?this == digger:(var1 instanceof ItemBow?this == bow:(var1 instanceof ItemFishingRod?this == fishing_rod:false)));
      }
   }

}
