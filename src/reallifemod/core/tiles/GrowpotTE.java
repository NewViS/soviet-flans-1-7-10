package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class GrowpotTE extends TileEntity {

   public boolean isFilled = false;


   public void func_145839_a(NBTTagCompound compound) {
      super.readFromNBT(compound);
      NBTTagCompound tag = compound.getCompoundTag("GrowPotTag");
      this.isFilled = tag.getBoolean("IsFilled");
   }

   public void func_145841_b(NBTTagCompound compound) {
      super.writeToNBT(compound);
      NBTTagCompound tag = new NBTTagCompound();
      tag.setBoolean("IsFilled", this.isFilled);
      compound.setTag("GrowPotTag", tag);
      this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
   }

   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
      NBTTagCompound nbttagcompound = pkt.func_148857_g();
      this.func_145839_a(nbttagcompound);
   }

   public Packet func_145844_m() {
      NBTTagCompound theTag = new NBTTagCompound();
      NBTTagCompound tag = new NBTTagCompound();
      tag.setBoolean("IsFilled", this.isFilled);
      theTag.setTag("GrowPotTag", tag);
      this.func_145841_b(theTag);
      return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145847_g, theTag);
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
