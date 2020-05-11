package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.core.config.TLMSettings;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiEMail;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiHelp;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiInternet;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiManagement;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiOnlineShop;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;

@SideOnly(Side.CLIENT)
public class GuiSettings extends GuiScreen implements GuiYesNoCallback {

   public Map Actions = new HashMap();


   public void func_73866_w_() {
      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiButton(4, 0, 0, this.field_146294_l / 5, 20, "Shop"));
      this.field_146292_n.add(new GuiButton(5, this.field_146294_l / 5, 0, this.field_146294_l / 5, 20, "E-Mail"));
      this.field_146292_n.add(new GuiButton(6, this.field_146294_l / 5 * 2, 0, this.field_146294_l / 5, 20, "Help"));
      this.field_146292_n.add(new GuiButton(7, this.field_146294_l / 5 * 3, 0, this.field_146294_l / 5, 20, "Home"));
      this.field_146292_n.add(new GuiButton(8, this.field_146294_l / 5 * 4, 0, this.field_146294_l / 5, 20, "Management"));
      this.field_146292_n.add(new GuiButton(10, 40, 40, 150, 20, "RealityMode: " + TLMSettings.realityMode));
      this.field_146292_n.add(new GuiButton(11, 40, 70, 150, 20, "Realistic 3D Models: " + TLMSettings.realisticmodels));
      this.field_146292_n.add(new GuiButton(12, 40, 100, 150, 20, "3D Inventory blocks: " + TLMSettings.Inventory3D));
      super.initGui();
   }

   public void func_73863_a(int x, int y, float s) {
      Gui.drawRect(0, 20, this.field_146294_l, this.field_146295_m, Color.WHITE.getRGB());
      super.drawScreen(x, y, s);
   }

   public void func_146284_a(GuiButton button) {
      if(button.id == 10) {
         TLMSettings.realityMode = !TLMSettings.realityMode;
         this.func_73878_a(true, button.id);
      }

      if(button.id == 11) {
         TLMSettings.realisticmodels = !TLMSettings.realisticmodels;
         this.func_73878_a(true, button.id);
      }

      if(button.id == 12) {
         TLMSettings.Inventory3D = !TLMSettings.Inventory3D;
         this.func_73878_a(true, button.id);
      }

      if(button.id == 3) {
         ;
      }

      if(button.id == 4) {
         this.field_146297_k.displayGuiScreen(new GuiOnlineShop());
      }

      if(button.id == 5) {
         this.field_146297_k.displayGuiScreen(new GuiEMail());
      }

      if(button.id == 6) {
         this.field_146297_k.displayGuiScreen(new GuiHelp());
      }

      if(button.id == 7) {
         this.field_146297_k.displayGuiScreen(new GuiInternet());
      }

      if(button.id == 8) {
         this.field_146297_k.displayGuiScreen(new GuiManagement());
      }

   }

   private void accept() {
      this.Actions.clear();
   }

   private void reset() {
      if(this.Actions != null && this.Actions.containsValue(Boolean.valueOf(TLMSettings.realityMode))) {
         TLMSettings.realityMode = !TLMSettings.realityMode;
      }

   }

   public void func_73878_a(boolean b1, int id) {
      this.field_146297_k.displayGuiScreen(this);
      super.confirmClicked(b1, id);
   }
}
