package net.minecraft.network.login.client;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginServer;

public class C00PacketLoginStart extends Packet {

   private GameProfile field_149305_a;


   public C00PacketLoginStart() {}

   public C00PacketLoginStart(GameProfile var1) {
      this.field_149305_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149305_a = new GameProfile((UUID)null, var1.readStringFromBuffer(16));
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeStringToBuffer(this.field_149305_a.getName());
   }

   public void processPacket(INetHandlerLoginServer var1) {
      var1.processLoginStart(this);
   }

   public GameProfile func_149304_c() {
      return this.field_149305_a;
   }
}
