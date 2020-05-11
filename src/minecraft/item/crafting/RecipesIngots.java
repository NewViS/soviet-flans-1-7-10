package net.minecraft.item.crafting;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class RecipesIngots {

   private Object[][] recipeItems;


   public RecipesIngots() {
      this.recipeItems = new Object[][]{{Blocks.gold_block, new ItemStack(Items.gold_ingot, 9)}, {Blocks.iron_block, new ItemStack(Items.iron_ingot, 9)}, {Blocks.diamond_block, new ItemStack(Items.diamond, 9)}, {Blocks.emerald_block, new ItemStack(Items.emerald, 9)}, {Blocks.lapis_block, new ItemStack(Items.dye, 9, 4)}, {Blocks.redstone_block, new ItemStack(Items.redstone, 9)}, {Blocks.coal_block, new ItemStack(Items.coal, 9, 0)}, {Blocks.hay_block, new ItemStack(Items.wheat, 9)}};
   }

   public void addRecipes(CraftingManager var1) {
      for(int var2 = 0; var2 < this.recipeItems.length; ++var2) {
         Block var3 = (Block)this.recipeItems[var2][0];
         ItemStack var4 = (ItemStack)this.recipeItems[var2][1];
         var1.addRecipe(new ItemStack(var3), new Object[]{"###", "###", "###", Character.valueOf('#'), var4});
         var1.addRecipe(var4, new Object[]{"#", Character.valueOf('#'), var3});
      }

      var1.addRecipe(new ItemStack(Items.gold_ingot), new Object[]{"###", "###", "###", Character.valueOf('#'), Items.gold_nugget});
      var1.addRecipe(new ItemStack(Items.gold_nugget, 9), new Object[]{"#", Character.valueOf('#'), Items.gold_ingot});
   }
}
