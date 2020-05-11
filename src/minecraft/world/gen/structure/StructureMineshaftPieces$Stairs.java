package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;

public class StructureMineshaftPieces$Stairs extends StructureComponent {

   public StructureMineshaftPieces$Stairs() {}

   public StructureMineshaftPieces$Stairs(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.boundingBox = var3;
   }

   protected void func_143012_a(NBTTagCompound var1) {}

   protected void func_143011_b(NBTTagCompound var1) {}

   public static StructureBoundingBox findValidPlacement(List var0, Random var1, int var2, int var3, int var4, int var5) {
      StructureBoundingBox var6 = new StructureBoundingBox(var2, var3 - 5, var4, var2, var3 + 2, var4);
      switch(var5) {
      case 0:
         var6.maxX = var2 + 2;
         var6.maxZ = var4 + 8;
         break;
      case 1:
         var6.minX = var2 - 8;
         var6.maxZ = var4 + 2;
         break;
      case 2:
         var6.maxX = var2 + 2;
         var6.minZ = var4 - 8;
         break;
      case 3:
         var6.maxX = var2 + 8;
         var6.maxZ = var4 + 2;
      }

      return StructureComponent.findIntersecting(var0, var6) != null?null:var6;
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      int var4 = this.getComponentType();
      switch(super.coordBaseMode) {
      case 0:
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX, super.boundingBox.minY, super.boundingBox.maxZ + 1, 0, var4);
         break;
      case 1:
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY, super.boundingBox.minZ, 1, var4);
         break;
      case 2:
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX, super.boundingBox.minY, super.boundingBox.minZ - 1, 2, var4);
         break;
      case 3:
         StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY, super.boundingBox.minZ, 3, var4);
      }

   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.isLiquidInStructureBoundingBox(var1, var3)) {
         return false;
      } else {
         this.fillWithBlocks(var1, var3, 0, 5, 0, 2, 7, 1, Blocks.air, Blocks.air, false);
         this.fillWithBlocks(var1, var3, 0, 0, 7, 2, 2, 8, Blocks.air, Blocks.air, false);

         for(int var4 = 0; var4 < 5; ++var4) {
            this.fillWithBlocks(var1, var3, 0, 5 - var4 - (var4 < 4?1:0), 2 + var4, 2, 7 - var4, 2 + var4, Blocks.air, Blocks.air, false);
         }

         return true;
      }
   }
}
