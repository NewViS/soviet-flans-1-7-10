package net.minecraft.network.play.server;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S11PacketSpawnExperienceOrb extends Packet {

   private int field_148992_a;
   private int field_148990_b;
   private int field_148991_c;
   private int field_148988_d;
   private int field_148989_e;


   public S11PacketSpawnExperienceOrb() {}

   public S11PacketSpawnExperienceOrb(EntityXPOrb var1) {
      this.field_148992_a = var1.getEntityId();
      this.field_148990_b = MathHelper.floor_double(var1.posX * 32.0D);
      this.field_148991_c = MathHelper.floor_double(var1.posY * 32.0D);
      this.field_148988_d = MathHelper.floor_double(var1.posZ * 32.0D);
      this.field_148989_e = var1.getXpValue();
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_148992_a = var1.readVarIntFromBuffer();
      this.field_148990_b = var1.readInt();
      this.field_148991_c = var1.readInt();
      this.field_148988_d = var1.readInt();
      this.field_148989_e = var1.readShort();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.field_148992_a);
      var1.writeInt(this.field_148990_b);
      var1.writeInt(this.field_148991_c);
      var1.writeInt(this.field_148988_d);
      var1.writeShort(this.field_148989_e);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnExperienceOrb(this);
   }

   public String serialize() {
      return String.format("id=%d, value=%d, x=%.2f, y=%.2f, z=%.2f", new Object[]{Integer.valueOf(this.field_148992_a), Integer.valueOf(this.field_148989_e), Float.valueOf((float)this.field_148990_b / 32.0F), Float.valueOf((float)this.field_148991_c / 32.0F), Float.valueOf((float)this.field_148988_d / 32.0F)});
   }

   public int func_148985_c() {
      return this.field_148992_a;
   }

   public int func_148984_d() {
      return this.field_148990_b;
   }

   public int func_148983_e() {
      return this.field_148991_c;
   }

   public int func_148982_f() {
      return this.field_148988_d;
   }

   public int func_148986_g() {
      return this.field_148989_e;
   }
}
