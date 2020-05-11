package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$Height;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.biome.BiomeGenForest$1;
import net.minecraft.world.biome.BiomeGenForest$2;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;

public class BiomeGenForest extends BiomeGenBase {

   private int field_150632_aF;
   protected static final WorldGenForest field_150629_aC = new WorldGenForest(false, true);
   protected static final WorldGenForest field_150630_aD = new WorldGenForest(false, false);
   protected static final WorldGenCanopyTree field_150631_aE = new WorldGenCanopyTree(false);


   public BiomeGenForest(int var1, int var2) {
      super(var1);
      this.field_150632_aF = var2;
      super.theBiomeDecorator.treesPerChunk = 10;
      super.theBiomeDecorator.grassPerChunk = 2;
      if(this.field_150632_aF == 1) {
         super.theBiomeDecorator.treesPerChunk = 6;
         super.theBiomeDecorator.flowersPerChunk = 100;
         super.theBiomeDecorator.grassPerChunk = 1;
      }

      this.func_76733_a(5159473);
      this.setTemperatureRainfall(0.7F, 0.8F);
      if(this.field_150632_aF == 2) {
         super.field_150609_ah = 353825;
         super.color = 3175492;
         this.setTemperatureRainfall(0.6F, 0.6F);
      }

      if(this.field_150632_aF == 0) {
         super.spawnableCreatureList.add(new BiomeGenBase$SpawnListEntry(EntityWolf.class, 5, 4, 4));
      }

      if(this.field_150632_aF == 3) {
         super.theBiomeDecorator.treesPerChunk = -999;
      }

   }

   protected BiomeGenBase func_150557_a(int var1, boolean var2) {
      if(this.field_150632_aF == 2) {
         super.field_150609_ah = 353825;
         super.color = var1;
         if(var2) {
            super.field_150609_ah = (super.field_150609_ah & 16711422) >> 1;
         }

         return this;
      } else {
         return super.func_150557_a(var1, var2);
      }
   }

   public WorldGenAbstractTree func_150567_a(Random var1) {
      return (WorldGenAbstractTree)(this.field_150632_aF == 3 && var1.nextInt(3) > 0?field_150631_aE:(this.field_150632_aF != 2 && var1.nextInt(5) != 0?super.worldGeneratorTrees:field_150630_aD));
   }

   public String func_150572_a(Random var1, int var2, int var3, int var4) {
      if(this.field_150632_aF == 1) {
         double var5 = MathHelper.clamp_double((1.0D + BiomeGenBase.plantNoise.func_151601_a((double)var2 / 48.0D, (double)var4 / 48.0D)) / 2.0D, 0.0D, 0.9999D);
         int var7 = (int)(var5 * (double)BlockFlower.field_149859_a.length);
         if(var7 == 1) {
            var7 = 0;
         }

         return BlockFlower.field_149859_a[var7];
      } else {
         return super.func_150572_a(var1, var2, var3, var4);
      }
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      int var5;
      int var6;
      int var7;
      int var8;
      int var9;
      if(this.field_150632_aF == 3) {
         for(var5 = 0; var5 < 4; ++var5) {
            for(var6 = 0; var6 < 4; ++var6) {
               var7 = var3 + var5 * 4 + 1 + 8 + var2.nextInt(3);
               var8 = var4 + var6 * 4 + 1 + 8 + var2.nextInt(3);
               var9 = var1.getHeightValue(var7, var8);
               if(var2.nextInt(20) == 0) {
                  WorldGenBigMushroom var10 = new WorldGenBigMushroom();
                  var10.generate(var1, var2, var7, var9, var8);
               } else {
                  WorldGenAbstractTree var12 = this.func_150567_a(var2);
                  var12.setScale(1.0D, 1.0D, 1.0D);
                  if(var12.generate(var1, var2, var7, var9, var8)) {
                     var12.func_150524_b(var1, var2, var7, var9, var8);
                  }
               }
            }
         }
      }

      var5 = var2.nextInt(5) - 3;
      if(this.field_150632_aF == 1) {
         var5 += 2;
      }

      var6 = 0;

      while(var6 < var5) {
         var7 = var2.nextInt(3);
         if(var7 == 0) {
            BiomeGenBase.genTallFlowers.func_150548_a(1);
         } else if(var7 == 1) {
            BiomeGenBase.genTallFlowers.func_150548_a(4);
         } else if(var7 == 2) {
            BiomeGenBase.genTallFlowers.func_150548_a(5);
         }

         var8 = 0;

         while(true) {
            if(var8 < 5) {
               var9 = var3 + var2.nextInt(16) + 8;
               int var13 = var4 + var2.nextInt(16) + 8;
               int var11 = var2.nextInt(var1.getHeightValue(var9, var13) + 32);
               if(!BiomeGenBase.genTallFlowers.generate(var1, var2, var9, var11, var13)) {
                  ++var8;
                  continue;
               }
            }

            ++var6;
            break;
         }
      }

      super.decorate(var1, var2, var3, var4);
   }

   public int getBiomeGrassColor(int var1, int var2, int var3) {
      int var4 = super.getBiomeGrassColor(var1, var2, var3);
      return this.field_150632_aF == 3?(var4 & 16711422) + 2634762 >> 1:var4;
   }

   protected BiomeGenBase createMutation() {
      if(super.biomeID == BiomeGenBase.forest.biomeID) {
         BiomeGenForest var1 = new BiomeGenForest(super.biomeID + 128, 1);
         var1.setHeight(new BiomeGenBase$Height(super.rootHeight, super.heightVariation + 0.2F));
         var1.setBiomeName("Flower Forest");
         var1.func_150557_a(6976549, true);
         var1.func_76733_a(8233509);
         return var1;
      } else {
         return (BiomeGenBase)(super.biomeID != BiomeGenBase.birchForest.biomeID && super.biomeID != BiomeGenBase.birchForestHills.biomeID?new BiomeGenForest$2(this, super.biomeID + 128, this):new BiomeGenForest$1(this, super.biomeID + 128, this));
      }
   }

}
