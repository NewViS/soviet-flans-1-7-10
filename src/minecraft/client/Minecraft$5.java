package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

class Minecraft$5 implements Callable {

   // $FF: synthetic field
   final Minecraft mc;


   Minecraft$5(Minecraft var1) {
      this.mc = var1;
   }

   public String call() {
      return Minecraft.access$000(this.mc);
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
