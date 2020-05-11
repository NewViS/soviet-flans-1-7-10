package net.minecraft.client.gui;

import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IChatComponent;

public class GuiDisconnected extends GuiScreen {

   private String field_146306_a;
   private IChatComponent field_146304_f;
   private List field_146305_g;
   private final GuiScreen field_146307_h;


   public GuiDisconnected(GuiScreen var1, String var2, IChatComponent var3) {
      this.field_146307_h = var1;
      this.field_146306_a = I18n.format(var2, new Object[0]);
      this.field_146304_f = var3;
   }

   protected void keyTyped(char var1, int var2) {}

   public void initGui() {
      super.buttonList.clear();
      super.buttonList.add(new GuiButton(0, super.width / 2 - 100, super.height / 4 + 120 + 12, I18n.format("gui.toMenu", new Object[0])));
      this.field_146305_g = super.fontRendererObj.listFormattedStringToWidth(this.field_146304_f.getFormattedText(), super.width - 50);
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.id == 0) {
         super.mc.displayGuiScreen(this.field_146307_h);
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, this.field_146306_a, super.width / 2, super.height / 2 - 50, 11184810);
      int var4 = super.height / 2 - 30;
      if(this.field_146305_g != null) {
         for(Iterator var5 = this.field_146305_g.iterator(); var5.hasNext(); var4 += super.fontRendererObj.FONT_HEIGHT) {
            String var6 = (String)var5.next();
            this.drawCenteredString(super.fontRendererObj, var6, super.width / 2, var4, 16777215);
         }
      }

      super.drawScreen(var1, var2, var3);
   }
}
