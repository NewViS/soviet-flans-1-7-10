package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.items.food.ExtendedItemFood;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class ItemFoodGeneric extends ExtendedItemFood {

   public ItemFoodGeneric(int HealAmmount, boolean isWolfsFavorite, String name, String texturename) {
      super(HealAmmount, isWolfsFavorite);
      this.func_77655_b(name);
      this.func_111206_d("reallifemod:" + texturename);
      this.func_77637_a(RealLifeMod.rlmfood);
      this.setPrice(0.11999999731779099D);
   }

   public EnumAction func_77661_b(ItemStack par1ItemStack) {
      return EnumAction.eat;
   }
}
