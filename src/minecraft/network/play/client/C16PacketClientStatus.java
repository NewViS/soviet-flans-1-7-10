package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C16PacketClientStatus$EnumState;

public class C16PacketClientStatus extends Packet {

   private C16PacketClientStatus$EnumState field_149437_a;


   public C16PacketClientStatus() {}

   public C16PacketClientStatus(C16PacketClientStatus$EnumState var1) {
      this.field_149437_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149437_a = C16PacketClientStatus$EnumState.access$000()[var1.readByte() % C16PacketClientStatus$EnumState.access$000().length];
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(C16PacketClientStatus$EnumState.access$100(this.field_149437_a));
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processClientStatus(this);
   }

   public C16PacketClientStatus$EnumState func_149435_c() {
      return this.field_149437_a;
   }
}
