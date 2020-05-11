package de.ItsAMysterious.mods.reallifemod.core.tiles;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.util.vector.Vector3f;

public class TableTE extends TileEntity {

   public static List edges = new ArrayList();


   public void func_145839_a(NBTTagCompound compound) {
      super.readFromNBT(compound);
      NBTTagCompound tag = compound.getCompoundTag("TableTag");
      edges = new ArrayList(tag.getInteger("numberofedges"));

      for(int i = 0; i < edges.size(); ++i) {
         edges.add(i, new Vector3f((float)tag.getIntArray("Edge" + i)[0], (float)tag.getIntArray("Edge" + i)[1], (float)tag.getIntArray("Edge" + i)[2]));
      }

   }

   public void func_145841_b(NBTTagCompound compound) {
      super.writeToNBT(compound);
      NBTTagCompound tag = new NBTTagCompound();
      compound.setTag("TableTag", tag);
      tag.setInteger("numberofedges", edges.size());

      for(int i = 0; i < edges.size(); ++i) {
         tag.setIntArray("Edge" + i, new int[]{(int)((Vector3f)edges.get(i)).x, (int)((Vector3f)edges.get(i)).y, (int)((Vector3f)edges.get(i)).z});
         System.out.println(this.getClass().getName() + "Saved edges:" + ((Vector3f)edges.get(i)).x + " " + ((Vector3f)edges.get(i)).y + " " + ((Vector3f)edges.get(i)).z);
      }

   }

   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
      NBTTagCompound nbttagcompound = pkt.func_148857_g();
      this.func_145839_a(nbttagcompound);
   }

   public Packet func_145844_m() {
      NBTTagCompound theTag = new NBTTagCompound();
      NBTTagCompound tag = new NBTTagCompound();
      theTag.setTag("TableTag", tag);
      tag.setInteger("numberofedges", edges.size());

      for(int i = 0; i < edges.size(); ++i) {
         tag.setIntArray("Edge" + i, new int[]{(int)((Vector3f)edges.get(i)).x, (int)((Vector3f)edges.get(i)).y, (int)((Vector3f)edges.get(i)).z});
      }

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
