package net.minecraft.world.gen.structure;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$End;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$PieceWeight;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Start;

abstract class StructureNetherBridgePieces$Piece extends StructureComponent {

   protected static final WeightedRandomChestContent[] field_111019_a = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 5), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 5), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 15), new WeightedRandomChestContent(Items.golden_sword, 0, 1, 1, 5), new WeightedRandomChestContent(Items.golden_chestplate, 0, 1, 1, 5), new WeightedRandomChestContent(Items.flint_and_steel, 0, 1, 1, 5), new WeightedRandomChestContent(Items.nether_wart, 0, 3, 7, 5), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 8), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 5), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 3)};


   public StructureNetherBridgePieces$Piece() {}

   protected StructureNetherBridgePieces$Piece(int var1) {
      super(var1);
   }

   protected void func_143011_b(NBTTagCompound var1) {}

   protected void func_143012_a(NBTTagCompound var1) {}

   private int getTotalWeight(List var1) {
      boolean var2 = false;
      int var3 = 0;

      StructureNetherBridgePieces$PieceWeight var5;
      for(Iterator var4 = var1.iterator(); var4.hasNext(); var3 += var5.field_78826_b) {
         var5 = (StructureNetherBridgePieces$PieceWeight)var4.next();
         if(var5.field_78824_d > 0 && var5.field_78827_c < var5.field_78824_d) {
            var2 = true;
         }
      }

      return var2?var3:-1;
   }

   private StructureNetherBridgePieces$Piece getNextComponent(StructureNetherBridgePieces$Start var1, List var2, List var3, Random var4, int var5, int var6, int var7, int var8, int var9) {
      int var10 = this.getTotalWeight(var2);
      boolean var11 = var10 > 0 && var9 <= 30;
      int var12 = 0;

      while(var12 < 5 && var11) {
         ++var12;
         int var13 = var4.nextInt(var10);
         Iterator var14 = var2.iterator();

         while(var14.hasNext()) {
            StructureNetherBridgePieces$PieceWeight var15 = (StructureNetherBridgePieces$PieceWeight)var14.next();
            var13 -= var15.field_78826_b;
            if(var13 < 0) {
               if(!var15.func_78822_a(var9) || var15 == var1.theNetherBridgePieceWeight && !var15.field_78825_e) {
                  break;
               }

               StructureNetherBridgePieces$Piece var16 = StructureNetherBridgePieces.access$000(var15, var3, var4, var5, var6, var7, var8, var9);
               if(var16 != null) {
                  ++var15.field_78827_c;
                  var1.theNetherBridgePieceWeight = var15;
                  if(!var15.func_78823_a()) {
                     var2.remove(var15);
                  }

                  return var16;
               }
            }
         }
      }

      return StructureNetherBridgePieces$End.func_74971_a(var3, var4, var5, var6, var7, var8, var9);
   }

   private StructureComponent getNextComponent(StructureNetherBridgePieces$Start var1, List var2, Random var3, int var4, int var5, int var6, int var7, int var8, boolean var9) {
      if(Math.abs(var4 - var1.getBoundingBox().minX) <= 112 && Math.abs(var6 - var1.getBoundingBox().minZ) <= 112) {
         List var10 = var1.primaryWeights;
         if(var9) {
            var10 = var1.secondaryWeights;
         }

         StructureNetherBridgePieces$Piece var11 = this.getNextComponent(var1, var10, var2, var3, var4, var5, var6, var7, var8 + 1);
         if(var11 != null) {
            var2.add(var11);
            var1.field_74967_d.add(var11);
         }

         return var11;
      } else {
         return StructureNetherBridgePieces$End.func_74971_a(var2, var3, var4, var5, var6, var7, var8);
      }
   }

   protected StructureComponent getNextComponentNormal(StructureNetherBridgePieces$Start var1, List var2, Random var3, int var4, int var5, boolean var6) {
      switch(super.coordBaseMode) {
      case 0:
         return this.getNextComponent(var1, var2, var3, super.boundingBox.minX + var4, super.boundingBox.minY + var5, super.boundingBox.maxZ + 1, super.coordBaseMode, this.getComponentType(), var6);
      case 1:
         return this.getNextComponent(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY + var5, super.boundingBox.minZ + var4, super.coordBaseMode, this.getComponentType(), var6);
      case 2:
         return this.getNextComponent(var1, var2, var3, super.boundingBox.minX + var4, super.boundingBox.minY + var5, super.boundingBox.minZ - 1, super.coordBaseMode, this.getComponentType(), var6);
      case 3:
         return this.getNextComponent(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY + var5, super.boundingBox.minZ + var4, super.coordBaseMode, this.getComponentType(), var6);
      default:
         return null;
      }
   }

   protected StructureComponent getNextComponentX(StructureNetherBridgePieces$Start var1, List var2, Random var3, int var4, int var5, boolean var6) {
      switch(super.coordBaseMode) {
      case 0:
         return this.getNextComponent(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY + var4, super.boundingBox.minZ + var5, 1, this.getComponentType(), var6);
      case 1:
         return this.getNextComponent(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var4, super.boundingBox.minZ - 1, 2, this.getComponentType(), var6);
      case 2:
         return this.getNextComponent(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY + var4, super.boundingBox.minZ + var5, 1, this.getComponentType(), var6);
      case 3:
         return this.getNextComponent(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var4, super.boundingBox.minZ - 1, 2, this.getComponentType(), var6);
      default:
         return null;
      }
   }

   protected StructureComponent getNextComponentZ(StructureNetherBridgePieces$Start var1, List var2, Random var3, int var4, int var5, boolean var6) {
      switch(super.coordBaseMode) {
      case 0:
         return this.getNextComponent(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY + var4, super.boundingBox.minZ + var5, 3, this.getComponentType(), var6);
      case 1:
         return this.getNextComponent(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var4, super.boundingBox.maxZ + 1, 0, this.getComponentType(), var6);
      case 2:
         return this.getNextComponent(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY + var4, super.boundingBox.minZ + var5, 3, this.getComponentType(), var6);
      case 3:
         return this.getNextComponent(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var4, super.boundingBox.maxZ + 1, 0, this.getComponentType(), var6);
      default:
         return null;
      }
   }

   protected static boolean isAboveGround(StructureBoundingBox var0) {
      return var0 != null && var0.minY > 10;
   }

}
