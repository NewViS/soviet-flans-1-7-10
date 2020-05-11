package net.minecraft.network.play.client;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0EPacketClickWindow extends Packet {

   private int field_149554_a;
   private int field_149552_b;
   private int field_149553_c;
   private short field_149550_d;
   private ItemStack field_149551_e;
   private int field_149549_f;


   public C0EPacketClickWindow() {}

   public C0EPacketClickWindow(int var1, int var2, int var3, int var4, ItemStack var5, short var6) {
      this.field_149554_a = var1;
      this.field_149552_b = var2;
      this.field_149553_c = var3;
      this.field_149551_e = var5 != null?var5.copy():null;
      this.field_149550_d = var6;
      this.field_149549_f = var4;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processClickWindow(this);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149554_a = var1.readByte();
      this.field_149552_b = var1.readShort();
      this.field_149553_c = var1.readByte();
      this.field_149550_d = var1.readShort();
      this.field_149549_f = var1.readByte();
      this.field_149551_e = var1.readItemStackFromBuffer();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.field_149554_a);
      var1.writeShort(this.field_149552_b);
      var1.writeByte(this.field_149553_c);
      var1.writeShort(this.field_149550_d);
      var1.writeByte(this.field_149549_f);
      var1.writeItemStackToBuffer(this.field_149551_e);
   }

   public String serialize() {
      return this.field_149551_e != null?String.format("id=%d, slot=%d, button=%d, type=%d, itemid=%d, itemcount=%d, itemaux=%d", new Object[]{Integer.valueOf(this.field_149554_a), Integer.valueOf(this.field_149552_b), Integer.valueOf(this.field_149553_c), Integer.valueOf(this.field_149549_f), Integer.valueOf(Item.getIdFromItem(this.field_149551_e.getItem())), Integer.valueOf(this.field_149551_e.stackSize), Integer.valueOf(this.field_149551_e.getItemDamage())}):String.format("id=%d, slot=%d, button=%d, type=%d, itemid=-1", new Object[]{Integer.valueOf(this.field_149554_a), Integer.valueOf(this.field_149552_b), Integer.valueOf(this.field_149553_c), Integer.valueOf(this.field_149549_f)});
   }

   public int func_149548_c() {
      return this.field_149554_a;
   }

   public int func_149544_d() {
      return this.field_149552_b;
   }

   public int func_149543_e() {
      return this.field_149553_c;
   }

   public short func_149547_f() {
      return this.field_149550_d;
   }

   public ItemStack func_149546_g() {
      return this.field_149551_e;
   }

   public int func_149542_h() {
      return this.field_149549_f;
   }
}
