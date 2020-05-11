package net.minecraft.util;


public enum EnumFacing {

   DOWN("DOWN", 0, 0, 1, 0, -1, 0),
   UP("UP", 1, 1, 0, 0, 1, 0),
   NORTH("NORTH", 2, 2, 3, 0, 0, -1),
   SOUTH("SOUTH", 3, 3, 2, 0, 0, 1),
   EAST("EAST", 4, 4, 5, -1, 0, 0),
   WEST("WEST", 5, 5, 4, 1, 0, 0);
   private final int order_a;
   private final int order_b;
   private final int frontOffsetX;
   private final int frontOffsetY;
   private final int frontOffsetZ;
   private static final EnumFacing[] faceList = new EnumFacing[6];
   // $FF: synthetic field
   private static final EnumFacing[] $VALUES = new EnumFacing[]{DOWN, UP, NORTH, SOUTH, EAST, WEST};


   private EnumFacing(String var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this.order_a = var3;
      this.order_b = var4;
      this.frontOffsetX = var5;
      this.frontOffsetY = var6;
      this.frontOffsetZ = var7;
   }

   public int getFrontOffsetX() {
      return this.frontOffsetX;
   }

   public int getFrontOffsetY() {
      return this.frontOffsetY;
   }

   public int getFrontOffsetZ() {
      return this.frontOffsetZ;
   }

   public static EnumFacing getFront(int var0) {
      return faceList[var0 % faceList.length];
   }

   static {
      EnumFacing[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         EnumFacing var3 = var0[var2];
         faceList[var3.order_a] = var3;
      }

   }
}
