package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;

public class GuiConfirmOpenLink extends GuiYesNo {

   private final String openLinkWarning;
   private final String copyLinkButtonText;
   private final String field_146361_t;
   private boolean field_146360_u = true;


   public GuiConfirmOpenLink(GuiYesNoCallback var1, String var2, int var3, boolean var4) {
      super(var1, I18n.format(var4?"chat.link.confirmTrusted":"chat.link.confirm", new Object[0]), var2, var3);
      super.confirmButtonText = I18n.format(var4?"chat.link.open":"gui.yes", new Object[0]);
      super.cancelButtonText = I18n.format(var4?"gui.cancel":"gui.no", new Object[0]);
      this.copyLinkButtonText = I18n.format("chat.copy", new Object[0]);
      this.openLinkWarning = I18n.format("chat.link.warning", new Object[0]);
      this.field_146361_t = var2;
   }

   public void initGui() {
      super.buttonList.add(new GuiButton(0, super.width / 3 - 83 + 0, super.height / 6 + 96, 100, 20, super.confirmButtonText));
      super.buttonList.add(new GuiButton(2, super.width / 3 - 83 + 105, super.height / 6 + 96, 100, 20, this.copyLinkButtonText));
      super.buttonList.add(new GuiButton(1, super.width / 3 - 83 + 210, super.height / 6 + 96, 100, 20, super.cancelButtonText));
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.id == 2) {
         this.copyLinkToClipboard();
      }

      super.parentScreen.confirmClicked(var1.id == 0, super.field_146357_i);
   }

   public void copyLinkToClipboard() {
      setClipboardString(this.field_146361_t);
   }

   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      if(this.field_146360_u) {
         this.drawCenteredString(super.fontRendererObj, this.openLinkWarning, super.width / 2, 110, 16764108);
      }

   }

   public void func_146358_g() {
      this.field_146360_u = false;
   }
}
