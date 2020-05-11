package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;
import org.lwjgl.Sys;

class Minecraft$6 implements Callable {

   // $FF: synthetic field
   final Minecraft mc;


   Minecraft$6(Minecraft var1) {
      this.mc = var1;
   }

   public String call() {
      return Sys.getVersion();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
