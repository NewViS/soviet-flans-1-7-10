package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S00PacketKeepAlive extends Packet {

   private int field_149136_a;


   public S00PacketKeepAlive() {}

   public S00PacketKeepAlive(int var1) {
      this.field_149136_a = var1;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleKeepAlive(this);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149136_a = var1.readInt();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149136_a);
   }

   public boolean hasPriority() {
      return true;
   }

   public int func_149134_c() {
      return this.field_149136_a;
   }
}
