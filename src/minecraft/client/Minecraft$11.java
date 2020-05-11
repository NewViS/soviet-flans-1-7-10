package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

class Minecraft$11 implements Callable {

   // $FF: synthetic field
   final Minecraft theMinecraft;


   Minecraft$11(Minecraft var1) {
      this.theMinecraft = var1;
   }

   public String call() {
      return this.theMinecraft.gameSettings.resourcePacks.toString();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
