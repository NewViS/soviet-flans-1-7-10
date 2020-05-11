package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityComparator;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneComparator extends BlockRedstoneDiode implements ITileEntityProvider {

   public BlockRedstoneComparator(boolean var1) {
      super(var1);
      super.isBlockContainer = true;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.comparator;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Items.comparator;
   }

   protected int func_149901_b(int var1) {
      return 2;
   }

   protected BlockRedstoneDiode getBlockPowered() {
      return Blocks.powered_comparator;
   }

   protected BlockRedstoneDiode getBlockUnpowered() {
      return Blocks.unpowered_comparator;
   }

   public int getRenderType() {
      return 37;
   }

   public IIcon getIcon(int var1, int var2) {
      boolean var3 = super.isRepeaterPowered || (var2 & 8) != 0;
      return var1 == 0?(var3?Blocks.redstone_torch.getBlockTextureFromSide(var1):Blocks.unlit_redstone_torch.getBlockTextureFromSide(var1)):(var1 == 1?(var3?Blocks.powered_comparator.blockIcon:super.blockIcon):Blocks.double_stone_slab.getBlockTextureFromSide(1));
   }

   protected boolean func_149905_c(int var1) {
      return super.isRepeaterPowered || (var1 & 8) != 0;
   }

   protected int func_149904_f(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return this.getTileEntityComparator(var1, var2, var3, var4).getOutputSignal();
   }

   private int getOutputStrength(World var1, int var2, int var3, int var4, int var5) {
      return !this.func_149969_d(var5)?this.getInputStrength(var1, var2, var3, var4, var5):Math.max(this.getInputStrength(var1, var2, var3, var4, var5) - this.func_149902_h(var1, var2, var3, var4, var5), 0);
   }

   public boolean func_149969_d(int var1) {
      return (var1 & 4) == 4;
   }

   protected boolean isGettingInput(World var1, int var2, int var3, int var4, int var5) {
      int var6 = this.getInputStrength(var1, var2, var3, var4, var5);
      if(var6 >= 15) {
         return true;
      } else if(var6 == 0) {
         return false;
      } else {
         int var7 = this.func_149902_h(var1, var2, var3, var4, var5);
         return var7 == 0?true:var6 >= var7;
      }
   }

   protected int getInputStrength(World var1, int var2, int var3, int var4, int var5) {
      int var6 = super.getInputStrength(var1, var2, var3, var4, var5);
      int var7 = getDirection(var5);
      int var8 = var2 + Direction.offsetX[var7];
      int var9 = var4 + Direction.offsetZ[var7];
      Block var10 = var1.getBlock(var8, var3, var9);
      if(var10.hasComparatorInputOverride()) {
         var6 = var10.getComparatorInputOverride(var1, var8, var3, var9, Direction.rotateOpposite[var7]);
      } else if(var6 < 15 && var10.isNormalCube()) {
         var8 += Direction.offsetX[var7];
         var9 += Direction.offsetZ[var7];
         var10 = var1.getBlock(var8, var3, var9);
         if(var10.hasComparatorInputOverride()) {
            var6 = var10.getComparatorInputOverride(var1, var8, var3, var9, Direction.rotateOpposite[var7]);
         }
      }

      return var6;
   }

   public TileEntityComparator getTileEntityComparator(IBlockAccess var1, int var2, int var3, int var4) {
      return (TileEntityComparator)var1.getTileEntity(var2, var3, var4);
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      int var10 = var1.getBlockMetadata(var2, var3, var4);
      boolean var11 = super.isRepeaterPowered | (var10 & 8) != 0;
      boolean var12 = !this.func_149969_d(var10);
      int var13 = var12?4:0;
      var13 |= var11?8:0;
      var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 0.3F, var12?0.55F:0.5F);
      var1.setBlockMetadataWithNotify(var2, var3, var4, var13 | var10 & 3, 2);
      this.func_149972_c(var1, var2, var3, var4, var1.rand);
      return true;
   }

   protected void func_149897_b(World var1, int var2, int var3, int var4, Block var5) {
      if(!var1.isBlockTickScheduledThisTick(var2, var3, var4, this)) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         int var7 = this.getOutputStrength(var1, var2, var3, var4, var6);
         int var8 = this.getTileEntityComparator(var1, var2, var3, var4).getOutputSignal();
         if(var7 != var8 || this.func_149905_c(var6) != this.isGettingInput(var1, var2, var3, var4, var6)) {
            if(this.func_149912_i(var1, var2, var3, var4, var6)) {
               var1.scheduleBlockUpdateWithPriority(var2, var3, var4, this, this.func_149901_b(0), -1);
            } else {
               var1.scheduleBlockUpdateWithPriority(var2, var3, var4, this, this.func_149901_b(0), 0);
            }
         }
      }

   }

   private void func_149972_c(World var1, int var2, int var3, int var4, Random var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      int var7 = this.getOutputStrength(var1, var2, var3, var4, var6);
      int var8 = this.getTileEntityComparator(var1, var2, var3, var4).getOutputSignal();
      this.getTileEntityComparator(var1, var2, var3, var4).setOutputSignal(var7);
      if(var8 != var7 || !this.func_149969_d(var6)) {
         boolean var9 = this.isGettingInput(var1, var2, var3, var4, var6);
         boolean var10 = super.isRepeaterPowered || (var6 & 8) != 0;
         if(var10 && !var9) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6 & -9, 2);
         } else if(!var10 && var9) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6 | 8, 2);
         }

         this.func_149911_e(var1, var2, var3, var4);
      }

   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(super.isRepeaterPowered) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         var1.setBlock(var2, var3, var4, this.getBlockUnpowered(), var6 | 8, 4);
      }

      this.func_149972_c(var1, var2, var3, var4, var5);
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      super.onBlockAdded(var1, var2, var3, var4);
      var1.setTileEntity(var2, var3, var4, this.createNewTileEntity(var1, 0));
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      super.breakBlock(var1, var2, var3, var4, var5, var6);
      var1.removeTileEntity(var2, var3, var4);
      this.func_149911_e(var1, var2, var3, var4);
   }

   public boolean onBlockEventReceived(World var1, int var2, int var3, int var4, int var5, int var6) {
      super.onBlockEventReceived(var1, var2, var3, var4, var5, var6);
      TileEntity var7 = var1.getTileEntity(var2, var3, var4);
      return var7 != null?var7.receiveClientEvent(var5, var6):false;
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityComparator();
   }
}
