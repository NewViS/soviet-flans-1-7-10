package net.minecraft.world.storage;

import java.util.concurrent.Callable;
import net.minecraft.world.storage.WorldInfo;

class WorldInfo$5 implements Callable {

   // $FF: synthetic field
   final WorldInfo worldInfoInstance;


   WorldInfo$5(WorldInfo var1) {
      this.worldInfoInstance = var1;
   }

   public String call() {
      return String.format("%d game time, %d day time", new Object[]{Long.valueOf(WorldInfo.access$600(this.worldInfoInstance)), Long.valueOf(WorldInfo.access$700(this.worldInfoInstance))});
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
