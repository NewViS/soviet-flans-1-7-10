package net.minecraft.inventory;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryBasic implements IInventory {

   private String inventoryTitle;
   private int slotsCount;
   private ItemStack[] inventoryContents;
   private List field_70480_d;
   private boolean field_94051_e;


   public InventoryBasic(String var1, boolean var2, int var3) {
      this.inventoryTitle = var1;
      this.field_94051_e = var2;
      this.slotsCount = var3;
      this.inventoryContents = new ItemStack[var3];
   }

   public void func_110134_a(IInvBasic var1) {
      if(this.field_70480_d == null) {
         this.field_70480_d = new ArrayList();
      }

      this.field_70480_d.add(var1);
   }

   public void func_110132_b(IInvBasic var1) {
      this.field_70480_d.remove(var1);
   }

   public ItemStack getStackInSlot(int var1) {
      return var1 >= 0 && var1 < this.inventoryContents.length?this.inventoryContents[var1]:null;
   }

   public ItemStack decrStackSize(int var1, int var2) {
      if(this.inventoryContents[var1] != null) {
         ItemStack var3;
         if(this.inventoryContents[var1].stackSize <= var2) {
            var3 = this.inventoryContents[var1];
            this.inventoryContents[var1] = null;
            this.markDirty();
            return var3;
         } else {
            var3 = this.inventoryContents[var1].splitStack(var2);
            if(this.inventoryContents[var1].stackSize == 0) {
               this.inventoryContents[var1] = null;
            }

            this.markDirty();
            return var3;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int var1) {
      if(this.inventoryContents[var1] != null) {
         ItemStack var2 = this.inventoryContents[var1];
         this.inventoryContents[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.inventoryContents[var1] = var2;
      if(var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
         var2.stackSize = this.getInventoryStackLimit();
      }

      this.markDirty();
   }

   public int getSizeInventory() {
      return this.slotsCount;
   }

   public String getInventoryName() {
      return this.inventoryTitle;
   }

   public boolean hasCustomInventoryName() {
      return this.field_94051_e;
   }

   public void func_110133_a(String var1) {
      this.field_94051_e = true;
      this.inventoryTitle = var1;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void markDirty() {
      if(this.field_70480_d != null) {
         for(int var1 = 0; var1 < this.field_70480_d.size(); ++var1) {
            ((IInvBasic)this.field_70480_d.get(var1)).onInventoryChanged(this);
         }
      }

   }

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return true;
   }

   public void openInventory() {}

   public void closeInventory() {}

   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return true;
   }
}
