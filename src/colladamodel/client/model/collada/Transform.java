package com.hea3ven.colladamodel.client.model.collada;

import com.hea3ven.colladamodel.client.model.collada.Animation;

public abstract class Transform {

   public abstract void apply();

   public abstract void applyAnimation(double var1);

   public abstract void setAnimation(String var1, Animation var2);

   public abstract double getAnimationLength();
}
