package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.core.gui.guiListElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.Gui;

public class guiDropdownMenu extends Gui {

   private int xPos;
   private int yPos;
   private int width;
   private int xM;
   private int yM;
   public static List elements = new ArrayList();
   public boolean isDropped;


   public guiDropdownMenu(int xp, int yp) {
      this.xPos = xp;
      this.yPos = yp;
   }

   public void onUpdate() {
      Iterator var1 = elements.iterator();

      while(var1.hasNext()) {
         guiListElement element = (guiListElement)var1.next();
         element.onUpdate();
      }

   }

   public void draw() {
      Iterator var1 = elements.iterator();

      while(var1.hasNext()) {
         guiListElement element = (guiListElement)var1.next();
         element.draw(this.xM, this.yM);
      }

   }

   public void onMouseClicked(int x, int y, int id) {
      Iterator var4 = elements.iterator();

      while(var4.hasNext()) {
         guiListElement element = (guiListElement)var4.next();
         element.onMouseClicked(x, y, id);
      }

      if(this.isMouseHoovering(x, y)) {
         ;
      }

   }

   private boolean isMouseHoovering(int x, int y) {
      Iterator var3 = elements.iterator();

      while(var3.hasNext()) {
         guiListElement element = (guiListElement)var3.next();
         element.isMouseHoovering(x, y);
      }

      return false;
   }

   public guiListElement getCurrentElement() {
      if(elements.get(0) != null) {
         Iterator var1 = elements.iterator();
         if(var1.hasNext()) {
            guiListElement element = (guiListElement)var1.next();
            return element.selected?element:null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

}
