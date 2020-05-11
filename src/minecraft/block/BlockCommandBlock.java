package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.world.World;

public class BlockCommandBlock extends BlockContainer {

   public BlockCommandBlock() {
      super(Material.iron);
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityCommandBlock();
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!var1.isRemote) {
         boolean var6 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4);
         int var7 = var1.getBlockMetadata(var2, var3, var4);
         boolean var8 = (var7 & 1) != 0;
         if(var6 && !var8) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var7 | 1, 4);
            var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
         } else if(!var6 && var8) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var7 & -2, 4);
         }
      }

   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      TileEntity var6 = var1.getTileEntity(var2, var3, var4);
      if(var6 != null && var6 instanceof TileEntityCommandBlock) {
         CommandBlockLogic var7 = ((TileEntityCommandBlock)var6).func_145993_a();
         var7.func_145755_a(var1);
         var1.func_147453_f(var2, var3, var4, this);
      }

   }

   public int tickRate(World var1) {
      return 1;
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      TileEntityCommandBlock var10 = (TileEntityCommandBlock)var1.getTileEntity(var2, var3, var4);
      if(var10 != null) {
         var5.func_146100_a(var10);
      }

      return true;
   }

   public boolean hasComparatorInputOverride() {
      return true;
   }

   public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5) {
      TileEntity var6 = var1.getTileEntity(var2, var3, var4);
      return var6 != null && var6 instanceof TileEntityCommandBlock?((TileEntityCommandBlock)var6).func_145993_a().func_145760_g():0;
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      TileEntityCommandBlock var7 = (TileEntityCommandBlock)var1.getTileEntity(var2, var3, var4);
      if(var6.hasDisplayName()) {
         var7.func_145993_a().func_145754_b(var6.getDisplayName());
      }

   }

   public int quantityDropped(Random var1) {
      return 0;
   }
}
