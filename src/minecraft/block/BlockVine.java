package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVine extends Block {

   public BlockVine() {
      super(Material.vine);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public int getRenderType() {
      return 20;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      float var5 = 0.0625F;
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      float var7 = 1.0F;
      float var8 = 1.0F;
      float var9 = 1.0F;
      float var10 = 0.0F;
      float var11 = 0.0F;
      float var12 = 0.0F;
      boolean var13 = var6 > 0;
      if((var6 & 2) != 0) {
         var10 = Math.max(var10, 0.0625F);
         var7 = 0.0F;
         var8 = 0.0F;
         var11 = 1.0F;
         var9 = 0.0F;
         var12 = 1.0F;
         var13 = true;
      }

      if((var6 & 8) != 0) {
         var7 = Math.min(var7, 0.9375F);
         var10 = 1.0F;
         var8 = 0.0F;
         var11 = 1.0F;
         var9 = 0.0F;
         var12 = 1.0F;
         var13 = true;
      }

      if((var6 & 4) != 0) {
         var12 = Math.max(var12, 0.0625F);
         var9 = 0.0F;
         var7 = 0.0F;
         var10 = 1.0F;
         var8 = 0.0F;
         var11 = 1.0F;
         var13 = true;
      }

      if((var6 & 1) != 0) {
         var9 = Math.min(var9, 0.9375F);
         var12 = 1.0F;
         var7 = 0.0F;
         var10 = 1.0F;
         var8 = 0.0F;
         var11 = 1.0F;
         var13 = true;
      }

      if(!var13 && this.func_150093_a(var1.getBlock(var2, var3 + 1, var4))) {
         var8 = Math.min(var8, 0.9375F);
         var11 = 1.0F;
         var7 = 0.0F;
         var10 = 1.0F;
         var9 = 0.0F;
         var12 = 1.0F;
      }

      this.setBlockBounds(var7, var8, var9, var10, var11, var12);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return null;
   }

   public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5) {
      switch(var5) {
      case 1:
         return this.func_150093_a(var1.getBlock(var2, var3 + 1, var4));
      case 2:
         return this.func_150093_a(var1.getBlock(var2, var3, var4 + 1));
      case 3:
         return this.func_150093_a(var1.getBlock(var2, var3, var4 - 1));
      case 4:
         return this.func_150093_a(var1.getBlock(var2 + 1, var3, var4));
      case 5:
         return this.func_150093_a(var1.getBlock(var2 - 1, var3, var4));
      default:
         return false;
      }
   }

   private boolean func_150093_a(Block var1) {
      return var1.renderAsNormalBlock() && var1.blockMaterial.blocksMovement();
   }

   private boolean func_150094_e(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      int var6 = var5;
      if(var5 > 0) {
         for(int var7 = 0; var7 <= 3; ++var7) {
            int var8 = 1 << var7;
            if((var5 & var8) != 0 && !this.func_150093_a(var1.getBlock(var2 + Direction.offsetX[var7], var3, var4 + Direction.offsetZ[var7])) && (var1.getBlock(var2, var3 + 1, var4) != this || (var1.getBlockMetadata(var2, var3 + 1, var4) & var8) == 0)) {
               var6 &= ~var8;
            }
         }
      }

      if(var6 == 0 && !this.func_150093_a(var1.getBlock(var2, var3 + 1, var4))) {
         return false;
      } else {
         if(var6 != var5) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 2);
         }

         return true;
      }
   }

   public int getBlockColor() {
      return ColorizerFoliage.getFoliageColorBasic();
   }

   public int getRenderColor(int var1) {
      return ColorizerFoliage.getFoliageColorBasic();
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      return var1.getBiomeGenForCoords(var2, var4).getBiomeFoliageColor(var2, var3, var4);
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!var1.isRemote && !this.func_150094_e(var1, var2, var3, var4)) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockToAir(var2, var3, var4);
      }

   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!var1.isRemote && var1.rand.nextInt(4) == 0) {
         byte var6 = 4;
         int var7 = 5;
         boolean var8 = false;

         int var9;
         int var10;
         int var11;
         label134:
         for(var9 = var2 - var6; var9 <= var2 + var6; ++var9) {
            for(var10 = var4 - var6; var10 <= var4 + var6; ++var10) {
               for(var11 = var3 - 1; var11 <= var3 + 1; ++var11) {
                  if(var1.getBlock(var9, var11, var10) == this) {
                     --var7;
                     if(var7 <= 0) {
                        var8 = true;
                        break label134;
                     }
                  }
               }
            }
         }

         var9 = var1.getBlockMetadata(var2, var3, var4);
         var10 = var1.rand.nextInt(6);
         var11 = Direction.facingToDirection[var10];
         int var13;
         if(var10 == 1 && var3 < 255 && var1.isAirBlock(var2, var3 + 1, var4)) {
            if(var8) {
               return;
            }

            int var15 = var1.rand.nextInt(16) & var9;
            if(var15 > 0) {
               for(var13 = 0; var13 <= 3; ++var13) {
                  if(!this.func_150093_a(var1.getBlock(var2 + Direction.offsetX[var13], var3 + 1, var4 + Direction.offsetZ[var13]))) {
                     var15 &= ~(1 << var13);
                  }
               }

               if(var15 > 0) {
                  var1.setBlock(var2, var3 + 1, var4, this, var15, 2);
               }
            }
         } else {
            Block var12;
            int var14;
            if(var10 >= 2 && var10 <= 5 && (var9 & 1 << var11) == 0) {
               if(var8) {
                  return;
               }

               var12 = var1.getBlock(var2 + Direction.offsetX[var11], var3, var4 + Direction.offsetZ[var11]);
               if(var12.blockMaterial == Material.air) {
                  var13 = var11 + 1 & 3;
                  var14 = var11 + 3 & 3;
                  if((var9 & 1 << var13) != 0 && this.func_150093_a(var1.getBlock(var2 + Direction.offsetX[var11] + Direction.offsetX[var13], var3, var4 + Direction.offsetZ[var11] + Direction.offsetZ[var13]))) {
                     var1.setBlock(var2 + Direction.offsetX[var11], var3, var4 + Direction.offsetZ[var11], this, 1 << var13, 2);
                  } else if((var9 & 1 << var14) != 0 && this.func_150093_a(var1.getBlock(var2 + Direction.offsetX[var11] + Direction.offsetX[var14], var3, var4 + Direction.offsetZ[var11] + Direction.offsetZ[var14]))) {
                     var1.setBlock(var2 + Direction.offsetX[var11], var3, var4 + Direction.offsetZ[var11], this, 1 << var14, 2);
                  } else if((var9 & 1 << var13) != 0 && var1.isAirBlock(var2 + Direction.offsetX[var11] + Direction.offsetX[var13], var3, var4 + Direction.offsetZ[var11] + Direction.offsetZ[var13]) && this.func_150093_a(var1.getBlock(var2 + Direction.offsetX[var13], var3, var4 + Direction.offsetZ[var13]))) {
                     var1.setBlock(var2 + Direction.offsetX[var11] + Direction.offsetX[var13], var3, var4 + Direction.offsetZ[var11] + Direction.offsetZ[var13], this, 1 << (var11 + 2 & 3), 2);
                  } else if((var9 & 1 << var14) != 0 && var1.isAirBlock(var2 + Direction.offsetX[var11] + Direction.offsetX[var14], var3, var4 + Direction.offsetZ[var11] + Direction.offsetZ[var14]) && this.func_150093_a(var1.getBlock(var2 + Direction.offsetX[var14], var3, var4 + Direction.offsetZ[var14]))) {
                     var1.setBlock(var2 + Direction.offsetX[var11] + Direction.offsetX[var14], var3, var4 + Direction.offsetZ[var11] + Direction.offsetZ[var14], this, 1 << (var11 + 2 & 3), 2);
                  } else if(this.func_150093_a(var1.getBlock(var2 + Direction.offsetX[var11], var3 + 1, var4 + Direction.offsetZ[var11]))) {
                     var1.setBlock(var2 + Direction.offsetX[var11], var3, var4 + Direction.offsetZ[var11], this, 0, 2);
                  }
               } else if(var12.blockMaterial.isOpaque() && var12.renderAsNormalBlock()) {
                  var1.setBlockMetadataWithNotify(var2, var3, var4, var9 | 1 << var11, 2);
               }
            } else if(var3 > 1) {
               var12 = var1.getBlock(var2, var3 - 1, var4);
               if(var12.blockMaterial == Material.air) {
                  var13 = var1.rand.nextInt(16) & var9;
                  if(var13 > 0) {
                     var1.setBlock(var2, var3 - 1, var4, this, var13, 2);
                  }
               } else if(var12 == this) {
                  var13 = var1.rand.nextInt(16) & var9;
                  var14 = var1.getBlockMetadata(var2, var3 - 1, var4);
                  if(var14 != (var14 | var13)) {
                     var1.setBlockMetadataWithNotify(var2, var3 - 1, var4, var14 | var13, 2);
                  }
               }
            }
         }
      }

   }

   public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9) {
      byte var10 = 0;
      switch(var5) {
      case 2:
         var10 = 1;
         break;
      case 3:
         var10 = 4;
         break;
      case 4:
         var10 = 8;
         break;
      case 5:
         var10 = 2;
      }

      return var10 != 0?var10:var9;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return null;
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6) {
      if(!var1.isRemote && var2.getCurrentEquippedItem() != null && var2.getCurrentEquippedItem().getItem() == Items.shears) {
         var2.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
         this.dropBlockAsItem(var1, var3, var4, var5, new ItemStack(Blocks.vine, 1, 0));
      } else {
         super.harvestBlock(var1, var2, var3, var4, var5, var6);
      }

   }
}
