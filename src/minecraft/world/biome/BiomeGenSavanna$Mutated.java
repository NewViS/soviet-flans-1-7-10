package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

public class BiomeGenSavanna$Mutated extends BiomeGenMutated {

   public BiomeGenSavanna$Mutated(int var1, BiomeGenBase var2) {
      super(var1, var2);
      super.theBiomeDecorator.treesPerChunk = 2;
      super.theBiomeDecorator.flowersPerChunk = 2;
      super.theBiomeDecorator.grassPerChunk = 5;
   }

   public void genTerrainBlocks(World var1, Random var2, Block[] var3, byte[] var4, int var5, int var6, double var7) {
      super.topBlock = Blocks.grass;
      super.field_150604_aj = 0;
      super.fillerBlock = Blocks.dirt;
      if(var7 > 1.75D) {
         super.topBlock = Blocks.stone;
         super.fillerBlock = Blocks.stone;
      } else if(var7 > -0.5D) {
         super.topBlock = Blocks.dirt;
         super.field_150604_aj = 1;
      }

      this.genBiomeTerrain(var1, var2, var3, var4, var5, var6, var7);
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      super.theBiomeDecorator.decorateChunk(var1, var2, this, var3, var4);
   }
}
