package de.ItsAMysterious.mods.reallifemod.api.gui;

import de.ItsAMysterious.mods.reallifemod.api.gui.GuiRadiobutton;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.gui.Gui;

public class GuiRadioGroup extends Gui {

   public static Map buttonList = new HashMap();
   public int xPos;
   public int yPos;
   public boolean singleChoice;
   public static GuiRadiobutton currentButton;


   public GuiRadioGroup(int xp, int yp) {
      this.xPos = xp;
      this.yPos = yp;
      this.singleChoice = true;
   }

   public GuiRadioGroup(int xp, int yp, boolean b) {
      this.xPos = xp;
      this.yPos = yp;
      this.singleChoice = b;
   }

   public void draw() {
      if(buttonList.size() != 0) {
         for(int i = 0; i < buttonList.size(); ++i) {
            GuiRadiobutton bt = (GuiRadiobutton)buttonList.get(Integer.valueOf(i));
            if(bt != null) {
               bt.draw();
            }
         }
      }

   }

   public void doUpdate() {
      if(buttonList.size() != 0) {
         for(int i = 0; i < buttonList.size(); ++i) {
            GuiRadiobutton bt = (GuiRadiobutton)buttonList.get(Integer.valueOf(i));
            if(bt != currentButton) {
               bt.checked = false;
            }
         }
      }

   }

   public void onMouseClicked(int x, int y, int id) {
      if(buttonList.size() != 0) {
         for(int i = 0; i < buttonList.size(); ++i) {
            GuiRadiobutton bt = (GuiRadiobutton)buttonList.get(Integer.valueOf(i));
            if(bt.isMouseHoovering(x, y)) {
               currentButton = bt;
               bt.onMouseClicked(x, y, id);
            }
         }
      }

   }

   public GuiRadiobutton getSelectedbutton() {
      return currentButton;
   }

}
