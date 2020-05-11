package net.minecraft.util;


public class ChunkCoordinates implements Comparable {

   public int posX;
   public int posY;
   public int posZ;


   public ChunkCoordinates() {}

   public ChunkCoordinates(int var1, int var2, int var3) {
      this.posX = var1;
      this.posY = var2;
      this.posZ = var3;
   }

   public ChunkCoordinates(ChunkCoordinates var1) {
      this.posX = var1.posX;
      this.posY = var1.posY;
      this.posZ = var1.posZ;
   }

   public boolean equals(Object var1) {
      if(!(var1 instanceof ChunkCoordinates)) {
         return false;
      } else {
         ChunkCoordinates var2 = (ChunkCoordinates)var1;
         return this.posX == var2.posX && this.posY == var2.posY && this.posZ == var2.posZ;
      }
   }

   public int hashCode() {
      return this.posX + this.posZ << 8 + this.posY << 16;
   }

   public int compareTo(ChunkCoordinates var1) {
      return this.posY == var1.posY?(this.posZ == var1.posZ?this.posX - var1.posX:this.posZ - var1.posZ):this.posY - var1.posY;
   }

   public void set(int var1, int var2, int var3) {
      this.posX = var1;
      this.posY = var2;
      this.posZ = var3;
   }

   public float getDistanceSquared(int var1, int var2, int var3) {
      float var4 = (float)(this.posX - var1);
      float var5 = (float)(this.posY - var2);
      float var6 = (float)(this.posZ - var3);
      return var4 * var4 + var5 * var5 + var6 * var6;
   }

   public float getDistanceSquaredToChunkCoordinates(ChunkCoordinates var1) {
      return this.getDistanceSquared(var1.posX, var1.posY, var1.posZ);
   }

   public String toString() {
      return "Pos{x=" + this.posX + ", y=" + this.posY + ", z=" + this.posZ + '}';
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.compareTo((ChunkCoordinates)var1);
   }
}
