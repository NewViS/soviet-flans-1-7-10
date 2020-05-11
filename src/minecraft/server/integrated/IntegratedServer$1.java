package net.minecraft.server.integrated;

import java.util.concurrent.Callable;
import net.minecraft.server.integrated.IntegratedServer;

class IntegratedServer$1 implements Callable {

   // $FF: synthetic field
   final IntegratedServer theIntegratedServer;


   IntegratedServer$1(IntegratedServer var1) {
      this.theIntegratedServer = var1;
   }

   public String call() {
      return "Integrated Server (map_client.txt)";
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
