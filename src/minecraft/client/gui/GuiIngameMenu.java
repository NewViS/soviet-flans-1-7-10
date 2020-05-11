package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;

public class GuiIngameMenu extends GuiScreen {

   private int field_146445_a;
   private int field_146444_f;


   public void initGui() {
      this.field_146445_a = 0;
      super.buttonList.clear();
      byte var1 = -16;
      boolean var2 = true;
      super.buttonList.add(new GuiButton(1, super.width / 2 - 100, super.height / 4 + 120 + var1, I18n.format("menu.returnToMenu", new Object[0])));
      if(!super.mc.isIntegratedServerRunning()) {
         ((GuiButton)super.buttonList.get(0)).displayString = I18n.format("menu.disconnect", new Object[0]);
      }

      super.buttonList.add(new GuiButton(4, super.width / 2 - 100, super.height / 4 + 24 + var1, I18n.format("menu.returnToGame", new Object[0])));
      super.buttonList.add(new GuiButton(0, super.width / 2 - 100, super.height / 4 + 96 + var1, 98, 20, I18n.format("menu.options", new Object[0])));
      GuiButton var3;
      super.buttonList.add(var3 = new GuiButton(7, super.width / 2 + 2, super.height / 4 + 96 + var1, 98, 20, I18n.format("menu.shareToLan", new Object[0])));
      super.buttonList.add(new GuiButton(5, super.width / 2 - 100, super.height / 4 + 48 + var1, 98, 20, I18n.format("gui.achievements", new Object[0])));
      super.buttonList.add(new GuiButton(6, super.width / 2 + 2, super.height / 4 + 48 + var1, 98, 20, I18n.format("gui.stats", new Object[0])));
      var3.enabled = super.mc.isSingleplayer() && !super.mc.getIntegratedServer().getPublic();
   }

   protected void actionPerformed(GuiButton var1) {
      switch(var1.id) {
      case 0:
         super.mc.displayGuiScreen(new GuiOptions(this, super.mc.gameSettings));
         break;
      case 1:
         var1.enabled = false;
         super.mc.theWorld.sendQuittingDisconnectingPacket();
         super.mc.loadWorld((WorldClient)null);
         super.mc.displayGuiScreen(new GuiMainMenu());
      case 2:
      case 3:
      default:
         break;
      case 4:
         super.mc.displayGuiScreen((GuiScreen)null);
         super.mc.setIngameFocus();
         break;
      case 5:
         super.mc.displayGuiScreen(new GuiAchievements(this, super.mc.thePlayer.getStatFileWriter()));
         break;
      case 6:
         super.mc.displayGuiScreen(new GuiStats(this, super.mc.thePlayer.getStatFileWriter()));
         break;
      case 7:
         super.mc.displayGuiScreen(new GuiShareToLan(this));
      }

   }

   public void updateScreen() {
      super.updateScreen();
      ++this.field_146444_f;
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, I18n.format("menu.game", new Object[0]), super.width / 2, 40, 16777215);
      super.drawScreen(var1, var2, var3);
   }
}
