package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiResourcePackAvailable;
import net.minecraft.client.gui.GuiResourcePackSelected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.ResourcePackListEntryDefault;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.ResourcePackRepository$Entry;
import net.minecraft.util.Util;
import net.minecraft.util.Util$EnumOS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;

public class GuiScreenResourcePacks extends GuiScreen {

   private static final Logger logger = LogManager.getLogger();
   private GuiScreen field_146965_f;
   private List field_146966_g;
   private List field_146969_h;
   private GuiResourcePackAvailable field_146970_i;
   private GuiResourcePackSelected field_146967_r;


   public GuiScreenResourcePacks(GuiScreen var1) {
      this.field_146965_f = var1;
   }

   public void initGui() {
      super.buttonList.add(new GuiOptionButton(2, super.width / 2 - 154, super.height - 48, I18n.format("resourcePack.openFolder", new Object[0])));
      super.buttonList.add(new GuiOptionButton(1, super.width / 2 + 4, super.height - 48, I18n.format("gui.done", new Object[0])));
      this.field_146966_g = new ArrayList();
      this.field_146969_h = new ArrayList();
      ResourcePackRepository var1 = super.mc.getResourcePackRepository();
      var1.updateRepositoryEntriesAll();
      ArrayList var2 = Lists.newArrayList(var1.getRepositoryEntriesAll());
      var2.removeAll(var1.getRepositoryEntries());
      Iterator var3 = var2.iterator();

      ResourcePackRepository$Entry var4;
      while(var3.hasNext()) {
         var4 = (ResourcePackRepository$Entry)var3.next();
         this.field_146966_g.add(new ResourcePackListEntryFound(this, var4));
      }

      var3 = Lists.reverse(var1.getRepositoryEntries()).iterator();

      while(var3.hasNext()) {
         var4 = (ResourcePackRepository$Entry)var3.next();
         this.field_146969_h.add(new ResourcePackListEntryFound(this, var4));
      }

      this.field_146969_h.add(new ResourcePackListEntryDefault(this));
      this.field_146970_i = new GuiResourcePackAvailable(super.mc, 200, super.height, this.field_146966_g);
      this.field_146970_i.setSlotXBoundsFromLeft(super.width / 2 - 4 - 200);
      this.field_146970_i.registerScrollButtons(7, 8);
      this.field_146967_r = new GuiResourcePackSelected(super.mc, 200, super.height, this.field_146969_h);
      this.field_146967_r.setSlotXBoundsFromLeft(super.width / 2 + 4);
      this.field_146967_r.registerScrollButtons(7, 8);
   }

   public boolean func_146961_a(ResourcePackListEntry var1) {
      return this.field_146969_h.contains(var1);
   }

   public List func_146962_b(ResourcePackListEntry var1) {
      return this.func_146961_a(var1)?this.field_146969_h:this.field_146966_g;
   }

   public List func_146964_g() {
      return this.field_146966_g;
   }

   public List func_146963_h() {
      return this.field_146969_h;
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 2) {
            File var2 = super.mc.getResourcePackRepository().getDirResourcepacks();
            String var3 = var2.getAbsolutePath();
            if(Util.getOSType() == Util$EnumOS.OSX) {
               try {
                  logger.info(var3);
                  Runtime.getRuntime().exec(new String[]{"/usr/bin/open", var3});
                  return;
               } catch (IOException var9) {
                  logger.error("Couldn\'t open file", var9);
               }
            } else if(Util.getOSType() == Util$EnumOS.WINDOWS) {
               String var4 = String.format("cmd.exe /C start \"Open file\" \"%s\"", new Object[]{var3});

               try {
                  Runtime.getRuntime().exec(var4);
                  return;
               } catch (IOException var8) {
                  logger.error("Couldn\'t open file", var8);
               }
            }

            boolean var12 = false;

            try {
               Class var5 = Class.forName("java.awt.Desktop");
               Object var6 = var5.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
               var5.getMethod("browse", new Class[]{URI.class}).invoke(var6, new Object[]{var2.toURI()});
            } catch (Throwable var7) {
               logger.error("Couldn\'t open link", var7);
               var12 = true;
            }

            if(var12) {
               logger.info("Opening via system class!");
               Sys.openURL("file://" + var3);
            }
         } else if(var1.id == 1) {
            ArrayList var10 = Lists.newArrayList();
            Iterator var11 = this.field_146969_h.iterator();

            while(var11.hasNext()) {
               ResourcePackListEntry var13 = (ResourcePackListEntry)var11.next();
               if(var13 instanceof ResourcePackListEntryFound) {
                  var10.add(((ResourcePackListEntryFound)var13).func_148318_i());
               }
            }

            Collections.reverse(var10);
            super.mc.getResourcePackRepository().func_148527_a(var10);
            super.mc.gameSettings.resourcePacks.clear();
            var11 = var10.iterator();

            while(var11.hasNext()) {
               ResourcePackRepository$Entry var14 = (ResourcePackRepository$Entry)var11.next();
               super.mc.gameSettings.resourcePacks.add(var14.getResourcePackName());
            }

            super.mc.gameSettings.saveOptions();
            super.mc.refreshResources();
            super.mc.displayGuiScreen(this.field_146965_f);
         }

      }
   }

   protected void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.field_146970_i.func_148179_a(var1, var2, var3);
      this.field_146967_r.func_148179_a(var1, var2, var3);
   }

   protected void mouseMovedOrUp(int var1, int var2, int var3) {
      super.mouseMovedOrUp(var1, var2, var3);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawBackground(0);
      this.field_146970_i.drawScreen(var1, var2, var3);
      this.field_146967_r.drawScreen(var1, var2, var3);
      this.drawCenteredString(super.fontRendererObj, I18n.format("resourcePack.title", new Object[0]), super.width / 2, 16, 16777215);
      this.drawCenteredString(super.fontRendererObj, I18n.format("resourcePack.folderInfo", new Object[0]), super.width / 2 - 77, super.height - 26, 8421504);
      super.drawScreen(var1, var2, var3);
   }

}
