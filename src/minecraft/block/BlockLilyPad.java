package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLilyPad extends BlockBush {

   protected BlockLilyPad() {
      float var1 = 0.5F;
      float var2 = 0.015625F;
      this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var2, 0.5F + var1);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public int getRenderType() {
      return 23;
   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
      if(var7 == null || !(var7 instanceof EntityBoat)) {
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      }

   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return AxisAlignedBB.getBoundingBox((double)var2 + super.minX, (double)var3 + super.minY, (double)var4 + super.minZ, (double)var2 + super.maxX, (double)var3 + super.maxY, (double)var4 + super.maxZ);
   }

   public int getBlockColor() {
      return 2129968;
   }

   public int getRenderColor(int var1) {
      return 2129968;
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      return 2129968;
   }

   protected boolean canPlaceBlockOn(Block var1) {
      return var1 == Blocks.water;
   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      return var3 >= 0 && var3 < 256?var1.getBlock(var2, var3 - 1, var4).getMaterial() == Material.water && var1.getBlockMetadata(var2, var3 - 1, var4) == 0:false;
   }
}
