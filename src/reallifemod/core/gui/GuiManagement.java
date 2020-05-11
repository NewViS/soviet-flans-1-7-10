package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiEMail;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiHelp;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiInternet;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiOnlineShop;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiSettings;
import de.ItsAMysterious.mods.reallifemod.core.gui.Gui_TrafficLight;
import java.awt.Color;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiManagement extends GuiScreen {

   public void func_73863_a(int x, int y, float f) {
      Gui.drawRect(0, 20, this.field_146294_l, this.field_146295_m, Color.WHITE.getRGB());
      super.drawScreen(x, y, f);
   }

   public void func_73876_c() {}

   public void func_73866_w_() {
      this.field_146292_n.add(new GuiButton(0, 0, 0, this.field_146294_l / 5, 20, "Shop"));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 5, 0, this.field_146294_l / 5, 20, "E-Mail"));
      this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 5 * 2, 0, this.field_146294_l / 5, 20, "Help"));
      this.field_146292_n.add(new GuiButton(3, this.field_146294_l / 5 * 3, 0, this.field_146294_l / 5, 20, "Settings"));
      this.field_146292_n.add(new GuiButton(4, this.field_146294_l / 5 * 4, 0, this.field_146294_l / 5, 20, "Home"));
      this.field_146292_n.add(new GuiButton(5, 10, 50, this.field_146294_l / 5, 20, "trafficlights"));
   }

   public void func_146284_a(GuiButton button) {
      if(button.id == 0) {
         this.field_146297_k.displayGuiScreen(new GuiOnlineShop());
      }

      if(button.id == 1) {
         this.field_146297_k.displayGuiScreen(new GuiEMail());
      }

      if(button.id == 2) {
         this.field_146297_k.displayGuiScreen(new GuiHelp());
      }

      if(button.id == 3) {
         this.field_146297_k.displayGuiScreen(new GuiSettings());
      }

      if(button.id == 4) {
         this.field_146297_k.displayGuiScreen(new GuiInternet());
      }

      if(button.id == 5) {
         FMLClientHandler.instance().getClient().displayGuiScreen(new Gui_TrafficLight(this.field_146297_k.objectMouseOver.blockX, this.field_146297_k.objectMouseOver.blockY, this.field_146297_k.objectMouseOver.blockZ));
      }

   }

   public boolean func_73868_f() {
      return false;
   }
}
