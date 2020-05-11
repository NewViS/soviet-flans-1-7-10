package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenTaiga1 extends WorldGenAbstractTree {

   public WorldGenTaiga1() {
      super(false);
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      int var6 = var2.nextInt(5) + 7;
      int var7 = var6 - var2.nextInt(2) - 3;
      int var8 = var6 - var7;
      int var9 = 1 + var2.nextInt(var8 + 1);
      boolean var10 = true;
      if(var4 >= 1 && var4 + var6 + 1 <= 256) {
         int var13;
         int var14;
         int var19;
         for(int var11 = var4; var11 <= var4 + 1 + var6 && var10; ++var11) {
            boolean var12 = true;
            if(var11 - var4 < var7) {
               var19 = 0;
            } else {
               var19 = var9;
            }

            for(var13 = var3 - var19; var13 <= var3 + var19 && var10; ++var13) {
               for(var14 = var5 - var19; var14 <= var5 + var19 && var10; ++var14) {
                  if(var11 >= 0 && var11 < 256) {
                     Block var15 = var1.getBlock(var13, var11, var14);
                     if(!this.func_150523_a(var15)) {
                        var10 = false;
                     }
                  } else {
                     var10 = false;
                  }
               }
            }
         }

         if(!var10) {
            return false;
         } else {
            Block var18 = var1.getBlock(var3, var4 - 1, var5);
            if((var18 == Blocks.grass || var18 == Blocks.dirt) && var4 < 256 - var6 - 1) {
               this.func_150515_a(var1, var3, var4 - 1, var5, Blocks.dirt);
               var19 = 0;

               for(var13 = var4 + var6; var13 >= var4 + var7; --var13) {
                  for(var14 = var3 - var19; var14 <= var3 + var19; ++var14) {
                     int var21 = var14 - var3;

                     for(int var16 = var5 - var19; var16 <= var5 + var19; ++var16) {
                        int var17 = var16 - var5;
                        if((Math.abs(var21) != var19 || Math.abs(var17) != var19 || var19 <= 0) && !var1.getBlock(var14, var13, var16).func_149730_j()) {
                           this.setBlockAndNotifyAdequately(var1, var14, var13, var16, Blocks.leaves, 1);
                        }
                     }
                  }

                  if(var19 >= 1 && var13 == var4 + var7 + 1) {
                     --var19;
                  } else if(var19 < var9) {
                     ++var19;
                  }
               }

               for(var13 = 0; var13 < var6 - 1; ++var13) {
                  Block var20 = var1.getBlock(var3, var4 + var13, var5);
                  if(var20.getMaterial() == Material.air || var20.getMaterial() == Material.leaves) {
                     this.setBlockAndNotifyAdequately(var1, var3, var4 + var13, var5, Blocks.log, 1);
                  }
               }

               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }
}
