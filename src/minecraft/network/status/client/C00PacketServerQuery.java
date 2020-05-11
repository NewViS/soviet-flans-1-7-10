package net.minecraft.network.status.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusServer;

public class C00PacketServerQuery extends Packet {

   public void readPacketData(PacketBuffer var1) {}

   public void writePacketData(PacketBuffer var1) {}

   public void processPacket(INetHandlerStatusServer var1) {
      var1.processServerQuery(this);
   }

   public boolean hasPriority() {
      return true;
   }
}
