package net.minecraft.network.play.server;

import net.minecraft.block.Block;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S24PacketBlockAction extends Packet {

   private int field_148876_a;
   private int field_148874_b;
   private int field_148875_c;
   private int field_148872_d;
   private int field_148873_e;
   private Block field_148871_f;


   public S24PacketBlockAction() {}

   public S24PacketBlockAction(int var1, int var2, int var3, Block var4, int var5, int var6) {
      this.field_148876_a = var1;
      this.field_148874_b = var2;
      this.field_148875_c = var3;
      this.field_148872_d = var5;
      this.field_148873_e = var6;
      this.field_148871_f = var4;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_148876_a = var1.readInt();
      this.field_148874_b = var1.readShort();
      this.field_148875_c = var1.readInt();
      this.field_148872_d = var1.readUnsignedByte();
      this.field_148873_e = var1.readUnsignedByte();
      this.field_148871_f = Block.getBlockById(var1.readVarIntFromBuffer() & 4095);
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_148876_a);
      var1.writeShort(this.field_148874_b);
      var1.writeInt(this.field_148875_c);
      var1.writeByte(this.field_148872_d);
      var1.writeByte(this.field_148873_e);
      var1.writeVarIntToBuffer(Block.getIdFromBlock(this.field_148871_f) & 4095);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleBlockAction(this);
   }

   public Block func_148868_c() {
      return this.field_148871_f;
   }

   public int func_148867_d() {
      return this.field_148876_a;
   }

   public int func_148866_e() {
      return this.field_148874_b;
   }

   public int func_148865_f() {
      return this.field_148875_c;
   }

   public int func_148869_g() {
      return this.field_148872_d;
   }

   public int func_148864_h() {
      return this.field_148873_e;
   }
}
