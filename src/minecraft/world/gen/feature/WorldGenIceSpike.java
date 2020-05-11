package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenIceSpike extends WorldGenerator {

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      while(var1.isAirBlock(var3, var4, var5) && var4 > 2) {
         --var4;
      }

      if(var1.getBlock(var3, var4, var5) != Blocks.snow) {
         return false;
      } else {
         var4 += var2.nextInt(4);
         int var6 = var2.nextInt(4) + 7;
         int var7 = var6 / 4 + var2.nextInt(2);
         if(var7 > 1 && var2.nextInt(60) == 0) {
            var4 += 10 + var2.nextInt(30);
         }

         int var8;
         int var10;
         int var11;
         for(var8 = 0; var8 < var6; ++var8) {
            float var9 = (1.0F - (float)var8 / (float)var6) * (float)var7;
            var10 = MathHelper.ceiling_float_int(var9);

            for(var11 = -var10; var11 <= var10; ++var11) {
               float var12 = (float)MathHelper.abs_int(var11) - 0.25F;

               for(int var13 = -var10; var13 <= var10; ++var13) {
                  float var14 = (float)MathHelper.abs_int(var13) - 0.25F;
                  if((var11 == 0 && var13 == 0 || var12 * var12 + var14 * var14 <= var9 * var9) && (var11 != -var10 && var11 != var10 && var13 != -var10 && var13 != var10 || var2.nextFloat() <= 0.75F)) {
                     Block var15 = var1.getBlock(var3 + var11, var4 + var8, var5 + var13);
                     if(var15.getMaterial() == Material.air || var15 == Blocks.dirt || var15 == Blocks.snow || var15 == Blocks.ice) {
                        this.func_150515_a(var1, var3 + var11, var4 + var8, var5 + var13, Blocks.packed_ice);
                     }

                     if(var8 != 0 && var10 > 1) {
                        var15 = var1.getBlock(var3 + var11, var4 - var8, var5 + var13);
                        if(var15.getMaterial() == Material.air || var15 == Blocks.dirt || var15 == Blocks.snow || var15 == Blocks.ice) {
                           this.func_150515_a(var1, var3 + var11, var4 - var8, var5 + var13, Blocks.packed_ice);
                        }
                     }
                  }
               }
            }
         }

         var8 = var7 - 1;
         if(var8 < 0) {
            var8 = 0;
         } else if(var8 > 1) {
            var8 = 1;
         }

         for(int var16 = -var8; var16 <= var8; ++var16) {
            var10 = -var8;

            while(var10 <= var8) {
               var11 = var4 - 1;
               int var17 = 50;
               if(Math.abs(var16) == 1 && Math.abs(var10) == 1) {
                  var17 = var2.nextInt(5);
               }

               while(true) {
                  if(var11 > 50) {
                     Block var18 = var1.getBlock(var3 + var16, var11, var5 + var10);
                     if(var18.getMaterial() == Material.air || var18 == Blocks.dirt || var18 == Blocks.snow || var18 == Blocks.ice || var18 == Blocks.packed_ice) {
                        this.func_150515_a(var1, var3 + var16, var11, var5 + var10, Blocks.packed_ice);
                        --var11;
                        --var17;
                        if(var17 <= 0) {
                           var11 -= var2.nextInt(5) + 1;
                           var17 = var2.nextInt(5);
                        }
                        continue;
                     }
                  }

                  ++var10;
                  break;
               }
            }
         }

         return true;
      }
   }
}
