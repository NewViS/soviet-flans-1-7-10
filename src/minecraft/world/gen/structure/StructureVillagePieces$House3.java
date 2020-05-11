package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces$Start;
import net.minecraft.world.gen.structure.StructureVillagePieces$Village;

public class StructureVillagePieces$House3 extends StructureVillagePieces$Village {

   public StructureVillagePieces$House3() {}

   public StructureVillagePieces$House3(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, int var5) {
      super(var1, var2);
      super.coordBaseMode = var5;
      super.boundingBox = var4;
   }

   public static StructureVillagePieces$House3 func_74921_a(StructureVillagePieces$Start var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 9, 7, 12, var6);
      return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null?new StructureVillagePieces$House3(var0, var7, var2, var8, var6):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(super.field_143015_k < 0) {
         super.field_143015_k = this.getAverageGroundLevel(var1, var3);
         if(super.field_143015_k < 0) {
            return true;
         }

         super.boundingBox.offset(0, super.field_143015_k - super.boundingBox.maxY + 7 - 1, 0);
      }

      this.fillWithBlocks(var1, var3, 1, 1, 1, 7, 4, 4, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 2, 1, 6, 8, 4, 10, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 2, 0, 5, 8, 0, 10, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 1, 0, 1, 7, 0, 4, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 0, 0, 0, 0, 3, 5, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 8, 0, 0, 8, 3, 10, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 1, 0, 0, 7, 2, 0, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 1, 0, 5, 2, 1, 5, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 2, 0, 6, 2, 3, 10, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 3, 0, 10, 7, 3, 10, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 1, 2, 0, 7, 3, 0, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 1, 2, 5, 2, 3, 5, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 0, 4, 1, 8, 4, 1, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 0, 4, 4, 3, 4, 4, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 0, 5, 2, 8, 5, 3, Blocks.planks, Blocks.planks, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 0, 4, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 0, 4, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 8, 4, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 8, 4, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 8, 4, 4, var3);
      int var4 = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
      int var5 = this.getMetadataWithOffset(Blocks.oak_stairs, 2);

      int var6;
      int var7;
      for(var6 = -1; var6 <= 2; ++var6) {
         for(var7 = 0; var7 <= 8; ++var7) {
            this.placeBlockAtCurrentPosition(var1, Blocks.oak_stairs, var4, var7, 4 + var6, var6, var3);
            if((var6 > -1 || var7 <= 1) && (var6 > 0 || var7 <= 3) && (var6 > 1 || var7 <= 4 || var7 >= 6)) {
               this.placeBlockAtCurrentPosition(var1, Blocks.oak_stairs, var5, var7, 4 + var6, 5 - var6, var3);
            }
         }
      }

      this.fillWithBlocks(var1, var3, 3, 4, 5, 3, 4, 10, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 7, 4, 2, 7, 4, 10, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 4, 5, 4, 4, 5, 10, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 6, 5, 4, 6, 5, 10, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 5, 6, 3, 5, 6, 10, Blocks.planks, Blocks.planks, false);
      var6 = this.getMetadataWithOffset(Blocks.oak_stairs, 0);

      int var8;
      for(var7 = 4; var7 >= 1; --var7) {
         this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, var7, 2 + var7, 7 - var7, var3);

         for(var8 = 8 - var7; var8 <= 10; ++var8) {
            this.placeBlockAtCurrentPosition(var1, Blocks.oak_stairs, var6, var7, 2 + var7, var8, var3);
         }
      }

      var7 = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 6, 6, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 7, 5, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.oak_stairs, var7, 6, 6, 4, var3);

      int var9;
      for(var8 = 6; var8 <= 8; ++var8) {
         for(var9 = 5; var9 <= 10; ++var9) {
            this.placeBlockAtCurrentPosition(var1, Blocks.oak_stairs, var7, var8, 12 - var8, var9, var3);
         }
      }

      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 0, 2, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 0, 2, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 0, 2, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 0, 2, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 4, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 5, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 6, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 8, 2, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 8, 2, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 8, 2, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 8, 2, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 8, 2, 5, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 8, 2, 6, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 8, 2, 7, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 8, 2, 8, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 8, 2, 9, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 2, 2, 6, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 2, 2, 7, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 2, 2, 8, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 2, 2, 9, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 4, 4, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 5, 4, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 6, 4, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 5, 5, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 2, 1, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 2, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 2, 3, 1, var3);
      this.placeDoorAtCurrentPosition(var1, var3, var2, 2, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));
      this.fillWithBlocks(var1, var3, 1, 0, -1, 3, 2, -1, Blocks.air, Blocks.air, false);
      if(this.getBlockAtCurrentPosition(var1, 2, 0, -1, var3).getMaterial() == Material.air && this.getBlockAtCurrentPosition(var1, 2, -1, -1, var3).getMaterial() != Material.air) {
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 2, 0, -1, var3);
      }

      for(var8 = 0; var8 < 5; ++var8) {
         for(var9 = 0; var9 < 9; ++var9) {
            this.clearCurrentPositionBlocksUpwards(var1, var9, 7, var8, var3);
            this.func_151554_b(var1, Blocks.cobblestone, 0, var9, -1, var8, var3);
         }
      }

      for(var8 = 5; var8 < 11; ++var8) {
         for(var9 = 2; var9 < 9; ++var9) {
            this.clearCurrentPositionBlocksUpwards(var1, var9, 7, var8, var3);
            this.func_151554_b(var1, Blocks.cobblestone, 0, var9, -1, var8, var3);
         }
      }

      this.spawnVillagers(var1, var3, 4, 1, 2, 2);
      return true;
   }
}
