package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

public class ItemBlockWithMetadata extends ItemBlock {

   private Block field_150950_b;


   public ItemBlockWithMetadata(Block var1, Block var2) {
      super(var1);
      this.field_150950_b = var2;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public IIcon getIconFromDamage(int var1) {
      return this.field_150950_b.getIcon(2, var1);
   }

   public int getMetadata(int var1) {
      return var1;
   }
}
