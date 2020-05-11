package net.minecraft.network.status.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusServer;

public class C01PacketPing extends Packet {

   private long field_149290_a;


   public C01PacketPing() {}

   public C01PacketPing(long var1) {
      this.field_149290_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149290_a = var1.readLong();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeLong(this.field_149290_a);
   }

   public void processPacket(INetHandlerStatusServer var1) {
      var1.processPing(this);
   }

   public boolean hasPriority() {
      return true;
   }

   public long func_149289_c() {
      return this.field_149290_a;
   }
}
