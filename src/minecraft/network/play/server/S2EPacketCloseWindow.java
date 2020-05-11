package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S2EPacketCloseWindow extends Packet {

   private int field_148896_a;


   public S2EPacketCloseWindow() {}

   public S2EPacketCloseWindow(int var1) {
      this.field_148896_a = var1;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleCloseWindow(this);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_148896_a = var1.readUnsignedByte();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.field_148896_a);
   }
}
