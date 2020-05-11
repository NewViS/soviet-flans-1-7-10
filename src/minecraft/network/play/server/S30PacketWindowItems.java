package net.minecraft.network.play.server;

import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S30PacketWindowItems extends Packet {

   private int field_148914_a;
   private ItemStack[] field_148913_b;


   public S30PacketWindowItems() {}

   public S30PacketWindowItems(int var1, List var2) {
      this.field_148914_a = var1;
      this.field_148913_b = new ItemStack[var2.size()];

      for(int var3 = 0; var3 < this.field_148913_b.length; ++var3) {
         ItemStack var4 = (ItemStack)var2.get(var3);
         this.field_148913_b[var3] = var4 == null?null:var4.copy();
      }

   }

   public void readPacketData(PacketBuffer var1) {
      this.field_148914_a = var1.readUnsignedByte();
      short var2 = var1.readShort();
      this.field_148913_b = new ItemStack[var2];

      for(int var3 = 0; var3 < var2; ++var3) {
         this.field_148913_b[var3] = var1.readItemStackFromBuffer();
      }

   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.field_148914_a);
      var1.writeShort(this.field_148913_b.length);
      ItemStack[] var2 = this.field_148913_b;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ItemStack var5 = var2[var4];
         var1.writeItemStackToBuffer(var5);
      }

   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleWindowItems(this);
   }

   public int func_148911_c() {
      return this.field_148914_a;
   }

   public ItemStack[] func_148910_d() {
      return this.field_148913_b;
   }
}
