package net.minecraft.client.network;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterables;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.client.network.OldServerPinger;
import net.minecraft.client.network.OldServerPinger$2;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

class OldServerPinger$2$1 extends SimpleChannelInboundHandler {

   // $FF: synthetic field
   final OldServerPinger$2 field_147220_a;


   OldServerPinger$2$1(OldServerPinger$2 var1) {
      this.field_147220_a = var1;
   }

   public void channelActive(ChannelHandlerContext var1) {
      super.channelActive(var1);
      ByteBuf var2 = Unpooled.buffer();

      try {
         var2.writeByte(254);
         var2.writeByte(1);
         var2.writeByte(250);
         char[] var3 = "MC|PingHost".toCharArray();
         var2.writeShort(var3.length);
         char[] var4 = var3;
         int var5 = var3.length;

         int var6;
         char var7;
         for(var6 = 0; var6 < var5; ++var6) {
            var7 = var4[var6];
            var2.writeChar(var7);
         }

         var2.writeShort(7 + 2 * this.field_147220_a.field_147218_a.getIP().length());
         var2.writeByte(127);
         var3 = this.field_147220_a.field_147218_a.getIP().toCharArray();
         var2.writeShort(var3.length);
         var4 = var3;
         var5 = var3.length;

         for(var6 = 0; var6 < var5; ++var6) {
            var7 = var4[var6];
            var2.writeChar(var7);
         }

         var2.writeInt(this.field_147220_a.field_147218_a.getPort());
         var1.channel().writeAndFlush(var2).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
      } finally {
         var2.release();
      }
   }

   protected void channelRead0(ChannelHandlerContext var1, ByteBuf var2) {
      short var3 = var2.readUnsignedByte();
      if(var3 == 255) {
         String var4 = new String(var2.readBytes(var2.readShort() * 2).array(), Charsets.UTF_16BE);
         String[] var5 = (String[])Iterables.toArray(OldServerPinger.access$200().split(var4), String.class);
         if("§1".equals(var5[0])) {
            int var6 = MathHelper.parseIntWithDefault(var5[1], 0);
            String var7 = var5[2];
            String var8 = var5[3];
            int var9 = MathHelper.parseIntWithDefault(var5[4], -1);
            int var10 = MathHelper.parseIntWithDefault(var5[5], -1);
            this.field_147220_a.field_147216_b.field_82821_f = -1;
            this.field_147220_a.field_147216_b.gameVersion = var7;
            this.field_147220_a.field_147216_b.serverMOTD = var8;
            this.field_147220_a.field_147216_b.populationInfo = EnumChatFormatting.GRAY + "" + var9 + "" + EnumChatFormatting.DARK_GRAY + "/" + EnumChatFormatting.GRAY + var10;
         }
      }

      var1.close();
   }

   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      var1.close();
   }

   // $FF: synthetic method
   protected void channelRead0(ChannelHandlerContext var1, Object var2) {
      this.channelRead0(var1, (ByteBuf)var2);
   }
}
