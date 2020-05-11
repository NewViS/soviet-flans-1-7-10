package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Piece;

public class StructureNetherBridgePieces$Throne extends StructureNetherBridgePieces$Piece {

   private boolean hasSpawner;


   public StructureNetherBridgePieces$Throne() {}

   public StructureNetherBridgePieces$Throne(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.boundingBox = var3;
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.hasSpawner = var1.getBoolean("Mob");
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setBoolean("Mob", this.hasSpawner);
   }

   public static StructureNetherBridgePieces$Throne createValidComponent(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -2, 0, 0, 7, 8, 9, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureNetherBridgePieces$Throne(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      this.fillWithBlocks(var1, var3, 0, 2, 0, 6, 7, 7, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 1, 0, 0, 5, 1, 7, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 1, 2, 1, 5, 2, 7, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 1, 3, 2, 5, 3, 7, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 1, 4, 3, 5, 4, 7, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 1, 2, 0, 1, 4, 2, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 5, 2, 0, 5, 4, 2, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 1, 5, 2, 1, 5, 3, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 5, 5, 2, 5, 5, 3, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 5, 3, 0, 5, 8, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 6, 5, 3, 6, 5, 8, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 1, 5, 8, 5, 5, 8, Blocks.nether_brick, Blocks.nether_brick, false);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_fence, 0, 1, 6, 3, var3);
      this.placeBlockAtCurrentPosition(var1, Blocks.nether_brick_fence, 0, 5, 6, 3, var3);
      this.fillWithBlocks(var1, var3, 0, 6, 3, 0, 6, 8, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 6, 6, 3, 6, 6, 8, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 1, 6, 8, 5, 7, 8, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 2, 8, 8, 4, 8, 8, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      int var4;
      int var5;
      if(!this.hasSpawner) {
         var4 = this.getYWithOffset(5);
         var5 = this.getXWithOffset(3, 5);
         int var6 = this.getZWithOffset(3, 5);
         if(var3.isVecInside(var5, var4, var6)) {
            this.hasSpawner = true;
            var1.setBlock(var5, var4, var6, Blocks.mob_spawner, 0, 2);
            TileEntityMobSpawner var7 = (TileEntityMobSpawner)var1.getTileEntity(var5, var4, var6);
            if(var7 != null) {
               var7.func_145881_a().setEntityName("Blaze");
            }
         }
      }

      for(var4 = 0; var4 <= 6; ++var4) {
         for(var5 = 0; var5 <= 6; ++var5) {
            this.func_151554_b(var1, Blocks.nether_brick, 0, var4, -1, var5, var3);
         }
      }

      return true;
   }
}
