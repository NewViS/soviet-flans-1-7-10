package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class WorldGenShrub extends WorldGenTrees {

   private int field_150528_a;
   private int field_150527_b;


   public WorldGenShrub(int var1, int var2) {
      super(false);
      this.field_150527_b = var1;
      this.field_150528_a = var2;
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      Block var6;
      while(((var6 = var1.getBlock(var3, var4, var5)).getMaterial() == Material.air || var6.getMaterial() == Material.leaves) && var4 > 0) {
         --var4;
      }

      Block var7 = var1.getBlock(var3, var4, var5);
      if(var7 == Blocks.dirt || var7 == Blocks.grass) {
         ++var4;
         this.setBlockAndNotifyAdequately(var1, var3, var4, var5, Blocks.log, this.field_150527_b);

         for(int var8 = var4; var8 <= var4 + 2; ++var8) {
            int var9 = var8 - var4;
            int var10 = 2 - var9;

            for(int var11 = var3 - var10; var11 <= var3 + var10; ++var11) {
               int var12 = var11 - var3;

               for(int var13 = var5 - var10; var13 <= var5 + var10; ++var13) {
                  int var14 = var13 - var5;
                  if((Math.abs(var12) != var10 || Math.abs(var14) != var10 || var2.nextInt(2) != 0) && !var1.getBlock(var11, var8, var13).func_149730_j()) {
                     this.setBlockAndNotifyAdequately(var1, var11, var8, var13, Blocks.leaves, this.field_150528_a);
                  }
               }
            }
         }
      }

      return true;
   }
}
