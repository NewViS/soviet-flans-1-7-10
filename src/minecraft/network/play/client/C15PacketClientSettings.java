package net.minecraft.network.play.client;

import net.minecraft.entity.player.EntityPlayer$EnumChatVisibility;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.world.EnumDifficulty;

public class C15PacketClientSettings extends Packet {

   private String field_149530_a;
   private int field_149528_b;
   private EntityPlayer$EnumChatVisibility field_149529_c;
   private boolean field_149526_d;
   private EnumDifficulty field_149527_e;
   private boolean field_149525_f;


   public C15PacketClientSettings() {}

   public C15PacketClientSettings(String var1, int var2, EntityPlayer$EnumChatVisibility var3, boolean var4, EnumDifficulty var5, boolean var6) {
      this.field_149530_a = var1;
      this.field_149528_b = var2;
      this.field_149529_c = var3;
      this.field_149526_d = var4;
      this.field_149527_e = var5;
      this.field_149525_f = var6;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149530_a = var1.readStringFromBuffer(7);
      this.field_149528_b = var1.readByte();
      this.field_149529_c = EntityPlayer$EnumChatVisibility.getEnumChatVisibility(var1.readByte());
      this.field_149526_d = var1.readBoolean();
      this.field_149527_e = EnumDifficulty.getDifficultyEnum(var1.readByte());
      this.field_149525_f = var1.readBoolean();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeStringToBuffer(this.field_149530_a);
      var1.writeByte(this.field_149528_b);
      var1.writeByte(this.field_149529_c.getChatVisibility());
      var1.writeBoolean(this.field_149526_d);
      var1.writeByte(this.field_149527_e.getDifficultyId());
      var1.writeBoolean(this.field_149525_f);
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processClientSettings(this);
   }

   public String func_149524_c() {
      return this.field_149530_a;
   }

   public int func_149521_d() {
      return this.field_149528_b;
   }

   public EntityPlayer$EnumChatVisibility func_149523_e() {
      return this.field_149529_c;
   }

   public boolean func_149520_f() {
      return this.field_149526_d;
   }

   public EnumDifficulty func_149518_g() {
      return this.field_149527_e;
   }

   public boolean func_149519_h() {
      return this.field_149525_f;
   }

   public String serialize() {
      return String.format("lang=\'%s\', view=%d, chat=%s, col=%b, difficulty=%s, cape=%b", new Object[]{this.field_149530_a, Integer.valueOf(this.field_149528_b), this.field_149529_c, Boolean.valueOf(this.field_149526_d), this.field_149527_e, Boolean.valueOf(this.field_149525_f)});
   }
}
