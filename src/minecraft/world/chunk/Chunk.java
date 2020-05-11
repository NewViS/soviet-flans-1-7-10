package net.minecraft.world.chunk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk$1;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Chunk {

   private static final Logger logger = LogManager.getLogger();
   public static boolean isLit;
   private ExtendedBlockStorage[] storageArrays;
   private byte[] blockBiomeArray;
   public int[] precipitationHeightMap;
   public boolean[] updateSkylightColumns;
   public boolean isChunkLoaded;
   public World worldObj;
   public int[] heightMap;
   public final int xPosition;
   public final int zPosition;
   private boolean isGapLightingUpdated;
   public Map chunkTileEntityMap;
   public List[] entityLists;
   public boolean isTerrainPopulated;
   public boolean isLightPopulated;
   public boolean field_150815_m;
   public boolean isModified;
   public boolean hasEntities;
   public long lastSaveTime;
   public boolean sendUpdates;
   public int heightMapMinimum;
   public long inhabitedTime;
   private int queuedLightChecks;


   public Chunk(World var1, int var2, int var3) {
      this.storageArrays = new ExtendedBlockStorage[16];
      this.blockBiomeArray = new byte[256];
      this.precipitationHeightMap = new int[256];
      this.updateSkylightColumns = new boolean[256];
      this.chunkTileEntityMap = new HashMap();
      this.queuedLightChecks = 4096;
      this.entityLists = new List[16];
      this.worldObj = var1;
      this.xPosition = var2;
      this.zPosition = var3;
      this.heightMap = new int[256];

      for(int var4 = 0; var4 < this.entityLists.length; ++var4) {
         this.entityLists[var4] = new ArrayList();
      }

      Arrays.fill(this.precipitationHeightMap, -999);
      Arrays.fill(this.blockBiomeArray, (byte)-1);
   }

   public Chunk(World var1, Block[] var2, int var3, int var4) {
      this(var1, var3, var4);
      int var5 = var2.length / 256;
      boolean var6 = !var1.provider.hasNoSky;

      for(int var7 = 0; var7 < 16; ++var7) {
         for(int var8 = 0; var8 < 16; ++var8) {
            for(int var9 = 0; var9 < var5; ++var9) {
               Block var10 = var2[var7 << 11 | var8 << 7 | var9];
               if(var10 != null && var10.getMaterial() != Material.air) {
                  int var11 = var9 >> 4;
                  if(this.storageArrays[var11] == null) {
                     this.storageArrays[var11] = new ExtendedBlockStorage(var11 << 4, var6);
                  }

                  this.storageArrays[var11].func_150818_a(var7, var9 & 15, var8, var10);
               }
            }
         }
      }

   }

   public Chunk(World var1, Block[] var2, byte[] var3, int var4, int var5) {
      this(var1, var4, var5);
      int var6 = var2.length / 256;
      boolean var7 = !var1.provider.hasNoSky;

      for(int var8 = 0; var8 < 16; ++var8) {
         for(int var9 = 0; var9 < 16; ++var9) {
            for(int var10 = 0; var10 < var6; ++var10) {
               int var11 = var8 * var6 * 16 | var9 * var6 | var10;
               Block var12 = var2[var11];
               if(var12 != null && var12 != Blocks.air) {
                  int var13 = var10 >> 4;
                  if(this.storageArrays[var13] == null) {
                     this.storageArrays[var13] = new ExtendedBlockStorage(var13 << 4, var7);
                  }

                  this.storageArrays[var13].func_150818_a(var8, var10 & 15, var9, var12);
                  this.storageArrays[var13].setExtBlockMetadata(var8, var10 & 15, var9, var3[var11]);
               }
            }
         }
      }

   }

   public boolean isAtLocation(int var1, int var2) {
      return var1 == this.xPosition && var2 == this.zPosition;
   }

   public int getHeightValue(int var1, int var2) {
      return this.heightMap[var2 << 4 | var1];
   }

   public int getTopFilledSegment() {
      for(int var1 = this.storageArrays.length - 1; var1 >= 0; --var1) {
         if(this.storageArrays[var1] != null) {
            return this.storageArrays[var1].getYLocation();
         }
      }

      return 0;
   }

   public ExtendedBlockStorage[] getBlockStorageArray() {
      return this.storageArrays;
   }

   public void generateHeightMap() {
      int var1 = this.getTopFilledSegment();
      this.heightMapMinimum = Integer.MAX_VALUE;

      for(int var2 = 0; var2 < 16; ++var2) {
         int var3 = 0;

         while(var3 < 16) {
            this.precipitationHeightMap[var2 + (var3 << 4)] = -999;
            int var4 = var1 + 16 - 1;

            while(true) {
               if(var4 > 0) {
                  Block var5 = this.getBlock(var2, var4 - 1, var3);
                  if(var5.getLightOpacity() == 0) {
                     --var4;
                     continue;
                  }

                  this.heightMap[var3 << 4 | var2] = var4;
                  if(var4 < this.heightMapMinimum) {
                     this.heightMapMinimum = var4;
                  }
               }

               ++var3;
               break;
            }
         }
      }

      this.isModified = true;
   }

   public void generateSkylightMap() {
      int var1 = this.getTopFilledSegment();
      this.heightMapMinimum = Integer.MAX_VALUE;

      for(int var2 = 0; var2 < 16; ++var2) {
         int var3 = 0;

         while(var3 < 16) {
            this.precipitationHeightMap[var2 + (var3 << 4)] = -999;
            int var4 = var1 + 16 - 1;

            while(true) {
               if(var4 > 0) {
                  if(this.func_150808_b(var2, var4 - 1, var3) == 0) {
                     --var4;
                     continue;
                  }

                  this.heightMap[var3 << 4 | var2] = var4;
                  if(var4 < this.heightMapMinimum) {
                     this.heightMapMinimum = var4;
                  }
               }

               if(!this.worldObj.provider.hasNoSky) {
                  var4 = 15;
                  int var5 = var1 + 16 - 1;

                  do {
                     int var6 = this.func_150808_b(var2, var5, var3);
                     if(var6 == 0 && var4 != 15) {
                        var6 = 1;
                     }

                     var4 -= var6;
                     if(var4 > 0) {
                        ExtendedBlockStorage var7 = this.storageArrays[var5 >> 4];
                        if(var7 != null) {
                           var7.setExtSkylightValue(var2, var5 & 15, var3, var4);
                           this.worldObj.func_147479_m((this.xPosition << 4) + var2, var5, (this.zPosition << 4) + var3);
                        }
                     }

                     --var5;
                  } while(var5 > 0 && var4 > 0);
               }

               ++var3;
               break;
            }
         }
      }

      this.isModified = true;
   }

   private void propagateSkylightOcclusion(int var1, int var2) {
      this.updateSkylightColumns[var1 + var2 * 16] = true;
      this.isGapLightingUpdated = true;
   }

   private void recheckGaps(boolean var1) {
      this.worldObj.theProfiler.startSection("recheckGaps");
      if(this.worldObj.doChunksNearChunkExist(this.xPosition * 16 + 8, 0, this.zPosition * 16 + 8, 16)) {
         for(int var2 = 0; var2 < 16; ++var2) {
            for(int var3 = 0; var3 < 16; ++var3) {
               if(this.updateSkylightColumns[var2 + var3 * 16]) {
                  this.updateSkylightColumns[var2 + var3 * 16] = false;
                  int var4 = this.getHeightValue(var2, var3);
                  int var5 = this.xPosition * 16 + var2;
                  int var6 = this.zPosition * 16 + var3;
                  int var7 = this.worldObj.getChunkHeightMapMinimum(var5 - 1, var6);
                  int var8 = this.worldObj.getChunkHeightMapMinimum(var5 + 1, var6);
                  int var9 = this.worldObj.getChunkHeightMapMinimum(var5, var6 - 1);
                  int var10 = this.worldObj.getChunkHeightMapMinimum(var5, var6 + 1);
                  if(var8 < var7) {
                     var7 = var8;
                  }

                  if(var9 < var7) {
                     var7 = var9;
                  }

                  if(var10 < var7) {
                     var7 = var10;
                  }

                  this.checkSkylightNeighborHeight(var5, var6, var7);
                  this.checkSkylightNeighborHeight(var5 - 1, var6, var4);
                  this.checkSkylightNeighborHeight(var5 + 1, var6, var4);
                  this.checkSkylightNeighborHeight(var5, var6 - 1, var4);
                  this.checkSkylightNeighborHeight(var5, var6 + 1, var4);
                  if(var1) {
                     this.worldObj.theProfiler.endSection();
                     return;
                  }
               }
            }
         }

         this.isGapLightingUpdated = false;
      }

      this.worldObj.theProfiler.endSection();
   }

   private void checkSkylightNeighborHeight(int var1, int var2, int var3) {
      int var4 = this.worldObj.getHeightValue(var1, var2);
      if(var4 > var3) {
         this.updateSkylightNeighborHeight(var1, var2, var3, var4 + 1);
      } else if(var4 < var3) {
         this.updateSkylightNeighborHeight(var1, var2, var4, var3 + 1);
      }

   }

   private void updateSkylightNeighborHeight(int var1, int var2, int var3, int var4) {
      if(var4 > var3 && this.worldObj.doChunksNearChunkExist(var1, 0, var2, 16)) {
         for(int var5 = var3; var5 < var4; ++var5) {
            this.worldObj.updateLightByType(EnumSkyBlock.Sky, var1, var5, var2);
         }

         this.isModified = true;
      }

   }

   private void relightBlock(int var1, int var2, int var3) {
      int var4 = this.heightMap[var3 << 4 | var1] & 255;
      int var5 = var4;
      if(var2 > var4) {
         var5 = var2;
      }

      while(var5 > 0 && this.func_150808_b(var1, var5 - 1, var3) == 0) {
         --var5;
      }

      if(var5 != var4) {
         this.worldObj.markBlocksDirtyVertical(var1 + this.xPosition * 16, var3 + this.zPosition * 16, var5, var4);
         this.heightMap[var3 << 4 | var1] = var5;
         int var6 = this.xPosition * 16 + var1;
         int var7 = this.zPosition * 16 + var3;
         int var8;
         int var12;
         if(!this.worldObj.provider.hasNoSky) {
            ExtendedBlockStorage var9;
            if(var5 < var4) {
               for(var8 = var5; var8 < var4; ++var8) {
                  var9 = this.storageArrays[var8 >> 4];
                  if(var9 != null) {
                     var9.setExtSkylightValue(var1, var8 & 15, var3, 15);
                     this.worldObj.func_147479_m((this.xPosition << 4) + var1, var8, (this.zPosition << 4) + var3);
                  }
               }
            } else {
               for(var8 = var4; var8 < var5; ++var8) {
                  var9 = this.storageArrays[var8 >> 4];
                  if(var9 != null) {
                     var9.setExtSkylightValue(var1, var8 & 15, var3, 0);
                     this.worldObj.func_147479_m((this.xPosition << 4) + var1, var8, (this.zPosition << 4) + var3);
                  }
               }
            }

            var8 = 15;

            while(var5 > 0 && var8 > 0) {
               --var5;
               var12 = this.func_150808_b(var1, var5, var3);
               if(var12 == 0) {
                  var12 = 1;
               }

               var8 -= var12;
               if(var8 < 0) {
                  var8 = 0;
               }

               ExtendedBlockStorage var10 = this.storageArrays[var5 >> 4];
               if(var10 != null) {
                  var10.setExtSkylightValue(var1, var5 & 15, var3, var8);
               }
            }
         }

         var8 = this.heightMap[var3 << 4 | var1];
         var12 = var4;
         int var13 = var8;
         if(var8 < var4) {
            var12 = var8;
            var13 = var4;
         }

         if(var8 < this.heightMapMinimum) {
            this.heightMapMinimum = var8;
         }

         if(!this.worldObj.provider.hasNoSky) {
            this.updateSkylightNeighborHeight(var6 - 1, var7, var12, var13);
            this.updateSkylightNeighborHeight(var6 + 1, var7, var12, var13);
            this.updateSkylightNeighborHeight(var6, var7 - 1, var12, var13);
            this.updateSkylightNeighborHeight(var6, var7 + 1, var12, var13);
            this.updateSkylightNeighborHeight(var6, var7, var12, var13);
         }

         this.isModified = true;
      }
   }

   public int func_150808_b(int var1, int var2, int var3) {
      return this.getBlock(var1, var2, var3).getLightOpacity();
   }

   public Block getBlock(int var1, int var2, int var3) {
      Block var4 = Blocks.air;
      if(var2 >> 4 < this.storageArrays.length) {
         ExtendedBlockStorage var5 = this.storageArrays[var2 >> 4];
         if(var5 != null) {
            try {
               var4 = var5.getBlockByExtId(var1, var2 & 15, var3);
            } catch (Throwable var9) {
               CrashReport var7 = CrashReport.makeCrashReport(var9, "Getting block");
               CrashReportCategory var8 = var7.makeCategory("Block being got");
               var8.addCrashSectionCallable("Location", new Chunk$1(this, var1, var2, var3));
               throw new ReportedException(var7);
            }
         }
      }

      return var4;
   }

   public int getBlockMetadata(int var1, int var2, int var3) {
      if(var2 >> 4 >= this.storageArrays.length) {
         return 0;
      } else {
         ExtendedBlockStorage var4 = this.storageArrays[var2 >> 4];
         return var4 != null?var4.getExtBlockMetadata(var1, var2 & 15, var3):0;
      }
   }

   public boolean func_150807_a(int var1, int var2, int var3, Block var4, int var5) {
      int var6 = var3 << 4 | var1;
      if(var2 >= this.precipitationHeightMap[var6] - 1) {
         this.precipitationHeightMap[var6] = -999;
      }

      int var7 = this.heightMap[var6];
      Block var8 = this.getBlock(var1, var2, var3);
      int var9 = this.getBlockMetadata(var1, var2, var3);
      if(var8 == var4 && var9 == var5) {
         return false;
      } else {
         ExtendedBlockStorage var10 = this.storageArrays[var2 >> 4];
         boolean var11 = false;
         if(var10 == null) {
            if(var4 == Blocks.air) {
               return false;
            }

            var10 = this.storageArrays[var2 >> 4] = new ExtendedBlockStorage(var2 >> 4 << 4, !this.worldObj.provider.hasNoSky);
            var11 = var2 >= var7;
         }

         int var12 = this.xPosition * 16 + var1;
         int var13 = this.zPosition * 16 + var3;
         if(!this.worldObj.isRemote) {
            var8.onBlockPreDestroy(this.worldObj, var12, var2, var13, var9);
         }

         var10.func_150818_a(var1, var2 & 15, var3, var4);
         if(!this.worldObj.isRemote) {
            var8.breakBlock(this.worldObj, var12, var2, var13, var8, var9);
         } else if(var8 instanceof ITileEntityProvider && var8 != var4) {
            this.worldObj.removeTileEntity(var12, var2, var13);
         }

         if(var10.getBlockByExtId(var1, var2 & 15, var3) != var4) {
            return false;
         } else {
            var10.setExtBlockMetadata(var1, var2 & 15, var3, var5);
            if(var11) {
               this.generateSkylightMap();
            } else {
               int var14 = var4.getLightOpacity();
               int var15 = var8.getLightOpacity();
               if(var14 > 0) {
                  if(var2 >= var7) {
                     this.relightBlock(var1, var2 + 1, var3);
                  }
               } else if(var2 == var7 - 1) {
                  this.relightBlock(var1, var2, var3);
               }

               if(var14 != var15 && (var14 < var15 || this.getSavedLightValue(EnumSkyBlock.Sky, var1, var2, var3) > 0 || this.getSavedLightValue(EnumSkyBlock.Block, var1, var2, var3) > 0)) {
                  this.propagateSkylightOcclusion(var1, var3);
               }
            }

            TileEntity var16;
            if(var8 instanceof ITileEntityProvider) {
               var16 = this.func_150806_e(var1, var2, var3);
               if(var16 != null) {
                  var16.updateContainingBlockInfo();
               }
            }

            if(!this.worldObj.isRemote) {
               var4.onBlockAdded(this.worldObj, var12, var2, var13);
            }

            if(var4 instanceof ITileEntityProvider) {
               var16 = this.func_150806_e(var1, var2, var3);
               if(var16 == null) {
                  var16 = ((ITileEntityProvider)var4).createNewTileEntity(this.worldObj, var5);
                  this.worldObj.setTileEntity(var12, var2, var13, var16);
               }

               if(var16 != null) {
                  var16.updateContainingBlockInfo();
               }
            }

            this.isModified = true;
            return true;
         }
      }
   }

   public boolean setBlockMetadata(int var1, int var2, int var3, int var4) {
      ExtendedBlockStorage var5 = this.storageArrays[var2 >> 4];
      if(var5 == null) {
         return false;
      } else {
         int var6 = var5.getExtBlockMetadata(var1, var2 & 15, var3);
         if(var6 == var4) {
            return false;
         } else {
            this.isModified = true;
            var5.setExtBlockMetadata(var1, var2 & 15, var3, var4);
            if(var5.getBlockByExtId(var1, var2 & 15, var3) instanceof ITileEntityProvider) {
               TileEntity var7 = this.func_150806_e(var1, var2, var3);
               if(var7 != null) {
                  var7.updateContainingBlockInfo();
                  var7.blockMetadata = var4;
               }
            }

            return true;
         }
      }
   }

   public int getSavedLightValue(EnumSkyBlock var1, int var2, int var3, int var4) {
      ExtendedBlockStorage var5 = this.storageArrays[var3 >> 4];
      return var5 == null?(this.canBlockSeeTheSky(var2, var3, var4)?var1.defaultLightValue:0):(var1 == EnumSkyBlock.Sky?(this.worldObj.provider.hasNoSky?0:var5.getExtSkylightValue(var2, var3 & 15, var4)):(var1 == EnumSkyBlock.Block?var5.getExtBlocklightValue(var2, var3 & 15, var4):var1.defaultLightValue));
   }

   public void setLightValue(EnumSkyBlock var1, int var2, int var3, int var4, int var5) {
      ExtendedBlockStorage var6 = this.storageArrays[var3 >> 4];
      if(var6 == null) {
         var6 = this.storageArrays[var3 >> 4] = new ExtendedBlockStorage(var3 >> 4 << 4, !this.worldObj.provider.hasNoSky);
         this.generateSkylightMap();
      }

      this.isModified = true;
      if(var1 == EnumSkyBlock.Sky) {
         if(!this.worldObj.provider.hasNoSky) {
            var6.setExtSkylightValue(var2, var3 & 15, var4, var5);
         }
      } else if(var1 == EnumSkyBlock.Block) {
         var6.setExtBlocklightValue(var2, var3 & 15, var4, var5);
      }

   }

   public int getBlockLightValue(int var1, int var2, int var3, int var4) {
      ExtendedBlockStorage var5 = this.storageArrays[var2 >> 4];
      if(var5 == null) {
         return !this.worldObj.provider.hasNoSky && var4 < EnumSkyBlock.Sky.defaultLightValue?EnumSkyBlock.Sky.defaultLightValue - var4:0;
      } else {
         int var6 = this.worldObj.provider.hasNoSky?0:var5.getExtSkylightValue(var1, var2 & 15, var3);
         if(var6 > 0) {
            isLit = true;
         }

         var6 -= var4;
         int var7 = var5.getExtBlocklightValue(var1, var2 & 15, var3);
         if(var7 > var6) {
            var6 = var7;
         }

         return var6;
      }
   }

   public void addEntity(Entity var1) {
      this.hasEntities = true;
      int var2 = MathHelper.floor_double(var1.posX / 16.0D);
      int var3 = MathHelper.floor_double(var1.posZ / 16.0D);
      if(var2 != this.xPosition || var3 != this.zPosition) {
         logger.warn("Wrong location! " + var1 + " (at " + var2 + ", " + var3 + " instead of " + this.xPosition + ", " + this.zPosition + ")");
         Thread.dumpStack();
      }

      int var4 = MathHelper.floor_double(var1.posY / 16.0D);
      if(var4 < 0) {
         var4 = 0;
      }

      if(var4 >= this.entityLists.length) {
         var4 = this.entityLists.length - 1;
      }

      var1.addedToChunk = true;
      var1.chunkCoordX = this.xPosition;
      var1.chunkCoordY = var4;
      var1.chunkCoordZ = this.zPosition;
      this.entityLists[var4].add(var1);
   }

   public void removeEntity(Entity var1) {
      this.removeEntityAtIndex(var1, var1.chunkCoordY);
   }

   public void removeEntityAtIndex(Entity var1, int var2) {
      if(var2 < 0) {
         var2 = 0;
      }

      if(var2 >= this.entityLists.length) {
         var2 = this.entityLists.length - 1;
      }

      this.entityLists[var2].remove(var1);
   }

   public boolean canBlockSeeTheSky(int var1, int var2, int var3) {
      return var2 >= this.heightMap[var3 << 4 | var1];
   }

   public TileEntity func_150806_e(int var1, int var2, int var3) {
      ChunkPosition var4 = new ChunkPosition(var1, var2, var3);
      TileEntity var5 = (TileEntity)this.chunkTileEntityMap.get(var4);
      if(var5 == null) {
         Block var6 = this.getBlock(var1, var2, var3);
         if(!var6.hasTileEntity()) {
            return null;
         }

         var5 = ((ITileEntityProvider)var6).createNewTileEntity(this.worldObj, this.getBlockMetadata(var1, var2, var3));
         this.worldObj.setTileEntity(this.xPosition * 16 + var1, var2, this.zPosition * 16 + var3, var5);
      }

      if(var5 != null && var5.isInvalid()) {
         this.chunkTileEntityMap.remove(var4);
         return null;
      } else {
         return var5;
      }
   }

   public void addTileEntity(TileEntity var1) {
      int var2 = var1.xCoord - this.xPosition * 16;
      int var3 = var1.yCoord;
      int var4 = var1.zCoord - this.zPosition * 16;
      this.func_150812_a(var2, var3, var4, var1);
      if(this.isChunkLoaded) {
         this.worldObj.loadedTileEntityList.add(var1);
      }

   }

   public void func_150812_a(int var1, int var2, int var3, TileEntity var4) {
      ChunkPosition var5 = new ChunkPosition(var1, var2, var3);
      var4.setWorldObj(this.worldObj);
      var4.xCoord = this.xPosition * 16 + var1;
      var4.yCoord = var2;
      var4.zCoord = this.zPosition * 16 + var3;
      if(this.getBlock(var1, var2, var3) instanceof ITileEntityProvider) {
         if(this.chunkTileEntityMap.containsKey(var5)) {
            ((TileEntity)this.chunkTileEntityMap.get(var5)).invalidate();
         }

         var4.validate();
         this.chunkTileEntityMap.put(var5, var4);
      }
   }

   public void removeTileEntity(int var1, int var2, int var3) {
      ChunkPosition var4 = new ChunkPosition(var1, var2, var3);
      if(this.isChunkLoaded) {
         TileEntity var5 = (TileEntity)this.chunkTileEntityMap.remove(var4);
         if(var5 != null) {
            var5.invalidate();
         }
      }

   }

   public void onChunkLoad() {
      this.isChunkLoaded = true;
      this.worldObj.func_147448_a(this.chunkTileEntityMap.values());

      for(int var1 = 0; var1 < this.entityLists.length; ++var1) {
         Iterator var2 = this.entityLists[var1].iterator();

         while(var2.hasNext()) {
            Entity var3 = (Entity)var2.next();
            var3.onChunkLoad();
         }

         this.worldObj.addLoadedEntities(this.entityLists[var1]);
      }

   }

   public void onChunkUnload() {
      this.isChunkLoaded = false;
      Iterator var1 = this.chunkTileEntityMap.values().iterator();

      while(var1.hasNext()) {
         TileEntity var2 = (TileEntity)var1.next();
         this.worldObj.func_147457_a(var2);
      }

      for(int var3 = 0; var3 < this.entityLists.length; ++var3) {
         this.worldObj.unloadEntities(this.entityLists[var3]);
      }

   }

   public void setChunkModified() {
      this.isModified = true;
   }

   public void getEntitiesWithinAABBForEntity(Entity var1, AxisAlignedBB var2, List var3, IEntitySelector var4) {
      int var5 = MathHelper.floor_double((var2.minY - 2.0D) / 16.0D);
      int var6 = MathHelper.floor_double((var2.maxY + 2.0D) / 16.0D);
      var5 = MathHelper.clamp_int(var5, 0, this.entityLists.length - 1);
      var6 = MathHelper.clamp_int(var6, 0, this.entityLists.length - 1);

      for(int var7 = var5; var7 <= var6; ++var7) {
         List var8 = this.entityLists[var7];

         for(int var9 = 0; var9 < var8.size(); ++var9) {
            Entity var10 = (Entity)var8.get(var9);
            if(var10 != var1 && var10.boundingBox.intersectsWith(var2) && (var4 == null || var4.isEntityApplicable(var10))) {
               var3.add(var10);
               Entity[] var11 = var10.getParts();
               if(var11 != null) {
                  for(int var12 = 0; var12 < var11.length; ++var12) {
                     var10 = var11[var12];
                     if(var10 != var1 && var10.boundingBox.intersectsWith(var2) && (var4 == null || var4.isEntityApplicable(var10))) {
                        var3.add(var10);
                     }
                  }
               }
            }
         }
      }

   }

   public void getEntitiesOfTypeWithinAAAB(Class var1, AxisAlignedBB var2, List var3, IEntitySelector var4) {
      int var5 = MathHelper.floor_double((var2.minY - 2.0D) / 16.0D);
      int var6 = MathHelper.floor_double((var2.maxY + 2.0D) / 16.0D);
      var5 = MathHelper.clamp_int(var5, 0, this.entityLists.length - 1);
      var6 = MathHelper.clamp_int(var6, 0, this.entityLists.length - 1);

      for(int var7 = var5; var7 <= var6; ++var7) {
         List var8 = this.entityLists[var7];

         for(int var9 = 0; var9 < var8.size(); ++var9) {
            Entity var10 = (Entity)var8.get(var9);
            if(var1.isAssignableFrom(var10.getClass()) && var10.boundingBox.intersectsWith(var2) && (var4 == null || var4.isEntityApplicable(var10))) {
               var3.add(var10);
            }
         }
      }

   }

   public boolean needsSaving(boolean var1) {
      if(var1) {
         if(this.hasEntities && this.worldObj.getTotalWorldTime() != this.lastSaveTime || this.isModified) {
            return true;
         }
      } else if(this.hasEntities && this.worldObj.getTotalWorldTime() >= this.lastSaveTime + 600L) {
         return true;
      }

      return this.isModified;
   }

   public Random getRandomWithSeed(long var1) {
      return new Random(this.worldObj.getSeed() + (long)(this.xPosition * this.xPosition * 4987142) + (long)(this.xPosition * 5947611) + (long)(this.zPosition * this.zPosition) * 4392871L + (long)(this.zPosition * 389711) ^ var1);
   }

   public boolean isEmpty() {
      return false;
   }

   public void populateChunk(IChunkProvider var1, IChunkProvider var2, int var3, int var4) {
      if(!this.isTerrainPopulated && var1.chunkExists(var3 + 1, var4 + 1) && var1.chunkExists(var3, var4 + 1) && var1.chunkExists(var3 + 1, var4)) {
         var1.populate(var2, var3, var4);
      }

      if(var1.chunkExists(var3 - 1, var4) && !var1.provideChunk(var3 - 1, var4).isTerrainPopulated && var1.chunkExists(var3 - 1, var4 + 1) && var1.chunkExists(var3, var4 + 1) && var1.chunkExists(var3 - 1, var4 + 1)) {
         var1.populate(var2, var3 - 1, var4);
      }

      if(var1.chunkExists(var3, var4 - 1) && !var1.provideChunk(var3, var4 - 1).isTerrainPopulated && var1.chunkExists(var3 + 1, var4 - 1) && var1.chunkExists(var3 + 1, var4 - 1) && var1.chunkExists(var3 + 1, var4)) {
         var1.populate(var2, var3, var4 - 1);
      }

      if(var1.chunkExists(var3 - 1, var4 - 1) && !var1.provideChunk(var3 - 1, var4 - 1).isTerrainPopulated && var1.chunkExists(var3, var4 - 1) && var1.chunkExists(var3 - 1, var4)) {
         var1.populate(var2, var3 - 1, var4 - 1);
      }

   }

   public int getPrecipitationHeight(int var1, int var2) {
      int var3 = var1 | var2 << 4;
      int var4 = this.precipitationHeightMap[var3];
      if(var4 == -999) {
         int var5 = this.getTopFilledSegment() + 15;
         var4 = -1;

         while(var5 > 0 && var4 == -1) {
            Block var6 = this.getBlock(var1, var5, var2);
            Material var7 = var6.getMaterial();
            if(!var7.blocksMovement() && !var7.isLiquid()) {
               --var5;
            } else {
               var4 = var5 + 1;
            }
         }

         this.precipitationHeightMap[var3] = var4;
      }

      return var4;
   }

   public void func_150804_b(boolean var1) {
      if(this.isGapLightingUpdated && !this.worldObj.provider.hasNoSky && !var1) {
         this.recheckGaps(this.worldObj.isRemote);
      }

      this.field_150815_m = true;
      if(!this.isLightPopulated && this.isTerrainPopulated) {
         this.func_150809_p();
      }

   }

   public boolean func_150802_k() {
      return this.field_150815_m && this.isTerrainPopulated && this.isLightPopulated;
   }

   public ChunkCoordIntPair getChunkCoordIntPair() {
      return new ChunkCoordIntPair(this.xPosition, this.zPosition);
   }

   public boolean getAreLevelsEmpty(int var1, int var2) {
      if(var1 < 0) {
         var1 = 0;
      }

      if(var2 >= 256) {
         var2 = 255;
      }

      for(int var3 = var1; var3 <= var2; var3 += 16) {
         ExtendedBlockStorage var4 = this.storageArrays[var3 >> 4];
         if(var4 != null && !var4.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public void setStorageArrays(ExtendedBlockStorage[] var1) {
      this.storageArrays = var1;
   }

   public void fillChunk(byte[] var1, int var2, int var3, boolean var4) {
      int var5 = 0;
      boolean var6 = !this.worldObj.provider.hasNoSky;

      int var7;
      for(var7 = 0; var7 < this.storageArrays.length; ++var7) {
         if((var2 & 1 << var7) != 0) {
            if(this.storageArrays[var7] == null) {
               this.storageArrays[var7] = new ExtendedBlockStorage(var7 << 4, var6);
            }

            byte[] var8 = this.storageArrays[var7].getBlockLSBArray();
            System.arraycopy(var1, var5, var8, 0, var8.length);
            var5 += var8.length;
         } else if(var4 && this.storageArrays[var7] != null) {
            this.storageArrays[var7] = null;
         }
      }

      NibbleArray var9;
      for(var7 = 0; var7 < this.storageArrays.length; ++var7) {
         if((var2 & 1 << var7) != 0 && this.storageArrays[var7] != null) {
            var9 = this.storageArrays[var7].getMetadataArray();
            System.arraycopy(var1, var5, var9.data, 0, var9.data.length);
            var5 += var9.data.length;
         }
      }

      for(var7 = 0; var7 < this.storageArrays.length; ++var7) {
         if((var2 & 1 << var7) != 0 && this.storageArrays[var7] != null) {
            var9 = this.storageArrays[var7].getBlocklightArray();
            System.arraycopy(var1, var5, var9.data, 0, var9.data.length);
            var5 += var9.data.length;
         }
      }

      if(var6) {
         for(var7 = 0; var7 < this.storageArrays.length; ++var7) {
            if((var2 & 1 << var7) != 0 && this.storageArrays[var7] != null) {
               var9 = this.storageArrays[var7].getSkylightArray();
               System.arraycopy(var1, var5, var9.data, 0, var9.data.length);
               var5 += var9.data.length;
            }
         }
      }

      for(var7 = 0; var7 < this.storageArrays.length; ++var7) {
         if((var3 & 1 << var7) != 0) {
            if(this.storageArrays[var7] == null) {
               var5 += 2048;
            } else {
               var9 = this.storageArrays[var7].getBlockMSBArray();
               if(var9 == null) {
                  var9 = this.storageArrays[var7].createBlockMSBArray();
               }

               System.arraycopy(var1, var5, var9.data, 0, var9.data.length);
               var5 += var9.data.length;
            }
         } else if(var4 && this.storageArrays[var7] != null && this.storageArrays[var7].getBlockMSBArray() != null) {
            this.storageArrays[var7].clearMSBArray();
         }
      }

      if(var4) {
         System.arraycopy(var1, var5, this.blockBiomeArray, 0, this.blockBiomeArray.length);
         int var10000 = var5 + this.blockBiomeArray.length;
      }

      for(var7 = 0; var7 < this.storageArrays.length; ++var7) {
         if(this.storageArrays[var7] != null && (var2 & 1 << var7) != 0) {
            this.storageArrays[var7].removeInvalidBlocks();
         }
      }

      this.isLightPopulated = true;
      this.isTerrainPopulated = true;
      this.generateHeightMap();
      Iterator var11 = this.chunkTileEntityMap.values().iterator();

      while(var11.hasNext()) {
         TileEntity var10 = (TileEntity)var11.next();
         var10.updateContainingBlockInfo();
      }

   }

   public BiomeGenBase getBiomeGenForWorldCoords(int var1, int var2, WorldChunkManager var3) {
      int var4 = this.blockBiomeArray[var2 << 4 | var1] & 255;
      if(var4 == 255) {
         BiomeGenBase var5 = var3.getBiomeGenAt((this.xPosition << 4) + var1, (this.zPosition << 4) + var2);
         var4 = var5.biomeID;
         this.blockBiomeArray[var2 << 4 | var1] = (byte)(var4 & 255);
      }

      return BiomeGenBase.getBiome(var4) == null?BiomeGenBase.plains:BiomeGenBase.getBiome(var4);
   }

   public byte[] getBiomeArray() {
      return this.blockBiomeArray;
   }

   public void setBiomeArray(byte[] var1) {
      this.blockBiomeArray = var1;
   }

   public void resetRelightChecks() {
      this.queuedLightChecks = 0;
   }

   public void enqueueRelightChecks() {
      for(int var1 = 0; var1 < 8; ++var1) {
         if(this.queuedLightChecks >= 4096) {
            return;
         }

         int var2 = this.queuedLightChecks % 16;
         int var3 = this.queuedLightChecks / 16 % 16;
         int var4 = this.queuedLightChecks / 256;
         ++this.queuedLightChecks;
         int var5 = (this.xPosition << 4) + var3;
         int var6 = (this.zPosition << 4) + var4;

         for(int var7 = 0; var7 < 16; ++var7) {
            int var8 = (var2 << 4) + var7;
            if(this.storageArrays[var2] == null && (var7 == 0 || var7 == 15 || var3 == 0 || var3 == 15 || var4 == 0 || var4 == 15) || this.storageArrays[var2] != null && this.storageArrays[var2].getBlockByExtId(var3, var7, var4).getMaterial() == Material.air) {
               if(this.worldObj.getBlock(var5, var8 - 1, var6).getLightValue() > 0) {
                  this.worldObj.func_147451_t(var5, var8 - 1, var6);
               }

               if(this.worldObj.getBlock(var5, var8 + 1, var6).getLightValue() > 0) {
                  this.worldObj.func_147451_t(var5, var8 + 1, var6);
               }

               if(this.worldObj.getBlock(var5 - 1, var8, var6).getLightValue() > 0) {
                  this.worldObj.func_147451_t(var5 - 1, var8, var6);
               }

               if(this.worldObj.getBlock(var5 + 1, var8, var6).getLightValue() > 0) {
                  this.worldObj.func_147451_t(var5 + 1, var8, var6);
               }

               if(this.worldObj.getBlock(var5, var8, var6 - 1).getLightValue() > 0) {
                  this.worldObj.func_147451_t(var5, var8, var6 - 1);
               }

               if(this.worldObj.getBlock(var5, var8, var6 + 1).getLightValue() > 0) {
                  this.worldObj.func_147451_t(var5, var8, var6 + 1);
               }

               this.worldObj.func_147451_t(var5, var8, var6);
            }
         }
      }

   }

   public void func_150809_p() {
      this.isTerrainPopulated = true;
      this.isLightPopulated = true;
      if(!this.worldObj.provider.hasNoSky) {
         if(this.worldObj.checkChunksExist(this.xPosition * 16 - 1, 0, this.zPosition * 16 - 1, this.xPosition * 16 + 1, 63, this.zPosition * 16 + 1)) {
            for(int var1 = 0; var1 < 16; ++var1) {
               for(int var2 = 0; var2 < 16; ++var2) {
                  if(!this.func_150811_f(var1, var2)) {
                     this.isLightPopulated = false;
                     break;
                  }
               }
            }

            if(this.isLightPopulated) {
               Chunk var3 = this.worldObj.getChunkFromBlockCoords(this.xPosition * 16 - 1, this.zPosition * 16);
               var3.func_150801_a(3);
               var3 = this.worldObj.getChunkFromBlockCoords(this.xPosition * 16 + 16, this.zPosition * 16);
               var3.func_150801_a(1);
               var3 = this.worldObj.getChunkFromBlockCoords(this.xPosition * 16, this.zPosition * 16 - 1);
               var3.func_150801_a(0);
               var3 = this.worldObj.getChunkFromBlockCoords(this.xPosition * 16, this.zPosition * 16 + 16);
               var3.func_150801_a(2);
            }
         } else {
            this.isLightPopulated = false;
         }
      }

   }

   private void func_150801_a(int var1) {
      if(this.isTerrainPopulated) {
         int var2;
         if(var1 == 3) {
            for(var2 = 0; var2 < 16; ++var2) {
               this.func_150811_f(15, var2);
            }
         } else if(var1 == 1) {
            for(var2 = 0; var2 < 16; ++var2) {
               this.func_150811_f(0, var2);
            }
         } else if(var1 == 0) {
            for(var2 = 0; var2 < 16; ++var2) {
               this.func_150811_f(var2, 15);
            }
         } else if(var1 == 2) {
            for(var2 = 0; var2 < 16; ++var2) {
               this.func_150811_f(var2, 0);
            }
         }

      }
   }

   private boolean func_150811_f(int var1, int var2) {
      int var3 = this.getTopFilledSegment();
      boolean var4 = false;
      boolean var5 = false;

      int var6;
      for(var6 = var3 + 16 - 1; var6 > 63 || var6 > 0 && !var5; --var6) {
         int var7 = this.func_150808_b(var1, var6, var2);
         if(var7 == 255 && var6 < 63) {
            var5 = true;
         }

         if(!var4 && var7 > 0) {
            var4 = true;
         } else if(var4 && var7 == 0 && !this.worldObj.func_147451_t(this.xPosition * 16 + var1, var6, this.zPosition * 16 + var2)) {
            return false;
         }
      }

      for(; var6 > 0; --var6) {
         if(this.getBlock(var1, var6, var2).getLightValue() > 0) {
            this.worldObj.func_147451_t(this.xPosition * 16 + var1, var6, this.zPosition * 16 + var2);
         }
      }

      return true;
   }

}
