package de.ItsAMysterious.mods.reallifemod.core.gui.containers;

import de.ItsAMysterious.mods.reallifemod.core.gui.containers.slots.SlotFridge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFridge extends Container {

   private int numRows;
   private IInventory FridgeInventory;


   public ContainerFridge(IInventory p_i1083_1_, IInventory FridgeInventory) {
      this.FridgeInventory = FridgeInventory;
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 0, 62, 20));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 1, 80, 20));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 2, 98, 20));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 3, 62, 38));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 4, 80, 38));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 5, 98, 38));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 6, 62, 56));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 7, 80, 56));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 8, 98, 56));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 9, 62, 74));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 10, 80, 74));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 11, 98, 74));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 12, 62, 92));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 13, 80, 92));
      this.func_75146_a(new SlotFridge(this, FridgeInventory, 14, 98, 92));

      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.func_75146_a(new Slot(p_i1083_1_, j + i * 9 + 9, 8 + j * 18, 122 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.func_75146_a(new Slot(p_i1083_1_, i, 8 + i * 18, 180));
      }

   }

   public boolean func_75145_c(EntityPlayer player) {
      return this.FridgeInventory.isUseableByPlayer(player);
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);
      if(slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if(p_82846_2_ < this.numRows * 3) {
            if(!this.func_75135_a(itemstack1, this.numRows * 3, this.field_75151_b.size(), true)) {
               return null;
            }
         } else if(!this.func_75135_a(itemstack1, 0, this.numRows * 3, false)) {
            return null;
         }

         if(itemstack1.stackSize == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }
      }

      return itemstack;
   }
}
