package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockLog;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCocoa extends BlockDirectional implements IGrowable {

   private IIcon[] field_149989_a;


   public BlockCocoa() {
      super(Material.plants);
      this.setTickRandomly(true);
   }

   public IIcon getIcon(int var1, int var2) {
      return this.field_149989_a[2];
   }

   public IIcon getCocoaIcon(int var1) {
      if(var1 < 0 || var1 >= this.field_149989_a.length) {
         var1 = this.field_149989_a.length - 1;
      }

      return this.field_149989_a[var1];
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!this.canBlockStay(var1, var2, var3, var4)) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlock(var2, var3, var4, getBlockById(0), 0, 2);
      } else if(var1.rand.nextInt(5) == 0) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         int var7 = func_149987_c(var6);
         if(var7 < 2) {
            ++var7;
            var1.setBlockMetadataWithNotify(var2, var3, var4, var7 << 2 | getDirection(var6), 2);
         }
      }

   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      int var5 = getDirection(var1.getBlockMetadata(var2, var3, var4));
      var2 += Direction.offsetX[var5];
      var4 += Direction.offsetZ[var5];
      Block var6 = var1.getBlock(var2, var3, var4);
      return var6 == Blocks.log && BlockLog.func_150165_c(var1.getBlockMetadata(var2, var3, var4)) == 3;
   }

   public int getRenderType() {
      return 28;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean isOpaqueCube() {
      return false;
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
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      int var6 = getDirection(var5);
      int var7 = func_149987_c(var5);
      int var8 = 4 + var7 * 2;
      int var9 = 5 + var7 * 2;
      float var10 = (float)var8 / 2.0F;
      switch(var6) {
      case 0:
         this.setBlockBounds((8.0F - var10) / 16.0F, (12.0F - (float)var9) / 16.0F, (15.0F - (float)var8) / 16.0F, (8.0F + var10) / 16.0F, 0.75F, 0.9375F);
         break;
      case 1:
         this.setBlockBounds(0.0625F, (12.0F - (float)var9) / 16.0F, (8.0F - var10) / 16.0F, (1.0F + (float)var8) / 16.0F, 0.75F, (8.0F + var10) / 16.0F);
         break;
      case 2:
         this.setBlockBounds((8.0F - var10) / 16.0F, (12.0F - (float)var9) / 16.0F, 0.0625F, (8.0F + var10) / 16.0F, 0.75F, (1.0F + (float)var8) / 16.0F);
         break;
      case 3:
         this.setBlockBounds((15.0F - (float)var8) / 16.0F, (12.0F - (float)var9) / 16.0F, (8.0F - var10) / 16.0F, 0.9375F, 0.75F, (8.0F + var10) / 16.0F);
      }

   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      int var7 = ((MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 0) % 4;
      var1.setBlockMetadataWithNotify(var2, var3, var4, var7, 2);
   }

   public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9) {
      if(var5 == 1 || var5 == 0) {
         var5 = 2;
      }

      return Direction.rotateOpposite[Direction.facingToDirection[var5]];
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!this.canBlockStay(var1, var2, var3, var4)) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlock(var2, var3, var4, getBlockById(0), 0, 2);
      }

   }

   public static int func_149987_c(int var0) {
      return (var0 & 12) >> 2;
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      int var8 = func_149987_c(var5);
      byte var9 = 1;
      if(var8 >= 2) {
         var9 = 3;
      }

      for(int var10 = 0; var10 < var9; ++var10) {
         this.dropBlockAsItem(var1, var2, var3, var4, new ItemStack(Items.dye, 1, 3));
      }

   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Items.dye;
   }

   public int getDamageValue(World var1, int var2, int var3, int var4) {
      return 3;
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_149989_a = new IIcon[3];

      for(int var2 = 0; var2 < this.field_149989_a.length; ++var2) {
         this.field_149989_a[var2] = var1.registerIcon(this.getTextureName() + "_stage_" + var2);
      }

   }

   public boolean func_149851_a(World var1, int var2, int var3, int var4, boolean var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      int var7 = func_149987_c(var6);
      return var7 < 2;
   }

   public boolean func_149852_a(World var1, Random var2, int var3, int var4, int var5) {
      return true;
   }

   public void func_149853_b(World var1, Random var2, int var3, int var4, int var5) {
      int var6 = var1.getBlockMetadata(var3, var4, var5);
      int var7 = BlockDirectional.getDirection(var6);
      int var8 = func_149987_c(var6);
      ++var8;
      var1.setBlockMetadataWithNotify(var3, var4, var5, var8 << 2 | var7, 2);
   }
}
