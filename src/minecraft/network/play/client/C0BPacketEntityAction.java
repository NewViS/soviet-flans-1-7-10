package net.minecraft.network.play.client;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0BPacketEntityAction extends Packet {

   private int field_149517_a;
   private int field_149515_b;
   private int field_149516_c;


   public C0BPacketEntityAction() {}

   public C0BPacketEntityAction(Entity var1, int var2) {
      this(var1, var2, 0);
   }

   public C0BPacketEntityAction(Entity var1, int var2, int var3) {
      this.field_149517_a = var1.getEntityId();
      this.field_149515_b = var2;
      this.field_149516_c = var3;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149517_a = var1.readInt();
      this.field_149515_b = var1.readByte();
      this.field_149516_c = var1.readInt();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149517_a);
      var1.writeByte(this.field_149515_b);
      var1.writeInt(this.field_149516_c);
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processEntityAction(this);
   }

   public int func_149513_d() {
      return this.field_149515_b;
   }

   public int func_149512_e() {
      return this.field_149516_c;
   }
}
