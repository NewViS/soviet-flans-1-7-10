package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.awt.Color;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class guiListElement extends Gui {

   private FontRenderer fontrenderObj;
   private int xPos;
   private int yPos;
   private int width;
   private int rectWidth;
   private String text;
   public boolean selected;
   public Color rectColor;


   public void onUpdate() {}

   public void draw(int x, int y) {
      if(this.isMouseHoovering(x, y)) {
         Gui.drawRect(this.xPos, this.yPos, this.xPos + this.width, this.yPos + 15, Color.blue.getRGB());
      } else {
         Gui.drawRect(this.xPos, this.yPos, this.xPos + this.width, this.yPos + 15, Color.gray.getRGB());
      }

      Gui.drawRect(this.xPos + 1, this.yPos + 1, this.xPos + 1 + this.rectWidth, this.yPos + 1 + 13, this.rectColor.getRGB());
   }

   public void onMouseClicked(int x, int y, int id) {}

   public boolean isMouseHoovering(int x, int y) {
      return x > this.xPos && x < this.xPos + this.fontrenderObj.getStringWidth(this.text) + this.rectWidth && y > this.yPos && y < this.yPos + 15;
   }
}
