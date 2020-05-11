package com.hea3ven.colladamodel.client.model.collada;

import com.hea3ven.colladamodel.client.model.collada.Animation;
import com.hea3ven.colladamodel.client.model.collada.Transform;
import java.nio.DoubleBuffer;
import org.lwjgl.opengl.GL11;

public class Matrix extends Transform {

   private DoubleBuffer matrix;


   public Matrix(DoubleBuffer matrix) {
      this.matrix = matrix;
   }

   public DoubleBuffer getMatrix() {
      return this.matrix;
   }

   public void setMatrix(DoubleBuffer matrix) {
      this.matrix = matrix;
   }

   public void apply() {
      this.matrix.rewind();
      GL11.glMultMatrix(this.matrix);
   }

   public void applyAnimation(double frame) {
      this.apply();
   }

   public void setAnimation(String paramName, Animation anim) {}

   public double getAnimationLength() {
      return 0.0D;
   }
}
