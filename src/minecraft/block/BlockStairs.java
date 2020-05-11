package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStairs extends Block {

   private static final int[][] field_150150_a = new int[][]{{2, 6}, {3, 7}, {2, 3}, {6, 7}, {0, 4}, {1, 5}, {0, 1}, {4, 5}};
   private final Block field_150149_b;
   private final int field_150151_M;
   private boolean field_150152_N;
   private int field_150153_O;


   protected BlockStairs(Block var1, int var2) {
      super(var1.blockMaterial);
      this.field_150149_b = var1;
      this.field_150151_M = var2;
      this.setHardness(var1.blockHardness);
      this.setResistance(var1.blockResistance / 3.0F);
      this.setStepSound(var1.stepSound);
      this.setLightOpacity(255);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      if(this.field_150152_N) {
         this.setBlockBounds(0.5F * (float)(this.field_150153_O % 2), 0.5F * (float)(this.field_150153_O / 2 % 2), 0.5F * (float)(this.field_150153_O / 4 % 2), 0.5F + 0.5F * (float)(this.field_150153_O % 2), 0.5F + 0.5F * (float)(this.field_150153_O / 2 % 2), 0.5F + 0.5F * (float)(this.field_150153_O / 4 % 2));
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 10;
   }

   public void func_150147_e(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      if((var5 & 4) != 0) {
         this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }

   }

   public static boolean func_150148_a(Block var0) {
      return var0 instanceof BlockStairs;
   }

   private boolean func_150146_f(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      Block var6 = var1.getBlock(var2, var3, var4);
      return func_150148_a(var6) && var1.getBlockMetadata(var2, var3, var4) == var5;
   }

   public boolean func_150145_f(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      int var6 = var5 & 3;
      float var7 = 0.5F;
      float var8 = 1.0F;
      if((var5 & 4) != 0) {
         var7 = 0.0F;
         var8 = 0.5F;
      }

      float var9 = 0.0F;
      float var10 = 1.0F;
      float var11 = 0.0F;
      float var12 = 0.5F;
      boolean var13 = true;
      Block var14;
      int var15;
      int var16;
      if(var6 == 0) {
         var9 = 0.5F;
         var12 = 1.0F;
         var14 = var1.getBlock(var2 + 1, var3, var4);
         var15 = var1.getBlockMetadata(var2 + 1, var3, var4);
         if(func_150148_a(var14) && (var5 & 4) == (var15 & 4)) {
            var16 = var15 & 3;
            if(var16 == 3 && !this.func_150146_f(var1, var2, var3, var4 + 1, var5)) {
               var12 = 0.5F;
               var13 = false;
            } else if(var16 == 2 && !this.func_150146_f(var1, var2, var3, var4 - 1, var5)) {
               var11 = 0.5F;
               var13 = false;
            }
         }
      } else if(var6 == 1) {
         var10 = 0.5F;
         var12 = 1.0F;
         var14 = var1.getBlock(var2 - 1, var3, var4);
         var15 = var1.getBlockMetadata(var2 - 1, var3, var4);
         if(func_150148_a(var14) && (var5 & 4) == (var15 & 4)) {
            var16 = var15 & 3;
            if(var16 == 3 && !this.func_150146_f(var1, var2, var3, var4 + 1, var5)) {
               var12 = 0.5F;
               var13 = false;
            } else if(var16 == 2 && !this.func_150146_f(var1, var2, var3, var4 - 1, var5)) {
               var11 = 0.5F;
               var13 = false;
            }
         }
      } else if(var6 == 2) {
         var11 = 0.5F;
         var12 = 1.0F;
         var14 = var1.getBlock(var2, var3, var4 + 1);
         var15 = var1.getBlockMetadata(var2, var3, var4 + 1);
         if(func_150148_a(var14) && (var5 & 4) == (var15 & 4)) {
            var16 = var15 & 3;
            if(var16 == 1 && !this.func_150146_f(var1, var2 + 1, var3, var4, var5)) {
               var10 = 0.5F;
               var13 = false;
            } else if(var16 == 0 && !this.func_150146_f(var1, var2 - 1, var3, var4, var5)) {
               var9 = 0.5F;
               var13 = false;
            }
         }
      } else if(var6 == 3) {
         var14 = var1.getBlock(var2, var3, var4 - 1);
         var15 = var1.getBlockMetadata(var2, var3, var4 - 1);
         if(func_150148_a(var14) && (var5 & 4) == (var15 & 4)) {
            var16 = var15 & 3;
            if(var16 == 1 && !this.func_150146_f(var1, var2 + 1, var3, var4, var5)) {
               var10 = 0.5F;
               var13 = false;
            } else if(var16 == 0 && !this.func_150146_f(var1, var2 - 1, var3, var4, var5)) {
               var9 = 0.5F;
               var13 = false;
            }
         }
      }

      this.setBlockBounds(var9, var7, var11, var10, var8, var12);
      return var13;
   }

   public boolean func_150144_g(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      int var6 = var5 & 3;
      float var7 = 0.5F;
      float var8 = 1.0F;
      if((var5 & 4) != 0) {
         var7 = 0.0F;
         var8 = 0.5F;
      }

      float var9 = 0.0F;
      float var10 = 0.5F;
      float var11 = 0.5F;
      float var12 = 1.0F;
      boolean var13 = false;
      Block var14;
      int var15;
      int var16;
      if(var6 == 0) {
         var14 = var1.getBlock(var2 - 1, var3, var4);
         var15 = var1.getBlockMetadata(var2 - 1, var3, var4);
         if(func_150148_a(var14) && (var5 & 4) == (var15 & 4)) {
            var16 = var15 & 3;
            if(var16 == 3 && !this.func_150146_f(var1, var2, var3, var4 - 1, var5)) {
               var11 = 0.0F;
               var12 = 0.5F;
               var13 = true;
            } else if(var16 == 2 && !this.func_150146_f(var1, var2, var3, var4 + 1, var5)) {
               var11 = 0.5F;
               var12 = 1.0F;
               var13 = true;
            }
         }
      } else if(var6 == 1) {
         var14 = var1.getBlock(var2 + 1, var3, var4);
         var15 = var1.getBlockMetadata(var2 + 1, var3, var4);
         if(func_150148_a(var14) && (var5 & 4) == (var15 & 4)) {
            var9 = 0.5F;
            var10 = 1.0F;
            var16 = var15 & 3;
            if(var16 == 3 && !this.func_150146_f(var1, var2, var3, var4 - 1, var5)) {
               var11 = 0.0F;
               var12 = 0.5F;
               var13 = true;
            } else if(var16 == 2 && !this.func_150146_f(var1, var2, var3, var4 + 1, var5)) {
               var11 = 0.5F;
               var12 = 1.0F;
               var13 = true;
            }
         }
      } else if(var6 == 2) {
         var14 = var1.getBlock(var2, var3, var4 - 1);
         var15 = var1.getBlockMetadata(var2, var3, var4 - 1);
         if(func_150148_a(var14) && (var5 & 4) == (var15 & 4)) {
            var11 = 0.0F;
            var12 = 0.5F;
            var16 = var15 & 3;
            if(var16 == 1 && !this.func_150146_f(var1, var2 - 1, var3, var4, var5)) {
               var13 = true;
            } else if(var16 == 0 && !this.func_150146_f(var1, var2 + 1, var3, var4, var5)) {
               var9 = 0.5F;
               var10 = 1.0F;
               var13 = true;
            }
         }
      } else if(var6 == 3) {
         var14 = var1.getBlock(var2, var3, var4 + 1);
         var15 = var1.getBlockMetadata(var2, var3, var4 + 1);
         if(func_150148_a(var14) && (var5 & 4) == (var15 & 4)) {
            var16 = var15 & 3;
            if(var16 == 1 && !this.func_150146_f(var1, var2 - 1, var3, var4, var5)) {
               var13 = true;
            } else if(var16 == 0 && !this.func_150146_f(var1, var2 + 1, var3, var4, var5)) {
               var9 = 0.5F;
               var10 = 1.0F;
               var13 = true;
            }
         }
      }

      if(var13) {
         this.setBlockBounds(var9, var7, var11, var10, var8, var12);
      }

      return var13;
   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
      this.func_150147_e(var1, var2, var3, var4);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      boolean var8 = this.func_150145_f(var1, var2, var3, var4);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      if(var8 && this.func_150144_g(var1, var2, var3, var4)) {
         super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      }

      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      this.field_150149_b.randomDisplayTick(var1, var2, var3, var4, var5);
   }

   public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {
      this.field_150149_b.onBlockClicked(var1, var2, var3, var4, var5);
   }

   public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5) {
      this.field_150149_b.onBlockDestroyedByPlayer(var1, var2, var3, var4, var5);
   }

   public int getMixedBrightnessForBlock(IBlockAccess var1, int var2, int var3, int var4) {
      return this.field_150149_b.getMixedBrightnessForBlock(var1, var2, var3, var4);
   }

   public float getExplosionResistance(Entity var1) {
      return this.field_150149_b.getExplosionResistance(var1);
   }

   public int getRenderBlockPass() {
      return this.field_150149_b.getRenderBlockPass();
   }

   public IIcon getIcon(int var1, int var2) {
      return this.field_150149_b.getIcon(var1, this.field_150151_M);
   }

   public int tickRate(World var1) {
      return this.field_150149_b.tickRate(var1);
   }

   public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return this.field_150149_b.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
   }

   public void velocityToAddToEntity(World var1, int var2, int var3, int var4, Entity var5, Vec3 var6) {
      this.field_150149_b.velocityToAddToEntity(var1, var2, var3, var4, var5, var6);
   }

   public boolean isCollidable() {
      return this.field_150149_b.isCollidable();
   }

   public boolean canCollideCheck(int var1, boolean var2) {
      return this.field_150149_b.canCollideCheck(var1, var2);
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return this.field_150149_b.canPlaceBlockAt(var1, var2, var3, var4);
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      this.onNeighborBlockChange(var1, var2, var3, var4, Blocks.air);
      this.field_150149_b.onBlockAdded(var1, var2, var3, var4);
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      this.field_150149_b.breakBlock(var1, var2, var3, var4, var5, var6);
   }

   public void onEntityWalking(World var1, int var2, int var3, int var4, Entity var5) {
      this.field_150149_b.onEntityWalking(var1, var2, var3, var4, var5);
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      this.field_150149_b.updateTick(var1, var2, var3, var4, var5);
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      return this.field_150149_b.onBlockActivated(var1, var2, var3, var4, var5, 0, 0.0F, 0.0F, 0.0F);
   }

   public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5) {
      this.field_150149_b.onBlockDestroyedByExplosion(var1, var2, var3, var4, var5);
   }

   public MapColor getMapColor(int var1) {
      return this.field_150149_b.getMapColor(this.field_150151_M);
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      int var7 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      int var8 = var1.getBlockMetadata(var2, var3, var4) & 4;
      if(var7 == 0) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 2 | var8, 2);
      }

      if(var7 == 1) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 1 | var8, 2);
      }

      if(var7 == 2) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 3 | var8, 2);
      }

      if(var7 == 3) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 0 | var8, 2);
      }

   }

   public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9) {
      return var5 != 0 && (var5 == 1 || (double)var7 <= 0.5D)?var9:var9 | 4;
   }

   public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6) {
      MovingObjectPosition[] var7 = new MovingObjectPosition[8];
      int var8 = var1.getBlockMetadata(var2, var3, var4);
      int var9 = var8 & 3;
      boolean var10 = (var8 & 4) == 4;
      int[] var11 = field_150150_a[var9 + (var10?4:0)];
      this.field_150152_N = true;

      int var14;
      int var15;
      int var16;
      for(int var12 = 0; var12 < 8; ++var12) {
         this.field_150153_O = var12;
         int[] var13 = var11;
         var14 = var11.length;

         for(var15 = 0; var15 < var14; ++var15) {
            var16 = var13[var15];
            if(var16 == var12) {
               ;
            }
         }

         var7[var12] = super.collisionRayTrace(var1, var2, var3, var4, var5, var6);
      }

      int[] var21 = var11;
      int var23 = var11.length;

      for(var14 = 0; var14 < var23; ++var14) {
         var15 = var21[var14];
         var7[var15] = null;
      }

      MovingObjectPosition var22 = null;
      double var24 = 0.0D;
      MovingObjectPosition[] var25 = var7;
      var16 = var7.length;

      for(int var17 = 0; var17 < var16; ++var17) {
         MovingObjectPosition var18 = var25[var17];
         if(var18 != null) {
            double var19 = var18.hitVec.squareDistanceTo(var6);
            if(var19 > var24) {
               var22 = var18;
               var24 = var19;
            }
         }
      }

      return var22;
   }

   public void registerBlockIcons(IIconRegister var1) {}

}
