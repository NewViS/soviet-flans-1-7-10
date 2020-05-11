package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenMutated;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

class BiomeGenForest$1 extends BiomeGenMutated {

   // $FF: synthetic field
   final BiomeGenForest field_150612_aC;


   BiomeGenForest$1(BiomeGenForest var1, int var2, BiomeGenBase var3) {
      super(var2, var3);
      this.field_150612_aC = var1;
   }

   public WorldGenAbstractTree func_150567_a(Random var1) {
      return var1.nextBoolean()?BiomeGenForest.field_150629_aC:BiomeGenForest.field_150630_aD;
   }
}
