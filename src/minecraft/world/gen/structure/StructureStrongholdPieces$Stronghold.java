package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs2;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold$Door;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$SwitchDoor;

abstract class StructureStrongholdPieces$Stronghold extends StructureComponent {

   protected StructureStrongholdPieces$Stronghold$Door field_143013_d;


   public StructureStrongholdPieces$Stronghold() {
      this.field_143013_d = StructureStrongholdPieces$Stronghold$Door.OPENING;
   }

   protected StructureStrongholdPieces$Stronghold(int var1) {
      super(var1);
      this.field_143013_d = StructureStrongholdPieces$Stronghold$Door.OPENING;
   }

   protected void func_143012_a(NBTTagCompound var1) {
      var1.setString("EntryDoor", this.field_143013_d.name());
   }

   protected void func_143011_b(NBTTagCompound var1) {
      this.field_143013_d = StructureStrongholdPieces$Stronghold$Door.valueOf(var1.getString("EntryDoor"));
   }

   protected void placeDoor(World var1, Random var2, StructureBoundingBox var3, StructureStrongholdPieces$Stronghold$Door var4, int var5, int var6, int var7) {
      switch(StructureStrongholdPieces$SwitchDoor.doorEnum[var4.ordinal()]) {
      case 1:
      default:
         this.fillWithBlocks(var1, var3, var5, var6, var7, var5 + 3 - 1, var6 + 3 - 1, var7, Blocks.air, Blocks.air, false);
         break;
      case 2:
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5, var6, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5, var6 + 1, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5, var6 + 2, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5 + 1, var6 + 2, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5 + 2, var6 + 2, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5 + 2, var6 + 1, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5 + 2, var6, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wooden_door, 0, var5 + 1, var6, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wooden_door, 8, var5 + 1, var6 + 1, var7, var3);
         break;
      case 3:
         this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, var5 + 1, var6, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, var5 + 1, var6 + 1, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_bars, 0, var5, var6, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_bars, 0, var5, var6 + 1, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_bars, 0, var5, var6 + 2, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_bars, 0, var5 + 1, var6 + 2, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_bars, 0, var5 + 2, var6 + 2, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_bars, 0, var5 + 2, var6 + 1, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_bars, 0, var5 + 2, var6, var7, var3);
         break;
      case 4:
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5, var6, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5, var6 + 1, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5, var6 + 2, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5 + 1, var6 + 2, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5 + 2, var6 + 2, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5 + 2, var6 + 1, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, var5 + 2, var6, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_door, 0, var5 + 1, var6, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.iron_door, 8, var5 + 1, var6 + 1, var7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_button, this.getMetadataWithOffset(Blocks.stone_button, 4), var5 + 2, var6 + 1, var7 + 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_button, this.getMetadataWithOffset(Blocks.stone_button, 3), var5 + 2, var6 + 1, var7 - 1, var3);
      }

   }

   protected StructureStrongholdPieces$Stronghold$Door getRandomDoor(Random var1) {
      int var2 = var1.nextInt(5);
      switch(var2) {
      case 0:
      case 1:
      default:
         return StructureStrongholdPieces$Stronghold$Door.OPENING;
      case 2:
         return StructureStrongholdPieces$Stronghold$Door.WOOD_DOOR;
      case 3:
         return StructureStrongholdPieces$Stronghold$Door.GRATES;
      case 4:
         return StructureStrongholdPieces$Stronghold$Door.IRON_DOOR;
      }
   }

   protected StructureComponent getNextComponentNormal(StructureStrongholdPieces$Stairs2 var1, List var2, Random var3, int var4, int var5) {
      switch(super.coordBaseMode) {
      case 0:
         return StructureStrongholdPieces.access$000(var1, var2, var3, super.boundingBox.minX + var4, super.boundingBox.minY + var5, super.boundingBox.maxZ + 1, super.coordBaseMode, this.getComponentType());
      case 1:
         return StructureStrongholdPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY + var5, super.boundingBox.minZ + var4, super.coordBaseMode, this.getComponentType());
      case 2:
         return StructureStrongholdPieces.access$000(var1, var2, var3, super.boundingBox.minX + var4, super.boundingBox.minY + var5, super.boundingBox.minZ - 1, super.coordBaseMode, this.getComponentType());
      case 3:
         return StructureStrongholdPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY + var5, super.boundingBox.minZ + var4, super.coordBaseMode, this.getComponentType());
      default:
         return null;
      }
   }

   protected StructureComponent getNextComponentX(StructureStrongholdPieces$Stairs2 var1, List var2, Random var3, int var4, int var5) {
      switch(super.coordBaseMode) {
      case 0:
         return StructureStrongholdPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY + var4, super.boundingBox.minZ + var5, 1, this.getComponentType());
      case 1:
         return StructureStrongholdPieces.access$000(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var4, super.boundingBox.minZ - 1, 2, this.getComponentType());
      case 2:
         return StructureStrongholdPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY + var4, super.boundingBox.minZ + var5, 1, this.getComponentType());
      case 3:
         return StructureStrongholdPieces.access$000(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var4, super.boundingBox.minZ - 1, 2, this.getComponentType());
      default:
         return null;
      }
   }

   protected StructureComponent getNextComponentZ(StructureStrongholdPieces$Stairs2 var1, List var2, Random var3, int var4, int var5) {
      switch(super.coordBaseMode) {
      case 0:
         return StructureStrongholdPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY + var4, super.boundingBox.minZ + var5, 3, this.getComponentType());
      case 1:
         return StructureStrongholdPieces.access$000(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var4, super.boundingBox.maxZ + 1, 0, this.getComponentType());
      case 2:
         return StructureStrongholdPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY + var4, super.boundingBox.minZ + var5, 3, this.getComponentType());
      case 3:
         return StructureStrongholdPieces.access$000(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var4, super.boundingBox.maxZ + 1, 0, this.getComponentType());
      default:
         return null;
      }
   }

   protected static boolean canStrongholdGoDeeper(StructureBoundingBox var0) {
      return var0 != null && var0.minY > 10;
   }
}
