package net.minecraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner$1;

public class TileEntityMobSpawner extends TileEntity {

   private final MobSpawnerBaseLogic field_145882_a = new TileEntityMobSpawner$1(this);


   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.field_145882_a.readFromNBT(var1);
   }

   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      this.field_145882_a.writeToNBT(var1);
   }

   public void updateEntity() {
      this.field_145882_a.updateSpawner();
      super.updateEntity();
   }

   public Packet getDescriptionPacket() {
      NBTTagCompound var1 = new NBTTagCompound();
      this.writeToNBT(var1);
      var1.removeTag("SpawnPotentials");
      return new S35PacketUpdateTileEntity(super.xCoord, super.yCoord, super.zCoord, 1, var1);
   }

   public boolean receiveClientEvent(int var1, int var2) {
      return this.field_145882_a.setDelayToMin(var1)?true:super.receiveClientEvent(var1, var2);
   }

   public MobSpawnerBaseLogic func_145881_a() {
      return this.field_145882_a;
   }
}
