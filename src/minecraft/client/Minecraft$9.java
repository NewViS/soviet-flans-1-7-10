package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;

class Minecraft$9 implements Callable {

   // $FF: synthetic field
   final Minecraft mc;


   Minecraft$9(Minecraft var1) {
      this.mc = var1;
   }

   public String call() {
      String var1 = ClientBrandRetriever.getClientModName();
      return !var1.equals("vanilla")?"Definitely; Client brand changed to \'" + var1 + "\'":(Minecraft.class.getSigners() == null?"Very likely; Jar signature invalidated":"Probably not. Jar signature remains and client brand is untouched.");
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
