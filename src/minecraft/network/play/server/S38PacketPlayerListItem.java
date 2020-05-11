package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S38PacketPlayerListItem extends Packet {

   private String field_149126_a;
   private boolean field_149124_b;
   private int field_149125_c;


   public S38PacketPlayerListItem() {}

   public S38PacketPlayerListItem(String var1, boolean var2, int var3) {
      this.field_149126_a = var1;
      this.field_149124_b = var2;
      this.field_149125_c = var3;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149126_a = var1.readStringFromBuffer(16);
      this.field_149124_b = var1.readBoolean();
      this.field_149125_c = var1.readShort();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeStringToBuffer(this.field_149126_a);
      var1.writeBoolean(this.field_149124_b);
      var1.writeShort(this.field_149125_c);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handlePlayerListItem(this);
   }

   public String func_149122_c() {
      return this.field_149126_a;
   }

   public boolean func_149121_d() {
      return this.field_149124_b;
   }

   public int func_149120_e() {
      return this.field_149125_c;
   }
}
