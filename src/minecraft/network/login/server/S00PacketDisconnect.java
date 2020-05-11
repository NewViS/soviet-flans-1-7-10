package net.minecraft.network.login.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IChatComponent$Serializer;

public class S00PacketDisconnect extends Packet {

   private IChatComponent field_149605_a;


   public S00PacketDisconnect() {}

   public S00PacketDisconnect(IChatComponent var1) {
      this.field_149605_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149605_a = IChatComponent$Serializer.func_150699_a(var1.readStringFromBuffer(32767));
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeStringToBuffer(IChatComponent$Serializer.func_150696_a(this.field_149605_a));
   }

   public void processPacket(INetHandlerLoginClient var1) {
      var1.handleDisconnect(this);
   }

   public boolean hasPriority() {
      return true;
   }

   public IChatComponent func_149603_c() {
      return this.field_149605_a;
   }
}
