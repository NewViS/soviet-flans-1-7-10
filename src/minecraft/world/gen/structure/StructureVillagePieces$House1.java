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

public class StructureVillagePieces$House1 extends StructureVillagePieces$Village {

   public StructureVillagePieces$House1() {}

   public StructureVillagePieces$House1(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, int var5) {
      super(var1, var2);
      super.coordBaseMode = var5;
      super.boundingBox = var4;
   }

   public static StructureVillagePieces$House1 func_74898_a(StructureVillagePieces$Start var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 9, 9, 6, var6);
      return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null?new StructureVillagePieces$House1(var0, var7, var2, var8, var6):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(super.field_143015_k < 0) {
         super.field_143015_k = this.getAverageGroundLevel(var1, var3);
         if(super.field_143015_k < 0) {
            return true;
         }

         super.boundingBox.offset(0, super.field_143015_k - super.boundingBox.maxY + 9 - 1, 0);
      }

      this.fillWithBlocks(var1, var3, 1, 1, 1, 7, 5, 4, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 0, 0, 0, 8, 0, 5, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 0, 5, 0, 8, 5, 5, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 0, 6, 1, 8, 6, 4, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 0, 7, 2, 8, 7, 3, Blocks.cobblestone, Blocks.cobblestone, false);
      int var4 = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
      int var5 = this.getMetadataWithOffset(Blocks.oak_stairs, 2);

      int var6;
      int var7;
      for(var6 = -1; var6 <= 2; ++var6) {
         for(var7 = 0; var7 <= 8; ++var7) {
            this.placeBlockAtCurrentPosition(var1, Blocks.oak_stairs, var4, var7, 6 + var6, var6, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.oak_stairs, var5, var7, 6 + var6, 5 - var6, var3);
         }
      }

      this.fillWithBlocks(var1, var3, 0, 1, 0, 0, 1, 5, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 1, 1, 5, 8, 1, 5, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 8, 1, 0, 8, 1, 4, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 2, 1, 0, 7, 1, 0, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 0, 2, 0, 0, 4, 0, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 0, 2, 5, 0, 4, 5, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 8, 2, 5, 8, 4, 5, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 8, 2, 0, 8, 4, 0, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 0, 2, 1, 0, 4, 4, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 1, 2, 5, 7, 4, 5, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 8, 2, 1, 8, 4, 4, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 1, 2, 0, 7, 4, 0, Blocks.planks, Blocks.planks, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 4, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 5, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 6, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 4, 3, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 5, 3, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 6, 3, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 0, 2, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 0, 2, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 0, 3, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 0, 3, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 8, 2, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 8, 2, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 8, 3, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 8, 3, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 2, 2, 5, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 3, 2, 5, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 5, 2, 5, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 6, 2, 5, var3);
      this.fillWithBlocks(var1, var3, 1, 4, 1, 7, 4, 1, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 1, 4, 4, 7, 4, 4, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 1, 3, 4, 7, 3, 4, Blocks.bookshelf, Blocks.bookshelf, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 7, 1, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 7, 1, 3, var3);
      var6 = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
      this.placeBlockAtCurrentPosition(var1, Blocks.oak_stairs, var6, 6, 1, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.oak_stairs, var6, 5, 1, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.oak_stairs, var6, 4, 1, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.oak_stairs, var6, 3, 1, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 6, 1, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wooden_pressure_plate, 0, 6, 2, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 4, 1, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wooden_pressure_plate, 0, 4, 2, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.crafting_table, 0, 7, 1, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 1, 1, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 1, 2, 0, var3);
      this.placeDoorAtCurrentPosition(var1, var3, var2, 1, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));
      if(this.getBlockAtCurrentPosition(var1, 1, 0, -1, var3).getMaterial() == Material.air && this.getBlockAtCurrentPosition(var1, 1, -1, -1, var3).getMaterial() != Material.air) {
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 1, 0, -1, var3);
      }

      for(var7 = 0; var7 < 6; ++var7) {
         for(int var8 = 0; var8 < 9; ++var8) {
            this.clearCurrentPositionBlocksUpwards(var1, var8, 9, var7, var3);
            this.func_151554_b(var1, Blocks.cobblestone, 0, var8, -1, var7, var3);
         }
      }

      this.spawnVillagers(var1, var3, 2, 1, 2, 1);
      return true;
   }

   protected int getVillagerType(int var1) {
      return 1;
   }
}
