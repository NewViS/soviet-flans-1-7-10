package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Piece;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Start;

public class StructureNetherBridgePieces$NetherStalkRoom extends StructureNetherBridgePieces$Piece {

   public StructureNetherBridgePieces$NetherStalkRoom() {}

   public StructureNetherBridgePieces$NetherStalkRoom(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.boundingBox = var3;
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      this.getNextComponentNormal((StructureNetherBridgePieces$Start)var1, var2, var3, 5, 3, true);
      this.getNextComponentNormal((StructureNetherBridgePieces$Start)var1, var2, var3, 5, 11, true);
   }

   public static StructureNetherBridgePieces$NetherStalkRoom createValidComponent(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -5, -3, 0, 13, 14, 13, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureNetherBridgePieces$NetherStalkRoom(var6, var1, var7, var5):null;
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

      var4 = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 3);

      int var5;
      int var6;
      int var7;
      for(var5 = 0; var5 <= 6; ++var5) {
         var6 = var5 + 4;

         for(var7 = 5; var7 <= 7; ++var7) {
            this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var4, var7, 5 + var5, var6, var3);
         }

         if(var6 >= 5 && var6 <= 8) {
            this.fillWithBlocks(var1, var3, 5, 5, var6, 7, var5 + 4, var6, Blocks.nether_brick, Blocks.nether_brick, false);
         } else if(var6 >= 9 && var6 <= 10) {
            this.fillWithBlocks(var1, var3, 5, 8, var6, 7, var5 + 4, var6, Blocks.nether_brick, Blocks.nether_brick, false);
         }

         if(var5 >= 1) {
            this.fillWithBlocks(var1, var3, 5, 6 + var5, var6, 7, 9 + var5, var6, Blocks.air, Blocks.air, false);
         }
      }

      for(var5 = 5; var5 <= 7; ++var5) {
         this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var4, var5, 12, 11, var3);
      }

      this.fillWithBlocks(var1, var3, 5, 6, 7, 5, 7, 7, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 7, 6, 7, 7, 7, 7, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 5, 13, 12, 7, 13, 12, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 2, 5, 2, 3, 5, 3, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 2, 5, 9, 3, 5, 10, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 2, 5, 4, 2, 5, 8, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 9, 5, 2, 10, 5, 3, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 9, 5, 9, 10, 5, 10, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 10, 5, 4, 10, 5, 8, Blocks.nether_brick, Blocks.nether_brick, false);
      var5 = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 0);
      var6 = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 1);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var6, 4, 5, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var6, 4, 5, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var6, 4, 5, 9, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var6, 4, 5, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var5, 8, 5, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var5, 8, 5, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var5, 8, 5, 9, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_stairs, var5, 8, 5, 10, var3);
      this.fillWithBlocks(var1, var3, 3, 4, 4, 4, 4, 8, Blocks.soul_sand, Blocks.soul_sand, false);
      this.fillWithBlocks(var1, var3, 8, 4, 4, 9, 4, 8, Blocks.soul_sand, Blocks.soul_sand, false);
      this.fillWithBlocks(var1, var3, 3, 5, 4, 4, 5, 8, Blocks.nether_wart, Blocks.nether_wart, false);
      this.fillWithBlocks(var1, var3, 8, 5, 4, 9, 5, 8, Blocks.nether_wart, Blocks.nether_wart, false);
      this.fillWithBlocks(var1, var3, 4, 2, 0, 8, 2, 12, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 2, 4, 12, 2, 8, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 4, 0, 0, 8, 1, 3, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 4, 0, 9, 8, 1, 12, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 0, 4, 3, 1, 8, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 9, 0, 4, 12, 1, 8, Blocks.nether_brick, Blocks.nether_brick, false);

      int var8;
      for(var7 = 4; var7 <= 8; ++var7) {
         for(var8 = 0; var8 <= 2; ++var8) {
            this.func_151554_b(var1, Blocks.nether_brick, 0, var7, -1, var8, var3);
            this.func_151554_b(var1, Blocks.nether_brick, 0, var7, -1, 12 - var8, var3);
         }
      }

      for(var7 = 0; var7 <= 2; ++var7) {
         for(var8 = 4; var8 <= 8; ++var8) {
            this.func_151554_b(var1, Blocks.nether_brick, 0, var7, -1, var8, var3);
            this.func_151554_b(var1, Blocks.nether_brick, 0, 12 - var7, -1, var8, var3);
         }
      }

      return true;
   }
}
