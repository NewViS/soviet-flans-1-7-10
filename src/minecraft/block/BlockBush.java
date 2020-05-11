package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockBush extends Block {

   protected BlockBush(Material var1) {
      super(var1);
      this.setTickRandomly(true);
      float var2 = 0.2F;
      this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var2 * 3.0F, 0.5F + var2);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   protected BlockBush() {
      this(Material.plants);
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return super.canPlaceBlockAt(var1, var2, var3, var4) && this.canPlaceBlockOn(var1.getBlock(var2, var3 - 1, var4));
   }

   protected boolean canPlaceBlockOn(Block var1) {
      return var1 == Blocks.grass || var1 == Blocks.dirt || var1 == Blocks.farmland;
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      super.onNeighborBlockChange(var1, var2, var3, var4, var5);
      this.checkAndDropBlock(var1, var2, var3, var4);
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      this.checkAndDropBlock(var1, var2, var3, var4);
   }

   protected void checkAndDropBlock(World var1, int var2, int var3, int var4) {
      if(!this.canBlockStay(var1, var2, var3, var4)) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlock(var2, var3, var4, getBlockById(0), 0, 2);
      }

   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      return this.canPlaceBlockOn(var1.getBlock(var2, var3 - 1, var4));
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

   public int getRenderType() {
      return 1;
   }
}
