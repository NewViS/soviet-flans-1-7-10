package net.minecraft.client.gui.stream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.stream.GuiIngestServers$ServerList;
import net.minecraft.client.resources.I18n;

public class GuiIngestServers extends GuiScreen {

   private final GuiScreen field_152309_a;
   private String field_152310_f;
   private GuiIngestServers$ServerList field_152311_g;


   public GuiIngestServers(GuiScreen var1) {
      this.field_152309_a = var1;
   }

   public void initGui() {
      this.field_152310_f = I18n.format("options.stream.ingest.title", new Object[0]);
      this.field_152311_g = new GuiIngestServers$ServerList(this);
      if(!super.mc.func_152346_Z().func_152908_z()) {
         super.mc.func_152346_Z().func_152909_x();
      }

      super.buttonList.add(new GuiButton(1, super.width / 2 - 155, super.height - 24 - 6, 150, 20, I18n.format("gui.done", new Object[0])));
      super.buttonList.add(new GuiButton(2, super.width / 2 + 5, super.height - 24 - 6, 150, 20, I18n.format("options.stream.ingest.reset", new Object[0])));
   }

   public void onGuiClosed() {
      if(super.mc.func_152346_Z().func_152908_z()) {
         super.mc.func_152346_Z().func_152932_y().func_153039_l();
      }

   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 1) {
            super.mc.displayGuiScreen(this.field_152309_a);
         } else {
            super.mc.gameSettings.field_152407_Q = "";
            super.mc.gameSettings.saveOptions();
         }

      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.field_152311_g.drawScreen(var1, var2, var3);
      this.drawCenteredString(super.fontRendererObj, this.field_152310_f, super.width / 2, 20, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   // $FF: synthetic method
   static Minecraft access$000(GuiIngestServers var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static Minecraft access$100(GuiIngestServers var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static Minecraft access$200(GuiIngestServers var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static Minecraft access$300(GuiIngestServers var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static Minecraft access$400(GuiIngestServers var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static Minecraft access$500(GuiIngestServers var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static Minecraft access$600(GuiIngestServers var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static Minecraft access$700(GuiIngestServers var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static Minecraft access$800(GuiIngestServers var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static Minecraft access$900(GuiIngestServers var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static FontRenderer access$1000(GuiIngestServers var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$1100(GuiIngestServers var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$1200(GuiIngestServers var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$1300(GuiIngestServers var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$1400(GuiIngestServers var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$1500(GuiIngestServers var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$1600(GuiIngestServers var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$1700(GuiIngestServers var0) {
      return var0.fontRendererObj;
   }
}
