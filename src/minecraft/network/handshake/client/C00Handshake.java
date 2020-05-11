package net.minecraft.network.handshake.client;

import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.handshake.INetHandlerHandshakeServer;

public class C00Handshake extends Packet {

   private int field_149600_a;
   private String field_149598_b;
   private int field_149599_c;
   private EnumConnectionState field_149597_d;


   public C00Handshake() {}

   public C00Handshake(int var1, String var2, int var3, EnumConnectionState var4) {
      this.field_149600_a = var1;
      this.field_149598_b = var2;
      this.field_149599_c = var3;
      this.field_149597_d = var4;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149600_a = var1.readVarIntFromBuffer();
      this.field_149598_b = var1.readStringFromBuffer(255);
      this.field_149599_c = var1.readUnsignedShort();
      this.field_149597_d = EnumConnectionState.func_150760_a(var1.readVarIntFromBuffer());
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.field_149600_a);
      var1.writeStringToBuffer(this.field_149598_b);
      var1.writeShort(this.field_149599_c);
      var1.writeVarIntToBuffer(this.field_149597_d.func_150759_c());
   }

   public void processPacket(INetHandlerHandshakeServer var1) {
      var1.processHandshake(this);
   }

   public boolean hasPriority() {
      return true;
   }

   public EnumConnectionState func_149594_c() {
      return this.field_149597_d;
   }

   public int func_149595_d() {
      return this.field_149600_a;
   }
}
