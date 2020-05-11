package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs2;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold$Door;

public class StructureStrongholdPieces$PortalRoom extends StructureStrongholdPieces$Stronghold {

   private boolean hasSpawner;


   public StructureStrongholdPieces$PortalRoom() {}

   public StructureStrongholdPieces$PortalRoom(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.boundingBox = var3;
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setBoolean("Mob", this.hasSpawner);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.hasSpawner = var1.getBoolean("Mob");
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      if(var1 != null) {
         ((StructureStrongholdPieces$Stairs2)var1).strongholdPortalRoom = this;
      }

   }

   public static StructureStrongholdPieces$PortalRoom findValidPlacement(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -4, -1, 0, 11, 8, 16, var5);
      return canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureStrongholdPieces$PortalRoom(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      this.fillWithRandomizedBlocks(var1, var3, 0, 0, 0, 10, 7, 15, false, var2, StructureStrongholdPieces.access$200());
      this.placeDoor(var1, var2, var3, StructureStrongholdPieces$Stronghold$Door.GRATES, 4, 1, 0);
      byte var4 = 6;
      this.fillWithRandomizedBlocks(var1, var3, 1, var4, 1, 1, var4, 14, false, var2, StructureStrongholdPieces.access$200());
      this.fillWithRandomizedBlocks(var1, var3, 9, var4, 1, 9, var4, 14, false, var2, StructureStrongholdPieces.access$200());
      this.fillWithRandomizedBlocks(var1, var3, 2, var4, 1, 8, var4, 2, false, var2, StructureStrongholdPieces.access$200());
      this.fillWithRandomizedBlocks(var1, var3, 2, var4, 14, 8, var4, 14, false, var2, StructureStrongholdPieces.access$200());
      this.fillWithRandomizedBlocks(var1, var3, 1, 1, 1, 2, 1, 4, false, var2, StructureStrongholdPieces.access$200());
      this.fillWithRandomizedBlocks(var1, var3, 8, 1, 1, 9, 1, 4, false, var2, StructureStrongholdPieces.access$200());
      this.fillWithBlocks(var1, var3, 1, 1, 1, 1, 1, 3, Blocks.flowing_lava, Blocks.flowing_lava, false);
      this.fillWithBlocks(var1, var3, 9, 1, 1, 9, 1, 3, Blocks.flowing_lava, Blocks.flowing_lava, false);
      this.fillWithRandomizedBlocks(var1, var3, 3, 1, 8, 7, 1, 12, false, var2, StructureStrongholdPieces.access$200());
      this.fillWithBlocks(var1, var3, 4, 1, 9, 6, 1, 11, Blocks.flowing_lava, Blocks.flowing_lava, false);

      int var5;
      for(var5 = 3; var5 < 14; var5 += 2) {
         this.fillWithBlocks(var1, var3, 0, 3, var5, 0, 4, var5, Blocks.iron_bars, Blocks.iron_bars, false);
         this.fillWithBlocks(var1, var3, 10, 3, var5, 10, 4, var5, Blocks.iron_bars, Blocks.iron_bars, false);
      }

      for(var5 = 2; var5 < 9; var5 += 2) {
         this.fillWithBlocks(var1, var3, var5, 3, 15, var5, 4, 15, Blocks.iron_bars, Blocks.iron_bars, false);
      }

      var5 = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 3);
      this.fillWithRandomizedBlocks(var1, var3, 4, 1, 5, 6, 1, 7, false, var2, StructureStrongholdPieces.access$200());
      this.fillWithRandomizedBlocks(var1, var3, 4, 2, 6, 6, 2, 7, false, var2, StructureStrongholdPieces.access$200());
      this.fillWithRandomizedBlocks(var1, var3, 4, 3, 7, 6, 3, 7, false, var2, StructureStrongholdPieces.access$200());

      for(int var6 = 4; var6 <= 6; ++var6) {
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_brick_stairs, var5, var6, 1, 4, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_brick_stairs, var5, var6, 2, 5, var3);
         this.placeBlockAtCurrentPosition(var1, Blocks.stone_brick_stairs, var5, var6, 3, 6, var3);
      }

      byte var14 = 2;
      byte var7 = 0;
      byte var8 = 3;
      byte var9 = 1;
      switch(super.coordBaseMode) {
      case 0:
         var14 = 0;
         var7 = 2;
         break;
      case 1:
         var14 = 1;
         var7 = 3;
         var8 = 0;
         var9 = 2;
      case 2:
      default:
         break;
      case 3:
         var14 = 3;
         var7 = 1;
         var8 = 0;
         var9 = 2;
      }

      this.placeBlockAtCurrentPosition(var1, Blocks.end_portal_frame, var14 + (var2.nextFloat() > 0.9F?4:0), 4, 3, 8, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.end_portal_frame, var14 + (var2.nextFloat() > 0.9F?4:0), 5, 3, 8, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.end_portal_frame, var14 + (var2.nextFloat() > 0.9F?4:0), 6, 3, 8, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.end_portal_frame, var7 + (var2.nextFloat() > 0.9F?4:0), 4, 3, 12, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.end_portal_frame, var7 + (var2.nextFloat() > 0.9F?4:0), 5, 3, 12, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.end_portal_frame, var7 + (var2.nextFloat() > 0.9F?4:0), 6, 3, 12, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.end_portal_frame, var8 + (var2.nextFloat() > 0.9F?4:0), 3, 3, 9, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.end_portal_frame, var8 + (var2.nextFloat() > 0.9F?4:0), 3, 3, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.end_portal_frame, var8 + (var2.nextFloat() > 0.9F?4:0), 3, 3, 11, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.end_portal_frame, var9 + (var2.nextFloat() > 0.9F?4:0), 7, 3, 9, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.end_portal_frame, var9 + (var2.nextFloat() > 0.9F?4:0), 7, 3, 10, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.end_portal_frame, var9 + (var2.nextFloat() > 0.9F?4:0), 7, 3, 11, var3);
      if(!this.hasSpawner) {
         int var13 = this.getYWithOffset(3);
         int var10 = this.getXWithOffset(5, 6);
         int var11 = this.getZWithOffset(5, 6);
         if(var3.isVecInside(var10, var13, var11)) {
            this.hasSpawner = true;
            var1.setBlock(var10, var13, var11, Blocks.mob_spawner, 0, 2);
            TileEntityMobSpawner var12 = (TileEntityMobSpawner)var1.getTileEntity(var10, var13, var11);
            if(var12 != null) {
               var12.func_145881_a().setEntityName("Silverfish");
            }
         }
      }

      return true;
   }
}
