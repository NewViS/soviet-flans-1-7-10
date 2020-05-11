package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.ScoreObjective;

public class S3BPacketScoreboardObjective extends Packet {

   private String field_149343_a;
   private String field_149341_b;
   private int field_149342_c;


   public S3BPacketScoreboardObjective() {}

   public S3BPacketScoreboardObjective(ScoreObjective var1, int var2) {
      this.field_149343_a = var1.getName();
      this.field_149341_b = var1.getDisplayName();
      this.field_149342_c = var2;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149343_a = var1.readStringFromBuffer(16);
      this.field_149341_b = var1.readStringFromBuffer(32);
      this.field_149342_c = var1.readByte();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeStringToBuffer(this.field_149343_a);
      var1.writeStringToBuffer(this.field_149341_b);
      var1.writeByte(this.field_149342_c);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleScoreboardObjective(this);
   }

   public String func_149339_c() {
      return this.field_149343_a;
   }

   public String func_149337_d() {
      return this.field_149341_b;
   }

   public int func_149338_e() {
      return this.field_149342_c;
   }
}
