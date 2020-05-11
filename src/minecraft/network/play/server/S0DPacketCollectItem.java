package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S0DPacketCollectItem extends Packet {

   private int field_149357_a;
   private int field_149356_b;


   public S0DPacketCollectItem() {}

   public S0DPacketCollectItem(int var1, int var2) {
      this.field_149357_a = var1;
      this.field_149356_b = var2;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149357_a = var1.readInt();
      this.field_149356_b = var1.readInt();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149357_a);
      var1.writeInt(this.field_149356_b);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleCollectItem(this);
   }

   public int func_149354_c() {
      return this.field_149357_a;
   }

   public int func_149353_d() {
      return this.field_149356_b;
   }
}
