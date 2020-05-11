package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDoublePlant extends BlockBush implements IGrowable {

   public static final String[] field_149892_a = new String[]{"sunflower", "syringa", "grass", "fern", "rose", "paeonia"};
   private IIcon[] doublePlantBottomIcons;
   private IIcon[] doublePlantTopIcons;
   public IIcon[] sunflowerIcons;


   public BlockDoublePlant() {
      super(Material.plants);
      this.setHardness(0.0F);
      this.setStepSound(Block.soundTypeGrass);
      this.setBlockName("doublePlant");
   }

   public int getRenderType() {
      return 40;
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public int func_149885_e(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      return !func_149887_c(var5)?var5 & 7:var1.getBlockMetadata(var2, var3 - 1, var4) & 7;
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return super.canPlaceBlockAt(var1, var2, var3, var4) && var1.isAirBlock(var2, var3 + 1, var4);
   }

   protected void checkAndDropBlock(World var1, int var2, int var3, int var4) {
      if(!this.canBlockStay(var1, var2, var3, var4)) {
         int var5 = var1.getBlockMetadata(var2, var3, var4);
         if(!func_149887_c(var5)) {
            this.dropBlockAsItem(var1, var2, var3, var4, var5, 0);
            if(var1.getBlock(var2, var3 + 1, var4) == this) {
               var1.setBlock(var2, var3 + 1, var4, Blocks.air, 0, 2);
            }
         }

         var1.setBlock(var2, var3, var4, Blocks.air, 0, 2);
      }

   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      return func_149887_c(var5)?var1.getBlock(var2, var3 - 1, var4) == this:var1.getBlock(var2, var3 + 1, var4) == this && super.canBlockStay(var1, var2, var3, var4);
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      if(func_149887_c(var1)) {
         return null;
      } else {
         int var4 = func_149890_d(var1);
         return var4 != 3 && var4 != 2?Item.getItemFromBlock(this):null;
      }
   }

   public int damageDropped(int var1) {
      return func_149887_c(var1)?0:var1 & 7;
   }

   public static boolean func_149887_c(int var0) {
      return (var0 & 8) != 0;
   }

   public static int func_149890_d(int var0) {
      return var0 & 7;
   }

   public IIcon getIcon(int var1, int var2) {
      return func_149887_c(var2)?this.doublePlantBottomIcons[0]:this.doublePlantBottomIcons[var2 & 7];
   }

   public IIcon func_149888_a(boolean var1, int var2) {
      return var1?this.doublePlantTopIcons[var2]:this.doublePlantBottomIcons[var2];
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = this.func_149885_e(var1, var2, var3, var4);
      return var5 != 2 && var5 != 3?16777215:var1.getBiomeGenForCoords(var2, var4).getBiomeGrassColor(var2, var3, var4);
   }

   public void func_149889_c(World var1, int var2, int var3, int var4, int var5, int var6) {
      var1.setBlock(var2, var3, var4, this, var5, var6);
      var1.setBlock(var2, var3 + 1, var4, this, 8, var6);
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      int var7 = ((MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
      var1.setBlock(var2, var3 + 1, var4, this, 8 | var7, 2);
   }

   public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6) {
      if(var1.isRemote || var2.getCurrentEquippedItem() == null || var2.getCurrentEquippedItem().getItem() != Items.shears || func_149887_c(var6) || !this.func_149886_b(var1, var3, var4, var5, var6, var2)) {
         super.harvestBlock(var1, var2, var3, var4, var5, var6);
      }
   }

   public void onBlockHarvested(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6) {
      if(func_149887_c(var5)) {
         if(var1.getBlock(var2, var3 - 1, var4) == this) {
            if(!var6.capabilities.isCreativeMode) {
               int var7 = var1.getBlockMetadata(var2, var3 - 1, var4);
               int var8 = func_149890_d(var7);
               if(var8 != 3 && var8 != 2) {
                  var1.func_147480_a(var2, var3 - 1, var4, true);
               } else {
                  if(!var1.isRemote && var6.getCurrentEquippedItem() != null && var6.getCurrentEquippedItem().getItem() == Items.shears) {
                     this.func_149886_b(var1, var2, var3, var4, var7, var6);
                  }

                  var1.setBlockToAir(var2, var3 - 1, var4);
               }
            } else {
               var1.setBlockToAir(var2, var3 - 1, var4);
            }
         }
      } else if(var6.capabilities.isCreativeMode && var1.getBlock(var2, var3 + 1, var4) == this) {
         var1.setBlock(var2, var3 + 1, var4, Blocks.air, 0, 2);
      }

      super.onBlockHarvested(var1, var2, var3, var4, var5, var6);
   }

   private boolean func_149886_b(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6) {
      int var7 = func_149890_d(var5);
      if(var7 != 3 && var7 != 2) {
         return false;
      } else {
         var6.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
         byte var8 = 1;
         if(var7 == 3) {
            var8 = 2;
         }

         this.dropBlockAsItem(var1, var2, var3, var4, new ItemStack(Blocks.tallgrass, 2, var8));
         return true;
      }
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.doublePlantBottomIcons = new IIcon[field_149892_a.length];
      this.doublePlantTopIcons = new IIcon[field_149892_a.length];

      for(int var2 = 0; var2 < this.doublePlantBottomIcons.length; ++var2) {
         this.doublePlantBottomIcons[var2] = var1.registerIcon("double_plant_" + field_149892_a[var2] + "_bottom");
         this.doublePlantTopIcons[var2] = var1.registerIcon("double_plant_" + field_149892_a[var2] + "_top");
      }

      this.sunflowerIcons = new IIcon[2];
      this.sunflowerIcons[0] = var1.registerIcon("double_plant_sunflower_front");
      this.sunflowerIcons[1] = var1.registerIcon("double_plant_sunflower_back");
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      for(int var4 = 0; var4 < this.doublePlantBottomIcons.length; ++var4) {
         var3.add(new ItemStack(var1, 1, var4));
      }

   }

   public int getDamageValue(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      return func_149887_c(var5)?func_149890_d(var1.getBlockMetadata(var2, var3 - 1, var4)):func_149890_d(var5);
   }

   public boolean func_149851_a(World var1, int var2, int var3, int var4, boolean var5) {
      int var6 = this.func_149885_e(var1, var2, var3, var4);
      return var6 != 2 && var6 != 3;
   }

   public boolean func_149852_a(World var1, Random var2, int var3, int var4, int var5) {
      return true;
   }

   public void func_149853_b(World var1, Random var2, int var3, int var4, int var5) {
      int var6 = this.func_149885_e(var1, var3, var4, var5);
      this.dropBlockAsItem(var1, var3, var4, var5, new ItemStack(this, 1, var6));
   }

}
