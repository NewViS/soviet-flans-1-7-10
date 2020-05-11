package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class bottleWater extends ExtendedItem {

   public bottleWater() {
      this.func_77655_b("waterbottle");
      this.func_111206_d("reallifemod:bottleFull");
      this.func_77637_a(RealLifeMod.rlmfood);
      this.func_77625_d(1);
      this.setPrice(0.699999988079071D);
   }

   public EnumAction func_77661_b(ItemStack par1ItemStack) {
      return EnumAction.drink;
   }

   public ItemStack func_77654_b(ItemStack par1ItemStack, World p_77654_2_, EntityPlayer par3EntityPlayer) {
      if(par3EntityPlayer != null && !par3EntityPlayer.capabilities.isCreativeMode) {
         RealLifeProperties props = (RealLifeProperties)par3EntityPlayer.getExtendedProperties("RealLifeProps");
         if(RealLifeProperties.thirst - 20.0F > 0.0F) {
            RealLifeProperties.thirst -= 20.0F;
         } else {
            RealLifeProperties.thirst = 0.0F;
         }

         par3EntityPlayer.inventory.setInventorySlotContents(par3EntityPlayer.inventory.currentItem, new ItemStack(RealLifeMod_Items.emptybottle));
      }

      return par1ItemStack;
   }

   public int func_77626_a(ItemStack p_77626_1_) {
      return 32;
   }

   public ItemStack func_77659_a(ItemStack par1ItemStack, World world, EntityPlayer par3EntityPlayer) {
      par3EntityPlayer.setItemInUse(par1ItemStack, this.func_77626_a(par1ItemStack));
      return par1ItemStack;
   }
}
