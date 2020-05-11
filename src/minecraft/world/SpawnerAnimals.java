package net.minecraft.world;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.chunk.Chunk;

public final class SpawnerAnimals {

   private HashMap eligibleChunksForSpawning = new HashMap();


   protected static ChunkPosition func_151350_a(World var0, int var1, int var2) {
      Chunk var3 = var0.getChunkFromChunkCoords(var1, var2);
      int var4 = var1 * 16 + var0.rand.nextInt(16);
      int var5 = var2 * 16 + var0.rand.nextInt(16);
      int var6 = var0.rand.nextInt(var3 == null?var0.getActualHeight():var3.getTopFilledSegment() + 16 - 1);
      return new ChunkPosition(var4, var6, var5);
   }

   public int findChunksForSpawning(WorldServer var1, boolean var2, boolean var3, boolean var4) {
      if(!var2 && !var3) {
         return 0;
      } else {
         this.eligibleChunksForSpawning.clear();

         int var5;
         int var8;
         for(var5 = 0; var5 < var1.playerEntities.size(); ++var5) {
            EntityPlayer var6 = (EntityPlayer)var1.playerEntities.get(var5);
            int var7 = MathHelper.floor_double(var6.posX / 16.0D);
            var8 = MathHelper.floor_double(var6.posZ / 16.0D);
            byte var9 = 8;

            for(int var10 = -var9; var10 <= var9; ++var10) {
               for(int var11 = -var9; var11 <= var9; ++var11) {
                  boolean var12 = var10 == -var9 || var10 == var9 || var11 == -var9 || var11 == var9;
                  ChunkCoordIntPair var13 = new ChunkCoordIntPair(var10 + var7, var11 + var8);
                  if(!var12) {
                     this.eligibleChunksForSpawning.put(var13, Boolean.valueOf(false));
                  } else if(!this.eligibleChunksForSpawning.containsKey(var13)) {
                     this.eligibleChunksForSpawning.put(var13, Boolean.valueOf(true));
                  }
               }
            }
         }

         var5 = 0;
         ChunkCoordinates var34 = var1.getSpawnPoint();
         EnumCreatureType[] var35 = EnumCreatureType.values();
         var8 = var35.length;

         for(int var36 = 0; var36 < var8; ++var36) {
            EnumCreatureType var37 = var35[var36];
            if((!var37.getPeacefulCreature() || var3) && (var37.getPeacefulCreature() || var2) && (!var37.getAnimal() || var4) && var1.countEntities(var37.getCreatureClass()) <= var37.getMaxNumberOfCreature() * this.eligibleChunksForSpawning.size() / 256) {
               Iterator var38 = this.eligibleChunksForSpawning.keySet().iterator();

               label110:
               while(var38.hasNext()) {
                  ChunkCoordIntPair var39 = (ChunkCoordIntPair)var38.next();
                  if(!((Boolean)this.eligibleChunksForSpawning.get(var39)).booleanValue()) {
                     ChunkPosition var40 = func_151350_a(var1, var39.chunkXPos, var39.chunkZPos);
                     int var14 = var40.chunkPosX;
                     int var15 = var40.chunkPosY;
                     int var16 = var40.chunkPosZ;
                     if(!var1.getBlock(var14, var15, var16).isNormalCube() && var1.getBlock(var14, var15, var16).getMaterial() == var37.getCreatureMaterial()) {
                        int var17 = 0;
                        int var18 = 0;

                        while(var18 < 3) {
                           int var19 = var14;
                           int var20 = var15;
                           int var21 = var16;
                           byte var22 = 6;
                           BiomeGenBase$SpawnListEntry var23 = null;
                           IEntityLivingData var24 = null;
                           int var25 = 0;

                           while(true) {
                              if(var25 < 4) {
                                 label103: {
                                    var19 += var1.rand.nextInt(var22) - var1.rand.nextInt(var22);
                                    var20 += var1.rand.nextInt(1) - var1.rand.nextInt(1);
                                    var21 += var1.rand.nextInt(var22) - var1.rand.nextInt(var22);
                                    if(canCreatureTypeSpawnAtLocation(var37, var1, var19, var20, var21)) {
                                       float var26 = (float)var19 + 0.5F;
                                       float var27 = (float)var20;
                                       float var28 = (float)var21 + 0.5F;
                                       if(var1.getClosestPlayer((double)var26, (double)var27, (double)var28, 24.0D) == null) {
                                          float var29 = var26 - (float)var34.posX;
                                          float var30 = var27 - (float)var34.posY;
                                          float var31 = var28 - (float)var34.posZ;
                                          float var32 = var29 * var29 + var30 * var30 + var31 * var31;
                                          if(var32 >= 576.0F) {
                                             if(var23 == null) {
                                                var23 = var1.spawnRandomCreature(var37, var19, var20, var21);
                                                if(var23 == null) {
                                                   break label103;
                                                }
                                             }

                                             EntityLiving var41;
                                             try {
                                                var41 = (EntityLiving)var23.entityClass.getConstructor(new Class[]{World.class}).newInstance(new Object[]{var1});
                                             } catch (Exception var33) {
                                                var33.printStackTrace();
                                                return var5;
                                             }

                                             var41.setLocationAndAngles((double)var26, (double)var27, (double)var28, var1.rand.nextFloat() * 360.0F, 0.0F);
                                             if(var41.getCanSpawnHere()) {
                                                ++var17;
                                                var1.spawnEntityInWorld(var41);
                                                var24 = var41.onSpawnWithEgg(var24);
                                                if(var17 >= var41.getMaxSpawnedInChunk()) {
                                                   continue label110;
                                                }
                                             }

                                             var5 += var17;
                                          }
                                       }
                                    }

                                    ++var25;
                                    continue;
                                 }
                              }

                              ++var18;
                              break;
                           }
                        }
                     }
                  }
               }
            }
         }

         return var5;
      }
   }

   public static boolean canCreatureTypeSpawnAtLocation(EnumCreatureType var0, World var1, int var2, int var3, int var4) {
      if(var0.getCreatureMaterial() == Material.water) {
         return var1.getBlock(var2, var3, var4).getMaterial().isLiquid() && var1.getBlock(var2, var3 - 1, var4).getMaterial().isLiquid() && !var1.getBlock(var2, var3 + 1, var4).isNormalCube();
      } else if(!World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4)) {
         return false;
      } else {
         Block var5 = var1.getBlock(var2, var3 - 1, var4);
         return var5 != Blocks.bedrock && !var1.getBlock(var2, var3, var4).isNormalCube() && !var1.getBlock(var2, var3, var4).getMaterial().isLiquid() && !var1.getBlock(var2, var3 + 1, var4).isNormalCube();
      }
   }

   public static void performWorldGenSpawning(World var0, BiomeGenBase var1, int var2, int var3, int var4, int var5, Random var6) {
      List var7 = var1.getSpawnableList(EnumCreatureType.creature);
      if(!var7.isEmpty()) {
         while(var6.nextFloat() < var1.getSpawningChance()) {
            BiomeGenBase$SpawnListEntry var8 = (BiomeGenBase$SpawnListEntry)WeightedRandom.getRandomItem(var0.rand, (Collection)var7);
            IEntityLivingData var9 = null;
            int var10 = var8.minGroupCount + var6.nextInt(1 + var8.maxGroupCount - var8.minGroupCount);
            int var11 = var2 + var6.nextInt(var4);
            int var12 = var3 + var6.nextInt(var5);
            int var13 = var11;
            int var14 = var12;

            for(int var15 = 0; var15 < var10; ++var15) {
               boolean var16 = false;

               for(int var17 = 0; !var16 && var17 < 4; ++var17) {
                  int var18 = var0.getTopSolidOrLiquidBlock(var11, var12);
                  if(canCreatureTypeSpawnAtLocation(EnumCreatureType.creature, var0, var11, var18, var12)) {
                     float var19 = (float)var11 + 0.5F;
                     float var20 = (float)var18;
                     float var21 = (float)var12 + 0.5F;

                     EntityLiving var22;
                     try {
                        var22 = (EntityLiving)var8.entityClass.getConstructor(new Class[]{World.class}).newInstance(new Object[]{var0});
                     } catch (Exception var24) {
                        var24.printStackTrace();
                        continue;
                     }

                     var22.setLocationAndAngles((double)var19, (double)var20, (double)var21, var6.nextFloat() * 360.0F, 0.0F);
                     var0.spawnEntityInWorld(var22);
                     var9 = var22.onSpawnWithEgg(var9);
                     var16 = true;
                  }

                  var11 += var6.nextInt(5) - var6.nextInt(5);

                  for(var12 += var6.nextInt(5) - var6.nextInt(5); var11 < var2 || var11 >= var2 + var4 || var12 < var3 || var12 >= var3 + var4; var12 = var14 + var6.nextInt(5) - var6.nextInt(5)) {
                     var11 = var13 + var6.nextInt(5) - var6.nextInt(5);
                  }
               }
            }
         }

      }
   }
}
