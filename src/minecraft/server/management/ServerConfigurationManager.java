package net.minecraft.server.management;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.BanList;
import net.minecraft.server.management.IPBanEntry;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.server.management.PlayerPositionComparator;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.UserListBans;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.server.management.UserListOps;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraft.server.management.UserListWhitelist;
import net.minecraft.server.management.UserListWhitelistEntry;
import net.minecraft.stats.StatList;
import net.minecraft.stats.StatisticsFile;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.demo.DemoWorldManager;
import net.minecraft.world.storage.IPlayerFileData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ServerConfigurationManager {

   public static final File field_152613_a = new File("banned-players.json");
   public static final File field_152614_b = new File("banned-ips.json");
   public static final File field_152615_c = new File("ops.json");
   public static final File field_152616_d = new File("whitelist.json");
   private static final Logger logger = LogManager.getLogger();
   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
   private final MinecraftServer mcServer;
   public final List playerEntityList = new ArrayList();
   private final UserListBans bannedPlayers;
   private final BanList bannedIPs;
   private final UserListOps ops;
   private final UserListWhitelist whiteListedPlayers;
   private final Map field_148547_k;
   private IPlayerFileData playerNBTManagerObj;
   private boolean whiteListEnforced;
   protected int maxPlayers;
   private int viewDistance;
   private WorldSettings$GameType gameType;
   private boolean commandsAllowedForAll;
   private int playerPingIndex;


   public ServerConfigurationManager(MinecraftServer var1) {
      this.bannedPlayers = new UserListBans(field_152613_a);
      this.bannedIPs = new BanList(field_152614_b);
      this.ops = new UserListOps(field_152615_c);
      this.whiteListedPlayers = new UserListWhitelist(field_152616_d);
      this.field_148547_k = Maps.newHashMap();
      this.mcServer = var1;
      this.bannedPlayers.func_152686_a(false);
      this.bannedIPs.func_152686_a(false);
      this.maxPlayers = 8;
   }

   public void initializeConnectionToPlayer(NetworkManager var1, EntityPlayerMP var2) {
      GameProfile var3 = var2.getGameProfile();
      PlayerProfileCache var4 = this.mcServer.func_152358_ax();
      GameProfile var5 = var4.func_152652_a(var3.getId());
      String var6 = var5 == null?var3.getName():var5.getName();
      var4.func_152649_a(var3);
      NBTTagCompound var7 = this.readPlayerDataFromFile(var2);
      var2.setWorld(this.mcServer.worldServerForDimension(var2.dimension));
      var2.theItemInWorldManager.setWorld((WorldServer)var2.worldObj);
      String var8 = "local";
      if(var1.getSocketAddress() != null) {
         var8 = var1.getSocketAddress().toString();
      }

      logger.info(var2.getCommandSenderName() + "[" + var8 + "] logged in with entity id " + var2.getEntityId() + " at (" + var2.posX + ", " + var2.posY + ", " + var2.posZ + ")");
      WorldServer var9 = this.mcServer.worldServerForDimension(var2.dimension);
      ChunkCoordinates var10 = var9.getSpawnPoint();
      this.func_72381_a(var2, (EntityPlayerMP)null, var9);
      NetHandlerPlayServer var11 = new NetHandlerPlayServer(this.mcServer, var1, var2);
      var11.sendPacket(new S01PacketJoinGame(var2.getEntityId(), var2.theItemInWorldManager.getGameType(), var9.getWorldInfo().isHardcoreModeEnabled(), var9.provider.dimensionId, var9.difficultySetting, this.getMaxPlayers(), var9.getWorldInfo().getTerrainType()));
      var11.sendPacket(new S3FPacketCustomPayload("MC|Brand", this.getServerInstance().getServerModName().getBytes(Charsets.UTF_8)));
      var11.sendPacket(new S05PacketSpawnPosition(var10.posX, var10.posY, var10.posZ));
      var11.sendPacket(new S39PacketPlayerAbilities(var2.capabilities));
      var11.sendPacket(new S09PacketHeldItemChange(var2.inventory.currentItem));
      var2.func_147099_x().func_150877_d();
      var2.func_147099_x().func_150884_b(var2);
      this.func_96456_a((ServerScoreboard)var9.getScoreboard(), var2);
      this.mcServer.func_147132_au();
      ChatComponentTranslation var12;
      if(!var2.getCommandSenderName().equalsIgnoreCase(var6)) {
         var12 = new ChatComponentTranslation("multiplayer.player.joined.renamed", new Object[]{var2.func_145748_c_(), var6});
      } else {
         var12 = new ChatComponentTranslation("multiplayer.player.joined", new Object[]{var2.func_145748_c_()});
      }

      var12.getChatStyle().setColor(EnumChatFormatting.YELLOW);
      this.sendChatMsg(var12);
      this.playerLoggedIn(var2);
      var11.setPlayerLocation(var2.posX, var2.posY, var2.posZ, var2.rotationYaw, var2.rotationPitch);
      this.updateTimeAndWeatherForPlayer(var2, var9);
      if(this.mcServer.getTexturePack().length() > 0) {
         var2.requestTexturePackLoad(this.mcServer.getTexturePack());
      }

      Iterator var13 = var2.getActivePotionEffects().iterator();

      while(var13.hasNext()) {
         PotionEffect var14 = (PotionEffect)var13.next();
         var11.sendPacket(new S1DPacketEntityEffect(var2.getEntityId(), var14));
      }

      var2.addSelfToInternalCraftingInventory();
      if(var7 != null && var7.hasKey("Riding", 10)) {
         Entity var15 = EntityList.createEntityFromNBT(var7.getCompoundTag("Riding"), var9);
         if(var15 != null) {
            var15.forceSpawn = true;
            var9.spawnEntityInWorld(var15);
            var2.mountEntity(var15);
            var15.forceSpawn = false;
         }
      }

   }

   protected void func_96456_a(ServerScoreboard var1, EntityPlayerMP var2) {
      HashSet var3 = new HashSet();
      Iterator var4 = var1.getTeams().iterator();

      while(var4.hasNext()) {
         ScorePlayerTeam var5 = (ScorePlayerTeam)var4.next();
         var2.playerNetServerHandler.sendPacket(new S3EPacketTeams(var5, 0));
      }

      for(int var9 = 0; var9 < 3; ++var9) {
         ScoreObjective var10 = var1.func_96539_a(var9);
         if(var10 != null && !var3.contains(var10)) {
            List var6 = var1.func_96550_d(var10);
            Iterator var7 = var6.iterator();

            while(var7.hasNext()) {
               Packet var8 = (Packet)var7.next();
               var2.playerNetServerHandler.sendPacket(var8);
            }

            var3.add(var10);
         }
      }

   }

   public void setPlayerManager(WorldServer[] var1) {
      this.playerNBTManagerObj = var1[0].getSaveHandler().getSaveHandler();
   }

   public void func_72375_a(EntityPlayerMP var1, WorldServer var2) {
      WorldServer var3 = var1.getServerForPlayer();
      if(var2 != null) {
         var2.getPlayerManager().removePlayer(var1);
      }

      var3.getPlayerManager().addPlayer(var1);
      var3.theChunkProviderServer.loadChunk((int)var1.posX >> 4, (int)var1.posZ >> 4);
   }

   public int getEntityViewDistance() {
      return PlayerManager.getFurthestViewableBlock(this.getViewDistance());
   }

   public NBTTagCompound readPlayerDataFromFile(EntityPlayerMP var1) {
      NBTTagCompound var2 = this.mcServer.worldServers[0].getWorldInfo().getPlayerNBTTagCompound();
      NBTTagCompound var3;
      if(var1.getCommandSenderName().equals(this.mcServer.getServerOwner()) && var2 != null) {
         var1.readFromNBT(var2);
         var3 = var2;
         logger.debug("loading single player");
      } else {
         var3 = this.playerNBTManagerObj.readPlayerData(var1);
      }

      return var3;
   }

   protected void writePlayerData(EntityPlayerMP var1) {
      this.playerNBTManagerObj.writePlayerData(var1);
      StatisticsFile var2 = (StatisticsFile)this.field_148547_k.get(var1.getUniqueID());
      if(var2 != null) {
         var2.func_150883_b();
      }

   }

   public void playerLoggedIn(EntityPlayerMP var1) {
      this.sendPacketToAllPlayers(new S38PacketPlayerListItem(var1.getCommandSenderName(), true, 1000));
      this.playerEntityList.add(var1);
      WorldServer var2 = this.mcServer.worldServerForDimension(var1.dimension);
      var2.spawnEntityInWorld(var1);
      this.func_72375_a(var1, (WorldServer)null);

      for(int var3 = 0; var3 < this.playerEntityList.size(); ++var3) {
         EntityPlayerMP var4 = (EntityPlayerMP)this.playerEntityList.get(var3);
         var1.playerNetServerHandler.sendPacket(new S38PacketPlayerListItem(var4.getCommandSenderName(), true, var4.ping));
      }

   }

   public void updatePlayerPertinentChunks(EntityPlayerMP var1) {
      var1.getServerForPlayer().getPlayerManager().updatePlayerPertinentChunks(var1);
   }

   public void playerLoggedOut(EntityPlayerMP var1) {
      var1.triggerAchievement(StatList.leaveGameStat);
      this.writePlayerData(var1);
      WorldServer var2 = var1.getServerForPlayer();
      if(var1.ridingEntity != null) {
         var2.removePlayerEntityDangerously(var1.ridingEntity);
         logger.debug("removing player mount");
      }

      var2.removeEntity(var1);
      var2.getPlayerManager().removePlayer(var1);
      this.playerEntityList.remove(var1);
      this.field_148547_k.remove(var1.getUniqueID());
      this.sendPacketToAllPlayers(new S38PacketPlayerListItem(var1.getCommandSenderName(), false, 9999));
   }

   public String allowUserToConnect(SocketAddress var1, GameProfile var2) {
      String var4;
      if(this.bannedPlayers.func_152702_a(var2)) {
         UserListBansEntry var5 = (UserListBansEntry)this.bannedPlayers.func_152683_b(var2);
         var4 = "You are banned from this server!\nReason: " + var5.getBanReason();
         if(var5.getBanEndDate() != null) {
            var4 = var4 + "\nYour ban will be removed on " + dateFormat.format(var5.getBanEndDate());
         }

         return var4;
      } else if(!this.func_152607_e(var2)) {
         return "You are not white-listed on this server!";
      } else if(this.bannedIPs.func_152708_a(var1)) {
         IPBanEntry var3 = this.bannedIPs.func_152709_b(var1);
         var4 = "Your IP address is banned from this server!\nReason: " + var3.getBanReason();
         if(var3.getBanEndDate() != null) {
            var4 = var4 + "\nYour ban will be removed on " + dateFormat.format(var3.getBanEndDate());
         }

         return var4;
      } else {
         return this.playerEntityList.size() >= this.maxPlayers?"The server is full!":null;
      }
   }

   public EntityPlayerMP createPlayerForUser(GameProfile var1) {
      UUID var2 = EntityPlayer.func_146094_a(var1);
      ArrayList var3 = Lists.newArrayList();

      EntityPlayerMP var5;
      for(int var4 = 0; var4 < this.playerEntityList.size(); ++var4) {
         var5 = (EntityPlayerMP)this.playerEntityList.get(var4);
         if(var5.getUniqueID().equals(var2)) {
            var3.add(var5);
         }
      }

      Iterator var6 = var3.iterator();

      while(var6.hasNext()) {
         var5 = (EntityPlayerMP)var6.next();
         var5.playerNetServerHandler.kickPlayerFromServer("You logged in from another location");
      }

      Object var7;
      if(this.mcServer.isDemo()) {
         var7 = new DemoWorldManager(this.mcServer.worldServerForDimension(0));
      } else {
         var7 = new ItemInWorldManager(this.mcServer.worldServerForDimension(0));
      }

      return new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(0), var1, (ItemInWorldManager)var7);
   }

   public EntityPlayerMP respawnPlayer(EntityPlayerMP var1, int var2, boolean var3) {
      var1.getServerForPlayer().getEntityTracker().removePlayerFromTrackers(var1);
      var1.getServerForPlayer().getEntityTracker().removeEntityFromAllTrackingPlayers(var1);
      var1.getServerForPlayer().getPlayerManager().removePlayer(var1);
      this.playerEntityList.remove(var1);
      this.mcServer.worldServerForDimension(var1.dimension).removePlayerEntityDangerously(var1);
      ChunkCoordinates var4 = var1.getBedLocation();
      boolean var5 = var1.isSpawnForced();
      var1.dimension = var2;
      Object var6;
      if(this.mcServer.isDemo()) {
         var6 = new DemoWorldManager(this.mcServer.worldServerForDimension(var1.dimension));
      } else {
         var6 = new ItemInWorldManager(this.mcServer.worldServerForDimension(var1.dimension));
      }

      EntityPlayerMP var7 = new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(var1.dimension), var1.getGameProfile(), (ItemInWorldManager)var6);
      var7.playerNetServerHandler = var1.playerNetServerHandler;
      var7.clonePlayer(var1, var3);
      var7.setEntityId(var1.getEntityId());
      WorldServer var8 = this.mcServer.worldServerForDimension(var1.dimension);
      this.func_72381_a(var7, var1, var8);
      ChunkCoordinates var9;
      if(var4 != null) {
         var9 = EntityPlayer.verifyRespawnCoordinates(this.mcServer.worldServerForDimension(var1.dimension), var4, var5);
         if(var9 != null) {
            var7.setLocationAndAngles((double)((float)var9.posX + 0.5F), (double)((float)var9.posY + 0.1F), (double)((float)var9.posZ + 0.5F), 0.0F, 0.0F);
            var7.setSpawnChunk(var4, var5);
         } else {
            var7.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(0, 0.0F));
         }
      }

      var8.theChunkProviderServer.loadChunk((int)var7.posX >> 4, (int)var7.posZ >> 4);

      while(!var8.getCollidingBoundingBoxes(var7, var7.boundingBox).isEmpty()) {
         var7.setPosition(var7.posX, var7.posY + 1.0D, var7.posZ);
      }

      var7.playerNetServerHandler.sendPacket(new S07PacketRespawn(var7.dimension, var7.worldObj.difficultySetting, var7.worldObj.getWorldInfo().getTerrainType(), var7.theItemInWorldManager.getGameType()));
      var9 = var8.getSpawnPoint();
      var7.playerNetServerHandler.setPlayerLocation(var7.posX, var7.posY, var7.posZ, var7.rotationYaw, var7.rotationPitch);
      var7.playerNetServerHandler.sendPacket(new S05PacketSpawnPosition(var9.posX, var9.posY, var9.posZ));
      var7.playerNetServerHandler.sendPacket(new S1FPacketSetExperience(var7.experience, var7.experienceTotal, var7.experienceLevel));
      this.updateTimeAndWeatherForPlayer(var7, var8);
      var8.getPlayerManager().addPlayer(var7);
      var8.spawnEntityInWorld(var7);
      this.playerEntityList.add(var7);
      var7.addSelfToInternalCraftingInventory();
      var7.setHealth(var7.getHealth());
      return var7;
   }

   public void transferPlayerToDimension(EntityPlayerMP var1, int var2) {
      int var3 = var1.dimension;
      WorldServer var4 = this.mcServer.worldServerForDimension(var1.dimension);
      var1.dimension = var2;
      WorldServer var5 = this.mcServer.worldServerForDimension(var1.dimension);
      var1.playerNetServerHandler.sendPacket(new S07PacketRespawn(var1.dimension, var1.worldObj.difficultySetting, var1.worldObj.getWorldInfo().getTerrainType(), var1.theItemInWorldManager.getGameType()));
      var4.removePlayerEntityDangerously(var1);
      var1.isDead = false;
      this.transferEntityToWorld(var1, var3, var4, var5);
      this.func_72375_a(var1, var4);
      var1.playerNetServerHandler.setPlayerLocation(var1.posX, var1.posY, var1.posZ, var1.rotationYaw, var1.rotationPitch);
      var1.theItemInWorldManager.setWorld(var5);
      this.updateTimeAndWeatherForPlayer(var1, var5);
      this.syncPlayerInventory(var1);
      Iterator var6 = var1.getActivePotionEffects().iterator();

      while(var6.hasNext()) {
         PotionEffect var7 = (PotionEffect)var6.next();
         var1.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(var1.getEntityId(), var7));
      }

   }

   public void transferEntityToWorld(Entity var1, int var2, WorldServer var3, WorldServer var4) {
      double var5 = var1.posX;
      double var7 = var1.posZ;
      double var9 = 8.0D;
      double var11 = var1.posX;
      double var13 = var1.posY;
      double var15 = var1.posZ;
      float var17 = var1.rotationYaw;
      var3.theProfiler.startSection("moving");
      if(var1.dimension == -1) {
         var5 /= var9;
         var7 /= var9;
         var1.setLocationAndAngles(var5, var1.posY, var7, var1.rotationYaw, var1.rotationPitch);
         if(var1.isEntityAlive()) {
            var3.updateEntityWithOptionalForce(var1, false);
         }
      } else if(var1.dimension == 0) {
         var5 *= var9;
         var7 *= var9;
         var1.setLocationAndAngles(var5, var1.posY, var7, var1.rotationYaw, var1.rotationPitch);
         if(var1.isEntityAlive()) {
            var3.updateEntityWithOptionalForce(var1, false);
         }
      } else {
         ChunkCoordinates var18;
         if(var2 == 1) {
            var18 = var4.getSpawnPoint();
         } else {
            var18 = var4.getEntrancePortalLocation();
         }

         var5 = (double)var18.posX;
         var1.posY = (double)var18.posY;
         var7 = (double)var18.posZ;
         var1.setLocationAndAngles(var5, var1.posY, var7, 90.0F, 0.0F);
         if(var1.isEntityAlive()) {
            var3.updateEntityWithOptionalForce(var1, false);
         }
      }

      var3.theProfiler.endSection();
      if(var2 != 1) {
         var3.theProfiler.startSection("placing");
         var5 = (double)MathHelper.clamp_int((int)var5, -29999872, 29999872);
         var7 = (double)MathHelper.clamp_int((int)var7, -29999872, 29999872);
         if(var1.isEntityAlive()) {
            var1.setLocationAndAngles(var5, var1.posY, var7, var1.rotationYaw, var1.rotationPitch);
            var4.getDefaultTeleporter().placeInPortal(var1, var11, var13, var15, var17);
            var4.spawnEntityInWorld(var1);
            var4.updateEntityWithOptionalForce(var1, false);
         }

         var3.theProfiler.endSection();
      }

      var1.setWorld(var4);
   }

   public void sendPlayerInfoToAllPlayers() {
      if(++this.playerPingIndex > 600) {
         this.playerPingIndex = 0;
      }

      if(this.playerPingIndex < this.playerEntityList.size()) {
         EntityPlayerMP var1 = (EntityPlayerMP)this.playerEntityList.get(this.playerPingIndex);
         this.sendPacketToAllPlayers(new S38PacketPlayerListItem(var1.getCommandSenderName(), true, var1.ping));
      }

   }

   public void sendPacketToAllPlayers(Packet var1) {
      for(int var2 = 0; var2 < this.playerEntityList.size(); ++var2) {
         ((EntityPlayerMP)this.playerEntityList.get(var2)).playerNetServerHandler.sendPacket(var1);
      }

   }

   public void sendPacketToAllPlayersInDimension(Packet var1, int var2) {
      for(int var3 = 0; var3 < this.playerEntityList.size(); ++var3) {
         EntityPlayerMP var4 = (EntityPlayerMP)this.playerEntityList.get(var3);
         if(var4.dimension == var2) {
            var4.playerNetServerHandler.sendPacket(var1);
         }
      }

   }

   public String func_152609_b(boolean var1) {
      String var2 = "";
      ArrayList var3 = Lists.newArrayList(this.playerEntityList);

      for(int var4 = 0; var4 < var3.size(); ++var4) {
         if(var4 > 0) {
            var2 = var2 + ", ";
         }

         var2 = var2 + ((EntityPlayerMP)var3.get(var4)).getCommandSenderName();
         if(var1) {
            var2 = var2 + " (" + ((EntityPlayerMP)var3.get(var4)).getUniqueID().toString() + ")";
         }
      }

      return var2;
   }

   public String[] getAllUsernames() {
      String[] var1 = new String[this.playerEntityList.size()];

      for(int var2 = 0; var2 < this.playerEntityList.size(); ++var2) {
         var1[var2] = ((EntityPlayerMP)this.playerEntityList.get(var2)).getCommandSenderName();
      }

      return var1;
   }

   public GameProfile[] func_152600_g() {
      GameProfile[] var1 = new GameProfile[this.playerEntityList.size()];

      for(int var2 = 0; var2 < this.playerEntityList.size(); ++var2) {
         var1[var2] = ((EntityPlayerMP)this.playerEntityList.get(var2)).getGameProfile();
      }

      return var1;
   }

   public UserListBans func_152608_h() {
      return this.bannedPlayers;
   }

   public BanList getBannedIPs() {
      return this.bannedIPs;
   }

   public void func_152605_a(GameProfile var1) {
      this.ops.func_152687_a(new UserListOpsEntry(var1, this.mcServer.getOpPermissionLevel()));
   }

   public void func_152610_b(GameProfile var1) {
      this.ops.func_152684_c(var1);
   }

   public boolean func_152607_e(GameProfile var1) {
      return !this.whiteListEnforced || this.ops.func_152692_d(var1) || this.whiteListedPlayers.func_152692_d(var1);
   }

   public boolean func_152596_g(GameProfile var1) {
      return this.ops.func_152692_d(var1) || this.mcServer.isSinglePlayer() && this.mcServer.worldServers[0].getWorldInfo().areCommandsAllowed() && this.mcServer.getServerOwner().equalsIgnoreCase(var1.getName()) || this.commandsAllowedForAll;
   }

   public EntityPlayerMP func_152612_a(String var1) {
      Iterator var2 = this.playerEntityList.iterator();

      EntityPlayerMP var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (EntityPlayerMP)var2.next();
      } while(!var3.getCommandSenderName().equalsIgnoreCase(var1));

      return var3;
   }

   public List findPlayers(ChunkCoordinates var1, int var2, int var3, int var4, int var5, int var6, int var7, Map var8, String var9, String var10, World var11) {
      if(this.playerEntityList.isEmpty()) {
         return Collections.emptyList();
      } else {
         Object var12 = new ArrayList();
         boolean var13 = var4 < 0;
         boolean var14 = var9 != null && var9.startsWith("!");
         boolean var15 = var10 != null && var10.startsWith("!");
         int var16 = var2 * var2;
         int var17 = var3 * var3;
         var4 = MathHelper.abs_int(var4);
         if(var14) {
            var9 = var9.substring(1);
         }

         if(var15) {
            var10 = var10.substring(1);
         }

         for(int var18 = 0; var18 < this.playerEntityList.size(); ++var18) {
            EntityPlayerMP var19 = (EntityPlayerMP)this.playerEntityList.get(var18);
            if((var11 == null || var19.worldObj == var11) && (var9 == null || var14 != var9.equalsIgnoreCase(var19.getCommandSenderName()))) {
               if(var10 != null) {
                  Team var20 = var19.getTeam();
                  String var21 = var20 == null?"":var20.getRegisteredName();
                  if(var15 == var10.equalsIgnoreCase(var21)) {
                     continue;
                  }
               }

               if(var1 != null && (var2 > 0 || var3 > 0)) {
                  float var22 = var1.getDistanceSquaredToChunkCoordinates(var19.getPlayerCoordinates());
                  if(var2 > 0 && var22 < (float)var16 || var3 > 0 && var22 > (float)var17) {
                     continue;
                  }
               }

               if(this.func_96457_a(var19, var8) && (var5 == WorldSettings$GameType.NOT_SET.getID() || var5 == var19.theItemInWorldManager.getGameType().getID()) && (var6 <= 0 || var19.experienceLevel >= var6) && var19.experienceLevel <= var7) {
                  ((List)var12).add(var19);
               }
            }
         }

         if(var1 != null) {
            Collections.sort((List)var12, new PlayerPositionComparator(var1));
         }

         if(var13) {
            Collections.reverse((List)var12);
         }

         if(var4 > 0) {
            var12 = ((List)var12).subList(0, Math.min(var4, ((List)var12).size()));
         }

         return (List)var12;
      }
   }

   private boolean func_96457_a(EntityPlayer var1, Map var2) {
      if(var2 != null && var2.size() != 0) {
         Iterator var3 = var2.entrySet().iterator();

         Entry var4;
         boolean var6;
         int var10;
         do {
            if(!var3.hasNext()) {
               return true;
            }

            var4 = (Entry)var3.next();
            String var5 = (String)var4.getKey();
            var6 = false;
            if(var5.endsWith("_min") && var5.length() > 4) {
               var6 = true;
               var5 = var5.substring(0, var5.length() - 4);
            }

            Scoreboard var7 = var1.getWorldScoreboard();
            ScoreObjective var8 = var7.getObjective(var5);
            if(var8 == null) {
               return false;
            }

            Score var9 = var1.getWorldScoreboard().func_96529_a(var1.getCommandSenderName(), var8);
            var10 = var9.getScorePoints();
            if(var10 < ((Integer)var4.getValue()).intValue() && var6) {
               return false;
            }
         } while(var10 <= ((Integer)var4.getValue()).intValue() || var6);

         return false;
      } else {
         return true;
      }
   }

   public void sendToAllNear(double var1, double var3, double var5, double var7, int var9, Packet var10) {
      this.sendToAllNearExcept((EntityPlayer)null, var1, var3, var5, var7, var9, var10);
   }

   public void sendToAllNearExcept(EntityPlayer var1, double var2, double var4, double var6, double var8, int var10, Packet var11) {
      for(int var12 = 0; var12 < this.playerEntityList.size(); ++var12) {
         EntityPlayerMP var13 = (EntityPlayerMP)this.playerEntityList.get(var12);
         if(var13 != var1 && var13.dimension == var10) {
            double var14 = var2 - var13.posX;
            double var16 = var4 - var13.posY;
            double var18 = var6 - var13.posZ;
            if(var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
               var13.playerNetServerHandler.sendPacket(var11);
            }
         }
      }

   }

   public void saveAllPlayerData() {
      for(int var1 = 0; var1 < this.playerEntityList.size(); ++var1) {
         this.writePlayerData((EntityPlayerMP)this.playerEntityList.get(var1));
      }

   }

   public void func_152601_d(GameProfile var1) {
      this.whiteListedPlayers.func_152687_a(new UserListWhitelistEntry(var1));
   }

   public void func_152597_c(GameProfile var1) {
      this.whiteListedPlayers.func_152684_c(var1);
   }

   public UserListWhitelist func_152599_k() {
      return this.whiteListedPlayers;
   }

   public String[] func_152598_l() {
      return this.whiteListedPlayers.func_152685_a();
   }

   public UserListOps func_152603_m() {
      return this.ops;
   }

   public String[] func_152606_n() {
      return this.ops.func_152685_a();
   }

   public void loadWhiteList() {}

   public void updateTimeAndWeatherForPlayer(EntityPlayerMP var1, WorldServer var2) {
      var1.playerNetServerHandler.sendPacket(new S03PacketTimeUpdate(var2.getTotalWorldTime(), var2.getWorldTime(), var2.getGameRules().getGameRuleBooleanValue("doDaylightCycle")));
      if(var2.isRaining()) {
         var1.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(1, 0.0F));
         var1.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(7, var2.getRainStrength(1.0F)));
         var1.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(8, var2.getWeightedThunderStrength(1.0F)));
      }

   }

   public void syncPlayerInventory(EntityPlayerMP var1) {
      var1.sendContainerToPlayer(var1.inventoryContainer);
      var1.setPlayerHealthUpdated();
      var1.playerNetServerHandler.sendPacket(new S09PacketHeldItemChange(var1.inventory.currentItem));
   }

   public int getCurrentPlayerCount() {
      return this.playerEntityList.size();
   }

   public int getMaxPlayers() {
      return this.maxPlayers;
   }

   public String[] getAvailablePlayerDat() {
      return this.mcServer.worldServers[0].getSaveHandler().getSaveHandler().getAvailablePlayerDat();
   }

   public void setWhiteListEnabled(boolean var1) {
      this.whiteListEnforced = var1;
   }

   public List getPlayerList(String var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.playerEntityList.iterator();

      while(var3.hasNext()) {
         EntityPlayerMP var4 = (EntityPlayerMP)var3.next();
         if(var4.getPlayerIP().equals(var1)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public int getViewDistance() {
      return this.viewDistance;
   }

   public MinecraftServer getServerInstance() {
      return this.mcServer;
   }

   public NBTTagCompound getHostPlayerData() {
      return null;
   }

   public void func_152604_a(WorldSettings$GameType var1) {
      this.gameType = var1;
   }

   private void func_72381_a(EntityPlayerMP var1, EntityPlayerMP var2, World var3) {
      if(var2 != null) {
         var1.theItemInWorldManager.setGameType(var2.theItemInWorldManager.getGameType());
      } else if(this.gameType != null) {
         var1.theItemInWorldManager.setGameType(this.gameType);
      }

      var1.theItemInWorldManager.initializeGameType(var3.getWorldInfo().getGameType());
   }

   public void setCommandsAllowedForAll(boolean var1) {
      this.commandsAllowedForAll = var1;
   }

   public void removeAllPlayers() {
      for(int var1 = 0; var1 < this.playerEntityList.size(); ++var1) {
         ((EntityPlayerMP)this.playerEntityList.get(var1)).playerNetServerHandler.kickPlayerFromServer("Server closed");
      }

   }

   public void sendChatMsgImpl(IChatComponent var1, boolean var2) {
      this.mcServer.addChatMessage(var1);
      this.sendPacketToAllPlayers(new S02PacketChat(var1, var2));
   }

   public void sendChatMsg(IChatComponent var1) {
      this.sendChatMsgImpl(var1, true);
   }

   public StatisticsFile func_152602_a(EntityPlayer var1) {
      UUID var2 = var1.getUniqueID();
      StatisticsFile var3 = var2 == null?null:(StatisticsFile)this.field_148547_k.get(var2);
      if(var3 == null) {
         File var4 = new File(this.mcServer.worldServerForDimension(0).getSaveHandler().getWorldDirectory(), "stats");
         File var5 = new File(var4, var2.toString() + ".json");
         if(!var5.exists()) {
            File var6 = new File(var4, var1.getCommandSenderName() + ".json");
            if(var6.exists() && var6.isFile()) {
               var6.renameTo(var5);
            }
         }

         var3 = new StatisticsFile(this.mcServer, var5);
         var3.func_150882_a();
         this.field_148547_k.put(var2, var3);
      }

      return var3;
   }

   public void func_152611_a(int var1) {
      this.viewDistance = var1;
      if(this.mcServer.worldServers != null) {
         WorldServer[] var2 = this.mcServer.worldServers;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            WorldServer var5 = var2[var4];
            if(var5 != null) {
               var5.getPlayerManager().func_152622_a(var1);
            }
         }

      }
   }

}
