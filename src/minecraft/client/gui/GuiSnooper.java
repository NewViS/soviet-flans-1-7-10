package net.minecraft.client.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSnooper$List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;

public class GuiSnooper extends GuiScreen {

   private final GuiScreen field_146608_a;
   private final GameSettings field_146603_f;
   private final List field_146604_g = new ArrayList();
   private final List field_146609_h = new ArrayList();
   private String field_146610_i;
   private String[] field_146607_r;
   private GuiSnooper$List field_146606_s;
   private GuiButton field_146605_t;


   public GuiSnooper(GuiScreen var1, GameSettings var2) {
      this.field_146608_a = var1;
      this.field_146603_f = var2;
   }

   public void initGui() {
      this.field_146610_i = I18n.format("options.snooper.title", new Object[0]);
      String var1 = I18n.format("options.snooper.desc", new Object[0]);
      ArrayList var2 = new ArrayList();
      Iterator var3 = super.fontRendererObj.listFormattedStringToWidth(var1, super.width - 30).iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         var2.add(var4);
      }

      this.field_146607_r = (String[])var2.toArray(new String[0]);
      this.field_146604_g.clear();
      this.field_146609_h.clear();
      super.buttonList.add(this.field_146605_t = new GuiButton(1, super.width / 2 - 152, super.height - 30, 150, 20, this.field_146603_f.getKeyBinding(GameSettings$Options.SNOOPER_ENABLED)));
      super.buttonList.add(new GuiButton(2, super.width / 2 + 2, super.height - 30, 150, 20, I18n.format("gui.done", new Object[0])));
      boolean var6 = super.mc.getIntegratedServer() != null && super.mc.getIntegratedServer().getPlayerUsageSnooper() != null;
      Iterator var7 = (new TreeMap(super.mc.getPlayerUsageSnooper().getCurrentStats())).entrySet().iterator();

      Entry var5;
      while(var7.hasNext()) {
         var5 = (Entry)var7.next();
         this.field_146604_g.add((var6?"C ":"") + (String)var5.getKey());
         this.field_146609_h.add(super.fontRendererObj.trimStringToWidth((String)var5.getValue(), super.width - 220));
      }

      if(var6) {
         var7 = (new TreeMap(super.mc.getIntegratedServer().getPlayerUsageSnooper().getCurrentStats())).entrySet().iterator();

         while(var7.hasNext()) {
            var5 = (Entry)var7.next();
            this.field_146604_g.add("S " + (String)var5.getKey());
            this.field_146609_h.add(super.fontRendererObj.trimStringToWidth((String)var5.getValue(), super.width - 220));
         }
      }

      this.field_146606_s = new GuiSnooper$List(this);
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 2) {
            this.field_146603_f.saveOptions();
            this.field_146603_f.saveOptions();
            super.mc.displayGuiScreen(this.field_146608_a);
         }

         if(var1.id == 1) {
            this.field_146603_f.setOptionValue(GameSettings$Options.SNOOPER_ENABLED, 1);
            this.field_146605_t.displayString = this.field_146603_f.getKeyBinding(GameSettings$Options.SNOOPER_ENABLED);
         }

      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.field_146606_s.drawScreen(var1, var2, var3);
      this.drawCenteredString(super.fontRendererObj, this.field_146610_i, super.width / 2, 8, 16777215);
      int var4 = 22;
      String[] var5 = this.field_146607_r;
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         String var8 = var5[var7];
         this.drawCenteredString(super.fontRendererObj, var8, super.width / 2, var4, 8421504);
         var4 += super.fontRendererObj.FONT_HEIGHT;
      }

      super.drawScreen(var1, var2, var3);
   }

   // $FF: synthetic method
   static List access$000(GuiSnooper var0) {
      return var0.field_146604_g;
   }

   // $FF: synthetic method
   static List access$100(GuiSnooper var0) {
      return var0.field_146609_h;
   }
}
