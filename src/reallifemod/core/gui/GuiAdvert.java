package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.core.tiles.BilboardTE;
import java.awt.Color;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiAdvert extends GuiScreen {

   public static int GUI_ID;
   public BilboardTE shield;


   public GuiAdvert(BilboardTE s) {
      this.shield = s;
      GUI_ID = 55;
   }

   public void func_73866_w_() {
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l - 20, 0, 20, 20, "X"));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 50, this.field_146295_m - 25, 100, 20, "OK"));
      this.field_146292_n.add(new GuiButton(2, 5, this.field_146295_m - 25, 100, 20, "Previous"));
      this.field_146292_n.add(new GuiButton(3, this.field_146294_l - 105, this.field_146295_m - 25, 100, 20, "Next"));
      super.initGui();
   }

   public void func_73863_a(int x, int y, float f) {
      this.func_73732_a(this.field_146289_q, "Image Number: " + this.shield.getImageNum(), this.field_146294_l / 2, 5, Color.white.getRGB());
      super.drawScreen(x, y, f);
   }

   public void func_146284_a(GuiButton b) {
      if(b.id == 0) {
         this.shield.func_145836_u();
         this.field_146297_k.displayGuiScreen((GuiScreen)null);
      }

      if(b.id == 2 && this.shield.getImageNum() > 0) {
         this.shield.setImageNum(this.shield.getImageNum() - 1);
      }

      if(b.id == 3) {
         this.shield.setImageNum(this.shield.getImageNum() + 1);
      }

   }
}
