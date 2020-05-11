package net.minecraft.network.play.server;

import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S2FPacketSetSlot extends Packet {

   private int field_149179_a;
   private int field_149177_b;
   private ItemStack field_149178_c;


   public S2FPacketSetSlot() {}

   public S2FPacketSetSlot(int var1, int var2, ItemStack var3) {
      this.field_149179_a = var1;
      this.field_149177_b = var2;
      this.field_149178_c = var3 == null?null:var3.copy();
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSetSlot(this);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149179_a = var1.readByte();
      this.field_149177_b = var1.readShort();
      this.field_149178_c = var1.readItemStackFromBuffer();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.field_149179_a);
      var1.writeShort(this.field_149177_b);
      var1.writeItemStackToBuffer(this.field_149178_c);
   }

   public int func_149175_c() {
      return this.field_149179_a;
   }

   public int func_149173_d() {
      return this.field_149177_b;
   }

   public ItemStack func_149174_e() {
      return this.field_149178_c;
   }
}
