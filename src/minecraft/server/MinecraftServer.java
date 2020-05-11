package net.minecraft.server;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.Proxy;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.imageio.ImageIO;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetworkSystem;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.ServerStatusResponse$MinecraftProtocolVersionIdentifier;
import net.minecraft.network.ServerStatusResponse$PlayerCountData;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.profiler.IPlayerUsage;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer$1;
import net.minecraft.server.MinecraftServer$3;
import net.minecraft.server.MinecraftServer$4;
import net.minecraft.server.MinecraftServer$5;
import net.minecraft.server.MinecraftServer$6;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldServerMulti;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MinecraftServer implements ICommandSender, Runnable, IPlayerUsage {

   private static final Logger logger = LogManager.getLogger();
   public static final File field_152367_a = new File("usercache.json");
   private static MinecraftServer mcServer;
   private final ISaveFormat anvilConverterForAnvilFile;
   private final PlayerUsageSnooper usageSnooper = new PlayerUsageSnooper("server", this, getSystemTimeMillis());
   private final File anvilFile;
   private final List tickables = new ArrayList();
   private final ICommandManager commandManager;
   public final Profiler theProfiler = new Profiler();
   private final NetworkSystem field_147144_o;
   private final ServerStatusResponse field_147147_p = new ServerStatusResponse();
   private final Random field_147146_q = new Random();
   private int serverPort = -1;
   public WorldServer[] worldServers;
   private ServerConfigurationManager serverConfigManager;
   private boolean serverRunning = true;
   private boolean serverStopped;
   private int tickCounter;
   protected final Proxy serverProxy;
   public String currentTask;
   public int percentDone;
   private boolean onlineMode;
   private boolean canSpawnAnimals;
   private boolean canSpawnNPCs;
   private boolean pvpEnabled;
   private boolean allowFlight;
   private String motd;
   private int buildLimit;
   private int field_143008_E = 0;
   public final long[] tickTimeArray = new long[100];
   public long[][] timeOfLastDimensionTick;
   private KeyPair serverKeyPair;
   private String serverOwner;
   private String folderName;
   private String worldName;
   private boolean isDemo;
   private boolean enableBonusChest;
   private boolean worldIsBeingDeleted;
   private String field_147141_M = "";
   private boolean serverIsRunning;
   private long timeOfLastWarning;
   private String userMessage;
   private boolean startProfiling;
   private boolean isGamemodeForced;
   private final YggdrasilAuthenticationService field_152364_T;
   private final MinecraftSessionService field_147143_S;
   private long field_147142_T = 0L;
   private final GameProfileRepository field_152365_W;
   private final PlayerProfileCache field_152366_X;


   public MinecraftServer(File var1, Proxy var2) {
      this.field_152366_X = new PlayerProfileCache(this, field_152367_a);
      mcServer = this;
      this.serverProxy = var2;
      this.anvilFile = var1;
      this.field_147144_o = new NetworkSystem(this);
      this.commandManager = new ServerCommandManager();
      this.anvilConverterForAnvilFile = new AnvilSaveConverter(var1);
      this.field_152364_T = new YggdrasilAuthenticationService(var2, UUID.randomUUID().toString());
      this.field_147143_S = this.field_152364_T.createMinecraftSessionService();
      this.field_152365_W = this.field_152364_T.createProfileRepository();
   }

   protected abstract boolean startServer();

   protected void convertMapIfNeeded(String var1) {
      if(this.getActiveAnvilConverter().isOldMapFormat(var1)) {
         logger.info("Converting map!");
         this.setUserMessage("menu.convertingLevel");
         this.getActiveAnvilConverter().convertMapFormat(var1, new MinecraftServer$1(this));
      }

   }

   protected synchronized void setUserMessage(String var1) {
      this.userMessage = var1;
   }

   public synchronized String getUserMessage() {
      return this.userMessage;
   }

   protected void loadAllWorlds(String var1, String var2, long var3, WorldType var5, String var6) {
      this.convertMapIfNeeded(var1);
      this.setUserMessage("menu.loadingLevel");
      this.worldServers = new WorldServer[3];
      this.timeOfLastDimensionTick = new long[this.worldServers.length][100];
      ISaveHandler var7 = this.anvilConverterForAnvilFile.getSaveLoader(var1, true);
      WorldInfo var9 = var7.loadWorldInfo();
      WorldSettings var8;
      if(var9 == null) {
         var8 = new WorldSettings(var3, this.getGameType(), this.canStructuresSpawn(), this.isHardcore(), var5);
         var8.func_82750_a(var6);
      } else {
         var8 = new WorldSettings(var9);
      }

      if(this.enableBonusChest) {
         var8.enableBonusChest();
      }

      for(int var10 = 0; var10 < this.worldServers.length; ++var10) {
         byte var11 = 0;
         if(var10 == 1) {
            var11 = -1;
         }

         if(var10 == 2) {
            var11 = 1;
         }

         if(var10 == 0) {
            if(this.isDemo()) {
               this.worldServers[var10] = new DemoWorldServer(this, var7, var2, var11, this.theProfiler);
            } else {
               this.worldServers[var10] = new WorldServer(this, var7, var2, var11, var8, this.theProfiler);
            }
         } else {
            this.worldServers[var10] = new WorldServerMulti(this, var7, var2, var11, var8, this.worldServers[0], this.theProfiler);
         }

         this.worldServers[var10].addWorldAccess(new WorldManager(this, this.worldServers[var10]));
         if(!this.isSinglePlayer()) {
            this.worldServers[var10].getWorldInfo().setGameType(this.getGameType());
         }

         this.serverConfigManager.setPlayerManager(this.worldServers);
      }

      this.func_147139_a(this.func_147135_j());
      this.initialWorldChunkLoad();
   }

   protected void initialWorldChunkLoad() {
      boolean var1 = true;
      boolean var2 = true;
      boolean var3 = true;
      boolean var4 = true;
      int var5 = 0;
      this.setUserMessage("menu.generatingTerrain");
      byte var6 = 0;
      logger.info("Preparing start region for level " + var6);
      WorldServer var7 = this.worldServers[var6];
      ChunkCoordinates var8 = var7.getSpawnPoint();
      long var9 = getSystemTimeMillis();

      for(int var11 = -192; var11 <= 192 && this.isServerRunning(); var11 += 16) {
         for(int var12 = -192; var12 <= 192 && this.isServerRunning(); var12 += 16) {
            long var13 = getSystemTimeMillis();
            if(var13 - var9 > 1000L) {
               this.outputPercentRemaining("Preparing spawn area", var5 * 100 / 625);
               var9 = var13;
            }

            ++var5;
            var7.theChunkProviderServer.loadChunk(var8.posX + var11 >> 4, var8.posZ + var12 >> 4);
         }
      }

      this.clearCurrentTask();
   }

   public abstract boolean canStructuresSpawn();

   public abstract WorldSettings$GameType getGameType();

   public abstract EnumDifficulty func_147135_j();

   public abstract boolean isHardcore();

   public abstract int getOpPermissionLevel();

   public abstract boolean func_152363_m();

   protected void outputPercentRemaining(String var1, int var2) {
      this.currentTask = var1;
      this.percentDone = var2;
      logger.info(var1 + ": " + var2 + "%");
   }

   protected void clearCurrentTask() {
      this.currentTask = null;
      this.percentDone = 0;
   }

   protected void saveAllWorlds(boolean var1) {
      if(!this.worldIsBeingDeleted) {
         WorldServer[] var2 = this.worldServers;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            WorldServer var5 = var2[var4];
            if(var5 != null) {
               if(!var1) {
                  logger.info("Saving chunks for level \'" + var5.getWorldInfo().getWorldName() + "\'/" + var5.provider.getDimensionName());
               }

               try {
                  var5.saveAllChunks(true, (IProgressUpdate)null);
               } catch (MinecraftException var7) {
                  logger.warn(var7.getMessage());
               }
            }
         }

      }
   }

   public void stopServer() {
      if(!this.worldIsBeingDeleted) {
         logger.info("Stopping server");
         if(this.func_147137_ag() != null) {
            this.func_147137_ag().terminateEndpoints();
         }

         if(this.serverConfigManager != null) {
            logger.info("Saving players");
            this.serverConfigManager.saveAllPlayerData();
            this.serverConfigManager.removeAllPlayers();
         }

         if(this.worldServers != null) {
            logger.info("Saving worlds");
            this.saveAllWorlds(false);

            for(int var1 = 0; var1 < this.worldServers.length; ++var1) {
               WorldServer var2 = this.worldServers[var1];
               var2.flush();
            }
         }

         if(this.usageSnooper.isSnooperRunning()) {
            this.usageSnooper.stopSnooper();
         }

      }
   }

   public boolean isServerRunning() {
      return this.serverRunning;
   }

   public void initiateShutdown() {
      this.serverRunning = false;
   }

   public void run() {
      try {
         if(this.startServer()) {
            long var1 = getSystemTimeMillis();
            long var50 = 0L;
            this.field_147147_p.func_151315_a(new ChatComponentText(this.motd));
            this.field_147147_p.func_151321_a(new ServerStatusResponse$MinecraftProtocolVersionIdentifier("1.7.10", 5));
            this.func_147138_a(this.field_147147_p);

            while(this.serverRunning) {
               long var5 = getSystemTimeMillis();
               long var7 = var5 - var1;
               if(var7 > 2000L && var1 - this.timeOfLastWarning >= 15000L) {
                  logger.warn("Can\'t keep up! Did the system time change, or is the server overloaded? Running {}ms behind, skipping {} tick(s)", new Object[]{Long.valueOf(var7), Long.valueOf(var7 / 50L)});
                  var7 = 2000L;
                  this.timeOfLastWarning = var1;
               }

               if(var7 < 0L) {
                  logger.warn("Time ran backwards! Did the system time change?");
                  var7 = 0L;
               }

               var50 += var7;
               var1 = var5;
               if(this.worldServers[0].areAllPlayersAsleep()) {
                  this.tick();
                  var50 = 0L;
               } else {
                  while(var50 > 50L) {
                     var50 -= 50L;
                     this.tick();
                  }
               }

               Thread.sleep(Math.max(1L, 50L - var50));
               this.serverIsRunning = true;
            }
         } else {
            this.finalTick((CrashReport)null);
         }
      } catch (Throwable var48) {
         logger.error("Encountered an unexpected exception", var48);
         CrashReport var2 = null;
         if(var48 instanceof ReportedException) {
            var2 = this.addServerInfoToCrashReport(((ReportedException)var48).getCrashReport());
         } else {
            var2 = this.addServerInfoToCrashReport(new CrashReport("Exception in server tick loop", var48));
         }

         File var3 = new File(new File(this.getDataDirectory(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");
         if(var2.saveToFile(var3)) {
            logger.error("This crash report has been saved to: " + var3.getAbsolutePath());
         } else {
            logger.error("We were unable to save this crash report to disk.");
         }

         this.finalTick(var2);
      } finally {
         try {
            this.stopServer();
            this.serverStopped = true;
         } catch (Throwable var46) {
            logger.error("Exception stopping the server", var46);
         } finally {
            this.systemExitNow();
         }

      }

   }

   private void func_147138_a(ServerStatusResponse var1) {
      File var2 = this.getFile("server-icon.png");
      if(var2.isFile()) {
         ByteBuf var3 = Unpooled.buffer();

         try {
            BufferedImage var4 = ImageIO.read(var2);
            Validate.validState(var4.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
            Validate.validState(var4.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
            ImageIO.write(var4, "PNG", new ByteBufOutputStream(var3));
            ByteBuf var5 = Base64.encode(var3);
            var1.func_151320_a("data:image/png;base64," + var5.toString(Charsets.UTF_8));
         } catch (Exception var9) {
            logger.error("Couldn\'t load server icon", var9);
         } finally {
            var3.release();
         }
      }

   }

   protected File getDataDirectory() {
      return new File(".");
   }

   protected void finalTick(CrashReport var1) {}

   protected void systemExitNow() {}

   public void tick() {
      long var1 = System.nanoTime();
      ++this.tickCounter;
      if(this.startProfiling) {
         this.startProfiling = false;
         this.theProfiler.profilingEnabled = true;
         this.theProfiler.clearProfiling();
      }

      this.theProfiler.startSection("root");
      this.updateTimeLightAndEntities();
      if(var1 - this.field_147142_T >= 5000000000L) {
         this.field_147142_T = var1;
         this.field_147147_p.func_151319_a(new ServerStatusResponse$PlayerCountData(this.getMaxPlayers(), this.getCurrentPlayerCount()));
         GameProfile[] var3 = new GameProfile[Math.min(this.getCurrentPlayerCount(), 12)];
         int var4 = MathHelper.getRandomIntegerInRange(this.field_147146_q, 0, this.getCurrentPlayerCount() - var3.length);

         for(int var5 = 0; var5 < var3.length; ++var5) {
            var3[var5] = ((EntityPlayerMP)this.serverConfigManager.playerEntityList.get(var4 + var5)).getGameProfile();
         }

         Collections.shuffle(Arrays.asList(var3));
         this.field_147147_p.func_151318_b().func_151330_a(var3);
      }

      if(this.tickCounter % 900 == 0) {
         this.theProfiler.startSection("save");
         this.serverConfigManager.saveAllPlayerData();
         this.saveAllWorlds(true);
         this.theProfiler.endSection();
      }

      this.theProfiler.startSection("tallying");
      this.tickTimeArray[this.tickCounter % 100] = System.nanoTime() - var1;
      this.theProfiler.endSection();
      this.theProfiler.startSection("snooper");
      if(!this.usageSnooper.isSnooperRunning() && this.tickCounter > 100) {
         this.usageSnooper.startSnooper();
      }

      if(this.tickCounter % 6000 == 0) {
         this.usageSnooper.addMemoryStatsToSnooper();
      }

      this.theProfiler.endSection();
      this.theProfiler.endSection();
   }

   public void updateTimeLightAndEntities() {
      this.theProfiler.startSection("levels");

      int var1;
      for(var1 = 0; var1 < this.worldServers.length; ++var1) {
         long var2 = System.nanoTime();
         if(var1 == 0 || this.getAllowNether()) {
            WorldServer var4 = this.worldServers[var1];
            this.theProfiler.startSection(var4.getWorldInfo().getWorldName());
            this.theProfiler.startSection("pools");
            this.theProfiler.endSection();
            if(this.tickCounter % 20 == 0) {
               this.theProfiler.startSection("timeSync");
               this.serverConfigManager.sendPacketToAllPlayersInDimension(new S03PacketTimeUpdate(var4.getTotalWorldTime(), var4.getWorldTime(), var4.getGameRules().getGameRuleBooleanValue("doDaylightCycle")), var4.provider.dimensionId);
               this.theProfiler.endSection();
            }

            this.theProfiler.startSection("tick");

            CrashReport var6;
            try {
               var4.tick();
            } catch (Throwable var8) {
               var6 = CrashReport.makeCrashReport(var8, "Exception ticking world");
               var4.addWorldInfoToCrashReport(var6);
               throw new ReportedException(var6);
            }

            try {
               var4.updateEntities();
            } catch (Throwable var7) {
               var6 = CrashReport.makeCrashReport(var7, "Exception ticking world entities");
               var4.addWorldInfoToCrashReport(var6);
               throw new ReportedException(var6);
            }

            this.theProfiler.endSection();
            this.theProfiler.startSection("tracker");
            var4.getEntityTracker().updateTrackedEntities();
            this.theProfiler.endSection();
            this.theProfiler.endSection();
         }

         this.timeOfLastDimensionTick[var1][this.tickCounter % 100] = System.nanoTime() - var2;
      }

      this.theProfiler.endStartSection("connection");
      this.func_147137_ag().networkTick();
      this.theProfiler.endStartSection("players");
      this.serverConfigManager.sendPlayerInfoToAllPlayers();
      this.theProfiler.endStartSection("tickables");

      for(var1 = 0; var1 < this.tickables.size(); ++var1) {
         ((IUpdatePlayerListBox)this.tickables.get(var1)).update();
      }

      this.theProfiler.endSection();
   }

   public boolean getAllowNether() {
      return true;
   }

   public void startServerThread() {
      (new MinecraftServer$3(this, "Server thread")).start();
   }

   public File getFile(String var1) {
      return new File(this.getDataDirectory(), var1);
   }

   public void logWarning(String var1) {
      logger.warn(var1);
   }

   public WorldServer worldServerForDimension(int var1) {
      return var1 == -1?this.worldServers[1]:(var1 == 1?this.worldServers[2]:this.worldServers[0]);
   }

   public String getMinecraftVersion() {
      return "1.7.10";
   }

   public int getCurrentPlayerCount() {
      return this.serverConfigManager.getCurrentPlayerCount();
   }

   public int getMaxPlayers() {
      return this.serverConfigManager.getMaxPlayers();
   }

   public String[] getAllUsernames() {
      return this.serverConfigManager.getAllUsernames();
   }

   public GameProfile[] func_152357_F() {
      return this.serverConfigManager.func_152600_g();
   }

   public String getServerModName() {
      return "vanilla";
   }

   public CrashReport addServerInfoToCrashReport(CrashReport var1) {
      var1.getCategory().addCrashSectionCallable("Profiler Position", new MinecraftServer$4(this));
      if(this.worldServers != null && this.worldServers.length > 0 && this.worldServers[0] != null) {
         var1.getCategory().addCrashSectionCallable("Vec3 Pool Size", new MinecraftServer$5(this));
      }

      if(this.serverConfigManager != null) {
         var1.getCategory().addCrashSectionCallable("Player Count", new MinecraftServer$6(this));
      }

      return var1;
   }

   public List getPossibleCompletions(ICommandSender var1, String var2) {
      ArrayList var3 = new ArrayList();
      if(var2.startsWith("/")) {
         var2 = var2.substring(1);
         boolean var10 = !var2.contains(" ");
         List var11 = this.commandManager.getPossibleCommands(var1, var2);
         if(var11 != null) {
            Iterator var12 = var11.iterator();

            while(var12.hasNext()) {
               String var13 = (String)var12.next();
               if(var10) {
                  var3.add("/" + var13);
               } else {
                  var3.add(var13);
               }
            }
         }

         return var3;
      } else {
         String[] var4 = var2.split(" ", -1);
         String var5 = var4[var4.length - 1];
         String[] var6 = this.serverConfigManager.getAllUsernames();
         int var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            String var9 = var6[var8];
            if(CommandBase.doesStringStartWith(var5, var9)) {
               var3.add(var9);
            }
         }

         return var3;
      }
   }

   public static MinecraftServer getServer() {
      return mcServer;
   }

   public String getCommandSenderName() {
      return "Server";
   }

   public void addChatMessage(IChatComponent var1) {
      logger.info(var1.getUnformattedText());
   }

   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return true;
   }

   public ICommandManager getCommandManager() {
      return this.commandManager;
   }

   public KeyPair getKeyPair() {
      return this.serverKeyPair;
   }

   public String getServerOwner() {
      return this.serverOwner;
   }

   public void setServerOwner(String var1) {
      this.serverOwner = var1;
   }

   public boolean isSinglePlayer() {
      return this.serverOwner != null;
   }

   public String getFolderName() {
      return this.folderName;
   }

   public void setFolderName(String var1) {
      this.folderName = var1;
   }

   public void setWorldName(String var1) {
      this.worldName = var1;
   }

   public String getWorldName() {
      return this.worldName;
   }

   public void setKeyPair(KeyPair var1) {
      this.serverKeyPair = var1;
   }

   public void func_147139_a(EnumDifficulty var1) {
      for(int var2 = 0; var2 < this.worldServers.length; ++var2) {
         WorldServer var3 = this.worldServers[var2];
         if(var3 != null) {
            if(var3.getWorldInfo().isHardcoreModeEnabled()) {
               var3.difficultySetting = EnumDifficulty.HARD;
               var3.setAllowedSpawnTypes(true, true);
            } else if(this.isSinglePlayer()) {
               var3.difficultySetting = var1;
               var3.setAllowedSpawnTypes(var3.difficultySetting != EnumDifficulty.PEACEFUL, true);
            } else {
               var3.difficultySetting = var1;
               var3.setAllowedSpawnTypes(this.allowSpawnMonsters(), this.canSpawnAnimals);
            }
         }
      }

   }

   protected boolean allowSpawnMonsters() {
      return true;
   }

   public boolean isDemo() {
      return this.isDemo;
   }

   public void setDemo(boolean var1) {
      this.isDemo = var1;
   }

   public void canCreateBonusChest(boolean var1) {
      this.enableBonusChest = var1;
   }

   public ISaveFormat getActiveAnvilConverter() {
      return this.anvilConverterForAnvilFile;
   }

   public void deleteWorldAndStopServer() {
      this.worldIsBeingDeleted = true;
      this.getActiveAnvilConverter().flushCache();

      for(int var1 = 0; var1 < this.worldServers.length; ++var1) {
         WorldServer var2 = this.worldServers[var1];
         if(var2 != null) {
            var2.flush();
         }
      }

      this.getActiveAnvilConverter().deleteWorldDirectory(this.worldServers[0].getSaveHandler().getWorldDirectoryName());
      this.initiateShutdown();
   }

   public String getTexturePack() {
      return this.field_147141_M;
   }

   public void addServerStatsToSnooper(PlayerUsageSnooper var1) {
      var1.func_152768_a("whitelist_enabled", Boolean.valueOf(false));
      var1.func_152768_a("whitelist_count", Integer.valueOf(0));
      var1.func_152768_a("players_current", Integer.valueOf(this.getCurrentPlayerCount()));
      var1.func_152768_a("players_max", Integer.valueOf(this.getMaxPlayers()));
      var1.func_152768_a("players_seen", Integer.valueOf(this.serverConfigManager.getAvailablePlayerDat().length));
      var1.func_152768_a("uses_auth", Boolean.valueOf(this.onlineMode));
      var1.func_152768_a("gui_state", this.getGuiEnabled()?"enabled":"disabled");
      var1.func_152768_a("run_time", Long.valueOf((getSystemTimeMillis() - var1.getMinecraftStartTimeMillis()) / 60L * 1000L));
      var1.func_152768_a("avg_tick_ms", Integer.valueOf((int)(MathHelper.average(this.tickTimeArray) * 1.0E-6D)));
      int var2 = 0;

      for(int var3 = 0; var3 < this.worldServers.length; ++var3) {
         if(this.worldServers[var3] != null) {
            WorldServer var4 = this.worldServers[var3];
            WorldInfo var5 = var4.getWorldInfo();
            var1.func_152768_a("world[" + var2 + "][dimension]", Integer.valueOf(var4.provider.dimensionId));
            var1.func_152768_a("world[" + var2 + "][mode]", var5.getGameType());
            var1.func_152768_a("world[" + var2 + "][difficulty]", var4.difficultySetting);
            var1.func_152768_a("world[" + var2 + "][hardcore]", Boolean.valueOf(var5.isHardcoreModeEnabled()));
            var1.func_152768_a("world[" + var2 + "][generator_name]", var5.getTerrainType().getWorldTypeName());
            var1.func_152768_a("world[" + var2 + "][generator_version]", Integer.valueOf(var5.getTerrainType().getGeneratorVersion()));
            var1.func_152768_a("world[" + var2 + "][height]", Integer.valueOf(this.buildLimit));
            var1.func_152768_a("world[" + var2 + "][chunks_loaded]", Integer.valueOf(var4.getChunkProvider().getLoadedChunkCount()));
            ++var2;
         }
      }

      var1.func_152768_a("worlds", Integer.valueOf(var2));
   }

   public void addServerTypeToSnooper(PlayerUsageSnooper var1) {
      var1.func_152767_b("singleplayer", Boolean.valueOf(this.isSinglePlayer()));
      var1.func_152767_b("server_brand", this.getServerModName());
      var1.func_152767_b("gui_supported", GraphicsEnvironment.isHeadless()?"headless":"supported");
      var1.func_152767_b("dedicated", Boolean.valueOf(this.isDedicatedServer()));
   }

   public boolean isSnooperEnabled() {
      return true;
   }

   public abstract boolean isDedicatedServer();

   public boolean isServerInOnlineMode() {
      return this.onlineMode;
   }

   public void setOnlineMode(boolean var1) {
      this.onlineMode = var1;
   }

   public boolean getCanSpawnAnimals() {
      return this.canSpawnAnimals;
   }

   public void setCanSpawnAnimals(boolean var1) {
      this.canSpawnAnimals = var1;
   }

   public boolean getCanSpawnNPCs() {
      return this.canSpawnNPCs;
   }

   public void setCanSpawnNPCs(boolean var1) {
      this.canSpawnNPCs = var1;
   }

   public boolean isPVPEnabled() {
      return this.pvpEnabled;
   }

   public void setAllowPvp(boolean var1) {
      this.pvpEnabled = var1;
   }

   public boolean isFlightAllowed() {
      return this.allowFlight;
   }

   public void setAllowFlight(boolean var1) {
      this.allowFlight = var1;
   }

   public abstract boolean isCommandBlockEnabled();

   public String getMOTD() {
      return this.motd;
   }

   public void setMOTD(String var1) {
      this.motd = var1;
   }

   public int getBuildLimit() {
      return this.buildLimit;
   }

   public void setBuildLimit(int var1) {
      this.buildLimit = var1;
   }

   public ServerConfigurationManager getConfigurationManager() {
      return this.serverConfigManager;
   }

   public void func_152361_a(ServerConfigurationManager var1) {
      this.serverConfigManager = var1;
   }

   public void setGameType(WorldSettings$GameType var1) {
      for(int var2 = 0; var2 < this.worldServers.length; ++var2) {
         getServer().worldServers[var2].getWorldInfo().setGameType(var1);
      }

   }

   public NetworkSystem func_147137_ag() {
      return this.field_147144_o;
   }

   public boolean serverIsInRunLoop() {
      return this.serverIsRunning;
   }

   public boolean getGuiEnabled() {
      return false;
   }

   public abstract String shareToLAN(WorldSettings$GameType var1, boolean var2);

   public int getTickCounter() {
      return this.tickCounter;
   }

   public void enableProfiling() {
      this.startProfiling = true;
   }

   public PlayerUsageSnooper getPlayerUsageSnooper() {
      return this.usageSnooper;
   }

   public ChunkCoordinates getPlayerCoordinates() {
      return new ChunkCoordinates(0, 0, 0);
   }

   public World getEntityWorld() {
      return this.worldServers[0];
   }

   public int getSpawnProtectionSize() {
      return 16;
   }

   public boolean isBlockProtected(World var1, int var2, int var3, int var4, EntityPlayer var5) {
      return false;
   }

   public boolean getForceGamemode() {
      return this.isGamemodeForced;
   }

   public Proxy getServerProxy() {
      return this.serverProxy;
   }

   public static long getSystemTimeMillis() {
      return System.currentTimeMillis();
   }

   public int func_143007_ar() {
      return this.field_143008_E;
   }

   public void func_143006_e(int var1) {
      this.field_143008_E = var1;
   }

   public IChatComponent func_145748_c_() {
      return new ChatComponentText(this.getCommandSenderName());
   }

   public boolean func_147136_ar() {
      return true;
   }

   public MinecraftSessionService func_147130_as() {
      return this.field_147143_S;
   }

   public GameProfileRepository func_152359_aw() {
      return this.field_152365_W;
   }

   public PlayerProfileCache func_152358_ax() {
      return this.field_152366_X;
   }

   public ServerStatusResponse func_147134_at() {
      return this.field_147147_p;
   }

   public void func_147132_au() {
      this.field_147142_T = 0L;
   }

   // $FF: synthetic method
   public static Logger access$000() {
      return logger;
   }

   // $FF: synthetic method
   public static ServerConfigurationManager access$100(MinecraftServer var0) {
      return var0.serverConfigManager;
   }

}
