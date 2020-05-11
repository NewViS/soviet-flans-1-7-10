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

public class StructureVillagePieces$House4Garden extends StructureVillagePieces$Village {

   private boolean isRoofAccessible;


   public StructureVillagePieces$House4Garden() {}

   public StructureVillagePieces$House4Garden(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, int var5) {
      super(var1, var2);
      super.coordBaseMode = var5;
      super.boundingBox = var4;
      this.isRoofAccessible = var3.nextBoolean();
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setBoolean("Terrace", this.isRoofAccessible);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.isRoofAccessible = var1.getBoolean("Terrace");
   }

   public static StructureVillagePieces$House4Garden func_74912_a(StructureVillagePieces$Start var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 5, 6, 5, var6);
      return StructureComponent.findIntersecting(var1, var8) != null?null:new StructureVillagePieces$House4Garden(var0, var7, var2, var8, var6);
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(super.field_143015_k < 0) {
         super.field_143015_k = this.getAverageGroundLevel(var1, var3);
         if(super.field_143015_k < 0) {
            return true;
         }

         super.boundingBox.offset(0, super.field_143015_k - super.boundingBox.maxY + 6 - 1, 0);
      }

      this.fillWithBlocks(var1, var3, 0, 0, 0, 4, 0, 4, Blocks.cobblestone, Blocks.cobblestone, false);
      this.fillWithBlocks(var1, var3, 0, 4, 0, 4, 4, 4, Blocks.log, Blocks.log, false);
      this.fillWithBlocks(var1, var3, 1, 4, 1, 3, 4, 3, Blocks.planks, Blocks.planks, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 0, 1, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 0, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 0, 3, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 4, 1, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 4, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 4, 3, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 0, 1, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 0, 2, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 0, 3, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 4, 1, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 4, 2, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 4, 3, 4, var3);
      this.fillWithBlocks(var1, var3, 0, 1, 1, 0, 3, 3, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 4, 1, 1, 4, 3, 3, Blocks.planks, Blocks.planks, false);
      this.fillWithBlocks(var1, var3, 1, 1, 4, 3, 3, 4, Blocks.planks, Blocks.planks, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 0, 2, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 2, 2, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.glass_pane, 0, 4, 2, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 1, 1, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 1, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 1, 3, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 2, 3, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 3, 3, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 3, 2, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 3, 1, 0, var3);
      if(this.getBlockAtCurrentPosition(var1, 2, 0, -1, var3).getMaterial() == Material.air && this.getBlockAtCurrentPosition(var1, 2, -1, -1, var3).getMaterial() != Material.air) {
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 2, 0, -1, var3);
      }

      this.fillWithBlocks(var1, var3, 1, 1, 1, 3, 3, 3, Blocks.air, Blocks.air, false);
      if(this.isRoofAccessible) {
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 0, 5, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 1, 5, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 2, 5, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 3, 5, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 4, 5, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 0, 5, 4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 1, 5, 4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 2, 5, 4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 3, 5, 4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 4, 5, 4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 4, 5, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 4, 5, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 4, 5, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 0, 5, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 0, 5, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 0, 5, 3, var3);
      }

      int var4;
      if(this.isRoofAccessible) {
         var4 = this.getMetadataWithOffset(Blocks.ladder, 3);
         this.placeBlockAtCurrentPosition(var1, Blocks.ladder, var4, 3, 1, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.ladder, var4, 3, 2, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.ladder, var4, 3, 3, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.ladder, var4, 3, 4, 3, var3);
      }

      this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 2, 3, 1, var3);

      for(var4 = 0; var4 < 5; ++var4) {
         for(int var5 = 0; var5 < 5; ++var5) {
            this.clearCurrentPositionBlocksUpwards(var1, var5, 6, var4, var3);
            this.func_151554_b(var1, Blocks.cobblestone, 0, var5, -1, var4, var3);
         }
      }

      this.spawnVillagers(var1, var3, 1, 1, 2, 1);
      return true;
   }
}
