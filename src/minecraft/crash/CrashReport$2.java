package net.minecraft.crash;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;

class CrashReport$2 implements Callable {

   // $FF: synthetic field
   final CrashReport theCrashReport;


   CrashReport$2(CrashReport var1) {
      this.theCrashReport = var1;
   }

   public String call() {
      return System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version");
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
