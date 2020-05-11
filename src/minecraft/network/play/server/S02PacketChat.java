package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IChatComponent$Serializer;

public class S02PacketChat extends Packet {

   private IChatComponent field_148919_a;
   private boolean field_148918_b;


   public S02PacketChat() {
      this.field_148918_b = true;
   }

   public S02PacketChat(IChatComponent var1) {
      this(var1, true);
   }

   public S02PacketChat(IChatComponent var1, boolean var2) {
      this.field_148918_b = true;
      this.field_148919_a = var1;
      this.field_148918_b = var2;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_148919_a = IChatComponent$Serializer.func_150699_a(var1.readStringFromBuffer(32767));
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeStringToBuffer(IChatComponent$Serializer.func_150696_a(this.field_148919_a));
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleChat(this);
   }

   public String serialize() {
      return String.format("message=\'%s\'", new Object[]{this.field_148919_a});
   }

   public IChatComponent func_148915_c() {
      return this.field_148919_a;
   }

   public boolean func_148916_d() {
      return this.field_148918_b;
   }
}
