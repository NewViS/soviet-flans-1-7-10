package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class FridgeTE extends TileEntity implements ISidedInventory {

   public static final int INV_SIZE = 15;
   private ItemStack[] inventory = new ItemStack[15];
   private final String name = "Fridge Inventory";
   private final String tagName = "FridgeInvTag";


   public int func_70302_i_() {
      return this.inventory.length;
   }

   public boolean func_145842_c(int par1, int par2) {
      return super.receiveClientEvent(par1, par2);
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
      for(int i = 0; i < this.inventory.length; ++i) {
         if(this.func_70301_a(i) != null && this.func_70301_a(i).stackSize == 0) {
            this.func_70299_a(i, (ItemStack)null);
         }
      }

   }

   public void func_70299_a(int slot, ItemStack stack) {
      this.inventory[slot] = stack;
      if(stack != null && stack.stackSize > this.func_70297_j_()) {
         stack.stackSize = this.func_70297_j_();
      }

      this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      this.func_70296_d();
   }

   public String func_145825_b() {
      return "reallifemod.Fridgeinventory";
   }

   public boolean func_145818_k_() {
      return "Fridge Inventory".length() > 0;
   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return this.func_145831_w().getTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && p_70300_1_.func_70092_e((double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.5D, (double)this.field_145849_e + 0.5D) <= 64.0D;
   }

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public boolean func_94041_b(int slot, ItemStack itemstack) {
      return slot == 0;
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.readFromNBT(p_145839_1_);
      NBTTagList nbttaglist = p_145839_1_.getTagList("FridgeItems", 10);
      this.inventory = new ItemStack[this.func_70302_i_()];

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         NBTTagCompound tag = nbttaglist.getCompoundTagAt(i);
         int j = tag.getByte("Slot") & 255;
         if(j >= 0 && j < this.inventory.length) {
            this.inventory[j] = ItemStack.loadItemStackFromNBT(tag);
         }
      }

   }

   public void func_145841_b(NBTTagCompound comp) {
      super.writeToNBT(comp);
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.inventory.length; ++i) {
         ItemStack stack = this.inventory[i];
         if(stack != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setByte("Slot", (byte)i);
            stack.writeToNBT(tag);
            nbttaglist.appendTag(tag);
         }
      }

      comp.setTag("FridgeItems", nbttaglist);
   }

   public ItemStack getItem(int slot) {
      ItemStack theItem = this.inventory[slot];
      return theItem != null?theItem:null;
   }

   public boolean canUpdate() {
      return false;
   }

   public Packet func_145844_m() {
      NBTTagCompound dataTag = new NBTTagCompound();
      NBTTagList itemList = new NBTTagList();

      for(int i = 0; i < this.inventory.length; ++i) {
         ItemStack stack = this.inventory[i];
         if(stack != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setLong("Slot", (long)((byte)i));
            stack.writeToNBT(tag);
            itemList.appendTag(tag);
         }
      }

      dataTag.setTag("FridgeItems", itemList);
      this.func_145841_b(dataTag);
      return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
   }

   public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
      NBTTagCompound nbtData = packet.func_148857_g();
      NBTTagList tagList = nbtData.getTagList("FridgeItems", 10);
      this.inventory = new ItemStack[this.func_70302_i_()];

      for(int i = 0; i < this.inventory.length; ++i) {
         NBTTagCompound tag = tagList.getCompoundTagAt(i);
         byte slot = tag.getByte("Slot");
         if(slot >= 0 && slot < this.inventory.length) {
            this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
         }
      }

      this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      this.func_70296_d();
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

   public int[] func_94128_d(int slot) {
      int[] slots = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
      return slots;
   }

   public boolean func_102007_a(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
      return true;
   }

   public boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
      return true;
   }
}
