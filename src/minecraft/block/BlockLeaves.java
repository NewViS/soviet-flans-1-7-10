package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockLeaves extends BlockLeavesBase {

   int[] field_150128_a;
   protected int field_150127_b;
   protected IIcon[][] field_150129_M = new IIcon[2][];


   public BlockLeaves() {
      super(Material.leaves, false);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.setHardness(0.2F);
      this.setLightOpacity(1);
      this.setStepSound(Block.soundTypeGrass);
   }

   public int getBlockColor() {
      double var1 = 0.5D;
      double var3 = 1.0D;
      return ColorizerFoliage.getFoliageColor(var1, var3);
   }

   public int getRenderColor(int var1) {
      return ColorizerFoliage.getFoliageColorBasic();
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      for(int var8 = -1; var8 <= 1; ++var8) {
         for(int var9 = -1; var9 <= 1; ++var9) {
            int var10 = var1.getBiomeGenForCoords(var2 + var9, var4 + var8).getBiomeFoliageColor(var2 + var9, var3, var4 + var8);
            var5 += (var10 & 16711680) >> 16;
            var6 += (var10 & '\uff00') >> 8;
            var7 += var10 & 255;
         }
      }

      return (var5 / 9 & 255) << 16 | (var6 / 9 & 255) << 8 | var7 / 9 & 255;
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      byte var7 = 1;
      int var8 = var7 + 1;
      if(var1.checkChunksExist(var2 - var8, var3 - var8, var4 - var8, var2 + var8, var3 + var8, var4 + var8)) {
         for(int var9 = -var7; var9 <= var7; ++var9) {
            for(int var10 = -var7; var10 <= var7; ++var10) {
               for(int var11 = -var7; var11 <= var7; ++var11) {
                  if(var1.getBlock(var2 + var9, var3 + var10, var4 + var11).getMaterial() == Material.leaves) {
                     int var12 = var1.getBlockMetadata(var2 + var9, var3 + var10, var4 + var11);
                     var1.setBlockMetadataWithNotify(var2 + var9, var3 + var10, var4 + var11, var12 | 8, 4);
                  }
               }
            }
         }
      }

   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!var1.isRemote) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         if((var6 & 8) != 0 && (var6 & 4) == 0) {
            byte var7 = 4;
            int var8 = var7 + 1;
            byte var9 = 32;
            int var10 = var9 * var9;
            int var11 = var9 / 2;
            if(this.field_150128_a == null) {
               this.field_150128_a = new int[var9 * var9 * var9];
            }

            int var12;
            if(var1.checkChunksExist(var2 - var8, var3 - var8, var4 - var8, var2 + var8, var3 + var8, var4 + var8)) {
               int var13;
               int var14;
               for(var12 = -var7; var12 <= var7; ++var12) {
                  for(var13 = -var7; var13 <= var7; ++var13) {
                     for(var14 = -var7; var14 <= var7; ++var14) {
                        Block var15 = var1.getBlock(var2 + var12, var3 + var13, var4 + var14);
                        if(var15 != Blocks.log && var15 != Blocks.log2) {
                           if(var15.getMaterial() == Material.leaves) {
                              this.field_150128_a[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -2;
                           } else {
                              this.field_150128_a[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -1;
                           }
                        } else {
                           this.field_150128_a[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
                        }
                     }
                  }
               }

               for(var12 = 1; var12 <= 4; ++var12) {
                  for(var13 = -var7; var13 <= var7; ++var13) {
                     for(var14 = -var7; var14 <= var7; ++var14) {
                        for(int var16 = -var7; var16 <= var7; ++var16) {
                           if(this.field_150128_a[(var13 + var11) * var10 + (var14 + var11) * var9 + var16 + var11] == var12 - 1) {
                              if(this.field_150128_a[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var16 + var11] == -2) {
                                 this.field_150128_a[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var16 + var11] = var12;
                              }

                              if(this.field_150128_a[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var16 + var11] == -2) {
                                 this.field_150128_a[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var16 + var11] = var12;
                              }

                              if(this.field_150128_a[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var16 + var11] == -2) {
                                 this.field_150128_a[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var16 + var11] = var12;
                              }

                              if(this.field_150128_a[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var16 + var11] == -2) {
                                 this.field_150128_a[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var16 + var11] = var12;
                              }

                              if(this.field_150128_a[(var13 + var11) * var10 + (var14 + var11) * var9 + (var16 + var11 - 1)] == -2) {
                                 this.field_150128_a[(var13 + var11) * var10 + (var14 + var11) * var9 + (var16 + var11 - 1)] = var12;
                              }

                              if(this.field_150128_a[(var13 + var11) * var10 + (var14 + var11) * var9 + var16 + var11 + 1] == -2) {
                                 this.field_150128_a[(var13 + var11) * var10 + (var14 + var11) * var9 + var16 + var11 + 1] = var12;
                              }
                           }
                        }
                     }
                  }
               }
            }

            var12 = this.field_150128_a[var11 * var10 + var11 * var9 + var11];
            if(var12 >= 0) {
               var1.setBlockMetadataWithNotify(var2, var3, var4, var6 & -9, 4);
            } else {
               this.removeLeaves(var1, var2, var3, var4);
            }
         }

      }
   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      if(var1.canLightningStrikeAt(var2, var3 + 1, var4) && !World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4) && var5.nextInt(15) == 1) {
         double var6 = (double)((float)var2 + var5.nextFloat());
         double var8 = (double)var3 - 0.05D;
         double var10 = (double)((float)var4 + var5.nextFloat());
         var1.spawnParticle("dripWater", var6, var8, var10, 0.0D, 0.0D, 0.0D);
      }

   }

   private void removeLeaves(World var1, int var2, int var3, int var4) {
      this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
      var1.setBlockToAir(var2, var3, var4);
   }

   public int quantityDropped(Random var1) {
      return var1.nextInt(20) == 0?1:0;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.sapling);
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      if(!var1.isRemote) {
         int var8 = this.func_150123_b(var5);
         if(var7 > 0) {
            var8 -= 2 << var7;
            if(var8 < 10) {
               var8 = 10;
            }
         }

         if(var1.rand.nextInt(var8) == 0) {
            Item var9 = this.getItemDropped(var5, var1.rand, var7);
            this.dropBlockAsItem(var1, var2, var3, var4, new ItemStack(var9, 1, this.damageDropped(var5)));
         }

         var8 = 200;
         if(var7 > 0) {
            var8 -= 10 << var7;
            if(var8 < 40) {
               var8 = 40;
            }
         }

         this.func_150124_c(var1, var2, var3, var4, var5, var8);
      }

   }

   protected void func_150124_c(World var1, int var2, int var3, int var4, int var5, int var6) {}

   protected int func_150123_b(int var1) {
      return 20;
   }

   public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6) {
      if(!var1.isRemote && var2.getCurrentEquippedItem() != null && var2.getCurrentEquippedItem().getItem() == Items.shears) {
         var2.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
         this.dropBlockAsItem(var1, var3, var4, var5, new ItemStack(Item.getItemFromBlock(this), 1, var6 & 3));
      } else {
         super.harvestBlock(var1, var2, var3, var4, var5, var6);
      }

   }

   public int damageDropped(int var1) {
      return var1 & 3;
   }

   public boolean isOpaqueCube() {
      return !super.field_150121_P;
   }

   public abstract IIcon getIcon(int var1, int var2);

   public void setGraphicsLevel(boolean var1) {
      super.field_150121_P = var1;
      this.field_150127_b = var1?0:1;
   }

   protected ItemStack createStackedBlock(int var1) {
      return new ItemStack(Item.getItemFromBlock(this), 1, var1 & 3);
   }

   public abstract String[] func_150125_e();
}
