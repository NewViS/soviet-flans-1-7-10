package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;

public class GuiScreenServerList extends GuiScreen {

   private final GuiScreen field_146303_a;
   private final ServerData field_146301_f;
   private GuiTextField field_146302_g;


   public GuiScreenServerList(GuiScreen var1, ServerData var2) {
      this.field_146303_a = var1;
      this.field_146301_f = var2;
   }

   public void updateScreen() {
      this.field_146302_g.updateCursorCounter();
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      super.buttonList.clear();
      super.buttonList.add(new GuiButton(0, super.width / 2 - 100, super.height / 4 + 96 + 12, I18n.format("selectServer.select", new Object[0])));
      super.buttonList.add(new GuiButton(1, super.width / 2 - 100, super.height / 4 + 120 + 12, I18n.format("gui.cancel", new Object[0])));
      this.field_146302_g = new GuiTextField(super.fontRendererObj, super.width / 2 - 100, 116, 200, 20);
      this.field_146302_g.setMaxStringLength(128);
      this.field_146302_g.setFocused(true);
      this.field_146302_g.setText(super.mc.gameSettings.lastServer);
      ((GuiButton)super.buttonList.get(0)).enabled = this.field_146302_g.getText().length() > 0 && this.field_146302_g.getText().split(":").length > 0;
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      super.mc.gameSettings.lastServer = this.field_146302_g.getText();
      super.mc.gameSettings.saveOptions();
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 1) {
            this.field_146303_a.confirmClicked(false, 0);
         } else if(var1.id == 0) {
            this.field_146301_f.serverIP = this.field_146302_g.getText();
            this.field_146303_a.confirmClicked(true, 0);
         }

      }
   }

   protected void keyTyped(char var1, int var2) {
      if(this.field_146302_g.textboxKeyTyped(var1, var2)) {
         ((GuiButton)super.buttonList.get(0)).enabled = this.field_146302_g.getText().length() > 0 && this.field_146302_g.getText().split(":").length > 0;
      } else if(var2 == 28 || var2 == 156) {
         this.actionPerformed((GuiButton)super.buttonList.get(0));
      }

   }

   protected void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.field_146302_g.mouseClicked(var1, var2, var3);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, I18n.format("selectServer.direct", new Object[0]), super.width / 2, 20, 16777215);
      this.drawString(super.fontRendererObj, I18n.format("addServer.enterIp", new Object[0]), super.width / 2 - 100, 100, 10526880);
      this.field_146302_g.drawTextBox();
      super.drawScreen(var1, var2, var3);
   }
}
