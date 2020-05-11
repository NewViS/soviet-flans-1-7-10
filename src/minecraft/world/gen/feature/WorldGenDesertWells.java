package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDesertWells extends WorldGenerator {

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      while(var1.isAirBlock(var3, var4, var5) && var4 > 2) {
         --var4;
      }

      if(var1.getBlock(var3, var4, var5) != Blocks.sand) {
         return false;
      } else {
         int var6;
         int var7;
         for(var6 = -2; var6 <= 2; ++var6) {
            for(var7 = -2; var7 <= 2; ++var7) {
               if(var1.isAirBlock(var3 + var6, var4 - 1, var5 + var7) && var1.isAirBlock(var3 + var6, var4 - 2, var5 + var7)) {
                  return false;
               }
            }
         }

         for(var6 = -1; var6 <= 0; ++var6) {
            for(var7 = -2; var7 <= 2; ++var7) {
               for(int var8 = -2; var8 <= 2; ++var8) {
                  var1.setBlock(var3 + var7, var4 + var6, var5 + var8, Blocks.sandstone, 0, 2);
               }
            }
         }

         var1.setBlock(var3, var4, var5, Blocks.flowing_water, 0, 2);
         var1.setBlock(var3 - 1, var4, var5, Blocks.flowing_water, 0, 2);
         var1.setBlock(var3 + 1, var4, var5, Blocks.flowing_water, 0, 2);
         var1.setBlock(var3, var4, var5 - 1, Blocks.flowing_water, 0, 2);
         var1.setBlock(var3, var4, var5 + 1, Blocks.flowing_water, 0, 2);

         for(var6 = -2; var6 <= 2; ++var6) {
            for(var7 = -2; var7 <= 2; ++var7) {
               if(var6 == -2 || var6 == 2 || var7 == -2 || var7 == 2) {
                  var1.setBlock(var3 + var6, var4 + 1, var5 + var7, Blocks.sandstone, 0, 2);
               }
            }
         }

         var1.setBlock(var3 + 2, var4 + 1, var5, Blocks.stone_slab, 1, 2);
         var1.setBlock(var3 - 2, var4 + 1, var5, Blocks.stone_slab, 1, 2);
         var1.setBlock(var3, var4 + 1, var5 + 2, Blocks.stone_slab, 1, 2);
         var1.setBlock(var3, var4 + 1, var5 - 2, Blocks.stone_slab, 1, 2);

         for(var6 = -1; var6 <= 1; ++var6) {
            for(var7 = -1; var7 <= 1; ++var7) {
               if(var6 == 0 && var7 == 0) {
                  var1.setBlock(var3 + var6, var4 + 4, var5 + var7, Blocks.sandstone, 0, 2);
               } else {
                  var1.setBlock(var3 + var6, var4 + 4, var5 + var7, Blocks.stone_slab, 1, 2);
               }
            }
         }

         for(var6 = 1; var6 <= 3; ++var6) {
            var1.setBlock(var3 - 1, var4 + var6, var5 - 1, Blocks.sandstone, 0, 2);
            var1.setBlock(var3 - 1, var4 + var6, var5 + 1, Blocks.sandstone, 0, 2);
            var1.setBlock(var3 + 1, var4 + var6, var5 - 1, Blocks.sandstone, 0, 2);
            var1.setBlock(var3 + 1, var4 + var6, var5 + 1, Blocks.sandstone, 0, 2);
         }

         return true;
      }
   }
}
