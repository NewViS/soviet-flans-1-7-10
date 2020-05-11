package net.minecraft.client.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonRealmsProxy;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.realms.RealmsButton;
import net.minecraft.realms.RealmsScreen;

public class GuiScreenRealmsProxy extends GuiScreen {

   private RealmsScreen field_154330_a;


   public GuiScreenRealmsProxy(RealmsScreen var1) {
      this.field_154330_a = var1;
      super.buttonList = Collections.synchronizedList(new ArrayList());
   }

   public RealmsScreen func_154321_a() {
      return this.field_154330_a;
   }

   public void initGui() {
      this.field_154330_a.init();
      super.initGui();
   }

   public void func_154325_a(String var1, int var2, int var3, int var4) {
      super.drawCenteredString(super.fontRendererObj, var1, var2, var3, var4);
   }

   public void func_154322_b(String var1, int var2, int var3, int var4) {
      super.drawString(super.fontRendererObj, var1, var2, var3, var4);
   }

   public void drawTexturedModalRect(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_154330_a.blit(var1, var2, var3, var4, var5, var6);
      super.drawTexturedModalRect(var1, var2, var3, var4, var5, var6);
   }

   public void drawGradientRect(int var1, int var2, int var3, int var4, int var5, int var6) {
      super.drawGradientRect(var1, var2, var3, var4, var5, var6);
   }

   public void drawDefaultBackground() {
      super.drawDefaultBackground();
   }

   public boolean doesGuiPauseGame() {
      return super.doesGuiPauseGame();
   }

   public void drawWorldBackground(int var1) {
      super.drawWorldBackground(var1);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.field_154330_a.render(var1, var2, var3);
   }

   public void renderToolTip(ItemStack var1, int var2, int var3) {
      super.renderToolTip(var1, var2, var3);
   }

   public void drawCreativeTabHoveringText(String var1, int var2, int var3) {
      super.drawCreativeTabHoveringText(var1, var2, var3);
   }

   public void func_146283_a(List var1, int var2, int var3) {
      super.func_146283_a(var1, var2, var3);
   }

   public void updateScreen() {
      this.field_154330_a.tick();
      super.updateScreen();
   }

   public int func_154329_h() {
      return super.fontRendererObj.FONT_HEIGHT;
   }

   public int func_154326_c(String var1) {
      return super.fontRendererObj.getStringWidth(var1);
   }

   public void func_154319_c(String var1, int var2, int var3, int var4) {
      super.fontRendererObj.drawStringWithShadow(var1, var2, var3, var4);
   }

   public List func_154323_a(String var1, int var2) {
      return super.fontRendererObj.listFormattedStringToWidth(var1, var2);
   }

   public final void actionPerformed(GuiButton var1) {
      this.field_154330_a.buttonClicked(((GuiButtonRealmsProxy)var1).func_154317_g());
   }

   public void func_154324_i() {
      super.buttonList.clear();
   }

   public void func_154327_a(RealmsButton var1) {
      super.buttonList.add(var1.getProxy());
   }

   public List func_154320_j() {
      ArrayList var1 = new ArrayList(super.buttonList.size());
      Iterator var2 = super.buttonList.iterator();

      while(var2.hasNext()) {
         GuiButton var3 = (GuiButton)var2.next();
         var1.add(((GuiButtonRealmsProxy)var3).func_154317_g());
      }

      return var1;
   }

   public void func_154328_b(RealmsButton var1) {
      super.buttonList.remove(var1);
   }

   public void mouseClicked(int var1, int var2, int var3) {
      this.field_154330_a.mouseClicked(var1, var2, var3);
      super.mouseClicked(var1, var2, var3);
   }

   public void handleMouseInput() {
      this.field_154330_a.mouseEvent();
      super.handleMouseInput();
   }

   public void handleKeyboardInput() {
      this.field_154330_a.keyboardEvent();
      super.handleKeyboardInput();
   }

   public void mouseMovedOrUp(int var1, int var2, int var3) {
      this.field_154330_a.mouseReleased(var1, var2, var3);
   }

   public void mouseClickMove(int var1, int var2, int var3, long var4) {
      this.field_154330_a.mouseDragged(var1, var2, var3, var4);
   }

   public void keyTyped(char var1, int var2) {
      this.field_154330_a.keyPressed(var1, var2);
   }

   public void confirmClicked(boolean var1, int var2) {
      this.field_154330_a.confirmResult(var1, var2);
   }

   public void onGuiClosed() {
      this.field_154330_a.removed();
      super.onGuiClosed();
   }
}
