package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLanguage$List;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;

public class GuiLanguage extends GuiScreen {

   protected GuiScreen field_146453_a;
   private GuiLanguage$List field_146450_f;
   private final GameSettings field_146451_g;
   private final LanguageManager field_146454_h;
   private GuiOptionButton field_146455_i;
   private GuiOptionButton field_146452_r;


   public GuiLanguage(GuiScreen var1, GameSettings var2, LanguageManager var3) {
      this.field_146453_a = var1;
      this.field_146451_g = var2;
      this.field_146454_h = var3;
   }

   public void initGui() {
      boolean var1 = false;
      if(this.field_146455_i != null) {
         ;
      }

      super.buttonList.add(this.field_146455_i = new GuiOptionButton(100, super.width / 2 - 155, super.height - 38, GameSettings$Options.FORCE_UNICODE_FONT, this.field_146451_g.getKeyBinding(GameSettings$Options.FORCE_UNICODE_FONT)));
      super.buttonList.add(this.field_146452_r = new GuiOptionButton(6, super.width / 2 - 155 + 160, super.height - 38, I18n.format("gui.done", new Object[0])));
      this.field_146450_f = new GuiLanguage$List(this);
      this.field_146450_f.registerScrollButtons(7, 8);
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         switch(var1.id) {
         case 5:
            break;
         case 6:
            super.mc.displayGuiScreen(this.field_146453_a);
            break;
         case 100:
            if(var1 instanceof GuiOptionButton) {
               this.field_146451_g.setOptionValue(((GuiOptionButton)var1).returnEnumOptions(), 1);
               var1.displayString = this.field_146451_g.getKeyBinding(GameSettings$Options.FORCE_UNICODE_FONT);
               ScaledResolution var2 = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
               int var3 = var2.getScaledWidth();
               int var4 = var2.getScaledHeight();
               this.setWorldAndResolution(super.mc, var3, var4);
            }
            break;
         default:
            this.field_146450_f.actionPerformed(var1);
         }

      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.field_146450_f.drawScreen(var1, var2, var3);
      this.drawCenteredString(super.fontRendererObj, I18n.format("options.language", new Object[0]), super.width / 2, 16, 16777215);
      this.drawCenteredString(super.fontRendererObj, "(" + I18n.format("options.languageWarning", new Object[0]) + ")", super.width / 2, super.height - 56, 8421504);
      super.drawScreen(var1, var2, var3);
   }

   // $FF: synthetic method
   static LanguageManager access$000(GuiLanguage var0) {
      return var0.field_146454_h;
   }

   // $FF: synthetic method
   static GameSettings access$100(GuiLanguage var0) {
      return var0.field_146451_g;
   }

   // $FF: synthetic method
   static GuiOptionButton access$200(GuiLanguage var0) {
      return var0.field_146452_r;
   }

   // $FF: synthetic method
   static GuiOptionButton access$300(GuiLanguage var0) {
      return var0.field_146455_i;
   }
}
