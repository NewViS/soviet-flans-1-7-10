package net.minecraft.client.gui.achievement;

import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;

class GuiStats$StatsGeneral extends GuiSlot {

   // $FF: synthetic field
   final GuiStats field_148208_k;


   public GuiStats$StatsGeneral(GuiStats var1) {
      super(GuiStats.access$000(var1), var1.width, var1.height, 32, var1.height - 64, 10);
      this.field_148208_k = var1;
      this.setShowSelectionBox(false);
   }

   protected int getSize() {
      return StatList.generalStats.size();
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {}

   protected boolean isSelected(int var1) {
      return false;
   }

   protected int getContentHeight() {
      return this.getSize() * 10;
   }

   protected void drawBackground() {
      this.field_148208_k.drawDefaultBackground();
   }

   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7) {
      StatBase var8 = (StatBase)StatList.generalStats.get(var1);
      this.field_148208_k.drawString(GuiStats.access$100(this.field_148208_k), var8.func_150951_e().getUnformattedText(), var2 + 2, var3 + 1, var1 % 2 == 0?16777215:9474192);
      String var9 = var8.func_75968_a(GuiStats.access$200(this.field_148208_k).writeStat(var8));
      this.field_148208_k.drawString(GuiStats.access$300(this.field_148208_k), var9, var2 + 2 + 213 - GuiStats.access$400(this.field_148208_k).getStringWidth(var9), var3 + 1, var1 % 2 == 0?16777215:9474192);
   }
}
