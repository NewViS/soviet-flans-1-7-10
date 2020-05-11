package net.minecraft.item.crafting;

import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class RecipesDyes {

   public void addRecipes(CraftingManager var1) {
      int var2;
      for(var2 = 0; var2 < 16; ++var2) {
         var1.addShapelessRecipe(new ItemStack(Blocks.wool, 1, BlockColored.func_150031_c(var2)), new Object[]{new ItemStack(Items.dye, 1, var2), new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0)});
         var1.addRecipe(new ItemStack(Blocks.stained_hardened_clay, 8, BlockColored.func_150031_c(var2)), new Object[]{"###", "#X#", "###", Character.valueOf('#'), new ItemStack(Blocks.hardened_clay), Character.valueOf('X'), new ItemStack(Items.dye, 1, var2)});
         var1.addRecipe(new ItemStack(Blocks.stained_glass, 8, BlockColored.func_150031_c(var2)), new Object[]{"###", "#X#", "###", Character.valueOf('#'), new ItemStack(Blocks.glass), Character.valueOf('X'), new ItemStack(Items.dye, 1, var2)});
         var1.addRecipe(new ItemStack(Blocks.stained_glass_pane, 16, var2), new Object[]{"###", "###", Character.valueOf('#'), new ItemStack(Blocks.stained_glass, 1, var2)});
      }

      var1.addShapelessRecipe(new ItemStack(Items.dye, 1, 11), new Object[]{new ItemStack(Blocks.yellow_flower, 1, 0)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 1, 1), new Object[]{new ItemStack(Blocks.red_flower, 1, 0)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 3, 15), new Object[]{Items.bone});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 9), new Object[]{new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 15)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 14), new Object[]{new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 11)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 10), new Object[]{new ItemStack(Items.dye, 1, 2), new ItemStack(Items.dye, 1, 15)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 8), new Object[]{new ItemStack(Items.dye, 1, 0), new ItemStack(Items.dye, 1, 15)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 7), new Object[]{new ItemStack(Items.dye, 1, 8), new ItemStack(Items.dye, 1, 15)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 3, 7), new Object[]{new ItemStack(Items.dye, 1, 0), new ItemStack(Items.dye, 1, 15), new ItemStack(Items.dye, 1, 15)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 12), new Object[]{new ItemStack(Items.dye, 1, 4), new ItemStack(Items.dye, 1, 15)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 6), new Object[]{new ItemStack(Items.dye, 1, 4), new ItemStack(Items.dye, 1, 2)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 5), new Object[]{new ItemStack(Items.dye, 1, 4), new ItemStack(Items.dye, 1, 1)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 13), new Object[]{new ItemStack(Items.dye, 1, 5), new ItemStack(Items.dye, 1, 9)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 3, 13), new Object[]{new ItemStack(Items.dye, 1, 4), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 9)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 4, 13), new Object[]{new ItemStack(Items.dye, 1, 4), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 15)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new Object[]{new ItemStack(Blocks.red_flower, 1, 1)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 1, 13), new Object[]{new ItemStack(Blocks.red_flower, 1, 2)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 1, 7), new Object[]{new ItemStack(Blocks.red_flower, 1, 3)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 1, 1), new Object[]{new ItemStack(Blocks.red_flower, 1, 4)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 1, 14), new Object[]{new ItemStack(Blocks.red_flower, 1, 5)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 1, 7), new Object[]{new ItemStack(Blocks.red_flower, 1, 6)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 1, 9), new Object[]{new ItemStack(Blocks.red_flower, 1, 7)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 1, 7), new Object[]{new ItemStack(Blocks.red_flower, 1, 8)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 11), new Object[]{new ItemStack(Blocks.double_plant, 1, 0)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 13), new Object[]{new ItemStack(Blocks.double_plant, 1, 1)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 1), new Object[]{new ItemStack(Blocks.double_plant, 1, 4)});
      var1.addShapelessRecipe(new ItemStack(Items.dye, 2, 9), new Object[]{new ItemStack(Blocks.double_plant, 1, 5)});

      for(var2 = 0; var2 < 16; ++var2) {
         var1.addRecipe(new ItemStack(Blocks.carpet, 3, var2), new Object[]{"##", Character.valueOf('#'), new ItemStack(Blocks.wool, 1, var2)});
      }

   }
}
