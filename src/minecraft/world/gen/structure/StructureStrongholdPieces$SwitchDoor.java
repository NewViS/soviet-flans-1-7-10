package net.minecraft.world.gen.structure;

import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold$Door;

// $FF: synthetic class
class StructureStrongholdPieces$SwitchDoor {

   // $FF: synthetic field
   static final int[] doorEnum = new int[StructureStrongholdPieces$Stronghold$Door.values().length];


   static {
      try {
         doorEnum[StructureStrongholdPieces$Stronghold$Door.OPENING.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
         ;
      }

      try {
         doorEnum[StructureStrongholdPieces$Stronghold$Door.WOOD_DOOR.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
         ;
      }

      try {
         doorEnum[StructureStrongholdPieces$Stronghold$Door.GRATES.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
         ;
      }

      try {
         doorEnum[StructureStrongholdPieces$Stronghold$Door.IRON_DOOR.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
         ;
      }

   }
}
