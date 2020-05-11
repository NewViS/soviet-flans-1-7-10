package net.minecraft.client.network;

import com.google.common.base.Splitter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.OldServerPinger$1;
import net.minecraft.client.network.OldServerPinger$2;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.util.ChatComponentText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OldServerPinger {

   private static final Splitter field_147230_a = Splitter.on('\u0000').limit(6);
   private static final Logger logger = LogManager.getLogger();
   private final List field_147229_c = Collections.synchronizedList(new ArrayList());


   public void func_147224_a(ServerData var1) {
      ServerAddress var2 = ServerAddress.func_78860_a(var1.serverIP);
      NetworkManager var3 = NetworkManager.provideLanClient(InetAddress.getByName(var2.getIP()), var2.getPort());
      this.field_147229_c.add(var3);
      var1.serverMOTD = "Pinging...";
      var1.pingToServer = -1L;
      var1.field_147412_i = null;
      var3.setNetHandler(new OldServerPinger$1(this, var1, var3));

      try {
         var3.scheduleOutboundPacket(new C00Handshake(5, var2.getIP(), var2.getPort(), EnumConnectionState.STATUS), new GenericFutureListener[0]);
         var3.scheduleOutboundPacket(new C00PacketServerQuery(), new GenericFutureListener[0]);
      } catch (Throwable var5) {
         logger.error(var5);
      }

   }

   private void func_147225_b(ServerData var1) {
      ServerAddress var2 = ServerAddress.func_78860_a(var1.serverIP);
      ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group(NetworkManager.eventLoops)).handler(new OldServerPinger$2(this, var2, var1))).channel(NioSocketChannel.class)).connect(var2.getIP(), var2.getPort());
   }

   public void func_147223_a() {
      List var1 = this.field_147229_c;
      synchronized(this.field_147229_c) {
         Iterator var2 = this.field_147229_c.iterator();

         while(var2.hasNext()) {
            NetworkManager var3 = (NetworkManager)var2.next();
            if(var3.isChannelOpen()) {
               var3.processReceivedPackets();
            } else {
               var2.remove();
               if(var3.getExitMessage() != null) {
                  var3.getNetHandler().onDisconnect(var3.getExitMessage());
               }
            }
         }

      }
   }

   public void func_147226_b() {
      List var1 = this.field_147229_c;
      synchronized(this.field_147229_c) {
         Iterator var2 = this.field_147229_c.iterator();

         while(var2.hasNext()) {
            NetworkManager var3 = (NetworkManager)var2.next();
            if(var3.isChannelOpen()) {
               var2.remove();
               var3.closeChannel(new ChatComponentText("Cancelled"));
            }
         }

      }
   }

   // $FF: synthetic method
   static Logger access$000() {
      return logger;
   }

   // $FF: synthetic method
   static void access$100(OldServerPinger var0, ServerData var1) {
      var0.func_147225_b(var1);
   }

   // $FF: synthetic method
   static Splitter access$200() {
      return field_147230_a;
   }

}
