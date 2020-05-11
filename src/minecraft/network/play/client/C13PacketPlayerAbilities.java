package net.minecraft.network.play.client;

import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C13PacketPlayerAbilities extends Packet {

   private boolean field_149500_a;
   private boolean field_149498_b;
   private boolean field_149499_c;
   private boolean field_149496_d;
   private float field_149497_e;
   private float field_149495_f;


   public C13PacketPlayerAbilities() {}

   public C13PacketPlayerAbilities(PlayerCapabilities var1) {
      this.func_149490_a(var1.disableDamage);
      this.func_149483_b(var1.isFlying);
      this.func_149491_c(var1.allowFlying);
      this.func_149493_d(var1.isCreativeMode);
      this.func_149485_a(var1.getFlySpeed());
      this.func_149492_b(var1.getWalkSpeed());
   }

   public void readPacketData(PacketBuffer var1) {
      byte var2 = var1.readByte();
      this.func_149490_a((var2 & 1) > 0);
      this.func_149483_b((var2 & 2) > 0);
      this.func_149491_c((var2 & 4) > 0);
      this.func_149493_d((var2 & 8) > 0);
      this.func_149485_a(var1.readFloat());
      this.func_149492_b(var1.readFloat());
   }

   public void writePacketData(PacketBuffer var1) {
      byte var2 = 0;
      if(this.func_149494_c()) {
         var2 = (byte)(var2 | 1);
      }

      if(this.func_149488_d()) {
         var2 = (byte)(var2 | 2);
      }

      if(this.func_149486_e()) {
         var2 = (byte)(var2 | 4);
      }

      if(this.func_149484_f()) {
         var2 = (byte)(var2 | 8);
      }

      var1.writeByte(var2);
      var1.writeFloat(this.field_149497_e);
      var1.writeFloat(this.field_149495_f);
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processPlayerAbilities(this);
   }

   public String serialize() {
      return String.format("invuln=%b, flying=%b, canfly=%b, instabuild=%b, flyspeed=%.4f, walkspped=%.4f", new Object[]{Boolean.valueOf(this.func_149494_c()), Boolean.valueOf(this.func_149488_d()), Boolean.valueOf(this.func_149486_e()), Boolean.valueOf(this.func_149484_f()), Float.valueOf(this.func_149482_g()), Float.valueOf(this.func_149489_h())});
   }

   public boolean func_149494_c() {
      return this.field_149500_a;
   }

   public void func_149490_a(boolean var1) {
      this.field_149500_a = var1;
   }

   public boolean func_149488_d() {
      return this.field_149498_b;
   }

   public void func_149483_b(boolean var1) {
      this.field_149498_b = var1;
   }

   public boolean func_149486_e() {
      return this.field_149499_c;
   }

   public void func_149491_c(boolean var1) {
      this.field_149499_c = var1;
   }

   public boolean func_149484_f() {
      return this.field_149496_d;
   }

   public void func_149493_d(boolean var1) {
      this.field_149496_d = var1;
   }

   public float func_149482_g() {
      return this.field_149497_e;
   }

   public void func_149485_a(float var1) {
      this.field_149497_e = var1;
   }

   public float func_149489_h() {
      return this.field_149495_f;
   }

   public void func_149492_b(float var1) {
      this.field_149495_f = var1;
   }
}
