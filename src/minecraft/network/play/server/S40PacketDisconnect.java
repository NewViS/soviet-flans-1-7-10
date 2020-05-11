package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IChatComponent$Serializer;

public class S40PacketDisconnect extends Packet {

   private IChatComponent field_149167_a;


   public S40PacketDisconnect() {}

   public S40PacketDisconnect(IChatComponent var1) {
      this.field_149167_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149167_a = IChatComponent$Serializer.func_150699_a(var1.readStringFromBuffer(32767));
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeStringToBuffer(IChatComponent$Serializer.func_150696_a(this.field_149167_a));
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleDisconnect(this);
   }

   public boolean hasPriority() {
      return true;
   }

   public IChatComponent func_149165_c() {
      return this.field_149167_a;
   }
}
