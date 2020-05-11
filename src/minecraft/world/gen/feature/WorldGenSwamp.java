package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenSwamp extends WorldGenAbstractTree {

   public WorldGenSwamp() {
      super(false);
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      int var6;
      for(var6 = var2.nextInt(4) + 5; var1.getBlock(var3, var4 - 1, var5).getMaterial() == Material.water; --var4) {
         ;
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
               var9 = 3;
            }

            for(var10 = var3 - var9; var10 <= var3 + var9 && var7; ++var10) {
               for(var11 = var5 - var9; var11 <= var5 + var9 && var7; ++var11) {
                  if(var8 >= 0 && var8 < 256) {
                     Block var12 = var1.getBlock(var10, var8, var11);
                     if(var12.getMaterial() != Material.air && var12.getMaterial() != Material.leaves) {
                        if(var12 != Blocks.water && var12 != Blocks.flowing_water) {
                           var7 = false;
                        } else if(var8 > var4) {
                           var7 = false;
                        }
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
            Block var16 = var1.getBlock(var3, var4 - 1, var5);
            if((var16 == Blocks.grass || var16 == Blocks.dirt) && var4 < 256 - var6 - 1) {
               this.func_150515_a(var1, var3, var4 - 1, var5, Blocks.dirt);

               int var13;
               int var17;
               int var19;
               for(var17 = var4 - 3 + var6; var17 <= var4 + var6; ++var17) {
                  var10 = var17 - (var4 + var6);
                  var11 = 2 - var10 / 2;

                  for(var19 = var3 - var11; var19 <= var3 + var11; ++var19) {
                     var13 = var19 - var3;

                     for(int var14 = var5 - var11; var14 <= var5 + var11; ++var14) {
                        int var15 = var14 - var5;
                        if((Math.abs(var13) != var11 || Math.abs(var15) != var11 || var2.nextInt(2) != 0 && var10 != 0) && !var1.getBlock(var19, var17, var14).func_149730_j()) {
                           this.func_150515_a(var1, var19, var17, var14, Blocks.leaves);
                        }
                     }
                  }
               }

               for(var17 = 0; var17 < var6; ++var17) {
                  Block var18 = var1.getBlock(var3, var4 + var17, var5);
                  if(var18.getMaterial() == Material.air || var18.getMaterial() == Material.leaves || var18 == Blocks.flowing_water || var18 == Blocks.water) {
                     this.func_150515_a(var1, var3, var4 + var17, var5, Blocks.log);
                  }
               }

               for(var17 = var4 - 3 + var6; var17 <= var4 + var6; ++var17) {
                  var10 = var17 - (var4 + var6);
                  var11 = 2 - var10 / 2;

                  for(var19 = var3 - var11; var19 <= var3 + var11; ++var19) {
                     for(var13 = var5 - var11; var13 <= var5 + var11; ++var13) {
                        if(var1.getBlock(var19, var17, var13).getMaterial() == Material.leaves) {
                           if(var2.nextInt(4) == 0 && var1.getBlock(var19 - 1, var17, var13).getMaterial() == Material.air) {
                              this.generateVines(var1, var19 - 1, var17, var13, 8);
                           }

                           if(var2.nextInt(4) == 0 && var1.getBlock(var19 + 1, var17, var13).getMaterial() == Material.air) {
                              this.generateVines(var1, var19 + 1, var17, var13, 2);
                           }

                           if(var2.nextInt(4) == 0 && var1.getBlock(var19, var17, var13 - 1).getMaterial() == Material.air) {
                              this.generateVines(var1, var19, var17, var13 - 1, 1);
                           }

                           if(var2.nextInt(4) == 0 && var1.getBlock(var19, var17, var13 + 1).getMaterial() == Material.air) {
                              this.generateVines(var1, var19, var17, var13 + 1, 4);
                           }
                        }
                     }
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

   private void generateVines(World var1, int var2, int var3, int var4, int var5) {
      this.setBlockAndNotifyAdequately(var1, var2, var3, var4, Blocks.vine, var5);
      int var6 = 4;

      while(true) {
         --var3;
         if(var1.getBlock(var2, var3, var4).getMaterial() != Material.air || var6 <= 0) {
            return;
         }

         this.setBlockAndNotifyAdequately(var1, var2, var3, var4, Blocks.vine, var5);
         --var6;
      }
   }
}
