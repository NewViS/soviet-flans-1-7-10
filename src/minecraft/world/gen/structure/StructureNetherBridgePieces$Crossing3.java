package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Piece;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Start;

public class StructureNetherBridgePieces$Crossing3 extends StructureNetherBridgePieces$Piece {

   public StructureNetherBridgePieces$Crossing3() {}

   public StructureNetherBridgePieces$Crossing3(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.boundingBox = var3;
   }

   protected StructureNetherBridgePieces$Crossing3(Random var1, int var2, int var3) {
      super(0);
      super.coordBaseMode = var1.nextInt(4);
      switch(super.coordBaseMode) {
      case 0:
      case 2:
         super.boundingBox = new StructureBoundingBox(var2, 64, var3, var2 + 19 - 1, 73, var3 + 19 - 1);
         break;
      default:
         super.boundingBox = new StructureBoundingBox(var2, 64, var3, var2 + 19 - 1, 73, var3 + 19 - 1);
      }

   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      this.getNextComponentNormal((StructureNetherBridgePieces$Start)var1, var2, var3, 8, 3, false);
      this.getNextComponentX((StructureNetherBridgePieces$Start)var1, var2, var3, 3, 8, false);
      this.getNextComponentZ((StructureNetherBridgePieces$Start)var1, var2, var3, 3, 8, false);
   }

   public static StructureNetherBridgePieces$Crossing3 createValidComponent(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -8, -3, 0, 19, 10, 19, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureNetherBridgePieces$Crossing3(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      this.fillWithBlocks(var1, var3, 7, 3, 0, 11, 4, 18, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 3, 7, 18, 4, 11, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 8, 5, 0, 10, 7, 18, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 0, 5, 8, 18, 7, 10, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 7, 5, 0, 7, 5, 7, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 7, 5, 11, 7, 5, 18, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 11, 5, 0, 11, 5, 7, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 11, 5, 11, 11, 5, 18, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 5, 7, 7, 5, 7, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 11, 5, 7, 18, 5, 7, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 5, 11, 7, 5, 11, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 11, 5, 11, 18, 5, 11, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 7, 2, 0, 11, 2, 5, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 7, 2, 13, 11, 2, 18, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 7, 0, 0, 11, 1, 3, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 7, 0, 15, 11, 1, 18, Blocks.nether_brick, Blocks.nether_brick, false);

      int var4;
      int var5;
      for(var4 = 7; var4 <= 11; ++var4) {
         for(var5 = 0; var5 <= 2; ++var5) {
            this.func_151554_b(var1, Blocks.nether_brick, 0, var4, -1, var5, var3);
            this.func_151554_b(var1, Blocks.nether_brick, 0, var4, -1, 18 - var5, var3);
         }
      }

      this.fillWithBlocks(var1, var3, 0, 2, 7, 5, 2, 11, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 13, 2, 7, 18, 2, 11, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 0, 7, 3, 1, 11, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 15, 0, 7, 18, 1, 11, Blocks.nether_brick, Blocks.nether_brick, false);

      for(var4 = 0; var4 <= 2; ++var4) {
         for(var5 = 7; var5 <= 11; ++var5) {
            this.func_151554_b(var1, Blocks.nether_brick, 0, var4, -1, var5, var3);
            this.func_151554_b(var1, Blocks.nether_brick, 0, 18 - var4, -1, var5, var3);
         }
      }

      return true;
   }
}
