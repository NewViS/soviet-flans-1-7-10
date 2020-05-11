package net.minecraft.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import net.minecraft.network.NetworkManager;

final class NetworkManager$3 extends ChannelInitializer {

   // $FF: synthetic field
   final NetworkManager field_150778_a;


   NetworkManager$3(NetworkManager var1) {
      this.field_150778_a = var1;
   }

   protected void initChannel(Channel var1) {
      var1.pipeline().addLast("packet_handler", this.field_150778_a);
   }
}
