package net.minecraft.tileentity;

import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock$1;

public class TileEntityCommandBlock extends TileEntity {

   private final CommandBlockLogic field_145994_a = new TileEntityCommandBlock$1(this);


   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      this.field_145994_a.func_145758_a(var1);
   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.field_145994_a.func_145759_b(var1);
   }

   public Packet getDescriptionPacket() {
      NBTTagCompound var1 = new NBTTagCompound();
      this.writeToNBT(var1);
      return new S35PacketUpdateTileEntity(super.xCoord, super.yCoord, super.zCoord, 2, var1);
   }

   public CommandBlockLogic func_145993_a() {
      return this.field_145994_a;
   }
}
