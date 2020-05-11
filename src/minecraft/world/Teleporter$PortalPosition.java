package net.minecraft.world;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;

public class Teleporter$PortalPosition extends ChunkCoordinates {

   public long lastUpdateTime;
   // $FF: synthetic field
   final Teleporter teleporterInstance;


   public Teleporter$PortalPosition(Teleporter var1, int var2, int var3, int var4, long var5) {
      super(var2, var3, var4);
      this.teleporterInstance = var1;
      this.lastUpdateTime = var5;
   }
}
