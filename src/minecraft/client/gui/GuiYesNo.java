package net.minecraft.client.gui;

import java.util.Iterator;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;

public class GuiYesNo extends GuiScreen {

   protected GuiYesNoCallback parentScreen;
   protected String field_146351_f;
   private String field_146354_r;
   protected String confirmButtonText;
   protected String cancelButtonText;
   protected int field_146357_i;
   private int field_146353_s;


   public GuiYesNo(GuiYesNoCallback var1, String var2, String var3, int var4) {
      this.parentScreen = var1;
      this.field_146351_f = var2;
      this.field_146354_r = var3;
      this.field_146357_i = var4;
      this.confirmButtonText = I18n.format("gui.yes", new Object[0]);
      this.cancelButtonText = I18n.format("gui.no", new Object[0]);
   }

   public GuiYesNo(GuiYesNoCallback var1, String var2, String var3, String var4, String var5, int var6) {
      this.parentScreen = var1;
      this.field_146351_f = var2;
      this.field_146354_r = var3;
      this.confirmButtonText = var4;
      this.cancelButtonText = var5;
      this.field_146357_i = var6;
   }

   public void initGui() {
      super.buttonList.add(new GuiOptionButton(0, super.width / 2 - 155, super.height / 6 + 96, this.confirmButtonText));
      super.buttonList.add(new GuiOptionButton(1, super.width / 2 - 155 + 160, super.height / 6 + 96, this.cancelButtonText));
   }

   protected void actionPerformed(GuiButton var1) {
      this.parentScreen.confirmClicked(var1.id == 0, this.field_146357_i);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, this.field_146351_f, super.width / 2, 70, 16777215);
      this.drawCenteredString(super.fontRendererObj, this.field_146354_r, super.width / 2, 90, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   public void func_146350_a(int var1) {
      this.field_146353_s = var1;

      GuiButton var3;
      for(Iterator var2 = super.buttonList.iterator(); var2.hasNext(); var3.enabled = false) {
         var3 = (GuiButton)var2.next();
      }

   }

   public void updateScreen() {
      super.updateScreen();
      GuiButton var2;
      if(--this.field_146353_s == 0) {
         for(Iterator var1 = super.buttonList.iterator(); var1.hasNext(); var2.enabled = true) {
            var2 = (GuiButton)var1.next();
         }
      }

   }
}
