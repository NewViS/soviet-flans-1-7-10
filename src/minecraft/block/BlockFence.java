package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemLead;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFence extends Block {

   private final String field_149827_a;


   public BlockFence(String var1, Material var2) {
      super(var2);
      this.field_149827_a = var1;
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
      boolean var8 = this.canConnectFenceTo(var1, var2, var3, var4 - 1);
      boolean var9 = this.canConnectFenceTo(var1, var2, var3, var4 + 1);
      boolean var10 = this.canConnectFenceTo(var1, var2 - 1, var3, var4);
      boolean var11 = this.canConnectFenceTo(var1, var2 + 1, var3, var4);
      float var12 = 0.375F;
      float var13 = 0.625F;
      float var14 = 0.375F;
      float var15 = 0.625F;
      if(var8) {
         var14 = 0.0F;
      }

      if(var9) {
         var15 = 1.0F;
      }

      if(var8 || var9) {
         this.setBlockBounds(var12, 0.0F, var14, var13, 1.5F, var15);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      }

      var14 = 0.375F;
      var15 = 0.625F;
      if(var10) {
         var12 = 0.0F;
      }

      if(var11) {
         var13 = 1.0F;
      }

      if(var10 || var11 || !var8 && !var9) {
         this.setBlockBounds(var12, 0.0F, var14, var13, 1.5F, var15);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      }

      if(var8) {
         var14 = 0.0F;
      }

      if(var9) {
         var15 = 1.0F;
      }

      this.setBlockBounds(var12, 0.0F, var14, var13, 1.0F, var15);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      boolean var5 = this.canConnectFenceTo(var1, var2, var3, var4 - 1);
      boolean var6 = this.canConnectFenceTo(var1, var2, var3, var4 + 1);
      boolean var7 = this.canConnectFenceTo(var1, var2 - 1, var3, var4);
      boolean var8 = this.canConnectFenceTo(var1, var2 + 1, var3, var4);
      float var9 = 0.375F;
      float var10 = 0.625F;
      float var11 = 0.375F;
      float var12 = 0.625F;
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

      this.setBlockBounds(var9, 0.0F, var11, var10, 1.0F, var12);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4) {
      return false;
   }

   public int getRenderType() {
      return 11;
   }

   public boolean canConnectFenceTo(IBlockAccess var1, int var2, int var3, int var4) {
      Block var5 = var1.getBlock(var2, var3, var4);
      return var5 != this && var5 != Blocks.fence_gate?(var5.blockMaterial.isOpaque() && var5.renderAsNormalBlock()?var5.blockMaterial != Material.gourd:false):true;
   }

   public static boolean func_149825_a(Block var0) {
      return var0 == Blocks.fence || var0 == Blocks.nether_brick_fence;
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return true;
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.field_149827_a);
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      return var1.isRemote?true:ItemLead.func_150909_a(var5, var1, var2, var3, var4);
   }
}
