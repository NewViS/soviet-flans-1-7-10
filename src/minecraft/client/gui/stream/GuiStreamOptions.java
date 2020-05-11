package net.minecraft.client.gui.stream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.stream.GuiIngestServers;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.util.EnumChatFormatting;

public class GuiStreamOptions extends GuiScreen {

   private static final GameSettings$Options[] field_152312_a = new GameSettings$Options[]{GameSettings$Options.STREAM_BYTES_PER_PIXEL, GameSettings$Options.STREAM_FPS, GameSettings$Options.STREAM_KBPS, GameSettings$Options.STREAM_SEND_METADATA, GameSettings$Options.STREAM_VOLUME_MIC, GameSettings$Options.STREAM_VOLUME_SYSTEM, GameSettings$Options.STREAM_MIC_TOGGLE_BEHAVIOR, GameSettings$Options.STREAM_COMPRESSION};
   private static final GameSettings$Options[] field_152316_f = new GameSettings$Options[]{GameSettings$Options.STREAM_CHAT_ENABLED, GameSettings$Options.STREAM_CHAT_USER_FILTER};
   private final GuiScreen field_152317_g;
   private final GameSettings field_152318_h;
   private String field_152319_i;
   private String field_152313_r;
   private int field_152314_s;
   private boolean field_152315_t = false;


   public GuiStreamOptions(GuiScreen var1, GameSettings var2) {
      this.field_152317_g = var1;
      this.field_152318_h = var2;
   }

   public void initGui() {
      int var1 = 0;
      this.field_152319_i = I18n.format("options.stream.title", new Object[0]);
      this.field_152313_r = I18n.format("options.stream.chat.title", new Object[0]);
      GameSettings$Options[] var2 = field_152312_a;
      int var3 = var2.length;

      int var4;
      GameSettings$Options var5;
      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         if(var5.getEnumFloat()) {
            super.buttonList.add(new GuiOptionSlider(var5.returnEnumOrdinal(), super.width / 2 - 155 + var1 % 2 * 160, super.height / 6 + 24 * (var1 >> 1), var5));
         } else {
            super.buttonList.add(new GuiOptionButton(var5.returnEnumOrdinal(), super.width / 2 - 155 + var1 % 2 * 160, super.height / 6 + 24 * (var1 >> 1), var5, this.field_152318_h.getKeyBinding(var5)));
         }

         ++var1;
      }

      if(var1 % 2 == 1) {
         ++var1;
      }

      this.field_152314_s = super.height / 6 + 24 * (var1 >> 1) + 6;
      var1 += 2;
      var2 = field_152316_f;
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         if(var5.getEnumFloat()) {
            super.buttonList.add(new GuiOptionSlider(var5.returnEnumOrdinal(), super.width / 2 - 155 + var1 % 2 * 160, super.height / 6 + 24 * (var1 >> 1), var5));
         } else {
            super.buttonList.add(new GuiOptionButton(var5.returnEnumOrdinal(), super.width / 2 - 155 + var1 % 2 * 160, super.height / 6 + 24 * (var1 >> 1), var5, this.field_152318_h.getKeyBinding(var5)));
         }

         ++var1;
      }

      super.buttonList.add(new GuiButton(200, super.width / 2 - 155, super.height / 6 + 168, 150, 20, I18n.format("gui.done", new Object[0])));
      GuiButton var6 = new GuiButton(201, super.width / 2 + 5, super.height / 6 + 168, 150, 20, I18n.format("options.stream.ingestSelection", new Object[0]));
      var6.enabled = super.mc.func_152346_Z().func_152924_m() && super.mc.func_152346_Z().func_152925_v().length > 0 || super.mc.func_152346_Z().func_152908_z();
      super.buttonList.add(var6);
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id < 100 && var1 instanceof GuiOptionButton) {
            GameSettings$Options var2 = ((GuiOptionButton)var1).returnEnumOptions();
            this.field_152318_h.setOptionValue(var2, 1);
            var1.displayString = this.field_152318_h.getKeyBinding(GameSettings$Options.getEnumOptions(var1.id));
            if(super.mc.func_152346_Z().func_152934_n() && var2 != GameSettings$Options.STREAM_CHAT_ENABLED && var2 != GameSettings$Options.STREAM_CHAT_USER_FILTER) {
               this.field_152315_t = true;
            }
         } else if(var1 instanceof GuiOptionSlider) {
            if(var1.id == GameSettings$Options.STREAM_VOLUME_MIC.returnEnumOrdinal()) {
               super.mc.func_152346_Z().func_152915_s();
            } else if(var1.id == GameSettings$Options.STREAM_VOLUME_SYSTEM.returnEnumOrdinal()) {
               super.mc.func_152346_Z().func_152915_s();
            } else if(super.mc.func_152346_Z().func_152934_n()) {
               this.field_152315_t = true;
            }
         }

         if(var1.id == 200) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(this.field_152317_g);
         } else if(var1.id == 201) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(new GuiIngestServers(this));
         }

      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, this.field_152319_i, super.width / 2, 20, 16777215);
      this.drawCenteredString(super.fontRendererObj, this.field_152313_r, super.width / 2, this.field_152314_s, 16777215);
      if(this.field_152315_t) {
         this.drawCenteredString(super.fontRendererObj, EnumChatFormatting.RED + I18n.format("options.stream.changes", new Object[0]), super.width / 2, 20 + super.fontRendererObj.FONT_HEIGHT, 16777215);
      }

      super.drawScreen(var1, var2, var3);
   }

}
