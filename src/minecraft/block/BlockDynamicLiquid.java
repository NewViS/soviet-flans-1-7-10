package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockDynamicLiquid extends BlockLiquid {

   int field_149815_a;
   boolean[] field_149814_b = new boolean[4];
   int[] field_149816_M = new int[4];


   protected BlockDynamicLiquid(Material var1) {
      super(var1);
   }

   private void func_149811_n(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      var1.setBlock(var2, var3, var4, Block.getBlockById(Block.getIdFromBlock(this) + 1), var5, 2);
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      int var6 = this.func_149804_e(var1, var2, var3, var4);
      byte var7 = 1;
      if(super.blockMaterial == Material.lava && !var1.provider.isHellWorld) {
         var7 = 2;
      }

      boolean var8 = true;
      int var9 = this.tickRate(var1);
      int var11;
      if(var6 > 0) {
         byte var10 = -100;
         this.field_149815_a = 0;
         int var13 = this.func_149810_a(var1, var2 - 1, var3, var4, var10);
         var13 = this.func_149810_a(var1, var2 + 1, var3, var4, var13);
         var13 = this.func_149810_a(var1, var2, var3, var4 - 1, var13);
         var13 = this.func_149810_a(var1, var2, var3, var4 + 1, var13);
         var11 = var13 + var7;
         if(var11 >= 8 || var13 < 0) {
            var11 = -1;
         }

         if(this.func_149804_e(var1, var2, var3 + 1, var4) >= 0) {
            int var12 = this.func_149804_e(var1, var2, var3 + 1, var4);
            if(var12 >= 8) {
               var11 = var12;
            } else {
               var11 = var12 + 8;
            }
         }

         if(this.field_149815_a >= 2 && super.blockMaterial == Material.water) {
            if(var1.getBlock(var2, var3 - 1, var4).getMaterial().isSolid()) {
               var11 = 0;
            } else if(var1.getBlock(var2, var3 - 1, var4).getMaterial() == super.blockMaterial && var1.getBlockMetadata(var2, var3 - 1, var4) == 0) {
               var11 = 0;
            }
         }

         if(super.blockMaterial == Material.lava && var6 < 8 && var11 < 8 && var11 > var6 && var5.nextInt(4) != 0) {
            var9 *= 4;
         }

         if(var11 == var6) {
            if(var8) {
               this.func_149811_n(var1, var2, var3, var4);
            }
         } else {
            var6 = var11;
            if(var11 < 0) {
               var1.setBlockToAir(var2, var3, var4);
            } else {
               var1.setBlockMetadataWithNotify(var2, var3, var4, var11, 2);
               var1.scheduleBlockUpdate(var2, var3, var4, this, var9);
               var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
            }
         }
      } else {
         this.func_149811_n(var1, var2, var3, var4);
      }

      if(this.func_149809_q(var1, var2, var3 - 1, var4)) {
         if(super.blockMaterial == Material.lava && var1.getBlock(var2, var3 - 1, var4).getMaterial() == Material.water) {
            var1.setBlock(var2, var3 - 1, var4, Blocks.stone);
            this.func_149799_m(var1, var2, var3 - 1, var4);
            return;
         }

         if(var6 >= 8) {
            this.func_149813_h(var1, var2, var3 - 1, var4, var6);
         } else {
            this.func_149813_h(var1, var2, var3 - 1, var4, var6 + 8);
         }
      } else if(var6 >= 0 && (var6 == 0 || this.func_149807_p(var1, var2, var3 - 1, var4))) {
         boolean[] var14 = this.func_149808_o(var1, var2, var3, var4);
         var11 = var6 + var7;
         if(var6 >= 8) {
            var11 = 1;
         }

         if(var11 >= 8) {
            return;
         }

         if(var14[0]) {
            this.func_149813_h(var1, var2 - 1, var3, var4, var11);
         }

         if(var14[1]) {
            this.func_149813_h(var1, var2 + 1, var3, var4, var11);
         }

         if(var14[2]) {
            this.func_149813_h(var1, var2, var3, var4 - 1, var11);
         }

         if(var14[3]) {
            this.func_149813_h(var1, var2, var3, var4 + 1, var11);
         }
      }

   }

   private void func_149813_h(World var1, int var2, int var3, int var4, int var5) {
      if(this.func_149809_q(var1, var2, var3, var4)) {
         Block var6 = var1.getBlock(var2, var3, var4);
         if(super.blockMaterial == Material.lava) {
            this.func_149799_m(var1, var2, var3, var4);
         } else {
            var6.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         }

         var1.setBlock(var2, var3, var4, this, var5, 3);
      }

   }

   private int func_149812_c(World var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = 1000;

      for(int var8 = 0; var8 < 4; ++var8) {
         if((var8 != 0 || var6 != 1) && (var8 != 1 || var6 != 0) && (var8 != 2 || var6 != 3) && (var8 != 3 || var6 != 2)) {
            int var9 = var2;
            int var11 = var4;
            if(var8 == 0) {
               var9 = var2 - 1;
            }

            if(var8 == 1) {
               ++var9;
            }

            if(var8 == 2) {
               var11 = var4 - 1;
            }

            if(var8 == 3) {
               ++var11;
            }

            if(!this.func_149807_p(var1, var9, var3, var11) && (var1.getBlock(var9, var3, var11).getMaterial() != super.blockMaterial || var1.getBlockMetadata(var9, var3, var11) != 0)) {
               if(!this.func_149807_p(var1, var9, var3 - 1, var11)) {
                  return var5;
               }

               if(var5 < 4) {
                  int var12 = this.func_149812_c(var1, var9, var3, var11, var5 + 1, var8);
                  if(var12 < var7) {
                     var7 = var12;
                  }
               }
            }
         }
      }

      return var7;
   }

   private boolean[] func_149808_o(World var1, int var2, int var3, int var4) {
      int var5;
      int var6;
      for(var5 = 0; var5 < 4; ++var5) {
         this.field_149816_M[var5] = 1000;
         var6 = var2;
         int var8 = var4;
         if(var5 == 0) {
            var6 = var2 - 1;
         }

         if(var5 == 1) {
            ++var6;
         }

         if(var5 == 2) {
            var8 = var4 - 1;
         }

         if(var5 == 3) {
            ++var8;
         }

         if(!this.func_149807_p(var1, var6, var3, var8) && (var1.getBlock(var6, var3, var8).getMaterial() != super.blockMaterial || var1.getBlockMetadata(var6, var3, var8) != 0)) {
            if(this.func_149807_p(var1, var6, var3 - 1, var8)) {
               this.field_149816_M[var5] = this.func_149812_c(var1, var6, var3, var8, 1, var5);
            } else {
               this.field_149816_M[var5] = 0;
            }
         }
      }

      var5 = this.field_149816_M[0];

      for(var6 = 1; var6 < 4; ++var6) {
         if(this.field_149816_M[var6] < var5) {
            var5 = this.field_149816_M[var6];
         }
      }

      for(var6 = 0; var6 < 4; ++var6) {
         this.field_149814_b[var6] = this.field_149816_M[var6] == var5;
      }

      return this.field_149814_b;
   }

   private boolean func_149807_p(World var1, int var2, int var3, int var4) {
      Block var5 = var1.getBlock(var2, var3, var4);
      return var5 != Blocks.wooden_door && var5 != Blocks.iron_door && var5 != Blocks.standing_sign && var5 != Blocks.ladder && var5 != Blocks.reeds?(var5.blockMaterial == Material.portal?true:var5.blockMaterial.blocksMovement()):true;
   }

   protected int func_149810_a(World var1, int var2, int var3, int var4, int var5) {
      int var6 = this.func_149804_e(var1, var2, var3, var4);
      if(var6 < 0) {
         return var5;
      } else {
         if(var6 == 0) {
            ++this.field_149815_a;
         }

         if(var6 >= 8) {
            var6 = 0;
         }

         return var5 >= 0 && var6 >= var5?var5:var6;
      }
   }

   private boolean func_149809_q(World var1, int var2, int var3, int var4) {
      Material var5 = var1.getBlock(var2, var3, var4).getMaterial();
      return var5 == super.blockMaterial?false:(var5 == Material.lava?false:!this.func_149807_p(var1, var2, var3, var4));
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      super.onBlockAdded(var1, var2, var3, var4);
      if(var1.getBlock(var2, var3, var4) == this) {
         var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
      }

   }

   public boolean func_149698_L() {
      return true;
   }
}
