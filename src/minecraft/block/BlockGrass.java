package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockGrass extends Block implements IGrowable {

   private static final Logger logger = LogManager.getLogger();
   private IIcon field_149991_b;
   private IIcon field_149993_M;
   private IIcon field_149994_N;


   protected BlockGrass() {
      super(Material.grass);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?this.field_149991_b:(var1 == 0?Blocks.dirt.getBlockTextureFromSide(var1):super.blockIcon);
   }

   public IIcon getIcon(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      if(var5 == 1) {
         return this.field_149991_b;
      } else if(var5 == 0) {
         return Blocks.dirt.getBlockTextureFromSide(var5);
      } else {
         Material var6 = var1.getBlock(var2, var3 + 1, var4).getMaterial();
         return var6 != Material.snow && var6 != Material.craftedSnow?super.blockIcon:this.field_149993_M;
      }
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_side");
      this.field_149991_b = var1.registerIcon(this.getTextureName() + "_top");
      this.field_149993_M = var1.registerIcon(this.getTextureName() + "_side_snowed");
      this.field_149994_N = var1.registerIcon(this.getTextureName() + "_side_overlay");
   }

   public int getBlockColor() {
      double var1 = 0.5D;
      double var3 = 1.0D;
      return ColorizerGrass.getGrassColor(var1, var3);
   }

   public int getRenderColor(int var1) {
      return this.getBlockColor();
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      for(int var8 = -1; var8 <= 1; ++var8) {
         for(int var9 = -1; var9 <= 1; ++var9) {
            int var10 = var1.getBiomeGenForCoords(var2 + var9, var4 + var8).getBiomeGrassColor(var2 + var9, var3, var4 + var8);
            var5 += (var10 & 16711680) >> 16;
            var6 += (var10 & '\uff00') >> 8;
            var7 += var10 & 255;
         }
      }

      return (var5 / 9 & 255) << 16 | (var6 / 9 & 255) << 8 | var7 / 9 & 255;
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!var1.isRemote) {
         if(var1.getBlockLightValue(var2, var3 + 1, var4) < 4 && var1.getBlock(var2, var3 + 1, var4).getLightOpacity() > 2) {
            var1.setBlock(var2, var3, var4, Blocks.dirt);
         } else if(var1.getBlockLightValue(var2, var3 + 1, var4) >= 9) {
            for(int var6 = 0; var6 < 4; ++var6) {
               int var7 = var2 + var5.nextInt(3) - 1;
               int var8 = var3 + var5.nextInt(5) - 3;
               int var9 = var4 + var5.nextInt(3) - 1;
               Block var10 = var1.getBlock(var7, var8 + 1, var9);
               if(var1.getBlock(var7, var8, var9) == Blocks.dirt && var1.getBlockMetadata(var7, var8, var9) == 0 && var1.getBlockLightValue(var7, var8 + 1, var9) >= 4 && var10.getLightOpacity() <= 2) {
                  var1.setBlock(var7, var8, var9, Blocks.grass);
               }
            }
         }

      }
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Blocks.dirt.getItemDropped(0, var2, var3);
   }

   public static IIcon getIconSideOverlay() {
      return Blocks.grass.field_149994_N;
   }

   public boolean func_149851_a(World var1, int var2, int var3, int var4, boolean var5) {
      return true;
   }

   public boolean func_149852_a(World var1, Random var2, int var3, int var4, int var5) {
      return true;
   }

   public void func_149853_b(World var1, Random var2, int var3, int var4, int var5) {
      int var6 = 0;

      while(var6 < 128) {
         int var7 = var3;
         int var8 = var4 + 1;
         int var9 = var5;
         int var10 = 0;

         while(true) {
            if(var10 < var6 / 16) {
               var7 += var2.nextInt(3) - 1;
               var8 += (var2.nextInt(3) - 1) * var2.nextInt(3) / 2;
               var9 += var2.nextInt(3) - 1;
               if(var1.getBlock(var7, var8 - 1, var9) == Blocks.grass && !var1.getBlock(var7, var8, var9).isNormalCube()) {
                  ++var10;
                  continue;
               }
            } else if(var1.getBlock(var7, var8, var9).blockMaterial == Material.air) {
               if(var2.nextInt(8) != 0) {
                  if(Blocks.tallgrass.canBlockStay(var1, var7, var8, var9)) {
                     var1.setBlock(var7, var8, var9, Blocks.tallgrass, 1, 3);
                  }
               } else {
                  String var13 = var1.getBiomeGenForCoords(var7, var9).func_150572_a(var2, var7, var8, var9);
                  logger.debug("Flower in " + var1.getBiomeGenForCoords(var7, var9).biomeName + ": " + var13);
                  BlockFlower var11 = BlockFlower.func_149857_e(var13);
                  if(var11 != null && var11.canBlockStay(var1, var7, var8, var9)) {
                     int var12 = BlockFlower.func_149856_f(var13);
                     var1.setBlock(var7, var8, var9, var11, var12, 3);
                  }
               }
            }

            ++var6;
            break;
         }
      }

   }

}
