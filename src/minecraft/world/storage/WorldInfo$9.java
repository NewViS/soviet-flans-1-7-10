package net.minecraft.world.storage;

import java.util.concurrent.Callable;
import net.minecraft.world.storage.WorldInfo;

class WorldInfo$9 implements Callable {

   // $FF: synthetic field
   final WorldInfo worldInfoInstance;


   WorldInfo$9(WorldInfo var1) {
      this.worldInfoInstance = var1;
   }

   public String call() {
      return String.format("Game mode: %s (ID %d). Hardcore: %b. Cheats: %b", new Object[]{WorldInfo.access$1400(this.worldInfoInstance).getName(), Integer.valueOf(WorldInfo.access$1400(this.worldInfoInstance).getID()), Boolean.valueOf(WorldInfo.access$1500(this.worldInfoInstance)), Boolean.valueOf(WorldInfo.access$1600(this.worldInfoInstance))});
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
