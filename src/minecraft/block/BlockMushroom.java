package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;

public class BlockMushroom extends BlockBush implements IGrowable {

   protected BlockMushroom() {
      float var1 = 0.2F;
      this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var1 * 2.0F, 0.5F + var1);
      this.setTickRandomly(true);
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(var5.nextInt(25) == 0) {
         byte var6 = 4;
         int var7 = 5;

         int var8;
         int var9;
         int var10;
         for(var8 = var2 - var6; var8 <= var2 + var6; ++var8) {
            for(var9 = var4 - var6; var9 <= var4 + var6; ++var9) {
               for(var10 = var3 - 1; var10 <= var3 + 1; ++var10) {
                  if(var1.getBlock(var8, var10, var9) == this) {
                     --var7;
                     if(var7 <= 0) {
                        return;
                     }
                  }
               }
            }
         }

         var8 = var2 + var5.nextInt(3) - 1;
         var9 = var3 + var5.nextInt(2) - var5.nextInt(2);
         var10 = var4 + var5.nextInt(3) - 1;

         for(int var11 = 0; var11 < 4; ++var11) {
            if(var1.isAirBlock(var8, var9, var10) && this.canBlockStay(var1, var8, var9, var10)) {
               var2 = var8;
               var3 = var9;
               var4 = var10;
            }

            var8 = var2 + var5.nextInt(3) - 1;
            var9 = var3 + var5.nextInt(2) - var5.nextInt(2);
            var10 = var4 + var5.nextInt(3) - 1;
         }

         if(var1.isAirBlock(var8, var9, var10) && this.canBlockStay(var1, var8, var9, var10)) {
            var1.setBlock(var8, var9, var10, this, 0, 2);
         }
      }

   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return super.canPlaceBlockAt(var1, var2, var3, var4) && this.canBlockStay(var1, var2, var3, var4);
   }

   protected boolean canPlaceBlockOn(Block var1) {
      return var1.func_149730_j();
   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      if(var3 >= 0 && var3 < 256) {
         Block var5 = var1.getBlock(var2, var3 - 1, var4);
         return var5 == Blocks.mycelium || var5 == Blocks.dirt && var1.getBlockMetadata(var2, var3 - 1, var4) == 2 || var1.getFullBlockLightValue(var2, var3, var4) < 13 && this.canPlaceBlockOn(var5);
      } else {
         return false;
      }
   }

   public boolean func_149884_c(World var1, int var2, int var3, int var4, Random var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      var1.setBlockToAir(var2, var3, var4);
      WorldGenBigMushroom var7 = null;
      if(this == Blocks.brown_mushroom) {
         var7 = new WorldGenBigMushroom(0);
      } else if(this == Blocks.red_mushroom) {
         var7 = new WorldGenBigMushroom(1);
      }

      if(var7 != null && var7.generate(var1, var5, var2, var3, var4)) {
         return true;
      } else {
         var1.setBlock(var2, var3, var4, this, var6, 3);
         return false;
      }
   }

   public boolean func_149851_a(World var1, int var2, int var3, int var4, boolean var5) {
      return true;
   }

   public boolean func_149852_a(World var1, Random var2, int var3, int var4, int var5) {
      return (double)var2.nextFloat() < 0.4D;
   }

   public void func_149853_b(World var1, Random var2, int var3, int var4, int var5) {
      this.func_149884_c(var1, var3, var4, var5, var2);
   }
}
