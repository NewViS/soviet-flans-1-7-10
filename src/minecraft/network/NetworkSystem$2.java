package net.minecraft.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import net.minecraft.client.network.NetHandlerHandshakeMemory;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkSystem;

class NetworkSystem$2 extends ChannelInitializer {

   // $FF: synthetic field
   final NetworkSystem field_151281_a;


   NetworkSystem$2(NetworkSystem var1) {
      this.field_151281_a = var1;
   }

   protected void initChannel(Channel var1) {
      NetworkManager var2 = new NetworkManager(false);
      var2.setNetHandler(new NetHandlerHandshakeMemory(NetworkSystem.access$100(this.field_151281_a), var2));
      NetworkSystem.access$000(this.field_151281_a).add(var2);
      var1.pipeline().addLast("packet_handler", var2);
   }
}
