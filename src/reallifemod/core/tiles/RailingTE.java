package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class RailingTE extends TileEntity {

   public static RailingTE.Sites site;


   public RailingTE() {
      site = RailingTE.Sites.left;
   }

   public void func_145839_a(NBTTagCompound compound) {
      super.readFromNBT(compound);
      RailingTE.Sites newSite = RailingTE.Sites.valueOf(compound.getString("site"));
      if(newSite != null) {
         site = newSite;
      }

   }

   public void func_145841_b(NBTTagCompound compound) {
      super.writeToNBT(compound);
      compound.setString("site", site.name());
   }

   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
      NBTTagCompound nbttagcompound = pkt.func_148857_g();
      this.func_145839_a(nbttagcompound);
      this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      this.func_70296_d();
   }

   public Packet func_145844_m() {
      NBTTagCompound theTag = new NBTTagCompound();
      theTag.setString("site", site.name());
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

   public static enum Sites {

      left("left", 0),
      right("right", 1);
      // $FF: synthetic field
      private static final RailingTE.Sites[] $VALUES = new RailingTE.Sites[]{left, right};


      private Sites(String var1, int var2) {}

   }
}
