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
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold$Door;

public class StructureStrongholdPieces$ChestCorridor extends StructureStrongholdPieces$Stronghold {

   private static final WeightedRandomChestContent[] strongholdChestContents = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.ender_pearl, 0, 1, 1, 10), new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_chestplate, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_helmet, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_leggings, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_boots, 0, 1, 1, 5), new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 1), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 1), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)};
   private boolean hasMadeChest;


   public StructureStrongholdPieces$ChestCorridor() {}

   public StructureStrongholdPieces$ChestCorridor(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.field_143013_d = this.getRandomDoor(var2);
      super.boundingBox = var3;
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setBoolean("Chest", this.hasMadeChest);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.hasMadeChest = var1.getBoolean("Chest");
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      this.getNextComponentNormal((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
   }

   public static StructureStrongholdPieces$ChestCorridor findValidPlacement(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -1, 0, 5, 5, 7, var5);
      return canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureStrongholdPieces$ChestCorridor(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.isLiquidInStructureBoundingBox(var1, var3)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(var1, var3, 0, 0, 0, 4, 4, 6, true, var2, StructureStrongholdPieces.access$200());
         this.placeDoor(var1, var2, var3, super.field_143013_d, 1, 1, 0);
         this.placeDoor(var1, var2, var3, StructureStrongholdPieces$Stronghold$Door.OPENING, 1, 1, 6);
         this.fillWithBlocks(var1, var3, 3, 1, 2, 3, 1, 4, Blocks.stonebrick, Blocks.stonebrick, false);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 5, 3, 1, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 5, 3, 1, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 5, 3, 2, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 5, 3, 2, 4, var3);

         int var4;
         for(var4 = 2; var4 <= 4; ++var4) {
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 5, 2, 1, var4, var3);
         }

         if(!this.hasMadeChest) {
            var4 = this.getYWithOffset(2);
            int var5 = this.getXWithOffset(3, 3);
            int var6 = this.getZWithOffset(3, 3);
            if(var3.isVecInside(var5, var4, var6)) {
               this.hasMadeChest = true;
               this.generateStructureChestContents(var1, var3, var2, 3, 2, 3, WeightedRandomChestContent.func_92080_a(strongholdChestContents, new WeightedRandomChestContent[]{Items.enchanted_book.func_92114_b(var2)}), 2 + var2.nextInt(2));
            }
         }

         return true;
      }
   }

}
