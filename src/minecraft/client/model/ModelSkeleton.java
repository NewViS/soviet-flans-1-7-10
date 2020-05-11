package net.minecraft.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;

public class ModelSkeleton extends ModelZombie {

   public ModelSkeleton() {
      this(0.0F);
   }

   public ModelSkeleton(float var1) {
      super(var1, 0.0F, 64, 32);
      super.bipedRightArm = new ModelRenderer(this, 40, 16);
      super.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, var1);
      super.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
      super.bipedLeftArm = new ModelRenderer(this, 40, 16);
      super.bipedLeftArm.mirror = true;
      super.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, var1);
      super.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
      super.bipedRightLeg = new ModelRenderer(this, 0, 16);
      super.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, var1);
      super.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
      super.bipedLeftLeg = new ModelRenderer(this, 0, 16);
      super.bipedLeftLeg.mirror = true;
      super.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, var1);
      super.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
   }

   public void setLivingAnimations(EntityLivingBase var1, float var2, float var3, float var4) {
      super.aimedBow = ((EntitySkeleton)var1).getSkeletonType() == 1;
      super.setLivingAnimations(var1, var2, var3, var4);
   }

   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
   }
}
