package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Piece;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Start;

public class StructureNetherBridgePieces$Corridor3 extends StructureNetherBridgePieces$Piece {

   public StructureNetherBridgePieces$Corridor3() {}

   public StructureNetherBridgePieces$Corridor3(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.boundingBox = var3;
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      this.getNextComponentNormal((StructureNetherBridgePieces$Start)var1, var2, var3, 1, 0, true);
   }

   public static StructureNetherBridgePieces$Corridor3 createValidComponent(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -7, 0, 5, 14, 10, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureNetherBridgePieces$Corridor3(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      int var4 = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 2);

      for(int var5 = 0; var5 <= 9; ++var5) {
         int var6 = Math.max(1, 7 - var5);
         int var7 = Math.min(Math.max(var6 + 5, 14 - var5), 13);
         int var8 = var5;
         this.fillWithBlocks(var1, var3, 0, 0, var5, 4, var6, var5, Blocks.nether_brick, Blocks.nether_brick, false);
         this.fillWithBlocks(var1, var3, 1, var6 + 1, var5, 3, var7 - 1, var5, Blocks.air, Blocks.air, false);
         if(var5 <= 6) {
            this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var4, 1, var6 + 1, var5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var4, 2, var6 + 1, var5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var4, 3, var6 + 1, var5, var3);
         }

         this.fillWithBlocks(var1, var3, 0, var7, var5, 4, var7, var5, Blocks.nether_brick, Blocks.nether_brick, false);
         this.fillWithBlocks(var1, var3, 0, var6 + 1, var5, 0, var7 - 1, var5, Blocks.nether_brick, Blocks.nether_brick, false);
         this.fillWithBlocks(var1, var3, 4, var6 + 1, var5, 4, var7 - 1, var5, Blocks.nether_brick, Blocks.nether_brick, false);
         if((var5 & 1) == 0) {
            this.fillWithBlocks(var1, var3, 0, var6 + 2, var5, 0, var6 + 3, var5, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
            this.fillWithBlocks(var1, var3, 4, var6 + 2, var5, 4, var6 + 3, var5, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
         }

         for(int var9 = 0; var9 <= 4; ++var9) {
            this.func_151554_b(var1, Blocks.nether_brick, 0, var9, -1, var8, var3);
         }
      }

      return true;
   }
}
