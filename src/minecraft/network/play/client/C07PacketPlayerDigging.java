package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C07PacketPlayerDigging extends Packet {

   private int field_149511_a;
   private int field_149509_b;
   private int field_149510_c;
   private int field_149507_d;
   private int field_149508_e;


   public C07PacketPlayerDigging() {}

   public C07PacketPlayerDigging(int var1, int var2, int var3, int var4, int var5) {
      this.field_149508_e = var1;
      this.field_149511_a = var2;
      this.field_149509_b = var3;
      this.field_149510_c = var4;
      this.field_149507_d = var5;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149508_e = var1.readUnsignedByte();
      this.field_149511_a = var1.readInt();
      this.field_149509_b = var1.readUnsignedByte();
      this.field_149510_c = var1.readInt();
      this.field_149507_d = var1.readUnsignedByte();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.field_149508_e);
      var1.writeInt(this.field_149511_a);
      var1.writeByte(this.field_149509_b);
      var1.writeInt(this.field_149510_c);
      var1.writeByte(this.field_149507_d);
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processPlayerDigging(this);
   }

   public int func_149505_c() {
      return this.field_149511_a;
   }

   public int func_149503_d() {
      return this.field_149509_b;
   }

   public int func_149502_e() {
      return this.field_149510_c;
   }

   public int func_149501_f() {
      return this.field_149507_d;
   }

   public int func_149506_g() {
      return this.field_149508_e;
   }
}
