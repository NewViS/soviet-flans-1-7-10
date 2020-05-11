package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.biome.BiomeGenSavanna$Mutated;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;

public class BiomeGenSavanna extends BiomeGenBase {

   private static final WorldGenSavannaTree field_150627_aC = new WorldGenSavannaTree(false);


   protected BiomeGenSavanna(int var1) {
      super(var1);
      super.spawnableCreatureList.add(new BiomeGenBase$SpawnListEntry(EntityHorse.class, 1, 2, 6));
      super.theBiomeDecorator.treesPerChunk = 1;
      super.theBiomeDecorator.flowersPerChunk = 4;
      super.theBiomeDecorator.grassPerChunk = 20;
   }

   public WorldGenAbstractTree func_150567_a(Random var1) {
      return (WorldGenAbstractTree)(var1.nextInt(5) > 0?field_150627_aC:super.worldGeneratorTrees);
   }

   protected BiomeGenBase createMutation() {
      BiomeGenSavanna$Mutated var1 = new BiomeGenSavanna$Mutated(super.biomeID + 128, this);
      var1.temperature = (super.temperature + 1.0F) * 0.5F;
      var1.rootHeight = super.rootHeight * 0.5F + 0.3F;
      var1.heightVariation = super.heightVariation * 0.5F + 1.2F;
      return var1;
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      BiomeGenBase.genTallFlowers.func_150548_a(2);

      for(int var5 = 0; var5 < 7; ++var5) {
         int var6 = var3 + var2.nextInt(16) + 8;
         int var7 = var4 + var2.nextInt(16) + 8;
         int var8 = var2.nextInt(var1.getHeightValue(var6, var7) + 32);
         BiomeGenBase.genTallFlowers.generate(var1, var2, var6, var8, var7);
      }

      super.decorate(var1, var2, var3, var4);
   }

}
