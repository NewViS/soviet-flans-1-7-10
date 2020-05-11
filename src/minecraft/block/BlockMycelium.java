package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMycelium extends Block {

   private IIcon field_150200_a;
   private IIcon field_150199_b;


   protected BlockMycelium() {
      super(Material.grass);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?this.field_150200_a:(var1 == 0?Blocks.dirt.getBlockTextureFromSide(var1):super.blockIcon);
   }

   public IIcon getIcon(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      if(var5 == 1) {
         return this.field_150200_a;
      } else if(var5 == 0) {
         return Blocks.dirt.getBlockTextureFromSide(var5);
      } else {
         Material var6 = var1.getBlock(var2, var3 + 1, var4).getMaterial();
         return var6 != Material.snow && var6 != Material.craftedSnow?super.blockIcon:this.field_150199_b;
      }
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_side");
      this.field_150200_a = var1.registerIcon(this.getTextureName() + "_top");
      this.field_150199_b = var1.registerIcon("grass_side_snowed");
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
                  var1.setBlock(var7, var8, var9, this);
               }
            }
         }

      }
   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      super.randomDisplayTick(var1, var2, var3, var4, var5);
      if(var5.nextInt(10) == 0) {
         var1.spawnParticle("townaura", (double)((float)var2 + var5.nextFloat()), (double)((float)var3 + 1.1F), (double)((float)var4 + var5.nextFloat()), 0.0D, 0.0D, 0.0D);
      }

   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Blocks.dirt.getItemDropped(0, var2, var3);
   }
}
