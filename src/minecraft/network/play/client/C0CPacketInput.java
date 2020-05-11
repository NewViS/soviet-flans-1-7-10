package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0CPacketInput extends Packet {

   private float field_149624_a;
   private float field_149622_b;
   private boolean field_149623_c;
   private boolean field_149621_d;


   public C0CPacketInput() {}

   public C0CPacketInput(float var1, float var2, boolean var3, boolean var4) {
      this.field_149624_a = var1;
      this.field_149622_b = var2;
      this.field_149623_c = var3;
      this.field_149621_d = var4;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149624_a = var1.readFloat();
      this.field_149622_b = var1.readFloat();
      this.field_149623_c = var1.readBoolean();
      this.field_149621_d = var1.readBoolean();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeFloat(this.field_149624_a);
      var1.writeFloat(this.field_149622_b);
      var1.writeBoolean(this.field_149623_c);
      var1.writeBoolean(this.field_149621_d);
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processInput(this);
   }

   public float func_149620_c() {
      return this.field_149624_a;
   }

   public float func_149616_d() {
      return this.field_149622_b;
   }

   public boolean func_149618_e() {
      return this.field_149623_c;
   }

   public boolean func_149617_f() {
      return this.field_149621_d;
   }
}
