package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiHelp;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiInternet;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiManagement;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiOnlineShop;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiSettings;
import java.awt.Color;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;

public class GuiEMail extends RLM_Gui {

   private GuiTextField to;
   private GuiTextField from;


   public void func_73863_a(int x, int y, float f) {
      Gui.drawRect(0, 20, this.field_146294_l, this.field_146295_m, Color.WHITE.getRGB());
      this.drawString("TO", 20, 25, Color.LIGHT_GRAY.getRGB());
      this.drawString("FROM", 20, 40, Color.LIGHT_GRAY.getRGB());
      this.to.drawTextBox();
      this.from.drawTextBox();
      this.to.setEnableBackgroundDrawing(true);
      this.from.setEnableBackgroundDrawing(true);
      super.func_73863_a(x, y, f);
   }

   public void func_73866_w_() {
      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiButton(0, 0, 0, this.field_146294_l / 5, 20, "Shop"));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 5, 0, this.field_146294_l / 5, 20, "Home"));
      this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 5 * 2, 0, this.field_146294_l / 5, 20, "Help"));
      this.field_146292_n.add(new GuiButton(3, this.field_146294_l / 5 * 3, 0, this.field_146294_l / 5, 20, "Settings"));
      this.field_146292_n.add(new GuiButton(4, this.field_146294_l / 5 * 4, 0, this.field_146294_l / 5, 20, "Management"));
      this.to = new GuiTextField(this.field_146289_q, 50, 25, this.field_146294_l - 80, 10);
      this.from = new GuiTextField(this.field_146289_q, 50, 40, this.field_146294_l - 80, 10);
      this.to.setCanLoseFocus(true);
      this.to.setCanLoseFocus(true);
      this.from.setFocused(true);
      this.from.setFocused(false);
   }

   public void func_146284_a(GuiButton button) {
      if(button.id == 0) {
         this.field_146297_k.displayGuiScreen(new GuiOnlineShop());
      }

      if(button.id == 1) {
         this.field_146297_k.displayGuiScreen(new GuiInternet());
      }

      if(button.id == 2) {
         this.field_146297_k.displayGuiScreen(new GuiHelp());
      }

      if(button.id == 3) {
         this.field_146297_k.displayGuiScreen(new GuiSettings());
      }

      if(button.id == 4) {
         this.field_146297_k.displayGuiScreen(new GuiManagement());
      }

   }

   public void onUpdate() {
      this.to.updateCursorCounter();
      this.from.updateCursorCounter();
   }

   public void func_73869_a(char taste, int id) {
      if(this.to.textboxKeyTyped(taste, id)) {
         ;
      }

      if(this.from.textboxKeyTyped(taste, id)) {
         ;
      }

      super.func_73869_a(taste, id);
   }

   public void func_73864_a(int x, int y, int id) {
      this.to.mouseClicked(x, y, id);
      this.from.mouseClicked(x, y, id);
      super.func_73864_a(x, y, id);
   }
}
