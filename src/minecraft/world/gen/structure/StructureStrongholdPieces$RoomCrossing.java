package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs2;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold;

public class StructureStrongholdPieces$RoomCrossing extends StructureStrongholdPieces$Stronghold {

   private static final WeightedRandomChestContent[] strongholdRoomCrossingChestContents = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5), new WeightedRandomChestContent(Items.coal, 0, 3, 8, 10), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 1)};
   protected int roomType;


   public StructureStrongholdPieces$RoomCrossing() {}

   public StructureStrongholdPieces$RoomCrossing(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.field_143013_d = this.getRandomDoor(var2);
      super.boundingBox = var3;
      this.roomType = var2.nextInt(5);
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setInteger("Type", this.roomType);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.roomType = var1.getInteger("Type");
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      this.getNextComponentNormal((StructureStrongholdPieces$Stairs2)var1, var2, var3, 4, 1);
      this.getNextComponentX((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 4);
      this.getNextComponentZ((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 4);
   }

   public static StructureStrongholdPieces$RoomCrossing findValidPlacement(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -4, -1, 0, 11, 7, 11, var5);
      return canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureStrongholdPieces$RoomCrossing(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.isLiquidInStructureBoundingBox(var1, var3)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(var1, var3, 0, 0, 0, 10, 6, 10, true, var2, StructureStrongholdPieces.access$200());
         this.placeDoor(var1, var2, var3, super.field_143013_d, 4, 1, 0);
         this.fillWithBlocks(var1, var3, 4, 1, 10, 6, 3, 10, Blocks.air, Blocks.air, false);
         this.fillWithBlocks(var1, var3, 0, 1, 4, 0, 3, 6, Blocks.air, Blocks.air, false);
         this.fillWithBlocks(var1, var3, 10, 1, 4, 10, 3, 6, Blocks.air, Blocks.air, false);
         int var4;
         switch(this.roomType) {
         case 0:
            this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 5, 1, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 5, 2, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 5, 3, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 4, 3, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 6, 3, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 5, 3, 4, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 5, 3, 6, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 4, 1, 4, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 4, 1, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 4, 1, 6, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 6, 1, 4, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 6, 1, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 6, 1, 6, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 5, 1, 4, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 0, 5, 1, 6, var3);
            break;
         case 1:
            for(var4 = 0; var4 < 5; ++var4) {
               this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 3, 1, 3 + var4, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 7, 1, 3 + var4, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 3 + var4, 1, 3, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 3 + var4, 1, 7, var3);
            }

            this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 5, 1, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 5, 2, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 0, 5, 3, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.flowing_water, 0, 5, 4, 5, var3);
            break;
         case 2:
            for(var4 = 1; var4 <= 9; ++var4) {
               this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 1, 3, var4, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 9, 3, var4, var3);
            }

            for(var4 = 1; var4 <= 9; ++var4) {
               this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, var4, 3, 1, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, var4, 3, 9, var3);
            }

            this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 5, 1, 4, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 5, 1, 6, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 5, 3, 4, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 5, 3, 6, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 4, 1, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 6, 1, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 4, 3, 5, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 6, 3, 5, var3);

            for(var4 = 1; var4 <= 3; ++var4) {
               this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 4, var4, 4, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 6, var4, 4, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 4, var4, 6, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.cobblestone, 0, 6, var4, 6, var3);
            }

            this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 5, 3, 5, var3);

            for(var4 = 2; var4 <= 8; ++var4) {
               this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 2, 3, var4, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 3, 3, var4, var3);
               if(var4 <= 3 || var4 >= 7) {
                  this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 4, 3, var4, var3);
                  this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 5, 3, var4, var3);
                  this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 6, 3, var4, var3);
               }

               this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 7, 3, var4, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 8, 3, var4, var3);
            }

            this.placeBlockAtCurrentPosition(var1, Blocks.ladder, this.getMetadataWithOffset(Blocks.ladder, 4), 9, 1, 3, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.ladder, this.getMetadataWithOffset(Blocks.ladder, 4), 9, 2, 3, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.ladder, this.getMetadataWithOffset(Blocks.ladder, 4), 9, 3, 3, var3);
            this.generateStructureChestContents(var1, var3, var2, 3, 4, 8, WeightedRandomChestContent.func_92080_a(strongholdRoomCrossingChestContents, new WeightedRandomChestContent[]{Items.enchanted_book.func_92114_b(var2)}), 1 + var2.nextInt(4));
         }

         return true;
      }
   }

}
