package net.minecraft.world.storage;

import java.util.concurrent.Callable;
import net.minecraft.world.storage.WorldInfo;

class WorldInfo$6 implements Callable {

   // $FF: synthetic field
   final WorldInfo worldInfoInstance;


   WorldInfo$6(WorldInfo var1) {
      this.worldInfoInstance = var1;
   }

   public String call() {
      return String.valueOf(WorldInfo.access$800(this.worldInfoInstance));
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
