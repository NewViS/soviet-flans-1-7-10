package com.hea3ven.colladamodel.client.model.collada;

import com.hea3ven.colladamodel.client.model.collada.KeyFrame;

public interface Interpolation {

   double interpolate(double var1, KeyFrame var3, KeyFrame var4);
}
