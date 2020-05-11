package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiListExtended$IGuiListEntry;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.ServerListEntryLanDetected;
import net.minecraft.client.gui.ServerListEntryLanScan;
import net.minecraft.client.gui.ServerListEntryNormal;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.LanServerDetector$LanServer;

public class ServerSelectionList extends GuiListExtended {

   private final GuiMultiplayer field_148200_k;
   private final List field_148198_l = Lists.newArrayList();
   private final List field_148199_m = Lists.newArrayList();
   private final GuiListExtended$IGuiListEntry field_148196_n = new ServerListEntryLanScan();
   private int field_148197_o = -1;


   public ServerSelectionList(GuiMultiplayer var1, Minecraft var2, int var3, int var4, int var5, int var6, int var7) {
      super(var2, var3, var4, var5, var6, var7);
      this.field_148200_k = var1;
   }

   public GuiListExtended$IGuiListEntry getListEntry(int var1) {
      if(var1 < this.field_148198_l.size()) {
         return (GuiListExtended$IGuiListEntry)this.field_148198_l.get(var1);
      } else {
         var1 -= this.field_148198_l.size();
         if(var1 == 0) {
            return this.field_148196_n;
         } else {
            --var1;
            return (GuiListExtended$IGuiListEntry)this.field_148199_m.get(var1);
         }
      }
   }

   protected int getSize() {
      return this.field_148198_l.size() + 1 + this.field_148199_m.size();
   }

   public void func_148192_c(int var1) {
      this.field_148197_o = var1;
   }

   protected boolean isSelected(int var1) {
      return var1 == this.field_148197_o;
   }

   public int func_148193_k() {
      return this.field_148197_o;
   }

   public void func_148195_a(ServerList var1) {
      this.field_148198_l.clear();

      for(int var2 = 0; var2 < var1.countServers(); ++var2) {
         this.field_148198_l.add(new ServerListEntryNormal(this.field_148200_k, var1.getServerData(var2)));
      }

   }

   public void func_148194_a(List var1) {
      this.field_148199_m.clear();
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         LanServerDetector$LanServer var3 = (LanServerDetector$LanServer)var2.next();
         this.field_148199_m.add(new ServerListEntryLanDetected(this.field_148200_k, var3));
      }

   }

   protected int getScrollBarX() {
      return super.getScrollBarX() + 30;
   }

   public int getListWidth() {
      return super.getListWidth() + 85;
   }
}
