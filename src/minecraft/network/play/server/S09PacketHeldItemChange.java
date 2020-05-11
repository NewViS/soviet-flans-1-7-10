package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S09PacketHeldItemChange extends Packet {

   private int field_149387_a;


   public S09PacketHeldItemChange() {}

   public S09PacketHeldItemChange(int var1) {
      this.field_149387_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149387_a = var1.readByte();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.field_149387_a);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleHeldItemChange(this);
   }

   public int func_149385_c() {
      return this.field_149387_a;
   }
}
