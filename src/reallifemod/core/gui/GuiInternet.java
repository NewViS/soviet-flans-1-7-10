package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.api.gui.GuiEdit;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiEMail;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiHelp;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiManagement;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiOnlineShop;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiSettings;
import java.awt.Color;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.util.ResourceLocation;

public class GuiInternet extends RLM_Gui implements GuiYesNoCallback {

   public GuiTextField SearchBar;
   int x0;
   int y0;
   public ResourceLocation MCSearch = new ResourceLocation("reallifemod:textures/gui/MC.png");


   public void func_73863_a(int par1, int par2, float f1) {
      Gui.drawRect(0, 20, this.field_146294_l, this.field_146295_m, Color.WHITE.getRGB());
      Gui.drawRect(this.x0 - 5, this.y0 - 5, this.x0 + 300, this.y0 + 55, Color.GRAY.getRGB());
      this.func_73731_b(this.field_146289_q, "Welcome to your new computer! It can be used to ", this.x0, this.y0, Color.WHITE.getRGB());
      this.func_73731_b(this.field_146289_q, "manage your life, to buy new things and of course ", this.x0, this.y0 + 10, Color.WHITE.getRGB());
      this.func_73731_b(this.field_146289_q, "to communicate! Search things in the searchbar", this.x0, this.y0 + 20, Color.WHITE.getRGB());
      this.func_73731_b(this.field_146289_q, "above ,to get help! You can also find many of tips and ", this.x0, this.y0 + 30, Color.WHITE.getRGB());
      this.func_73731_b(this.field_146289_q, "tricks for the \'real life mod\' in the \'Help\' tab!", this.x0, this.y0 + 40, Color.WHITE.getRGB());
      this.field_146297_k.renderEngine.bindTexture(this.MCSearch);
      this.func_73729_b(this.field_146294_l / 2 - 100, this.field_146295_m / 2 - 75, 0, 0, 200, 50);
      this.SearchBar.drawTextBox();
      this.SearchBar.setEnableBackgroundDrawing(true);
      super.func_73863_a(par1, par2, f1);
   }

   public void func_73876_c() {
      this.SearchBar.updateCursorCounter();
      this.x0 = this.field_146294_l / 2 - 145;
      this.y0 = this.field_146295_m / 2 + 20;
   }

   public void func_73866_w_() {
      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiButton(0, 0, 0, this.field_146294_l / 5, 20, "Shop"));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 5, 0, this.field_146294_l / 5, 20, "E-Mail"));
      this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 5 * 2, 0, this.field_146294_l / 5, 20, "Help"));
      this.field_146292_n.add(new GuiButton(3, this.field_146294_l / 5 * 3, 0, this.field_146294_l / 5, 20, "Settings"));
      this.field_146292_n.add(new GuiButton(4, this.field_146294_l / 5 * 4, 0, this.field_146294_l / 5, 20, "Management"));
      this.field_146292_n.add(new GuiButton(6, this.field_146294_l / 2 + 105, this.field_146295_m / 2 - 20, 40, 15, "search"));
      this.SearchBar = new GuiEdit(this.field_146289_q, this.field_146294_l / 2 - 100, this.field_146295_m / 2 - 20, 200, 15);
      this.SearchBar.setCanLoseFocus(true);
      this.SearchBar.setFocused(true);
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
         this.field_146297_k.displayGuiScreen(new GuiManagement());
      }

   }

   public void func_73869_a(char taste, int id) {
      if(this.SearchBar.textboxKeyTyped(taste, id)) {
         ;
      }

      super.func_73869_a(taste, id);
   }

   public void func_73864_a(int x, int y, int id) {
      this.SearchBar.mouseClicked(x, y, id);
      super.func_73864_a(x, y, id);
   }
}
