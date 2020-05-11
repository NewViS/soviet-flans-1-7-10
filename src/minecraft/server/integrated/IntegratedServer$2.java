package net.minecraft.server.integrated;

import java.util.concurrent.Callable;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.server.integrated.IntegratedServer;

class IntegratedServer$2 implements Callable {

   // $FF: synthetic field
   final IntegratedServer theIntegratedServer;


   IntegratedServer$2(IntegratedServer var1) {
      this.theIntegratedServer = var1;
   }

   public String call() {
      String var1 = ClientBrandRetriever.getClientModName();
      if(!var1.equals("vanilla")) {
         return "Definitely; Client brand changed to \'" + var1 + "\'";
      } else {
         var1 = this.theIntegratedServer.getServerModName();
         return !var1.equals("vanilla")?"Definitely; Server brand changed to \'" + var1 + "\'":(Minecraft.class.getSigners() == null?"Very likely; Jar signature invalidated":"Probably not. Jar signature remains and both client + server brands are untouched.");
      }
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
