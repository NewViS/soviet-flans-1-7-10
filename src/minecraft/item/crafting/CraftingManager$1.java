package net.minecraft.item.crafting;

import java.util.Comparator;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

class CraftingManager$1 implements Comparator {

   // $FF: synthetic field
   final CraftingManager craftingManager;


   CraftingManager$1(CraftingManager var1) {
      this.craftingManager = var1;
   }

   public int compare(IRecipe var1, IRecipe var2) {
      return var1 instanceof ShapelessRecipes && var2 instanceof ShapedRecipes?1:(var2 instanceof ShapelessRecipes && var1 instanceof ShapedRecipes?-1:(var2.getRecipeSize() < var1.getRecipeSize()?-1:(var2.getRecipeSize() > var1.getRecipeSize()?1:0)));
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.compare((IRecipe)var1, (IRecipe)var2);
   }
}
