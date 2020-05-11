package net.minecraft.network.play.client;

import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C10PacketCreativeInventoryAction extends Packet {

   private int field_149629_a;
   private ItemStack field_149628_b;


   public C10PacketCreativeInventoryAction() {}

   public C10PacketCreativeInventoryAction(int var1, ItemStack var2) {
      this.field_149629_a = var1;
      this.field_149628_b = var2 != null?var2.copy():null;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processCreativeInventoryAction(this);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149629_a = var1.readShort();
      this.field_149628_b = var1.readItemStackFromBuffer();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeShort(this.field_149629_a);
      var1.writeItemStackToBuffer(this.field_149628_b);
   }

   public int func_149627_c() {
      return this.field_149629_a;
   }

   public ItemStack func_149625_d() {
      return this.field_149628_b;
   }
}
