package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWreath extends ModelBase {

   ModelRenderer part1;
   ModelRenderer part2;
   ModelRenderer part3;
   ModelRenderer part4;


   public ModelWreath() {
      this.field_78090_t = 64;
      this.field_78089_u = 32;
      this.part1 = new ModelRenderer(this, 0, 0);
      this.part1.addBox(-5.0F, 0.0F, 0.0F, 2, 4, 2);
      this.part1.setRotationPoint(7.0F, 13.0F, 6.0F);
      this.part1.setTextureSize(64, 32);
      this.part1.mirror = true;
      this.setRotation(this.part1, 0.0F, 0.0F, 0.0F);
      this.part2 = new ModelRenderer(this, 0, 6);
      this.part2.addBox(-4.0F, 0.0F, 0.0F, 8, 2, 2);
      this.part2.setRotationPoint(0.0F, 11.0F, 6.0F);
      this.part2.setTextureSize(64, 32);
      this.part2.mirror = true;
      this.setRotation(this.part2, 0.0F, 0.0F, 0.0F);
      this.part3 = new ModelRenderer(this, 0, 0);
      this.part3.addBox(-4.0F, 0.0F, 0.0F, 2, 4, 2);
      this.part3.setRotationPoint(0.0F, 13.0F, 6.0F);
      this.part3.setTextureSize(64, 32);
      this.part3.mirror = true;
      this.setRotation(this.part3, 0.0F, 0.0F, 0.0F);
      this.part4 = new ModelRenderer(this, 0, 6);
      this.part4.addBox(-4.0F, 0.0F, 0.0F, 8, 2, 2);
      this.part4.setRotationPoint(0.0F, 17.0F, 6.0F);
      this.part4.setTextureSize(64, 32);
      this.part4.mirror = true;
      this.setRotation(this.part4, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.part1.render(f5);
      this.part2.render(f5);
      this.part3.render(f5);
      this.part4.render(f5);
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
