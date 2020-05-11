package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;

public class BiomeGenPlains extends BiomeGenBase {

   protected boolean field_150628_aC;


   protected BiomeGenPlains(int var1) {
      super(var1);
      this.setTemperatureRainfall(0.8F, 0.4F);
      this.setHeight(BiomeGenBase.height_LowPlains);
      super.spawnableCreatureList.add(new BiomeGenBase$SpawnListEntry(EntityHorse.class, 5, 2, 6));
      super.theBiomeDecorator.treesPerChunk = -999;
      super.theBiomeDecorator.flowersPerChunk = 4;
      super.theBiomeDecorator.grassPerChunk = 10;
   }

   public String func_150572_a(Random var1, int var2, int var3, int var4) {
      double var5 = BiomeGenBase.plantNoise.func_151601_a((double)var2 / 200.0D, (double)var4 / 200.0D);
      int var7;
      if(var5 < -0.8D) {
         var7 = var1.nextInt(4);
         return BlockFlower.field_149859_a[4 + var7];
      } else if(var1.nextInt(3) > 0) {
         var7 = var1.nextInt(3);
         return var7 == 0?BlockFlower.field_149859_a[0]:(var7 == 1?BlockFlower.field_149859_a[3]:BlockFlower.field_149859_a[8]);
      } else {
         return BlockFlower.field_149858_b[0];
      }
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      double var5 = BiomeGenBase.plantNoise.func_151601_a((double)(var3 + 8) / 200.0D, (double)(var4 + 8) / 200.0D);
      int var7;
      int var8;
      int var9;
      int var10;
      if(var5 < -0.8D) {
         super.theBiomeDecorator.flowersPerChunk = 15;
         super.theBiomeDecorator.grassPerChunk = 5;
      } else {
         super.theBiomeDecorator.flowersPerChunk = 4;
         super.theBiomeDecorator.grassPerChunk = 10;
         BiomeGenBase.genTallFlowers.func_150548_a(2);

         for(var7 = 0; var7 < 7; ++var7) {
            var8 = var3 + var2.nextInt(16) + 8;
            var9 = var4 + var2.nextInt(16) + 8;
            var10 = var2.nextInt(var1.getHeightValue(var8, var9) + 32);
            BiomeGenBase.genTallFlowers.generate(var1, var2, var8, var10, var9);
         }
      }

      if(this.field_150628_aC) {
         BiomeGenBase.genTallFlowers.func_150548_a(0);

         for(var7 = 0; var7 < 10; ++var7) {
            var8 = var3 + var2.nextInt(16) + 8;
            var9 = var4 + var2.nextInt(16) + 8;
            var10 = var2.nextInt(var1.getHeightValue(var8, var9) + 32);
            BiomeGenBase.genTallFlowers.generate(var1, var2, var8, var10, var9);
         }
      }

      super.decorate(var1, var2, var3, var4);
   }

   protected BiomeGenBase createMutation() {
      BiomeGenPlains var1 = new BiomeGenPlains(super.biomeID + 128);
      var1.setBiomeName("Sunflower Plains");
      var1.field_150628_aC = true;
      var1.setColor(9286496);
      var1.field_150609_ah = 14273354;
      return var1;
   }
}
