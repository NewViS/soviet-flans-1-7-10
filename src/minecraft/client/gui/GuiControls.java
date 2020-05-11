package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.client.settings.KeyBinding;

public class GuiControls extends GuiScreen {

   private static final GameSettings$Options[] field_146492_g = new GameSettings$Options[]{GameSettings$Options.INVERT_MOUSE, GameSettings$Options.SENSITIVITY, GameSettings$Options.TOUCHSCREEN};
   private GuiScreen parentScreen;
   protected String field_146495_a = "Controls";
   private GameSettings options;
   public KeyBinding buttonId = null;
   public long field_152177_g;
   private GuiKeyBindingList keyBindingList;
   private GuiButton field_146493_s;


   public GuiControls(GuiScreen var1, GameSettings var2) {
      this.parentScreen = var1;
      this.options = var2;
   }

   public void initGui() {
      this.keyBindingList = new GuiKeyBindingList(this, super.mc);
      super.buttonList.add(new GuiButton(200, super.width / 2 - 155, super.height - 29, 150, 20, I18n.format("gui.done", new Object[0])));
      super.buttonList.add(this.field_146493_s = new GuiButton(201, super.width / 2 - 155 + 160, super.height - 29, 150, 20, I18n.format("controls.resetAll", new Object[0])));
      this.field_146495_a = I18n.format("controls.title", new Object[0]);
      int var1 = 0;
      GameSettings$Options[] var2 = field_146492_g;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         GameSettings$Options var5 = var2[var4];
         if(var5.getEnumFloat()) {
            super.buttonList.add(new GuiOptionSlider(var5.returnEnumOrdinal(), super.width / 2 - 155 + var1 % 2 * 160, 18 + 24 * (var1 >> 1), var5));
         } else {
            super.buttonList.add(new GuiOptionButton(var5.returnEnumOrdinal(), super.width / 2 - 155 + var1 % 2 * 160, 18 + 24 * (var1 >> 1), var5, this.options.getKeyBinding(var5)));
         }

         ++var1;
      }

   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.id == 200) {
         super.mc.displayGuiScreen(this.parentScreen);
      } else if(var1.id == 201) {
         KeyBinding[] var2 = super.mc.gameSettings.keyBindings;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            KeyBinding var5 = var2[var4];
            var5.setKeyCode(var5.getKeyCodeDefault());
         }

         KeyBinding.resetKeyBindingArrayAndHash();
      } else if(var1.id < 100 && var1 instanceof GuiOptionButton) {
         this.options.setOptionValue(((GuiOptionButton)var1).returnEnumOptions(), 1);
         var1.displayString = this.options.getKeyBinding(GameSettings$Options.getEnumOptions(var1.id));
      }

   }

   protected void mouseClicked(int var1, int var2, int var3) {
      if(this.buttonId != null) {
         this.options.setOptionKeyBinding(this.buttonId, -100 + var3);
         this.buttonId = null;
         KeyBinding.resetKeyBindingArrayAndHash();
      } else if(var3 != 0 || !this.keyBindingList.func_148179_a(var1, var2, var3)) {
         super.mouseClicked(var1, var2, var3);
      }

   }

   protected void mouseMovedOrUp(int var1, int var2, int var3) {
      if(var3 != 0 || !this.keyBindingList.func_148181_b(var1, var2, var3)) {
         super.mouseMovedOrUp(var1, var2, var3);
      }

   }

   protected void keyTyped(char var1, int var2) {
      if(this.buttonId != null) {
         if(var2 == 1) {
            this.options.setOptionKeyBinding(this.buttonId, 0);
         } else {
            this.options.setOptionKeyBinding(this.buttonId, var2);
         }

         this.buttonId = null;
         this.field_152177_g = Minecraft.getSystemTime();
         KeyBinding.resetKeyBindingArrayAndHash();
      } else {
         super.keyTyped(var1, var2);
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.keyBindingList.drawScreen(var1, var2, var3);
      this.drawCenteredString(super.fontRendererObj, this.field_146495_a, super.width / 2, 8, 16777215);
      boolean var4 = true;
      KeyBinding[] var5 = this.options.keyBindings;
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         KeyBinding var8 = var5[var7];
         if(var8.getKeyCode() != var8.getKeyCodeDefault()) {
            var4 = false;
            break;
         }
      }

      this.field_146493_s.enabled = !var4;
      super.drawScreen(var1, var2, var3);
   }

}
