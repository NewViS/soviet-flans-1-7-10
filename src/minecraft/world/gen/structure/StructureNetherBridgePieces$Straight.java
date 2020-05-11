package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Piece;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Start;

public class StructureNetherBridgePieces$Straight extends StructureNetherBridgePieces$Piece {

   public StructureNetherBridgePieces$Straight() {}

   public StructureNetherBridgePieces$Straight(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.boundingBox = var3;
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      this.getNextComponentNormal((StructureNetherBridgePieces$Start)var1, var2, var3, 1, 3, false);
   }

   public static StructureNetherBridgePieces$Straight createValidComponent(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -3, 0, 5, 10, 19, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureNetherBridgePieces$Straight(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      this.fillWithBlocks(var1, var3, 0, 3, 0, 4, 4, 18, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 1, 5, 0, 3, 7, 18, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 0, 5, 0, 0, 5, 18, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 4, 5, 0, 4, 5, 18, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 2, 0, 4, 2, 5, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 2, 13, 4, 2, 18, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 0, 0, 4, 1, 3, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 0, 15, 4, 1, 18, Blocks.nether_brick, Blocks.nether_brick, false);

      for(int var4 = 0; var4 <= 4; ++var4) {
         for(int var5 = 0; var5 <= 2; ++var5) {
            this.func_151554_b(var1, Blocks.nether_brick, 0, var4, -1, var5, var3);
            this.func_151554_b(var1, Blocks.nether_brick, 0, var4, -1, 18 - var5, var3);
         }
      }

      this.fillWithBlocks(var1, var3, 0, 1, 1, 0, 4, 1, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 0, 3, 4, 0, 4, 4, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 0, 3, 14, 0, 4, 14, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 0, 1, 17, 0, 4, 17, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 4, 1, 1, 4, 4, 1, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 4, 3, 4, 4, 4, 4, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 4, 3, 14, 4, 4, 14, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 4, 1, 17, 4, 4, 17, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      return true;
   }
}
