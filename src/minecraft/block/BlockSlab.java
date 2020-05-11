package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockSlab extends Block {

   protected final boolean field_150004_a;


   public BlockSlab(boolean var1, Material var2) {
      super(var2);
      this.field_150004_a = var1;
      if(var1) {
         super.opaque = true;
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }

      this.setLightOpacity(255);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      if(this.field_150004_a) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         boolean var5 = (var1.getBlockMetadata(var2, var3, var4) & 8) != 0;
         if(var5) {
            this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
         } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
         }
      }

   }

   public void setBlockBoundsForItemRender() {
      if(this.field_150004_a) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }

   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
   }

   public boolean isOpaqueCube() {
      return this.field_150004_a;
   }

   public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9) {
      return this.field_150004_a?var9:(var5 != 0 && (var5 == 1 || (double)var7 <= 0.5D)?var9:var9 | 8);
   }

   public int quantityDropped(Random var1) {
      return this.field_150004_a?2:1;
   }

   public int damageDropped(int var1) {
      return var1 & 7;
   }

   public boolean renderAsNormalBlock() {
      return this.field_150004_a;
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      if(this.field_150004_a) {
         return super.shouldSideBeRendered(var1, var2, var3, var4, var5);
      } else if(var5 != 1 && var5 != 0 && !super.shouldSideBeRendered(var1, var2, var3, var4, var5)) {
         return false;
      } else {
         int var6 = var2 + Facing.offsetsXForSide[Facing.oppositeSide[var5]];
         int var7 = var3 + Facing.offsetsYForSide[Facing.oppositeSide[var5]];
         int var8 = var4 + Facing.offsetsZForSide[Facing.oppositeSide[var5]];
         boolean var9 = (var1.getBlockMetadata(var6, var7, var8) & 8) != 0;
         return var9?(var5 == 0?true:(var5 == 1 && super.shouldSideBeRendered(var1, var2, var3, var4, var5)?true:!func_150003_a(var1.getBlock(var2, var3, var4)) || (var1.getBlockMetadata(var2, var3, var4) & 8) == 0)):(var5 == 1?true:(var5 == 0 && super.shouldSideBeRendered(var1, var2, var3, var4, var5)?true:!func_150003_a(var1.getBlock(var2, var3, var4)) || (var1.getBlockMetadata(var2, var3, var4) & 8) != 0));
      }
   }

   private static boolean func_150003_a(Block var0) {
      return var0 == Blocks.stone_slab || var0 == Blocks.wooden_slab;
   }

   public abstract String func_150002_b(int var1);

   public int getDamageValue(World var1, int var2, int var3, int var4) {
      return super.getDamageValue(var1, var2, var3, var4) & 7;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return func_150003_a(this)?Item.getItemFromBlock(this):(this == Blocks.double_stone_slab?Item.getItemFromBlock(Blocks.stone_slab):(this == Blocks.double_wooden_slab?Item.getItemFromBlock(Blocks.wooden_slab):Item.getItemFromBlock(Blocks.stone_slab)));
   }
}
