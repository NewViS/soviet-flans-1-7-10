package com.hea3ven.colladamodel.client.model.collada;

import com.hea3ven.colladamodel.client.model.collada.Interpolation;
import com.hea3ven.colladamodel.client.model.collada.KeyFrame;

public class LinearInterpolation implements Interpolation {

   public double interpolate(double time, KeyFrame frame, KeyFrame nextFrame) {
      double s = (time - frame.getFrame()) / (nextFrame.getFrame() - frame.getFrame());
      return frame.getValue() + (nextFrame.getValue() - frame.getValue()) * s;
   }
}
