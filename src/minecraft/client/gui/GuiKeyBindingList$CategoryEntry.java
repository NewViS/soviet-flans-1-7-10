package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.gui.GuiListExtended$IGuiListEntry;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;

public class GuiKeyBindingList$CategoryEntry implements GuiListExtended$IGuiListEntry {

   private final String field_148285_b;
   private final int field_148286_c;
   // $FF: synthetic field
   final GuiKeyBindingList field_148287_a;


   public GuiKeyBindingList$CategoryEntry(GuiKeyBindingList var1, String var2) {
      this.field_148287_a = var1;
      this.field_148285_b = I18n.format(var2, new Object[0]);
      this.field_148286_c = GuiKeyBindingList.access$100(var1).fontRenderer.getStringWidth(this.field_148285_b);
   }

   public void drawEntry(int var1, int var2, int var3, int var4, int var5, Tessellator var6, int var7, int var8, boolean var9) {
      GuiKeyBindingList.access$100(this.field_148287_a).fontRenderer.drawString(this.field_148285_b, GuiKeyBindingList.access$100(this.field_148287_a).currentScreen.width / 2 - this.field_148286_c / 2, var3 + var5 - GuiKeyBindingList.access$100(this.field_148287_a).fontRenderer.FONT_HEIGHT - 1, 16777215);
   }

   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      return false;
   }

   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {}
}
