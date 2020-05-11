package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockPortal$Size;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPortal extends BlockBreakable {

   public static final int[][] field_150001_a = new int[][]{new int[0], {3, 1}, {2, 0}};


   public BlockPortal() {
      super("portal", Material.portal, false);
      this.setTickRandomly(true);
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      super.updateTick(var1, var2, var3, var4, var5);
      if(var1.provider.isSurfaceWorld() && var1.getGameRules().getGameRuleBooleanValue("doMobSpawning") && var5.nextInt(2000) < var1.difficultySetting.getDifficultyId()) {
         int var6;
         for(var6 = var3; !World.doesBlockHaveSolidTopSurface(var1, var2, var6, var4) && var6 > 0; --var6) {
            ;
         }

         if(var6 > 0 && !var1.getBlock(var2, var6 + 1, var4).isNormalCube()) {
            Entity var7 = ItemMonsterPlacer.spawnCreature(var1, 57, (double)var2 + 0.5D, (double)var6 + 1.1D, (double)var4 + 0.5D);
            if(var7 != null) {
               var7.timeUntilPortal = var7.getPortalCooldown();
            }
         }
      }

   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return null;
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = func_149999_b(var1.getBlockMetadata(var2, var3, var4));
      if(var5 == 0) {
         if(var1.getBlock(var2 - 1, var3, var4) != this && var1.getBlock(var2 + 1, var3, var4) != this) {
            var5 = 2;
         } else {
            var5 = 1;
         }

         if(var1 instanceof World && !((World)var1).isRemote) {
            ((World)var1).setBlockMetadataWithNotify(var2, var3, var4, var5, 2);
         }
      }

      float var6 = 0.125F;
      float var7 = 0.125F;
      if(var5 == 1) {
         var6 = 0.5F;
      }

      if(var5 == 2) {
         var7 = 0.5F;
      }

      this.setBlockBounds(0.5F - var6, 0.0F, 0.5F - var7, 0.5F + var6, 1.0F, 0.5F + var7);
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean func_150000_e(World var1, int var2, int var3, int var4) {
      BlockPortal$Size var5 = new BlockPortal$Size(var1, var2, var3, var4, 1);
      BlockPortal$Size var6 = new BlockPortal$Size(var1, var2, var3, var4, 2);
      if(var5.func_150860_b() && BlockPortal$Size.access$000(var5) == 0) {
         var5.func_150859_c();
         return true;
      } else if(var6.func_150860_b() && BlockPortal$Size.access$000(var6) == 0) {
         var6.func_150859_c();
         return true;
      } else {
         return false;
      }
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      int var6 = func_149999_b(var1.getBlockMetadata(var2, var3, var4));
      BlockPortal$Size var7 = new BlockPortal$Size(var1, var2, var3, var4, 1);
      BlockPortal$Size var8 = new BlockPortal$Size(var1, var2, var3, var4, 2);
      if(var6 == 1 && (!var7.func_150860_b() || BlockPortal$Size.access$000(var7) < BlockPortal$Size.access$100(var7) * BlockPortal$Size.access$200(var7))) {
         var1.setBlock(var2, var3, var4, Blocks.air);
      } else if(var6 == 2 && (!var8.func_150860_b() || BlockPortal$Size.access$000(var8) < BlockPortal$Size.access$100(var8) * BlockPortal$Size.access$200(var8))) {
         var1.setBlock(var2, var3, var4, Blocks.air);
      } else if(var6 == 0 && !var7.func_150860_b() && !var8.func_150860_b()) {
         var1.setBlock(var2, var3, var4, Blocks.air);
      }

   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      int var6 = 0;
      if(var1.getBlock(var2, var3, var4) == this) {
         var6 = func_149999_b(var1.getBlockMetadata(var2, var3, var4));
         if(var6 == 0) {
            return false;
         }

         if(var6 == 2 && var5 != 5 && var5 != 4) {
            return false;
         }

         if(var6 == 1 && var5 != 3 && var5 != 2) {
            return false;
         }
      }

      boolean var7 = var1.getBlock(var2 - 1, var3, var4) == this && var1.getBlock(var2 - 2, var3, var4) != this;
      boolean var8 = var1.getBlock(var2 + 1, var3, var4) == this && var1.getBlock(var2 + 2, var3, var4) != this;
      boolean var9 = var1.getBlock(var2, var3, var4 - 1) == this && var1.getBlock(var2, var3, var4 - 2) != this;
      boolean var10 = var1.getBlock(var2, var3, var4 + 1) == this && var1.getBlock(var2, var3, var4 + 2) != this;
      boolean var11 = var7 || var8 || var6 == 1;
      boolean var12 = var9 || var10 || var6 == 2;
      return var11 && var5 == 4?true:(var11 && var5 == 5?true:(var12 && var5 == 2?true:var12 && var5 == 3));
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public int getRenderBlockPass() {
      return 1;
   }

   public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
      if(var5.ridingEntity == null && var5.riddenByEntity == null) {
         var5.setInPortal();
      }

   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      if(var5.nextInt(100) == 0) {
         var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "portal.portal", 0.5F, var5.nextFloat() * 0.4F + 0.8F, false);
      }

      for(int var6 = 0; var6 < 4; ++var6) {
         double var7 = (double)((float)var2 + var5.nextFloat());
         double var9 = (double)((float)var3 + var5.nextFloat());
         double var11 = (double)((float)var4 + var5.nextFloat());
         double var13 = 0.0D;
         double var15 = 0.0D;
         double var17 = 0.0D;
         int var19 = var5.nextInt(2) * 2 - 1;
         var13 = ((double)var5.nextFloat() - 0.5D) * 0.5D;
         var15 = ((double)var5.nextFloat() - 0.5D) * 0.5D;
         var17 = ((double)var5.nextFloat() - 0.5D) * 0.5D;
         if(var1.getBlock(var2 - 1, var3, var4) != this && var1.getBlock(var2 + 1, var3, var4) != this) {
            var7 = (double)var2 + 0.5D + 0.25D * (double)var19;
            var13 = (double)(var5.nextFloat() * 2.0F * (float)var19);
         } else {
            var11 = (double)var4 + 0.5D + 0.25D * (double)var19;
            var17 = (double)(var5.nextFloat() * 2.0F * (float)var19);
         }

         var1.spawnParticle("portal", var7, var9, var11, var13, var15, var17);
      }

   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Item.getItemById(0);
   }

   public static int func_149999_b(int var0) {
      return var0 & 3;
   }

}
