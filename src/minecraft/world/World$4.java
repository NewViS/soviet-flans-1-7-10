package net.minecraft.world;

import java.util.concurrent.Callable;
import net.minecraft.world.World;

class World$4 implements Callable {

   // $FF: synthetic field
   final World field_151308_a;


   World$4(World var1) {
      this.field_151308_a = var1;
   }

   public String call() {
      return this.field_151308_a.chunkProvider.makeString();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
