package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.potion.PotionEffect;

public class S1DPacketEntityEffect extends Packet {

   private int field_149434_a;
   private byte field_149432_b;
   private byte field_149433_c;
   private short field_149431_d;


   public S1DPacketEntityEffect() {}

   public S1DPacketEntityEffect(int var1, PotionEffect var2) {
      this.field_149434_a = var1;
      this.field_149432_b = (byte)(var2.getPotionID() & 255);
      this.field_149433_c = (byte)(var2.getAmplifier() & 255);
      if(var2.getDuration() > 32767) {
         this.field_149431_d = 32767;
      } else {
         this.field_149431_d = (short)var2.getDuration();
      }

   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149434_a = var1.readInt();
      this.field_149432_b = var1.readByte();
      this.field_149433_c = var1.readByte();
      this.field_149431_d = var1.readShort();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149434_a);
      var1.writeByte(this.field_149432_b);
      var1.writeByte(this.field_149433_c);
      var1.writeShort(this.field_149431_d);
   }

   public boolean func_149429_c() {
      return this.field_149431_d == 32767;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityEffect(this);
   }

   public int func_149426_d() {
      return this.field_149434_a;
   }

   public byte func_149427_e() {
      return this.field_149432_b;
   }

   public byte func_149428_f() {
      return this.field_149433_c;
   }

   public short func_149425_g() {
      return this.field_149431_d;
   }
}
