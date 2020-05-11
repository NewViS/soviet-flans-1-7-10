package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.WorldType;

public class S07PacketRespawn extends Packet {

   private int field_149088_a;
   private EnumDifficulty field_149086_b;
   private WorldSettings$GameType field_149087_c;
   private WorldType field_149085_d;


   public S07PacketRespawn() {}

   public S07PacketRespawn(int var1, EnumDifficulty var2, WorldType var3, WorldSettings$GameType var4) {
      this.field_149088_a = var1;
      this.field_149086_b = var2;
      this.field_149087_c = var4;
      this.field_149085_d = var3;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleRespawn(this);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149088_a = var1.readInt();
      this.field_149086_b = EnumDifficulty.getDifficultyEnum(var1.readUnsignedByte());
      this.field_149087_c = WorldSettings$GameType.getByID(var1.readUnsignedByte());
      this.field_149085_d = WorldType.parseWorldType(var1.readStringFromBuffer(16));
      if(this.field_149085_d == null) {
         this.field_149085_d = WorldType.DEFAULT;
      }

   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149088_a);
      var1.writeByte(this.field_149086_b.getDifficultyId());
      var1.writeByte(this.field_149087_c.getID());
      var1.writeStringToBuffer(this.field_149085_d.getWorldTypeName());
   }

   public int func_149082_c() {
      return this.field_149088_a;
   }

   public EnumDifficulty func_149081_d() {
      return this.field_149086_b;
   }

   public WorldSettings$GameType func_149083_e() {
      return this.field_149087_c;
   }

   public WorldType func_149080_f() {
      return this.field_149085_d;
   }
}
