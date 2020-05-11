package net.minecraft.network.play.server;

import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityPainting$EnumArt;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S10PacketSpawnPainting extends Packet {

   private int field_148973_a;
   private int field_148971_b;
   private int field_148972_c;
   private int field_148969_d;
   private int field_148970_e;
   private String field_148968_f;


   public S10PacketSpawnPainting() {}

   public S10PacketSpawnPainting(EntityPainting var1) {
      this.field_148973_a = var1.getEntityId();
      this.field_148971_b = var1.field_146063_b;
      this.field_148972_c = var1.field_146064_c;
      this.field_148969_d = var1.field_146062_d;
      this.field_148970_e = var1.hangingDirection;
      this.field_148968_f = var1.art.title;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_148973_a = var1.readVarIntFromBuffer();
      this.field_148968_f = var1.readStringFromBuffer(EntityPainting$EnumArt.maxArtTitleLength);
      this.field_148971_b = var1.readInt();
      this.field_148972_c = var1.readInt();
      this.field_148969_d = var1.readInt();
      this.field_148970_e = var1.readInt();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.field_148973_a);
      var1.writeStringToBuffer(this.field_148968_f);
      var1.writeInt(this.field_148971_b);
      var1.writeInt(this.field_148972_c);
      var1.writeInt(this.field_148969_d);
      var1.writeInt(this.field_148970_e);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnPainting(this);
   }

   public String serialize() {
      return String.format("id=%d, type=%s, x=%d, y=%d, z=%d", new Object[]{Integer.valueOf(this.field_148973_a), this.field_148968_f, Integer.valueOf(this.field_148971_b), Integer.valueOf(this.field_148972_c), Integer.valueOf(this.field_148969_d)});
   }

   public int func_148965_c() {
      return this.field_148973_a;
   }

   public int func_148964_d() {
      return this.field_148971_b;
   }

   public int func_148963_e() {
      return this.field_148972_c;
   }

   public int func_148962_f() {
      return this.field_148969_d;
   }

   public int func_148966_g() {
      return this.field_148970_e;
   }

   public String func_148961_h() {
      return this.field_148968_f;
   }
}
