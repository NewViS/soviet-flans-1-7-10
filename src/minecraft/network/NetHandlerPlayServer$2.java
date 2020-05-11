package net.minecraft.network;

import java.util.concurrent.Callable;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;

class NetHandlerPlayServer$2 implements Callable {

   // $FF: synthetic field
   final Packet field_151287_a;
   // $FF: synthetic field
   final NetHandlerPlayServer field_151286_b;


   NetHandlerPlayServer$2(NetHandlerPlayServer var1, Packet var2) {
      this.field_151286_b = var1;
      this.field_151287_a = var2;
   }

   public String call() {
      return this.field_151287_a.getClass().getCanonicalName();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
