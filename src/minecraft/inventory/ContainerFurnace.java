package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerFurnace extends Container {

   private TileEntityFurnace tileFurnace;
   private int lastCookTime;
   private int lastBurnTime;
   private int lastItemBurnTime;


   public ContainerFurnace(InventoryPlayer var1, TileEntityFurnace var2) {
      this.tileFurnace = var2;
      this.addSlotToContainer(new Slot(var2, 0, 56, 17));
      this.addSlotToContainer(new Slot(var2, 1, 56, 53));
      this.addSlotToContainer(new SlotFurnace(var1.player, var2, 2, 116, 35));

      int var3;
      for(var3 = 0; var3 < 3; ++var3) {
         for(int var4 = 0; var4 < 9; ++var4) {
            this.addSlotToContainer(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
         }
      }

      for(var3 = 0; var3 < 9; ++var3) {
         this.addSlotToContainer(new Slot(var1, var3, 8 + var3 * 18, 142));
      }

   }

   public void addCraftingToCrafters(ICrafting var1) {
      super.addCraftingToCrafters(var1);
      var1.sendProgressBarUpdate(this, 0, this.tileFurnace.furnaceCookTime);
      var1.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
      var1.sendProgressBarUpdate(this, 2, this.tileFurnace.currentItemBurnTime);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int var1 = 0; var1 < super.crafters.size(); ++var1) {
         ICrafting var2 = (ICrafting)super.crafters.get(var1);
         if(this.lastCookTime != this.tileFurnace.furnaceCookTime) {
            var2.sendProgressBarUpdate(this, 0, this.tileFurnace.furnaceCookTime);
         }

         if(this.lastBurnTime != this.tileFurnace.furnaceBurnTime) {
            var2.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
         }

         if(this.lastItemBurnTime != this.tileFurnace.currentItemBurnTime) {
            var2.sendProgressBarUpdate(this, 2, this.tileFurnace.currentItemBurnTime);
         }
      }

      this.lastCookTime = this.tileFurnace.furnaceCookTime;
      this.lastBurnTime = this.tileFurnace.furnaceBurnTime;
      this.lastItemBurnTime = this.tileFurnace.currentItemBurnTime;
   }

   public void updateProgressBar(int var1, int var2) {
      if(var1 == 0) {
         this.tileFurnace.furnaceCookTime = var2;
      }

      if(var1 == 1) {
         this.tileFurnace.furnaceBurnTime = var2;
      }

      if(var1 == 2) {
         this.tileFurnace.currentItemBurnTime = var2;
      }

   }

   public boolean canInteractWith(EntityPlayer var1) {
      return this.tileFurnace.isUseableByPlayer(var1);
   }

   public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
      ItemStack var3 = null;
      Slot var4 = (Slot)super.inventorySlots.get(var2);
      if(var4 != null && var4.getHasStack()) {
         ItemStack var5 = var4.getStack();
         var3 = var5.copy();
         if(var2 == 2) {
            if(!this.mergeItemStack(var5, 3, 39, true)) {
               return null;
            }

            var4.onSlotChange(var5, var3);
         } else if(var2 != 1 && var2 != 0) {
            if(FurnaceRecipes.smelting().getSmeltingResult(var5) != null) {
               if(!this.mergeItemStack(var5, 0, 1, false)) {
                  return null;
               }
            } else if(TileEntityFurnace.isItemFuel(var5)) {
               if(!this.mergeItemStack(var5, 1, 2, false)) {
                  return null;
               }
            } else if(var2 >= 3 && var2 < 30) {
               if(!this.mergeItemStack(var5, 30, 39, false)) {
                  return null;
               }
            } else if(var2 >= 30 && var2 < 39 && !this.mergeItemStack(var5, 3, 30, false)) {
               return null;
            }
         } else if(!this.mergeItemStack(var5, 3, 39, false)) {
            return null;
         }

         if(var5.stackSize == 0) {
            var4.putStack((ItemStack)null);
         } else {
            var4.onSlotChanged();
         }

         if(var5.stackSize == var3.stackSize) {
            return null;
         }

         var4.onPickupFromSlot(var1, var5);
      }

      return var3;
   }
}
