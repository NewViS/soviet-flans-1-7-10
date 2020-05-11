package net.minecraft.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelZombieVillager extends ModelBiped {

   public ModelZombieVillager() {
      this(0.0F, 0.0F, false);
   }

   public ModelZombieVillager(float var1, float var2, boolean var3) {
      super(var1, 0.0F, 64, var3?32:64);
      if(var3) {
         super.bipedHead = new ModelRenderer(this, 0, 0);
         super.bipedHead.addBox(-4.0F, -10.0F, -4.0F, 8, 6, 8, var1);
         super.bipedHead.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
      } else {
         super.bipedHead = new ModelRenderer(this);
         super.bipedHead.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
         super.bipedHead.setTextureOffset(0, 32).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, var1);
         super.bipedHead.setTextureOffset(24, 32).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, var1);
      }

   }

   public int func_82897_a() {
      return 10;
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
