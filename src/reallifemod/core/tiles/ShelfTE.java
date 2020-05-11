package de.ItsAMysterious.mods.reallifemod.core.tiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class ShelfTE extends TileEntity implements IInventory {

   public List items = new ArrayList(9);


   public void func_145839_a(NBTTagCompound parCompound) {
      super.readFromNBT(parCompound);
      NBTTagList itemlist = parCompound.getTagList("shelfItems", 10);

      for(int i = 0; i < itemlist.tagCount(); ++i) {
         NBTTagCompound nbttagcompound1 = itemlist.getCompoundTagAt(i);
         int j = nbttagcompound1.getByte("Slot") & 255;
         if(j >= 0 && j < this.items.size() - 1) {
            this.items.add(ItemStack.loadItemStackFromNBT(nbttagcompound1));
         }
      }

   }

   public void func_145841_b(NBTTagCompound parCompound) {
      super.writeToNBT(parCompound);
      NBTTagList itemList = new NBTTagList();
      Iterator var3 = this.items.iterator();

      while(var3.hasNext()) {
         ItemStack stack = (ItemStack)var3.next();
         NBTTagCompound compound = new NBTTagCompound();
         compound.setByte("Slot", (byte)this.items.indexOf(stack));
         stack.writeToNBT(compound);
         itemList.appendTag(compound);
      }

      parCompound.setTag("shelfItems", itemList);
   }

   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
      NBTTagCompound nbttagcompound = pkt.func_148857_g();
      this.func_145839_a(nbttagcompound);
   }

   public Packet func_145844_m() {
      NBTTagList itemList = new NBTTagList();
      NBTTagCompound parCompound = new NBTTagCompound();
      parCompound.setTag("shelfItems", itemList);
      super.writeToNBT(parCompound);
      Iterator var3 = this.items.iterator();

      while(var3.hasNext()) {
         ItemStack stack = (ItemStack)var3.next();
         NBTTagCompound compound = new NBTTagCompound();
         compound.setByte("Slot", (byte)this.items.indexOf(stack));
         stack.writeToNBT(compound);
         itemList.appendTag(compound);
      }

      return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145847_g, parCompound);
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      this.items.add(p_70299_2_);
      if(p_70299_2_ != null && p_70299_2_.stackSize > 64) {
         p_70299_2_.stackSize = 64;
      }

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

   public String func_145825_b() {
      return null;
   }

   public boolean func_145818_k_() {
      return false;
   }

   public int func_70297_j_() {
      return 0;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return false;
   }

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return false;
   }
}
