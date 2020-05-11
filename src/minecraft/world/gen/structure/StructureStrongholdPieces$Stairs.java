package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Crossing;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs2;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold$Door;

public class StructureStrongholdPieces$Stairs extends StructureStrongholdPieces$Stronghold {

   private boolean field_75024_a;


   public StructureStrongholdPieces$Stairs() {}

   public StructureStrongholdPieces$Stairs(int var1, Random var2, int var3, int var4) {
      super(var1);
      this.field_75024_a = true;
      super.coordBaseMode = var2.nextInt(4);
      super.field_143013_d = StructureStrongholdPieces$Stronghold$Door.OPENING;
      switch(super.coordBaseMode) {
      case 0:
      case 2:
         super.boundingBox = new StructureBoundingBox(var3, 64, var4, var3 + 5 - 1, 74, var4 + 5 - 1);
         break;
      default:
         super.boundingBox = new StructureBoundingBox(var3, 64, var4, var3 + 5 - 1, 74, var4 + 5 - 1);
      }

   }

   public StructureStrongholdPieces$Stairs(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      this.field_75024_a = false;
      super.coordBaseMode = var4;
      super.field_143013_d = this.getRandomDoor(var2);
      super.boundingBox = var3;
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setBoolean("Source", this.field_75024_a);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.field_75024_a = var1.getBoolean("Source");
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      if(this.field_75024_a) {
         StructureStrongholdPieces.access$102(StructureStrongholdPieces$Crossing.class);
      }

      this.getNextComponentNormal((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
   }

   public static StructureStrongholdPieces$Stairs getStrongholdStairsComponent(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -7, 0, 5, 11, 5, var5);
      return canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureStrongholdPieces$Stairs(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.isLiquidInStructureBoundingBox(var1, var3)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(var1, var3, 0, 0, 0, 4, 10, 4, true, var2, StructureStrongholdPieces.access$200());
         this.placeDoor(var1, var2, var3, super.field_143013_d, 1, 7, 0);
         this.placeDoor(var1, var2, var3, StructureStrongholdPieces$Stronghold$Door.OPENING, 1, 1, 4);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 2, 6, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 1, 5, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 1, 6, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 1, 5, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 1, 4, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 1, 5, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 2, 4, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 3, 3, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 3, 4, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 3, 3, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 3, 2, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 3, 3, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 2, 2, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 1, 1, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 1, 2, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 1, 1, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 1, 1, 3, var3);
         return true;
      }
   }
}
