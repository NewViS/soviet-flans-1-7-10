package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDesertWells;

public class BiomeGenDesert extends BiomeGenBase {

   public BiomeGenDesert(int var1) {
      super(var1);
      super.spawnableCreatureList.clear();
      super.topBlock = Blocks.sand;
      super.fillerBlock = Blocks.sand;
      super.theBiomeDecorator.treesPerChunk = -999;
      super.theBiomeDecorator.deadBushPerChunk = 2;
      super.theBiomeDecorator.reedsPerChunk = 50;
      super.theBiomeDecorator.cactiPerChunk = 10;
      super.spawnableCreatureList.clear();
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      super.decorate(var1, var2, var3, var4);
      if(var2.nextInt(1000) == 0) {
         int var5 = var3 + var2.nextInt(16) + 8;
         int var6 = var4 + var2.nextInt(16) + 8;
         WorldGenDesertWells var7 = new WorldGenDesertWells();
         var7.generate(var1, var2, var5, var1.getHeightValue(var5, var6) + 1, var6);
      }

   }
}
