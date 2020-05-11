package net.minecraft.inventory;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class InventoryMerchant implements IInventory {

   private final IMerchant theMerchant;
   private ItemStack[] theInventory = new ItemStack[3];
   private final EntityPlayer thePlayer;
   private MerchantRecipe currentRecipe;
   private int currentRecipeIndex;


   public InventoryMerchant(EntityPlayer var1, IMerchant var2) {
      this.thePlayer = var1;
      this.theMerchant = var2;
   }

   public int getSizeInventory() {
      return this.theInventory.length;
   }

   public ItemStack getStackInSlot(int var1) {
      return this.theInventory[var1];
   }

   public ItemStack decrStackSize(int var1, int var2) {
      if(this.theInventory[var1] != null) {
         ItemStack var3;
         if(var1 == 2) {
            var3 = this.theInventory[var1];
            this.theInventory[var1] = null;
            return var3;
         } else if(this.theInventory[var1].stackSize <= var2) {
            var3 = this.theInventory[var1];
            this.theInventory[var1] = null;
            if(this.inventoryResetNeededOnSlotChange(var1)) {
               this.resetRecipeAndSlots();
            }

            return var3;
         } else {
            var3 = this.theInventory[var1].splitStack(var2);
            if(this.theInventory[var1].stackSize == 0) {
               this.theInventory[var1] = null;
            }

            if(this.inventoryResetNeededOnSlotChange(var1)) {
               this.resetRecipeAndSlots();
            }

            return var3;
         }
      } else {
         return null;
      }
   }

   private boolean inventoryResetNeededOnSlotChange(int var1) {
      return var1 == 0 || var1 == 1;
   }

   public ItemStack getStackInSlotOnClosing(int var1) {
      if(this.theInventory[var1] != null) {
         ItemStack var2 = this.theInventory[var1];
         this.theInventory[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.theInventory[var1] = var2;
      if(var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
         var2.stackSize = this.getInventoryStackLimit();
      }

      if(this.inventoryResetNeededOnSlotChange(var1)) {
         this.resetRecipeAndSlots();
      }

   }

   public String getInventoryName() {
      return "mob.villager";
   }

   public boolean hasCustomInventoryName() {
      return false;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return this.theMerchant.getCustomer() == var1;
   }

   public void openInventory() {}

   public void closeInventory() {}

   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return true;
   }

   public void markDirty() {
      this.resetRecipeAndSlots();
   }

   public void resetRecipeAndSlots() {
      this.currentRecipe = null;
      ItemStack var1 = this.theInventory[0];
      ItemStack var2 = this.theInventory[1];
      if(var1 == null) {
         var1 = var2;
         var2 = null;
      }

      if(var1 == null) {
         this.setInventorySlotContents(2, (ItemStack)null);
      } else {
         MerchantRecipeList var3 = this.theMerchant.getRecipes(this.thePlayer);
         if(var3 != null) {
            MerchantRecipe var4 = var3.canRecipeBeUsed(var1, var2, this.currentRecipeIndex);
            if(var4 != null && !var4.isRecipeDisabled()) {
               this.currentRecipe = var4;
               this.setInventorySlotContents(2, var4.getItemToSell().copy());
            } else if(var2 != null) {
               var4 = var3.canRecipeBeUsed(var2, var1, this.currentRecipeIndex);
               if(var4 != null && !var4.isRecipeDisabled()) {
                  this.currentRecipe = var4;
                  this.setInventorySlotContents(2, var4.getItemToSell().copy());
               } else {
                  this.setInventorySlotContents(2, (ItemStack)null);
               }
            } else {
               this.setInventorySlotContents(2, (ItemStack)null);
            }
         }
      }

      this.theMerchant.func_110297_a_(this.getStackInSlot(2));
   }

   public MerchantRecipe getCurrentRecipe() {
      return this.currentRecipe;
   }

   public void setCurrentRecipeIndex(int var1) {
      this.currentRecipeIndex = var1;
      this.resetRecipeAndSlots();
   }
}
