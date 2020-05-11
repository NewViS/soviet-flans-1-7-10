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
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold;

public class StructureStrongholdPieces$Library extends StructureStrongholdPieces$Stronghold {

   private static final WeightedRandomChestContent[] strongholdLibraryChestContents = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.book, 0, 1, 3, 20), new WeightedRandomChestContent(Items.paper, 0, 2, 7, 20), new WeightedRandomChestContent(Items.map, 0, 1, 1, 1), new WeightedRandomChestContent(Items.compass, 0, 1, 1, 1)};
   private boolean isLargeRoom;


   public StructureStrongholdPieces$Library() {}

   public StructureStrongholdPieces$Library(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.field_143013_d = this.getRandomDoor(var2);
      super.boundingBox = var3;
      this.isLargeRoom = var3.getYSize() > 6;
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setBoolean("Tall", this.isLargeRoom);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.isLargeRoom = var1.getBoolean("Tall");
   }

   public static StructureStrongholdPieces$Library findValidPlacement(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -4, -1, 0, 14, 11, 15, var5);
      if(!canStrongholdGoDeeper(var7) || StructureComponent.findIntersecting(var0, var7) != null) {
         var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -4, -1, 0, 14, 6, 15, var5);
         if(!canStrongholdGoDeeper(var7) || StructureComponent.findIntersecting(var0, var7) != null) {
            return null;
         }
      }

      return new StructureStrongholdPieces$Library(var6, var1, var7, var5);
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.isLiquidInStructureBoundingBox(var1, var3)) {
         return false;
      } else {
         byte var4 = 11;
         if(!this.isLargeRoom) {
            var4 = 6;
         }

         this.fillWithRandomizedBlocks(var1, var3, 0, 0, 0, 13, var4 - 1, 14, true, var2, StructureStrongholdPieces.access$200());
         this.placeDoor(var1, var2, var3, super.field_143013_d, 4, 1, 0);
         this.randomlyFillWithBlocks(var1, var3, var2, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.web, Blocks.web, false);
         boolean var5 = true;
         boolean var6 = true;

         int var7;
         for(var7 = 1; var7 <= 13; ++var7) {
            if((var7 - 1) % 4 == 0) {
               this.fillWithBlocks(var1, var3, 1, 1, var7, 1, 4, var7, Blocks.planks, Blocks.planks, false);
               this.fillWithBlocks(var1, var3, 12, 1, var7, 12, 4, var7, Blocks.planks, Blocks.planks, false);
               this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 2, 3, var7, var3);
               this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, 11, 3, var7, var3);
               if(this.isLargeRoom) {
                  this.fillWithBlocks(var1, var3, 1, 6, var7, 1, 9, var7, Blocks.planks, Blocks.planks, false);
                  this.fillWithBlocks(var1, var3, 12, 6, var7, 12, 9, var7, Blocks.planks, Blocks.planks, false);
               }
            } else {
               this.fillWithBlocks(var1, var3, 1, 1, var7, 1, 4, var7, Blocks.bookshelf, Blocks.bookshelf, false);
               this.fillWithBlocks(var1, var3, 12, 1, var7, 12, 4, var7, Blocks.bookshelf, Blocks.bookshelf, false);
               if(this.isLargeRoom) {
                  this.fillWithBlocks(var1, var3, 1, 6, var7, 1, 9, var7, Blocks.bookshelf, Blocks.bookshelf, false);
                  this.fillWithBlocks(var1, var3, 12, 6, var7, 12, 9, var7, Blocks.bookshelf, Blocks.bookshelf, false);
               }
            }
         }

         for(var7 = 3; var7 < 12; var7 += 2) {
            this.fillWithBlocks(var1, var3, 3, 1, var7, 4, 3, var7, Blocks.bookshelf, Blocks.bookshelf, false);
            this.fillWithBlocks(var1, var3, 6, 1, var7, 7, 3, var7, Blocks.bookshelf, Blocks.bookshelf, false);
            this.fillWithBlocks(var1, var3, 9, 1, var7, 10, 3, var7, Blocks.bookshelf, Blocks.bookshelf, false);
         }

         if(this.isLargeRoom) {
            this.fillWithBlocks(var1, var3, 1, 5, 1, 3, 5, 13, Blocks.planks, Blocks.planks, false);
            this.fillWithBlocks(var1, var3, 10, 5, 1, 12, 5, 13, Blocks.planks, Blocks.planks, false);
            this.fillWithBlocks(var1, var3, 4, 5, 1, 9, 5, 2, Blocks.planks, Blocks.planks, false);
            this.fillWithBlocks(var1, var3, 4, 5, 12, 9, 5, 13, Blocks.planks, Blocks.planks, false);
            this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 9, 5, 11, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 8, 5, 11, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, 9, 5, 10, var3);
            this.fillWithBlocks(var1, var3, 3, 6, 2, 3, 6, 12, Blocks.fence, Blocks.fence, false);
            this.fillWithBlocks(var1, var3, 10, 6, 2, 10, 6, 10, Blocks.fence, Blocks.fence, false);
            this.fillWithBlocks(var1, var3, 4, 6, 2, 9, 6, 2, Blocks.fence, Blocks.fence, false);
            this.fillWithBlocks(var1, var3, 4, 6, 12, 8, 6, 12, Blocks.fence, Blocks.fence, false);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 9, 6, 11, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 8, 6, 11, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 9, 6, 10, var3);
            var7 = this.getMetadataWithOffset(Blocks.ladder, 3);
            this.placeBlockAtCurrentPosition(var1, Blocks.ladder, var7, 10, 1, 13, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.ladder, var7, 10, 2, 13, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.ladder, var7, 10, 3, 13, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.ladder, var7, 10, 4, 13, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.ladder, var7, 10, 5, 13, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.ladder, var7, 10, 6, 13, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.ladder, var7, 10, 7, 13, var3);
            byte var8 = 7;
            byte var9 = 7;
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, var8 - 1, 9, var9, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, var8, 9, var9, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, var8 - 1, 8, var9, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, var8, 8, var9, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, var8 - 1, 7, var9, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, var8, 7, var9, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, var8 - 2, 7, var9, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, var8 + 1, 7, var9, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, var8 - 1, 7, var9 - 1, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, var8 - 1, 7, var9 + 1, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, var8, 7, var9 - 1, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, var8, 7, var9 + 1, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, var8 - 2, 8, var9, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, var8 + 1, 8, var9, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, var8 - 1, 8, var9 - 1, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, var8 - 1, 8, var9 + 1, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, var8, 8, var9 - 1, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.torch, 0, var8, 8, var9 + 1, var3);
         }

         this.generateStructureChestContents(var1, var3, var2, 3, 3, 5, WeightedRandomChestContent.func_92080_a(strongholdLibraryChestContents, new WeightedRandomChestContent[]{Items.enchanted_book.func_92112_a(var2, 1, 5, 2)}), 1 + var2.nextInt(4));
         if(this.isLargeRoom) {
            this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 12, 9, 1, var3);
            this.generateStructureChestContents(var1, var3, var2, 12, 8, 1, WeightedRandomChestContent.func_92080_a(strongholdLibraryChestContents, new WeightedRandomChestContent[]{Items.enchanted_book.func_92112_a(var2, 1, 5, 2)}), 1 + var2.nextInt(4));
         }

         return true;
      }
   }

}
