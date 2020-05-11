package net.minecraft.util;

import java.util.Random;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandom$Item;

public class WeightedRandomChestContent extends WeightedRandom$Item {

   private ItemStack theItemId;
   private int theMinimumChanceToGenerateItem;
   private int theMaximumChanceToGenerateItem;


   public WeightedRandomChestContent(Item var1, int var2, int var3, int var4, int var5) {
      super(var5);
      this.theItemId = new ItemStack(var1, 1, var2);
      this.theMinimumChanceToGenerateItem = var3;
      this.theMaximumChanceToGenerateItem = var4;
   }

   public WeightedRandomChestContent(ItemStack var1, int var2, int var3, int var4) {
      super(var4);
      this.theItemId = var1;
      this.theMinimumChanceToGenerateItem = var2;
      this.theMaximumChanceToGenerateItem = var3;
   }

   public static void generateChestContents(Random var0, WeightedRandomChestContent[] var1, IInventory var2, int var3) {
      for(int var4 = 0; var4 < var3; ++var4) {
         WeightedRandomChestContent var5 = (WeightedRandomChestContent)WeightedRandom.getRandomItem(var0, (WeightedRandom$Item[])var1);
         int var6 = var5.theMinimumChanceToGenerateItem + var0.nextInt(var5.theMaximumChanceToGenerateItem - var5.theMinimumChanceToGenerateItem + 1);
         if(var5.theItemId.getMaxStackSize() >= var6) {
            ItemStack var7 = var5.theItemId.copy();
            var7.stackSize = var6;
            var2.setInventorySlotContents(var0.nextInt(var2.getSizeInventory()), var7);
         } else {
            for(int var9 = 0; var9 < var6; ++var9) {
               ItemStack var8 = var5.theItemId.copy();
               var8.stackSize = 1;
               var2.setInventorySlotContents(var0.nextInt(var2.getSizeInventory()), var8);
            }
         }
      }

   }

   public static void generateDispenserContents(Random var0, WeightedRandomChestContent[] var1, TileEntityDispenser var2, int var3) {
      for(int var4 = 0; var4 < var3; ++var4) {
         WeightedRandomChestContent var5 = (WeightedRandomChestContent)WeightedRandom.getRandomItem(var0, (WeightedRandom$Item[])var1);
         int var6 = var5.theMinimumChanceToGenerateItem + var0.nextInt(var5.theMaximumChanceToGenerateItem - var5.theMinimumChanceToGenerateItem + 1);
         if(var5.theItemId.getMaxStackSize() >= var6) {
            ItemStack var7 = var5.theItemId.copy();
            var7.stackSize = var6;
            var2.setInventorySlotContents(var0.nextInt(var2.getSizeInventory()), var7);
         } else {
            for(int var9 = 0; var9 < var6; ++var9) {
               ItemStack var8 = var5.theItemId.copy();
               var8.stackSize = 1;
               var2.setInventorySlotContents(var0.nextInt(var2.getSizeInventory()), var8);
            }
         }
      }

   }

   public static WeightedRandomChestContent[] func_92080_a(WeightedRandomChestContent[] var0, WeightedRandomChestContent ... var1) {
      WeightedRandomChestContent[] var2 = new WeightedRandomChestContent[var0.length + var1.length];
      int var3 = 0;

      for(int var4 = 0; var4 < var0.length; ++var4) {
         var2[var3++] = var0[var4];
      }

      WeightedRandomChestContent[] var8 = var1;
      int var5 = var1.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         WeightedRandomChestContent var7 = var8[var6];
         var2[var3++] = var7;
      }

      return var2;
   }
}
