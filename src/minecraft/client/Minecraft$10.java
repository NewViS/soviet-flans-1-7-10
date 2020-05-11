package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

class Minecraft$10 implements Callable {

   // $FF: synthetic field
   final Minecraft theMinecraft;


   Minecraft$10(Minecraft var1) {
      this.theMinecraft = var1;
   }

   public String call() {
      return "Client (map_client.txt)";
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
