package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenVines extends WorldGenerator {

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      int var6 = var3;

      for(int var7 = var5; var4 < 128; ++var4) {
         if(var1.isAirBlock(var3, var4, var5)) {
            for(int var8 = 2; var8 <= 5; ++var8) {
               if(Blocks.vine.canPlaceBlockOnSide(var1, var3, var4, var5, var8)) {
                  var1.setBlock(var3, var4, var5, Blocks.vine, 1 << Direction.facingToDirection[Facing.oppositeSide[var8]], 2);
                  break;
               }
            }
         } else {
            var3 = var6 + var2.nextInt(4) - var2.nextInt(4);
            var5 = var7 + var2.nextInt(4) - var2.nextInt(4);
         }
      }

      return true;
   }
}
