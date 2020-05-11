package net.minecraft.server;

import net.minecraft.server.MinecraftServer;

public class MinecraftServer$3 extends Thread {

   // $FF: synthetic field
   final MinecraftServer theServer;


   public MinecraftServer$3(MinecraftServer var1, String var2) {
      super(var2);
      this.theServer = var1;
   }

   public void run() {
      this.theServer.run();
   }
}
