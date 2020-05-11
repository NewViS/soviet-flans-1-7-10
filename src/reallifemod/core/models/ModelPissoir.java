package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPissoir extends ModelBase {

   ModelRenderer part1;
   ModelRenderer part2;
   ModelRenderer part3;
   ModelRenderer part4;
   ModelRenderer part5;
   ModelRenderer part6;
   ModelRenderer part7;
   ModelRenderer part8;


   public ModelPissoir() {
      this.field_78090_t = 16;
      this.field_78089_u = 8;
      this.part1 = new ModelRenderer(this, 37, 0);
      this.part1.addBox(0.0F, 0.0F, -4.0F, 1, 15, 3);
      this.part1.setRotationPoint(4.0F, -1.0F, 8.0F);
      this.part1.setTextureSize(64, 32);
      this.part1.mirror = true;
      this.setRotation(this.part1, 0.0F, 0.0F, 0.0F);
      this.part2 = new ModelRenderer(this, 9, 16);
      this.part2.addBox(0.0F, 0.0F, 0.0F, 10, 15, 1);
      this.part2.setRotationPoint(-5.0F, -1.0F, 7.0F);
      this.part2.setTextureSize(64, 32);
      this.part2.mirror = true;
      this.setRotation(this.part2, 0.0F, 0.0F, 0.0F);
      this.part3 = new ModelRenderer(this, 9, 10);
      this.part3.addBox(0.0F, 0.0F, -6.0F, 8, 2, 1);
      this.part3.setRotationPoint(-4.0F, 12.0F, 6.0F);
      this.part3.setTextureSize(64, 32);
      this.part3.mirror = true;
      this.setRotation(this.part3, 0.0F, 0.0F, 0.0F);
      this.part4 = new ModelRenderer(this, 32, 19);
      this.part4.addBox(0.0F, 0.0F, -4.0F, 8, 1, 3);
      this.part4.setRotationPoint(-4.0F, -1.0F, 8.0F);
      this.part4.setTextureSize(64, 32);
      this.part4.mirror = true;
      this.setRotation(this.part4, 0.0F, 0.0F, 0.0F);
      this.part5 = new ModelRenderer(this, 0, 0);
      this.part5.addBox(0.0F, 0.0F, -6.0F, 10, 1, 8);
      this.part5.setRotationPoint(-5.0F, 14.0F, 6.0F);
      this.part5.setTextureSize(64, 32);
      this.part5.mirror = true;
      this.setRotation(this.part5, 0.0F, 0.0F, 0.0F);
      this.part6 = new ModelRenderer(this, 46, 0);
      this.part6.addBox(0.0F, 0.0F, -6.0F, 1, 2, 4);
      this.part6.setRotationPoint(-5.0F, 12.0F, 6.0F);
      this.part6.setTextureSize(64, 32);
      this.part6.mirror = true;
      this.setRotation(this.part6, 0.0F, 0.0F, 0.0F);
      this.part7 = new ModelRenderer(this, 46, 0);
      this.part7.addBox(0.0F, 0.0F, -6.0F, 1, 2, 4);
      this.part7.setRotationPoint(4.0F, 12.0F, 6.0F);
      this.part7.setTextureSize(64, 32);
      this.part7.mirror = true;
      this.setRotation(this.part7, 0.0F, 0.0F, 0.0F);
      this.part8 = new ModelRenderer(this, 37, 0);
      this.part8.addBox(0.0F, 0.0F, -4.0F, 1, 15, 3);
      this.part8.setRotationPoint(-5.0F, -1.0F, 8.0F);
      this.part8.setTextureSize(64, 32);
      this.part8.mirror = true;
      this.setRotation(this.part8, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.part1.render(f5);
      this.part2.render(f5);
      this.part3.render(f5);
      this.part4.render(f5);
      this.part5.render(f5);
      this.part6.render(f5);
      this.part7.render(f5);
      this.part8.render(f5);
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
