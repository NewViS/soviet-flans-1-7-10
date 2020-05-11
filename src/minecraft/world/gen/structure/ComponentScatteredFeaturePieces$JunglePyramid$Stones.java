package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$1;
import net.minecraft.world.gen.structure.StructureComponent$BlockSelector;

class ComponentScatteredFeaturePieces$JunglePyramid$Stones extends StructureComponent$BlockSelector {

   private ComponentScatteredFeaturePieces$JunglePyramid$Stones() {}

   public void selectBlocks(Random var1, int var2, int var3, int var4, boolean var5) {
      if(var1.nextFloat() < 0.4F) {
         super.field_151562_a = Blocks.cobblestone;
      } else {
         super.field_151562_a = Blocks.mossy_cobblestone;
      }

   }

   // $FF: synthetic method
   ComponentScatteredFeaturePieces$JunglePyramid$Stones(ComponentScatteredFeaturePieces$1 var1) {
      this();
   }
}
