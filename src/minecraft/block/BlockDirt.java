package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDirt extends Block {

   public static final String[] field_150009_a = new String[]{"default", "default", "podzol"};
   private IIcon field_150008_b;
   private IIcon field_150010_M;


   protected BlockDirt() {
      super(Material.ground);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      if(var2 == 2) {
         if(var1 == 1) {
            return this.field_150008_b;
         }

         if(var1 != 0) {
            return this.field_150010_M;
         }
      }

      return super.blockIcon;
   }

   public IIcon getIcon(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      if(var6 == 2) {
         if(var5 == 1) {
            return this.field_150008_b;
         }

         if(var5 != 0) {
            Material var7 = var1.getBlock(var2, var3 + 1, var4).getMaterial();
            if(var7 == Material.snow || var7 == Material.craftedSnow) {
               return Blocks.grass.getIcon(var1, var2, var3, var4, var5);
            }

            Block var8 = var1.getBlock(var2, var3 + 1, var4);
            if(var8 != Blocks.dirt && var8 != Blocks.grass) {
               return this.field_150010_M;
            }
         }
      }

      return super.blockIcon;
   }

   public int damageDropped(int var1) {
      return 0;
   }

   protected ItemStack createStackedBlock(int var1) {
      if(var1 == 1) {
         var1 = 0;
      }

      return super.createStackedBlock(var1);
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(this, 1, 0));
      var3.add(new ItemStack(this, 1, 2));
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.registerBlockIcons(var1);
      this.field_150008_b = var1.registerIcon(this.getTextureName() + "_" + "podzol_top");
      this.field_150010_M = var1.registerIcon(this.getTextureName() + "_" + "podzol_side");
   }

   public int getDamageValue(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      if(var5 == 1) {
         var5 = 0;
      }

      return var5;
   }

}
