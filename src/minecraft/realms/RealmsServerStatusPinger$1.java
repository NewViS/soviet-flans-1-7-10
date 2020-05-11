package net.minecraft.realms;

import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.realms.Realms;
import net.minecraft.realms.RealmsServerStatusPinger;
import net.minecraft.realms.ServerPing;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class RealmsServerStatusPinger$1 implements INetHandlerStatusClient {

   private boolean field_154345_e;
   // $FF: synthetic field
   final ServerPing field_154341_a;
   // $FF: synthetic field
   final NetworkManager field_154342_b;
   // $FF: synthetic field
   final String field_154343_c;
   // $FF: synthetic field
   final RealmsServerStatusPinger field_154344_d;


   public RealmsServerStatusPinger$1(RealmsServerStatusPinger var1, ServerPing var2, NetworkManager var3, String var4) {
      this.field_154344_d = var1;
      this.field_154341_a = var2;
      this.field_154342_b = var3;
      this.field_154343_c = var4;
      this.field_154345_e = false;
   }

   public void handleServerInfo(S00PacketServerInfo var1) {
      ServerStatusResponse var2 = var1.func_149294_c();
      if(var2.func_151318_b() != null) {
         this.field_154341_a.nrOfPlayers = String.valueOf(var2.func_151318_b().func_151333_b());
      }

      this.field_154342_b.scheduleOutboundPacket(new C01PacketPing(Realms.currentTimeMillis()), new GenericFutureListener[0]);
      this.field_154345_e = true;
   }

   public void handlePong(S01PacketPong var1) {
      this.field_154342_b.closeChannel(new ChatComponentText("Finished"));
   }

   public void onDisconnect(IChatComponent var1) {
      if(!this.field_154345_e) {
         RealmsServerStatusPinger.access$000().error("Can\'t ping " + this.field_154343_c + ": " + var1.getUnformattedText());
      }

   }

   public void onConnectionStateTransition(EnumConnectionState var1, EnumConnectionState var2) {
      if(var2 != EnumConnectionState.STATUS) {
         throw new UnsupportedOperationException("Unexpected change in protocol to " + var2);
      }
   }

   public void onNetworkTick() {}
}
