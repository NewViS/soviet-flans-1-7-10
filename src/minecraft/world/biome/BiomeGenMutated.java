package net.minecraft.world.biome;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$TempCategory;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenMutated extends BiomeGenBase {

   protected BiomeGenBase baseBiome;


   public BiomeGenMutated(int var1, BiomeGenBase var2) {
      super(var1);
      this.baseBiome = var2;
      this.func_150557_a(var2.color, true);
      super.biomeName = var2.biomeName + " M";
      super.topBlock = var2.topBlock;
      super.fillerBlock = var2.fillerBlock;
      super.field_76754_C = var2.field_76754_C;
      super.rootHeight = var2.rootHeight;
      super.heightVariation = var2.heightVariation;
      super.temperature = var2.temperature;
      super.rainfall = var2.rainfall;
      super.waterColorMultiplier = var2.waterColorMultiplier;
      super.enableSnow = var2.enableSnow;
      super.enableRain = var2.enableRain;
      super.spawnableCreatureList = new ArrayList(var2.spawnableCreatureList);
      super.spawnableMonsterList = new ArrayList(var2.spawnableMonsterList);
      super.spawnableCaveCreatureList = new ArrayList(var2.spawnableCaveCreatureList);
      super.spawnableWaterCreatureList = new ArrayList(var2.spawnableWaterCreatureList);
      super.temperature = var2.temperature;
      super.rainfall = var2.rainfall;
      super.rootHeight = var2.rootHeight + 0.1F;
      super.heightVariation = var2.heightVariation + 0.2F;
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      this.baseBiome.theBiomeDecorator.decorateChunk(var1, var2, this, var3, var4);
   }

   public void genTerrainBlocks(World var1, Random var2, Block[] var3, byte[] var4, int var5, int var6, double var7) {
      this.baseBiome.genTerrainBlocks(var1, var2, var3, var4, var5, var6, var7);
   }

   public float getSpawningChance() {
      return this.baseBiome.getSpawningChance();
   }

   public WorldGenAbstractTree func_150567_a(Random var1) {
      return this.baseBiome.func_150567_a(var1);
   }

   public int getBiomeFoliageColor(int var1, int var2, int var3) {
      return this.baseBiome.getBiomeFoliageColor(var1, var2, var2);
   }

   public int getBiomeGrassColor(int var1, int var2, int var3) {
      return this.baseBiome.getBiomeGrassColor(var1, var2, var2);
   }

   public Class getBiomeClass() {
      return this.baseBiome.getBiomeClass();
   }

   public boolean isEqualTo(BiomeGenBase var1) {
      return this.baseBiome.isEqualTo(var1);
   }

   public BiomeGenBase$TempCategory getTempCategory() {
      return this.baseBiome.getTempCategory();
   }
}
