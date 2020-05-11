package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import org.lwjgl.input.Keyboard;

public class GuiRenameWorld extends GuiScreen {

   private GuiScreen field_146585_a;
   private GuiTextField field_146583_f;
   private final String field_146584_g;


   public GuiRenameWorld(GuiScreen var1, String var2) {
      this.field_146585_a = var1;
      this.field_146584_g = var2;
   }

   public void updateScreen() {
      this.field_146583_f.updateCursorCounter();
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      super.buttonList.clear();
      super.buttonList.add(new GuiButton(0, super.width / 2 - 100, super.height / 4 + 96 + 12, I18n.format("selectWorld.renameButton", new Object[0])));
      super.buttonList.add(new GuiButton(1, super.width / 2 - 100, super.height / 4 + 120 + 12, I18n.format("gui.cancel", new Object[0])));
      ISaveFormat var1 = super.mc.getSaveLoader();
      WorldInfo var2 = var1.getWorldInfo(this.field_146584_g);
      String var3 = var2.getWorldName();
      this.field_146583_f = new GuiTextField(super.fontRendererObj, super.width / 2 - 100, 60, 200, 20);
      this.field_146583_f.setFocused(true);
      this.field_146583_f.setText(var3);
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 1) {
            super.mc.displayGuiScreen(this.field_146585_a);
         } else if(var1.id == 0) {
            ISaveFormat var2 = super.mc.getSaveLoader();
            var2.renameWorld(this.field_146584_g, this.field_146583_f.getText().trim());
            super.mc.displayGuiScreen(this.field_146585_a);
         }

      }
   }

   protected void keyTyped(char var1, int var2) {
      this.field_146583_f.textboxKeyTyped(var1, var2);
      ((GuiButton)super.buttonList.get(0)).enabled = this.field_146583_f.getText().trim().length() > 0;
      if(var2 == 28 || var2 == 156) {
         this.actionPerformed((GuiButton)super.buttonList.get(0));
      }

   }

   protected void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.field_146583_f.mouseClicked(var1, var2, var3);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, I18n.format("selectWorld.renameTitle", new Object[0]), super.width / 2, 20, 16777215);
      this.drawString(super.fontRendererObj, I18n.format("selectWorld.enterName", new Object[0]), super.width / 2 - 100, 47, 10526880);
      this.field_146583_f.drawTextBox();
      super.drawScreen(var1, var2, var3);
   }
}
