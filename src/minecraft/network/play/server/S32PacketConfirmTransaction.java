package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S32PacketConfirmTransaction extends Packet {

   private int field_148894_a;
   private short field_148892_b;
   private boolean field_148893_c;


   public S32PacketConfirmTransaction() {}

   public S32PacketConfirmTransaction(int var1, short var2, boolean var3) {
      this.field_148894_a = var1;
      this.field_148892_b = var2;
      this.field_148893_c = var3;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleConfirmTransaction(this);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_148894_a = var1.readUnsignedByte();
      this.field_148892_b = var1.readShort();
      this.field_148893_c = var1.readBoolean();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.field_148894_a);
      var1.writeShort(this.field_148892_b);
      var1.writeBoolean(this.field_148893_c);
   }

   public String serialize() {
      return String.format("id=%d, uid=%d, accepted=%b", new Object[]{Integer.valueOf(this.field_148894_a), Short.valueOf(this.field_148892_b), Boolean.valueOf(this.field_148893_c)});
   }

   public int func_148889_c() {
      return this.field_148894_a;
   }

   public short func_148890_d() {
      return this.field_148892_b;
   }

   public boolean func_148888_e() {
      return this.field_148893_c;
   }
}
