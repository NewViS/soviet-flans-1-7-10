package net.minecraft.client.network;

import net.minecraft.client.network.NetHandlerHandshakeMemory$SwitchEnumConnectionState;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.INetHandlerHandshakeServer;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.NetHandlerLoginServer;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.Validate;

public class NetHandlerHandshakeMemory implements INetHandlerHandshakeServer {

   private final MinecraftServer field_147385_a;
   private final NetworkManager field_147384_b;


   public NetHandlerHandshakeMemory(MinecraftServer var1, NetworkManager var2) {
      this.field_147385_a = var1;
      this.field_147384_b = var2;
   }

   public void processHandshake(C00Handshake var1) {
      this.field_147384_b.setConnectionState(var1.func_149594_c());
   }

   public void onDisconnect(IChatComponent var1) {}

   public void onConnectionStateTransition(EnumConnectionState var1, EnumConnectionState var2) {
      Validate.validState(var2 == EnumConnectionState.LOGIN || var2 == EnumConnectionState.STATUS, "Unexpected protocol " + var2, new Object[0]);
      switch(NetHandlerHandshakeMemory$SwitchEnumConnectionState.field_151263_a[var2.ordinal()]) {
      case 1:
         this.field_147384_b.setNetHandler(new NetHandlerLoginServer(this.field_147385_a, this.field_147384_b));
      case 2:
         throw new UnsupportedOperationException("NYI");
      default:
      }
   }

   public void onNetworkTick() {}
}
