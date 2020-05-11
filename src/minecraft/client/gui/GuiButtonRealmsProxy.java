package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.realms.RealmsButton;

public class GuiButtonRealmsProxy extends GuiButton {

   private RealmsButton field_154318_o;


   public GuiButtonRealmsProxy(RealmsButton var1, int var2, int var3, int var4, String var5) {
      super(var2, var3, var4, var5);
      this.field_154318_o = var1;
   }

   public GuiButtonRealmsProxy(RealmsButton var1, int var2, int var3, int var4, String var5, int var6, int var7) {
      super(var2, var3, var4, var6, var7, var5);
      this.field_154318_o = var1;
   }

   public int func_154314_d() {
      return super.id;
   }

   public boolean func_154315_e() {
      return super.enabled;
   }

   public void func_154313_b(boolean var1) {
      super.enabled = var1;
   }

   public void func_154311_a(String var1) {
      super.displayString = var1;
   }

   public int getButtonWidth() {
      return super.getButtonWidth();
   }

   public int func_154316_f() {
      return super.yPosition;
   }

   public boolean mousePressed(Minecraft var1, int var2, int var3) {
      if(super.mousePressed(var1, var2, var3)) {
         this.field_154318_o.clicked(var2, var3);
      }

      return super.mousePressed(var1, var2, var3);
   }

   public void mouseReleased(int var1, int var2) {
      this.field_154318_o.released(var1, var2);
   }

   public void mouseDragged(Minecraft var1, int var2, int var3) {
      this.field_154318_o.renderBg(var2, var3);
   }

   public RealmsButton func_154317_g() {
      return this.field_154318_o;
   }

   public int getHoverState(boolean var1) {
      return this.field_154318_o.getYImage(var1);
   }

   public int func_154312_c(boolean var1) {
      return super.getHoverState(var1);
   }
}
