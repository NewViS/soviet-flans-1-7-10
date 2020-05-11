package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S36PacketSignEditorOpen extends Packet {

   private int field_149133_a;
   private int field_149131_b;
   private int field_149132_c;


   public S36PacketSignEditorOpen() {}

   public S36PacketSignEditorOpen(int var1, int var2, int var3) {
      this.field_149133_a = var1;
      this.field_149131_b = var2;
      this.field_149132_c = var3;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSignEditorOpen(this);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149133_a = var1.readInt();
      this.field_149131_b = var1.readInt();
      this.field_149132_c = var1.readInt();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149133_a);
      var1.writeInt(this.field_149131_b);
      var1.writeInt(this.field_149132_c);
   }

   public int func_149129_c() {
      return this.field_149133_a;
   }

   public int func_149128_d() {
      return this.field_149131_b;
   }

   public int func_149127_e() {
      return this.field_149132_c;
   }
}
