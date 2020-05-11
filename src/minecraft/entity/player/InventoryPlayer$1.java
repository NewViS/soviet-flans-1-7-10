package net.minecraft.entity.player;

import java.util.concurrent.Callable;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

class InventoryPlayer$1 implements Callable {

   // $FF: synthetic field
   final ItemStack theItemStack;
   // $FF: synthetic field
   final InventoryPlayer playerInventory;


   InventoryPlayer$1(InventoryPlayer var1, ItemStack var2) {
      this.playerInventory = var1;
      this.theItemStack = var2;
   }

   public String call() {
      return this.theItemStack.getDisplayName();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
