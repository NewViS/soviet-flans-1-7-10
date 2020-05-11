package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces$Start;
import net.minecraft.world.gen.structure.StructureVillagePieces$Village;

public class StructureVillagePieces$WoodHut extends StructureVillagePieces$Village {

   private boolean isTallHouse;
   private int tablePosition;


   public StructureVillagePieces$WoodHut() {}

   public StructureVillagePieces$WoodHut(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, int var5) {
      super(var1, var2);
      super.coordBaseMode = var5;
      super.boundingBox = var4;
      this.isTallHouse = var3.nextBoolean();
      this.tablePosition = var3.nextInt(3);
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setInteger("T", this.tablePosition);
      var1.setBoolean("C", this.isTallHouse);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.tablePosition = var1.getInteger("T");
      this.isTallHouse = var1.getBoolean("C");
   }

   public static StructureVillagePieces$WoodHut func_74908_a(StructureVillagePieces$Start var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 4, 6, 5, var6);
      return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null?new StructureVillagePieces$WoodHut(var0, var7, var2, var8, var6):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(super.field_143015_k < 0) {
         super.field_143015_k = this.getAverageGroundLevel(var1, var3);
         if(super.field_143015_k < 0) {
            return true;
         }

         super.boundingBox.offset(0, super.field_143015_k - super.boundingBox.maxY + 6 - 1, 0);
      }

      this.fillWithBlocks(var1, var3, 1, 1, 1, 3, 5, 4, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 0, 0, 0, 3, 0, 4, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 1, 0, 1, 2, 0, 3, Blocks.dirt, Blocks.dirt, false);
      if(this.isTallHouse) {
         this.fillWithBlocks(var1, var3, 1, 4, 1, 2, 4, 3, Blocks.log, Blocks.log, false);
      } else {
         this.fillWithBlocks(var1, var3, 1, 5, 1, 2, 5, 3, Blocks.log, Blocks.log, false);
      }

      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 1, 4, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 2, 4, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 1, 4, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 2, 4, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 0, 4, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 0, 4, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 0, 4, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 3, 4, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 3, 4, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.log, 0, 3, 4, 3, var3);
      this.fillWithBlocks(var1, var3, 0, 1, 0, 0, 3, 0, Blocks.log, Blocks.log, false);
      this.fillWithBlocks(var1, var3, 3, 1, 0, 3, 3, 0, Blocks.log, Blocks.log, false);
      this.fillWithBlocks(var1, var3, 0, 1, 4, 0, 3, 4, Blocks.log, Blocks.log, false);
      this.fillWithBlocks(var1, var3, 3, 1, 4, 3, 3, 4, Blocks.log, Blocks.log, false);
      this.fillWithBlocks(var1, var3, 0, 1, 1, 0, 3, 3, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 3, 1, 1, 3, 3, 3, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 1, 1, 0, 2, 3, 0, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 1, 1, 4, 2, 3, 4, Blocks.planks, Blocks.planks, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 0, 2, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 3, 2, 2, var3);
      if(this.tablePosition > 0) {
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, this.tablePosition, 1, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wooden_pressure_plate, 0, this.tablePosition, 2, 3, var3);
      }

      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 1, 1, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 1, 2, 0, var3);
      this.placeDoorAtCurrentPosition(var1, var3, var2, 1, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));
      if(this.getBlockAtCurrentPosition(var1, 1, 0, -1, var3).getMaterial() == Material.air && this.getBlockAtCurrentPosition(var1, 1, -1, -1, var3).getMaterial() != Material.air) {
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 1, 0, -1, var3);
      }

      for(int var4 = 0; var4 < 5; ++var4) {
         for(int var5 = 0; var5 < 4; ++var5) {
            this.clearCurrentPositionBlocksUpwards(var1, var5, 6, var4, var3);
            this.func_151554_b(var1, Blocks.cobblestone, 0, var5, -1, var4, var3);
         }
      }

      this.spawnVillagers(var1, var3, 1, 1, 2, 1);
      return true;
   }
}
