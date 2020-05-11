package de.ItsAMysterious.mods.reallifemod.core.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCBO extends TileEntity implements IInventory {

   private ItemStack inv = null;


   public int func_70302_i_() {
      return 1;
   }

   public ItemStack func_70301_a(int slot) {
      return this.inv;
   }

   public void func_70299_a(int slot, ItemStack stack) {
      this.inv = stack;
      if(stack != null && stack.stackSize > this.func_70297_j_()) {
         stack.stackSize = this.func_70297_j_();
      }

   }

   public ItemStack func_70298_a(int slot, int amt) {
      ItemStack stack = this.func_70301_a(slot);
      if(stack != null) {
         if(stack.stackSize <= amt) {
            this.func_70299_a(slot, (ItemStack)null);
         } else {
            stack = stack.splitStack(amt);
            if(stack.stackSize == 0) {
               this.func_70299_a(slot, (ItemStack)null);
            }
         }
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

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_70300_a(EntityPlayer player) {
      return this.field_145850_b.getTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && player.func_70092_e((double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.5D, (double)this.field_145849_e + 0.5D) < 64.0D;
   }

   public void func_145839_a(NBTTagCompound tagCompound) {
      super.readFromNBT(tagCompound);
   }

   public void func_145841_b(NBTTagCompound tagCompound) {
      super.writeToNBT(tagCompound);
   }

   public String func_145825_b() {
      return "tbme.TileEntityCBO";
   }

   public boolean func_145818_k_() {
      return true;
   }

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return true;
   }
}
