package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockRedstoneOre extends Block {

   private boolean field_150187_a;


   public BlockRedstoneOre(boolean var1) {
      super(Material.rock);
      if(var1) {
         this.setTickRandomly(true);
      }

      this.field_150187_a = var1;
   }

   public int tickRate(World var1) {
      return 30;
   }

   public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {
      this.func_150185_e(var1, var2, var3, var4);
      super.onBlockClicked(var1, var2, var3, var4, var5);
   }

   public void onEntityWalking(World var1, int var2, int var3, int var4, Entity var5) {
      this.func_150185_e(var1, var2, var3, var4);
      super.onEntityWalking(var1, var2, var3, var4, var5);
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      this.func_150185_e(var1, var2, var3, var4);
      return super.onBlockActivated(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   private void func_150185_e(World var1, int var2, int var3, int var4) {
      this.func_150186_m(var1, var2, var3, var4);
      if(this == Blocks.redstone_ore) {
         var1.setBlock(var2, var3, var4, Blocks.lit_redstone_ore);
      }

   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(this == Blocks.lit_redstone_ore) {
         var1.setBlock(var2, var3, var4, Blocks.redstone_ore);
      }

   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.redstone;
   }

   public int quantityDroppedWithBonus(int var1, Random var2) {
      return this.quantityDropped(var2) + var2.nextInt(var1 + 1);
   }

   public int quantityDropped(Random var1) {
      return 4 + var1.nextInt(2);
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);
      if(this.getItemDropped(var5, var1.rand, var7) != Item.getItemFromBlock(this)) {
         int var8 = 1 + var1.rand.nextInt(5);
         this.dropXpOnBlockBreak(var1, var2, var3, var4, var8);
      }

   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      if(this.field_150187_a) {
         this.func_150186_m(var1, var2, var3, var4);
      }

   }

   private void func_150186_m(World var1, int var2, int var3, int var4) {
      Random var5 = var1.rand;
      double var6 = 0.0625D;

      for(int var8 = 0; var8 < 6; ++var8) {
         double var9 = (double)((float)var2 + var5.nextFloat());
         double var11 = (double)((float)var3 + var5.nextFloat());
         double var13 = (double)((float)var4 + var5.nextFloat());
         if(var8 == 0 && !var1.getBlock(var2, var3 + 1, var4).isOpaqueCube()) {
            var11 = (double)(var3 + 1) + var6;
         }

         if(var8 == 1 && !var1.getBlock(var2, var3 - 1, var4).isOpaqueCube()) {
            var11 = (double)(var3 + 0) - var6;
         }

         if(var8 == 2 && !var1.getBlock(var2, var3, var4 + 1).isOpaqueCube()) {
            var13 = (double)(var4 + 1) + var6;
         }

         if(var8 == 3 && !var1.getBlock(var2, var3, var4 - 1).isOpaqueCube()) {
            var13 = (double)(var4 + 0) - var6;
         }

         if(var8 == 4 && !var1.getBlock(var2 + 1, var3, var4).isOpaqueCube()) {
            var9 = (double)(var2 + 1) + var6;
         }

         if(var8 == 5 && !var1.getBlock(var2 - 1, var3, var4).isOpaqueCube()) {
            var9 = (double)(var2 + 0) - var6;
         }

         if(var9 < (double)var2 || var9 > (double)(var2 + 1) || var11 < 0.0D || var11 > (double)(var3 + 1) || var13 < (double)var4 || var13 > (double)(var4 + 1)) {
            var1.spawnParticle("reddust", var9, var11, var13, 0.0D, 0.0D, 0.0D);
         }
      }

   }

   protected ItemStack createStackedBlock(int var1) {
      return new ItemStack(Blocks.redstone_ore);
   }
}
