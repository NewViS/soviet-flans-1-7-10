package net.minecraft.world.storage;

import net.minecraft.world.storage.MapData;

public class MapData$MapCoord {

   public byte iconSize;
   public byte centerX;
   public byte centerZ;
   public byte iconRotation;
   // $FF: synthetic field
   final MapData data;


   public MapData$MapCoord(MapData var1, byte var2, byte var3, byte var4, byte var5) {
      this.data = var1;
      this.iconSize = var2;
      this.centerX = var3;
      this.centerZ = var4;
      this.iconRotation = var5;
   }
}
