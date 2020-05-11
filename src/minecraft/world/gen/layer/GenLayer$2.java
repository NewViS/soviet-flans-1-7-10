package net.minecraft.world.gen.layer;

import java.util.concurrent.Callable;
import net.minecraft.world.biome.BiomeGenBase;

final class GenLayer$2 implements Callable {

   // $FF: synthetic field
   final int field_151682_a;


   GenLayer$2(int var1) {
      this.field_151682_a = var1;
   }

   public String call() {
      return String.valueOf(BiomeGenBase.getBiome(this.field_151682_a));
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
