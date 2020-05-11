package net.minecraft.world.gen.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$1;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$2;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$ChestCorridor;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Corridor;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Crossing;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$LeftTurn;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Library;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$PieceWeight;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$PortalRoom;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Prison;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$RightTurn;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$RoomCrossing;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs2;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$StairsStraight;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stones;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Straight;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold;

public class StructureStrongholdPieces {

   private static final StructureStrongholdPieces$PieceWeight[] pieceWeightArray = new StructureStrongholdPieces$PieceWeight[]{new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$Straight.class, 40, 0), new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$Prison.class, 5, 5), new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$LeftTurn.class, 20, 0), new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$RightTurn.class, 20, 0), new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$RoomCrossing.class, 10, 6), new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$StairsStraight.class, 5, 5), new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$Stairs.class, 5, 5), new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$Crossing.class, 5, 4), new StructureStrongholdPieces$PieceWeight(StructureStrongholdPieces$ChestCorridor.class, 5, 4), new StructureStrongholdPieces$1(StructureStrongholdPieces$Library.class, 10, 2), new StructureStrongholdPieces$2(StructureStrongholdPieces$PortalRoom.class, 20, 1)};
   private static List structurePieceList;
   private static Class strongComponentType;
   static int totalWeight;
   private static final StructureStrongholdPieces$Stones strongholdStones = new StructureStrongholdPieces$Stones((StructureStrongholdPieces$1)null);


   public static void registerStrongholdPieces() {
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$ChestCorridor.class, "SHCC");
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$Corridor.class, "SHFC");
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$Crossing.class, "SH5C");
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$LeftTurn.class, "SHLT");
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$Library.class, "SHLi");
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$PortalRoom.class, "SHPR");
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$Prison.class, "SHPH");
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$RightTurn.class, "SHRT");
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$RoomCrossing.class, "SHRC");
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$Stairs.class, "SHSD");
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$Stairs2.class, "SHStart");
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$Straight.class, "SHS");
      MapGenStructureIO.func_143031_a(StructureStrongholdPieces$StairsStraight.class, "SHSSD");
   }

   public static void prepareStructurePieces() {
      structurePieceList = new ArrayList();
      StructureStrongholdPieces$PieceWeight[] var0 = pieceWeightArray;
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         StructureStrongholdPieces$PieceWeight var3 = var0[var2];
         var3.instancesSpawned = 0;
         structurePieceList.add(var3);
      }

      strongComponentType = null;
   }

   private static boolean canAddStructurePieces() {
      boolean var0 = false;
      totalWeight = 0;

      StructureStrongholdPieces$PieceWeight var2;
      for(Iterator var1 = structurePieceList.iterator(); var1.hasNext(); totalWeight += var2.pieceWeight) {
         var2 = (StructureStrongholdPieces$PieceWeight)var1.next();
         if(var2.instancesLimit > 0 && var2.instancesSpawned < var2.instancesLimit) {
            var0 = true;
         }
      }

      return var0;
   }

   private static StructureStrongholdPieces$Stronghold getStrongholdComponentFromWeightedPiece(Class var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      Object var8 = null;
      if(var0 == StructureStrongholdPieces$Straight.class) {
         var8 = StructureStrongholdPieces$Straight.findValidPlacement(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == StructureStrongholdPieces$Prison.class) {
         var8 = StructureStrongholdPieces$Prison.findValidPlacement(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == StructureStrongholdPieces$LeftTurn.class) {
         var8 = StructureStrongholdPieces$LeftTurn.findValidPlacement(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == StructureStrongholdPieces$RightTurn.class) {
         var8 = StructureStrongholdPieces$RightTurn.findValidPlacement(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == StructureStrongholdPieces$RoomCrossing.class) {
         var8 = StructureStrongholdPieces$RoomCrossing.findValidPlacement(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == StructureStrongholdPieces$StairsStraight.class) {
         var8 = StructureStrongholdPieces$StairsStraight.findValidPlacement(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == StructureStrongholdPieces$Stairs.class) {
         var8 = StructureStrongholdPieces$Stairs.getStrongholdStairsComponent(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == StructureStrongholdPieces$Crossing.class) {
         var8 = StructureStrongholdPieces$Crossing.findValidPlacement(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == StructureStrongholdPieces$ChestCorridor.class) {
         var8 = StructureStrongholdPieces$ChestCorridor.findValidPlacement(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == StructureStrongholdPieces$Library.class) {
         var8 = StructureStrongholdPieces$Library.findValidPlacement(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == StructureStrongholdPieces$PortalRoom.class) {
         var8 = StructureStrongholdPieces$PortalRoom.findValidPlacement(var1, var2, var3, var4, var5, var6, var7);
      }

      return (StructureStrongholdPieces$Stronghold)var8;
   }

   private static StructureStrongholdPieces$Stronghold getNextComponent(StructureStrongholdPieces$Stairs2 var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      if(!canAddStructurePieces()) {
         return null;
      } else {
         if(strongComponentType != null) {
            StructureStrongholdPieces$Stronghold var8 = getStrongholdComponentFromWeightedPiece(strongComponentType, var1, var2, var3, var4, var5, var6, var7);
            strongComponentType = null;
            if(var8 != null) {
               return var8;
            }
         }

         int var13 = 0;

         while(var13 < 5) {
            ++var13;
            int var9 = var2.nextInt(totalWeight);
            Iterator var10 = structurePieceList.iterator();

            while(var10.hasNext()) {
               StructureStrongholdPieces$PieceWeight var11 = (StructureStrongholdPieces$PieceWeight)var10.next();
               var9 -= var11.pieceWeight;
               if(var9 < 0) {
                  if(!var11.canSpawnMoreStructuresOfType(var7) || var11 == var0.strongholdPieceWeight) {
                     break;
                  }

                  StructureStrongholdPieces$Stronghold var12 = getStrongholdComponentFromWeightedPiece(var11.pieceClass, var1, var2, var3, var4, var5, var6, var7);
                  if(var12 != null) {
                     ++var11.instancesSpawned;
                     var0.strongholdPieceWeight = var11;
                     if(!var11.canSpawnMoreStructures()) {
                        structurePieceList.remove(var11);
                     }

                     return var12;
                  }
               }
            }
         }

         StructureBoundingBox var14 = StructureStrongholdPieces$Corridor.func_74992_a(var1, var2, var3, var4, var5, var6);
         if(var14 != null && var14.minY > 1) {
            return new StructureStrongholdPieces$Corridor(var7, var2, var14, var6);
         } else {
            return null;
         }
      }
   }

   private static StructureComponent getNextValidComponent(StructureStrongholdPieces$Stairs2 var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      if(var7 > 50) {
         return null;
      } else if(Math.abs(var3 - var0.getBoundingBox().minX) <= 112 && Math.abs(var5 - var0.getBoundingBox().minZ) <= 112) {
         StructureStrongholdPieces$Stronghold var8 = getNextComponent(var0, var1, var2, var3, var4, var5, var6, var7 + 1);
         if(var8 != null) {
            var1.add(var8);
            var0.field_75026_c.add(var8);
         }

         return var8;
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   static StructureComponent access$000(StructureStrongholdPieces$Stairs2 var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      return getNextValidComponent(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   static Class access$102(Class var0) {
      strongComponentType = var0;
      return var0;
   }

   // $FF: synthetic method
   static StructureStrongholdPieces$Stones access$200() {
      return strongholdStones;
   }

}
