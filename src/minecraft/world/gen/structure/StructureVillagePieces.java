package net.minecraft.world.gen.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces$Church;
import net.minecraft.world.gen.structure.StructureVillagePieces$Field1;
import net.minecraft.world.gen.structure.StructureVillagePieces$Field2;
import net.minecraft.world.gen.structure.StructureVillagePieces$Hall;
import net.minecraft.world.gen.structure.StructureVillagePieces$House1;
import net.minecraft.world.gen.structure.StructureVillagePieces$House2;
import net.minecraft.world.gen.structure.StructureVillagePieces$House3;
import net.minecraft.world.gen.structure.StructureVillagePieces$House4Garden;
import net.minecraft.world.gen.structure.StructureVillagePieces$Path;
import net.minecraft.world.gen.structure.StructureVillagePieces$PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces$Start;
import net.minecraft.world.gen.structure.StructureVillagePieces$Torch;
import net.minecraft.world.gen.structure.StructureVillagePieces$Village;
import net.minecraft.world.gen.structure.StructureVillagePieces$Well;
import net.minecraft.world.gen.structure.StructureVillagePieces$WoodHut;

public class StructureVillagePieces {

   public static void registerVillagePieces() {
      MapGenStructureIO.func_143031_a(StructureVillagePieces$House1.class, "ViBH");
      MapGenStructureIO.func_143031_a(StructureVillagePieces$Field1.class, "ViDF");
      MapGenStructureIO.func_143031_a(StructureVillagePieces$Field2.class, "ViF");
      MapGenStructureIO.func_143031_a(StructureVillagePieces$Torch.class, "ViL");
      MapGenStructureIO.func_143031_a(StructureVillagePieces$Hall.class, "ViPH");
      MapGenStructureIO.func_143031_a(StructureVillagePieces$House4Garden.class, "ViSH");
      MapGenStructureIO.func_143031_a(StructureVillagePieces$WoodHut.class, "ViSmH");
      MapGenStructureIO.func_143031_a(StructureVillagePieces$Church.class, "ViST");
      MapGenStructureIO.func_143031_a(StructureVillagePieces$House2.class, "ViS");
      MapGenStructureIO.func_143031_a(StructureVillagePieces$Start.class, "ViStart");
      MapGenStructureIO.func_143031_a(StructureVillagePieces$Path.class, "ViSR");
      MapGenStructureIO.func_143031_a(StructureVillagePieces$House3.class, "ViTRH");
      MapGenStructureIO.func_143031_a(StructureVillagePieces$Well.class, "ViW");
   }

   public static List getStructureVillageWeightedPieceList(Random var0, int var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$House4Garden.class, 4, MathHelper.getRandomIntegerInRange(var0, 2 + var1, 4 + var1 * 2)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$Church.class, 20, MathHelper.getRandomIntegerInRange(var0, 0 + var1, 1 + var1)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$House1.class, 20, MathHelper.getRandomIntegerInRange(var0, 0 + var1, 2 + var1)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$WoodHut.class, 3, MathHelper.getRandomIntegerInRange(var0, 2 + var1, 5 + var1 * 3)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$Hall.class, 15, MathHelper.getRandomIntegerInRange(var0, 0 + var1, 2 + var1)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$Field1.class, 3, MathHelper.getRandomIntegerInRange(var0, 1 + var1, 4 + var1)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$Field2.class, 3, MathHelper.getRandomIntegerInRange(var0, 2 + var1, 4 + var1 * 2)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$House2.class, 15, MathHelper.getRandomIntegerInRange(var0, 0, 1 + var1)));
      var2.add(new StructureVillagePieces$PieceWeight(StructureVillagePieces$House3.class, 8, MathHelper.getRandomIntegerInRange(var0, 0 + var1, 3 + var1 * 2)));
      Iterator var3 = var2.iterator();

      while(var3.hasNext()) {
         if(((StructureVillagePieces$PieceWeight)var3.next()).villagePiecesLimit == 0) {
            var3.remove();
         }
      }

      return var2;
   }

   private static int func_75079_a(List var0) {
      boolean var1 = false;
      int var2 = 0;

      StructureVillagePieces$PieceWeight var4;
      for(Iterator var3 = var0.iterator(); var3.hasNext(); var2 += var4.villagePieceWeight) {
         var4 = (StructureVillagePieces$PieceWeight)var3.next();
         if(var4.villagePiecesLimit > 0 && var4.villagePiecesSpawned < var4.villagePiecesLimit) {
            var1 = true;
         }
      }

      return var1?var2:-1;
   }

   private static StructureVillagePieces$Village func_75083_a(StructureVillagePieces$Start var0, StructureVillagePieces$PieceWeight var1, List var2, Random var3, int var4, int var5, int var6, int var7, int var8) {
      Class var9 = var1.villagePieceClass;
      Object var10 = null;
      if(var9 == StructureVillagePieces$House4Garden.class) {
         var10 = StructureVillagePieces$House4Garden.func_74912_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if(var9 == StructureVillagePieces$Church.class) {
         var10 = StructureVillagePieces$Church.func_74919_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if(var9 == StructureVillagePieces$House1.class) {
         var10 = StructureVillagePieces$House1.func_74898_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if(var9 == StructureVillagePieces$WoodHut.class) {
         var10 = StructureVillagePieces$WoodHut.func_74908_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if(var9 == StructureVillagePieces$Hall.class) {
         var10 = StructureVillagePieces$Hall.func_74906_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if(var9 == StructureVillagePieces$Field1.class) {
         var10 = StructureVillagePieces$Field1.func_74900_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if(var9 == StructureVillagePieces$Field2.class) {
         var10 = StructureVillagePieces$Field2.func_74902_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if(var9 == StructureVillagePieces$House2.class) {
         var10 = StructureVillagePieces$House2.func_74915_a(var0, var2, var3, var4, var5, var6, var7, var8);
      } else if(var9 == StructureVillagePieces$House3.class) {
         var10 = StructureVillagePieces$House3.func_74921_a(var0, var2, var3, var4, var5, var6, var7, var8);
      }

      return (StructureVillagePieces$Village)var10;
   }

   private static StructureVillagePieces$Village getNextVillageComponent(StructureVillagePieces$Start var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      int var8 = func_75079_a(var0.structureVillageWeightedPieceList);
      if(var8 <= 0) {
         return null;
      } else {
         int var9 = 0;

         while(var9 < 5) {
            ++var9;
            int var10 = var2.nextInt(var8);
            Iterator var11 = var0.structureVillageWeightedPieceList.iterator();

            while(var11.hasNext()) {
               StructureVillagePieces$PieceWeight var12 = (StructureVillagePieces$PieceWeight)var11.next();
               var10 -= var12.villagePieceWeight;
               if(var10 < 0) {
                  if(!var12.canSpawnMoreVillagePiecesOfType(var7) || var12 == var0.structVillagePieceWeight && var0.structureVillageWeightedPieceList.size() > 1) {
                     break;
                  }

                  StructureVillagePieces$Village var13 = func_75083_a(var0, var12, var1, var2, var3, var4, var5, var6, var7);
                  if(var13 != null) {
                     ++var12.villagePiecesSpawned;
                     var0.structVillagePieceWeight = var12;
                     if(!var12.canSpawnMoreVillagePieces()) {
                        var0.structureVillageWeightedPieceList.remove(var12);
                     }

                     return var13;
                  }
               }
            }
         }

         StructureBoundingBox var14 = StructureVillagePieces$Torch.func_74904_a(var0, var1, var2, var3, var4, var5, var6);
         if(var14 != null) {
            return new StructureVillagePieces$Torch(var0, var7, var2, var14, var6);
         } else {
            return null;
         }
      }
   }

   private static StructureComponent getNextVillageStructureComponent(StructureVillagePieces$Start var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      if(var7 > 50) {
         return null;
      } else if(Math.abs(var3 - var0.getBoundingBox().minX) <= 112 && Math.abs(var5 - var0.getBoundingBox().minZ) <= 112) {
         StructureVillagePieces$Village var8 = getNextVillageComponent(var0, var1, var2, var3, var4, var5, var6, var7 + 1);
         if(var8 != null) {
            int var9 = (var8.boundingBox.minX + var8.boundingBox.maxX) / 2;
            int var10 = (var8.boundingBox.minZ + var8.boundingBox.maxZ) / 2;
            int var11 = var8.boundingBox.maxX - var8.boundingBox.minX;
            int var12 = var8.boundingBox.maxZ - var8.boundingBox.minZ;
            int var13 = var11 > var12?var11:var12;
            if(var0.getWorldChunkManager().areBiomesViable(var9, var10, var13 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
               var1.add(var8);
               var0.field_74932_i.add(var8);
               return var8;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private static StructureComponent getNextComponentVillagePath(StructureVillagePieces$Start var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      if(var7 > 3 + var0.terrainType) {
         return null;
      } else if(Math.abs(var3 - var0.getBoundingBox().minX) <= 112 && Math.abs(var5 - var0.getBoundingBox().minZ) <= 112) {
         StructureBoundingBox var8 = StructureVillagePieces$Path.func_74933_a(var0, var1, var2, var3, var4, var5, var6);
         if(var8 != null && var8.minY > 10) {
            StructureVillagePieces$Path var9 = new StructureVillagePieces$Path(var0, var7, var2, var8, var6);
            int var10 = (var9.boundingBox.minX + var9.boundingBox.maxX) / 2;
            int var11 = (var9.boundingBox.minZ + var9.boundingBox.maxZ) / 2;
            int var12 = var9.boundingBox.maxX - var9.boundingBox.minX;
            int var13 = var9.boundingBox.maxZ - var9.boundingBox.minZ;
            int var14 = var12 > var13?var12:var13;
            if(var0.getWorldChunkManager().areBiomesViable(var10, var11, var14 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
               var1.add(var9);
               var0.field_74930_j.add(var9);
               return var9;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   static StructureComponent access$000(StructureVillagePieces$Start var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      return getNextVillageStructureComponent(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   static StructureComponent access$100(StructureVillagePieces$Start var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      return getNextComponentVillagePath(var0, var1, var2, var3, var4, var5, var6, var7);
   }
}
