package net.minecraft.client.network;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import javax.crypto.SecretKey;
import net.minecraft.client.network.NetHandlerLoginClient;

class NetHandlerLoginClient$1 implements GenericFutureListener {

   // $FF: synthetic field
   final SecretKey field_147495_a;
   // $FF: synthetic field
   final NetHandlerLoginClient field_147494_b;


   NetHandlerLoginClient$1(NetHandlerLoginClient var1, SecretKey var2) {
      this.field_147494_b = var1;
      this.field_147495_a = var2;
   }

   public void operationComplete(Future var1) {
      NetHandlerLoginClient.access$000(this.field_147494_b).enableEncryption(this.field_147495_a);
   }
}
