package net.minecraft.client.gui.stream;

import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.stream.GuiIngestServers;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.stream.IngestServerTester;
import net.minecraft.util.EnumChatFormatting;
import tv.twitch.broadcast.IngestServer;

class GuiIngestServers$ServerList extends GuiSlot {

   // $FF: synthetic field
   final GuiIngestServers field_152435_k;


   public GuiIngestServers$ServerList(GuiIngestServers var1) {
      super(GuiIngestServers.access$000(var1), var1.width, var1.height, 32, var1.height - 35, (int)((double)GuiIngestServers.access$100(var1).fontRenderer.FONT_HEIGHT * 3.5D));
      this.field_152435_k = var1;
      this.setShowSelectionBox(false);
   }

   protected int getSize() {
      return GuiIngestServers.access$200(this.field_152435_k).func_152346_Z().func_152925_v().length;
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {
      GuiIngestServers.access$300(this.field_152435_k).gameSettings.field_152407_Q = GuiIngestServers.access$400(this.field_152435_k).func_152346_Z().func_152925_v()[var1].serverUrl;
      GuiIngestServers.access$500(this.field_152435_k).gameSettings.saveOptions();
   }

   protected boolean isSelected(int var1) {
      return GuiIngestServers.access$700(this.field_152435_k).func_152346_Z().func_152925_v()[var1].serverUrl.equals(GuiIngestServers.access$600(this.field_152435_k).gameSettings.field_152407_Q);
   }

   protected void drawBackground() {}

   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7) {
      IngestServer var8 = GuiIngestServers.access$800(this.field_152435_k).func_152346_Z().func_152925_v()[var1];
      String var9 = var8.serverUrl.replaceAll("\\{stream_key\\}", "");
      String var10 = (int)var8.bitrateKbps + " kbps";
      String var11 = null;
      IngestServerTester var12 = GuiIngestServers.access$900(this.field_152435_k).func_152346_Z().func_152932_y();
      if(var12 != null) {
         if(var8 == var12.func_153040_c()) {
            var9 = EnumChatFormatting.GREEN + var9;
            var10 = (int)(var12.func_153030_h() * 100.0F) + "%";
         } else if(var1 < var12.func_153028_p()) {
            if(var8.bitrateKbps == 0.0F) {
               var10 = EnumChatFormatting.RED + "Down!";
            }
         } else {
            var10 = EnumChatFormatting.OBFUSCATED + "1234" + EnumChatFormatting.RESET + " kbps";
         }
      } else if(var8.bitrateKbps == 0.0F) {
         var10 = EnumChatFormatting.RED + "Down!";
      }

      var2 -= 15;
      if(this.isSelected(var1)) {
         var11 = EnumChatFormatting.BLUE + "(Preferred)";
      } else if(var8.defaultServer) {
         var11 = EnumChatFormatting.GREEN + "(Default)";
      }

      this.field_152435_k.drawString(GuiIngestServers.access$1000(this.field_152435_k), var8.serverName, var2 + 2, var3 + 5, 16777215);
      this.field_152435_k.drawString(GuiIngestServers.access$1100(this.field_152435_k), var9, var2 + 2, var3 + GuiIngestServers.access$1200(this.field_152435_k).FONT_HEIGHT + 5 + 3, 3158064);
      this.field_152435_k.drawString(GuiIngestServers.access$1300(this.field_152435_k), var10, this.getScrollBarX() - 5 - GuiIngestServers.access$1400(this.field_152435_k).getStringWidth(var10), var3 + 5, 8421504);
      if(var11 != null) {
         this.field_152435_k.drawString(GuiIngestServers.access$1500(this.field_152435_k), var11, this.getScrollBarX() - 5 - GuiIngestServers.access$1600(this.field_152435_k).getStringWidth(var11), var3 + 5 + 3 + GuiIngestServers.access$1700(this.field_152435_k).FONT_HEIGHT, 8421504);
      }

   }

   protected int getScrollBarX() {
      return super.getScrollBarX() + 15;
   }
}
