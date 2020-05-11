package net.minecraft.client;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import io.netty.util.concurrent.GenericFutureListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft$1;
import net.minecraft.client.Minecraft$10;
import net.minecraft.client.Minecraft$11;
import net.minecraft.client.Minecraft$12;
import net.minecraft.client.Minecraft$13;
import net.minecraft.client.Minecraft$14;
import net.minecraft.client.Minecraft$15;
import net.minecraft.client.Minecraft$16;
import net.minecraft.client.Minecraft$2;
import net.minecraft.client.Minecraft$3;
import net.minecraft.client.Minecraft$4;
import net.minecraft.client.Minecraft$5;
import net.minecraft.client.Minecraft$6;
import net.minecraft.client.Minecraft$7;
import net.minecraft.client.Minecraft$8;
import net.minecraft.client.Minecraft$9;
import net.minecraft.client.Minecraft$SwitchMovingObjectType;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.MusicTicker$MusicType;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMemoryErrorScreen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSleepMP;
import net.minecraft.client.gui.GuiWinGame;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.FoliageColorReloadListener;
import net.minecraft.client.resources.GrassColorReloadListener;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.ResourceIndex;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.ResourcePackRepository$Entry;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.data.FontMetadataSection;
import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.client.resources.data.LanguageMetadataSectionSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.client.resources.data.PackMetadataSectionSerializer;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSectionSerializer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.stream.IStream;
import net.minecraft.client.stream.NullStream;
import net.minecraft.client.stream.TwitchStream;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer$EnumChatVisibility;
import net.minecraft.init.Bootstrap;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C16PacketClientStatus$EnumState;
import net.minecraft.profiler.IPlayerUsage;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.profiler.Profiler;
import net.minecraft.profiler.Profiler$Result;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MinecraftError;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import net.minecraft.util.Util;
import net.minecraft.util.Util$EnumOS;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.OpenGLException;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;

public class Minecraft implements IPlayerUsage {

   private static final Logger logger = LogManager.getLogger();
   private static final ResourceLocation locationMojangPng = new ResourceLocation("textures/gui/title/mojang.png");
   public static final boolean isRunningOnMac = Util.getOSType() == Util$EnumOS.OSX;
   public static byte[] memoryReserve = new byte[10485760];
   private static final List macDisplayModes = Lists.newArrayList(new DisplayMode[]{new DisplayMode(2560, 1600), new DisplayMode(2880, 1800)});
   private final File fileResourcepacks;
   private final Multimap field_152356_J;
   private ServerData currentServerData;
   private TextureManager renderEngine;
   private static Minecraft theMinecraft;
   public PlayerControllerMP playerController;
   private boolean fullscreen;
   private boolean hasCrashed;
   private CrashReport crashReporter;
   public int displayWidth;
   public int displayHeight;
   private Timer timer = new Timer(20.0F);
   private PlayerUsageSnooper usageSnooper = new PlayerUsageSnooper("client", this, MinecraftServer.getSystemTimeMillis());
   public WorldClient theWorld;
   public RenderGlobal renderGlobal;
   public EntityClientPlayerMP thePlayer;
   public EntityLivingBase renderViewEntity;
   public Entity pointedEntity;
   public EffectRenderer effectRenderer;
   private final Session session;
   private boolean isGamePaused;
   public FontRenderer fontRenderer;
   public FontRenderer standardGalacticFontRenderer;
   public GuiScreen currentScreen;
   public LoadingScreenRenderer loadingScreen;
   public EntityRenderer entityRenderer;
   private int leftClickCounter;
   private int tempDisplayWidth;
   private int tempDisplayHeight;
   private IntegratedServer theIntegratedServer;
   public GuiAchievement guiAchievement;
   public GuiIngame ingameGUI;
   public boolean skipRenderWorld;
   public MovingObjectPosition objectMouseOver;
   public GameSettings gameSettings;
   public MouseHelper mouseHelper;
   public final File mcDataDir;
   private final File fileAssets;
   private final String launchedVersion;
   private final Proxy proxy;
   private ISaveFormat saveLoader;
   private static int debugFPS;
   private int rightClickDelayTimer;
   private boolean refreshTexturePacksScheduled;
   private String serverName;
   private int serverPort;
   public boolean inGameHasFocus;
   long systemTime = getSystemTime();
   private int joinPlayerCounter;
   private final boolean jvm64bit;
   private final boolean isDemo;
   private NetworkManager myNetworkManager;
   private boolean integratedServerIsRunning;
   public final Profiler mcProfiler = new Profiler();
   private long field_83002_am = -1L;
   private IReloadableResourceManager mcResourceManager;
   private final IMetadataSerializer metadataSerializer_ = new IMetadataSerializer();
   private List defaultResourcePacks = Lists.newArrayList();
   private DefaultResourcePack mcDefaultResourcePack;
   private ResourcePackRepository mcResourcePackRepository;
   private LanguageManager mcLanguageManager;
   private IStream field_152353_at;
   private Framebuffer framebufferMc;
   private TextureMap textureMapBlocks;
   private SoundHandler mcSoundHandler;
   private MusicTicker mcMusicTicker;
   private ResourceLocation field_152354_ay;
   private final MinecraftSessionService field_152355_az;
   private SkinManager field_152350_aA;
   private final Queue field_152351_aB = Queues.newArrayDeque();
   private final Thread field_152352_aC = Thread.currentThread();
   volatile boolean running = true;
   public String debug = "";
   long debugUpdateTime = getSystemTime();
   int fpsCounter;
   long prevFrameTime = -1L;
   private String debugProfilerName = "root";


   public Minecraft(Session var1, int var2, int var3, boolean var4, boolean var5, File var6, File var7, File var8, Proxy var9, String var10, Multimap var11, String var12) {
      theMinecraft = this;
      this.mcDataDir = var6;
      this.fileAssets = var7;
      this.fileResourcepacks = var8;
      this.launchedVersion = var10;
      this.field_152356_J = var11;
      this.mcDefaultResourcePack = new DefaultResourcePack((new ResourceIndex(var7, var12)).func_152782_a());
      this.addDefaultResourcePack();
      this.proxy = var9 == null?Proxy.NO_PROXY:var9;
      this.field_152355_az = (new YggdrasilAuthenticationService(var9, UUID.randomUUID().toString())).createMinecraftSessionService();
      this.startTimerHackThread();
      this.session = var1;
      logger.info("Setting user: " + var1.getUsername());
      logger.info("(Session ID is " + var1.getSessionID() + ")");
      this.isDemo = var5;
      this.displayWidth = var2;
      this.displayHeight = var3;
      this.tempDisplayWidth = var2;
      this.tempDisplayHeight = var3;
      this.fullscreen = var4;
      this.jvm64bit = isJvm64bit();
      ImageIO.setUseCache(false);
      Bootstrap.func_151354_b();
   }

   private static boolean isJvm64bit() {
      String[] var0 = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};
      String[] var1 = var0;
      int var2 = var0.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         String var4 = var1[var3];
         String var5 = System.getProperty(var4);
         if(var5 != null && var5.contains("64")) {
            return true;
         }
      }

      return false;
   }

   public Framebuffer getFramebuffer() {
      return this.framebufferMc;
   }

   private void startTimerHackThread() {
      Minecraft$1 var1 = new Minecraft$1(this, "Timer hack thread");
      var1.setDaemon(true);
      var1.start();
   }

   public void crashed(CrashReport var1) {
      this.hasCrashed = true;
      this.crashReporter = var1;
   }

   public void displayCrashReport(CrashReport var1) {
      File var2 = new File(getMinecraft().mcDataDir, "crash-reports");
      File var3 = new File(var2, "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-client.txt");
      System.out.println(var1.getCompleteReport());
      if(var1.getFile() != null) {
         System.out.println("#@!@# Game crashed! Crash report saved to: #@!@# " + var1.getFile());
         System.exit(-1);
      } else if(var1.saveToFile(var3)) {
         System.out.println("#@!@# Game crashed! Crash report saved to: #@!@# " + var3.getAbsolutePath());
         System.exit(-1);
      } else {
         System.out.println("#@?@# Game crashed! Crash report could not be saved. #@?@#");
         System.exit(-2);
      }

   }

   public void setServer(String var1, int var2) {
      this.serverName = var1;
      this.serverPort = var2;
   }

   private void startGame() {
      this.gameSettings = new GameSettings(this, this.mcDataDir);
      if(this.gameSettings.overrideHeight > 0 && this.gameSettings.overrideWidth > 0) {
         this.displayWidth = this.gameSettings.overrideWidth;
         this.displayHeight = this.gameSettings.overrideHeight;
      }

      if(this.fullscreen) {
         Display.setFullscreen(true);
         this.displayWidth = Display.getDisplayMode().getWidth();
         this.displayHeight = Display.getDisplayMode().getHeight();
         if(this.displayWidth <= 0) {
            this.displayWidth = 1;
         }

         if(this.displayHeight <= 0) {
            this.displayHeight = 1;
         }
      } else {
         Display.setDisplayMode(new DisplayMode(this.displayWidth, this.displayHeight));
      }

      Display.setResizable(true);
      Display.setTitle("Minecraft 1.7.10");
      logger.info("LWJGL Version: " + Sys.getVersion());
      Util$EnumOS var1 = Util.getOSType();
      if(var1 != Util$EnumOS.OSX) {
         try {
            InputStream var2 = this.mcDefaultResourcePack.func_152780_c(new ResourceLocation("icons/icon_16x16.png"));
            InputStream var3 = this.mcDefaultResourcePack.func_152780_c(new ResourceLocation("icons/icon_32x32.png"));
            if(var2 != null && var3 != null) {
               Display.setIcon(new ByteBuffer[]{this.func_152340_a(var2), this.func_152340_a(var3)});
            }
         } catch (IOException var8) {
            logger.error("Couldn\'t set icon", var8);
         }
      }

      try {
         Display.create((new PixelFormat()).withDepthBits(24));
      } catch (LWJGLException var7) {
         logger.error("Couldn\'t set pixel format", var7);

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException var6) {
            ;
         }

         if(this.fullscreen) {
            this.updateDisplayMode();
         }

         Display.create();
      }

      OpenGlHelper.initializeTextures();

      try {
         this.field_152353_at = new TwitchStream(this, (String)Iterables.getFirst(this.field_152356_J.get("twitch_access_token"), (Object)null));
      } catch (Throwable var5) {
         this.field_152353_at = new NullStream(var5);
         logger.error("Couldn\'t initialize twitch stream");
      }

      this.framebufferMc = new Framebuffer(this.displayWidth, this.displayHeight, true);
      this.framebufferMc.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
      this.guiAchievement = new GuiAchievement(this);
      this.metadataSerializer_.registerMetadataSectionType(new TextureMetadataSectionSerializer(), TextureMetadataSection.class);
      this.metadataSerializer_.registerMetadataSectionType(new FontMetadataSectionSerializer(), FontMetadataSection.class);
      this.metadataSerializer_.registerMetadataSectionType(new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
      this.metadataSerializer_.registerMetadataSectionType(new PackMetadataSectionSerializer(), PackMetadataSection.class);
      this.metadataSerializer_.registerMetadataSectionType(new LanguageMetadataSectionSerializer(), LanguageMetadataSection.class);
      this.saveLoader = new AnvilSaveConverter(new File(this.mcDataDir, "saves"));
      this.mcResourcePackRepository = new ResourcePackRepository(this.fileResourcepacks, new File(this.mcDataDir, "server-resource-packs"), this.mcDefaultResourcePack, this.metadataSerializer_, this.gameSettings);
      this.mcResourceManager = new SimpleReloadableResourceManager(this.metadataSerializer_);
      this.mcLanguageManager = new LanguageManager(this.metadataSerializer_, this.gameSettings.language);
      this.mcResourceManager.registerReloadListener(this.mcLanguageManager);
      this.refreshResources();
      this.renderEngine = new TextureManager(this.mcResourceManager);
      this.mcResourceManager.registerReloadListener(this.renderEngine);
      this.field_152350_aA = new SkinManager(this.renderEngine, new File(this.fileAssets, "skins"), this.field_152355_az);
      this.loadScreen();
      this.mcSoundHandler = new SoundHandler(this.mcResourceManager, this.gameSettings);
      this.mcResourceManager.registerReloadListener(this.mcSoundHandler);
      this.mcMusicTicker = new MusicTicker(this);
      this.fontRenderer = new FontRenderer(this.gameSettings, new ResourceLocation("textures/font/ascii.png"), this.renderEngine, false);
      if(this.gameSettings.language != null) {
         this.fontRenderer.setUnicodeFlag(this.func_152349_b());
         this.fontRenderer.setBidiFlag(this.mcLanguageManager.isCurrentLanguageBidirectional());
      }

      this.standardGalacticFontRenderer = new FontRenderer(this.gameSettings, new ResourceLocation("textures/font/ascii_sga.png"), this.renderEngine, false);
      this.mcResourceManager.registerReloadListener(this.fontRenderer);
      this.mcResourceManager.registerReloadListener(this.standardGalacticFontRenderer);
      this.mcResourceManager.registerReloadListener(new GrassColorReloadListener());
      this.mcResourceManager.registerReloadListener(new FoliageColorReloadListener());
      RenderManager.instance.itemRenderer = new ItemRenderer(this);
      this.entityRenderer = new EntityRenderer(this, this.mcResourceManager);
      this.mcResourceManager.registerReloadListener(this.entityRenderer);
      AchievementList.openInventory.setStatStringFormatter(new Minecraft$2(this));
      this.mouseHelper = new MouseHelper();
      this.checkGLError("Pre startup");
      GL11.glEnable(3553);
      GL11.glShadeModel(7425);
      GL11.glClearDepth(1.0D);
      GL11.glEnable(2929);
      GL11.glDepthFunc(515);
      GL11.glEnable(3008);
      GL11.glAlphaFunc(516, 0.1F);
      GL11.glCullFace(1029);
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      GL11.glMatrixMode(5888);
      this.checkGLError("Startup");
      this.renderGlobal = new RenderGlobal(this);
      this.textureMapBlocks = new TextureMap(0, "textures/blocks");
      this.textureMapBlocks.setAnisotropicFiltering(this.gameSettings.anisotropicFiltering);
      this.textureMapBlocks.setMipmapLevels(this.gameSettings.mipmapLevels);
      this.renderEngine.loadTextureMap(TextureMap.locationBlocksTexture, this.textureMapBlocks);
      this.renderEngine.loadTextureMap(TextureMap.locationItemsTexture, new TextureMap(1, "textures/items"));
      GL11.glViewport(0, 0, this.displayWidth, this.displayHeight);
      this.effectRenderer = new EffectRenderer(this.theWorld, this.renderEngine);
      this.checkGLError("Post startup");
      this.ingameGUI = new GuiIngame(this);
      if(this.serverName != null) {
         this.displayGuiScreen(new GuiConnecting(new GuiMainMenu(), this, this.serverName, this.serverPort));
      } else {
         this.displayGuiScreen(new GuiMainMenu());
      }

      this.renderEngine.deleteTexture(this.field_152354_ay);
      this.field_152354_ay = null;
      this.loadingScreen = new LoadingScreenRenderer(this);
      if(this.gameSettings.fullScreen && !this.fullscreen) {
         this.toggleFullscreen();
      }

      try {
         Display.setVSyncEnabled(this.gameSettings.enableVsync);
      } catch (OpenGLException var4) {
         this.gameSettings.enableVsync = false;
         this.gameSettings.saveOptions();
      }

   }

   public boolean func_152349_b() {
      return this.mcLanguageManager.isCurrentLocaleUnicode() || this.gameSettings.forceUnicodeFont;
   }

   public void refreshResources() {
      ArrayList var1 = Lists.newArrayList(this.defaultResourcePacks);
      Iterator var2 = this.mcResourcePackRepository.getRepositoryEntries().iterator();

      while(var2.hasNext()) {
         ResourcePackRepository$Entry var3 = (ResourcePackRepository$Entry)var2.next();
         var1.add(var3.getResourcePack());
      }

      if(this.mcResourcePackRepository.func_148530_e() != null) {
         var1.add(this.mcResourcePackRepository.func_148530_e());
      }

      try {
         this.mcResourceManager.reloadResources(var1);
      } catch (RuntimeException var4) {
         logger.info("Caught error stitching, removing all assigned resourcepacks", var4);
         var1.clear();
         var1.addAll(this.defaultResourcePacks);
         this.mcResourcePackRepository.func_148527_a(Collections.emptyList());
         this.mcResourceManager.reloadResources(var1);
         this.gameSettings.resourcePacks.clear();
         this.gameSettings.saveOptions();
      }

      this.mcLanguageManager.parseLanguageMetadata(var1);
      if(this.renderGlobal != null) {
         this.renderGlobal.loadRenderers();
      }

   }

   private void addDefaultResourcePack() {
      this.defaultResourcePacks.add(this.mcDefaultResourcePack);
   }

   private ByteBuffer func_152340_a(InputStream var1) {
      BufferedImage var2 = ImageIO.read(var1);
      int[] var3 = var2.getRGB(0, 0, var2.getWidth(), var2.getHeight(), (int[])null, 0, var2.getWidth());
      ByteBuffer var4 = ByteBuffer.allocate(4 * var3.length);
      int[] var5 = var3;
      int var6 = var3.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         int var8 = var5[var7];
         var4.putInt(var8 << 8 | var8 >> 24 & 255);
      }

      var4.flip();
      return var4;
   }

   private void updateDisplayMode() {
      HashSet var1 = new HashSet();
      Collections.addAll(var1, Display.getAvailableDisplayModes());
      DisplayMode var2 = Display.getDesktopDisplayMode();
      if(!var1.contains(var2) && Util.getOSType() == Util$EnumOS.OSX) {
         Iterator var3 = macDisplayModes.iterator();

         while(var3.hasNext()) {
            DisplayMode var4 = (DisplayMode)var3.next();
            boolean var5 = true;
            Iterator var6 = var1.iterator();

            DisplayMode var7;
            while(var6.hasNext()) {
               var7 = (DisplayMode)var6.next();
               if(var7.getBitsPerPixel() == 32 && var7.getWidth() == var4.getWidth() && var7.getHeight() == var4.getHeight()) {
                  var5 = false;
                  break;
               }
            }

            if(!var5) {
               var6 = var1.iterator();

               while(var6.hasNext()) {
                  var7 = (DisplayMode)var6.next();
                  if(var7.getBitsPerPixel() == 32 && var7.getWidth() == var4.getWidth() / 2 && var7.getHeight() == var4.getHeight() / 2) {
                     var2 = var7;
                     break;
                  }
               }
            }
         }
      }

      Display.setDisplayMode(var2);
      this.displayWidth = var2.getWidth();
      this.displayHeight = var2.getHeight();
   }

   private void loadScreen() {
      ScaledResolution var1 = new ScaledResolution(this, this.displayWidth, this.displayHeight);
      int var2 = var1.getScaleFactor();
      Framebuffer var3 = new Framebuffer(var1.getScaledWidth() * var2, var1.getScaledHeight() * var2, true);
      var3.bindFramebuffer(false);
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      GL11.glOrtho(0.0D, (double)var1.getScaledWidth(), (double)var1.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
      GL11.glDisable(2896);
      GL11.glDisable(2912);
      GL11.glDisable(2929);
      GL11.glEnable(3553);

      try {
         this.field_152354_ay = this.renderEngine.getDynamicTextureLocation("logo", new DynamicTexture(ImageIO.read(this.mcDefaultResourcePack.getInputStream(locationMojangPng))));
         this.renderEngine.bindTexture(this.field_152354_ay);
      } catch (IOException var7) {
         logger.error("Unable to load logo: " + locationMojangPng, var7);
      }

      Tessellator var4 = Tessellator.instance;
      var4.startDrawingQuads();
      var4.setColorOpaque_I(16777215);
      var4.addVertexWithUV(0.0D, (double)this.displayHeight, 0.0D, 0.0D, 0.0D);
      var4.addVertexWithUV((double)this.displayWidth, (double)this.displayHeight, 0.0D, 0.0D, 0.0D);
      var4.addVertexWithUV((double)this.displayWidth, 0.0D, 0.0D, 0.0D, 0.0D);
      var4.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      var4.draw();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      var4.setColorOpaque_I(16777215);
      short var5 = 256;
      short var6 = 256;
      this.scaledTessellator((var1.getScaledWidth() - var5) / 2, (var1.getScaledHeight() - var6) / 2, 0, 0, var5, var6);
      GL11.glDisable(2896);
      GL11.glDisable(2912);
      var3.unbindFramebuffer();
      var3.framebufferRender(var1.getScaledWidth() * var2, var1.getScaledHeight() * var2);
      GL11.glEnable(3008);
      GL11.glAlphaFunc(516, 0.1F);
      GL11.glFlush();
      this.func_147120_f();
   }

   public void scaledTessellator(int var1, int var2, int var3, int var4, int var5, int var6) {
      float var7 = 0.00390625F;
      float var8 = 0.00390625F;
      Tessellator var9 = Tessellator.instance;
      var9.startDrawingQuads();
      var9.addVertexWithUV((double)(var1 + 0), (double)(var2 + var6), 0.0D, (double)((float)(var3 + 0) * var7), (double)((float)(var4 + var6) * var8));
      var9.addVertexWithUV((double)(var1 + var5), (double)(var2 + var6), 0.0D, (double)((float)(var3 + var5) * var7), (double)((float)(var4 + var6) * var8));
      var9.addVertexWithUV((double)(var1 + var5), (double)(var2 + 0), 0.0D, (double)((float)(var3 + var5) * var7), (double)((float)(var4 + 0) * var8));
      var9.addVertexWithUV((double)(var1 + 0), (double)(var2 + 0), 0.0D, (double)((float)(var3 + 0) * var7), (double)((float)(var4 + 0) * var8));
      var9.draw();
   }

   public ISaveFormat getSaveLoader() {
      return this.saveLoader;
   }

   public void displayGuiScreen(GuiScreen var1) {
      if(this.currentScreen != null) {
         this.currentScreen.onGuiClosed();
      }

      if(var1 == null && this.theWorld == null) {
         var1 = new GuiMainMenu();
      } else if(var1 == null && this.thePlayer.getHealth() <= 0.0F) {
         var1 = new GuiGameOver();
      }

      if(var1 instanceof GuiMainMenu) {
         this.gameSettings.showDebugInfo = false;
         this.ingameGUI.getChatGUI().clearChatMessages();
      }

      this.currentScreen = (GuiScreen)var1;
      if(var1 != null) {
         this.setIngameNotInFocus();
         ScaledResolution var2 = new ScaledResolution(this, this.displayWidth, this.displayHeight);
         int var3 = var2.getScaledWidth();
         int var4 = var2.getScaledHeight();
         ((GuiScreen)var1).setWorldAndResolution(this, var3, var4);
         this.skipRenderWorld = false;
      } else {
         this.mcSoundHandler.resumeSounds();
         this.setIngameFocus();
      }

   }

   private void checkGLError(String var1) {
      int var2 = GL11.glGetError();
      if(var2 != 0) {
         String var3 = GLU.gluErrorString(var2);
         logger.error("########## GL ERROR ##########");
         logger.error("@ " + var1);
         logger.error(var2 + ": " + var3);
      }

   }

   public void shutdownMinecraftApplet() {
      try {
         this.field_152353_at.func_152923_i();
         logger.info("Stopping!");

         try {
            this.loadWorld((WorldClient)null);
         } catch (Throwable var7) {
            ;
         }

         try {
            GLAllocation.deleteTexturesAndDisplayLists();
         } catch (Throwable var6) {
            ;
         }

         this.mcSoundHandler.unloadSounds();
      } finally {
         Display.destroy();
         if(!this.hasCrashed) {
            System.exit(0);
         }

      }

      System.gc();
   }

   public void run() {
      this.running = true;

      CrashReport var2;
      try {
         this.startGame();
      } catch (Throwable var11) {
         var2 = CrashReport.makeCrashReport(var11, "Initializing game");
         var2.makeCategory("Initialization");
         this.displayCrashReport(this.addGraphicsAndWorldToCrashReport(var2));
         return;
      }

      while(true) {
         try {
            if(this.running) {
               if(!this.hasCrashed || this.crashReporter == null) {
                  try {
                     this.runGameLoop();
                  } catch (OutOfMemoryError var10) {
                     this.freeMemory();
                     this.displayGuiScreen(new GuiMemoryErrorScreen());
                     System.gc();
                  }
                  continue;
               }

               this.displayCrashReport(this.crashReporter);
               return;
            }
         } catch (MinecraftError var12) {
            ;
         } catch (ReportedException var13) {
            this.addGraphicsAndWorldToCrashReport(var13.getCrashReport());
            this.freeMemory();
            logger.fatal("Reported exception thrown!", var13);
            this.displayCrashReport(var13.getCrashReport());
         } catch (Throwable var14) {
            var2 = this.addGraphicsAndWorldToCrashReport(new CrashReport("Unexpected error", var14));
            this.freeMemory();
            logger.fatal("Unreported exception thrown!", var14);
            this.displayCrashReport(var2);
         } finally {
            this.shutdownMinecraftApplet();
         }

         return;
      }
   }

   private void runGameLoop() {
      this.mcProfiler.startSection("root");
      if(Display.isCreated() && Display.isCloseRequested()) {
         this.shutdown();
      }

      if(this.isGamePaused && this.theWorld != null) {
         float var1 = this.timer.renderPartialTicks;
         this.timer.updateTimer();
         this.timer.renderPartialTicks = var1;
      } else {
         this.timer.updateTimer();
      }

      if((this.theWorld == null || this.currentScreen == null) && this.refreshTexturePacksScheduled) {
         this.refreshTexturePacksScheduled = false;
         this.refreshResources();
      }

      long var5 = System.nanoTime();
      this.mcProfiler.startSection("tick");

      for(int var3 = 0; var3 < this.timer.elapsedTicks; ++var3) {
         this.runTick();
      }

      this.mcProfiler.endStartSection("preRenderErrors");
      long var6 = System.nanoTime() - var5;
      this.checkGLError("Pre render");
      RenderBlocks.fancyGrass = this.gameSettings.fancyGraphics;
      this.mcProfiler.endStartSection("sound");
      this.mcSoundHandler.setListener(this.thePlayer, this.timer.renderPartialTicks);
      this.mcProfiler.endSection();
      this.mcProfiler.startSection("render");
      GL11.glPushMatrix();
      GL11.glClear(16640);
      this.framebufferMc.bindFramebuffer(true);
      this.mcProfiler.startSection("display");
      GL11.glEnable(3553);
      if(this.thePlayer != null && this.thePlayer.isEntityInsideOpaqueBlock()) {
         this.gameSettings.thirdPersonView = 0;
      }

      this.mcProfiler.endSection();
      if(!this.skipRenderWorld) {
         this.mcProfiler.endStartSection("gameRenderer");
         this.entityRenderer.updateCameraAndRender(this.timer.renderPartialTicks);
         this.mcProfiler.endSection();
      }

      GL11.glFlush();
      this.mcProfiler.endSection();
      if(!Display.isActive() && this.fullscreen) {
         this.toggleFullscreen();
      }

      if(this.gameSettings.showDebugInfo && this.gameSettings.showDebugProfilerChart) {
         if(!this.mcProfiler.profilingEnabled) {
            this.mcProfiler.clearProfiling();
         }

         this.mcProfiler.profilingEnabled = true;
         this.displayDebugInfo(var6);
      } else {
         this.mcProfiler.profilingEnabled = false;
         this.prevFrameTime = System.nanoTime();
      }

      this.guiAchievement.func_146254_a();
      this.framebufferMc.unbindFramebuffer();
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      this.framebufferMc.framebufferRender(this.displayWidth, this.displayHeight);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      this.entityRenderer.func_152430_c(this.timer.renderPartialTicks);
      GL11.glPopMatrix();
      this.mcProfiler.startSection("root");
      this.func_147120_f();
      Thread.yield();
      this.mcProfiler.startSection("stream");
      this.mcProfiler.startSection("update");
      this.field_152353_at.func_152935_j();
      this.mcProfiler.endStartSection("submit");
      this.field_152353_at.func_152922_k();
      this.mcProfiler.endSection();
      this.mcProfiler.endSection();
      this.checkGLError("Post render");
      ++this.fpsCounter;
      this.isGamePaused = this.isSingleplayer() && this.currentScreen != null && this.currentScreen.doesGuiPauseGame() && !this.theIntegratedServer.getPublic();

      while(getSystemTime() >= this.debugUpdateTime + 1000L) {
         debugFPS = this.fpsCounter;
         this.debug = debugFPS + " fps, " + WorldRenderer.chunksUpdated + " chunk updates";
         WorldRenderer.chunksUpdated = 0;
         this.debugUpdateTime += 1000L;
         this.fpsCounter = 0;
         this.usageSnooper.addMemoryStatsToSnooper();
         if(!this.usageSnooper.isSnooperRunning()) {
            this.usageSnooper.startSnooper();
         }
      }

      this.mcProfiler.endSection();
      if(this.isFramerateLimitBelowMax()) {
         Display.sync(this.getLimitFramerate());
      }

   }

   public void func_147120_f() {
      Display.update();
      if(!this.fullscreen && Display.wasResized()) {
         int var1 = this.displayWidth;
         int var2 = this.displayHeight;
         this.displayWidth = Display.getWidth();
         this.displayHeight = Display.getHeight();
         if(this.displayWidth != var1 || this.displayHeight != var2) {
            if(this.displayWidth <= 0) {
               this.displayWidth = 1;
            }

            if(this.displayHeight <= 0) {
               this.displayHeight = 1;
            }

            this.resize(this.displayWidth, this.displayHeight);
         }
      }

   }

   public int getLimitFramerate() {
      return this.theWorld == null && this.currentScreen != null?30:this.gameSettings.limitFramerate;
   }

   public boolean isFramerateLimitBelowMax() {
      return (float)this.getLimitFramerate() < GameSettings$Options.FRAMERATE_LIMIT.getValueMax();
   }

   public void freeMemory() {
      try {
         memoryReserve = new byte[0];
         this.renderGlobal.deleteAllDisplayLists();
      } catch (Throwable var4) {
         ;
      }

      try {
         System.gc();
      } catch (Throwable var3) {
         ;
      }

      try {
         System.gc();
         this.loadWorld((WorldClient)null);
      } catch (Throwable var2) {
         ;
      }

      System.gc();
   }

   private void updateDebugProfilerName(int var1) {
      List var2 = this.mcProfiler.getProfilingData(this.debugProfilerName);
      if(var2 != null && !var2.isEmpty()) {
         Profiler$Result var3 = (Profiler$Result)var2.remove(0);
         if(var1 == 0) {
            if(var3.field_76331_c.length() > 0) {
               int var4 = this.debugProfilerName.lastIndexOf(".");
               if(var4 >= 0) {
                  this.debugProfilerName = this.debugProfilerName.substring(0, var4);
               }
            }
         } else {
            --var1;
            if(var1 < var2.size() && !((Profiler$Result)var2.get(var1)).field_76331_c.equals("unspecified")) {
               if(this.debugProfilerName.length() > 0) {
                  this.debugProfilerName = this.debugProfilerName + ".";
               }

               this.debugProfilerName = this.debugProfilerName + ((Profiler$Result)var2.get(var1)).field_76331_c;
            }
         }

      }
   }

   private void displayDebugInfo(long var1) {
      if(this.mcProfiler.profilingEnabled) {
         List var3 = this.mcProfiler.getProfilingData(this.debugProfilerName);
         Profiler$Result var4 = (Profiler$Result)var3.remove(0);
         GL11.glClear(256);
         GL11.glMatrixMode(5889);
         GL11.glEnable(2903);
         GL11.glLoadIdentity();
         GL11.glOrtho(0.0D, (double)this.displayWidth, (double)this.displayHeight, 0.0D, 1000.0D, 3000.0D);
         GL11.glMatrixMode(5888);
         GL11.glLoadIdentity();
         GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
         GL11.glLineWidth(1.0F);
         GL11.glDisable(3553);
         Tessellator var5 = Tessellator.instance;
         short var6 = 160;
         int var7 = this.displayWidth - var6 - 10;
         int var8 = this.displayHeight - var6 * 2;
         GL11.glEnable(3042);
         var5.startDrawingQuads();
         var5.setColorRGBA_I(0, 200);
         var5.addVertex((double)((float)var7 - (float)var6 * 1.1F), (double)((float)var8 - (float)var6 * 0.6F - 16.0F), 0.0D);
         var5.addVertex((double)((float)var7 - (float)var6 * 1.1F), (double)(var8 + var6 * 2), 0.0D);
         var5.addVertex((double)((float)var7 + (float)var6 * 1.1F), (double)(var8 + var6 * 2), 0.0D);
         var5.addVertex((double)((float)var7 + (float)var6 * 1.1F), (double)((float)var8 - (float)var6 * 0.6F - 16.0F), 0.0D);
         var5.draw();
         GL11.glDisable(3042);
         double var9 = 0.0D;

         int var13;
         for(int var11 = 0; var11 < var3.size(); ++var11) {
            Profiler$Result var12 = (Profiler$Result)var3.get(var11);
            var13 = MathHelper.floor_double(var12.field_76332_a / 4.0D) + 1;
            var5.startDrawing(6);
            var5.setColorOpaque_I(var12.func_76329_a());
            var5.addVertex((double)var7, (double)var8, 0.0D);

            int var14;
            float var15;
            float var16;
            float var17;
            for(var14 = var13; var14 >= 0; --var14) {
               var15 = (float)((var9 + var12.field_76332_a * (double)var14 / (double)var13) * 3.1415927410125732D * 2.0D / 100.0D);
               var16 = MathHelper.sin(var15) * (float)var6;
               var17 = MathHelper.cos(var15) * (float)var6 * 0.5F;
               var5.addVertex((double)((float)var7 + var16), (double)((float)var8 - var17), 0.0D);
            }

            var5.draw();
            var5.startDrawing(5);
            var5.setColorOpaque_I((var12.func_76329_a() & 16711422) >> 1);

            for(var14 = var13; var14 >= 0; --var14) {
               var15 = (float)((var9 + var12.field_76332_a * (double)var14 / (double)var13) * 3.1415927410125732D * 2.0D / 100.0D);
               var16 = MathHelper.sin(var15) * (float)var6;
               var17 = MathHelper.cos(var15) * (float)var6 * 0.5F;
               var5.addVertex((double)((float)var7 + var16), (double)((float)var8 - var17), 0.0D);
               var5.addVertex((double)((float)var7 + var16), (double)((float)var8 - var17 + 10.0F), 0.0D);
            }

            var5.draw();
            var9 += var12.field_76332_a;
         }

         DecimalFormat var18 = new DecimalFormat("##0.00");
         GL11.glEnable(3553);
         String var19 = "";
         if(!var4.field_76331_c.equals("unspecified")) {
            var19 = var19 + "[0] ";
         }

         if(var4.field_76331_c.length() == 0) {
            var19 = var19 + "ROOT ";
         } else {
            var19 = var19 + var4.field_76331_c + " ";
         }

         var13 = 16777215;
         this.fontRenderer.drawStringWithShadow(var19, var7 - var6, var8 - var6 / 2 - 16, var13);
         this.fontRenderer.drawStringWithShadow(var19 = var18.format(var4.field_76330_b) + "%", var7 + var6 - this.fontRenderer.getStringWidth(var19), var8 - var6 / 2 - 16, var13);

         for(int var21 = 0; var21 < var3.size(); ++var21) {
            Profiler$Result var20 = (Profiler$Result)var3.get(var21);
            String var22 = "";
            if(var20.field_76331_c.equals("unspecified")) {
               var22 = var22 + "[?] ";
            } else {
               var22 = var22 + "[" + (var21 + 1) + "] ";
            }

            var22 = var22 + var20.field_76331_c;
            this.fontRenderer.drawStringWithShadow(var22, var7 - var6, var8 + var6 / 2 + var21 * 8 + 20, var20.func_76329_a());
            this.fontRenderer.drawStringWithShadow(var22 = var18.format(var20.field_76332_a) + "%", var7 + var6 - 50 - this.fontRenderer.getStringWidth(var22), var8 + var6 / 2 + var21 * 8 + 20, var20.func_76329_a());
            this.fontRenderer.drawStringWithShadow(var22 = var18.format(var20.field_76330_b) + "%", var7 + var6 - this.fontRenderer.getStringWidth(var22), var8 + var6 / 2 + var21 * 8 + 20, var20.func_76329_a());
         }

      }
   }

   public void shutdown() {
      this.running = false;
   }

   public void setIngameFocus() {
      if(Display.isActive()) {
         if(!this.inGameHasFocus) {
            this.inGameHasFocus = true;
            this.mouseHelper.grabMouseCursor();
            this.displayGuiScreen((GuiScreen)null);
            this.leftClickCounter = 10000;
         }
      }
   }

   public void setIngameNotInFocus() {
      if(this.inGameHasFocus) {
         KeyBinding.unPressAllKeys();
         this.inGameHasFocus = false;
         this.mouseHelper.ungrabMouseCursor();
      }
   }

   public void displayInGameMenu() {
      if(this.currentScreen == null) {
         this.displayGuiScreen(new GuiIngameMenu());
         if(this.isSingleplayer() && !this.theIntegratedServer.getPublic()) {
            this.mcSoundHandler.pauseSounds();
         }

      }
   }

   private void func_147115_a(boolean var1) {
      if(!var1) {
         this.leftClickCounter = 0;
      }

      if(this.leftClickCounter <= 0) {
         if(var1 && this.objectMouseOver != null && this.objectMouseOver.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK) {
            int var2 = this.objectMouseOver.blockX;
            int var3 = this.objectMouseOver.blockY;
            int var4 = this.objectMouseOver.blockZ;
            if(this.theWorld.getBlock(var2, var3, var4).getMaterial() != Material.air) {
               this.playerController.onPlayerDamageBlock(var2, var3, var4, this.objectMouseOver.sideHit);
               if(this.thePlayer.isCurrentToolAdventureModeExempt(var2, var3, var4)) {
                  this.effectRenderer.addBlockHitEffects(var2, var3, var4, this.objectMouseOver.sideHit);
                  this.thePlayer.swingItem();
               }
            }

         } else {
            this.playerController.resetBlockRemoving();
         }
      }
   }

   private void func_147116_af() {
      if(this.leftClickCounter <= 0) {
         this.thePlayer.swingItem();
         if(this.objectMouseOver == null) {
            logger.error("Null returned as \'hitResult\', this shouldn\'t happen!");
            if(this.playerController.isNotCreative()) {
               this.leftClickCounter = 10;
            }

         } else {
            switch(Minecraft$SwitchMovingObjectType.field_152390_a[this.objectMouseOver.typeOfHit.ordinal()]) {
            case 1:
               this.playerController.attackEntity(this.thePlayer, this.objectMouseOver.entityHit);
               break;
            case 2:
               int var1 = this.objectMouseOver.blockX;
               int var2 = this.objectMouseOver.blockY;
               int var3 = this.objectMouseOver.blockZ;
               if(this.theWorld.getBlock(var1, var2, var3).getMaterial() == Material.air) {
                  if(this.playerController.isNotCreative()) {
                     this.leftClickCounter = 10;
                  }
               } else {
                  this.playerController.clickBlock(var1, var2, var3, this.objectMouseOver.sideHit);
               }
            }

         }
      }
   }

   private void func_147121_ag() {
      this.rightClickDelayTimer = 4;
      boolean var1 = true;
      ItemStack var2 = this.thePlayer.inventory.getCurrentItem();
      if(this.objectMouseOver == null) {
         logger.warn("Null returned as \'hitResult\', this shouldn\'t happen!");
      } else {
         switch(Minecraft$SwitchMovingObjectType.field_152390_a[this.objectMouseOver.typeOfHit.ordinal()]) {
         case 1:
            if(this.playerController.interactWithEntitySendPacket(this.thePlayer, this.objectMouseOver.entityHit)) {
               var1 = false;
            }
            break;
         case 2:
            int var3 = this.objectMouseOver.blockX;
            int var4 = this.objectMouseOver.blockY;
            int var5 = this.objectMouseOver.blockZ;
            if(this.theWorld.getBlock(var3, var4, var5).getMaterial() != Material.air) {
               int var6 = var2 != null?var2.stackSize:0;
               if(this.playerController.onPlayerRightClick(this.thePlayer, this.theWorld, var2, var3, var4, var5, this.objectMouseOver.sideHit, this.objectMouseOver.hitVec)) {
                  var1 = false;
                  this.thePlayer.swingItem();
               }

               if(var2 == null) {
                  return;
               }

               if(var2.stackSize == 0) {
                  this.thePlayer.inventory.mainInventory[this.thePlayer.inventory.currentItem] = null;
               } else if(var2.stackSize != var6 || this.playerController.isInCreativeMode()) {
                  this.entityRenderer.itemRenderer.resetEquippedProgress();
               }
            }
         }
      }

      if(var1) {
         ItemStack var7 = this.thePlayer.inventory.getCurrentItem();
         if(var7 != null && this.playerController.sendUseItem(this.thePlayer, this.theWorld, var7)) {
            this.entityRenderer.itemRenderer.resetEquippedProgress2();
         }
      }

   }

   public void toggleFullscreen() {
      try {
         this.fullscreen = !this.fullscreen;
         if(this.fullscreen) {
            this.updateDisplayMode();
            this.displayWidth = Display.getDisplayMode().getWidth();
            this.displayHeight = Display.getDisplayMode().getHeight();
            if(this.displayWidth <= 0) {
               this.displayWidth = 1;
            }

            if(this.displayHeight <= 0) {
               this.displayHeight = 1;
            }
         } else {
            Display.setDisplayMode(new DisplayMode(this.tempDisplayWidth, this.tempDisplayHeight));
            this.displayWidth = this.tempDisplayWidth;
            this.displayHeight = this.tempDisplayHeight;
            if(this.displayWidth <= 0) {
               this.displayWidth = 1;
            }

            if(this.displayHeight <= 0) {
               this.displayHeight = 1;
            }
         }

         if(this.currentScreen != null) {
            this.resize(this.displayWidth, this.displayHeight);
         } else {
            this.updateFramebufferSize();
         }

         Display.setFullscreen(this.fullscreen);
         Display.setVSyncEnabled(this.gameSettings.enableVsync);
         this.func_147120_f();
      } catch (Exception var2) {
         logger.error("Couldn\'t toggle fullscreen", var2);
      }

   }

   private void resize(int var1, int var2) {
      this.displayWidth = var1 <= 0?1:var1;
      this.displayHeight = var2 <= 0?1:var2;
      if(this.currentScreen != null) {
         ScaledResolution var3 = new ScaledResolution(this, var1, var2);
         int var4 = var3.getScaledWidth();
         int var5 = var3.getScaledHeight();
         this.currentScreen.setWorldAndResolution(this, var4, var5);
      }

      this.loadingScreen = new LoadingScreenRenderer(this);
      this.updateFramebufferSize();
   }

   private void updateFramebufferSize() {
      this.framebufferMc.createBindFramebuffer(this.displayWidth, this.displayHeight);
      if(this.entityRenderer != null) {
         this.entityRenderer.updateShaderGroupSize(this.displayWidth, this.displayHeight);
      }

   }

   public void runTick() {
      this.mcProfiler.startSection("scheduledExecutables");
      Queue var1 = this.field_152351_aB;
      synchronized(this.field_152351_aB) {
         while(!this.field_152351_aB.isEmpty()) {
            ((FutureTask)this.field_152351_aB.poll()).run();
         }
      }

      this.mcProfiler.endSection();
      if(this.rightClickDelayTimer > 0) {
         --this.rightClickDelayTimer;
      }

      this.mcProfiler.startSection("gui");
      if(!this.isGamePaused) {
         this.ingameGUI.updateTick();
      }

      this.mcProfiler.endStartSection("pick");
      this.entityRenderer.getMouseOver(1.0F);
      this.mcProfiler.endStartSection("gameMode");
      if(!this.isGamePaused && this.theWorld != null) {
         this.playerController.updateController();
      }

      this.mcProfiler.endStartSection("textures");
      if(!this.isGamePaused) {
         this.renderEngine.tick();
      }

      if(this.currentScreen == null && this.thePlayer != null) {
         if(this.thePlayer.getHealth() <= 0.0F) {
            this.displayGuiScreen((GuiScreen)null);
         } else if(this.thePlayer.isPlayerSleeping() && this.theWorld != null) {
            this.displayGuiScreen(new GuiSleepMP());
         }
      } else if(this.currentScreen != null && this.currentScreen instanceof GuiSleepMP && !this.thePlayer.isPlayerSleeping()) {
         this.displayGuiScreen((GuiScreen)null);
      }

      if(this.currentScreen != null) {
         this.leftClickCounter = 10000;
      }

      CrashReport var2;
      CrashReportCategory var3;
      if(this.currentScreen != null) {
         try {
            this.currentScreen.handleInput();
         } catch (Throwable var6) {
            var2 = CrashReport.makeCrashReport(var6, "Updating screen events");
            var3 = var2.makeCategory("Affected screen");
            var3.addCrashSectionCallable("Screen name", new Minecraft$3(this));
            throw new ReportedException(var2);
         }

         if(this.currentScreen != null) {
            try {
               this.currentScreen.updateScreen();
            } catch (Throwable var5) {
               var2 = CrashReport.makeCrashReport(var5, "Ticking screen");
               var3 = var2.makeCategory("Affected screen");
               var3.addCrashSectionCallable("Screen name", new Minecraft$4(this));
               throw new ReportedException(var2);
            }
         }
      }

      if(this.currentScreen == null || this.currentScreen.allowUserInput) {
         this.mcProfiler.endStartSection("mouse");

         int var9;
         while(Mouse.next()) {
            var9 = Mouse.getEventButton();
            KeyBinding.setKeyBindState(var9 - 100, Mouse.getEventButtonState());
            if(Mouse.getEventButtonState()) {
               KeyBinding.onTick(var9 - 100);
            }

            long var11 = getSystemTime() - this.systemTime;
            if(var11 <= 200L) {
               int var4 = Mouse.getEventDWheel();
               if(var4 != 0) {
                  this.thePlayer.inventory.changeCurrentItem(var4);
                  if(this.gameSettings.noclip) {
                     if(var4 > 0) {
                        var4 = 1;
                     }

                     if(var4 < 0) {
                        var4 = -1;
                     }

                     this.gameSettings.noclipRate += (float)var4 * 0.25F;
                  }
               }

               if(this.currentScreen == null) {
                  if(!this.inGameHasFocus && Mouse.getEventButtonState()) {
                     this.setIngameFocus();
                  }
               } else if(this.currentScreen != null) {
                  this.currentScreen.handleMouseInput();
               }
            }
         }

         if(this.leftClickCounter > 0) {
            --this.leftClickCounter;
         }

         this.mcProfiler.endStartSection("keyboard");

         boolean var10;
         while(Keyboard.next()) {
            KeyBinding.setKeyBindState(Keyboard.getEventKey(), Keyboard.getEventKeyState());
            if(Keyboard.getEventKeyState()) {
               KeyBinding.onTick(Keyboard.getEventKey());
            }

            if(this.field_83002_am > 0L) {
               if(getSystemTime() - this.field_83002_am >= 6000L) {
                  throw new ReportedException(new CrashReport("Manually triggered debug crash", new Throwable()));
               }

               if(!Keyboard.isKeyDown(46) || !Keyboard.isKeyDown(61)) {
                  this.field_83002_am = -1L;
               }
            } else if(Keyboard.isKeyDown(46) && Keyboard.isKeyDown(61)) {
               this.field_83002_am = getSystemTime();
            }

            this.func_152348_aa();
            if(Keyboard.getEventKeyState()) {
               if(Keyboard.getEventKey() == 62 && this.entityRenderer != null) {
                  this.entityRenderer.deactivateShader();
               }

               if(this.currentScreen != null) {
                  this.currentScreen.handleKeyboardInput();
               } else {
                  if(Keyboard.getEventKey() == 1) {
                     this.displayInGameMenu();
                  }

                  if(Keyboard.getEventKey() == 31 && Keyboard.isKeyDown(61)) {
                     this.refreshResources();
                  }

                  if(Keyboard.getEventKey() == 20 && Keyboard.isKeyDown(61)) {
                     this.refreshResources();
                  }

                  if(Keyboard.getEventKey() == 33 && Keyboard.isKeyDown(61)) {
                     var10 = Keyboard.isKeyDown(42) | Keyboard.isKeyDown(54);
                     this.gameSettings.setOptionValue(GameSettings$Options.RENDER_DISTANCE, var10?-1:1);
                  }

                  if(Keyboard.getEventKey() == 30 && Keyboard.isKeyDown(61)) {
                     this.renderGlobal.loadRenderers();
                  }

                  if(Keyboard.getEventKey() == 35 && Keyboard.isKeyDown(61)) {
                     this.gameSettings.advancedItemTooltips = !this.gameSettings.advancedItemTooltips;
                     this.gameSettings.saveOptions();
                  }

                  if(Keyboard.getEventKey() == 48 && Keyboard.isKeyDown(61)) {
                     RenderManager.debugBoundingBox = !RenderManager.debugBoundingBox;
                  }

                  if(Keyboard.getEventKey() == 25 && Keyboard.isKeyDown(61)) {
                     this.gameSettings.pauseOnLostFocus = !this.gameSettings.pauseOnLostFocus;
                     this.gameSettings.saveOptions();
                  }

                  if(Keyboard.getEventKey() == 59) {
                     this.gameSettings.hideGUI = !this.gameSettings.hideGUI;
                  }

                  if(Keyboard.getEventKey() == 61) {
                     this.gameSettings.showDebugInfo = !this.gameSettings.showDebugInfo;
                     this.gameSettings.showDebugProfilerChart = GuiScreen.isShiftKeyDown();
                  }

                  if(this.gameSettings.keyBindTogglePerspective.isPressed()) {
                     ++this.gameSettings.thirdPersonView;
                     if(this.gameSettings.thirdPersonView > 2) {
                        this.gameSettings.thirdPersonView = 0;
                     }
                  }

                  if(this.gameSettings.keyBindSmoothCamera.isPressed()) {
                     this.gameSettings.smoothCamera = !this.gameSettings.smoothCamera;
                  }
               }

               if(this.gameSettings.showDebugInfo && this.gameSettings.showDebugProfilerChart) {
                  if(Keyboard.getEventKey() == 11) {
                     this.updateDebugProfilerName(0);
                  }

                  for(var9 = 0; var9 < 9; ++var9) {
                     if(Keyboard.getEventKey() == 2 + var9) {
                        this.updateDebugProfilerName(var9 + 1);
                     }
                  }
               }
            }
         }

         for(var9 = 0; var9 < 9; ++var9) {
            if(this.gameSettings.keyBindsHotbar[var9].isPressed()) {
               this.thePlayer.inventory.currentItem = var9;
            }
         }

         var10 = this.gameSettings.chatVisibility != EntityPlayer$EnumChatVisibility.HIDDEN;

         while(this.gameSettings.keyBindInventory.isPressed()) {
            if(this.playerController.func_110738_j()) {
               this.thePlayer.func_110322_i();
            } else {
               this.getNetHandler().addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus$EnumState.OPEN_INVENTORY_ACHIEVEMENT));
               this.displayGuiScreen(new GuiInventory(this.thePlayer));
            }
         }

         while(this.gameSettings.keyBindDrop.isPressed()) {
            this.thePlayer.dropOneItem(GuiScreen.isCtrlKeyDown());
         }

         while(this.gameSettings.keyBindChat.isPressed() && var10) {
            this.displayGuiScreen(new GuiChat());
         }

         if(this.currentScreen == null && this.gameSettings.keyBindCommand.isPressed() && var10) {
            this.displayGuiScreen(new GuiChat("/"));
         }

         if(this.thePlayer.isUsingItem()) {
            if(!this.gameSettings.keyBindUseItem.getIsKeyPressed()) {
               this.playerController.onStoppedUsingItem(this.thePlayer);
            }

            label391:
            while(true) {
               if(!this.gameSettings.keyBindAttack.isPressed()) {
                  while(this.gameSettings.keyBindUseItem.isPressed()) {
                     ;
                  }

                  while(true) {
                     if(this.gameSettings.keyBindPickBlock.isPressed()) {
                        continue;
                     }
                     break label391;
                  }
               }
            }
         } else {
            while(this.gameSettings.keyBindAttack.isPressed()) {
               this.func_147116_af();
            }

            while(this.gameSettings.keyBindUseItem.isPressed()) {
               this.func_147121_ag();
            }

            while(this.gameSettings.keyBindPickBlock.isPressed()) {
               this.func_147112_ai();
            }
         }

         if(this.gameSettings.keyBindUseItem.getIsKeyPressed() && this.rightClickDelayTimer == 0 && !this.thePlayer.isUsingItem()) {
            this.func_147121_ag();
         }

         this.func_147115_a(this.currentScreen == null && this.gameSettings.keyBindAttack.getIsKeyPressed() && this.inGameHasFocus);
      }

      if(this.theWorld != null) {
         if(this.thePlayer != null) {
            ++this.joinPlayerCounter;
            if(this.joinPlayerCounter == 30) {
               this.joinPlayerCounter = 0;
               this.theWorld.joinEntityInSurroundings(this.thePlayer);
            }
         }

         this.mcProfiler.endStartSection("gameRenderer");
         if(!this.isGamePaused) {
            this.entityRenderer.updateRenderer();
         }

         this.mcProfiler.endStartSection("levelRenderer");
         if(!this.isGamePaused) {
            this.renderGlobal.updateClouds();
         }

         this.mcProfiler.endStartSection("level");
         if(!this.isGamePaused) {
            if(this.theWorld.lastLightningBolt > 0) {
               --this.theWorld.lastLightningBolt;
            }

            this.theWorld.updateEntities();
         }
      }

      if(!this.isGamePaused) {
         this.mcMusicTicker.update();
         this.mcSoundHandler.update();
      }

      if(this.theWorld != null) {
         if(!this.isGamePaused) {
            this.theWorld.setAllowedSpawnTypes(this.theWorld.difficultySetting != EnumDifficulty.PEACEFUL, true);

            try {
               this.theWorld.tick();
            } catch (Throwable var7) {
               var2 = CrashReport.makeCrashReport(var7, "Exception in world tick");
               if(this.theWorld == null) {
                  var3 = var2.makeCategory("Affected level");
                  var3.addCrashSection("Problem", "Level is null!");
               } else {
                  this.theWorld.addWorldInfoToCrashReport(var2);
               }

               throw new ReportedException(var2);
            }
         }

         this.mcProfiler.endStartSection("animateTick");
         if(!this.isGamePaused && this.theWorld != null) {
            this.theWorld.doVoidFogParticles(MathHelper.floor_double(this.thePlayer.posX), MathHelper.floor_double(this.thePlayer.posY), MathHelper.floor_double(this.thePlayer.posZ));
         }

         this.mcProfiler.endStartSection("particles");
         if(!this.isGamePaused) {
            this.effectRenderer.updateEffects();
         }
      } else if(this.myNetworkManager != null) {
         this.mcProfiler.endStartSection("pendingConnection");
         this.myNetworkManager.processReceivedPackets();
      }

      this.mcProfiler.endSection();
      this.systemTime = getSystemTime();
   }

   public void launchIntegratedServer(String var1, String var2, WorldSettings var3) {
      this.loadWorld((WorldClient)null);
      System.gc();
      ISaveHandler var4 = this.saveLoader.getSaveLoader(var1, false);
      WorldInfo var5 = var4.loadWorldInfo();
      if(var5 == null && var3 != null) {
         var5 = new WorldInfo(var3, var1);
         var4.saveWorldInfo(var5);
      }

      if(var3 == null) {
         var3 = new WorldSettings(var5);
      }

      try {
         this.theIntegratedServer = new IntegratedServer(this, var1, var2, var3);
         this.theIntegratedServer.startServerThread();
         this.integratedServerIsRunning = true;
      } catch (Throwable var10) {
         CrashReport var7 = CrashReport.makeCrashReport(var10, "Starting integrated server");
         CrashReportCategory var8 = var7.makeCategory("Starting integrated server");
         var8.addCrashSection("Level ID", var1);
         var8.addCrashSection("Level Name", var2);
         throw new ReportedException(var7);
      }

      this.loadingScreen.displayProgressMessage(I18n.format("menu.loadingLevel", new Object[0]));

      while(!this.theIntegratedServer.serverIsInRunLoop()) {
         String var6 = this.theIntegratedServer.getUserMessage();
         if(var6 != null) {
            this.loadingScreen.resetProgresAndWorkingMessage(I18n.format(var6, new Object[0]));
         } else {
            this.loadingScreen.resetProgresAndWorkingMessage("");
         }

         try {
            Thread.sleep(200L);
         } catch (InterruptedException var9) {
            ;
         }
      }

      this.displayGuiScreen((GuiScreen)null);
      SocketAddress var11 = this.theIntegratedServer.func_147137_ag().addLocalEndpoint();
      NetworkManager var12 = NetworkManager.provideLocalClient(var11);
      var12.setNetHandler(new NetHandlerLoginClient(var12, this, (GuiScreen)null));
      var12.scheduleOutboundPacket(new C00Handshake(5, var11.toString(), 0, EnumConnectionState.LOGIN), new GenericFutureListener[0]);
      var12.scheduleOutboundPacket(new C00PacketLoginStart(this.getSession().func_148256_e()), new GenericFutureListener[0]);
      this.myNetworkManager = var12;
   }

   public void loadWorld(WorldClient var1) {
      this.loadWorld(var1, "");
   }

   public void loadWorld(WorldClient var1, String var2) {
      if(var1 == null) {
         NetHandlerPlayClient var3 = this.getNetHandler();
         if(var3 != null) {
            var3.cleanup();
         }

         if(this.theIntegratedServer != null) {
            this.theIntegratedServer.initiateShutdown();
         }

         this.theIntegratedServer = null;
         this.guiAchievement.func_146257_b();
         this.entityRenderer.getMapItemRenderer().func_148249_a();
      }

      this.renderViewEntity = null;
      this.myNetworkManager = null;
      if(this.loadingScreen != null) {
         this.loadingScreen.resetProgressAndMessage(var2);
         this.loadingScreen.resetProgresAndWorkingMessage("");
      }

      if(var1 == null && this.theWorld != null) {
         if(this.mcResourcePackRepository.func_148530_e() != null) {
            this.scheduleResourcesRefresh();
         }

         this.mcResourcePackRepository.func_148529_f();
         this.setServerData((ServerData)null);
         this.integratedServerIsRunning = false;
      }

      this.mcSoundHandler.stopSounds();
      this.theWorld = var1;
      if(var1 != null) {
         if(this.renderGlobal != null) {
            this.renderGlobal.setWorldAndLoadRenderers(var1);
         }

         if(this.effectRenderer != null) {
            this.effectRenderer.clearEffects(var1);
         }

         if(this.thePlayer == null) {
            this.thePlayer = this.playerController.func_147493_a(var1, new StatFileWriter());
            this.playerController.flipPlayer(this.thePlayer);
         }

         this.thePlayer.preparePlayerToSpawn();
         var1.spawnEntityInWorld(this.thePlayer);
         this.thePlayer.movementInput = new MovementInputFromOptions(this.gameSettings);
         this.playerController.setPlayerCapabilities(this.thePlayer);
         this.renderViewEntity = this.thePlayer;
      } else {
         this.saveLoader.flushCache();
         this.thePlayer = null;
      }

      System.gc();
      this.systemTime = 0L;
   }

   public String debugInfoRenders() {
      return this.renderGlobal.getDebugInfoRenders();
   }

   public String getEntityDebug() {
      return this.renderGlobal.getDebugInfoEntities();
   }

   public String getWorldProviderName() {
      return this.theWorld.getProviderName();
   }

   public String debugInfoEntities() {
      return "P: " + this.effectRenderer.getStatistics() + ". T: " + this.theWorld.getDebugLoadedEntities();
   }

   public void setDimensionAndSpawnPlayer(int var1) {
      this.theWorld.setSpawnLocation();
      this.theWorld.removeAllEntities();
      int var2 = 0;
      String var3 = null;
      if(this.thePlayer != null) {
         var2 = this.thePlayer.getEntityId();
         this.theWorld.removeEntity(this.thePlayer);
         var3 = this.thePlayer.func_142021_k();
      }

      this.renderViewEntity = null;
      this.thePlayer = this.playerController.func_147493_a(this.theWorld, this.thePlayer == null?new StatFileWriter():this.thePlayer.getStatFileWriter());
      this.thePlayer.dimension = var1;
      this.renderViewEntity = this.thePlayer;
      this.thePlayer.preparePlayerToSpawn();
      this.thePlayer.func_142020_c(var3);
      this.theWorld.spawnEntityInWorld(this.thePlayer);
      this.playerController.flipPlayer(this.thePlayer);
      this.thePlayer.movementInput = new MovementInputFromOptions(this.gameSettings);
      this.thePlayer.setEntityId(var2);
      this.playerController.setPlayerCapabilities(this.thePlayer);
      if(this.currentScreen instanceof GuiGameOver) {
         this.displayGuiScreen((GuiScreen)null);
      }

   }

   public final boolean isDemo() {
      return this.isDemo;
   }

   public NetHandlerPlayClient getNetHandler() {
      return this.thePlayer != null?this.thePlayer.sendQueue:null;
   }

   public static boolean isGuiEnabled() {
      return theMinecraft == null || !theMinecraft.gameSettings.hideGUI;
   }

   public static boolean isFancyGraphicsEnabled() {
      return theMinecraft != null && theMinecraft.gameSettings.fancyGraphics;
   }

   public static boolean isAmbientOcclusionEnabled() {
      return theMinecraft != null && theMinecraft.gameSettings.ambientOcclusion != 0;
   }

   private void func_147112_ai() {
      if(this.objectMouseOver != null) {
         boolean var1 = this.thePlayer.capabilities.isCreativeMode;
         int var3 = 0;
         boolean var4 = false;
         Item var2;
         int var5;
         if(this.objectMouseOver.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK) {
            var5 = this.objectMouseOver.blockX;
            int var6 = this.objectMouseOver.blockY;
            int var7 = this.objectMouseOver.blockZ;
            Block var8 = this.theWorld.getBlock(var5, var6, var7);
            if(var8.getMaterial() == Material.air) {
               return;
            }

            var2 = var8.getItem(this.theWorld, var5, var6, var7);
            if(var2 == null) {
               return;
            }

            var4 = var2.getHasSubtypes();
            Block var9 = var2 instanceof ItemBlock && !var8.isFlowerPot()?Block.getBlockFromItem(var2):var8;
            var3 = var9.getDamageValue(this.theWorld, var5, var6, var7);
         } else {
            if(this.objectMouseOver.typeOfHit != MovingObjectPosition$MovingObjectType.ENTITY || this.objectMouseOver.entityHit == null || !var1) {
               return;
            }

            if(this.objectMouseOver.entityHit instanceof EntityPainting) {
               var2 = Items.painting;
            } else if(this.objectMouseOver.entityHit instanceof EntityLeashKnot) {
               var2 = Items.lead;
            } else if(this.objectMouseOver.entityHit instanceof EntityItemFrame) {
               EntityItemFrame var10 = (EntityItemFrame)this.objectMouseOver.entityHit;
               ItemStack var12 = var10.getDisplayedItem();
               if(var12 == null) {
                  var2 = Items.item_frame;
               } else {
                  var2 = var12.getItem();
                  var3 = var12.getItemDamage();
                  var4 = true;
               }
            } else if(this.objectMouseOver.entityHit instanceof EntityMinecart) {
               EntityMinecart var11 = (EntityMinecart)this.objectMouseOver.entityHit;
               if(var11.getMinecartType() == 2) {
                  var2 = Items.furnace_minecart;
               } else if(var11.getMinecartType() == 1) {
                  var2 = Items.chest_minecart;
               } else if(var11.getMinecartType() == 3) {
                  var2 = Items.tnt_minecart;
               } else if(var11.getMinecartType() == 5) {
                  var2 = Items.hopper_minecart;
               } else if(var11.getMinecartType() == 6) {
                  var2 = Items.command_block_minecart;
               } else {
                  var2 = Items.minecart;
               }
            } else if(this.objectMouseOver.entityHit instanceof EntityBoat) {
               var2 = Items.boat;
            } else {
               var2 = Items.spawn_egg;
               var3 = EntityList.getEntityID(this.objectMouseOver.entityHit);
               var4 = true;
               if(var3 <= 0 || !EntityList.entityEggs.containsKey(Integer.valueOf(var3))) {
                  return;
               }
            }
         }

         this.thePlayer.inventory.func_146030_a(var2, var3, var4, var1);
         if(var1) {
            var5 = this.thePlayer.inventoryContainer.inventorySlots.size() - 9 + this.thePlayer.inventory.currentItem;
            this.playerController.sendSlotPacket(this.thePlayer.inventory.getStackInSlot(this.thePlayer.inventory.currentItem), var5);
         }

      }
   }

   public CrashReport addGraphicsAndWorldToCrashReport(CrashReport var1) {
      var1.getCategory().addCrashSectionCallable("Launched Version", new Minecraft$5(this));
      var1.getCategory().addCrashSectionCallable("LWJGL", new Minecraft$6(this));
      var1.getCategory().addCrashSectionCallable("OpenGL", new Minecraft$7(this));
      var1.getCategory().addCrashSectionCallable("GL Caps", new Minecraft$8(this));
      var1.getCategory().addCrashSectionCallable("Is Modded", new Minecraft$9(this));
      var1.getCategory().addCrashSectionCallable("Type", new Minecraft$10(this));
      var1.getCategory().addCrashSectionCallable("Resource Packs", new Minecraft$11(this));
      var1.getCategory().addCrashSectionCallable("Current Language", new Minecraft$12(this));
      var1.getCategory().addCrashSectionCallable("Profiler Position", new Minecraft$13(this));
      var1.getCategory().addCrashSectionCallable("Vec3 Pool Size", new Minecraft$14(this));
      var1.getCategory().addCrashSectionCallable("Anisotropic Filtering", new Minecraft$15(this));
      if(this.theWorld != null) {
         this.theWorld.addWorldInfoToCrashReport(var1);
      }

      return var1;
   }

   public static Minecraft getMinecraft() {
      return theMinecraft;
   }

   public void scheduleResourcesRefresh() {
      this.refreshTexturePacksScheduled = true;
   }

   public void addServerStatsToSnooper(PlayerUsageSnooper var1) {
      var1.func_152768_a("fps", Integer.valueOf(debugFPS));
      var1.func_152768_a("vsync_enabled", Boolean.valueOf(this.gameSettings.enableVsync));
      var1.func_152768_a("display_frequency", Integer.valueOf(Display.getDisplayMode().getFrequency()));
      var1.func_152768_a("display_type", this.fullscreen?"fullscreen":"windowed");
      var1.func_152768_a("run_time", Long.valueOf((MinecraftServer.getSystemTimeMillis() - var1.getMinecraftStartTimeMillis()) / 60L * 1000L));
      var1.func_152768_a("resource_packs", Integer.valueOf(this.mcResourcePackRepository.getRepositoryEntries().size()));
      int var2 = 0;
      Iterator var3 = this.mcResourcePackRepository.getRepositoryEntries().iterator();

      while(var3.hasNext()) {
         ResourcePackRepository$Entry var4 = (ResourcePackRepository$Entry)var3.next();
         var1.func_152768_a("resource_pack[" + var2++ + "]", var4.getResourcePackName());
      }

      if(this.theIntegratedServer != null && this.theIntegratedServer.getPlayerUsageSnooper() != null) {
         var1.func_152768_a("snooper_partner", this.theIntegratedServer.getPlayerUsageSnooper().getUniqueID());
      }

   }

   public void addServerTypeToSnooper(PlayerUsageSnooper var1) {
      var1.func_152767_b("opengl_version", GL11.glGetString(7938));
      var1.func_152767_b("opengl_vendor", GL11.glGetString(7936));
      var1.func_152767_b("client_brand", ClientBrandRetriever.getClientModName());
      var1.func_152767_b("launched_version", this.launchedVersion);
      ContextCapabilities var2 = GLContext.getCapabilities();
      var1.func_152767_b("gl_caps[ARB_arrays_of_arrays]", Boolean.valueOf(var2.GL_ARB_arrays_of_arrays));
      var1.func_152767_b("gl_caps[ARB_base_instance]", Boolean.valueOf(var2.GL_ARB_base_instance));
      var1.func_152767_b("gl_caps[ARB_blend_func_extended]", Boolean.valueOf(var2.GL_ARB_blend_func_extended));
      var1.func_152767_b("gl_caps[ARB_clear_buffer_object]", Boolean.valueOf(var2.GL_ARB_clear_buffer_object));
      var1.func_152767_b("gl_caps[ARB_color_buffer_float]", Boolean.valueOf(var2.GL_ARB_color_buffer_float));
      var1.func_152767_b("gl_caps[ARB_compatibility]", Boolean.valueOf(var2.GL_ARB_compatibility));
      var1.func_152767_b("gl_caps[ARB_compressed_texture_pixel_storage]", Boolean.valueOf(var2.GL_ARB_compressed_texture_pixel_storage));
      var1.func_152767_b("gl_caps[ARB_compute_shader]", Boolean.valueOf(var2.GL_ARB_compute_shader));
      var1.func_152767_b("gl_caps[ARB_copy_buffer]", Boolean.valueOf(var2.GL_ARB_copy_buffer));
      var1.func_152767_b("gl_caps[ARB_copy_image]", Boolean.valueOf(var2.GL_ARB_copy_image));
      var1.func_152767_b("gl_caps[ARB_depth_buffer_float]", Boolean.valueOf(var2.GL_ARB_depth_buffer_float));
      var1.func_152767_b("gl_caps[ARB_compute_shader]", Boolean.valueOf(var2.GL_ARB_compute_shader));
      var1.func_152767_b("gl_caps[ARB_copy_buffer]", Boolean.valueOf(var2.GL_ARB_copy_buffer));
      var1.func_152767_b("gl_caps[ARB_copy_image]", Boolean.valueOf(var2.GL_ARB_copy_image));
      var1.func_152767_b("gl_caps[ARB_depth_buffer_float]", Boolean.valueOf(var2.GL_ARB_depth_buffer_float));
      var1.func_152767_b("gl_caps[ARB_depth_clamp]", Boolean.valueOf(var2.GL_ARB_depth_clamp));
      var1.func_152767_b("gl_caps[ARB_depth_texture]", Boolean.valueOf(var2.GL_ARB_depth_texture));
      var1.func_152767_b("gl_caps[ARB_draw_buffers]", Boolean.valueOf(var2.GL_ARB_draw_buffers));
      var1.func_152767_b("gl_caps[ARB_draw_buffers_blend]", Boolean.valueOf(var2.GL_ARB_draw_buffers_blend));
      var1.func_152767_b("gl_caps[ARB_draw_elements_base_vertex]", Boolean.valueOf(var2.GL_ARB_draw_elements_base_vertex));
      var1.func_152767_b("gl_caps[ARB_draw_indirect]", Boolean.valueOf(var2.GL_ARB_draw_indirect));
      var1.func_152767_b("gl_caps[ARB_draw_instanced]", Boolean.valueOf(var2.GL_ARB_draw_instanced));
      var1.func_152767_b("gl_caps[ARB_explicit_attrib_location]", Boolean.valueOf(var2.GL_ARB_explicit_attrib_location));
      var1.func_152767_b("gl_caps[ARB_explicit_uniform_location]", Boolean.valueOf(var2.GL_ARB_explicit_uniform_location));
      var1.func_152767_b("gl_caps[ARB_fragment_layer_viewport]", Boolean.valueOf(var2.GL_ARB_fragment_layer_viewport));
      var1.func_152767_b("gl_caps[ARB_fragment_program]", Boolean.valueOf(var2.GL_ARB_fragment_program));
      var1.func_152767_b("gl_caps[ARB_fragment_shader]", Boolean.valueOf(var2.GL_ARB_fragment_shader));
      var1.func_152767_b("gl_caps[ARB_fragment_program_shadow]", Boolean.valueOf(var2.GL_ARB_fragment_program_shadow));
      var1.func_152767_b("gl_caps[ARB_framebuffer_object]", Boolean.valueOf(var2.GL_ARB_framebuffer_object));
      var1.func_152767_b("gl_caps[ARB_framebuffer_sRGB]", Boolean.valueOf(var2.GL_ARB_framebuffer_sRGB));
      var1.func_152767_b("gl_caps[ARB_geometry_shader4]", Boolean.valueOf(var2.GL_ARB_geometry_shader4));
      var1.func_152767_b("gl_caps[ARB_gpu_shader5]", Boolean.valueOf(var2.GL_ARB_gpu_shader5));
      var1.func_152767_b("gl_caps[ARB_half_float_pixel]", Boolean.valueOf(var2.GL_ARB_half_float_pixel));
      var1.func_152767_b("gl_caps[ARB_half_float_vertex]", Boolean.valueOf(var2.GL_ARB_half_float_vertex));
      var1.func_152767_b("gl_caps[ARB_instanced_arrays]", Boolean.valueOf(var2.GL_ARB_instanced_arrays));
      var1.func_152767_b("gl_caps[ARB_map_buffer_alignment]", Boolean.valueOf(var2.GL_ARB_map_buffer_alignment));
      var1.func_152767_b("gl_caps[ARB_map_buffer_range]", Boolean.valueOf(var2.GL_ARB_map_buffer_range));
      var1.func_152767_b("gl_caps[ARB_multisample]", Boolean.valueOf(var2.GL_ARB_multisample));
      var1.func_152767_b("gl_caps[ARB_multitexture]", Boolean.valueOf(var2.GL_ARB_multitexture));
      var1.func_152767_b("gl_caps[ARB_occlusion_query2]", Boolean.valueOf(var2.GL_ARB_occlusion_query2));
      var1.func_152767_b("gl_caps[ARB_pixel_buffer_object]", Boolean.valueOf(var2.GL_ARB_pixel_buffer_object));
      var1.func_152767_b("gl_caps[ARB_seamless_cube_map]", Boolean.valueOf(var2.GL_ARB_seamless_cube_map));
      var1.func_152767_b("gl_caps[ARB_shader_objects]", Boolean.valueOf(var2.GL_ARB_shader_objects));
      var1.func_152767_b("gl_caps[ARB_shader_stencil_export]", Boolean.valueOf(var2.GL_ARB_shader_stencil_export));
      var1.func_152767_b("gl_caps[ARB_shader_texture_lod]", Boolean.valueOf(var2.GL_ARB_shader_texture_lod));
      var1.func_152767_b("gl_caps[ARB_shadow]", Boolean.valueOf(var2.GL_ARB_shadow));
      var1.func_152767_b("gl_caps[ARB_shadow_ambient]", Boolean.valueOf(var2.GL_ARB_shadow_ambient));
      var1.func_152767_b("gl_caps[ARB_stencil_texturing]", Boolean.valueOf(var2.GL_ARB_stencil_texturing));
      var1.func_152767_b("gl_caps[ARB_sync]", Boolean.valueOf(var2.GL_ARB_sync));
      var1.func_152767_b("gl_caps[ARB_tessellation_shader]", Boolean.valueOf(var2.GL_ARB_tessellation_shader));
      var1.func_152767_b("gl_caps[ARB_texture_border_clamp]", Boolean.valueOf(var2.GL_ARB_texture_border_clamp));
      var1.func_152767_b("gl_caps[ARB_texture_buffer_object]", Boolean.valueOf(var2.GL_ARB_texture_buffer_object));
      var1.func_152767_b("gl_caps[ARB_texture_cube_map]", Boolean.valueOf(var2.GL_ARB_texture_cube_map));
      var1.func_152767_b("gl_caps[ARB_texture_cube_map_array]", Boolean.valueOf(var2.GL_ARB_texture_cube_map_array));
      var1.func_152767_b("gl_caps[ARB_texture_non_power_of_two]", Boolean.valueOf(var2.GL_ARB_texture_non_power_of_two));
      var1.func_152767_b("gl_caps[ARB_uniform_buffer_object]", Boolean.valueOf(var2.GL_ARB_uniform_buffer_object));
      var1.func_152767_b("gl_caps[ARB_vertex_blend]", Boolean.valueOf(var2.GL_ARB_vertex_blend));
      var1.func_152767_b("gl_caps[ARB_vertex_buffer_object]", Boolean.valueOf(var2.GL_ARB_vertex_buffer_object));
      var1.func_152767_b("gl_caps[ARB_vertex_program]", Boolean.valueOf(var2.GL_ARB_vertex_program));
      var1.func_152767_b("gl_caps[ARB_vertex_shader]", Boolean.valueOf(var2.GL_ARB_vertex_shader));
      var1.func_152767_b("gl_caps[EXT_bindable_uniform]", Boolean.valueOf(var2.GL_EXT_bindable_uniform));
      var1.func_152767_b("gl_caps[EXT_blend_equation_separate]", Boolean.valueOf(var2.GL_EXT_blend_equation_separate));
      var1.func_152767_b("gl_caps[EXT_blend_func_separate]", Boolean.valueOf(var2.GL_EXT_blend_func_separate));
      var1.func_152767_b("gl_caps[EXT_blend_minmax]", Boolean.valueOf(var2.GL_EXT_blend_minmax));
      var1.func_152767_b("gl_caps[EXT_blend_subtract]", Boolean.valueOf(var2.GL_EXT_blend_subtract));
      var1.func_152767_b("gl_caps[EXT_draw_instanced]", Boolean.valueOf(var2.GL_EXT_draw_instanced));
      var1.func_152767_b("gl_caps[EXT_framebuffer_multisample]", Boolean.valueOf(var2.GL_EXT_framebuffer_multisample));
      var1.func_152767_b("gl_caps[EXT_framebuffer_object]", Boolean.valueOf(var2.GL_EXT_framebuffer_object));
      var1.func_152767_b("gl_caps[EXT_framebuffer_sRGB]", Boolean.valueOf(var2.GL_EXT_framebuffer_sRGB));
      var1.func_152767_b("gl_caps[EXT_geometry_shader4]", Boolean.valueOf(var2.GL_EXT_geometry_shader4));
      var1.func_152767_b("gl_caps[EXT_gpu_program_parameters]", Boolean.valueOf(var2.GL_EXT_gpu_program_parameters));
      var1.func_152767_b("gl_caps[EXT_gpu_shader4]", Boolean.valueOf(var2.GL_EXT_gpu_shader4));
      var1.func_152767_b("gl_caps[EXT_multi_draw_arrays]", Boolean.valueOf(var2.GL_EXT_multi_draw_arrays));
      var1.func_152767_b("gl_caps[EXT_packed_depth_stencil]", Boolean.valueOf(var2.GL_EXT_packed_depth_stencil));
      var1.func_152767_b("gl_caps[EXT_paletted_texture]", Boolean.valueOf(var2.GL_EXT_paletted_texture));
      var1.func_152767_b("gl_caps[EXT_rescale_normal]", Boolean.valueOf(var2.GL_EXT_rescale_normal));
      var1.func_152767_b("gl_caps[EXT_separate_shader_objects]", Boolean.valueOf(var2.GL_EXT_separate_shader_objects));
      var1.func_152767_b("gl_caps[EXT_shader_image_load_store]", Boolean.valueOf(var2.GL_EXT_shader_image_load_store));
      var1.func_152767_b("gl_caps[EXT_shadow_funcs]", Boolean.valueOf(var2.GL_EXT_shadow_funcs));
      var1.func_152767_b("gl_caps[EXT_shared_texture_palette]", Boolean.valueOf(var2.GL_EXT_shared_texture_palette));
      var1.func_152767_b("gl_caps[EXT_stencil_clear_tag]", Boolean.valueOf(var2.GL_EXT_stencil_clear_tag));
      var1.func_152767_b("gl_caps[EXT_stencil_two_side]", Boolean.valueOf(var2.GL_EXT_stencil_two_side));
      var1.func_152767_b("gl_caps[EXT_stencil_wrap]", Boolean.valueOf(var2.GL_EXT_stencil_wrap));
      var1.func_152767_b("gl_caps[EXT_texture_3d]", Boolean.valueOf(var2.GL_EXT_texture_3d));
      var1.func_152767_b("gl_caps[EXT_texture_array]", Boolean.valueOf(var2.GL_EXT_texture_array));
      var1.func_152767_b("gl_caps[EXT_texture_buffer_object]", Boolean.valueOf(var2.GL_EXT_texture_buffer_object));
      var1.func_152767_b("gl_caps[EXT_texture_filter_anisotropic]", Boolean.valueOf(var2.GL_EXT_texture_filter_anisotropic));
      var1.func_152767_b("gl_caps[EXT_texture_integer]", Boolean.valueOf(var2.GL_EXT_texture_integer));
      var1.func_152767_b("gl_caps[EXT_texture_lod_bias]", Boolean.valueOf(var2.GL_EXT_texture_lod_bias));
      var1.func_152767_b("gl_caps[EXT_texture_sRGB]", Boolean.valueOf(var2.GL_EXT_texture_sRGB));
      var1.func_152767_b("gl_caps[EXT_vertex_shader]", Boolean.valueOf(var2.GL_EXT_vertex_shader));
      var1.func_152767_b("gl_caps[EXT_vertex_weighting]", Boolean.valueOf(var2.GL_EXT_vertex_weighting));
      var1.func_152767_b("gl_caps[gl_max_vertex_uniforms]", Integer.valueOf(GL11.glGetInteger('\u8b4a')));
      GL11.glGetError();
      var1.func_152767_b("gl_caps[gl_max_fragment_uniforms]", Integer.valueOf(GL11.glGetInteger('\u8b49')));
      GL11.glGetError();
      var1.func_152767_b("gl_caps[gl_max_vertex_attribs]", Integer.valueOf(GL11.glGetInteger('\u8869')));
      GL11.glGetError();
      var1.func_152767_b("gl_caps[gl_max_vertex_texture_image_units]", Integer.valueOf(GL11.glGetInteger('\u8b4c')));
      GL11.glGetError();
      var1.func_152767_b("gl_caps[gl_max_texture_image_units]", Integer.valueOf(GL11.glGetInteger('\u8872')));
      GL11.glGetError();
      var1.func_152767_b("gl_caps[gl_max_texture_image_units]", Integer.valueOf(GL11.glGetInteger('\u88ff')));
      GL11.glGetError();
      var1.func_152767_b("gl_max_texture_size", Integer.valueOf(getGLMaximumTextureSize()));
   }

   public static int getGLMaximumTextureSize() {
      for(int var0 = 16384; var0 > 0; var0 >>= 1) {
         GL11.glTexImage2D('\u8064', 0, 6408, var0, var0, 0, 6408, 5121, (ByteBuffer)null);
         int var1 = GL11.glGetTexLevelParameteri('\u8064', 0, 4096);
         if(var1 != 0) {
            return var0;
         }
      }

      return -1;
   }

   public boolean isSnooperEnabled() {
      return this.gameSettings.snooperEnabled;
   }

   public void setServerData(ServerData var1) {
      this.currentServerData = var1;
   }

   public ServerData func_147104_D() {
      return this.currentServerData;
   }

   public boolean isIntegratedServerRunning() {
      return this.integratedServerIsRunning;
   }

   public boolean isSingleplayer() {
      return this.integratedServerIsRunning && this.theIntegratedServer != null;
   }

   public IntegratedServer getIntegratedServer() {
      return this.theIntegratedServer;
   }

   public static void stopIntegratedServer() {
      if(theMinecraft != null) {
         IntegratedServer var0 = theMinecraft.getIntegratedServer();
         if(var0 != null) {
            var0.stopServer();
         }

      }
   }

   public PlayerUsageSnooper getPlayerUsageSnooper() {
      return this.usageSnooper;
   }

   public static long getSystemTime() {
      return Sys.getTime() * 1000L / Sys.getTimerResolution();
   }

   public boolean isFullScreen() {
      return this.fullscreen;
   }

   public Session getSession() {
      return this.session;
   }

   public Multimap func_152341_N() {
      return this.field_152356_J;
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   public TextureManager getTextureManager() {
      return this.renderEngine;
   }

   public IResourceManager getResourceManager() {
      return this.mcResourceManager;
   }

   public ResourcePackRepository getResourcePackRepository() {
      return this.mcResourcePackRepository;
   }

   public LanguageManager getLanguageManager() {
      return this.mcLanguageManager;
   }

   public TextureMap getTextureMapBlocks() {
      return this.textureMapBlocks;
   }

   public boolean isJava64bit() {
      return this.jvm64bit;
   }

   public boolean isGamePaused() {
      return this.isGamePaused;
   }

   public SoundHandler getSoundHandler() {
      return this.mcSoundHandler;
   }

   public MusicTicker$MusicType func_147109_W() {
      return this.currentScreen instanceof GuiWinGame?MusicTicker$MusicType.CREDITS:(this.thePlayer != null?(this.thePlayer.worldObj.provider instanceof WorldProviderHell?MusicTicker$MusicType.NETHER:(this.thePlayer.worldObj.provider instanceof WorldProviderEnd?(BossStatus.bossName != null && BossStatus.statusBarTime > 0?MusicTicker$MusicType.END_BOSS:MusicTicker$MusicType.END):(this.thePlayer.capabilities.isCreativeMode && this.thePlayer.capabilities.allowFlying?MusicTicker$MusicType.CREATIVE:MusicTicker$MusicType.GAME))):MusicTicker$MusicType.MENU);
   }

   public IStream func_152346_Z() {
      return this.field_152353_at;
   }

   public void func_152348_aa() {
      int var1 = Keyboard.getEventKey();
      if(var1 != 0 && !Keyboard.isRepeatEvent()) {
         if(!(this.currentScreen instanceof GuiControls) || ((GuiControls)this.currentScreen).field_152177_g <= getSystemTime() - 20L) {
            if(Keyboard.getEventKeyState()) {
               if(var1 == this.gameSettings.field_152396_an.getKeyCode()) {
                  if(this.func_152346_Z().func_152934_n()) {
                     this.func_152346_Z().func_152914_u();
                  } else if(this.func_152346_Z().func_152924_m()) {
                     this.displayGuiScreen(new GuiYesNo(new Minecraft$16(this), I18n.format("stream.confirm_start", new Object[0]), "", 0));
                  } else if(this.func_152346_Z().func_152928_D() && this.func_152346_Z().func_152936_l()) {
                     if(this.theWorld != null) {
                        this.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Not ready to start streaming yet!"));
                     }
                  } else {
                     GuiStreamUnavailable.func_152321_a(this.currentScreen);
                  }
               } else if(var1 == this.gameSettings.field_152397_ao.getKeyCode()) {
                  if(this.func_152346_Z().func_152934_n()) {
                     if(this.func_152346_Z().func_152919_o()) {
                        this.func_152346_Z().func_152933_r();
                     } else {
                        this.func_152346_Z().func_152916_q();
                     }
                  }
               } else if(var1 == this.gameSettings.field_152398_ap.getKeyCode()) {
                  if(this.func_152346_Z().func_152934_n()) {
                     this.func_152346_Z().func_152931_p();
                  }
               } else if(var1 == this.gameSettings.field_152399_aq.getKeyCode()) {
                  this.field_152353_at.func_152910_a(true);
               } else if(var1 == this.gameSettings.field_152395_am.getKeyCode()) {
                  this.toggleFullscreen();
               } else if(var1 == this.gameSettings.keyBindScreenshot.getKeyCode()) {
                  this.ingameGUI.getChatGUI().printChatMessage(ScreenShotHelper.saveScreenshot(this.mcDataDir, this.displayWidth, this.displayHeight, this.framebufferMc));
               }
            } else if(var1 == this.gameSettings.field_152399_aq.getKeyCode()) {
               this.field_152353_at.func_152910_a(false);
            }

         }
      }
   }

   public ListenableFuture func_152343_a(Callable var1) {
      Validate.notNull(var1);
      if(!this.func_152345_ab()) {
         ListenableFutureTask var2 = ListenableFutureTask.create(var1);
         Queue var3 = this.field_152351_aB;
         synchronized(this.field_152351_aB) {
            this.field_152351_aB.add(var2);
            return var2;
         }
      } else {
         try {
            return Futures.immediateFuture(var1.call());
         } catch (Exception var6) {
            return Futures.immediateFailedCheckedFuture(var6);
         }
      }
   }

   public ListenableFuture func_152344_a(Runnable var1) {
      Validate.notNull(var1);
      return this.func_152343_a(Executors.callable(var1));
   }

   public boolean func_152345_ab() {
      return Thread.currentThread() == this.field_152352_aC;
   }

   public MinecraftSessionService func_152347_ac() {
      return this.field_152355_az;
   }

   public SkinManager func_152342_ad() {
      return this.field_152350_aA;
   }

   // $FF: synthetic method
   static String access$000(Minecraft var0) {
      return var0.launchedVersion;
   }

   // $FF: synthetic method
   static LanguageManager access$100(Minecraft var0) {
      return var0.mcLanguageManager;
   }

}
