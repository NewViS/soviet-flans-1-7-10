package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.GuiSnooper;
import net.minecraft.client.renderer.Tessellator;

class GuiSnooper$List extends GuiSlot {

   // $FF: synthetic field
   final GuiSnooper field_148206_k;


   public GuiSnooper$List(GuiSnooper var1) {
      super(var1.mc, var1.width, var1.height, 80, var1.height - 40, var1.fontRendererObj.FONT_HEIGHT + 1);
      this.field_148206_k = var1;
   }

   protected int getSize() {
      return GuiSnooper.access$000(this.field_148206_k).size();
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {}

   protected boolean isSelected(int var1) {
      return false;
   }

   protected void drawBackground() {}

   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7) {
      this.field_148206_k.fontRendererObj.drawString((String)GuiSnooper.access$000(this.field_148206_k).get(var1), 10, var3, 16777215);
      this.field_148206_k.fontRendererObj.drawString((String)GuiSnooper.access$100(this.field_148206_k).get(var1), 230, var3, 16777215);
   }

   protected int getScrollBarX() {
      return super.width - 10;
   }
}
