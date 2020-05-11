package net.minecraft.network;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.ChatComponentText;

class NetHandlerPlayServer$1 implements GenericFutureListener {

   // $FF: synthetic field
   final ChatComponentText field_151289_a;
   // $FF: synthetic field
   final NetHandlerPlayServer field_151288_b;


   NetHandlerPlayServer$1(NetHandlerPlayServer var1, ChatComponentText var2) {
      this.field_151288_b = var1;
      this.field_151289_a = var2;
   }

   public void operationComplete(Future var1) {
      this.field_151288_b.netManager.closeChannel(this.field_151289_a);
   }
}
