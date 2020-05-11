package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiOptions$1;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiSnooper;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.client.stream.IStream;

public class GuiOptions extends GuiScreen implements GuiYesNoCallback {

   private static final GameSettings$Options[] field_146440_f = new GameSettings$Options[]{GameSettings$Options.FOV, GameSettings$Options.DIFFICULTY};
   private final GuiScreen field_146441_g;
   private final GameSettings field_146443_h;
   protected String field_146442_a = "Options";


   public GuiOptions(GuiScreen var1, GameSettings var2) {
      this.field_146441_g = var1;
      this.field_146443_h = var2;
   }

   public void initGui() {
      int var1 = 0;
      this.field_146442_a = I18n.format("options.title", new Object[0]);
      GameSettings$Options[] var2 = field_146440_f;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         GameSettings$Options var5 = var2[var4];
         if(var5.getEnumFloat()) {
            super.buttonList.add(new GuiOptionSlider(var5.returnEnumOrdinal(), super.width / 2 - 155 + var1 % 2 * 160, super.height / 6 - 12 + 24 * (var1 >> 1), var5));
         } else {
            GuiOptionButton var6 = new GuiOptionButton(var5.returnEnumOrdinal(), super.width / 2 - 155 + var1 % 2 * 160, super.height / 6 - 12 + 24 * (var1 >> 1), var5, this.field_146443_h.getKeyBinding(var5));
            if(var5 == GameSettings$Options.DIFFICULTY && super.mc.theWorld != null && super.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
               var6.enabled = false;
               var6.displayString = I18n.format("options.difficulty", new Object[0]) + ": " + I18n.format("options.difficulty.hardcore", new Object[0]);
            }

            super.buttonList.add(var6);
         }

         ++var1;
      }

      super.buttonList.add(new GuiOptions$1(this, 8675309, super.width / 2 + 5, super.height / 6 + 48 - 6, 150, 20, "Super Secret Settings..."));
      super.buttonList.add(new GuiButton(106, super.width / 2 - 155, super.height / 6 + 72 - 6, 150, 20, I18n.format("options.sounds", new Object[0])));
      super.buttonList.add(new GuiButton(107, super.width / 2 + 5, super.height / 6 + 72 - 6, 150, 20, I18n.format("options.stream", new Object[0])));
      super.buttonList.add(new GuiButton(101, super.width / 2 - 155, super.height / 6 + 96 - 6, 150, 20, I18n.format("options.video", new Object[0])));
      super.buttonList.add(new GuiButton(100, super.width / 2 + 5, super.height / 6 + 96 - 6, 150, 20, I18n.format("options.controls", new Object[0])));
      super.buttonList.add(new GuiButton(102, super.width / 2 - 155, super.height / 6 + 120 - 6, 150, 20, I18n.format("options.language", new Object[0])));
      super.buttonList.add(new GuiButton(103, super.width / 2 + 5, super.height / 6 + 120 - 6, 150, 20, I18n.format("options.multiplayer.title", new Object[0])));
      super.buttonList.add(new GuiButton(105, super.width / 2 - 155, super.height / 6 + 144 - 6, 150, 20, I18n.format("options.resourcepack", new Object[0])));
      super.buttonList.add(new GuiButton(104, super.width / 2 + 5, super.height / 6 + 144 - 6, 150, 20, I18n.format("options.snooper.view", new Object[0])));
      super.buttonList.add(new GuiButton(200, super.width / 2 - 100, super.height / 6 + 168, I18n.format("gui.done", new Object[0])));
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id < 100 && var1 instanceof GuiOptionButton) {
            this.field_146443_h.setOptionValue(((GuiOptionButton)var1).returnEnumOptions(), 1);
            var1.displayString = this.field_146443_h.getKeyBinding(GameSettings$Options.getEnumOptions(var1.id));
         }

         if(var1.id == 8675309) {
            super.mc.entityRenderer.activateNextShader();
         }

         if(var1.id == 101) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(new GuiVideoSettings(this, this.field_146443_h));
         }

         if(var1.id == 100) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(new GuiControls(this, this.field_146443_h));
         }

         if(var1.id == 102) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(new GuiLanguage(this, this.field_146443_h, super.mc.getLanguageManager()));
         }

         if(var1.id == 103) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(new ScreenChatOptions(this, this.field_146443_h));
         }

         if(var1.id == 104) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(new GuiSnooper(this, this.field_146443_h));
         }

         if(var1.id == 200) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(this.field_146441_g);
         }

         if(var1.id == 105) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(new GuiScreenResourcePacks(this));
         }

         if(var1.id == 106) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(new GuiScreenOptionsSounds(this, this.field_146443_h));
         }

         if(var1.id == 107) {
            super.mc.gameSettings.saveOptions();
            IStream var2 = super.mc.func_152346_Z();
            if(var2.func_152936_l() && var2.func_152928_D()) {
               super.mc.displayGuiScreen(new GuiStreamOptions(this, this.field_146443_h));
            } else {
               GuiStreamUnavailable.func_152321_a(this);
            }
         }

      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, this.field_146442_a, super.width / 2, 15, 16777215);
      super.drawScreen(var1, var2, var3);
   }

}
