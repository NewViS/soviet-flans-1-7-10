package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockNetherWart extends BlockBush {

   private IIcon[] field_149883_a;


   protected BlockNetherWart() {
      this.setTickRandomly(true);
      float var1 = 0.5F;
      this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.25F, 0.5F + var1);
      this.setCreativeTab((CreativeTabs)null);
   }

   protected boolean canPlaceBlockOn(Block var1) {
      return var1 == Blocks.soul_sand;
   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      return this.canPlaceBlockOn(var1.getBlock(var2, var3 - 1, var4));
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      if(var6 < 3 && var5.nextInt(10) == 0) {
         ++var6;
         var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 2);
      }

      super.updateTick(var1, var2, var3, var4, var5);
   }

   public IIcon getIcon(int var1, int var2) {
      return var2 >= 3?this.field_149883_a[2]:(var2 > 0?this.field_149883_a[1]:this.field_149883_a[0]);
   }

   public int getRenderType() {
      return 6;
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      if(!var1.isRemote) {
         int var8 = 1;
         if(var5 >= 3) {
            var8 = 2 + var1.rand.nextInt(3);
            if(var7 > 0) {
               var8 += var1.rand.nextInt(var7 + 1);
            }
         }

         for(int var9 = 0; var9 < var8; ++var9) {
            this.dropBlockAsItem(var1, var2, var3, var4, new ItemStack(Items.nether_wart));
         }

      }
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return null;
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Items.nether_wart;
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_149883_a = new IIcon[3];

      for(int var2 = 0; var2 < this.field_149883_a.length; ++var2) {
         this.field_149883_a[var2] = var1.registerIcon(this.getTextureName() + "_stage_" + var2);
      }

   }
}
