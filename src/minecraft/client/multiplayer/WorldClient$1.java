package net.minecraft.client.multiplayer;

import java.util.concurrent.Callable;
import net.minecraft.client.multiplayer.WorldClient;

class WorldClient$1 implements Callable {

   // $FF: synthetic field
   final WorldClient theWorldClient;


   WorldClient$1(WorldClient var1) {
      this.theWorldClient = var1;
   }

   public String call() {
      return WorldClient.access$000(this.theWorldClient).size() + " total; " + WorldClient.access$000(this.theWorldClient).toString();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
