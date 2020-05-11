package net.minecraft.crash;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.world.gen.layer.IntCache;

class CrashReport$8 implements Callable {

   // $FF: synthetic field
   final CrashReport theCrashReport;


   CrashReport$8(CrashReport var1) {
      this.theCrashReport = var1;
   }

   public String call() {
      return IntCache.getCacheSizes();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
