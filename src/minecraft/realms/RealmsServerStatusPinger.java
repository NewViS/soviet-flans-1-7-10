package net.minecraft.realms;

import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.realms.RealmsServerAddress;
import net.minecraft.realms.RealmsServerStatusPinger$1;
import net.minecraft.realms.RealmsSharedConstants;
import net.minecraft.realms.ServerPing;
import net.minecraft.util.ChatComponentText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsServerStatusPinger {

   private static final Logger LOGGER = LogManager.getLogger();
   private final List connections = Collections.synchronizedList(new ArrayList());


   public void pingServer(String var1, ServerPing var2) {
      if(var1 != null && !var1.startsWith("0.0.0.0") && !var1.isEmpty()) {
         RealmsServerAddress var3 = RealmsServerAddress.parseString(var1);
         NetworkManager var4 = NetworkManager.provideLanClient(InetAddress.getByName(var3.getHost()), var3.getPort());
         this.connections.add(var4);
         var4.setNetHandler(new RealmsServerStatusPinger$1(this, var2, var4, var1));

         try {
            var4.scheduleOutboundPacket(new C00Handshake(RealmsSharedConstants.NETWORK_PROTOCOL_VERSION, var3.getHost(), var3.getPort(), EnumConnectionState.STATUS), new GenericFutureListener[0]);
            var4.scheduleOutboundPacket(new C00PacketServerQuery(), new GenericFutureListener[0]);
         } catch (Throwable var6) {
            LOGGER.error(var6);
         }

      }
   }

   public void tick() {
      List var1 = this.connections;
      synchronized(this.connections) {
         Iterator var2 = this.connections.iterator();

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

   public void removeAll() {
      List var1 = this.connections;
      synchronized(this.connections) {
         Iterator var2 = this.connections.iterator();

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
   public static Logger access$000() {
      return LOGGER;
   }

}
