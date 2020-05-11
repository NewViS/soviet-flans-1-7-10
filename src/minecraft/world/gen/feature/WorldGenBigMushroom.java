package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBigMushroom extends WorldGenerator {

   private int mushroomType = -1;


   public WorldGenBigMushroom(int var1) {
      super(true);
      this.mushroomType = var1;
   }

   public WorldGenBigMushroom() {
      super(false);
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      int var6 = var2.nextInt(2);
      if(this.mushroomType >= 0) {
         var6 = this.mushroomType;
      }

      int var7 = var2.nextInt(3) + 4;
      boolean var8 = true;
      if(var4 >= 1 && var4 + var7 + 1 < 256) {
         int var11;
         int var12;
         for(int var9 = var4; var9 <= var4 + 1 + var7; ++var9) {
            byte var10 = 3;
            if(var9 <= var4 + 3) {
               var10 = 0;
            }

            for(var11 = var3 - var10; var11 <= var3 + var10 && var8; ++var11) {
               for(var12 = var5 - var10; var12 <= var5 + var10 && var8; ++var12) {
                  if(var9 >= 0 && var9 < 256) {
                     Block var13 = var1.getBlock(var11, var9, var12);
                     if(var13.getMaterial() != Material.air && var13.getMaterial() != Material.leaves) {
                        var8 = false;
                     }
                  } else {
                     var8 = false;
                  }
               }
            }
         }

         if(!var8) {
            return false;
         } else {
            Block var16 = var1.getBlock(var3, var4 - 1, var5);
            if(var16 != Blocks.dirt && var16 != Blocks.grass && var16 != Blocks.mycelium) {
               return false;
            } else {
               int var17 = var4 + var7;
               if(var6 == 1) {
                  var17 = var4 + var7 - 3;
               }

               for(var11 = var17; var11 <= var4 + var7; ++var11) {
                  var12 = 1;
                  if(var11 < var4 + var7) {
                     ++var12;
                  }

                  if(var6 == 0) {
                     var12 = 3;
                  }

                  for(int var18 = var3 - var12; var18 <= var3 + var12; ++var18) {
                     for(int var14 = var5 - var12; var14 <= var5 + var12; ++var14) {
                        int var15 = 5;
                        if(var18 == var3 - var12) {
                           --var15;
                        }

                        if(var18 == var3 + var12) {
                           ++var15;
                        }

                        if(var14 == var5 - var12) {
                           var15 -= 3;
                        }

                        if(var14 == var5 + var12) {
                           var15 += 3;
                        }

                        if(var6 == 0 || var11 < var4 + var7) {
                           if((var18 == var3 - var12 || var18 == var3 + var12) && (var14 == var5 - var12 || var14 == var5 + var12)) {
                              continue;
                           }

                           if(var18 == var3 - (var12 - 1) && var14 == var5 - var12) {
                              var15 = 1;
                           }

                           if(var18 == var3 - var12 && var14 == var5 - (var12 - 1)) {
                              var15 = 1;
                           }

                           if(var18 == var3 + (var12 - 1) && var14 == var5 - var12) {
                              var15 = 3;
                           }

                           if(var18 == var3 + var12 && var14 == var5 - (var12 - 1)) {
                              var15 = 3;
                           }

                           if(var18 == var3 - (var12 - 1) && var14 == var5 + var12) {
                              var15 = 7;
                           }

                           if(var18 == var3 - var12 && var14 == var5 + (var12 - 1)) {
                              var15 = 7;
                           }

                           if(var18 == var3 + (var12 - 1) && var14 == var5 + var12) {
                              var15 = 9;
                           }

                           if(var18 == var3 + var12 && var14 == var5 + (var12 - 1)) {
                              var15 = 9;
                           }
                        }

                        if(var15 == 5 && var11 < var4 + var7) {
                           var15 = 0;
                        }

                        if((var15 != 0 || var4 >= var4 + var7 - 1) && !var1.getBlock(var18, var11, var14).func_149730_j()) {
                           this.setBlockAndNotifyAdequately(var1, var18, var11, var14, Block.getBlockById(Block.getIdFromBlock(Blocks.brown_mushroom_block) + var6), var15);
                        }
                     }
                  }
               }

               for(var11 = 0; var11 < var7; ++var11) {
                  Block var19 = var1.getBlock(var3, var4 + var11, var5);
                  if(!var19.func_149730_j()) {
                     this.setBlockAndNotifyAdequately(var1, var3, var4 + var11, var5, Block.getBlockById(Block.getIdFromBlock(Blocks.brown_mushroom_block) + var6), 10);
                  }
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }
}
