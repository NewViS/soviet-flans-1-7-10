package net.minecraft.util;

import net.minecraft.util.MathHelper;

public class Vec3 {

   public double xCoord;
   public double yCoord;
   public double zCoord;


   public static Vec3 createVectorHelper(double var0, double var2, double var4) {
      return new Vec3(var0, var2, var4);
   }

   protected Vec3(double var1, double var3, double var5) {
      if(var1 == -0.0D) {
         var1 = 0.0D;
      }

      if(var3 == -0.0D) {
         var3 = 0.0D;
      }

      if(var5 == -0.0D) {
         var5 = 0.0D;
      }

      this.xCoord = var1;
      this.yCoord = var3;
      this.zCoord = var5;
   }

   protected Vec3 setComponents(double var1, double var3, double var5) {
      this.xCoord = var1;
      this.yCoord = var3;
      this.zCoord = var5;
      return this;
   }

   public Vec3 subtract(Vec3 var1) {
      return createVectorHelper(var1.xCoord - this.xCoord, var1.yCoord - this.yCoord, var1.zCoord - this.zCoord);
   }

   public Vec3 normalize() {
      double var1 = (double)MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
      return var1 < 1.0E-4D?createVectorHelper(0.0D, 0.0D, 0.0D):createVectorHelper(this.xCoord / var1, this.yCoord / var1, this.zCoord / var1);
   }

   public double dotProduct(Vec3 var1) {
      return this.xCoord * var1.xCoord + this.yCoord * var1.yCoord + this.zCoord * var1.zCoord;
   }

   public Vec3 crossProduct(Vec3 var1) {
      return createVectorHelper(this.yCoord * var1.zCoord - this.zCoord * var1.yCoord, this.zCoord * var1.xCoord - this.xCoord * var1.zCoord, this.xCoord * var1.yCoord - this.yCoord * var1.xCoord);
   }

   public Vec3 addVector(double var1, double var3, double var5) {
      return createVectorHelper(this.xCoord + var1, this.yCoord + var3, this.zCoord + var5);
   }

   public double distanceTo(Vec3 var1) {
      double var2 = var1.xCoord - this.xCoord;
      double var4 = var1.yCoord - this.yCoord;
      double var6 = var1.zCoord - this.zCoord;
      return (double)MathHelper.sqrt_double(var2 * var2 + var4 * var4 + var6 * var6);
   }

   public double squareDistanceTo(Vec3 var1) {
      double var2 = var1.xCoord - this.xCoord;
      double var4 = var1.yCoord - this.yCoord;
      double var6 = var1.zCoord - this.zCoord;
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public double squareDistanceTo(double var1, double var3, double var5) {
      double var7 = var1 - this.xCoord;
      double var9 = var3 - this.yCoord;
      double var11 = var5 - this.zCoord;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   public double lengthVector() {
      return (double)MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
   }

   public Vec3 getIntermediateWithXValue(Vec3 var1, double var2) {
      double var4 = var1.xCoord - this.xCoord;
      double var6 = var1.yCoord - this.yCoord;
      double var8 = var1.zCoord - this.zCoord;
      if(var4 * var4 < 1.0000000116860974E-7D) {
         return null;
      } else {
         double var10 = (var2 - this.xCoord) / var4;
         return var10 >= 0.0D && var10 <= 1.0D?createVectorHelper(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10):null;
      }
   }

   public Vec3 getIntermediateWithYValue(Vec3 var1, double var2) {
      double var4 = var1.xCoord - this.xCoord;
      double var6 = var1.yCoord - this.yCoord;
      double var8 = var1.zCoord - this.zCoord;
      if(var6 * var6 < 1.0000000116860974E-7D) {
         return null;
      } else {
         double var10 = (var2 - this.yCoord) / var6;
         return var10 >= 0.0D && var10 <= 1.0D?createVectorHelper(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10):null;
      }
   }

   public Vec3 getIntermediateWithZValue(Vec3 var1, double var2) {
      double var4 = var1.xCoord - this.xCoord;
      double var6 = var1.yCoord - this.yCoord;
      double var8 = var1.zCoord - this.zCoord;
      if(var8 * var8 < 1.0000000116860974E-7D) {
         return null;
      } else {
         double var10 = (var2 - this.zCoord) / var8;
         return var10 >= 0.0D && var10 <= 1.0D?createVectorHelper(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10):null;
      }
   }

   public String toString() {
      return "(" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + ")";
   }

   public void rotateAroundX(float var1) {
      float var2 = MathHelper.cos(var1);
      float var3 = MathHelper.sin(var1);
      double var4 = this.xCoord;
      double var6 = this.yCoord * (double)var2 + this.zCoord * (double)var3;
      double var8 = this.zCoord * (double)var2 - this.yCoord * (double)var3;
      this.setComponents(var4, var6, var8);
   }

   public void rotateAroundY(float var1) {
      float var2 = MathHelper.cos(var1);
      float var3 = MathHelper.sin(var1);
      double var4 = this.xCoord * (double)var2 + this.zCoord * (double)var3;
      double var6 = this.yCoord;
      double var8 = this.zCoord * (double)var2 - this.xCoord * (double)var3;
      this.setComponents(var4, var6, var8);
   }

   public void rotateAroundZ(float var1) {
      float var2 = MathHelper.cos(var1);
      float var3 = MathHelper.sin(var1);
      double var4 = this.xCoord * (double)var2 + this.yCoord * (double)var3;
      double var6 = this.yCoord * (double)var2 - this.xCoord * (double)var3;
      double var8 = this.zCoord;
      this.setComponents(var4, var6, var8);
   }
}
