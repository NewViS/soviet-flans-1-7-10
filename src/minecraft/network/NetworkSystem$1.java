package net.minecraft.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkSystem;
import net.minecraft.network.PingResponseHandler;
import net.minecraft.server.network.NetHandlerHandshakeTCP;
import net.minecraft.util.MessageDeserializer;
import net.minecraft.util.MessageDeserializer2;
import net.minecraft.util.MessageSerializer;
import net.minecraft.util.MessageSerializer2;

class NetworkSystem$1 extends ChannelInitializer {

   // $FF: synthetic field
   final NetworkSystem field_151264_a;


   NetworkSystem$1(NetworkSystem var1) {
      this.field_151264_a = var1;
   }

   protected void initChannel(Channel var1) {
      try {
         var1.config().setOption(ChannelOption.IP_TOS, Integer.valueOf(24));
      } catch (ChannelException var4) {
         ;
      }

      try {
         var1.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(false));
      } catch (ChannelException var3) {
         ;
      }

      var1.pipeline().addLast("timeout", new ReadTimeoutHandler(30)).addLast("legacy_query", new PingResponseHandler(this.field_151264_a)).addLast("splitter", new MessageDeserializer2()).addLast("decoder", new MessageDeserializer(NetworkManager.field_152462_h)).addLast("prepender", new MessageSerializer2()).addLast("encoder", new MessageSerializer(NetworkManager.field_152462_h));
      NetworkManager var2 = new NetworkManager(false);
      NetworkSystem.access$000(this.field_151264_a).add(var2);
      var1.pipeline().addLast("packet_handler", var2);
      var2.setNetHandler(new NetHandlerHandshakeTCP(NetworkSystem.access$100(this.field_151264_a), var2));
   }
}
