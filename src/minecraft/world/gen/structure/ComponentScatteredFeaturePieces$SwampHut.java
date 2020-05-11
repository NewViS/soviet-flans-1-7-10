package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$Feature;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentScatteredFeaturePieces$SwampHut extends ComponentScatteredFeaturePieces$Feature {

   private boolean hasWitch;


   public ComponentScatteredFeaturePieces$SwampHut() {}

   public ComponentScatteredFeaturePieces$SwampHut(Random var1, int var2, int var3) {
      super(var1, var2, 64, var3, 7, 5, 9);
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setBoolean("Witch", this.hasWitch);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.hasWitch = var1.getBoolean("Witch");
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(!this.func_74935_a(var1, var3, 0)) {
         return false;
      } else {
         this.fillWithMetadataBlocks(var1, var3, 1, 1, 1, 5, 1, 7, Blocks.planks, 1, Blocks.planks, 1, false);
         this.fillWithMetadataBlocks(var1, var3, 1, 4, 2, 5, 4, 7, Blocks.planks, 1, Blocks.planks, 1, false);
         this.fillWithMetadataBlocks(var1, var3, 2, 1, 0, 4, 1, 0, Blocks.planks, 1, Blocks.planks, 1, false);
         this.fillWithMetadataBlocks(var1, var3, 2, 2, 2, 3, 3, 2, Blocks.planks, 1, Blocks.planks, 1, false);
         this.fillWithMetadataBlocks(var1, var3, 1, 2, 3, 1, 3, 6, Blocks.planks, 1, Blocks.planks, 1, false);
         this.fillWithMetadataBlocks(var1, var3, 5, 2, 3, 5, 3, 6, Blocks.planks, 1, Blocks.planks, 1, false);
         this.fillWithMetadataBlocks(var1, var3, 2, 2, 7, 4, 3, 7, Blocks.planks, 1, Blocks.planks, 1, false);
         this.fillWithBlocks(var1, var3, 1, 0, 2, 1, 3, 2, Blocks.log, Blocks.log, false);
         this.fillWithBlocks(var1, var3, 5, 0, 2, 5, 3, 2, Blocks.log, Blocks.log, false);
         this.fillWithBlocks(var1, var3, 1, 0, 7, 1, 3, 7, Blocks.log, Blocks.log, false);
         this.fillWithBlocks(var1, var3, 5, 0, 7, 5, 3, 7, Blocks.log, Blocks.log, false);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 2, 3, 2, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 3, 3, 7, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 1, 3, 4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 5, 3, 4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, 5, 3, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.flower_pot, 7, 1, 3, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.crafting_table, 0, 3, 2, 6, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.cauldron, 0, 4, 2, 6, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 1, 2, 1, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.fence, 0, 5, 2, 1, var3);
         int var4 = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
         int var5 = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
         int var6 = this.getMetadataWithOffset(Blocks.oak_stairs, 0);
         int var7 = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
         this.fillWithMetadataBlocks(var1, var3, 0, 4, 1, 6, 4, 1, Blocks.spruce_stairs, var4, Blocks.spruce_stairs, var4, false);
         this.fillWithMetadataBlocks(var1, var3, 0, 4, 2, 0, 4, 7, Blocks.spruce_stairs, var6, Blocks.spruce_stairs, var6, false);
         this.fillWithMetadataBlocks(var1, var3, 6, 4, 2, 6, 4, 7, Blocks.spruce_stairs, var5, Blocks.spruce_stairs, var5, false);
         this.fillWithMetadataBlocks(var1, var3, 0, 4, 8, 6, 4, 8, Blocks.spruce_stairs, var7, Blocks.spruce_stairs, var7, false);

         int var8;
         int var9;
         for(var8 = 2; var8 <= 7; var8 += 5) {
            for(var9 = 1; var9 <= 5; var9 += 4) {
               this.func_151554_b(var1, Blocks.log, 0, var9, -1, var8, var3);
            }
         }

         if(!this.hasWitch) {
            var8 = this.getXWithOffset(2, 5);
            var9 = this.getYWithOffset(2);
            int var10 = this.getZWithOffset(2, 5);
            if(var3.isVecInside(var8, var9, var10)) {
               this.hasWitch = true;
               EntityWitch var11 = new EntityWitch(var1);
               var11.setLocationAndAngles((double)var8 + 0.5D, (double)var9, (double)var10 + 0.5D, 0.0F, 0.0F);
               var11.onSpawnWithEgg((IEntityLivingData)null);
               var1.spawnEntityInWorld(var11);
            }
         }

         return true;
      }
   }
}
