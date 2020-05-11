package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPane extends Block {

   private final String field_150100_a;
   private final boolean field_150099_b;
   private final String field_150101_M;
   private IIcon field_150102_N;


   protected BlockPane(String var1, String var2, Material var3, boolean var4) {
      super(var3);
      this.field_150100_a = var2;
      this.field_150099_b = var4;
      this.field_150101_M = var1;
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return !this.field_150099_b?null:super.getItemDropped(var1, var2, var3);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return super.blockMaterial == Material.glass?41:18;
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var1.getBlock(var2, var3, var4) == this?false:super.shouldSideBeRendered(var1, var2, var3, var4, var5);
   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
      boolean var8 = this.canPaneConnectToBlock(var1.getBlock(var2, var3, var4 - 1));
      boolean var9 = this.canPaneConnectToBlock(var1.getBlock(var2, var3, var4 + 1));
      boolean var10 = this.canPaneConnectToBlock(var1.getBlock(var2 - 1, var3, var4));
      boolean var11 = this.canPaneConnectToBlock(var1.getBlock(var2 + 1, var3, var4));
      if((!var10 || !var11) && (var10 || var11 || var8 || var9)) {
         if(var10 && !var11) {
            this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
            super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         } else if(!var10 && var11) {
            this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         }
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      }

      if((!var8 || !var9) && (var10 || var11 || var8 || var9)) {
         if(var8 && !var9) {
            this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
            super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         } else if(!var8 && var9) {
            this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         }
      } else {
         this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      }

   }

   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      float var5 = 0.4375F;
      float var6 = 0.5625F;
      float var7 = 0.4375F;
      float var8 = 0.5625F;
      boolean var9 = this.canPaneConnectToBlock(var1.getBlock(var2, var3, var4 - 1));
      boolean var10 = this.canPaneConnectToBlock(var1.getBlock(var2, var3, var4 + 1));
      boolean var11 = this.canPaneConnectToBlock(var1.getBlock(var2 - 1, var3, var4));
      boolean var12 = this.canPaneConnectToBlock(var1.getBlock(var2 + 1, var3, var4));
      if((!var11 || !var12) && (var11 || var12 || var9 || var10)) {
         if(var11 && !var12) {
            var5 = 0.0F;
         } else if(!var11 && var12) {
            var6 = 1.0F;
         }
      } else {
         var5 = 0.0F;
         var6 = 1.0F;
      }

      if((!var9 || !var10) && (var11 || var12 || var9 || var10)) {
         if(var9 && !var10) {
            var7 = 0.0F;
         } else if(!var9 && var10) {
            var8 = 1.0F;
         }
      } else {
         var7 = 0.0F;
         var8 = 1.0F;
      }

      this.setBlockBounds(var5, 0.0F, var7, var6, 1.0F, var8);
   }

   public IIcon func_150097_e() {
      return this.field_150102_N;
   }

   public final boolean canPaneConnectToBlock(Block var1) {
      return var1.func_149730_j() || var1 == this || var1 == Blocks.glass || var1 == Blocks.stained_glass || var1 == Blocks.stained_glass_pane || var1 instanceof BlockPane;
   }

   protected boolean canSilkHarvest() {
      return true;
   }

   protected ItemStack createStackedBlock(int var1) {
      return new ItemStack(Item.getItemFromBlock(this), 1, var1);
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.field_150101_M);
      this.field_150102_N = var1.registerIcon(this.field_150100_a);
   }
}
