package de.ItsAMysterious.mods.reallifemod.core.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Blocks;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class OreGenerator implements IWorldGenerator {

   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
      switch(world.provider.dimensionId) {
      case -1:
         this.genNether();
         break;
      case 0:
         this.genWorld(world, random, chunkX * 16, chunkZ * 16);
         break;
      case 1:
         this.genEnd();
      }

   }

   private void genWorld(World world, Random rand, int ChunkX, int ChunkZ) {
      for(int i = 0; i < 4; ++i) {
         int randPosX = ChunkX + rand.nextInt(16);
         int randPosY = rand.nextInt(64);
         int randPosZ = ChunkZ + rand.nextInt(16);
         (new WorldGenMinable(RealLifeMod_Blocks.Marmor, 0, 30, Blocks.stone)).generate(world, rand, randPosX, randPosY, randPosZ);
      }

   }

   private void genEnd() {}

   private void genNether() {}
}
