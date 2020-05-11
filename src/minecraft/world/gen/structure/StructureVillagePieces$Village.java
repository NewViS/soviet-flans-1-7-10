package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces$Start;

abstract class StructureVillagePieces$Village extends StructureComponent {

   protected int field_143015_k = -1;
   private int villagersSpawned;
   private boolean field_143014_b;


   public StructureVillagePieces$Village() {}

   protected StructureVillagePieces$Village(StructureVillagePieces$Start var1, int var2) {
      super(var2);
      if(var1 != null) {
         this.field_143014_b = var1.inDesert;
      }

   }

   protected void func_143012_a(NBTTagCompound var1) {
      var1.setInteger("HPos", this.field_143015_k);
      var1.setInteger("VCount", this.villagersSpawned);
      var1.setBoolean("Desert", this.field_143014_b);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      this.field_143015_k = var1.getInteger("HPos");
      this.villagersSpawned = var1.getInteger("VCount");
      this.field_143014_b = var1.getBoolean("Desert");
   }

   protected StructureComponent getNextComponentNN(StructureVillagePieces$Start var1, List var2, Random var3, int var4, int var5) {
      switch(super.coordBaseMode) {
      case 0:
         return StructureVillagePieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY + var4, super.boundingBox.minZ + var5, 1, this.getComponentType());
      case 1:
         return StructureVillagePieces.access$000(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var4, super.boundingBox.minZ - 1, 2, this.getComponentType());
      case 2:
         return StructureVillagePieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY + var4, super.boundingBox.minZ + var5, 1, this.getComponentType());
      case 3:
         return StructureVillagePieces.access$000(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var4, super.boundingBox.minZ - 1, 2, this.getComponentType());
      default:
         return null;
      }
   }

   protected StructureComponent getNextComponentPP(StructureVillagePieces$Start var1, List var2, Random var3, int var4, int var5) {
      switch(super.coordBaseMode) {
      case 0:
         return StructureVillagePieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY + var4, super.boundingBox.minZ + var5, 3, this.getComponentType());
      case 1:
         return StructureVillagePieces.access$000(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var4, super.boundingBox.maxZ + 1, 0, this.getComponentType());
      case 2:
         return StructureVillagePieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY + var4, super.boundingBox.minZ + var5, 3, this.getComponentType());
      case 3:
         return StructureVillagePieces.access$000(var1, var2, var3, super.boundingBox.minX + var5, super.boundingBox.minY + var4, super.boundingBox.maxZ + 1, 0, this.getComponentType());
      default:
         return null;
      }
   }

   protected int getAverageGroundLevel(World var1, StructureBoundingBox var2) {
      int var3 = 0;
      int var4 = 0;

      for(int var5 = super.boundingBox.minZ; var5 <= super.boundingBox.maxZ; ++var5) {
         for(int var6 = super.boundingBox.minX; var6 <= super.boundingBox.maxX; ++var6) {
            if(var2.isVecInside(var6, 64, var5)) {
               var3 += Math.max(var1.getTopSolidOrLiquidBlock(var6, var5), var1.provider.getAverageGroundLevel());
               ++var4;
            }
         }
      }

      if(var4 == 0) {
         return -1;
      } else {
         return var3 / var4;
      }
   }

   protected static boolean canVillageGoDeeper(StructureBoundingBox var0) {
      return var0 != null && var0.minY > 10;
   }

   protected void spawnVillagers(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6) {
      if(this.villagersSpawned < var6) {
         for(int var7 = this.villagersSpawned; var7 < var6; ++var7) {
            int var8 = this.getXWithOffset(var3 + var7, var5);
            int var9 = this.getYWithOffset(var4);
            int var10 = this.getZWithOffset(var3 + var7, var5);
            if(!var2.isVecInside(var8, var9, var10)) {
               break;
            }

            ++this.villagersSpawned;
            EntityVillager var11 = new EntityVillager(var1, this.getVillagerType(var7));
            var11.setLocationAndAngles((double)var8 + 0.5D, (double)var9, (double)var10 + 0.5D, 0.0F, 0.0F);
            var1.spawnEntityInWorld(var11);
         }

      }
   }

   protected int getVillagerType(int var1) {
      return 0;
   }

   protected Block func_151558_b(Block var1, int var2) {
      if(this.field_143014_b) {
         if(var1 == Blocks.log || var1 == Blocks.log2) {
            return Blocks.sandstone;
         }

         if(var1 == Blocks.cobblestone) {
            return Blocks.sandstone;
         }

         if(var1 == Blocks.planks) {
            return Blocks.sandstone;
         }

         if(var1 == Blocks.oak_stairs) {
            return Blocks.sandstone_stairs;
         }

         if(var1 == Blocks.stone_stairs) {
            return Blocks.sandstone_stairs;
         }

         if(var1 == Blocks.gravel) {
            return Blocks.sandstone;
         }
      }

      return var1;
   }

   protected int func_151557_c(Block var1, int var2) {
      if(this.field_143014_b) {
         if(var1 == Blocks.log || var1 == Blocks.log2) {
            return 0;
         }

         if(var1 == Blocks.cobblestone) {
            return 0;
         }

         if(var1 == Blocks.planks) {
            return 2;
         }
      }

      return var2;
   }

   protected void placeBlockAtCurrentPosition(World var1, Block var2, int var3, int var4, int var5, int var6, StructureBoundingBox var7) {
      Block var8 = this.func_151558_b(var2, var3);
      int var9 = this.func_151557_c(var2, var3);
      super.placeBlockAtCurrentPosition(var1, var8, var9, var4, var5, var6, var7);
   }

   protected void fillWithBlocks(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, Block var9, Block var10, boolean var11) {
      Block var12 = this.func_151558_b(var9, 0);
      int var13 = this.func_151557_c(var9, 0);
      Block var14 = this.func_151558_b(var10, 0);
      int var15 = this.func_151557_c(var10, 0);
      super.fillWithMetadataBlocks(var1, var2, var3, var4, var5, var6, var7, var8, var12, var13, var14, var15, var11);
   }

   protected void func_151554_b(World var1, Block var2, int var3, int var4, int var5, int var6, StructureBoundingBox var7) {
      Block var8 = this.func_151558_b(var2, var3);
      int var9 = this.func_151557_c(var2, var3);
      super.func_151554_b(var1, var8, var9, var4, var5, var6, var7);
   }
}
