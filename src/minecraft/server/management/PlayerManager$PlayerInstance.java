package net.minecraft.server.management;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;

class PlayerManager$PlayerInstance {

   private final List playersWatchingChunk;
   private final ChunkCoordIntPair chunkLocation;
   private short[] locationOfBlockChange;
   private int numberOfTilesToUpdate;
   private int flagsYAreasToUpdate;
   private long previousWorldTime;
   // $FF: synthetic field
   final PlayerManager thePlayerManager;


   public PlayerManager$PlayerInstance(PlayerManager var1, int var2, int var3) {
      this.thePlayerManager = var1;
      this.playersWatchingChunk = new ArrayList();
      this.locationOfBlockChange = new short[64];
      this.chunkLocation = new ChunkCoordIntPair(var2, var3);
      var1.getWorldServer().theChunkProviderServer.loadChunk(var2, var3);
   }

   public void addPlayer(EntityPlayerMP var1) {
      if(this.playersWatchingChunk.contains(var1)) {
         PlayerManager.access$000().debug("Failed to add player. {} already is in chunk {}, {}", new Object[]{var1, Integer.valueOf(this.chunkLocation.chunkXPos), Integer.valueOf(this.chunkLocation.chunkZPos)});
      } else {
         if(this.playersWatchingChunk.isEmpty()) {
            this.previousWorldTime = PlayerManager.access$100(this.thePlayerManager).getTotalWorldTime();
         }

         this.playersWatchingChunk.add(var1);
         var1.loadedChunks.add(this.chunkLocation);
      }
   }

   public void removePlayer(EntityPlayerMP var1) {
      if(this.playersWatchingChunk.contains(var1)) {
         Chunk var2 = PlayerManager.access$100(this.thePlayerManager).getChunkFromChunkCoords(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos);
         if(var2.func_150802_k()) {
            var1.playerNetServerHandler.sendPacket(new S21PacketChunkData(var2, true, 0));
         }

         this.playersWatchingChunk.remove(var1);
         var1.loadedChunks.remove(this.chunkLocation);
         if(this.playersWatchingChunk.isEmpty()) {
            long var3 = (long)this.chunkLocation.chunkXPos + 2147483647L | (long)this.chunkLocation.chunkZPos + 2147483647L << 32;
            this.increaseInhabitedTime(var2);
            PlayerManager.access$200(this.thePlayerManager).remove(var3);
            PlayerManager.access$300(this.thePlayerManager).remove(this);
            if(this.numberOfTilesToUpdate > 0) {
               PlayerManager.access$400(this.thePlayerManager).remove(this);
            }

            this.thePlayerManager.getWorldServer().theChunkProviderServer.unloadChunksIfNotNearSpawn(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos);
         }

      }
   }

   public void processChunk() {
      this.increaseInhabitedTime(PlayerManager.access$100(this.thePlayerManager).getChunkFromChunkCoords(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos));
   }

   private void increaseInhabitedTime(Chunk var1) {
      var1.inhabitedTime += PlayerManager.access$100(this.thePlayerManager).getTotalWorldTime() - this.previousWorldTime;
      this.previousWorldTime = PlayerManager.access$100(this.thePlayerManager).getTotalWorldTime();
   }

   public void flagChunkForUpdate(int var1, int var2, int var3) {
      if(this.numberOfTilesToUpdate == 0) {
         PlayerManager.access$400(this.thePlayerManager).add(this);
      }

      this.flagsYAreasToUpdate |= 1 << (var2 >> 4);
      if(this.numberOfTilesToUpdate < 64) {
         short var4 = (short)(var1 << 12 | var3 << 8 | var2);

         for(int var5 = 0; var5 < this.numberOfTilesToUpdate; ++var5) {
            if(this.locationOfBlockChange[var5] == var4) {
               return;
            }
         }

         this.locationOfBlockChange[this.numberOfTilesToUpdate++] = var4;
      }

   }

   public void sendToAllPlayersWatchingChunk(Packet var1) {
      for(int var2 = 0; var2 < this.playersWatchingChunk.size(); ++var2) {
         EntityPlayerMP var3 = (EntityPlayerMP)this.playersWatchingChunk.get(var2);
         if(!var3.loadedChunks.contains(this.chunkLocation)) {
            var3.playerNetServerHandler.sendPacket(var1);
         }
      }

   }

   public void sendChunkUpdate() {
      if(this.numberOfTilesToUpdate != 0) {
         int var1;
         int var2;
         int var3;
         if(this.numberOfTilesToUpdate == 1) {
            var1 = this.chunkLocation.chunkXPos * 16 + (this.locationOfBlockChange[0] >> 12 & 15);
            var2 = this.locationOfBlockChange[0] & 255;
            var3 = this.chunkLocation.chunkZPos * 16 + (this.locationOfBlockChange[0] >> 8 & 15);
            this.sendToAllPlayersWatchingChunk(new S23PacketBlockChange(var1, var2, var3, PlayerManager.access$100(this.thePlayerManager)));
            if(PlayerManager.access$100(this.thePlayerManager).getBlock(var1, var2, var3).hasTileEntity()) {
               this.sendTileToAllPlayersWatchingChunk(PlayerManager.access$100(this.thePlayerManager).getTileEntity(var1, var2, var3));
            }
         } else {
            int var4;
            if(this.numberOfTilesToUpdate == 64) {
               var1 = this.chunkLocation.chunkXPos * 16;
               var2 = this.chunkLocation.chunkZPos * 16;
               this.sendToAllPlayersWatchingChunk(new S21PacketChunkData(PlayerManager.access$100(this.thePlayerManager).getChunkFromChunkCoords(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos), false, this.flagsYAreasToUpdate));

               for(var3 = 0; var3 < 16; ++var3) {
                  if((this.flagsYAreasToUpdate & 1 << var3) != 0) {
                     var4 = var3 << 4;
                     List var5 = PlayerManager.access$100(this.thePlayerManager).func_147486_a(var1, var4, var2, var1 + 16, var4 + 16, var2 + 16);

                     for(int var6 = 0; var6 < var5.size(); ++var6) {
                        this.sendTileToAllPlayersWatchingChunk((TileEntity)var5.get(var6));
                     }
                  }
               }
            } else {
               this.sendToAllPlayersWatchingChunk(new S22PacketMultiBlockChange(this.numberOfTilesToUpdate, this.locationOfBlockChange, PlayerManager.access$100(this.thePlayerManager).getChunkFromChunkCoords(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos)));

               for(var1 = 0; var1 < this.numberOfTilesToUpdate; ++var1) {
                  var2 = this.chunkLocation.chunkXPos * 16 + (this.locationOfBlockChange[var1] >> 12 & 15);
                  var3 = this.locationOfBlockChange[var1] & 255;
                  var4 = this.chunkLocation.chunkZPos * 16 + (this.locationOfBlockChange[var1] >> 8 & 15);
                  if(PlayerManager.access$100(this.thePlayerManager).getBlock(var2, var3, var4).hasTileEntity()) {
                     this.sendTileToAllPlayersWatchingChunk(PlayerManager.access$100(this.thePlayerManager).getTileEntity(var2, var3, var4));
                  }
               }
            }
         }

         this.numberOfTilesToUpdate = 0;
         this.flagsYAreasToUpdate = 0;
      }
   }

   private void sendTileToAllPlayersWatchingChunk(TileEntity var1) {
      if(var1 != null) {
         Packet var2 = var1.getDescriptionPacket();
         if(var2 != null) {
            this.sendToAllPlayersWatchingChunk(var2);
         }
      }

   }

   // $FF: synthetic method
   static ChunkCoordIntPair access$500(PlayerManager$PlayerInstance var0) {
      return var0.chunkLocation;
   }

   // $FF: synthetic method
   static List access$600(PlayerManager$PlayerInstance var0) {
      return var0.playersWatchingChunk;
   }
}
