package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BlockSapling extends BlockBush implements IGrowable {

   public static final String[] field_149882_a = new String[]{"oak", "spruce", "birch", "jungle", "acacia", "roofed_oak"};
   private static final IIcon[] field_149881_b = new IIcon[field_149882_a.length];


   protected BlockSapling() {
      float var1 = 0.4F;
      this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var1 * 2.0F, 0.5F + var1);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!var1.isRemote) {
         super.updateTick(var1, var2, var3, var4, var5);
         if(var1.getBlockLightValue(var2, var3 + 1, var4) >= 9 && var5.nextInt(7) == 0) {
            this.func_149879_c(var1, var2, var3, var4, var5);
         }

      }
   }

   public IIcon getIcon(int var1, int var2) {
      var2 &= 7;
      return field_149881_b[MathHelper.clamp_int(var2, 0, 5)];
   }

   public void func_149879_c(World var1, int var2, int var3, int var4, Random var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      if((var6 & 8) == 0) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var6 | 8, 4);
      } else {
         this.func_149878_d(var1, var2, var3, var4, var5);
      }

   }

   public void func_149878_d(World var1, int var2, int var3, int var4, Random var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4) & 7;
      Object var7 = var5.nextInt(10) == 0?new WorldGenBigTree(true):new WorldGenTrees(true);
      int var8 = 0;
      int var9 = 0;
      boolean var10 = false;
      switch(var6) {
      case 0:
      default:
         break;
      case 1:
         label78:
         for(var8 = 0; var8 >= -1; --var8) {
            for(var9 = 0; var9 >= -1; --var9) {
               if(this.func_149880_a(var1, var2 + var8, var3, var4 + var9, 1) && this.func_149880_a(var1, var2 + var8 + 1, var3, var4 + var9, 1) && this.func_149880_a(var1, var2 + var8, var3, var4 + var9 + 1, 1) && this.func_149880_a(var1, var2 + var8 + 1, var3, var4 + var9 + 1, 1)) {
                  var7 = new WorldGenMegaPineTree(false, var5.nextBoolean());
                  var10 = true;
                  break label78;
               }
            }
         }

         if(!var10) {
            var9 = 0;
            var8 = 0;
            var7 = new WorldGenTaiga2(true);
         }
         break;
      case 2:
         var7 = new WorldGenForest(true, false);
         break;
      case 3:
         label93:
         for(var8 = 0; var8 >= -1; --var8) {
            for(var9 = 0; var9 >= -1; --var9) {
               if(this.func_149880_a(var1, var2 + var8, var3, var4 + var9, 3) && this.func_149880_a(var1, var2 + var8 + 1, var3, var4 + var9, 3) && this.func_149880_a(var1, var2 + var8, var3, var4 + var9 + 1, 3) && this.func_149880_a(var1, var2 + var8 + 1, var3, var4 + var9 + 1, 3)) {
                  var7 = new WorldGenMegaJungle(true, 10, 20, 3, 3);
                  var10 = true;
                  break label93;
               }
            }
         }

         if(!var10) {
            var9 = 0;
            var8 = 0;
            var7 = new WorldGenTrees(true, 4 + var5.nextInt(7), 3, 3, false);
         }
         break;
      case 4:
         var7 = new WorldGenSavannaTree(true);
         break;
      case 5:
         label108:
         for(var8 = 0; var8 >= -1; --var8) {
            for(var9 = 0; var9 >= -1; --var9) {
               if(this.func_149880_a(var1, var2 + var8, var3, var4 + var9, 5) && this.func_149880_a(var1, var2 + var8 + 1, var3, var4 + var9, 5) && this.func_149880_a(var1, var2 + var8, var3, var4 + var9 + 1, 5) && this.func_149880_a(var1, var2 + var8 + 1, var3, var4 + var9 + 1, 5)) {
                  var7 = new WorldGenCanopyTree(true);
                  var10 = true;
                  break label108;
               }
            }
         }

         if(!var10) {
            return;
         }
      }

      Block var11 = Blocks.air;
      if(var10) {
         var1.setBlock(var2 + var8, var3, var4 + var9, var11, 0, 4);
         var1.setBlock(var2 + var8 + 1, var3, var4 + var9, var11, 0, 4);
         var1.setBlock(var2 + var8, var3, var4 + var9 + 1, var11, 0, 4);
         var1.setBlock(var2 + var8 + 1, var3, var4 + var9 + 1, var11, 0, 4);
      } else {
         var1.setBlock(var2, var3, var4, var11, 0, 4);
      }

      if(!((WorldGenerator)var7).generate(var1, var5, var2 + var8, var3, var4 + var9)) {
         if(var10) {
            var1.setBlock(var2 + var8, var3, var4 + var9, this, var6, 4);
            var1.setBlock(var2 + var8 + 1, var3, var4 + var9, this, var6, 4);
            var1.setBlock(var2 + var8, var3, var4 + var9 + 1, this, var6, 4);
            var1.setBlock(var2 + var8 + 1, var3, var4 + var9 + 1, this, var6, 4);
         } else {
            var1.setBlock(var2, var3, var4, this, var6, 4);
         }
      }

   }

   public boolean func_149880_a(World var1, int var2, int var3, int var4, int var5) {
      return var1.getBlock(var2, var3, var4) == this && (var1.getBlockMetadata(var2, var3, var4) & 7) == var5;
   }

   public int damageDropped(int var1) {
      return MathHelper.clamp_int(var1 & 7, 0, 5);
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
      var3.add(new ItemStack(var1, 1, 2));
      var3.add(new ItemStack(var1, 1, 3));
      var3.add(new ItemStack(var1, 1, 4));
      var3.add(new ItemStack(var1, 1, 5));
   }

   public void registerBlockIcons(IIconRegister var1) {
      for(int var2 = 0; var2 < field_149881_b.length; ++var2) {
         field_149881_b[var2] = var1.registerIcon(this.getTextureName() + "_" + field_149882_a[var2]);
      }

   }

   public boolean func_149851_a(World var1, int var2, int var3, int var4, boolean var5) {
      return true;
   }

   public boolean func_149852_a(World var1, Random var2, int var3, int var4, int var5) {
      return (double)var1.rand.nextFloat() < 0.45D;
   }

   public void func_149853_b(World var1, Random var2, int var3, int var4, int var5) {
      this.func_149879_c(var1, var3, var4, var5, var2);
   }

}
