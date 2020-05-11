package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenTrees extends WorldGenAbstractTree {

   private final int minTreeHeight;
   private final boolean vinesGrow;
   private final int metaWood;
   private final int metaLeaves;


   public WorldGenTrees(boolean var1) {
      this(var1, 4, 0, 0, false);
   }

   public WorldGenTrees(boolean var1, int var2, int var3, int var4, boolean var5) {
      super(var1);
      this.minTreeHeight = var2;
      this.metaWood = var3;
      this.metaLeaves = var4;
      this.vinesGrow = var5;
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      int var6 = var2.nextInt(3) + this.minTreeHeight;
      boolean var7 = true;
      if(var4 >= 1 && var4 + var6 + 1 <= 256) {
         byte var9;
         int var11;
         Block var12;
         for(int var8 = var4; var8 <= var4 + 1 + var6; ++var8) {
            var9 = 1;
            if(var8 == var4) {
               var9 = 0;
            }

            if(var8 >= var4 + 1 + var6 - 2) {
               var9 = 2;
            }

            for(int var10 = var3 - var9; var10 <= var3 + var9 && var7; ++var10) {
               for(var11 = var5 - var9; var11 <= var5 + var9 && var7; ++var11) {
                  if(var8 >= 0 && var8 < 256) {
                     var12 = var1.getBlock(var10, var8, var11);
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
            Block var19 = var1.getBlock(var3, var4 - 1, var5);
            if((var19 == Blocks.grass || var19 == Blocks.dirt || var19 == Blocks.farmland) && var4 < 256 - var6 - 1) {
               this.func_150515_a(var1, var3, var4 - 1, var5, Blocks.dirt);
               var9 = 3;
               byte var20 = 0;

               int var13;
               int var14;
               int var15;
               int var21;
               for(var11 = var4 - var9 + var6; var11 <= var4 + var6; ++var11) {
                  var21 = var11 - (var4 + var6);
                  var13 = var20 + 1 - var21 / 2;

                  for(var14 = var3 - var13; var14 <= var3 + var13; ++var14) {
                     var15 = var14 - var3;

                     for(int var16 = var5 - var13; var16 <= var5 + var13; ++var16) {
                        int var17 = var16 - var5;
                        if(Math.abs(var15) != var13 || Math.abs(var17) != var13 || var2.nextInt(2) != 0 && var21 != 0) {
                           Block var18 = var1.getBlock(var14, var11, var16);
                           if(var18.getMaterial() == Material.air || var18.getMaterial() == Material.leaves) {
                              this.setBlockAndNotifyAdequately(var1, var14, var11, var16, Blocks.leaves, this.metaLeaves);
                           }
                        }
                     }
                  }
               }

               for(var11 = 0; var11 < var6; ++var11) {
                  var12 = var1.getBlock(var3, var4 + var11, var5);
                  if(var12.getMaterial() == Material.air || var12.getMaterial() == Material.leaves) {
                     this.setBlockAndNotifyAdequately(var1, var3, var4 + var11, var5, Blocks.log, this.metaWood);
                     if(this.vinesGrow && var11 > 0) {
                        if(var2.nextInt(3) > 0 && var1.isAirBlock(var3 - 1, var4 + var11, var5)) {
                           this.setBlockAndNotifyAdequately(var1, var3 - 1, var4 + var11, var5, Blocks.vine, 8);
                        }

                        if(var2.nextInt(3) > 0 && var1.isAirBlock(var3 + 1, var4 + var11, var5)) {
                           this.setBlockAndNotifyAdequately(var1, var3 + 1, var4 + var11, var5, Blocks.vine, 2);
                        }

                        if(var2.nextInt(3) > 0 && var1.isAirBlock(var3, var4 + var11, var5 - 1)) {
                           this.setBlockAndNotifyAdequately(var1, var3, var4 + var11, var5 - 1, Blocks.vine, 1);
                        }

                        if(var2.nextInt(3) > 0 && var1.isAirBlock(var3, var4 + var11, var5 + 1)) {
                           this.setBlockAndNotifyAdequately(var1, var3, var4 + var11, var5 + 1, Blocks.vine, 4);
                        }
                     }
                  }
               }

               if(this.vinesGrow) {
                  for(var11 = var4 - 3 + var6; var11 <= var4 + var6; ++var11) {
                     var21 = var11 - (var4 + var6);
                     var13 = 2 - var21 / 2;

                     for(var14 = var3 - var13; var14 <= var3 + var13; ++var14) {
                        for(var15 = var5 - var13; var15 <= var5 + var13; ++var15) {
                           if(var1.getBlock(var14, var11, var15).getMaterial() == Material.leaves) {
                              if(var2.nextInt(4) == 0 && var1.getBlock(var14 - 1, var11, var15).getMaterial() == Material.air) {
                                 this.growVines(var1, var14 - 1, var11, var15, 8);
                              }

                              if(var2.nextInt(4) == 0 && var1.getBlock(var14 + 1, var11, var15).getMaterial() == Material.air) {
                                 this.growVines(var1, var14 + 1, var11, var15, 2);
                              }

                              if(var2.nextInt(4) == 0 && var1.getBlock(var14, var11, var15 - 1).getMaterial() == Material.air) {
                                 this.growVines(var1, var14, var11, var15 - 1, 1);
                              }

                              if(var2.nextInt(4) == 0 && var1.getBlock(var14, var11, var15 + 1).getMaterial() == Material.air) {
                                 this.growVines(var1, var14, var11, var15 + 1, 4);
                              }
                           }
                        }
                     }
                  }

                  if(var2.nextInt(5) == 0 && var6 > 5) {
                     for(var11 = 0; var11 < 2; ++var11) {
                        for(var21 = 0; var21 < 4; ++var21) {
                           if(var2.nextInt(4 - var11) == 0) {
                              var13 = var2.nextInt(3);
                              this.setBlockAndNotifyAdequately(var1, var3 + Direction.offsetX[Direction.rotateOpposite[var21]], var4 + var6 - 5 + var11, var5 + Direction.offsetZ[Direction.rotateOpposite[var21]], Blocks.cocoa, var13 << 2 | var21);
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

   private void growVines(World var1, int var2, int var3, int var4, int var5) {
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
