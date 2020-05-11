package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonMoving extends BlockContainer {

   public BlockPistonMoving() {
      super(Material.piston);
      this.setHardness(-1.0F);
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return null;
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {}

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      TileEntity var7 = var1.getTileEntity(var2, var3, var4);
      if(var7 instanceof TileEntityPiston) {
         ((TileEntityPiston)var7).clearPistonTileEntity();
      } else {
         super.breakBlock(var1, var2, var3, var4, var5, var6);
      }

   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return false;
   }

   public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5) {
      return false;
   }

   public int getRenderType() {
      return -1;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(!var1.isRemote && var1.getTileEntity(var2, var3, var4) == null) {
         var1.setBlockToAir(var2, var3, var4);
         return true;
      } else {
         return false;
      }
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return null;
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      if(!var1.isRemote) {
         TileEntityPiston var8 = this.func_149963_e(var1, var2, var3, var4);
         if(var8 != null) {
            var8.getStoredBlockID().dropBlockAsItem(var1, var2, var3, var4, var8.getBlockMetadata(), 0);
         }
      }
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!var1.isRemote) {
         var1.getTileEntity(var2, var3, var4);
      }

   }

   public static TileEntity getTileEntity(Block var0, int var1, int var2, boolean var3, boolean var4) {
      return new TileEntityPiston(var0, var1, var2, var3, var4);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      TileEntityPiston var5 = this.func_149963_e(var1, var2, var3, var4);
      if(var5 == null) {
         return null;
      } else {
         float var6 = var5.func_145860_a(0.0F);
         if(var5.isExtending()) {
            var6 = 1.0F - var6;
         }

         return this.func_149964_a(var1, var2, var3, var4, var5.getStoredBlockID(), var6, var5.getPistonOrientation());
      }
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      TileEntityPiston var5 = this.func_149963_e(var1, var2, var3, var4);
      if(var5 != null) {
         Block var6 = var5.getStoredBlockID();
         if(var6 == this || var6.getMaterial() == Material.air) {
            return;
         }

         var6.setBlockBoundsBasedOnState(var1, var2, var3, var4);
         float var7 = var5.func_145860_a(0.0F);
         if(var5.isExtending()) {
            var7 = 1.0F - var7;
         }

         int var8 = var5.getPistonOrientation();
         super.minX = var6.getBlockBoundsMinX() - (double)((float)Facing.offsetsXForSide[var8] * var7);
         super.minY = var6.getBlockBoundsMinY() - (double)((float)Facing.offsetsYForSide[var8] * var7);
         super.minZ = var6.getBlockBoundsMinZ() - (double)((float)Facing.offsetsZForSide[var8] * var7);
         super.maxX = var6.getBlockBoundsMaxX() - (double)((float)Facing.offsetsXForSide[var8] * var7);
         super.maxY = var6.getBlockBoundsMaxY() - (double)((float)Facing.offsetsYForSide[var8] * var7);
         super.maxZ = var6.getBlockBoundsMaxZ() - (double)((float)Facing.offsetsZForSide[var8] * var7);
      }

   }

   public AxisAlignedBB func_149964_a(World var1, int var2, int var3, int var4, Block var5, float var6, int var7) {
      if(var5 != this && var5.getMaterial() != Material.air) {
         AxisAlignedBB var8 = var5.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
         if(var8 == null) {
            return null;
         } else {
            if(Facing.offsetsXForSide[var7] < 0) {
               var8.minX -= (double)((float)Facing.offsetsXForSide[var7] * var6);
            } else {
               var8.maxX -= (double)((float)Facing.offsetsXForSide[var7] * var6);
            }

            if(Facing.offsetsYForSide[var7] < 0) {
               var8.minY -= (double)((float)Facing.offsetsYForSide[var7] * var6);
            } else {
               var8.maxY -= (double)((float)Facing.offsetsYForSide[var7] * var6);
            }

            if(Facing.offsetsZForSide[var7] < 0) {
               var8.minZ -= (double)((float)Facing.offsetsZForSide[var7] * var6);
            } else {
               var8.maxZ -= (double)((float)Facing.offsetsZForSide[var7] * var6);
            }

            return var8;
         }
      } else {
         return null;
      }
   }

   private TileEntityPiston func_149963_e(IBlockAccess var1, int var2, int var3, int var4) {
      TileEntity var5 = var1.getTileEntity(var2, var3, var4);
      return var5 instanceof TileEntityPiston?(TileEntityPiston)var5:null;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Item.getItemById(0);
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon("piston_top_normal");
   }
}
