package de.ItsAMysterious.mods.reallifemod.core.gui.computer.system;

import cpw.mods.fml.client.FMLClientHandler;
import java.awt.Color;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class guiExplorerWindow extends GuiScreen {

   private static ResourceLocation WindowBackground = new ResourceLocation("reallifemod:textures/gui/System/defaultBackground.png");
   public int posX;
   public int posY;
   public int id;
   public boolean visible;
   public String caption;


   public guiExplorerWindow(int id, int xPos, int yPos, int theWidth, int theHeight, String caption) {
      this.id = id;
      this.posX = xPos;
      this.posY = yPos;
      this.field_146294_l = theWidth;
      this.field_146295_m = theHeight;
      this.caption = caption;
      this.visible = true;
   }

   public void showExplorerWindow(int posX, int posY) {
      if(this.visible) {
         FontRenderer renderOver = FMLClientHandler.instance().getClient().fontRenderer;
         FMLClientHandler.instance().getClient().getTextureManager().bindTexture(WindowBackground);
         GL11.glEnable(3042);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         GL11.glBlendFunc(770, 771);
         this.func_73729_b(this.posX, this.posY, 0, 0, this.field_146294_l, this.field_146295_m);
         this.func_73732_a(renderOver, this.caption, this.posX + this.field_146294_l / 2, this.posY + 8, Color.WHITE.getRGB());
      }

   }

   public void func_73866_w_() {
      super.initGui();
   }

   public void func_73863_a(int x, int y, float f) {
      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l - 40, 1, 15, 15, "X"));
      super.drawScreen(x, y, f);
   }

}
