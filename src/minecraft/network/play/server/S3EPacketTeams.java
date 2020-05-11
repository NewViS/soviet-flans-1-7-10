package net.minecraft.network.play.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class S3EPacketTeams extends Packet {

   private String field_149320_a = "";
   private String field_149318_b = "";
   private String field_149319_c = "";
   private String field_149316_d = "";
   private Collection field_149317_e = new ArrayList();
   private int field_149314_f;
   private int field_149315_g;


   public S3EPacketTeams() {}

   public S3EPacketTeams(ScorePlayerTeam var1, int var2) {
      this.field_149320_a = var1.getRegisteredName();
      this.field_149314_f = var2;
      if(var2 == 0 || var2 == 2) {
         this.field_149318_b = var1.func_96669_c();
         this.field_149319_c = var1.getColorPrefix();
         this.field_149316_d = var1.getColorSuffix();
         this.field_149315_g = var1.func_98299_i();
      }

      if(var2 == 0) {
         this.field_149317_e.addAll(var1.getMembershipCollection());
      }

   }

   public S3EPacketTeams(ScorePlayerTeam var1, Collection var2, int var3) {
      if(var3 != 3 && var3 != 4) {
         throw new IllegalArgumentException("Method must be join or leave for player constructor");
      } else if(var2 != null && !var2.isEmpty()) {
         this.field_149314_f = var3;
         this.field_149320_a = var1.getRegisteredName();
         this.field_149317_e.addAll(var2);
      } else {
         throw new IllegalArgumentException("Players cannot be null/empty");
      }
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149320_a = var1.readStringFromBuffer(16);
      this.field_149314_f = var1.readByte();
      if(this.field_149314_f == 0 || this.field_149314_f == 2) {
         this.field_149318_b = var1.readStringFromBuffer(32);
         this.field_149319_c = var1.readStringFromBuffer(16);
         this.field_149316_d = var1.readStringFromBuffer(16);
         this.field_149315_g = var1.readByte();
      }

      if(this.field_149314_f == 0 || this.field_149314_f == 3 || this.field_149314_f == 4) {
         short var2 = var1.readShort();

         for(int var3 = 0; var3 < var2; ++var3) {
            this.field_149317_e.add(var1.readStringFromBuffer(40));
         }
      }

   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeStringToBuffer(this.field_149320_a);
      var1.writeByte(this.field_149314_f);
      if(this.field_149314_f == 0 || this.field_149314_f == 2) {
         var1.writeStringToBuffer(this.field_149318_b);
         var1.writeStringToBuffer(this.field_149319_c);
         var1.writeStringToBuffer(this.field_149316_d);
         var1.writeByte(this.field_149315_g);
      }

      if(this.field_149314_f == 0 || this.field_149314_f == 3 || this.field_149314_f == 4) {
         var1.writeShort(this.field_149317_e.size());
         Iterator var2 = this.field_149317_e.iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            var1.writeStringToBuffer(var3);
         }
      }

   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleTeams(this);
   }

   public String func_149312_c() {
      return this.field_149320_a;
   }

   public String func_149306_d() {
      return this.field_149318_b;
   }

   public String func_149311_e() {
      return this.field_149319_c;
   }

   public String func_149309_f() {
      return this.field_149316_d;
   }

   public Collection func_149310_g() {
      return this.field_149317_e;
   }

   public int func_149307_h() {
      return this.field_149314_f;
   }

   public int func_149308_i() {
      return this.field_149315_g;
   }
}
