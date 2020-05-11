package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockRedstoneDiode extends BlockDirectional {

   protected final boolean isRepeaterPowered;


   protected BlockRedstoneDiode(boolean var1) {
      super(Material.circuits);
      this.isRepeaterPowered = var1;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return !World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4)?false:super.canPlaceBlockAt(var1, var2, var3, var4);
   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      return !World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4)?false:super.canBlockStay(var1, var2, var3, var4);
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      if(!this.func_149910_g(var1, var2, var3, var4, var6)) {
         boolean var7 = this.isGettingInput(var1, var2, var3, var4, var6);
         if(this.isRepeaterPowered && !var7) {
            var1.setBlock(var2, var3, var4, this.getBlockUnpowered(), var6, 2);
         } else if(!this.isRepeaterPowered) {
            var1.setBlock(var2, var3, var4, this.getBlockPowered(), var6, 2);
            if(!var7) {
               var1.scheduleBlockUpdateWithPriority(var2, var3, var4, this.getBlockPowered(), this.func_149899_k(var6), -1);
            }
         }
      }

   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 0?(this.isRepeaterPowered?Blocks.redstone_torch.getBlockTextureFromSide(var1):Blocks.unlit_redstone_torch.getBlockTextureFromSide(var1)):(var1 == 1?super.blockIcon:Blocks.double_stone_slab.getBlockTextureFromSide(1));
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var5 != 0 && var5 != 1;
   }

   public int getRenderType() {
      return 36;
   }

   protected boolean func_149905_c(int var1) {
      return this.isRepeaterPowered;
   }

   public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return this.isProvidingWeakPower(var1, var2, var3, var4, var5);
   }

   public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      if(!this.func_149905_c(var6)) {
         return 0;
      } else {
         int var7 = getDirection(var6);
         return var7 == 0 && var5 == 3?this.func_149904_f(var1, var2, var3, var4, var6):(var7 == 1 && var5 == 4?this.func_149904_f(var1, var2, var3, var4, var6):(var7 == 2 && var5 == 2?this.func_149904_f(var1, var2, var3, var4, var6):(var7 == 3 && var5 == 5?this.func_149904_f(var1, var2, var3, var4, var6):0)));
      }
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!this.canBlockStay(var1, var2, var3, var4)) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockToAir(var2, var3, var4);
         var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
         var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
      } else {
         this.func_149897_b(var1, var2, var3, var4, var5);
      }
   }

   protected void func_149897_b(World var1, int var2, int var3, int var4, Block var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      if(!this.func_149910_g(var1, var2, var3, var4, var6)) {
         boolean var7 = this.isGettingInput(var1, var2, var3, var4, var6);
         if((this.isRepeaterPowered && !var7 || !this.isRepeaterPowered && var7) && !var1.isBlockTickScheduledThisTick(var2, var3, var4, this)) {
            byte var8 = -1;
            if(this.func_149912_i(var1, var2, var3, var4, var6)) {
               var8 = -3;
            } else if(this.isRepeaterPowered) {
               var8 = -2;
            }

            var1.scheduleBlockUpdateWithPriority(var2, var3, var4, this, this.func_149901_b(var6), var8);
         }
      }

   }

   public boolean func_149910_g(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return false;
   }

   protected boolean isGettingInput(World var1, int var2, int var3, int var4, int var5) {
      return this.getInputStrength(var1, var2, var3, var4, var5) > 0;
   }

   protected int getInputStrength(World var1, int var2, int var3, int var4, int var5) {
      int var6 = getDirection(var5);
      int var7 = var2 + Direction.offsetX[var6];
      int var8 = var4 + Direction.offsetZ[var6];
      int var9 = var1.getIndirectPowerLevelTo(var7, var3, var8, Direction.directionToFacing[var6]);
      return var9 >= 15?var9:Math.max(var9, var1.getBlock(var7, var3, var8) == Blocks.redstone_wire?var1.getBlockMetadata(var7, var3, var8):0);
   }

   protected int func_149902_h(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      int var6 = getDirection(var5);
      switch(var6) {
      case 0:
      case 2:
         return Math.max(this.func_149913_i(var1, var2 - 1, var3, var4, 4), this.func_149913_i(var1, var2 + 1, var3, var4, 5));
      case 1:
      case 3:
         return Math.max(this.func_149913_i(var1, var2, var3, var4 + 1, 3), this.func_149913_i(var1, var2, var3, var4 - 1, 2));
      default:
         return 0;
      }
   }

   protected int func_149913_i(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      Block var6 = var1.getBlock(var2, var3, var4);
      return this.func_149908_a(var6)?(var6 == Blocks.redstone_wire?var1.getBlockMetadata(var2, var3, var4):var1.isBlockProvidingPowerTo(var2, var3, var4, var5)):0;
   }

   public boolean canProvidePower() {
      return true;
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      int var7 = ((MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
      var1.setBlockMetadataWithNotify(var2, var3, var4, var7, 3);
      boolean var8 = this.isGettingInput(var1, var2, var3, var4, var7);
      if(var8) {
         var1.scheduleBlockUpdate(var2, var3, var4, this, 1);
      }

   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      this.func_149911_e(var1, var2, var3, var4);
   }

   protected void func_149911_e(World var1, int var2, int var3, int var4) {
      int var5 = getDirection(var1.getBlockMetadata(var2, var3, var4));
      if(var5 == 1) {
         var1.notifyBlockOfNeighborChange(var2 + 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this, 4);
      }

      if(var5 == 3) {
         var1.notifyBlockOfNeighborChange(var2 - 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this, 5);
      }

      if(var5 == 2) {
         var1.notifyBlockOfNeighborChange(var2, var3, var4 + 1, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this, 2);
      }

      if(var5 == 0) {
         var1.notifyBlockOfNeighborChange(var2, var3, var4 - 1, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this, 3);
      }

   }

   public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5) {
      if(this.isRepeaterPowered) {
         var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
         var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
      }

      super.onBlockDestroyedByPlayer(var1, var2, var3, var4, var5);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   protected boolean func_149908_a(Block var1) {
      return var1.canProvidePower();
   }

   protected int func_149904_f(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return 15;
   }

   public static boolean isRedstoneRepeaterBlockID(Block var0) {
      return Blocks.unpowered_repeater.func_149907_e(var0) || Blocks.unpowered_comparator.func_149907_e(var0);
   }

   public boolean func_149907_e(Block var1) {
      return var1 == this.getBlockPowered() || var1 == this.getBlockUnpowered();
   }

   public boolean func_149912_i(World var1, int var2, int var3, int var4, int var5) {
      int var6 = getDirection(var5);
      if(isRedstoneRepeaterBlockID(var1.getBlock(var2 - Direction.offsetX[var6], var3, var4 - Direction.offsetZ[var6]))) {
         int var7 = var1.getBlockMetadata(var2 - Direction.offsetX[var6], var3, var4 - Direction.offsetZ[var6]);
         int var8 = getDirection(var7);
         return var8 != var6;
      } else {
         return false;
      }
   }

   protected int func_149899_k(int var1) {
      return this.func_149901_b(var1);
   }

   protected abstract int func_149901_b(int var1);

   protected abstract BlockRedstoneDiode getBlockPowered();

   protected abstract BlockRedstoneDiode getBlockUnpowered();

   public boolean isAssociatedBlock(Block var1) {
      return this.func_149907_e(var1);
   }
}
