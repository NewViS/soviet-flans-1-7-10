package net.minecraft.network;

import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.Packet;

class NetworkManager$InboundHandlerTuplePacketListener {

   private final Packet field_150774_a;
   private final GenericFutureListener[] field_150773_b;


   public NetworkManager$InboundHandlerTuplePacketListener(Packet var1, GenericFutureListener ... var2) {
      this.field_150774_a = var1;
      this.field_150773_b = var2;
   }

   // $FF: synthetic method
   static Packet access$100(NetworkManager$InboundHandlerTuplePacketListener var0) {
      return var0.field_150774_a;
   }

   // $FF: synthetic method
   static GenericFutureListener[] access$200(NetworkManager$InboundHandlerTuplePacketListener var0) {
      return var0.field_150773_b;
   }
}
