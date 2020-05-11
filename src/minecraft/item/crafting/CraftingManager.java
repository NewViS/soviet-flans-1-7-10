package net.minecraft.item.crafting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager$1;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeBookCloning;
import net.minecraft.item.crafting.RecipeFireworks;
import net.minecraft.item.crafting.RecipesArmor;
import net.minecraft.item.crafting.RecipesArmorDyes;
import net.minecraft.item.crafting.RecipesCrafting;
import net.minecraft.item.crafting.RecipesDyes;
import net.minecraft.item.crafting.RecipesFood;
import net.minecraft.item.crafting.RecipesIngots;
import net.minecraft.item.crafting.RecipesMapCloning;
import net.minecraft.item.crafting.RecipesMapExtending;
import net.minecraft.item.crafting.RecipesTools;
import net.minecraft.item.crafting.RecipesWeapons;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

public class CraftingManager {

   private static final CraftingManager instance = new CraftingManager();
   private List recipes = new ArrayList();


   public static final CraftingManager getInstance() {
      return instance;
   }

   private CraftingManager() {
      (new RecipesTools()).addRecipes(this);
      (new RecipesWeapons()).addRecipes(this);
      (new RecipesIngots()).addRecipes(this);
      (new RecipesFood()).addRecipes(this);
      (new RecipesCrafting()).addRecipes(this);
      (new RecipesArmor()).addRecipes(this);
      (new RecipesDyes()).addRecipes(this);
      this.recipes.add(new RecipesArmorDyes());
      this.recipes.add(new RecipeBookCloning());
      this.recipes.add(new RecipesMapCloning());
      this.recipes.add(new RecipesMapExtending());
      this.recipes.add(new RecipeFireworks());
      this.addRecipe(new ItemStack(Items.paper, 3), new Object[]{"###", Character.valueOf('#'), Items.reeds});
      this.addShapelessRecipe(new ItemStack(Items.book, 1), new Object[]{Items.paper, Items.paper, Items.paper, Items.leather});
      this.addShapelessRecipe(new ItemStack(Items.writable_book, 1), new Object[]{Items.book, new ItemStack(Items.dye, 1, 0), Items.feather});
      this.addRecipe(new ItemStack(Blocks.fence, 2), new Object[]{"###", "###", Character.valueOf('#'), Items.stick});
      this.addRecipe(new ItemStack(Blocks.cobblestone_wall, 6, 0), new Object[]{"###", "###", Character.valueOf('#'), Blocks.cobblestone});
      this.addRecipe(new ItemStack(Blocks.cobblestone_wall, 6, 1), new Object[]{"###", "###", Character.valueOf('#'), Blocks.mossy_cobblestone});
      this.addRecipe(new ItemStack(Blocks.nether_brick_fence, 6), new Object[]{"###", "###", Character.valueOf('#'), Blocks.nether_brick});
      this.addRecipe(new ItemStack(Blocks.fence_gate, 1), new Object[]{"#W#", "#W#", Character.valueOf('#'), Items.stick, Character.valueOf('W'), Blocks.planks});
      this.addRecipe(new ItemStack(Blocks.jukebox, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Blocks.planks, Character.valueOf('X'), Items.diamond});
      this.addRecipe(new ItemStack(Items.lead, 2), new Object[]{"~~ ", "~O ", "  ~", Character.valueOf('~'), Items.string, Character.valueOf('O'), Items.slime_ball});
      this.addRecipe(new ItemStack(Blocks.noteblock, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Blocks.planks, Character.valueOf('X'), Items.redstone});
      this.addRecipe(new ItemStack(Blocks.bookshelf, 1), new Object[]{"###", "XXX", "###", Character.valueOf('#'), Blocks.planks, Character.valueOf('X'), Items.book});
      this.addRecipe(new ItemStack(Blocks.snow, 1), new Object[]{"##", "##", Character.valueOf('#'), Items.snowball});
      this.addRecipe(new ItemStack(Blocks.snow_layer, 6), new Object[]{"###", Character.valueOf('#'), Blocks.snow});
      this.addRecipe(new ItemStack(Blocks.clay, 1), new Object[]{"##", "##", Character.valueOf('#'), Items.clay_ball});
      this.addRecipe(new ItemStack(Blocks.brick_block, 1), new Object[]{"##", "##", Character.valueOf('#'), Items.brick});
      this.addRecipe(new ItemStack(Blocks.glowstone, 1), new Object[]{"##", "##", Character.valueOf('#'), Items.glowstone_dust});
      this.addRecipe(new ItemStack(Blocks.quartz_block, 1), new Object[]{"##", "##", Character.valueOf('#'), Items.quartz});
      this.addRecipe(new ItemStack(Blocks.wool, 1), new Object[]{"##", "##", Character.valueOf('#'), Items.string});
      this.addRecipe(new ItemStack(Blocks.tnt, 1), new Object[]{"X#X", "#X#", "X#X", Character.valueOf('X'), Items.gunpowder, Character.valueOf('#'), Blocks.sand});
      this.addRecipe(new ItemStack(Blocks.stone_slab, 6, 3), new Object[]{"###", Character.valueOf('#'), Blocks.cobblestone});
      this.addRecipe(new ItemStack(Blocks.stone_slab, 6, 0), new Object[]{"###", Character.valueOf('#'), Blocks.stone});
      this.addRecipe(new ItemStack(Blocks.stone_slab, 6, 1), new Object[]{"###", Character.valueOf('#'), Blocks.sandstone});
      this.addRecipe(new ItemStack(Blocks.stone_slab, 6, 4), new Object[]{"###", Character.valueOf('#'), Blocks.brick_block});
      this.addRecipe(new ItemStack(Blocks.stone_slab, 6, 5), new Object[]{"###", Character.valueOf('#'), Blocks.stonebrick});
      this.addRecipe(new ItemStack(Blocks.stone_slab, 6, 6), new Object[]{"###", Character.valueOf('#'), Blocks.nether_brick});
      this.addRecipe(new ItemStack(Blocks.stone_slab, 6, 7), new Object[]{"###", Character.valueOf('#'), Blocks.quartz_block});
      this.addRecipe(new ItemStack(Blocks.wooden_slab, 6, 0), new Object[]{"###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 0)});
      this.addRecipe(new ItemStack(Blocks.wooden_slab, 6, 2), new Object[]{"###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 2)});
      this.addRecipe(new ItemStack(Blocks.wooden_slab, 6, 1), new Object[]{"###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 1)});
      this.addRecipe(new ItemStack(Blocks.wooden_slab, 6, 3), new Object[]{"###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 3)});
      this.addRecipe(new ItemStack(Blocks.wooden_slab, 6, 4), new Object[]{"###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 4)});
      this.addRecipe(new ItemStack(Blocks.wooden_slab, 6, 5), new Object[]{"###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 5)});
      this.addRecipe(new ItemStack(Blocks.ladder, 3), new Object[]{"# #", "###", "# #", Character.valueOf('#'), Items.stick});
      this.addRecipe(new ItemStack(Items.wooden_door, 1), new Object[]{"##", "##", "##", Character.valueOf('#'), Blocks.planks});
      this.addRecipe(new ItemStack(Blocks.trapdoor, 2), new Object[]{"###", "###", Character.valueOf('#'), Blocks.planks});
      this.addRecipe(new ItemStack(Items.iron_door, 1), new Object[]{"##", "##", "##", Character.valueOf('#'), Items.iron_ingot});
      this.addRecipe(new ItemStack(Items.sign, 3), new Object[]{"###", "###", " X ", Character.valueOf('#'), Blocks.planks, Character.valueOf('X'), Items.stick});
      this.addRecipe(new ItemStack(Items.cake, 1), new Object[]{"AAA", "BEB", "CCC", Character.valueOf('A'), Items.milk_bucket, Character.valueOf('B'), Items.sugar, Character.valueOf('C'), Items.wheat, Character.valueOf('E'), Items.egg});
      this.addRecipe(new ItemStack(Items.sugar, 1), new Object[]{"#", Character.valueOf('#'), Items.reeds});
      this.addRecipe(new ItemStack(Blocks.planks, 4, 0), new Object[]{"#", Character.valueOf('#'), new ItemStack(Blocks.log, 1, 0)});
      this.addRecipe(new ItemStack(Blocks.planks, 4, 1), new Object[]{"#", Character.valueOf('#'), new ItemStack(Blocks.log, 1, 1)});
      this.addRecipe(new ItemStack(Blocks.planks, 4, 2), new Object[]{"#", Character.valueOf('#'), new ItemStack(Blocks.log, 1, 2)});
      this.addRecipe(new ItemStack(Blocks.planks, 4, 3), new Object[]{"#", Character.valueOf('#'), new ItemStack(Blocks.log, 1, 3)});
      this.addRecipe(new ItemStack(Blocks.planks, 4, 4), new Object[]{"#", Character.valueOf('#'), new ItemStack(Blocks.log2, 1, 0)});
      this.addRecipe(new ItemStack(Blocks.planks, 4, 5), new Object[]{"#", Character.valueOf('#'), new ItemStack(Blocks.log2, 1, 1)});
      this.addRecipe(new ItemStack(Items.stick, 4), new Object[]{"#", "#", Character.valueOf('#'), Blocks.planks});
      this.addRecipe(new ItemStack(Blocks.torch, 4), new Object[]{"X", "#", Character.valueOf('X'), Items.coal, Character.valueOf('#'), Items.stick});
      this.addRecipe(new ItemStack(Blocks.torch, 4), new Object[]{"X", "#", Character.valueOf('X'), new ItemStack(Items.coal, 1, 1), Character.valueOf('#'), Items.stick});
      this.addRecipe(new ItemStack(Items.bowl, 4), new Object[]{"# #", " # ", Character.valueOf('#'), Blocks.planks});
      this.addRecipe(new ItemStack(Items.glass_bottle, 3), new Object[]{"# #", " # ", Character.valueOf('#'), Blocks.glass});
      this.addRecipe(new ItemStack(Blocks.rail, 16), new Object[]{"X X", "X#X", "X X", Character.valueOf('X'), Items.iron_ingot, Character.valueOf('#'), Items.stick});
      this.addRecipe(new ItemStack(Blocks.golden_rail, 6), new Object[]{"X X", "X#X", "XRX", Character.valueOf('X'), Items.gold_ingot, Character.valueOf('R'), Items.redstone, Character.valueOf('#'), Items.stick});
      this.addRecipe(new ItemStack(Blocks.activator_rail, 6), new Object[]{"XSX", "X#X", "XSX", Character.valueOf('X'), Items.iron_ingot, Character.valueOf('#'), Blocks.redstone_torch, Character.valueOf('S'), Items.stick});
      this.addRecipe(new ItemStack(Blocks.detector_rail, 6), new Object[]{"X X", "X#X", "XRX", Character.valueOf('X'), Items.iron_ingot, Character.valueOf('R'), Items.redstone, Character.valueOf('#'), Blocks.stone_pressure_plate});
      this.addRecipe(new ItemStack(Items.minecart, 1), new Object[]{"# #", "###", Character.valueOf('#'), Items.iron_ingot});
      this.addRecipe(new ItemStack(Items.cauldron, 1), new Object[]{"# #", "# #", "###", Character.valueOf('#'), Items.iron_ingot});
      this.addRecipe(new ItemStack(Items.brewing_stand, 1), new Object[]{" B ", "###", Character.valueOf('#'), Blocks.cobblestone, Character.valueOf('B'), Items.blaze_rod});
      this.addRecipe(new ItemStack(Blocks.lit_pumpkin, 1), new Object[]{"A", "B", Character.valueOf('A'), Blocks.pumpkin, Character.valueOf('B'), Blocks.torch});
      this.addRecipe(new ItemStack(Items.chest_minecart, 1), new Object[]{"A", "B", Character.valueOf('A'), Blocks.chest, Character.valueOf('B'), Items.minecart});
      this.addRecipe(new ItemStack(Items.furnace_minecart, 1), new Object[]{"A", "B", Character.valueOf('A'), Blocks.furnace, Character.valueOf('B'), Items.minecart});
      this.addRecipe(new ItemStack(Items.tnt_minecart, 1), new Object[]{"A", "B", Character.valueOf('A'), Blocks.tnt, Character.valueOf('B'), Items.minecart});
      this.addRecipe(new ItemStack(Items.hopper_minecart, 1), new Object[]{"A", "B", Character.valueOf('A'), Blocks.hopper, Character.valueOf('B'), Items.minecart});
      this.addRecipe(new ItemStack(Items.boat, 1), new Object[]{"# #", "###", Character.valueOf('#'), Blocks.planks});
      this.addRecipe(new ItemStack(Items.bucket, 1), new Object[]{"# #", " # ", Character.valueOf('#'), Items.iron_ingot});
      this.addRecipe(new ItemStack(Items.flower_pot, 1), new Object[]{"# #", " # ", Character.valueOf('#'), Items.brick});
      this.addShapelessRecipe(new ItemStack(Items.flint_and_steel, 1), new Object[]{new ItemStack(Items.iron_ingot, 1), new ItemStack(Items.flint, 1)});
      this.addRecipe(new ItemStack(Items.bread, 1), new Object[]{"###", Character.valueOf('#'), Items.wheat});
      this.addRecipe(new ItemStack(Blocks.oak_stairs, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 0)});
      this.addRecipe(new ItemStack(Blocks.birch_stairs, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 2)});
      this.addRecipe(new ItemStack(Blocks.spruce_stairs, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 1)});
      this.addRecipe(new ItemStack(Blocks.jungle_stairs, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 3)});
      this.addRecipe(new ItemStack(Blocks.acacia_stairs, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 4)});
      this.addRecipe(new ItemStack(Blocks.dark_oak_stairs, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 5)});
      this.addRecipe(new ItemStack(Items.fishing_rod, 1), new Object[]{"  #", " #X", "# X", Character.valueOf('#'), Items.stick, Character.valueOf('X'), Items.string});
      this.addRecipe(new ItemStack(Items.carrot_on_a_stick, 1), new Object[]{"# ", " X", Character.valueOf('#'), Items.fishing_rod, Character.valueOf('X'), Items.carrot}).func_92100_c();
      this.addRecipe(new ItemStack(Blocks.stone_stairs, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Blocks.cobblestone});
      this.addRecipe(new ItemStack(Blocks.brick_stairs, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Blocks.brick_block});
      this.addRecipe(new ItemStack(Blocks.stone_brick_stairs, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Blocks.stonebrick});
      this.addRecipe(new ItemStack(Blocks.nether_brick_stairs, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Blocks.nether_brick});
      this.addRecipe(new ItemStack(Blocks.sandstone_stairs, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Blocks.sandstone});
      this.addRecipe(new ItemStack(Blocks.quartz_stairs, 4), new Object[]{"#  ", "## ", "###", Character.valueOf('#'), Blocks.quartz_block});
      this.addRecipe(new ItemStack(Items.painting, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Items.stick, Character.valueOf('X'), Blocks.wool});
      this.addRecipe(new ItemStack(Items.item_frame, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Items.stick, Character.valueOf('X'), Items.leather});
      this.addRecipe(new ItemStack(Items.golden_apple, 1, 0), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Items.gold_ingot, Character.valueOf('X'), Items.apple});
      this.addRecipe(new ItemStack(Items.golden_apple, 1, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Blocks.gold_block, Character.valueOf('X'), Items.apple});
      this.addRecipe(new ItemStack(Items.golden_carrot, 1, 0), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Items.gold_nugget, Character.valueOf('X'), Items.carrot});
      this.addRecipe(new ItemStack(Items.speckled_melon, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Items.gold_nugget, Character.valueOf('X'), Items.melon});
      this.addRecipe(new ItemStack(Blocks.lever, 1), new Object[]{"X", "#", Character.valueOf('#'), Blocks.cobblestone, Character.valueOf('X'), Items.stick});
      this.addRecipe(new ItemStack(Blocks.tripwire_hook, 2), new Object[]{"I", "S", "#", Character.valueOf('#'), Blocks.planks, Character.valueOf('S'), Items.stick, Character.valueOf('I'), Items.iron_ingot});
      this.addRecipe(new ItemStack(Blocks.redstone_torch, 1), new Object[]{"X", "#", Character.valueOf('#'), Items.stick, Character.valueOf('X'), Items.redstone});
      this.addRecipe(new ItemStack(Items.repeater, 1), new Object[]{"#X#", "III", Character.valueOf('#'), Blocks.redstone_torch, Character.valueOf('X'), Items.redstone, Character.valueOf('I'), Blocks.stone});
      this.addRecipe(new ItemStack(Items.comparator, 1), new Object[]{" # ", "#X#", "III", Character.valueOf('#'), Blocks.redstone_torch, Character.valueOf('X'), Items.quartz, Character.valueOf('I'), Blocks.stone});
      this.addRecipe(new ItemStack(Items.clock, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Items.gold_ingot, Character.valueOf('X'), Items.redstone});
      this.addRecipe(new ItemStack(Items.compass, 1), new Object[]{" # ", "#X#", " # ", Character.valueOf('#'), Items.iron_ingot, Character.valueOf('X'), Items.redstone});
      this.addRecipe(new ItemStack(Items.map, 1), new Object[]{"###", "#X#", "###", Character.valueOf('#'), Items.paper, Character.valueOf('X'), Items.compass});
      this.addRecipe(new ItemStack(Blocks.stone_button, 1), new Object[]{"#", Character.valueOf('#'), Blocks.stone});
      this.addRecipe(new ItemStack(Blocks.wooden_button, 1), new Object[]{"#", Character.valueOf('#'), Blocks.planks});
      this.addRecipe(new ItemStack(Blocks.stone_pressure_plate, 1), new Object[]{"##", Character.valueOf('#'), Blocks.stone});
      this.addRecipe(new ItemStack(Blocks.wooden_pressure_plate, 1), new Object[]{"##", Character.valueOf('#'), Blocks.planks});
      this.addRecipe(new ItemStack(Blocks.heavy_weighted_pressure_plate, 1), new Object[]{"##", Character.valueOf('#'), Items.iron_ingot});
      this.addRecipe(new ItemStack(Blocks.light_weighted_pressure_plate, 1), new Object[]{"##", Character.valueOf('#'), Items.gold_ingot});
      this.addRecipe(new ItemStack(Blocks.dispenser, 1), new Object[]{"###", "#X#", "#R#", Character.valueOf('#'), Blocks.cobblestone, Character.valueOf('X'), Items.bow, Character.valueOf('R'), Items.redstone});
      this.addRecipe(new ItemStack(Blocks.dropper, 1), new Object[]{"###", "# #", "#R#", Character.valueOf('#'), Blocks.cobblestone, Character.valueOf('R'), Items.redstone});
      this.addRecipe(new ItemStack(Blocks.piston, 1), new Object[]{"TTT", "#X#", "#R#", Character.valueOf('#'), Blocks.cobblestone, Character.valueOf('X'), Items.iron_ingot, Character.valueOf('R'), Items.redstone, Character.valueOf('T'), Blocks.planks});
      this.addRecipe(new ItemStack(Blocks.sticky_piston, 1), new Object[]{"S", "P", Character.valueOf('S'), Items.slime_ball, Character.valueOf('P'), Blocks.piston});
      this.addRecipe(new ItemStack(Items.bed, 1), new Object[]{"###", "XXX", Character.valueOf('#'), Blocks.wool, Character.valueOf('X'), Blocks.planks});
      this.addRecipe(new ItemStack(Blocks.enchanting_table, 1), new Object[]{" B ", "D#D", "###", Character.valueOf('#'), Blocks.obsidian, Character.valueOf('B'), Items.book, Character.valueOf('D'), Items.diamond});
      this.addRecipe(new ItemStack(Blocks.anvil, 1), new Object[]{"III", " i ", "iii", Character.valueOf('I'), Blocks.iron_block, Character.valueOf('i'), Items.iron_ingot});
      this.addShapelessRecipe(new ItemStack(Items.ender_eye, 1), new Object[]{Items.ender_pearl, Items.blaze_powder});
      this.addShapelessRecipe(new ItemStack(Items.fire_charge, 3), new Object[]{Items.gunpowder, Items.blaze_powder, Items.coal});
      this.addShapelessRecipe(new ItemStack(Items.fire_charge, 3), new Object[]{Items.gunpowder, Items.blaze_powder, new ItemStack(Items.coal, 1, 1)});
      this.addRecipe(new ItemStack(Blocks.daylight_detector), new Object[]{"GGG", "QQQ", "WWW", Character.valueOf('G'), Blocks.glass, Character.valueOf('Q'), Items.quartz, Character.valueOf('W'), Blocks.wooden_slab});
      this.addRecipe(new ItemStack(Blocks.hopper), new Object[]{"I I", "ICI", " I ", Character.valueOf('I'), Items.iron_ingot, Character.valueOf('C'), Blocks.chest});
      Collections.sort(this.recipes, new CraftingManager$1(this));
   }

   ShapedRecipes addRecipe(ItemStack var1, Object ... var2) {
      String var3 = "";
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      if(var2[var4] instanceof String[]) {
         String[] var7 = (String[])((String[])var2[var4++]);

         for(int var8 = 0; var8 < var7.length; ++var8) {
            String var9 = var7[var8];
            ++var6;
            var5 = var9.length();
            var3 = var3 + var9;
         }
      } else {
         while(var2[var4] instanceof String) {
            String var11 = (String)var2[var4++];
            ++var6;
            var5 = var11.length();
            var3 = var3 + var11;
         }
      }

      HashMap var12;
      for(var12 = new HashMap(); var4 < var2.length; var4 += 2) {
         Character var13 = (Character)var2[var4];
         ItemStack var15 = null;
         if(var2[var4 + 1] instanceof Item) {
            var15 = new ItemStack((Item)var2[var4 + 1]);
         } else if(var2[var4 + 1] instanceof Block) {
            var15 = new ItemStack((Block)var2[var4 + 1], 1, 32767);
         } else if(var2[var4 + 1] instanceof ItemStack) {
            var15 = (ItemStack)var2[var4 + 1];
         }

         var12.put(var13, var15);
      }

      ItemStack[] var14 = new ItemStack[var5 * var6];

      for(int var16 = 0; var16 < var5 * var6; ++var16) {
         char var10 = var3.charAt(var16);
         if(var12.containsKey(Character.valueOf(var10))) {
            var14[var16] = ((ItemStack)var12.get(Character.valueOf(var10))).copy();
         } else {
            var14[var16] = null;
         }
      }

      ShapedRecipes var17 = new ShapedRecipes(var5, var6, var14, var1);
      this.recipes.add(var17);
      return var17;
   }

   void addShapelessRecipe(ItemStack var1, Object ... var2) {
      ArrayList var3 = new ArrayList();
      Object[] var4 = var2;
      int var5 = var2.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         Object var7 = var4[var6];
         if(var7 instanceof ItemStack) {
            var3.add(((ItemStack)var7).copy());
         } else if(var7 instanceof Item) {
            var3.add(new ItemStack((Item)var7));
         } else {
            if(!(var7 instanceof Block)) {
               throw new RuntimeException("Invalid shapeless recipy!");
            }

            var3.add(new ItemStack((Block)var7));
         }
      }

      this.recipes.add(new ShapelessRecipes(var1, var3));
   }

   public ItemStack findMatchingRecipe(InventoryCrafting var1, World var2) {
      int var3 = 0;
      ItemStack var4 = null;
      ItemStack var5 = null;

      int var6;
      for(var6 = 0; var6 < var1.getSizeInventory(); ++var6) {
         ItemStack var7 = var1.getStackInSlot(var6);
         if(var7 != null) {
            if(var3 == 0) {
               var4 = var7;
            }

            if(var3 == 1) {
               var5 = var7;
            }

            ++var3;
         }
      }

      if(var3 == 2 && var4.getItem() == var5.getItem() && var4.stackSize == 1 && var5.stackSize == 1 && var4.getItem().isDamageable()) {
         Item var11 = var4.getItem();
         int var13 = var11.getMaxDamage() - var4.getItemDamageForDisplay();
         int var8 = var11.getMaxDamage() - var5.getItemDamageForDisplay();
         int var9 = var13 + var8 + var11.getMaxDamage() * 5 / 100;
         int var10 = var11.getMaxDamage() - var9;
         if(var10 < 0) {
            var10 = 0;
         }

         return new ItemStack(var4.getItem(), 1, var10);
      } else {
         for(var6 = 0; var6 < this.recipes.size(); ++var6) {
            IRecipe var12 = (IRecipe)this.recipes.get(var6);
            if(var12.matches(var1, var2)) {
               return var12.getCraftingResult(var1);
            }
         }

         return null;
      }
   }

   public List getRecipeList() {
      return this.recipes;
   }

}
