package net.minecraft.inventory;

import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.InventoryBasic;

class ContainerEnchantment$1 extends InventoryBasic {

   // $FF: synthetic field
   final ContainerEnchantment container;


   ContainerEnchantment$1(ContainerEnchantment var1, String var2, boolean var3, int var4) {
      super(var2, var3, var4);
      this.container = var1;
   }

   public int getInventoryStackLimit() {
      return 1;
   }

   public void markDirty() {
      super.markDirty();
      this.container.onCraftMatrixChanged(this);
   }
}
