package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Piece;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Start;

public class StructureNetherBridgePieces$Corridor extends StructureNetherBridgePieces$Piece {

   private boolean field_111021_b;


   public StructureNetherBridgePieces$Corridor() {}

   public StructureNetherBridgePieces$Corridor(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.boundingBox = var3;
      this.field_111021_b = var2.nextInt(3) == 0;
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.field_111021_b = var1.getBoolean("Chest");
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setBoolean("Chest", this.field_111021_b);
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      this.getNextComponentX((StructureNetherBridgePieces$Start)var1, var2, var3, 0, 1, true);
   }

   public static StructureNetherBridgePieces$Corridor createValidComponent(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, 0, 0, 5, 7, 5, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null?new StructureNetherBridgePieces$Corridor(var6, var1, var7, var5):null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      this.fillWithBlocks(var1, var3, 0, 0, 0, 4, 1, 4, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 2, 0, 4, 5, 4, Blocks.air, Blocks.air, false);
      this.fillWithBlocks(var1, var3, 4, 2, 0, 4, 5, 4, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 4, 3, 1, 4, 4, 1, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 4, 3, 3, 4, 4, 3, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
      this.fillWithBlocks(var1, var3, 0, 2, 0, 0, 5, 0, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 0, 2, 4, 3, 5, 4, Blocks.nether_brick, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 1, 3, 4, 1, 4, 4, Blocks.nether_brick_fence, Blocks.nether_brick, false);
      this.fillWithBlocks(var1, var3, 3, 3, 4, 3, 4, 4, Blocks.nether_brick_fence, Blocks.nether_brick, false);
      int var4;
      int var5;
      if(this.field_111021_b) {
         var4 = this.getYWithOffset(2);
         var5 = this.getXWithOffset(3, 3);
         int var6 = this.getZWithOffset(3, 3);
         if(var3.isVecInside(var5, var4, var6)) {
            this.field_111021_b = false;
            this.generateStructureChestContents(var1, var3, var2, 3, 2, 3, StructureNetherBridgePieces$Piece.field_111019_a, 2 + var2.nextInt(4));
         }
      }

      this.fillWithBlocks(var1, var3, 0, 6, 0, 4, 6, 4, Blocks.nether_brick, Blocks.nether_brick, false);

      for(var4 = 0; var4 <= 4; ++var4) {
         for(var5 = 0; var5 <= 4; ++var5) {
            this.func_151554_b(var1, Blocks.nether_brick, 0, var4, -1, var5, var3);
         }
      }

      return true;
   }
}
