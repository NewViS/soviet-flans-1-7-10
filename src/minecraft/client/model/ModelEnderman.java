package net.minecraft.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEnderman extends ModelBiped {

   public boolean isCarrying;
   public boolean isAttacking;


   public ModelEnderman() {
      super(0.0F, -14.0F, 64, 32);
      float var1 = -14.0F;
      float var2 = 0.0F;
      super.bipedHeadwear = new ModelRenderer(this, 0, 16);
      super.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, var2 - 0.5F);
      super.bipedHeadwear.setRotationPoint(0.0F, 0.0F + var1, 0.0F);
      super.bipedBody = new ModelRenderer(this, 32, 16);
      super.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, var2);
      super.bipedBody.setRotationPoint(0.0F, 0.0F + var1, 0.0F);
      super.bipedRightArm = new ModelRenderer(this, 56, 0);
      super.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, var2);
      super.bipedRightArm.setRotationPoint(-3.0F, 2.0F + var1, 0.0F);
      super.bipedLeftArm = new ModelRenderer(this, 56, 0);
      super.bipedLeftArm.mirror = true;
      super.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, var2);
      super.bipedLeftArm.setRotationPoint(5.0F, 2.0F + var1, 0.0F);
      super.bipedRightLeg = new ModelRenderer(this, 56, 0);
      super.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, var2);
      super.bipedRightLeg.setRotationPoint(-2.0F, 12.0F + var1, 0.0F);
      super.bipedLeftLeg = new ModelRenderer(this, 56, 0);
      super.bipedLeftLeg.mirror = true;
      super.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, var2);
      super.bipedLeftLeg.setRotationPoint(2.0F, 12.0F + var1, 0.0F);
   }

   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
      super.bipedHead.showModel = true;
      float var8 = -14.0F;
      super.bipedBody.rotateAngleX = 0.0F;
      super.bipedBody.rotationPointY = var8;
      super.bipedBody.rotationPointZ = -0.0F;
      super.bipedRightLeg.rotateAngleX -= 0.0F;
      super.bipedLeftLeg.rotateAngleX -= 0.0F;
      super.bipedRightArm.rotateAngleX = (float)((double)super.bipedRightArm.rotateAngleX * 0.5D);
      super.bipedLeftArm.rotateAngleX = (float)((double)super.bipedLeftArm.rotateAngleX * 0.5D);
      super.bipedRightLeg.rotateAngleX = (float)((double)super.bipedRightLeg.rotateAngleX * 0.5D);
      super.bipedLeftLeg.rotateAngleX = (float)((double)super.bipedLeftLeg.rotateAngleX * 0.5D);
      float var9 = 0.4F;
      if(super.bipedRightArm.rotateAngleX > var9) {
         super.bipedRightArm.rotateAngleX = var9;
      }

      if(super.bipedLeftArm.rotateAngleX > var9) {
         super.bipedLeftArm.rotateAngleX = var9;
      }

      if(super.bipedRightArm.rotateAngleX < -var9) {
         super.bipedRightArm.rotateAngleX = -var9;
      }

      if(super.bipedLeftArm.rotateAngleX < -var9) {
         super.bipedLeftArm.rotateAngleX = -var9;
      }

      if(super.bipedRightLeg.rotateAngleX > var9) {
         super.bipedRightLeg.rotateAngleX = var9;
      }

      if(super.bipedLeftLeg.rotateAngleX > var9) {
         super.bipedLeftLeg.rotateAngleX = var9;
      }

      if(super.bipedRightLeg.rotateAngleX < -var9) {
         super.bipedRightLeg.rotateAngleX = -var9;
      }

      if(super.bipedLeftLeg.rotateAngleX < -var9) {
         super.bipedLeftLeg.rotateAngleX = -var9;
      }

      if(this.isCarrying) {
         super.bipedRightArm.rotateAngleX = -0.5F;
         super.bipedLeftArm.rotateAngleX = -0.5F;
         super.bipedRightArm.rotateAngleZ = 0.05F;
         super.bipedLeftArm.rotateAngleZ = -0.05F;
      }

      super.bipedRightArm.rotationPointZ = 0.0F;
      super.bipedLeftArm.rotationPointZ = 0.0F;
      super.bipedRightLeg.rotationPointZ = 0.0F;
      super.bipedLeftLeg.rotationPointZ = 0.0F;
      super.bipedRightLeg.rotationPointY = 9.0F + var8;
      super.bipedLeftLeg.rotationPointY = 9.0F + var8;
      super.bipedHead.rotationPointZ = -0.0F;
      super.bipedHead.rotationPointY = var8 + 1.0F;
      super.bipedHeadwear.rotationPointX = super.bipedHead.rotationPointX;
      super.bipedHeadwear.rotationPointY = super.bipedHead.rotationPointY;
      super.bipedHeadwear.rotationPointZ = super.bipedHead.rotationPointZ;
      super.bipedHeadwear.rotateAngleX = super.bipedHead.rotateAngleX;
      super.bipedHeadwear.rotateAngleY = super.bipedHead.rotateAngleY;
      super.bipedHeadwear.rotateAngleZ = super.bipedHead.rotateAngleZ;
      if(this.isAttacking) {
         float var10 = 1.0F;
         super.bipedHead.rotationPointY -= var10 * 5.0F;
      }

   }
}
