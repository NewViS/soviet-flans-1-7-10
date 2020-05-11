package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class BlockSilverfish extends Block {

   public static final String[] field_150198_a = new String[]{"stone", "cobble", "brick", "mossybrick", "crackedbrick", "chiseledbrick"};


   public BlockSilverfish() {
      super(Material.clay);
      this.setHardness(0.0F);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public IIcon getIcon(int var1, int var2) {
      switch(var2) {
      case 1:
         return Blocks.cobblestone.getBlockTextureFromSide(var1);
      case 2:
         return Blocks.stonebrick.getBlockTextureFromSide(var1);
      case 3:
         return Blocks.stonebrick.getIcon(var1, 1);
      case 4:
         return Blocks.stonebrick.getIcon(var1, 2);
      case 5:
         return Blocks.stonebrick.getIcon(var1, 3);
      default:
         return Blocks.stone.getBlockTextureFromSide(var1);
      }
   }

   public void registerBlockIcons(IIconRegister var1) {}

   public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5) {
      if(!var1.isRemote) {
         EntitySilverfish var6 = new EntitySilverfish(var1);
         var6.setLocationAndAngles((double)var2 + 0.5D, (double)var3, (double)var4 + 0.5D, 0.0F, 0.0F);
         var1.spawnEntityInWorld(var6);
         var6.spawnExplosionParticle();
      }

      super.onBlockDestroyedByPlayer(var1, var2, var3, var4, var5);
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public static boolean func_150196_a(Block var0) {
      return var0 == Blocks.stone || var0 == Blocks.cobblestone || var0 == Blocks.stonebrick;
   }

   public static int func_150195_a(Block var0, int var1) {
      if(var1 == 0) {
         if(var0 == Blocks.cobblestone) {
            return 1;
         }

         if(var0 == Blocks.stonebrick) {
            return 2;
         }
      } else if(var0 == Blocks.stonebrick) {
         switch(var1) {
         case 1:
            return 3;
         case 2:
            return 4;
         case 3:
            return 5;
         }
      }

      return 0;
   }

   public static ImmutablePair func_150197_b(int var0) {
      switch(var0) {
      case 1:
         return new ImmutablePair(Blocks.cobblestone, Integer.valueOf(0));
      case 2:
         return new ImmutablePair(Blocks.stonebrick, Integer.valueOf(0));
      case 3:
         return new ImmutablePair(Blocks.stonebrick, Integer.valueOf(1));
      case 4:
         return new ImmutablePair(Blocks.stonebrick, Integer.valueOf(2));
      case 5:
         return new ImmutablePair(Blocks.stonebrick, Integer.valueOf(3));
      default:
         return new ImmutablePair(Blocks.stone, Integer.valueOf(0));
      }
   }

   protected ItemStack createStackedBlock(int var1) {
      switch(var1) {
      case 1:
         return new ItemStack(Blocks.cobblestone);
      case 2:
         return new ItemStack(Blocks.stonebrick);
      case 3:
         return new ItemStack(Blocks.stonebrick, 1, 1);
      case 4:
         return new ItemStack(Blocks.stonebrick, 1, 2);
      case 5:
         return new ItemStack(Blocks.stonebrick, 1, 3);
      default:
         return new ItemStack(Blocks.stone);
      }
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      if(!var1.isRemote) {
         EntitySilverfish var8 = new EntitySilverfish(var1);
         var8.setLocationAndAngles((double)var2 + 0.5D, (double)var3, (double)var4 + 0.5D, 0.0F, 0.0F);
         var1.spawnEntityInWorld(var8);
         var8.spawnExplosionParticle();
      }

   }

   public int getDamageValue(World var1, int var2, int var3, int var4) {
      return var1.getBlockMetadata(var2, var3, var4);
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      for(int var4 = 0; var4 < field_150198_a.length; ++var4) {
         var3.add(new ItemStack(var1, 1, var4));
      }

   }

}
