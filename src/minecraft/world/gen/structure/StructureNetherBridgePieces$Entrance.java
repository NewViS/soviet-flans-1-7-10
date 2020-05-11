package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Piece;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Start;

public class StructureNetherBridgePieces$Entrance extends StructureNetherBridgePieces$Piece {

   public StructureNetherBridgePieces$Entrance() {}

   public StructureNetherBridgePieces$Entrance(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.boundingBox = var3;
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      this.getNextComponentNormal((StructureNetherBridgePieces$Start)var1, var2, var3, 5, 3, true);
   }

   public static StructureNetherBridgePieces$Entrance createValidComponent(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -5, -3, 0, 13, 14, 13, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureNetherBridgePieces$Entrance(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      this.fillWithBlocks(var1, var3, 0, 3, 0, 12, 4, 12, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 5, 0, 12, 13, 12, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 0, 5, 0, 1, 12, 12, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 11, 5, 0, 12, 12, 12, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 2, 5, 11, 4, 12, 12, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 8, 5, 11, 10, 12, 12, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 5, 9, 11, 7, 12, 12, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 2, 5, 0, 4, 12, 1, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 8, 5, 0, 10, 12, 1, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 5, 9, 0, 7, 12, 1, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 2, 11, 2, 10, 12, 10, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 5, 8, 0, 7, 8, 0, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);

      int var4;
      for(var4 = 1; var4 <= 11; var4 += 2) {
         this.fillWithBlocks(var1, var3, var4, 10, 0, var4, 11, 0, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
         this.fillWithBlocks(var1, var3, var4, 10, 12, var4, 11, 12, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
         this.fillWithBlocks(var1, var3, 0, 10, var4, 0, 11, var4, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
         this.fillWithBlocks(var1, var3, 12, 10, var4, 12, 11, var4, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
         this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick, 0, var4, 13, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick, 0, var4, 13, 12, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick, 0, 0, 13, var4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick, 0, 12, 13, var4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_fence, 0, var4 + 1, 13, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_fence, 0, var4 + 1, 13, 12, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_fence, 0, 0, 13, var4 + 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_fence, 0, 12, 13, var4 + 1, var3);
      }

      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_fence, 0, 0, 13, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_fence, 0, 0, 13, 12, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_fence, 0, 0, 13, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_fence, 0, 12, 13, 0, var3);

      for(var4 = 3; var4 <= 9; var4 += 2) {
         this.fillWithBlocks(var1, var3, 1, 7, var4, 1, 8, var4, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
         this.fillWithBlocks(var1, var3, 11, 7, var4, 11, 8, var4, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      }

      this.fillWithBlocks(var1, var3, 4, 2, 0, 8, 2, 12, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 2, 4, 12, 2, 8, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 4, 0, 0, 8, 1, 3, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 4, 0, 9, 8, 1, 12, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 0, 4, 3, 1, 8, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 9, 0, 4, 12, 1, 8, Blocks.nether_brick, Blocks.nether_brick, false);

      int var5;
      for(var4 = 4; var4 <= 8; ++var4) {
         for(var5 = 0; var5 <= 2; ++var5) {
            this.func_151554_b(var1, Blocks.nether_brick, 0, var4, -1, var5, var3);
            this.func_151554_b(var1, Blocks.nether_brick, 0, var4, -1, 12 - var5, var3);
         }
      }

      for(var4 = 0; var4 <= 2; ++var4) {
         for(var5 = 4; var5 <= 8; ++var5) {
            this.func_151554_b(var1, Blocks.nether_brick, 0, var4, -1, var5, var3);
            this.func_151554_b(var1, Blocks.nether_brick, 0, 12 - var4, -1, var5, var3);
         }
      }

      this.fillWithBlocks(var1, var3, 5, 5, 5, 7, 5, 7, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 6, 1, 6, 6, 4, 6, Blocks.air, Blocks.air, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick, 0, 6, 0, 6, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.flowing_lava, 0, 6, 5, 6, var3);
      var4 = this.getXWithOffset(6, 6);
      var5 = this.getYWithOffset(5);
      int var6 = this.getZWithOffset(6, 6);
      if(var3.isVecInside(var4, var5, var6)) {
         var1.scheduledUpdatesAreImmediate = true;
         Blocks.flowing_lava.updateTick(var1, var4, var5, var6, var2);
         var1.scheduledUpdatesAreImmediate = false;
      }

      return true;
   }
}
