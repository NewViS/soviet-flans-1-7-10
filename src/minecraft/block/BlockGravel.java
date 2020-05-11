package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.BlockFalling;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockGravel extends BlockFalling {

   public Item getItemDropped(int var1, Random var2, int var3) {
      if(var3 > 3) {
         var3 = 3;
      }

      return var2.nextInt(10 - var3 * 3) == 0?Items.flint:Item.getItemFromBlock(this);
   }
}
