package net.minecraft.world;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class ChunkPosition {

   public final int chunkPosX;
   public final int chunkPosY;
   public final int chunkPosZ;


   public ChunkPosition(int var1, int var2, int var3) {
      this.chunkPosX = var1;
      this.chunkPosY = var2;
      this.chunkPosZ = var3;
   }

   public ChunkPosition(Vec3 var1) {
      this(MathHelper.floor_double(var1.xCoord), MathHelper.floor_double(var1.yCoord), MathHelper.floor_double(var1.zCoord));
   }

   public boolean equals(Object var1) {
      if(!(var1 instanceof ChunkPosition)) {
         return false;
      } else {
         ChunkPosition var2 = (ChunkPosition)var1;
         return var2.chunkPosX == this.chunkPosX && var2.chunkPosY == this.chunkPosY && var2.chunkPosZ == this.chunkPosZ;
      }
   }

   public int hashCode() {
      return this.chunkPosX * 8976890 + this.chunkPosY * 981131 + this.chunkPosZ;
   }
}
