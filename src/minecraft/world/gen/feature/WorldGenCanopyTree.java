package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenCanopyTree extends WorldGenAbstractTree {

   public WorldGenCanopyTree(boolean var1) {
      super(var1);
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      int var6 = var2.nextInt(3) + var2.nextInt(2) + 6;
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
            Block var20 = var1.getBlock(var3, var4 - 1, var5);
            if((var20 == Blocks.grass || var20 == Blocks.dirt) && var4 < 256 - var6 - 1) {
               this.func_150515_a(var1, var3, var4 - 1, var5, Blocks.dirt);
               this.func_150515_a(var1, var3 + 1, var4 - 1, var5, Blocks.dirt);
               this.func_150515_a(var1, var3 + 1, var4 - 1, var5 + 1, Blocks.dirt);
               this.func_150515_a(var1, var3, var4 - 1, var5 + 1, Blocks.dirt);
               int var21 = var2.nextInt(4);
               var10 = var6 - var2.nextInt(4);
               var11 = 2 - var2.nextInt(3);
               int var22 = var3;
               int var13 = var5;
               int var14 = 0;

               int var15;
               int var16;
               for(var15 = 0; var15 < var6; ++var15) {
                  var16 = var4 + var15;
                  if(var15 >= var10 && var11 > 0) {
                     var22 += Direction.offsetX[var21];
                     var13 += Direction.offsetZ[var21];
                     --var11;
                  }

                  Block var17 = var1.getBlock(var22, var16, var13);
                  if(var17.getMaterial() == Material.air || var17.getMaterial() == Material.leaves) {
                     this.setBlockAndNotifyAdequately(var1, var22, var16, var13, Blocks.log2, 1);
                     this.setBlockAndNotifyAdequately(var1, var22 + 1, var16, var13, Blocks.log2, 1);
                     this.setBlockAndNotifyAdequately(var1, var22, var16, var13 + 1, Blocks.log2, 1);
                     this.setBlockAndNotifyAdequately(var1, var22 + 1, var16, var13 + 1, Blocks.log2, 1);
                     var14 = var16;
                  }
               }

               for(var15 = -2; var15 <= 0; ++var15) {
                  for(var16 = -2; var16 <= 0; ++var16) {
                     byte var23 = -1;
                     this.func_150526_a(var1, var22 + var15, var14 + var23, var13 + var16);
                     this.func_150526_a(var1, 1 + var22 - var15, var14 + var23, var13 + var16);
                     this.func_150526_a(var1, var22 + var15, var14 + var23, 1 + var13 - var16);
                     this.func_150526_a(var1, 1 + var22 - var15, var14 + var23, 1 + var13 - var16);
                     if((var15 > -2 || var16 > -1) && (var15 != -1 || var16 != -2)) {
                        byte var24 = 1;
                        this.func_150526_a(var1, var22 + var15, var14 + var24, var13 + var16);
                        this.func_150526_a(var1, 1 + var22 - var15, var14 + var24, var13 + var16);
                        this.func_150526_a(var1, var22 + var15, var14 + var24, 1 + var13 - var16);
                        this.func_150526_a(var1, 1 + var22 - var15, var14 + var24, 1 + var13 - var16);
                     }
                  }
               }

               if(var2.nextBoolean()) {
                  this.func_150526_a(var1, var22, var14 + 2, var13);
                  this.func_150526_a(var1, var22 + 1, var14 + 2, var13);
                  this.func_150526_a(var1, var22 + 1, var14 + 2, var13 + 1);
                  this.func_150526_a(var1, var22, var14 + 2, var13 + 1);
               }

               for(var15 = -3; var15 <= 4; ++var15) {
                  for(var16 = -3; var16 <= 4; ++var16) {
                     if((var15 != -3 || var16 != -3) && (var15 != -3 || var16 != 4) && (var15 != 4 || var16 != -3) && (var15 != 4 || var16 != 4) && (Math.abs(var15) < 3 || Math.abs(var16) < 3)) {
                        this.func_150526_a(var1, var22 + var15, var14, var13 + var16);
                     }
                  }
               }

               for(var15 = -1; var15 <= 2; ++var15) {
                  for(var16 = -1; var16 <= 2; ++var16) {
                     if((var15 < 0 || var15 > 1 || var16 < 0 || var16 > 1) && var2.nextInt(3) <= 0) {
                        int var25 = var2.nextInt(3) + 2;

                        int var18;
                        for(var18 = 0; var18 < var25; ++var18) {
                           this.setBlockAndNotifyAdequately(var1, var3 + var15, var14 - var18 - 1, var5 + var16, Blocks.log2, 1);
                        }

                        int var19;
                        for(var18 = -1; var18 <= 1; ++var18) {
                           for(var19 = -1; var19 <= 1; ++var19) {
                              this.func_150526_a(var1, var22 + var15 + var18, var14 - 0, var13 + var16 + var19);
                           }
                        }

                        for(var18 = -2; var18 <= 2; ++var18) {
                           for(var19 = -2; var19 <= 2; ++var19) {
                              if(Math.abs(var18) != 2 || Math.abs(var19) != 2) {
                                 this.func_150526_a(var1, var22 + var15 + var18, var14 - 1, var13 + var16 + var19);
                              }
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

   private void func_150526_a(World var1, int var2, int var3, int var4) {
      Block var5 = var1.getBlock(var2, var3, var4);
      if(var5.getMaterial() == Material.air) {
         this.setBlockAndNotifyAdequately(var1, var2, var3, var4, Blocks.leaves2, 1);
      }

   }
}
