package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;

public class ScreenChatOptions extends GuiScreen {

   private static final GameSettings$Options[] field_146399_a = new GameSettings$Options[]{GameSettings$Options.CHAT_VISIBILITY, GameSettings$Options.CHAT_COLOR, GameSettings$Options.CHAT_LINKS, GameSettings$Options.CHAT_OPACITY, GameSettings$Options.CHAT_LINKS_PROMPT, GameSettings$Options.CHAT_SCALE, GameSettings$Options.CHAT_HEIGHT_FOCUSED, GameSettings$Options.CHAT_HEIGHT_UNFOCUSED, GameSettings$Options.CHAT_WIDTH};
   private static final GameSettings$Options[] field_146395_f = new GameSettings$Options[]{GameSettings$Options.SHOW_CAPE};
   private final GuiScreen field_146396_g;
   private final GameSettings field_146400_h;
   private String field_146401_i;
   private String field_146398_r;
   private int field_146397_s;


   public ScreenChatOptions(GuiScreen var1, GameSettings var2) {
      this.field_146396_g = var1;
      this.field_146400_h = var2;
   }

   public void initGui() {
      int var1 = 0;
      this.field_146401_i = I18n.format("options.chat.title", new Object[0]);
      this.field_146398_r = I18n.format("options.multiplayer.title", new Object[0]);
      GameSettings$Options[] var2 = field_146399_a;
      int var3 = var2.length;

      int var4;
      GameSettings$Options var5;
      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         if(var5.getEnumFloat()) {
            super.buttonList.add(new GuiOptionSlider(var5.returnEnumOrdinal(), super.width / 2 - 155 + var1 % 2 * 160, super.height / 6 + 24 * (var1 >> 1), var5));
         } else {
            super.buttonList.add(new GuiOptionButton(var5.returnEnumOrdinal(), super.width / 2 - 155 + var1 % 2 * 160, super.height / 6 + 24 * (var1 >> 1), var5, this.field_146400_h.getKeyBinding(var5)));
         }

         ++var1;
      }

      if(var1 % 2 == 1) {
         ++var1;
      }

      this.field_146397_s = super.height / 6 + 24 * (var1 >> 1);
      var1 += 2;
      var2 = field_146395_f;
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         var5 = var2[var4];
         if(var5.getEnumFloat()) {
            super.buttonList.add(new GuiOptionSlider(var5.returnEnumOrdinal(), super.width / 2 - 155 + var1 % 2 * 160, super.height / 6 + 24 * (var1 >> 1), var5));
         } else {
            super.buttonList.add(new GuiOptionButton(var5.returnEnumOrdinal(), super.width / 2 - 155 + var1 % 2 * 160, super.height / 6 + 24 * (var1 >> 1), var5, this.field_146400_h.getKeyBinding(var5)));
         }

         ++var1;
      }

      super.buttonList.add(new GuiButton(200, super.width / 2 - 100, super.height / 6 + 168, I18n.format("gui.done", new Object[0])));
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id < 100 && var1 instanceof GuiOptionButton) {
            this.field_146400_h.setOptionValue(((GuiOptionButton)var1).returnEnumOptions(), 1);
            var1.displayString = this.field_146400_h.getKeyBinding(GameSettings$Options.getEnumOptions(var1.id));
         }

         if(var1.id == 200) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(this.field_146396_g);
         }

      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, this.field_146401_i, super.width / 2, 20, 16777215);
      this.drawCenteredString(super.fontRendererObj, this.field_146398_r, super.width / 2, this.field_146397_s + 7, 16777215);
      super.drawScreen(var1, var2, var3);
   }

}
