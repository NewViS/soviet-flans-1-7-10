package net.minecraft.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;

class Minecraft$16 implements GuiYesNoCallback {

   // $FF: synthetic field
   final Minecraft field_152128_a;


   Minecraft$16(Minecraft var1) {
      this.field_152128_a = var1;
   }

   public void confirmClicked(boolean var1, int var2) {
      if(var1) {
         this.field_152128_a.func_152346_Z().func_152930_t();
      }

      this.field_152128_a.displayGuiScreen((GuiScreen)null);
   }
}
