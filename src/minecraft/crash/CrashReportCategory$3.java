package net.minecraft.crash;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;

final class CrashReportCategory$3 implements Callable {

   // $FF: synthetic field
   final int blockXCoord;
   // $FF: synthetic field
   final int blockYCoord;
   // $FF: synthetic field
   final int blockZCoord;


   CrashReportCategory$3(int var1, int var2, int var3) {
      this.blockXCoord = var1;
      this.blockYCoord = var2;
      this.blockZCoord = var3;
   }

   public String call() {
      return CrashReportCategory.getLocationInfo(this.blockXCoord, this.blockYCoord, this.blockZCoord);
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
