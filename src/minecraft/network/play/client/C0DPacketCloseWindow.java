package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0DPacketCloseWindow extends Packet {

   private int field_149556_a;


   public C0DPacketCloseWindow() {}

   public C0DPacketCloseWindow(int var1) {
      this.field_149556_a = var1;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processCloseWindow(this);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149556_a = var1.readByte();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.field_149556_a);
   }
}
