package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces$Start;
import net.minecraft.world.gen.structure.StructureVillagePieces$Village;

public class StructureVillagePieces$Well extends StructureVillagePieces$Village {

   public StructureVillagePieces$Well() {}

   public StructureVillagePieces$Well(StructureVillagePieces$Start var1, int var2, Random var3, int var4, int var5) {
      super(var1, var2);
      super.coordBaseMode = var3.nextInt(4);
      switch(super.coordBaseMode) {
      case 0:
      case 2:
         super.boundingBox = new StructureBoundingBox(var4, 64, var5, var4 + 6 - 1, 78, var5 + 6 - 1);
         break;
      default:
         super.boundingBox = new StructureBoundingBox(var4, 64, var5, var4 + 6 - 1, 78, var5 + 6 - 1);
      }

   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      StructureVillagePieces.access$100((StructureVillagePieces$Start)var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.maxY - 4, super.boundingBox.minZ + 1, 1, this.getComponentType());
      StructureVillagePieces.access$100((StructureVillagePieces$Start)var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.maxY - 4, super.boundingBox.minZ + 1, 3, this.getComponentType());
      StructureVillagePieces.access$100((StructureVillagePieces$Start)var1, var2, var3, super.boundingBox.minX + 1, super.boundingBox.maxY - 4, super.boundingBox.minZ - 1, 2, this.getComponentType());
      StructureVillagePieces.access$100((StructureVillagePieces$Start)var1, var2, var3, super.boundingBox.minX + 1, super.boundingBox.maxY - 4, super.boundingBox.maxZ + 1, 0, this.getComponentType());
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(super.field_143015_k < 0) {
         super.field_143015_k = this.getAverageGroundLevel(var1, var3);
         if(super.field_143015_k < 0) {
            return true;
         }

         super.boundingBox.offset(0, super.field_143015_k - super.boundingBox.maxY + 3, 0);
      }

      this.fillWithBlocks(var1, var3, 1, 0, 1, 4, 12, 4, Blocks.cobblestone, Blocks.flowing_water, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 2, 12, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 3, 12, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 2, 12, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 3, 12, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 1, 13, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 1, 14, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 4, 13, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 4, 14, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 1, 13, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 1, 14, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 4, 13, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 4, 14, 4, var3);
      this.fillWithBlocks(var1, var3, 1, 15, 1, 4, 15, 4, Blocks.cobblestone, Blocks.cobblestone, false);

      for(int var4 = 0; var4 <= 5; ++var4) {
         for(int var5 = 0; var5 <= 5; ++var5) {
            if(var5 == 0 || var5 == 5 || var4 == 0 || var4 == 5) {
               this.placeBlockAtCurrentPosition(var1, Blocks.gravel, 0, var5, 11, var4, var3);
               this.clearCurrentPositionBlocksUpwards(var1, var5, 12, var4, var3);
            }
         }
      }

      return true;
   }
}
