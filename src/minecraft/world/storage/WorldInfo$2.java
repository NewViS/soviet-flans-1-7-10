package net.minecraft.world.storage;

import java.util.concurrent.Callable;
import net.minecraft.world.storage.WorldInfo;

class WorldInfo$2 implements Callable {

   // $FF: synthetic field
   final WorldInfo worldInfoInstance;


   WorldInfo$2(WorldInfo var1) {
      this.worldInfoInstance = var1;
   }

   public String call() {
      return String.format("ID %02d - %s, ver %d. Features enabled: %b", new Object[]{Integer.valueOf(WorldInfo.access$000(this.worldInfoInstance).getWorldTypeID()), WorldInfo.access$000(this.worldInfoInstance).getWorldTypeName(), Integer.valueOf(WorldInfo.access$000(this.worldInfoInstance).getGeneratorVersion()), Boolean.valueOf(WorldInfo.access$100(this.worldInfoInstance))});
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
