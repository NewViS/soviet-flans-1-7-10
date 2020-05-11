package net.minecraft.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.stats.IStatStringFormat;

class Minecraft$2 implements IStatStringFormat {

   // $FF: synthetic field
   final Minecraft mc;


   Minecraft$2(Minecraft var1) {
      this.mc = var1;
   }

   public String formatString(String var1) {
      try {
         return String.format(var1, new Object[]{GameSettings.getKeyDisplayString(this.mc.gameSettings.keyBindInventory.getKeyCode())});
      } catch (Exception var3) {
         return "Error: " + var3.getLocalizedMessage();
      }
   }
}
