package net.minecraft.inventory;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class ContainerHorseInventory$2 extends Slot {

   // $FF: synthetic field
   final EntityHorse theHorse;
   // $FF: synthetic field
   final ContainerHorseInventory field_111240_b;


   ContainerHorseInventory$2(ContainerHorseInventory var1, IInventory var2, int var3, int var4, int var5, EntityHorse var6) {
      super(var2, var3, var4, var5);
      this.field_111240_b = var1;
      this.theHorse = var6;
   }

   public boolean isItemValid(ItemStack var1) {
      return super.isItemValid(var1) && this.theHorse.func_110259_cr() && EntityHorse.func_146085_a(var1.getItem());
   }

   public boolean func_111238_b() {
      return this.theHorse.func_110259_cr();
   }
}
