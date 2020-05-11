package net.minecraft.client.multiplayer;

import java.util.concurrent.Callable;
import net.minecraft.client.multiplayer.WorldClient;

class WorldClient$4 implements Callable {

   // $FF: synthetic field
   final WorldClient theWorldClient;


   WorldClient$4(WorldClient var1) {
      this.theWorldClient = var1;
   }

   public String call() {
      return WorldClient.access$200(this.theWorldClient).getIntegratedServer() == null?"Non-integrated multiplayer server":"Integrated singleplayer server";
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
