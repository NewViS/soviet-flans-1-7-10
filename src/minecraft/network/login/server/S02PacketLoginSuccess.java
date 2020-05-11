package net.minecraft.network.login.server;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;

public class S02PacketLoginSuccess extends Packet {

   private GameProfile field_149602_a;


   public S02PacketLoginSuccess() {}

   public S02PacketLoginSuccess(GameProfile var1) {
      this.field_149602_a = var1;
   }

   public void readPacketData(PacketBuffer var1) {
      String var2 = var1.readStringFromBuffer(36);
      String var3 = var1.readStringFromBuffer(16);
      UUID var4 = UUID.fromString(var2);
      this.field_149602_a = new GameProfile(var4, var3);
   }

   public void writePacketData(PacketBuffer var1) {
      UUID var2 = this.field_149602_a.getId();
      var1.writeStringToBuffer(var2 == null?"":var2.toString());
      var1.writeStringToBuffer(this.field_149602_a.getName());
   }

   public void processPacket(INetHandlerLoginClient var1) {
      var1.handleLoginSuccess(this);
   }

   public boolean hasPriority() {
      return true;
   }
}
