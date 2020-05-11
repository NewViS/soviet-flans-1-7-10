package net.minecraft.client.gui.achievement;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.achievement.GuiStats$Stats;
import net.minecraft.client.gui.achievement.GuiStats$StatsItem$1;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatList;

class GuiStats$StatsItem extends GuiStats$Stats {

   // $FF: synthetic field
   final GuiStats field_148220_k;


   public GuiStats$StatsItem(GuiStats var1) {
      super(var1);
      this.field_148220_k = var1;
      super.field_148219_m = new ArrayList();
      Iterator var2 = StatList.itemStats.iterator();

      while(var2.hasNext()) {
         StatCrafting var3 = (StatCrafting)var2.next();
         boolean var4 = false;
         int var5 = Item.getIdFromItem(var3.func_150959_a());
         if(GuiStats.access$200(var1).writeStat(var3) > 0) {
            var4 = true;
         } else if(StatList.objectBreakStats[var5] != null && GuiStats.access$200(var1).writeStat(StatList.objectBreakStats[var5]) > 0) {
            var4 = true;
         } else if(StatList.objectCraftStats[var5] != null && GuiStats.access$200(var1).writeStat(StatList.objectCraftStats[var5]) > 0) {
            var4 = true;
         }

         if(var4) {
            super.field_148219_m.add(var3);
         }
      }

      super.field_148216_n = new GuiStats$StatsItem$1(this, var1);
   }

   protected void drawListHeader(int var1, int var2, Tessellator var3) {
      super.drawListHeader(var1, var2, var3);
      if(super.field_148218_l == 0) {
         GuiStats.access$600(this.field_148220_k, var1 + 115 - 18 + 1, var2 + 1 + 1, 72, 18);
      } else {
         GuiStats.access$600(this.field_148220_k, var1 + 115 - 18, var2 + 1, 72, 18);
      }

      if(super.field_148218_l == 1) {
         GuiStats.access$600(this.field_148220_k, var1 + 165 - 18 + 1, var2 + 1 + 1, 18, 18);
      } else {
         GuiStats.access$600(this.field_148220_k, var1 + 165 - 18, var2 + 1, 18, 18);
      }

      if(super.field_148218_l == 2) {
         GuiStats.access$600(this.field_148220_k, var1 + 215 - 18 + 1, var2 + 1 + 1, 36, 18);
      } else {
         GuiStats.access$600(this.field_148220_k, var1 + 215 - 18, var2 + 1, 36, 18);
      }

   }

   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7) {
      StatCrafting var8 = this.func_148211_c(var1);
      Item var9 = var8.func_150959_a();
      GuiStats.access$1800(this.field_148220_k, var2 + 40, var3, var9);
      int var10 = Item.getIdFromItem(var9);
      this.func_148209_a(StatList.objectBreakStats[var10], var2 + 115, var3, var1 % 2 == 0);
      this.func_148209_a(StatList.objectCraftStats[var10], var2 + 165, var3, var1 % 2 == 0);
      this.func_148209_a(var8, var2 + 215, var3, var1 % 2 == 0);
   }

   protected String func_148210_b(int var1) {
      return var1 == 1?"stat.crafted":(var1 == 2?"stat.used":"stat.depleted");
   }
}
