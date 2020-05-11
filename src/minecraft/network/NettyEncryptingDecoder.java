package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import javax.crypto.Cipher;
import net.minecraft.network.NettyEncryptionTranslator;

public class NettyEncryptingDecoder extends MessageToMessageDecoder {

   private final NettyEncryptionTranslator field_150509_a;


   public NettyEncryptingDecoder(Cipher var1) {
      this.field_150509_a = new NettyEncryptionTranslator(var1);
   }

   protected void decode(ChannelHandlerContext var1, ByteBuf var2, List var3) {
      var3.add(this.field_150509_a.func_150503_a(var1, var2));
   }

   // $FF: synthetic method
   protected void decode(ChannelHandlerContext var1, Object var2, List var3) {
      this.decode(var1, (ByteBuf)var2, var3);
   }
}
