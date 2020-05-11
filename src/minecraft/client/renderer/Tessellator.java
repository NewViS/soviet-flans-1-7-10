package net.minecraft.client.renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.PriorityQueue;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.TesselatorVertexState;
import net.minecraft.client.util.QuadComparator;
import org.lwjgl.opengl.GL11;

public class Tessellator {

   private ByteBuffer byteBuffer;
   private IntBuffer intBuffer;
   private FloatBuffer floatBuffer;
   private ShortBuffer shortBuffer;
   private int[] rawBuffer;
   private int vertexCount;
   private double textureU;
   private double textureV;
   private int brightness;
   private int color;
   private boolean hasColor;
   private boolean hasTexture;
   private boolean hasBrightness;
   private boolean hasNormals;
   private int rawBufferIndex;
   private int addedVertices;
   private boolean isColorDisabled;
   private int drawMode;
   private double xOffset;
   private double yOffset;
   private double zOffset;
   private int normal;
   public static final Tessellator instance = new Tessellator(2097152);
   private boolean isDrawing;
   private int bufferSize;


   private Tessellator(int var1) {
      this.bufferSize = var1;
      this.byteBuffer = GLAllocation.createDirectByteBuffer(var1 * 4);
      this.intBuffer = this.byteBuffer.asIntBuffer();
      this.floatBuffer = this.byteBuffer.asFloatBuffer();
      this.shortBuffer = this.byteBuffer.asShortBuffer();
      this.rawBuffer = new int[var1];
   }

   public int draw() {
      if(!this.isDrawing) {
         throw new IllegalStateException("Not tesselating!");
      } else {
         this.isDrawing = false;
         if(this.vertexCount > 0) {
            this.intBuffer.clear();
            this.intBuffer.put(this.rawBuffer, 0, this.rawBufferIndex);
            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.rawBufferIndex * 4);
            if(this.hasTexture) {
               this.floatBuffer.position(3);
               GL11.glTexCoordPointer(2, 32, this.floatBuffer);
               GL11.glEnableClientState('\u8078');
            }

            if(this.hasBrightness) {
               OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
               this.shortBuffer.position(14);
               GL11.glTexCoordPointer(2, 32, this.shortBuffer);
               GL11.glEnableClientState('\u8078');
               OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            }

            if(this.hasColor) {
               this.byteBuffer.position(20);
               GL11.glColorPointer(4, true, 32, this.byteBuffer);
               GL11.glEnableClientState('\u8076');
            }

            if(this.hasNormals) {
               this.byteBuffer.position(24);
               GL11.glNormalPointer(32, this.byteBuffer);
               GL11.glEnableClientState('\u8075');
            }

            this.floatBuffer.position(0);
            GL11.glVertexPointer(3, 32, this.floatBuffer);
            GL11.glEnableClientState('\u8074');
            GL11.glDrawArrays(this.drawMode, 0, this.vertexCount);
            GL11.glDisableClientState('\u8074');
            if(this.hasTexture) {
               GL11.glDisableClientState('\u8078');
            }

            if(this.hasBrightness) {
               OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
               GL11.glDisableClientState('\u8078');
               OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            }

            if(this.hasColor) {
               GL11.glDisableClientState('\u8076');
            }

            if(this.hasNormals) {
               GL11.glDisableClientState('\u8075');
            }
         }

         int var1 = this.rawBufferIndex * 4;
         this.reset();
         return var1;
      }
   }

   public TesselatorVertexState getVertexState(float var1, float var2, float var3) {
      int[] var4 = new int[this.rawBufferIndex];
      PriorityQueue var5 = new PriorityQueue(this.rawBufferIndex, new QuadComparator(this.rawBuffer, var1 + (float)this.xOffset, var2 + (float)this.yOffset, var3 + (float)this.zOffset));
      byte var6 = 32;

      int var7;
      for(var7 = 0; var7 < this.rawBufferIndex; var7 += var6) {
         var5.add(Integer.valueOf(var7));
      }

      for(var7 = 0; !var5.isEmpty(); var7 += var6) {
         int var8 = ((Integer)var5.remove()).intValue();

         for(int var9 = 0; var9 < var6; ++var9) {
            var4[var7 + var9] = this.rawBuffer[var8 + var9];
         }
      }

      System.arraycopy(var4, 0, this.rawBuffer, 0, var4.length);
      return new TesselatorVertexState(var4, this.rawBufferIndex, this.vertexCount, this.hasTexture, this.hasBrightness, this.hasNormals, this.hasColor);
   }

   public void setVertexState(TesselatorVertexState var1) {
      System.arraycopy(var1.getRawBuffer(), 0, this.rawBuffer, 0, var1.getRawBuffer().length);
      this.rawBufferIndex = var1.getRawBufferIndex();
      this.vertexCount = var1.getVertexCount();
      this.hasTexture = var1.getHasTexture();
      this.hasBrightness = var1.getHasBrightness();
      this.hasColor = var1.getHasColor();
      this.hasNormals = var1.getHasNormals();
   }

   private void reset() {
      this.vertexCount = 0;
      this.byteBuffer.clear();
      this.rawBufferIndex = 0;
      this.addedVertices = 0;
   }

   public void startDrawingQuads() {
      this.startDrawing(7);
   }

   public void startDrawing(int var1) {
      if(this.isDrawing) {
         throw new IllegalStateException("Already tesselating!");
      } else {
         this.isDrawing = true;
         this.reset();
         this.drawMode = var1;
         this.hasNormals = false;
         this.hasColor = false;
         this.hasTexture = false;
         this.hasBrightness = false;
         this.isColorDisabled = false;
      }
   }

   public void setTextureUV(double var1, double var3) {
      this.hasTexture = true;
      this.textureU = var1;
      this.textureV = var3;
   }

   public void setBrightness(int var1) {
      this.hasBrightness = true;
      this.brightness = var1;
   }

   public void setColorOpaque_F(float var1, float var2, float var3) {
      this.setColorOpaque((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F));
   }

   public void setColorRGBA_F(float var1, float var2, float var3, float var4) {
      this.setColorRGBA((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F), (int)(var4 * 255.0F));
   }

   public void setColorOpaque(int var1, int var2, int var3) {
      this.setColorRGBA(var1, var2, var3, 255);
   }

   public void setColorRGBA(int var1, int var2, int var3, int var4) {
      if(!this.isColorDisabled) {
         if(var1 > 255) {
            var1 = 255;
         }

         if(var2 > 255) {
            var2 = 255;
         }

         if(var3 > 255) {
            var3 = 255;
         }

         if(var4 > 255) {
            var4 = 255;
         }

         if(var1 < 0) {
            var1 = 0;
         }

         if(var2 < 0) {
            var2 = 0;
         }

         if(var3 < 0) {
            var3 = 0;
         }

         if(var4 < 0) {
            var4 = 0;
         }

         this.hasColor = true;
         if(ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            this.color = var4 << 24 | var3 << 16 | var2 << 8 | var1;
         } else {
            this.color = var1 << 24 | var2 << 16 | var3 << 8 | var4;
         }

      }
   }

   public void func_154352_a(byte var1, byte var2, byte var3) {
      this.setColorOpaque(var1 & 255, var2 & 255, var3 & 255);
   }

   public void addVertexWithUV(double var1, double var3, double var5, double var7, double var9) {
      this.setTextureUV(var7, var9);
      this.addVertex(var1, var3, var5);
   }

   public void addVertex(double var1, double var3, double var5) {
      ++this.addedVertices;
      if(this.hasTexture) {
         this.rawBuffer[this.rawBufferIndex + 3] = Float.floatToRawIntBits((float)this.textureU);
         this.rawBuffer[this.rawBufferIndex + 4] = Float.floatToRawIntBits((float)this.textureV);
      }

      if(this.hasBrightness) {
         this.rawBuffer[this.rawBufferIndex + 7] = this.brightness;
      }

      if(this.hasColor) {
         this.rawBuffer[this.rawBufferIndex + 5] = this.color;
      }

      if(this.hasNormals) {
         this.rawBuffer[this.rawBufferIndex + 6] = this.normal;
      }

      this.rawBuffer[this.rawBufferIndex + 0] = Float.floatToRawIntBits((float)(var1 + this.xOffset));
      this.rawBuffer[this.rawBufferIndex + 1] = Float.floatToRawIntBits((float)(var3 + this.yOffset));
      this.rawBuffer[this.rawBufferIndex + 2] = Float.floatToRawIntBits((float)(var5 + this.zOffset));
      this.rawBufferIndex += 8;
      ++this.vertexCount;
      if(this.vertexCount % 4 == 0 && this.rawBufferIndex >= this.bufferSize - 32) {
         this.draw();
         this.isDrawing = true;
      }

   }

   public void setColorOpaque_I(int var1) {
      int var2 = var1 >> 16 & 255;
      int var3 = var1 >> 8 & 255;
      int var4 = var1 & 255;
      this.setColorOpaque(var2, var3, var4);
   }

   public void setColorRGBA_I(int var1, int var2) {
      int var3 = var1 >> 16 & 255;
      int var4 = var1 >> 8 & 255;
      int var5 = var1 & 255;
      this.setColorRGBA(var3, var4, var5, var2);
   }

   public void disableColor() {
      this.isColorDisabled = true;
   }

   public void setNormal(float var1, float var2, float var3) {
      this.hasNormals = true;
      byte var4 = (byte)((int)(var1 * 127.0F));
      byte var5 = (byte)((int)(var2 * 127.0F));
      byte var6 = (byte)((int)(var3 * 127.0F));
      this.normal = var4 & 255 | (var5 & 255) << 8 | (var6 & 255) << 16;
   }

   public void setTranslation(double var1, double var3, double var5) {
      this.xOffset = var1;
      this.yOffset = var3;
      this.zOffset = var5;
   }

   public void addTranslation(float var1, float var2, float var3) {
      this.xOffset += (double)var1;
      this.yOffset += (double)var2;
      this.zOffset += (double)var3;
   }

}
