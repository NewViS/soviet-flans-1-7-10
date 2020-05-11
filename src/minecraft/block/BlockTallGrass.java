package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTallGrass extends BlockBush implements IGrowable {

   private static final String[] field_149871_a = new String[]{"deadbush", "tallgrass", "fern"};
   private IIcon[] field_149870_b;


   protected BlockTallGrass() {
      super(Material.vine);
      float var1 = 0.4F;
      this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.8F, 0.5F + var1);
   }

   public IIcon getIcon(int var1, int var2) {
      if(var2 >= this.field_149870_b.length) {
         var2 = 0;
      }

      return this.field_149870_b[var2];
   }

   public int getBlockColor() {
      double var1 = 0.5D;
      double var3 = 1.0D;
      return ColorizerGrass.getGrassColor(var1, var3);
   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      return this.canPlaceBlockOn(var1.getBlock(var2, var3 - 1, var4));
   }

   public int getRenderColor(int var1) {
      return var1 == 0?16777215:ColorizerGrass.getGrassColor(0.5D, 1.0D);
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      return var5 == 0?16777215:var1.getBiomeGenForCoords(var2, var4).getBiomeGrassColor(var2, var3, var4);
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return var2.nextInt(8) == 0?Items.wheat_seeds:null;
   }

   public int quantityDroppedWithBonus(int var1, Random var2) {
      return 1 + var2.nextInt(var1 * 2 + 1);
   }

   public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6) {
      if(!var1.isRemote && var2.getCurrentEquippedItem() != null && var2.getCurrentEquippedItem().getItem() == Items.shears) {
         var2.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
         this.dropBlockAsItem(var1, var3, var4, var5, new ItemStack(Blocks.tallgrass, 1, var6));
      } else {
         super.harvestBlock(var1, var2, var3, var4, var5, var6);
      }

   }

   public int getDamageValue(World var1, int var2, int var3, int var4) {
      return var1.getBlockMetadata(var2, var3, var4);
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      for(int var4 = 1; var4 < 3; ++var4) {
         var3.add(new ItemStack(var1, 1, var4));
      }

   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_149870_b = new IIcon[field_149871_a.length];

      for(int var2 = 0; var2 < this.field_149870_b.length; ++var2) {
         this.field_149870_b[var2] = var1.registerIcon(field_149871_a[var2]);
      }

   }

   public boolean func_149851_a(World var1, int var2, int var3, int var4, boolean var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      return var6 != 0;
   }

   public boolean func_149852_a(World var1, Random var2, int var3, int var4, int var5) {
      return true;
   }

   public void func_149853_b(World var1, Random var2, int var3, int var4, int var5) {
      int var6 = var1.getBlockMetadata(var3, var4, var5);
      byte var7 = 2;
      if(var6 == 2) {
         var7 = 3;
      }

      if(Blocks.double_plant.canPlaceBlockAt(var1, var3, var4, var5)) {
         Blocks.double_plant.func_149889_c(var1, var3, var4, var5, var7, 2);
      }

   }

}
