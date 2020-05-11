package net.minecraft.network;

import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;

class NetworkManager$1 implements Runnable {

   // $FF: synthetic field
   final EnumConnectionState field_150717_a;
   // $FF: synthetic field
   final EnumConnectionState field_150715_b;
   // $FF: synthetic field
   final Packet field_150716_c;
   // $FF: synthetic field
   final GenericFutureListener[] field_150713_d;
   // $FF: synthetic field
   final NetworkManager field_150714_e;


   NetworkManager$1(NetworkManager var1, EnumConnectionState var2, EnumConnectionState var3, Packet var4, GenericFutureListener[] var5) {
      this.field_150714_e = var1;
      this.field_150717_a = var2;
      this.field_150715_b = var3;
      this.field_150716_c = var4;
      this.field_150713_d = var5;
   }

   public void run() {
      if(this.field_150717_a != this.field_150715_b) {
         this.field_150714_e.setConnectionState(this.field_150717_a);
      }

      NetworkManager.access$000(this.field_150714_e).writeAndFlush(this.field_150716_c).addListeners(this.field_150713_d).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
   }
}
