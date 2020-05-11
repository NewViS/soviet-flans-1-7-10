package net.minecraft.client.gui;

import java.util.Iterator;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

public class GuiGameOver extends GuiScreen implements GuiYesNoCallback {

   private int field_146347_a;
   private boolean field_146346_f = false;


   public void initGui() {
      super.buttonList.clear();
      if(super.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
         if(super.mc.isIntegratedServerRunning()) {
            super.buttonList.add(new GuiButton(1, super.width / 2 - 100, super.height / 4 + 96, I18n.format("deathScreen.deleteWorld", new Object[0])));
         } else {
            super.buttonList.add(new GuiButton(1, super.width / 2 - 100, super.height / 4 + 96, I18n.format("deathScreen.leaveServer", new Object[0])));
         }
      } else {
         super.buttonList.add(new GuiButton(0, super.width / 2 - 100, super.height / 4 + 72, I18n.format("deathScreen.respawn", new Object[0])));
         super.buttonList.add(new GuiButton(1, super.width / 2 - 100, super.height / 4 + 96, I18n.format("deathScreen.titleScreen", new Object[0])));
         if(super.mc.getSession() == null) {
            ((GuiButton)super.buttonList.get(1)).enabled = false;
         }
      }

      GuiButton var2;
      for(Iterator var1 = super.buttonList.iterator(); var1.hasNext(); var2.enabled = false) {
         var2 = (GuiButton)var1.next();
      }

   }

   protected void keyTyped(char var1, int var2) {}

   protected void actionPerformed(GuiButton var1) {
      switch(var1.id) {
      case 0:
         super.mc.thePlayer.respawnPlayer();
         super.mc.displayGuiScreen((GuiScreen)null);
         break;
      case 1:
         GuiYesNo var2 = new GuiYesNo(this, I18n.format("deathScreen.quit.confirm", new Object[0]), "", I18n.format("deathScreen.titleScreen", new Object[0]), I18n.format("deathScreen.respawn", new Object[0]), 0);
         super.mc.displayGuiScreen(var2);
         var2.func_146350_a(20);
      }

   }

   public void confirmClicked(boolean var1, int var2) {
      if(var1) {
         super.mc.theWorld.sendQuittingDisconnectingPacket();
         super.mc.loadWorld((WorldClient)null);
         super.mc.displayGuiScreen(new GuiMainMenu());
      } else {
         super.mc.thePlayer.respawnPlayer();
         super.mc.displayGuiScreen((GuiScreen)null);
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawGradientRect(0, 0, super.width, super.height, 1615855616, -1602211792);
      GL11.glPushMatrix();
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      boolean var4 = super.mc.theWorld.getWorldInfo().isHardcoreModeEnabled();
      String var5 = var4?I18n.format("deathScreen.title.hardcore", new Object[0]):I18n.format("deathScreen.title", new Object[0]);
      this.drawCenteredString(super.fontRendererObj, var5, super.width / 2 / 2, 30, 16777215);
      GL11.glPopMatrix();
      if(var4) {
         this.drawCenteredString(super.fontRendererObj, I18n.format("deathScreen.hardcoreInfo", new Object[0]), super.width / 2, 144, 16777215);
      }

      this.drawCenteredString(super.fontRendererObj, I18n.format("deathScreen.score", new Object[0]) + ": " + EnumChatFormatting.YELLOW + super.mc.thePlayer.getScore(), super.width / 2, 100, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void updateScreen() {
      super.updateScreen();
      ++this.field_146347_a;
      GuiButton var2;
      if(this.field_146347_a == 20) {
         for(Iterator var1 = super.buttonList.iterator(); var1.hasNext(); var2.enabled = true) {
            var2 = (GuiButton)var1.next();
         }
      }

   }
}
