package net.minecraft.client.renderer.culling;

import java.nio.FloatBuffer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ClippingHelperImpl extends ClippingHelper {

   private static ClippingHelperImpl instance = new ClippingHelperImpl();
   private FloatBuffer projectionMatrixBuffer = GLAllocation.createDirectFloatBuffer(16);
   private FloatBuffer modelviewMatrixBuffer = GLAllocation.createDirectFloatBuffer(16);
   private FloatBuffer field_78564_h = GLAllocation.createDirectFloatBuffer(16);


   public static ClippingHelper getInstance() {
      instance.init();
      return instance;
   }

   private void normalize(float[][] var1, int var2) {
      float var3 = MathHelper.sqrt_float(var1[var2][0] * var1[var2][0] + var1[var2][1] * var1[var2][1] + var1[var2][2] * var1[var2][2]);
      var1[var2][0] /= var3;
      var1[var2][1] /= var3;
      var1[var2][2] /= var3;
      var1[var2][3] /= var3;
   }

   private void init() {
      this.projectionMatrixBuffer.clear();
      this.modelviewMatrixBuffer.clear();
      this.field_78564_h.clear();
      GL11.glGetFloat(2983, this.projectionMatrixBuffer);
      GL11.glGetFloat(2982, this.modelviewMatrixBuffer);
      this.projectionMatrixBuffer.flip().limit(16);
      this.projectionMatrixBuffer.get(super.projectionMatrix);
      this.modelviewMatrixBuffer.flip().limit(16);
      this.modelviewMatrixBuffer.get(super.modelviewMatrix);
      super.clippingMatrix[0] = super.modelviewMatrix[0] * super.projectionMatrix[0] + super.modelviewMatrix[1] * super.projectionMatrix[4] + super.modelviewMatrix[2] * super.projectionMatrix[8] + super.modelviewMatrix[3] * super.projectionMatrix[12];
      super.clippingMatrix[1] = super.modelviewMatrix[0] * super.projectionMatrix[1] + super.modelviewMatrix[1] * super.projectionMatrix[5] + super.modelviewMatrix[2] * super.projectionMatrix[9] + super.modelviewMatrix[3] * super.projectionMatrix[13];
      super.clippingMatrix[2] = super.modelviewMatrix[0] * super.projectionMatrix[2] + super.modelviewMatrix[1] * super.projectionMatrix[6] + super.modelviewMatrix[2] * super.projectionMatrix[10] + super.modelviewMatrix[3] * super.projectionMatrix[14];
      super.clippingMatrix[3] = super.modelviewMatrix[0] * super.projectionMatrix[3] + super.modelviewMatrix[1] * super.projectionMatrix[7] + super.modelviewMatrix[2] * super.projectionMatrix[11] + super.modelviewMatrix[3] * super.projectionMatrix[15];
      super.clippingMatrix[4] = super.modelviewMatrix[4] * super.projectionMatrix[0] + super.modelviewMatrix[5] * super.projectionMatrix[4] + super.modelviewMatrix[6] * super.projectionMatrix[8] + super.modelviewMatrix[7] * super.projectionMatrix[12];
      super.clippingMatrix[5] = super.modelviewMatrix[4] * super.projectionMatrix[1] + super.modelviewMatrix[5] * super.projectionMatrix[5] + super.modelviewMatrix[6] * super.projectionMatrix[9] + super.modelviewMatrix[7] * super.projectionMatrix[13];
      super.clippingMatrix[6] = super.modelviewMatrix[4] * super.projectionMatrix[2] + super.modelviewMatrix[5] * super.projectionMatrix[6] + super.modelviewMatrix[6] * super.projectionMatrix[10] + super.modelviewMatrix[7] * super.projectionMatrix[14];
      super.clippingMatrix[7] = super.modelviewMatrix[4] * super.projectionMatrix[3] + super.modelviewMatrix[5] * super.projectionMatrix[7] + super.modelviewMatrix[6] * super.projectionMatrix[11] + super.modelviewMatrix[7] * super.projectionMatrix[15];
      super.clippingMatrix[8] = super.modelviewMatrix[8] * super.projectionMatrix[0] + super.modelviewMatrix[9] * super.projectionMatrix[4] + super.modelviewMatrix[10] * super.projectionMatrix[8] + super.modelviewMatrix[11] * super.projectionMatrix[12];
      super.clippingMatrix[9] = super.modelviewMatrix[8] * super.projectionMatrix[1] + super.modelviewMatrix[9] * super.projectionMatrix[5] + super.modelviewMatrix[10] * super.projectionMatrix[9] + super.modelviewMatrix[11] * super.projectionMatrix[13];
      super.clippingMatrix[10] = super.modelviewMatrix[8] * super.projectionMatrix[2] + super.modelviewMatrix[9] * super.projectionMatrix[6] + super.modelviewMatrix[10] * super.projectionMatrix[10] + super.modelviewMatrix[11] * super.projectionMatrix[14];
      super.clippingMatrix[11] = super.modelviewMatrix[8] * super.projectionMatrix[3] + super.modelviewMatrix[9] * super.projectionMatrix[7] + super.modelviewMatrix[10] * super.projectionMatrix[11] + super.modelviewMatrix[11] * super.projectionMatrix[15];
      super.clippingMatrix[12] = super.modelviewMatrix[12] * super.projectionMatrix[0] + super.modelviewMatrix[13] * super.projectionMatrix[4] + super.modelviewMatrix[14] * super.projectionMatrix[8] + super.modelviewMatrix[15] * super.projectionMatrix[12];
      super.clippingMatrix[13] = super.modelviewMatrix[12] * super.projectionMatrix[1] + super.modelviewMatrix[13] * super.projectionMatrix[5] + super.modelviewMatrix[14] * super.projectionMatrix[9] + super.modelviewMatrix[15] * super.projectionMatrix[13];
      super.clippingMatrix[14] = super.modelviewMatrix[12] * super.projectionMatrix[2] + super.modelviewMatrix[13] * super.projectionMatrix[6] + super.modelviewMatrix[14] * super.projectionMatrix[10] + super.modelviewMatrix[15] * super.projectionMatrix[14];
      super.clippingMatrix[15] = super.modelviewMatrix[12] * super.projectionMatrix[3] + super.modelviewMatrix[13] * super.projectionMatrix[7] + super.modelviewMatrix[14] * super.projectionMatrix[11] + super.modelviewMatrix[15] * super.projectionMatrix[15];
      super.frustum[0][0] = super.clippingMatrix[3] - super.clippingMatrix[0];
      super.frustum[0][1] = super.clippingMatrix[7] - super.clippingMatrix[4];
      super.frustum[0][2] = super.clippingMatrix[11] - super.clippingMatrix[8];
      super.frustum[0][3] = super.clippingMatrix[15] - super.clippingMatrix[12];
      this.normalize(super.frustum, 0);
      super.frustum[1][0] = super.clippingMatrix[3] + super.clippingMatrix[0];
      super.frustum[1][1] = super.clippingMatrix[7] + super.clippingMatrix[4];
      super.frustum[1][2] = super.clippingMatrix[11] + super.clippingMatrix[8];
      super.frustum[1][3] = super.clippingMatrix[15] + super.clippingMatrix[12];
      this.normalize(super.frustum, 1);
      super.frustum[2][0] = super.clippingMatrix[3] + super.clippingMatrix[1];
      super.frustum[2][1] = super.clippingMatrix[7] + super.clippingMatrix[5];
      super.frustum[2][2] = super.clippingMatrix[11] + super.clippingMatrix[9];
      super.frustum[2][3] = super.clippingMatrix[15] + super.clippingMatrix[13];
      this.normalize(super.frustum, 2);
      super.frustum[3][0] = super.clippingMatrix[3] - super.clippingMatrix[1];
      super.frustum[3][1] = super.clippingMatrix[7] - super.clippingMatrix[5];
      super.frustum[3][2] = super.clippingMatrix[11] - super.clippingMatrix[9];
      super.frustum[3][3] = super.clippingMatrix[15] - super.clippingMatrix[13];
      this.normalize(super.frustum, 3);
      super.frustum[4][0] = super.clippingMatrix[3] - super.clippingMatrix[2];
      super.frustum[4][1] = super.clippingMatrix[7] - super.clippingMatrix[6];
      super.frustum[4][2] = super.clippingMatrix[11] - super.clippingMatrix[10];
      super.frustum[4][3] = super.clippingMatrix[15] - super.clippingMatrix[14];
      this.normalize(super.frustum, 4);
      super.frustum[5][0] = super.clippingMatrix[3] + super.clippingMatrix[2];
      super.frustum[5][1] = super.clippingMatrix[7] + super.clippingMatrix[6];
      super.frustum[5][2] = super.clippingMatrix[11] + super.clippingMatrix[10];
      super.frustum[5][3] = super.clippingMatrix[15] + super.clippingMatrix[14];
      this.normalize(super.frustum, 5);
   }

}
