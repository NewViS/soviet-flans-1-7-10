package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.core.items.food.ExtendedItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemToast extends ExtendedItemFood {

   private boolean alwaysEdible;


   public ItemToast(int HealAmmount, boolean isWolfsFavorite) {
      super(HealAmmount, isWolfsFavorite);
      this.func_77655_b("toast");
      this.func_111206_d("reallifemod:itemToast");
      this.alwaysEdible = true;
      this.setPrice(0.05D);
   }

   public EnumAction func_77661_b(ItemStack par1ItemStack) {
      return EnumAction.eat;
   }

   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      if(!par3EntityPlayer.capabilities.isCreativeMode && par3EntityPlayer.canEat(this.alwaysEdible)) {
         par3EntityPlayer.setItemInUse(par1ItemStack, this.func_77626_a(par1ItemStack));
      }

      return par1ItemStack;
   }
}
