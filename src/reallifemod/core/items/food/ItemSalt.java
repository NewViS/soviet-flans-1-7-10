package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.core.items.food.ExtendedItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSalt extends ExtendedItemFood {

   private boolean alwaysEdible;


   public ItemSalt(int HealAmount, float saturationModifier, boolean isWolfsFavoriteMeat) {
      super(HealAmount, saturationModifier, isWolfsFavoriteMeat);
      this.func_77655_b("salt");
      this.func_111206_d("reallifemod:salt");
      this.alwaysEdible = true;
   }

   public EnumAction func_77661_b(ItemStack par1ItemStack) {
      return EnumAction.eat;
   }

   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      if(par3EntityPlayer.canEat(this.alwaysEdible)) {
         par3EntityPlayer.setItemInUse(par1ItemStack, this.func_77626_a(par1ItemStack));
      }

      RealLifeProperties props = RealLifeProperties.get(par3EntityPlayer);
      if(RealLifeProperties.thirst + 20.0F < 100.0F) {
         RealLifeProperties.thirst += 20.0F;
      } else {
         RealLifeProperties.thirst = 100.0F;
      }

      return par1ItemStack;
   }
}
