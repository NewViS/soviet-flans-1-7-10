package net.minecraft.client.multiplayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkProviderClient implements IChunkProvider {

   private static final Logger logger = LogManager.getLogger();
   private Chunk blankChunk;
   private LongHashMap chunkMapping = new LongHashMap();
   private List chunkListing = new ArrayList();
   private World worldObj;


   public ChunkProviderClient(World var1) {
      this.blankChunk = new EmptyChunk(var1, 0, 0);
      this.worldObj = var1;
   }

   public boolean chunkExists(int var1, int var2) {
      return true;
   }

   public void unloadChunk(int var1, int var2) {
      Chunk var3 = this.provideChunk(var1, var2);
      if(!var3.isEmpty()) {
         var3.onChunkUnload();
      }

      this.chunkMapping.remove(ChunkCoordIntPair.chunkXZ2Int(var1, var2));
      this.chunkListing.remove(var3);
   }

   public Chunk loadChunk(int var1, int var2) {
      Chunk var3 = new Chunk(this.worldObj, var1, var2);
      this.chunkMapping.add(ChunkCoordIntPair.chunkXZ2Int(var1, var2), var3);
      this.chunkListing.add(var3);
      var3.isChunkLoaded = true;
      return var3;
   }

   public Chunk provideChunk(int var1, int var2) {
      Chunk var3 = (Chunk)this.chunkMapping.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(var1, var2));
      return var3 == null?this.blankChunk:var3;
   }

   public boolean saveChunks(boolean var1, IProgressUpdate var2) {
      return true;
   }

   public void saveExtraData() {}

   public boolean unloadQueuedChunks() {
      long var1 = System.currentTimeMillis();
      Iterator var3 = this.chunkListing.iterator();

      while(var3.hasNext()) {
         Chunk var4 = (Chunk)var3.next();
         var4.func_150804_b(System.currentTimeMillis() - var1 > 5L);
      }

      if(System.currentTimeMillis() - var1 > 100L) {
         logger.info("Warning: Clientside chunk ticking took {} ms", new Object[]{Long.valueOf(System.currentTimeMillis() - var1)});
      }

      return false;
   }

   public boolean canSave() {
      return false;
   }

   public void populate(IChunkProvider var1, int var2, int var3) {}

   public String makeString() {
      return "MultiplayerChunkCache: " + this.chunkMapping.getNumHashElements() + ", " + this.chunkListing.size();
   }

   public List getPossibleCreatures(EnumCreatureType var1, int var2, int var3, int var4) {
      return null;
   }

   public ChunkPosition func_147416_a(World var1, String var2, int var3, int var4, int var5) {
      return null;
   }

   public int getLoadedChunkCount() {
      return this.chunkListing.size();
   }

   public void recreateStructures(int var1, int var2) {}

}
