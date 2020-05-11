package net.minecraft.block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneWire extends Block {

   private boolean field_150181_a = true;
   private Set field_150179_b = new HashSet();
   private IIcon field_150182_M;
   private IIcon field_150183_N;
   private IIcon field_150184_O;
   private IIcon field_150180_P;


   public BlockRedstoneWire() {
      super(Material.circuits);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return null;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 5;
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      return 8388608;
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4) || var1.getBlock(var2, var3 - 1, var4) == Blocks.glowstone;
   }

   private void func_150177_e(World var1, int var2, int var3, int var4) {
      this.func_150175_a(var1, var2, var3, var4, var2, var3, var4);
      ArrayList var5 = new ArrayList(this.field_150179_b);
      this.field_150179_b.clear();

      for(int var6 = 0; var6 < var5.size(); ++var6) {
         ChunkPosition var7 = (ChunkPosition)var5.get(var6);
         var1.notifyBlocksOfNeighborChange(var7.chunkPosX, var7.chunkPosY, var7.chunkPosZ, this);
      }

   }

   private void func_150175_a(World var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      int var8 = var1.getBlockMetadata(var2, var3, var4);
      byte var9 = 0;
      int var15 = this.func_150178_a(var1, var5, var6, var7, var9);
      this.field_150181_a = false;
      int var10 = var1.getStrongestIndirectPower(var2, var3, var4);
      this.field_150181_a = true;
      if(var10 > 0 && var10 > var15 - 1) {
         var15 = var10;
      }

      int var11 = 0;

      for(int var12 = 0; var12 < 4; ++var12) {
         int var13 = var2;
         int var14 = var4;
         if(var12 == 0) {
            var13 = var2 - 1;
         }

         if(var12 == 1) {
            ++var13;
         }

         if(var12 == 2) {
            var14 = var4 - 1;
         }

         if(var12 == 3) {
            ++var14;
         }

         if(var13 != var5 || var14 != var7) {
            var11 = this.func_150178_a(var1, var13, var3, var14, var11);
         }

         if(var1.getBlock(var13, var3, var14).isNormalCube() && !var1.getBlock(var2, var3 + 1, var4).isNormalCube()) {
            if((var13 != var5 || var14 != var7) && var3 >= var6) {
               var11 = this.func_150178_a(var1, var13, var3 + 1, var14, var11);
            }
         } else if(!var1.getBlock(var13, var3, var14).isNormalCube() && (var13 != var5 || var14 != var7) && var3 <= var6) {
            var11 = this.func_150178_a(var1, var13, var3 - 1, var14, var11);
         }
      }

      if(var11 > var15) {
         var15 = var11 - 1;
      } else if(var15 > 0) {
         --var15;
      } else {
         var15 = 0;
      }

      if(var10 > var15 - 1) {
         var15 = var10;
      }

      if(var8 != var15) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var15, 2);
         this.field_150179_b.add(new ChunkPosition(var2, var3, var4));
         this.field_150179_b.add(new ChunkPosition(var2 - 1, var3, var4));
         this.field_150179_b.add(new ChunkPosition(var2 + 1, var3, var4));
         this.field_150179_b.add(new ChunkPosition(var2, var3 - 1, var4));
         this.field_150179_b.add(new ChunkPosition(var2, var3 + 1, var4));
         this.field_150179_b.add(new ChunkPosition(var2, var3, var4 - 1));
         this.field_150179_b.add(new ChunkPosition(var2, var3, var4 + 1));
      }

   }

   private void func_150172_m(World var1, int var2, int var3, int var4) {
      if(var1.getBlock(var2, var3, var4) == this) {
         var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
         var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
      }
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      super.onBlockAdded(var1, var2, var3, var4);
      if(!var1.isRemote) {
         this.func_150177_e(var1, var2, var3, var4);
         var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
         this.func_150172_m(var1, var2 - 1, var3, var4);
         this.func_150172_m(var1, var2 + 1, var3, var4);
         this.func_150172_m(var1, var2, var3, var4 - 1);
         this.func_150172_m(var1, var2, var3, var4 + 1);
         if(var1.getBlock(var2 - 1, var3, var4).isNormalCube()) {
            this.func_150172_m(var1, var2 - 1, var3 + 1, var4);
         } else {
            this.func_150172_m(var1, var2 - 1, var3 - 1, var4);
         }

         if(var1.getBlock(var2 + 1, var3, var4).isNormalCube()) {
            this.func_150172_m(var1, var2 + 1, var3 + 1, var4);
         } else {
            this.func_150172_m(var1, var2 + 1, var3 - 1, var4);
         }

         if(var1.getBlock(var2, var3, var4 - 1).isNormalCube()) {
            this.func_150172_m(var1, var2, var3 + 1, var4 - 1);
         } else {
            this.func_150172_m(var1, var2, var3 - 1, var4 - 1);
         }

         if(var1.getBlock(var2, var3, var4 + 1).isNormalCube()) {
            this.func_150172_m(var1, var2, var3 + 1, var4 + 1);
         } else {
            this.func_150172_m(var1, var2, var3 - 1, var4 + 1);
         }

      }
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      super.breakBlock(var1, var2, var3, var4, var5, var6);
      if(!var1.isRemote) {
         var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
         var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
         this.func_150177_e(var1, var2, var3, var4);
         this.func_150172_m(var1, var2 - 1, var3, var4);
         this.func_150172_m(var1, var2 + 1, var3, var4);
         this.func_150172_m(var1, var2, var3, var4 - 1);
         this.func_150172_m(var1, var2, var3, var4 + 1);
         if(var1.getBlock(var2 - 1, var3, var4).isNormalCube()) {
            this.func_150172_m(var1, var2 - 1, var3 + 1, var4);
         } else {
            this.func_150172_m(var1, var2 - 1, var3 - 1, var4);
         }

         if(var1.getBlock(var2 + 1, var3, var4).isNormalCube()) {
            this.func_150172_m(var1, var2 + 1, var3 + 1, var4);
         } else {
            this.func_150172_m(var1, var2 + 1, var3 - 1, var4);
         }

         if(var1.getBlock(var2, var3, var4 - 1).isNormalCube()) {
            this.func_150172_m(var1, var2, var3 + 1, var4 - 1);
         } else {
            this.func_150172_m(var1, var2, var3 - 1, var4 - 1);
         }

         if(var1.getBlock(var2, var3, var4 + 1).isNormalCube()) {
            this.func_150172_m(var1, var2, var3 + 1, var4 + 1);
         } else {
            this.func_150172_m(var1, var2, var3 - 1, var4 + 1);
         }

      }
   }

   private int func_150178_a(World var1, int var2, int var3, int var4, int var5) {
      if(var1.getBlock(var2, var3, var4) != this) {
         return var5;
      } else {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         return var6 > var5?var6:var5;
      }
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!var1.isRemote) {
         boolean var6 = this.canPlaceBlockAt(var1, var2, var3, var4);
         if(var6) {
            this.func_150177_e(var1, var2, var3, var4);
         } else {
            this.dropBlockAsItem(var1, var2, var3, var4, 0, 0);
            var1.setBlockToAir(var2, var3, var4);
         }

         super.onNeighborBlockChange(var1, var2, var3, var4, var5);
      }
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.redstone;
   }

   public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return !this.field_150181_a?0:this.isProvidingWeakPower(var1, var2, var3, var4, var5);
   }

   public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      if(!this.field_150181_a) {
         return 0;
      } else {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         if(var6 == 0) {
            return 0;
         } else if(var5 == 1) {
            return var6;
         } else {
            boolean var7 = func_150176_g(var1, var2 - 1, var3, var4, 1) || !var1.getBlock(var2 - 1, var3, var4).isNormalCube() && func_150176_g(var1, var2 - 1, var3 - 1, var4, -1);
            boolean var8 = func_150176_g(var1, var2 + 1, var3, var4, 3) || !var1.getBlock(var2 + 1, var3, var4).isNormalCube() && func_150176_g(var1, var2 + 1, var3 - 1, var4, -1);
            boolean var9 = func_150176_g(var1, var2, var3, var4 - 1, 2) || !var1.getBlock(var2, var3, var4 - 1).isNormalCube() && func_150176_g(var1, var2, var3 - 1, var4 - 1, -1);
            boolean var10 = func_150176_g(var1, var2, var3, var4 + 1, 0) || !var1.getBlock(var2, var3, var4 + 1).isNormalCube() && func_150176_g(var1, var2, var3 - 1, var4 + 1, -1);
            if(!var1.getBlock(var2, var3 + 1, var4).isNormalCube()) {
               if(var1.getBlock(var2 - 1, var3, var4).isNormalCube() && func_150176_g(var1, var2 - 1, var3 + 1, var4, -1)) {
                  var7 = true;
               }

               if(var1.getBlock(var2 + 1, var3, var4).isNormalCube() && func_150176_g(var1, var2 + 1, var3 + 1, var4, -1)) {
                  var8 = true;
               }

               if(var1.getBlock(var2, var3, var4 - 1).isNormalCube() && func_150176_g(var1, var2, var3 + 1, var4 - 1, -1)) {
                  var9 = true;
               }

               if(var1.getBlock(var2, var3, var4 + 1).isNormalCube() && func_150176_g(var1, var2, var3 + 1, var4 + 1, -1)) {
                  var10 = true;
               }
            }

            return !var9 && !var8 && !var7 && !var10 && var5 >= 2 && var5 <= 5?var6:(var5 == 2 && var9 && !var7 && !var8?var6:(var5 == 3 && var10 && !var7 && !var8?var6:(var5 == 4 && var7 && !var9 && !var10?var6:(var5 == 5 && var8 && !var9 && !var10?var6:0))));
         }
      }
   }

   public boolean canProvidePower() {
      return this.field_150181_a;
   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      if(var6 > 0) {
         double var7 = (double)var2 + 0.5D + ((double)var5.nextFloat() - 0.5D) * 0.2D;
         double var9 = (double)((float)var3 + 0.0625F);
         double var11 = (double)var4 + 0.5D + ((double)var5.nextFloat() - 0.5D) * 0.2D;
         float var13 = (float)var6 / 15.0F;
         float var14 = var13 * 0.6F + 0.4F;
         if(var6 == 0) {
            var14 = 0.0F;
         }

         float var15 = var13 * var13 * 0.7F - 0.5F;
         float var16 = var13 * var13 * 0.6F - 0.7F;
         if(var15 < 0.0F) {
            var15 = 0.0F;
         }

         if(var16 < 0.0F) {
            var16 = 0.0F;
         }

         var1.spawnParticle("reddust", var7, var9, var11, (double)var14, (double)var15, (double)var16);
      }

   }

   public static boolean isPowerProviderOrWire(IBlockAccess var0, int var1, int var2, int var3, int var4) {
      Block var5 = var0.getBlock(var1, var2, var3);
      if(var5 == Blocks.redstone_wire) {
         return true;
      } else if(!Blocks.unpowered_repeater.func_149907_e(var5)) {
         return var5.canProvidePower() && var4 != -1;
      } else {
         int var6 = var0.getBlockMetadata(var1, var2, var3);
         return var4 == (var6 & 3) || var4 == Direction.rotateOpposite[var6 & 3];
      }
   }

   public static boolean func_150176_g(IBlockAccess var0, int var1, int var2, int var3, int var4) {
      if(isPowerProviderOrWire(var0, var1, var2, var3, var4)) {
         return true;
      } else if(var0.getBlock(var1, var2, var3) == Blocks.powered_repeater) {
         int var5 = var0.getBlockMetadata(var1, var2, var3);
         return var4 == (var5 & 3);
      } else {
         return false;
      }
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Items.redstone;
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_150182_M = var1.registerIcon(this.getTextureName() + "_" + "cross");
      this.field_150183_N = var1.registerIcon(this.getTextureName() + "_" + "line");
      this.field_150184_O = var1.registerIcon(this.getTextureName() + "_" + "cross_overlay");
      this.field_150180_P = var1.registerIcon(this.getTextureName() + "_" + "line_overlay");
      super.blockIcon = this.field_150182_M;
   }

   public static IIcon getRedstoneWireIcon(String var0) {
      return var0.equals("cross")?Blocks.redstone_wire.field_150182_M:(var0.equals("line")?Blocks.redstone_wire.field_150183_N:(var0.equals("cross_overlay")?Blocks.redstone_wire.field_150184_O:(var0.equals("line_overlay")?Blocks.redstone_wire.field_150180_P:null)));
   }
}
