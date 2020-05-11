package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBrewingStand$Ingredient;
import net.minecraft.inventory.ContainerBrewingStand$Potion;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBrewingStand;

public class ContainerBrewingStand extends Container {

   private TileEntityBrewingStand tileBrewingStand;
   private final Slot theSlot;
   private int brewTime;


   public ContainerBrewingStand(InventoryPlayer var1, TileEntityBrewingStand var2) {
      this.tileBrewingStand = var2;
      this.addSlotToContainer(new ContainerBrewingStand$Potion(var1.player, var2, 0, 56, 46));
      this.addSlotToContainer(new ContainerBrewingStand$Potion(var1.player, var2, 1, 79, 53));
      this.addSlotToContainer(new ContainerBrewingStand$Potion(var1.player, var2, 2, 102, 46));
      this.theSlot = this.addSlotToContainer(new ContainerBrewingStand$Ingredient(this, var2, 3, 79, 17));

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
      var1.sendProgressBarUpdate(this, 0, this.tileBrewingStand.getBrewTime());
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int var1 = 0; var1 < super.crafters.size(); ++var1) {
         ICrafting var2 = (ICrafting)super.crafters.get(var1);
         if(this.brewTime != this.tileBrewingStand.getBrewTime()) {
            var2.sendProgressBarUpdate(this, 0, this.tileBrewingStand.getBrewTime());
         }
      }

      this.brewTime = this.tileBrewingStand.getBrewTime();
   }

   public void updateProgressBar(int var1, int var2) {
      if(var1 == 0) {
         this.tileBrewingStand.func_145938_d(var2);
      }

   }

   public boolean canInteractWith(EntityPlayer var1) {
      return this.tileBrewingStand.isUseableByPlayer(var1);
   }

   public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
      ItemStack var3 = null;
      Slot var4 = (Slot)super.inventorySlots.get(var2);
      if(var4 != null && var4.getHasStack()) {
         ItemStack var5 = var4.getStack();
         var3 = var5.copy();
         if((var2 < 0 || var2 > 2) && var2 != 3) {
            if(!this.theSlot.getHasStack() && this.theSlot.isItemValid(var5)) {
               if(!this.mergeItemStack(var5, 3, 4, false)) {
                  return null;
               }
            } else if(ContainerBrewingStand$Potion.canHoldPotion(var3)) {
               if(!this.mergeItemStack(var5, 0, 3, false)) {
                  return null;
               }
            } else if(var2 >= 4 && var2 < 31) {
               if(!this.mergeItemStack(var5, 31, 40, false)) {
                  return null;
               }
            } else if(var2 >= 31 && var2 < 40) {
               if(!this.mergeItemStack(var5, 4, 31, false)) {
                  return null;
               }
            } else if(!this.mergeItemStack(var5, 4, 40, false)) {
               return null;
            }
         } else {
            if(!this.mergeItemStack(var5, 4, 40, true)) {
               return null;
            }

            var4.onSlotChange(var5, var3);
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
