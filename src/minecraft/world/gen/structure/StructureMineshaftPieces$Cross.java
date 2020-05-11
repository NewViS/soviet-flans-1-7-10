package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;

public class StructureMineshaftPieces$Cross extends StructureComponent {

   private int corridorDirection;
   private boolean isMultipleFloors;


   public StructureMineshaftPieces$Cross() {}

   protected void func_143012_a(NBTTagCompound var1) {
      var1.setBoolean("tf", this.isMultipleFloors);
      var1.setInteger("D", this.corridorDirection);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      this.isMultipleFloors = var1.getBoolean("tf");
      this.corridorDirection = var1.getInteger("D");
   }

   public StructureMineshaftPieces$Cross(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      this.corridorDirection = var4;
      super.boundingBox = var3;
      this.isMultipleFloors = var3.getYSize() > 3;
   }

   public static StructureBoundingBox findValidPlacement(List var0, Random var1, int var2, int var3, int var4, int var5) {
      StructureBoundingBox var6 = new StructureBoundingBox(var2, var3, var4, var2, var3 + 2, var4);
      if(var1.nextInt(4) == 0) {
         var6.maxY += 4;
      }

      switch(var5) {
      case 0:
         var6.minX = var2 - 1;
         var6.maxX = var2 + 3;
         var6.maxZ = var4 + 4;
         break;
      case 1:
         var6.minX = var2 - 4;
         var6.minZ = var4 - 1;
         var6.maxZ = var4 + 3;
         break;
      case 2:
         var6.minX = var2 - 1;
         var6.maxX = var2 + 3;
         var6.minZ = var4 - 4;
         break;
      case 3:
         var6.maxX = var2 + 4;
         var6.minZ = var4 - 1;
         var6.maxZ = var4 + 3;
      }

      return StructureComponent.findIntersecting(var0, var6) != null?null:var6;
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      int var4 = this.getComponentType();
      switch(this.corridorDirection) {
      case 0:
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX + 1, super.boundingBox.minY, super.boundingBox.maxZ + 1, 0, var4);
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY, super.boundingBox.minZ + 1, 1, var4);
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY, super.boundingBox.minZ + 1, 3, var4);
         break;
      case 1:
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX + 1, super.boundingBox.minY, super.boundingBox.minZ - 1, 2, var4);
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX + 1, super.boundingBox.minY, super.boundingBox.maxZ + 1, 0, var4);
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY, super.boundingBox.minZ + 1, 1, var4);
         break;
      case 2:
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX + 1, super.boundingBox.minY, super.boundingBox.minZ - 1, 2, var4);
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY, super.boundingBox.minZ + 1, 1, var4);
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY, super.boundingBox.minZ + 1, 3, var4);
         break;
      case 3:
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX + 1, super.boundingBox.minY, super.boundingBox.minZ - 1, 2, var4);
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX + 1, super.boundingBox.minY, super.boundingBox.maxZ + 1, 0, var4);
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY, super.boundingBox.minZ + 1, 3, var4);
      }

      if(this.isMultipleFloors) {
         if(var3.nextBoolean()) {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX + 1, super.boundingBox.minY + 3 + 1, super.boundingBox.minZ - 1, 2, var4);
         }

         if(var3.nextBoolean()) {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY + 3 + 1, super.boundingBox.minZ + 1, 1, var4);
         }

         if(var3.nextBoolean()) {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY + 3 + 1, super.boundingBox.minZ + 1, 3, var4);
         }

         if(var3.nextBoolean()) {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX + 1, super.boundingBox.minY + 3 + 1, super.boundingBox.maxZ + 1, 0, var4);
         }
      }

   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.isLiquidInStructureBoundingBox(var1, var3)) {
         return false;
      } else {
         if(this.isMultipleFloors) {
            this.fillWithBlocks(var1, var3, super.boundingBox.minX + 1, super.boundingBox.minY, super.boundingBox.minZ, super.boundingBox.maxX - 1, super.boundingBox.minY + 3 - 1, super.boundingBox.maxZ, Blocks.air, Blocks.air, false);
            this.fillWithBlocks(var1, var3, super.boundingBox.minX, super.boundingBox.minY, super.boundingBox.minZ + 1, super.boundingBox.maxX, super.boundingBox.minY + 3 - 1, super.boundingBox.maxZ - 1, Blocks.air, Blocks.air, false);
            this.fillWithBlocks(var1, var3, super.boundingBox.minX + 1, super.boundingBox.maxY - 2, super.boundingBox.minZ, super.boundingBox.maxX - 1, super.boundingBox.maxY, super.boundingBox.maxZ, Blocks.air, Blocks.air, false);
            this.fillWithBlocks(var1, var3, super.boundingBox.minX, super.boundingBox.maxY - 2, super.boundingBox.minZ + 1, super.boundingBox.maxX, super.boundingBox.maxY, super.boundingBox.maxZ - 1, Blocks.air, Blocks.air, false);
            this.fillWithBlocks(var1, var3, super.boundingBox.minX + 1, super.boundingBox.minY + 3, super.boundingBox.minZ + 1, super.boundingBox.maxX - 1, super.boundingBox.minY + 3, super.boundingBox.maxZ - 1, Blocks.air, Blocks.air, false);
         } else {
            this.fillWithBlocks(var1, var3, super.boundingBox.minX + 1, super.boundingBox.minY, super.boundingBox.minZ, super.boundingBox.maxX - 1, super.boundingBox.maxY, super.boundingBox.maxZ, Blocks.air, Blocks.air, false);
            this.fillWithBlocks(var1, var3, super.boundingBox.minX, super.boundingBox.minY, super.boundingBox.minZ + 1, super.boundingBox.maxX, super.boundingBox.maxY, super.boundingBox.maxZ - 1, Blocks.air, Blocks.air, false);
         }

         this.fillWithBlocks(var1, var3, super.boundingBox.minX + 1, super.boundingBox.minY, super.boundingBox.minZ + 1, super.boundingBox.minX + 1, super.boundingBox.maxY, super.boundingBox.minZ + 1, Blocks.planks, Blocks.air, false);
         this.fillWithBlocks(var1, var3, super.boundingBox.minX + 1, super.boundingBox.minY, super.boundingBox.maxZ - 1, super.boundingBox.minX + 1, super.boundingBox.maxY, super.boundingBox.maxZ - 1, Blocks.planks, Blocks.air, false);
         this.fillWithBlocks(var1, var3, super.boundingBox.maxX - 1, super.boundingBox.minY, super.boundingBox.minZ + 1, super.boundingBox.maxX - 1, super.boundingBox.maxY, super.boundingBox.minZ + 1, Blocks.planks, Blocks.air, false);
         this.fillWithBlocks(var1, var3, super.boundingBox.maxX - 1, super.boundingBox.minY, super.boundingBox.maxZ - 1, super.boundingBox.maxX - 1, super.boundingBox.maxY, super.boundingBox.maxZ - 1, Blocks.planks, Blocks.air, false);

         for(int var4 = super.boundingBox.minX; var4 <= super.boundingBox.maxX; ++var4) {
            for(int var5 = super.boundingBox.minZ; var5 <= super.boundingBox.maxZ; ++var5) {
               if(this.getBlockAtCurrentPosition(var1, var4, super.boundingBox.minY - 1, var5, var3).getMaterial() == Material.air) {
                  this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, var4, super.boundingBox.minY - 1, var5, var3);
               }
            }
         }

         return true;
      }
   }
}
