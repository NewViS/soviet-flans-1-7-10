package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IProgressUpdate;

public class GuiScreenWorking extends GuiScreen implements IProgressUpdate {

   private String field_146591_a = "";
   private String field_146589_f = "";
   private int field_146590_g;
   private boolean field_146592_h;


   public void displayProgressMessage(String var1) {
      this.resetProgressAndMessage(var1);
   }

   public void resetProgressAndMessage(String var1) {
      this.field_146591_a = var1;
      this.resetProgresAndWorkingMessage("Working...");
   }

   public void resetProgresAndWorkingMessage(String var1) {
      this.field_146589_f = var1;
      this.setLoadingProgress(0);
   }

   public void setLoadingProgress(int var1) {
      this.field_146590_g = var1;
   }

   public void func_146586_a() {
      this.field_146592_h = true;
   }

   public void drawScreen(int var1, int var2, float var3) {
      if(this.field_146592_h) {
         super.mc.displayGuiScreen((GuiScreen)null);
      } else {
         this.drawDefaultBackground();
         this.drawCenteredString(super.fontRendererObj, this.field_146591_a, super.width / 2, 70, 16777215);
         this.drawCenteredString(super.fontRendererObj, this.field_146589_f + " " + this.field_146590_g + "%", super.width / 2, 90, 16777215);
         super.drawScreen(var1, var2, var3);
      }
   }
}
