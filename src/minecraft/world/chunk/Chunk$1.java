package net.minecraft.world.chunk;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.world.chunk.Chunk;

class Chunk$1 implements Callable {

   // $FF: synthetic field
   final int field_150824_a;
   // $FF: synthetic field
   final int field_150822_b;
   // $FF: synthetic field
   final int field_150823_c;
   // $FF: synthetic field
   final Chunk field_150821_d;


   Chunk$1(Chunk var1, int var2, int var3, int var4) {
      this.field_150821_d = var1;
      this.field_150824_a = var2;
      this.field_150822_b = var3;
      this.field_150823_c = var4;
   }

   public String call() {
      return CrashReportCategory.getLocationInfo(this.field_150824_a, this.field_150822_b, this.field_150823_c);
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
