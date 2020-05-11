package net.minecraft.crash;

import java.util.concurrent.Callable;
import net.minecraft.block.Block;

final class CrashReportCategory$1 implements Callable {

   // $FF: synthetic field
   final int blockID;
   // $FF: synthetic field
   final Block field_147151_b;


   CrashReportCategory$1(int var1, Block var2) {
      this.blockID = var1;
      this.field_147151_b = var2;
   }

   public String call() {
      try {
         return String.format("ID #%d (%s // %s)", new Object[]{Integer.valueOf(this.blockID), this.field_147151_b.getUnlocalizedName(), this.field_147151_b.getClass().getCanonicalName()});
      } catch (Throwable var2) {
         return "ID #" + this.blockID;
      }
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
