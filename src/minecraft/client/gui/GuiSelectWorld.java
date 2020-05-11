package net.minecraft.client.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiRenameWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld$List;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiSelectWorld extends GuiScreen implements GuiYesNoCallback {

   private static final Logger logger = LogManager.getLogger();
   private final DateFormat field_146633_h = new SimpleDateFormat();
   protected GuiScreen field_146632_a;
   protected String field_146628_f = "Select world";
   private boolean field_146634_i;
   private int field_146640_r;
   private List field_146639_s;
   private GuiSelectWorld$List field_146638_t;
   private String field_146637_u;
   private String field_146636_v;
   private String[] field_146635_w = new String[3];
   private boolean field_146643_x;
   private GuiButton field_146642_y;
   private GuiButton field_146641_z;
   private GuiButton field_146630_A;
   private GuiButton field_146631_B;


   public GuiSelectWorld(GuiScreen var1) {
      this.field_146632_a = var1;
   }

   public void initGui() {
      this.field_146628_f = I18n.format("selectWorld.title", new Object[0]);

      try {
         this.func_146627_h();
      } catch (AnvilConverterException var2) {
         logger.error("Couldn\'t load level list", var2);
         super.mc.displayGuiScreen(new GuiErrorScreen("Unable to load worlds", var2.getMessage()));
         return;
      }

      this.field_146637_u = I18n.format("selectWorld.world", new Object[0]);
      this.field_146636_v = I18n.format("selectWorld.conversion", new Object[0]);
      this.field_146635_w[WorldSettings$GameType.SURVIVAL.getID()] = I18n.format("gameMode.survival", new Object[0]);
      this.field_146635_w[WorldSettings$GameType.CREATIVE.getID()] = I18n.format("gameMode.creative", new Object[0]);
      this.field_146635_w[WorldSettings$GameType.ADVENTURE.getID()] = I18n.format("gameMode.adventure", new Object[0]);
      this.field_146638_t = new GuiSelectWorld$List(this);
      this.field_146638_t.registerScrollButtons(4, 5);
      this.func_146618_g();
   }

   private void func_146627_h() {
      ISaveFormat var1 = super.mc.getSaveLoader();
      this.field_146639_s = var1.getSaveList();
      Collections.sort(this.field_146639_s);
      this.field_146640_r = -1;
   }

   protected String func_146621_a(int var1) {
      return ((SaveFormatComparator)this.field_146639_s.get(var1)).getFileName();
   }

   protected String func_146614_d(int var1) {
      String var2 = ((SaveFormatComparator)this.field_146639_s.get(var1)).getDisplayName();
      if(var2 == null || MathHelper.stringNullOrLengthZero(var2)) {
         var2 = I18n.format("selectWorld.world", new Object[0]) + " " + (var1 + 1);
      }

      return var2;
   }

   public void func_146618_g() {
      super.buttonList.add(this.field_146641_z = new GuiButton(1, super.width / 2 - 154, super.height - 52, 150, 20, I18n.format("selectWorld.select", new Object[0])));
      super.buttonList.add(new GuiButton(3, super.width / 2 + 4, super.height - 52, 150, 20, I18n.format("selectWorld.create", new Object[0])));
      super.buttonList.add(this.field_146630_A = new GuiButton(6, super.width / 2 - 154, super.height - 28, 72, 20, I18n.format("selectWorld.rename", new Object[0])));
      super.buttonList.add(this.field_146642_y = new GuiButton(2, super.width / 2 - 76, super.height - 28, 72, 20, I18n.format("selectWorld.delete", new Object[0])));
      super.buttonList.add(this.field_146631_B = new GuiButton(7, super.width / 2 + 4, super.height - 28, 72, 20, I18n.format("selectWorld.recreate", new Object[0])));
      super.buttonList.add(new GuiButton(0, super.width / 2 + 82, super.height - 28, 72, 20, I18n.format("gui.cancel", new Object[0])));
      this.field_146641_z.enabled = false;
      this.field_146642_y.enabled = false;
      this.field_146630_A.enabled = false;
      this.field_146631_B.enabled = false;
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 2) {
            String var2 = this.func_146614_d(this.field_146640_r);
            if(var2 != null) {
               this.field_146643_x = true;
               GuiYesNo var3 = func_152129_a(this, var2, this.field_146640_r);
               super.mc.displayGuiScreen(var3);
            }
         } else if(var1.id == 1) {
            this.func_146615_e(this.field_146640_r);
         } else if(var1.id == 3) {
            super.mc.displayGuiScreen(new GuiCreateWorld(this));
         } else if(var1.id == 6) {
            super.mc.displayGuiScreen(new GuiRenameWorld(this, this.func_146621_a(this.field_146640_r)));
         } else if(var1.id == 0) {
            super.mc.displayGuiScreen(this.field_146632_a);
         } else if(var1.id == 7) {
            GuiCreateWorld var5 = new GuiCreateWorld(this);
            ISaveHandler var6 = super.mc.getSaveLoader().getSaveLoader(this.func_146621_a(this.field_146640_r), false);
            WorldInfo var4 = var6.loadWorldInfo();
            var6.flush();
            var5.func_146318_a(var4);
            super.mc.displayGuiScreen(var5);
         } else {
            this.field_146638_t.actionPerformed(var1);
         }

      }
   }

   public void func_146615_e(int var1) {
      super.mc.displayGuiScreen((GuiScreen)null);
      if(!this.field_146634_i) {
         this.field_146634_i = true;
         String var2 = this.func_146621_a(var1);
         if(var2 == null) {
            var2 = "World" + var1;
         }

         String var3 = this.func_146614_d(var1);
         if(var3 == null) {
            var3 = "World" + var1;
         }

         if(super.mc.getSaveLoader().canLoadWorld(var2)) {
            super.mc.launchIntegratedServer(var2, var3, (WorldSettings)null);
         }

      }
   }

   public void confirmClicked(boolean var1, int var2) {
      if(this.field_146643_x) {
         this.field_146643_x = false;
         if(var1) {
            ISaveFormat var3 = super.mc.getSaveLoader();
            var3.flushCache();
            var3.deleteWorldDirectory(this.func_146621_a(var2));

            try {
               this.func_146627_h();
            } catch (AnvilConverterException var5) {
               logger.error("Couldn\'t load level list", var5);
            }
         }

         super.mc.displayGuiScreen(this);
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      this.field_146638_t.drawScreen(var1, var2, var3);
      this.drawCenteredString(super.fontRendererObj, this.field_146628_f, super.width / 2, 20, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   public static GuiYesNo func_152129_a(GuiYesNoCallback var0, String var1, int var2) {
      String var3 = I18n.format("selectWorld.deleteQuestion", new Object[0]);
      String var4 = "\'" + var1 + "\' " + I18n.format("selectWorld.deleteWarning", new Object[0]);
      String var5 = I18n.format("selectWorld.deleteButton", new Object[0]);
      String var6 = I18n.format("gui.cancel", new Object[0]);
      GuiYesNo var7 = new GuiYesNo(var0, var3, var4, var5, var6, var2);
      return var7;
   }

   // $FF: synthetic method
   static List access$000(GuiSelectWorld var0) {
      return var0.field_146639_s;
   }

   // $FF: synthetic method
   static int access$102(GuiSelectWorld var0, int var1) {
      return var0.field_146640_r = var1;
   }

   // $FF: synthetic method
   static int access$100(GuiSelectWorld var0) {
      return var0.field_146640_r;
   }

   // $FF: synthetic method
   static GuiButton access$200(GuiSelectWorld var0) {
      return var0.field_146641_z;
   }

   // $FF: synthetic method
   static GuiButton access$300(GuiSelectWorld var0) {
      return var0.field_146642_y;
   }

   // $FF: synthetic method
   static GuiButton access$400(GuiSelectWorld var0) {
      return var0.field_146630_A;
   }

   // $FF: synthetic method
   static GuiButton access$500(GuiSelectWorld var0) {
      return var0.field_146631_B;
   }

   // $FF: synthetic method
   static String access$600(GuiSelectWorld var0) {
      return var0.field_146637_u;
   }

   // $FF: synthetic method
   static DateFormat access$700(GuiSelectWorld var0) {
      return var0.field_146633_h;
   }

   // $FF: synthetic method
   static String access$800(GuiSelectWorld var0) {
      return var0.field_146636_v;
   }

   // $FF: synthetic method
   static String[] access$900(GuiSelectWorld var0) {
      return var0.field_146635_w;
   }

}
