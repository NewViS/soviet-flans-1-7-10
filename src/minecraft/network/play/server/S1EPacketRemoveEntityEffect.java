package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.potion.PotionEffect;

public class S1EPacketRemoveEntityEffect extends Packet {

   private int field_149079_a;
   private int field_149078_b;


   public S1EPacketRemoveEntityEffect() {}

   public S1EPacketRemoveEntityEffect(int var1, PotionEffect var2) {
      this.field_149079_a = var1;
      this.field_149078_b = var2.getPotionID();
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149079_a = var1.readInt();
      this.field_149078_b = var1.readUnsignedByte();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149079_a);
      var1.writeByte(this.field_149078_b);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleRemoveEntityEffect(this);
   }

   public int func_149076_c() {
      return this.field_149079_a;
   }

   public int func_149075_d() {
      return this.field_149078_b;
   }
}
