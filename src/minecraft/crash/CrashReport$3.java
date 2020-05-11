package net.minecraft.crash;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;

class CrashReport$3 implements Callable {

   // $FF: synthetic field
   final CrashReport theCrashReport;


   CrashReport$3(CrashReport var1) {
      this.theCrashReport = var1;
   }

   public String call() {
      return System.getProperty("java.version") + ", " + System.getProperty("java.vendor");
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
