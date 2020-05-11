package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase$Rail;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockRailBase extends Block {

   protected final boolean field_150053_a;


   public static final boolean func_150049_b_(World var0, int var1, int var2, int var3) {
      return func_150051_a(var0.getBlock(var1, var2, var3));
   }

   public static final boolean func_150051_a(Block var0) {
      return var0 == Blocks.rail || var0 == Blocks.golden_rail || var0 == Blocks.detector_rail || var0 == Blocks.activator_rail;
   }

   protected BlockRailBase(boolean var1) {
      super(Material.circuits);
      this.field_150053_a = var1;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      this.setCreativeTab(CreativeTabs.tabTransport);
   }

   public boolean isPowered() {
      return this.field_150053_a;
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return null;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      return super.collisionRayTrace(var1, var2, var3, var4, var5, var6);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      if(var5 >= 2 && var5 <= 5) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      }

   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 9;
   }

   public int quantityDropped(Random var1) {
      return 1;
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4);
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      if(!var1.isRemote) {
         this.func_150052_a(var1, var2, var3, var4, true);
         if(this.field_150053_a) {
            this.onNeighborBlockChange(var1, var2, var3, var4, this);
         }
      }

   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!var1.isRemote) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         int var7 = var6;
         if(this.field_150053_a) {
            var7 = var6 & 7;
         }

         boolean var8 = false;
         if(!World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4)) {
            var8 = true;
         }

         if(var7 == 2 && !World.doesBlockHaveSolidTopSurface(var1, var2 + 1, var3, var4)) {
            var8 = true;
         }

         if(var7 == 3 && !World.doesBlockHaveSolidTopSurface(var1, var2 - 1, var3, var4)) {
            var8 = true;
         }

         if(var7 == 4 && !World.doesBlockHaveSolidTopSurface(var1, var2, var3, var4 - 1)) {
            var8 = true;
         }

         if(var7 == 5 && !World.doesBlockHaveSolidTopSurface(var1, var2, var3, var4 + 1)) {
            var8 = true;
         }

         if(var8) {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockToAir(var2, var3, var4);
         } else {
            this.func_150048_a(var1, var2, var3, var4, var6, var7, var5);
         }

      }
   }

   protected void func_150048_a(World var1, int var2, int var3, int var4, int var5, int var6, Block var7) {}

   protected void func_150052_a(World var1, int var2, int var3, int var4, boolean var5) {
      if(!var1.isRemote) {
         (new BlockRailBase$Rail(this, var1, var2, var3, var4)).func_150655_a(var1.isBlockIndirectlyGettingPowered(var2, var3, var4), var5);
      }
   }

   public int getMobilityFlag() {
      return 0;
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      int var7 = var6;
      if(this.field_150053_a) {
         var7 = var6 & 7;
      }

      super.breakBlock(var1, var2, var3, var4, var5, var6);
      if(var7 == 2 || var7 == 3 || var7 == 4 || var7 == 5) {
         var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, var5);
      }

      if(this.field_150053_a) {
         var1.notifyBlocksOfNeighborChange(var2, var3, var4, var5);
         var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, var5);
      }

   }
}
