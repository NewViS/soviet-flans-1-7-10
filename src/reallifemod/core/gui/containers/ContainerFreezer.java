package de.ItsAMysterious.mods.reallifemod.core.gui.containers;

import de.ItsAMysterious.mods.reallifemod.core.gui.containers.slots.SlotFreezer;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FreezerTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFreezer extends Container {

   protected FreezerTE tile;
   protected SlotFreezer freezerSlot;


   public ContainerFreezer(InventoryPlayer inventoryPlayer, FreezerTE tileEntity) {
      this.tile = tileEntity;
      this.func_75146_a(new SlotFreezer(this, this.tile, 0, 90, 18));
      this.func_75146_a(new SlotFreezer(this, this.tile, 1, 108, 18));
      this.func_75146_a(new SlotFreezer(this, this.tile, 2, 126, 18));
      this.func_75146_a(new SlotFreezer(this, this.tile, 3, 90, 36));
      this.func_75146_a(new SlotFreezer(this, this.tile, 4, 108, 36));
      this.func_75146_a(new SlotFreezer(this, this.tile, 5, 126, 36));
      this.func_75146_a(new SlotFreezer(this, this.tile, 6, 90, 54));
      this.func_75146_a(new SlotFreezer(this, this.tile, 7, 108, 54));
      this.func_75146_a(new SlotFreezer(this, this.tile, 8, 126, 54));
      this.func_75146_a(new SlotFreezer(this, this.tile, 9, 90, 72));
      this.func_75146_a(new SlotFreezer(this, this.tile, 10, 108, 72));
      this.func_75146_a(new SlotFreezer(this, this.tile, 11, 126, 72));
      this.func_75146_a(new SlotFreezer(this, this.tile, 12, 90, 90));
      this.func_75146_a(new SlotFreezer(this, this.tile, 13, 108, 90));
      this.func_75146_a(new SlotFreezer(this, this.tile, 14, 126, 90));
      this.addPlayerInventorySlots(inventoryPlayer);
   }

   public boolean func_75145_c(EntityPlayer player) {
      return true;
   }

   public void addPlayerInventorySlots(InventoryPlayer inventoryPlayer) {
      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 122 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 180));
      }

   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int slotNumber) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.field_75151_b.get(slotNumber);
      if(slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if(slotNumber < 15) {
            if(!this.func_75135_a(itemstack1, 15, 45, true)) {
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

      return itemstack;
   }
}
