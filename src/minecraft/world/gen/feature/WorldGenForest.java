package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenForest extends WorldGenAbstractTree {

   private boolean field_150531_a;


   public WorldGenForest(boolean var1, boolean var2) {
      super(var1);
      this.field_150531_a = var2;
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      int var6 = var2.nextInt(3) + 5;
      if(this.field_150531_a) {
         var6 += var2.nextInt(7);
      }

      boolean var7 = true;
      if(var4 >= 1 && var4 + var6 + 1 <= 256) {
         int var10;
         int var11;
         for(int var8 = var4; var8 <= var4 + 1 + var6; ++var8) {
            byte var9 = 1;
            if(var8 == var4) {
               var9 = 0;
            }

            if(var8 >= var4 + 1 + var6 - 2) {
               var9 = 2;
            }

            for(var10 = var3 - var9; var10 <= var3 + var9 && var7; ++var10) {
               for(var11 = var5 - var9; var11 <= var5 + var9 && var7; ++var11) {
                  if(var8 >= 0 && var8 < 256) {
                     Block var12 = var1.getBlock(var10, var8, var11);
                     if(!this.func_150523_a(var12)) {
                        var7 = false;
                     }
                  } else {
                     var7 = false;
                  }
               }
            }
         }

         if(!var7) {
            return false;
         } else {
            Block var17 = var1.getBlock(var3, var4 - 1, var5);
            if((var17 == Blocks.grass || var17 == Blocks.dirt || var17 == Blocks.farmland) && var4 < 256 - var6 - 1) {
               this.func_150515_a(var1, var3, var4 - 1, var5, Blocks.dirt);

               int var18;
               for(var18 = var4 - 3 + var6; var18 <= var4 + var6; ++var18) {
                  var10 = var18 - (var4 + var6);
                  var11 = 1 - var10 / 2;

                  for(int var20 = var3 - var11; var20 <= var3 + var11; ++var20) {
                     int var13 = var20 - var3;

                     for(int var14 = var5 - var11; var14 <= var5 + var11; ++var14) {
                        int var15 = var14 - var5;
                        if(Math.abs(var13) != var11 || Math.abs(var15) != var11 || var2.nextInt(2) != 0 && var10 != 0) {
                           Block var16 = var1.getBlock(var20, var18, var14);
                           if(var16.getMaterial() == Material.air || var16.getMaterial() == Material.leaves) {
                              this.setBlockAndNotifyAdequately(var1, var20, var18, var14, Blocks.leaves, 2);
                           }
                        }
                     }
                  }
               }

               for(var18 = 0; var18 < var6; ++var18) {
                  Block var19 = var1.getBlock(var3, var4 + var18, var5);
                  if(var19.getMaterial() == Material.air || var19.getMaterial() == Material.leaves) {
                     this.setBlockAndNotifyAdequately(var1, var3, var4 + var18, var5, Blocks.log, 2);
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
