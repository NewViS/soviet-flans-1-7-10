package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs2;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold$Door;

public class StructureStrongholdPieces$StairsStraight extends StructureStrongholdPieces$Stronghold {

   public StructureStrongholdPieces$StairsStraight() {}

   public StructureStrongholdPieces$StairsStraight(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.field_143013_d = this.getRandomDoor(var2);
      super.boundingBox = var3;
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      this.getNextComponentNormal((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
   }

   public static StructureStrongholdPieces$StairsStraight findValidPlacement(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -7, 0, 5, 11, 8, var5);
      return canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureStrongholdPieces$StairsStraight(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.isLiquidInStructureBoundingBox(var1, var3)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(var1, var3, 0, 0, 0, 4, 10, 7, true, var2, StructureStrongholdPieces.access$200());
         this.placeDoor(var1, var2, var3, super.field_143013_d, 1, 7, 0);
         this.placeDoor(var1, var2, var3, StructureStrongholdPieces$Stronghold$Door.OPENING, 1, 1, 7);
         int var4 = this.getMetadataWithOffset(Blocks.stone_stairs, 2);

         for(int var5 = 0; var5 < 6; ++var5) {
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 1, 6 - var5, 1 + var5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 2, 6 - var5, 1 + var5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 3, 6 - var5, 1 + var5, var3);
            if(var5 < 5) {
               this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 1, 5 - var5, 1 + var5, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 2, 5 - var5, 1 + var5, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 3, 5 - var5, 1 + var5, var3);
            }
         }

         return true;
      }
   }
}
