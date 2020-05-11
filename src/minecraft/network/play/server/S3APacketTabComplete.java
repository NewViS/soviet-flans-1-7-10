package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import org.apache.commons.lang3.ArrayUtils;

public class S3APacketTabComplete extends Packet {

   private String[] field_149632_a;


   public S3APacketTabComplete() {}

   public S3APacketTabComplete(String[] var1) {
      this.field_149632_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149632_a = new String[var1.readVarIntFromBuffer()];

      for(int var2 = 0; var2 < this.field_149632_a.length; ++var2) {
         this.field_149632_a[var2] = var1.readStringFromBuffer(32767);
      }

   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.field_149632_a.length);
      String[] var2 = this.field_149632_a;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String var5 = var2[var4];
         var1.writeStringToBuffer(var5);
      }

   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleTabComplete(this);
   }

   public String[] func_149630_c() {
      return this.field_149632_a;
   }

   public String serialize() {
      return String.format("candidates=\'%s\'", new Object[]{ArrayUtils.toString(this.field_149632_a)});
   }
}
