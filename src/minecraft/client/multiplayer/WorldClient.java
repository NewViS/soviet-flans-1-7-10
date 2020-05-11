package net.minecraft.client.multiplayer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSoundMinecart;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.multiplayer.WorldClient$1;
import net.minecraft.client.multiplayer.WorldClient$2;
import net.minecraft.client.multiplayer.WorldClient$3;
import net.minecraft.client.multiplayer.WorldClient$4;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.particle.EntityFireworkStarterFX;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.SaveHandlerMP;

public class WorldClient extends World {

   private NetHandlerPlayClient sendQueue;
   private ChunkProviderClient clientChunkProvider;
   private IntHashMap entityHashSet = new IntHashMap();
   private Set entityList = new HashSet();
   private Set entitySpawnQueue = new HashSet();
   private final Minecraft mc = Minecraft.getMinecraft();
   private final Set previousActiveChunkSet = new HashSet();


   public WorldClient(NetHandlerPlayClient var1, WorldSettings var2, int var3, EnumDifficulty var4, Profiler var5) {
      super(new SaveHandlerMP(), "MpServer", WorldProvider.getProviderForDimension(var3), var2, var5);
      this.sendQueue = var1;
      super.difficultySetting = var4;
      this.setSpawnLocation(8, 64, 8);
      super.mapStorage = var1.mapStorageOrigin;
   }

   public void tick() {
      super.tick();
      this.func_82738_a(this.getTotalWorldTime() + 1L);
      if(this.getGameRules().getGameRuleBooleanValue("doDaylightCycle")) {
         this.setWorldTime(this.getWorldTime() + 1L);
      }

      super.theProfiler.startSection("reEntryProcessing");

      for(int var1 = 0; var1 < 10 && !this.entitySpawnQueue.isEmpty(); ++var1) {
         Entity var2 = (Entity)this.entitySpawnQueue.iterator().next();
         this.entitySpawnQueue.remove(var2);
         if(!super.loadedEntityList.contains(var2)) {
            this.spawnEntityInWorld(var2);
         }
      }

      super.theProfiler.endStartSection("connection");
      this.sendQueue.onNetworkTick();
      super.theProfiler.endStartSection("chunkCache");
      this.clientChunkProvider.unloadQueuedChunks();
      super.theProfiler.endStartSection("blocks");
      this.func_147456_g();
      super.theProfiler.endSection();
   }

   public void invalidateBlockReceiveRegion(int var1, int var2, int var3, int var4, int var5, int var6) {}

   protected IChunkProvider createChunkProvider() {
      this.clientChunkProvider = new ChunkProviderClient(this);
      return this.clientChunkProvider;
   }

   protected void func_147456_g() {
      super.func_147456_g();
      this.previousActiveChunkSet.retainAll(super.activeChunkSet);
      if(this.previousActiveChunkSet.size() == super.activeChunkSet.size()) {
         this.previousActiveChunkSet.clear();
      }

      int var1 = 0;
      Iterator var2 = super.activeChunkSet.iterator();

      while(var2.hasNext()) {
         ChunkCoordIntPair var3 = (ChunkCoordIntPair)var2.next();
         if(!this.previousActiveChunkSet.contains(var3)) {
            int var4 = var3.chunkXPos * 16;
            int var5 = var3.chunkZPos * 16;
            super.theProfiler.startSection("getChunk");
            Chunk var6 = this.getChunkFromChunkCoords(var3.chunkXPos, var3.chunkZPos);
            this.func_147467_a(var4, var5, var6);
            super.theProfiler.endSection();
            this.previousActiveChunkSet.add(var3);
            ++var1;
            if(var1 >= 10) {
               return;
            }
         }
      }

   }

   public void doPreChunk(int var1, int var2, boolean var3) {
      if(var3) {
         this.clientChunkProvider.loadChunk(var1, var2);
      } else {
         this.clientChunkProvider.unloadChunk(var1, var2);
      }

      if(!var3) {
         this.markBlockRangeForRenderUpdate(var1 * 16, 0, var2 * 16, var1 * 16 + 15, 256, var2 * 16 + 15);
      }

   }

   public boolean spawnEntityInWorld(Entity var1) {
      boolean var2 = super.spawnEntityInWorld(var1);
      this.entityList.add(var1);
      if(!var2) {
         this.entitySpawnQueue.add(var1);
      } else if(var1 instanceof EntityMinecart) {
         this.mc.getSoundHandler().playSound(new MovingSoundMinecart((EntityMinecart)var1));
      }

      return var2;
   }

   public void removeEntity(Entity var1) {
      super.removeEntity(var1);
      this.entityList.remove(var1);
   }

   protected void onEntityAdded(Entity var1) {
      super.onEntityAdded(var1);
      if(this.entitySpawnQueue.contains(var1)) {
         this.entitySpawnQueue.remove(var1);
      }

   }

   protected void onEntityRemoved(Entity var1) {
      super.onEntityRemoved(var1);
      boolean var2 = false;
      if(this.entityList.contains(var1)) {
         if(var1.isEntityAlive()) {
            this.entitySpawnQueue.add(var1);
            var2 = true;
         } else {
            this.entityList.remove(var1);
         }
      }

      if(RenderManager.instance.getEntityRenderObject(var1).isStaticEntity() && !var2) {
         this.mc.renderGlobal.onStaticEntitiesChanged();
      }

   }

   public void addEntityToWorld(int var1, Entity var2) {
      Entity var3 = this.getEntityByID(var1);
      if(var3 != null) {
         this.removeEntity(var3);
      }

      this.entityList.add(var2);
      var2.setEntityId(var1);
      if(!this.spawnEntityInWorld(var2)) {
         this.entitySpawnQueue.add(var2);
      }

      this.entityHashSet.addKey(var1, var2);
      if(RenderManager.instance.getEntityRenderObject(var2).isStaticEntity()) {
         this.mc.renderGlobal.onStaticEntitiesChanged();
      }

   }

   public Entity getEntityByID(int var1) {
      return (Entity)(var1 == this.mc.thePlayer.getEntityId()?this.mc.thePlayer:(Entity)this.entityHashSet.lookup(var1));
   }

   public Entity removeEntityFromWorld(int var1) {
      Entity var2 = (Entity)this.entityHashSet.removeObject(var1);
      if(var2 != null) {
         this.entityList.remove(var2);
         this.removeEntity(var2);
      }

      return var2;
   }

   public boolean func_147492_c(int var1, int var2, int var3, Block var4, int var5) {
      this.invalidateBlockReceiveRegion(var1, var2, var3, var1, var2, var3);
      return super.setBlock(var1, var2, var3, var4, var5, 3);
   }

   public void sendQuittingDisconnectingPacket() {
      this.sendQueue.getNetworkManager().closeChannel(new ChatComponentText("Quitting"));
   }

   protected void updateWeather() {
      if(!super.provider.hasNoSky) {
         ;
      }
   }

   protected int func_152379_p() {
      return this.mc.gameSettings.renderDistanceChunks;
   }

   public void doVoidFogParticles(int var1, int var2, int var3) {
      byte var4 = 16;
      Random var5 = new Random();

      for(int var6 = 0; var6 < 1000; ++var6) {
         int var7 = var1 + super.rand.nextInt(var4) - super.rand.nextInt(var4);
         int var8 = var2 + super.rand.nextInt(var4) - super.rand.nextInt(var4);
         int var9 = var3 + super.rand.nextInt(var4) - super.rand.nextInt(var4);
         Block var10 = this.getBlock(var7, var8, var9);
         if(var10.getMaterial() == Material.air) {
            if(super.rand.nextInt(8) > var8 && super.provider.getWorldHasVoidParticles()) {
               this.spawnParticle("depthsuspend", (double)((float)var7 + super.rand.nextFloat()), (double)((float)var8 + super.rand.nextFloat()), (double)((float)var9 + super.rand.nextFloat()), 0.0D, 0.0D, 0.0D);
            }
         } else {
            var10.randomDisplayTick(this, var7, var8, var9, var5);
         }
      }

   }

   public void removeAllEntities() {
      super.loadedEntityList.removeAll(super.unloadedEntityList);

      int var1;
      Entity var2;
      int var3;
      int var4;
      for(var1 = 0; var1 < super.unloadedEntityList.size(); ++var1) {
         var2 = (Entity)super.unloadedEntityList.get(var1);
         var3 = var2.chunkCoordX;
         var4 = var2.chunkCoordZ;
         if(var2.addedToChunk && this.chunkExists(var3, var4)) {
            this.getChunkFromChunkCoords(var3, var4).removeEntity(var2);
         }
      }

      for(var1 = 0; var1 < super.unloadedEntityList.size(); ++var1) {
         this.onEntityRemoved((Entity)super.unloadedEntityList.get(var1));
      }

      super.unloadedEntityList.clear();

      for(var1 = 0; var1 < super.loadedEntityList.size(); ++var1) {
         var2 = (Entity)super.loadedEntityList.get(var1);
         if(var2.ridingEntity != null) {
            if(!var2.ridingEntity.isDead && var2.ridingEntity.riddenByEntity == var2) {
               continue;
            }

            var2.ridingEntity.riddenByEntity = null;
            var2.ridingEntity = null;
         }

         if(var2.isDead) {
            var3 = var2.chunkCoordX;
            var4 = var2.chunkCoordZ;
            if(var2.addedToChunk && this.chunkExists(var3, var4)) {
               this.getChunkFromChunkCoords(var3, var4).removeEntity(var2);
            }

            super.loadedEntityList.remove(var1--);
            this.onEntityRemoved(var2);
         }
      }

   }

   public CrashReportCategory addWorldInfoToCrashReport(CrashReport var1) {
      CrashReportCategory var2 = super.addWorldInfoToCrashReport(var1);
      var2.addCrashSectionCallable("Forced entities", new WorldClient$1(this));
      var2.addCrashSectionCallable("Retry entities", new WorldClient$2(this));
      var2.addCrashSectionCallable("Server brand", new WorldClient$3(this));
      var2.addCrashSectionCallable("Server type", new WorldClient$4(this));
      return var2;
   }

   public void playSound(double var1, double var3, double var5, String var7, float var8, float var9, boolean var10) {
      double var11 = this.mc.renderViewEntity.getDistanceSq(var1, var3, var5);
      PositionedSoundRecord var13 = new PositionedSoundRecord(new ResourceLocation(var7), var8, var9, (float)var1, (float)var3, (float)var5);
      if(var10 && var11 > 100.0D) {
         double var14 = Math.sqrt(var11) / 40.0D;
         this.mc.getSoundHandler().playDelayedSound(var13, (int)(var14 * 20.0D));
      } else {
         this.mc.getSoundHandler().playSound(var13);
      }

   }

   public void makeFireworks(double var1, double var3, double var5, double var7, double var9, double var11, NBTTagCompound var13) {
      this.mc.effectRenderer.addEffect(new EntityFireworkStarterFX(this, var1, var3, var5, var7, var9, var11, this.mc.effectRenderer, var13));
   }

   public void setWorldScoreboard(Scoreboard var1) {
      super.worldScoreboard = var1;
   }

   public void setWorldTime(long var1) {
      if(var1 < 0L) {
         var1 = -var1;
         this.getGameRules().setOrCreateGameRule("doDaylightCycle", "false");
      } else {
         this.getGameRules().setOrCreateGameRule("doDaylightCycle", "true");
      }

      super.setWorldTime(var1);
   }

   // $FF: synthetic method
   static Set access$000(WorldClient var0) {
      return var0.entityList;
   }

   // $FF: synthetic method
   static Set access$100(WorldClient var0) {
      return var0.entitySpawnQueue;
   }

   // $FF: synthetic method
   static Minecraft access$200(WorldClient var0) {
      return var0.mc;
   }
}
