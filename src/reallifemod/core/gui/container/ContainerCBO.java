package de.ItsAMysterious.mods.reallifemod.core.gui.container;

import de.ItsAMysterious.mods.reallifemod.core.gui.container.TileEntityCBO;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCBO extends Container {

   protected TileEntityCBO tileEntity;


   public ContainerCBO(IInventory inventory, TileEntityCBO te) {
      this.tileEntity = te;
      this.func_75146_a(new Slot(this.tileEntity, 0, 250, 250));
   }

   public boolean func_75145_c(EntityPlayer player) {
      return this.tileEntity.func_70300_a(player);
   }

   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
      this.func_75146_a(new Slot(inventoryPlayer, 0, 180, 200));
   }

   public ItemStack func_82846_b(EntityPlayer player, int slot) {
      ItemStack stack = null;
      Slot slotObject = (Slot)this.field_75151_b.get(slot);
      if(slotObject != null && slotObject.getHasStack()) {
         ItemStack stackInSlot = slotObject.getStack();
         stack = stackInSlot.copy();
         if(slot < 0) {
            if(!this.func_75135_a(stackInSlot, 0, 35, true)) {
               return null;
            }
         } else if(!this.func_75135_a(stackInSlot, 0, 9, false)) {
            return null;
         }

         if(stackInSlot.stackSize == 0) {
            slotObject.putStack((ItemStack)null);
         } else {
            slotObject.onSlotChanged();
         }

         if(stackInSlot.stackSize == stack.stackSize) {
            return null;
         }

         slotObject.onPickupFromSlot(player, stackInSlot);
      }

      return stack;
   }
}
