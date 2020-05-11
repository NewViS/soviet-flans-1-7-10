package net.minecraft.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenStoneBeach extends BiomeGenBase {

   public BiomeGenStoneBeach(int var1) {
      super(var1);
      super.spawnableCreatureList.clear();
      super.topBlock = Blocks.stone;
      super.fillerBlock = Blocks.stone;
      super.theBiomeDecorator.treesPerChunk = -999;
      super.theBiomeDecorator.deadBushPerChunk = 0;
      super.theBiomeDecorator.reedsPerChunk = 0;
      super.theBiomeDecorator.cactiPerChunk = 0;
   }
}
