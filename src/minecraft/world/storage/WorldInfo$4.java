package net.minecraft.world.storage;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.world.storage.WorldInfo;

class WorldInfo$4 implements Callable {

   // $FF: synthetic field
   final WorldInfo worldInfoInstance;


   WorldInfo$4(WorldInfo var1) {
      this.worldInfoInstance = var1;
   }

   public String call() {
      return CrashReportCategory.getLocationInfo(WorldInfo.access$300(this.worldInfoInstance), WorldInfo.access$400(this.worldInfoInstance), WorldInfo.access$500(this.worldInfoInstance));
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
