package de.ItsAMysterious.mods.reallifemod.api.gui;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class SelectableElement extends Gui {

   public Minecraft mc;
   public int posX;
   public int posY;
   protected int sizeX;
   protected int sizeY;
   protected int mouseX;
   protected int mouseY;
   protected int id;
   private boolean mouseOver;
   private boolean selected;


   public SelectableElement(int id, int x, int y, int width, int height) {
      this.posX = x;
      this.posY = y;
      this.sizeX = width;
      this.sizeY = height;
      this.id = id;
   }

   public boolean mouseOver(int x, int y) {
      return x > this.posX && x < this.posX + this.sizeX && y < this.posY + this.sizeY && y > this.posY;
   }

   public void mouseReleased(int p_146118_1_, int p_146118_2_) {}

   public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
      return this.mouseOver && Mouse.isButtonDown(1);
   }

   public void draw(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
      if(!this.mouseOver) {
         Gui.drawRect(this.posX, this.posY, this.posX + 5, this.posY + 5, Color.white.getRGB());
      } else if(this.mouseOver) {
         Gui.drawRect(this.posX, this.posY, this.posX + 5, this.posY + 5, Color.green.getRGB());
      }

      this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
   }

   protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_) {}

   public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
      FontRenderer fontrenderer = p_146112_1_.fontRenderer;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3042);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glBlendFunc(770, 771);
      if(!this.mouseOver) {
         Gui.drawRect(this.posX, this.posY, this.posX + 5, this.posY + 5, Color.white.getRGB());
      } else if(this.mouseOver) {
         Gui.drawRect(this.posX, this.posY, this.posX + 5, this.posY + 5, Color.green.getRGB());
      }

      this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
   }
}
