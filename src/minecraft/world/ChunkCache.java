package net.minecraft.world;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class ChunkCache implements IBlockAccess {

   private int chunkX;
   private int chunkZ;
   private Chunk[][] chunkArray;
   private boolean isEmpty;
   private World worldObj;


   public ChunkCache(World var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      this.worldObj = var1;
      this.chunkX = var2 - var8 >> 4;
      this.chunkZ = var4 - var8 >> 4;
      int var9 = var5 + var8 >> 4;
      int var10 = var7 + var8 >> 4;
      this.chunkArray = new apx[var9 - this.chunkX + 1][var10 - this.chunkZ + 1];
      this.isEmpty = true;

      int var11;
      int var12;
      Chunk var13;
      for(var11 = this.chunkX; var11 <= var9; ++var11) {
         for(var12 = this.chunkZ; var12 <= var10; ++var12) {
            var13 = var1.getChunkFromChunkCoords(var11, var12);
            if(var13 != null) {
               this.chunkArray[var11 - this.chunkX][var12 - this.chunkZ] = var13;
            }
         }
      }

      for(var11 = var2 >> 4; var11 <= var5 >> 4; ++var11) {
         for(var12 = var4 >> 4; var12 <= var7 >> 4; ++var12) {
            var13 = this.chunkArray[var11 - this.chunkX][var12 - this.chunkZ];
            if(var13 != null && !var13.getAreLevelsEmpty(var3, var6)) {
               this.isEmpty = false;
            }
         }
      }

   }

   public boolean extendedLevelsInChunkCache() {
      return this.isEmpty;
   }

   public Block getBlock(int var1, int var2, int var3) {
      Block var4 = Blocks.air;
      if(var2 >= 0 && var2 < 256) {
         int var5 = (var1 >> 4) - this.chunkX;
         int var6 = (var3 >> 4) - this.chunkZ;
         if(var5 >= 0 && var5 < this.chunkArray.length && var6 >= 0 && var6 < this.chunkArray[var5].length) {
            Chunk var7 = this.chunkArray[var5][var6];
            if(var7 != null) {
               var4 = var7.getBlock(var1 & 15, var2, var3 & 15);
            }
         }
      }

      return var4;
   }

   public TileEntity getTileEntity(int var1, int var2, int var3) {
      int var4 = (var1 >> 4) - this.chunkX;
      int var5 = (var3 >> 4) - this.chunkZ;
      return this.chunkArray[var4][var5].func_150806_e(var1 & 15, var2, var3 & 15);
   }

   public int getLightBrightnessForSkyBlocks(int var1, int var2, int var3, int var4) {
      int var5 = this.getSkyBlockTypeBrightness(EnumSkyBlock.Sky, var1, var2, var3);
      int var6 = this.getSkyBlockTypeBrightness(EnumSkyBlock.Block, var1, var2, var3);
      if(var6 < var4) {
         var6 = var4;
      }

      return var5 << 20 | var6 << 4;
   }

   public int getBlockMetadata(int var1, int var2, int var3) {
      if(var2 < 0) {
         return 0;
      } else if(var2 >= 256) {
         return 0;
      } else {
         int var4 = (var1 >> 4) - this.chunkX;
         int var5 = (var3 >> 4) - this.chunkZ;
         return this.chunkArray[var4][var5].getBlockMetadata(var1 & 15, var2, var3 & 15);
      }
   }

   public BiomeGenBase getBiomeGenForCoords(int var1, int var2) {
      return this.worldObj.getBiomeGenForCoords(var1, var2);
   }

   public boolean isAirBlock(int var1, int var2, int var3) {
      return this.getBlock(var1, var2, var3).getMaterial() == Material.air;
   }

   public int getSkyBlockTypeBrightness(EnumSkyBlock var1, int var2, int var3, int var4) {
      if(var3 < 0) {
         var3 = 0;
      }

      if(var3 >= 256) {
         var3 = 255;
      }

      if(var3 >= 0 && var3 < 256 && var2 >= -30000000 && var4 >= -30000000 && var2 < 30000000 && var4 <= 30000000) {
         if(var1 == EnumSkyBlock.Sky && this.worldObj.provider.hasNoSky) {
            return 0;
         } else {
            int var5;
            int var6;
            if(this.getBlock(var2, var3, var4).getUseNeighborBrightness()) {
               var5 = this.getSpecialBlockBrightness(var1, var2, var3 + 1, var4);
               var6 = this.getSpecialBlockBrightness(var1, var2 + 1, var3, var4);
               int var7 = this.getSpecialBlockBrightness(var1, var2 - 1, var3, var4);
               int var8 = this.getSpecialBlockBrightness(var1, var2, var3, var4 + 1);
               int var9 = this.getSpecialBlockBrightness(var1, var2, var3, var4 - 1);
               if(var6 > var5) {
                  var5 = var6;
               }

               if(var7 > var5) {
                  var5 = var7;
               }

               if(var8 > var5) {
                  var5 = var8;
               }

               if(var9 > var5) {
                  var5 = var9;
               }

               return var5;
            } else {
               var5 = (var2 >> 4) - this.chunkX;
               var6 = (var4 >> 4) - this.chunkZ;
               return this.chunkArray[var5][var6].getSavedLightValue(var1, var2 & 15, var3, var4 & 15);
            }
         }
      } else {
         return var1.defaultLightValue;
      }
   }

   public int getSpecialBlockBrightness(EnumSkyBlock var1, int var2, int var3, int var4) {
      if(var3 < 0) {
         var3 = 0;
      }

      if(var3 >= 256) {
         var3 = 255;
      }

      if(var3 >= 0 && var3 < 256 && var2 >= -30000000 && var4 >= -30000000 && var2 < 30000000 && var4 <= 30000000) {
         int var5 = (var2 >> 4) - this.chunkX;
         int var6 = (var4 >> 4) - this.chunkZ;
         return this.chunkArray[var5][var6].getSavedLightValue(var1, var2 & 15, var3, var4 & 15);
      } else {
         return var1.defaultLightValue;
      }
   }

   public int getHeight() {
      return 256;
   }

   public int isBlockProvidingPowerTo(int var1, int var2, int var3, int var4) {
      return this.getBlock(var1, var2, var3).isProvidingStrongPower(this, var1, var2, var3, var4);
   }
}
