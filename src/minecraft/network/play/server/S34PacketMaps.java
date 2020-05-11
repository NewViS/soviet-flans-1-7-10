package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S34PacketMaps extends Packet {

   private int field_149191_a;
   private byte[] field_149190_b;


   public S34PacketMaps() {}

   public S34PacketMaps(int var1, byte[] var2) {
      this.field_149191_a = var1;
      this.field_149190_b = var2;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149191_a = var1.readVarIntFromBuffer();
      this.field_149190_b = new byte[var1.readUnsignedShort()];
      var1.readBytes(this.field_149190_b);
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.field_149191_a);
      var1.writeShort(this.field_149190_b.length);
      var1.writeBytes(this.field_149190_b);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleMaps(this);
   }

   public String serialize() {
      return String.format("id=%d, length=%d", new Object[]{Integer.valueOf(this.field_149191_a), Integer.valueOf(this.field_149190_b.length)});
   }

   public int func_149188_c() {
      return this.field_149191_a;
   }

   public byte[] func_149187_d() {
      return this.field_149190_b;
   }
}
