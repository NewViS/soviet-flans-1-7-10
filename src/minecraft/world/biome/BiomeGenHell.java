package net.minecraft.world.biome;

import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;

public class BiomeGenHell extends BiomeGenBase {

   public BiomeGenHell(int var1) {
      super(var1);
      super.spawnableMonsterList.clear();
      super.spawnableCreatureList.clear();
      super.spawnableWaterCreatureList.clear();
      super.spawnableCaveCreatureList.clear();
      super.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntityGhast.class, 50, 4, 4));
      super.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
      super.spawnableMonsterList.add(new BiomeGenBase$SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
   }
}
