package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIce extends BlockBreakable {

   public BlockIce() {
      super("ice", Material.ice, false);
      super.slipperiness = 0.98F;
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public int getRenderBlockPass() {
      return 1;
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return super.shouldSideBeRendered(var1, var2, var3, var4, 1 - var5);
   }

   public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6) {
      var2.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
      var2.addExhaustion(0.025F);
      if(this.canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier(var2)) {
         ItemStack var9 = this.createStackedBlock(var6);
         if(var9 != null) {
            this.dropBlockAsItem(var1, var3, var4, var5, var9);
         }
      } else {
         if(var1.provider.isHellWorld) {
            var1.setBlockToAir(var3, var4, var5);
            return;
         }

         int var7 = EnchantmentHelper.getFortuneModifier(var2);
         this.dropBlockAsItem(var1, var3, var4, var5, var6, var7);
         Material var8 = var1.getBlock(var3, var4 - 1, var5).getMaterial();
         if(var8.blocksMovement() || var8.isLiquid()) {
            var1.setBlock(var3, var4, var5, Blocks.flowing_water);
         }
      }

   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(var1.getSavedLightValue(EnumSkyBlock.Block, var2, var3, var4) > 11 - this.getLightOpacity()) {
         if(var1.provider.isHellWorld) {
            var1.setBlockToAir(var2, var3, var4);
            return;
         }

         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlock(var2, var3, var4, Blocks.water);
      }

   }

   public int getMobilityFlag() {
      return 0;
   }
}
