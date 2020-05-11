package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S06PacketUpdateHealth extends Packet {

   private float field_149336_a;
   private int field_149334_b;
   private float field_149335_c;


   public S06PacketUpdateHealth() {}

   public S06PacketUpdateHealth(float var1, int var2, float var3) {
      this.field_149336_a = var1;
      this.field_149334_b = var2;
      this.field_149335_c = var3;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149336_a = var1.readFloat();
      this.field_149334_b = var1.readShort();
      this.field_149335_c = var1.readFloat();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeFloat(this.field_149336_a);
      var1.writeShort(this.field_149334_b);
      var1.writeFloat(this.field_149335_c);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleUpdateHealth(this);
   }

   public float func_149332_c() {
      return this.field_149336_a;
   }

   public int func_149330_d() {
      return this.field_149334_b;
   }

   public float func_149331_e() {
      return this.field_149335_c;
   }
}
