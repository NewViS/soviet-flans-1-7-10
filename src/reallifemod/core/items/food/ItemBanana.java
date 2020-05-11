package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.core.items.food.ExtendedItemFood;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class ItemBanana extends ExtendedItemFood {

   public ItemBanana(int HealAmmount, boolean isWolfsFavorite) {
      super(HealAmmount, isWolfsFavorite);
      this.func_77655_b("banana");
      this.func_111206_d("reallifemod:banana");
      this.setPrice(0.3100000023841858D);
   }

   public EnumAction func_77661_b(ItemStack par1ItemStack) {
      return EnumAction.eat;
   }
}
