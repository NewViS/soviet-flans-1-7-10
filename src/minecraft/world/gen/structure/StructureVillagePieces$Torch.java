package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces$Start;
import net.minecraft.world.gen.structure.StructureVillagePieces$Village;

public class StructureVillagePieces$Torch extends StructureVillagePieces$Village {

   public StructureVillagePieces$Torch() {}

   public StructureVillagePieces$Torch(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, int var5) {
      super(var1, var2);
      super.coordBaseMode = var5;
      super.boundingBox = var4;
   }

   public static StructureBoundingBox func_74904_a(StructureVillagePieces$Start var0, List var1, Random var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 3, 4, 2, var6);
      return StructureComponent.findIntersecting(var1, var7) != null?null:var7;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(super.field_143015_k < 0) {
         super.field_143015_k = this.getAverageGroundLevel(var1, var3);
         if(super.field_143015_k < 0) {
            return true;
         }

         super.boundingBox.offset(0, super.field_143015_k - super.boundingBox.maxY + 4 - 1, 0);
      }

      this.fillWithBlocks(var1, var3, 0, 0, 0, 2, 3, 1, Blocks.air, Blocks.air, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 1, 0, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 1, 1, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 1, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, 15, 1, 3, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 0, 3, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 1, 3, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 2, 3, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 1, 3, -1, var3);
      return true;
   }
}
