package net.minecraft.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelZombie extends ModelBiped {

   public ModelZombie() {
      this(0.0F, false);
   }

   protected ModelZombie(float var1, float var2, int var3, int var4) {
      super(var1, var2, var3, var4);
   }

   public ModelZombie(float var1, boolean var2) {
      super(var1, 0.0F, 64, var2?32:64);
   }

   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
      float var8 = MathHelper.sin(super.onGround * 3.1415927F);
      float var9 = MathHelper.sin((1.0F - (1.0F - super.onGround) * (1.0F - super.onGround)) * 3.1415927F);
      super.bipedRightArm.rotateAngleZ = 0.0F;
      super.bipedLeftArm.rotateAngleZ = 0.0F;
      super.bipedRightArm.rotateAngleY = -(0.1F - var8 * 0.6F);
      super.bipedLeftArm.rotateAngleY = 0.1F - var8 * 0.6F;
      super.bipedRightArm.rotateAngleX = -1.5707964F;
      super.bipedLeftArm.rotateAngleX = -1.5707964F;
      super.bipedRightArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
      super.bipedLeftArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
      super.bipedRightArm.rotateAngleZ += MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F;
      super.bipedLeftArm.rotateAngleZ -= MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F;
      super.bipedRightArm.rotateAngleX += MathHelper.sin(var3 * 0.067F) * 0.05F;
      super.bipedLeftArm.rotateAngleX -= MathHelper.sin(var3 * 0.067F) * 0.05F;
   }
}
