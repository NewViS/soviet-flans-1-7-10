package net.minecraft.tileentity;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StringUtils;

public class TileEntitySkull extends TileEntity {

   private int field_145908_a;
   private int field_145910_i;
   private GameProfile field_152110_j = null;


   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setByte("SkullType", (byte)(this.field_145908_a & 255));
      var1.setByte("Rot", (byte)(this.field_145910_i & 255));
      if(this.field_152110_j != null) {
         NBTTagCompound var2 = new NBTTagCompound();
         NBTUtil.func_152460_a(var2, this.field_152110_j);
         var1.setTag("Owner", var2);
      }

   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.field_145908_a = var1.getByte("SkullType");
      this.field_145910_i = var1.getByte("Rot");
      if(this.field_145908_a == 3) {
         if(var1.hasKey("Owner", 10)) {
            this.field_152110_j = NBTUtil.func_152459_a(var1.getCompoundTag("Owner"));
         } else if(var1.hasKey("ExtraType", 8) && !StringUtils.isNullOrEmpty(var1.getString("ExtraType"))) {
            this.field_152110_j = new GameProfile((UUID)null, var1.getString("ExtraType"));
            this.func_152109_d();
         }
      }

   }

   public GameProfile func_152108_a() {
      return this.field_152110_j;
   }

   public Packet getDescriptionPacket() {
      NBTTagCompound var1 = new NBTTagCompound();
      this.writeToNBT(var1);
      return new S35PacketUpdateTileEntity(super.xCoord, super.yCoord, super.zCoord, 4, var1);
   }

   public void func_152107_a(int var1) {
      this.field_145908_a = var1;
      this.field_152110_j = null;
   }

   public void func_152106_a(GameProfile var1) {
      this.field_145908_a = 3;
      this.field_152110_j = var1;
      this.func_152109_d();
   }

   private void func_152109_d() {
      if(this.field_152110_j != null && !StringUtils.isNullOrEmpty(this.field_152110_j.getName())) {
         if(!this.field_152110_j.isComplete() || !this.field_152110_j.getProperties().containsKey("textures")) {
            GameProfile var1 = MinecraftServer.getServer().func_152358_ax().func_152655_a(this.field_152110_j.getName());
            if(var1 != null) {
               Property var2 = (Property)Iterables.getFirst(var1.getProperties().get("textures"), (Object)null);
               if(var2 == null) {
                  var1 = MinecraftServer.getServer().func_147130_as().fillProfileProperties(var1, true);
               }

               this.field_152110_j = var1;
               this.markDirty();
            }
         }
      }
   }

   public int func_145904_a() {
      return this.field_145908_a;
   }

   public int func_145906_b() {
      return this.field_145910_i;
   }

   public void func_145903_a(int var1) {
      this.field_145910_i = var1;
   }
}
