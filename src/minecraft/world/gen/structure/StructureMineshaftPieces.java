package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$Corridor;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$Cross;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$Room;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$Stairs;

public class StructureMineshaftPieces {

   private static final WeightedRandomChestContent[] mineshaftChestContents = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5), new WeightedRandomChestContent(Items.dye, 4, 4, 9, 5), new WeightedRandomChestContent(Items.diamond, 0, 1, 2, 3), new WeightedRandomChestContent(Items.coal, 0, 3, 8, 10), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 1), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.rail), 0, 4, 8, 1), new WeightedRandomChestContent(Items.melon_seeds, 0, 2, 4, 10), new WeightedRandomChestContent(Items.pumpkin_seeds, 0, 2, 4, 10), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1)};


   public static void registerStructurePieces() {
      MapGenStructureIO.func_143031_a(StructureMineshaftPieces$Corridor.class, "MSCorridor");
      MapGenStructureIO.func_143031_a(StructureMineshaftPieces$Cross.class, "MSCrossing");
      MapGenStructureIO.func_143031_a(StructureMineshaftPieces$Room.class, "MSRoom");
      MapGenStructureIO.func_143031_a(StructureMineshaftPieces$Stairs.class, "MSStairs");
   }

   private static StructureComponent getRandomComponent(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = var1.nextInt(100);
      StructureBoundingBox var8;
      if(var7 >= 80) {
         var8 = StructureMineshaftPieces$Cross.findValidPlacement(var0, var1, var2, var3, var4, var5);
         if(var8 != null) {
            return new StructureMineshaftPieces$Cross(var6, var1, var8, var5);
         }
      } else if(var7 >= 70) {
         var8 = StructureMineshaftPieces$Stairs.findValidPlacement(var0, var1, var2, var3, var4, var5);
         if(var8 != null) {
            return new StructureMineshaftPieces$Stairs(var6, var1, var8, var5);
         }
      } else {
         var8 = StructureMineshaftPieces$Corridor.findValidPlacement(var0, var1, var2, var3, var4, var5);
         if(var8 != null) {
            return new StructureMineshaftPieces$Corridor(var6, var1, var8, var5);
         }
      }

      return null;
   }

   private static StructureComponent getNextMineShaftComponent(StructureComponent var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      if(var7 > 8) {
         return null;
      } else if(Math.abs(var3 - var0.getBoundingBox().minX) <= 80 && Math.abs(var5 - var0.getBoundingBox().minZ) <= 80) {
         StructureComponent var8 = getRandomComponent(var1, var2, var3, var4, var5, var6, var7 + 1);
         if(var8 != null) {
            var1.add(var8);
            var8.buildComponent(var0, var1, var2);
         }

         return var8;
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   static StructureComponent access$000(StructureComponent var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      return getNextMineShaftComponent(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   static WeightedRandomChestContent[] access$100() {
      return mineshaftChestContents;
   }

}
