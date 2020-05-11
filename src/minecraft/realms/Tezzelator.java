package net.minecraft.realms;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.shader.TesselatorVertexState;

public class Tezzelator {

   public static Tessellator t = Tessellator.instance;
   public static final Tezzelator instance = new Tezzelator();


   public int end() {
      return t.draw();
   }

   public void vertex(double var1, double var3, double var5) {
      t.addVertex(var1, var3, var5);
   }

   public void color(float var1, float var2, float var3, float var4) {
      t.setColorRGBA_F(var1, var2, var3, var4);
   }

   public void color(int var1, int var2, int var3) {
      t.setColorOpaque(var1, var2, var3);
   }

   public void tex2(int var1) {
      t.setBrightness(var1);
   }

   public void normal(float var1, float var2, float var3) {
      t.setNormal(var1, var2, var3);
   }

   public void noColor() {
      t.disableColor();
   }

   public void color(int var1) {
      t.setColorOpaque_I(var1);
   }

   public void color(float var1, float var2, float var3) {
      t.setColorOpaque_F(var1, var2, var3);
   }

   public TesselatorVertexState sortQuads(float var1, float var2, float var3) {
      return t.getVertexState(var1, var2, var3);
   }

   public void restoreState(TesselatorVertexState var1) {
      t.setVertexState(var1);
   }

   public void begin(int var1) {
      t.startDrawing(var1);
   }

   public void begin() {
      t.startDrawingQuads();
   }

   public void vertexUV(double var1, double var3, double var5, double var7, double var9) {
      t.addVertexWithUV(var1, var3, var5, var7, var9);
   }

   public void color(int var1, int var2) {
      t.setColorRGBA_I(var1, var2);
   }

   public void offset(double var1, double var3, double var5) {
      t.setTranslation(var1, var3, var5);
   }

   public void color(int var1, int var2, int var3, int var4) {
      t.setColorRGBA(var1, var2, var3, var4);
   }

   public void addOffset(float var1, float var2, float var3) {
      t.addTranslation(var1, var2, var3);
   }

   public void tex(double var1, double var3) {
      t.setTextureUV(var1, var3);
   }

   public void color(byte var1, byte var2, byte var3) {
      t.func_154352_a(var1, var2, var3);
   }

}
