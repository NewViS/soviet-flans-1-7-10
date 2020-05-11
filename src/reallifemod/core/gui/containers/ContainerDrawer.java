package de.ItsAMysterious.mods.reallifemod.core.gui.containers;

import de.ItsAMysterious.mods.reallifemod.core.gui.containers.slots.SlotDrawer;
import de.ItsAMysterious.mods.reallifemod.core.tiles.DrawerTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDrawer extends Container {

   protected DrawerTE DrawerInventory;


   public ContainerDrawer(InventoryPlayer playerInventory, DrawerTE drawerInventory) {
      this.DrawerInventory = drawerInventory;
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 0, 44, 20));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 1, 62, 20));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 2, 80, 20));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 3, 98, 20));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 4, 116, 20));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 5, 44, 38));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 6, 62, 38));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 7, 80, 38));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 8, 98, 38));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 9, 116, 38));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 10, 44, 56));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 11, 62, 56));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 12, 80, 56));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 13, 98, 56));
      this.func_75146_a(new SlotDrawer(this, drawerInventory, 14, 116, 56));
      this.addPlayerInventorySlots(playerInventory);
   }

   public void addPlayerInventorySlots(InventoryPlayer inventoryPlayer) {
      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 87 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 145));
      }

   }

   public boolean func_75145_c(EntityPlayer player) {
      return this.DrawerInventory.func_70300_a(player);
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int slotNumber) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.field_75151_b.get(slotNumber);
      if(slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if(slotNumber < 15) {
            if(!this.func_75135_a(itemstack1, 15, 51, false)) {
               return null;
            }
         } else if(!this.func_75135_a(itemstack1, 0, 15, false)) {
            return null;
         }

         if(itemstack1.stackSize == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }
      }

      slot.onPickupFromSlot(p_82846_1_, itemstack);
      return itemstack;
   }
}
