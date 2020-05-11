package net.minecraft.server;

import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;

public class MinecraftServer$6 implements Callable {

   // $FF: synthetic field
   final MinecraftServer mcServer;


   public MinecraftServer$6(MinecraftServer var1) {
      this.mcServer = var1;
   }

   public String call() {
      return MinecraftServer.access$100(this.mcServer).getCurrentPlayerCount() + " / " + MinecraftServer.access$100(this.mcServer).getMaxPlayers() + "; " + MinecraftServer.access$100(this.mcServer).playerEntityList;
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
