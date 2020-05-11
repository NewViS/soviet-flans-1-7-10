package net.minecraft.network.play.server;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S0BPacketAnimation extends Packet {

   private int field_148981_a;
   private int field_148980_b;


   public S0BPacketAnimation() {}

   public S0BPacketAnimation(Entity var1, int var2) {
      this.field_148981_a = var1.getEntityId();
      this.field_148980_b = var2;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_148981_a = var1.readVarIntFromBuffer();
      this.field_148980_b = var1.readUnsignedByte();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.field_148981_a);
      var1.writeByte(this.field_148980_b);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleAnimation(this);
   }

   public String serialize() {
      return String.format("id=%d, type=%d", new Object[]{Integer.valueOf(this.field_148981_a), Integer.valueOf(this.field_148980_b)});
   }

   public int func_148978_c() {
      return this.field_148981_a;
   }

   public int func_148977_d() {
      return this.field_148980_b;
   }
}
