package net.minecraft.network.play.client;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0APacketAnimation extends Packet {

   private int field_149424_a;
   private int field_149423_b;


   public C0APacketAnimation() {}

   public C0APacketAnimation(Entity var1, int var2) {
      this.field_149424_a = var1.getEntityId();
      this.field_149423_b = var2;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149424_a = var1.readInt();
      this.field_149423_b = var1.readByte();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149424_a);
      var1.writeByte(this.field_149423_b);
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processAnimation(this);
   }

   public String serialize() {
      return String.format("id=%d, type=%d", new Object[]{Integer.valueOf(this.field_149424_a), Integer.valueOf(this.field_149423_b)});
   }

   public int func_149421_d() {
      return this.field_149423_b;
   }
}
