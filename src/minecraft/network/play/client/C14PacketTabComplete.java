package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import org.apache.commons.lang3.StringUtils;

public class C14PacketTabComplete extends Packet {

   private String field_149420_a;


   public C14PacketTabComplete() {}

   public C14PacketTabComplete(String var1) {
      this.field_149420_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149420_a = var1.readStringFromBuffer(32767);
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeStringToBuffer(StringUtils.substring(this.field_149420_a, 0, 32767));
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processTabComplete(this);
   }

   public String func_149419_c() {
      return this.field_149420_a;
   }

   public String serialize() {
      return String.format("message=\'%s\'", new Object[]{this.field_149420_a});
   }
}
