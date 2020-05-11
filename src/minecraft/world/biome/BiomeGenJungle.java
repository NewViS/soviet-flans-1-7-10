package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenJungle extends BiomeGenBase {

   private boolean field_150614_aC;


   public BiomeGenJungle(int var1, boolean var2) {
      super(var1);
      this.field_150614_aC = var2;
      if(var2) {
         super.theBiomeDecorator.treesPerChunk = 2;
      } else {
         super.theBiomeDecorator.treesPerChunk = 50;
      }

      super.theBiomeDecorator.grassPerChunk = 25;
      super.theBiomeDecorator.flowersPerChunk = 4;
      if(!var2) {
         super.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntityOcelot.class, 2, 1, 1));
      }

      super.spawnableCreatureList.add(new BiomeGenBase$SpawnListEntry(EntityChicken.class, 10, 4, 4));
   }

   public WorldGenAbstractTree func_150567_a(Random var1) {
      return (WorldGenAbstractTree)(var1.nextInt(10) == 0?super.worldGeneratorBigTree:(var1.nextInt(2) == 0?new WorldGenShrub(3, 0):(!this.field_150614_aC && var1.nextInt(3) == 0?new WorldGenMegaJungle(false, 10, 20, 3, 3):new WorldGenTrees(false, 4 + var1.nextInt(7), 3, 3, true))));
   }

   public WorldGenerator getRandomWorldGenForGrass(Random var1) {
      return var1.nextInt(4) == 0?new WorldGenTallGrass(Blocks.tallgrass, 2):new WorldGenTallGrass(Blocks.tallgrass, 1);
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      super.decorate(var1, var2, var3, var4);
      int var5 = var3 + var2.nextInt(16) + 8;
      int var6 = var4 + var2.nextInt(16) + 8;
      int var7 = var2.nextInt(var1.getHeightValue(var5, var6) * 2);
      (new WorldGenMelon()).generate(var1, var2, var5, var7, var6);
      WorldGenVines var10 = new WorldGenVines();

      for(var6 = 0; var6 < 50; ++var6) {
         var7 = var3 + var2.nextInt(16) + 8;
         short var8 = 128;
         int var9 = var4 + var2.nextInt(16) + 8;
         var10.generate(var1, var2, var7, var8, var9);
      }

   }
}
