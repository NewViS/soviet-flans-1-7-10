package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWaterlily extends WorldGenerator {

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      for(int var6 = 0; var6 < 10; ++var6) {
         int var7 = var3 + var2.nextInt(8) - var2.nextInt(8);
         int var8 = var4 + var2.nextInt(4) - var2.nextInt(4);
         int var9 = var5 + var2.nextInt(8) - var2.nextInt(8);
         if(var1.isAirBlock(var7, var8, var9) && Blocks.waterlily.canPlaceBlockAt(var1, var7, var8, var9)) {
            var1.setBlock(var7, var8, var9, Blocks.waterlily, 0, 2);
         }
      }

      return true;
   }
}
