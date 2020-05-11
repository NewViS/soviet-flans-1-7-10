package net.minecraft.client.renderer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings$Options;
import org.lwjgl.opengl.ARBFramebufferObject;
import org.lwjgl.opengl.ARBMultitexture;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.EXTBlendFuncSeparate;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;

public class OpenGlHelper {

   public static boolean openGL21;
   public static int defaultTexUnit;
   public static int lightmapTexUnit;
   public static boolean field_153197_d;
   public static int field_153198_e;
   public static int field_153199_f;
   public static int field_153200_g;
   public static int field_153201_h;
   public static int field_153202_i;
   public static int field_153203_j;
   public static int field_153204_k;
   public static int field_153205_l;
   public static int field_153206_m;
   private static int field_153212_w;
   public static boolean framebufferSupported;
   private static boolean field_153213_x;
   private static boolean field_153214_y;
   public static int field_153207_o;
   public static int field_153208_p;
   public static int field_153209_q;
   public static int field_153210_r;
   public static boolean anisotropicFilteringSupported;
   public static int anisotropicFilteringMax;
   private static boolean field_153215_z;
   private static boolean openGL14;
   public static boolean field_153211_u;
   public static boolean shadersSupported;
   private static String field_153196_B = "";


   public static void initializeTextures() {
      ContextCapabilities var0 = GLContext.getCapabilities();
      field_153215_z = var0.GL_ARB_multitexture && !var0.OpenGL13;
      if(field_153215_z) {
         field_153196_B = field_153196_B + "Using multitexturing ARB.\n";
         defaultTexUnit = '\u84c0';
         lightmapTexUnit = '\u84c1';
      } else {
         field_153196_B = field_153196_B + "Using GL 1.3 multitexturing.\n";
         defaultTexUnit = '\u84c0';
         lightmapTexUnit = '\u84c1';
      }

      field_153211_u = var0.GL_EXT_blend_func_separate && !var0.OpenGL14;
      openGL14 = var0.OpenGL14 || var0.GL_EXT_blend_func_separate;
      framebufferSupported = openGL14 && (var0.GL_ARB_framebuffer_object || var0.GL_EXT_framebuffer_object || var0.OpenGL30);
      if(framebufferSupported) {
         field_153196_B = field_153196_B + "Using framebuffer objects because ";
         if(var0.OpenGL30) {
            field_153196_B = field_153196_B + "OpenGL 3.0 is supported and separate blending is supported.\n";
            field_153212_w = 0;
            field_153198_e = '\u8d40';
            field_153199_f = '\u8d41';
            field_153200_g = '\u8ce0';
            field_153201_h = '\u8d00';
            field_153202_i = '\u8cd5';
            field_153203_j = '\u8cd6';
            field_153204_k = '\u8cd7';
            field_153205_l = '\u8cdb';
            field_153206_m = '\u8cdc';
         } else if(var0.GL_ARB_framebuffer_object) {
            field_153196_B = field_153196_B + "ARB_framebuffer_object is supported and separate blending is supported.\n";
            field_153212_w = 1;
            field_153198_e = '\u8d40';
            field_153199_f = '\u8d41';
            field_153200_g = '\u8ce0';
            field_153201_h = '\u8d00';
            field_153202_i = '\u8cd5';
            field_153204_k = '\u8cd7';
            field_153203_j = '\u8cd6';
            field_153205_l = '\u8cdb';
            field_153206_m = '\u8cdc';
         } else if(var0.GL_EXT_framebuffer_object) {
            field_153196_B = field_153196_B + "EXT_framebuffer_object is supported.\n";
            field_153212_w = 2;
            field_153198_e = '\u8d40';
            field_153199_f = '\u8d41';
            field_153200_g = '\u8ce0';
            field_153201_h = '\u8d00';
            field_153202_i = '\u8cd5';
            field_153204_k = '\u8cd7';
            field_153203_j = '\u8cd6';
            field_153205_l = '\u8cdb';
            field_153206_m = '\u8cdc';
         }
      } else {
         field_153196_B = field_153196_B + "Not using framebuffer objects because ";
         field_153196_B = field_153196_B + "OpenGL 1.4 is " + (var0.OpenGL14?"":"not ") + "supported, ";
         field_153196_B = field_153196_B + "EXT_blend_func_separate is " + (var0.GL_EXT_blend_func_separate?"":"not ") + "supported, ";
         field_153196_B = field_153196_B + "OpenGL 3.0 is " + (var0.OpenGL30?"":"not ") + "supported, ";
         field_153196_B = field_153196_B + "ARB_framebuffer_object is " + (var0.GL_ARB_framebuffer_object?"":"not ") + "supported, and ";
         field_153196_B = field_153196_B + "EXT_framebuffer_object is " + (var0.GL_EXT_framebuffer_object?"":"not ") + "supported.\n";
      }

      anisotropicFilteringSupported = var0.GL_EXT_texture_filter_anisotropic;
      anisotropicFilteringMax = (int)(anisotropicFilteringSupported?GL11.glGetFloat('\u84ff'):0.0F);
      field_153196_B = field_153196_B + "Anisotropic filtering is " + (anisotropicFilteringSupported?"":"not ") + "supported";
      if(anisotropicFilteringSupported) {
         field_153196_B = field_153196_B + " and maximum anisotropy is " + anisotropicFilteringMax + ".\n";
      } else {
         field_153196_B = field_153196_B + ".\n";
      }

      GameSettings$Options.ANISOTROPIC_FILTERING.setValueMax((float)anisotropicFilteringMax);
      openGL21 = var0.OpenGL21;
      field_153213_x = openGL21 || var0.GL_ARB_vertex_shader && var0.GL_ARB_fragment_shader && var0.GL_ARB_shader_objects;
      field_153196_B = field_153196_B + "Shaders are " + (field_153213_x?"":"not ") + "available because ";
      if(field_153213_x) {
         if(var0.OpenGL21) {
            field_153196_B = field_153196_B + "OpenGL 2.1 is supported.\n";
            field_153214_y = false;
            field_153207_o = '\u8b82';
            field_153208_p = '\u8b81';
            field_153209_q = '\u8b31';
            field_153210_r = '\u8b30';
         } else {
            field_153196_B = field_153196_B + "ARB_shader_objects, ARB_vertex_shader, and ARB_fragment_shader are supported.\n";
            field_153214_y = true;
            field_153207_o = '\u8b82';
            field_153208_p = '\u8b81';
            field_153209_q = '\u8b31';
            field_153210_r = '\u8b30';
         }
      } else {
         field_153196_B = field_153196_B + "OpenGL 2.1 is " + (var0.OpenGL21?"":"not ") + "supported, ";
         field_153196_B = field_153196_B + "ARB_shader_objects is " + (var0.GL_ARB_shader_objects?"":"not ") + "supported, ";
         field_153196_B = field_153196_B + "ARB_vertex_shader is " + (var0.GL_ARB_vertex_shader?"":"not ") + "supported, and ";
         field_153196_B = field_153196_B + "ARB_fragment_shader is " + (var0.GL_ARB_fragment_shader?"":"not ") + "supported.\n";
      }

      shadersSupported = framebufferSupported && field_153213_x;
      field_153197_d = GL11.glGetString(7936).toLowerCase().contains("nvidia");
   }

   public static boolean func_153193_b() {
      return shadersSupported;
   }

   public static String func_153172_c() {
      return field_153196_B;
   }

   public static int func_153175_a(int var0, int var1) {
      return field_153214_y?ARBShaderObjects.glGetObjectParameteriARB(var0, var1):GL20.glGetProgrami(var0, var1);
   }

   public static void func_153178_b(int var0, int var1) {
      if(field_153214_y) {
         ARBShaderObjects.glAttachObjectARB(var0, var1);
      } else {
         GL20.glAttachShader(var0, var1);
      }

   }

   public static void func_153180_a(int var0) {
      if(field_153214_y) {
         ARBShaderObjects.glDeleteObjectARB(var0);
      } else {
         GL20.glDeleteShader(var0);
      }

   }

   public static int func_153195_b(int var0) {
      return field_153214_y?ARBShaderObjects.glCreateShaderObjectARB(var0):GL20.glCreateShader(var0);
   }

   public static void func_153169_a(int var0, ByteBuffer var1) {
      if(field_153214_y) {
         ARBShaderObjects.glShaderSourceARB(var0, var1);
      } else {
         GL20.glShaderSource(var0, var1);
      }

   }

   public static void func_153170_c(int var0) {
      if(field_153214_y) {
         ARBShaderObjects.glCompileShaderARB(var0);
      } else {
         GL20.glCompileShader(var0);
      }

   }

   public static int func_153157_c(int var0, int var1) {
      return field_153214_y?ARBShaderObjects.glGetObjectParameteriARB(var0, var1):GL20.glGetShaderi(var0, var1);
   }

   public static String func_153158_d(int var0, int var1) {
      return field_153214_y?ARBShaderObjects.glGetInfoLogARB(var0, var1):GL20.glGetShaderInfoLog(var0, var1);
   }

   public static String func_153166_e(int var0, int var1) {
      return field_153214_y?ARBShaderObjects.glGetInfoLogARB(var0, var1):GL20.glGetProgramInfoLog(var0, var1);
   }

   public static void func_153161_d(int var0) {
      if(field_153214_y) {
         ARBShaderObjects.glUseProgramObjectARB(var0);
      } else {
         GL20.glUseProgram(var0);
      }

   }

   public static int func_153183_d() {
      return field_153214_y?ARBShaderObjects.glCreateProgramObjectARB():GL20.glCreateProgram();
   }

   public static void func_153187_e(int var0) {
      if(field_153214_y) {
         ARBShaderObjects.glDeleteObjectARB(var0);
      } else {
         GL20.glDeleteProgram(var0);
      }

   }

   public static void func_153179_f(int var0) {
      if(field_153214_y) {
         ARBShaderObjects.glLinkProgramARB(var0);
      } else {
         GL20.glLinkProgram(var0);
      }

   }

   public static int func_153194_a(int var0, CharSequence var1) {
      return field_153214_y?ARBShaderObjects.glGetUniformLocationARB(var0, var1):GL20.glGetUniformLocation(var0, var1);
   }

   public static void func_153181_a(int var0, IntBuffer var1) {
      if(field_153214_y) {
         ARBShaderObjects.glUniform1ARB(var0, var1);
      } else {
         GL20.glUniform1(var0, var1);
      }

   }

   public static void func_153163_f(int var0, int var1) {
      if(field_153214_y) {
         ARBShaderObjects.glUniform1iARB(var0, var1);
      } else {
         GL20.glUniform1i(var0, var1);
      }

   }

   public static void func_153168_a(int var0, FloatBuffer var1) {
      if(field_153214_y) {
         ARBShaderObjects.glUniform1ARB(var0, var1);
      } else {
         GL20.glUniform1(var0, var1);
      }

   }

   public static void func_153182_b(int var0, IntBuffer var1) {
      if(field_153214_y) {
         ARBShaderObjects.glUniform2ARB(var0, var1);
      } else {
         GL20.glUniform2(var0, var1);
      }

   }

   public static void func_153177_b(int var0, FloatBuffer var1) {
      if(field_153214_y) {
         ARBShaderObjects.glUniform2ARB(var0, var1);
      } else {
         GL20.glUniform2(var0, var1);
      }

   }

   public static void func_153192_c(int var0, IntBuffer var1) {
      if(field_153214_y) {
         ARBShaderObjects.glUniform3ARB(var0, var1);
      } else {
         GL20.glUniform3(var0, var1);
      }

   }

   public static void func_153191_c(int var0, FloatBuffer var1) {
      if(field_153214_y) {
         ARBShaderObjects.glUniform3ARB(var0, var1);
      } else {
         GL20.glUniform3(var0, var1);
      }

   }

   public static void func_153162_d(int var0, IntBuffer var1) {
      if(field_153214_y) {
         ARBShaderObjects.glUniform4ARB(var0, var1);
      } else {
         GL20.glUniform4(var0, var1);
      }

   }

   public static void func_153159_d(int var0, FloatBuffer var1) {
      if(field_153214_y) {
         ARBShaderObjects.glUniform4ARB(var0, var1);
      } else {
         GL20.glUniform4(var0, var1);
      }

   }

   public static void func_153173_a(int var0, boolean var1, FloatBuffer var2) {
      if(field_153214_y) {
         ARBShaderObjects.glUniformMatrix2ARB(var0, var1, var2);
      } else {
         GL20.glUniformMatrix2(var0, var1, var2);
      }

   }

   public static void func_153189_b(int var0, boolean var1, FloatBuffer var2) {
      if(field_153214_y) {
         ARBShaderObjects.glUniformMatrix3ARB(var0, var1, var2);
      } else {
         GL20.glUniformMatrix3(var0, var1, var2);
      }

   }

   public static void func_153160_c(int var0, boolean var1, FloatBuffer var2) {
      if(field_153214_y) {
         ARBShaderObjects.glUniformMatrix4ARB(var0, var1, var2);
      } else {
         GL20.glUniformMatrix4(var0, var1, var2);
      }

   }

   public static int func_153164_b(int var0, CharSequence var1) {
      return field_153214_y?ARBVertexShader.glGetAttribLocationARB(var0, var1):GL20.glGetAttribLocation(var0, var1);
   }

   public static void func_153171_g(int var0, int var1) {
      if(framebufferSupported) {
         switch(field_153212_w) {
         case 0:
            GL30.glBindFramebuffer(var0, var1);
            break;
         case 1:
            ARBFramebufferObject.glBindFramebuffer(var0, var1);
            break;
         case 2:
            EXTFramebufferObject.glBindFramebufferEXT(var0, var1);
         }

      }
   }

   public static void func_153176_h(int var0, int var1) {
      if(framebufferSupported) {
         switch(field_153212_w) {
         case 0:
            GL30.glBindRenderbuffer(var0, var1);
            break;
         case 1:
            ARBFramebufferObject.glBindRenderbuffer(var0, var1);
            break;
         case 2:
            EXTFramebufferObject.glBindRenderbufferEXT(var0, var1);
         }

      }
   }

   public static void func_153184_g(int var0) {
      if(framebufferSupported) {
         switch(field_153212_w) {
         case 0:
            GL30.glDeleteRenderbuffers(var0);
            break;
         case 1:
            ARBFramebufferObject.glDeleteRenderbuffers(var0);
            break;
         case 2:
            EXTFramebufferObject.glDeleteRenderbuffersEXT(var0);
         }

      }
   }

   public static void func_153174_h(int var0) {
      if(framebufferSupported) {
         switch(field_153212_w) {
         case 0:
            GL30.glDeleteFramebuffers(var0);
            break;
         case 1:
            ARBFramebufferObject.glDeleteFramebuffers(var0);
            break;
         case 2:
            EXTFramebufferObject.glDeleteFramebuffersEXT(var0);
         }

      }
   }

   public static int func_153165_e() {
      if(!framebufferSupported) {
         return -1;
      } else {
         switch(field_153212_w) {
         case 0:
            return GL30.glGenFramebuffers();
         case 1:
            return ARBFramebufferObject.glGenFramebuffers();
         case 2:
            return EXTFramebufferObject.glGenFramebuffersEXT();
         default:
            return -1;
         }
      }
   }

   public static int func_153185_f() {
      if(!framebufferSupported) {
         return -1;
      } else {
         switch(field_153212_w) {
         case 0:
            return GL30.glGenRenderbuffers();
         case 1:
            return ARBFramebufferObject.glGenRenderbuffers();
         case 2:
            return EXTFramebufferObject.glGenRenderbuffersEXT();
         default:
            return -1;
         }
      }
   }

   public static void func_153186_a(int var0, int var1, int var2, int var3) {
      if(framebufferSupported) {
         switch(field_153212_w) {
         case 0:
            GL30.glRenderbufferStorage(var0, var1, var2, var3);
            break;
         case 1:
            ARBFramebufferObject.glRenderbufferStorage(var0, var1, var2, var3);
            break;
         case 2:
            EXTFramebufferObject.glRenderbufferStorageEXT(var0, var1, var2, var3);
         }

      }
   }

   public static void func_153190_b(int var0, int var1, int var2, int var3) {
      if(framebufferSupported) {
         switch(field_153212_w) {
         case 0:
            GL30.glFramebufferRenderbuffer(var0, var1, var2, var3);
            break;
         case 1:
            ARBFramebufferObject.glFramebufferRenderbuffer(var0, var1, var2, var3);
            break;
         case 2:
            EXTFramebufferObject.glFramebufferRenderbufferEXT(var0, var1, var2, var3);
         }

      }
   }

   public static int func_153167_i(int var0) {
      if(!framebufferSupported) {
         return -1;
      } else {
         switch(field_153212_w) {
         case 0:
            return GL30.glCheckFramebufferStatus(var0);
         case 1:
            return ARBFramebufferObject.glCheckFramebufferStatus(var0);
         case 2:
            return EXTFramebufferObject.glCheckFramebufferStatusEXT(var0);
         default:
            return -1;
         }
      }
   }

   public static void func_153188_a(int var0, int var1, int var2, int var3, int var4) {
      if(framebufferSupported) {
         switch(field_153212_w) {
         case 0:
            GL30.glFramebufferTexture2D(var0, var1, var2, var3, var4);
            break;
         case 1:
            ARBFramebufferObject.glFramebufferTexture2D(var0, var1, var2, var3, var4);
            break;
         case 2:
            EXTFramebufferObject.glFramebufferTexture2DEXT(var0, var1, var2, var3, var4);
         }

      }
   }

   public static void setActiveTexture(int var0) {
      if(field_153215_z) {
         ARBMultitexture.glActiveTextureARB(var0);
      } else {
         GL13.glActiveTexture(var0);
      }

   }

   public static void setClientActiveTexture(int var0) {
      if(field_153215_z) {
         ARBMultitexture.glClientActiveTextureARB(var0);
      } else {
         GL13.glClientActiveTexture(var0);
      }

   }

   public static void setLightmapTextureCoords(int var0, float var1, float var2) {
      if(field_153215_z) {
         ARBMultitexture.glMultiTexCoord2fARB(var0, var1, var2);
      } else {
         GL13.glMultiTexCoord2f(var0, var1, var2);
      }

   }

   public static void glBlendFunc(int var0, int var1, int var2, int var3) {
      if(openGL14) {
         if(field_153211_u) {
            EXTBlendFuncSeparate.glBlendFuncSeparateEXT(var0, var1, var2, var3);
         } else {
            GL14.glBlendFuncSeparate(var0, var1, var2, var3);
         }
      } else {
         GL11.glBlendFunc(var0, var1);
      }

   }

   public static boolean isFramebufferEnabled() {
      return framebufferSupported && Minecraft.getMinecraft().gameSettings.fboEnable;
   }

}
