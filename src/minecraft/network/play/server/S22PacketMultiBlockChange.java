package net.minecraft.network.play.server;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class S22PacketMultiBlockChange extends Packet {

   private static final Logger logger = LogManager.getLogger();
   private ChunkCoordIntPair field_148925_b;
   private byte[] field_148926_c;
   private int field_148924_d;


   public S22PacketMultiBlockChange() {}

   public S22PacketMultiBlockChange(int var1, short[] var2, Chunk var3) {
      this.field_148925_b = new ChunkCoordIntPair(var3.xPosition, var3.zPosition);
      this.field_148924_d = var1;
      int var4 = 4 * var1;

      try {
         ByteArrayOutputStream var5 = new ByteArrayOutputStream(var4);
         DataOutputStream var6 = new DataOutputStream(var5);

         for(int var7 = 0; var7 < var1; ++var7) {
            int var8 = var2[var7] >> 12 & 15;
            int var9 = var2[var7] >> 8 & 15;
            int var10 = var2[var7] & 255;
            var6.writeShort(var2[var7]);
            var6.writeShort((short)((Block.getIdFromBlock(var3.getBlock(var8, var10, var9)) & 4095) << 4 | var3.getBlockMetadata(var8, var10, var9) & 15));
         }

         this.field_148926_c = var5.toByteArray();
         if(this.field_148926_c.length != var4) {
            throw new RuntimeException("Expected length " + var4 + " doesn\'t match received length " + this.field_148926_c.length);
         }
      } catch (IOException var11) {
         logger.error("Couldn\'t create bulk block update packet", var11);
         this.field_148926_c = null;
      }

   }

   public void readPacketData(PacketBuffer var1) {
      this.field_148925_b = new ChunkCoordIntPair(var1.readInt(), var1.readInt());
      this.field_148924_d = var1.readShort() & '\uffff';
      int var2 = var1.readInt();
      if(var2 > 0) {
         this.field_148926_c = new byte[var2];
         var1.readBytes(this.field_148926_c);
      }

   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_148925_b.chunkXPos);
      var1.writeInt(this.field_148925_b.chunkZPos);
      var1.writeShort((short)this.field_148924_d);
      if(this.field_148926_c != null) {
         var1.writeInt(this.field_148926_c.length);
         var1.writeBytes(this.field_148926_c);
      } else {
         var1.writeInt(0);
      }

   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleMultiBlockChange(this);
   }

   public String serialize() {
      return String.format("xc=%d, zc=%d, count=%d", new Object[]{Integer.valueOf(this.field_148925_b.chunkXPos), Integer.valueOf(this.field_148925_b.chunkZPos), Integer.valueOf(this.field_148924_d)});
   }

   public ChunkCoordIntPair func_148920_c() {
      return this.field_148925_b;
   }

   public byte[] func_148921_d() {
      return this.field_148926_c;
   }

   public int func_148922_e() {
      return this.field_148924_d;
   }

}
