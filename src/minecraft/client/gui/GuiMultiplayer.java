package net.minecraft.client.gui;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended$IGuiListEntry;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenAddServer;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ServerListEntryLanDetected;
import net.minecraft.client.gui.ServerListEntryLanScan;
import net.minecraft.client.gui.ServerListEntryNormal;
import net.minecraft.client.gui.ServerSelectionList;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.LanServerDetector$LanServer;
import net.minecraft.client.network.LanServerDetector$LanServerList;
import net.minecraft.client.network.LanServerDetector$ThreadLanServerFind;
import net.minecraft.client.network.OldServerPinger;
import net.minecraft.client.resources.I18n;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class GuiMultiplayer extends GuiScreen implements GuiYesNoCallback {

   private static final Logger logger = LogManager.getLogger();
   private final OldServerPinger field_146797_f = new OldServerPinger();
   private GuiScreen field_146798_g;
   private ServerSelectionList field_146803_h;
   private ServerList field_146804_i;
   private GuiButton field_146810_r;
   private GuiButton field_146809_s;
   private GuiButton field_146808_t;
   private boolean field_146807_u;
   private boolean field_146806_v;
   private boolean field_146805_w;
   private boolean field_146813_x;
   private String field_146812_y;
   private ServerData field_146811_z;
   private LanServerDetector$LanServerList field_146799_A;
   private LanServerDetector$ThreadLanServerFind field_146800_B;
   private boolean field_146801_C;


   public GuiMultiplayer(GuiScreen var1) {
      this.field_146798_g = var1;
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      super.buttonList.clear();
      if(!this.field_146801_C) {
         this.field_146801_C = true;
         this.field_146804_i = new ServerList(super.mc);
         this.field_146804_i.loadServerList();
         this.field_146799_A = new LanServerDetector$LanServerList();

         try {
            this.field_146800_B = new LanServerDetector$ThreadLanServerFind(this.field_146799_A);
            this.field_146800_B.start();
         } catch (Exception var2) {
            logger.warn("Unable to start LAN server detection: " + var2.getMessage());
         }

         this.field_146803_h = new ServerSelectionList(this, super.mc, super.width, super.height, 32, super.height - 64, 36);
         this.field_146803_h.func_148195_a(this.field_146804_i);
      } else {
         this.field_146803_h.func_148122_a(super.width, super.height, 32, super.height - 64);
      }

      this.func_146794_g();
   }

   public void func_146794_g() {
      super.buttonList.add(this.field_146810_r = new GuiButton(7, super.width / 2 - 154, super.height - 28, 70, 20, I18n.format("selectServer.edit", new Object[0])));
      super.buttonList.add(this.field_146808_t = new GuiButton(2, super.width / 2 - 74, super.height - 28, 70, 20, I18n.format("selectServer.delete", new Object[0])));
      super.buttonList.add(this.field_146809_s = new GuiButton(1, super.width / 2 - 154, super.height - 52, 100, 20, I18n.format("selectServer.select", new Object[0])));
      super.buttonList.add(new GuiButton(4, super.width / 2 - 50, super.height - 52, 100, 20, I18n.format("selectServer.direct", new Object[0])));
      super.buttonList.add(new GuiButton(3, super.width / 2 + 4 + 50, super.height - 52, 100, 20, I18n.format("selectServer.add", new Object[0])));
      super.buttonList.add(new GuiButton(8, super.width / 2 + 4, super.height - 28, 70, 20, I18n.format("selectServer.refresh", new Object[0])));
      super.buttonList.add(new GuiButton(0, super.width / 2 + 4 + 76, super.height - 28, 75, 20, I18n.format("gui.cancel", new Object[0])));
      this.func_146790_a(this.field_146803_h.func_148193_k());
   }

   public void updateScreen() {
      super.updateScreen();
      if(this.field_146799_A.getWasUpdated()) {
         List var1 = this.field_146799_A.getLanServers();
         this.field_146799_A.setWasNotUpdated();
         this.field_146803_h.func_148194_a(var1);
      }

      this.field_146797_f.func_147223_a();
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      if(this.field_146800_B != null) {
         this.field_146800_B.interrupt();
         this.field_146800_B = null;
      }

      this.field_146797_f.func_147226_b();
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         GuiListExtended$IGuiListEntry var2 = this.field_146803_h.func_148193_k() < 0?null:this.field_146803_h.getListEntry(this.field_146803_h.func_148193_k());
         if(var1.id == 2 && var2 instanceof ServerListEntryNormal) {
            String var9 = ((ServerListEntryNormal)var2).func_148296_a().serverName;
            if(var9 != null) {
               this.field_146807_u = true;
               String var4 = I18n.format("selectServer.deleteQuestion", new Object[0]);
               String var5 = "\'" + var9 + "\' " + I18n.format("selectServer.deleteWarning", new Object[0]);
               String var6 = I18n.format("selectServer.deleteButton", new Object[0]);
               String var7 = I18n.format("gui.cancel", new Object[0]);
               GuiYesNo var8 = new GuiYesNo(this, var4, var5, var6, var7, this.field_146803_h.func_148193_k());
               super.mc.displayGuiScreen(var8);
            }
         } else if(var1.id == 1) {
            this.func_146796_h();
         } else if(var1.id == 4) {
            this.field_146813_x = true;
            super.mc.displayGuiScreen(new GuiScreenServerList(this, this.field_146811_z = new ServerData(I18n.format("selectServer.defaultName", new Object[0]), "")));
         } else if(var1.id == 3) {
            this.field_146806_v = true;
            super.mc.displayGuiScreen(new GuiScreenAddServer(this, this.field_146811_z = new ServerData(I18n.format("selectServer.defaultName", new Object[0]), "")));
         } else if(var1.id == 7 && var2 instanceof ServerListEntryNormal) {
            this.field_146805_w = true;
            ServerData var3 = ((ServerListEntryNormal)var2).func_148296_a();
            this.field_146811_z = new ServerData(var3.serverName, var3.serverIP);
            this.field_146811_z.func_152583_a(var3);
            super.mc.displayGuiScreen(new GuiScreenAddServer(this, this.field_146811_z));
         } else if(var1.id == 0) {
            super.mc.displayGuiScreen(this.field_146798_g);
         } else if(var1.id == 8) {
            this.func_146792_q();
         }

      }
   }

   private void func_146792_q() {
      super.mc.displayGuiScreen(new GuiMultiplayer(this.field_146798_g));
   }

   public void confirmClicked(boolean var1, int var2) {
      GuiListExtended$IGuiListEntry var3 = this.field_146803_h.func_148193_k() < 0?null:this.field_146803_h.getListEntry(this.field_146803_h.func_148193_k());
      if(this.field_146807_u) {
         this.field_146807_u = false;
         if(var1 && var3 instanceof ServerListEntryNormal) {
            this.field_146804_i.removeServerData(this.field_146803_h.func_148193_k());
            this.field_146804_i.saveServerList();
            this.field_146803_h.func_148192_c(-1);
            this.field_146803_h.func_148195_a(this.field_146804_i);
         }

         super.mc.displayGuiScreen(this);
      } else if(this.field_146813_x) {
         this.field_146813_x = false;
         if(var1) {
            this.func_146791_a(this.field_146811_z);
         } else {
            super.mc.displayGuiScreen(this);
         }
      } else if(this.field_146806_v) {
         this.field_146806_v = false;
         if(var1) {
            this.field_146804_i.addServerData(this.field_146811_z);
            this.field_146804_i.saveServerList();
            this.field_146803_h.func_148192_c(-1);
            this.field_146803_h.func_148195_a(this.field_146804_i);
         }

         super.mc.displayGuiScreen(this);
      } else if(this.field_146805_w) {
         this.field_146805_w = false;
         if(var1 && var3 instanceof ServerListEntryNormal) {
            ServerData var4 = ((ServerListEntryNormal)var3).func_148296_a();
            var4.serverName = this.field_146811_z.serverName;
            var4.serverIP = this.field_146811_z.serverIP;
            var4.func_152583_a(this.field_146811_z);
            this.field_146804_i.saveServerList();
            this.field_146803_h.func_148195_a(this.field_146804_i);
         }

         super.mc.displayGuiScreen(this);
      }

   }

   protected void keyTyped(char var1, int var2) {
      int var3 = this.field_146803_h.func_148193_k();
      GuiListExtended$IGuiListEntry var4 = var3 < 0?null:this.field_146803_h.getListEntry(var3);
      if(var2 == 63) {
         this.func_146792_q();
      } else {
         if(var3 >= 0) {
            if(var2 == 200) {
               if(isShiftKeyDown()) {
                  if(var3 > 0 && var4 instanceof ServerListEntryNormal) {
                     this.field_146804_i.swapServers(var3, var3 - 1);
                     this.func_146790_a(this.field_146803_h.func_148193_k() - 1);
                     this.field_146803_h.scrollBy(-this.field_146803_h.getSlotHeight());
                     this.field_146803_h.func_148195_a(this.field_146804_i);
                  }
               } else if(var3 > 0) {
                  this.func_146790_a(this.field_146803_h.func_148193_k() - 1);
                  this.field_146803_h.scrollBy(-this.field_146803_h.getSlotHeight());
                  if(this.field_146803_h.getListEntry(this.field_146803_h.func_148193_k()) instanceof ServerListEntryLanScan) {
                     if(this.field_146803_h.func_148193_k() > 0) {
                        this.func_146790_a(this.field_146803_h.getSize() - 1);
                        this.field_146803_h.scrollBy(-this.field_146803_h.getSlotHeight());
                     } else {
                        this.func_146790_a(-1);
                     }
                  }
               } else {
                  this.func_146790_a(-1);
               }
            } else if(var2 == 208) {
               if(isShiftKeyDown()) {
                  if(var3 < this.field_146804_i.countServers() - 1) {
                     this.field_146804_i.swapServers(var3, var3 + 1);
                     this.func_146790_a(var3 + 1);
                     this.field_146803_h.scrollBy(this.field_146803_h.getSlotHeight());
                     this.field_146803_h.func_148195_a(this.field_146804_i);
                  }
               } else if(var3 < this.field_146803_h.getSize()) {
                  this.func_146790_a(this.field_146803_h.func_148193_k() + 1);
                  this.field_146803_h.scrollBy(this.field_146803_h.getSlotHeight());
                  if(this.field_146803_h.getListEntry(this.field_146803_h.func_148193_k()) instanceof ServerListEntryLanScan) {
                     if(this.field_146803_h.func_148193_k() < this.field_146803_h.getSize() - 1) {
                        this.func_146790_a(this.field_146803_h.getSize() + 1);
                        this.field_146803_h.scrollBy(this.field_146803_h.getSlotHeight());
                     } else {
                        this.func_146790_a(-1);
                     }
                  }
               } else {
                  this.func_146790_a(-1);
               }
            } else if(var2 != 28 && var2 != 156) {
               super.keyTyped(var1, var2);
            } else {
               this.actionPerformed((GuiButton)super.buttonList.get(2));
            }
         } else {
            super.keyTyped(var1, var2);
         }

      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.field_146812_y = null;
      this.drawDefaultBackground();
      this.field_146803_h.drawScreen(var1, var2, var3);
      this.drawCenteredString(super.fontRendererObj, I18n.format("multiplayer.title", new Object[0]), super.width / 2, 20, 16777215);
      super.drawScreen(var1, var2, var3);
      if(this.field_146812_y != null) {
         this.func_146283_a(Lists.newArrayList(Splitter.on("\n").split(this.field_146812_y)), var1, var2);
      }

   }

   public void func_146796_h() {
      GuiListExtended$IGuiListEntry var1 = this.field_146803_h.func_148193_k() < 0?null:this.field_146803_h.getListEntry(this.field_146803_h.func_148193_k());
      if(var1 instanceof ServerListEntryNormal) {
         this.func_146791_a(((ServerListEntryNormal)var1).func_148296_a());
      } else if(var1 instanceof ServerListEntryLanDetected) {
         LanServerDetector$LanServer var2 = ((ServerListEntryLanDetected)var1).func_148289_a();
         this.func_146791_a(new ServerData(var2.getServerMotd(), var2.getServerIpPort(), true));
      }

   }

   private void func_146791_a(ServerData var1) {
      super.mc.displayGuiScreen(new GuiConnecting(this, super.mc, var1));
   }

   public void func_146790_a(int var1) {
      this.field_146803_h.func_148192_c(var1);
      GuiListExtended$IGuiListEntry var2 = var1 < 0?null:this.field_146803_h.getListEntry(var1);
      this.field_146809_s.enabled = false;
      this.field_146810_r.enabled = false;
      this.field_146808_t.enabled = false;
      if(var2 != null && !(var2 instanceof ServerListEntryLanScan)) {
         this.field_146809_s.enabled = true;
         if(var2 instanceof ServerListEntryNormal) {
            this.field_146810_r.enabled = true;
            this.field_146808_t.enabled = true;
         }
      }

   }

   public OldServerPinger func_146789_i() {
      return this.field_146797_f;
   }

   public void func_146793_a(String var1) {
      this.field_146812_y = var1;
   }

   protected void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.field_146803_h.func_148179_a(var1, var2, var3);
   }

   protected void mouseMovedOrUp(int var1, int var2, int var3) {
      super.mouseMovedOrUp(var1, var2, var3);
      this.field_146803_h.func_148181_b(var1, var2, var3);
   }

   public ServerList func_146795_p() {
      return this.field_146804_i;
   }

}
