package net.minecraft.network.play.client;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C03PacketPlayer;

public class C03PacketPlayer$C04PacketPlayerPosition extends C03PacketPlayer {

   public C03PacketPlayer$C04PacketPlayerPosition() {
      super.field_149480_h = true;
   }

   public C03PacketPlayer$C04PacketPlayerPosition(double var1, double var3, double var5, double var7, boolean var9) {
      super.field_149479_a = var1;
      super.field_149477_b = var3;
      super.field_149475_d = var5;
      super.field_149478_c = var7;
      super.field_149474_g = var9;
      super.field_149480_h = true;
   }

   public void readPacketData(PacketBuffer var1) {
      super.field_149479_a = var1.readDouble();
      super.field_149477_b = var1.readDouble();
      super.field_149475_d = var1.readDouble();
      super.field_149478_c = var1.readDouble();
      super.readPacketData(var1);
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeDouble(super.field_149479_a);
      var1.writeDouble(super.field_149477_b);
      var1.writeDouble(super.field_149475_d);
      var1.writeDouble(super.field_149478_c);
      super.writePacketData(var1);
   }
}
