package net.minecraft.client.multiplayer;

import java.util.concurrent.Callable;
import net.minecraft.client.multiplayer.WorldClient;

class WorldClient$3 implements Callable {

   // $FF: synthetic field
   final WorldClient theWorldClient;


   WorldClient$3(WorldClient var1) {
      this.theWorldClient = var1;
   }

   public String call() {
      return WorldClient.access$200(this.theWorldClient).thePlayer.func_142021_k();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
