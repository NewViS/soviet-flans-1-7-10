package net.minecraft.world.gen.structure;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent$BlockSelector;

public abstract class StructureComponent {

   protected StructureBoundingBox boundingBox;
   protected int coordBaseMode;
   protected int componentType;


   public StructureComponent() {}

   protected StructureComponent(int var1) {
      this.componentType = var1;
      this.coordBaseMode = -1;
   }

   public NBTTagCompound func_143010_b() {
      NBTTagCompound var1 = new NBTTagCompound();
      var1.setString("id", MapGenStructureIO.func_143036_a(this));
      var1.setTag("BB", this.boundingBox.func_151535_h());
      var1.setInteger("O", this.coordBaseMode);
      var1.setInteger("GD", this.componentType);
      this.func_143012_a(var1);
      return var1;
   }

   protected abstract void func_143012_a(NBTTagCompound var1);

   public void func_143009_a(World var1, NBTTagCompound var2) {
      if(var2.hasKey("BB")) {
         this.boundingBox = new StructureBoundingBox(var2.getIntArray("BB"));
      }

      this.coordBaseMode = var2.getInteger("O");
      this.componentType = var2.getInteger("GD");
      this.func_143011_b(var2);
   }

   protected abstract void func_143011_b(NBTTagCompound var1);

   public void buildComponent(StructureComponent var1, List var2, Random var3) {}

   public abstract boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3);

   public StructureBoundingBox getBoundingBox() {
      return this.boundingBox;
   }

   public int getComponentType() {
      return this.componentType;
   }

   public static StructureComponent findIntersecting(List var0, StructureBoundingBox var1) {
      Iterator var2 = var0.iterator();

      StructureComponent var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (StructureComponent)var2.next();
      } while(var3.getBoundingBox() == null || !var3.getBoundingBox().intersectsWith(var1));

      return var3;
   }

   public ChunkPosition func_151553_a() {
      return new ChunkPosition(this.boundingBox.getCenterX(), this.boundingBox.getCenterY(), this.boundingBox.getCenterZ());
   }

   protected boolean isLiquidInStructureBoundingBox(World var1, StructureBoundingBox var2) {
      int var3 = Math.max(this.boundingBox.minX - 1, var2.minX);
      int var4 = Math.max(this.boundingBox.minY - 1, var2.minY);
      int var5 = Math.max(this.boundingBox.minZ - 1, var2.minZ);
      int var6 = Math.min(this.boundingBox.maxX + 1, var2.maxX);
      int var7 = Math.min(this.boundingBox.maxY + 1, var2.maxY);
      int var8 = Math.min(this.boundingBox.maxZ + 1, var2.maxZ);

      int var9;
      int var10;
      for(var9 = var3; var9 <= var6; ++var9) {
         for(var10 = var5; var10 <= var8; ++var10) {
            if(var1.getBlock(var9, var4, var10).getMaterial().isLiquid()) {
               return true;
            }

            if(var1.getBlock(var9, var7, var10).getMaterial().isLiquid()) {
               return true;
            }
         }
      }

      for(var9 = var3; var9 <= var6; ++var9) {
         for(var10 = var4; var10 <= var7; ++var10) {
            if(var1.getBlock(var9, var10, var5).getMaterial().isLiquid()) {
               return true;
            }

            if(var1.getBlock(var9, var10, var8).getMaterial().isLiquid()) {
               return true;
            }
         }
      }

      for(var9 = var5; var9 <= var8; ++var9) {
         for(var10 = var4; var10 <= var7; ++var10) {
            if(var1.getBlock(var3, var10, var9).getMaterial().isLiquid()) {
               return true;
            }

            if(var1.getBlock(var6, var10, var9).getMaterial().isLiquid()) {
               return true;
            }
         }
      }

      return false;
   }

   protected int getXWithOffset(int var1, int var2) {
      switch(this.coordBaseMode) {
      case 0:
      case 2:
         return this.boundingBox.minX + var1;
      case 1:
         return this.boundingBox.maxX - var2;
      case 3:
         return this.boundingBox.minX + var2;
      default:
         return var1;
      }
   }

   protected int getYWithOffset(int var1) {
      return this.coordBaseMode == -1?var1:var1 + this.boundingBox.minY;
   }

   protected int getZWithOffset(int var1, int var2) {
      switch(this.coordBaseMode) {
      case 0:
         return this.boundingBox.minZ + var2;
      case 1:
      case 3:
         return this.boundingBox.minZ + var1;
      case 2:
         return this.boundingBox.maxZ - var2;
      default:
         return var2;
      }
   }

   protected int getMetadataWithOffset(Block var1, int var2) {
      if(var1 == Blocks.rail) {
         if(this.coordBaseMode == 1 || this.coordBaseMode == 3) {
            if(var2 == 1) {
               return 0;
            }

            return 1;
         }
      } else if(var1 != Blocks.wooden_door && var1 != Blocks.iron_door) {
         if(var1 != Blocks.stone_stairs && var1 != Blocks.oak_stairs && var1 != Blocks.nether_brick_stairs && var1 != Blocks.stone_brick_stairs && var1 != Blocks.sandstone_stairs) {
            if(var1 == Blocks.ladder) {
               if(this.coordBaseMode == 0) {
                  if(var2 == 2) {
                     return 3;
                  }

                  if(var2 == 3) {
                     return 2;
                  }
               } else if(this.coordBaseMode == 1) {
                  if(var2 == 2) {
                     return 4;
                  }

                  if(var2 == 3) {
                     return 5;
                  }

                  if(var2 == 4) {
                     return 2;
                  }

                  if(var2 == 5) {
                     return 3;
                  }
               } else if(this.coordBaseMode == 3) {
                  if(var2 == 2) {
                     return 5;
                  }

                  if(var2 == 3) {
                     return 4;
                  }

                  if(var2 == 4) {
                     return 2;
                  }

                  if(var2 == 5) {
                     return 3;
                  }
               }
            } else if(var1 == Blocks.stone_button) {
               if(this.coordBaseMode == 0) {
                  if(var2 == 3) {
                     return 4;
                  }

                  if(var2 == 4) {
                     return 3;
                  }
               } else if(this.coordBaseMode == 1) {
                  if(var2 == 3) {
                     return 1;
                  }

                  if(var2 == 4) {
                     return 2;
                  }

                  if(var2 == 2) {
                     return 3;
                  }

                  if(var2 == 1) {
                     return 4;
                  }
               } else if(this.coordBaseMode == 3) {
                  if(var2 == 3) {
                     return 2;
                  }

                  if(var2 == 4) {
                     return 1;
                  }

                  if(var2 == 2) {
                     return 3;
                  }

                  if(var2 == 1) {
                     return 4;
                  }
               }
            } else if(var1 != Blocks.tripwire_hook && !(var1 instanceof BlockDirectional)) {
               if(var1 == Blocks.piston || var1 == Blocks.sticky_piston || var1 == Blocks.lever || var1 == Blocks.dispenser) {
                  if(this.coordBaseMode == 0) {
                     if(var2 == 2 || var2 == 3) {
                        return Facing.oppositeSide[var2];
                     }
                  } else if(this.coordBaseMode == 1) {
                     if(var2 == 2) {
                        return 4;
                     }

                     if(var2 == 3) {
                        return 5;
                     }

                     if(var2 == 4) {
                        return 2;
                     }

                     if(var2 == 5) {
                        return 3;
                     }
                  } else if(this.coordBaseMode == 3) {
                     if(var2 == 2) {
                        return 5;
                     }

                     if(var2 == 3) {
                        return 4;
                     }

                     if(var2 == 4) {
                        return 2;
                     }

                     if(var2 == 5) {
                        return 3;
                     }
                  }
               }
            } else if(this.coordBaseMode == 0) {
               if(var2 == 0 || var2 == 2) {
                  return Direction.rotateOpposite[var2];
               }
            } else if(this.coordBaseMode == 1) {
               if(var2 == 2) {
                  return 1;
               }

               if(var2 == 0) {
                  return 3;
               }

               if(var2 == 1) {
                  return 2;
               }

               if(var2 == 3) {
                  return 0;
               }
            } else if(this.coordBaseMode == 3) {
               if(var2 == 2) {
                  return 3;
               }

               if(var2 == 0) {
                  return 1;
               }

               if(var2 == 1) {
                  return 2;
               }

               if(var2 == 3) {
                  return 0;
               }
            }
         } else if(this.coordBaseMode == 0) {
            if(var2 == 2) {
               return 3;
            }

            if(var2 == 3) {
               return 2;
            }
         } else if(this.coordBaseMode == 1) {
            if(var2 == 0) {
               return 2;
            }

            if(var2 == 1) {
               return 3;
            }

            if(var2 == 2) {
               return 0;
            }

            if(var2 == 3) {
               return 1;
            }
         } else if(this.coordBaseMode == 3) {
            if(var2 == 0) {
               return 2;
            }

            if(var2 == 1) {
               return 3;
            }

            if(var2 == 2) {
               return 1;
            }

            if(var2 == 3) {
               return 0;
            }
         }
      } else if(this.coordBaseMode == 0) {
         if(var2 == 0) {
            return 2;
         }

         if(var2 == 2) {
            return 0;
         }
      } else {
         if(this.coordBaseMode == 1) {
            return var2 + 1 & 3;
         }

         if(this.coordBaseMode == 3) {
            return var2 + 3 & 3;
         }
      }

      return var2;
   }

   protected void placeBlockAtCurrentPosition(World var1, Block var2, int var3, int var4, int var5, int var6, StructureBoundingBox var7) {
      int var8 = this.getXWithOffset(var4, var6);
      int var9 = this.getYWithOffset(var5);
      int var10 = this.getZWithOffset(var4, var6);
      if(var7.isVecInside(var8, var9, var10)) {
         var1.setBlock(var8, var9, var10, var2, var3, 2);
      }
   }

   protected Block getBlockAtCurrentPosition(World var1, int var2, int var3, int var4, StructureBoundingBox var5) {
      int var6 = this.getXWithOffset(var2, var4);
      int var7 = this.getYWithOffset(var3);
      int var8 = this.getZWithOffset(var2, var4);
      return !var5.isVecInside(var6, var7, var8)?Blocks.air:var1.getBlock(var6, var7, var8);
   }

   protected void fillWithAir(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      for(int var9 = var4; var9 <= var7; ++var9) {
         for(int var10 = var3; var10 <= var6; ++var10) {
            for(int var11 = var5; var11 <= var8; ++var11) {
               this.placeBlockAtCurrentPosition(var1, Blocks.air, 0, var10, var9, var11, var2);
            }
         }
      }

   }

   protected void fillWithBlocks(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, Block var9, Block var10, boolean var11) {
      for(int var12 = var4; var12 <= var7; ++var12) {
         for(int var13 = var3; var13 <= var6; ++var13) {
            for(int var14 = var5; var14 <= var8; ++var14) {
               if(!var11 || this.getBlockAtCurrentPosition(var1, var13, var12, var14, var2).getMaterial() != Material.air) {
                  if(var12 != var4 && var12 != var7 && var13 != var3 && var13 != var6 && var14 != var5 && var14 != var8) {
                     this.placeBlockAtCurrentPosition(var1, var10, 0, var13, var12, var14, var2);
                  } else {
                     this.placeBlockAtCurrentPosition(var1, var9, 0, var13, var12, var14, var2);
                  }
               }
            }
         }
      }

   }

   protected void fillWithMetadataBlocks(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, Block var9, int var10, Block var11, int var12, boolean var13) {
      for(int var14 = var4; var14 <= var7; ++var14) {
         for(int var15 = var3; var15 <= var6; ++var15) {
            for(int var16 = var5; var16 <= var8; ++var16) {
               if(!var13 || this.getBlockAtCurrentPosition(var1, var15, var14, var16, var2).getMaterial() != Material.air) {
                  if(var14 != var4 && var14 != var7 && var15 != var3 && var15 != var6 && var16 != var5 && var16 != var8) {
                     this.placeBlockAtCurrentPosition(var1, var11, var12, var15, var14, var16, var2);
                  } else {
                     this.placeBlockAtCurrentPosition(var1, var9, var10, var15, var14, var16, var2);
                  }
               }
            }
         }
      }

   }

   protected void fillWithRandomizedBlocks(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9, Random var10, StructureComponent$BlockSelector var11) {
      for(int var12 = var4; var12 <= var7; ++var12) {
         for(int var13 = var3; var13 <= var6; ++var13) {
            for(int var14 = var5; var14 <= var8; ++var14) {
               if(!var9 || this.getBlockAtCurrentPosition(var1, var13, var12, var14, var2).getMaterial() != Material.air) {
                  var11.selectBlocks(var10, var13, var12, var14, var12 == var4 || var12 == var7 || var13 == var3 || var13 == var6 || var14 == var5 || var14 == var8);
                  this.placeBlockAtCurrentPosition(var1, var11.func_151561_a(), var11.getSelectedBlockMetaData(), var13, var12, var14, var2);
               }
            }
         }
      }

   }

   protected void randomlyFillWithBlocks(World var1, StructureBoundingBox var2, Random var3, float var4, int var5, int var6, int var7, int var8, int var9, int var10, Block var11, Block var12, boolean var13) {
      for(int var14 = var6; var14 <= var9; ++var14) {
         for(int var15 = var5; var15 <= var8; ++var15) {
            for(int var16 = var7; var16 <= var10; ++var16) {
               if(var3.nextFloat() <= var4 && (!var13 || this.getBlockAtCurrentPosition(var1, var15, var14, var16, var2).getMaterial() != Material.air)) {
                  if(var14 != var6 && var14 != var9 && var15 != var5 && var15 != var8 && var16 != var7 && var16 != var10) {
                     this.placeBlockAtCurrentPosition(var1, var12, 0, var15, var14, var16, var2);
                  } else {
                     this.placeBlockAtCurrentPosition(var1, var11, 0, var15, var14, var16, var2);
                  }
               }
            }
         }
      }

   }

   protected void func_151552_a(World var1, StructureBoundingBox var2, Random var3, float var4, int var5, int var6, int var7, Block var8, int var9) {
      if(var3.nextFloat() < var4) {
         this.placeBlockAtCurrentPosition(var1, var8, var9, var5, var6, var7, var2);
      }

   }

   protected void func_151547_a(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, Block var9, boolean var10) {
      float var11 = (float)(var6 - var3 + 1);
      float var12 = (float)(var7 - var4 + 1);
      float var13 = (float)(var8 - var5 + 1);
      float var14 = (float)var3 + var11 / 2.0F;
      float var15 = (float)var5 + var13 / 2.0F;

      for(int var16 = var4; var16 <= var7; ++var16) {
         float var17 = (float)(var16 - var4) / var12;

         for(int var18 = var3; var18 <= var6; ++var18) {
            float var19 = ((float)var18 - var14) / (var11 * 0.5F);

            for(int var20 = var5; var20 <= var8; ++var20) {
               float var21 = ((float)var20 - var15) / (var13 * 0.5F);
               if(!var10 || this.getBlockAtCurrentPosition(var1, var18, var16, var20, var2).getMaterial() != Material.air) {
                  float var22 = var19 * var19 + var17 * var17 + var21 * var21;
                  if(var22 <= 1.05F) {
                     this.placeBlockAtCurrentPosition(var1, var9, 0, var18, var16, var20, var2);
                  }
               }
            }
         }
      }

   }

   protected void clearCurrentPositionBlocksUpwards(World var1, int var2, int var3, int var4, StructureBoundingBox var5) {
      int var6 = this.getXWithOffset(var2, var4);
      int var7 = this.getYWithOffset(var3);
      int var8 = this.getZWithOffset(var2, var4);
      if(var5.isVecInside(var6, var7, var8)) {
         while(!var1.isAirBlock(var6, var7, var8) && var7 < 255) {
            var1.setBlock(var6, var7, var8, Blocks.air, 0, 2);
            ++var7;
         }

      }
   }

   protected void func_151554_b(World var1, Block var2, int var3, int var4, int var5, int var6, StructureBoundingBox var7) {
      int var8 = this.getXWithOffset(var4, var6);
      int var9 = this.getYWithOffset(var5);
      int var10 = this.getZWithOffset(var4, var6);
      if(var7.isVecInside(var8, var9, var10)) {
         while((var1.isAirBlock(var8, var9, var10) || var1.getBlock(var8, var9, var10).getMaterial().isLiquid()) && var9 > 1) {
            var1.setBlock(var8, var9, var10, var2, var3, 2);
            --var9;
         }

      }
   }

   protected boolean generateStructureChestContents(World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, WeightedRandomChestContent[] var7, int var8) {
      int var9 = this.getXWithOffset(var4, var6);
      int var10 = this.getYWithOffset(var5);
      int var11 = this.getZWithOffset(var4, var6);
      if(var2.isVecInside(var9, var10, var11) && var1.getBlock(var9, var10, var11) != Blocks.chest) {
         var1.setBlock(var9, var10, var11, Blocks.chest, 0, 2);
         TileEntityChest var12 = (TileEntityChest)var1.getTileEntity(var9, var10, var11);
         if(var12 != null) {
            WeightedRandomChestContent.generateChestContents(var3, var7, var12, var8);
         }

         return true;
      } else {
         return false;
      }
   }

   protected boolean generateStructureDispenserContents(World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, int var7, WeightedRandomChestContent[] var8, int var9) {
      int var10 = this.getXWithOffset(var4, var6);
      int var11 = this.getYWithOffset(var5);
      int var12 = this.getZWithOffset(var4, var6);
      if(var2.isVecInside(var10, var11, var12) && var1.getBlock(var10, var11, var12) != Blocks.dispenser) {
         var1.setBlock(var10, var11, var12, Blocks.dispenser, this.getMetadataWithOffset(Blocks.dispenser, var7), 2);
         TileEntityDispenser var13 = (TileEntityDispenser)var1.getTileEntity(var10, var11, var12);
         if(var13 != null) {
            WeightedRandomChestContent.generateDispenserContents(var3, var8, var13, var9);
         }

         return true;
      } else {
         return false;
      }
   }

   protected void placeDoorAtCurrentPosition(World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, int var7) {
      int var8 = this.getXWithOffset(var4, var6);
      int var9 = this.getYWithOffset(var5);
      int var10 = this.getZWithOffset(var4, var6);
      if(var2.isVecInside(var8, var9, var10)) {
         ItemDoor.placeDoorBlock(var1, var8, var9, var10, var7, Blocks.wooden_door);
      }

   }
}
