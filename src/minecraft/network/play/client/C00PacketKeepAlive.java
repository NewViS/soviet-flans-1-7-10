package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C00PacketKeepAlive extends Packet {

   private int field_149461_a;


   public C00PacketKeepAlive() {}

   public C00PacketKeepAlive(int var1) {
      this.field_149461_a = var1;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processKeepAlive(this);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149461_a = var1.readInt();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149461_a);
   }

   public boolean hasPriority() {
      return true;
   }

   public int func_149460_c() {
      return this.field_149461_a;
   }
}
