package net.minecraft.network.status.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusClient;

public class S01PacketPong extends Packet {

   private long field_149293_a;


   public S01PacketPong() {}

   public S01PacketPong(long var1) {
      this.field_149293_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149293_a = var1.readLong();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeLong(this.field_149293_a);
   }

   public void processPacket(INetHandlerStatusClient var1) {
      var1.handlePong(this);
   }

   public boolean hasPriority() {
      return true;
   }

   public long func_149292_c() {
      return this.field_149293_a;
   }
}
