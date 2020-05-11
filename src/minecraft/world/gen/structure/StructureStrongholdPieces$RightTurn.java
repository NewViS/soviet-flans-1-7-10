package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$LeftTurn;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs2;

public class StructureStrongholdPieces$RightTurn extends StructureStrongholdPieces$LeftTurn {

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      if(super.coordBaseMode != 2 && super.coordBaseMode != 3) {
         this.getNextComponentX((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
      } else {
         this.getNextComponentZ((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
      }

   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.isLiquidInStructureBoundingBox(var1, var3)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(var1, var3, 0, 0, 0, 4, 4, 4, true, var2, StructureStrongholdPieces.access$200());
         this.placeDoor(var1, var2, var3, super.field_143013_d, 1, 1, 0);
         if(super.coordBaseMode != 2 && super.coordBaseMode != 3) {
            this.fillWithBlocks(var1, var3, 0, 1, 1, 0, 3, 3, Blocks.air, Blocks.air, false);
         } else {
            this.fillWithBlocks(var1, var3, 4, 1, 1, 4, 3, 3, Blocks.air, Blocks.air, false);
         }

         return true;
      }
   }
}
