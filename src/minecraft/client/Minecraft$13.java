package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

class Minecraft$13 implements Callable {

   // $FF: synthetic field
   final Minecraft field_142056_a;


   Minecraft$13(Minecraft var1) {
      this.field_142056_a = var1;
   }

   public String call() {
      return this.field_142056_a.mcProfiler.profilingEnabled?this.field_142056_a.mcProfiler.getNameOfLastSection():"N/A (disabled)";
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
