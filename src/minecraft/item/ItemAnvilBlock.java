package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.item.ItemMultiTexture;

public class ItemAnvilBlock extends ItemMultiTexture {

   public ItemAnvilBlock(Block var1) {
      super(var1, var1, BlockAnvil.anvilDamageNames);
   }

   public int getMetadata(int var1) {
      return var1 << 2;
   }
}
