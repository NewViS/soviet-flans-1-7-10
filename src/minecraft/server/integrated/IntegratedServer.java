package net.minecraft.server.integrated;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import net.minecraft.crash.CrashReport;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedPlayerList;
import net.minecraft.server.integrated.IntegratedServer$1;
import net.minecraft.server.integrated.IntegratedServer$2;
import net.minecraft.util.CryptManager;
import net.minecraft.util.HttpUtil;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldServerMulti;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.WorldType;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntegratedServer extends MinecraftServer {

   private static final Logger logger = LogManager.getLogger();
   private final Minecraft mc;
   private final WorldSettings theWorldSettings;
   private boolean isGamePaused;
   private boolean isPublic;
   private ThreadLanServerPing lanServerPing;


   public IntegratedServer(Minecraft var1, String var2, String var3, WorldSettings var4) {
      super(new File(var1.mcDataDir, "saves"), var1.getProxy());
      this.setServerOwner(var1.getSession().getUsername());
      this.setFolderName(var2);
      this.setWorldName(var3);
      this.setDemo(var1.isDemo());
      this.canCreateBonusChest(var4.isBonusChestEnabled());
      this.setBuildLimit(256);
      this.func_152361_a(new IntegratedPlayerList(this));
      this.mc = var1;
      this.theWorldSettings = var4;
   }

   protected void loadAllWorlds(String var1, String var2, long var3, WorldType var5, String var6) {
      this.convertMapIfNeeded(var1);
      super.worldServers = new WorldServer[3];
      super.timeOfLastDimensionTick = new long[super.worldServers.length][100];
      ISaveHandler var7 = this.getActiveAnvilConverter().getSaveLoader(var1, true);

      for(int var8 = 0; var8 < super.worldServers.length; ++var8) {
         byte var9 = 0;
         if(var8 == 1) {
            var9 = -1;
         }

         if(var8 == 2) {
            var9 = 1;
         }

         if(var8 == 0) {
            if(this.isDemo()) {
               super.worldServers[var8] = new DemoWorldServer(this, var7, var2, var9, super.theProfiler);
            } else {
               super.worldServers[var8] = new WorldServer(this, var7, var2, var9, this.theWorldSettings, super.theProfiler);
            }
         } else {
            super.worldServers[var8] = new WorldServerMulti(this, var7, var2, var9, this.theWorldSettings, super.worldServers[0], super.theProfiler);
         }

         super.worldServers[var8].addWorldAccess(new WorldManager(this, super.worldServers[var8]));
         this.getConfigurationManager().setPlayerManager(super.worldServers);
      }

      this.func_147139_a(this.func_147135_j());
      this.initialWorldChunkLoad();
   }

   protected boolean startServer() {
      logger.info("Starting integrated minecraft server version 1.7.10");
      this.setOnlineMode(true);
      this.setCanSpawnAnimals(true);
      this.setCanSpawnNPCs(true);
      this.setAllowPvp(true);
      this.setAllowFlight(true);
      logger.info("Generating keypair");
      this.setKeyPair(CryptManager.createNewKeyPair());
      this.loadAllWorlds(this.getFolderName(), this.getWorldName(), this.theWorldSettings.getSeed(), this.theWorldSettings.getTerrainType(), this.theWorldSettings.func_82749_j());
      this.setMOTD(this.getServerOwner() + " - " + super.worldServers[0].getWorldInfo().getWorldName());
      return true;
   }

   protected void tick() {
      boolean var1 = this.isGamePaused;
      this.isGamePaused = Minecraft.getMinecraft().getNetHandler() != null && Minecraft.getMinecraft().isGamePaused();
      if(!var1 && this.isGamePaused) {
         logger.info("Saving and pausing game...");
         this.getConfigurationManager().saveAllPlayerData();
         this.saveAllWorlds(false);
      }

      if(!this.isGamePaused) {
         super.tick();
         if(this.mc.gameSettings.renderDistanceChunks != this.getConfigurationManager().getViewDistance()) {
            logger.info("Changing view distance to {}, from {}", new Object[]{Integer.valueOf(this.mc.gameSettings.renderDistanceChunks), Integer.valueOf(this.getConfigurationManager().getViewDistance())});
            this.getConfigurationManager().func_152611_a(this.mc.gameSettings.renderDistanceChunks);
         }
      }

   }

   public boolean canStructuresSpawn() {
      return false;
   }

   public WorldSettings$GameType getGameType() {
      return this.theWorldSettings.getGameType();
   }

   public EnumDifficulty func_147135_j() {
      return this.mc.gameSettings.difficulty;
   }

   public boolean isHardcore() {
      return this.theWorldSettings.getHardcoreEnabled();
   }

   public boolean func_152363_m() {
      return false;
   }

   protected File getDataDirectory() {
      return this.mc.mcDataDir;
   }

   public boolean isDedicatedServer() {
      return false;
   }

   protected void finalTick(CrashReport var1) {
      this.mc.crashed(var1);
   }

   public CrashReport addServerInfoToCrashReport(CrashReport var1) {
      var1 = super.addServerInfoToCrashReport(var1);
      var1.getCategory().addCrashSectionCallable("Type", new IntegratedServer$1(this));
      var1.getCategory().addCrashSectionCallable("Is Modded", new IntegratedServer$2(this));
      return var1;
   }

   public void addServerStatsToSnooper(PlayerUsageSnooper var1) {
      super.addServerStatsToSnooper(var1);
      var1.func_152768_a("snooper_partner", this.mc.getPlayerUsageSnooper().getUniqueID());
   }

   public boolean isSnooperEnabled() {
      return Minecraft.getMinecraft().isSnooperEnabled();
   }

   public String shareToLAN(WorldSettings$GameType var1, boolean var2) {
      try {
         int var3 = -1;

         try {
            var3 = HttpUtil.func_76181_a();
         } catch (IOException var5) {
            ;
         }

         if(var3 <= 0) {
            var3 = 25564;
         }

         this.func_147137_ag().addLanEndpoint((InetAddress)null, var3);
         logger.info("Started on " + var3);
         this.isPublic = true;
         this.lanServerPing = new ThreadLanServerPing(this.getMOTD(), var3 + "");
         this.lanServerPing.start();
         this.getConfigurationManager().func_152604_a(var1);
         this.getConfigurationManager().setCommandsAllowedForAll(var2);
         return var3 + "";
      } catch (IOException var6) {
         return null;
      }
   }

   public void stopServer() {
      super.stopServer();
      if(this.lanServerPing != null) {
         this.lanServerPing.interrupt();
         this.lanServerPing = null;
      }

   }

   public void initiateShutdown() {
      super.initiateShutdown();
      if(this.lanServerPing != null) {
         this.lanServerPing.interrupt();
         this.lanServerPing = null;
      }

   }

   public boolean getPublic() {
      return this.isPublic;
   }

   public void setGameType(WorldSettings$GameType var1) {
      this.getConfigurationManager().func_152604_a(var1);
   }

   public boolean isCommandBlockEnabled() {
      return true;
   }

   public int getOpPermissionLevel() {
      return 4;
   }

}
