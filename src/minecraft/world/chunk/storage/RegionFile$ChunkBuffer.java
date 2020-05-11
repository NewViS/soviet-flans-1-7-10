package net.minecraft.world.chunk.storage;

import java.io.ByteArrayOutputStream;
import net.minecraft.world.chunk.storage.RegionFile;

class RegionFile$ChunkBuffer extends ByteArrayOutputStream {

   private int chunkX;
   private int chunkZ;
   // $FF: synthetic field
   final RegionFile regionFile;


   public RegionFile$ChunkBuffer(RegionFile var1, int var2, int var3) {
      super(8096);
      this.regionFile = var1;
      this.chunkX = var2;
      this.chunkZ = var3;
   }

   public void close() {
      this.regionFile.write(this.chunkX, this.chunkZ, this.buf, this.count);
   }
}
