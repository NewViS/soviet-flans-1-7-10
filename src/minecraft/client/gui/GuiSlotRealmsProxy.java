package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.realms.RealmsScrolledSelectionList;

public class GuiSlotRealmsProxy extends GuiSlot {

   private final RealmsScrolledSelectionList field_154340_k;


   public GuiSlotRealmsProxy(RealmsScrolledSelectionList var1, int var2, int var3, int var4, int var5, int var6) {
      super(Minecraft.getMinecraft(), var2, var3, var4, var5, var6);
      this.field_154340_k = var1;
   }

   protected int getSize() {
      return this.field_154340_k.getItemCount();
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {
      this.field_154340_k.selectItem(var1, var2, var3, var4);
   }

   protected boolean isSelected(int var1) {
      return this.field_154340_k.isSelectedItem(var1);
   }

   protected void drawBackground() {
      this.field_154340_k.renderBackground();
   }

   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7) {
      this.field_154340_k.renderItem(var1, var2, var3, var4, var6, var7);
   }

   public int func_154338_k() {
      return super.width;
   }

   public int func_154339_l() {
      return super.mouseY;
   }

   public int func_154337_m() {
      return super.mouseX;
   }

   protected int getContentHeight() {
      return this.field_154340_k.getMaxPosition();
   }

   protected int getScrollBarX() {
      return this.field_154340_k.getScrollbarPosition();
   }
}
