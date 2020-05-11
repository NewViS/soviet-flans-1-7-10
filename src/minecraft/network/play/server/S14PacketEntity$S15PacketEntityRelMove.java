package net.minecraft.network.play.server;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S14PacketEntity;

public class S14PacketEntity$S15PacketEntityRelMove extends S14PacketEntity {

   public S14PacketEntity$S15PacketEntityRelMove() {}

   public S14PacketEntity$S15PacketEntityRelMove(int var1, byte var2, byte var3, byte var4) {
      super(var1);
      super.field_149072_b = var2;
      super.field_149073_c = var3;
      super.field_149070_d = var4;
   }

   public void readPacketData(PacketBuffer var1) {
      super.readPacketData(var1);
      super.field_149072_b = var1.readByte();
      super.field_149073_c = var1.readByte();
      super.field_149070_d = var1.readByte();
   }

   public void writePacketData(PacketBuffer var1) {
      super.writePacketData(var1);
      var1.writeByte(super.field_149072_b);
      var1.writeByte(super.field_149073_c);
      var1.writeByte(super.field_149070_d);
   }

   public String serialize() {
      return super.serialize() + String.format(", xa=%d, ya=%d, za=%d", new Object[]{Byte.valueOf(super.field_149072_b), Byte.valueOf(super.field_149073_c), Byte.valueOf(super.field_149070_d)});
   }
}
