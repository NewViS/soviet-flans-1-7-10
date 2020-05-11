package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenSwamp extends BiomeGenBase {

   protected BiomeGenSwamp(int var1) {
      super(var1);
      super.theBiomeDecorator.treesPerChunk = 2;
      super.theBiomeDecorator.flowersPerChunk = 1;
      super.theBiomeDecorator.deadBushPerChunk = 1;
      super.theBiomeDecorator.mushroomsPerChunk = 8;
      super.theBiomeDecorator.reedsPerChunk = 10;
      super.theBiomeDecorator.clayPerChunk = 1;
      super.theBiomeDecorator.waterlilyPerChunk = 4;
      super.theBiomeDecorator.sandPerChunk2 = 0;
      super.theBiomeDecorator.sandPerChunk = 0;
      super.theBiomeDecorator.grassPerChunk = 5;
      super.waterColorMultiplier = 14745518;
      super.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntitySlime.class, 1, 1, 1));
   }

   public WorldGenAbstractTree func_150567_a(Random var1) {
      return super.worldGeneratorSwamp;
   }

   public int getBiomeGrassColor(int var1, int var2, int var3) {
      double var4 = BiomeGenBase.plantNoise.func_151601_a((double)var1 * 0.0225D, (double)var3 * 0.0225D);
      return var4 < -0.1D?5011004:6975545;
   }

   public int getBiomeFoliageColor(int var1, int var2, int var3) {
      return 6975545;
   }

   public String func_150572_a(Random var1, int var2, int var3, int var4) {
      return BlockFlower.field_149859_a[1];
   }

   public void genTerrainBlocks(World var1, Random var2, Block[] var3, byte[] var4, int var5, int var6, double var7) {
      double var9 = BiomeGenBase.plantNoise.func_151601_a((double)var5 * 0.25D, (double)var6 * 0.25D);
      if(var9 > 0.0D) {
         int var11 = var5 & 15;
         int var12 = var6 & 15;
         int var13 = var3.length / 256;

         for(int var14 = 255; var14 >= 0; --var14) {
            int var15 = (var12 * 16 + var11) * var13 + var14;
            if(var3[var15] == null || var3[var15].getMaterial() != Material.air) {
               if(var14 == 62 && var3[var15] != Blocks.water) {
                  var3[var15] = Blocks.water;
                  if(var9 < 0.12D) {
                     var3[var15 + 1] = Blocks.waterlily;
                  }
               }
               break;
            }
         }
      }

      this.genBiomeTerrain(var1, var2, var3, var4, var5, var6, var7);
   }
}
