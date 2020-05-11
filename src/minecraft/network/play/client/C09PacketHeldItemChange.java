package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C09PacketHeldItemChange extends Packet {

   private int field_149615_a;


   public C09PacketHeldItemChange() {}

   public C09PacketHeldItemChange(int var1) {
      this.field_149615_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149615_a = var1.readShort();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeShort(this.field_149615_a);
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processHeldItemChange(this);
   }

   public int func_149614_c() {
      return this.field_149615_a;
   }
}
