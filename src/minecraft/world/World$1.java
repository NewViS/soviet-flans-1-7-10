package net.minecraft.world;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.world.World;

class World$1 implements Callable {

   // $FF: synthetic field
   final int field_151302_a;
   // $FF: synthetic field
   final int field_151301_b;
   // $FF: synthetic field
   final World theWorld;


   World$1(World var1, int var2, int var3) {
      this.theWorld = var1;
      this.field_151302_a = var2;
      this.field_151301_b = var3;
   }

   public String call() {
      return CrashReportCategory.getLocationInfo(this.field_151302_a, 0, this.field_151301_b);
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
