package de.ItsAMysterious.mods.reallifemod.api.entity.properties;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryBelt implements IInventory {

   public int func_70302_i_() {
      return 0;
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      return null;
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      return null;
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      return null;
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {}

   public String func_145825_b() {
      return null;
   }

   public boolean func_145818_k_() {
      return false;
   }

   public int func_70297_j_() {
      return 64;
   }

   public void func_70296_d() {}

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return true;
   }

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return true;
   }

   public void writeToNBT(NBTTagCompound tagcompound) {
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.func_70302_i_(); ++i) {
         if(this.func_70301_a(i) != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.func_70301_a(i).writeToNBT(nbttagcompound1);
            nbttaglist.appendTag(nbttagcompound1);
         }
      }

      tagcompound.setTag("beltItems", nbttaglist);
   }
}
