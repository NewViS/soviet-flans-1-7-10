package net.minecraft.world.gen.structure;

import java.util.concurrent.Callable;
import net.minecraft.world.gen.structure.MapGenStructure;

class MapGenStructure$3 implements Callable {

   // $FF: synthetic field
   final MapGenStructure theMapStructureGenerator;


   MapGenStructure$3(MapGenStructure var1) {
      this.theMapStructureGenerator = var1;
   }

   public String call() {
      return this.theMapStructureGenerator.getClass().getCanonicalName();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
