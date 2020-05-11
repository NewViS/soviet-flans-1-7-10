package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;

public class ItemDoublePlant extends ItemMultiTexture {

   public ItemDoublePlant(Block var1, BlockDoublePlant var2, String[] var3) {
      super(var1, var2, var3);
   }

   public IIcon getIconFromDamage(int var1) {
      return BlockDoublePlant.func_149890_d(var1) == 0?((BlockDoublePlant)super.field_150941_b).sunflowerIcons[0]:((BlockDoublePlant)super.field_150941_b).func_149888_a(true, var1);
   }

   public int getColorFromItemStack(ItemStack var1, int var2) {
      int var3 = BlockDoublePlant.func_149890_d(var1.getItemDamage());
      return var3 != 2 && var3 != 3?super.getColorFromItemStack(var1, var2):ColorizerGrass.getGrassColor(0.5D, 1.0D);
   }
}
