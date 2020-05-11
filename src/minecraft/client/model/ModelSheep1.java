package net.minecraft.client.model;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;

public class ModelSheep1 extends ModelQuadruped {

   private float field_78152_i;


   public ModelSheep1() {
      super(12, 0.0F);
      super.head = new ModelRenderer(this, 0, 0);
      super.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
      super.head.setRotationPoint(0.0F, 6.0F, -8.0F);
      super.body = new ModelRenderer(this, 28, 8);
      super.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
      super.body.setRotationPoint(0.0F, 5.0F, 2.0F);
      float var1 = 0.5F;
      super.leg1 = new ModelRenderer(this, 0, 16);
      super.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
      super.leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
      super.leg2 = new ModelRenderer(this, 0, 16);
      super.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
      super.leg2.setRotationPoint(3.0F, 12.0F, 7.0F);
      super.leg3 = new ModelRenderer(this, 0, 16);
      super.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
      super.leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);
      super.leg4 = new ModelRenderer(this, 0, 16);
      super.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
      super.leg4.setRotationPoint(3.0F, 12.0F, -5.0F);
   }

   public void setLivingAnimations(EntityLivingBase var1, float var2, float var3, float var4) {
      super.setLivingAnimations(var1, var2, var3, var4);
      super.head.rotationPointY = 6.0F + ((EntitySheep)var1).func_70894_j(var4) * 9.0F;
      this.field_78152_i = ((EntitySheep)var1).func_70890_k(var4);
   }

   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
      super.head.rotateAngleX = this.field_78152_i;
   }
}
