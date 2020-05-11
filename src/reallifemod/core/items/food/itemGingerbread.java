package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.core.items.food.ExtendedItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class itemGingerbread extends ExtendedItemFood {

   private boolean alwaysEdible;


   public itemGingerbread(int HealAmmount, float saturationModifier, boolean isWolfsFavoriteMeat) {
      super(HealAmmount, saturationModifier, isWolfsFavoriteMeat);
      this.func_77655_b("gingerbread");
      this.func_111206_d("reallifemod:gingerbread");
      this.setPrice(0.6600000262260437D);
   }

   public EnumAction func_77661_b(ItemStack par1ItemStack) {
      return EnumAction.eat;
   }

   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      if(!par3EntityPlayer.capabilities.isCreativeMode) {
         par3EntityPlayer.setItemInUse(par1ItemStack, this.func_77626_a(par1ItemStack));
      }

      return par1ItemStack;
   }
}
