package net.minecraft.client.shader;

import java.nio.ByteBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

public class Framebuffer {

   public int framebufferTextureWidth;
   public int framebufferTextureHeight;
   public int framebufferWidth;
   public int framebufferHeight;
   public boolean useDepth;
   public int framebufferObject;
   public int framebufferTexture;
   public int depthBuffer;
   public float[] framebufferColor;
   public int framebufferFilter;


   public Framebuffer(int var1, int var2, boolean var3) {
      this.useDepth = var3;
      this.framebufferObject = -1;
      this.framebufferTexture = -1;
      this.depthBuffer = -1;
      this.framebufferColor = new float[4];
      this.framebufferColor[0] = 1.0F;
      this.framebufferColor[1] = 1.0F;
      this.framebufferColor[2] = 1.0F;
      this.framebufferColor[3] = 0.0F;
      this.createBindFramebuffer(var1, var2);
   }

   public void createBindFramebuffer(int var1, int var2) {
      if(!OpenGlHelper.isFramebufferEnabled()) {
         this.framebufferWidth = var1;
         this.framebufferHeight = var2;
      } else {
         GL11.glEnable(2929);
         if(this.framebufferObject >= 0) {
            this.deleteFramebuffer();
         }

         this.createFramebuffer(var1, var2);
         this.checkFramebufferComplete();
         OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
      }
   }

   public void deleteFramebuffer() {
      if(OpenGlHelper.isFramebufferEnabled()) {
         this.unbindFramebufferTexture();
         this.unbindFramebuffer();
         if(this.depthBuffer > -1) {
            OpenGlHelper.func_153184_g(this.depthBuffer);
            this.depthBuffer = -1;
         }

         if(this.framebufferTexture > -1) {
            TextureUtil.deleteTexture(this.framebufferTexture);
            this.framebufferTexture = -1;
         }

         if(this.framebufferObject > -1) {
            OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
            OpenGlHelper.func_153174_h(this.framebufferObject);
            this.framebufferObject = -1;
         }

      }
   }

   public void createFramebuffer(int var1, int var2) {
      this.framebufferWidth = var1;
      this.framebufferHeight = var2;
      this.framebufferTextureWidth = var1;
      this.framebufferTextureHeight = var2;
      if(!OpenGlHelper.isFramebufferEnabled()) {
         this.framebufferClear();
      } else {
         this.framebufferObject = OpenGlHelper.func_153165_e();
         this.framebufferTexture = TextureUtil.glGenTextures();
         if(this.useDepth) {
            this.depthBuffer = OpenGlHelper.func_153185_f();
         }

         this.setFramebufferFilter(9728);
         GL11.glBindTexture(3553, this.framebufferTexture);
         GL11.glTexImage2D(3553, 0, '\u8058', this.framebufferTextureWidth, this.framebufferTextureHeight, 0, 6408, 5121, (ByteBuffer)null);
         OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, this.framebufferObject);
         OpenGlHelper.func_153188_a(OpenGlHelper.field_153198_e, OpenGlHelper.field_153200_g, 3553, this.framebufferTexture, 0);
         if(this.useDepth) {
            OpenGlHelper.func_153176_h(OpenGlHelper.field_153199_f, this.depthBuffer);
            OpenGlHelper.func_153186_a(OpenGlHelper.field_153199_f, '\u81a6', this.framebufferTextureWidth, this.framebufferTextureHeight);
            OpenGlHelper.func_153190_b(OpenGlHelper.field_153198_e, OpenGlHelper.field_153201_h, OpenGlHelper.field_153199_f, this.depthBuffer);
         }

         this.framebufferClear();
         this.unbindFramebufferTexture();
      }
   }

   public void setFramebufferFilter(int var1) {
      if(OpenGlHelper.isFramebufferEnabled()) {
         this.framebufferFilter = var1;
         GL11.glBindTexture(3553, this.framebufferTexture);
         GL11.glTexParameterf(3553, 10241, (float)var1);
         GL11.glTexParameterf(3553, 10240, (float)var1);
         GL11.glTexParameterf(3553, 10242, 10496.0F);
         GL11.glTexParameterf(3553, 10243, 10496.0F);
         GL11.glBindTexture(3553, 0);
      }

   }

   public void checkFramebufferComplete() {
      int var1 = OpenGlHelper.func_153167_i(OpenGlHelper.field_153198_e);
      if(var1 != OpenGlHelper.field_153202_i) {
         if(var1 == OpenGlHelper.field_153203_j) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT");
         } else if(var1 == OpenGlHelper.field_153204_k) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT");
         } else if(var1 == OpenGlHelper.field_153205_l) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER");
         } else if(var1 == OpenGlHelper.field_153206_m) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER");
         } else {
            throw new RuntimeException("glCheckFramebufferStatus returned unknown status:" + var1);
         }
      }
   }

   public void bindFramebufferTexture() {
      if(OpenGlHelper.isFramebufferEnabled()) {
         GL11.glBindTexture(3553, this.framebufferTexture);
      }

   }

   public void unbindFramebufferTexture() {
      if(OpenGlHelper.isFramebufferEnabled()) {
         GL11.glBindTexture(3553, 0);
      }

   }

   public void bindFramebuffer(boolean var1) {
      if(OpenGlHelper.isFramebufferEnabled()) {
         OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, this.framebufferObject);
         if(var1) {
            GL11.glViewport(0, 0, this.framebufferWidth, this.framebufferHeight);
         }
      }

   }

   public void unbindFramebuffer() {
      if(OpenGlHelper.isFramebufferEnabled()) {
         OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
      }

   }

   public void setFramebufferColor(float var1, float var2, float var3, float var4) {
      this.framebufferColor[0] = var1;
      this.framebufferColor[1] = var2;
      this.framebufferColor[2] = var3;
      this.framebufferColor[3] = var4;
   }

   public void framebufferRender(int var1, int var2) {
      if(OpenGlHelper.isFramebufferEnabled()) {
         GL11.glColorMask(true, true, true, false);
         GL11.glDisable(2929);
         GL11.glDepthMask(false);
         GL11.glMatrixMode(5889);
         GL11.glLoadIdentity();
         GL11.glOrtho(0.0D, (double)var1, (double)var2, 0.0D, 1000.0D, 3000.0D);
         GL11.glMatrixMode(5888);
         GL11.glLoadIdentity();
         GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
         GL11.glViewport(0, 0, var1, var2);
         GL11.glEnable(3553);
         GL11.glDisable(2896);
         GL11.glDisable(3008);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glEnable(2903);
         this.bindFramebufferTexture();
         float var3 = (float)var1;
         float var4 = (float)var2;
         float var5 = (float)this.framebufferWidth / (float)this.framebufferTextureWidth;
         float var6 = (float)this.framebufferHeight / (float)this.framebufferTextureHeight;
         Tessellator var7 = Tessellator.instance;
         var7.startDrawingQuads();
         var7.setColorOpaque_I(-1);
         var7.addVertexWithUV(0.0D, (double)var4, 0.0D, 0.0D, 0.0D);
         var7.addVertexWithUV((double)var3, (double)var4, 0.0D, (double)var5, 0.0D);
         var7.addVertexWithUV((double)var3, 0.0D, 0.0D, (double)var5, (double)var6);
         var7.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, (double)var6);
         var7.draw();
         this.unbindFramebufferTexture();
         GL11.glDepthMask(true);
         GL11.glColorMask(true, true, true, true);
      }
   }

   public void framebufferClear() {
      this.bindFramebuffer(true);
      GL11.glClearColor(this.framebufferColor[0], this.framebufferColor[1], this.framebufferColor[2], this.framebufferColor[3]);
      int var1 = 16384;
      if(this.useDepth) {
         GL11.glClearDepth(1.0D);
         var1 |= 256;
      }

      GL11.glClear(var1);
      this.unbindFramebuffer();
   }
}
