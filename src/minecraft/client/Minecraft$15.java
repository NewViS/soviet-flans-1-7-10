package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

class Minecraft$15 implements Callable {

   // $FF: synthetic field
   final Minecraft field_152389_a;


   Minecraft$15(Minecraft var1) {
      this.field_152389_a = var1;
   }

   public String func_152388_a() {
      return this.field_152389_a.gameSettings.anisotropicFiltering == 1?"Off (1)":"On (" + this.field_152389_a.gameSettings.anisotropicFiltering + ")";
   }

   // $FF: synthetic method
   public Object call() {
      return this.func_152388_a();
   }
}
