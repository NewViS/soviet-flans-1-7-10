package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEndPortal extends BlockContainer {

   public static boolean field_149948_a;


   protected BlockEndPortal(Material var1) {
      super(var1);
      this.setLightLevel(1.0F);
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityEndPortal();
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      float var5 = 0.0625F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, var5, 1.0F);
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var5 != 0?false:super.shouldSideBeRendered(var1, var2, var3, var4, var5);
   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {}

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
      if(var5.ridingEntity == null && var5.riddenByEntity == null && !var1.isRemote) {
         var5.travelToDimension(1);
      }

   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      double var6 = (double)((float)var2 + var5.nextFloat());
      double var8 = (double)((float)var3 + 0.8F);
      double var10 = (double)((float)var4 + var5.nextFloat());
      double var12 = 0.0D;
      double var14 = 0.0D;
      double var16 = 0.0D;
      var1.spawnParticle("smoke", var6, var8, var10, var12, var14, var16);
   }

   public int getRenderType() {
      return -1;
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      if(!field_149948_a) {
         if(var1.provider.dimensionId != 0) {
            var1.setBlockToAir(var2, var3, var4);
         }

      }
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Item.getItemById(0);
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon("portal");
   }

   public MapColor getMapColor(int var1) {
      return MapColor.obsidianColor;
   }
}
