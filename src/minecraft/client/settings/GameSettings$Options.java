package net.minecraft.client.settings;

import net.minecraft.client.settings.GameSettings$1;
import net.minecraft.util.MathHelper;

public enum GameSettings$Options {

   INVERT_MOUSE("INVERT_MOUSE", 0, "options.invertMouse", false, true),
   SENSITIVITY("SENSITIVITY", 1, "options.sensitivity", true, false),
   FOV("FOV", 2, "options.fov", true, false, 30.0F, 110.0F, 1.0F),
   GAMMA("GAMMA", 3, "options.gamma", true, false),
   SATURATION("SATURATION", 4, "options.saturation", true, false),
   RENDER_DISTANCE("RENDER_DISTANCE", 5, "options.renderDistance", true, false, 2.0F, 16.0F, 1.0F),
   VIEW_BOBBING("VIEW_BOBBING", 6, "options.viewBobbing", false, true),
   ANAGLYPH("ANAGLYPH", 7, "options.anaglyph", false, true),
   ADVANCED_OPENGL("ADVANCED_OPENGL", 8, "options.advancedOpengl", false, true),
   FRAMERATE_LIMIT("FRAMERATE_LIMIT", 9, "options.framerateLimit", true, false, 10.0F, 260.0F, 10.0F),
   FBO_ENABLE("FBO_ENABLE", 10, "options.fboEnable", false, true),
   DIFFICULTY("DIFFICULTY", 11, "options.difficulty", false, false),
   GRAPHICS("GRAPHICS", 12, "options.graphics", false, false),
   AMBIENT_OCCLUSION("AMBIENT_OCCLUSION", 13, "options.ao", false, false),
   GUI_SCALE("GUI_SCALE", 14, "options.guiScale", false, false),
   RENDER_CLOUDS("RENDER_CLOUDS", 15, "options.renderClouds", false, true),
   PARTICLES("PARTICLES", 16, "options.particles", false, false),
   CHAT_VISIBILITY("CHAT_VISIBILITY", 17, "options.chat.visibility", false, false),
   CHAT_COLOR("CHAT_COLOR", 18, "options.chat.color", false, true),
   CHAT_LINKS("CHAT_LINKS", 19, "options.chat.links", false, true),
   CHAT_OPACITY("CHAT_OPACITY", 20, "options.chat.opacity", true, false),
   CHAT_LINKS_PROMPT("CHAT_LINKS_PROMPT", 21, "options.chat.links.prompt", false, true),
   SNOOPER_ENABLED("SNOOPER_ENABLED", 22, "options.snooper", false, true),
   USE_FULLSCREEN("USE_FULLSCREEN", 23, "options.fullscreen", false, true),
   ENABLE_VSYNC("ENABLE_VSYNC", 24, "options.vsync", false, true),
   SHOW_CAPE("SHOW_CAPE", 25, "options.showCape", false, true),
   TOUCHSCREEN("TOUCHSCREEN", 26, "options.touchscreen", false, true),
   CHAT_SCALE("CHAT_SCALE", 27, "options.chat.scale", true, false),
   CHAT_WIDTH("CHAT_WIDTH", 28, "options.chat.width", true, false),
   CHAT_HEIGHT_FOCUSED("CHAT_HEIGHT_FOCUSED", 29, "options.chat.height.focused", true, false),
   CHAT_HEIGHT_UNFOCUSED("CHAT_HEIGHT_UNFOCUSED", 30, "options.chat.height.unfocused", true, false),
   MIPMAP_LEVELS("MIPMAP_LEVELS", 31, "options.mipmapLevels", true, false, 0.0F, 4.0F, 1.0F),
   ANISOTROPIC_FILTERING("ANISOTROPIC_FILTERING", 32, "options.anisotropicFiltering", true, false, 1.0F, 16.0F, 0.0F),
   FORCE_UNICODE_FONT("FORCE_UNICODE_FONT", 33, "options.forceUnicodeFont", false, true),
   STREAM_BYTES_PER_PIXEL("STREAM_BYTES_PER_PIXEL", 34, "options.stream.bytesPerPixel", true, false),
   STREAM_VOLUME_MIC("STREAM_VOLUME_MIC", 35, "options.stream.micVolumne", true, false),
   STREAM_VOLUME_SYSTEM("STREAM_VOLUME_SYSTEM", 36, "options.stream.systemVolume", true, false),
   STREAM_KBPS("STREAM_KBPS", 37, "options.stream.kbps", true, false),
   STREAM_FPS("STREAM_FPS", 38, "options.stream.fps", true, false),
   STREAM_COMPRESSION("STREAM_COMPRESSION", 39, "options.stream.compression", false, false),
   STREAM_SEND_METADATA("STREAM_SEND_METADATA", 40, "options.stream.sendMetadata", false, true),
   STREAM_CHAT_ENABLED("STREAM_CHAT_ENABLED", 41, "options.stream.chat.enabled", false, false),
   STREAM_CHAT_USER_FILTER("STREAM_CHAT_USER_FILTER", 42, "options.stream.chat.userFilter", false, false),
   STREAM_MIC_TOGGLE_BEHAVIOR("STREAM_MIC_TOGGLE_BEHAVIOR", 43, "options.stream.micToggleBehavior", false, false);
   private final boolean enumFloat;
   private final boolean enumBoolean;
   private final String enumString;
   private final float valueStep;
   private float valueMin;
   private float valueMax;
   // $FF: synthetic field
   private static final GameSettings$Options[] $VALUES = new GameSettings$Options[]{INVERT_MOUSE, SENSITIVITY, FOV, GAMMA, SATURATION, RENDER_DISTANCE, VIEW_BOBBING, ANAGLYPH, ADVANCED_OPENGL, FRAMERATE_LIMIT, FBO_ENABLE, DIFFICULTY, GRAPHICS, AMBIENT_OCCLUSION, GUI_SCALE, RENDER_CLOUDS, PARTICLES, CHAT_VISIBILITY, CHAT_COLOR, CHAT_LINKS, CHAT_OPACITY, CHAT_LINKS_PROMPT, SNOOPER_ENABLED, USE_FULLSCREEN, ENABLE_VSYNC, SHOW_CAPE, TOUCHSCREEN, CHAT_SCALE, CHAT_WIDTH, CHAT_HEIGHT_FOCUSED, CHAT_HEIGHT_UNFOCUSED, MIPMAP_LEVELS, ANISOTROPIC_FILTERING, FORCE_UNICODE_FONT, STREAM_BYTES_PER_PIXEL, STREAM_VOLUME_MIC, STREAM_VOLUME_SYSTEM, STREAM_KBPS, STREAM_FPS, STREAM_COMPRESSION, STREAM_SEND_METADATA, STREAM_CHAT_ENABLED, STREAM_CHAT_USER_FILTER, STREAM_MIC_TOGGLE_BEHAVIOR};


   public static GameSettings$Options getEnumOptions(int var0) {
      GameSettings$Options[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         GameSettings$Options var4 = var1[var3];
         if(var4.returnEnumOrdinal() == var0) {
            return var4;
         }
      }

      return null;
   }

   private GameSettings$Options(String var1, int var2, String var3, boolean var4, boolean var5) {
      this(var1, var2, var3, var4, var5, 0.0F, 1.0F, 0.0F);
   }

   private GameSettings$Options(String var1, int var2, String var3, boolean var4, boolean var5, float var6, float var7, float var8) {
      this.enumString = var3;
      this.enumFloat = var4;
      this.enumBoolean = var5;
      this.valueMin = var6;
      this.valueMax = var7;
      this.valueStep = var8;
   }

   public boolean getEnumFloat() {
      return this.enumFloat;
   }

   public boolean getEnumBoolean() {
      return this.enumBoolean;
   }

   public int returnEnumOrdinal() {
      return this.ordinal();
   }

   public String getEnumString() {
      return this.enumString;
   }

   public float getValueMax() {
      return this.valueMax;
   }

   public void setValueMax(float var1) {
      this.valueMax = var1;
   }

   public float normalizeValue(float var1) {
      return MathHelper.clamp_float((this.snapToStepClamp(var1) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
   }

   public float denormalizeValue(float var1) {
      return this.snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp_float(var1, 0.0F, 1.0F));
   }

   public float snapToStepClamp(float var1) {
      var1 = this.snapToStep(var1);
      return MathHelper.clamp_float(var1, this.valueMin, this.valueMax);
   }

   protected float snapToStep(float var1) {
      if(this.valueStep > 0.0F) {
         var1 = this.valueStep * (float)Math.round(var1 / this.valueStep);
      }

      return var1;
   }

   // $FF: synthetic method
   GameSettings$Options(String var1, int var2, String var3, boolean var4, boolean var5, float var6, float var7, float var8, GameSettings$1 var9) {
      this(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   // $FF: synthetic method
   static float access$100(GameSettings$Options var0) {
      return var0.valueMax;
   }

}
