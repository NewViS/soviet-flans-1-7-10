package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Direction;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$Feature;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentScatteredFeaturePieces$DesertPyramid extends ComponentScatteredFeaturePieces$Feature {

   private boolean[] field_74940_h = new boolean[4];
   private static final WeightedRandomChestContent[] itemsToGenerateInTemple = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 7, 15), new WeightedRandomChestContent(Items.emerald, 0, 1, 3, 2), new WeightedRandomChestContent(Items.bone, 0, 4, 6, 20), new WeightedRandomChestContent(Items.rotten_flesh, 0, 3, 7, 16), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)};


   public ComponentScatteredFeaturePieces$DesertPyramid() {}

   public ComponentScatteredFeaturePieces$DesertPyramid(Random var1, int var2, int var3) {
      super(var1, var2, 64, var3, 21, 15, 21);
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setBoolean("hasPlacedChest0", this.field_74940_h[0]);
      var1.setBoolean("hasPlacedChest1", this.field_74940_h[1]);
      var1.setBoolean("hasPlacedChest2", this.field_74940_h[2]);
      var1.setBoolean("hasPlacedChest3", this.field_74940_h[3]);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.field_74940_h[0] = var1.getBoolean("hasPlacedChest0");
      this.field_74940_h[1] = var1.getBoolean("hasPlacedChest1");
      this.field_74940_h[2] = var1.getBoolean("hasPlacedChest2");
      this.field_74940_h[3] = var1.getBoolean("hasPlacedChest3");
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      this.fillWithBlocks(var1, var3, 0, -4, 0, super.scatteredFeatureSizeX - 1, 0, super.scatteredFeatureSizeZ - 1, Blocks.sandstone, Blocks.sandstone, false);

      int var4;
      for(var4 = 1; var4 <= 9; ++var4) {
         this.fillWithBlocks(var1, var3, var4, var4, var4, super.scatteredFeatureSizeX - 1 - var4, var4, super.scatteredFeatureSizeZ - 1 - var4, Blocks.sandstone, Blocks.sandstone, false);
         this.fillWithBlocks(var1, var3, var4 + 1, var4, var4 + 1, super.scatteredFeatureSizeX - 2 - var4, var4, super.scatteredFeatureSizeZ - 2 - var4, Blocks.air, Blocks.air, false);
      }

      int var5;
      for(var4 = 0; var4 < super.scatteredFeatureSizeX; ++var4) {
         for(var5 = 0; var5 < super.scatteredFeatureSizeZ; ++var5) {
            byte var6 = -5;
            this.func_151554_b(var1, Blocks.sandstone, 0, var4, var6, var5, var3);
         }
      }

      var4 = this.getMetadataWithOffset(Blocks.sandstone_stairs, 3);
      var5 = this.getMetadataWithOffset(Blocks.sandstone_stairs, 2);
      int var13 = this.getMetadataWithOffset(Blocks.sandstone_stairs, 0);
      int var7 = this.getMetadataWithOffset(Blocks.sandstone_stairs, 1);
      byte var8 = 1;
      byte var9 = 11;
      this.fillWithBlocks(var1, var3, 0, 0, 0, 4, 9, 4, Blocks.sandstone, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 1, 10, 1, 3, 10, 3, Blocks.sandstone, Blocks.sandstone, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var4, 2, 10, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var5, 2, 10, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var13, 0, 10, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var7, 4, 10, 2, var3);
      this.fillWithBlocks(var1, var3, super.scatteredFeatureSizeX - 5, 0, 0, super.scatteredFeatureSizeX - 1, 9, 4, Blocks.sandstone, Blocks.air, false);
      this.fillWithBlocks(var1, var3, super.scatteredFeatureSizeX - 4, 10, 1, super.scatteredFeatureSizeX - 2, 10, 3, Blocks.sandstone, Blocks.sandstone, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var4, super.scatteredFeatureSizeX - 3, 10, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var5, super.scatteredFeatureSizeX - 3, 10, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var13, super.scatteredFeatureSizeX - 5, 10, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var7, super.scatteredFeatureSizeX - 1, 10, 2, var3);
      this.fillWithBlocks(var1, var3, 8, 0, 0, 12, 4, 4, Blocks.sandstone, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 9, 1, 0, 11, 3, 4, Blocks.air, Blocks.air, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, 9, 1, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, 9, 2, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, 9, 3, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, 10, 3, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, 11, 3, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, 11, 2, 1, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, 11, 1, 1, var3);
      this.fillWithBlocks(var1, var3, 4, 1, 1, 8, 3, 3, Blocks.sandstone, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 4, 1, 2, 8, 2, 2, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 12, 1, 1, 16, 3, 3, Blocks.sandstone, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 12, 1, 2, 16, 2, 2, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 5, 4, 5, super.scatteredFeatureSizeX - 6, 4, super.scatteredFeatureSizeZ - 6, Blocks.sandstone, Blocks.sandstone, false);
      this.fillWithBlocks(var1, var3, 9, 4, 9, 11, 4, 11, Blocks.air, Blocks.air, false);
      this.fillWithMetadataBlocks(var1, var3, 8, 1, 8, 8, 3, 8, Blocks.sandstone, 2, Blocks.sandstone, 2, false);
      this.fillWithMetadataBlocks(var1, var3, 12, 1, 8, 12, 3, 8, Blocks.sandstone, 2, Blocks.sandstone, 2, false);
      this.fillWithMetadataBlocks(var1, var3, 8, 1, 12, 8, 3, 12, Blocks.sandstone, 2, Blocks.sandstone, 2, false);
      this.fillWithMetadataBlocks(var1, var3, 12, 1, 12, 12, 3, 12, Blocks.sandstone, 2, Blocks.sandstone, 2, false);
      this.fillWithBlocks(var1, var3, 1, 1, 5, 4, 4, 11, Blocks.sandstone, Blocks.sandstone, false);
      this.fillWithBlocks(var1, var3, super.scatteredFeatureSizeX - 5, 1, 5, super.scatteredFeatureSizeX - 2, 4, 11, Blocks.sandstone, Blocks.sandstone, false);
      this.fillWithBlocks(var1, var3, 6, 7, 9, 6, 7, 11, Blocks.sandstone, Blocks.sandstone, false);
      this.fillWithBlocks(var1, var3, super.scatteredFeatureSizeX - 7, 7, 9, super.scatteredFeatureSizeX - 7, 7, 11, Blocks.sandstone, Blocks.sandstone, false);
      this.fillWithMetadataBlocks(var1, var3, 5, 5, 9, 5, 7, 11, Blocks.sandstone, 2, Blocks.sandstone, 2, false);
      this.fillWithMetadataBlocks(var1, var3, super.scatteredFeatureSizeX - 6, 5, 9, super.scatteredFeatureSizeX - 6, 7, 11, Blocks.sandstone, 2, Blocks.sandstone, 2, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 5, 5, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 5, 6, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 6, 6, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, super.scatteredFeatureSizeX - 6, 5, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, super.scatteredFeatureSizeX - 6, 6, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, super.scatteredFeatureSizeX - 7, 6, 10, var3);
      this.fillWithBlocks(var1, var3, 2, 4, 4, 2, 6, 4, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, super.scatteredFeatureSizeX - 3, 4, 4, super.scatteredFeatureSizeX - 3, 6, 4, Blocks.air, Blocks.air, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var4, 2, 4, 5, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var4, 2, 3, 4, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var4, super.scatteredFeatureSizeX - 3, 4, 5, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var4, super.scatteredFeatureSizeX - 3, 3, 4, var3);
      this.fillWithBlocks(var1, var3, 1, 1, 3, 2, 2, 3, Blocks.sandstone, Blocks.sandstone, false);
      this.fillWithBlocks(var1, var3, super.scatteredFeatureSizeX - 3, 1, 3, super.scatteredFeatureSizeX - 2, 2, 3, Blocks.sandstone, Blocks.sandstone, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, 0, 1, 1, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, 0, super.scatteredFeatureSizeX - 2, 1, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 1, 1, 2, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.stone_slab, 1, super.scatteredFeatureSizeX - 2, 2, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var7, 2, 1, 2, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone_stairs, var13, super.scatteredFeatureSizeX - 3, 1, 2, var3);
      this.fillWithBlocks(var1, var3, 4, 3, 5, 4, 3, 18, Blocks.sandstone, Blocks.sandstone, false);
      this.fillWithBlocks(var1, var3, super.scatteredFeatureSizeX - 5, 3, 5, super.scatteredFeatureSizeX - 5, 3, 17, Blocks.sandstone, Blocks.sandstone, false);
      this.fillWithBlocks(var1, var3, 3, 1, 5, 4, 2, 16, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, super.scatteredFeatureSizeX - 6, 1, 5, super.scatteredFeatureSizeX - 5, 2, 16, Blocks.air, Blocks.air, false);

      int var10;
      for(var10 = 5; var10 <= 17; var10 += 2) {
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, 4, 1, var10, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 1, 4, 2, var10, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, super.scatteredFeatureSizeX - 5, 1, var10, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 1, super.scatteredFeatureSizeX - 5, 2, var10, var3);
      }

      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 10, 0, 7, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 10, 0, 8, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 9, 0, 9, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 11, 0, 9, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 8, 0, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 12, 0, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 7, 0, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 13, 0, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 9, 0, 11, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 11, 0, 11, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 10, 0, 12, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 10, 0, 13, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var9, 10, 0, 10, var3);

      for(var10 = 0; var10 <= super.scatteredFeatureSizeX - 1; var10 += super.scatteredFeatureSizeX - 1) {
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10, 2, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 2, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10, 2, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10, 3, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 3, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10, 3, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 4, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 1, var10, 4, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 4, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10, 5, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 5, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10, 5, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 6, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 1, var10, 6, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 6, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 7, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 7, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 7, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10, 8, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10, 8, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10, 8, 3, var3);
      }

      for(var10 = 2; var10 <= super.scatteredFeatureSizeX - 3; var10 += super.scatteredFeatureSizeX - 3 - 2) {
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10 - 1, 2, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 2, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10 + 1, 2, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10 - 1, 3, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 3, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10 + 1, 3, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10 - 1, 4, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 1, var10, 4, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10 + 1, 4, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10 - 1, 5, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 5, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10 + 1, 5, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10 - 1, 6, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 1, var10, 6, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10 + 1, 6, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10 - 1, 7, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10, 7, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, var10 + 1, 7, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10 - 1, 8, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10, 8, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, var10 + 1, 8, 0, var3);
      }

      this.fillWithMetadataBlocks(var1, var3, 8, 4, 0, 12, 6, 0, Blocks.sandstone, 2, Blocks.sandstone, 2, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 8, 6, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 12, 6, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 9, 5, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 1, 10, 5, 0, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.wool, var8, 11, 5, 0, var3);
      this.fillWithMetadataBlocks(var1, var3, 8, -14, 8, 12, -11, 12, Blocks.sandstone, 2, Blocks.sandstone, 2, false);
      this.fillWithMetadataBlocks(var1, var3, 8, -10, 8, 12, -10, 12, Blocks.sandstone, 1, Blocks.sandstone, 1, false);
      this.fillWithMetadataBlocks(var1, var3, 8, -9, 8, 12, -9, 12, Blocks.sandstone, 2, Blocks.sandstone, 2, false);
      this.fillWithBlocks(var1, var3, 8, -8, 8, 12, -1, 12, Blocks.sandstone, Blocks.sandstone, false);
      this.fillWithBlocks(var1, var3, 9, -11, 9, 11, -1, 11, Blocks.air, Blocks.air, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.stone_pressure_plate, 0, 10, -11, 10, var3);
      this.fillWithBlocks(var1, var3, 9, -13, 9, 11, -13, 11, Blocks.tnt, Blocks.air, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 8, -11, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 8, -10, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 1, 7, -10, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, 7, -11, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 12, -11, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 12, -10, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 1, 13, -10, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, 13, -11, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 10, -11, 8, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 10, -10, 8, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 1, 10, -10, 7, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, 10, -11, 7, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 10, -11, 12, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 10, -10, 12, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 1, 10, -10, 13, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.sandstone, 2, 10, -11, 13, var3);

      for(var10 = 0; var10 < 4; ++var10) {
         if(!this.field_74940_h[var10]) {
            int var11 = Direction.offsetX[var10] * 2;
            int var12 = Direction.offsetZ[var10] * 2;
            this.field_74940_h[var10] = this.generateStructureChestContents(var1, var3, var2, 10 + var11, -11, 10 + var12, WeightedRandomChestContent.func_92080_a(itemsToGenerateInTemple, new WeightedRandomChestContent[]{Items.enchanted_book.func_92114_b(var2)}), 2 + var2.nextInt(5));
         }
      }

      return true;
   }

}
