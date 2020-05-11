package net.minecraft.tileentity;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFlowerPot extends TileEntity {

   private Item flowerPotItem;
   private int flowerPotData;


   public TileEntityFlowerPot() {}

   public TileEntityFlowerPot(Item var1, int var2) {
      this.flowerPotItem = var1;
      this.flowerPotData = var2;
   }

   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setInteger("Item", Item.getIdFromItem(this.flowerPotItem));
      var1.setInteger("Data", this.flowerPotData);
   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.flowerPotItem = Item.getItemById(var1.getInteger("Item"));
      this.flowerPotData = var1.getInteger("Data");
   }

   public Packet getDescriptionPacket() {
      NBTTagCompound var1 = new NBTTagCompound();
      this.writeToNBT(var1);
      return new S35PacketUpdateTileEntity(super.xCoord, super.yCoord, super.zCoord, 5, var1);
   }

   public void func_145964_a(Item var1, int var2) {
      this.flowerPotItem = var1;
      this.flowerPotData = var2;
   }

   public Item getFlowerPotItem() {
      return this.flowerPotItem;
   }

   public int getFlowerPotData() {
      return this.flowerPotData;
   }
}
