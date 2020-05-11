package net.minecraft.client.settings;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings$1;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.client.settings.GameSettings$SwitchOptions;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.stream.TwitchStream;
import net.minecraft.entity.player.EntityPlayer$EnumChatVisibility;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.world.EnumDifficulty;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class GameSettings {

   private static final Logger logger = LogManager.getLogger();
   private static final Gson gson = new Gson();
   private static final ParameterizedType typeListString = new GameSettings$1();
   private static final String[] GUISCALES = new String[]{"options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large"};
   private static final String[] PARTICLES = new String[]{"options.particles.all", "options.particles.decreased", "options.particles.minimal"};
   private static final String[] AMBIENT_OCCLUSIONS = new String[]{"options.ao.off", "options.ao.min", "options.ao.max"};
   private static final String[] field_152391_aS = new String[]{"options.stream.compression.low", "options.stream.compression.medium", "options.stream.compression.high"};
   private static final String[] field_152392_aT = new String[]{"options.stream.chat.enabled.streaming", "options.stream.chat.enabled.always", "options.stream.chat.enabled.never"};
   private static final String[] field_152393_aU = new String[]{"options.stream.chat.userFilter.all", "options.stream.chat.userFilter.subs", "options.stream.chat.userFilter.mods"};
   private static final String[] field_152394_aV = new String[]{"options.stream.mic_toggle.mute", "options.stream.mic_toggle.talk"};
   public float mouseSensitivity = 0.5F;
   public boolean invertMouse;
   public int renderDistanceChunks = -1;
   public boolean viewBobbing = true;
   public boolean anaglyph;
   public boolean advancedOpengl;
   public boolean fboEnable = true;
   public int limitFramerate = 120;
   public boolean fancyGraphics = true;
   public int ambientOcclusion = 2;
   public boolean clouds = true;
   public List resourcePacks = new ArrayList();
   public EntityPlayer$EnumChatVisibility chatVisibility;
   public boolean chatColours;
   public boolean chatLinks;
   public boolean chatLinksPrompt;
   public float chatOpacity;
   public boolean snooperEnabled;
   public boolean fullScreen;
   public boolean enableVsync;
   public boolean hideServerAddress;
   public boolean advancedItemTooltips;
   public boolean pauseOnLostFocus;
   public boolean showCape;
   public boolean touchscreen;
   public int overrideWidth;
   public int overrideHeight;
   public boolean heldItemTooltips;
   public float chatScale;
   public float chatWidth;
   public float chatHeightUnfocused;
   public float chatHeightFocused;
   public boolean showInventoryAchievementHint;
   public int mipmapLevels;
   public int anisotropicFiltering;
   private Map mapSoundLevels;
   public float field_152400_J;
   public float field_152401_K;
   public float field_152402_L;
   public float field_152403_M;
   public float field_152404_N;
   public int field_152405_O;
   public boolean field_152406_P;
   public String field_152407_Q;
   public int field_152408_R;
   public int field_152409_S;
   public int field_152410_T;
   public KeyBinding keyBindForward;
   public KeyBinding keyBindLeft;
   public KeyBinding keyBindBack;
   public KeyBinding keyBindRight;
   public KeyBinding keyBindJump;
   public KeyBinding keyBindSneak;
   public KeyBinding keyBindInventory;
   public KeyBinding keyBindUseItem;
   public KeyBinding keyBindDrop;
   public KeyBinding keyBindAttack;
   public KeyBinding keyBindPickBlock;
   public KeyBinding keyBindSprint;
   public KeyBinding keyBindChat;
   public KeyBinding keyBindPlayerList;
   public KeyBinding keyBindCommand;
   public KeyBinding keyBindScreenshot;
   public KeyBinding keyBindTogglePerspective;
   public KeyBinding keyBindSmoothCamera;
   public KeyBinding field_152395_am;
   public KeyBinding field_152396_an;
   public KeyBinding field_152397_ao;
   public KeyBinding field_152398_ap;
   public KeyBinding field_152399_aq;
   public KeyBinding[] keyBindsHotbar;
   public KeyBinding[] keyBindings;
   protected Minecraft mc;
   private File optionsFile;
   public EnumDifficulty difficulty;
   public boolean hideGUI;
   public int thirdPersonView;
   public boolean showDebugInfo;
   public boolean showDebugProfilerChart;
   public String lastServer;
   public boolean noclip;
   public boolean smoothCamera;
   public boolean debugCamEnable;
   public float noclipRate;
   public float debugCamRate;
   public float fovSetting;
   public float gammaSetting;
   public float saturation;
   public int guiScale;
   public int particleSetting;
   public String language;
   public boolean forceUnicodeFont;


   public GameSettings(Minecraft var1, File var2) {
      this.chatVisibility = EntityPlayer$EnumChatVisibility.FULL;
      this.chatColours = true;
      this.chatLinks = true;
      this.chatLinksPrompt = true;
      this.chatOpacity = 1.0F;
      this.snooperEnabled = true;
      this.enableVsync = true;
      this.pauseOnLostFocus = true;
      this.showCape = true;
      this.heldItemTooltips = true;
      this.chatScale = 1.0F;
      this.chatWidth = 1.0F;
      this.chatHeightUnfocused = 0.44366196F;
      this.chatHeightFocused = 1.0F;
      this.showInventoryAchievementHint = true;
      this.mipmapLevels = 4;
      this.anisotropicFiltering = 1;
      this.mapSoundLevels = Maps.newEnumMap(SoundCategory.class);
      this.field_152400_J = 0.5F;
      this.field_152401_K = 1.0F;
      this.field_152402_L = 1.0F;
      this.field_152403_M = 0.5412844F;
      this.field_152404_N = 0.31690142F;
      this.field_152405_O = 1;
      this.field_152406_P = true;
      this.field_152407_Q = "";
      this.field_152408_R = 0;
      this.field_152409_S = 0;
      this.field_152410_T = 0;
      this.keyBindForward = new KeyBinding("key.forward", 17, "key.categories.movement");
      this.keyBindLeft = new KeyBinding("key.left", 30, "key.categories.movement");
      this.keyBindBack = new KeyBinding("key.back", 31, "key.categories.movement");
      this.keyBindRight = new KeyBinding("key.right", 32, "key.categories.movement");
      this.keyBindJump = new KeyBinding("key.jump", 57, "key.categories.movement");
      this.keyBindSneak = new KeyBinding("key.sneak", 42, "key.categories.movement");
      this.keyBindInventory = new KeyBinding("key.inventory", 18, "key.categories.inventory");
      this.keyBindUseItem = new KeyBinding("key.use", -99, "key.categories.gameplay");
      this.keyBindDrop = new KeyBinding("key.drop", 16, "key.categories.gameplay");
      this.keyBindAttack = new KeyBinding("key.attack", -100, "key.categories.gameplay");
      this.keyBindPickBlock = new KeyBinding("key.pickItem", -98, "key.categories.gameplay");
      this.keyBindSprint = new KeyBinding("key.sprint", 29, "key.categories.gameplay");
      this.keyBindChat = new KeyBinding("key.chat", 20, "key.categories.multiplayer");
      this.keyBindPlayerList = new KeyBinding("key.playerlist", 15, "key.categories.multiplayer");
      this.keyBindCommand = new KeyBinding("key.command", 53, "key.categories.multiplayer");
      this.keyBindScreenshot = new KeyBinding("key.screenshot", 60, "key.categories.misc");
      this.keyBindTogglePerspective = new KeyBinding("key.togglePerspective", 63, "key.categories.misc");
      this.keyBindSmoothCamera = new KeyBinding("key.smoothCamera", 0, "key.categories.misc");
      this.field_152395_am = new KeyBinding("key.fullscreen", 87, "key.categories.misc");
      this.field_152396_an = new KeyBinding("key.streamStartStop", 64, "key.categories.stream");
      this.field_152397_ao = new KeyBinding("key.streamPauseUnpause", 65, "key.categories.stream");
      this.field_152398_ap = new KeyBinding("key.streamCommercial", 0, "key.categories.stream");
      this.field_152399_aq = new KeyBinding("key.streamToggleMic", 0, "key.categories.stream");
      this.keyBindsHotbar = new KeyBinding[]{new KeyBinding("key.hotbar.1", 2, "key.categories.inventory"), new KeyBinding("key.hotbar.2", 3, "key.categories.inventory"), new KeyBinding("key.hotbar.3", 4, "key.categories.inventory"), new KeyBinding("key.hotbar.4", 5, "key.categories.inventory"), new KeyBinding("key.hotbar.5", 6, "key.categories.inventory"), new KeyBinding("key.hotbar.6", 7, "key.categories.inventory"), new KeyBinding("key.hotbar.7", 8, "key.categories.inventory"), new KeyBinding("key.hotbar.8", 9, "key.categories.inventory"), new KeyBinding("key.hotbar.9", 10, "key.categories.inventory")};
      this.keyBindings = (KeyBinding[])ArrayUtils.addAll(new KeyBinding[]{this.keyBindAttack, this.keyBindUseItem, this.keyBindForward, this.keyBindLeft, this.keyBindBack, this.keyBindRight, this.keyBindJump, this.keyBindSneak, this.keyBindDrop, this.keyBindInventory, this.keyBindChat, this.keyBindPlayerList, this.keyBindPickBlock, this.keyBindCommand, this.keyBindScreenshot, this.keyBindTogglePerspective, this.keyBindSmoothCamera, this.keyBindSprint, this.field_152396_an, this.field_152397_ao, this.field_152398_ap, this.field_152399_aq, this.field_152395_am}, this.keyBindsHotbar);
      this.difficulty = EnumDifficulty.NORMAL;
      this.lastServer = "";
      this.noclipRate = 1.0F;
      this.debugCamRate = 1.0F;
      this.fovSetting = 70.0F;
      this.language = "en_US";
      this.forceUnicodeFont = false;
      this.mc = var1;
      this.optionsFile = new File(var2, "options.txt");
      GameSettings$Options.RENDER_DISTANCE.setValueMax(16.0F);
      this.renderDistanceChunks = var1.isJava64bit()?12:8;
      this.loadOptions();
   }

   public GameSettings() {
      this.chatVisibility = EntityPlayer$EnumChatVisibility.FULL;
      this.chatColours = true;
      this.chatLinks = true;
      this.chatLinksPrompt = true;
      this.chatOpacity = 1.0F;
      this.snooperEnabled = true;
      this.enableVsync = true;
      this.pauseOnLostFocus = true;
      this.showCape = true;
      this.heldItemTooltips = true;
      this.chatScale = 1.0F;
      this.chatWidth = 1.0F;
      this.chatHeightUnfocused = 0.44366196F;
      this.chatHeightFocused = 1.0F;
      this.showInventoryAchievementHint = true;
      this.mipmapLevels = 4;
      this.anisotropicFiltering = 1;
      this.mapSoundLevels = Maps.newEnumMap(SoundCategory.class);
      this.field_152400_J = 0.5F;
      this.field_152401_K = 1.0F;
      this.field_152402_L = 1.0F;
      this.field_152403_M = 0.5412844F;
      this.field_152404_N = 0.31690142F;
      this.field_152405_O = 1;
      this.field_152406_P = true;
      this.field_152407_Q = "";
      this.field_152408_R = 0;
      this.field_152409_S = 0;
      this.field_152410_T = 0;
      this.keyBindForward = new KeyBinding("key.forward", 17, "key.categories.movement");
      this.keyBindLeft = new KeyBinding("key.left", 30, "key.categories.movement");
      this.keyBindBack = new KeyBinding("key.back", 31, "key.categories.movement");
      this.keyBindRight = new KeyBinding("key.right", 32, "key.categories.movement");
      this.keyBindJump = new KeyBinding("key.jump", 57, "key.categories.movement");
      this.keyBindSneak = new KeyBinding("key.sneak", 42, "key.categories.movement");
      this.keyBindInventory = new KeyBinding("key.inventory", 18, "key.categories.inventory");
      this.keyBindUseItem = new KeyBinding("key.use", -99, "key.categories.gameplay");
      this.keyBindDrop = new KeyBinding("key.drop", 16, "key.categories.gameplay");
      this.keyBindAttack = new KeyBinding("key.attack", -100, "key.categories.gameplay");
      this.keyBindPickBlock = new KeyBinding("key.pickItem", -98, "key.categories.gameplay");
      this.keyBindSprint = new KeyBinding("key.sprint", 29, "key.categories.gameplay");
      this.keyBindChat = new KeyBinding("key.chat", 20, "key.categories.multiplayer");
      this.keyBindPlayerList = new KeyBinding("key.playerlist", 15, "key.categories.multiplayer");
      this.keyBindCommand = new KeyBinding("key.command", 53, "key.categories.multiplayer");
      this.keyBindScreenshot = new KeyBinding("key.screenshot", 60, "key.categories.misc");
      this.keyBindTogglePerspective = new KeyBinding("key.togglePerspective", 63, "key.categories.misc");
      this.keyBindSmoothCamera = new KeyBinding("key.smoothCamera", 0, "key.categories.misc");
      this.field_152395_am = new KeyBinding("key.fullscreen", 87, "key.categories.misc");
      this.field_152396_an = new KeyBinding("key.streamStartStop", 64, "key.categories.stream");
      this.field_152397_ao = new KeyBinding("key.streamPauseUnpause", 65, "key.categories.stream");
      this.field_152398_ap = new KeyBinding("key.streamCommercial", 0, "key.categories.stream");
      this.field_152399_aq = new KeyBinding("key.streamToggleMic", 0, "key.categories.stream");
      this.keyBindsHotbar = new KeyBinding[]{new KeyBinding("key.hotbar.1", 2, "key.categories.inventory"), new KeyBinding("key.hotbar.2", 3, "key.categories.inventory"), new KeyBinding("key.hotbar.3", 4, "key.categories.inventory"), new KeyBinding("key.hotbar.4", 5, "key.categories.inventory"), new KeyBinding("key.hotbar.5", 6, "key.categories.inventory"), new KeyBinding("key.hotbar.6", 7, "key.categories.inventory"), new KeyBinding("key.hotbar.7", 8, "key.categories.inventory"), new KeyBinding("key.hotbar.8", 9, "key.categories.inventory"), new KeyBinding("key.hotbar.9", 10, "key.categories.inventory")};
      this.keyBindings = (KeyBinding[])ArrayUtils.addAll(new KeyBinding[]{this.keyBindAttack, this.keyBindUseItem, this.keyBindForward, this.keyBindLeft, this.keyBindBack, this.keyBindRight, this.keyBindJump, this.keyBindSneak, this.keyBindDrop, this.keyBindInventory, this.keyBindChat, this.keyBindPlayerList, this.keyBindPickBlock, this.keyBindCommand, this.keyBindScreenshot, this.keyBindTogglePerspective, this.keyBindSmoothCamera, this.keyBindSprint, this.field_152396_an, this.field_152397_ao, this.field_152398_ap, this.field_152399_aq, this.field_152395_am}, this.keyBindsHotbar);
      this.difficulty = EnumDifficulty.NORMAL;
      this.lastServer = "";
      this.noclipRate = 1.0F;
      this.debugCamRate = 1.0F;
      this.fovSetting = 70.0F;
      this.language = "en_US";
      this.forceUnicodeFont = false;
   }

   public static String getKeyDisplayString(int var0) {
      return var0 < 0?I18n.format("key.mouseButton", new Object[]{Integer.valueOf(var0 + 101)}):Keyboard.getKeyName(var0);
   }

   public static boolean isKeyDown(KeyBinding var0) {
      return var0.getKeyCode() == 0?false:(var0.getKeyCode() < 0?Mouse.isButtonDown(var0.getKeyCode() + 100):Keyboard.isKeyDown(var0.getKeyCode()));
   }

   public void setOptionKeyBinding(KeyBinding var1, int var2) {
      var1.setKeyCode(var2);
      this.saveOptions();
   }

   public void setOptionFloatValue(GameSettings$Options var1, float var2) {
      if(var1 == GameSettings$Options.SENSITIVITY) {
         this.mouseSensitivity = var2;
      }

      if(var1 == GameSettings$Options.FOV) {
         this.fovSetting = var2;
      }

      if(var1 == GameSettings$Options.GAMMA) {
         this.gammaSetting = var2;
      }

      if(var1 == GameSettings$Options.FRAMERATE_LIMIT) {
         this.limitFramerate = (int)var2;
      }

      if(var1 == GameSettings$Options.CHAT_OPACITY) {
         this.chatOpacity = var2;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if(var1 == GameSettings$Options.CHAT_HEIGHT_FOCUSED) {
         this.chatHeightFocused = var2;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if(var1 == GameSettings$Options.CHAT_HEIGHT_UNFOCUSED) {
         this.chatHeightUnfocused = var2;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if(var1 == GameSettings$Options.CHAT_WIDTH) {
         this.chatWidth = var2;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if(var1 == GameSettings$Options.CHAT_SCALE) {
         this.chatScale = var2;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      int var3;
      if(var1 == GameSettings$Options.ANISOTROPIC_FILTERING) {
         var3 = this.anisotropicFiltering;
         this.anisotropicFiltering = (int)var2;
         if((float)var3 != var2) {
            this.mc.getTextureMapBlocks().setAnisotropicFiltering(this.anisotropicFiltering);
            this.mc.scheduleResourcesRefresh();
         }
      }

      if(var1 == GameSettings$Options.MIPMAP_LEVELS) {
         var3 = this.mipmapLevels;
         this.mipmapLevels = (int)var2;
         if((float)var3 != var2) {
            this.mc.getTextureMapBlocks().setMipmapLevels(this.mipmapLevels);
            this.mc.scheduleResourcesRefresh();
         }
      }

      if(var1 == GameSettings$Options.RENDER_DISTANCE) {
         this.renderDistanceChunks = (int)var2;
      }

      if(var1 == GameSettings$Options.STREAM_BYTES_PER_PIXEL) {
         this.field_152400_J = var2;
      }

      if(var1 == GameSettings$Options.STREAM_VOLUME_MIC) {
         this.field_152401_K = var2;
         this.mc.func_152346_Z().func_152915_s();
      }

      if(var1 == GameSettings$Options.STREAM_VOLUME_SYSTEM) {
         this.field_152402_L = var2;
         this.mc.func_152346_Z().func_152915_s();
      }

      if(var1 == GameSettings$Options.STREAM_KBPS) {
         this.field_152403_M = var2;
      }

      if(var1 == GameSettings$Options.STREAM_FPS) {
         this.field_152404_N = var2;
      }

   }

   public void setOptionValue(GameSettings$Options var1, int var2) {
      if(var1 == GameSettings$Options.INVERT_MOUSE) {
         this.invertMouse = !this.invertMouse;
      }

      if(var1 == GameSettings$Options.GUI_SCALE) {
         this.guiScale = this.guiScale + var2 & 3;
      }

      if(var1 == GameSettings$Options.PARTICLES) {
         this.particleSetting = (this.particleSetting + var2) % 3;
      }

      if(var1 == GameSettings$Options.VIEW_BOBBING) {
         this.viewBobbing = !this.viewBobbing;
      }

      if(var1 == GameSettings$Options.RENDER_CLOUDS) {
         this.clouds = !this.clouds;
      }

      if(var1 == GameSettings$Options.FORCE_UNICODE_FONT) {
         this.forceUnicodeFont = !this.forceUnicodeFont;
         this.mc.fontRenderer.setUnicodeFlag(this.mc.getLanguageManager().isCurrentLocaleUnicode() || this.forceUnicodeFont);
      }

      if(var1 == GameSettings$Options.ADVANCED_OPENGL) {
         this.advancedOpengl = !this.advancedOpengl;
         this.mc.renderGlobal.loadRenderers();
      }

      if(var1 == GameSettings$Options.FBO_ENABLE) {
         this.fboEnable = !this.fboEnable;
      }

      if(var1 == GameSettings$Options.ANAGLYPH) {
         this.anaglyph = !this.anaglyph;
         this.mc.refreshResources();
      }

      if(var1 == GameSettings$Options.DIFFICULTY) {
         this.difficulty = EnumDifficulty.getDifficultyEnum(this.difficulty.getDifficultyId() + var2 & 3);
      }

      if(var1 == GameSettings$Options.GRAPHICS) {
         this.fancyGraphics = !this.fancyGraphics;
         this.mc.renderGlobal.loadRenderers();
      }

      if(var1 == GameSettings$Options.AMBIENT_OCCLUSION) {
         this.ambientOcclusion = (this.ambientOcclusion + var2) % 3;
         this.mc.renderGlobal.loadRenderers();
      }

      if(var1 == GameSettings$Options.CHAT_VISIBILITY) {
         this.chatVisibility = EntityPlayer$EnumChatVisibility.getEnumChatVisibility((this.chatVisibility.getChatVisibility() + var2) % 3);
      }

      if(var1 == GameSettings$Options.STREAM_COMPRESSION) {
         this.field_152405_O = (this.field_152405_O + var2) % 3;
      }

      if(var1 == GameSettings$Options.STREAM_SEND_METADATA) {
         this.field_152406_P = !this.field_152406_P;
      }

      if(var1 == GameSettings$Options.STREAM_CHAT_ENABLED) {
         this.field_152408_R = (this.field_152408_R + var2) % 3;
      }

      if(var1 == GameSettings$Options.STREAM_CHAT_USER_FILTER) {
         this.field_152409_S = (this.field_152409_S + var2) % 3;
      }

      if(var1 == GameSettings$Options.STREAM_MIC_TOGGLE_BEHAVIOR) {
         this.field_152410_T = (this.field_152410_T + var2) % 2;
      }

      if(var1 == GameSettings$Options.CHAT_COLOR) {
         this.chatColours = !this.chatColours;
      }

      if(var1 == GameSettings$Options.CHAT_LINKS) {
         this.chatLinks = !this.chatLinks;
      }

      if(var1 == GameSettings$Options.CHAT_LINKS_PROMPT) {
         this.chatLinksPrompt = !this.chatLinksPrompt;
      }

      if(var1 == GameSettings$Options.SNOOPER_ENABLED) {
         this.snooperEnabled = !this.snooperEnabled;
      }

      if(var1 == GameSettings$Options.SHOW_CAPE) {
         this.showCape = !this.showCape;
      }

      if(var1 == GameSettings$Options.TOUCHSCREEN) {
         this.touchscreen = !this.touchscreen;
      }

      if(var1 == GameSettings$Options.USE_FULLSCREEN) {
         this.fullScreen = !this.fullScreen;
         if(this.mc.isFullScreen() != this.fullScreen) {
            this.mc.toggleFullscreen();
         }
      }

      if(var1 == GameSettings$Options.ENABLE_VSYNC) {
         this.enableVsync = !this.enableVsync;
         Display.setVSyncEnabled(this.enableVsync);
      }

      this.saveOptions();
   }

   public float getOptionFloatValue(GameSettings$Options var1) {
      return var1 == GameSettings$Options.FOV?this.fovSetting:(var1 == GameSettings$Options.GAMMA?this.gammaSetting:(var1 == GameSettings$Options.SATURATION?this.saturation:(var1 == GameSettings$Options.SENSITIVITY?this.mouseSensitivity:(var1 == GameSettings$Options.CHAT_OPACITY?this.chatOpacity:(var1 == GameSettings$Options.CHAT_HEIGHT_FOCUSED?this.chatHeightFocused:(var1 == GameSettings$Options.CHAT_HEIGHT_UNFOCUSED?this.chatHeightUnfocused:(var1 == GameSettings$Options.CHAT_SCALE?this.chatScale:(var1 == GameSettings$Options.CHAT_WIDTH?this.chatWidth:(var1 == GameSettings$Options.FRAMERATE_LIMIT?(float)this.limitFramerate:(var1 == GameSettings$Options.ANISOTROPIC_FILTERING?(float)this.anisotropicFiltering:(var1 == GameSettings$Options.MIPMAP_LEVELS?(float)this.mipmapLevels:(var1 == GameSettings$Options.RENDER_DISTANCE?(float)this.renderDistanceChunks:(var1 == GameSettings$Options.STREAM_BYTES_PER_PIXEL?this.field_152400_J:(var1 == GameSettings$Options.STREAM_VOLUME_MIC?this.field_152401_K:(var1 == GameSettings$Options.STREAM_VOLUME_SYSTEM?this.field_152402_L:(var1 == GameSettings$Options.STREAM_KBPS?this.field_152403_M:(var1 == GameSettings$Options.STREAM_FPS?this.field_152404_N:0.0F)))))))))))))))));
   }

   public boolean getOptionOrdinalValue(GameSettings$Options var1) {
      switch(GameSettings$SwitchOptions.optionIds[var1.ordinal()]) {
      case 1:
         return this.invertMouse;
      case 2:
         return this.viewBobbing;
      case 3:
         return this.anaglyph;
      case 4:
         return this.advancedOpengl;
      case 5:
         return this.fboEnable;
      case 6:
         return this.clouds;
      case 7:
         return this.chatColours;
      case 8:
         return this.chatLinks;
      case 9:
         return this.chatLinksPrompt;
      case 10:
         return this.snooperEnabled;
      case 11:
         return this.fullScreen;
      case 12:
         return this.enableVsync;
      case 13:
         return this.showCape;
      case 14:
         return this.touchscreen;
      case 15:
         return this.field_152406_P;
      case 16:
         return this.forceUnicodeFont;
      default:
         return false;
      }
   }

   private static String getTranslation(String[] var0, int var1) {
      if(var1 < 0 || var1 >= var0.length) {
         var1 = 0;
      }

      return I18n.format(var0[var1], new Object[0]);
   }

   public String getKeyBinding(GameSettings$Options var1) {
      String var2 = I18n.format(var1.getEnumString(), new Object[0]) + ": ";
      if(var1.getEnumFloat()) {
         float var6 = this.getOptionFloatValue(var1);
         float var4 = var1.normalizeValue(var6);
         return var1 == GameSettings$Options.SENSITIVITY?(var4 == 0.0F?var2 + I18n.format("options.sensitivity.min", new Object[0]):(var4 == 1.0F?var2 + I18n.format("options.sensitivity.max", new Object[0]):var2 + (int)(var4 * 200.0F) + "%")):(var1 == GameSettings$Options.FOV?(var6 == 70.0F?var2 + I18n.format("options.fov.min", new Object[0]):(var6 == 110.0F?var2 + I18n.format("options.fov.max", new Object[0]):var2 + (int)var6)):(var1 == GameSettings$Options.FRAMERATE_LIMIT?(var6 == GameSettings$Options.access$100(var1)?var2 + I18n.format("options.framerateLimit.max", new Object[0]):var2 + (int)var6 + " fps"):(var1 == GameSettings$Options.GAMMA?(var4 == 0.0F?var2 + I18n.format("options.gamma.min", new Object[0]):(var4 == 1.0F?var2 + I18n.format("options.gamma.max", new Object[0]):var2 + "+" + (int)(var4 * 100.0F) + "%")):(var1 == GameSettings$Options.SATURATION?var2 + (int)(var4 * 400.0F) + "%":(var1 == GameSettings$Options.CHAT_OPACITY?var2 + (int)(var4 * 90.0F + 10.0F) + "%":(var1 == GameSettings$Options.CHAT_HEIGHT_UNFOCUSED?var2 + GuiNewChat.func_146243_b(var4) + "px":(var1 == GameSettings$Options.CHAT_HEIGHT_FOCUSED?var2 + GuiNewChat.func_146243_b(var4) + "px":(var1 == GameSettings$Options.CHAT_WIDTH?var2 + GuiNewChat.func_146233_a(var4) + "px":(var1 == GameSettings$Options.RENDER_DISTANCE?var2 + (int)var6 + " chunks":(var1 == GameSettings$Options.ANISOTROPIC_FILTERING?(var6 == 1.0F?var2 + I18n.format("options.off", new Object[0]):var2 + (int)var6):(var1 == GameSettings$Options.MIPMAP_LEVELS?(var6 == 0.0F?var2 + I18n.format("options.off", new Object[0]):var2 + (int)var6):(var1 == GameSettings$Options.STREAM_FPS?var2 + TwitchStream.func_152948_a(var4) + " fps":(var1 == GameSettings$Options.STREAM_KBPS?var2 + TwitchStream.func_152946_b(var4) + " Kbps":(var1 == GameSettings$Options.STREAM_BYTES_PER_PIXEL?var2 + String.format("%.3f bpp", new Object[]{Float.valueOf(TwitchStream.func_152947_c(var4))}):(var4 == 0.0F?var2 + I18n.format("options.off", new Object[0]):var2 + (int)(var4 * 100.0F) + "%")))))))))))))));
      } else if(var1.getEnumBoolean()) {
         boolean var5 = this.getOptionOrdinalValue(var1);
         return var5?var2 + I18n.format("options.on", new Object[0]):var2 + I18n.format("options.off", new Object[0]);
      } else if(var1 == GameSettings$Options.DIFFICULTY) {
         return var2 + I18n.format(this.difficulty.getDifficultyResourceKey(), new Object[0]);
      } else if(var1 == GameSettings$Options.GUI_SCALE) {
         return var2 + getTranslation(GUISCALES, this.guiScale);
      } else if(var1 == GameSettings$Options.CHAT_VISIBILITY) {
         return var2 + I18n.format(this.chatVisibility.getResourceKey(), new Object[0]);
      } else if(var1 == GameSettings$Options.PARTICLES) {
         return var2 + getTranslation(PARTICLES, this.particleSetting);
      } else if(var1 == GameSettings$Options.AMBIENT_OCCLUSION) {
         return var2 + getTranslation(AMBIENT_OCCLUSIONS, this.ambientOcclusion);
      } else if(var1 == GameSettings$Options.STREAM_COMPRESSION) {
         return var2 + getTranslation(field_152391_aS, this.field_152405_O);
      } else if(var1 == GameSettings$Options.STREAM_CHAT_ENABLED) {
         return var2 + getTranslation(field_152392_aT, this.field_152408_R);
      } else if(var1 == GameSettings$Options.STREAM_CHAT_USER_FILTER) {
         return var2 + getTranslation(field_152393_aU, this.field_152409_S);
      } else if(var1 == GameSettings$Options.STREAM_MIC_TOGGLE_BEHAVIOR) {
         return var2 + getTranslation(field_152394_aV, this.field_152410_T);
      } else if(var1 == GameSettings$Options.GRAPHICS) {
         if(this.fancyGraphics) {
            return var2 + I18n.format("options.graphics.fancy", new Object[0]);
         } else {
            String var3 = "options.graphics.fast";
            return var2 + I18n.format("options.graphics.fast", new Object[0]);
         }
      } else {
         return var2;
      }
   }

   public void loadOptions() {
      try {
         if(!this.optionsFile.exists()) {
            return;
         }

         BufferedReader var1 = new BufferedReader(new FileReader(this.optionsFile));
         String var2 = "";
         this.mapSoundLevels.clear();

         while((var2 = var1.readLine()) != null) {
            try {
               String[] var3 = var2.split(":");
               if(var3[0].equals("mouseSensitivity")) {
                  this.mouseSensitivity = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("invertYMouse")) {
                  this.invertMouse = var3[1].equals("true");
               }

               if(var3[0].equals("fov")) {
                  this.fovSetting = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("gamma")) {
                  this.gammaSetting = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("saturation")) {
                  this.saturation = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("fov")) {
                  this.fovSetting = this.parseFloat(var3[1]) * 40.0F + 70.0F;
               }

               if(var3[0].equals("renderDistance")) {
                  this.renderDistanceChunks = Integer.parseInt(var3[1]);
               }

               if(var3[0].equals("guiScale")) {
                  this.guiScale = Integer.parseInt(var3[1]);
               }

               if(var3[0].equals("particles")) {
                  this.particleSetting = Integer.parseInt(var3[1]);
               }

               if(var3[0].equals("bobView")) {
                  this.viewBobbing = var3[1].equals("true");
               }

               if(var3[0].equals("anaglyph3d")) {
                  this.anaglyph = var3[1].equals("true");
               }

               if(var3[0].equals("advancedOpengl")) {
                  this.advancedOpengl = var3[1].equals("true");
               }

               if(var3[0].equals("maxFps")) {
                  this.limitFramerate = Integer.parseInt(var3[1]);
               }

               if(var3[0].equals("fboEnable")) {
                  this.fboEnable = var3[1].equals("true");
               }

               if(var3[0].equals("difficulty")) {
                  this.difficulty = EnumDifficulty.getDifficultyEnum(Integer.parseInt(var3[1]));
               }

               if(var3[0].equals("fancyGraphics")) {
                  this.fancyGraphics = var3[1].equals("true");
               }

               if(var3[0].equals("ao")) {
                  if(var3[1].equals("true")) {
                     this.ambientOcclusion = 2;
                  } else if(var3[1].equals("false")) {
                     this.ambientOcclusion = 0;
                  } else {
                     this.ambientOcclusion = Integer.parseInt(var3[1]);
                  }
               }

               if(var3[0].equals("clouds")) {
                  this.clouds = var3[1].equals("true");
               }

               if(var3[0].equals("resourcePacks")) {
                  this.resourcePacks = (List)gson.fromJson(var2.substring(var2.indexOf(58) + 1), typeListString);
                  if(this.resourcePacks == null) {
                     this.resourcePacks = new ArrayList();
                  }
               }

               if(var3[0].equals("lastServer") && var3.length >= 2) {
                  this.lastServer = var2.substring(var2.indexOf(58) + 1);
               }

               if(var3[0].equals("lang") && var3.length >= 2) {
                  this.language = var3[1];
               }

               if(var3[0].equals("chatVisibility")) {
                  this.chatVisibility = EntityPlayer$EnumChatVisibility.getEnumChatVisibility(Integer.parseInt(var3[1]));
               }

               if(var3[0].equals("chatColors")) {
                  this.chatColours = var3[1].equals("true");
               }

               if(var3[0].equals("chatLinks")) {
                  this.chatLinks = var3[1].equals("true");
               }

               if(var3[0].equals("chatLinksPrompt")) {
                  this.chatLinksPrompt = var3[1].equals("true");
               }

               if(var3[0].equals("chatOpacity")) {
                  this.chatOpacity = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("snooperEnabled")) {
                  this.snooperEnabled = var3[1].equals("true");
               }

               if(var3[0].equals("fullscreen")) {
                  this.fullScreen = var3[1].equals("true");
               }

               if(var3[0].equals("enableVsync")) {
                  this.enableVsync = var3[1].equals("true");
               }

               if(var3[0].equals("hideServerAddress")) {
                  this.hideServerAddress = var3[1].equals("true");
               }

               if(var3[0].equals("advancedItemTooltips")) {
                  this.advancedItemTooltips = var3[1].equals("true");
               }

               if(var3[0].equals("pauseOnLostFocus")) {
                  this.pauseOnLostFocus = var3[1].equals("true");
               }

               if(var3[0].equals("showCape")) {
                  this.showCape = var3[1].equals("true");
               }

               if(var3[0].equals("touchscreen")) {
                  this.touchscreen = var3[1].equals("true");
               }

               if(var3[0].equals("overrideHeight")) {
                  this.overrideHeight = Integer.parseInt(var3[1]);
               }

               if(var3[0].equals("overrideWidth")) {
                  this.overrideWidth = Integer.parseInt(var3[1]);
               }

               if(var3[0].equals("heldItemTooltips")) {
                  this.heldItemTooltips = var3[1].equals("true");
               }

               if(var3[0].equals("chatHeightFocused")) {
                  this.chatHeightFocused = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("chatHeightUnfocused")) {
                  this.chatHeightUnfocused = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("chatScale")) {
                  this.chatScale = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("chatWidth")) {
                  this.chatWidth = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("showInventoryAchievementHint")) {
                  this.showInventoryAchievementHint = var3[1].equals("true");
               }

               if(var3[0].equals("mipmapLevels")) {
                  this.mipmapLevels = Integer.parseInt(var3[1]);
               }

               if(var3[0].equals("anisotropicFiltering")) {
                  this.anisotropicFiltering = Integer.parseInt(var3[1]);
               }

               if(var3[0].equals("streamBytesPerPixel")) {
                  this.field_152400_J = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("streamMicVolume")) {
                  this.field_152401_K = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("streamSystemVolume")) {
                  this.field_152402_L = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("streamKbps")) {
                  this.field_152403_M = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("streamFps")) {
                  this.field_152404_N = this.parseFloat(var3[1]);
               }

               if(var3[0].equals("streamCompression")) {
                  this.field_152405_O = Integer.parseInt(var3[1]);
               }

               if(var3[0].equals("streamSendMetadata")) {
                  this.field_152406_P = var3[1].equals("true");
               }

               if(var3[0].equals("streamPreferredServer") && var3.length >= 2) {
                  this.field_152407_Q = var2.substring(var2.indexOf(58) + 1);
               }

               if(var3[0].equals("streamChatEnabled")) {
                  this.field_152408_R = Integer.parseInt(var3[1]);
               }

               if(var3[0].equals("streamChatUserFilter")) {
                  this.field_152409_S = Integer.parseInt(var3[1]);
               }

               if(var3[0].equals("streamMicToggleBehavior")) {
                  this.field_152410_T = Integer.parseInt(var3[1]);
               }

               if(var3[0].equals("forceUnicodeFont")) {
                  this.forceUnicodeFont = var3[1].equals("true");
               }

               KeyBinding[] var4 = this.keyBindings;
               int var5 = var4.length;

               int var6;
               for(var6 = 0; var6 < var5; ++var6) {
                  KeyBinding var7 = var4[var6];
                  if(var3[0].equals("key_" + var7.getKeyDescription())) {
                     var7.setKeyCode(Integer.parseInt(var3[1]));
                  }
               }

               SoundCategory[] var10 = SoundCategory.values();
               var5 = var10.length;

               for(var6 = 0; var6 < var5; ++var6) {
                  SoundCategory var11 = var10[var6];
                  if(var3[0].equals("soundCategory_" + var11.getCategoryName())) {
                     this.mapSoundLevels.put(var11, Float.valueOf(this.parseFloat(var3[1])));
                  }
               }
            } catch (Exception var8) {
               logger.warn("Skipping bad option: " + var2);
            }
         }

         KeyBinding.resetKeyBindingArrayAndHash();
         var1.close();
      } catch (Exception var9) {
         logger.error("Failed to load options", var9);
      }

   }

   private float parseFloat(String var1) {
      return var1.equals("true")?1.0F:(var1.equals("false")?0.0F:Float.parseFloat(var1));
   }

   public void saveOptions() {
      try {
         PrintWriter var1 = new PrintWriter(new FileWriter(this.optionsFile));
         var1.println("invertYMouse:" + this.invertMouse);
         var1.println("mouseSensitivity:" + this.mouseSensitivity);
         var1.println("fov:" + (this.fovSetting - 70.0F) / 40.0F);
         var1.println("gamma:" + this.gammaSetting);
         var1.println("saturation:" + this.saturation);
         var1.println("renderDistance:" + this.renderDistanceChunks);
         var1.println("guiScale:" + this.guiScale);
         var1.println("particles:" + this.particleSetting);
         var1.println("bobView:" + this.viewBobbing);
         var1.println("anaglyph3d:" + this.anaglyph);
         var1.println("advancedOpengl:" + this.advancedOpengl);
         var1.println("maxFps:" + this.limitFramerate);
         var1.println("fboEnable:" + this.fboEnable);
         var1.println("difficulty:" + this.difficulty.getDifficultyId());
         var1.println("fancyGraphics:" + this.fancyGraphics);
         var1.println("ao:" + this.ambientOcclusion);
         var1.println("clouds:" + this.clouds);
         var1.println("resourcePacks:" + gson.toJson(this.resourcePacks));
         var1.println("lastServer:" + this.lastServer);
         var1.println("lang:" + this.language);
         var1.println("chatVisibility:" + this.chatVisibility.getChatVisibility());
         var1.println("chatColors:" + this.chatColours);
         var1.println("chatLinks:" + this.chatLinks);
         var1.println("chatLinksPrompt:" + this.chatLinksPrompt);
         var1.println("chatOpacity:" + this.chatOpacity);
         var1.println("snooperEnabled:" + this.snooperEnabled);
         var1.println("fullscreen:" + this.fullScreen);
         var1.println("enableVsync:" + this.enableVsync);
         var1.println("hideServerAddress:" + this.hideServerAddress);
         var1.println("advancedItemTooltips:" + this.advancedItemTooltips);
         var1.println("pauseOnLostFocus:" + this.pauseOnLostFocus);
         var1.println("showCape:" + this.showCape);
         var1.println("touchscreen:" + this.touchscreen);
         var1.println("overrideWidth:" + this.overrideWidth);
         var1.println("overrideHeight:" + this.overrideHeight);
         var1.println("heldItemTooltips:" + this.heldItemTooltips);
         var1.println("chatHeightFocused:" + this.chatHeightFocused);
         var1.println("chatHeightUnfocused:" + this.chatHeightUnfocused);
         var1.println("chatScale:" + this.chatScale);
         var1.println("chatWidth:" + this.chatWidth);
         var1.println("showInventoryAchievementHint:" + this.showInventoryAchievementHint);
         var1.println("mipmapLevels:" + this.mipmapLevels);
         var1.println("anisotropicFiltering:" + this.anisotropicFiltering);
         var1.println("streamBytesPerPixel:" + this.field_152400_J);
         var1.println("streamMicVolume:" + this.field_152401_K);
         var1.println("streamSystemVolume:" + this.field_152402_L);
         var1.println("streamKbps:" + this.field_152403_M);
         var1.println("streamFps:" + this.field_152404_N);
         var1.println("streamCompression:" + this.field_152405_O);
         var1.println("streamSendMetadata:" + this.field_152406_P);
         var1.println("streamPreferredServer:" + this.field_152407_Q);
         var1.println("streamChatEnabled:" + this.field_152408_R);
         var1.println("streamChatUserFilter:" + this.field_152409_S);
         var1.println("streamMicToggleBehavior:" + this.field_152410_T);
         var1.println("forceUnicodeFont:" + this.forceUnicodeFont);
         KeyBinding[] var2 = this.keyBindings;
         int var3 = var2.length;

         int var4;
         for(var4 = 0; var4 < var3; ++var4) {
            KeyBinding var5 = var2[var4];
            var1.println("key_" + var5.getKeyDescription() + ":" + var5.getKeyCode());
         }

         SoundCategory[] var7 = SoundCategory.values();
         var3 = var7.length;

         for(var4 = 0; var4 < var3; ++var4) {
            SoundCategory var8 = var7[var4];
            var1.println("soundCategory_" + var8.getCategoryName() + ":" + this.getSoundLevel(var8));
         }

         var1.close();
      } catch (Exception var6) {
         logger.error("Failed to save options", var6);
      }

      this.sendSettingsToServer();
   }

   public float getSoundLevel(SoundCategory var1) {
      return this.mapSoundLevels.containsKey(var1)?((Float)this.mapSoundLevels.get(var1)).floatValue():1.0F;
   }

   public void setSoundLevel(SoundCategory var1, float var2) {
      this.mc.getSoundHandler().setSoundLevel(var1, var2);
      this.mapSoundLevels.put(var1, Float.valueOf(var2));
   }

   public void sendSettingsToServer() {
      if(this.mc.thePlayer != null) {
         this.mc.thePlayer.sendQueue.addToSendQueue(new C15PacketClientSettings(this.language, this.renderDistanceChunks, this.chatVisibility, this.chatColours, this.difficulty, this.showCape));
      }

   }

   public boolean shouldRenderClouds() {
      return this.renderDistanceChunks >= 4 && this.clouds;
   }

}
