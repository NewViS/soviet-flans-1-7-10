package de.ItsAMysterious.mods.reallifemod.core.gui.containers;

import de.ItsAMysterious.mods.reallifemod.core.gui.containers.slots.SlotSideboard;
import de.ItsAMysterious.mods.reallifemod.core.tiles.SideboardTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSideboard extends Container {

   private int numRows;
   protected SideboardTE tile;


   public ContainerSideboard(InventoryPlayer inventoryPlayer, SideboardTE tileEntity) {
      this.tile = tileEntity;
      this.numRows = 1;
      this.func_75146_a(new SlotSideboard(this, this.tile, 0, 62, 20));
      this.func_75146_a(new SlotSideboard(this, this.tile, 1, 80, 20));
      this.func_75146_a(new SlotSideboard(this, this.tile, 2, 98, 20));
      this.addPlayerInventorySlots(inventoryPlayer);
   }

   public boolean func_75145_c(EntityPlayer player) {
      return this.tile.func_70300_a(player);
   }

   public void addPlayerInventorySlots(InventoryPlayer inventoryPlayer) {
      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 51 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 109));
      }

   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int slotNumber) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.field_75151_b.get(slotNumber);
      if(slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if(slotNumber < 3) {
            if(!this.func_75135_a(itemstack1, 3, 39, true)) {
               return null;
            }
         } else if(!this.func_75135_a(itemstack1, 0, 3, false)) {
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
