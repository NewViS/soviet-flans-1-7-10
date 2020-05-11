package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.api.util.LittleFunctions;
import de.ItsAMysterious.mods.reallifemod.core.entitys.EntityFirFalling;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class FirTreeTE extends TileEntity {

   public float stability = 8.0F;
   public static EntityFirFalling theTree;
   public double scale = LittleFunctions.randMinMax(0.25D, 0.9D);
   public static double windRotation;
   private static double delta;
   public boolean isTrunk;


   public FirTreeTE() {
      this.stability = 8.0F;
      this.isTrunk = false;
   }

   public void func_145845_h() {
      super.updateEntity();
      if(this.field_145850_b != null) {
         if(!this.field_145850_b.isRaining()) {
            delta += 0.05D + LittleFunctions.randMinMax(0.5D, 0.5D);
         } else {
            ++delta;
         }

         windRotation -= Math.sin(delta / 20.0D * 3.141592653589793D / 180.0D) * 0.001D;
         if(this.stability <= 1.0F) {
            this.stability = 0.0F;
            this.isTrunk = true;
            this.func_70296_d();
         }
      }

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

   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
      NBTTagCompound nbttagcompound = pkt.func_148857_g();
      this.func_145839_a(nbttagcompound);
   }

   public Packet func_145844_m() {
      NBTTagCompound tag = new NBTTagCompound();
      tag.setBoolean("IsTreeTrunk", this.isTrunk);
      tag.setDouble("TreeSize", this.scale);
      this.func_145841_b(tag);
      return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145847_g, tag);
   }

   public void func_145839_a(NBTTagCompound comp) {
      super.readFromNBT(comp);
      this.isTrunk = comp.getBoolean("IsTreeTrunk");
      if(comp.getDouble("TreeSize") > 0.0D) {
         this.scale = comp.getDouble("TreeSize");
      } else {
         this.scale = LittleFunctions.randMinMax(0.25D, 0.9D);
      }

   }

   public void func_145841_b(NBTTagCompound comp) {
      super.writeToNBT(comp);
      comp.setBoolean("IsTreeTrunk", this.isTrunk);
      comp.setDouble("TreeSize", this.scale);
      this.func_70296_d();
   }
}
