package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockLeavesBase extends Block {

   protected boolean field_150121_P;


   protected BlockLeavesBase(Material var1, boolean var2) {
      super(var1);
      this.field_150121_P = var2;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      Block var6 = var1.getBlock(var2, var3, var4);
      return !this.field_150121_P && var6 == this?false:super.shouldSideBeRendered(var1, var2, var3, var4, var5);
   }
}
