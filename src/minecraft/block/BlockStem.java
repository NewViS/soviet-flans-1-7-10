package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStem extends BlockBush implements IGrowable {

   private final Block field_149877_a;
   private IIcon field_149876_b;


   protected BlockStem(Block var1) {
      this.field_149877_a = var1;
      this.setTickRandomly(true);
      float var2 = 0.125F;
      this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, 0.25F, 0.5F + var2);
      this.setCreativeTab((CreativeTabs)null);
   }

   protected boolean canPlaceBlockOn(Block var1) {
      return var1 == Blocks.farmland;
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      super.updateTick(var1, var2, var3, var4, var5);
      if(var1.getBlockLightValue(var2, var3 + 1, var4) >= 9) {
         float var6 = this.func_149875_n(var1, var2, var3, var4);
         if(var5.nextInt((int)(25.0F / var6) + 1) == 0) {
            int var7 = var1.getBlockMetadata(var2, var3, var4);
            if(var7 < 7) {
               ++var7;
               var1.setBlockMetadataWithNotify(var2, var3, var4, var7, 2);
            } else {
               if(var1.getBlock(var2 - 1, var3, var4) == this.field_149877_a) {
                  return;
               }

               if(var1.getBlock(var2 + 1, var3, var4) == this.field_149877_a) {
                  return;
               }

               if(var1.getBlock(var2, var3, var4 - 1) == this.field_149877_a) {
                  return;
               }

               if(var1.getBlock(var2, var3, var4 + 1) == this.field_149877_a) {
                  return;
               }

               int var8 = var5.nextInt(4);
               int var9 = var2;
               int var10 = var4;
               if(var8 == 0) {
                  var9 = var2 - 1;
               }

               if(var8 == 1) {
                  ++var9;
               }

               if(var8 == 2) {
                  var10 = var4 - 1;
               }

               if(var8 == 3) {
                  ++var10;
               }

               Block var11 = var1.getBlock(var9, var3 - 1, var10);
               if(var1.getBlock(var9, var3, var10).blockMaterial == Material.air && (var11 == Blocks.farmland || var11 == Blocks.dirt || var11 == Blocks.grass)) {
                  var1.setBlock(var9, var3, var10, this.field_149877_a);
               }
            }
         }
      }

   }

   public void func_149874_m(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4) + MathHelper.getRandomIntegerInRange(var1.rand, 2, 5);
      if(var5 > 7) {
         var5 = 7;
      }

      var1.setBlockMetadataWithNotify(var2, var3, var4, var5, 2);
   }

   private float func_149875_n(World var1, int var2, int var3, int var4) {
      float var5 = 1.0F;
      Block var6 = var1.getBlock(var2, var3, var4 - 1);
      Block var7 = var1.getBlock(var2, var3, var4 + 1);
      Block var8 = var1.getBlock(var2 - 1, var3, var4);
      Block var9 = var1.getBlock(var2 + 1, var3, var4);
      Block var10 = var1.getBlock(var2 - 1, var3, var4 - 1);
      Block var11 = var1.getBlock(var2 + 1, var3, var4 - 1);
      Block var12 = var1.getBlock(var2 + 1, var3, var4 + 1);
      Block var13 = var1.getBlock(var2 - 1, var3, var4 + 1);
      boolean var14 = var8 == this || var9 == this;
      boolean var15 = var6 == this || var7 == this;
      boolean var16 = var10 == this || var11 == this || var12 == this || var13 == this;

      for(int var17 = var2 - 1; var17 <= var2 + 1; ++var17) {
         for(int var18 = var4 - 1; var18 <= var4 + 1; ++var18) {
            Block var19 = var1.getBlock(var17, var3 - 1, var18);
            float var20 = 0.0F;
            if(var19 == Blocks.farmland) {
               var20 = 1.0F;
               if(var1.getBlockMetadata(var17, var3 - 1, var18) > 0) {
                  var20 = 3.0F;
               }
            }

            if(var17 != var2 || var18 != var4) {
               var20 /= 4.0F;
            }

            var5 += var20;
         }
      }

      if(var16 || var14 && var15) {
         var5 /= 2.0F;
      }

      return var5;
   }

   public int getRenderColor(int var1) {
      int var2 = var1 * 32;
      int var3 = 255 - var1 * 8;
      int var4 = var1 * 4;
      return var2 << 16 | var3 << 8 | var4;
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      return this.getRenderColor(var1.getBlockMetadata(var2, var3, var4));
   }

   public void setBlockBoundsForItemRender() {
      float var1 = 0.125F;
      this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.25F, 0.5F + var1);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      super.maxY = (double)((float)(var1.getBlockMetadata(var2, var3, var4) * 2 + 2) / 16.0F);
      float var5 = 0.125F;
      this.setBlockBounds(0.5F - var5, 0.0F, 0.5F - var5, 0.5F + var5, (float)super.maxY, 0.5F + var5);
   }

   public int getRenderType() {
      return 19;
   }

   public int getState(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      return var5 < 7?-1:(var1.getBlock(var2 - 1, var3, var4) == this.field_149877_a?0:(var1.getBlock(var2 + 1, var3, var4) == this.field_149877_a?1:(var1.getBlock(var2, var3, var4 - 1) == this.field_149877_a?2:(var1.getBlock(var2, var3, var4 + 1) == this.field_149877_a?3:-1))));
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);
      if(!var1.isRemote) {
         Item var8 = null;
         if(this.field_149877_a == Blocks.pumpkin) {
            var8 = Items.pumpkin_seeds;
         }

         if(this.field_149877_a == Blocks.melon_block) {
            var8 = Items.melon_seeds;
         }

         for(int var9 = 0; var9 < 3; ++var9) {
            if(var1.rand.nextInt(15) <= var5) {
               this.dropBlockAsItem(var1, var2, var3, var4, new ItemStack(var8));
            }
         }

      }
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return null;
   }

   public int quantityDropped(Random var1) {
      return 1;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return this.field_149877_a == Blocks.pumpkin?Items.pumpkin_seeds:(this.field_149877_a == Blocks.melon_block?Items.melon_seeds:Item.getItemById(0));
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_disconnected");
      this.field_149876_b = var1.registerIcon(this.getTextureName() + "_connected");
   }

   public IIcon getStemIcon() {
      return this.field_149876_b;
   }

   public boolean func_149851_a(World var1, int var2, int var3, int var4, boolean var5) {
      return var1.getBlockMetadata(var2, var3, var4) != 7;
   }

   public boolean func_149852_a(World var1, Random var2, int var3, int var4, int var5) {
      return true;
   }

   public void func_149853_b(World var1, Random var2, int var3, int var4, int var5) {
      this.func_149874_m(var1, var3, var4, var5);
   }
}
