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

public class StructureStrongholdPieces$Prison extends StructureStrongholdPieces$Stronghold {

   public StructureStrongholdPieces$Prison() {}

   public StructureStrongholdPieces$Prison(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.field_143013_d = this.getRandomDoor(var2);
      super.boundingBox = var3;
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      this.getNextComponentNormal((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
   }

   public static StructureStrongholdPieces$Prison findValidPlacement(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -1, 0, 9, 5, 11, var5);
      return canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureStrongholdPieces$Prison(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.isLiquidInStructureBoundingBox(var1, var3)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(var1, var3, 0, 0, 0, 8, 4, 10, true, var2, StructureStrongholdPieces.access$200());
         this.placeDoor(var1, var2, var3, super.field_143013_d, 1, 1, 0);
         this.fillWithBlocks(var1, var3, 1, 1, 10, 3, 3, 10, Blocks.air, Blocks.air, false);
         this.fillWithRandomizedBlocks(var1, var3, 4, 1, 1, 4, 3, 1, false, var2, StructureStrongholdPieces.access$200());
         this.fillWithRandomizedBlocks(var1, var3, 4, 1, 3, 4, 3, 3, false, var2, StructureStrongholdPieces.access$200());
         this.fillWithRandomizedBlocks(var1, var3, 4, 1, 7, 4, 3, 7, false, var2, StructureStrongholdPieces.access$200());
         this.fillWithRandomizedBlocks(var1, var3, 4, 1, 9, 4, 3, 9, false, var2, StructureStrongholdPieces.access$200());
         this.fillWithBlocks(var1, var3, 4, 1, 4, 4, 3, 6, Blocks.iron_bars, Blocks.iron_bars, false);
         this.fillWithBlocks(var1, var3, 5, 1, 5, 7, 3, 5, Blocks.iron_bars, Blocks.iron_bars, false);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_bars, 0, 4, 3, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_bars, 0, 4, 3, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_door, this.getMetadataWithOffset(Blocks.iron_door, 3), 4, 1, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_door, this.getMetadataWithOffset(Blocks.iron_door, 3) + 8, 4, 2, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_door, this.getMetadataWithOffset(Blocks.iron_door, 3), 4, 1, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_door, this.getMetadataWithOffset(Blocks.iron_door, 3) + 8, 4, 2, 8, var3);
         return true;
      }
   }
}
