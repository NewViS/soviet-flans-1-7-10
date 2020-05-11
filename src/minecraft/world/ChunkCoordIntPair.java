package net.minecraft.world;

import net.minecraft.world.ChunkPosition;

public class ChunkCoordIntPair {

   public final int chunkXPos;
   public final int chunkZPos;


   public ChunkCoordIntPair(int var1, int var2) {
      this.chunkXPos = var1;
      this.chunkZPos = var2;
   }

   public static long chunkXZ2Int(int var0, int var1) {
      return (long)var0 & 4294967295L | ((long)var1 & 4294967295L) << 32;
   }

   public int hashCode() {
      int var1 = 1664525 * this.chunkXPos + 1013904223;
      int var2 = 1664525 * (this.chunkZPos ^ -559038737) + 1013904223;
      return var1 ^ var2;
   }

   public boolean equals(Object var1) {
      if(this == var1) {
         return true;
      } else if(!(var1 instanceof ChunkCoordIntPair)) {
         return false;
      } else {
         ChunkCoordIntPair var2 = (ChunkCoordIntPair)var1;
         return this.chunkXPos == var2.chunkXPos && this.chunkZPos == var2.chunkZPos;
      }
   }

   public int getCenterXPos() {
      return (this.chunkXPos << 4) + 8;
   }

   public int getCenterZPosition() {
      return (this.chunkZPos << 4) + 8;
   }

   public ChunkPosition func_151349_a(int var1) {
      return new ChunkPosition(this.getCenterXPos(), var1, this.getCenterZPosition());
   }

   public String toString() {
      return "[" + this.chunkXPos + ", " + this.chunkZPos + "]";
   }
}
