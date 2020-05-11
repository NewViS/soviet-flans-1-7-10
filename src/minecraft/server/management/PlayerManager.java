package net.minecraft.server.management;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerManager$PlayerInstance;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerManager {

   private static final Logger field_152627_a = LogManager.getLogger();
   private final WorldServer theWorldServer;
   private final List players = new ArrayList();
   private final LongHashMap playerInstances = new LongHashMap();
   private final List chunkWatcherWithPlayers = new ArrayList();
   private final List playerInstanceList = new ArrayList();
   private int playerViewRadius;
   private long previousTotalWorldTime;
   private final int[][] xzDirectionsConst = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};


   public PlayerManager(WorldServer var1) {
      this.theWorldServer = var1;
      this.func_152622_a(var1.func_73046_m().getConfigurationManager().getViewDistance());
   }

   public WorldServer getWorldServer() {
      return this.theWorldServer;
   }

   public void updatePlayerInstances() {
      long var1 = this.theWorldServer.getTotalWorldTime();
      int var3;
      PlayerManager$PlayerInstance var4;
      if(var1 - this.previousTotalWorldTime > 8000L) {
         this.previousTotalWorldTime = var1;

         for(var3 = 0; var3 < this.playerInstanceList.size(); ++var3) {
            var4 = (PlayerManager$PlayerInstance)this.playerInstanceList.get(var3);
            var4.sendChunkUpdate();
            var4.processChunk();
         }
      } else {
         for(var3 = 0; var3 < this.chunkWatcherWithPlayers.size(); ++var3) {
            var4 = (PlayerManager$PlayerInstance)this.chunkWatcherWithPlayers.get(var3);
            var4.sendChunkUpdate();
         }
      }

      this.chunkWatcherWithPlayers.clear();
      if(this.players.isEmpty()) {
         WorldProvider var5 = this.theWorldServer.provider;
         if(!var5.canRespawnHere()) {
            this.theWorldServer.theChunkProviderServer.unloadAllChunks();
         }
      }

   }

   public boolean func_152621_a(int var1, int var2) {
      long var3 = (long)var1 + 2147483647L | (long)var2 + 2147483647L << 32;
      return this.playerInstances.getValueByKey(var3) != null;
   }

   private PlayerManager$PlayerInstance getOrCreateChunkWatcher(int var1, int var2, boolean var3) {
      long var4 = (long)var1 + 2147483647L | (long)var2 + 2147483647L << 32;
      PlayerManager$PlayerInstance var6 = (PlayerManager$PlayerInstance)this.playerInstances.getValueByKey(var4);
      if(var6 == null && var3) {
         var6 = new PlayerManager$PlayerInstance(this, var1, var2);
         this.playerInstances.add(var4, var6);
         this.playerInstanceList.add(var6);
      }

      return var6;
   }

   public void markBlockForUpdate(int var1, int var2, int var3) {
      int var4 = var1 >> 4;
      int var5 = var3 >> 4;
      PlayerManager$PlayerInstance var6 = this.getOrCreateChunkWatcher(var4, var5, false);
      if(var6 != null) {
         var6.flagChunkForUpdate(var1 & 15, var2, var3 & 15);
      }

   }

   public void addPlayer(EntityPlayerMP var1) {
      int var2 = (int)var1.posX >> 4;
      int var3 = (int)var1.posZ >> 4;
      var1.managedPosX = var1.posX;
      var1.managedPosZ = var1.posZ;

      for(int var4 = var2 - this.playerViewRadius; var4 <= var2 + this.playerViewRadius; ++var4) {
         for(int var5 = var3 - this.playerViewRadius; var5 <= var3 + this.playerViewRadius; ++var5) {
            this.getOrCreateChunkWatcher(var4, var5, true).addPlayer(var1);
         }
      }

      this.players.add(var1);
      this.filterChunkLoadQueue(var1);
   }

   public void filterChunkLoadQueue(EntityPlayerMP var1) {
      ArrayList var2 = new ArrayList(var1.loadedChunks);
      int var3 = 0;
      int var4 = this.playerViewRadius;
      int var5 = (int)var1.posX >> 4;
      int var6 = (int)var1.posZ >> 4;
      int var7 = 0;
      int var8 = 0;
      ChunkCoordIntPair var9 = PlayerManager$PlayerInstance.access$500(this.getOrCreateChunkWatcher(var5, var6, true));
      var1.loadedChunks.clear();
      if(var2.contains(var9)) {
         var1.loadedChunks.add(var9);
      }

      int var10;
      for(var10 = 1; var10 <= var4 * 2; ++var10) {
         for(int var11 = 0; var11 < 2; ++var11) {
            int[] var12 = this.xzDirectionsConst[var3++ % 4];

            for(int var13 = 0; var13 < var10; ++var13) {
               var7 += var12[0];
               var8 += var12[1];
               var9 = PlayerManager$PlayerInstance.access$500(this.getOrCreateChunkWatcher(var5 + var7, var6 + var8, true));
               if(var2.contains(var9)) {
                  var1.loadedChunks.add(var9);
               }
            }
         }
      }

      var3 %= 4;

      for(var10 = 0; var10 < var4 * 2; ++var10) {
         var7 += this.xzDirectionsConst[var3][0];
         var8 += this.xzDirectionsConst[var3][1];
         var9 = PlayerManager$PlayerInstance.access$500(this.getOrCreateChunkWatcher(var5 + var7, var6 + var8, true));
         if(var2.contains(var9)) {
            var1.loadedChunks.add(var9);
         }
      }

   }

   public void removePlayer(EntityPlayerMP var1) {
      int var2 = (int)var1.managedPosX >> 4;
      int var3 = (int)var1.managedPosZ >> 4;

      for(int var4 = var2 - this.playerViewRadius; var4 <= var2 + this.playerViewRadius; ++var4) {
         for(int var5 = var3 - this.playerViewRadius; var5 <= var3 + this.playerViewRadius; ++var5) {
            PlayerManager$PlayerInstance var6 = this.getOrCreateChunkWatcher(var4, var5, false);
            if(var6 != null) {
               var6.removePlayer(var1);
            }
         }
      }

      this.players.remove(var1);
   }

   private boolean overlaps(int var1, int var2, int var3, int var4, int var5) {
      int var6 = var1 - var3;
      int var7 = var2 - var4;
      return var6 >= -var5 && var6 <= var5?var7 >= -var5 && var7 <= var5:false;
   }

   public void updatePlayerPertinentChunks(EntityPlayerMP var1) {
      int var2 = (int)var1.posX >> 4;
      int var3 = (int)var1.posZ >> 4;
      double var4 = var1.managedPosX - var1.posX;
      double var6 = var1.managedPosZ - var1.posZ;
      double var8 = var4 * var4 + var6 * var6;
      if(var8 >= 64.0D) {
         int var10 = (int)var1.managedPosX >> 4;
         int var11 = (int)var1.managedPosZ >> 4;
         int var12 = this.playerViewRadius;
         int var13 = var2 - var10;
         int var14 = var3 - var11;
         if(var13 != 0 || var14 != 0) {
            for(int var15 = var2 - var12; var15 <= var2 + var12; ++var15) {
               for(int var16 = var3 - var12; var16 <= var3 + var12; ++var16) {
                  if(!this.overlaps(var15, var16, var10, var11, var12)) {
                     this.getOrCreateChunkWatcher(var15, var16, true).addPlayer(var1);
                  }

                  if(!this.overlaps(var15 - var13, var16 - var14, var2, var3, var12)) {
                     PlayerManager$PlayerInstance var17 = this.getOrCreateChunkWatcher(var15 - var13, var16 - var14, false);
                     if(var17 != null) {
                        var17.removePlayer(var1);
                     }
                  }
               }
            }

            this.filterChunkLoadQueue(var1);
            var1.managedPosX = var1.posX;
            var1.managedPosZ = var1.posZ;
         }
      }
   }

   public boolean isPlayerWatchingChunk(EntityPlayerMP var1, int var2, int var3) {
      PlayerManager$PlayerInstance var4 = this.getOrCreateChunkWatcher(var2, var3, false);
      return var4 != null && PlayerManager$PlayerInstance.access$600(var4).contains(var1) && !var1.loadedChunks.contains(PlayerManager$PlayerInstance.access$500(var4));
   }

   public void func_152622_a(int var1) {
      var1 = MathHelper.clamp_int(var1, 3, 20);
      if(var1 != this.playerViewRadius) {
         int var2 = var1 - this.playerViewRadius;
         Iterator var3 = this.players.iterator();

         while(var3.hasNext()) {
            EntityPlayerMP var4 = (EntityPlayerMP)var3.next();
            int var5 = (int)var4.posX >> 4;
            int var6 = (int)var4.posZ >> 4;
            int var7;
            int var8;
            if(var2 > 0) {
               for(var7 = var5 - var1; var7 <= var5 + var1; ++var7) {
                  for(var8 = var6 - var1; var8 <= var6 + var1; ++var8) {
                     PlayerManager$PlayerInstance var9 = this.getOrCreateChunkWatcher(var7, var8, true);
                     if(!PlayerManager$PlayerInstance.access$600(var9).contains(var4)) {
                        var9.addPlayer(var4);
                     }
                  }
               }
            } else {
               for(var7 = var5 - this.playerViewRadius; var7 <= var5 + this.playerViewRadius; ++var7) {
                  for(var8 = var6 - this.playerViewRadius; var8 <= var6 + this.playerViewRadius; ++var8) {
                     if(!this.overlaps(var7, var8, var5, var6, var1)) {
                        this.getOrCreateChunkWatcher(var7, var8, true).removePlayer(var4);
                     }
                  }
               }
            }
         }

         this.playerViewRadius = var1;
      }
   }

   public static int getFurthestViewableBlock(int var0) {
      return var0 * 16 - 16;
   }

   // $FF: synthetic method
   static Logger access$000() {
      return field_152627_a;
   }

   // $FF: synthetic method
   static WorldServer access$100(PlayerManager var0) {
      return var0.theWorldServer;
   }

   // $FF: synthetic method
   static LongHashMap access$200(PlayerManager var0) {
      return var0.playerInstances;
   }

   // $FF: synthetic method
   static List access$300(PlayerManager var0) {
      return var0.playerInstanceList;
   }

   // $FF: synthetic method
   static List access$400(PlayerManager var0) {
      return var0.chunkWatcherWithPlayers;
   }

}
