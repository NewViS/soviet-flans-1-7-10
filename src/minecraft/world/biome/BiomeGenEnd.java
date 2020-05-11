package net.minecraft.world.biome;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeEndDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;

public class BiomeGenEnd extends BiomeGenBase {

   public BiomeGenEnd(int var1) {
      super(var1);
      super.spawnableMonsterList.clear();
      super.spawnableCreatureList.clear();
      super.spawnableWaterCreatureList.clear();
      super.spawnableCaveCreatureList.clear();
      super.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntityEnderman.class, 10, 4, 4));
      super.topBlock = Blocks.dirt;
      super.fillerBlock = Blocks.dirt;
      super.theBiomeDecorator = new BiomeEndDecorator();
   }

   public int getSkyColorByTemp(float var1) {
      return 0;
   }
}
