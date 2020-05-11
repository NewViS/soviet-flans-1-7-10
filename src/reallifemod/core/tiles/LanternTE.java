package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityDirectional;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class LanternTE extends TileEntityDirectional {

   public boolean active;


   public void func_145845_h() {
      if(!this.field_145850_b.isDaytime()) {
         this.active = true;
         this.field_145850_b.getBlock(this.field_145851_c, this.field_145848_d, this.field_145849_e).setLightLevel(1.0F);
      } else {
         this.active = false;
         this.field_145850_b.getBlock(this.field_145851_c, this.field_145848_d, this.field_145849_e).setLightLevel(0.0F);
      }

      this.func_70296_d();
      this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
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

   public void func_145839_a(NBTTagCompound compound) {
      super.func_145839_a(compound);
      this.active = compound.getBoolean("lanternactive");
   }

   public void func_145841_b(NBTTagCompound compound) {
      super.func_145841_b(compound);
      compound.setBoolean("lanternactive", this.active);
      this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
   }

   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
      NBTTagCompound nbttagcompound = pkt.func_148857_g();
      this.func_145839_a(nbttagcompound);
   }

   public Packet func_145844_m() {
      NBTTagCompound tag = new NBTTagCompound();
      tag.setBoolean("lanternactive", this.active);
      this.func_145841_b(tag);
      return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145847_g, tag);
   }
}
