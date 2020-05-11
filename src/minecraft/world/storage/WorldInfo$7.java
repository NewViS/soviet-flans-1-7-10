package net.minecraft.world.storage;

import java.util.concurrent.Callable;
import net.minecraft.world.storage.WorldInfo;

class WorldInfo$7 implements Callable {

   // $FF: synthetic field
   final WorldInfo worldInfoInstance;


   WorldInfo$7(WorldInfo var1) {
      this.worldInfoInstance = var1;
   }

   public String call() {
      String var1 = "Unknown?";

      try {
         switch(WorldInfo.access$900(this.worldInfoInstance)) {
         case 19132:
            var1 = "McRegion";
            break;
         case 19133:
            var1 = "Anvil";
         }
      } catch (Throwable var3) {
         ;
      }

      return String.format("0x%05X - %s", new Object[]{Integer.valueOf(WorldInfo.access$900(this.worldInfoInstance)), var1});
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
