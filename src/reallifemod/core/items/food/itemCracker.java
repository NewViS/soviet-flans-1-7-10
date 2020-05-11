package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.core.items.food.ExtendedItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class itemCracker extends ExtendedItemFood {

   private boolean alwaysEdible;


   public itemCracker(int HealAmmount, float saturationModifier, boolean isWolfsFavoriteMeat) {
      super(HealAmmount, saturationModifier, isWolfsFavoriteMeat);
      this.func_77655_b("cracker");
      this.func_111206_d("reallifemod:itemCracker");
      this.setPrice(0.5D);
   }

   public EnumAction func_77661_b(ItemStack par1ItemStack) {
      return EnumAction.eat;
   }

   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      if(par3EntityPlayer.canEat(this.alwaysEdible)) {
         par3EntityPlayer.setItemInUse(par1ItemStack, this.func_77626_a(par1ItemStack));
      }

      return par1ItemStack;
   }
}
