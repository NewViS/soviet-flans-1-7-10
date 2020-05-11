package net.minecraft.server.network;

import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.status.INetHandlerStatusServer;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IChatComponent;

public class NetHandlerStatusServer implements INetHandlerStatusServer {

   private final MinecraftServer field_147314_a;
   private final NetworkManager field_147313_b;


   public NetHandlerStatusServer(MinecraftServer var1, NetworkManager var2) {
      this.field_147314_a = var1;
      this.field_147313_b = var2;
   }

   public void onDisconnect(IChatComponent var1) {}

   public void onConnectionStateTransition(EnumConnectionState var1, EnumConnectionState var2) {
      if(var2 != EnumConnectionState.STATUS) {
         throw new UnsupportedOperationException("Unexpected change in protocol to " + var2);
      }
   }

   public void onNetworkTick() {}

   public void processServerQuery(C00PacketServerQuery var1) {
      this.field_147313_b.scheduleOutboundPacket(new S00PacketServerInfo(this.field_147314_a.func_147134_at()), new GenericFutureListener[0]);
   }

   public void processPing(C01PacketPing var1) {
      this.field_147313_b.scheduleOutboundPacket(new S01PacketPong(var1.func_149289_c()), new GenericFutureListener[0]);
   }
}
