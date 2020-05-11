package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryCraftResult implements IInventory {

   private ItemStack[] stackResult = new ItemStack[1];


   public int getSizeInventory() {
      return 1;
   }

   public ItemStack getStackInSlot(int var1) {
      return this.stackResult[0];
   }

   public String getInventoryName() {
      return "Result";
   }

   public boolean hasCustomInventoryName() {
      return false;
   }

   public ItemStack decrStackSize(int var1, int var2) {
      if(this.stackResult[0] != null) {
         ItemStack var3 = this.stackResult[0];
         this.stackResult[0] = null;
         return var3;
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int var1) {
      if(this.stackResult[0] != null) {
         ItemStack var2 = this.stackResult[0];
         this.stackResult[0] = null;
         return var2;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.stackResult[0] = var2;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void markDirty() {}

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return true;
   }

   public void openInventory() {}

   public void closeInventory() {}

   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return true;
   }
}
