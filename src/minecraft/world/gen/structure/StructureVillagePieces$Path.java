package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces$Road;
import net.minecraft.world.gen.structure.StructureVillagePieces$Start;

public class StructureVillagePieces$Path extends StructureVillagePieces$Road {

   private int averageGroundLevel;


   public StructureVillagePieces$Path() {}

   public StructureVillagePieces$Path(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, int var5) {
      super(var1, var2);
      super.coordBaseMode = var5;
      super.boundingBox = var4;
      this.averageGroundLevel = Math.max(var4.getXSize(), var4.getZSize());
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setInteger("Length", this.averageGroundLevel);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.averageGroundLevel = var1.getInteger("Length");
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      boolean var4 = false;

      int var5;
      StructureComponent var6;
      for(var5 = var3.nextInt(5); var5 < this.averageGroundLevel - 8; var5 += 2 + var3.nextInt(5)) {
         var6 = this.getNextComponentNN((StructureVillagePieces$Start)var1, var2, var3, 0, var5);
         if(var6 != null) {
            var5 += Math.max(var6.boundingBox.getXSize(), var6.boundingBox.getZSize());
            var4 = true;
         }
      }

      for(var5 = var3.nextInt(5); var5 < this.averageGroundLevel - 8; var5 += 2 + var3.nextInt(5)) {
         var6 = this.getNextComponentPP((StructureVillagePieces$Start)var1, var2, var3, 0, var5);
         if(var6 != null) {
            var5 += Math.max(var6.boundingBox.getXSize(), var6.boundingBox.getZSize());
            var4 = true;
         }
      }

      if(var4 && var3.nextInt(3) > 0) {
         switch(super.coordBaseMode) {
         case 0:
            StructureVillagePieces.access$100((StructureVillagePieces$Start)var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY, super.boundingBox.maxZ - 2, 1, this.getComponentType());
            break;
         case 1:
            StructureVillagePieces.access$100((StructureVillagePieces$Start)var1, var2, var3, super.boundingBox.minX, super.boundingBox.minY, super.boundingBox.minZ - 1, 2, this.getComponentType());
            break;
         case 2:
            StructureVillagePieces.access$100((StructureVillagePieces$Start)var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY, super.boundingBox.minZ, 1, this.getComponentType());
            break;
         case 3:
            StructureVillagePieces.access$100((StructureVillagePieces$Start)var1, var2, var3, super.boundingBox.maxX - 2, super.boundingBox.minY, super.boundingBox.minZ - 1, 2, this.getComponentType());
         }
      }

      if(var4 && var3.nextInt(3) > 0) {
         switch(super.coordBaseMode) {
         case 0:
            StructureVillagePieces.access$100((StructureVillagePieces$Start)var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY, super.boundingBox.maxZ - 2, 3, this.getComponentType());
            break;
         case 1:
            StructureVillagePieces.access$100((StructureVillagePieces$Start)var1, var2, var3, super.boundingBox.minX, super.boundingBox.minY, super.boundingBox.maxZ + 1, 0, this.getComponentType());
            break;
         case 2:
            StructureVillagePieces.access$100((StructureVillagePieces$Start)var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY, super.boundingBox.minZ, 3, this.getComponentType());
            break;
         case 3:
            StructureVillagePieces.access$100((StructureVillagePieces$Start)var1, var2, var3, super.boundingBox.maxX - 2, super.boundingBox.minY, super.boundingBox.maxZ + 1, 0, this.getComponentType());
         }
      }

   }

   public static StructureBoundingBox func_74933_a(StructureVillagePieces$Start var0, List var1, Random var2, int var3, int var4, int var5, int var6) {
      for(int var7 = 7 * MathHelper.getRandomIntegerInRange(var2, 3, 5); var7 >= 7; var7 -= 7) {
         StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 3, 3, var7, var6);
         if(StructureComponent.findIntersecting(var1, var8) == null) {
            return var8;
         }
      }

      return null;
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      Block var4 = this.func_151558_b(Blocks.gravel, 0);

      for(int var5 = super.boundingBox.minX; var5 <= super.boundingBox.maxX; ++var5) {
         for(int var6 = super.boundingBox.minZ; var6 <= super.boundingBox.maxZ; ++var6) {
            if(var3.isVecInside(var5, 64, var6)) {
               int var7 = var1.getTopSolidOrLiquidBlock(var5, var6) - 1;
               var1.setBlock(var5, var7, var6, var4, 0, 2);
            }
         }
      }

      return true;
   }
}
