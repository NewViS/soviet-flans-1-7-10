package net.minecraft.client.gui;

import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class GuiCommandBlock extends GuiScreen {

   private static final Logger field_146488_a = LogManager.getLogger();
   private GuiTextField commandTextField;
   private GuiTextField field_146486_g;
   private final CommandBlockLogic localCommandBlock;
   private GuiButton doneBtn;
   private GuiButton cancelBtn;


   public GuiCommandBlock(CommandBlockLogic var1) {
      this.localCommandBlock = var1;
   }

   public void updateScreen() {
      this.commandTextField.updateCursorCounter();
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      super.buttonList.clear();
      super.buttonList.add(this.doneBtn = new GuiButton(0, super.width / 2 - 4 - 150, super.height / 4 + 120 + 12, 150, 20, I18n.format("gui.done", new Object[0])));
      super.buttonList.add(this.cancelBtn = new GuiButton(1, super.width / 2 + 4, super.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));
      this.commandTextField = new GuiTextField(super.fontRendererObj, super.width / 2 - 150, 50, 300, 20);
      this.commandTextField.setMaxStringLength(32767);
      this.commandTextField.setFocused(true);
      this.commandTextField.setText(this.localCommandBlock.func_145753_i());
      this.field_146486_g = new GuiTextField(super.fontRendererObj, super.width / 2 - 150, 135, 300, 20);
      this.field_146486_g.setMaxStringLength(32767);
      this.field_146486_g.setEnabled(false);
      this.field_146486_g.setText(this.localCommandBlock.func_145753_i());
      if(this.localCommandBlock.func_145749_h() != null) {
         this.field_146486_g.setText(this.localCommandBlock.func_145749_h().getUnformattedText());
      }

      this.doneBtn.enabled = this.commandTextField.getText().trim().length() > 0;
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 1) {
            super.mc.displayGuiScreen((GuiScreen)null);
         } else if(var1.id == 0) {
            PacketBuffer var2 = new PacketBuffer(Unpooled.buffer());

            try {
               var2.writeByte(this.localCommandBlock.func_145751_f());
               this.localCommandBlock.func_145757_a(var2);
               var2.writeStringToBuffer(this.commandTextField.getText());
               super.mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload("MC|AdvCdm", var2));
            } catch (Exception var7) {
               field_146488_a.error("Couldn\'t send command block info", var7);
            } finally {
               var2.release();
            }

            super.mc.displayGuiScreen((GuiScreen)null);
         }

      }
   }

   protected void keyTyped(char var1, int var2) {
      this.commandTextField.textboxKeyTyped(var1, var2);
      this.field_146486_g.textboxKeyTyped(var1, var2);
      this.doneBtn.enabled = this.commandTextField.getText().trim().length() > 0;
      if(var2 != 28 && var2 != 156) {
         if(var2 == 1) {
            this.actionPerformed(this.cancelBtn);
         }
      } else {
         this.actionPerformed(this.doneBtn);
      }

   }

   protected void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.commandTextField.mouseClicked(var1, var2, var3);
      this.field_146486_g.mouseClicked(var1, var2, var3);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, I18n.format("advMode.setCommand", new Object[0]), super.width / 2, 20, 16777215);
      this.drawString(super.fontRendererObj, I18n.format("advMode.command", new Object[0]), super.width / 2 - 150, 37, 10526880);
      this.commandTextField.drawTextBox();
      byte var4 = 75;
      byte var5 = 0;
      FontRenderer var10001 = super.fontRendererObj;
      String var10002 = I18n.format("advMode.nearestPlayer", new Object[0]);
      int var10003 = super.width / 2 - 150;
      int var8 = var5 + 1;
      this.drawString(var10001, var10002, var10003, var4 + var5 * super.fontRendererObj.FONT_HEIGHT, 10526880);
      this.drawString(super.fontRendererObj, I18n.format("advMode.randomPlayer", new Object[0]), super.width / 2 - 150, var4 + var8++ * super.fontRendererObj.FONT_HEIGHT, 10526880);
      this.drawString(super.fontRendererObj, I18n.format("advMode.allPlayers", new Object[0]), super.width / 2 - 150, var4 + var8++ * super.fontRendererObj.FONT_HEIGHT, 10526880);
      if(this.field_146486_g.getText().length() > 0) {
         int var7 = var4 + var8 * super.fontRendererObj.FONT_HEIGHT + 20;
         this.drawString(super.fontRendererObj, I18n.format("advMode.previousOutput", new Object[0]), super.width / 2 - 150, var7, 10526880);
         this.field_146486_g.drawTextBox();
      }

      super.drawScreen(var1, var2, var3);
   }

}
