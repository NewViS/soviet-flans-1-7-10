package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockBasePressurePlate extends Block {

   private String field_150067_a;


   protected BlockBasePressurePlate(String var1, Material var2) {
      super(var2);
      this.field_150067_a = var1;
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setTickRandomly(true);
      this.func_150063_b(this.func_150066_d(15));
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      this.func_150063_b(var1.getBlockMetadata(var2, var3, var4));
   }

   protected void func_150063_b(int var1) {
      boolean var2 = this.func_150060_c(var1) > 0;
      float var3 = 0.0625F;
      if(var2) {
         this.setBlockBounds(var3, 0.0F, var3, 1.0F - var3, 0.03125F, 1.0F - var3);
      } else {
         this.setBlockBounds(var3, 0.0F, var3, 1.0F - var3, 0.0625F, 1.0F - var3);
      }

   }

   public int tickRate(World var1) {
      return 20;
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

   public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4) {
      return true;
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4) || BlockFence.func_149825_a(var1.getBlock(var2, var3 - 1, var4));
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      boolean var6 = false;
      if(!World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4) && !BlockFence.func_149825_a(var1.getBlock(var2, var3 - 1, var4))) {
         var6 = true;
      }

      if(var6) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockToAir(var2, var3, var4);
      }

   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!var1.isRemote) {
         int var6 = this.func_150060_c(var1.getBlockMetadata(var2, var3, var4));
         if(var6 > 0) {
            this.func_150062_a(var1, var2, var3, var4, var6);
         }

      }
   }

   public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
      if(!var1.isRemote) {
         int var6 = this.func_150060_c(var1.getBlockMetadata(var2, var3, var4));
         if(var6 == 0) {
            this.func_150062_a(var1, var2, var3, var4, var6);
         }

      }
   }

   protected void func_150062_a(World var1, int var2, int var3, int var4, int var5) {
      int var6 = this.func_150065_e(var1, var2, var3, var4);
      boolean var7 = var5 > 0;
      boolean var8 = var6 > 0;
      if(var5 != var6) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, this.func_150066_d(var6), 2);
         this.func_150064_a_(var1, var2, var3, var4);
         var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
      }

      if(!var8 && var7) {
         var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.1D, (double)var4 + 0.5D, "random.click", 0.3F, 0.5F);
      } else if(var8 && !var7) {
         var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.1D, (double)var4 + 0.5D, "random.click", 0.3F, 0.6F);
      }

      if(var8) {
         var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
      }

   }

   protected AxisAlignedBB func_150061_a(int var1, int var2, int var3) {
      float var4 = 0.125F;
      return AxisAlignedBB.getBoundingBox((double)((float)var1 + var4), (double)var2, (double)((float)var3 + var4), (double)((float)(var1 + 1) - var4), (double)var2 + 0.25D, (double)((float)(var3 + 1) - var4));
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      if(this.func_150060_c(var6) > 0) {
         this.func_150064_a_(var1, var2, var3, var4);
      }

      super.breakBlock(var1, var2, var3, var4, var5, var6);
   }

   protected void func_150064_a_(World var1, int var2, int var3, int var4) {
      var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
      var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
   }

   public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return this.func_150060_c(var1.getBlockMetadata(var2, var3, var4));
   }

   public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var5 == 1?this.func_150060_c(var1.getBlockMetadata(var2, var3, var4)):0;
   }

   public boolean canProvidePower() {
      return true;
   }

   public void setBlockBoundsForItemRender() {
      float var1 = 0.5F;
      float var2 = 0.125F;
      float var3 = 0.5F;
      this.setBlockBounds(0.5F - var1, 0.5F - var2, 0.5F - var3, 0.5F + var1, 0.5F + var2, 0.5F + var3);
   }

   public int getMobilityFlag() {
      return 1;
   }

   protected abstract int func_150065_e(World var1, int var2, int var3, int var4);

   protected abstract int func_150060_c(int var1);

   protected abstract int func_150066_d(int var1);

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.field_150067_a);
   }
}
