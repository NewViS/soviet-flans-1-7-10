package net.minecraft.inventory;

import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class ContainerEnchantment$2 extends Slot {

   // $FF: synthetic field
   final ContainerEnchantment container;


   ContainerEnchantment$2(ContainerEnchantment var1, IInventory var2, int var3, int var4, int var5) {
      super(var2, var3, var4, var5);
      this.container = var1;
   }

   public boolean isItemValid(ItemStack var1) {
      return true;
   }
}
