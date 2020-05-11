package de.ItsAMysterious.mods.reallifemod.core.driveables;

import de.ItsAMysterious.mods.reallifemod.core.driveables.DriveableType;
import de.ItsAMysterious.mods.reallifemod.core.items.ItemAmmo;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DriveableData implements IInventory {

   public HashMap addons;
   public int numCargo;
   public int numAmmo;
   public ItemStack[] ammo;
   public ItemStack[] cargo;
   public float currentfuel;
   public String type;


   public DriveableData(NBTTagCompound nbt) {
      this.readFromNBT(nbt);
   }

   public void readFromNBT(NBTTagCompound tag) {
      if(tag != null) {
         if(tag.hasKey("DriveableType")) {
            this.type = tag.getString("DriveableType");
            DriveableType theType = DriveableType.getDriveable(this.type);
            this.numCargo = theType.numCargo;
            this.numAmmo = theType.numAmmo;
            this.ammo = new ItemStack[this.numAmmo];
            this.cargo = new ItemStack[this.numCargo];

            int i;
            for(i = 0; i < this.numAmmo; ++i) {
               this.ammo[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Ammo " + i));
            }

            for(i = 0; i < this.numCargo; ++i) {
               this.cargo[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Cargo " + i));
            }

            this.currentfuel = tag.getFloat("FuelValue");
         }
      }
   }

   public void writeToNBT(NBTTagCompound tag) {
      tag.setString("Type", this.type);

      int i;
      for(i = 0; i < this.ammo.length; ++i) {
         if(this.ammo[i] != null) {
            tag.setTag("Ammo " + i, this.ammo[i].writeToNBT(new NBTTagCompound()));
         }
      }

      for(i = 0; i < this.cargo.length; ++i) {
         if(this.cargo[i] != null) {
            tag.setTag("Cargo " + i, this.cargo[i].writeToNBT(new NBTTagCompound()));
         }
      }

      tag.setFloat("FuelValue", this.currentfuel);
   }

   public int func_70302_i_() {
      return 0;
   }

   public ItemStack func_70301_a(int i) {
      ItemStack[] inv = this.ammo;
      if(i >= this.ammo.length) {
         i -= this.ammo.length;
         inv = this.cargo;
      }

      return inv[i];
   }

   public ItemStack func_70298_a(int i, int number) {
      ItemStack[] inv = this.ammo;
      if(i >= this.ammo.length) {
         i -= this.ammo.length;
         inv = this.cargo;
      }

      if(inv[i] != null) {
         ItemStack itemstack1;
         if(inv[i].stackSize <= number) {
            itemstack1 = inv[i];
            inv[i] = null;
            return itemstack1;
         } else {
            itemstack1 = inv[i];
            if(inv[i].stackSize <= 0) {
               inv[i] = null;
            }

            return itemstack1;
         }
      } else {
         return null;
      }
   }

   public ItemStack func_70304_b(int i) {
      return this.func_70301_a(i);
   }

   public void func_70299_a(int i, ItemStack stack) {
      ItemStack[] inv = this.ammo;
      if(i >= this.ammo.length) {
         i -= this.ammo.length;
         inv = this.cargo;
      }

      inv[i] = stack;
   }

   public int getAmmoInventoryStart() {
      return 0;
   }

   public int getCargoInventoryStart() {
      return this.ammo.length;
   }

   public String func_145825_b() {
      return "Real Life Mod Data";
   }

   public boolean func_145818_k_() {
      return true;
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

   public boolean func_94041_b(int slot, ItemStack stack) {
      return slot >= this.ammo.length?stack.getItem() instanceof ItemAmmo:true;
   }
}
