package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended$IGuiListEntry;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.network.LanServerDetector$LanServer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;

public class ServerListEntryLanDetected implements GuiListExtended$IGuiListEntry {

   private final GuiMultiplayer field_148292_c;
   protected final Minecraft field_148293_a;
   protected final LanServerDetector$LanServer field_148291_b;
   private long field_148290_d = 0L;


   protected ServerListEntryLanDetected(GuiMultiplayer var1, LanServerDetector$LanServer var2) {
      this.field_148292_c = var1;
      this.field_148291_b = var2;
      this.field_148293_a = Minecraft.getMinecraft();
   }

   public void drawEntry(int var1, int var2, int var3, int var4, int var5, Tessellator var6, int var7, int var8, boolean var9) {
      this.field_148293_a.fontRenderer.drawString(I18n.format("lanServer.title", new Object[0]), var2 + 32 + 3, var3 + 1, 16777215);
      this.field_148293_a.fontRenderer.drawString(this.field_148291_b.getServerMotd(), var2 + 32 + 3, var3 + 12, 8421504);
      if(this.field_148293_a.gameSettings.hideServerAddress) {
         this.field_148293_a.fontRenderer.drawString(I18n.format("selectServer.hiddenAddress", new Object[0]), var2 + 32 + 3, var3 + 12 + 11, 3158064);
      } else {
         this.field_148293_a.fontRenderer.drawString(this.field_148291_b.getServerIpPort(), var2 + 32 + 3, var3 + 12 + 11, 3158064);
      }

   }

   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_148292_c.func_146790_a(var1);
      if(Minecraft.getSystemTime() - this.field_148290_d < 250L) {
         this.field_148292_c.func_146796_h();
      }

      this.field_148290_d = Minecraft.getSystemTime();
      return false;
   }

   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {}

   public LanServerDetector$LanServer func_148289_a() {
      return this.field_148291_b;
   }
}
