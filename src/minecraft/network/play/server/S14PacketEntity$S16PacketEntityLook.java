package net.minecraft.network.play.server;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S14PacketEntity;

public class S14PacketEntity$S16PacketEntityLook extends S14PacketEntity {

   public S14PacketEntity$S16PacketEntityLook() {
      super.field_149069_g = true;
   }

   public S14PacketEntity$S16PacketEntityLook(int var1, byte var2, byte var3) {
      super(var1);
      super.field_149071_e = var2;
      super.field_149068_f = var3;
      super.field_149069_g = true;
   }

   public void readPacketData(PacketBuffer var1) {
      super.readPacketData(var1);
      super.field_149071_e = var1.readByte();
      super.field_149068_f = var1.readByte();
   }

   public void writePacketData(PacketBuffer var1) {
      super.writePacketData(var1);
      var1.writeByte(super.field_149071_e);
      var1.writeByte(super.field_149068_f);
   }

   public String serialize() {
      return super.serialize() + String.format(", yRot=%d, xRot=%d", new Object[]{Byte.valueOf(super.field_149071_e), Byte.valueOf(super.field_149068_f)});
   }
}
