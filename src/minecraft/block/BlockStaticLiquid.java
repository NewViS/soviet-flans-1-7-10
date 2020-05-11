package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockStaticLiquid extends BlockLiquid {

   protected BlockStaticLiquid(Material var1) {
      super(var1);
      this.setTickRandomly(false);
      if(var1 == Material.lava) {
         this.setTickRandomly(true);
      }

   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      super.onNeighborBlockChange(var1, var2, var3, var4, var5);
      if(var1.getBlock(var2, var3, var4) == this) {
         this.setNotStationary(var1, var2, var3, var4);
      }

   }

   private void setNotStationary(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      var1.setBlock(var2, var3, var4, Block.getBlockById(Block.getIdFromBlock(this) - 1), var5, 2);
      var1.scheduleBlockUpdate(var2, var3, var4, Block.getBlockById(Block.getIdFromBlock(this) - 1), this.tickRate(var1));
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(super.blockMaterial == Material.lava) {
         int var6 = var5.nextInt(3);

         int var7;
         for(var7 = 0; var7 < var6; ++var7) {
            var2 += var5.nextInt(3) - 1;
            ++var3;
            var4 += var5.nextInt(3) - 1;
            Block var8 = var1.getBlock(var2, var3, var4);
            if(var8.blockMaterial == Material.air) {
               if(this.isFlammable(var1, var2 - 1, var3, var4) || this.isFlammable(var1, var2 + 1, var3, var4) || this.isFlammable(var1, var2, var3, var4 - 1) || this.isFlammable(var1, var2, var3, var4 + 1) || this.isFlammable(var1, var2, var3 - 1, var4) || this.isFlammable(var1, var2, var3 + 1, var4)) {
                  var1.setBlock(var2, var3, var4, Blocks.fire);
                  return;
               }
            } else if(var8.blockMaterial.blocksMovement()) {
               return;
            }
         }

         if(var6 == 0) {
            var7 = var2;
            int var10 = var4;

            for(int var9 = 0; var9 < 3; ++var9) {
               var2 = var7 + var5.nextInt(3) - 1;
               var4 = var10 + var5.nextInt(3) - 1;
               if(var1.isAirBlock(var2, var3 + 1, var4) && this.isFlammable(var1, var2, var3, var4)) {
                  var1.setBlock(var2, var3 + 1, var4, Blocks.fire);
               }
            }
         }
      }

   }

   private boolean isFlammable(World var1, int var2, int var3, int var4) {
      return var1.getBlock(var2, var3, var4).getMaterial().getCanBurn();
   }
}
