package net.minecraft.inventory;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHorseInventory$1;
import net.minecraft.inventory.ContainerHorseInventory$2;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHorseInventory extends Container {

   private IInventory field_111243_a;
   private EntityHorse theHorse;


   public ContainerHorseInventory(IInventory var1, IInventory var2, EntityHorse var3) {
      this.field_111243_a = var2;
      this.theHorse = var3;
      byte var4 = 3;
      var2.openInventory();
      int var5 = (var4 - 4) * 18;
      this.addSlotToContainer(new ContainerHorseInventory$1(this, var2, 0, 8, 18));
      this.addSlotToContainer(new ContainerHorseInventory$2(this, var2, 1, 8, 36, var3));
      int var6;
      int var7;
      if(var3.isChested()) {
         for(var6 = 0; var6 < var4; ++var6) {
            for(var7 = 0; var7 < 5; ++var7) {
               this.addSlotToContainer(new Slot(var2, 2 + var7 + var6 * 5, 80 + var7 * 18, 18 + var6 * 18));
            }
         }
      }

      for(var6 = 0; var6 < 3; ++var6) {
         for(var7 = 0; var7 < 9; ++var7) {
            this.addSlotToContainer(new Slot(var1, var7 + var6 * 9 + 9, 8 + var7 * 18, 102 + var6 * 18 + var5));
         }
      }

      for(var6 = 0; var6 < 9; ++var6) {
         this.addSlotToContainer(new Slot(var1, var6, 8 + var6 * 18, 160 + var5));
      }

   }

   public boolean canInteractWith(EntityPlayer var1) {
      return this.field_111243_a.isUseableByPlayer(var1) && this.theHorse.isEntityAlive() && this.theHorse.getDistanceToEntity(var1) < 8.0F;
   }

   public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
      ItemStack var3 = null;
      Slot var4 = (Slot)super.inventorySlots.get(var2);
      if(var4 != null && var4.getHasStack()) {
         ItemStack var5 = var4.getStack();
         var3 = var5.copy();
         if(var2 < this.field_111243_a.getSizeInventory()) {
            if(!this.mergeItemStack(var5, this.field_111243_a.getSizeInventory(), super.inventorySlots.size(), true)) {
               return null;
            }
         } else if(this.getSlot(1).isItemValid(var5) && !this.getSlot(1).getHasStack()) {
            if(!this.mergeItemStack(var5, 1, 2, false)) {
               return null;
            }
         } else if(this.getSlot(0).isItemValid(var5)) {
            if(!this.mergeItemStack(var5, 0, 1, false)) {
               return null;
            }
         } else if(this.field_111243_a.getSizeInventory() <= 2 || !this.mergeItemStack(var5, 2, this.field_111243_a.getSizeInventory(), false)) {
            return null;
         }

         if(var5.stackSize == 0) {
            var4.putStack((ItemStack)null);
         } else {
            var4.onSlotChanged();
         }
      }

      return var3;
   }

   public void onContainerClosed(EntityPlayer var1) {
      super.onContainerClosed(var1);
      this.field_111243_a.closeInventory();
   }
}
