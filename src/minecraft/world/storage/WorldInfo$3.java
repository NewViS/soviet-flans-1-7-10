package net.minecraft.world.storage;

import java.util.concurrent.Callable;
import net.minecraft.world.storage.WorldInfo;

class WorldInfo$3 implements Callable {

   // $FF: synthetic field
   final WorldInfo worldInfoInstance;


   WorldInfo$3(WorldInfo var1) {
      this.worldInfoInstance = var1;
   }

   public String call() {
      return WorldInfo.access$200(this.worldInfoInstance);
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
