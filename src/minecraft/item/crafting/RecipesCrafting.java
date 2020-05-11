package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class RecipesCrafting {

   public void addRecipes(CraftingManager var1) {
      var1.addRecipe(new ItemStack(Blocks.chest), new Object[]{"###", "# #", "###", Character.valueOf('#'), Blocks.planks});
      var1.addRecipe(new ItemStack(Blocks.trapped_chest), new Object[]{"#-", Character.valueOf('#'), Blocks.chest, Character.valueOf('-'), Blocks.tripwire_hook});
      var1.addRecipe(new ItemStack(Blocks.ender_chest), new Object[]{"###", "#E#", "###", Character.valueOf('#'), Blocks.obsidian, Character.valueOf('E'), Items.ender_eye});
      var1.addRecipe(new ItemStack(Blocks.furnace), new Object[]{"###", "# #", "###", Character.valueOf('#'), Blocks.cobblestone});
      var1.addRecipe(new ItemStack(Blocks.crafting_table), new Object[]{"##", "##", Character.valueOf('#'), Blocks.planks});
      var1.addRecipe(new ItemStack(Blocks.sandstone), new Object[]{"##", "##", Character.valueOf('#'), new ItemStack(Blocks.sand, 1, 0)});
      var1.addRecipe(new ItemStack(Blocks.sandstone, 4, 2), new Object[]{"##", "##", Character.valueOf('#'), Blocks.sandstone});
      var1.addRecipe(new ItemStack(Blocks.sandstone, 1, 1), new Object[]{"#", "#", Character.valueOf('#'), new ItemStack(Blocks.stone_slab, 1, 1)});
      var1.addRecipe(new ItemStack(Blocks.quartz_block, 1, 1), new Object[]{"#", "#", Character.valueOf('#'), new ItemStack(Blocks.stone_slab, 1, 7)});
      var1.addRecipe(new ItemStack(Blocks.quartz_block, 2, 2), new Object[]{"#", "#", Character.valueOf('#'), new ItemStack(Blocks.quartz_block, 1, 0)});
      var1.addRecipe(new ItemStack(Blocks.stonebrick, 4), new Object[]{"##", "##", Character.valueOf('#'), Blocks.stone});
      var1.addRecipe(new ItemStack(Blocks.iron_bars, 16), new Object[]{"###", "###", Character.valueOf('#'), Items.iron_ingot});
      var1.addRecipe(new ItemStack(Blocks.glass_pane, 16), new Object[]{"###", "###", Character.valueOf('#'), Blocks.glass});
      var1.addRecipe(new ItemStack(Blocks.redstone_lamp, 1), new Object[]{" R ", "RGR", " R ", Character.valueOf('R'), Items.redstone, Character.valueOf('G'), Blocks.glowstone});
      var1.addRecipe(new ItemStack(Blocks.beacon, 1), new Object[]{"GGG", "GSG", "OOO", Character.valueOf('G'), Blocks.glass, Character.valueOf('S'), Items.nether_star, Character.valueOf('O'), Blocks.obsidian});
      var1.addRecipe(new ItemStack(Blocks.nether_brick, 1), new Object[]{"NN", "NN", Character.valueOf('N'), Items.netherbrick});
   }
}
