package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C11PacketEnchantItem extends Packet {

   private int field_149541_a;
   private int field_149540_b;


   public C11PacketEnchantItem() {}

   public C11PacketEnchantItem(int var1, int var2) {
      this.field_149541_a = var1;
      this.field_149540_b = var2;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processEnchantItem(this);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149541_a = var1.readByte();
      this.field_149540_b = var1.readByte();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.field_149541_a);
      var1.writeByte(this.field_149540_b);
   }

   public String serialize() {
      return String.format("id=%d, button=%d", new Object[]{Integer.valueOf(this.field_149541_a), Integer.valueOf(this.field_149540_b)});
   }

   public int func_149539_c() {
      return this.field_149541_a;
   }

   public int func_149537_d() {
      return this.field_149540_b;
   }
}
