package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenHills extends BiomeGenBase {

   private WorldGenerator theWorldGenerator;
   private WorldGenTaiga2 field_150634_aD;
   private int field_150635_aE;
   private int field_150636_aF;
   private int field_150637_aG;
   private int field_150638_aH;


   protected BiomeGenHills(int var1, boolean var2) {
      super(var1);
      this.theWorldGenerator = new WorldGenMinable(Blocks.monster_egg, 8);
      this.field_150634_aD = new WorldGenTaiga2(false);
      this.field_150635_aE = 0;
      this.field_150636_aF = 1;
      this.field_150637_aG = 2;
      this.field_150638_aH = this.field_150635_aE;
      if(var2) {
         super.theBiomeDecorator.treesPerChunk = 3;
         this.field_150638_aH = this.field_150636_aF;
      }

   }

   public WorldGenAbstractTree func_150567_a(Random var1) {
      return (WorldGenAbstractTree)(var1.nextInt(3) > 0?this.field_150634_aD:super.func_150567_a(var1));
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      super.decorate(var1, var2, var3, var4);
      int var5 = 3 + var2.nextInt(6);

      int var6;
      int var7;
      int var8;
      for(var6 = 0; var6 < var5; ++var6) {
         var7 = var3 + var2.nextInt(16);
         var8 = var2.nextInt(28) + 4;
         int var9 = var4 + var2.nextInt(16);
         if(var1.getBlock(var7, var8, var9) == Blocks.stone) {
            var1.setBlock(var7, var8, var9, Blocks.emerald_ore, 0, 2);
         }
      }

      for(var5 = 0; var5 < 7; ++var5) {
         var6 = var3 + var2.nextInt(16);
         var7 = var2.nextInt(64);
         var8 = var4 + var2.nextInt(16);
         this.theWorldGenerator.generate(var1, var2, var6, var7, var8);
      }

   }

   public void genTerrainBlocks(World var1, Random var2, Block[] var3, byte[] var4, int var5, int var6, double var7) {
      super.topBlock = Blocks.grass;
      super.field_150604_aj = 0;
      super.fillerBlock = Blocks.dirt;
      if((var7 < -1.0D || var7 > 2.0D) && this.field_150638_aH == this.field_150637_aG) {
         super.topBlock = Blocks.gravel;
         super.fillerBlock = Blocks.gravel;
      } else if(var7 > 1.0D && this.field_150638_aH != this.field_150636_aF) {
         super.topBlock = Blocks.stone;
         super.fillerBlock = Blocks.stone;
      }

      this.genBiomeTerrain(var1, var2, var3, var4, var5, var6, var7);
   }

   private BiomeGenHills mutateHills(BiomeGenBase var1) {
      this.field_150638_aH = this.field_150637_aG;
      this.func_150557_a(var1.color, true);
      this.setBiomeName(var1.biomeName + " M");
      this.setHeight(new BiomeGenBase$Height(var1.rootHeight, var1.heightVariation));
      this.setTemperatureRainfall(var1.temperature, var1.rainfall);
      return this;
   }

   protected BiomeGenBase createMutation() {
      return (new BiomeGenHills(super.biomeID + 128, false)).mutateHills(this);
   }
}
