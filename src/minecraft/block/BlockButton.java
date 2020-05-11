package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockButton extends Block {

   private final boolean field_150047_a;


   protected BlockButton(boolean var1) {
      super(Material.circuits);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.field_150047_a = var1;
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return null;
   }

   public int tickRate(World var1) {
      return this.field_150047_a?30:20;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5) {
      return var5 == 2 && var1.getBlock(var2, var3, var4 + 1).isNormalCube()?true:(var5 == 3 && var1.getBlock(var2, var3, var4 - 1).isNormalCube()?true:(var5 == 4 && var1.getBlock(var2 + 1, var3, var4).isNormalCube()?true:var5 == 5 && var1.getBlock(var2 - 1, var3, var4).isNormalCube()));
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return var1.getBlock(var2 - 1, var3, var4).isNormalCube()?true:(var1.getBlock(var2 + 1, var3, var4).isNormalCube()?true:(var1.getBlock(var2, var3, var4 - 1).isNormalCube()?true:var1.getBlock(var2, var3, var4 + 1).isNormalCube()));
   }

   public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9) {
      int var10 = var1.getBlockMetadata(var2, var3, var4);
      int var11 = var10 & 8;
      var10 &= 7;
      if(var5 == 2 && var1.getBlock(var2, var3, var4 + 1).isNormalCube()) {
         var10 = 4;
      } else if(var5 == 3 && var1.getBlock(var2, var3, var4 - 1).isNormalCube()) {
         var10 = 3;
      } else if(var5 == 4 && var1.getBlock(var2 + 1, var3, var4).isNormalCube()) {
         var10 = 2;
      } else if(var5 == 5 && var1.getBlock(var2 - 1, var3, var4).isNormalCube()) {
         var10 = 1;
      } else {
         var10 = this.func_150045_e(var1, var2, var3, var4);
      }

      return var10 + var11;
   }

   private int func_150045_e(World var1, int var2, int var3, int var4) {
      return var1.getBlock(var2 - 1, var3, var4).isNormalCube()?1:(var1.getBlock(var2 + 1, var3, var4).isNormalCube()?2:(var1.getBlock(var2, var3, var4 - 1).isNormalCube()?3:(var1.getBlock(var2, var3, var4 + 1).isNormalCube()?4:1)));
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(this.func_150044_m(var1, var2, var3, var4)) {
         int var6 = var1.getBlockMetadata(var2, var3, var4) & 7;
         boolean var7 = false;
         if(!var1.getBlock(var2 - 1, var3, var4).isNormalCube() && var6 == 1) {
            var7 = true;
         }

         if(!var1.getBlock(var2 + 1, var3, var4).isNormalCube() && var6 == 2) {
            var7 = true;
         }

         if(!var1.getBlock(var2, var3, var4 - 1).isNormalCube() && var6 == 3) {
            var7 = true;
         }

         if(!var1.getBlock(var2, var3, var4 + 1).isNormalCube() && var6 == 4) {
            var7 = true;
         }

         if(var7) {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockToAir(var2, var3, var4);
         }
      }

   }

   private boolean func_150044_m(World var1, int var2, int var3, int var4) {
      if(!this.canPlaceBlockAt(var1, var2, var3, var4)) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockToAir(var2, var3, var4);
         return false;
      } else {
         return true;
      }
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      this.func_150043_b(var5);
   }

   private void func_150043_b(int var1) {
      int var2 = var1 & 7;
      boolean var3 = (var1 & 8) > 0;
      float var4 = 0.375F;
      float var5 = 0.625F;
      float var6 = 0.1875F;
      float var7 = 0.125F;
      if(var3) {
         var7 = 0.0625F;
      }

      if(var2 == 1) {
         this.setBlockBounds(0.0F, var4, 0.5F - var6, var7, var5, 0.5F + var6);
      } else if(var2 == 2) {
         this.setBlockBounds(1.0F - var7, var4, 0.5F - var6, 1.0F, var5, 0.5F + var6);
      } else if(var2 == 3) {
         this.setBlockBounds(0.5F - var6, var4, 0.0F, 0.5F + var6, var5, var7);
      } else if(var2 == 4) {
         this.setBlockBounds(0.5F - var6, var4, 1.0F - var7, 0.5F + var6, var5, 1.0F);
      }

   }

   public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {}

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      int var10 = var1.getBlockMetadata(var2, var3, var4);
      int var11 = var10 & 7;
      int var12 = 8 - (var10 & 8);
      if(var12 == 0) {
         return true;
      } else {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var11 + var12, 3);
         var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
         var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 0.3F, 0.6F);
         this.func_150042_a(var1, var2, var3, var4, var11);
         var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
         return true;
      }
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      if((var6 & 8) > 0) {
         int var7 = var6 & 7;
         this.func_150042_a(var1, var2, var3, var4, var7);
      }

      super.breakBlock(var1, var2, var3, var4, var5, var6);
   }

   public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return (var1.getBlockMetadata(var2, var3, var4) & 8) > 0?15:0;
   }

   public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      if((var6 & 8) == 0) {
         return 0;
      } else {
         int var7 = var6 & 7;
         return var7 == 5 && var5 == 1?15:(var7 == 4 && var5 == 2?15:(var7 == 3 && var5 == 3?15:(var7 == 2 && var5 == 4?15:(var7 == 1 && var5 == 5?15:0))));
      }
   }

   public boolean canProvidePower() {
      return true;
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!var1.isRemote) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         if((var6 & 8) != 0) {
            if(this.field_150047_a) {
               this.func_150046_n(var1, var2, var3, var4);
            } else {
               var1.setBlockMetadataWithNotify(var2, var3, var4, var6 & 7, 3);
               int var7 = var6 & 7;
               this.func_150042_a(var1, var2, var3, var4, var7);
               var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 0.3F, 0.5F);
               var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
            }

         }
      }
   }

   public void setBlockBoundsForItemRender() {
      float var1 = 0.1875F;
      float var2 = 0.125F;
      float var3 = 0.125F;
      this.setBlockBounds(0.5F - var1, 0.5F - var2, 0.5F - var3, 0.5F + var1, 0.5F + var2, 0.5F + var3);
   }

   public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
      if(!var1.isRemote) {
         if(this.field_150047_a) {
            if((var1.getBlockMetadata(var2, var3, var4) & 8) == 0) {
               this.func_150046_n(var1, var2, var3, var4);
            }
         }
      }
   }

   private void func_150046_n(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      int var6 = var5 & 7;
      boolean var7 = (var5 & 8) != 0;
      this.func_150043_b(var5);
      List var9 = var1.getEntitiesWithinAABB(EntityArrow.class, AxisAlignedBB.getBoundingBox((double)var2 + super.minX, (double)var3 + super.minY, (double)var4 + super.minZ, (double)var2 + super.maxX, (double)var3 + super.maxY, (double)var4 + super.maxZ));
      boolean var8 = !var9.isEmpty();
      if(var8 && !var7) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var6 | 8, 3);
         this.func_150042_a(var1, var2, var3, var4, var6);
         var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
         var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 0.3F, 0.6F);
      }

      if(!var8 && var7) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 3);
         this.func_150042_a(var1, var2, var3, var4, var6);
         var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
         var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 0.3F, 0.5F);
      }

      if(var8) {
         var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
      }

   }

   private void func_150042_a(World var1, int var2, int var3, int var4, int var5) {
      var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
      if(var5 == 1) {
         var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
      } else if(var5 == 2) {
         var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
      } else if(var5 == 3) {
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
      } else if(var5 == 4) {
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
      } else {
         var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
      }

   }

   public void registerBlockIcons(IIconRegister var1) {}
}
