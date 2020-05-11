package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDirectional extends TileEntity {

   protected int facingDirection = 0;
   private double heightToSubstract = 0.0D;


   public int getFacingDirection() {
      return this.facingDirection;
   }

   public void setFacingDirection(int par1) {
      this.facingDirection = par1;
   }

   public void func_145839_a(NBTTagCompound compound) {
      super.readFromNBT(compound);
      NBTTagCompound tag = compound.getCompoundTag("dirctionalTag");
      this.facingDirection = tag.getInteger("facingDirection");
   }

   public void func_145841_b(NBTTagCompound compound) {
      super.writeToNBT(compound);
      NBTTagCompound tag = new NBTTagCompound();
      tag.setInteger("facingDirection", this.facingDirection);
      compound.setTag("dirctionalTag", tag);
      this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
   }

   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
      NBTTagCompound nbttagcompound = pkt.func_148857_g();
      this.func_145839_a(nbttagcompound);
   }

   public Packet func_145844_m() {
      NBTTagCompound theTag = new NBTTagCompound();
      NBTTagCompound tag = new NBTTagCompound();
      tag.setInteger("facingDirection", this.facingDirection);
      theTag.setTag("dirctionalTag", tag);
      this.func_145841_b(theTag);
      return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145847_g, theTag);
   }

   public double getHeightToSubstract() {
      return this.heightToSubstract;
   }

   public void setHeightToSubstract(double heightToSubstract) {
      this.heightToSubstract = heightToSubstract;
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
}
