package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTrapDoor extends Block {

   protected BlockTrapDoor(Material var1) {
      super(var1);
      float var2 = 0.5F;
      float var3 = 1.0F;
      this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var3, 0.5F + var2);
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4) {
      return !func_150118_d(var1.getBlockMetadata(var2, var3, var4));
   }

   public int getRenderType() {
      return 0;
   }

   public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      return super.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      return super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      this.func_150117_b(var1.getBlockMetadata(var2, var3, var4));
   }

   public void setBlockBoundsForItemRender() {
      float var1 = 0.1875F;
      this.setBlockBounds(0.0F, 0.5F - var1 / 2.0F, 0.0F, 1.0F, 0.5F + var1 / 2.0F, 1.0F);
   }

   public void func_150117_b(int var1) {
      float var2 = 0.1875F;
      if((var1 & 8) != 0) {
         this.setBlockBounds(0.0F, 1.0F - var2, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, var2, 1.0F);
      }

      if(func_150118_d(var1)) {
         if((var1 & 3) == 0) {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
         }

         if((var1 & 3) == 1) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
         }

         if((var1 & 3) == 2) {
            this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         }

         if((var1 & 3) == 3) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
         }
      }

   }

   public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {}

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(super.blockMaterial == Material.iron) {
         return true;
      } else {
         int var10 = var1.getBlockMetadata(var2, var3, var4);
         var1.setBlockMetadataWithNotify(var2, var3, var4, var10 ^ 4, 2);
         var1.playAuxSFXAtEntity(var5, 1003, var2, var3, var4, 0);
         return true;
      }
   }

   public void func_150120_a(World var1, int var2, int var3, int var4, boolean var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      boolean var7 = (var6 & 4) > 0;
      if(var7 != var5) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var6 ^ 4, 2);
         var1.playAuxSFXAtEntity((EntityPlayer)null, 1003, var2, var3, var4, 0);
      }
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!var1.isRemote) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         int var7 = var2;
         int var8 = var4;
         if((var6 & 3) == 0) {
            var8 = var4 + 1;
         }

         if((var6 & 3) == 1) {
            --var8;
         }

         if((var6 & 3) == 2) {
            var7 = var2 + 1;
         }

         if((var6 & 3) == 3) {
            --var7;
         }

         if(!func_150119_a(var1.getBlock(var7, var3, var8))) {
            var1.setBlockToAir(var2, var3, var4);
            this.dropBlockAsItem(var1, var2, var3, var4, var6, 0);
         }

         boolean var9 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4);
         if(var9 || var5.canProvidePower()) {
            this.func_150120_a(var1, var2, var3, var4, var9);
         }

      }
   }

   public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      return super.collisionRayTrace(var1, var2, var3, var4, var5, var6);
   }

   public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9) {
      int var10 = 0;
      if(var5 == 2) {
         var10 = 0;
      }

      if(var5 == 3) {
         var10 = 1;
      }

      if(var5 == 4) {
         var10 = 2;
      }

      if(var5 == 5) {
         var10 = 3;
      }

      if(var5 != 1 && var5 != 0 && var7 > 0.5F) {
         var10 |= 8;
      }

      return var10;
   }

   public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5) {
      if(var5 == 0) {
         return false;
      } else if(var5 == 1) {
         return false;
      } else {
         if(var5 == 2) {
            ++var4;
         }

         if(var5 == 3) {
            --var4;
         }

         if(var5 == 4) {
            ++var2;
         }

         if(var5 == 5) {
            --var2;
         }

         return func_150119_a(var1.getBlock(var2, var3, var4));
      }
   }

   public static boolean func_150118_d(int var0) {
      return (var0 & 4) != 0;
   }

   private static boolean func_150119_a(Block var0) {
      return var0.blockMaterial.isOpaque() && var0.renderAsNormalBlock() || var0 == Blocks.glowstone || var0 instanceof BlockSlab || var0 instanceof BlockStairs;
   }
}
