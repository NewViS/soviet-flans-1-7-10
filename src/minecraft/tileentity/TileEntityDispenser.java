package net.minecraft.tileentity;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDispenser extends TileEntity implements IInventory {

   private ItemStack[] field_146022_i = new ItemStack[9];
   private Random field_146021_j = new Random();
   protected String field_146020_a;


   public int getSizeInventory() {
      return 9;
   }

   public ItemStack getStackInSlot(int var1) {
      return this.field_146022_i[var1];
   }

   public ItemStack decrStackSize(int var1, int var2) {
      if(this.field_146022_i[var1] != null) {
         ItemStack var3;
         if(this.field_146022_i[var1].stackSize <= var2) {
            var3 = this.field_146022_i[var1];
            this.field_146022_i[var1] = null;
            this.markDirty();
            return var3;
         } else {
            var3 = this.field_146022_i[var1].splitStack(var2);
            if(this.field_146022_i[var1].stackSize == 0) {
               this.field_146022_i[var1] = null;
            }

            this.markDirty();
            return var3;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int var1) {
      if(this.field_146022_i[var1] != null) {
         ItemStack var2 = this.field_146022_i[var1];
         this.field_146022_i[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   public int func_146017_i() {
      int var1 = -1;
      int var2 = 1;

      for(int var3 = 0; var3 < this.field_146022_i.length; ++var3) {
         if(this.field_146022_i[var3] != null && this.field_146021_j.nextInt(var2++) == 0) {
            var1 = var3;
         }
      }

      return var1;
   }

   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.field_146022_i[var1] = var2;
      if(var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
         var2.stackSize = this.getInventoryStackLimit();
      }

      this.markDirty();
   }

   public int func_146019_a(ItemStack var1) {
      for(int var2 = 0; var2 < this.field_146022_i.length; ++var2) {
         if(this.field_146022_i[var2] == null || this.field_146022_i[var2].getItem() == null) {
            this.setInventorySlotContents(var2, var1);
            return var2;
         }
      }

      return -1;
   }

   public String getInventoryName() {
      return this.hasCustomInventoryName()?this.field_146020_a:"container.dispenser";
   }

   public void func_146018_a(String var1) {
      this.field_146020_a = var1;
   }

   public boolean hasCustomInventoryName() {
      return this.field_146020_a != null;
   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      NBTTagList var2 = var1.getTagList("Items", 10);
      this.field_146022_i = new ItemStack[this.getSizeInventory()];

      for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
         NBTTagCompound var4 = var2.getCompoundTagAt(var3);
         int var5 = var4.getByte("Slot") & 255;
         if(var5 >= 0 && var5 < this.field_146022_i.length) {
            this.field_146022_i[var5] = ItemStack.loadItemStackFromNBT(var4);
         }
      }

      if(var1.hasKey("CustomName", 8)) {
         this.field_146020_a = var1.getString("CustomName");
      }

   }

   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      NBTTagList var2 = new NBTTagList();

      for(int var3 = 0; var3 < this.field_146022_i.length; ++var3) {
         if(this.field_146022_i[var3] != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var3);
            this.field_146022_i[var3].writeToNBT(var4);
            var2.appendTag(var4);
         }
      }

      var1.setTag("Items", var2);
      if(this.hasCustomInventoryName()) {
         var1.setString("CustomName", this.field_146020_a);
      }

   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return super.worldObj.getTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:var1.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public void openInventory() {}

   public void closeInventory() {}

   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return true;
   }
}
