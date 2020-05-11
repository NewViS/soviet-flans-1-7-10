package net.minecraft.network.play.client;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C03PacketPlayer;

public class C03PacketPlayer$C05PacketPlayerLook extends C03PacketPlayer {

   public C03PacketPlayer$C05PacketPlayerLook() {
      super.field_149481_i = true;
   }

   public C03PacketPlayer$C05PacketPlayerLook(float var1, float var2, boolean var3) {
      super.field_149476_e = var1;
      super.field_149473_f = var2;
      super.field_149474_g = var3;
      super.field_149481_i = true;
   }

   public void readPacketData(PacketBuffer var1) {
      super.field_149476_e = var1.readFloat();
      super.field_149473_f = var1.readFloat();
      super.readPacketData(var1);
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeFloat(super.field_149476_e);
      var1.writeFloat(super.field_149473_f);
      super.writePacketData(var1);
   }
}
