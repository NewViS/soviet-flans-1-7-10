package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiMemoryErrorScreen extends GuiScreen {

   public void initGui() {
      super.buttonList.clear();
      super.buttonList.add(new GuiOptionButton(0, super.width / 2 - 155, super.height / 4 + 120 + 12, I18n.format("gui.toMenu", new Object[0])));
      super.buttonList.add(new GuiOptionButton(1, super.width / 2 - 155 + 160, super.height / 4 + 120 + 12, I18n.format("menu.quit", new Object[0])));
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.id == 0) {
         super.mc.displayGuiScreen(new GuiMainMenu());
      } else if(var1.id == 1) {
         super.mc.shutdown();
      }

   }

   protected void keyTyped(char var1, int var2) {}

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, "Out of memory!", super.width / 2, super.height / 4 - 60 + 20, 16777215);
      this.drawString(super.fontRendererObj, "Minecraft has run out of memory.", super.width / 2 - 140, super.height / 4 - 60 + 60 + 0, 10526880);
      this.drawString(super.fontRendererObj, "This could be caused by a bug in the game or by the", super.width / 2 - 140, super.height / 4 - 60 + 60 + 18, 10526880);
      this.drawString(super.fontRendererObj, "Java Virtual Machine not being allocated enough", super.width / 2 - 140, super.height / 4 - 60 + 60 + 27, 10526880);
      this.drawString(super.fontRendererObj, "memory.", super.width / 2 - 140, super.height / 4 - 60 + 60 + 36, 10526880);
      this.drawString(super.fontRendererObj, "To prevent level corruption, the current game has quit.", super.width / 2 - 140, super.height / 4 - 60 + 60 + 54, 10526880);
      this.drawString(super.fontRendererObj, "We\'ve tried to free up enough memory to let you go back to", super.width / 2 - 140, super.height / 4 - 60 + 60 + 63, 10526880);
      this.drawString(super.fontRendererObj, "the main menu and back to playing, but this may not have worked.", super.width / 2 - 140, super.height / 4 - 60 + 60 + 72, 10526880);
      this.drawString(super.fontRendererObj, "Please restart the game if you see this message again.", super.width / 2 - 140, super.height / 4 - 60 + 60 + 81, 10526880);
      super.drawScreen(var1, var2, var3);
   }
}
