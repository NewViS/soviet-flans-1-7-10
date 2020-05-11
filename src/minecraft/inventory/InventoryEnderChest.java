package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityEnderChest;

public class InventoryEnderChest extends InventoryBasic {

   private TileEntityEnderChest associatedChest;


   public InventoryEnderChest() {
      super("container.enderchest", false, 27);
   }

   public void func_146031_a(TileEntityEnderChest var1) {
      this.associatedChest = var1;
   }

   public void loadInventoryFromNBT(NBTTagList var1) {
      int var2;
      for(var2 = 0; var2 < this.getSizeInventory(); ++var2) {
         this.setInventorySlotContents(var2, (ItemStack)null);
      }

      for(var2 = 0; var2 < var1.tagCount(); ++var2) {
         NBTTagCompound var3 = var1.getCompoundTagAt(var2);
         int var4 = var3.getByte("Slot") & 255;
         if(var4 >= 0 && var4 < this.getSizeInventory()) {
            this.setInventorySlotContents(var4, ItemStack.loadItemStackFromNBT(var3));
         }
      }

   }

   public NBTTagList saveInventoryToNBT() {
      NBTTagList var1 = new NBTTagList();

      for(int var2 = 0; var2 < this.getSizeInventory(); ++var2) {
         ItemStack var3 = this.getStackInSlot(var2);
         if(var3 != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var2);
            var3.writeToNBT(var4);
            var1.appendTag(var4);
         }
      }

      return var1;
   }

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return this.associatedChest != null && !this.associatedChest.func_145971_a(var1)?false:super.isUseableByPlayer(var1);
   }

   public void openInventory() {
      if(this.associatedChest != null) {
         this.associatedChest.func_145969_a();
      }

      super.openInventory();
   }

   public void closeInventory() {
      if(this.associatedChest != null) {
         this.associatedChest.func_145970_b();
      }

      super.closeInventory();
      this.associatedChest = null;
   }
}
