package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended$IGuiListEntry;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;

public class ServerListEntryLanScan implements GuiListExtended$IGuiListEntry {

   private final Minecraft field_148288_a = Minecraft.getMinecraft();


   public void drawEntry(int var1, int var2, int var3, int var4, int var5, Tessellator var6, int var7, int var8, boolean var9) {
      int var10 = var3 + var5 / 2 - this.field_148288_a.fontRenderer.FONT_HEIGHT / 2;
      this.field_148288_a.fontRenderer.drawString(I18n.format("lanServer.scanning", new Object[0]), this.field_148288_a.currentScreen.width / 2 - this.field_148288_a.fontRenderer.getStringWidth(I18n.format("lanServer.scanning", new Object[0])) / 2, var10, 16777215);
      String var11;
      switch((int)(Minecraft.getSystemTime() / 300L % 4L)) {
      case 0:
      default:
         var11 = "O o o";
         break;
      case 1:
      case 3:
         var11 = "o O o";
         break;
      case 2:
         var11 = "o o O";
      }

      this.field_148288_a.fontRenderer.drawString(var11, this.field_148288_a.currentScreen.width / 2 - this.field_148288_a.fontRenderer.getStringWidth(var11) / 2, var10 + this.field_148288_a.fontRenderer.FONT_HEIGHT, 8421504);
   }

   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      return false;
   }

   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {}
}
