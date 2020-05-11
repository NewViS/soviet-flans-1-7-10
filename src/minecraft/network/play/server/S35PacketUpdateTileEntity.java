package net.minecraft.network.play.server;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S35PacketUpdateTileEntity extends Packet {

   private int field_148863_a;
   private int field_148861_b;
   private int field_148862_c;
   private int field_148859_d;
   private NBTTagCompound field_148860_e;


   public S35PacketUpdateTileEntity() {}

   public S35PacketUpdateTileEntity(int var1, int var2, int var3, int var4, NBTTagCompound var5) {
      this.field_148863_a = var1;
      this.field_148861_b = var2;
      this.field_148862_c = var3;
      this.field_148859_d = var4;
      this.field_148860_e = var5;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_148863_a = var1.readInt();
      this.field_148861_b = var1.readShort();
      this.field_148862_c = var1.readInt();
      this.field_148859_d = var1.readUnsignedByte();
      this.field_148860_e = var1.readNBTTagCompoundFromBuffer();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_148863_a);
      var1.writeShort(this.field_148861_b);
      var1.writeInt(this.field_148862_c);
      var1.writeByte((byte)this.field_148859_d);
      var1.writeNBTTagCompoundToBuffer(this.field_148860_e);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleUpdateTileEntity(this);
   }

   public int func_148856_c() {
      return this.field_148863_a;
   }

   public int func_148855_d() {
      return this.field_148861_b;
   }

   public int func_148854_e() {
      return this.field_148862_c;
   }

   public int func_148853_f() {
      return this.field_148859_d;
   }

   public NBTTagCompound func_148857_g() {
      return this.field_148860_e;
   }
}
