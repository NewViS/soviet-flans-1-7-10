package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTGX extends ModelBase {

   ModelRenderer back;
   ModelRenderer front;
   ModelRenderer wheel1;
   ModelRenderer wheel2;
   ModelRenderer wheel3;
   ModelRenderer wheel4;


   public ModelTGX() {
      this.field_78090_t = 512;
      this.field_78089_u = 256;
      this.back = new ModelRenderer(this, 180, 0);
      this.back.addBox(-24.0F, 0.0F, 0.0F, 48, 8, 50);
      this.back.setRotationPoint(0.0F, 9.0F, 18.0F);
      this.back.setTextureSize(512, 256);
      this.back.mirror = true;
      this.setRotation(this.back, 0.0F, 0.0F, 0.0F);
      this.front = new ModelRenderer(this, 0, 0);
      this.front.addBox(-24.0F, -24.0F, 0.0F, 48, 48, 38);
      this.front.setRotationPoint(0.0F, -7.0F, -20.0F);
      this.front.setTextureSize(512, 256);
      this.front.mirror = true;
      this.setRotation(this.front, 0.0F, 0.0F, 0.0F);
      this.wheel1 = new ModelRenderer(this, 0, 100);
      this.wheel1.addBox(0.0F, -8.0F, -8.0F, 8, 16, 16);
      this.wheel1.setRotationPoint(17.0F, 16.0F, 62.0F);
      this.wheel1.setTextureSize(512, 256);
      this.wheel1.mirror = true;
      this.setRotation(this.wheel1, 0.0F, 0.0F, 0.0F);
      this.wheel2 = new ModelRenderer(this, 0, 100);
      this.wheel2.addBox(-8.0F, -8.0F, -8.0F, 8, 16, 16);
      this.wheel2.setRotationPoint(-17.0F, 16.0F, 62.0F);
      this.wheel2.setTextureSize(512, 256);
      this.wheel2.mirror = true;
      this.setRotation(this.wheel2, 0.0F, 0.0F, 0.0F);
      this.wheel3 = new ModelRenderer(this, 0, 100);
      this.wheel3.addBox(-8.0F, -8.0F, -8.0F, 8, 16, 16);
      this.wheel3.setRotationPoint(-17.0F, 16.0F, 5.0F);
      this.wheel3.setTextureSize(512, 256);
      this.wheel3.mirror = true;
      this.setRotation(this.wheel3, 0.0F, 0.0F, 0.0F);
      this.wheel4 = new ModelRenderer(this, 0, 100);
      this.wheel4.addBox(0.0F, -8.0F, -8.0F, 8, 16, 16);
      this.wheel4.setRotationPoint(17.0F, 16.0F, 5.0F);
      this.wheel4.setTextureSize(512, 256);
      this.wheel4.mirror = true;
      this.setRotation(this.wheel4, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.back.render(f5);
      this.front.render(f5);
      this.wheel1.render(f5);
      this.wheel2.render(f5);
      this.wheel3.render(f5);
      this.wheel4.render(f5);
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
