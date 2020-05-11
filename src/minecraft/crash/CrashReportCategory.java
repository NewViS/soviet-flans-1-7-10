package net.minecraft.crash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory$1;
import net.minecraft.crash.CrashReportCategory$2;
import net.minecraft.crash.CrashReportCategory$3;
import net.minecraft.crash.CrashReportCategory$Entry;
import net.minecraft.util.MathHelper;

public class CrashReportCategory {

   private final CrashReport theCrashReport;
   private final String field_85076_b;
   private final List field_85077_c = new ArrayList();
   private StackTraceElement[] stackTrace = new StackTraceElement[0];


   public CrashReportCategory(CrashReport var1, String var2) {
      this.theCrashReport = var1;
      this.field_85076_b = var2;
   }

   public static String func_85074_a(double var0, double var2, double var4) {
      return String.format("%.2f,%.2f,%.2f - %s", new Object[]{Double.valueOf(var0), Double.valueOf(var2), Double.valueOf(var4), getLocationInfo(MathHelper.floor_double(var0), MathHelper.floor_double(var2), MathHelper.floor_double(var4))});
   }

   public static String getLocationInfo(int var0, int var1, int var2) {
      StringBuilder var3 = new StringBuilder();

      try {
         var3.append(String.format("World: (%d,%d,%d)", new Object[]{Integer.valueOf(var0), Integer.valueOf(var1), Integer.valueOf(var2)}));
      } catch (Throwable var16) {
         var3.append("(Error finding world loc)");
      }

      var3.append(", ");

      int var4;
      int var5;
      int var6;
      int var7;
      int var8;
      int var9;
      int var10;
      int var11;
      int var12;
      try {
         var4 = var0 >> 4;
         var5 = var2 >> 4;
         var6 = var0 & 15;
         var7 = var1 >> 4;
         var8 = var2 & 15;
         var9 = var4 << 4;
         var10 = var5 << 4;
         var11 = (var4 + 1 << 4) - 1;
         var12 = (var5 + 1 << 4) - 1;
         var3.append(String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", new Object[]{Integer.valueOf(var6), Integer.valueOf(var7), Integer.valueOf(var8), Integer.valueOf(var4), Integer.valueOf(var5), Integer.valueOf(var9), Integer.valueOf(var10), Integer.valueOf(var11), Integer.valueOf(var12)}));
      } catch (Throwable var15) {
         var3.append("(Error finding chunk loc)");
      }

      var3.append(", ");

      try {
         var4 = var0 >> 9;
         var5 = var2 >> 9;
         var6 = var4 << 5;
         var7 = var5 << 5;
         var8 = (var4 + 1 << 5) - 1;
         var9 = (var5 + 1 << 5) - 1;
         var10 = var4 << 9;
         var11 = var5 << 9;
         var12 = (var4 + 1 << 9) - 1;
         int var13 = (var5 + 1 << 9) - 1;
         var3.append(String.format("Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)", new Object[]{Integer.valueOf(var4), Integer.valueOf(var5), Integer.valueOf(var6), Integer.valueOf(var7), Integer.valueOf(var8), Integer.valueOf(var9), Integer.valueOf(var10), Integer.valueOf(var11), Integer.valueOf(var12), Integer.valueOf(var13)}));
      } catch (Throwable var14) {
         var3.append("(Error finding world loc)");
      }

      return var3.toString();
   }

   public void addCrashSectionCallable(String var1, Callable var2) {
      try {
         this.addCrashSection(var1, var2.call());
      } catch (Throwable var4) {
         this.addCrashSectionThrowable(var1, var4);
      }

   }

   public void addCrashSection(String var1, Object var2) {
      this.field_85077_c.add(new CrashReportCategory$Entry(var1, var2));
   }

   public void addCrashSectionThrowable(String var1, Throwable var2) {
      this.addCrashSection(var1, var2);
   }

   public int getPrunedStackTrace(int var1) {
      StackTraceElement[] var2 = Thread.currentThread().getStackTrace();
      if(var2.length <= 0) {
         return 0;
      } else {
         this.stackTrace = new StackTraceElement[var2.length - 3 - var1];
         System.arraycopy(var2, 3 + var1, this.stackTrace, 0, this.stackTrace.length);
         return this.stackTrace.length;
      }
   }

   public boolean firstTwoElementsOfStackTraceMatch(StackTraceElement var1, StackTraceElement var2) {
      if(this.stackTrace.length != 0 && var1 != null) {
         StackTraceElement var3 = this.stackTrace[0];
         if(var3.isNativeMethod() == var1.isNativeMethod() && var3.getClassName().equals(var1.getClassName()) && var3.getFileName().equals(var1.getFileName()) && var3.getMethodName().equals(var1.getMethodName())) {
            if(var2 != null != this.stackTrace.length > 1) {
               return false;
            } else if(var2 != null && !this.stackTrace[1].equals(var2)) {
               return false;
            } else {
               this.stackTrace[0] = var1;
               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void trimStackTraceEntriesFromBottom(int var1) {
      StackTraceElement[] var2 = new StackTraceElement[this.stackTrace.length - var1];
      System.arraycopy(this.stackTrace, 0, var2, 0, var2.length);
      this.stackTrace = var2;
   }

   public void appendToStringBuilder(StringBuilder var1) {
      var1.append("-- ").append(this.field_85076_b).append(" --\n");
      var1.append("Details:");
      Iterator var2 = this.field_85077_c.iterator();

      while(var2.hasNext()) {
         CrashReportCategory$Entry var3 = (CrashReportCategory$Entry)var2.next();
         var1.append("\n\t");
         var1.append(var3.func_85089_a());
         var1.append(": ");
         var1.append(var3.func_85090_b());
      }

      if(this.stackTrace != null && this.stackTrace.length > 0) {
         var1.append("\nStacktrace:");
         StackTraceElement[] var6 = this.stackTrace;
         int var7 = var6.length;

         for(int var4 = 0; var4 < var7; ++var4) {
            StackTraceElement var5 = var6[var4];
            var1.append("\n\tat ");
            var1.append(var5.toString());
         }
      }

   }

   public StackTraceElement[] func_147152_a() {
      return this.stackTrace;
   }

   public static void func_147153_a(CrashReportCategory var0, int var1, int var2, int var3, Block var4, int var5) {
      int var6 = Block.getIdFromBlock(var4);
      var0.addCrashSectionCallable("Block type", new CrashReportCategory$1(var6, var4));
      var0.addCrashSectionCallable("Block data value", new CrashReportCategory$2(var5));
      var0.addCrashSectionCallable("Block location", new CrashReportCategory$3(var1, var2, var3));
   }
}
