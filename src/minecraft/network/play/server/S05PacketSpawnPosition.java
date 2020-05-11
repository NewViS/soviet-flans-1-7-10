package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S05PacketSpawnPosition extends Packet {

   private int field_149364_a;
   private int field_149362_b;
   private int field_149363_c;


   public S05PacketSpawnPosition() {}

   public S05PacketSpawnPosition(int var1, int var2, int var3) {
      this.field_149364_a = var1;
      this.field_149362_b = var2;
      this.field_149363_c = var3;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149364_a = var1.readInt();
      this.field_149362_b = var1.readInt();
      this.field_149363_c = var1.readInt();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149364_a);
      var1.writeInt(this.field_149362_b);
      var1.writeInt(this.field_149363_c);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnPosition(this);
   }

   public boolean hasPriority() {
      return false;
   }

   public String serialize() {
      return String.format("x=%d, y=%d, z=%d", new Object[]{Integer.valueOf(this.field_149364_a), Integer.valueOf(this.field_149362_b), Integer.valueOf(this.field_149363_c)});
   }

   public int func_149360_c() {
      return this.field_149364_a;
   }

   public int func_149359_d() {
      return this.field_149362_b;
   }

   public int func_149358_e() {
      return this.field_149363_c;
   }
}
