package net.minecraft.client.shader;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.ShaderManager;
import net.minecraft.client.util.JsonException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShaderLinkHelper {

   private static final Logger logger = LogManager.getLogger();
   private static ShaderLinkHelper staticShaderLinkHelper;


   public static void setNewStaticShaderLinkHelper() {
      staticShaderLinkHelper = new ShaderLinkHelper();
   }

   public static ShaderLinkHelper getStaticShaderLinkHelper() {
      return staticShaderLinkHelper;
   }

   public void func_148077_a(ShaderManager var1) {
      var1.func_147994_f().func_148054_b(var1);
      var1.func_147989_e().func_148054_b(var1);
      OpenGlHelper.func_153187_e(var1.func_147986_h());
   }

   public int func_148078_c() {
      int var1 = OpenGlHelper.func_153183_d();
      if(var1 <= 0) {
         throw new JsonException("Could not create shader program (returned program ID " + var1 + ")");
      } else {
         return var1;
      }
   }

   public void func_148075_b(ShaderManager var1) {
      var1.func_147994_f().func_148056_a(var1);
      var1.func_147989_e().func_148056_a(var1);
      OpenGlHelper.func_153179_f(var1.func_147986_h());
      int var2 = OpenGlHelper.func_153175_a(var1.func_147986_h(), OpenGlHelper.field_153207_o);
      if(var2 == 0) {
         logger.warn("Error encountered when linking program containing VS " + var1.func_147989_e().func_148055_a() + " and FS " + var1.func_147994_f().func_148055_a() + ". Log output:");
         logger.warn(OpenGlHelper.func_153166_e(var1.func_147986_h(), '\u8000'));
      }

   }

}
