package net.minecraft.world.demo;

import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveHandler;

public class DemoWorldServer extends WorldServer {

   private static final long demoWorldSeed = (long)"North Carolina".hashCode();
   public static final WorldSettings demoWorldSettings = (new WorldSettings(demoWorldSeed, WorldSettings$GameType.SURVIVAL, true, false, WorldType.DEFAULT)).enableBonusChest();


   public DemoWorldServer(MinecraftServer var1, ISaveHandler var2, String var3, int var4, Profiler var5) {
      super(var1, var2, var3, var4, demoWorldSettings, var5);
   }

}
