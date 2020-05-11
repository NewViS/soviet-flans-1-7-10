package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.Score;

public class S3CPacketUpdateScore extends Packet {

   private String field_149329_a = "";
   private String field_149327_b = "";
   private int field_149328_c;
   private int field_149326_d;


   public S3CPacketUpdateScore() {}

   public S3CPacketUpdateScore(Score var1, int var2) {
      this.field_149329_a = var1.getPlayerName();
      this.field_149327_b = var1.func_96645_d().getName();
      this.field_149328_c = var1.getScorePoints();
      this.field_149326_d = var2;
   }

   public S3CPacketUpdateScore(String var1) {
      this.field_149329_a = var1;
      this.field_149327_b = "";
      this.field_149328_c = 0;
      this.field_149326_d = 1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149329_a = var1.readStringFromBuffer(16);
      this.field_149326_d = var1.readByte();
      if(this.field_149326_d != 1) {
         this.field_149327_b = var1.readStringFromBuffer(16);
         this.field_149328_c = var1.readInt();
      }

   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeStringToBuffer(this.field_149329_a);
      var1.writeByte(this.field_149326_d);
      if(this.field_149326_d != 1) {
         var1.writeStringToBuffer(this.field_149327_b);
         var1.writeInt(this.field_149328_c);
      }

   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleUpdateScore(this);
   }

   public String func_149324_c() {
      return this.field_149329_a;
   }

   public String func_149321_d() {
      return this.field_149327_b;
   }

   public int func_149323_e() {
      return this.field_149328_c;
   }

   public int func_149322_f() {
      return this.field_149326_d;
   }
}
