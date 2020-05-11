package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C01PacketChatMessage extends Packet {

   private String field_149440_a;


   public C01PacketChatMessage() {}

   public C01PacketChatMessage(String var1) {
      if(var1.length() > 100) {
         var1 = var1.substring(0, 100);
      }

      this.field_149440_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149440_a = var1.readStringFromBuffer(100);
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeStringToBuffer(this.field_149440_a);
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processChatMessage(this);
   }

   public String serialize() {
      return String.format("message=\'%s\'", new Object[]{this.field_149440_a});
   }

   public String func_149439_c() {
      return this.field_149440_a;
   }
}
