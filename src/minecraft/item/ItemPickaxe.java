package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item$ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemPickaxe extends ItemTool {

   private static final Set field_150915_c = Sets.newHashSet(new Block[]{Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});


   protected ItemPickaxe(Item$ToolMaterial var1) {
      super(2.0F, var1, field_150915_c);
   }

   public boolean func_150897_b(Block var1) {
      return var1 == Blocks.obsidian?super.toolMaterial.getHarvestLevel() == 3:(var1 != Blocks.diamond_block && var1 != Blocks.diamond_ore?(var1 != Blocks.emerald_ore && var1 != Blocks.emerald_block?(var1 != Blocks.gold_block && var1 != Blocks.gold_ore?(var1 != Blocks.iron_block && var1 != Blocks.iron_ore?(var1 != Blocks.lapis_block && var1 != Blocks.lapis_ore?(var1 != Blocks.redstone_ore && var1 != Blocks.lit_redstone_ore?(var1.getMaterial() == Material.rock?true:(var1.getMaterial() == Material.iron?true:var1.getMaterial() == Material.anvil)):super.toolMaterial.getHarvestLevel() >= 2):super.toolMaterial.getHarvestLevel() >= 1):super.toolMaterial.getHarvestLevel() >= 1):super.toolMaterial.getHarvestLevel() >= 2):super.toolMaterial.getHarvestLevel() >= 2):super.toolMaterial.getHarvestLevel() >= 2);
   }

   public float func_150893_a(ItemStack var1, Block var2) {
      return var2.getMaterial() != Material.iron && var2.getMaterial() != Material.anvil && var2.getMaterial() != Material.rock?super.func_150893_a(var1, var2):super.efficiencyOnProperMaterial;
   }

}
