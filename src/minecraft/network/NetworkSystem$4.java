package net.minecraft.network;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkSystem;
import net.minecraft.util.ChatComponentText;

class NetworkSystem$4 implements GenericFutureListener {

   // $FF: synthetic field
   final NetworkManager field_151284_a;
   // $FF: synthetic field
   final ChatComponentText field_151282_b;
   // $FF: synthetic field
   final NetworkSystem field_151283_c;


   NetworkSystem$4(NetworkSystem var1, NetworkManager var2, ChatComponentText var3) {
      this.field_151283_c = var1;
      this.field_151284_a = var2;
      this.field_151282_b = var3;
   }

   public void operationComplete(Future var1) {
      this.field_151284_a.closeChannel(this.field_151282_b);
   }
}
