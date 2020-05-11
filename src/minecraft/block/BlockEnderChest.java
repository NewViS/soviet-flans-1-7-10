package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockEnderChest extends BlockContainer {

   protected BlockEnderChest() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 22;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.obsidian);
   }

   public int quantityDropped(Random var1) {
      return 8;
   }

   protected boolean canSilkHarvest() {
      return true;
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      byte var7 = 0;
      int var8 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      if(var8 == 0) {
         var7 = 2;
      }

      if(var8 == 1) {
         var7 = 5;
      }

      if(var8 == 2) {
         var7 = 3;
      }

      if(var8 == 3) {
         var7 = 4;
      }

      var1.setBlockMetadataWithNotify(var2, var3, var4, var7, 2);
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      InventoryEnderChest var10 = var5.getInventoryEnderChest();
      TileEntityEnderChest var11 = (TileEntityEnderChest)var1.getTileEntity(var2, var3, var4);
      if(var10 != null && var11 != null) {
         if(var1.getBlock(var2, var3 + 1, var4).isNormalCube()) {
            return true;
         } else if(var1.isRemote) {
            return true;
         } else {
            var10.func_146031_a(var11);
            var5.displayGUIChest(var10);
            return true;
         }
      } else {
         return true;
      }
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityEnderChest();
   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      for(int var6 = 0; var6 < 3; ++var6) {
         double var10000 = (double)((float)var2 + var5.nextFloat());
         double var9 = (double)((float)var3 + var5.nextFloat());
         var10000 = (double)((float)var4 + var5.nextFloat());
         double var13 = 0.0D;
         double var15 = 0.0D;
         double var17 = 0.0D;
         int var19 = var5.nextInt(2) * 2 - 1;
         int var20 = var5.nextInt(2) * 2 - 1;
         var13 = ((double)var5.nextFloat() - 0.5D) * 0.125D;
         var15 = ((double)var5.nextFloat() - 0.5D) * 0.125D;
         var17 = ((double)var5.nextFloat() - 0.5D) * 0.125D;
         double var11 = (double)var4 + 0.5D + 0.25D * (double)var20;
         var17 = (double)(var5.nextFloat() * 1.0F * (float)var20);
         double var7 = (double)var2 + 0.5D + 0.25D * (double)var19;
         var13 = (double)(var5.nextFloat() * 1.0F * (float)var19);
         var1.spawnParticle("portal", var7, var9, var11, var13, var15, var17);
      }

   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon("obsidian");
   }
}
