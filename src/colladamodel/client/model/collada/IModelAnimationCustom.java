package com.hea3ven.colladamodel.client.model.collada;

import net.minecraftforge.client.model.IModelCustom;

public interface IModelAnimationCustom extends IModelCustom {

   String getType();

   double getAnimationLength();

   void renderAnimationAll(double var1);

   void renderAnimationOnly(double var1, String ... var3);

   void renderAnimationPart(double var1, String var3);

   void renderAnimationAllExcept(double var1, String ... var3);
}
