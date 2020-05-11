package net.minecraft.world.biome;

import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;

public class BiomeGenMushroomIsland extends BiomeGenBase {

   public BiomeGenMushroomIsland(int var1) {
      super(var1);
      super.theBiomeDecorator.treesPerChunk = -100;
      super.theBiomeDecorator.flowersPerChunk = -100;
      super.theBiomeDecorator.grassPerChunk = -100;
      super.theBiomeDecorator.mushroomsPerChunk = 1;
      super.theBiomeDecorator.bigMushroomsPerChunk = 1;
      super.topBlock = Blocks.mycelium;
      super.spawnableMonsterList.clear();
      super.spawnableCreatureList.clear();
      super.spawnableWaterCreatureList.clear();
      super.spawnableCreatureList.add(new BiomeGenBase$SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
   }
}
