package net.minecraft.network;

import com.google.common.collect.BiMap;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Packet {

   private static final Logger logger = LogManager.getLogger();


   public static Packet generatePacket(BiMap var0, int var1) {
      try {
         Class var2 = (Class)var0.get(Integer.valueOf(var1));
         return var2 == null?null:(Packet)var2.newInstance();
      } catch (Exception var3) {
         logger.error("Couldn\'t create packet " + var1, var3);
         return null;
      }
   }

   public static void writeBlob(ByteBuf var0, byte[] var1) {
      var0.writeShort(var1.length);
      var0.writeBytes(var1);
   }

   public static byte[] readBlob(ByteBuf var0) {
      short var1 = var0.readShort();
      if(var1 < 0) {
         throw new IOException("Key was smaller than nothing!  Weird key!");
      } else {
         byte[] var2 = new byte[var1];
         var0.readBytes(var2);
         return var2;
      }
   }

   public abstract void readPacketData(PacketBuffer var1);

   public abstract void writePacketData(PacketBuffer var1);

   public abstract void processPacket(INetHandler var1);

   public boolean hasPriority() {
      return false;
   }

   public String toString() {
      return this.getClass().getSimpleName();
   }

   public String serialize() {
      return "";
   }

}
