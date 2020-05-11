package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.settings.GameSettings$Options;

class GuiLanguage$List extends GuiSlot {

   private final List field_148176_l;
   private final Map field_148177_m;
   // $FF: synthetic field
   final GuiLanguage field_148178_k;


   public GuiLanguage$List(GuiLanguage var1) {
      super(var1.mc, var1.width, var1.height, 32, var1.height - 65 + 4, 18);
      this.field_148178_k = var1;
      this.field_148176_l = Lists.newArrayList();
      this.field_148177_m = Maps.newHashMap();
      Iterator var2 = GuiLanguage.access$000(var1).getLanguages().iterator();

      while(var2.hasNext()) {
         Language var3 = (Language)var2.next();
         this.field_148177_m.put(var3.getLanguageCode(), var3);
         this.field_148176_l.add(var3.getLanguageCode());
      }

   }

   protected int getSize() {
      return this.field_148176_l.size();
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {
      Language var5 = (Language)this.field_148177_m.get(this.field_148176_l.get(var1));
      GuiLanguage.access$000(this.field_148178_k).setCurrentLanguage(var5);
      GuiLanguage.access$100(this.field_148178_k).language = var5.getLanguageCode();
      this.field_148178_k.mc.refreshResources();
      this.field_148178_k.fontRendererObj.setUnicodeFlag(GuiLanguage.access$000(this.field_148178_k).isCurrentLocaleUnicode() || GuiLanguage.access$100(this.field_148178_k).forceUnicodeFont);
      this.field_148178_k.fontRendererObj.setBidiFlag(GuiLanguage.access$000(this.field_148178_k).isCurrentLanguageBidirectional());
      GuiLanguage.access$200(this.field_148178_k).displayString = I18n.format("gui.done", new Object[0]);
      GuiLanguage.access$300(this.field_148178_k).displayString = GuiLanguage.access$100(this.field_148178_k).getKeyBinding(GameSettings$Options.FORCE_UNICODE_FONT);
      GuiLanguage.access$100(this.field_148178_k).saveOptions();
   }

   protected boolean isSelected(int var1) {
      return ((String)this.field_148176_l.get(var1)).equals(GuiLanguage.access$000(this.field_148178_k).getCurrentLanguage().getLanguageCode());
   }

   protected int getContentHeight() {
      return this.getSize() * 18;
   }

   protected void drawBackground() {
      this.field_148178_k.drawDefaultBackground();
   }

   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7) {
      this.field_148178_k.fontRendererObj.setBidiFlag(true);
      this.field_148178_k.drawCenteredString(this.field_148178_k.fontRendererObj, ((Language)this.field_148177_m.get(this.field_148176_l.get(var1))).toString(), super.width / 2, var3 + 1, 16777215);
      this.field_148178_k.fontRendererObj.setBidiFlag(GuiLanguage.access$000(this.field_148178_k).getCurrentLanguage().isBidirectional());
   }
}
