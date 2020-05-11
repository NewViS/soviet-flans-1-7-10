package net.minecraft.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ShapedRecipes implements IRecipe {

   private int recipeWidth;
   private int recipeHeight;
   private ItemStack[] recipeItems;
   private ItemStack recipeOutput;
   private boolean field_92101_f;


   public ShapedRecipes(int var1, int var2, ItemStack[] var3, ItemStack var4) {
      this.recipeWidth = var1;
      this.recipeHeight = var2;
      this.recipeItems = var3;
      this.recipeOutput = var4;
   }

   public ItemStack getRecipeOutput() {
      return this.recipeOutput;
   }

   public boolean matches(InventoryCrafting var1, World var2) {
      for(int var3 = 0; var3 <= 3 - this.recipeWidth; ++var3) {
         for(int var4 = 0; var4 <= 3 - this.recipeHeight; ++var4) {
            if(this.checkMatch(var1, var3, var4, true)) {
               return true;
            }

            if(this.checkMatch(var1, var3, var4, false)) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean checkMatch(InventoryCrafting var1, int var2, int var3, boolean var4) {
      for(int var5 = 0; var5 < 3; ++var5) {
         for(int var6 = 0; var6 < 3; ++var6) {
            int var7 = var5 - var2;
            int var8 = var6 - var3;
            ItemStack var9 = null;
            if(var7 >= 0 && var8 >= 0 && var7 < this.recipeWidth && var8 < this.recipeHeight) {
               if(var4) {
                  var9 = this.recipeItems[this.recipeWidth - var7 - 1 + var8 * this.recipeWidth];
               } else {
                  var9 = this.recipeItems[var7 + var8 * this.recipeWidth];
               }
            }

            ItemStack var10 = var1.getStackInRowAndColumn(var5, var6);
            if(var10 != null || var9 != null) {
               if(var10 == null && var9 != null || var10 != null && var9 == null) {
                  return false;
               }

               if(var9.getItem() != var10.getItem()) {
                  return false;
               }

               if(var9.getItemDamage() != 32767 && var9.getItemDamage() != var10.getItemDamage()) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   public ItemStack getCraftingResult(InventoryCrafting var1) {
      ItemStack var2 = this.getRecipeOutput().copy();
      if(this.field_92101_f) {
         for(int var3 = 0; var3 < var1.getSizeInventory(); ++var3) {
            ItemStack var4 = var1.getStackInSlot(var3);
            if(var4 != null && var4.hasTagCompound()) {
               var2.setTagCompound((NBTTagCompound)var4.stackTagCompound.copy());
            }
         }
      }

      return var2;
   }

   public int getRecipeSize() {
      return this.recipeWidth * this.recipeHeight;
   }

   public ShapedRecipes func_92100_c() {
      this.field_92101_f = true;
      return this;
   }
}
