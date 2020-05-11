package net.minecraft.client.gui.achievement;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatCrafting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

abstract class GuiStats$Stats extends GuiSlot {

   protected int field_148218_l;
   protected List field_148219_m;
   protected Comparator field_148216_n;
   protected int field_148217_o;
   protected int field_148215_p;
   // $FF: synthetic field
   final GuiStats field_148214_q;


   protected GuiStats$Stats(GuiStats var1) {
      super(GuiStats.access$500(var1), var1.width, var1.height, 32, var1.height - 64, 20);
      this.field_148214_q = var1;
      this.field_148218_l = -1;
      this.field_148217_o = -1;
      this.setShowSelectionBox(false);
      this.setHasListHeader(true, 20);
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {}

   protected boolean isSelected(int var1) {
      return false;
   }

   protected void drawBackground() {
      this.field_148214_q.drawDefaultBackground();
   }

   protected void drawListHeader(int var1, int var2, Tessellator var3) {
      if(!Mouse.isButtonDown(0)) {
         this.field_148218_l = -1;
      }

      if(this.field_148218_l == 0) {
         GuiStats.access$600(this.field_148214_q, var1 + 115 - 18, var2 + 1, 0, 0);
      } else {
         GuiStats.access$600(this.field_148214_q, var1 + 115 - 18, var2 + 1, 0, 18);
      }

      if(this.field_148218_l == 1) {
         GuiStats.access$600(this.field_148214_q, var1 + 165 - 18, var2 + 1, 0, 0);
      } else {
         GuiStats.access$600(this.field_148214_q, var1 + 165 - 18, var2 + 1, 0, 18);
      }

      if(this.field_148218_l == 2) {
         GuiStats.access$600(this.field_148214_q, var1 + 215 - 18, var2 + 1, 0, 0);
      } else {
         GuiStats.access$600(this.field_148214_q, var1 + 215 - 18, var2 + 1, 0, 18);
      }

      if(this.field_148217_o != -1) {
         short var4 = 79;
         byte var5 = 18;
         if(this.field_148217_o == 1) {
            var4 = 129;
         } else if(this.field_148217_o == 2) {
            var4 = 179;
         }

         if(this.field_148215_p == 1) {
            var5 = 36;
         }

         GuiStats.access$600(this.field_148214_q, var1 + var4, var2 + 1, var5, 0);
      }

   }

   protected void func_148132_a(int var1, int var2) {
      this.field_148218_l = -1;
      if(var1 >= 79 && var1 < 115) {
         this.field_148218_l = 0;
      } else if(var1 >= 129 && var1 < 165) {
         this.field_148218_l = 1;
      } else if(var1 >= 179 && var1 < 215) {
         this.field_148218_l = 2;
      }

      if(this.field_148218_l >= 0) {
         this.func_148212_h(this.field_148218_l);
         GuiStats.access$700(this.field_148214_q).getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
      }

   }

   protected final int getSize() {
      return this.field_148219_m.size();
   }

   protected final StatCrafting func_148211_c(int var1) {
      return (StatCrafting)this.field_148219_m.get(var1);
   }

   protected abstract String func_148210_b(int var1);

   protected void func_148209_a(StatBase var1, int var2, int var3, boolean var4) {
      String var5;
      if(var1 != null) {
         var5 = var1.func_75968_a(GuiStats.access$200(this.field_148214_q).writeStat(var1));
         this.field_148214_q.drawString(GuiStats.access$800(this.field_148214_q), var5, var2 - GuiStats.access$900(this.field_148214_q).getStringWidth(var5), var3 + 5, var4?16777215:9474192);
      } else {
         var5 = "-";
         this.field_148214_q.drawString(GuiStats.access$1000(this.field_148214_q), var5, var2 - GuiStats.access$1100(this.field_148214_q).getStringWidth(var5), var3 + 5, var4?16777215:9474192);
      }

   }

   protected void func_148142_b(int var1, int var2) {
      if(var2 >= super.top && var2 <= super.bottom) {
         int var3 = this.func_148124_c(var1, var2);
         int var4 = super.width / 2 - 92 - 16;
         if(var3 >= 0) {
            if(var1 < var4 + 40 || var1 > var4 + 40 + 20) {
               return;
            }

            StatCrafting var5 = this.func_148211_c(var3);
            this.func_148213_a(var5, var1, var2);
         } else {
            String var9 = "";
            if(var1 >= var4 + 115 - 18 && var1 <= var4 + 115) {
               var9 = this.func_148210_b(0);
            } else if(var1 >= var4 + 165 - 18 && var1 <= var4 + 165) {
               var9 = this.func_148210_b(1);
            } else {
               if(var1 < var4 + 215 - 18 || var1 > var4 + 215) {
                  return;
               }

               var9 = this.func_148210_b(2);
            }

            var9 = ("" + I18n.format(var9, new Object[0])).trim();
            if(var9.length() > 0) {
               int var6 = var1 + 12;
               int var7 = var2 - 12;
               int var8 = GuiStats.access$1200(this.field_148214_q).getStringWidth(var9);
               GuiStats.access$1300(this.field_148214_q, var6 - 3, var7 - 3, var6 + var8 + 3, var7 + 8 + 3, -1073741824, -1073741824);
               GuiStats.access$1400(this.field_148214_q).drawStringWithShadow(var9, var6, var7, -1);
            }
         }

      }
   }

   protected void func_148213_a(StatCrafting var1, int var2, int var3) {
      if(var1 != null) {
         Item var4 = var1.func_150959_a();
         String var5 = ("" + I18n.format(var4.getUnlocalizedName() + ".name", new Object[0])).trim();
         if(var5.length() > 0) {
            int var6 = var2 + 12;
            int var7 = var3 - 12;
            int var8 = GuiStats.access$1500(this.field_148214_q).getStringWidth(var5);
            GuiStats.access$1600(this.field_148214_q, var6 - 3, var7 - 3, var6 + var8 + 3, var7 + 8 + 3, -1073741824, -1073741824);
            GuiStats.access$1700(this.field_148214_q).drawStringWithShadow(var5, var6, var7, -1);
         }

      }
   }

   protected void func_148212_h(int var1) {
      if(var1 != this.field_148217_o) {
         this.field_148217_o = var1;
         this.field_148215_p = -1;
      } else if(this.field_148215_p == -1) {
         this.field_148215_p = 1;
      } else {
         this.field_148217_o = -1;
         this.field_148215_p = 0;
      }

      Collections.sort(this.field_148219_m, this.field_148216_n);
   }
}
