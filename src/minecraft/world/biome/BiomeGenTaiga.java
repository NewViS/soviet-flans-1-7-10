package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$Height;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenTaiga extends BiomeGenBase {

   private static final WorldGenTaiga1 field_150639_aC = new WorldGenTaiga1();
   private static final WorldGenTaiga2 field_150640_aD = new WorldGenTaiga2(false);
   private static final WorldGenMegaPineTree field_150641_aE = new WorldGenMegaPineTree(false, false);
   private static final WorldGenMegaPineTree field_150642_aF = new WorldGenMegaPineTree(false, true);
   private static final WorldGenBlockBlob field_150643_aG = new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0);
   private int field_150644_aH;


   public BiomeGenTaiga(int var1, int var2) {
      super(var1);
      this.field_150644_aH = var2;
      super.spawnableCreatureList.add(new BiomeGenBase$SpawnListEntry(EntityWolf.class, 8, 4, 4));
      super.theBiomeDecorator.treesPerChunk = 10;
      if(var2 != 1 && var2 != 2) {
         super.theBiomeDecorator.grassPerChunk = 1;
         super.theBiomeDecorator.mushroomsPerChunk = 1;
      } else {
         super.theBiomeDecorator.grassPerChunk = 7;
         super.theBiomeDecorator.deadBushPerChunk = 1;
         super.theBiomeDecorator.mushroomsPerChunk = 3;
      }

   }

   public WorldGenAbstractTree func_150567_a(Random var1) {
      return (WorldGenAbstractTree)((this.field_150644_aH == 1 || this.field_150644_aH == 2) && var1.nextInt(3) == 0?(this.field_150644_aH != 2 && var1.nextInt(13) != 0?field_150641_aE:field_150642_aF):(var1.nextInt(3) == 0?field_150639_aC:field_150640_aD));
   }

   public WorldGenerator getRandomWorldGenForGrass(Random var1) {
      return var1.nextInt(5) > 0?new WorldGenTallGrass(Blocks.tallgrass, 2):new WorldGenTallGrass(Blocks.tallgrass, 1);
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      int var5;
      int var6;
      int var7;
      int var8;
      if(this.field_150644_aH == 1 || this.field_150644_aH == 2) {
         var5 = var2.nextInt(3);

         for(var6 = 0; var6 < var5; ++var6) {
            var7 = var3 + var2.nextInt(16) + 8;
            var8 = var4 + var2.nextInt(16) + 8;
            int var9 = var1.getHeightValue(var7, var8);
            field_150643_aG.generate(var1, var2, var7, var9, var8);
         }
      }

      BiomeGenBase.genTallFlowers.func_150548_a(3);

      for(var5 = 0; var5 < 7; ++var5) {
         var6 = var3 + var2.nextInt(16) + 8;
         var7 = var4 + var2.nextInt(16) + 8;
         var8 = var2.nextInt(var1.getHeightValue(var6, var7) + 32);
         BiomeGenBase.genTallFlowers.generate(var1, var2, var6, var8, var7);
      }

      super.decorate(var1, var2, var3, var4);
   }

   public void genTerrainBlocks(World var1, Random var2, Block[] var3, byte[] var4, int var5, int var6, double var7) {
      if(this.field_150644_aH == 1 || this.field_150644_aH == 2) {
         super.topBlock = Blocks.grass;
         super.field_150604_aj = 0;
         super.fillerBlock = Blocks.dirt;
         if(var7 > 1.75D) {
            super.topBlock = Blocks.dirt;
            super.field_150604_aj = 1;
         } else if(var7 > -0.95D) {
            super.topBlock = Blocks.dirt;
            super.field_150604_aj = 2;
         }
      }

      this.genBiomeTerrain(var1, var2, var3, var4, var5, var6, var7);
   }

   protected BiomeGenBase createMutation() {
      return super.biomeID == BiomeGenBase.megaTaiga.biomeID?(new BiomeGenTaiga(super.biomeID + 128, 2)).func_150557_a(5858897, true).setBiomeName("Mega Spruce Taiga").func_76733_a(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(new BiomeGenBase$Height(super.rootHeight, super.heightVariation)):super.createMutation();
   }

}
