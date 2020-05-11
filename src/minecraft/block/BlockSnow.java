package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSnow extends Block {

   protected BlockSnow() {
      super(Material.snow);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.func_150154_b(0);
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon("snow");
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4) & 7;
      float var6 = 0.125F;
      return AxisAlignedBB.getBoundingBox((double)var2 + super.minX, (double)var3 + super.minY, (double)var4 + super.minZ, (double)var2 + super.maxX, (double)((float)var3 + (float)var5 * var6), (double)var4 + super.maxZ);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public void setBlockBoundsForItemRender() {
      this.func_150154_b(0);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      this.func_150154_b(var1.getBlockMetadata(var2, var3, var4));
   }

   protected void func_150154_b(int var1) {
      int var2 = var1 & 7;
      float var3 = (float)(2 * (1 + var2)) / 16.0F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, var3, 1.0F);
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      Block var5 = var1.getBlock(var2, var3 - 1, var4);
      return var5 != Blocks.ice && var5 != Blocks.packed_ice?(var5.getMaterial() == Material.leaves?true:(var5 == this && (var1.getBlockMetadata(var2, var3 - 1, var4) & 7) == 7?true:var5.isOpaqueCube() && var5.blockMaterial.blocksMovement())):false;
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      this.func_150155_m(var1, var2, var3, var4);
   }

   private boolean func_150155_m(World var1, int var2, int var3, int var4) {
      if(!this.canPlaceBlockAt(var1, var2, var3, var4)) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockToAir(var2, var3, var4);
         return false;
      } else {
         return true;
      }
   }

   public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6) {
      int var7 = var6 & 7;
      this.dropBlockAsItem(var1, var3, var4, var5, new ItemStack(Items.snowball, var7 + 1, 0));
      var1.setBlockToAir(var3, var4, var5);
      var2.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.snowball;
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(var1.getSavedLightValue(EnumSkyBlock.Block, var2, var3, var4) > 11) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockToAir(var2, var3, var4);
      }

   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var5 == 1?true:super.shouldSideBeRendered(var1, var2, var3, var4, var5);
   }
}
