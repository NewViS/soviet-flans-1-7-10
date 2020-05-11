package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCarpet extends Block {

   protected BlockCarpet() {
      super(Material.carpet);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.func_150089_b(0);
   }

   public IIcon getIcon(int var1, int var2) {
      return Blocks.wool.getIcon(var1, var2);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      byte var5 = 0;
      float var6 = 0.0625F;
      return AxisAlignedBB.getBoundingBox((double)var2 + super.minX, (double)var3 + super.minY, (double)var4 + super.minZ, (double)var2 + super.maxX, (double)((float)var3 + (float)var5 * var6), (double)var4 + super.maxZ);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public void setBlockBoundsForItemRender() {
      this.func_150089_b(0);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      this.func_150089_b(var1.getBlockMetadata(var2, var3, var4));
   }

   protected void func_150089_b(int var1) {
      byte var2 = 0;
      float var3 = (float)(1 * (1 + var2)) / 16.0F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, var3, 1.0F);
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return super.canPlaceBlockAt(var1, var2, var3, var4) && this.canBlockStay(var1, var2, var3, var4);
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      this.func_150090_e(var1, var2, var3, var4);
   }

   private boolean func_150090_e(World var1, int var2, int var3, int var4) {
      if(!this.canBlockStay(var1, var2, var3, var4)) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockToAir(var2, var3, var4);
         return false;
      } else {
         return true;
      }
   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      return !var1.isAirBlock(var2, var3 - 1, var4);
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var5 == 1?true:super.shouldSideBeRendered(var1, var2, var3, var4, var5);
   }

   public int damageDropped(int var1) {
      return var1;
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      for(int var4 = 0; var4 < 16; ++var4) {
         var3.add(new ItemStack(var1, 1, var4));
      }

   }

   public void registerBlockIcons(IIconRegister var1) {}
}
