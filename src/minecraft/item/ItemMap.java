package net.minecraft.item;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multisets;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapData$MapInfo;

public class ItemMap extends ItemMapBase {

   protected ItemMap() {
      this.setHasSubtypes(true);
   }

   public static MapData func_150912_a(int var0, World var1) {
      String var2 = "map_" + var0;
      MapData var3 = (MapData)var1.loadItemData(MapData.class, var2);
      if(var3 == null) {
         var3 = new MapData(var2);
         var1.setItemData(var2, var3);
      }

      return var3;
   }

   public MapData getMapData(ItemStack var1, World var2) {
      String var3 = "map_" + var1.getItemDamage();
      MapData var4 = (MapData)var2.loadItemData(MapData.class, var3);
      if(var4 == null && !var2.isRemote) {
         var1.setItemDamage(var2.getUniqueDataId("map"));
         var3 = "map_" + var1.getItemDamage();
         var4 = new MapData(var3);
         var4.scale = 3;
         int var5 = 128 * (1 << var4.scale);
         var4.xCenter = Math.round((float)var2.getWorldInfo().getSpawnX() / (float)var5) * var5;
         var4.zCenter = Math.round((float)(var2.getWorldInfo().getSpawnZ() / var5)) * var5;
         var4.dimension = (byte)var2.provider.dimensionId;
         var4.markDirty();
         var2.setItemData(var3, var4);
      }

      return var4;
   }

   public void updateMapData(World var1, Entity var2, MapData var3) {
      if(var1.provider.dimensionId == var3.dimension && var2 instanceof EntityPlayer) {
         int var4 = 1 << var3.scale;
         int var5 = var3.xCenter;
         int var6 = var3.zCenter;
         int var7 = MathHelper.floor_double(var2.posX - (double)var5) / var4 + 64;
         int var8 = MathHelper.floor_double(var2.posZ - (double)var6) / var4 + 64;
         int var9 = 128 / var4;
         if(var1.provider.hasNoSky) {
            var9 /= 2;
         }

         MapData$MapInfo var10 = var3.func_82568_a((EntityPlayer)var2);
         ++var10.field_82569_d;

         for(int var11 = var7 - var9 + 1; var11 < var7 + var9; ++var11) {
            if((var11 & 15) == (var10.field_82569_d & 15)) {
               int var12 = 255;
               int var13 = 0;
               double var14 = 0.0D;

               for(int var16 = var8 - var9 - 1; var16 < var8 + var9; ++var16) {
                  if(var11 >= 0 && var16 >= -1 && var11 < 128 && var16 < 128) {
                     int var17 = var11 - var7;
                     int var18 = var16 - var8;
                     boolean var19 = var17 * var17 + var18 * var18 > (var9 - 2) * (var9 - 2);
                     int var20 = (var5 / var4 + var11 - 64) * var4;
                     int var21 = (var6 / var4 + var16 - 64) * var4;
                     HashMultiset var22 = HashMultiset.create();
                     Chunk var23 = var1.getChunkFromBlockCoords(var20, var21);
                     if(!var23.isEmpty()) {
                        int var24 = var20 & 15;
                        int var25 = var21 & 15;
                        int var26 = 0;
                        double var27 = 0.0D;
                        int var29;
                        if(var1.provider.hasNoSky) {
                           var29 = var20 + var21 * 231871;
                           var29 = var29 * var29 * 31287121 + var29 * 11;
                           if((var29 >> 20 & 1) == 0) {
                              var22.add(Blocks.dirt.getMapColor(0), 10);
                           } else {
                              var22.add(Blocks.stone.getMapColor(0), 100);
                           }

                           var27 = 100.0D;
                        } else {
                           for(var29 = 0; var29 < var4; ++var29) {
                              for(int var30 = 0; var30 < var4; ++var30) {
                                 int var31 = var23.getHeightValue(var29 + var24, var30 + var25) + 1;
                                 Block var32 = Blocks.air;
                                 int var33 = 0;
                                 if(var31 > 1) {
                                    do {
                                       --var31;
                                       var32 = var23.getBlock(var29 + var24, var31, var30 + var25);
                                       var33 = var23.getBlockMetadata(var29 + var24, var31, var30 + var25);
                                    } while(var32.getMapColor(var33) == MapColor.airColor && var31 > 0);

                                    if(var31 > 0 && var32.getMaterial().isLiquid()) {
                                       int var34 = var31 - 1;

                                       Block var35;
                                       do {
                                          var35 = var23.getBlock(var29 + var24, var34--, var30 + var25);
                                          ++var26;
                                       } while(var34 > 0 && var35.getMaterial().isLiquid());
                                    }
                                 }

                                 var27 += (double)var31 / (double)(var4 * var4);
                                 var22.add(var32.getMapColor(var33));
                              }
                           }
                        }

                        var26 /= var4 * var4;
                        double var36 = (var27 - var14) * 4.0D / (double)(var4 + 4) + ((double)(var11 + var16 & 1) - 0.5D) * 0.4D;
                        byte var37 = 1;
                        if(var36 > 0.6D) {
                           var37 = 2;
                        }

                        if(var36 < -0.6D) {
                           var37 = 0;
                        }

                        MapColor var38 = (MapColor)Iterables.getFirst(Multisets.copyHighestCountFirst(var22), MapColor.airColor);
                        if(var38 == MapColor.waterColor) {
                           var36 = (double)var26 * 0.1D + (double)(var11 + var16 & 1) * 0.2D;
                           var37 = 1;
                           if(var36 < 0.5D) {
                              var37 = 2;
                           }

                           if(var36 > 0.9D) {
                              var37 = 0;
                           }
                        }

                        var14 = var27;
                        if(var16 >= 0 && var17 * var17 + var18 * var18 < var9 * var9 && (!var19 || (var11 + var16 & 1) != 0)) {
                           byte var39 = var3.colors[var11 + var16 * 128];
                           byte var40 = (byte)(var38.colorIndex * 4 + var37);
                           if(var39 != var40) {
                              if(var12 > var16) {
                                 var12 = var16;
                              }

                              if(var13 < var16) {
                                 var13 = var16;
                              }

                              var3.colors[var11 + var16 * 128] = var40;
                           }
                        }
                     }
                  }
               }

               if(var12 <= var13) {
                  var3.setColumnDirty(var11, var12, var13);
               }
            }
         }

      }
   }

   public void onUpdate(ItemStack var1, World var2, Entity var3, int var4, boolean var5) {
      if(!var2.isRemote) {
         MapData var6 = this.getMapData(var1, var2);
         if(var3 instanceof EntityPlayer) {
            EntityPlayer var7 = (EntityPlayer)var3;
            var6.updateVisiblePlayers(var7, var1);
         }

         if(var5) {
            this.updateMapData(var2, var3, var6);
         }

      }
   }

   public Packet func_150911_c(ItemStack var1, World var2, EntityPlayer var3) {
      byte[] var4 = this.getMapData(var1, var2).getUpdatePacketData(var1, var2, var3);
      return var4 == null?null:new S34PacketMaps(var1.getItemDamage(), var4);
   }

   public void onCreated(ItemStack var1, World var2, EntityPlayer var3) {
      if(var1.hasTagCompound() && var1.getTagCompound().getBoolean("map_is_scaling")) {
         MapData var4 = Items.filled_map.getMapData(var1, var2);
         var1.setItemDamage(var2.getUniqueDataId("map"));
         MapData var5 = new MapData("map_" + var1.getItemDamage());
         var5.scale = (byte)(var4.scale + 1);
         if(var5.scale > 4) {
            var5.scale = 4;
         }

         var5.xCenter = var4.xCenter;
         var5.zCenter = var4.zCenter;
         var5.dimension = var4.dimension;
         var5.markDirty();
         var2.setItemData("map_" + var1.getItemDamage(), var5);
      }

   }

   public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4) {
      MapData var5 = this.getMapData(var1, var2.worldObj);
      if(var4) {
         if(var5 == null) {
            var3.add("Unknown map");
         } else {
            var3.add("Scaling at 1:" + (1 << var5.scale));
            var3.add("(Level " + var5.scale + "/" + 4 + ")");
         }
      }

   }
}
