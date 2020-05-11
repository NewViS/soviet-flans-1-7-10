package de.ItsAMysterious.mods.reallifemod.api.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class RLM_Gui extends GuiScreen {

   public void drawTexturedModalRectRotate(double left, double top, double width, double height, double uLeft, double vTop, double uWidth, double vHeight, float rot) {
      GL11.glPushMatrix();
      GL11.glTranslated(left + width / 2.0D, top + height / 2.0D, 0.0D);
      GL11.glRotatef(rot, 0.0F, 0.0F, 1.0F);
      RenderHelper.enableStandardItemLighting();
      float f = 0.00390625F;
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV(-width / 2.0D, height / 2.0D, -11.0D, uLeft * 0.00390625D, (vTop + vHeight) * 0.00390625D);
      tessellator.addVertexWithUV(width / 2.0D, height / 2.0D, -11.0D, (uLeft + uWidth) * 0.00390625D, (vTop + vHeight) * 0.00390625D);
      tessellator.addVertexWithUV(width / 2.0D, -height / 2.0D, -11.0D, (uLeft + uWidth) * 0.00390625D, vTop * 0.00390625D);
      tessellator.addVertexWithUV(-width / 2.0D, -height / 2.0D, -11.0D, uLeft * 0.00390625D, vTop * 0.00390625D);
      tessellator.draw();
      GL11.glPopMatrix();
   }

   public void drawRect(int x1, int y1, int x2, int y2, float value, int color, float zPos) {
      int j1;
      if(x1 < x2) {
         j1 = x1;
         x1 = x2;
         x2 = j1;
      }

      if(y1 < y2) {
         j1 = y1;
         y1 = y2;
         y2 = j1;
      }

      float f3 = (float)(color >> 24 & 255) / 255.0F;
      float f = (float)(color >> 16 & 255) / 255.0F;
      float f1 = (float)(color >> 8 & 255) / 255.0F;
      float f2 = (float)(color & 255) / 255.0F;
      Tessellator tessellator = Tessellator.instance;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      GL11.glColor4f(f, f1, f2, f3);
      tessellator.startDrawingQuads();
      tessellator.addVertex((double)x1, (double)y1, (double)zPos);
      tessellator.addVertex((double)x2 - (double)this.field_146297_k.displayWidth - (double)((float)((x1 + x2) / 100) * value), (double)y1, (double)zPos);
      tessellator.addVertex((double)x2 - (double)this.field_146297_k.displayWidth - (double)((float)((x1 + x2) / 100) * value), (double)y2, (double)zPos);
      tessellator.addVertex((double)x1, (double)y2, (double)zPos);
      tessellator.draw();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }

   public void drawProgressBar(int x1, int y1, float position, float min, float max, int color, float zPos) {
      Tessellator tessellator = Tessellator.instance;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      tessellator.setColorRGBA(color, color, color, 255);
      tessellator.startDrawingQuads();
      tessellator.addVertex((double)x1, (double)y1, (double)zPos);
      tessellator.addVertex((double)(x1 + 100), (double)y1, (double)zPos);
      tessellator.addVertex((double)(x1 + 100), (double)y1 + 10.0D, (double)zPos);
      tessellator.addVertex((double)x1, (double)(y1 + 10), (double)zPos);
      tessellator.draw();
      GL11.glEnable(3553);
   }

   public void drawLineStipple(double[] line, int color, int factor, int pattern) {
      GL11.glEnable(2852);
      GL11.glLineStipple(factor, (short)pattern);
      this.drawLine(line, color);
      GL11.glDisable(2852);
   }

   public void drawLine(double[] line, int color) {
      this.drawLine(line, color, 1);
   }

   public void drawString(String s, int x, int y, int color) {
      this.func_73731_b(this.field_146289_q, s, x, y, color);
   }

   public void drawCenteredString(String s, int x, int y, int color) {
      this.func_73732_a(this.field_146289_q, s, x, y, color);
   }

   public void drawLine(double[] line, int color, int mode) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4ub((byte)(color >> 16 & 255), (byte)(color >> 8 & 255), (byte)(color >> 0 & 255), (byte)(color >> 24 & 255));
      Tessellator tessellator = Tessellator.instance;
      tessellator.setBrightness(mode);
      tessellator.startDrawing(1);

      for(int i = 0; i < line.length; i += 2) {
         tessellator.addVertex(line[i + 0], line[i + 1], (double)color);
      }

      tessellator.draw();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
      GL11.glPopMatrix();
   }

   public void drawPoints(double[] points, int color, int pointWidth) {
      int prevWidth = GL11.glGetInteger(2833);
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4ub((byte)(color >> 16 & 255), (byte)(color >> 8 & 255), (byte)(color >> 0 & 255), (byte)(color >> 24 & 255));
      GL11.glPointSize((float)pointWidth);
      Tessellator tessellator = Tessellator.instance;
      tessellator.setBrightness(0);

      for(int i = 0; i < points.length; i += 2) {
         tessellator.addVertex(points[i], points[i + 1], 0.0D);
      }

      tessellator.draw();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
      GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
      GL11.glPointSize((float)prevWidth);
   }

   public void func_73878_a(boolean b1, int id) {
      super.confirmClicked(b1, id);
   }

   protected void func_73733_a(int p_73733_1_, int p_73733_2_, int p_73733_3_, int p_73733_4_, int p_73733_5_, int p_73733_6_) {
      float f = (float)(p_73733_5_ >> 24 & 255) / 255.0F;
      float f1 = (float)(p_73733_5_ >> 16 & 255) / 255.0F;
      float f2 = (float)(p_73733_5_ >> 8 & 255) / 255.0F;
      float f3 = (float)(p_73733_5_ & 255) / 255.0F;
      float f4 = (float)(p_73733_6_ >> 24 & 255) / 255.0F;
      float f5 = (float)(p_73733_6_ >> 16 & 255) / 255.0F;
      float f6 = (float)(p_73733_6_ >> 8 & 255) / 255.0F;
      float f7 = (float)(p_73733_6_ & 255) / 255.0F;
      GL11.glDisable(3553);
      GL11.glEnable(3042);
      GL11.glEnable(3008);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glShadeModel(7425);
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.setColorRGBA_F(f1, f2, f3, f);
      tessellator.addVertex((double)p_73733_3_, (double)p_73733_2_, (double)this.field_73735_i);
      tessellator.addVertex((double)p_73733_1_, (double)p_73733_2_, (double)this.field_73735_i);
      tessellator.setColorRGBA_F(f5, f6, f7, f4);
      tessellator.addVertex((double)p_73733_1_, (double)p_73733_4_, (double)this.field_73735_i);
      tessellator.addVertex((double)p_73733_3_, (double)p_73733_4_, (double)this.field_73735_i);
      tessellator.draw();
      GL11.glShadeModel(7424);
      GL11.glDisable(3042);
      GL11.glEnable(3553);
   }
}
