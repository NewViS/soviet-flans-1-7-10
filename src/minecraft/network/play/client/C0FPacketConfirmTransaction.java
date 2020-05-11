package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0FPacketConfirmTransaction extends Packet {

   private int field_149536_a;
   private short field_149534_b;
   private boolean field_149535_c;


   public C0FPacketConfirmTransaction() {}

   public C0FPacketConfirmTransaction(int var1, short var2, boolean var3) {
      this.field_149536_a = var1;
      this.field_149534_b = var2;
      this.field_149535_c = var3;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processConfirmTransaction(this);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149536_a = var1.readByte();
      this.field_149534_b = var1.readShort();
      this.field_149535_c = var1.readByte() != 0;
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.field_149536_a);
      var1.writeShort(this.field_149534_b);
      var1.writeByte(this.field_149535_c?1:0);
   }

   public String serialize() {
      return String.format("id=%d, uid=%d, accepted=%b", new Object[]{Integer.valueOf(this.field_149536_a), Short.valueOf(this.field_149534_b), Boolean.valueOf(this.field_149535_c)});
   }

   public int func_149532_c() {
      return this.field_149536_a;
   }

   public short func_149533_d() {
      return this.field_149534_b;
   }
}
