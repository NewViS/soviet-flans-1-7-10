package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockLiquid extends Block {

   private IIcon[] field_149806_a;


   protected BlockLiquid(Material var1) {
      super(var1);
      float var2 = 0.0F;
      float var3 = 0.0F;
      this.setBlockBounds(0.0F + var3, 0.0F + var2, 0.0F + var3, 1.0F + var3, 1.0F + var2, 1.0F + var3);
      this.setTickRandomly(true);
   }

   public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4) {
      return super.blockMaterial != Material.lava;
   }

   public int getBlockColor() {
      return 16777215;
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      if(super.blockMaterial != Material.water) {
         return 16777215;
      } else {
         int var5 = 0;
         int var6 = 0;
         int var7 = 0;

         for(int var8 = -1; var8 <= 1; ++var8) {
            for(int var9 = -1; var9 <= 1; ++var9) {
               int var10 = var1.getBiomeGenForCoords(var2 + var9, var4 + var8).waterColorMultiplier;
               var5 += (var10 & 16711680) >> 16;
               var6 += (var10 & '\uff00') >> 8;
               var7 += var10 & 255;
            }
         }

         return (var5 / 9 & 255) << 16 | (var6 / 9 & 255) << 8 | var7 / 9 & 255;
      }
   }

   public static float getLiquidHeightPercent(int var0) {
      if(var0 >= 8) {
         var0 = 0;
      }

      return (float)(var0 + 1) / 9.0F;
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 != 0 && var1 != 1?this.field_149806_a[1]:this.field_149806_a[0];
   }

   protected int func_149804_e(World var1, int var2, int var3, int var4) {
      return var1.getBlock(var2, var3, var4).getMaterial() == super.blockMaterial?var1.getBlockMetadata(var2, var3, var4):-1;
   }

   protected int getEffectiveFlowDecay(IBlockAccess var1, int var2, int var3, int var4) {
      if(var1.getBlock(var2, var3, var4).getMaterial() != super.blockMaterial) {
         return -1;
      } else {
         int var5 = var1.getBlockMetadata(var2, var3, var4);
         if(var5 >= 8) {
            var5 = 0;
         }

         return var5;
      }
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean canCollideCheck(int var1, boolean var2) {
      return var2 && var1 == 0;
   }

   public boolean isBlockSolid(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      Material var6 = var1.getBlock(var2, var3, var4).getMaterial();
      return var6 == super.blockMaterial?false:(var5 == 1?true:(var6 == Material.ice?false:super.isBlockSolid(var1, var2, var3, var4, var5)));
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      Material var6 = var1.getBlock(var2, var3, var4).getMaterial();
      return var6 == super.blockMaterial?false:(var5 == 1?true:super.shouldSideBeRendered(var1, var2, var3, var4, var5));
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return null;
   }

   public int getRenderType() {
      return 4;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return null;
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   private Vec3 getFlowVector(IBlockAccess var1, int var2, int var3, int var4) {
      Vec3 var5 = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
      int var6 = this.getEffectiveFlowDecay(var1, var2, var3, var4);

      for(int var7 = 0; var7 < 4; ++var7) {
         int var8 = var2;
         int var10 = var4;
         if(var7 == 0) {
            var8 = var2 - 1;
         }

         if(var7 == 1) {
            var10 = var4 - 1;
         }

         if(var7 == 2) {
            ++var8;
         }

         if(var7 == 3) {
            ++var10;
         }

         int var11 = this.getEffectiveFlowDecay(var1, var8, var3, var10);
         int var12;
         if(var11 < 0) {
            if(!var1.getBlock(var8, var3, var10).getMaterial().blocksMovement()) {
               var11 = this.getEffectiveFlowDecay(var1, var8, var3 - 1, var10);
               if(var11 >= 0) {
                  var12 = var11 - (var6 - 8);
                  var5 = var5.addVector((double)((var8 - var2) * var12), (double)((var3 - var3) * var12), (double)((var10 - var4) * var12));
               }
            }
         } else if(var11 >= 0) {
            var12 = var11 - var6;
            var5 = var5.addVector((double)((var8 - var2) * var12), (double)((var3 - var3) * var12), (double)((var10 - var4) * var12));
         }
      }

      if(var1.getBlockMetadata(var2, var3, var4) >= 8) {
         boolean var13 = false;
         if(var13 || this.isBlockSolid(var1, var2, var3, var4 - 1, 2)) {
            var13 = true;
         }

         if(var13 || this.isBlockSolid(var1, var2, var3, var4 + 1, 3)) {
            var13 = true;
         }

         if(var13 || this.isBlockSolid(var1, var2 - 1, var3, var4, 4)) {
            var13 = true;
         }

         if(var13 || this.isBlockSolid(var1, var2 + 1, var3, var4, 5)) {
            var13 = true;
         }

         if(var13 || this.isBlockSolid(var1, var2, var3 + 1, var4 - 1, 2)) {
            var13 = true;
         }

         if(var13 || this.isBlockSolid(var1, var2, var3 + 1, var4 + 1, 3)) {
            var13 = true;
         }

         if(var13 || this.isBlockSolid(var1, var2 - 1, var3 + 1, var4, 4)) {
            var13 = true;
         }

         if(var13 || this.isBlockSolid(var1, var2 + 1, var3 + 1, var4, 5)) {
            var13 = true;
         }

         if(var13) {
            var5 = var5.normalize().addVector(0.0D, -6.0D, 0.0D);
         }
      }

      var5 = var5.normalize();
      return var5;
   }

   public void velocityToAddToEntity(World var1, int var2, int var3, int var4, Entity var5, Vec3 var6) {
      Vec3 var7 = this.getFlowVector(var1, var2, var3, var4);
      var6.xCoord += var7.xCoord;
      var6.yCoord += var7.yCoord;
      var6.zCoord += var7.zCoord;
   }

   public int tickRate(World var1) {
      return super.blockMaterial == Material.water?5:(super.blockMaterial == Material.lava?(var1.provider.hasNoSky?10:30):0);
   }

   public int getMixedBrightnessForBlock(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getLightBrightnessForSkyBlocks(var2, var3, var4, 0);
      int var6 = var1.getLightBrightnessForSkyBlocks(var2, var3 + 1, var4, 0);
      int var7 = var5 & 255;
      int var8 = var6 & 255;
      int var9 = var5 >> 16 & 255;
      int var10 = var6 >> 16 & 255;
      return (var7 > var8?var7:var8) | (var9 > var10?var9:var10) << 16;
   }

   public int getRenderBlockPass() {
      return super.blockMaterial == Material.water?1:0;
   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      int var6;
      if(super.blockMaterial == Material.water) {
         if(var5.nextInt(10) == 0) {
            var6 = var1.getBlockMetadata(var2, var3, var4);
            if(var6 <= 0 || var6 >= 8) {
               var1.spawnParticle("suspended", (double)((float)var2 + var5.nextFloat()), (double)((float)var3 + var5.nextFloat()), (double)((float)var4 + var5.nextFloat()), 0.0D, 0.0D, 0.0D);
            }
         }

         for(var6 = 0; var6 < 0; ++var6) {
            int var7 = var5.nextInt(4);
            int var8 = var2;
            int var9 = var4;
            if(var7 == 0) {
               var8 = var2 - 1;
            }

            if(var7 == 1) {
               ++var8;
            }

            if(var7 == 2) {
               var9 = var4 - 1;
            }

            if(var7 == 3) {
               ++var9;
            }

            if(var1.getBlock(var8, var3, var9).getMaterial() == Material.air && (var1.getBlock(var8, var3 - 1, var9).getMaterial().blocksMovement() || var1.getBlock(var8, var3 - 1, var9).getMaterial().isLiquid())) {
               float var10 = 0.0625F;
               double var11 = (double)((float)var2 + var5.nextFloat());
               double var13 = (double)((float)var3 + var5.nextFloat());
               double var15 = (double)((float)var4 + var5.nextFloat());
               if(var7 == 0) {
                  var11 = (double)((float)var2 - var10);
               }

               if(var7 == 1) {
                  var11 = (double)((float)(var2 + 1) + var10);
               }

               if(var7 == 2) {
                  var15 = (double)((float)var4 - var10);
               }

               if(var7 == 3) {
                  var15 = (double)((float)(var4 + 1) + var10);
               }

               double var17 = 0.0D;
               double var19 = 0.0D;
               if(var7 == 0) {
                  var17 = (double)(-var10);
               }

               if(var7 == 1) {
                  var17 = (double)var10;
               }

               if(var7 == 2) {
                  var19 = (double)(-var10);
               }

               if(var7 == 3) {
                  var19 = (double)var10;
               }

               var1.spawnParticle("splash", var11, var13, var15, var17, 0.0D, var19);
            }
         }
      }

      if(super.blockMaterial == Material.water && var5.nextInt(64) == 0) {
         var6 = var1.getBlockMetadata(var2, var3, var4);
         if(var6 > 0 && var6 < 8) {
            var1.playSound((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), "liquid.water", var5.nextFloat() * 0.25F + 0.75F, var5.nextFloat() * 1.0F + 0.5F, false);
         }
      }

      double var21;
      double var22;
      double var23;
      if(super.blockMaterial == Material.lava && var1.getBlock(var2, var3 + 1, var4).getMaterial() == Material.air && !var1.getBlock(var2, var3 + 1, var4).isOpaqueCube()) {
         if(var5.nextInt(100) == 0) {
            var21 = (double)((float)var2 + var5.nextFloat());
            var22 = (double)var3 + super.maxY;
            var23 = (double)((float)var4 + var5.nextFloat());
            var1.spawnParticle("lava", var21, var22, var23, 0.0D, 0.0D, 0.0D);
            var1.playSound(var21, var22, var23, "liquid.lavapop", 0.2F + var5.nextFloat() * 0.2F, 0.9F + var5.nextFloat() * 0.15F, false);
         }

         if(var5.nextInt(200) == 0) {
            var1.playSound((double)var2, (double)var3, (double)var4, "liquid.lava", 0.2F + var5.nextFloat() * 0.2F, 0.9F + var5.nextFloat() * 0.15F, false);
         }
      }

      if(var5.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4) && !var1.getBlock(var2, var3 - 2, var4).getMaterial().blocksMovement()) {
         var21 = (double)((float)var2 + var5.nextFloat());
         var22 = (double)var3 - 1.05D;
         var23 = (double)((float)var4 + var5.nextFloat());
         if(super.blockMaterial == Material.water) {
            var1.spawnParticle("dripWater", var21, var22, var23, 0.0D, 0.0D, 0.0D);
         } else {
            var1.spawnParticle("dripLava", var21, var22, var23, 0.0D, 0.0D, 0.0D);
         }
      }

   }

   public static double getFlowDirection(IBlockAccess var0, int var1, int var2, int var3, Material var4) {
      Vec3 var5 = null;
      if(var4 == Material.water) {
         var5 = Blocks.flowing_water.getFlowVector(var0, var1, var2, var3);
      }

      if(var4 == Material.lava) {
         var5 = Blocks.flowing_lava.getFlowVector(var0, var1, var2, var3);
      }

      return var5.xCoord == 0.0D && var5.zCoord == 0.0D?-1000.0D:Math.atan2(var5.zCoord, var5.xCoord) - 1.5707963267948966D;
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      this.func_149805_n(var1, var2, var3, var4);
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      this.func_149805_n(var1, var2, var3, var4);
   }

   private void func_149805_n(World var1, int var2, int var3, int var4) {
      if(var1.getBlock(var2, var3, var4) == this) {
         if(super.blockMaterial == Material.lava) {
            boolean var5 = false;
            if(var5 || var1.getBlock(var2, var3, var4 - 1).getMaterial() == Material.water) {
               var5 = true;
            }

            if(var5 || var1.getBlock(var2, var3, var4 + 1).getMaterial() == Material.water) {
               var5 = true;
            }

            if(var5 || var1.getBlock(var2 - 1, var3, var4).getMaterial() == Material.water) {
               var5 = true;
            }

            if(var5 || var1.getBlock(var2 + 1, var3, var4).getMaterial() == Material.water) {
               var5 = true;
            }

            if(var5 || var1.getBlock(var2, var3 + 1, var4).getMaterial() == Material.water) {
               var5 = true;
            }

            if(var5) {
               int var6 = var1.getBlockMetadata(var2, var3, var4);
               if(var6 == 0) {
                  var1.setBlock(var2, var3, var4, Blocks.obsidian);
               } else if(var6 <= 4) {
                  var1.setBlock(var2, var3, var4, Blocks.cobblestone);
               }

               this.func_149799_m(var1, var2, var3, var4);
            }
         }

      }
   }

   protected void func_149799_m(World var1, int var2, int var3, int var4) {
      var1.playSoundEffect((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), "random.fizz", 0.5F, 2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);

      for(int var5 = 0; var5 < 8; ++var5) {
         var1.spawnParticle("largesmoke", (double)var2 + Math.random(), (double)var3 + 1.2D, (double)var4 + Math.random(), 0.0D, 0.0D, 0.0D);
      }

   }

   public void registerBlockIcons(IIconRegister var1) {
      if(super.blockMaterial == Material.lava) {
         this.field_149806_a = new IIcon[]{var1.registerIcon("lava_still"), var1.registerIcon("lava_flow")};
      } else {
         this.field_149806_a = new IIcon[]{var1.registerIcon("water_still"), var1.registerIcon("water_flow")};
      }

   }

   public static IIcon getLiquidIcon(String var0) {
      return var0 == "water_still"?Blocks.flowing_water.field_149806_a[0]:(var0 == "water_flow"?Blocks.flowing_water.field_149806_a[1]:(var0 == "lava_still"?Blocks.flowing_lava.field_149806_a[0]:(var0 == "lava_flow"?Blocks.flowing_lava.field_149806_a[1]:null)));
   }
}
