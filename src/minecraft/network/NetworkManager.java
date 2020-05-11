package net.minecraft.network;

import com.google.common.collect.Queues;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.TimeoutException;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Queue;
import javax.crypto.SecretKey;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NettyEncryptingDecoder;
import net.minecraft.network.NettyEncryptingEncoder;
import net.minecraft.network.NetworkManager$1;
import net.minecraft.network.NetworkManager$2;
import net.minecraft.network.NetworkManager$3;
import net.minecraft.network.NetworkManager$InboundHandlerTuplePacketListener;
import net.minecraft.network.NetworkStatistics;
import net.minecraft.network.Packet;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.CryptManager;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class NetworkManager extends SimpleChannelInboundHandler {

   private static final Logger logger = LogManager.getLogger();
   public static final Marker logMarkerNetwork = MarkerManager.getMarker("NETWORK");
   public static final Marker logMarkerPackets = MarkerManager.getMarker("NETWORK_PACKETS", logMarkerNetwork);
   public static final Marker field_152461_c = MarkerManager.getMarker("NETWORK_STAT", logMarkerNetwork);
   public static final AttributeKey attrKeyConnectionState = new AttributeKey("protocol");
   public static final AttributeKey attrKeyReceivable = new AttributeKey("receivable_packets");
   public static final AttributeKey attrKeySendable = new AttributeKey("sendable_packets");
   public static final NioEventLoopGroup eventLoops = new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Client IO #%d").setDaemon(true).build());
   public static final NetworkStatistics field_152462_h = new NetworkStatistics();
   private final boolean isClientSide;
   private final Queue receivedPacketsQueue = Queues.newConcurrentLinkedQueue();
   private final Queue outboundPacketsQueue = Queues.newConcurrentLinkedQueue();
   private Channel channel;
   private SocketAddress socketAddress;
   private INetHandler netHandler;
   private EnumConnectionState connectionState;
   private IChatComponent terminationReason;
   private boolean field_152463_r;


   public NetworkManager(boolean var1) {
      this.isClientSide = var1;
   }

   public void channelActive(ChannelHandlerContext var1) {
      super.channelActive(var1);
      this.channel = var1.channel();
      this.socketAddress = this.channel.remoteAddress();
      this.setConnectionState(EnumConnectionState.HANDSHAKING);
   }

   public void setConnectionState(EnumConnectionState var1) {
      this.connectionState = (EnumConnectionState)this.channel.attr(attrKeyConnectionState).getAndSet(var1);
      this.channel.attr(attrKeyReceivable).set(var1.func_150757_a(this.isClientSide));
      this.channel.attr(attrKeySendable).set(var1.func_150754_b(this.isClientSide));
      this.channel.config().setAutoRead(true);
      logger.debug("Enabled auto read");
   }

   public void channelInactive(ChannelHandlerContext var1) {
      this.closeChannel(new ChatComponentTranslation("disconnect.endOfStream", new Object[0]));
   }

   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      ChatComponentTranslation var3;
      if(var2 instanceof TimeoutException) {
         var3 = new ChatComponentTranslation("disconnect.timeout", new Object[0]);
      } else {
         var3 = new ChatComponentTranslation("disconnect.genericReason", new Object[]{"Internal Exception: " + var2});
      }

      this.closeChannel(var3);
   }

   protected void channelRead0(ChannelHandlerContext var1, Packet var2) {
      if(this.channel.isOpen()) {
         if(var2.hasPriority()) {
            var2.processPacket(this.netHandler);
         } else {
            this.receivedPacketsQueue.add(var2);
         }
      }

   }

   public void setNetHandler(INetHandler var1) {
      Validate.notNull(var1, "packetListener", new Object[0]);
      logger.debug("Set listener of {} to {}", new Object[]{this, var1});
      this.netHandler = var1;
   }

   public void scheduleOutboundPacket(Packet var1, GenericFutureListener ... var2) {
      if(this.channel != null && this.channel.isOpen()) {
         this.flushOutboundQueue();
         this.dispatchPacket(var1, var2);
      } else {
         this.outboundPacketsQueue.add(new NetworkManager$InboundHandlerTuplePacketListener(var1, var2));
      }

   }

   private void dispatchPacket(Packet var1, GenericFutureListener[] var2) {
      EnumConnectionState var3 = EnumConnectionState.func_150752_a(var1);
      EnumConnectionState var4 = (EnumConnectionState)this.channel.attr(attrKeyConnectionState).get();
      if(var4 != var3) {
         logger.debug("Disabled auto read");
         this.channel.config().setAutoRead(false);
      }

      if(this.channel.eventLoop().inEventLoop()) {
         if(var3 != var4) {
            this.setConnectionState(var3);
         }

         this.channel.writeAndFlush(var1).addListeners(var2).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
      } else {
         this.channel.eventLoop().execute(new NetworkManager$1(this, var3, var4, var1, var2));
      }

   }

   private void flushOutboundQueue() {
      if(this.channel != null && this.channel.isOpen()) {
         while(!this.outboundPacketsQueue.isEmpty()) {
            NetworkManager$InboundHandlerTuplePacketListener var1 = (NetworkManager$InboundHandlerTuplePacketListener)this.outboundPacketsQueue.poll();
            this.dispatchPacket(NetworkManager$InboundHandlerTuplePacketListener.access$100(var1), NetworkManager$InboundHandlerTuplePacketListener.access$200(var1));
         }

      }
   }

   public void processReceivedPackets() {
      this.flushOutboundQueue();
      EnumConnectionState var1 = (EnumConnectionState)this.channel.attr(attrKeyConnectionState).get();
      if(this.connectionState != var1) {
         if(this.connectionState != null) {
            this.netHandler.onConnectionStateTransition(this.connectionState, var1);
         }

         this.connectionState = var1;
      }

      if(this.netHandler != null) {
         for(int var2 = 1000; !this.receivedPacketsQueue.isEmpty() && var2 >= 0; --var2) {
            Packet var3 = (Packet)this.receivedPacketsQueue.poll();
            var3.processPacket(this.netHandler);
         }

         this.netHandler.onNetworkTick();
      }

      this.channel.flush();
   }

   public SocketAddress getSocketAddress() {
      return this.socketAddress;
   }

   public void closeChannel(IChatComponent var1) {
      if(this.channel.isOpen()) {
         this.channel.close();
         this.terminationReason = var1;
      }

   }

   public boolean isLocalChannel() {
      return this.channel instanceof LocalChannel || this.channel instanceof LocalServerChannel;
   }

   public static NetworkManager provideLanClient(InetAddress var0, int var1) {
      NetworkManager var2 = new NetworkManager(true);
      ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group(eventLoops)).handler(new NetworkManager$2(var2))).channel(NioSocketChannel.class)).connect(var0, var1).syncUninterruptibly();
      return var2;
   }

   public static NetworkManager provideLocalClient(SocketAddress var0) {
      NetworkManager var1 = new NetworkManager(true);
      ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group(eventLoops)).handler(new NetworkManager$3(var1))).channel(LocalChannel.class)).connect(var0).syncUninterruptibly();
      return var1;
   }

   public void enableEncryption(SecretKey var1) {
      this.channel.pipeline().addBefore("splitter", "decrypt", new NettyEncryptingDecoder(CryptManager.func_151229_a(2, var1)));
      this.channel.pipeline().addBefore("prepender", "encrypt", new NettyEncryptingEncoder(CryptManager.func_151229_a(1, var1)));
      this.field_152463_r = true;
   }

   public boolean isChannelOpen() {
      return this.channel != null && this.channel.isOpen();
   }

   public INetHandler getNetHandler() {
      return this.netHandler;
   }

   public IChatComponent getExitMessage() {
      return this.terminationReason;
   }

   public void disableAutoRead() {
      this.channel.config().setAutoRead(false);
   }

   // $FF: synthetic method
   protected void channelRead0(ChannelHandlerContext var1, Object var2) {
      this.channelRead0(var1, (Packet)var2);
   }

   // $FF: synthetic method
   static Channel access$000(NetworkManager var0) {
      return var0.channel;
   }

}
