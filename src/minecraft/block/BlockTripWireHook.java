package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTripWireHook extends Block {

   public BlockTripWireHook() {
      super(Material.circuits);
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setTickRandomly(true);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return null;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 29;
   }

   public int tickRate(World var1) {
      return 10;
   }

   public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5) {
      return var5 == 2 && var1.getBlock(var2, var3, var4 + 1).isNormalCube()?true:(var5 == 3 && var1.getBlock(var2, var3, var4 - 1).isNormalCube()?true:(var5 == 4 && var1.getBlock(var2 + 1, var3, var4).isNormalCube()?true:var5 == 5 && var1.getBlock(var2 - 1, var3, var4).isNormalCube()));
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return var1.getBlock(var2 - 1, var3, var4).isNormalCube()?true:(var1.getBlock(var2 + 1, var3, var4).isNormalCube()?true:(var1.getBlock(var2, var3, var4 - 1).isNormalCube()?true:var1.getBlock(var2, var3, var4 + 1).isNormalCube()));
   }

   public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9) {
      byte var10 = 0;
      if(var5 == 2 && var1.isBlockNormalCubeDefault(var2, var3, var4 + 1, true)) {
         var10 = 2;
      }

      if(var5 == 3 && var1.isBlockNormalCubeDefault(var2, var3, var4 - 1, true)) {
         var10 = 0;
      }

      if(var5 == 4 && var1.isBlockNormalCubeDefault(var2 + 1, var3, var4, true)) {
         var10 = 1;
      }

      if(var5 == 5 && var1.isBlockNormalCubeDefault(var2 - 1, var3, var4, true)) {
         var10 = 3;
      }

      return var10;
   }

   public void onPostBlockPlaced(World var1, int var2, int var3, int var4, int var5) {
      this.func_150136_a(var1, var2, var3, var4, false, var5, false, -1, 0);
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(var5 != this) {
         if(this.func_150137_e(var1, var2, var3, var4)) {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            int var7 = var6 & 3;
            boolean var8 = false;
            if(!var1.getBlock(var2 - 1, var3, var4).isNormalCube() && var7 == 3) {
               var8 = true;
            }

            if(!var1.getBlock(var2 + 1, var3, var4).isNormalCube() && var7 == 1) {
               var8 = true;
            }

            if(!var1.getBlock(var2, var3, var4 - 1).isNormalCube() && var7 == 0) {
               var8 = true;
            }

            if(!var1.getBlock(var2, var3, var4 + 1).isNormalCube() && var7 == 2) {
               var8 = true;
            }

            if(var8) {
               this.dropBlockAsItem(var1, var2, var3, var4, var6, 0);
               var1.setBlockToAir(var2, var3, var4);
            }
         }

      }
   }

   public void func_150136_a(World var1, int var2, int var3, int var4, boolean var5, int var6, boolean var7, int var8, int var9) {
      int var10 = var6 & 3;
      boolean var11 = (var6 & 4) == 4;
      boolean var12 = (var6 & 8) == 8;
      boolean var13 = !var5;
      boolean var14 = false;
      boolean var15 = !World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4);
      int var16 = Direction.offsetX[var10];
      int var17 = Direction.offsetZ[var10];
      int var18 = 0;
      int[] var19 = new int[42];

      int var20;
      int var21;
      int var22;
      int var24;
      for(var20 = 1; var20 < 42; ++var20) {
         var21 = var2 + var16 * var20;
         var22 = var4 + var17 * var20;
         Block var23 = var1.getBlock(var21, var3, var22);
         if(var23 == Blocks.tripwire_hook) {
            var24 = var1.getBlockMetadata(var21, var3, var22);
            if((var24 & 3) == Direction.rotateOpposite[var10]) {
               var18 = var20;
            }
            break;
         }

         if(var23 != Blocks.tripwire && var20 != var8) {
            var19[var20] = -1;
            var13 = false;
         } else {
            var24 = var20 == var8?var9:var1.getBlockMetadata(var21, var3, var22);
            boolean var25 = (var24 & 8) != 8;
            boolean var26 = (var24 & 1) == 1;
            boolean var27 = (var24 & 2) == 2;
            var13 &= var27 == var15;
            var14 |= var25 && var26;
            var19[var20] = var24;
            if(var20 == var8) {
               var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
               var13 &= var25;
            }
         }
      }

      var13 &= var18 > 1;
      var14 &= var13;
      var20 = (var13?4:0) | (var14?8:0);
      var6 = var10 | var20;
      int var28;
      if(var18 > 0) {
         var21 = var2 + var16 * var18;
         var22 = var4 + var17 * var18;
         var28 = Direction.rotateOpposite[var10];
         var1.setBlockMetadataWithNotify(var21, var3, var22, var28 | var20, 3);
         this.func_150134_a(var1, var21, var3, var22, var28);
         this.func_150135_a(var1, var21, var3, var22, var13, var14, var11, var12);
      }

      this.func_150135_a(var1, var2, var3, var4, var13, var14, var11, var12);
      if(!var5) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 3);
         if(var7) {
            this.func_150134_a(var1, var2, var3, var4, var10);
         }
      }

      if(var11 != var13) {
         for(var21 = 1; var21 < var18; ++var21) {
            var22 = var2 + var16 * var21;
            var28 = var4 + var17 * var21;
            var24 = var19[var21];
            if(var24 >= 0) {
               if(var13) {
                  var24 |= 4;
               } else {
                  var24 &= -5;
               }

               var1.setBlockMetadataWithNotify(var22, var3, var28, var24, 3);
            }
         }
      }

   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      this.func_150136_a(var1, var2, var3, var4, false, var1.getBlockMetadata(var2, var3, var4), true, -1, 0);
   }

   private void func_150135_a(World var1, int var2, int var3, int var4, boolean var5, boolean var6, boolean var7, boolean var8) {
      if(var6 && !var8) {
         var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.1D, (double)var4 + 0.5D, "random.click", 0.4F, 0.6F);
      } else if(!var6 && var8) {
         var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.1D, (double)var4 + 0.5D, "random.click", 0.4F, 0.5F);
      } else if(var5 && !var7) {
         var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.1D, (double)var4 + 0.5D, "random.click", 0.4F, 0.7F);
      } else if(!var5 && var7) {
         var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.1D, (double)var4 + 0.5D, "random.bowhit", 0.4F, 1.2F / (var1.rand.nextFloat() * 0.2F + 0.9F));
      }

   }

   private void func_150134_a(World var1, int var2, int var3, int var4, int var5) {
      var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
      if(var5 == 3) {
         var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
      } else if(var5 == 1) {
         var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
      } else if(var5 == 0) {
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
      } else if(var5 == 2) {
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
      }

   }

   private boolean func_150137_e(World var1, int var2, int var3, int var4) {
      if(!this.canPlaceBlockAt(var1, var2, var3, var4)) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockToAir(var2, var3, var4);
         return false;
      } else {
         return true;
      }
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4) & 3;
      float var6 = 0.1875F;
      if(var5 == 3) {
         this.setBlockBounds(0.0F, 0.2F, 0.5F - var6, var6 * 2.0F, 0.8F, 0.5F + var6);
      } else if(var5 == 1) {
         this.setBlockBounds(1.0F - var6 * 2.0F, 0.2F, 0.5F - var6, 1.0F, 0.8F, 0.5F + var6);
      } else if(var5 == 0) {
         this.setBlockBounds(0.5F - var6, 0.2F, 0.0F, 0.5F + var6, 0.8F, var6 * 2.0F);
      } else if(var5 == 2) {
         this.setBlockBounds(0.5F - var6, 0.2F, 1.0F - var6 * 2.0F, 0.5F + var6, 0.8F, 1.0F);
      }

   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      boolean var7 = (var6 & 4) == 4;
      boolean var8 = (var6 & 8) == 8;
      if(var7 || var8) {
         this.func_150136_a(var1, var2, var3, var4, true, var6, false, -1, 0);
      }

      if(var8) {
         var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
         int var9 = var6 & 3;
         if(var9 == 3) {
            var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
         } else if(var9 == 1) {
            var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
         } else if(var9 == 0) {
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
         } else if(var9 == 2) {
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
         }
      }

      super.breakBlock(var1, var2, var3, var4, var5, var6);
   }

   public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return (var1.getBlockMetadata(var2, var3, var4) & 8) == 8?15:0;
   }

   public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      if((var6 & 8) != 8) {
         return 0;
      } else {
         int var7 = var6 & 3;
         return var7 == 2 && var5 == 2?15:(var7 == 0 && var5 == 3?15:(var7 == 1 && var5 == 4?15:(var7 == 3 && var5 == 5?15:0)));
      }
   }

   public boolean canProvidePower() {
      return true;
   }
}
