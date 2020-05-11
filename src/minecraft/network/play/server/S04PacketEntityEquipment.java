package net.minecraft.network.play.server;

import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S04PacketEntityEquipment extends Packet {

   private int field_149394_a;
   private int field_149392_b;
   private ItemStack field_149393_c;


   public S04PacketEntityEquipment() {}

   public S04PacketEntityEquipment(int var1, int var2, ItemStack var3) {
      this.field_149394_a = var1;
      this.field_149392_b = var2;
      this.field_149393_c = var3 == null?null:var3.copy();
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149394_a = var1.readInt();
      this.field_149392_b = var1.readShort();
      this.field_149393_c = var1.readItemStackFromBuffer();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149394_a);
      var1.writeShort(this.field_149392_b);
      var1.writeItemStackToBuffer(this.field_149393_c);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityEquipment(this);
   }

   public ItemStack func_149390_c() {
      return this.field_149393_c;
   }

   public String serialize() {
      return String.format("entity=%d, slot=%d, item=%s", new Object[]{Integer.valueOf(this.field_149394_a), Integer.valueOf(this.field_149392_b), this.field_149393_c});
   }

   public int func_149389_d() {
      return this.field_149394_a;
   }

   public int func_149388_e() {
      return this.field_149392_b;
   }
}
