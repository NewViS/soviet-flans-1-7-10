package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLadder extends Block {

   protected BlockLadder() {
      super(Material.circuits);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      return super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
   }

   public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      return super.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      this.func_149797_b(var1.getBlockMetadata(var2, var3, var4));
   }

   public void func_149797_b(int var1) {
      float var3 = 0.125F;
      if(var1 == 2) {
         this.setBlockBounds(0.0F, 0.0F, 1.0F - var3, 1.0F, 1.0F, 1.0F);
      }

      if(var1 == 3) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var3);
      }

      if(var1 == 4) {
         this.setBlockBounds(1.0F - var3, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

      if(var1 == 5) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, var3, 1.0F, 1.0F);
      }

   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 8;
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return var1.getBlock(var2 - 1, var3, var4).isNormalCube()?true:(var1.getBlock(var2 + 1, var3, var4).isNormalCube()?true:(var1.getBlock(var2, var3, var4 - 1).isNormalCube()?true:var1.getBlock(var2, var3, var4 + 1).isNormalCube()));
   }

   public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9) {
      int var10 = var9;
      if((var9 == 0 || var5 == 2) && var1.getBlock(var2, var3, var4 + 1).isNormalCube()) {
         var10 = 2;
      }

      if((var10 == 0 || var5 == 3) && var1.getBlock(var2, var3, var4 - 1).isNormalCube()) {
         var10 = 3;
      }

      if((var10 == 0 || var5 == 4) && var1.getBlock(var2 + 1, var3, var4).isNormalCube()) {
         var10 = 4;
      }

      if((var10 == 0 || var5 == 5) && var1.getBlock(var2 - 1, var3, var4).isNormalCube()) {
         var10 = 5;
      }

      return var10;
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      boolean var7 = false;
      if(var6 == 2 && var1.getBlock(var2, var3, var4 + 1).isNormalCube()) {
         var7 = true;
      }

      if(var6 == 3 && var1.getBlock(var2, var3, var4 - 1).isNormalCube()) {
         var7 = true;
      }

      if(var6 == 4 && var1.getBlock(var2 + 1, var3, var4).isNormalCube()) {
         var7 = true;
      }

      if(var6 == 5 && var1.getBlock(var2 - 1, var3, var4).isNormalCube()) {
         var7 = true;
      }

      if(!var7) {
         this.dropBlockAsItem(var1, var2, var3, var4, var6, 0);
         var1.setBlockToAir(var2, var3, var4);
      }

      super.onNeighborBlockChange(var1, var2, var3, var4, var5);
   }

   public int quantityDropped(Random var1) {
      return 1;
   }
}
