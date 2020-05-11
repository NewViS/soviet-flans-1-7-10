package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonExtension extends Block {

   private IIcon field_150088_a;


   public BlockPistonExtension() {
      super(Material.piston);
      this.setStepSound(Block.soundTypePiston);
      this.setHardness(0.5F);
   }

   public void func_150086_a(IIcon var1) {
      this.field_150088_a = var1;
   }

   public void func_150087_e() {
      this.field_150088_a = null;
   }

   public void onBlockHarvested(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6) {
      if(var6.capabilities.isCreativeMode) {
         int var7 = getDirectionMeta(var5);
         Block var8 = var1.getBlock(var2 - Facing.offsetsXForSide[var7], var3 - Facing.offsetsYForSide[var7], var4 - Facing.offsetsZForSide[var7]);
         if(var8 == Blocks.piston || var8 == Blocks.sticky_piston) {
            var1.setBlockToAir(var2 - Facing.offsetsXForSide[var7], var3 - Facing.offsetsYForSide[var7], var4 - Facing.offsetsZForSide[var7]);
         }
      }

      super.onBlockHarvested(var1, var2, var3, var4, var5, var6);
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      super.breakBlock(var1, var2, var3, var4, var5, var6);
      int var7 = Facing.oppositeSide[getDirectionMeta(var6)];
      var2 += Facing.offsetsXForSide[var7];
      var3 += Facing.offsetsYForSide[var7];
      var4 += Facing.offsetsZForSide[var7];
      Block var8 = var1.getBlock(var2, var3, var4);
      if(var8 == Blocks.piston || var8 == Blocks.sticky_piston) {
         var6 = var1.getBlockMetadata(var2, var3, var4);
         if(BlockPistonBase.isExtended(var6)) {
            var8.dropBlockAsItem(var1, var2, var3, var4, var6, 0);
            var1.setBlockToAir(var2, var3, var4);
         }
      }

   }

   public IIcon getIcon(int var1, int var2) {
      int var3 = getDirectionMeta(var2);
      return var1 == var3?(this.field_150088_a != null?this.field_150088_a:((var2 & 8) != 0?BlockPistonBase.getPistonBaseIcon("piston_top_sticky"):BlockPistonBase.getPistonBaseIcon("piston_top_normal"))):(var3 < 6 && var1 == Facing.oppositeSide[var3]?BlockPistonBase.getPistonBaseIcon("piston_top_normal"):BlockPistonBase.getPistonBaseIcon("piston_side"));
   }

   public void registerBlockIcons(IIconRegister var1) {}

   public int getRenderType() {
      return 17;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return false;
   }

   public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5) {
      return false;
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
      int var8 = var1.getBlockMetadata(var2, var3, var4);
      float var9 = 0.25F;
      float var10 = 0.375F;
      float var11 = 0.625F;
      float var12 = 0.25F;
      float var13 = 0.75F;
      switch(getDirectionMeta(var8)) {
      case 0:
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         this.setBlockBounds(0.375F, 0.25F, 0.375F, 0.625F, 1.0F, 0.625F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         break;
      case 1:
         this.setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         break;
      case 2:
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         this.setBlockBounds(0.25F, 0.375F, 0.25F, 0.75F, 0.625F, 1.0F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         break;
      case 3:
         this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         this.setBlockBounds(0.25F, 0.375F, 0.0F, 0.75F, 0.625F, 0.75F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         break;
      case 4:
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         this.setBlockBounds(0.375F, 0.25F, 0.25F, 0.625F, 0.75F, 1.0F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         break;
      case 5:
         this.setBlockBounds(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
         this.setBlockBounds(0.0F, 0.375F, 0.25F, 0.75F, 0.625F, 0.75F);
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      }

      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      float var6 = 0.25F;
      switch(getDirectionMeta(var5)) {
      case 0:
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
         break;
      case 1:
         this.setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
         break;
      case 2:
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
         break;
      case 3:
         this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
         break;
      case 4:
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
         break;
      case 5:
         this.setBlockBounds(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      int var6 = getDirectionMeta(var1.getBlockMetadata(var2, var3, var4));
      Block var7 = var1.getBlock(var2 - Facing.offsetsXForSide[var6], var3 - Facing.offsetsYForSide[var6], var4 - Facing.offsetsZForSide[var6]);
      if(var7 != Blocks.piston && var7 != Blocks.sticky_piston) {
         var1.setBlockToAir(var2, var3, var4);
      } else {
         var7.onNeighborBlockChange(var1, var2 - Facing.offsetsXForSide[var6], var3 - Facing.offsetsYForSide[var6], var4 - Facing.offsetsZForSide[var6], var5);
      }

   }

   public static int getDirectionMeta(int var0) {
      return MathHelper.clamp_int(var0 & 7, 0, Facing.offsetsXForSide.length - 1);
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      return (var5 & 8) != 0?Item.getItemFromBlock(Blocks.sticky_piston):Item.getItemFromBlock(Blocks.piston);
   }
}
