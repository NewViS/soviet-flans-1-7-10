package net.minecraft.crash;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;

class CrashReport$4 implements Callable {

   // $FF: synthetic field
   final CrashReport theCrashReport;


   CrashReport$4(CrashReport var1) {
      this.theCrashReport = var1;
   }

   public String call() {
      return System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor");
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
