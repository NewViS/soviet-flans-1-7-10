package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.core.items.food.ExtendedItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class itemChips extends ExtendedItemFood {

   public itemChips(int heal, float sat, boolean wolfsmeat) {
      super(heal, sat, wolfsmeat);
      this.func_77655_b("frenchfries");
      this.func_111206_d("reallifemod:itemChips");
      this.setPrice(1.2000000476837158D);
   }

   public EnumAction func_77661_b(ItemStack par1ItemStack) {
      return EnumAction.eat;
   }

   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      par3EntityPlayer.setItemInUse(par1ItemStack, this.func_77626_a(par1ItemStack));
      return par1ItemStack;
   }
}
