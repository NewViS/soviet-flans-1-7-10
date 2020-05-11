package net.minecraft.client;

import net.minecraft.client.Minecraft;

class Minecraft$1 extends Thread {

   // $FF: synthetic field
   final Minecraft mc;


   Minecraft$1(Minecraft var1, String var2) {
      super(var2);
      this.mc = var1;
   }

   public void run() {
      while(this.mc.running) {
         try {
            Thread.sleep(2147483647L);
         } catch (InterruptedException var2) {
            ;
         }
      }

   }
}
