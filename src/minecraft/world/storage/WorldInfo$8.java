package net.minecraft.world.storage;

import java.util.concurrent.Callable;
import net.minecraft.world.storage.WorldInfo;

class WorldInfo$8 implements Callable {

   // $FF: synthetic field
   final WorldInfo worldInfoInstance;


   WorldInfo$8(WorldInfo var1) {
      this.worldInfoInstance = var1;
   }

   public String call() {
      return String.format("Rain time: %d (now: %b), thunder time: %d (now: %b)", new Object[]{Integer.valueOf(WorldInfo.access$1000(this.worldInfoInstance)), Boolean.valueOf(WorldInfo.access$1100(this.worldInfoInstance)), Integer.valueOf(WorldInfo.access$1200(this.worldInfoInstance)), Boolean.valueOf(WorldInfo.access$1300(this.worldInfoInstance))});
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
