package net.minecraft.network.play.server;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S14PacketEntity;

public class S14PacketEntity$S17PacketEntityLookMove extends S14PacketEntity {

   public S14PacketEntity$S17PacketEntityLookMove() {
      super.field_149069_g = true;
   }

   public S14PacketEntity$S17PacketEntityLookMove(int var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      super(var1);
      super.field_149072_b = var2;
      super.field_149073_c = var3;
      super.field_149070_d = var4;
      super.field_149071_e = var5;
      super.field_149068_f = var6;
      super.field_149069_g = true;
   }

   public void readPacketData(PacketBuffer var1) {
      super.readPacketData(var1);
      super.field_149072_b = var1.readByte();
      super.field_149073_c = var1.readByte();
      super.field_149070_d = var1.readByte();
      super.field_149071_e = var1.readByte();
      super.field_149068_f = var1.readByte();
   }

   public void writePacketData(PacketBuffer var1) {
      super.writePacketData(var1);
      var1.writeByte(super.field_149072_b);
      var1.writeByte(super.field_149073_c);
      var1.writeByte(super.field_149070_d);
      var1.writeByte(super.field_149071_e);
      var1.writeByte(super.field_149068_f);
   }

   public String serialize() {
      return super.serialize() + String.format(", xa=%d, ya=%d, za=%d, yRot=%d, xRot=%d", new Object[]{Byte.valueOf(super.field_149072_b), Byte.valueOf(super.field_149073_c), Byte.valueOf(super.field_149070_d), Byte.valueOf(super.field_149071_e), Byte.valueOf(super.field_149068_f)});
   }
}
