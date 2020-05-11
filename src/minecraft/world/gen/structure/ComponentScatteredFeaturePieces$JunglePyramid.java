package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.block.BlockLever;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$1;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$Feature;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$JunglePyramid$Stones;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentScatteredFeaturePieces$JunglePyramid extends ComponentScatteredFeaturePieces$Feature {

   private boolean field_74947_h;
   private boolean field_74948_i;
   private boolean field_74945_j;
   private boolean field_74946_k;
   private static final WeightedRandomChestContent[] junglePyramidsChestContents = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 7, 15), new WeightedRandomChestContent(Items.emerald, 0, 1, 3, 2), new WeightedRandomChestContent(Items.bone, 0, 4, 6, 20), new WeightedRandomChestContent(Items.rotten_flesh, 0, 3, 7, 16), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)};
   private static final WeightedRandomChestContent[] junglePyramidsDispenserContents = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.arrow, 0, 2, 7, 30)};
   private static ComponentScatteredFeaturePieces$JunglePyramid$Stones junglePyramidsRandomScatteredStones = new ComponentScatteredFeaturePieces$JunglePyramid$Stones((ComponentScatteredFeaturePieces$1)null);


   public ComponentScatteredFeaturePieces$JunglePyramid() {}

   public ComponentScatteredFeaturePieces$JunglePyramid(Random var1, int var2, int var3) {
      super(var1, var2, 64, var3, 12, 10, 15);
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setBoolean("placedMainChest", this.field_74947_h);
      var1.setBoolean("placedHiddenChest", this.field_74948_i);
      var1.setBoolean("placedTrap1", this.field_74945_j);
      var1.setBoolean("placedTrap2", this.field_74946_k);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.field_74947_h = var1.getBoolean("placedMainChest");
      this.field_74948_i = var1.getBoolean("placedHiddenChest");
      this.field_74945_j = var1.getBoolean("placedTrap1");
      this.field_74946_k = var1.getBoolean("placedTrap2");
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(!this.func_74935_a(var1, var3, 0)) {
         return false;
      } else {
         int var4 = this.getMetadataWithOffset(Blocks.stone_stairs, 3);
         int var5 = this.getMetadataWithOffset(Blocks.stone_stairs, 2);
         int var6 = this.getMetadataWithOffset(Blocks.stone_stairs, 0);
         int var7 = this.getMetadataWithOffset(Blocks.stone_stairs, 1);
         this.fillWithRandomizedBlocks(var1, var3, 0, -4, 0, super.scatteredFeatureSizeX - 1, 0, super.scatteredFeatureSizeZ - 1, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 2, 1, 2, 9, 2, 2, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 2, 1, 12, 9, 2, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 2, 1, 3, 2, 2, 11, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 9, 1, 3, 9, 2, 11, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 1, 3, 1, 10, 6, 1, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 1, 3, 13, 10, 6, 13, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 1, 3, 2, 1, 6, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 10, 3, 2, 10, 6, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 2, 3, 2, 9, 3, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 2, 6, 2, 9, 6, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 3, 7, 3, 8, 7, 11, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 4, 8, 4, 7, 8, 10, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithAir(var1, var3, 3, 1, 3, 8, 2, 11);
         this.fillWithAir(var1, var3, 4, 3, 6, 7, 3, 9);
         this.fillWithAir(var1, var3, 2, 4, 2, 9, 5, 12);
         this.fillWithAir(var1, var3, 4, 6, 5, 7, 6, 9);
         this.fillWithAir(var1, var3, 5, 7, 6, 6, 7, 8);
         this.fillWithAir(var1, var3, 5, 1, 2, 6, 2, 2);
         this.fillWithAir(var1, var3, 5, 2, 12, 6, 2, 12);
         this.fillWithAir(var1, var3, 5, 5, 1, 6, 5, 1);
         this.fillWithAir(var1, var3, 5, 5, 13, 6, 5, 13);
         this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 1, 5, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 10, 5, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 1, 5, 9, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 10, 5, 9, var3);

         int var8;
         for(var8 = 0; var8 <= 14; var8 += 14) {
            this.fillWithRandomizedBlocks(var1, var3, 2, 4, var8, 2, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(var1, var3, 4, 4, var8, 4, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(var1, var3, 7, 4, var8, 7, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(var1, var3, 9, 4, var8, 9, 5, var8, false, var2, junglePyramidsRandomScatteredStones);
         }

         this.fillWithRandomizedBlocks(var1, var3, 5, 6, 0, 6, 6, 0, false, var2, junglePyramidsRandomScatteredStones);

         for(var8 = 0; var8 <= 11; var8 += 11) {
            for(int var9 = 2; var9 <= 12; var9 += 2) {
               this.fillWithRandomizedBlocks(var1, var3, var8, 4, var9, var8, 5, var9, false, var2, junglePyramidsRandomScatteredStones);
            }

            this.fillWithRandomizedBlocks(var1, var3, var8, 6, 5, var8, 6, 5, false, var2, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(var1, var3, var8, 6, 9, var8, 6, 9, false, var2, junglePyramidsRandomScatteredStones);
         }

         this.fillWithRandomizedBlocks(var1, var3, 2, 7, 2, 2, 9, 2, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 9, 7, 2, 9, 9, 2, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 2, 7, 12, 2, 9, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 9, 7, 12, 9, 9, 12, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 4, 9, 4, 4, 9, 4, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 7, 9, 4, 7, 9, 4, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 4, 9, 10, 4, 9, 10, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 7, 9, 10, 7, 9, 10, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 5, 9, 7, 6, 9, 7, false, var2, junglePyramidsRandomScatteredStones);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 5, 9, 6, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 6, 9, 6, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var5, 5, 9, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var5, 6, 9, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 4, 0, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 5, 0, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 6, 0, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 7, 0, 0, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 4, 1, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 4, 2, 9, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 4, 3, 10, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 7, 1, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 7, 2, 9, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var4, 7, 3, 10, var3);
         this.fillWithRandomizedBlocks(var1, var3, 4, 1, 9, 4, 1, 9, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 7, 1, 9, 7, 1, 9, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 4, 1, 10, 7, 2, 10, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 5, 4, 5, 6, 4, 5, false, var2, junglePyramidsRandomScatteredStones);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var6, 4, 4, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var7, 7, 4, 5, var3);

         for(var8 = 0; var8 < 4; ++var8) {
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var5, 5, 0 - var8, 6 + var8, var3);
            this.placeBlockAtCurrentPosition(var1, Blocks.stone_stairs, var5, 6, 0 - var8, 6 + var8, var3);
            this.fillWithAir(var1, var3, 5, 0 - var8, 7 + var8, 6, 0 - var8, 9 + var8);
         }

         this.fillWithAir(var1, var3, 1, -3, 12, 10, -1, 13);
         this.fillWithAir(var1, var3, 1, -3, 1, 3, -1, 13);
         this.fillWithAir(var1, var3, 1, -3, 1, 9, -1, 5);

         for(var8 = 1; var8 <= 13; var8 += 2) {
            this.fillWithRandomizedBlocks(var1, var3, 1, -3, var8, 1, -2, var8, false, var2, junglePyramidsRandomScatteredStones);
         }

         for(var8 = 2; var8 <= 12; var8 += 2) {
            this.fillWithRandomizedBlocks(var1, var3, 1, -1, var8, 3, -1, var8, false, var2, junglePyramidsRandomScatteredStones);
         }

         this.fillWithRandomizedBlocks(var1, var3, 2, -2, 1, 5, -2, 1, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 7, -2, 1, 9, -2, 1, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 6, -3, 1, 6, -3, 1, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 6, -1, 1, 6, -1, 1, false, var2, junglePyramidsRandomScatteredStones);
         this.placeBlockAtCurrentPosition(var1, Blocks.tripwire_hook, this.getMetadataWithOffset(Blocks.tripwire_hook, 3) | 4, 1, -3, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.tripwire_hook, this.getMetadataWithOffset(Blocks.tripwire_hook, 1) | 4, 4, -3, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.tripwire, 4, 2, -3, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.tripwire, 4, 3, -3, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 5, -3, 7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 5, -3, 6, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 5, -3, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 5, -3, 4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 5, -3, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 5, -3, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 5, -3, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 4, -3, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.mossy_cobblestone, 0, 3, -3, 1, var3);
         if(!this.field_74945_j) {
            this.field_74945_j = this.generateStructureDispenserContents(var1, var3, var2, 3, -2, 1, 2, junglePyramidsDispenserContents, 2);
         }

         this.placeBlockAtCurrentPosition(var1, Blocks.vine, 15, 3, -2, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.tripwire_hook, this.getMetadataWithOffset(Blocks.tripwire_hook, 2) | 4, 7, -3, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.tripwire_hook, this.getMetadataWithOffset(Blocks.tripwire_hook, 0) | 4, 7, -3, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.tripwire, 4, 7, -3, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.tripwire, 4, 7, -3, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.tripwire, 4, 7, -3, 4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 8, -3, 6, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 9, -3, 6, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 9, -3, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.mossy_cobblestone, 0, 9, -3, 4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 9, -2, 4, var3);
         if(!this.field_74946_k) {
            this.field_74946_k = this.generateStructureDispenserContents(var1, var3, var2, 9, -2, 3, 4, junglePyramidsDispenserContents, 2);
         }

         this.placeBlockAtCurrentPosition(var1, Blocks.vine, 15, 8, -1, 3, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.vine, 15, 8, -2, 3, var3);
         if(!this.field_74947_h) {
            this.field_74947_h = this.generateStructureChestContents(var1, var3, var2, 8, -3, 3, WeightedRandomChestContent.func_92080_a(junglePyramidsChestContents, new WeightedRandomChestContent[]{Items.enchanted_book.func_92114_b(var2)}), 2 + var2.nextInt(5));
         }

         this.placeBlockAtCurrentPosition(var1, Blocks.mossy_cobblestone, 0, 9, -3, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.mossy_cobblestone, 0, 8, -3, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.mossy_cobblestone, 0, 4, -3, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.mossy_cobblestone, 0, 5, -2, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.mossy_cobblestone, 0, 5, -1, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.mossy_cobblestone, 0, 6, -3, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.mossy_cobblestone, 0, 7, -2, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.mossy_cobblestone, 0, 7, -1, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.mossy_cobblestone, 0, 8, -3, 5, var3);
         this.fillWithRandomizedBlocks(var1, var3, 9, -1, 1, 9, -1, 5, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithAir(var1, var3, 8, -3, 8, 10, -1, 10);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 3, 8, -2, 11, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 3, 9, -2, 11, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stonebrick, 3, 10, -2, 11, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.lever, BlockLever.invertMetadata(this.getMetadataWithOffset(Blocks.lever, 2)), 8, -2, 12, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.lever, BlockLever.invertMetadata(this.getMetadataWithOffset(Blocks.lever, 2)), 9, -2, 12, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.lever, BlockLever.invertMetadata(this.getMetadataWithOffset(Blocks.lever, 2)), 10, -2, 12, var3);
         this.fillWithRandomizedBlocks(var1, var3, 8, -3, 8, 8, -3, 10, false, var2, junglePyramidsRandomScatteredStones);
         this.fillWithRandomizedBlocks(var1, var3, 10, -3, 8, 10, -3, 10, false, var2, junglePyramidsRandomScatteredStones);
         this.placeBlockAtCurrentPosition(var1, Blocks.mossy_cobblestone, 0, 10, -2, 9, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 8, -2, 9, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 8, -2, 10, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.redstone_wire, 0, 10, -1, 9, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sticky_piston, 1, 9, -2, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sticky_piston, this.getMetadataWithOffset(Blocks.sticky_piston, 4), 10, -2, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.sticky_piston, this.getMetadataWithOffset(Blocks.sticky_piston, 4), 10, -1, 8, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.unpowered_repeater, this.getMetadataWithOffset(Blocks.unpowered_repeater, 2), 10, -2, 10, var3);
         if(!this.field_74948_i) {
            this.field_74948_i = this.generateStructureChestContents(var1, var3, var2, 9, -3, 10, WeightedRandomChestContent.func_92080_a(junglePyramidsChestContents, new WeightedRandomChestContent[]{Items.enchanted_book.func_92114_b(var2)}), 2 + var2.nextInt(5));
         }

         return true;
      }
   }

}
