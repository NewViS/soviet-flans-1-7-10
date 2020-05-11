package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockEndPortalFrame extends Block {

   private IIcon iconEndPortalFrameTop;
   private IIcon iconEndPortalFrameEye;


   public BlockEndPortalFrame() {
      super(Material.rock);
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?this.iconEndPortalFrameTop:(var1 == 0?Blocks.end_stone.getBlockTextureFromSide(var1):super.blockIcon);
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_side");
      this.iconEndPortalFrameTop = var1.registerIcon(this.getTextureName() + "_top");
      this.iconEndPortalFrameEye = var1.registerIcon(this.getTextureName() + "_eye");
   }

   public IIcon getIconEndPortalFrameEye() {
      return this.iconEndPortalFrameEye;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public int getRenderType() {
      return 26;
   }

   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      int var8 = var1.getBlockMetadata(var2, var3, var4);
      if(isEnderEyeInserted(var8)) {
         this.setBlockBounds(0.3125F, 0.8125F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      }

      this.setBlockBoundsForItemRender();
   }

   public static boolean isEnderEyeInserted(int var0) {
      return (var0 & 4) != 0;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return null;
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      int var7 = ((MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
      var1.setBlockMetadataWithNotify(var2, var3, var4, var7, 2);
   }

   public boolean hasComparatorInputOverride() {
      return true;
   }

   public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      return isEnderEyeInserted(var6)?15:0;
   }
}
