package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;

public class GuiVideoSettings extends GuiScreen {

   private GuiScreen parentGuiScreen;
   protected String screenTitle = "Video Settings";
   private GameSettings guiGameSettings;
   private GuiListExtended optionsRowList;
   private static final GameSettings$Options[] videoOptions = new GameSettings$Options[]{GameSettings$Options.GRAPHICS, GameSettings$Options.RENDER_DISTANCE, GameSettings$Options.AMBIENT_OCCLUSION, GameSettings$Options.FRAMERATE_LIMIT, GameSettings$Options.ANAGLYPH, GameSettings$Options.VIEW_BOBBING, GameSettings$Options.GUI_SCALE, GameSettings$Options.ADVANCED_OPENGL, GameSettings$Options.GAMMA, GameSettings$Options.RENDER_CLOUDS, GameSettings$Options.PARTICLES, GameSettings$Options.USE_FULLSCREEN, GameSettings$Options.ENABLE_VSYNC, GameSettings$Options.MIPMAP_LEVELS, GameSettings$Options.ANISOTROPIC_FILTERING};


   public GuiVideoSettings(GuiScreen var1, GameSettings var2) {
      this.parentGuiScreen = var1;
      this.guiGameSettings = var2;
   }

   public void initGui() {
      this.screenTitle = I18n.format("options.videoTitle", new Object[0]);
      super.buttonList.clear();
      super.buttonList.add(new GuiButton(200, super.width / 2 - 100, super.height - 27, I18n.format("gui.done", new Object[0])));
      if(OpenGlHelper.field_153197_d) {
         this.optionsRowList = new GuiOptionsRowList(super.mc, super.width, super.height, 32, super.height - 32, 25, videoOptions);
      } else {
         GameSettings$Options[] var1 = new GameSettings$Options[videoOptions.length - 1];
         int var2 = 0;
         GameSettings$Options[] var3 = videoOptions;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            GameSettings$Options var6 = var3[var5];
            if(var6 != GameSettings$Options.ADVANCED_OPENGL) {
               var1[var2] = var6;
               ++var2;
            }
         }

         this.optionsRowList = new GuiOptionsRowList(super.mc, super.width, super.height, 32, super.height - 32, 25, var1);
      }

   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 200) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(this.parentGuiScreen);
         }

      }
   }

   protected void mouseClicked(int var1, int var2, int var3) {
      int var4 = this.guiGameSettings.guiScale;
      super.mouseClicked(var1, var2, var3);
      this.optionsRowList.func_148179_a(var1, var2, var3);
      if(this.guiGameSettings.guiScale != var4) {
         ScaledResolution var5 = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
         int var6 = var5.getScaledWidth();
         int var7 = var5.getScaledHeight();
         this.setWorldAndResolution(super.mc, var6, var7);
      }

   }

   protected void mouseMovedOrUp(int var1, int var2, int var3) {
      int var4 = this.guiGameSettings.guiScale;
      super.mouseMovedOrUp(var1, var2, var3);
      this.optionsRowList.func_148181_b(var1, var2, var3);
      if(this.guiGameSettings.guiScale != var4) {
         ScaledResolution var5 = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
         int var6 = var5.getScaledWidth();
         int var7 = var5.getScaledHeight();
         this.setWorldAndResolution(super.mc, var6, var7);
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.optionsRowList.drawScreen(var1, var2, var3);
      this.drawCenteredString(super.fontRendererObj, this.screenTitle, super.width / 2, 5, 16777215);
      super.drawScreen(var1, var2, var3);
   }

}
