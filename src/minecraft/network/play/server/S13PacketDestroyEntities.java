package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S13PacketDestroyEntities extends Packet {

   private int[] field_149100_a;


   public S13PacketDestroyEntities() {}

   public S13PacketDestroyEntities(int ... var1) {
      this.field_149100_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149100_a = new int[var1.readByte()];

      for(int var2 = 0; var2 < this.field_149100_a.length; ++var2) {
         this.field_149100_a[var2] = var1.readInt();
      }

   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.field_149100_a.length);

      for(int var2 = 0; var2 < this.field_149100_a.length; ++var2) {
         var1.writeInt(this.field_149100_a[var2]);
      }

   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleDestroyEntities(this);
   }

   public String serialize() {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 0; var2 < this.field_149100_a.length; ++var2) {
         if(var2 > 0) {
            var1.append(", ");
         }

         var1.append(this.field_149100_a[var2]);
      }

      return String.format("entities=%d[%s]", new Object[]{Integer.valueOf(this.field_149100_a.length), var1});
   }

   public int[] func_149098_c() {
      return this.field_149100_a;
   }
}
