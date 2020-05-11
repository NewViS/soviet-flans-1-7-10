package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.StructureComponent$BlockSelector;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$1;

class StructureStrongholdPieces$Stones extends StructureComponent$BlockSelector {

   private StructureStrongholdPieces$Stones() {}

   public void selectBlocks(Random var1, int var2, int var3, int var4, boolean var5) {
      if(var5) {
         super.field_151562_a = Blocks.stonebrick;
         float var6 = var1.nextFloat();
         if(var6 < 0.2F) {
            super.selectedBlockMetaData = 2;
         } else if(var6 < 0.5F) {
            super.selectedBlockMetaData = 1;
         } else if(var6 < 0.55F) {
            super.field_151562_a = Blocks.monster_egg;
            super.selectedBlockMetaData = 2;
         } else {
            super.selectedBlockMetaData = 0;
         }
      } else {
         super.field_151562_a = Blocks.air;
         super.selectedBlockMetaData = 0;
      }

   }

   // $FF: synthetic method
   StructureStrongholdPieces$Stones(StructureStrongholdPieces$1 var1) {
      this();
   }
}
