package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCrops extends BlockBush implements IGrowable {

   private IIcon[] field_149867_a;


   protected BlockCrops() {
      this.setTickRandomly(true);
      float var1 = 0.5F;
      this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.25F, 0.5F + var1);
      this.setCreativeTab((CreativeTabs)null);
      this.setHardness(0.0F);
      this.setStepSound(Block.soundTypeGrass);
      this.disableStats();
   }

   protected boolean canPlaceBlockOn(Block var1) {
      return var1 == Blocks.farmland;
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      super.updateTick(var1, var2, var3, var4, var5);
      if(var1.getBlockLightValue(var2, var3 + 1, var4) >= 9) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         if(var6 < 7) {
            float var7 = this.func_149864_n(var1, var2, var3, var4);
            if(var5.nextInt((int)(25.0F / var7) + 1) == 0) {
               ++var6;
               var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 2);
            }
         }
      }

   }

   public void func_149863_m(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4) + MathHelper.getRandomIntegerInRange(var1.rand, 2, 5);
      if(var5 > 7) {
         var5 = 7;
      }

      var1.setBlockMetadataWithNotify(var2, var3, var4, var5, 2);
   }

   private float func_149864_n(World var1, int var2, int var3, int var4) {
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
            float var19 = 0.0F;
            if(var1.getBlock(var17, var3 - 1, var18) == Blocks.farmland) {
               var19 = 1.0F;
               if(var1.getBlockMetadata(var17, var3 - 1, var18) > 0) {
                  var19 = 3.0F;
               }
            }

            if(var17 != var2 || var18 != var4) {
               var19 /= 4.0F;
            }

            var5 += var19;
         }
      }

      if(var16 || var14 && var15) {
         var5 /= 2.0F;
      }

      return var5;
   }

   public IIcon getIcon(int var1, int var2) {
      if(var2 < 0 || var2 > 7) {
         var2 = 7;
      }

      return this.field_149867_a[var2];
   }

   public int getRenderType() {
      return 6;
   }

   protected Item func_149866_i() {
      return Items.wheat_seeds;
   }

   protected Item func_149865_P() {
      return Items.wheat;
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, 0);
      if(!var1.isRemote) {
         if(var5 >= 7) {
            int var8 = 3 + var7;

            for(int var9 = 0; var9 < var8; ++var9) {
               if(var1.rand.nextInt(15) <= var5) {
                  this.dropBlockAsItem(var1, var2, var3, var4, new ItemStack(this.func_149866_i(), 1, 0));
               }
            }
         }

      }
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return var1 == 7?this.func_149865_P():this.func_149866_i();
   }

   public int quantityDropped(Random var1) {
      return 1;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return this.func_149866_i();
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_149867_a = new IIcon[8];

      for(int var2 = 0; var2 < this.field_149867_a.length; ++var2) {
         this.field_149867_a[var2] = var1.registerIcon(this.getTextureName() + "_stage_" + var2);
      }

   }

   public boolean func_149851_a(World var1, int var2, int var3, int var4, boolean var5) {
      return var1.getBlockMetadata(var2, var3, var4) != 7;
   }

   public boolean func_149852_a(World var1, Random var2, int var3, int var4, int var5) {
      return true;
   }

   public void func_149853_b(World var1, Random var2, int var3, int var4, int var5) {
      this.func_149863_m(var1, var3, var4, var5);
   }
}
