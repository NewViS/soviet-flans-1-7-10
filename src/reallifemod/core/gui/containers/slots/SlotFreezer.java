package de.ItsAMysterious.mods.reallifemod.core.gui.containers.slots;

import de.ItsAMysterious.mods.reallifemod.core.gui.containers.ContainerFreezer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFreezer extends Slot {

   final ContainerFreezer sideBoard;


   public SlotFreezer(ContainerFreezer sideboard, IInventory inventory, int id, int x, int y) {
      super(inventory, id, x, y);
      this.sideBoard = sideboard;
   }

   public boolean isStackValidForSlot(ItemStack stack) {
      return true;
   }

   public int func_75219_a() {
      return 64;
   }
}
