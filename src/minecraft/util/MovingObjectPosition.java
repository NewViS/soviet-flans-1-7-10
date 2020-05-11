package net.minecraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.util.Vec3;

public class MovingObjectPosition {

   public MovingObjectPosition$MovingObjectType typeOfHit;
   public int blockX;
   public int blockY;
   public int blockZ;
   public int sideHit;
   public Vec3 hitVec;
   public Entity entityHit;


   public MovingObjectPosition(int var1, int var2, int var3, int var4, Vec3 var5) {
      this(var1, var2, var3, var4, var5, true);
   }

   public MovingObjectPosition(int var1, int var2, int var3, int var4, Vec3 var5, boolean var6) {
      this.typeOfHit = var6?MovingObjectPosition$MovingObjectType.BLOCK:MovingObjectPosition$MovingObjectType.MISS;
      this.blockX = var1;
      this.blockY = var2;
      this.blockZ = var3;
      this.sideHit = var4;
      this.hitVec = Vec3.createVectorHelper(var5.xCoord, var5.yCoord, var5.zCoord);
   }

   public MovingObjectPosition(Entity var1) {
      this(var1, Vec3.createVectorHelper(var1.posX, var1.posY, var1.posZ));
   }

   public MovingObjectPosition(Entity var1, Vec3 var2) {
      this.typeOfHit = MovingObjectPosition$MovingObjectType.ENTITY;
      this.entityHit = var1;
      this.hitVec = var2;
   }

   public String toString() {
      return "HitResult{type=" + this.typeOfHit + ", x=" + this.blockX + ", y=" + this.blockY + ", z=" + this.blockZ + ", f=" + this.sideHit + ", pos=" + this.hitVec + ", entity=" + this.entityHit + '}';
   }
}
