package net.minecraft.network.play.server;

import net.minecraft.block.Block;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S23PacketBlockChange extends Packet {

   private int field_148887_a;
   private int field_148885_b;
   private int field_148886_c;
   private Block field_148883_d;
   private int field_148884_e;


   public S23PacketBlockChange() {}

   public S23PacketBlockChange(int var1, int var2, int var3, World var4) {
      this.field_148887_a = var1;
      this.field_148885_b = var2;
      this.field_148886_c = var3;
      this.field_148883_d = var4.getBlock(var1, var2, var3);
      this.field_148884_e = var4.getBlockMetadata(var1, var2, var3);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_148887_a = var1.readInt();
      this.field_148885_b = var1.readUnsignedByte();
      this.field_148886_c = var1.readInt();
      this.field_148883_d = Block.getBlockById(var1.readVarIntFromBuffer());
      this.field_148884_e = var1.readUnsignedByte();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_148887_a);
      var1.writeByte(this.field_148885_b);
      var1.writeInt(this.field_148886_c);
      var1.writeVarIntToBuffer(Block.getIdFromBlock(this.field_148883_d));
      var1.writeByte(this.field_148884_e);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleBlockChange(this);
   }

   public String serialize() {
      return String.format("type=%d, data=%d, x=%d, y=%d, z=%d", new Object[]{Integer.valueOf(Block.getIdFromBlock(this.field_148883_d)), Integer.valueOf(this.field_148884_e), Integer.valueOf(this.field_148887_a), Integer.valueOf(this.field_148885_b), Integer.valueOf(this.field_148886_c)});
   }

   public Block func_148880_c() {
      return this.field_148883_d;
   }

   public int func_148879_d() {
      return this.field_148887_a;
   }

   public int func_148878_e() {
      return this.field_148885_b;
   }

   public int func_148877_f() {
      return this.field_148886_c;
   }

   public int func_148881_g() {
      return this.field_148884_e;
   }
}
