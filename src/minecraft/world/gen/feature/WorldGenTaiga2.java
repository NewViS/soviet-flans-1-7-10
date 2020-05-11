package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenTaiga2 extends WorldGenAbstractTree {

   public WorldGenTaiga2(boolean var1) {
      super(var1);
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      int var6 = var2.nextInt(4) + 6;
      int var7 = 1 + var2.nextInt(2);
      int var8 = var6 - var7;
      int var9 = 2 + var2.nextInt(2);
      boolean var10 = true;
      if(var4 >= 1 && var4 + var6 + 1 <= 256) {
         int var13;
         int var22;
         for(int var11 = var4; var11 <= var4 + 1 + var6 && var10; ++var11) {
            boolean var12 = true;
            if(var11 - var4 < var7) {
               var22 = 0;
            } else {
               var22 = var9;
            }

            for(var13 = var3 - var22; var13 <= var3 + var22 && var10; ++var13) {
               for(int var14 = var5 - var22; var14 <= var5 + var22 && var10; ++var14) {
                  if(var11 >= 0 && var11 < 256) {
                     Block var15 = var1.getBlock(var13, var11, var14);
                     if(var15.getMaterial() != Material.air && var15.getMaterial() != Material.leaves) {
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
            Block var21 = var1.getBlock(var3, var4 - 1, var5);
            if((var21 == Blocks.grass || var21 == Blocks.dirt || var21 == Blocks.farmland) && var4 < 256 - var6 - 1) {
               this.func_150515_a(var1, var3, var4 - 1, var5, Blocks.dirt);
               var22 = var2.nextInt(2);
               var13 = 1;
               byte var23 = 0;

               int var16;
               int var24;
               for(var24 = 0; var24 <= var8; ++var24) {
                  var16 = var4 + var6 - var24;

                  for(int var17 = var3 - var22; var17 <= var3 + var22; ++var17) {
                     int var18 = var17 - var3;

                     for(int var19 = var5 - var22; var19 <= var5 + var22; ++var19) {
                        int var20 = var19 - var5;
                        if((Math.abs(var18) != var22 || Math.abs(var20) != var22 || var22 <= 0) && !var1.getBlock(var17, var16, var19).func_149730_j()) {
                           this.setBlockAndNotifyAdequately(var1, var17, var16, var19, Blocks.leaves, 1);
                        }
                     }
                  }

                  if(var22 >= var13) {
                     var22 = var23;
                     var23 = 1;
                     ++var13;
                     if(var13 > var9) {
                        var13 = var9;
                     }
                  } else {
                     ++var22;
                  }
               }

               var24 = var2.nextInt(3);

               for(var16 = 0; var16 < var6 - var24; ++var16) {
                  Block var25 = var1.getBlock(var3, var4 + var16, var5);
                  if(var25.getMaterial() == Material.air || var25.getMaterial() == Material.leaves) {
                     this.setBlockAndNotifyAdequately(var1, var3, var4 + var16, var5, Blocks.log, 1);
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
