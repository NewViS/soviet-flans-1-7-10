package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.C00PacketKeepAlive;

public class GuiDownloadTerrain extends GuiScreen {

   private NetHandlerPlayClient field_146594_a;
   private int field_146593_f;


   public GuiDownloadTerrain(NetHandlerPlayClient var1) {
      this.field_146594_a = var1;
   }

   protected void keyTyped(char var1, int var2) {}

   public void initGui() {
      super.buttonList.clear();
   }

   public void updateScreen() {
      ++this.field_146593_f;
      if(this.field_146593_f % 20 == 0) {
         this.field_146594_a.addToSendQueue(new C00PacketKeepAlive());
      }

      if(this.field_146594_a != null) {
         this.field_146594_a.onNetworkTick();
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawBackground(0);
      this.drawCenteredString(super.fontRendererObj, I18n.format("multiplayer.downloadingTerrain", new Object[0]), super.width / 2, super.height / 2 - 50, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }
}
