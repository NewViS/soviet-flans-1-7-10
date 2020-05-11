package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiErrorScreen extends GuiScreen {

   private String field_146313_a;
   private String field_146312_f;


   public GuiErrorScreen(String var1, String var2) {
      this.field_146313_a = var1;
      this.field_146312_f = var2;
   }

   public void initGui() {
      super.initGui();
      super.buttonList.add(new GuiButton(0, super.width / 2 - 100, 140, I18n.format("gui.cancel", new Object[0])));
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawGradientRect(0, 0, super.width, super.height, -12574688, -11530224);
      this.drawCenteredString(super.fontRendererObj, this.field_146313_a, super.width / 2, 90, 16777215);
      this.drawCenteredString(super.fontRendererObj, this.field_146312_f, super.width / 2, 110, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   protected void keyTyped(char var1, int var2) {}

   protected void actionPerformed(GuiButton var1) {
      super.mc.displayGuiScreen((GuiScreen)null);
   }
}
