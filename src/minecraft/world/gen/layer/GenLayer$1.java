package net.minecraft.world.gen.layer;

import java.util.concurrent.Callable;
import net.minecraft.world.biome.BiomeGenBase;

final class GenLayer$1 implements Callable {

   // $FF: synthetic field
   final int field_151684_a;


   GenLayer$1(int var1) {
      this.field_151684_a = var1;
   }

   public String call() {
      return String.valueOf(BiomeGenBase.getBiome(this.field_151684_a));
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
