package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenMutated;

class BiomeGenForest$2 extends BiomeGenMutated {

   // $FF: synthetic field
   final BiomeGenForest field_150613_aC;


   BiomeGenForest$2(BiomeGenForest var1, int var2, BiomeGenBase var3) {
      super(var2, var3);
      this.field_150613_aC = var1;
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      super.baseBiome.decorate(var1, var2, var3, var4);
   }
}
