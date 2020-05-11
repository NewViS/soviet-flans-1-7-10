package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S28PacketEffect extends Packet {

   private int field_149251_a;
   private int field_149249_b;
   private int field_149250_c;
   private int field_149247_d;
   private int field_149248_e;
   private boolean field_149246_f;


   public S28PacketEffect() {}

   public S28PacketEffect(int var1, int var2, int var3, int var4, int var5, boolean var6) {
      this.field_149251_a = var1;
      this.field_149250_c = var2;
      this.field_149247_d = var3;
      this.field_149248_e = var4;
      this.field_149249_b = var5;
      this.field_149246_f = var6;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149251_a = var1.readInt();
      this.field_149250_c = var1.readInt();
      this.field_149247_d = var1.readByte() & 255;
      this.field_149248_e = var1.readInt();
      this.field_149249_b = var1.readInt();
      this.field_149246_f = var1.readBoolean();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149251_a);
      var1.writeInt(this.field_149250_c);
      var1.writeByte(this.field_149247_d & 255);
      var1.writeInt(this.field_149248_e);
      var1.writeInt(this.field_149249_b);
      var1.writeBoolean(this.field_149246_f);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEffect(this);
   }

   public boolean func_149244_c() {
      return this.field_149246_f;
   }

   public int func_149242_d() {
      return this.field_149251_a;
   }

   public int func_149241_e() {
      return this.field_149249_b;
   }

   public int func_149240_f() {
      return this.field_149250_c;
   }

   public int func_149243_g() {
      return this.field_149247_d;
   }

   public int func_149239_h() {
      return this.field_149248_e;
   }
}
