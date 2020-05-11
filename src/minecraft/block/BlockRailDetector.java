package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRailDetector extends BlockRailBase {

   private IIcon[] field_150055_b;


   public BlockRailDetector() {
      super(true);
      this.setTickRandomly(true);
   }

   public int tickRate(World var1) {
      return 20;
   }

   public boolean canProvidePower() {
      return true;
   }

   public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
      if(!var1.isRemote) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         if((var6 & 8) == 0) {
            this.func_150054_a(var1, var2, var3, var4, var6);
         }
      }
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!var1.isRemote) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         if((var6 & 8) != 0) {
            this.func_150054_a(var1, var2, var3, var4, var6);
         }
      }
   }

   public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return (var1.getBlockMetadata(var2, var3, var4) & 8) != 0?15:0;
   }

   public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return (var1.getBlockMetadata(var2, var3, var4) & 8) == 0?0:(var5 == 1?15:0);
   }

   private void func_150054_a(World var1, int var2, int var3, int var4, int var5) {
      boolean var6 = (var5 & 8) != 0;
      boolean var7 = false;
      float var8 = 0.125F;
      List var9 = var1.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox((double)((float)var2 + var8), (double)var3, (double)((float)var4 + var8), (double)((float)(var2 + 1) - var8), (double)((float)(var3 + 1) - var8), (double)((float)(var4 + 1) - var8)));
      if(!var9.isEmpty()) {
         var7 = true;
      }

      if(var7 && !var6) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var5 | 8, 3);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
         var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
      }

      if(!var7 && var6) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var5 & 7, 3);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
         var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
      }

      if(var7) {
         var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
      }

      var1.func_147453_f(var2, var3, var4, this);
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      super.onBlockAdded(var1, var2, var3, var4);
      this.func_150054_a(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4));
   }

   public boolean hasComparatorInputOverride() {
      return true;
   }

   public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5) {
      if((var1.getBlockMetadata(var2, var3, var4) & 8) > 0) {
         float var6 = 0.125F;
         List var7 = var1.getEntitiesWithinAABB(EntityMinecartCommandBlock.class, AxisAlignedBB.getBoundingBox((double)((float)var2 + var6), (double)var3, (double)((float)var4 + var6), (double)((float)(var2 + 1) - var6), (double)((float)(var3 + 1) - var6), (double)((float)(var4 + 1) - var6)));
         if(var7.size() > 0) {
            return ((EntityMinecartCommandBlock)var7.get(0)).func_145822_e().func_145760_g();
         }

         List var8 = var1.selectEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox((double)((float)var2 + var6), (double)var3, (double)((float)var4 + var6), (double)((float)(var2 + 1) - var6), (double)((float)(var3 + 1) - var6), (double)((float)(var4 + 1) - var6)), IEntitySelector.selectInventories);
         if(var8.size() > 0) {
            return Container.calcRedstoneFromInventory((IInventory)var8.get(0));
         }
      }

      return 0;
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_150055_b = new IIcon[2];
      this.field_150055_b[0] = var1.registerIcon(this.getTextureName());
      this.field_150055_b[1] = var1.registerIcon(this.getTextureName() + "_powered");
   }

   public IIcon getIcon(int var1, int var2) {
      return (var2 & 8) != 0?this.field_150055_b[1]:this.field_150055_b[0];
   }
}
