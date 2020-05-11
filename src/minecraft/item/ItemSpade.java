package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item$ToolMaterial;
import net.minecraft.item.ItemTool;

public class ItemSpade extends ItemTool {

   private static final Set field_150916_c = Sets.newHashSet(new Block[]{Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium});


   public ItemSpade(Item$ToolMaterial var1) {
      super(1.0F, var1, field_150916_c);
   }

   public boolean func_150897_b(Block var1) {
      return var1 == Blocks.snow_layer?true:var1 == Blocks.snow;
   }

}
