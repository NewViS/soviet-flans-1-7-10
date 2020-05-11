package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S03PacketTimeUpdate extends Packet {

   private long field_149369_a;
   private long field_149368_b;


   public S03PacketTimeUpdate() {}

   public S03PacketTimeUpdate(long var1, long var3, boolean var5) {
      this.field_149369_a = var1;
      this.field_149368_b = var3;
      if(!var5) {
         this.field_149368_b = -this.field_149368_b;
         if(this.field_149368_b == 0L) {
            this.field_149368_b = -1L;
         }
      }

   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149369_a = var1.readLong();
      this.field_149368_b = var1.readLong();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeLong(this.field_149369_a);
      var1.writeLong(this.field_149368_b);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleTimeUpdate(this);
   }

   public String serialize() {
      return String.format("time=%d,dtime=%d", new Object[]{Long.valueOf(this.field_149369_a), Long.valueOf(this.field_149368_b)});
   }

   public long func_149366_c() {
      return this.field_149369_a;
   }

   public long func_149365_d() {
      return this.field_149368_b;
   }
}
