package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

class Minecraft$12 implements Callable {

   // $FF: synthetic field
   final Minecraft theMinecraft;


   Minecraft$12(Minecraft var1) {
      this.theMinecraft = var1;
   }

   public String call() {
      return Minecraft.access$100(this.theMinecraft).getCurrentLanguage().toString();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
