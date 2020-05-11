package net.minecraft.client.network;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenDemo;
import net.minecraft.client.gui.GuiScreenRealmsProxy;
import net.minecraft.client.gui.GuiWinGame;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.ServerData$ServerResourceMode;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient$1;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.client.particle.EntityPickupFX;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.stream.MetadataAchievement;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.NpcMerchant;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C03PacketPlayer$C06PacketPlayerPosLook;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S20PacketEntityProperties$Snapshot;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.realms.DisconnectedOnlineScreen;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.Explosion;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerPlayClient implements INetHandlerPlayClient {

   private static final Logger logger = LogManager.getLogger();
   private final NetworkManager netManager;
   private Minecraft gameController;
   private WorldClient clientWorldController;
   private boolean doneLoadingTerrain;
   public MapStorage mapStorageOrigin = new MapStorage((ISaveHandler)null);
   private Map playerInfoMap = new HashMap();
   public List playerInfoList = new ArrayList();
   public int currentServerMaxPlayers = 20;
   private GuiScreen guiScreenServer;
   private boolean field_147308_k = false;
   private Random avRandomizer = new Random();


   public NetHandlerPlayClient(Minecraft var1, GuiScreen var2, NetworkManager var3) {
      this.gameController = var1;
      this.guiScreenServer = var2;
      this.netManager = var3;
   }

   public void cleanup() {
      this.clientWorldController = null;
   }

   public void onNetworkTick() {}

   public void handleJoinGame(S01PacketJoinGame var1) {
      this.gameController.playerController = new PlayerControllerMP(this.gameController, this);
      this.clientWorldController = new WorldClient(this, new WorldSettings(0L, var1.func_149198_e(), false, var1.func_149195_d(), var1.func_149196_i()), var1.func_149194_f(), var1.func_149192_g(), this.gameController.mcProfiler);
      this.clientWorldController.isRemote = true;
      this.gameController.loadWorld(this.clientWorldController);
      this.gameController.thePlayer.dimension = var1.func_149194_f();
      this.gameController.displayGuiScreen(new GuiDownloadTerrain(this));
      this.gameController.thePlayer.setEntityId(var1.func_149197_c());
      this.currentServerMaxPlayers = var1.func_149193_h();
      this.gameController.playerController.setGameType(var1.func_149198_e());
      this.gameController.gameSettings.sendSettingsToServer();
      this.netManager.scheduleOutboundPacket(new C17PacketCustomPayload("MC|Brand", ClientBrandRetriever.getClientModName().getBytes(Charsets.UTF_8)), new GenericFutureListener[0]);
   }

   public void handleSpawnObject(S0EPacketSpawnObject var1) {
      double var2 = (double)var1.func_148997_d() / 32.0D;
      double var4 = (double)var1.func_148998_e() / 32.0D;
      double var6 = (double)var1.func_148994_f() / 32.0D;
      Object var8 = null;
      if(var1.func_148993_l() == 10) {
         var8 = EntityMinecart.createMinecart(this.clientWorldController, var2, var4, var6, var1.func_149009_m());
      } else if(var1.func_148993_l() == 90) {
         Entity var9 = this.clientWorldController.getEntityByID(var1.func_149009_m());
         if(var9 instanceof EntityPlayer) {
            var8 = new EntityFishHook(this.clientWorldController, var2, var4, var6, (EntityPlayer)var9);
         }

         var1.func_149002_g(0);
      } else if(var1.func_148993_l() == 60) {
         var8 = new EntityArrow(this.clientWorldController, var2, var4, var6);
      } else if(var1.func_148993_l() == 61) {
         var8 = new EntitySnowball(this.clientWorldController, var2, var4, var6);
      } else if(var1.func_148993_l() == 71) {
         var8 = new EntityItemFrame(this.clientWorldController, (int)var2, (int)var4, (int)var6, var1.func_149009_m());
         var1.func_149002_g(0);
      } else if(var1.func_148993_l() == 77) {
         var8 = new EntityLeashKnot(this.clientWorldController, (int)var2, (int)var4, (int)var6);
         var1.func_149002_g(0);
      } else if(var1.func_148993_l() == 65) {
         var8 = new EntityEnderPearl(this.clientWorldController, var2, var4, var6);
      } else if(var1.func_148993_l() == 72) {
         var8 = new EntityEnderEye(this.clientWorldController, var2, var4, var6);
      } else if(var1.func_148993_l() == 76) {
         var8 = new EntityFireworkRocket(this.clientWorldController, var2, var4, var6, (ItemStack)null);
      } else if(var1.func_148993_l() == 63) {
         var8 = new EntityLargeFireball(this.clientWorldController, var2, var4, var6, (double)var1.func_149010_g() / 8000.0D, (double)var1.func_149004_h() / 8000.0D, (double)var1.func_148999_i() / 8000.0D);
         var1.func_149002_g(0);
      } else if(var1.func_148993_l() == 64) {
         var8 = new EntitySmallFireball(this.clientWorldController, var2, var4, var6, (double)var1.func_149010_g() / 8000.0D, (double)var1.func_149004_h() / 8000.0D, (double)var1.func_148999_i() / 8000.0D);
         var1.func_149002_g(0);
      } else if(var1.func_148993_l() == 66) {
         var8 = new EntityWitherSkull(this.clientWorldController, var2, var4, var6, (double)var1.func_149010_g() / 8000.0D, (double)var1.func_149004_h() / 8000.0D, (double)var1.func_148999_i() / 8000.0D);
         var1.func_149002_g(0);
      } else if(var1.func_148993_l() == 62) {
         var8 = new EntityEgg(this.clientWorldController, var2, var4, var6);
      } else if(var1.func_148993_l() == 73) {
         var8 = new EntityPotion(this.clientWorldController, var2, var4, var6, var1.func_149009_m());
         var1.func_149002_g(0);
      } else if(var1.func_148993_l() == 75) {
         var8 = new EntityExpBottle(this.clientWorldController, var2, var4, var6);
         var1.func_149002_g(0);
      } else if(var1.func_148993_l() == 1) {
         var8 = new EntityBoat(this.clientWorldController, var2, var4, var6);
      } else if(var1.func_148993_l() == 50) {
         var8 = new EntityTNTPrimed(this.clientWorldController, var2, var4, var6, (EntityLivingBase)null);
      } else if(var1.func_148993_l() == 51) {
         var8 = new EntityEnderCrystal(this.clientWorldController, var2, var4, var6);
      } else if(var1.func_148993_l() == 2) {
         var8 = new EntityItem(this.clientWorldController, var2, var4, var6);
      } else if(var1.func_148993_l() == 70) {
         var8 = new EntityFallingBlock(this.clientWorldController, var2, var4, var6, Block.getBlockById(var1.func_149009_m() & '\uffff'), var1.func_149009_m() >> 16);
         var1.func_149002_g(0);
      }

      if(var8 != null) {
         ((Entity)var8).serverPosX = var1.func_148997_d();
         ((Entity)var8).serverPosY = var1.func_148998_e();
         ((Entity)var8).serverPosZ = var1.func_148994_f();
         ((Entity)var8).rotationPitch = (float)(var1.func_149008_j() * 360) / 256.0F;
         ((Entity)var8).rotationYaw = (float)(var1.func_149006_k() * 360) / 256.0F;
         Entity[] var12 = ((Entity)var8).getParts();
         if(var12 != null) {
            int var10 = var1.func_149001_c() - ((Entity)var8).getEntityId();

            for(int var11 = 0; var11 < var12.length; ++var11) {
               var12[var11].setEntityId(var12[var11].getEntityId() + var10);
            }
         }

         ((Entity)var8).setEntityId(var1.func_149001_c());
         this.clientWorldController.addEntityToWorld(var1.func_149001_c(), (Entity)var8);
         if(var1.func_149009_m() > 0) {
            if(var1.func_148993_l() == 60) {
               Entity var13 = this.clientWorldController.getEntityByID(var1.func_149009_m());
               if(var13 instanceof EntityLivingBase) {
                  EntityArrow var14 = (EntityArrow)var8;
                  var14.shootingEntity = var13;
               }
            }

            ((Entity)var8).setVelocity((double)var1.func_149010_g() / 8000.0D, (double)var1.func_149004_h() / 8000.0D, (double)var1.func_148999_i() / 8000.0D);
         }
      }

   }

   public void handleSpawnExperienceOrb(S11PacketSpawnExperienceOrb var1) {
      EntityXPOrb var2 = new EntityXPOrb(this.clientWorldController, (double)var1.func_148984_d(), (double)var1.func_148983_e(), (double)var1.func_148982_f(), var1.func_148986_g());
      var2.serverPosX = var1.func_148984_d();
      var2.serverPosY = var1.func_148983_e();
      var2.serverPosZ = var1.func_148982_f();
      var2.rotationYaw = 0.0F;
      var2.rotationPitch = 0.0F;
      var2.setEntityId(var1.func_148985_c());
      this.clientWorldController.addEntityToWorld(var1.func_148985_c(), var2);
   }

   public void handleSpawnGlobalEntity(S2CPacketSpawnGlobalEntity var1) {
      double var2 = (double)var1.func_149051_d() / 32.0D;
      double var4 = (double)var1.func_149050_e() / 32.0D;
      double var6 = (double)var1.func_149049_f() / 32.0D;
      EntityLightningBolt var8 = null;
      if(var1.func_149053_g() == 1) {
         var8 = new EntityLightningBolt(this.clientWorldController, var2, var4, var6);
      }

      if(var8 != null) {
         var8.serverPosX = var1.func_149051_d();
         var8.serverPosY = var1.func_149050_e();
         var8.serverPosZ = var1.func_149049_f();
         var8.rotationYaw = 0.0F;
         var8.rotationPitch = 0.0F;
         var8.setEntityId(var1.func_149052_c());
         this.clientWorldController.addWeatherEffect(var8);
      }

   }

   public void handleSpawnPainting(S10PacketSpawnPainting var1) {
      EntityPainting var2 = new EntityPainting(this.clientWorldController, var1.func_148964_d(), var1.func_148963_e(), var1.func_148962_f(), var1.func_148966_g(), var1.func_148961_h());
      this.clientWorldController.addEntityToWorld(var1.func_148965_c(), var2);
   }

   public void handleEntityVelocity(S12PacketEntityVelocity var1) {
      Entity var2 = this.clientWorldController.getEntityByID(var1.func_149412_c());
      if(var2 != null) {
         var2.setVelocity((double)var1.func_149411_d() / 8000.0D, (double)var1.func_149410_e() / 8000.0D, (double)var1.func_149409_f() / 8000.0D);
      }
   }

   public void handleEntityMetadata(S1CPacketEntityMetadata var1) {
      Entity var2 = this.clientWorldController.getEntityByID(var1.func_149375_d());
      if(var2 != null && var1.func_149376_c() != null) {
         var2.getDataWatcher().updateWatchedObjectsFromList(var1.func_149376_c());
      }

   }

   public void handleSpawnPlayer(S0CPacketSpawnPlayer var1) {
      double var2 = (double)var1.func_148942_f() / 32.0D;
      double var4 = (double)var1.func_148949_g() / 32.0D;
      double var6 = (double)var1.func_148946_h() / 32.0D;
      float var8 = (float)(var1.func_148941_i() * 360) / 256.0F;
      float var9 = (float)(var1.func_148945_j() * 360) / 256.0F;
      GameProfile var10 = var1.func_148948_e();
      EntityOtherPlayerMP var11 = new EntityOtherPlayerMP(this.gameController.theWorld, var1.func_148948_e());
      var11.prevPosX = var11.lastTickPosX = (double)(var11.serverPosX = var1.func_148942_f());
      var11.prevPosY = var11.lastTickPosY = (double)(var11.serverPosY = var1.func_148949_g());
      var11.prevPosZ = var11.lastTickPosZ = (double)(var11.serverPosZ = var1.func_148946_h());
      int var12 = var1.func_148947_k();
      if(var12 == 0) {
         var11.inventory.mainInventory[var11.inventory.currentItem] = null;
      } else {
         var11.inventory.mainInventory[var11.inventory.currentItem] = new ItemStack(Item.getItemById(var12), 1, 0);
      }

      var11.setPositionAndRotation(var2, var4, var6, var8, var9);
      this.clientWorldController.addEntityToWorld(var1.func_148943_d(), var11);
      List var13 = var1.func_148944_c();
      if(var13 != null) {
         var11.getDataWatcher().updateWatchedObjectsFromList(var13);
      }

   }

   public void handleEntityTeleport(S18PacketEntityTeleport var1) {
      Entity var2 = this.clientWorldController.getEntityByID(var1.func_149451_c());
      if(var2 != null) {
         var2.serverPosX = var1.func_149449_d();
         var2.serverPosY = var1.func_149448_e();
         var2.serverPosZ = var1.func_149446_f();
         double var3 = (double)var2.serverPosX / 32.0D;
         double var5 = (double)var2.serverPosY / 32.0D + 0.015625D;
         double var7 = (double)var2.serverPosZ / 32.0D;
         float var9 = (float)(var1.func_149450_g() * 360) / 256.0F;
         float var10 = (float)(var1.func_149447_h() * 360) / 256.0F;
         var2.setPositionAndRotation2(var3, var5, var7, var9, var10, 3);
      }
   }

   public void handleHeldItemChange(S09PacketHeldItemChange var1) {
      if(var1.func_149385_c() >= 0 && var1.func_149385_c() < InventoryPlayer.getHotbarSize()) {
         this.gameController.thePlayer.inventory.currentItem = var1.func_149385_c();
      }

   }

   public void handleEntityMovement(S14PacketEntity var1) {
      Entity var2 = var1.func_149065_a(this.clientWorldController);
      if(var2 != null) {
         var2.serverPosX += var1.func_149062_c();
         var2.serverPosY += var1.func_149061_d();
         var2.serverPosZ += var1.func_149064_e();
         double var3 = (double)var2.serverPosX / 32.0D;
         double var5 = (double)var2.serverPosY / 32.0D;
         double var7 = (double)var2.serverPosZ / 32.0D;
         float var9 = var1.func_149060_h()?(float)(var1.func_149066_f() * 360) / 256.0F:var2.rotationYaw;
         float var10 = var1.func_149060_h()?(float)(var1.func_149063_g() * 360) / 256.0F:var2.rotationPitch;
         var2.setPositionAndRotation2(var3, var5, var7, var9, var10, 3);
      }
   }

   public void handleEntityHeadLook(S19PacketEntityHeadLook var1) {
      Entity var2 = var1.func_149381_a(this.clientWorldController);
      if(var2 != null) {
         float var3 = (float)(var1.func_149380_c() * 360) / 256.0F;
         var2.setRotationYawHead(var3);
      }
   }

   public void handleDestroyEntities(S13PacketDestroyEntities var1) {
      for(int var2 = 0; var2 < var1.func_149098_c().length; ++var2) {
         this.clientWorldController.removeEntityFromWorld(var1.func_149098_c()[var2]);
      }

   }

   public void handlePlayerPosLook(S08PacketPlayerPosLook var1) {
      EntityClientPlayerMP var2 = this.gameController.thePlayer;
      double var3 = var1.func_148932_c();
      double var5 = var1.func_148928_d();
      double var7 = var1.func_148933_e();
      float var9 = var1.func_148931_f();
      float var10 = var1.func_148930_g();
      var2.ySize = 0.0F;
      var2.motionX = var2.motionY = var2.motionZ = 0.0D;
      var2.setPositionAndRotation(var3, var5, var7, var9, var10);
      this.netManager.scheduleOutboundPacket(new C03PacketPlayer$C06PacketPlayerPosLook(var2.posX, var2.boundingBox.minY, var2.posY, var2.posZ, var1.func_148931_f(), var1.func_148930_g(), var1.func_148929_h()), new GenericFutureListener[0]);
      if(!this.doneLoadingTerrain) {
         this.gameController.thePlayer.prevPosX = this.gameController.thePlayer.posX;
         this.gameController.thePlayer.prevPosY = this.gameController.thePlayer.posY;
         this.gameController.thePlayer.prevPosZ = this.gameController.thePlayer.posZ;
         this.doneLoadingTerrain = true;
         this.gameController.displayGuiScreen((GuiScreen)null);
      }

   }

   public void handleMultiBlockChange(S22PacketMultiBlockChange var1) {
      int var2 = var1.func_148920_c().chunkXPos * 16;
      int var3 = var1.func_148920_c().chunkZPos * 16;
      if(var1.func_148921_d() != null) {
         DataInputStream var4 = new DataInputStream(new ByteArrayInputStream(var1.func_148921_d()));

         try {
            for(int var5 = 0; var5 < var1.func_148922_e(); ++var5) {
               short var6 = var4.readShort();
               short var7 = var4.readShort();
               int var8 = var7 >> 4 & 4095;
               int var9 = var7 & 15;
               int var10 = var6 >> 12 & 15;
               int var11 = var6 >> 8 & 15;
               int var12 = var6 & 255;
               this.clientWorldController.func_147492_c(var10 + var2, var12, var11 + var3, Block.getBlockById(var8), var9);
            }
         } catch (IOException var13) {
            ;
         }

      }
   }

   public void handleChunkData(S21PacketChunkData var1) {
      if(var1.func_149274_i()) {
         if(var1.func_149276_g() == 0) {
            this.clientWorldController.doPreChunk(var1.func_149273_e(), var1.func_149271_f(), false);
            return;
         }

         this.clientWorldController.doPreChunk(var1.func_149273_e(), var1.func_149271_f(), true);
      }

      this.clientWorldController.invalidateBlockReceiveRegion(var1.func_149273_e() << 4, 0, var1.func_149271_f() << 4, (var1.func_149273_e() << 4) + 15, 256, (var1.func_149271_f() << 4) + 15);
      Chunk var2 = this.clientWorldController.getChunkFromChunkCoords(var1.func_149273_e(), var1.func_149271_f());
      var2.fillChunk(var1.func_149272_d(), var1.func_149276_g(), var1.func_149270_h(), var1.func_149274_i());
      this.clientWorldController.markBlockRangeForRenderUpdate(var1.func_149273_e() << 4, 0, var1.func_149271_f() << 4, (var1.func_149273_e() << 4) + 15, 256, (var1.func_149271_f() << 4) + 15);
      if(!var1.func_149274_i() || !(this.clientWorldController.provider instanceof WorldProviderSurface)) {
         var2.resetRelightChecks();
      }

   }

   public void handleBlockChange(S23PacketBlockChange var1) {
      this.clientWorldController.func_147492_c(var1.func_148879_d(), var1.func_148878_e(), var1.func_148877_f(), var1.func_148880_c(), var1.func_148881_g());
   }

   public void handleDisconnect(S40PacketDisconnect var1) {
      this.netManager.closeChannel(var1.func_149165_c());
   }

   public void onDisconnect(IChatComponent var1) {
      this.gameController.loadWorld((WorldClient)null);
      if(this.guiScreenServer != null) {
         if(this.guiScreenServer instanceof GuiScreenRealmsProxy) {
            this.gameController.displayGuiScreen((new DisconnectedOnlineScreen(((GuiScreenRealmsProxy)this.guiScreenServer).func_154321_a(), "disconnect.lost", var1)).getProxy());
         } else {
            this.gameController.displayGuiScreen(new GuiDisconnected(this.guiScreenServer, "disconnect.lost", var1));
         }
      } else {
         this.gameController.displayGuiScreen(new GuiDisconnected(new GuiMultiplayer(new GuiMainMenu()), "disconnect.lost", var1));
      }

   }

   public void addToSendQueue(Packet var1) {
      this.netManager.scheduleOutboundPacket(var1, new GenericFutureListener[0]);
   }

   public void handleCollectItem(S0DPacketCollectItem var1) {
      Entity var2 = this.clientWorldController.getEntityByID(var1.func_149354_c());
      Object var3 = (EntityLivingBase)this.clientWorldController.getEntityByID(var1.func_149353_d());
      if(var3 == null) {
         var3 = this.gameController.thePlayer;
      }

      if(var2 != null) {
         if(var2 instanceof EntityXPOrb) {
            this.clientWorldController.playSoundAtEntity(var2, "random.orb", 0.2F, ((this.avRandomizer.nextFloat() - this.avRandomizer.nextFloat()) * 0.7F + 1.0F) * 2.0F);
         } else {
            this.clientWorldController.playSoundAtEntity(var2, "random.pop", 0.2F, ((this.avRandomizer.nextFloat() - this.avRandomizer.nextFloat()) * 0.7F + 1.0F) * 2.0F);
         }

         this.gameController.effectRenderer.addEffect(new EntityPickupFX(this.gameController.theWorld, var2, (Entity)var3, -0.5F));
         this.clientWorldController.removeEntityFromWorld(var1.func_149354_c());
      }

   }

   public void handleChat(S02PacketChat var1) {
      this.gameController.ingameGUI.getChatGUI().printChatMessage(var1.func_148915_c());
   }

   public void handleAnimation(S0BPacketAnimation var1) {
      Entity var2 = this.clientWorldController.getEntityByID(var1.func_148978_c());
      if(var2 != null) {
         if(var1.func_148977_d() == 0) {
            EntityLivingBase var3 = (EntityLivingBase)var2;
            var3.swingItem();
         } else if(var1.func_148977_d() == 1) {
            var2.performHurtAnimation();
         } else if(var1.func_148977_d() == 2) {
            EntityPlayer var4 = (EntityPlayer)var2;
            var4.wakeUpPlayer(false, false, false);
         } else if(var1.func_148977_d() == 4) {
            this.gameController.effectRenderer.addEffect(new EntityCrit2FX(this.gameController.theWorld, var2));
         } else if(var1.func_148977_d() == 5) {
            EntityCrit2FX var5 = new EntityCrit2FX(this.gameController.theWorld, var2, "magicCrit");
            this.gameController.effectRenderer.addEffect(var5);
         }

      }
   }

   public void handleUseBed(S0APacketUseBed var1) {
      var1.func_149091_a(this.clientWorldController).sleepInBedAt(var1.func_149092_c(), var1.func_149090_d(), var1.func_149089_e());
   }

   public void handleSpawnMob(S0FPacketSpawnMob var1) {
      double var2 = (double)var1.func_149023_f() / 32.0D;
      double var4 = (double)var1.func_149034_g() / 32.0D;
      double var6 = (double)var1.func_149029_h() / 32.0D;
      float var8 = (float)(var1.func_149028_l() * 360) / 256.0F;
      float var9 = (float)(var1.func_149030_m() * 360) / 256.0F;
      EntityLivingBase var10 = (EntityLivingBase)EntityList.createEntityByID(var1.func_149025_e(), this.gameController.theWorld);
      var10.serverPosX = var1.func_149023_f();
      var10.serverPosY = var1.func_149034_g();
      var10.serverPosZ = var1.func_149029_h();
      var10.rotationYawHead = (float)(var1.func_149032_n() * 360) / 256.0F;
      Entity[] var11 = var10.getParts();
      if(var11 != null) {
         int var12 = var1.func_149024_d() - var10.getEntityId();

         for(int var13 = 0; var13 < var11.length; ++var13) {
            var11[var13].setEntityId(var11[var13].getEntityId() + var12);
         }
      }

      var10.setEntityId(var1.func_149024_d());
      var10.setPositionAndRotation(var2, var4, var6, var8, var9);
      var10.motionX = (double)((float)var1.func_149026_i() / 8000.0F);
      var10.motionY = (double)((float)var1.func_149033_j() / 8000.0F);
      var10.motionZ = (double)((float)var1.func_149031_k() / 8000.0F);
      this.clientWorldController.addEntityToWorld(var1.func_149024_d(), var10);
      List var14 = var1.func_149027_c();
      if(var14 != null) {
         var10.getDataWatcher().updateWatchedObjectsFromList(var14);
      }

   }

   public void handleTimeUpdate(S03PacketTimeUpdate var1) {
      this.gameController.theWorld.func_82738_a(var1.func_149366_c());
      this.gameController.theWorld.setWorldTime(var1.func_149365_d());
   }

   public void handleSpawnPosition(S05PacketSpawnPosition var1) {
      this.gameController.thePlayer.setSpawnChunk(new ChunkCoordinates(var1.func_149360_c(), var1.func_149359_d(), var1.func_149358_e()), true);
      this.gameController.theWorld.getWorldInfo().setSpawnPosition(var1.func_149360_c(), var1.func_149359_d(), var1.func_149358_e());
   }

   public void handleEntityAttach(S1BPacketEntityAttach var1) {
      Object var2 = this.clientWorldController.getEntityByID(var1.func_149403_d());
      Entity var3 = this.clientWorldController.getEntityByID(var1.func_149402_e());
      if(var1.func_149404_c() == 0) {
         boolean var4 = false;
         if(var1.func_149403_d() == this.gameController.thePlayer.getEntityId()) {
            var2 = this.gameController.thePlayer;
            if(var3 instanceof EntityBoat) {
               ((EntityBoat)var3).setIsBoatEmpty(false);
            }

            var4 = ((Entity)var2).ridingEntity == null && var3 != null;
         } else if(var3 instanceof EntityBoat) {
            ((EntityBoat)var3).setIsBoatEmpty(true);
         }

         if(var2 == null) {
            return;
         }

         ((Entity)var2).mountEntity(var3);
         if(var4) {
            GameSettings var5 = this.gameController.gameSettings;
            this.gameController.ingameGUI.func_110326_a(I18n.format("mount.onboard", new Object[]{GameSettings.getKeyDisplayString(var5.keyBindSneak.getKeyCode())}), false);
         }
      } else if(var1.func_149404_c() == 1 && var2 != null && var2 instanceof EntityLiving) {
         if(var3 != null) {
            ((EntityLiving)var2).setLeashedToEntity(var3, false);
         } else {
            ((EntityLiving)var2).clearLeashed(false, false);
         }
      }

   }

   public void handleEntityStatus(S19PacketEntityStatus var1) {
      Entity var2 = var1.func_149161_a(this.clientWorldController);
      if(var2 != null) {
         var2.handleHealthUpdate(var1.func_149160_c());
      }

   }

   public void handleUpdateHealth(S06PacketUpdateHealth var1) {
      this.gameController.thePlayer.setPlayerSPHealth(var1.func_149332_c());
      this.gameController.thePlayer.getFoodStats().setFoodLevel(var1.func_149330_d());
      this.gameController.thePlayer.getFoodStats().setFoodSaturationLevel(var1.func_149331_e());
   }

   public void handleSetExperience(S1FPacketSetExperience var1) {
      this.gameController.thePlayer.setXPStats(var1.func_149397_c(), var1.func_149396_d(), var1.func_149395_e());
   }

   public void handleRespawn(S07PacketRespawn var1) {
      if(var1.func_149082_c() != this.gameController.thePlayer.dimension) {
         this.doneLoadingTerrain = false;
         Scoreboard var2 = this.clientWorldController.getScoreboard();
         this.clientWorldController = new WorldClient(this, new WorldSettings(0L, var1.func_149083_e(), false, this.gameController.theWorld.getWorldInfo().isHardcoreModeEnabled(), var1.func_149080_f()), var1.func_149082_c(), var1.func_149081_d(), this.gameController.mcProfiler);
         this.clientWorldController.setWorldScoreboard(var2);
         this.clientWorldController.isRemote = true;
         this.gameController.loadWorld(this.clientWorldController);
         this.gameController.thePlayer.dimension = var1.func_149082_c();
         this.gameController.displayGuiScreen(new GuiDownloadTerrain(this));
      }

      this.gameController.setDimensionAndSpawnPlayer(var1.func_149082_c());
      this.gameController.playerController.setGameType(var1.func_149083_e());
   }

   public void handleExplosion(S27PacketExplosion var1) {
      Explosion var2 = new Explosion(this.gameController.theWorld, (Entity)null, var1.func_149148_f(), var1.func_149143_g(), var1.func_149145_h(), var1.func_149146_i());
      var2.affectedBlockPositions = var1.func_149150_j();
      var2.doExplosionB(true);
      this.gameController.thePlayer.motionX += (double)var1.func_149149_c();
      this.gameController.thePlayer.motionY += (double)var1.func_149144_d();
      this.gameController.thePlayer.motionZ += (double)var1.func_149147_e();
   }

   public void handleOpenWindow(S2DPacketOpenWindow var1) {
      EntityClientPlayerMP var2 = this.gameController.thePlayer;
      switch(var1.func_148899_d()) {
      case 0:
         var2.displayGUIChest(new InventoryBasic(var1.func_148902_e(), var1.func_148900_g(), var1.func_148898_f()));
         var2.openContainer.windowId = var1.func_148901_c();
         break;
      case 1:
         var2.displayGUIWorkbench(MathHelper.floor_double(var2.posX), MathHelper.floor_double(var2.posY), MathHelper.floor_double(var2.posZ));
         var2.openContainer.windowId = var1.func_148901_c();
         break;
      case 2:
         TileEntityFurnace var4 = new TileEntityFurnace();
         if(var1.func_148900_g()) {
            var4.func_145951_a(var1.func_148902_e());
         }

         var2.func_146101_a(var4);
         var2.openContainer.windowId = var1.func_148901_c();
         break;
      case 3:
         TileEntityDispenser var7 = new TileEntityDispenser();
         if(var1.func_148900_g()) {
            var7.func_146018_a(var1.func_148902_e());
         }

         var2.func_146102_a(var7);
         var2.openContainer.windowId = var1.func_148901_c();
         break;
      case 4:
         var2.displayGUIEnchantment(MathHelper.floor_double(var2.posX), MathHelper.floor_double(var2.posY), MathHelper.floor_double(var2.posZ), var1.func_148900_g()?var1.func_148902_e():null);
         var2.openContainer.windowId = var1.func_148901_c();
         break;
      case 5:
         TileEntityBrewingStand var5 = new TileEntityBrewingStand();
         if(var1.func_148900_g()) {
            var5.func_145937_a(var1.func_148902_e());
         }

         var2.func_146098_a(var5);
         var2.openContainer.windowId = var1.func_148901_c();
         break;
      case 6:
         var2.displayGUIMerchant(new NpcMerchant(var2), var1.func_148900_g()?var1.func_148902_e():null);
         var2.openContainer.windowId = var1.func_148901_c();
         break;
      case 7:
         TileEntityBeacon var8 = new TileEntityBeacon();
         var2.func_146104_a(var8);
         if(var1.func_148900_g()) {
            var8.func_145999_a(var1.func_148902_e());
         }

         var2.openContainer.windowId = var1.func_148901_c();
         break;
      case 8:
         var2.displayGUIAnvil(MathHelper.floor_double(var2.posX), MathHelper.floor_double(var2.posY), MathHelper.floor_double(var2.posZ));
         var2.openContainer.windowId = var1.func_148901_c();
         break;
      case 9:
         TileEntityHopper var3 = new TileEntityHopper();
         if(var1.func_148900_g()) {
            var3.func_145886_a(var1.func_148902_e());
         }

         var2.func_146093_a(var3);
         var2.openContainer.windowId = var1.func_148901_c();
         break;
      case 10:
         TileEntityDropper var6 = new TileEntityDropper();
         if(var1.func_148900_g()) {
            var6.func_146018_a(var1.func_148902_e());
         }

         var2.func_146102_a(var6);
         var2.openContainer.windowId = var1.func_148901_c();
         break;
      case 11:
         Entity var9 = this.clientWorldController.getEntityByID(var1.func_148897_h());
         if(var9 != null && var9 instanceof EntityHorse) {
            var2.displayGUIHorse((EntityHorse)var9, new AnimalChest(var1.func_148902_e(), var1.func_148900_g(), var1.func_148898_f()));
            var2.openContainer.windowId = var1.func_148901_c();
         }
      }

   }

   public void handleSetSlot(S2FPacketSetSlot var1) {
      EntityClientPlayerMP var2 = this.gameController.thePlayer;
      if(var1.func_149175_c() == -1) {
         var2.inventory.setItemStack(var1.func_149174_e());
      } else {
         boolean var3 = false;
         if(this.gameController.currentScreen instanceof GuiContainerCreative) {
            GuiContainerCreative var4 = (GuiContainerCreative)this.gameController.currentScreen;
            var3 = var4.func_147056_g() != CreativeTabs.tabInventory.getTabIndex();
         }

         if(var1.func_149175_c() == 0 && var1.func_149173_d() >= 36 && var1.func_149173_d() < 45) {
            ItemStack var5 = var2.inventoryContainer.getSlot(var1.func_149173_d()).getStack();
            if(var1.func_149174_e() != null && (var5 == null || var5.stackSize < var1.func_149174_e().stackSize)) {
               var1.func_149174_e().animationsToGo = 5;
            }

            var2.inventoryContainer.putStackInSlot(var1.func_149173_d(), var1.func_149174_e());
         } else if(var1.func_149175_c() == var2.openContainer.windowId && (var1.func_149175_c() != 0 || !var3)) {
            var2.openContainer.putStackInSlot(var1.func_149173_d(), var1.func_149174_e());
         }
      }

   }

   public void handleConfirmTransaction(S32PacketConfirmTransaction var1) {
      Container var2 = null;
      EntityClientPlayerMP var3 = this.gameController.thePlayer;
      if(var1.func_148889_c() == 0) {
         var2 = var3.inventoryContainer;
      } else if(var1.func_148889_c() == var3.openContainer.windowId) {
         var2 = var3.openContainer;
      }

      if(var2 != null && !var1.func_148888_e()) {
         this.addToSendQueue(new C0FPacketConfirmTransaction(var1.func_148889_c(), var1.func_148890_d(), true));
      }

   }

   public void handleWindowItems(S30PacketWindowItems var1) {
      EntityClientPlayerMP var2 = this.gameController.thePlayer;
      if(var1.func_148911_c() == 0) {
         var2.inventoryContainer.putStacksInSlots(var1.func_148910_d());
      } else if(var1.func_148911_c() == var2.openContainer.windowId) {
         var2.openContainer.putStacksInSlots(var1.func_148910_d());
      }

   }

   public void handleSignEditorOpen(S36PacketSignEditorOpen var1) {
      Object var2 = this.clientWorldController.getTileEntity(var1.func_149129_c(), var1.func_149128_d(), var1.func_149127_e());
      if(var2 == null) {
         var2 = new TileEntitySign();
         ((TileEntity)var2).setWorldObj(this.clientWorldController);
         ((TileEntity)var2).xCoord = var1.func_149129_c();
         ((TileEntity)var2).yCoord = var1.func_149128_d();
         ((TileEntity)var2).zCoord = var1.func_149127_e();
      }

      this.gameController.thePlayer.func_146100_a((TileEntity)var2);
   }

   public void handleUpdateSign(S33PacketUpdateSign var1) {
      boolean var2 = false;
      if(this.gameController.theWorld.blockExists(var1.func_149346_c(), var1.func_149345_d(), var1.func_149344_e())) {
         TileEntity var3 = this.gameController.theWorld.getTileEntity(var1.func_149346_c(), var1.func_149345_d(), var1.func_149344_e());
         if(var3 instanceof TileEntitySign) {
            TileEntitySign var4 = (TileEntitySign)var3;
            if(var4.func_145914_a()) {
               for(int var5 = 0; var5 < 4; ++var5) {
                  var4.signText[var5] = var1.func_149347_f()[var5];
               }

               var4.markDirty();
            }

            var2 = true;
         }
      }

      if(!var2 && this.gameController.thePlayer != null) {
         this.gameController.thePlayer.addChatMessage(new ChatComponentText("Unable to locate sign at " + var1.func_149346_c() + ", " + var1.func_149345_d() + ", " + var1.func_149344_e()));
      }

   }

   public void handleUpdateTileEntity(S35PacketUpdateTileEntity var1) {
      if(this.gameController.theWorld.blockExists(var1.func_148856_c(), var1.func_148855_d(), var1.func_148854_e())) {
         TileEntity var2 = this.gameController.theWorld.getTileEntity(var1.func_148856_c(), var1.func_148855_d(), var1.func_148854_e());
         if(var2 != null) {
            if(var1.func_148853_f() == 1 && var2 instanceof TileEntityMobSpawner) {
               var2.readFromNBT(var1.func_148857_g());
            } else if(var1.func_148853_f() == 2 && var2 instanceof TileEntityCommandBlock) {
               var2.readFromNBT(var1.func_148857_g());
            } else if(var1.func_148853_f() == 3 && var2 instanceof TileEntityBeacon) {
               var2.readFromNBT(var1.func_148857_g());
            } else if(var1.func_148853_f() == 4 && var2 instanceof TileEntitySkull) {
               var2.readFromNBT(var1.func_148857_g());
            } else if(var1.func_148853_f() == 5 && var2 instanceof TileEntityFlowerPot) {
               var2.readFromNBT(var1.func_148857_g());
            }
         }
      }

   }

   public void handleWindowProperty(S31PacketWindowProperty var1) {
      EntityClientPlayerMP var2 = this.gameController.thePlayer;
      if(var2.openContainer != null && var2.openContainer.windowId == var1.func_149182_c()) {
         var2.openContainer.updateProgressBar(var1.func_149181_d(), var1.func_149180_e());
      }

   }

   public void handleEntityEquipment(S04PacketEntityEquipment var1) {
      Entity var2 = this.clientWorldController.getEntityByID(var1.func_149389_d());
      if(var2 != null) {
         var2.setCurrentItemOrArmor(var1.func_149388_e(), var1.func_149390_c());
      }

   }

   public void handleCloseWindow(S2EPacketCloseWindow var1) {
      this.gameController.thePlayer.closeScreenNoPacket();
   }

   public void handleBlockAction(S24PacketBlockAction var1) {
      this.gameController.theWorld.addBlockEvent(var1.func_148867_d(), var1.func_148866_e(), var1.func_148865_f(), var1.func_148868_c(), var1.func_148869_g(), var1.func_148864_h());
   }

   public void handleBlockBreakAnim(S25PacketBlockBreakAnim var1) {
      this.gameController.theWorld.destroyBlockInWorldPartially(var1.func_148845_c(), var1.func_148844_d(), var1.func_148843_e(), var1.func_148842_f(), var1.func_148846_g());
   }

   public void handleMapChunkBulk(S26PacketMapChunkBulk var1) {
      for(int var2 = 0; var2 < var1.func_149254_d(); ++var2) {
         int var3 = var1.func_149255_a(var2);
         int var4 = var1.func_149253_b(var2);
         this.clientWorldController.doPreChunk(var3, var4, true);
         this.clientWorldController.invalidateBlockReceiveRegion(var3 << 4, 0, var4 << 4, (var3 << 4) + 15, 256, (var4 << 4) + 15);
         Chunk var5 = this.clientWorldController.getChunkFromChunkCoords(var3, var4);
         var5.fillChunk(var1.func_149256_c(var2), var1.func_149252_e()[var2], var1.func_149257_f()[var2], true);
         this.clientWorldController.markBlockRangeForRenderUpdate(var3 << 4, 0, var4 << 4, (var3 << 4) + 15, 256, (var4 << 4) + 15);
         if(!(this.clientWorldController.provider instanceof WorldProviderSurface)) {
            var5.resetRelightChecks();
         }
      }

   }

   public void handleChangeGameState(S2BPacketChangeGameState var1) {
      EntityClientPlayerMP var2 = this.gameController.thePlayer;
      int var3 = var1.func_149138_c();
      float var4 = var1.func_149137_d();
      int var5 = MathHelper.floor_float(var4 + 0.5F);
      if(var3 >= 0 && var3 < S2BPacketChangeGameState.field_149142_a.length && S2BPacketChangeGameState.field_149142_a[var3] != null) {
         var2.addChatComponentMessage(new ChatComponentTranslation(S2BPacketChangeGameState.field_149142_a[var3], new Object[0]));
      }

      if(var3 == 1) {
         this.clientWorldController.getWorldInfo().setRaining(true);
         this.clientWorldController.setRainStrength(0.0F);
      } else if(var3 == 2) {
         this.clientWorldController.getWorldInfo().setRaining(false);
         this.clientWorldController.setRainStrength(1.0F);
      } else if(var3 == 3) {
         this.gameController.playerController.setGameType(WorldSettings$GameType.getByID(var5));
      } else if(var3 == 4) {
         this.gameController.displayGuiScreen(new GuiWinGame());
      } else if(var3 == 5) {
         GameSettings var6 = this.gameController.gameSettings;
         if(var4 == 0.0F) {
            this.gameController.displayGuiScreen(new GuiScreenDemo());
         } else if(var4 == 101.0F) {
            this.gameController.ingameGUI.getChatGUI().printChatMessage(new ChatComponentTranslation("demo.help.movement", new Object[]{GameSettings.getKeyDisplayString(var6.keyBindForward.getKeyCode()), GameSettings.getKeyDisplayString(var6.keyBindLeft.getKeyCode()), GameSettings.getKeyDisplayString(var6.keyBindBack.getKeyCode()), GameSettings.getKeyDisplayString(var6.keyBindRight.getKeyCode())}));
         } else if(var4 == 102.0F) {
            this.gameController.ingameGUI.getChatGUI().printChatMessage(new ChatComponentTranslation("demo.help.jump", new Object[]{GameSettings.getKeyDisplayString(var6.keyBindJump.getKeyCode())}));
         } else if(var4 == 103.0F) {
            this.gameController.ingameGUI.getChatGUI().printChatMessage(new ChatComponentTranslation("demo.help.inventory", new Object[]{GameSettings.getKeyDisplayString(var6.keyBindInventory.getKeyCode())}));
         }
      } else if(var3 == 6) {
         this.clientWorldController.playSound(var2.posX, var2.posY + (double)var2.getEyeHeight(), var2.posZ, "random.successful_hit", 0.18F, 0.45F, false);
      } else if(var3 == 7) {
         this.clientWorldController.setRainStrength(var4);
      } else if(var3 == 8) {
         this.clientWorldController.setThunderStrength(var4);
      }

   }

   public void handleMaps(S34PacketMaps var1) {
      MapData var2 = ItemMap.func_150912_a(var1.func_149188_c(), this.gameController.theWorld);
      var2.updateMPMapData(var1.func_149187_d());
      this.gameController.entityRenderer.getMapItemRenderer().func_148246_a(var2);
   }

   public void handleEffect(S28PacketEffect var1) {
      if(var1.func_149244_c()) {
         this.gameController.theWorld.playBroadcastSound(var1.func_149242_d(), var1.func_149240_f(), var1.func_149243_g(), var1.func_149239_h(), var1.func_149241_e());
      } else {
         this.gameController.theWorld.playAuxSFX(var1.func_149242_d(), var1.func_149240_f(), var1.func_149243_g(), var1.func_149239_h(), var1.func_149241_e());
      }

   }

   public void handleStatistics(S37PacketStatistics var1) {
      boolean var2 = false;

      StatBase var5;
      int var6;
      for(Iterator var3 = var1.func_148974_c().entrySet().iterator(); var3.hasNext(); this.gameController.thePlayer.getStatFileWriter().func_150873_a(this.gameController.thePlayer, var5, var6)) {
         Entry var4 = (Entry)var3.next();
         var5 = (StatBase)var4.getKey();
         var6 = ((Integer)var4.getValue()).intValue();
         if(var5.isAchievement() && var6 > 0) {
            if(this.field_147308_k && this.gameController.thePlayer.getStatFileWriter().writeStat(var5) == 0) {
               Achievement var7 = (Achievement)var5;
               this.gameController.guiAchievement.func_146256_a(var7);
               this.gameController.func_152346_Z().func_152911_a(new MetadataAchievement(var7), 0L);
               if(var5 == AchievementList.openInventory) {
                  this.gameController.gameSettings.showInventoryAchievementHint = false;
                  this.gameController.gameSettings.saveOptions();
               }
            }

            var2 = true;
         }
      }

      if(!this.field_147308_k && !var2 && this.gameController.gameSettings.showInventoryAchievementHint) {
         this.gameController.guiAchievement.func_146255_b(AchievementList.openInventory);
      }

      this.field_147308_k = true;
      if(this.gameController.currentScreen instanceof IProgressMeter) {
         ((IProgressMeter)this.gameController.currentScreen).func_146509_g();
      }

   }

   public void handleEntityEffect(S1DPacketEntityEffect var1) {
      Entity var2 = this.clientWorldController.getEntityByID(var1.func_149426_d());
      if(var2 instanceof EntityLivingBase) {
         PotionEffect var3 = new PotionEffect(var1.func_149427_e(), var1.func_149425_g(), var1.func_149428_f());
         var3.setPotionDurationMax(var1.func_149429_c());
         ((EntityLivingBase)var2).addPotionEffect(var3);
      }
   }

   public void handleRemoveEntityEffect(S1EPacketRemoveEntityEffect var1) {
      Entity var2 = this.clientWorldController.getEntityByID(var1.func_149076_c());
      if(var2 instanceof EntityLivingBase) {
         ((EntityLivingBase)var2).removePotionEffectClient(var1.func_149075_d());
      }

   }

   public void handlePlayerListItem(S38PacketPlayerListItem var1) {
      GuiPlayerInfo var2 = (GuiPlayerInfo)this.playerInfoMap.get(var1.func_149122_c());
      if(var2 == null && var1.func_149121_d()) {
         var2 = new GuiPlayerInfo(var1.func_149122_c());
         this.playerInfoMap.put(var1.func_149122_c(), var2);
         this.playerInfoList.add(var2);
      }

      if(var2 != null && !var1.func_149121_d()) {
         this.playerInfoMap.remove(var1.func_149122_c());
         this.playerInfoList.remove(var2);
      }

      if(var2 != null && var1.func_149121_d()) {
         var2.responseTime = var1.func_149120_e();
      }

   }

   public void handleKeepAlive(S00PacketKeepAlive var1) {
      this.addToSendQueue(new C00PacketKeepAlive(var1.func_149134_c()));
   }

   public void onConnectionStateTransition(EnumConnectionState var1, EnumConnectionState var2) {
      throw new IllegalStateException("Unexpected protocol change!");
   }

   public void handlePlayerAbilities(S39PacketPlayerAbilities var1) {
      EntityClientPlayerMP var2 = this.gameController.thePlayer;
      var2.capabilities.isFlying = var1.func_149106_d();
      var2.capabilities.isCreativeMode = var1.func_149103_f();
      var2.capabilities.disableDamage = var1.func_149112_c();
      var2.capabilities.allowFlying = var1.func_149105_e();
      var2.capabilities.setFlySpeed(var1.func_149101_g());
      var2.capabilities.setPlayerWalkSpeed(var1.func_149107_h());
   }

   public void handleTabComplete(S3APacketTabComplete var1) {
      String[] var2 = var1.func_149630_c();
      if(this.gameController.currentScreen instanceof GuiChat) {
         GuiChat var3 = (GuiChat)this.gameController.currentScreen;
         var3.func_146406_a(var2);
      }

   }

   public void handleSoundEffect(S29PacketSoundEffect var1) {
      this.gameController.theWorld.playSound(var1.func_149207_d(), var1.func_149211_e(), var1.func_149210_f(), var1.func_149212_c(), var1.func_149208_g(), var1.func_149209_h(), false);
   }

   public void handleCustomPayload(S3FPacketCustomPayload var1) {
      if("MC|TrList".equals(var1.func_149169_c())) {
         ByteBuf var2 = Unpooled.wrappedBuffer(var1.func_149168_d());

         try {
            int var3 = var2.readInt();
            GuiScreen var4 = this.gameController.currentScreen;
            if(var4 != null && var4 instanceof GuiMerchant && var3 == this.gameController.thePlayer.openContainer.windowId) {
               IMerchant var5 = ((GuiMerchant)var4).func_147035_g();
               MerchantRecipeList var6 = MerchantRecipeList.func_151390_b(new PacketBuffer(var2));
               var5.setRecipes(var6);
            }
         } catch (IOException var10) {
            logger.error("Couldn\'t load trade info", var10);
         } finally {
            var2.release();
         }
      } else if("MC|Brand".equals(var1.func_149169_c())) {
         this.gameController.thePlayer.func_142020_c(new String(var1.func_149168_d(), Charsets.UTF_8));
      } else if("MC|RPack".equals(var1.func_149169_c())) {
         String var12 = new String(var1.func_149168_d(), Charsets.UTF_8);
         if(this.gameController.func_147104_D() != null && this.gameController.func_147104_D().func_152586_b() == ServerData$ServerResourceMode.ENABLED) {
            this.gameController.getResourcePackRepository().func_148526_a(var12);
         } else if(this.gameController.func_147104_D() == null || this.gameController.func_147104_D().func_152586_b() == ServerData$ServerResourceMode.PROMPT) {
            this.gameController.displayGuiScreen(new GuiYesNo(new NetHandlerPlayClient$1(this, var12), I18n.format("multiplayer.texturePrompt.line1", new Object[0]), I18n.format("multiplayer.texturePrompt.line2", new Object[0]), 0));
         }
      }

   }

   public void handleScoreboardObjective(S3BPacketScoreboardObjective var1) {
      Scoreboard var2 = this.clientWorldController.getScoreboard();
      ScoreObjective var3;
      if(var1.func_149338_e() == 0) {
         var3 = var2.addScoreObjective(var1.func_149339_c(), IScoreObjectiveCriteria.field_96641_b);
         var3.setDisplayName(var1.func_149337_d());
      } else {
         var3 = var2.getObjective(var1.func_149339_c());
         if(var1.func_149338_e() == 1) {
            var2.func_96519_k(var3);
         } else if(var1.func_149338_e() == 2) {
            var3.setDisplayName(var1.func_149337_d());
         }
      }

   }

   public void handleUpdateScore(S3CPacketUpdateScore var1) {
      Scoreboard var2 = this.clientWorldController.getScoreboard();
      ScoreObjective var3 = var2.getObjective(var1.func_149321_d());
      if(var1.func_149322_f() == 0) {
         Score var4 = var2.func_96529_a(var1.func_149324_c(), var3);
         var4.setScorePoints(var1.func_149323_e());
      } else if(var1.func_149322_f() == 1) {
         var2.func_96515_c(var1.func_149324_c());
      }

   }

   public void handleDisplayScoreboard(S3DPacketDisplayScoreboard var1) {
      Scoreboard var2 = this.clientWorldController.getScoreboard();
      if(var1.func_149370_d().length() == 0) {
         var2.func_96530_a(var1.func_149371_c(), (ScoreObjective)null);
      } else {
         ScoreObjective var3 = var2.getObjective(var1.func_149370_d());
         var2.func_96530_a(var1.func_149371_c(), var3);
      }

   }

   public void handleTeams(S3EPacketTeams var1) {
      Scoreboard var2 = this.clientWorldController.getScoreboard();
      ScorePlayerTeam var3;
      if(var1.func_149307_h() == 0) {
         var3 = var2.createTeam(var1.func_149312_c());
      } else {
         var3 = var2.getTeam(var1.func_149312_c());
      }

      if(var1.func_149307_h() == 0 || var1.func_149307_h() == 2) {
         var3.setTeamName(var1.func_149306_d());
         var3.setNamePrefix(var1.func_149311_e());
         var3.setNameSuffix(var1.func_149309_f());
         var3.func_98298_a(var1.func_149308_i());
      }

      Iterator var4;
      String var5;
      if(var1.func_149307_h() == 0 || var1.func_149307_h() == 3) {
         var4 = var1.func_149310_g().iterator();

         while(var4.hasNext()) {
            var5 = (String)var4.next();
            var2.func_151392_a(var5, var1.func_149312_c());
         }
      }

      if(var1.func_149307_h() == 4) {
         var4 = var1.func_149310_g().iterator();

         while(var4.hasNext()) {
            var5 = (String)var4.next();
            var2.removePlayerFromTeam(var5, var3);
         }
      }

      if(var1.func_149307_h() == 1) {
         var2.removeTeam(var3);
      }

   }

   public void handleParticles(S2APacketParticles var1) {
      if(var1.func_149222_k() == 0) {
         double var2 = (double)(var1.func_149227_j() * var1.func_149221_g());
         double var4 = (double)(var1.func_149227_j() * var1.func_149224_h());
         double var6 = (double)(var1.func_149227_j() * var1.func_149223_i());
         this.clientWorldController.spawnParticle(var1.func_149228_c(), var1.func_149220_d(), var1.func_149226_e(), var1.func_149225_f(), var2, var4, var6);
      } else {
         for(int var15 = 0; var15 < var1.func_149222_k(); ++var15) {
            double var3 = this.avRandomizer.nextGaussian() * (double)var1.func_149221_g();
            double var5 = this.avRandomizer.nextGaussian() * (double)var1.func_149224_h();
            double var7 = this.avRandomizer.nextGaussian() * (double)var1.func_149223_i();
            double var9 = this.avRandomizer.nextGaussian() * (double)var1.func_149227_j();
            double var11 = this.avRandomizer.nextGaussian() * (double)var1.func_149227_j();
            double var13 = this.avRandomizer.nextGaussian() * (double)var1.func_149227_j();
            this.clientWorldController.spawnParticle(var1.func_149228_c(), var1.func_149220_d() + var3, var1.func_149226_e() + var5, var1.func_149225_f() + var7, var9, var11, var13);
         }
      }

   }

   public void handleEntityProperties(S20PacketEntityProperties var1) {
      Entity var2 = this.clientWorldController.getEntityByID(var1.func_149442_c());
      if(var2 != null) {
         if(!(var2 instanceof EntityLivingBase)) {
            throw new IllegalStateException("Server tried to update attributes of a non-living entity (actually: " + var2 + ")");
         } else {
            BaseAttributeMap var3 = ((EntityLivingBase)var2).getAttributeMap();
            Iterator var4 = var1.func_149441_d().iterator();

            while(var4.hasNext()) {
               S20PacketEntityProperties$Snapshot var5 = (S20PacketEntityProperties$Snapshot)var4.next();
               IAttributeInstance var6 = var3.getAttributeInstanceByName(var5.func_151409_a());
               if(var6 == null) {
                  var6 = var3.registerAttribute(new RangedAttribute(var5.func_151409_a(), 0.0D, 2.2250738585072014E-308D, Double.MAX_VALUE));
               }

               var6.setBaseValue(var5.func_151410_b());
               var6.removeAllModifiers();
               Iterator var7 = var5.func_151408_c().iterator();

               while(var7.hasNext()) {
                  AttributeModifier var8 = (AttributeModifier)var7.next();
                  var6.applyModifier(var8);
               }
            }

         }
      }
   }

   public NetworkManager getNetworkManager() {
      return this.netManager;
   }

   // $FF: synthetic method
   static Minecraft access$002(NetHandlerPlayClient var0, Minecraft var1) {
      return var0.gameController = var1;
   }

   // $FF: synthetic method
   static Minecraft access$000(NetHandlerPlayClient var0) {
      return var0.gameController;
   }

}
