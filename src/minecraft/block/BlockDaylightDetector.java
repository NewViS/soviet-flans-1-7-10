package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDaylightDetector;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDaylightDetector extends BlockContainer {

   private IIcon[] field_149958_a = new IIcon[2];


   public BlockDaylightDetector() {
      super(Material.wood);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
   }

   public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var1.getBlockMetadata(var2, var3, var4);
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {}

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {}

   public void onBlockAdded(World var1, int var2, int var3, int var4) {}

   public void func_149957_e(World var1, int var2, int var3, int var4) {
      if(!var1.provider.hasNoSky) {
         int var5 = var1.getBlockMetadata(var2, var3, var4);
         int var6 = var1.getSavedLightValue(EnumSkyBlock.Sky, var2, var3, var4) - var1.skylightSubtracted;
         float var7 = var1.getCelestialAngleRadians(1.0F);
         if(var7 < 3.1415927F) {
            var7 += (0.0F - var7) * 0.2F;
         } else {
            var7 += (6.2831855F - var7) * 0.2F;
         }

         var6 = Math.round((float)var6 * MathHelper.cos(var7));
         if(var6 < 0) {
            var6 = 0;
         }

         if(var6 > 15) {
            var6 = 15;
         }

         if(var5 != var6) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 3);
         }

      }
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean canProvidePower() {
      return true;
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityDaylightDetector();
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?this.field_149958_a[0]:this.field_149958_a[1];
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_149958_a[0] = var1.registerIcon(this.getTextureName() + "_top");
      this.field_149958_a[1] = var1.registerIcon(this.getTextureName() + "_side");
   }
}
