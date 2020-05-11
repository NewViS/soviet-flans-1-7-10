package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon$BeaconSlot;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBeacon;

public class ContainerBeacon extends Container {

   private TileEntityBeacon tileBeacon;
   private final ContainerBeacon$BeaconSlot beaconSlot;
   private int field_82865_g;
   private int field_82867_h;
   private int field_82868_i;


   public ContainerBeacon(InventoryPlayer var1, TileEntityBeacon var2) {
      this.tileBeacon = var2;
      this.addSlotToContainer(this.beaconSlot = new ContainerBeacon$BeaconSlot(this, var2, 0, 136, 110));
      byte var3 = 36;
      short var4 = 137;

      int var5;
      for(var5 = 0; var5 < 3; ++var5) {
         for(int var6 = 0; var6 < 9; ++var6) {
            this.addSlotToContainer(new Slot(var1, var6 + var5 * 9 + 9, var3 + var6 * 18, var4 + var5 * 18));
         }
      }

      for(var5 = 0; var5 < 9; ++var5) {
         this.addSlotToContainer(new Slot(var1, var5, var3 + var5 * 18, 58 + var4));
      }

      this.field_82865_g = var2.getLevels();
      this.field_82867_h = var2.getPrimaryEffect();
      this.field_82868_i = var2.getSecondaryEffect();
   }

   public void addCraftingToCrafters(ICrafting var1) {
      super.addCraftingToCrafters(var1);
      var1.sendProgressBarUpdate(this, 0, this.field_82865_g);
      var1.sendProgressBarUpdate(this, 1, this.field_82867_h);
      var1.sendProgressBarUpdate(this, 2, this.field_82868_i);
   }

   public void updateProgressBar(int var1, int var2) {
      if(var1 == 0) {
         this.tileBeacon.func_146005_c(var2);
      }

      if(var1 == 1) {
         this.tileBeacon.setPrimaryEffect(var2);
      }

      if(var1 == 2) {
         this.tileBeacon.setSecondaryEffect(var2);
      }

   }

   public TileEntityBeacon func_148327_e() {
      return this.tileBeacon;
   }

   public boolean canInteractWith(EntityPlayer var1) {
      return this.tileBeacon.isUseableByPlayer(var1);
   }

   public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
      ItemStack var3 = null;
      Slot var4 = (Slot)super.inventorySlots.get(var2);
      if(var4 != null && var4.getHasStack()) {
         ItemStack var5 = var4.getStack();
         var3 = var5.copy();
         if(var2 == 0) {
            if(!this.mergeItemStack(var5, 1, 37, true)) {
               return null;
            }

            var4.onSlotChange(var5, var3);
         } else if(!this.beaconSlot.getHasStack() && this.beaconSlot.isItemValid(var5) && var5.stackSize == 1) {
            if(!this.mergeItemStack(var5, 0, 1, false)) {
               return null;
            }
         } else if(var2 >= 1 && var2 < 28) {
            if(!this.mergeItemStack(var5, 28, 37, false)) {
               return null;
            }
         } else if(var2 >= 28 && var2 < 37) {
            if(!this.mergeItemStack(var5, 1, 28, false)) {
               return null;
            }
         } else if(!this.mergeItemStack(var5, 1, 37, false)) {
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
