package net.minecraft.client.gui;

import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOptionsSounds$Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiScreenOptionsSounds extends GuiScreen {

   private final GuiScreen field_146505_f;
   private final GameSettings field_146506_g;
   protected String field_146507_a = "Options";
   private String field_146508_h;


   public GuiScreenOptionsSounds(GuiScreen var1, GameSettings var2) {
      this.field_146505_f = var1;
      this.field_146506_g = var2;
   }

   public void initGui() {
      byte var1 = 0;
      this.field_146507_a = I18n.format("options.sounds.title", new Object[0]);
      this.field_146508_h = I18n.format("options.off", new Object[0]);
      super.buttonList.add(new GuiScreenOptionsSounds$Button(this, SoundCategory.MASTER.getCategoryId(), super.width / 2 - 155 + var1 % 2 * 160, super.height / 6 - 12 + 24 * (var1 >> 1), SoundCategory.MASTER, true));
      int var6 = var1 + 2;
      SoundCategory[] var2 = SoundCategory.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         SoundCategory var5 = var2[var4];
         if(var5 != SoundCategory.MASTER) {
            super.buttonList.add(new GuiScreenOptionsSounds$Button(this, var5.getCategoryId(), super.width / 2 - 155 + var6 % 2 * 160, super.height / 6 - 12 + 24 * (var6 >> 1), var5, false));
            ++var6;
         }
      }

      super.buttonList.add(new GuiButton(200, super.width / 2 - 100, super.height / 6 + 168, I18n.format("gui.done", new Object[0])));
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 200) {
            super.mc.gameSettings.saveOptions();
            super.mc.displayGuiScreen(this.field_146505_f);
         }

      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, this.field_146507_a, super.width / 2, 15, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   protected String func_146504_a(SoundCategory var1) {
      float var2 = this.field_146506_g.getSoundLevel(var1);
      return var2 == 0.0F?this.field_146508_h:(int)(var2 * 100.0F) + "%";
   }

   // $FF: synthetic method
   static GameSettings access$000(GuiScreenOptionsSounds var0) {
      return var0.field_146506_g;
   }
}
