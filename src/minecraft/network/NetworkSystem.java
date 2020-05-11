package net.minecraft.network;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkSystem$1;
import net.minecraft.network.NetworkSystem$2;
import net.minecraft.network.NetworkSystem$3;
import net.minecraft.network.NetworkSystem$4;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ReportedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetworkSystem {

   private static final Logger logger = LogManager.getLogger();
   private static final NioEventLoopGroup eventLoops = new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty IO #%d").setDaemon(true).build());
   private final MinecraftServer mcServer;
   public volatile boolean isAlive;
   private final List endpoints = Collections.synchronizedList(new ArrayList());
   private final List networkManagers = Collections.synchronizedList(new ArrayList());


   public NetworkSystem(MinecraftServer var1) {
      this.mcServer = var1;
      this.isAlive = true;
   }

   public void addLanEndpoint(InetAddress var1, int var2) {
      List var3 = this.endpoints;
      synchronized(this.endpoints) {
         this.endpoints.add(((ServerBootstrap)((ServerBootstrap)(new ServerBootstrap()).channel(NioServerSocketChannel.class)).childHandler(new NetworkSystem$1(this)).group(eventLoops).localAddress(var1, var2)).bind().syncUninterruptibly());
      }
   }

   public SocketAddress addLocalEndpoint() {
      List var2 = this.endpoints;
      ChannelFuture var1;
      synchronized(this.endpoints) {
         var1 = ((ServerBootstrap)((ServerBootstrap)(new ServerBootstrap()).channel(LocalServerChannel.class)).childHandler(new NetworkSystem$2(this)).group(eventLoops).localAddress(LocalAddress.ANY)).bind().syncUninterruptibly();
         this.endpoints.add(var1);
      }

      return var1.channel().localAddress();
   }

   public void terminateEndpoints() {
      this.isAlive = false;
      Iterator var1 = this.endpoints.iterator();

      while(var1.hasNext()) {
         ChannelFuture var2 = (ChannelFuture)var1.next();
         var2.channel().close().syncUninterruptibly();
      }

   }

   public void networkTick() {
      List var1 = this.networkManagers;
      synchronized(this.networkManagers) {
         Iterator var2 = this.networkManagers.iterator();

         while(var2.hasNext()) {
            NetworkManager var3 = (NetworkManager)var2.next();
            if(!var3.isChannelOpen()) {
               var2.remove();
               if(var3.getExitMessage() != null) {
                  var3.getNetHandler().onDisconnect(var3.getExitMessage());
               } else if(var3.getNetHandler() != null) {
                  var3.getNetHandler().onDisconnect(new ChatComponentText("Disconnected"));
               }
            } else {
               try {
                  var3.processReceivedPackets();
               } catch (Exception var8) {
                  if(var3.isLocalChannel()) {
                     CrashReport var10 = CrashReport.makeCrashReport(var8, "Ticking memory connection");
                     CrashReportCategory var6 = var10.makeCategory("Ticking connection");
                     var6.addCrashSectionCallable("Connection", new NetworkSystem$3(this, var3));
                     throw new ReportedException(var10);
                  }

                  logger.warn("Failed to handle packet for " + var3.getSocketAddress(), var8);
                  ChatComponentText var5 = new ChatComponentText("Internal server error");
                  var3.scheduleOutboundPacket(new S40PacketDisconnect(var5), new GenericFutureListener[]{new NetworkSystem$4(this, var3, var5)});
                  var3.disableAutoRead();
               }
            }
         }

      }
   }

   public MinecraftServer func_151267_d() {
      return this.mcServer;
   }

   // $FF: synthetic method
   static List access$000(NetworkSystem var0) {
      return var0.networkManagers;
   }

   // $FF: synthetic method
   static MinecraftServer access$100(NetworkSystem var0) {
      return var0.mcServer;
   }

}
