package net.minecraft.world;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEventData;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.INpc;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.ScoreboardSaveData;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer$1;
import net.minecraft.world.WorldServer$ServerBlockEventList;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.feature.WorldGeneratorBonusChest;
import net.minecraft.world.storage.ISaveHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldServer extends World {

   private static final Logger logger = LogManager.getLogger();
   private final MinecraftServer mcServer;
   private final EntityTracker theEntityTracker;
   private final PlayerManager thePlayerManager;
   private Set pendingTickListEntriesHashSet;
   private TreeSet pendingTickListEntriesTreeSet;
   public ChunkProviderServer theChunkProviderServer;
   public boolean levelSaving;
   private boolean allPlayersSleeping;
   private int updateEntityTick;
   private final Teleporter worldTeleporter;
   private final SpawnerAnimals animalSpawner = new SpawnerAnimals();
   private WorldServer$ServerBlockEventList[] field_147490_S = new WorldServer$ServerBlockEventList[]{new WorldServer$ServerBlockEventList((WorldServer$1)null), new WorldServer$ServerBlockEventList((WorldServer$1)null)};
   private int blockEventCacheIndex;
   private static final WeightedRandomChestContent[] bonusChestContent = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.stick, 0, 1, 3, 10), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.planks), 0, 1, 3, 10), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.log), 0, 1, 3, 10), new WeightedRandomChestContent(Items.stone_axe, 0, 1, 1, 3), new WeightedRandomChestContent(Items.wooden_axe, 0, 1, 1, 5), new WeightedRandomChestContent(Items.stone_pickaxe, 0, 1, 1, 3), new WeightedRandomChestContent(Items.wooden_pickaxe, 0, 1, 1, 5), new WeightedRandomChestContent(Items.apple, 0, 2, 3, 5), new WeightedRandomChestContent(Items.bread, 0, 2, 3, 3), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.log2), 0, 1, 3, 10)};
   private List pendingTickListEntriesThisTick = new ArrayList();
   private IntHashMap entityIdMap;


   public WorldServer(MinecraftServer var1, ISaveHandler var2, String var3, int var4, WorldSettings var5, Profiler var6) {
      super(var2, var3, var5, WorldProvider.getProviderForDimension(var4), var6);
      this.mcServer = var1;
      this.theEntityTracker = new EntityTracker(this);
      this.thePlayerManager = new PlayerManager(this);
      if(this.entityIdMap == null) {
         this.entityIdMap = new IntHashMap();
      }

      if(this.pendingTickListEntriesHashSet == null) {
         this.pendingTickListEntriesHashSet = new HashSet();
      }

      if(this.pendingTickListEntriesTreeSet == null) {
         this.pendingTickListEntriesTreeSet = new TreeSet();
      }

      this.worldTeleporter = new Teleporter(this);
      super.worldScoreboard = new ServerScoreboard(var1);
      ScoreboardSaveData var7 = (ScoreboardSaveData)super.mapStorage.loadData(ScoreboardSaveData.class, "scoreboard");
      if(var7 == null) {
         var7 = new ScoreboardSaveData();
         super.mapStorage.setData("scoreboard", var7);
      }

      var7.func_96499_a(super.worldScoreboard);
      ((ServerScoreboard)super.worldScoreboard).func_96547_a(var7);
   }

   public void tick() {
      super.tick();
      if(this.getWorldInfo().isHardcoreModeEnabled() && super.difficultySetting != EnumDifficulty.HARD) {
         super.difficultySetting = EnumDifficulty.HARD;
      }

      super.provider.worldChunkMgr.cleanupCache();
      if(this.areAllPlayersAsleep()) {
         if(this.getGameRules().getGameRuleBooleanValue("doDaylightCycle")) {
            long var1 = super.worldInfo.getWorldTime() + 24000L;
            super.worldInfo.setWorldTime(var1 - var1 % 24000L);
         }

         this.wakeAllPlayers();
      }

      super.theProfiler.startSection("mobSpawner");
      if(this.getGameRules().getGameRuleBooleanValue("doMobSpawning")) {
         this.animalSpawner.findChunksForSpawning(this, super.spawnHostileMobs, super.spawnPeacefulMobs, super.worldInfo.getWorldTotalTime() % 400L == 0L);
      }

      super.theProfiler.endStartSection("chunkSource");
      super.chunkProvider.unloadQueuedChunks();
      int var3 = this.calculateSkylightSubtracted(1.0F);
      if(var3 != super.skylightSubtracted) {
         super.skylightSubtracted = var3;
      }

      super.worldInfo.incrementTotalWorldTime(super.worldInfo.getWorldTotalTime() + 1L);
      if(this.getGameRules().getGameRuleBooleanValue("doDaylightCycle")) {
         super.worldInfo.setWorldTime(super.worldInfo.getWorldTime() + 1L);
      }

      super.theProfiler.endStartSection("tickPending");
      this.tickUpdates(false);
      super.theProfiler.endStartSection("tickBlocks");
      this.func_147456_g();
      super.theProfiler.endStartSection("chunkMap");
      this.thePlayerManager.updatePlayerInstances();
      super.theProfiler.endStartSection("village");
      super.villageCollectionObj.tick();
      super.villageSiegeObj.tick();
      super.theProfiler.endStartSection("portalForcer");
      this.worldTeleporter.removeStalePortalLocations(this.getTotalWorldTime());
      super.theProfiler.endSection();
      this.func_147488_Z();
   }

   public BiomeGenBase$SpawnListEntry spawnRandomCreature(EnumCreatureType var1, int var2, int var3, int var4) {
      List var5 = this.getChunkProvider().getPossibleCreatures(var1, var2, var3, var4);
      return var5 != null && !var5.isEmpty()?(BiomeGenBase$SpawnListEntry)WeightedRandom.getRandomItem(super.rand, (Collection)var5):null;
   }

   public void updateAllPlayersSleepingFlag() {
      this.allPlayersSleeping = !super.playerEntities.isEmpty();
      Iterator var1 = super.playerEntities.iterator();

      while(var1.hasNext()) {
         EntityPlayer var2 = (EntityPlayer)var1.next();
         if(!var2.isPlayerSleeping()) {
            this.allPlayersSleeping = false;
            break;
         }
      }

   }

   protected void wakeAllPlayers() {
      this.allPlayersSleeping = false;
      Iterator var1 = super.playerEntities.iterator();

      while(var1.hasNext()) {
         EntityPlayer var2 = (EntityPlayer)var1.next();
         if(var2.isPlayerSleeping()) {
            var2.wakeUpPlayer(false, false, true);
         }
      }

      this.resetRainAndThunder();
   }

   private void resetRainAndThunder() {
      super.worldInfo.setRainTime(0);
      super.worldInfo.setRaining(false);
      super.worldInfo.setThunderTime(0);
      super.worldInfo.setThundering(false);
   }

   public boolean areAllPlayersAsleep() {
      if(this.allPlayersSleeping && !super.isRemote) {
         Iterator var1 = super.playerEntities.iterator();

         EntityPlayer var2;
         do {
            if(!var1.hasNext()) {
               return true;
            }

            var2 = (EntityPlayer)var1.next();
         } while(var2.isPlayerFullyAsleep());

         return false;
      } else {
         return false;
      }
   }

   public void setSpawnLocation() {
      if(super.worldInfo.getSpawnY() <= 0) {
         super.worldInfo.setSpawnY(64);
      }

      int var1 = super.worldInfo.getSpawnX();
      int var2 = super.worldInfo.getSpawnZ();
      int var3 = 0;

      while(this.getTopBlock(var1, var2).getMaterial() == Material.air) {
         var1 += super.rand.nextInt(8) - super.rand.nextInt(8);
         var2 += super.rand.nextInt(8) - super.rand.nextInt(8);
         ++var3;
         if(var3 == 10000) {
            break;
         }
      }

      super.worldInfo.setSpawnX(var1);
      super.worldInfo.setSpawnZ(var2);
   }

   protected void func_147456_g() {
      super.func_147456_g();
      int var1 = 0;
      int var2 = 0;
      Iterator var3 = super.activeChunkSet.iterator();

      while(var3.hasNext()) {
         ChunkCoordIntPair var4 = (ChunkCoordIntPair)var3.next();
         int var5 = var4.chunkXPos * 16;
         int var6 = var4.chunkZPos * 16;
         super.theProfiler.startSection("getChunk");
         Chunk var7 = this.getChunkFromChunkCoords(var4.chunkXPos, var4.chunkZPos);
         this.func_147467_a(var5, var6, var7);
         super.theProfiler.endStartSection("tickChunk");
         var7.func_150804_b(false);
         super.theProfiler.endStartSection("thunder");
         int var8;
         int var9;
         int var10;
         int var11;
         if(super.rand.nextInt(100000) == 0 && this.isRaining() && this.isThundering()) {
            super.updateLCG = super.updateLCG * 3 + 1013904223;
            var8 = super.updateLCG >> 2;
            var9 = var5 + (var8 & 15);
            var10 = var6 + (var8 >> 8 & 15);
            var11 = this.getPrecipitationHeight(var9, var10);
            if(this.canLightningStrikeAt(var9, var11, var10)) {
               this.addWeatherEffect(new EntityLightningBolt(this, (double)var9, (double)var11, (double)var10));
            }
         }

         super.theProfiler.endStartSection("iceandsnow");
         if(super.rand.nextInt(16) == 0) {
            super.updateLCG = super.updateLCG * 3 + 1013904223;
            var8 = super.updateLCG >> 2;
            var9 = var8 & 15;
            var10 = var8 >> 8 & 15;
            var11 = this.getPrecipitationHeight(var9 + var5, var10 + var6);
            if(this.isBlockFreezableNaturally(var9 + var5, var11 - 1, var10 + var6)) {
               this.setBlock(var9 + var5, var11 - 1, var10 + var6, Blocks.ice);
            }

            if(this.isRaining() && this.func_147478_e(var9 + var5, var11, var10 + var6, true)) {
               this.setBlock(var9 + var5, var11, var10 + var6, Blocks.snow_layer);
            }

            if(this.isRaining()) {
               BiomeGenBase var12 = this.getBiomeGenForCoords(var9 + var5, var10 + var6);
               if(var12.canSpawnLightningBolt()) {
                  this.getBlock(var9 + var5, var11 - 1, var10 + var6).fillWithRain(this, var9 + var5, var11 - 1, var10 + var6);
               }
            }
         }

         super.theProfiler.endStartSection("tickBlocks");
         ExtendedBlockStorage[] var18 = var7.getBlockStorageArray();
         var9 = var18.length;

         for(var10 = 0; var10 < var9; ++var10) {
            ExtendedBlockStorage var19 = var18[var10];
            if(var19 != null && var19.getNeedsRandomTick()) {
               for(int var20 = 0; var20 < 3; ++var20) {
                  super.updateLCG = super.updateLCG * 3 + 1013904223;
                  int var13 = super.updateLCG >> 2;
                  int var14 = var13 & 15;
                  int var15 = var13 >> 8 & 15;
                  int var16 = var13 >> 16 & 15;
                  ++var2;
                  Block var17 = var19.getBlockByExtId(var14, var16, var15);
                  if(var17.getTickRandomly()) {
                     ++var1;
                     var17.updateTick(this, var14 + var5, var16 + var19.getYLocation(), var15 + var6, super.rand);
                  }
               }
            }
         }

         super.theProfiler.endSection();
      }

   }

   public boolean isBlockTickScheduledThisTick(int var1, int var2, int var3, Block var4) {
      NextTickListEntry var5 = new NextTickListEntry(var1, var2, var3, var4);
      return this.pendingTickListEntriesThisTick.contains(var5);
   }

   public void scheduleBlockUpdate(int var1, int var2, int var3, Block var4, int var5) {
      this.scheduleBlockUpdateWithPriority(var1, var2, var3, var4, var5, 0);
   }

   public void scheduleBlockUpdateWithPriority(int var1, int var2, int var3, Block var4, int var5, int var6) {
      NextTickListEntry var7 = new NextTickListEntry(var1, var2, var3, var4);
      byte var8 = 0;
      if(super.scheduledUpdatesAreImmediate && var4.getMaterial() != Material.air) {
         if(var4.func_149698_L()) {
            var8 = 8;
            if(this.checkChunksExist(var7.xCoord - var8, var7.yCoord - var8, var7.zCoord - var8, var7.xCoord + var8, var7.yCoord + var8, var7.zCoord + var8)) {
               Block var9 = this.getBlock(var7.xCoord, var7.yCoord, var7.zCoord);
               if(var9.getMaterial() != Material.air && var9 == var7.func_151351_a()) {
                  var9.updateTick(this, var7.xCoord, var7.yCoord, var7.zCoord, super.rand);
               }
            }

            return;
         }

         var5 = 1;
      }

      if(this.checkChunksExist(var1 - var8, var2 - var8, var3 - var8, var1 + var8, var2 + var8, var3 + var8)) {
         if(var4.getMaterial() != Material.air) {
            var7.setScheduledTime((long)var5 + super.worldInfo.getWorldTotalTime());
            var7.setPriority(var6);
         }

         if(!this.pendingTickListEntriesHashSet.contains(var7)) {
            this.pendingTickListEntriesHashSet.add(var7);
            this.pendingTickListEntriesTreeSet.add(var7);
         }
      }

   }

   public void func_147446_b(int var1, int var2, int var3, Block var4, int var5, int var6) {
      NextTickListEntry var7 = new NextTickListEntry(var1, var2, var3, var4);
      var7.setPriority(var6);
      if(var4.getMaterial() != Material.air) {
         var7.setScheduledTime((long)var5 + super.worldInfo.getWorldTotalTime());
      }

      if(!this.pendingTickListEntriesHashSet.contains(var7)) {
         this.pendingTickListEntriesHashSet.add(var7);
         this.pendingTickListEntriesTreeSet.add(var7);
      }

   }

   public void updateEntities() {
      if(super.playerEntities.isEmpty()) {
         if(this.updateEntityTick++ >= 1200) {
            return;
         }
      } else {
         this.resetUpdateEntityTick();
      }

      super.updateEntities();
   }

   public void resetUpdateEntityTick() {
      this.updateEntityTick = 0;
   }

   public boolean tickUpdates(boolean var1) {
      int var2 = this.pendingTickListEntriesTreeSet.size();
      if(var2 != this.pendingTickListEntriesHashSet.size()) {
         throw new IllegalStateException("TickNextTick list out of synch");
      } else {
         if(var2 > 1000) {
            var2 = 1000;
         }

         super.theProfiler.startSection("cleaning");

         NextTickListEntry var4;
         for(int var3 = 0; var3 < var2; ++var3) {
            var4 = (NextTickListEntry)this.pendingTickListEntriesTreeSet.first();
            if(!var1 && var4.scheduledTime > super.worldInfo.getWorldTotalTime()) {
               break;
            }

            this.pendingTickListEntriesTreeSet.remove(var4);
            this.pendingTickListEntriesHashSet.remove(var4);
            this.pendingTickListEntriesThisTick.add(var4);
         }

         super.theProfiler.endSection();
         super.theProfiler.startSection("ticking");
         Iterator var14 = this.pendingTickListEntriesThisTick.iterator();

         while(var14.hasNext()) {
            var4 = (NextTickListEntry)var14.next();
            var14.remove();
            byte var5 = 0;
            if(this.checkChunksExist(var4.xCoord - var5, var4.yCoord - var5, var4.zCoord - var5, var4.xCoord + var5, var4.yCoord + var5, var4.zCoord + var5)) {
               Block var6 = this.getBlock(var4.xCoord, var4.yCoord, var4.zCoord);
               if(var6.getMaterial() != Material.air && Block.isEqualTo(var6, var4.func_151351_a())) {
                  try {
                     var6.updateTick(this, var4.xCoord, var4.yCoord, var4.zCoord, super.rand);
                  } catch (Throwable var13) {
                     CrashReport var8 = CrashReport.makeCrashReport(var13, "Exception while ticking a block");
                     CrashReportCategory var9 = var8.makeCategory("Block being ticked");

                     int var10;
                     try {
                        var10 = this.getBlockMetadata(var4.xCoord, var4.yCoord, var4.zCoord);
                     } catch (Throwable var12) {
                        var10 = -1;
                     }

                     CrashReportCategory.func_147153_a(var9, var4.xCoord, var4.yCoord, var4.zCoord, var6, var10);
                     throw new ReportedException(var8);
                  }
               }
            } else {
               this.scheduleBlockUpdate(var4.xCoord, var4.yCoord, var4.zCoord, var4.func_151351_a(), 0);
            }
         }

         super.theProfiler.endSection();
         this.pendingTickListEntriesThisTick.clear();
         return !this.pendingTickListEntriesTreeSet.isEmpty();
      }
   }

   public List getPendingBlockUpdates(Chunk var1, boolean var2) {
      ArrayList var3 = null;
      ChunkCoordIntPair var4 = var1.getChunkCoordIntPair();
      int var5 = (var4.chunkXPos << 4) - 2;
      int var6 = var5 + 16 + 2;
      int var7 = (var4.chunkZPos << 4) - 2;
      int var8 = var7 + 16 + 2;

      for(int var9 = 0; var9 < 2; ++var9) {
         Iterator var10;
         if(var9 == 0) {
            var10 = this.pendingTickListEntriesTreeSet.iterator();
         } else {
            var10 = this.pendingTickListEntriesThisTick.iterator();
            if(!this.pendingTickListEntriesThisTick.isEmpty()) {
               logger.debug("toBeTicked = " + this.pendingTickListEntriesThisTick.size());
            }
         }

         while(var10.hasNext()) {
            NextTickListEntry var11 = (NextTickListEntry)var10.next();
            if(var11.xCoord >= var5 && var11.xCoord < var6 && var11.zCoord >= var7 && var11.zCoord < var8) {
               if(var2) {
                  this.pendingTickListEntriesHashSet.remove(var11);
                  var10.remove();
               }

               if(var3 == null) {
                  var3 = new ArrayList();
               }

               var3.add(var11);
            }
         }
      }

      return var3;
   }

   public void updateEntityWithOptionalForce(Entity var1, boolean var2) {
      if(!this.mcServer.getCanSpawnAnimals() && (var1 instanceof EntityAnimal || var1 instanceof EntityWaterMob)) {
         var1.setDead();
      }

      if(!this.mcServer.getCanSpawnNPCs() && var1 instanceof INpc) {
         var1.setDead();
      }

      super.updateEntityWithOptionalForce(var1, var2);
   }

   protected IChunkProvider createChunkProvider() {
      IChunkLoader var1 = super.saveHandler.getChunkLoader(super.provider);
      this.theChunkProviderServer = new ChunkProviderServer(this, var1, super.provider.createChunkGenerator());
      return this.theChunkProviderServer;
   }

   public List func_147486_a(int var1, int var2, int var3, int var4, int var5, int var6) {
      ArrayList var7 = new ArrayList();

      for(int var8 = 0; var8 < super.loadedTileEntityList.size(); ++var8) {
         TileEntity var9 = (TileEntity)super.loadedTileEntityList.get(var8);
         if(var9.xCoord >= var1 && var9.yCoord >= var2 && var9.zCoord >= var3 && var9.xCoord < var4 && var9.yCoord < var5 && var9.zCoord < var6) {
            var7.add(var9);
         }
      }

      return var7;
   }

   public boolean canMineBlock(EntityPlayer var1, int var2, int var3, int var4) {
      return !this.mcServer.isBlockProtected(this, var2, var3, var4, var1);
   }

   protected void initialize(WorldSettings var1) {
      if(this.entityIdMap == null) {
         this.entityIdMap = new IntHashMap();
      }

      if(this.pendingTickListEntriesHashSet == null) {
         this.pendingTickListEntriesHashSet = new HashSet();
      }

      if(this.pendingTickListEntriesTreeSet == null) {
         this.pendingTickListEntriesTreeSet = new TreeSet();
      }

      this.createSpawnPosition(var1);
      super.initialize(var1);
   }

   protected void createSpawnPosition(WorldSettings var1) {
      if(!super.provider.canRespawnHere()) {
         super.worldInfo.setSpawnPosition(0, super.provider.getAverageGroundLevel(), 0);
      } else {
         super.findingSpawnPoint = true;
         WorldChunkManager var2 = super.provider.worldChunkMgr;
         List var3 = var2.getBiomesToSpawnIn();
         Random var4 = new Random(this.getSeed());
         ChunkPosition var5 = var2.findBiomePosition(0, 0, 256, var3, var4);
         int var6 = 0;
         int var7 = super.provider.getAverageGroundLevel();
         int var8 = 0;
         if(var5 != null) {
            var6 = var5.chunkPosX;
            var8 = var5.chunkPosZ;
         } else {
            logger.warn("Unable to find spawn biome");
         }

         int var9 = 0;

         while(!super.provider.canCoordinateBeSpawn(var6, var8)) {
            var6 += var4.nextInt(64) - var4.nextInt(64);
            var8 += var4.nextInt(64) - var4.nextInt(64);
            ++var9;
            if(var9 == 1000) {
               break;
            }
         }

         super.worldInfo.setSpawnPosition(var6, var7, var8);
         super.findingSpawnPoint = false;
         if(var1.isBonusChestEnabled()) {
            this.createBonusChest();
         }

      }
   }

   protected void createBonusChest() {
      WorldGeneratorBonusChest var1 = new WorldGeneratorBonusChest(bonusChestContent, 10);

      for(int var2 = 0; var2 < 10; ++var2) {
         int var3 = super.worldInfo.getSpawnX() + super.rand.nextInt(6) - super.rand.nextInt(6);
         int var4 = super.worldInfo.getSpawnZ() + super.rand.nextInt(6) - super.rand.nextInt(6);
         int var5 = this.getTopSolidOrLiquidBlock(var3, var4) + 1;
         if(var1.generate(this, super.rand, var3, var5, var4)) {
            break;
         }
      }

   }

   public ChunkCoordinates getEntrancePortalLocation() {
      return super.provider.getEntrancePortalLocation();
   }

   public void saveAllChunks(boolean var1, IProgressUpdate var2) {
      if(super.chunkProvider.canSave()) {
         if(var2 != null) {
            var2.displayProgressMessage("Saving level");
         }

         this.saveLevel();
         if(var2 != null) {
            var2.resetProgresAndWorkingMessage("Saving chunks");
         }

         super.chunkProvider.saveChunks(var1, var2);
         ArrayList var3 = Lists.newArrayList(this.theChunkProviderServer.func_152380_a());
         Iterator var4 = var3.iterator();

         while(var4.hasNext()) {
            Chunk var5 = (Chunk)var4.next();
            if(var5 != null && !this.thePlayerManager.func_152621_a(var5.xPosition, var5.zPosition)) {
               this.theChunkProviderServer.unloadChunksIfNotNearSpawn(var5.xPosition, var5.zPosition);
            }
         }

      }
   }

   public void saveChunkData() {
      if(super.chunkProvider.canSave()) {
         super.chunkProvider.saveExtraData();
      }
   }

   protected void saveLevel() {
      this.checkSessionLock();
      super.saveHandler.saveWorldInfoWithPlayer(super.worldInfo, this.mcServer.getConfigurationManager().getHostPlayerData());
      super.mapStorage.saveAllData();
   }

   protected void onEntityAdded(Entity var1) {
      super.onEntityAdded(var1);
      this.entityIdMap.addKey(var1.getEntityId(), var1);
      Entity[] var2 = var1.getParts();
      if(var2 != null) {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            this.entityIdMap.addKey(var2[var3].getEntityId(), var2[var3]);
         }
      }

   }

   protected void onEntityRemoved(Entity var1) {
      super.onEntityRemoved(var1);
      this.entityIdMap.removeObject(var1.getEntityId());
      Entity[] var2 = var1.getParts();
      if(var2 != null) {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            this.entityIdMap.removeObject(var2[var3].getEntityId());
         }
      }

   }

   public Entity getEntityByID(int var1) {
      return (Entity)this.entityIdMap.lookup(var1);
   }

   public boolean addWeatherEffect(Entity var1) {
      if(super.addWeatherEffect(var1)) {
         this.mcServer.getConfigurationManager().sendToAllNear(var1.posX, var1.posY, var1.posZ, 512.0D, super.provider.dimensionId, new S2CPacketSpawnGlobalEntity(var1));
         return true;
      } else {
         return false;
      }
   }

   public void setEntityState(Entity var1, byte var2) {
      this.getEntityTracker().func_151248_b(var1, new S19PacketEntityStatus(var1, var2));
   }

   public Explosion newExplosion(Entity var1, double var2, double var4, double var6, float var8, boolean var9, boolean var10) {
      Explosion var11 = new Explosion(this, var1, var2, var4, var6, var8);
      var11.isFlaming = var9;
      var11.isSmoking = var10;
      var11.doExplosionA();
      var11.doExplosionB(false);
      if(!var10) {
         var11.affectedBlockPositions.clear();
      }

      Iterator var12 = super.playerEntities.iterator();

      while(var12.hasNext()) {
         EntityPlayer var13 = (EntityPlayer)var12.next();
         if(var13.getDistanceSq(var2, var4, var6) < 4096.0D) {
            ((EntityPlayerMP)var13).playerNetServerHandler.sendPacket(new S27PacketExplosion(var2, var4, var6, var8, var11.affectedBlockPositions, (Vec3)var11.func_77277_b().get(var13)));
         }
      }

      return var11;
   }

   public void addBlockEvent(int var1, int var2, int var3, Block var4, int var5, int var6) {
      BlockEventData var7 = new BlockEventData(var1, var2, var3, var4, var5, var6);
      Iterator var8 = this.field_147490_S[this.blockEventCacheIndex].iterator();

      BlockEventData var9;
      do {
         if(!var8.hasNext()) {
            this.field_147490_S[this.blockEventCacheIndex].add(var7);
            return;
         }

         var9 = (BlockEventData)var8.next();
      } while(!var9.equals(var7));

   }

   private void func_147488_Z() {
      while(!this.field_147490_S[this.blockEventCacheIndex].isEmpty()) {
         int var1 = this.blockEventCacheIndex;
         this.blockEventCacheIndex ^= 1;
         Iterator var2 = this.field_147490_S[var1].iterator();

         while(var2.hasNext()) {
            BlockEventData var3 = (BlockEventData)var2.next();
            if(this.func_147485_a(var3)) {
               this.mcServer.getConfigurationManager().sendToAllNear((double)var3.func_151340_a(), (double)var3.func_151342_b(), (double)var3.func_151341_c(), 64.0D, super.provider.dimensionId, new S24PacketBlockAction(var3.func_151340_a(), var3.func_151342_b(), var3.func_151341_c(), var3.getBlock(), var3.getEventID(), var3.getEventParameter()));
            }
         }

         this.field_147490_S[var1].clear();
      }

   }

   private boolean func_147485_a(BlockEventData var1) {
      Block var2 = this.getBlock(var1.func_151340_a(), var1.func_151342_b(), var1.func_151341_c());
      return var2 == var1.getBlock()?var2.onBlockEventReceived(this, var1.func_151340_a(), var1.func_151342_b(), var1.func_151341_c(), var1.getEventID(), var1.getEventParameter()):false;
   }

   public void flush() {
      super.saveHandler.flush();
   }

   protected void updateWeather() {
      boolean var1 = this.isRaining();
      super.updateWeather();
      if(super.prevRainingStrength != super.rainingStrength) {
         this.mcServer.getConfigurationManager().sendPacketToAllPlayersInDimension(new S2BPacketChangeGameState(7, super.rainingStrength), super.provider.dimensionId);
      }

      if(super.prevThunderingStrength != super.thunderingStrength) {
         this.mcServer.getConfigurationManager().sendPacketToAllPlayersInDimension(new S2BPacketChangeGameState(8, super.thunderingStrength), super.provider.dimensionId);
      }

      if(var1 != this.isRaining()) {
         if(var1) {
            this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(2, 0.0F));
         } else {
            this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(1, 0.0F));
         }

         this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(7, super.rainingStrength));
         this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(8, super.thunderingStrength));
      }

   }

   protected int func_152379_p() {
      return this.mcServer.getConfigurationManager().getViewDistance();
   }

   public MinecraftServer func_73046_m() {
      return this.mcServer;
   }

   public EntityTracker getEntityTracker() {
      return this.theEntityTracker;
   }

   public PlayerManager getPlayerManager() {
      return this.thePlayerManager;
   }

   public Teleporter getDefaultTeleporter() {
      return this.worldTeleporter;
   }

   public void func_147487_a(String var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, double var15) {
      S2APacketParticles var17 = new S2APacketParticles(var1, (float)var2, (float)var4, (float)var6, (float)var9, (float)var11, (float)var13, (float)var15, var8);

      for(int var18 = 0; var18 < super.playerEntities.size(); ++var18) {
         EntityPlayerMP var19 = (EntityPlayerMP)super.playerEntities.get(var18);
         ChunkCoordinates var20 = var19.getPlayerCoordinates();
         double var21 = var2 - (double)var20.posX;
         double var23 = var4 - (double)var20.posY;
         double var25 = var6 - (double)var20.posZ;
         double var27 = var21 * var21 + var23 * var23 + var25 * var25;
         if(var27 <= 256.0D) {
            var19.playerNetServerHandler.sendPacket(var17);
         }
      }

   }

}
