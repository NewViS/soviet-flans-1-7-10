package de.ItsAMysterious.mods.reallifemod.core.gui.containers;

import de.ItsAMysterious.mods.reallifemod.core.gui.containers.slots.SlotCookingRod;
import de.ItsAMysterious.mods.reallifemod.core.tiles.CookingRodTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCookingRod extends Container {

   protected CookingRodTE CookingRodInventory;


   public ContainerCookingRod(InventoryPlayer playerInventory, CookingRodTE CookingRodInventory) {
      this.CookingRodInventory = CookingRodInventory;
      this.func_75146_a(new SlotCookingRod(this, CookingRodInventory, 0, 53, 10));
      this.func_75146_a(new SlotCookingRod(this, CookingRodInventory, 1, 71, 10));
      this.func_75146_a(new SlotCookingRod(this, CookingRodInventory, 2, 89, 10));
      this.func_75146_a(new SlotCookingRod(this, CookingRodInventory, 3, 107, 10));
      this.func_75146_a(new SlotCookingRod(this, CookingRodInventory, 4, 53, 28));
      this.func_75146_a(new SlotCookingRod(this, CookingRodInventory, 5, 71, 28));
      this.func_75146_a(new SlotCookingRod(this, CookingRodInventory, 6, 89, 28));
      this.func_75146_a(new SlotCookingRod(this, CookingRodInventory, 7, 107, 28));
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
      return this.CookingRodInventory.func_70300_a(player);
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int slotNumber) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.field_75151_b.get(slotNumber);
      if(slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if(slotNumber < 8) {
            if(!this.func_75135_a(itemstack1, 8, 44, false)) {
               return null;
            }
         } else if(!this.func_75135_a(itemstack1, 0, 8, false)) {
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
