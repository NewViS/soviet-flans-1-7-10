package net.minecraft.network.play.client;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C03PacketPlayer;

public class C03PacketPlayer$C06PacketPlayerPosLook extends C03PacketPlayer {

   public C03PacketPlayer$C06PacketPlayerPosLook() {
      super.field_149480_h = true;
      super.field_149481_i = true;
   }

   public C03PacketPlayer$C06PacketPlayerPosLook(double var1, double var3, double var5, double var7, float var9, float var10, boolean var11) {
      super.field_149479_a = var1;
      super.field_149477_b = var3;
      super.field_149475_d = var5;
      super.field_149478_c = var7;
      super.field_149476_e = var9;
      super.field_149473_f = var10;
      super.field_149474_g = var11;
      super.field_149481_i = true;
      super.field_149480_h = true;
   }

   public void readPacketData(PacketBuffer var1) {
      super.field_149479_a = var1.readDouble();
      super.field_149477_b = var1.readDouble();
      super.field_149475_d = var1.readDouble();
      super.field_149478_c = var1.readDouble();
      super.field_149476_e = var1.readFloat();
      super.field_149473_f = var1.readFloat();
      super.readPacketData(var1);
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeDouble(super.field_149479_a);
      var1.writeDouble(super.field_149477_b);
      var1.writeDouble(super.field_149475_d);
      var1.writeDouble(super.field_149478_c);
      var1.writeFloat(super.field_149476_e);
      var1.writeFloat(super.field_149473_f);
      super.writePacketData(var1);
   }
}
