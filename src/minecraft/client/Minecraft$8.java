package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;

class Minecraft$8 implements Callable {

   // $FF: synthetic field
   final Minecraft mc;


   Minecraft$8(Minecraft var1) {
      this.mc = var1;
   }

   public String call() {
      return OpenGlHelper.func_153172_c();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
