package net.minecraft.network.play.client;

import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C08PacketPlayerBlockPlacement extends Packet {

   private int field_149583_a;
   private int field_149581_b;
   private int field_149582_c;
   private int field_149579_d;
   private ItemStack field_149580_e;
   private float field_149577_f;
   private float field_149578_g;
   private float field_149584_h;


   public C08PacketPlayerBlockPlacement() {}

   public C08PacketPlayerBlockPlacement(int var1, int var2, int var3, int var4, ItemStack var5, float var6, float var7, float var8) {
      this.field_149583_a = var1;
      this.field_149581_b = var2;
      this.field_149582_c = var3;
      this.field_149579_d = var4;
      this.field_149580_e = var5 != null?var5.copy():null;
      this.field_149577_f = var6;
      this.field_149578_g = var7;
      this.field_149584_h = var8;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149583_a = var1.readInt();
      this.field_149581_b = var1.readUnsignedByte();
      this.field_149582_c = var1.readInt();
      this.field_149579_d = var1.readUnsignedByte();
      this.field_149580_e = var1.readItemStackFromBuffer();
      this.field_149577_f = (float)var1.readUnsignedByte() / 16.0F;
      this.field_149578_g = (float)var1.readUnsignedByte() / 16.0F;
      this.field_149584_h = (float)var1.readUnsignedByte() / 16.0F;
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149583_a);
      var1.writeByte(this.field_149581_b);
      var1.writeInt(this.field_149582_c);
      var1.writeByte(this.field_149579_d);
      var1.writeItemStackToBuffer(this.field_149580_e);
      var1.writeByte((int)(this.field_149577_f * 16.0F));
      var1.writeByte((int)(this.field_149578_g * 16.0F));
      var1.writeByte((int)(this.field_149584_h * 16.0F));
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processPlayerBlockPlacement(this);
   }

   public int func_149576_c() {
      return this.field_149583_a;
   }

   public int func_149571_d() {
      return this.field_149581_b;
   }

   public int func_149570_e() {
      return this.field_149582_c;
   }

   public int func_149568_f() {
      return this.field_149579_d;
   }

   public ItemStack func_149574_g() {
      return this.field_149580_e;
   }

   public float func_149573_h() {
      return this.field_149577_f;
   }

   public float func_149569_i() {
      return this.field_149578_g;
   }

   public float func_149575_j() {
      return this.field_149584_h;
   }
}
