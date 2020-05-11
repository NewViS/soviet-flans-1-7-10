package net.minecraft.util;

import com.google.common.collect.BiMap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.IOException;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkStatistics;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class MessageSerializer extends MessageToByteEncoder {

   private static final Logger logger = LogManager.getLogger();
   private static final Marker field_150797_b = MarkerManager.getMarker("PACKET_SENT", NetworkManager.logMarkerPackets);
   private final NetworkStatistics field_152500_c;


   public MessageSerializer(NetworkStatistics var1) {
      this.field_152500_c = var1;
   }

   protected void encode(ChannelHandlerContext var1, Packet var2, ByteBuf var3) {
      Integer var4 = (Integer)((BiMap)var1.channel().attr(NetworkManager.attrKeySendable).get()).inverse().get(var2.getClass());
      if(logger.isDebugEnabled()) {
         logger.debug(field_150797_b, "OUT: [{}:{}] {}[{}]", new Object[]{var1.channel().attr(NetworkManager.attrKeyConnectionState).get(), var4, var2.getClass().getName(), var2.serialize()});
      }

      if(var4 == null) {
         throw new IOException("Can\'t serialize unregistered packet");
      } else {
         PacketBuffer var5 = new PacketBuffer(var3);
         var5.writeVarIntToBuffer(var4.intValue());
         var2.writePacketData(var5);
         this.field_152500_c.func_152464_b(var4.intValue(), (long)var5.readableBytes());
      }
   }

   // $FF: synthetic method
   protected void encode(ChannelHandlerContext var1, Object var2, ByteBuf var3) {
      this.encode(var1, (Packet)var2, var3);
   }

}
