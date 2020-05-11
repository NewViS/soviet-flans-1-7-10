package net.minecraft.network.play.server;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S19PacketEntityHeadLook extends Packet {

   private int field_149384_a;
   private byte field_149383_b;


   public S19PacketEntityHeadLook() {}

   public S19PacketEntityHeadLook(Entity var1, byte var2) {
      this.field_149384_a = var1.getEntityId();
      this.field_149383_b = var2;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149384_a = var1.readInt();
      this.field_149383_b = var1.readByte();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149384_a);
      var1.writeByte(this.field_149383_b);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityHeadLook(this);
   }

   public String serialize() {
      return String.format("id=%d, rot=%d", new Object[]{Integer.valueOf(this.field_149384_a), Byte.valueOf(this.field_149383_b)});
   }

   public Entity func_149381_a(World var1) {
      return var1.getEntityByID(this.field_149384_a);
   }

   public byte func_149380_c() {
      return this.field_149383_b;
   }
}
