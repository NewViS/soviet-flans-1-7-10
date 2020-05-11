package net.minecraft.client.gui.achievement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList$EntityEggInfo;

class GuiStats$StatsMobsList extends GuiSlot {

   private final List field_148222_l;
   // $FF: synthetic field
   final GuiStats field_148223_k;


   public GuiStats$StatsMobsList(GuiStats var1) {
      super(GuiStats.access$1900(var1), var1.width, var1.height, 32, var1.height - 64, GuiStats.access$2000(var1).FONT_HEIGHT * 4);
      this.field_148223_k = var1;
      this.field_148222_l = new ArrayList();
      this.setShowSelectionBox(false);
      Iterator var2 = EntityList.entityEggs.values().iterator();

      while(var2.hasNext()) {
         EntityList$EntityEggInfo var3 = (EntityList$EntityEggInfo)var2.next();
         if(GuiStats.access$200(var1).writeStat(var3.field_151512_d) > 0 || GuiStats.access$200(var1).writeStat(var3.field_151513_e) > 0) {
            this.field_148222_l.add(var3);
         }
      }

   }

   protected int getSize() {
      return this.field_148222_l.size();
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {}

   protected boolean isSelected(int var1) {
      return false;
   }

   protected int getContentHeight() {
      return this.getSize() * GuiStats.access$2100(this.field_148223_k).FONT_HEIGHT * 4;
   }

   protected void drawBackground() {
      this.field_148223_k.drawDefaultBackground();
   }

   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7) {
      EntityList$EntityEggInfo var8 = (EntityList$EntityEggInfo)this.field_148222_l.get(var1);
      String var9 = I18n.format("entity." + EntityList.getStringFromID(var8.spawnedID) + ".name", new Object[0]);
      int var10 = GuiStats.access$200(this.field_148223_k).writeStat(var8.field_151512_d);
      int var11 = GuiStats.access$200(this.field_148223_k).writeStat(var8.field_151513_e);
      String var12 = I18n.format("stat.entityKills", new Object[]{Integer.valueOf(var10), var9});
      String var13 = I18n.format("stat.entityKilledBy", new Object[]{var9, Integer.valueOf(var11)});
      if(var10 == 0) {
         var12 = I18n.format("stat.entityKills.none", new Object[]{var9});
      }

      if(var11 == 0) {
         var13 = I18n.format("stat.entityKilledBy.none", new Object[]{var9});
      }

      this.field_148223_k.drawString(GuiStats.access$2200(this.field_148223_k), var9, var2 + 2 - 10, var3 + 1, 16777215);
      this.field_148223_k.drawString(GuiStats.access$2300(this.field_148223_k), var12, var2 + 2, var3 + 1 + GuiStats.access$2400(this.field_148223_k).FONT_HEIGHT, var10 == 0?6316128:9474192);
      this.field_148223_k.drawString(GuiStats.access$2500(this.field_148223_k), var13, var2 + 2, var3 + 1 + GuiStats.access$2600(this.field_148223_k).FONT_HEIGHT * 2, var11 == 0?6316128:9474192);
   }
}
