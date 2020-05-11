package net.minecraft.world;

import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraft.world.storage.ISaveHandler;

public class WorldServerMulti extends WorldServer {

   public WorldServerMulti(MinecraftServer var1, ISaveHandler var2, String var3, int var4, WorldSettings var5, WorldServer var6, Profiler var7) {
      super(var1, var2, var3, var4, var5, var7);
      super.mapStorage = var6.mapStorage;
      super.worldScoreboard = var6.getScoreboard();
      super.worldInfo = new DerivedWorldInfo(var6.getWorldInfo());
   }

   protected void saveLevel() {}
}
