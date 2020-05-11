package net.minecraft.server;

import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;

public class MinecraftServer$4 implements Callable {

   // $FF: synthetic field
   final MinecraftServer mcServer;


   public MinecraftServer$4(MinecraftServer var1) {
      this.mcServer = var1;
   }

   public String call() {
      return this.mcServer.theProfiler.profilingEnabled?this.mcServer.theProfiler.getNameOfLastSection():"N/A (disabled)";
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
