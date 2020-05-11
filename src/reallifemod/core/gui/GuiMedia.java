package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.awt.Color;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiMedia extends GuiScreen {

   private String YouTube = "https://www.youtube.com/channel/UCyKlPlyI9lZi3vATCv8zKgA?subconfirmation=1";
   private String Twitter = "https://www.twitter.com/EmeraldMinors";


   public void func_73866_w_() {
      super.initGui();
   }

   public void func_73863_a(int par1, int par2, float f) {
      func_73734_a(0, 0, this.field_146294_l, this.field_146295_m, Color.white.getRGB());
   }

   public void func_146284_a(GuiButton button) {
      if(button.id == 0) {
         ;
      }

      if(button.id == 1) {
         ;
      }

      if(button.id == 3) {
         ;
      }

   }
}
