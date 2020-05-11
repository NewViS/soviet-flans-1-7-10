package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelParkBench extends ModelBase {

   ModelRenderer leg1;
   ModelRenderer leg2;
   ModelRenderer leg3;
   ModelRenderer leg4;
   ModelRenderer back;
   ModelRenderer seat;


   public ModelParkBench() {
      this.field_78090_t = 128;
      this.field_78089_u = 32;
      this.leg1 = new ModelRenderer(this, 107, 18);
      this.leg1.addBox(0.0F, 0.0F, 0.0F, 1, 10, 1);
      this.leg1.setRotationPoint(-24.0F, 14.0F, 5.0F);
      this.leg1.setTextureSize(64, 32);
      this.leg1.mirror = true;
      this.setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
      this.leg2 = new ModelRenderer(this, 107, 18);
      this.leg2.addBox(0.0F, 0.0F, 0.0F, 1, 10, 1);
      this.leg2.setRotationPoint(23.0F, 14.0F, 5.0F);
      this.leg2.setTextureSize(64, 32);
      this.leg2.mirror = true;
      this.setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
      this.leg3 = new ModelRenderer(this, 107, 18);
      this.leg3.addBox(0.0F, 0.0F, 0.0F, 1, 10, 1);
      this.leg3.setRotationPoint(-24.0F, 14.0F, -7.0F);
      this.leg3.setTextureSize(64, 32);
      this.leg3.mirror = true;
      this.setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
      this.leg4 = new ModelRenderer(this, 107, 18);
      this.leg4.addBox(0.0F, 0.0F, 0.0F, 1, 10, 1);
      this.leg4.setRotationPoint(23.0F, 14.0F, -7.0F);
      this.leg4.setTextureSize(64, 32);
      this.leg4.mirror = true;
      this.setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
      this.back = new ModelRenderer(this, 0, 18);
      this.back.addBox(0.0F, 0.0F, 0.0F, 48, 11, 1);
      this.back.setRotationPoint(-24.0F, 2.0F, 5.0F);
      this.back.setTextureSize(64, 32);
      this.back.mirror = true;
      this.setRotation(this.back, 0.0F, 0.0F, 0.0F);
      this.seat = new ModelRenderer(this, 0, 0);
      this.seat.addBox(0.0F, 0.0F, 0.0F, 48, 1, 13);
      this.seat.setRotationPoint(-24.0F, 13.0F, -7.0F);
      this.seat.setTextureSize(64, 32);
      this.seat.mirror = true;
      this.setRotation(this.seat, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.leg1.render(f5);
      this.leg2.render(f5);
      this.leg3.render(f5);
      this.leg4.render(f5);
      this.back.render(f5);
      this.seat.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }
}
