package net.minecraft.world;

import java.util.concurrent.Callable;
import net.minecraft.world.World;

class World$3 implements Callable {

   // $FF: synthetic field
   final World theWorld;


   World$3(World var1) {
      this.theWorld = var1;
   }

   public String call() {
      return this.theWorld.playerEntities.size() + " total; " + this.theWorld.playerEntities.toString();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
