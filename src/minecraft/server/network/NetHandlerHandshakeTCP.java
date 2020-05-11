package net.minecraft.server.network;

import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.INetHandlerHandshakeServer;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.NetHandlerHandshakeTCP$SwitchEnumConnectionState;
import net.minecraft.server.network.NetHandlerLoginServer;
import net.minecraft.server.network.NetHandlerStatusServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class NetHandlerHandshakeTCP implements INetHandlerHandshakeServer {

   private final MinecraftServer field_147387_a;
   private final NetworkManager field_147386_b;


   public NetHandlerHandshakeTCP(MinecraftServer var1, NetworkManager var2) {
      this.field_147387_a = var1;
      this.field_147386_b = var2;
   }

   public void processHandshake(C00Handshake var1) {
      switch(NetHandlerHandshakeTCP$SwitchEnumConnectionState.field_151291_a[var1.func_149594_c().ordinal()]) {
      case 1:
         this.field_147386_b.setConnectionState(EnumConnectionState.LOGIN);
         ChatComponentText var2;
         if(var1.func_149595_d() > 5) {
            var2 = new ChatComponentText("Outdated server! I\'m still on 1.7.10");
            this.field_147386_b.scheduleOutboundPacket(new S00PacketDisconnect(var2), new GenericFutureListener[0]);
            this.field_147386_b.closeChannel(var2);
         } else if(var1.func_149595_d() < 5) {
            var2 = new ChatComponentText("Outdated client! Please use 1.7.10");
            this.field_147386_b.scheduleOutboundPacket(new S00PacketDisconnect(var2), new GenericFutureListener[0]);
            this.field_147386_b.closeChannel(var2);
         } else {
            this.field_147386_b.setNetHandler(new NetHandlerLoginServer(this.field_147387_a, this.field_147386_b));
         }
         break;
      case 2:
         this.field_147386_b.setConnectionState(EnumConnectionState.STATUS);
         this.field_147386_b.setNetHandler(new NetHandlerStatusServer(this.field_147387_a, this.field_147386_b));
         break;
      default:
         throw new UnsupportedOperationException("Invalid intention " + var1.func_149594_c());
      }

   }

   public void onDisconnect(IChatComponent var1) {}

   public void onConnectionStateTransition(EnumConnectionState var1, EnumConnectionState var2) {
      if(var2 != EnumConnectionState.LOGIN && var2 != EnumConnectionState.STATUS) {
         throw new UnsupportedOperationException("Invalid state " + var2);
      }
   }

   public void onNetworkTick() {}
}
