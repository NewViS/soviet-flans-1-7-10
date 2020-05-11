package de.ItsAMysterious.mods.reallifemod;

import cpw.mods.fml.common.registry.GameRegistry;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Blocks;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RealLifeMod_Recipes {

   public static void addRecipes() {
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Items.cracker), new Object[]{"SSS", "TTT", "XXX", Character.valueOf('S'), RealLifeMod_Items.salt, Character.valueOf('T'), RealLifeMod_Items.keksteig});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Items.steelAxe), new Object[]{"XKK", "XSK", "XSX", Character.valueOf('S'), Items.stick, Character.valueOf('K'), RealLifeMod_Items.ingotSteel});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Items.chips, 4), new Object[]{"XXX", "XKX", "XXX", Character.valueOf('K'), Items.baked_potato});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Blocks.blastFurnace), new Object[]{"BBB", "BLB", "BCB", Character.valueOf('C'), Items.cauldron, Character.valueOf('L'), Items.lava_bucket, Character.valueOf('B'), Blocks.brick_block});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Blocks.basin), new Object[]{"XIX", "MWM", "XMX", Character.valueOf('I'), Items.iron_ingot, Character.valueOf('M'), RealLifeMod_Blocks.Marmor, Character.valueOf('W'), Items.water_bucket});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Blocks.fireplace), new Object[]{"XSX", "SLS", "RRR", Character.valueOf('S'), Items.stick, Character.valueOf('L'), Blocks.leaves, Character.valueOf('R'), Blocks.stone});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Blocks.toaster), new Object[]{"IXI", "IRI", "WXW", Character.valueOf('I'), Items.iron_ingot, Character.valueOf('R'), Items.redstone, Character.valueOf('W'), Items.stick});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Blocks.PC), new Object[]{"III", "ICR", "ICR", Character.valueOf('I'), Items.iron_ingot, Character.valueOf('R'), Items.redstone, Character.valueOf('C'), RealLifeMod_Items.circuitBrd});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Blocks.pillarB), new Object[]{"XBX", "XBX", "XBX", Character.valueOf('B'), Blocks.brick_block});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Blocks.pillarM), new Object[]{"XMX", "XMX", "XMX", Character.valueOf('M'), RealLifeMod_Blocks.Marmor});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Blocks.pillarI), new Object[]{"XIX", "XIX", "XIX", Character.valueOf('I'), Blocks.iron_block});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Blocks.urinal), new Object[]{"XXM", "MXM", "XMX", Character.valueOf('M'), RealLifeMod_Blocks.Marmor});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Blocks.toilet), new Object[]{"MXM", "MWM", "XMM", Character.valueOf('M'), RealLifeMod_Blocks.Marmor, Character.valueOf('W'), Items.water_bucket});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Blocks.growpot), new Object[]{"CXC", "CXC", "CCC", Character.valueOf('C'), Items.clay_ball});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Blocks.Sideboard), new Object[]{"XXX", "WWW", "SXS", Character.valueOf('W'), Blocks.planks, Character.valueOf('S'), Items.stick});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Items.battleAxe), new Object[]{"XII", "XSI", "XSX", Character.valueOf('S'), RealLifeMod_Items.rawStick, Character.valueOf('I'), Items.iron_ingot});
      GameRegistry.addRecipe(new ItemStack(RealLifeMod_Items.rawStick), new Object[]{"XXX", "XWX", "XWX", Character.valueOf('W'), Blocks.log, Character.valueOf('W'), Blocks.log2});
   }

   public static void addSmeltings() {
      GameRegistry.addSmelting(Blocks.coal_block, new ItemStack(RealLifeMod_Items.coke), 0.0F);
      GameRegistry.addSmelting(Items.bread, new ItemStack(RealLifeMod_Items.toast), 0.0F);
   }
}
