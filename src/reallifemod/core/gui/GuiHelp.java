package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiEMail;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiInternet;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiManagement;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiOnlineShop;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiSettings;
import de.ItsAMysterious.mods.reallifemod.core.gui.guiDropdownMenu;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiYesNoCallback;

public class GuiHelp extends RLM_Gui implements GuiYesNoCallback {

   int gray;
   public String infoText;
   public String[] lines;
   public guiDropdownMenu menu;
   public GuiHelp.texts text;
   public static String heat = "HEAT AND COLD: From now, the world in minecraft will have a new attribute: TEMPERATURE!\n It will have influence on your energy and your strength. If the temperature gets higher than 40ï¿½ you will have to drink something and also you will have to seek shelter in the shadows! If it gets to cold you have to dress warmer, otherwiseyou will freeze to death. And be carefull:DO NOT STAY NEAR LAVA FOR TOO LONG! IT WILL BURN YOU!";
   public static String param = "PARAMEDICS: When you get hurt, paramedics will arrive and bring you to the next village or the next hospital.";


   public GuiHelp() {
      this.gray = Color.darkGray.getRGB();
      this.infoText = "temperature";
      this.text = GuiHelp.texts.temperatur;
   }

   public void func_73876_c() {
      super.func_73876_c();
      this.menu.onUpdate();
   }

   public void func_73863_a(int par1, int par2, float f1) {
      Gui.drawRect(0, 20, this.field_146294_l, this.field_146295_m, Color.gray.getRGB());
      Gui.drawRect(2, 45, this.field_146294_l - 2, this.field_146295_m / 2 + 10, Color.GRAY.darker().getRGB());
      this.func_73732_a(this.field_146289_q, "Information,tips,tricks and more", this.field_146294_l / 2, 25, Color.white.getRGB());
      this.menu.draw();
      super.func_73863_a(par1, par2, f1);
   }

   public void func_146284_a(GuiButton button) {
      if(button.id == 0) {
         this.field_146297_k.displayGuiScreen(new GuiOnlineShop());
      }

      if(button.id == 1) {
         this.field_146297_k.displayGuiScreen(new GuiEMail());
      }

      if(button.id == 2) {
         this.field_146297_k.displayGuiScreen(new GuiInternet());
      }

      if(button.id == 3) {
         this.field_146297_k.displayGuiScreen(new GuiSettings());
      }

      if(button.id == 4) {
         this.field_146297_k.displayGuiScreen(new GuiManagement());
      }

      if(button.id == 5) {
         this.text = GuiHelp.texts.temperatur;
      }

      if(button.id == 6) {
         this.text = GuiHelp.texts.paramedics;
      }

   }

   private void displayInfo(String heat) {
      for(int i = 1; i < heat.length(); ++i) {
         int k = i / 150;
         this.drawString(heat.substring(0 + i * 180).toString(), 0, 180 + k * 10, Color.green.getRGB());
      }

   }

   public void func_73866_w_() {
      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiButton(0, 0, 0, this.field_146294_l / 5, 20, "Shop"));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 5, 0, this.field_146294_l / 5, 20, "E-Mail"));
      this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 5 * 2, 0, this.field_146294_l / 5, 20, "Home"));
      this.field_146292_n.add(new GuiButton(3, this.field_146294_l / 5 * 3, 0, this.field_146294_l / 5, 20, "Settings"));
      this.field_146292_n.add(new GuiButton(4, this.field_146294_l / 5 * 4, 0, this.field_146294_l / 5, 20, "Management"));
      this.field_146292_n.add(new GuiButton(5, 0, 160, this.field_146294_l / 5, 20, "Heat and Cold"));
      this.field_146292_n.add(new GuiButton(6, this.field_146294_l / 5, 160, this.field_146294_l / 5, 20, "Paramedics"));
      this.menu = new guiDropdownMenu(5, this.field_146295_m / 2 + 50);
   }

   protected void func_73864_a(int x, int y, int id) {
      this.menu.onMouseClicked(x, y, id);
      super.func_73864_a(x, y, id);
   }

   public void loadInfoText(String subject, File file) throws FileNotFoundException, IOException {
      BufferedReader reader = new BufferedReader(new FileReader(file));

      String line;
      while((line = reader.readLine()) != null) {
         if(line.startsWith("[INFO]")) {
            String Heading = line.split(" ")[1];
         }
      }

      reader.close();
   }


   public static enum texts {

      temperatur("temperatur", 0),
      paramedics("paramedics", 1);
      // $FF: synthetic field
      private static final GuiHelp.texts[] $VALUES = new GuiHelp.texts[]{temperatur, paramedics};


      private texts(String var1, int var2) {}

   }
}
