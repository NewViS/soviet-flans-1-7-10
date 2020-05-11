package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public abstract class StructureComponent$BlockSelector {

   protected Block field_151562_a;
   protected int selectedBlockMetaData;


   protected StructureComponent$BlockSelector() {
      this.field_151562_a = Blocks.air;
   }

   public abstract void selectBlocks(Random var1, int var2, int var3, int var4, boolean var5);

   public Block func_151561_a() {
      return this.field_151562_a;
   }

   public int getSelectedBlockMetaData() {
      return this.selectedBlockMetaData;
   }
}
