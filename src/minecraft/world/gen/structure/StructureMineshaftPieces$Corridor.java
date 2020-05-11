package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;

public class StructureMineshaftPieces$Corridor extends StructureComponent {

   private boolean hasRails;
   private boolean hasSpiders;
   private boolean spawnerPlaced;
   private int sectionCount;


   public StructureMineshaftPieces$Corridor() {}

   protected void func_143012_a(NBTTagCompound var1) {
      var1.setBoolean("hr", this.hasRails);
      var1.setBoolean("sc", this.hasSpiders);
      var1.setBoolean("hps", this.spawnerPlaced);
      var1.setInteger("Num", this.sectionCount);
   }

   protected void func_143011_b(NBTTagCompound var1) {
      this.hasRails = var1.getBoolean("hr");
      this.hasSpiders = var1.getBoolean("sc");
      this.spawnerPlaced = var1.getBoolean("hps");
      this.sectionCount = var1.getInteger("Num");
   }

   public StructureMineshaftPieces$Corridor(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      super.coordBaseMode = var4;
      super.boundingBox = var3;
      this.hasRails = var2.nextInt(3) == 0;
      this.hasSpiders = !this.hasRails && var2.nextInt(23) == 0;
      if(super.coordBaseMode != 2 && super.coordBaseMode != 0) {
         this.sectionCount = var3.getXSize() / 5;
      } else {
         this.sectionCount = var3.getZSize() / 5;
      }

   }

   public static StructureBoundingBox findValidPlacement(List var0, Random var1, int var2, int var3, int var4, int var5) {
      StructureBoundingBox var6 = new StructureBoundingBox(var2, var3, var4, var2, var3 + 2, var4);

      int var7;
      for(var7 = var1.nextInt(3) + 2; var7 > 0; --var7) {
         int var8 = var7 * 5;
         switch(var5) {
         case 0:
            var6.maxX = var2 + 2;
            var6.maxZ = var4 + (var8 - 1);
            break;
         case 1:
            var6.minX = var2 - (var8 - 1);
            var6.maxZ = var4 + 2;
            break;
         case 2:
            var6.maxX = var2 + 2;
            var6.minZ = var4 - (var8 - 1);
            break;
         case 3:
            var6.maxX = var2 + (var8 - 1);
            var6.maxZ = var4 + 2;
         }

         if(StructureComponent.findIntersecting(var0, var6) == null) {
            break;
         }
      }

      return var7 > 0?var6:null;
   }

   public void buildComponent(StructureComponent var1, List var2, Random var3) {
      int var4 = this.getComponentType();
      int var5 = var3.nextInt(4);
      switch(super.coordBaseMode) {
      case 0:
         if(var5 <= 1) {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX, super.boundingBox.minY - 1 + var3.nextInt(3), super.boundingBox.maxZ + 1, super.coordBaseMode, var4);
         } else if(var5 == 2) {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY - 1 + var3.nextInt(3), super.boundingBox.maxZ - 3, 1, var4);
         } else {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY - 1 + var3.nextInt(3), super.boundingBox.maxZ - 3, 3, var4);
         }
         break;
      case 1:
         if(var5 <= 1) {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY - 1 + var3.nextInt(3), super.boundingBox.minZ, super.coordBaseMode, var4);
         } else if(var5 == 2) {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX, super.boundingBox.minY - 1 + var3.nextInt(3), super.boundingBox.minZ - 1, 2, var4);
         } else {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX, super.boundingBox.minY - 1 + var3.nextInt(3), super.boundingBox.maxZ + 1, 0, var4);
         }
         break;
      case 2:
         if(var5 <= 1) {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX, super.boundingBox.minY - 1 + var3.nextInt(3), super.boundingBox.minZ - 1, super.coordBaseMode, var4);
         } else if(var5 == 2) {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY - 1 + var3.nextInt(3), super.boundingBox.minZ, 1, var4);
         } else {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY - 1 + var3.nextInt(3), super.boundingBox.minZ, 3, var4);
         }
         break;
      case 3:
         if(var5 <= 1) {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY - 1 + var3.nextInt(3), super.boundingBox.minZ, super.coordBaseMode, var4);
         } else if(var5 == 2) {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.maxX - 3, super.boundingBox.minY - 1 + var3.nextInt(3), super.boundingBox.minZ - 1, 2, var4);
         } else {
            StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.maxX - 3, super.boundingBox.minY - 1 + var3.nextInt(3), super.boundingBox.maxZ + 1, 0, var4);
         }
      }

      if(var4 < 8) {
         int var6;
         int var7;
         if(super.coordBaseMode != 2 && super.coordBaseMode != 0) {
            for(var6 = super.boundingBox.minX + 3; var6 + 3 <= super.boundingBox.maxX; var6 += 5) {
               var7 = var3.nextInt(5);
               if(var7 == 0) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, var6, super.boundingBox.minY, super.boundingBox.minZ - 1, 2, var4 + 1);
               } else if(var7 == 1) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, var6, super.boundingBox.minY, super.boundingBox.maxZ + 1, 0, var4 + 1);
               }
            }
         } else {
            for(var6 = super.boundingBox.minZ + 3; var6 + 3 <= super.boundingBox.maxZ; var6 += 5) {
               var7 = var3.nextInt(5);
               if(var7 == 0) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.minX - 1, super.boundingBox.minY, var6, 1, var4 + 1);
               } else if(var7 == 1) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, super.boundingBox.maxX + 1, super.boundingBox.minY, var6, 3, var4 + 1);
               }
            }
         }
      }

   }

   protected boolean generateStructureChestContents(World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, WeightedRandomChestContent[] var7, int var8) {
      int var9 = this.getXWithOffset(var4, var6);
      int var10 = this.getYWithOffset(var5);
      int var11 = this.getZWithOffset(var4, var6);
      if(var2.isVecInside(var9, var10, var11) && var1.getBlock(var9, var10, var11).getMaterial() == Material.air) {
         int var12 = var3.nextBoolean()?1:0;
         var1.setBlock(var9, var10, var11, Blocks.rail, this.getMetadataWithOffset(Blocks.rail, var12), 2);
         EntityMinecartChest var13 = new EntityMinecartChest(var1, (double)((float)var9 + 0.5F), (double)((float)var10 + 0.5F), (double)((float)var11 + 0.5F));
         WeightedRandomChestContent.generateChestContents(var3, var7, var13, var8);
         var1.spawnEntityInWorld(var13);
         return true;
      } else {
         return false;
      }
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if(this.isLiquidInStructureBoundingBox(var1, var3)) {
         return false;
      } else {
         boolean var4 = false;
         boolean var5 = true;
         boolean var6 = false;
         boolean var7 = true;
         int var8 = this.sectionCount * 5 - 1;
         this.fillWithBlocks(var1, var3, 0, 0, 0, 2, 1, var8, Blocks.air, Blocks.air, false);
         this.randomlyFillWithBlocks(var1, var3, var2, 0.8F, 0, 2, 0, 2, 2, var8, Blocks.air, Blocks.air, false);
         if(this.hasSpiders) {
            this.randomlyFillWithBlocks(var1, var3, var2, 0.6F, 0, 0, 0, 2, 1, var8, Blocks.web, Blocks.air, false);
         }

         int var9;
         int var10;
         for(var9 = 0; var9 < this.sectionCount; ++var9) {
            var10 = 2 + var9 * 5;
            this.fillWithBlocks(var1, var3, 0, 0, var10, 0, 1, var10, Blocks.fence, Blocks.air, false);
            this.fillWithBlocks(var1, var3, 2, 0, var10, 2, 1, var10, Blocks.fence, Blocks.air, false);
            if(var2.nextInt(4) == 0) {
               this.fillWithBlocks(var1, var3, 0, 2, var10, 0, 2, var10, Blocks.planks, Blocks.air, false);
               this.fillWithBlocks(var1, var3, 2, 2, var10, 2, 2, var10, Blocks.planks, Blocks.air, false);
            } else {
               this.fillWithBlocks(var1, var3, 0, 2, var10, 2, 2, var10, Blocks.planks, Blocks.air, false);
            }

            this.func_151552_a(var1, var3, var2, 0.1F, 0, 2, var10 - 1, Blocks.web, 0);
            this.func_151552_a(var1, var3, var2, 0.1F, 2, 2, var10 - 1, Blocks.web, 0);
            this.func_151552_a(var1, var3, var2, 0.1F, 0, 2, var10 + 1, Blocks.web, 0);
            this.func_151552_a(var1, var3, var2, 0.1F, 2, 2, var10 + 1, Blocks.web, 0);
            this.func_151552_a(var1, var3, var2, 0.05F, 0, 2, var10 - 2, Blocks.web, 0);
            this.func_151552_a(var1, var3, var2, 0.05F, 2, 2, var10 - 2, Blocks.web, 0);
            this.func_151552_a(var1, var3, var2, 0.05F, 0, 2, var10 + 2, Blocks.web, 0);
            this.func_151552_a(var1, var3, var2, 0.05F, 2, 2, var10 + 2, Blocks.web, 0);
            this.func_151552_a(var1, var3, var2, 0.05F, 1, 2, var10 - 1, Blocks.torch, 0);
            this.func_151552_a(var1, var3, var2, 0.05F, 1, 2, var10 + 1, Blocks.torch, 0);
            if(var2.nextInt(100) == 0) {
               this.generateStructureChestContents(var1, var3, var2, 2, 0, var10 - 1, WeightedRandomChestContent.func_92080_a(StructureMineshaftPieces.access$100(), new WeightedRandomChestContent[]{Items.enchanted_book.func_92114_b(var2)}), 3 + var2.nextInt(4));
            }

            if(var2.nextInt(100) == 0) {
               this.generateStructureChestContents(var1, var3, var2, 0, 0, var10 + 1, WeightedRandomChestContent.func_92080_a(StructureMineshaftPieces.access$100(), new WeightedRandomChestContent[]{Items.enchanted_book.func_92114_b(var2)}), 3 + var2.nextInt(4));
            }

            if(this.hasSpiders && !this.spawnerPlaced) {
               int var11 = this.getYWithOffset(0);
               int var12 = var10 - 1 + var2.nextInt(3);
               int var13 = this.getXWithOffset(1, var12);
               var12 = this.getZWithOffset(1, var12);
               if(var3.isVecInside(var13, var11, var12)) {
                  this.spawnerPlaced = true;
                  var1.setBlock(var13, var11, var12, Blocks.mob_spawner, 0, 2);
                  TileEntityMobSpawner var14 = (TileEntityMobSpawner)var1.getTileEntity(var13, var11, var12);
                  if(var14 != null) {
                     var14.func_145881_a().setEntityName("CaveSpider");
                  }
               }
            }
         }

         for(var9 = 0; var9 <= 2; ++var9) {
            for(var10 = 0; var10 <= var8; ++var10) {
               byte var16 = -1;
               Block var17 = this.getBlockAtCurrentPosition(var1, var9, var16, var10, var3);
               if(var17.getMaterial() == Material.air) {
                  byte var18 = -1;
                  this.placeBlockAtCurrentPosition(var1, Blocks.planks, 0, var9, var18, var10, var3);
               }
            }
         }

         if(this.hasRails) {
            for(var9 = 0; var9 <= var8; ++var9) {
               Block var15 = this.getBlockAtCurrentPosition(var1, 1, -1, var9, var3);
               if(var15.getMaterial() != Material.air && var15.func_149730_j()) {
                  this.func_151552_a(var1, var3, var2, 0.7F, 1, 0, var9, Blocks.rail, this.getMetadataWithOffset(Blocks.rail, 0));
               }
            }
         }

         return true;
      }
   }
}
