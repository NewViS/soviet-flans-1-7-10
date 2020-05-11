package net.minecraft.world.storage;

import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.WorldInfo$1;
import net.minecraft.world.storage.WorldInfo$2;
import net.minecraft.world.storage.WorldInfo$3;
import net.minecraft.world.storage.WorldInfo$4;
import net.minecraft.world.storage.WorldInfo$5;
import net.minecraft.world.storage.WorldInfo$6;
import net.minecraft.world.storage.WorldInfo$7;
import net.minecraft.world.storage.WorldInfo$8;
import net.minecraft.world.storage.WorldInfo$9;

public class WorldInfo {

   private long randomSeed;
   private WorldType terrainType;
   private String generatorOptions;
   private int spawnX;
   private int spawnY;
   private int spawnZ;
   private long totalTime;
   private long worldTime;
   private long lastTimePlayed;
   private long sizeOnDisk;
   private NBTTagCompound playerTag;
   private int dimension;
   private String levelName;
   private int saveVersion;
   private boolean raining;
   private int rainTime;
   private boolean thundering;
   private int thunderTime;
   private WorldSettings$GameType theGameType;
   private boolean mapFeaturesEnabled;
   private boolean hardcore;
   private boolean allowCommands;
   private boolean initialized;
   private GameRules theGameRules;


   protected WorldInfo() {
      this.terrainType = WorldType.DEFAULT;
      this.generatorOptions = "";
      this.theGameRules = new GameRules();
   }

   public WorldInfo(NBTTagCompound var1) {
      this.terrainType = WorldType.DEFAULT;
      this.generatorOptions = "";
      this.theGameRules = new GameRules();
      this.randomSeed = var1.getLong("RandomSeed");
      if(var1.hasKey("generatorName", 8)) {
         String var2 = var1.getString("generatorName");
         this.terrainType = WorldType.parseWorldType(var2);
         if(this.terrainType == null) {
            this.terrainType = WorldType.DEFAULT;
         } else if(this.terrainType.isVersioned()) {
            int var3 = 0;
            if(var1.hasKey("generatorVersion", 99)) {
               var3 = var1.getInteger("generatorVersion");
            }

            this.terrainType = this.terrainType.getWorldTypeForGeneratorVersion(var3);
         }

         if(var1.hasKey("generatorOptions", 8)) {
            this.generatorOptions = var1.getString("generatorOptions");
         }
      }

      this.theGameType = WorldSettings$GameType.getByID(var1.getInteger("GameType"));
      if(var1.hasKey("MapFeatures", 99)) {
         this.mapFeaturesEnabled = var1.getBoolean("MapFeatures");
      } else {
         this.mapFeaturesEnabled = true;
      }

      this.spawnX = var1.getInteger("SpawnX");
      this.spawnY = var1.getInteger("SpawnY");
      this.spawnZ = var1.getInteger("SpawnZ");
      this.totalTime = var1.getLong("Time");
      if(var1.hasKey("DayTime", 99)) {
         this.worldTime = var1.getLong("DayTime");
      } else {
         this.worldTime = this.totalTime;
      }

      this.lastTimePlayed = var1.getLong("LastPlayed");
      this.sizeOnDisk = var1.getLong("SizeOnDisk");
      this.levelName = var1.getString("LevelName");
      this.saveVersion = var1.getInteger("version");
      this.rainTime = var1.getInteger("rainTime");
      this.raining = var1.getBoolean("raining");
      this.thunderTime = var1.getInteger("thunderTime");
      this.thundering = var1.getBoolean("thundering");
      this.hardcore = var1.getBoolean("hardcore");
      if(var1.hasKey("initialized", 99)) {
         this.initialized = var1.getBoolean("initialized");
      } else {
         this.initialized = true;
      }

      if(var1.hasKey("allowCommands", 99)) {
         this.allowCommands = var1.getBoolean("allowCommands");
      } else {
         this.allowCommands = this.theGameType == WorldSettings$GameType.CREATIVE;
      }

      if(var1.hasKey("Player", 10)) {
         this.playerTag = var1.getCompoundTag("Player");
         this.dimension = this.playerTag.getInteger("Dimension");
      }

      if(var1.hasKey("GameRules", 10)) {
         this.theGameRules.readGameRulesFromNBT(var1.getCompoundTag("GameRules"));
      }

   }

   public WorldInfo(WorldSettings var1, String var2) {
      this.terrainType = WorldType.DEFAULT;
      this.generatorOptions = "";
      this.theGameRules = new GameRules();
      this.randomSeed = var1.getSeed();
      this.theGameType = var1.getGameType();
      this.mapFeaturesEnabled = var1.isMapFeaturesEnabled();
      this.levelName = var2;
      this.hardcore = var1.getHardcoreEnabled();
      this.terrainType = var1.getTerrainType();
      this.generatorOptions = var1.func_82749_j();
      this.allowCommands = var1.areCommandsAllowed();
      this.initialized = false;
   }

   public WorldInfo(WorldInfo var1) {
      this.terrainType = WorldType.DEFAULT;
      this.generatorOptions = "";
      this.theGameRules = new GameRules();
      this.randomSeed = var1.randomSeed;
      this.terrainType = var1.terrainType;
      this.generatorOptions = var1.generatorOptions;
      this.theGameType = var1.theGameType;
      this.mapFeaturesEnabled = var1.mapFeaturesEnabled;
      this.spawnX = var1.spawnX;
      this.spawnY = var1.spawnY;
      this.spawnZ = var1.spawnZ;
      this.totalTime = var1.totalTime;
      this.worldTime = var1.worldTime;
      this.lastTimePlayed = var1.lastTimePlayed;
      this.sizeOnDisk = var1.sizeOnDisk;
      this.playerTag = var1.playerTag;
      this.dimension = var1.dimension;
      this.levelName = var1.levelName;
      this.saveVersion = var1.saveVersion;
      this.rainTime = var1.rainTime;
      this.raining = var1.raining;
      this.thunderTime = var1.thunderTime;
      this.thundering = var1.thundering;
      this.hardcore = var1.hardcore;
      this.allowCommands = var1.allowCommands;
      this.initialized = var1.initialized;
      this.theGameRules = var1.theGameRules;
   }

   public NBTTagCompound getNBTTagCompound() {
      NBTTagCompound var1 = new NBTTagCompound();
      this.updateTagCompound(var1, this.playerTag);
      return var1;
   }

   public NBTTagCompound cloneNBTCompound(NBTTagCompound var1) {
      NBTTagCompound var2 = new NBTTagCompound();
      this.updateTagCompound(var2, var1);
      return var2;
   }

   private void updateTagCompound(NBTTagCompound var1, NBTTagCompound var2) {
      var1.setLong("RandomSeed", this.randomSeed);
      var1.setString("generatorName", this.terrainType.getWorldTypeName());
      var1.setInteger("generatorVersion", this.terrainType.getGeneratorVersion());
      var1.setString("generatorOptions", this.generatorOptions);
      var1.setInteger("GameType", this.theGameType.getID());
      var1.setBoolean("MapFeatures", this.mapFeaturesEnabled);
      var1.setInteger("SpawnX", this.spawnX);
      var1.setInteger("SpawnY", this.spawnY);
      var1.setInteger("SpawnZ", this.spawnZ);
      var1.setLong("Time", this.totalTime);
      var1.setLong("DayTime", this.worldTime);
      var1.setLong("SizeOnDisk", this.sizeOnDisk);
      var1.setLong("LastPlayed", MinecraftServer.getSystemTimeMillis());
      var1.setString("LevelName", this.levelName);
      var1.setInteger("version", this.saveVersion);
      var1.setInteger("rainTime", this.rainTime);
      var1.setBoolean("raining", this.raining);
      var1.setInteger("thunderTime", this.thunderTime);
      var1.setBoolean("thundering", this.thundering);
      var1.setBoolean("hardcore", this.hardcore);
      var1.setBoolean("allowCommands", this.allowCommands);
      var1.setBoolean("initialized", this.initialized);
      var1.setTag("GameRules", this.theGameRules.writeGameRulesToNBT());
      if(var2 != null) {
         var1.setTag("Player", var2);
      }

   }

   public long getSeed() {
      return this.randomSeed;
   }

   public int getSpawnX() {
      return this.spawnX;
   }

   public int getSpawnY() {
      return this.spawnY;
   }

   public int getSpawnZ() {
      return this.spawnZ;
   }

   public long getWorldTotalTime() {
      return this.totalTime;
   }

   public long getWorldTime() {
      return this.worldTime;
   }

   public long getSizeOnDisk() {
      return this.sizeOnDisk;
   }

   public NBTTagCompound getPlayerNBTTagCompound() {
      return this.playerTag;
   }

   public int getVanillaDimension() {
      return this.dimension;
   }

   public void setSpawnX(int var1) {
      this.spawnX = var1;
   }

   public void setSpawnY(int var1) {
      this.spawnY = var1;
   }

   public void setSpawnZ(int var1) {
      this.spawnZ = var1;
   }

   public void incrementTotalWorldTime(long var1) {
      this.totalTime = var1;
   }

   public void setWorldTime(long var1) {
      this.worldTime = var1;
   }

   public void setSpawnPosition(int var1, int var2, int var3) {
      this.spawnX = var1;
      this.spawnY = var2;
      this.spawnZ = var3;
   }

   public String getWorldName() {
      return this.levelName;
   }

   public void setWorldName(String var1) {
      this.levelName = var1;
   }

   public int getSaveVersion() {
      return this.saveVersion;
   }

   public void setSaveVersion(int var1) {
      this.saveVersion = var1;
   }

   public long getLastTimePlayed() {
      return this.lastTimePlayed;
   }

   public boolean isThundering() {
      return this.thundering;
   }

   public void setThundering(boolean var1) {
      this.thundering = var1;
   }

   public int getThunderTime() {
      return this.thunderTime;
   }

   public void setThunderTime(int var1) {
      this.thunderTime = var1;
   }

   public boolean isRaining() {
      return this.raining;
   }

   public void setRaining(boolean var1) {
      this.raining = var1;
   }

   public int getRainTime() {
      return this.rainTime;
   }

   public void setRainTime(int var1) {
      this.rainTime = var1;
   }

   public WorldSettings$GameType getGameType() {
      return this.theGameType;
   }

   public boolean isMapFeaturesEnabled() {
      return this.mapFeaturesEnabled;
   }

   public void setGameType(WorldSettings$GameType var1) {
      this.theGameType = var1;
   }

   public boolean isHardcoreModeEnabled() {
      return this.hardcore;
   }

   public WorldType getTerrainType() {
      return this.terrainType;
   }

   public void setTerrainType(WorldType var1) {
      this.terrainType = var1;
   }

   public String getGeneratorOptions() {
      return this.generatorOptions;
   }

   public boolean areCommandsAllowed() {
      return this.allowCommands;
   }

   public boolean isInitialized() {
      return this.initialized;
   }

   public void setServerInitialized(boolean var1) {
      this.initialized = var1;
   }

   public GameRules getGameRulesInstance() {
      return this.theGameRules;
   }

   public void addToCrashReport(CrashReportCategory var1) {
      var1.addCrashSectionCallable("Level seed", new WorldInfo$1(this));
      var1.addCrashSectionCallable("Level generator", new WorldInfo$2(this));
      var1.addCrashSectionCallable("Level generator options", new WorldInfo$3(this));
      var1.addCrashSectionCallable("Level spawn location", new WorldInfo$4(this));
      var1.addCrashSectionCallable("Level time", new WorldInfo$5(this));
      var1.addCrashSectionCallable("Level dimension", new WorldInfo$6(this));
      var1.addCrashSectionCallable("Level storage version", new WorldInfo$7(this));
      var1.addCrashSectionCallable("Level weather", new WorldInfo$8(this));
      var1.addCrashSectionCallable("Level game mode", new WorldInfo$9(this));
   }

   // $FF: synthetic method
   static WorldType access$000(WorldInfo var0) {
      return var0.terrainType;
   }

   // $FF: synthetic method
   static boolean access$100(WorldInfo var0) {
      return var0.mapFeaturesEnabled;
   }

   // $FF: synthetic method
   static String access$200(WorldInfo var0) {
      return var0.generatorOptions;
   }

   // $FF: synthetic method
   static int access$300(WorldInfo var0) {
      return var0.spawnX;
   }

   // $FF: synthetic method
   static int access$400(WorldInfo var0) {
      return var0.spawnY;
   }

   // $FF: synthetic method
   static int access$500(WorldInfo var0) {
      return var0.spawnZ;
   }

   // $FF: synthetic method
   static long access$600(WorldInfo var0) {
      return var0.totalTime;
   }

   // $FF: synthetic method
   static long access$700(WorldInfo var0) {
      return var0.worldTime;
   }

   // $FF: synthetic method
   static int access$800(WorldInfo var0) {
      return var0.dimension;
   }

   // $FF: synthetic method
   static int access$900(WorldInfo var0) {
      return var0.saveVersion;
   }

   // $FF: synthetic method
   static int access$1000(WorldInfo var0) {
      return var0.rainTime;
   }

   // $FF: synthetic method
   static boolean access$1100(WorldInfo var0) {
      return var0.raining;
   }

   // $FF: synthetic method
   static int access$1200(WorldInfo var0) {
      return var0.thunderTime;
   }

   // $FF: synthetic method
   static boolean access$1300(WorldInfo var0) {
      return var0.thundering;
   }

   // $FF: synthetic method
   static WorldSettings$GameType access$1400(WorldInfo var0) {
      return var0.theGameType;
   }

   // $FF: synthetic method
   static boolean access$1500(WorldInfo var0) {
      return var0.hardcore;
   }

   // $FF: synthetic method
   static boolean access$1600(WorldInfo var0) {
      return var0.allowCommands;
   }
}
