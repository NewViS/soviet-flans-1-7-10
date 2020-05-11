package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S2BPacketChangeGameState extends Packet {

   public static final String[] field_149142_a = new String[]{"tile.bed.notValid", null, null, "gameMode.changed"};
   private int field_149140_b;
   private float field_149141_c;


   public S2BPacketChangeGameState() {}

   public S2BPacketChangeGameState(int var1, float var2) {
      this.field_149140_b = var1;
      this.field_149141_c = var2;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149140_b = var1.readUnsignedByte();
      this.field_149141_c = var1.readFloat();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.field_149140_b);
      var1.writeFloat(this.field_149141_c);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleChangeGameState(this);
   }

   public int func_149138_c() {
      return this.field_149140_b;
   }

   public float func_149137_d() {
      return this.field_149141_c;
   }

}
