package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

class Minecraft$14 implements Callable {

   // $FF: synthetic field
   final Minecraft field_151425_a;


   Minecraft$14(Minecraft var1) {
      this.field_151425_a = var1;
   }

   public String call() {
      byte var1 = 0;
      int var2 = 56 * var1;
      int var3 = var2 / 1024 / 1024;
      byte var4 = 0;
      int var5 = 56 * var4;
      int var6 = var5 / 1024 / 1024;
      return var1 + " (" + var2 + " bytes; " + var3 + " MB) allocated, " + var4 + " (" + var5 + " bytes; " + var6 + " MB) used";
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
