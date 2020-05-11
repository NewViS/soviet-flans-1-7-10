package net.minecraft.inventory;

import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.InventoryBasic;

class ContainerRepair$1 extends InventoryBasic {

   // $FF: synthetic field
   final ContainerRepair repairContainer;


   ContainerRepair$1(ContainerRepair var1, String var2, boolean var3, int var4) {
      super(var2, var3, var4);
      this.repairContainer = var1;
   }

   public void markDirty() {
      super.markDirty();
      this.repairContainer.onCraftMatrixChanged(this);
   }
}
