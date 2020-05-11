package net.minecraft.world.biome;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenSpikes;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeEndDecorator extends BiomeDecorator {

   protected WorldGenerator spikeGen;


   public BiomeEndDecorator() {
      this.spikeGen = new WorldGenSpikes(Blocks.end_stone);
   }

   protected void genDecorations(BiomeGenBase var1) {
      this.generateOres();
      if(super.randomGenerator.nextInt(5) == 0) {
         int var2 = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         int var3 = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         int var4 = super.currentWorld.getTopSolidOrLiquidBlock(var2, var3);
         this.spikeGen.generate(super.currentWorld, super.randomGenerator, var2, var4, var3);
      }

      if(super.chunk_X == 0 && super.chunk_Z == 0) {
         EntityDragon var5 = new EntityDragon(super.currentWorld);
         var5.setLocationAndAngles(0.0D, 128.0D, 0.0D, super.randomGenerator.nextFloat() * 360.0F, 0.0F);
         super.currentWorld.spawnEntityInWorld(var5);
      }

   }
}
