package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSign extends BlockContainer {

   private Class field_149968_a;
   private boolean field_149967_b;


   protected BlockSign(Class var1, boolean var2) {
      super(Material.wood);
      this.field_149967_b = var2;
      this.field_149968_a = var1;
      float var3 = 0.25F;
      float var4 = 1.0F;
      this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
   }

   public IIcon getIcon(int var1, int var2) {
      return Blocks.planks.getBlockTextureFromSide(var1);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return null;
   }

   public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      return super.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      if(!this.field_149967_b) {
         int var5 = var1.getBlockMetadata(var2, var3, var4);
         float var6 = 0.28125F;
         float var7 = 0.78125F;
         float var8 = 0.0F;
         float var9 = 1.0F;
         float var10 = 0.125F;
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         if(var5 == 2) {
            this.setBlockBounds(var8, var6, 1.0F - var10, var9, var7, 1.0F);
         }

         if(var5 == 3) {
            this.setBlockBounds(var8, var6, 0.0F, var9, var7, var10);
         }

         if(var5 == 4) {
            this.setBlockBounds(1.0F - var10, var6, var8, 1.0F, var7, var9);
         }

         if(var5 == 5) {
            this.setBlockBounds(0.0F, var6, var8, var10, var7, var9);
         }

      }
   }

   public int getRenderType() {
      return -1;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4) {
      return true;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      try {
         return (TileEntity)this.field_149968_a.newInstance();
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.sign;
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      boolean var6 = false;
      if(this.field_149967_b) {
         if(!var1.getBlock(var2, var3 - 1, var4).getMaterial().isSolid()) {
            var6 = true;
         }
      } else {
         int var7 = var1.getBlockMetadata(var2, var3, var4);
         var6 = true;
         if(var7 == 2 && var1.getBlock(var2, var3, var4 + 1).getMaterial().isSolid()) {
            var6 = false;
         }

         if(var7 == 3 && var1.getBlock(var2, var3, var4 - 1).getMaterial().isSolid()) {
            var6 = false;
         }

         if(var7 == 4 && var1.getBlock(var2 + 1, var3, var4).getMaterial().isSolid()) {
            var6 = false;
         }

         if(var7 == 5 && var1.getBlock(var2 - 1, var3, var4).getMaterial().isSolid()) {
            var6 = false;
         }
      }

      if(var6) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockToAir(var2, var3, var4);
      }

      super.onNeighborBlockChange(var1, var2, var3, var4, var5);
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Items.sign;
   }

   public void registerBlockIcons(IIconRegister var1) {}
}
