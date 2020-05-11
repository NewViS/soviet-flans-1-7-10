package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class CabinetTE extends TileEntity implements IInventory {

   public static String tile;
   public static final int INV_SIZE = 9;
   private ItemStack[] inventory = new ItemStack[9];
   private final String name = "Cabinet Inventory";
   private final String tagName = "CabinetInvTag";


   public CabinetTE(int type) {
      switch(type) {
      case 0:
         tile = "kitchen-cabinet";
      case 1:
         tile = "kitchen-cabinethuge";
      default:
      }
   }

   public int func_145832_p() {
      if(this.field_145850_b != null) {
         if(this.field_145847_g == -1) {
            this.field_145847_g = this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, this.field_145849_e);
         }

         return this.field_145847_g;
      } else {
         return 1;
      }
   }

   public int func_70302_i_() {
      return this.inventory.length;
   }

   public ItemStack func_70301_a(int slot) {
      return this.inventory[slot];
   }

   public ItemStack func_70298_a(int slot, int amount) {
      ItemStack stack = this.func_70301_a(slot);
      if(stack != null) {
         if(stack.stackSize > amount) {
            stack = stack.splitStack(amount);
            if(stack.stackSize == 0) {
               this.func_70299_a(slot, (ItemStack)null);
            }
         } else {
            this.func_70299_a(slot, (ItemStack)null);
         }

         this.onInventoryChanged();
      }

      return stack;
   }

   public ItemStack func_70304_b(int slot) {
      ItemStack stack = this.func_70301_a(slot);
      if(stack != null) {
         this.func_70299_a(slot, (ItemStack)null);
      }

      return stack;
   }

   public void onInventoryChanged() {
      for(int i = 0; i < this.func_70302_i_(); ++i) {
         if(this.func_70301_a(i) != null && this.func_70301_a(i).stackSize == 0) {
            this.func_70299_a(i, (ItemStack)null);
         }
      }

   }

   public void func_70299_a(int slot, ItemStack stack) {
      this.inventory[slot] = stack;
   }

   public String func_145825_b() {
      return "Cabinet Inventory";
   }

   public boolean func_145818_k_() {
      return false;
   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return true;
   }

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public boolean func_94041_b(int slot, ItemStack itemstack) {
      return true;
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.readFromNBT(p_145839_1_);
      NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
      this.inventory = new ItemStack[this.func_70302_i_()];

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
         int j = nbttagcompound1.getByte("Slot") & 255;
         if(j >= 0 && j < this.inventory.length) {
            this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
         }
      }

   }

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.writeToNBT(p_145841_1_);
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.inventory.length; ++i) {
         if(this.inventory[i] != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.inventory[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag(nbttagcompound1);
         }
      }

      p_145841_1_.setTag("Items", nbttaglist);
   }
}
