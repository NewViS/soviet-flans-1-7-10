package net.minecraft.client.gui;

import java.util.Random;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateFlatWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import org.lwjgl.input.Keyboard;

public class GuiCreateWorld extends GuiScreen {

   private GuiScreen field_146332_f;
   private GuiTextField field_146333_g;
   private GuiTextField field_146335_h;
   private String field_146336_i;
   private String field_146342_r = "survival";
   private boolean field_146341_s = true;
   private boolean field_146340_t;
   private boolean field_146339_u;
   private boolean field_146338_v;
   private boolean field_146337_w;
   private boolean field_146345_x;
   private boolean field_146344_y;
   private GuiButton field_146343_z;
   private GuiButton field_146324_A;
   private GuiButton field_146325_B;
   private GuiButton field_146326_C;
   private GuiButton field_146320_D;
   private GuiButton field_146321_E;
   private GuiButton field_146322_F;
   private String field_146323_G;
   private String field_146328_H;
   private String field_146329_I;
   private String field_146330_J;
   private int field_146331_K;
   public String field_146334_a = "";
   private static final String[] field_146327_L = new String[]{"CON", "COM", "PRN", "AUX", "CLOCK$", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"};


   public GuiCreateWorld(GuiScreen var1) {
      this.field_146332_f = var1;
      this.field_146329_I = "";
      this.field_146330_J = I18n.format("selectWorld.newWorld", new Object[0]);
   }

   public void updateScreen() {
      this.field_146333_g.updateCursorCounter();
      this.field_146335_h.updateCursorCounter();
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      super.buttonList.clear();
      super.buttonList.add(new GuiButton(0, super.width / 2 - 155, super.height - 28, 150, 20, I18n.format("selectWorld.create", new Object[0])));
      super.buttonList.add(new GuiButton(1, super.width / 2 + 5, super.height - 28, 150, 20, I18n.format("gui.cancel", new Object[0])));
      super.buttonList.add(this.field_146343_z = new GuiButton(2, super.width / 2 - 75, 115, 150, 20, I18n.format("selectWorld.gameMode", new Object[0])));
      super.buttonList.add(this.field_146324_A = new GuiButton(3, super.width / 2 - 75, 187, 150, 20, I18n.format("selectWorld.moreWorldOptions", new Object[0])));
      super.buttonList.add(this.field_146325_B = new GuiButton(4, super.width / 2 - 155, 100, 150, 20, I18n.format("selectWorld.mapFeatures", new Object[0])));
      this.field_146325_B.visible = false;
      super.buttonList.add(this.field_146326_C = new GuiButton(7, super.width / 2 + 5, 151, 150, 20, I18n.format("selectWorld.bonusItems", new Object[0])));
      this.field_146326_C.visible = false;
      super.buttonList.add(this.field_146320_D = new GuiButton(5, super.width / 2 + 5, 100, 150, 20, I18n.format("selectWorld.mapType", new Object[0])));
      this.field_146320_D.visible = false;
      super.buttonList.add(this.field_146321_E = new GuiButton(6, super.width / 2 - 155, 151, 150, 20, I18n.format("selectWorld.allowCommands", new Object[0])));
      this.field_146321_E.visible = false;
      super.buttonList.add(this.field_146322_F = new GuiButton(8, super.width / 2 + 5, 120, 150, 20, I18n.format("selectWorld.customizeType", new Object[0])));
      this.field_146322_F.visible = false;
      this.field_146333_g = new GuiTextField(super.fontRendererObj, super.width / 2 - 100, 60, 200, 20);
      this.field_146333_g.setFocused(true);
      this.field_146333_g.setText(this.field_146330_J);
      this.field_146335_h = new GuiTextField(super.fontRendererObj, super.width / 2 - 100, 60, 200, 20);
      this.field_146335_h.setText(this.field_146329_I);
      this.func_146316_a(this.field_146344_y);
      this.func_146314_g();
      this.func_146319_h();
   }

   private void func_146314_g() {
      this.field_146336_i = this.field_146333_g.getText().trim();
      char[] var1 = ChatAllowedCharacters.allowedCharacters;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         char var4 = var1[var3];
         this.field_146336_i = this.field_146336_i.replace(var4, '_');
      }

      if(MathHelper.stringNullOrLengthZero(this.field_146336_i)) {
         this.field_146336_i = "World";
      }

      this.field_146336_i = func_146317_a(super.mc.getSaveLoader(), this.field_146336_i);
   }

   private void func_146319_h() {
      this.field_146343_z.displayString = I18n.format("selectWorld.gameMode", new Object[0]) + " " + I18n.format("selectWorld.gameMode." + this.field_146342_r, new Object[0]);
      this.field_146323_G = I18n.format("selectWorld.gameMode." + this.field_146342_r + ".line1", new Object[0]);
      this.field_146328_H = I18n.format("selectWorld.gameMode." + this.field_146342_r + ".line2", new Object[0]);
      this.field_146325_B.displayString = I18n.format("selectWorld.mapFeatures", new Object[0]) + " ";
      if(this.field_146341_s) {
         this.field_146325_B.displayString = this.field_146325_B.displayString + I18n.format("options.on", new Object[0]);
      } else {
         this.field_146325_B.displayString = this.field_146325_B.displayString + I18n.format("options.off", new Object[0]);
      }

      this.field_146326_C.displayString = I18n.format("selectWorld.bonusItems", new Object[0]) + " ";
      if(this.field_146338_v && !this.field_146337_w) {
         this.field_146326_C.displayString = this.field_146326_C.displayString + I18n.format("options.on", new Object[0]);
      } else {
         this.field_146326_C.displayString = this.field_146326_C.displayString + I18n.format("options.off", new Object[0]);
      }

      this.field_146320_D.displayString = I18n.format("selectWorld.mapType", new Object[0]) + " " + I18n.format(WorldType.worldTypes[this.field_146331_K].getTranslateName(), new Object[0]);
      this.field_146321_E.displayString = I18n.format("selectWorld.allowCommands", new Object[0]) + " ";
      if(this.field_146340_t && !this.field_146337_w) {
         this.field_146321_E.displayString = this.field_146321_E.displayString + I18n.format("options.on", new Object[0]);
      } else {
         this.field_146321_E.displayString = this.field_146321_E.displayString + I18n.format("options.off", new Object[0]);
      }

   }

   public static String func_146317_a(ISaveFormat var0, String var1) {
      var1 = var1.replaceAll("[\\./\"]", "_");
      String[] var2 = field_146327_L;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String var5 = var2[var4];
         if(var1.equalsIgnoreCase(var5)) {
            var1 = "_" + var1 + "_";
         }
      }

      while(var0.getWorldInfo(var1) != null) {
         var1 = var1 + "-";
      }

      return var1;
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 1) {
            super.mc.displayGuiScreen(this.field_146332_f);
         } else if(var1.id == 0) {
            super.mc.displayGuiScreen((GuiScreen)null);
            if(this.field_146345_x) {
               return;
            }

            this.field_146345_x = true;
            long var2 = (new Random()).nextLong();
            String var4 = this.field_146335_h.getText();
            if(!MathHelper.stringNullOrLengthZero(var4)) {
               try {
                  long var5 = Long.parseLong(var4);
                  if(var5 != 0L) {
                     var2 = var5;
                  }
               } catch (NumberFormatException var7) {
                  var2 = (long)var4.hashCode();
               }
            }

            WorldSettings$GameType var8 = WorldSettings$GameType.getByName(this.field_146342_r);
            WorldSettings var6 = new WorldSettings(var2, var8, this.field_146341_s, this.field_146337_w, WorldType.worldTypes[this.field_146331_K]);
            var6.func_82750_a(this.field_146334_a);
            if(this.field_146338_v && !this.field_146337_w) {
               var6.enableBonusChest();
            }

            if(this.field_146340_t && !this.field_146337_w) {
               var6.enableCommands();
            }

            super.mc.launchIntegratedServer(this.field_146336_i, this.field_146333_g.getText().trim(), var6);
         } else if(var1.id == 3) {
            this.func_146315_i();
         } else if(var1.id == 2) {
            if(this.field_146342_r.equals("survival")) {
               if(!this.field_146339_u) {
                  this.field_146340_t = false;
               }

               this.field_146337_w = false;
               this.field_146342_r = "hardcore";
               this.field_146337_w = true;
               this.field_146321_E.enabled = false;
               this.field_146326_C.enabled = false;
               this.func_146319_h();
            } else if(this.field_146342_r.equals("hardcore")) {
               if(!this.field_146339_u) {
                  this.field_146340_t = true;
               }

               this.field_146337_w = false;
               this.field_146342_r = "creative";
               this.func_146319_h();
               this.field_146337_w = false;
               this.field_146321_E.enabled = true;
               this.field_146326_C.enabled = true;
            } else {
               if(!this.field_146339_u) {
                  this.field_146340_t = false;
               }

               this.field_146342_r = "survival";
               this.func_146319_h();
               this.field_146321_E.enabled = true;
               this.field_146326_C.enabled = true;
               this.field_146337_w = false;
            }

            this.func_146319_h();
         } else if(var1.id == 4) {
            this.field_146341_s = !this.field_146341_s;
            this.func_146319_h();
         } else if(var1.id == 7) {
            this.field_146338_v = !this.field_146338_v;
            this.func_146319_h();
         } else if(var1.id == 5) {
            ++this.field_146331_K;
            if(this.field_146331_K >= WorldType.worldTypes.length) {
               this.field_146331_K = 0;
            }

            while(WorldType.worldTypes[this.field_146331_K] == null || !WorldType.worldTypes[this.field_146331_K].getCanBeCreated()) {
               ++this.field_146331_K;
               if(this.field_146331_K >= WorldType.worldTypes.length) {
                  this.field_146331_K = 0;
               }
            }

            this.field_146334_a = "";
            this.func_146319_h();
            this.func_146316_a(this.field_146344_y);
         } else if(var1.id == 6) {
            this.field_146339_u = true;
            this.field_146340_t = !this.field_146340_t;
            this.func_146319_h();
         } else if(var1.id == 8) {
            super.mc.displayGuiScreen(new GuiCreateFlatWorld(this, this.field_146334_a));
         }

      }
   }

   private void func_146315_i() {
      this.func_146316_a(!this.field_146344_y);
   }

   private void func_146316_a(boolean var1) {
      this.field_146344_y = var1;
      this.field_146343_z.visible = !this.field_146344_y;
      this.field_146325_B.visible = this.field_146344_y;
      this.field_146326_C.visible = this.field_146344_y;
      this.field_146320_D.visible = this.field_146344_y;
      this.field_146321_E.visible = this.field_146344_y;
      this.field_146322_F.visible = this.field_146344_y && WorldType.worldTypes[this.field_146331_K] == WorldType.FLAT;
      if(this.field_146344_y) {
         this.field_146324_A.displayString = I18n.format("gui.done", new Object[0]);
      } else {
         this.field_146324_A.displayString = I18n.format("selectWorld.moreWorldOptions", new Object[0]);
      }

   }

   protected void keyTyped(char var1, int var2) {
      if(this.field_146333_g.isFocused() && !this.field_146344_y) {
         this.field_146333_g.textboxKeyTyped(var1, var2);
         this.field_146330_J = this.field_146333_g.getText();
      } else if(this.field_146335_h.isFocused() && this.field_146344_y) {
         this.field_146335_h.textboxKeyTyped(var1, var2);
         this.field_146329_I = this.field_146335_h.getText();
      }

      if(var2 == 28 || var2 == 156) {
         this.actionPerformed((GuiButton)super.buttonList.get(0));
      }

      ((GuiButton)super.buttonList.get(0)).enabled = this.field_146333_g.getText().length() > 0;
      this.func_146314_g();
   }

   protected void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      if(this.field_146344_y) {
         this.field_146335_h.mouseClicked(var1, var2, var3);
      } else {
         this.field_146333_g.mouseClicked(var1, var2, var3);
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, I18n.format("selectWorld.create", new Object[0]), super.width / 2, 20, -1);
      if(this.field_146344_y) {
         this.drawString(super.fontRendererObj, I18n.format("selectWorld.enterSeed", new Object[0]), super.width / 2 - 100, 47, -6250336);
         this.drawString(super.fontRendererObj, I18n.format("selectWorld.seedInfo", new Object[0]), super.width / 2 - 100, 85, -6250336);
         this.drawString(super.fontRendererObj, I18n.format("selectWorld.mapFeatures.info", new Object[0]), super.width / 2 - 150, 122, -6250336);
         this.drawString(super.fontRendererObj, I18n.format("selectWorld.allowCommands.info", new Object[0]), super.width / 2 - 150, 172, -6250336);
         this.field_146335_h.drawTextBox();
         if(WorldType.worldTypes[this.field_146331_K].showWorldInfoNotice()) {
            super.fontRendererObj.drawSplitString(I18n.format(WorldType.worldTypes[this.field_146331_K].func_151359_c(), new Object[0]), this.field_146320_D.xPosition + 2, this.field_146320_D.yPosition + 22, this.field_146320_D.getButtonWidth(), 10526880);
         }
      } else {
         this.drawString(super.fontRendererObj, I18n.format("selectWorld.enterName", new Object[0]), super.width / 2 - 100, 47, -6250336);
         this.drawString(super.fontRendererObj, I18n.format("selectWorld.resultFolder", new Object[0]) + " " + this.field_146336_i, super.width / 2 - 100, 85, -6250336);
         this.field_146333_g.drawTextBox();
         this.drawString(super.fontRendererObj, this.field_146323_G, super.width / 2 - 100, 137, -6250336);
         this.drawString(super.fontRendererObj, this.field_146328_H, super.width / 2 - 100, 149, -6250336);
      }

      super.drawScreen(var1, var2, var3);
   }

   public void func_146318_a(WorldInfo var1) {
      this.field_146330_J = I18n.format("selectWorld.newWorld.copyOf", new Object[]{var1.getWorldName()});
      this.field_146329_I = var1.getSeed() + "";
      this.field_146331_K = var1.getTerrainType().getWorldTypeID();
      this.field_146334_a = var1.getGeneratorOptions();
      this.field_146341_s = var1.isMapFeaturesEnabled();
      this.field_146340_t = var1.areCommandsAllowed();
      if(var1.isHardcoreModeEnabled()) {
         this.field_146342_r = "hardcore";
      } else if(var1.getGameType().isSurvivalOrAdventure()) {
         this.field_146342_r = "survival";
      } else if(var1.getGameType().isCreative()) {
         this.field_146342_r = "creative";
      }

   }

}
