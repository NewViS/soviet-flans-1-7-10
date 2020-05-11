package net.minecraft.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBeach extends BiomeGenBase {

   public BiomeGenBeach(int var1) {
      super(var1);
      super.spawnableCreatureList.clear();
      super.topBlock = Blocks.sand;
      super.fillerBlock = Blocks.sand;
      super.theBiomeDecorator.treesPerChunk = -999;
      super.theBiomeDecorator.deadBushPerChunk = 0;
      super.theBiomeDecorator.reedsPerChunk = 0;
      super.theBiomeDecorator.cactiPerChunk = 0;
   }
}
