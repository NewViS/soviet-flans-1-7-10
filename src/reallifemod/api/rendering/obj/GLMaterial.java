package de.ItsAMysterious.mods.reallifemod.api.rendering.obj;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;

public class GLMaterial {

   public static final float[] colorClear = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
   public static final float[] colorNone = new float[]{0.0F, 0.0F, 0.0F, 1.0F};
   public static final float[] colorRed = new float[]{1.0F, 0.0F, 0.0F, 1.0F};
   public static final float[] colorGreen = new float[]{0.0F, 1.0F, 0.0F, 1.0F};
   public static final float[] colorBlue = new float[]{0.0F, 0.0F, 1.0F, 1.0F};
   public static final float[] colorYellow = new float[]{1.0F, 1.0F, 0.0F, 1.0F};
   public static final float[] colorCyan = new float[]{0.0F, 1.0F, 1.0F, 1.0F};
   public static final float[] colorMagenta = new float[]{1.0F, 0.0F, 1.0F, 1.0F};
   public static final float[] colorGrayLight = new float[]{0.8F, 0.8F, 0.8F, 1.0F};
   public static final float[] colorGrayMedium = new float[]{0.5F, 0.5F, 0.5F, 1.0F};
   public static final float[] colorGrayDark = new float[]{0.2F, 0.2F, 0.2F, 1.0F};
   public static final float[] colorWhite = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
   public static final float[] colorBlack = new float[]{0.0F, 0.0F, 0.0F, 1.0F};
   public static final float[] colorBeige = new float[]{0.7F, 0.7F, 0.4F, 1.0F};
   public static final float[] colorDefaultDiffuse = new float[]{0.8F, 0.8F, 0.8F, 1.0F};
   public static final float[] colorDefaultAmbient = new float[]{0.2F, 0.2F, 0.2F, 1.0F};
   public static final float minShine = 0.0F;
   public static final float maxShine = 127.0F;
   private static FloatBuffer defaultDiffuse = allocFloats(colorDefaultDiffuse);
   private static FloatBuffer defaultAmbient = allocFloats(colorDefaultAmbient);
   private static FloatBuffer defaultSpecular = allocFloats(colorNone);
   private static FloatBuffer defaultEmission = allocFloats(colorNone);
   private static FloatBuffer defaultShine = allocFloats(new float[]{0.0F, 0.0F, 0.0F, 0.0F});
   public FloatBuffer diffuse;
   public FloatBuffer ambient;
   public FloatBuffer specular;
   public FloatBuffer emission;
   public FloatBuffer shininess;
   public String mtlname = "noname";
   public String textureFile = null;
   public int textureHandle;
   public static final int SIZE_FLOAT = 4;


   public GLMaterial() {
      this.setDefaults();
   }

   public GLMaterial(float[] color) {
      this.setDefaults();
      this.setColor(color);
   }

   public void setDefaults() {
      this.setDiffuse(colorDefaultDiffuse);
      this.setAmbient(colorDefaultAmbient);
      this.setSpecular(colorNone);
      this.setEmission(colorNone);
      this.setShininess(0.0F);
   }

   public void setDiffuse(float[] color) {
      this.diffuse = allocFloats(color);
   }

   public void setAmbient(float[] color) {
      this.ambient = allocFloats(color);
   }

   public void setSpecular(float[] color) {
      this.specular = allocFloats(color);
   }

   public void setEmission(float[] color) {
      this.emission = allocFloats(color);
   }

   public void setShininess(float howShiny) {
      if(howShiny >= 0.0F && howShiny <= 127.0F) {
         float[] tmp = new float[]{howShiny, 0.0F, 0.0F, 0.0F};
         this.shininess = allocFloats(tmp);
      }

   }

   public void apply() {
      GL11.glMaterial(1028, 4609, this.diffuse);
      GL11.glMaterial(1028, 4608, this.ambient);
      GL11.glMaterial(1028, 4610, this.specular);
      GL11.glMaterial(1028, 5632, this.emission);
      GL11.glMaterial(1028, 5633, this.shininess);
   }

   public static void clear() {
      GL11.glMaterial(1028, 4609, defaultDiffuse);
      GL11.glMaterial(1028, 4608, defaultAmbient);
      GL11.glMaterial(1028, 4610, defaultSpecular);
      GL11.glMaterial(1028, 5632, defaultEmission);
      GL11.glMaterial(1028, 5633, defaultShine);
   }

   public void setColor(float[] color) {
      this.setDiffuse(color);
      this.setAmbient(color);
   }

   public void setReflection(float intensity, float highlight) {
      float[] color = new float[]{intensity, intensity, intensity, 1.0F};
      this.setSpecular(color);
      this.setShininess((float)((int)(highlight * 127.0F)));
   }

   public void setGlowColor(float[] color) {
      this.emission = allocFloats(color);
   }

   public void setAlpha(float alphaVal) {
      this.diffuse.put(3, alphaVal);
   }

   public float getAlpha() {
      return this.diffuse.get(3);
   }

   public void setTextureFile(String s) {
      this.textureFile = s;
   }

   public void setTexture(int txtrHandle) {
      this.textureHandle = txtrHandle;
   }

   public String getTextureFile() {
      return this.textureFile;
   }

   public int getTexture() {
      return this.textureHandle;
   }

   public void setName(String s) {
      this.mtlname = s;
   }

   public String getName() {
      return this.mtlname;
   }

   public static FloatBuffer allocFloats(int howmany) {
      return ByteBuffer.allocateDirect(howmany * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
   }

   public static FloatBuffer allocFloats(float[] floatarray) {
      FloatBuffer fb = ByteBuffer.allocateDirect(floatarray.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
      fb.put(floatarray).flip();
      return fb;
   }

}
