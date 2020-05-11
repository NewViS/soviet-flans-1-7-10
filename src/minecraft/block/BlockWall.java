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

public class BlockWall extends Block {

   public static final String[] field_150092_a = new String[]{"normal", "mossy"};


   public BlockWall(Block var1) {
      super(var1.blockMaterial);
      this.setHardness(var1.blockHardness);
      this.setResistance(var1.blockResistance / 3.0F);
      this.setStepSound(var1.stepSound);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      return var2 == 1?Blocks.mossy_cobblestone.getBlockTextureFromSide(var1):Blocks.cobblestone.getBlockTextureFromSide(var1);
   }

   public int getRenderType() {
      return 32;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4) {
      return false;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      boolean var5 = this.canConnectWallTo(var1, var2, var3, var4 - 1);
      boolean var6 = this.canConnectWallTo(var1, var2, var3, var4 + 1);
      boolean var7 = this.canConnectWallTo(var1, var2 - 1, var3, var4);
      boolean var8 = this.canConnectWallTo(var1, var2 + 1, var3, var4);
      float var9 = 0.25F;
      float var10 = 0.75F;
      float var11 = 0.25F;
      float var12 = 0.75F;
      float var13 = 1.0F;
      if(var5) {
         var11 = 0.0F;
      }

      if(var6) {
         var12 = 1.0F;
      }

      if(var7) {
         var9 = 0.0F;
      }

      if(var8) {
         var10 = 1.0F;
      }

      if(var5 && var6 && !var7 && !var8) {
         var13 = 0.8125F;
         var9 = 0.3125F;
         var10 = 0.6875F;
      } else if(!var5 && !var6 && var7 && var8) {
         var13 = 0.8125F;
         var11 = 0.3125F;
         var12 = 0.6875F;
      }

      this.setBlockBounds(var9, 0.0F, var11, var10, var13, var12);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      super.maxY = 1.5D;
      return super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
   }

   public boolean canConnectWallTo(IBlockAccess var1, int var2, int var3, int var4) {
      Block var5 = var1.getBlock(var2, var3, var4);
      return var5 != this && var5 != Blocks.fence_gate?(var5.blockMaterial.isOpaque() && var5.renderAsNormalBlock()?var5.blockMaterial != Material.gourd:false):true;
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
   }

   public int damageDropped(int var1) {
      return var1;
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var5 == 0?super.shouldSideBeRendered(var1, var2, var3, var4, var5):true;
   }

   public void registerBlockIcons(IIconRegister var1) {}

}
