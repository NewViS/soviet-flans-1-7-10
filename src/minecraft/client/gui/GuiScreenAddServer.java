package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerData$ServerResourceMode;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;

public class GuiScreenAddServer extends GuiScreen {

   private final GuiScreen field_146310_a;
   private final ServerData field_146311_h;
   private GuiTextField field_146308_f;
   private GuiTextField field_146309_g;
   private GuiButton field_152176_i;


   public GuiScreenAddServer(GuiScreen var1, ServerData var2) {
      this.field_146310_a = var1;
      this.field_146311_h = var2;
   }

   public void updateScreen() {
      this.field_146309_g.updateCursorCounter();
      this.field_146308_f.updateCursorCounter();
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      super.buttonList.clear();
      super.buttonList.add(new GuiButton(0, super.width / 2 - 100, super.height / 4 + 96 + 18, I18n.format("addServer.add", new Object[0])));
      super.buttonList.add(new GuiButton(1, super.width / 2 - 100, super.height / 4 + 120 + 18, I18n.format("gui.cancel", new Object[0])));
      super.buttonList.add(this.field_152176_i = new GuiButton(2, super.width / 2 - 100, super.height / 4 + 72, I18n.format("addServer.resourcePack", new Object[0]) + ": " + this.field_146311_h.func_152586_b().func_152589_a().getFormattedText()));
      this.field_146309_g = new GuiTextField(super.fontRendererObj, super.width / 2 - 100, 66, 200, 20);
      this.field_146309_g.setFocused(true);
      this.field_146309_g.setText(this.field_146311_h.serverName);
      this.field_146308_f = new GuiTextField(super.fontRendererObj, super.width / 2 - 100, 106, 200, 20);
      this.field_146308_f.setMaxStringLength(128);
      this.field_146308_f.setText(this.field_146311_h.serverIP);
      ((GuiButton)super.buttonList.get(0)).enabled = this.field_146308_f.getText().length() > 0 && this.field_146308_f.getText().split(":").length > 0 && this.field_146309_g.getText().length() > 0;
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 2) {
            this.field_146311_h.func_152584_a(ServerData$ServerResourceMode.values()[(this.field_146311_h.func_152586_b().ordinal() + 1) % ServerData$ServerResourceMode.values().length]);
            this.field_152176_i.displayString = I18n.format("addServer.resourcePack", new Object[0]) + ": " + this.field_146311_h.func_152586_b().func_152589_a().getFormattedText();
         } else if(var1.id == 1) {
            this.field_146310_a.confirmClicked(false, 0);
         } else if(var1.id == 0) {
            this.field_146311_h.serverName = this.field_146309_g.getText();
            this.field_146311_h.serverIP = this.field_146308_f.getText();
            this.field_146310_a.confirmClicked(true, 0);
         }

      }
   }

   protected void keyTyped(char var1, int var2) {
      this.field_146309_g.textboxKeyTyped(var1, var2);
      this.field_146308_f.textboxKeyTyped(var1, var2);
      if(var2 == 15) {
         this.field_146309_g.setFocused(!this.field_146309_g.isFocused());
         this.field_146308_f.setFocused(!this.field_146308_f.isFocused());
      }

      if(var2 == 28 || var2 == 156) {
         this.actionPerformed((GuiButton)super.buttonList.get(0));
      }

      ((GuiButton)super.buttonList.get(0)).enabled = this.field_146308_f.getText().length() > 0 && this.field_146308_f.getText().split(":").length > 0 && this.field_146309_g.getText().length() > 0;
   }

   protected void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.field_146308_f.mouseClicked(var1, var2, var3);
      this.field_146309_g.mouseClicked(var1, var2, var3);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, I18n.format("addServer.title", new Object[0]), super.width / 2, 17, 16777215);
      this.drawString(super.fontRendererObj, I18n.format("addServer.enterName", new Object[0]), super.width / 2 - 100, 53, 10526880);
      this.drawString(super.fontRendererObj, I18n.format("addServer.enterIp", new Object[0]), super.width / 2 - 100, 94, 10526880);
      this.field_146309_g.drawTextBox();
      this.field_146308_f.drawTextBox();
      super.drawScreen(var1, var2, var3);
   }
}
