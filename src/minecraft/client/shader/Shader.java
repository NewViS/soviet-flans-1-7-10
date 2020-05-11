package net.minecraft.client.shader;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderManager;
import org.lwjgl.opengl.GL11;

public class Shader {

   private final ShaderManager manager;
   public final Framebuffer framebufferIn;
   public final Framebuffer framebufferOut;
   private final List listAuxFramebuffers = Lists.newArrayList();
   private final List listAuxNames = Lists.newArrayList();
   private final List listAuxWidths = Lists.newArrayList();
   private final List listAuxHeights = Lists.newArrayList();
   private Matrix4f projectionMatrix;


   public Shader(IResourceManager var1, String var2, Framebuffer var3, Framebuffer var4) {
      this.manager = new ShaderManager(var1, var2);
      this.framebufferIn = var3;
      this.framebufferOut = var4;
   }

   public void deleteShader() {
      this.manager.func_147988_a();
   }

   public void addAuxFramebuffer(String var1, Object var2, int var3, int var4) {
      this.listAuxNames.add(this.listAuxNames.size(), var1);
      this.listAuxFramebuffers.add(this.listAuxFramebuffers.size(), var2);
      this.listAuxWidths.add(this.listAuxWidths.size(), Integer.valueOf(var3));
      this.listAuxHeights.add(this.listAuxHeights.size(), Integer.valueOf(var4));
   }

   private void preLoadShader() {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(3042);
      GL11.glDisable(2929);
      GL11.glDisable(3008);
      GL11.glDisable(2912);
      GL11.glDisable(2896);
      GL11.glDisable(2903);
      GL11.glEnable(3553);
      GL11.glBindTexture(3553, 0);
   }

   public void setProjectionMatrix(Matrix4f var1) {
      this.projectionMatrix = var1;
   }

   public void loadShader(float var1) {
      this.preLoadShader();
      this.framebufferIn.unbindFramebuffer();
      float var2 = (float)this.framebufferOut.framebufferTextureWidth;
      float var3 = (float)this.framebufferOut.framebufferTextureHeight;
      GL11.glViewport(0, 0, (int)var2, (int)var3);
      this.manager.func_147992_a("DiffuseSampler", this.framebufferIn);

      for(int var4 = 0; var4 < this.listAuxFramebuffers.size(); ++var4) {
         this.manager.func_147992_a((String)this.listAuxNames.get(var4), this.listAuxFramebuffers.get(var4));
         this.manager.func_147984_b("AuxSize" + var4).func_148087_a((float)((Integer)this.listAuxWidths.get(var4)).intValue(), (float)((Integer)this.listAuxHeights.get(var4)).intValue());
      }

      this.manager.func_147984_b("ProjMat").func_148088_a(this.projectionMatrix);
      this.manager.func_147984_b("InSize").func_148087_a((float)this.framebufferIn.framebufferTextureWidth, (float)this.framebufferIn.framebufferTextureHeight);
      this.manager.func_147984_b("OutSize").func_148087_a(var2, var3);
      this.manager.func_147984_b("Time").func_148090_a(var1);
      Minecraft var8 = Minecraft.getMinecraft();
      this.manager.func_147984_b("ScreenSize").func_148087_a((float)var8.displayWidth, (float)var8.displayHeight);
      this.manager.func_147995_c();
      this.framebufferOut.framebufferClear();
      this.framebufferOut.bindFramebuffer(false);
      GL11.glDepthMask(false);
      GL11.glColorMask(true, true, true, false);
      Tessellator var5 = Tessellator.instance;
      var5.startDrawingQuads();
      var5.setColorOpaque_I(-1);
      var5.addVertex(0.0D, (double)var3, 500.0D);
      var5.addVertex((double)var2, (double)var3, 500.0D);
      var5.addVertex((double)var2, 0.0D, 500.0D);
      var5.addVertex(0.0D, 0.0D, 500.0D);
      var5.draw();
      GL11.glDepthMask(true);
      GL11.glColorMask(true, true, true, true);
      this.manager.func_147993_b();
      this.framebufferOut.unbindFramebuffer();
      this.framebufferIn.unbindFramebufferTexture();
      Iterator var6 = this.listAuxFramebuffers.iterator();

      while(var6.hasNext()) {
         Object var7 = var6.next();
         if(var7 instanceof Framebuffer) {
            ((Framebuffer)var7).unbindFramebufferTexture();
         }
      }

   }

   public ShaderManager getShaderManager() {
      return this.manager;
   }
}
